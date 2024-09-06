package com.unionpay.sdk;

/* loaded from: classes.dex */
public interface OttoThreadEnforcer {
    public static final OttoThreadEnforcer ANY = new o();
    public static final OttoThreadEnforcer MAIN = new p();

    void enforce(OttoBus ottoBus);
}
