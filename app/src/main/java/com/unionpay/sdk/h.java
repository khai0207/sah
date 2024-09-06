package com.unionpay.sdk;

/* loaded from: classes.dex */
class h extends ae {
    private static volatile h a;
    private v b = ar.c();

    static {
        ai.a().register(a());
    }

    private h() {
    }

    public static h a() {
        if (a == null) {
            synchronized (h.class) {
                if (a == null) {
                    a = new h();
                }
            }
        }
        return a;
    }
}
