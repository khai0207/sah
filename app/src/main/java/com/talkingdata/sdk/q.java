package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.zz;
import com.tendcloud.appcpa.ShoppingCart;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
class q implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ ShoppingCart b;
    final /* synthetic */ zz c;

    q(zz zzVar, a aVar, ShoppingCart shoppingCart) {
        this.c = zzVar;
        this.a = aVar;
        this.b = shoppingCart;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 8);
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
            aVar.a.put(SpeechConstant.DOMAIN, "iap");
            aVar.a.put("action", "viewItems");
            TreeMap treeMap = new TreeMap();
            treeMap.put("items", this.b);
            aVar.a.put("data", treeMap);
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
