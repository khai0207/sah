package com.netease.nimlib.biz;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UIPreferences.java */
/* loaded from: classes.dex */
public class k {
    public static com.netease.nimlib.b.a a() {
        com.netease.nimlib.b.a aVar = new com.netease.nimlib.b.a();
        String a = a("k_client_antispam");
        if (TextUtils.isEmpty(a)) {
            a(aVar);
            return aVar;
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            aVar.a(jSONObject.optInt("version"));
            aVar.a(jSONObject.optString("md5"));
            aVar.b(jSONObject.optString(Constants.URL_ENCODING));
        } catch (Exception e) {
            e.printStackTrace();
            a(aVar);
        }
        return aVar;
    }

    public static void a(com.netease.nimlib.b.a aVar) {
        if (aVar == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", aVar.a());
            jSONObject.put("md5", aVar.b());
            jSONObject.put(Constants.URL_ENCODING, aVar.c());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a("k_client_antispam", jSONObject.toString());
    }

    private static void a(String str, String str2) {
        SharedPreferences.Editor edit = b().edit();
        edit.putString(str, str2);
        edit.apply();
    }

    private static String a(String str) {
        return b().getString(str, null);
    }

    public static SharedPreferences b() {
        return com.netease.nimlib.c.e().getSharedPreferences("NIMSDK_Config_UI" + com.netease.nimlib.c.g(), 0);
    }
}
