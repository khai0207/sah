package com.unionpay.sdk;

import android.os.Message;
import com.unionpay.sdk.r;

/* loaded from: classes.dex */
final class z implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;

    z(int i, String str) {
        this.a = i;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (r.b) {
                r.a aVar = new r.a();
                aVar.a.put("apiType", Integer.valueOf(this.a));
                aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
                aVar.a.put("pageName", aw.a(this.b));
                Message.obtain(aj.a(), 102, aVar).sendToTarget();
                r.c = true;
            }
        } catch (Throwable th) {
            al.a(th);
        }
    }
}
