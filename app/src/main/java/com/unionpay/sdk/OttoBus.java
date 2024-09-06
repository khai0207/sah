package com.unionpay.sdk;

import com.unionpay.sdk.r;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes.dex */
public class OttoBus {
    public static final String DEFAULT_IDENTIFIER = "default";
    private final ConcurrentMap a;
    private final ConcurrentMap b;
    private final String c;
    private final OttoThreadEnforcer d;
    private final com.unionpay.sdk.a e;
    private final ThreadLocal f;
    private final ThreadLocal g;
    private final Map h;

    /* loaded from: classes.dex */
    static class a {
        final Object a;
        final l b;

        public a(Object obj, l lVar) {
            this.a = obj;
            this.b = lVar;
        }
    }

    public OttoBus() {
        this(DEFAULT_IDENTIFIER);
    }

    public OttoBus(OttoThreadEnforcer ottoThreadEnforcer) {
        this(ottoThreadEnforcer, DEFAULT_IDENTIFIER);
    }

    public OttoBus(OttoThreadEnforcer ottoThreadEnforcer, String str) {
        this(ottoThreadEnforcer, str, com.unionpay.sdk.a.a);
    }

    private OttoBus(OttoThreadEnforcer ottoThreadEnforcer, String str, com.unionpay.sdk.a aVar) {
        this.a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
        this.f = new j(this);
        this.g = new k(this);
        this.h = new HashMap();
        this.d = ottoThreadEnforcer;
        this.c = str;
        this.e = aVar;
    }

    public OttoBus(String str) {
        this(OttoThreadEnforcer.MAIN, str);
    }

    private Set a(Class cls) {
        return (Set) this.a.get(cls);
    }

    private void a(l lVar, m mVar) {
        Object obj;
        try {
            obj = mVar.c();
        } catch (InvocationTargetException e) {
            a("Producer " + mVar + " threw an exception.", e);
            obj = null;
        }
        if (obj == null) {
            return;
        }
        dispatch(obj, lVar);
    }

    private static void a(String str, InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause != null) {
            throw new RuntimeException(str + ": " + cause.getMessage(), cause);
        }
        throw new RuntimeException(str + ": " + invocationTargetException.getMessage(), invocationTargetException);
    }

    private static Set b(Class cls) {
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        while (true) {
            linkedList.add(cls);
            while (!linkedList.isEmpty()) {
                Class cls2 = (Class) linkedList.remove(0);
                hashSet.add(cls2);
                cls = cls2.getSuperclass();
                if (cls != null) {
                    break;
                }
            }
            return hashSet;
        }
    }

    protected void dispatch(Object obj, l lVar) {
        try {
            if (r.a && (obj instanceof r.a)) {
                al.b("[dispatch]", String.valueOf(((r.a) obj).a.get("apiType")), lVar.toString());
            }
            lVar.a(obj);
        } catch (InvocationTargetException e) {
            a("Could not dispatch event: " + obj.getClass() + " to handler " + lVar, e);
        }
    }

    protected void dispatchQueuedEvents() {
        if (((Boolean) this.g.get()).booleanValue()) {
            return;
        }
        this.g.set(true);
        while (true) {
            try {
                a aVar = (a) ((ConcurrentLinkedQueue) this.f.get()).poll();
                if (aVar == null) {
                    return;
                }
                if (aVar.b.a()) {
                    dispatch(aVar.a, aVar.b);
                }
            } finally {
                this.g.set(false);
            }
        }
    }

    protected void enqueueEvent(Object obj, l lVar) {
        ((ConcurrentLinkedQueue) this.f.get()).offer(new a(obj, lVar));
    }

    public void post(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Event to post must not be null.");
        }
        this.d.enforce(this);
        Class<?> cls = obj.getClass();
        Set set = (Set) this.h.get(cls);
        if (set == null) {
            set = b(cls);
            this.h.put(cls, set);
        }
        boolean z = false;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Set a2 = a((Class) it.next());
            if (a2 != null && !a2.isEmpty()) {
                z = true;
                Iterator it2 = a2.iterator();
                while (it2.hasNext()) {
                    enqueueEvent(obj, (l) it2.next());
                }
            }
        }
        if (!z && !(obj instanceof OttoDeadEvent)) {
            post(new OttoDeadEvent(this, obj));
        }
        dispatchQueuedEvents();
    }

    public void register(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Object to register must not be null.");
        }
        this.d.enforce(this);
        Map a2 = this.e.a(obj);
        for (Class cls : a2.keySet()) {
            m mVar = (m) a2.get(cls);
            m mVar2 = (m) this.b.putIfAbsent(cls, mVar);
            if (mVar2 != null) {
                throw new IllegalArgumentException("Producer method for type " + cls + " found on type " + mVar.a.getClass() + ", but already registered by type " + mVar2.a.getClass() + ".");
            }
            Set set = (Set) this.a.get(cls);
            if (set != null && !set.isEmpty()) {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    a((l) it.next(), mVar);
                }
            }
        }
        Map b = this.e.b(obj);
        for (Class cls2 : b.keySet()) {
            Set set2 = (Set) this.a.get(cls2);
            if (set2 == null) {
                set2 = new CopyOnWriteArraySet();
                Set set3 = (Set) this.a.putIfAbsent(cls2, set2);
                if (set3 != null) {
                    set2 = set3;
                }
            }
            if (!set2.addAll((Set) b.get(cls2))) {
                throw new IllegalArgumentException("Object already registered.");
            }
        }
        for (Map.Entry entry : b.entrySet()) {
            m mVar3 = (m) this.b.get((Class) entry.getKey());
            if (mVar3 != null && mVar3.a()) {
                for (l lVar : (Set) entry.getValue()) {
                    if (mVar3.a()) {
                        if (lVar.a()) {
                            a(lVar, mVar3);
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        return "[Bus \"" + this.c + "\"]";
    }

    public void unregister(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Object to unregister must not be null.");
        }
        this.d.enforce(this);
        for (Map.Entry entry : this.e.a(obj).entrySet()) {
            Class cls = (Class) entry.getKey();
            m mVar = (m) this.b.get(cls);
            m mVar2 = (m) entry.getValue();
            if (mVar2 == null || !mVar2.equals(mVar)) {
                throw new IllegalArgumentException("Missing event producer for an annotated method. Is " + obj.getClass() + " registered?");
            }
            ((m) this.b.remove(cls)).b();
        }
        for (Map.Entry entry2 : this.e.b(obj).entrySet()) {
            Set<l> a2 = a((Class) entry2.getKey());
            Collection<?> collection = (Collection) entry2.getValue();
            if (a2 == null || !a2.containsAll(collection)) {
                throw new IllegalArgumentException("Missing event handler for an annotated method. Is " + obj.getClass() + " registered?");
            }
            for (l lVar : a2) {
                if (collection.contains(lVar)) {
                    lVar.b();
                }
            }
            a2.removeAll(collection);
        }
    }
}
