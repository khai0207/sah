package com.talkingdata.sdk;

import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class dm {
    private static cy a;
    private static db b;
    private static df c;
    private static dc d;
    private static volatile dm e;

    public synchronized JSONObject a(cx cxVar, boolean z) {
        return a(cxVar, z, null);
    }

    public synchronized JSONObject a(cx cxVar, boolean z, a aVar) {
        if (cxVar != null) {
            if (cxVar.a_() != null) {
                try {
                    if (a == null) {
                        a = cy.a();
                        a.setUniqueId(dl.a(ab.f, ab.f.getPackageName()));
                    }
                    a.setSubmitAppId(aVar);
                    a.setSubmitChannelId(aVar);
                    if (c == null) {
                        c = new df();
                    } else {
                        df.g.b();
                        c.c().b();
                    }
                    if (b == null) {
                        b = new db();
                    }
                    if (d == null) {
                        dc dcVar = new dc();
                        d = dcVar;
                        dcVar.b();
                        c.c().setSlots(bg.a);
                    }
                } catch (Throwable unused) {
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("version", "2.0");
                    jSONObject.put("action", cxVar.a_());
                    jSONObject.put(com.alipay.sdk.m.l.e.p, c.a_());
                    jSONObject.put("app", a.a_());
                    jSONObject.put("sdk", b.a_());
                    jSONObject.put("appContext", cv.a().a_());
                    jSONObject.put("user", d.a_());
                    long currentTimeMillis = System.currentTimeMillis();
                    jSONObject.put("ts", currentTimeMillis);
                    jSONObject.put("fingerprint", bh.c(currentTimeMillis + dl.a(ab.f) + c.b().b() + c.b().c()));
                    if (z) {
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.put(new di(dk.WIFI).a_());
                        jSONArray.put(new di(dk.CELLULAR).a_());
                        try {
                            if (bh.b(ab.f, "android.permission.BLUETOOTH")) {
                                jSONArray.put(new di(dk.BLUETOOTH).a_());
                            }
                        } catch (Throwable unused2) {
                        }
                        jSONObject.put("networks", jSONArray);
                        jSONObject.put("locations", new cz().a_());
                    }
                } catch (Throwable th) {
                    ce.postSDKError(th);
                }
                return jSONObject;
            }
        }
        return null;
    }

    public static dm a() {
        if (e == null) {
            synchronized (dm.class) {
                if (e == null) {
                    e = new dm();
                }
            }
        }
        return e;
    }
}
