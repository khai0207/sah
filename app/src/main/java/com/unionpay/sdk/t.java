package com.unionpay.sdk;

import android.content.Context;
import android.os.Message;
import com.unionpay.sdk.r;
import java.util.Map;

/* loaded from: classes.dex */
final class t implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ Map d;

    t(Context context, String str, String str2, Map map) {
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            al.a("onEvent being called!");
            r.a aVar = new r.a();
            aVar.a.put("context", this.a);
            aVar.a.put("apiType", 4);
            aVar.a.put("eventId", this.b);
            aVar.a.put("eventLabel", this.c);
            aVar.a.put("map", this.d);
            aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
            Message.obtain(aj.a(), 102, aVar).sendToTarget();
        } catch (Throwable th) {
            if (UPAgent.LOG_ON) {
                th.printStackTrace();
            }
        }
    }
}
