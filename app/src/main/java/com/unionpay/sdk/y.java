package com.unionpay.sdk;

import android.content.Context;
import android.os.Message;
import com.unionpay.sdk.r;

/* loaded from: classes.dex */
final class y implements Runnable {
    final /* synthetic */ Throwable a;
    final /* synthetic */ Context b;

    y(Throwable th, Context context) {
        this.a = th;
        this.b = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.a == null) {
                return;
            }
            if (this.b != null && !r.b) {
                r.a(this.b);
            }
            r.a aVar = new r.a();
            aVar.a.put("apiType", 5);
            aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
            aVar.a.put("throwable", this.a);
            Message.obtain(aj.a(), 102, aVar).sendToTarget();
        } catch (Throwable th) {
            al.a(th);
        }
    }
}
