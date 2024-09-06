package com.unionpay.sdk;

import android.content.Context;

/* loaded from: classes.dex */
final class aa implements Runnable {
    final /* synthetic */ Context a;

    aa(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ad.e = aw.a();
        r.b();
        r.e();
    }
}
