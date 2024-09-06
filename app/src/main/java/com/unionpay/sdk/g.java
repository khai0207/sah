package com.unionpay.sdk;

/* loaded from: classes.dex */
class g extends ae {
    private static volatile g a;

    static {
        ai.a().register(a());
    }

    private g() {
    }

    public static g a() {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    a = new g();
                }
            }
        }
        return a;
    }
}
