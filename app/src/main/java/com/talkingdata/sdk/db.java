package com.talkingdata.sdk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class db extends da {
    public static final String a = "TalkingData";
    public static final String c = "SaaS";
    public static final String d = "Android";
    public static String e = "";
    public static final int f = 0;
    public static final int g = 1;
    public static final int h = 2;
    public static final int i = 3;
    public static final int j = 4;

    public db() {
        a("version", aj.a);
        a("minorVersion", "0");
        a("build", aj.c);
        a(com.alipay.sdk.m.g.b.u0, c);
        a("platform", "Android");
        a("type", a);
        a("framework", e);
    }

    public void setFrameWork(String str) {
        e = str;
    }

    public String b() {
        return e;
    }

    public void a(String str, String str2, String str3) {
        JSONArray jSONArray;
        if (this.b.isNull("features")) {
            jSONArray = new JSONArray();
        } else {
            try {
                jSONArray = this.b.getJSONArray("features");
            } catch (JSONException e2) {
                e2.printStackTrace();
                jSONArray = null;
            }
        }
        if (jSONArray != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(com.alipay.sdk.m.h.c.e, str);
                jSONObject.put("version", str2);
                jSONObject.put("minorVersion", str3);
                jSONArray.put(jSONObject);
            } catch (JSONException e3) {
                e3.printStackTrace();
                ce.postSDKError(e3);
            }
            a("features", jSONArray);
        }
    }
}
