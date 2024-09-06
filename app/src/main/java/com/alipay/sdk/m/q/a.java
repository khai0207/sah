package com.alipay.sdk.m.q;

import android.text.TextUtils;
import com.alipay.sdk.m.g.a;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    public static final String a = "ap_req";
    public static final String b = "ap_args";
    public static final String c = "ap_resp";

    public static com.alipay.sdk.m.c.a a() {
        try {
            try {
                return com.alipay.sdk.m.d.a.a("NP", System.currentTimeMillis(), new com.alipay.sdk.m.d.c(com.alipay.sdk.m.o.b.d().c()), (short) a.c.a(com.alipay.sdk.m.o.b.d().b()), new com.alipay.sdk.m.d.e());
            } catch (Exception unused) {
                return com.alipay.sdk.m.d.a.c();
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static HashMap<String, String> a(com.alipay.sdk.m.o.a aVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            com.alipay.sdk.m.c.a a2 = a();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ap_q", a2 != null ? a2.a() : "");
            jSONObject.put(com.alipay.sdk.m.o.a.x, aVar != null ? aVar.d : "");
            jSONObject.put("u_pd", String.valueOf(m.g()));
            jSONObject.put("u_lk", String.valueOf(m.e(m.b())));
            hashMap.put(a, jSONObject.toString());
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "ap_q", a2 != null ? a2.a() : "");
        } catch (Exception e) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "APMEx1", e);
        }
        return hashMap;
    }

    public static JSONObject a(com.alipay.sdk.m.o.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString(c);
        try {
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            return new JSONObject(optString);
        } catch (JSONException e) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "APMEx2", e);
            return null;
        }
    }

    public static void a(com.alipay.sdk.m.o.a aVar, JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return;
        }
        try {
            jSONObject.putOpt(b, jSONObject2);
        } catch (JSONException e) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "APMEx2", e);
        }
    }

    public static void a(com.alipay.sdk.m.o.a aVar, HashMap<String, String> hashMap) {
        JSONObject a2 = com.alipay.sdk.m.i.a.x().a();
        if (hashMap == null || a2 == null) {
            return;
        }
        com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "ap_r", a2.optString("ap_r"));
        hashMap.putAll(m.a(a2));
    }
}
