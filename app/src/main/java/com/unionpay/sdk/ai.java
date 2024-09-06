package com.unionpay.sdk;

/* loaded from: classes.dex */
class ai {
    private static volatile OttoBus a;

    ai() {
    }

    public static OttoBus a() {
        if (a == null) {
            synchronized (ai.class) {
                if (a == null) {
                    a = new OttoBus(OttoThreadEnforcer.ANY);
                }
            }
        }
        return a;
    }
}
