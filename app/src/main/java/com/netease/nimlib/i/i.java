package com.netease.nimlib.i;

import java.util.HashMap;
import java.util.Map;

/* compiled from: ServiceManager.java */
/* loaded from: classes.dex */
public final class i {
    private final Map<Class<?>, Object> a = new HashMap();

    public <T> T a(Class<T> cls) {
        T t;
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("only accept interface: " + cls);
        }
        synchronized (this.a) {
            t = (T) this.a.get(cls);
            if (t == null) {
                t = (T) g.a(cls);
                this.a.put(cls, t);
            }
        }
        return t;
    }
}
