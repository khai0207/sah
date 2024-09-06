package com.unionpay.sdk;

import android.content.Context;
import android.os.Message;
import com.unionpay.sdk.r;

/* loaded from: classes.dex */
final class w implements Runnable {
    final /* synthetic */ Context a;

    w(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            al.a("onResume(Context, appId, partnerid) being called!");
            r.a aVar = new r.a();
            aVar.a.put("context", this.a);
            aVar.a.put("apiType", 2);
            aVar.a.put("occurTime", Long.valueOf(System.currentTimeMillis()));
            aVar.a.put("isSessionOnly", "0");
            Message.obtain(aj.a(), 102, aVar).sendToTarget();
        } catch (Throwable th) {
            if (UPAgent.LOG_ON) {
                th.printStackTrace();
            }
        }
    }
}
