package com.unionpay.mobile.android.upviews;

import com.unionpay.mobile.android.upviews.b;
import java.util.TimerTask;

/* loaded from: classes.dex */
final class c extends TimerTask {
    final /* synthetic */ b.d a;

    c(b.d dVar) {
        this.a = dVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        boolean z;
        z = b.this.e;
        if (!z) {
            b.this.b.sendEmptyMessage(3);
        }
        b.this.d.cancel();
        b.this.d.purge();
    }
}
