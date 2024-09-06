package com.unionpay.sdk;

import android.content.Context;
import android.os.Message;
import com.unionpay.sdk.r;

/* loaded from: classes.dex */
final class x implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;

    x(String str, Context context) {
        this.a = str;
        this.b = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            al.a("onPause being called! pageName: " + this.a);
            r.a aVar = new r.a();
            aVar.a.put("context", this.b);
            aVar.a.put("apiType", 3);
            aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
            aVar.a.put("pageName", this.a);
            Message.obtain(aj.a(), 102, aVar).sendToTarget();
        } catch (Throwable th) {
            if (UPAgent.LOG_ON) {
                th.printStackTrace();
            }
        }
    }
}
