package com.talkingdata.sdk;

import org.json.JSONArray;

/* compiled from: td */
/* loaded from: classes.dex */
public class de extends da {
    public de() {
        a("tid", as.a(ab.f));
        try {
            JSONArray x = az.x(ab.f);
            JSONArray jSONArray = new JSONArray();
            if (x != null) {
                jSONArray.put(x.getJSONObject(0).get("imei"));
                if (x.length() == 2) {
                    jSONArray.put(x.getJSONObject(1).get("imei"));
                }
            }
            a("imeis", jSONArray);
        } catch (Exception unused) {
        }
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put(as.f(ab.f));
        a("wifiMacs", jSONArray2);
        a("androidId", as.b(ab.f));
        a("adId", as.g(ab.f));
        a("serialNo", as.a() == null ? "" : as.a());
    }

    public void b() {
        a("adId", as.g(ab.f));
    }
}
