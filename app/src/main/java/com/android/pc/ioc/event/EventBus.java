package com.android.pc.ioc.event;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class EventBus {
    private static volatile EventBus defaultInstance;
    private boolean subscribed;
    static ExecutorService executorService = Executors.newCachedThreadPool();
    public static String TAG = "Event";
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();
    private final ThreadLocal<List<Object>> currentThreadEventQueue = new ThreadLocal<List<Object>>() { // from class: com.android.pc.ioc.event.EventBus.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public List<Object> initialValue() {
            return new ArrayList();
        }
    };
    private final ThreadLocal<BooleanWrapper> currentThreadIsPosting = new ThreadLocal<BooleanWrapper>() { // from class: com.android.pc.ioc.event.EventBus.2
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public BooleanWrapper initialValue() {
            return new BooleanWrapper();
        }
    };
    private String defaultMethodName = "onEvent";
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType = new HashMap();
    private final Map<Object, List<Class<?>>> typesBySubscriber = new HashMap();
    private final Map<Class<?>, Object> stickyEvents = new ConcurrentHashMap();
    private final HandlerPoster mainThreadPoster = new HandlerPoster(this, Looper.getMainLooper(), 10);
    private final BackgroundPoster backgroundPoster = new BackgroundPoster(this);
    private final AsyncPoster asyncPoster = new AsyncPoster(this);
    private final SubscriberMethodFinder subscriberMethodFinder = new SubscriberMethodFinder();
    private boolean logSubscriberExceptions = true;

    /* loaded from: classes.dex */
    interface PostCallback {
        void onPostCompleted(List<SubscriberExceptionEvent> list);
    }

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    public static void clearCaches() {
        SubscriberMethodFinder.clearCaches();
        eventTypesCache.clear();
    }

    public static void skipMethodNameVerificationFor(Class<?> cls) {
        SubscriberMethodFinder.skipMethodNameVerificationFor(cls);
    }

    public static void clearSkipMethodNameVerifications() {
        SubscriberMethodFinder.clearSkipMethodNameVerifications();
    }

    public void configureLogSubscriberExceptions(boolean z) {
        if (this.subscribed) {
            throw new EventBusException("This method must be called before any registration");
        }
        this.logSubscriberExceptions = z;
    }

    public void register(Object obj) {
        register(obj, this.defaultMethodName, false);
    }

    public void register(Object obj, String str) {
        register(obj, str, false);
    }

    public void registerSticky(Object obj) {
        register(obj, this.defaultMethodName, true);
    }

    public void registerSticky(Object obj, String str) {
        register(obj, str, true);
    }

    private void register(Object obj, String str, boolean z) {
        Iterator<SubscriberMethod> it = this.subscriberMethodFinder.findSubscriberMethods(obj.getClass(), str).iterator();
        while (it.hasNext()) {
            subscribe(obj, it.next(), z);
        }
    }

    public void register(Object obj, Class<?> cls, Class<?>... clsArr) {
        register(obj, this.defaultMethodName, false, cls, clsArr);
    }

    public synchronized void register(Object obj, String str, Class<?> cls, Class<?>... clsArr) {
        register(obj, str, false, cls, clsArr);
    }

    public void registerSticky(Object obj, Class<?> cls, Class<?>... clsArr) {
        register(obj, this.defaultMethodName, true, cls, clsArr);
    }

    public synchronized void registerSticky(Object obj, String str, Class<?> cls, Class<?>... clsArr) {
        register(obj, str, true, cls, clsArr);
    }

    private synchronized void register(Object obj, String str, boolean z, Class<?> cls, Class<?>... clsArr) {
        for (SubscriberMethod subscriberMethod : this.subscriberMethodFinder.findSubscriberMethods(obj.getClass(), str)) {
            if (cls == subscriberMethod.eventType) {
                subscribe(obj, subscriberMethod, z);
            } else if (clsArr != null) {
                int length = clsArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (clsArr[i] == subscriberMethod.eventType) {
                        subscribe(obj, subscriberMethod, z);
                        break;
                    }
                    i++;
                }
            }
        }
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod, boolean z) {
        Object obj2;
        this.subscribed = true;
        Class<?> cls = subscriberMethod.eventType;
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        Subscription subscription = new Subscription(obj, subscriberMethod);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.subscriptionsByEventType.put(cls, copyOnWriteArrayList);
        } else {
            Iterator<Subscription> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                if (it.next().equals(subscription)) {
                    throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
                }
            }
        }
        subscriberMethod.method.setAccessible(true);
        copyOnWriteArrayList.add(subscription);
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list == null) {
            list = new ArrayList<>();
            this.typesBySubscriber.put(obj, list);
        }
        list.add(cls);
        if (z) {
            synchronized (this.stickyEvents) {
                obj2 = this.stickyEvents.get(cls);
            }
            if (obj2 != null) {
                postToSubscription(subscription, obj2, Looper.getMainLooper() == Looper.myLooper());
            }
        }
    }

    public synchronized void unregister(Object obj, Class<?>... clsArr) {
        if (clsArr.length == 0) {
            throw new IllegalArgumentException("Provide at least one event class");
        }
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list != null) {
            for (Class<?> cls : clsArr) {
                unubscribeByEventType(obj, cls);
                list.remove(cls);
            }
            if (list.isEmpty()) {
                this.typesBySubscriber.remove(obj);
            }
        } else {
            Log.w(TAG, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    private void unubscribeByEventType(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i < size) {
                if (copyOnWriteArrayList.get(i).subscriber == obj) {
                    copyOnWriteArrayList.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    public synchronized void unregister(Object obj) {
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list != null) {
            Iterator<Class<?>> it = list.iterator();
            while (it.hasNext()) {
                unubscribeByEventType(obj, it.next());
            }
            this.typesBySubscriber.remove(obj);
        } else {
            Log.w(TAG, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void post(Object obj) {
        List<Object> list = this.currentThreadEventQueue.get();
        list.add(obj);
        BooleanWrapper booleanWrapper = this.currentThreadIsPosting.get();
        if (booleanWrapper.value) {
            return;
        }
        boolean z = Looper.getMainLooper() == Looper.myLooper();
        booleanWrapper.value = true;
        while (!list.isEmpty()) {
            try {
                postSingleEvent(list.remove(0), z);
            } finally {
                booleanWrapper.value = false;
            }
        }
    }

    public void postSticky(Object obj) {
        synchronized (this.stickyEvents) {
            this.stickyEvents.put(obj.getClass(), obj);
        }
        post(obj);
    }

    public Object getStickyEvent(Class<?> cls) {
        Object obj;
        synchronized (this.stickyEvents) {
            obj = this.stickyEvents.get(cls);
        }
        return obj;
    }

    public Object removeStickyEvent(Class<?> cls) {
        Object remove;
        synchronized (this.stickyEvents) {
            remove = this.stickyEvents.remove(cls);
        }
        return remove;
    }

    public boolean removeStickyEvent(Object obj) {
        synchronized (this.stickyEvents) {
            Class<?> cls = obj.getClass();
            if (!obj.equals(this.stickyEvents.get(cls))) {
                return false;
            }
            this.stickyEvents.remove(cls);
            return true;
        }
    }

    private void postSingleEvent(Object obj, boolean z) throws Error {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList;
        Class<?> cls = obj.getClass();
        List<Class<?>> findEventTypes = findEventTypes(cls);
        int size = findEventTypes.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            Class<?> cls2 = findEventTypes.get(i);
            synchronized (this) {
                copyOnWriteArrayList = this.subscriptionsByEventType.get(cls2);
            }
            if (copyOnWriteArrayList != null) {
                Iterator<Subscription> it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    postToSubscription(it.next(), obj, z);
                }
                z2 = true;
            }
        }
        if (z2 || cls == NoSubscriberEvent.class || cls == SubscriberExceptionEvent.class) {
            return;
        }
        post(new NoSubscriberEvent(this, obj));
    }

    /* renamed from: com.android.pc.ioc.event.EventBus$3, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$android$pc$ioc$event$ThreadMode;

        static {
            int[] iArr = new int[ThreadMode.values().length];
            $SwitchMap$com$android$pc$ioc$event$ThreadMode = iArr;
            try {
                iArr[ThreadMode.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$pc$ioc$event$ThreadMode[ThreadMode.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$pc$ioc$event$ThreadMode[ThreadMode.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$pc$ioc$event$ThreadMode[ThreadMode.Async.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void postToSubscription(Subscription subscription, Object obj, boolean z) {
        int i = AnonymousClass3.$SwitchMap$com$android$pc$ioc$event$ThreadMode[subscription.subscriberMethod.threadMode.ordinal()];
        if (i == 1) {
            invokeSubscriber(subscription, obj);
            return;
        }
        if (i == 2) {
            if (z) {
                invokeSubscriber(subscription, obj);
                return;
            } else {
                this.mainThreadPoster.enqueue(subscription, obj);
                return;
            }
        }
        if (i == 3) {
            if (z) {
                this.backgroundPoster.enqueue(subscription, obj);
                return;
            } else {
                invokeSubscriber(subscription, obj);
                return;
            }
        }
        if (i == 4) {
            this.asyncPoster.enqueue(subscription, obj);
            return;
        }
        throw new IllegalStateException("Unknown thread mode: " + subscription.subscriberMethod.threadMode);
    }

    private List<Class<?>> findEventTypes(Class<?> cls) {
        List<Class<?>> list;
        synchronized (eventTypesCache) {
            list = eventTypesCache.get(cls);
            if (list == null) {
                list = new ArrayList<>();
                for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    addInterfaces(list, cls2.getInterfaces());
                }
                eventTypesCache.put(cls, list);
            }
        }
        return list;
    }

    static void addInterfaces(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                addInterfaces(list, cls.getInterfaces());
            }
        }
    }

    void invokeSubscriber(PendingPost pendingPost) {
        Object obj = pendingPost.event;
        Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        invokeSubscriber(subscription, obj);
    }

    void invokeSubscriber(Subscription subscription, Object obj) throws Error {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, obj);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unexpected exception", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (obj instanceof SubscriberExceptionEvent) {
                Log.e(TAG, "SubscriberExceptionEvent subscriber " + subscription.subscriber.getClass() + " threw an exception", cause);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                Log.e(TAG, "Initial event " + subscriberExceptionEvent.causingEvent + " caused exception in " + subscriberExceptionEvent.causingSubscriber, subscriberExceptionEvent.throwable);
                return;
            }
            if (this.logSubscriberExceptions) {
                Log.e(TAG, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + subscription.subscriber.getClass(), cause);
            }
            post(new SubscriberExceptionEvent(this, cause, obj, subscription.subscriber));
        }
    }

    /* loaded from: classes.dex */
    static final class BooleanWrapper {
        boolean value;

        BooleanWrapper() {
        }
    }
}
