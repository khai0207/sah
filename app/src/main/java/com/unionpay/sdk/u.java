package com.unionpay.sdk;

import android.content.Context;
import android.os.Message;
import com.unionpay.sdk.r;

/* loaded from: classes.dex */
final class u implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;

    u(String str, Context context, String str2) {
        this.a = str;
        this.b = context;
        this.c = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            al.a("onResume being called! pageName: " + this.a);
            r.a aVar = new r.a();
            aVar.a.put("context", this.b);
            aVar.a.put("apiType", 2);
            aVar.a.put("occurTime", Long.valueOf(System.currentTimeMillis()));
            aVar.a.put("pageName", this.c);
            Message.obtain(aj.a(), 102, aVar).sendToTarget();
        } catch (Throwable th) {
            if (UPAgent.LOG_ON) {
                th.printStackTrace();
            }
        }
    }
}
