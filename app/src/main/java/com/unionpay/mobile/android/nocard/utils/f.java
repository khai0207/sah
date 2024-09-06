package com.unionpay.mobile.android.nocard.utils;

import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class f {
    public static int a(com.unionpay.mobile.android.model.b bVar, JSONObject jSONObject) {
        bVar.bn = i.a(jSONObject, "promotion_change_info");
        bVar.f39u = i.d(jSONObject, "rules");
        bVar.Y = i.d(jSONObject, "available_area_codes");
        bVar.y = i.d(jSONObject, "entrust_rules");
        bVar.z = i.a(jSONObject, "pre_cmd");
        bVar.v = i.a(jSONObject, com.alipay.sdk.m.s.d.v);
        bVar.x = i.c(jSONObject, "rules_button");
        bVar.ag = i.c(jSONObject, "service_checkbox");
        bVar.ah = i.c(jSONObject, "bind_card_checkbox");
        String a = i.a(jSONObject, "timeout_msg");
        if (a != null && !TextUtils.isEmpty(a)) {
            bVar.af = a;
        }
        bVar.k = new HashMap<>();
        JSONObject c = i.c(jSONObject, "f55");
        String a2 = i.a(c, "order_amount");
        HashMap<String, String> hashMap = bVar.k;
        if (a2 == null || a2.length() <= 0) {
            a2 = Constant.DEFAULT_BALANCE;
        }
        hashMap.put("trans_amt", a2);
        String a3 = i.a(c, "order_currency");
        HashMap<String, String> hashMap2 = bVar.k;
        if (a3 == null || a3.length() <= 0) {
            a3 = "0156";
        }
        hashMap2.put("trans currcy code", a3);
        String a4 = i.a(c, "trans_type");
        HashMap<String, String> hashMap3 = bVar.k;
        if (a4 == null || a4.length() <= 0) {
            a4 = "00";
        }
        hashMap3.put("trans_type", a4);
        String a5 = i.a(c, "mer_name");
        HashMap<String, String> hashMap4 = bVar.k;
        if (a5 == null || a5.length() <= 0) {
            a5 = "";
        }
        hashMap4.put("mer_name", a5);
        bVar.al = i.a(jSONObject, Constant.KEY_PAN);
        bVar.aZ = i.a(jSONObject, "encrypt_key");
        bVar.ba = i.a(jSONObject, "auth_id");
        String a6 = i.a(jSONObject, "fail_continue");
        if (a6 != null && a6.equalsIgnoreCase("0")) {
            bVar.A = true;
        }
        return ((bVar.f39u == null || bVar.f39u.length() <= 0) && (bVar.y == null || bVar.y.length() <= 0)) ? 2 : 0;
    }

    public static int a(com.unionpay.mobile.android.model.b bVar, JSONObject jSONObject, boolean z) {
        if (!z) {
            bVar.B = jSONObject;
        }
        return a(bVar, jSONObject);
    }

    public static com.unionpay.mobile.android.model.e a(JSONObject jSONObject) {
        com.unionpay.mobile.android.model.f fVar = new com.unionpay.mobile.android.model.f();
        fVar.a("promotion", i.c(jSONObject, "promotion"));
        fVar.a("instalment", i.c(jSONObject, "instalment"));
        fVar.a("promotion_instalment_msgbox", i.c(jSONObject, "promotion_instalment_msgbox"));
        return fVar;
    }

    public static boolean a(JSONArray jSONArray) {
        String a;
        String a2;
        boolean z = false;
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    a = i.a(jSONObject, "type");
                    a2 = i.a(jSONObject, "readonly");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (Constant.KEY_PAN.equalsIgnoreCase(a)) {
                    z = "true".equalsIgnoreCase(a2);
                    break;
                }
                continue;
            }
        }
        return z;
    }

    public static int b(com.unionpay.mobile.android.model.b bVar, JSONObject jSONObject) {
        int i = jSONObject == null ? 2 : 0;
        if (bVar.W == null) {
            bVar.W = new ArrayList();
        }
        bVar.W.clear();
        List<JSONArray> e = i.e(jSONObject, "user_cards");
        if (e != null && e.size() > 0) {
            for (int i2 = 0; e != null && i2 < e.size(); i2++) {
                bVar.W.add(new com.unionpay.mobile.android.model.a(i.a(e.get(i2), 0), i.a(e.get(i2), 1), i.a(e.get(i2), 2), (byte) 0));
            }
        }
        bVar.Y = i.d(jSONObject, "available_area_codes");
        bVar.bn = i.a(jSONObject, "promotion_change_info");
        bVar.X = i.d(jSONObject, "user_info");
        bVar.f39u = i.d(jSONObject, "rules");
        bVar.U = i.c(jSONObject, "service_url");
        bVar.V = i.c(jSONObject, "bind_url");
        bVar.Z = i.a(jSONObject, "empty_info");
        bVar.aT = i.a(jSONObject, "add_card_tip");
        bVar.al = i.a(jSONObject, Constant.KEY_PAN);
        return i;
    }

    public static boolean c(com.unionpay.mobile.android.model.b bVar, JSONObject jSONObject) {
        bVar.aA = null;
        bVar.aA = i.c(jSONObject, "cardExpireMsgBox");
        if (bVar.aA == null) {
            bVar.aA = i.c(jSONObject, "openByCounterMsgBox");
        }
        if (bVar.aA == null) {
            bVar.aA = i.c(jSONObject, "restrictPayMsgBox");
        }
        if (bVar.aA == null) {
            return false;
        }
        bVar.aB = i.a(bVar.aA, com.alipay.sdk.m.s.d.v);
        bVar.aC = i.a(bVar.aA, SpeechConstant.TEXT);
        JSONObject c = i.c(bVar.aA, "func");
        JSONObject c2 = i.c(bVar.aA, Constant.CASH_LOAD_CANCEL);
        bVar.aF = i.a(c, "label");
        bVar.aG = i.a(c, "action");
        bVar.aD = i.a(c2, "label");
        bVar.aE = i.a(c2, "action");
        return true;
    }
}
