package com.unionpay.sdk;

import android.os.Looper;

/* loaded from: classes.dex */
final class p implements OttoThreadEnforcer {
    p() {
    }

    @Override // com.unionpay.sdk.OttoThreadEnforcer
    public final void enforce(OttoBus ottoBus) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        throw new IllegalStateException("Event bus " + ottoBus + " accessed from non-main thread " + Looper.myLooper());
    }
}
