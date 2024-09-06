package com.unionpay.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class f {
    public static Object a(JSONArray jSONArray, int i) {
        if (jSONArray != null && i < jSONArray.length() && i >= 0) {
            try {
                return jSONArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String a(JSONObject jSONObject, String str) {
        if (d(jSONObject, str)) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException unused) {
                g.b("uppay", f.class.toString() + " get " + str + " failed!!");
            }
        }
        return "";
    }

    public static boolean b(JSONObject jSONObject, String str) {
        if (d(jSONObject, str)) {
            try {
                return jSONObject.getBoolean(str);
            } catch (JSONException unused) {
                g.b("uppay", f.class.toString() + " get " + str + " failed!!");
            }
        }
        return false;
    }

    public static JSONArray c(JSONObject jSONObject, String str) {
        if (d(jSONObject, str)) {
            try {
                return jSONObject.getJSONArray(str);
            } catch (JSONException unused) {
                g.b("uppay", f.class.toString() + " get " + str + " failed!!");
            }
        }
        return null;
    }

    private static boolean d(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }
}
