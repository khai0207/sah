package com.unionpay.sdk;

/* loaded from: classes.dex */
final class k extends ThreadLocal {
    final /* synthetic */ OttoBus a;

    k(OttoBus ottoBus) {
        this.a = ottoBus;
    }

    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ Object initialValue() {
        return false;
    }
}
