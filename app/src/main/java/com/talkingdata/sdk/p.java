package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
class p implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;
    final /* synthetic */ zz d;

    p(zz zzVar, String str, String str2, a aVar) {
        this.d = zzVar;
        this.a = str;
        this.b = str2;
        this.c = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            cb.a();
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 1);
            aVar.a.put("appId", this.a != null ? this.a : "");
            aVar.a.put("channelId", this.b != null ? this.b : "");
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.c);
            aVar.a.put("action", "init");
            Message.obtain(zz.c(), 101, aVar).sendToTarget();
            cc.a();
        } catch (Throwable unused) {
        }
    }
}
