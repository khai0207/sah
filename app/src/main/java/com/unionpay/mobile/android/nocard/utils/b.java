package com.unionpay.mobile.android.nocard.utils;

import com.unionpay.mobile.android.utils.i;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    public static void a(JSONObject jSONObject, com.unionpay.mobile.android.model.b bVar) {
        bVar.an = i.a(jSONObject, "red_packet_url");
    }

    public static void b(JSONObject jSONObject, com.unionpay.mobile.android.model.b bVar) {
        bVar.aM = i.a(jSONObject, "pay_msg");
        bVar.bd = i.a(jSONObject, "reserved");
        bVar.aN = i.a(jSONObject, "promotion_msg");
        bVar.aO = i.a(jSONObject, "instalment_msg");
        bVar.aR = i.a(jSONObject, "temporary_pay_flag");
        if ("0".equalsIgnoreCase(bVar.aR)) {
            bVar.aS = i.a(jSONObject, "temporary_pay_info");
        }
        if ("0".equalsIgnoreCase(i.a(jSONObject, "luck_draw_flag"))) {
            bVar.aQ = true;
        }
    }
}
