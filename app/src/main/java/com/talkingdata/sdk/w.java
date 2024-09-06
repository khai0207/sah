package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.af;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
class w implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ af.AccountType c;
    final /* synthetic */ String d;
    final /* synthetic */ zz e;

    w(zz zzVar, a aVar, String str, af.AccountType accountType, String str2) {
        this.e = zzVar;
        this.a = aVar;
        this.b = str;
        this.c = accountType;
        this.d = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 9);
            aVar.a.put(SpeechConstant.DOMAIN, "account");
            aVar.a.put("action", "login");
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
            aVar.a.put("accountId", this.b);
            if (this.c != null) {
                aVar.a.put("type", this.c.name());
            }
            if (this.d != null) {
                aVar.a.put(com.alipay.sdk.m.h.c.e, this.d);
            }
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
