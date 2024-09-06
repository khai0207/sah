package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
class s implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ a b;
    final /* synthetic */ zz c;

    s(zz zzVar, String str, a aVar) {
        this.c = zzVar;
        this.a = str;
        this.b = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        zz.a aVar = new zz.a();
        aVar.a.put("apiType", 9);
        aVar.a.put(SpeechConstant.DOMAIN, "account");
        aVar.a.put("action", "roleCreate");
        aVar.a.put("parameter", this.a);
        aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.b);
        Message.obtain(zz.c(), 102, aVar).sendToTarget();
    }
}
