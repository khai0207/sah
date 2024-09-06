package com.talkingdata.sdk;

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

/* compiled from: td */
/* loaded from: classes.dex */
public final class bk {
    private static volatile bk a;
    private final ConcurrentMap b = new ConcurrentHashMap();
    private final ThreadLocal c = new bl(this);
    private final ThreadLocal d = new bm(this);
    private final Map e = new HashMap();

    public static bk a() {
        if (a == null) {
            synchronized (bk.class) {
                if (a == null) {
                    a = new bk();
                }
            }
        }
        return a;
    }

    private bk() {
    }

    public void register(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            Map a2 = bn.a(obj);
            for (Class cls : a2.keySet()) {
                Set set = (Set) this.b.get(cls);
                if (set == null) {
                    set = new CopyOnWriteArraySet();
                    Set set2 = (Set) this.b.putIfAbsent(cls, set);
                    if (set2 != null) {
                        set = set2;
                    }
                }
                if (!set.addAll((Set) a2.get(cls))) {
                    return;
                }
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public void unregister(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            for (Map.Entry entry : bn.a(obj).entrySet()) {
                Set<bo> a2 = a((Class) entry.getKey());
                Collection<?> collection = (Collection) entry.getValue();
                if (a2 != null && a2.containsAll(collection)) {
                    for (bo boVar : a2) {
                        if (collection.contains(boVar)) {
                            boVar.b();
                        }
                    }
                    a2.removeAll(collection);
                }
                return;
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public void post(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            boolean z = false;
            Iterator it = b(obj.getClass()).iterator();
            while (it.hasNext()) {
                Set a2 = a((Class) it.next());
                if (a2 != null && !a2.isEmpty()) {
                    z = true;
                    Iterator it2 = a2.iterator();
                    while (it2.hasNext()) {
                        a(obj, (bo) it2.next());
                    }
                }
            }
            if (!z && !(obj instanceof bp)) {
                post(new bp(this, obj));
            }
            b();
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    protected void a(Object obj, bo boVar) {
        try {
            ((ConcurrentLinkedQueue) this.c.get()).offer(new a(obj, boVar));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    protected void b() {
        try {
            if (((Boolean) this.d.get()).booleanValue()) {
                return;
            }
            this.d.set(true);
            while (true) {
                a aVar = (a) ((ConcurrentLinkedQueue) this.c.get()).poll();
                if (aVar == null) {
                    return;
                }
                if (aVar.b.a()) {
                    b(aVar.a, aVar.b);
                }
            }
        } finally {
            this.d.set(false);
        }
    }

    protected void b(Object obj, bo boVar) {
        try {
            boVar.handleEvent(obj);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    Set a(Class cls) {
        try {
            return (Set) this.b.get(cls);
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    Set b(Class cls) {
        try {
            Set set = (Set) this.e.get(cls);
            if (set != null) {
                return set;
            }
            Set c = c(cls);
            this.e.put(cls, c);
            return c;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    private Set c(Class cls) {
        try {
            LinkedList linkedList = new LinkedList();
            HashSet hashSet = new HashSet();
            linkedList.add(cls);
            while (!linkedList.isEmpty()) {
                Class cls2 = (Class) linkedList.remove(0);
                hashSet.add(cls2);
                Class superclass = cls2.getSuperclass();
                if (superclass != null) {
                    linkedList.add(superclass);
                }
            }
            return hashSet;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    static class a {
        final Object a;
        final bo b;

        public a(Object obj, bo boVar) {
            this.a = obj;
            this.b = boVar;
        }
    }
}
