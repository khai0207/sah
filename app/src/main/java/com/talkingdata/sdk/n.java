package com.talkingdata.sdk;

import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.zz;
import com.tendcloud.appcpa.Order;
import com.unionpay.tsmservice.data.Constant;
import java.util.TreeMap;
import org.json.JSONArray;

/* compiled from: td */
/* loaded from: classes.dex */
class n implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ Order c;
    final /* synthetic */ zz d;

    n(zz zzVar, a aVar, String str, Order order) {
        this.d = zzVar;
        this.a = aVar;
        this.b = str;
        this.c = order;
    }

    @Override // java.lang.Runnable
    public void run() {
        JSONArray jSONArray;
        try {
            zz.a aVar = new zz.a();
            aVar.a.put("apiType", 8);
            aVar.a.put(NotificationCompat.CATEGORY_SERVICE, this.a);
            aVar.a.put(SpeechConstant.DOMAIN, "iap");
            aVar.a.put("action", "placeOrder");
            TreeMap treeMap = new TreeMap();
            treeMap.put("accountId", this.b);
            treeMap.put("orderId", this.c.getString(Order.keyOrderId));
            treeMap.put("amount", Integer.valueOf(this.c.optInt(Order.keyTotalPrice)));
            if (this.a.b().equals("TRACKING")) {
                treeMap.put("currencyType", this.c.optString(Order.keyCurrencyType));
            } else if (this.a.b().equals("APP")) {
                String optString = this.c.optString(Order.keyCurrencyType);
                if (TextUtils.isEmpty(optString)) {
                    optString = Constant.KEY_CURRENCYTYPE_CNY;
                }
                treeMap.put("currencyType", optString);
            }
            if (this.c.has(Order.keyOrderDetail) && (jSONArray = this.c.getJSONArray(Order.keyOrderDetail)) != null && jSONArray.length() > 0) {
                treeMap.put("items", jSONArray);
            }
            aVar.a.put("data", treeMap);
            Message.obtain(zz.c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
