package com.talkingdata.sdk;

import android.net.Proxy;
import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class di extends da {
    public static bu a;
    public static String c = UUID.randomUUID().toString();

    public di(dk dkVar) {
        int i = dj.a[dkVar.ordinal()];
        if (i != 1) {
            try {
                if (i != 2) {
                    if (i != 3) {
                        return;
                    }
                    a("type", dk.BLUETOOTH.a());
                    return;
                }
                a("type", dk.CELLULAR.a());
                a("available", Boolean.valueOf(az.f(ab.f)));
                a("connected", Boolean.valueOf(az.h(ab.f)));
                if (az.c(ab.f)) {
                    a("current", az.s(ab.f));
                }
                if (az.a()) {
                    a("proxy", Proxy.getDefaultHost() + ":" + Proxy.getDefaultPort());
                }
                a("scannable", az.t(ab.f));
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        a("type", dk.WIFI.a());
        a("available", Boolean.valueOf(az.e(ab.f)));
        if (az.g(ab.f)) {
            a("connected", true);
            a("current", az.v(ab.f));
            JSONArray w = az.w(ab.f);
            bu a2 = a(w);
            if (a == null) {
                a("scannable", w);
                a = a2;
            } else if (new bv().a(a, a2) > 0.8d) {
                a("scannable", null);
            } else {
                a("scannable", w);
                a = a2;
                c = UUID.randomUUID().toString();
            }
            a("configured", az.u(ab.f));
            a("ip", az.b(ab.f));
        } else {
            a("connected", false);
        }
        if (az.a()) {
            a("proxy", Proxy.getDefaultHost() + ":" + Proxy.getDefaultPort());
        }
        a("scannableFingerId", c);
    }

    private static bu a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                arrayList.add(new bq(jSONObject.getString(com.alipay.sdk.m.h.c.e), jSONObject.getString("id"), (byte) jSONObject.getInt("level"), (byte) 0, (byte) 0));
            } catch (JSONException unused) {
            }
        }
        bu buVar = new bu();
        buVar.setBsslist(arrayList);
        return buVar;
    }
}
