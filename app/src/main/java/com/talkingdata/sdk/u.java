package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
class u implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ zz c;

    u(zz zzVar, a aVar, String str) {
        this.c = zzVar;
        this.a = aVar;
        this.b = str;
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
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
