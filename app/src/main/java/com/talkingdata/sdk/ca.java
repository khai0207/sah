package com.talkingdata.sdk;

import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechConstant;
import com.talkingdata.sdk.cm;
import com.talkingdata.sdk.zz;
import java.util.Map;

/* compiled from: td */
/* loaded from: classes.dex */
public class ca {
    private static volatile ca a;

    public final void onTDEBEventIAP(zz.a aVar) {
        if (aVar != null) {
            try {
                if (aVar.a != null && Integer.parseInt(String.valueOf(aVar.a.get("apiType"))) == 8) {
                    cn cnVar = new cn();
                    Object obj = aVar.a.get("data");
                    a aVar2 = (a) aVar.a.get(NotificationCompat.CATEGORY_SERVICE);
                    cnVar.b = String.valueOf(aVar.a.get(SpeechConstant.DOMAIN));
                    cnVar.c = String.valueOf(aVar.a.get("action"));
                    if (obj != null && (obj instanceof Map)) {
                        cnVar.d = (Map) obj;
                    }
                    cnVar.a = aVar2;
                    bk.a().post(cnVar);
                    if (aVar.a.get("action") != null) {
                        if (aVar.a.get("action").equals("addItem") || aVar.a.get("action").equals("recharge") || aVar.a.get("action").equals("currencyPurchase") || aVar.a.get("action").equals("placeOrder") || aVar.a.get("action").equals("deeplink") || aVar.a.get("action").equals("viewItem") || aVar.a.get("action").equals("viewItems") || aVar.a.get("action").equals("reward") || aVar.a.get("action").equals("onRechargeSucc") || aVar.a.get("action").equals("virtualCurrencyPurchase") || aVar.a.get("action").equals("itemUsedFor") || aVar.a.get("action").equals("pay") || aVar.a.get("action").equals("apply") || aVar.a.get("action").equals("activate")) {
                            cm cmVar = new cm();
                            cmVar.a = aVar2;
                            cmVar.b = cm.a.IMMEDIATELY;
                            bk.a().post(cmVar);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static ca a() {
        if (a == null) {
            synchronized (ca.class) {
                if (a == null) {
                    a = new ca();
                }
            }
        }
        return a;
    }

    private ca() {
    }

    static {
        try {
            bk.a().register(a());
        } catch (Throwable unused) {
        }
    }
}
