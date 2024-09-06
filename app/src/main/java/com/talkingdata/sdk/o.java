package com.talkingdata.sdk;

import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.zz;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
class o implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ int e;
    final /* synthetic */ zz f;

    o(zz zzVar, a aVar, String str, String str2, String str3, int i) {
        this.f = zzVar;
        this.a = aVar;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 8);
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
            aVar.a.put(SpeechConstant.DOMAIN, "iap");
            aVar.a.put("action", "viewItem");
            TreeMap treeMap = new TreeMap();
            if (this.a.b().equals("APP")) {
                if (!TextUtils.isEmpty(this.b)) {
                    treeMap.put("id", this.b);
                }
                if (!TextUtils.isEmpty(this.c)) {
                    treeMap.put("category", this.c);
                }
                if (!TextUtils.isEmpty(this.d)) {
                    treeMap.put(com.alipay.sdk.m.h.c.e, this.d);
                }
                treeMap.put("unitPrice", Integer.valueOf(this.e));
            } else {
                treeMap.put("id", this.b);
                treeMap.put("category", this.c);
                treeMap.put(com.alipay.sdk.m.h.c.e, this.d);
                treeMap.put("unitPrice", Integer.valueOf(this.e));
            }
            aVar.a.put("data", treeMap);
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
