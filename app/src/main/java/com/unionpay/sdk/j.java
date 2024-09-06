package com.unionpay.sdk;

import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
final class j extends ThreadLocal {
    final /* synthetic */ OttoBus a;

    j(OttoBus ottoBus) {
        this.a = ottoBus;
    }

    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ Object initialValue() {
        return new ConcurrentLinkedQueue();
    }
}
