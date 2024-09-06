package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.zz;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
class j implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ String g;
    final /* synthetic */ int h;
    final /* synthetic */ zz i;

    j(zz zzVar, a aVar, String str, String str2, int i, String str3, String str4, String str5, int i2) {
        this.i = zzVar;
        this.a = aVar;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 8);
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
            aVar.a.put(SpeechConstant.DOMAIN, "iap");
            aVar.a.put("action", "currencyPurchase");
            TreeMap treeMap = new TreeMap();
            treeMap.put("accountId", this.b);
            treeMap.put("orderId", this.c);
            treeMap.put("amount", Integer.valueOf(this.d));
            treeMap.put("currencyType", this.e);
            treeMap.put("payType", this.f);
            treeMap.put("itemId", this.g);
            treeMap.put("itemCount", Integer.valueOf(this.h));
            aVar.a.put("data", treeMap);
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
