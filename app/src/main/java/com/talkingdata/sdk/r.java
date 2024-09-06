package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.zz;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
class r implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ a b;
    final /* synthetic */ zz c;

    r(zz zzVar, String str, a aVar) {
        this.c = zzVar;
        this.a = str;
        this.b = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            cv.a().setDeepLink(this.a);
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 8);
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.b);
            aVar.a.put(SpeechConstant.DOMAIN, "app");
            aVar.a.put("action", "deeplink");
            TreeMap treeMap = new TreeMap();
            treeMap.put("link", this.a);
            aVar.a.put("data", treeMap);
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
