package com.unionpay.sdk;

/* loaded from: classes.dex */
class f extends ae {
    private static volatile f a;
    private v b = ar.c();

    static {
        ai.a().register(a());
    }

    private f() {
    }

    public static f a() {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f();
                }
            }
        }
        return a;
    }
}
