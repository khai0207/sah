package com.talkingdata.sdk;

import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.zz;
import com.tendcloud.appcpa.Order;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
class l implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ Order g;
    final /* synthetic */ zz h;

    l(zz zzVar, a aVar, String str, String str2, int i, String str3, String str4, Order order) {
        this.h = zzVar;
        this.a = aVar;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
        this.f = str4;
        this.g = order;
    }

    @Override // java.lang.Runnable
    public void run() {
        JSONArray jSONArray;
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
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("orderId", this.g.getString(Order.keyOrderId));
            jSONObject.put("amount", this.g.getInt(Order.keyTotalPrice));
            jSONObject.put("currencyType", this.g.getString(Order.keyCurrencyType));
            if (this.g.has(Order.keyOrderDetail) && (jSONArray = this.g.getJSONArray(Order.keyOrderDetail)) != null && jSONArray.length() > 0) {
                jSONObject.put("items", jSONArray);
            }
            treeMap.put("order", jSONObject);
            aVar.a.put("data", treeMap);
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
