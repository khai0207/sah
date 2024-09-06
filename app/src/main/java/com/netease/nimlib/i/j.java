package com.netease.nimlib.i;

/* compiled from: ServiceRemoteImpl.java */
/* loaded from: classes.dex */
public abstract class j {
    private static final ThreadLocal<k> a = new ThreadLocal<>();

    public static final void a(k kVar) {
        a.set(kVar);
    }

    public static final void a() {
        a.remove();
    }

    protected k b() {
        return a.get();
    }
}
