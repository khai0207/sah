package com.netease.nimlib.o;

import android.util.LruCache;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NimObjectTempCache.java */
/* loaded from: classes.dex */
public class r {
    private static Map<String, LruCache<Object, Object>> b;
    private final Object a;

    /* compiled from: NimObjectTempCache.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final r a = new r();
    }

    private r() {
        this.a = new Object();
        b = new HashMap();
    }

    public void a(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return;
        }
        synchronized (this.a) {
            b(obj2.getClass()).put(obj, obj2);
        }
    }

    public <T> T a(Class<T> cls, Object obj) {
        T t;
        if (obj == null || cls == null) {
            return null;
        }
        synchronized (this.a) {
            t = (T) b(cls).get(obj);
        }
        return t;
    }

    private LruCache<Object, Object> b(Class cls) {
        String name = cls.getName();
        LruCache<Object, Object> lruCache = b.get(name);
        if (lruCache != null) {
            return lruCache;
        }
        LruCache<Object, Object> lruCache2 = new LruCache<>(512);
        b.put(name, lruCache2);
        return lruCache2;
    }

    public void a(Class cls) {
        if (cls == null) {
            return;
        }
        synchronized (this.a) {
            b(cls).evictAll();
        }
    }

    public static r a() {
        return a.a;
    }
}
