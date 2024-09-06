package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.zz;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
class h implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ zz g;

    h(zz zzVar, a aVar, String str, String str2, int i, String str3, String str4) {
        this.g = zzVar;
        this.a = aVar;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
        this.f = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 8);
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
            aVar.a.put(SpeechConstant.DOMAIN, "iap");
            aVar.a.put("action", "recharge");
            TreeMap treeMap = new TreeMap();
            treeMap.put("accountId", this.b);
            treeMap.put("orderId", this.c);
            treeMap.put("amount", Integer.valueOf(this.d));
            treeMap.put("currencyType", this.e);
            treeMap.put("payType", this.f);
            aVar.a.put("data", treeMap);
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
