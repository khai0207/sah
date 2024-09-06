package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.af;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
class z implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ af.AccountType c;
    final /* synthetic */ zz d;

    z(zz zzVar, a aVar, String str, af.AccountType accountType) {
        this.d = zzVar;
        this.a = aVar;
        this.b = str;
        this.c = accountType;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 9);
            aVar.a.put(SpeechConstant.DOMAIN, "account");
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
            aVar.a.put("action", "apply");
            aVar.a.put("accountId", this.b);
            if (this.c != null) {
                aVar.a.put("type", this.c.name());
            }
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
