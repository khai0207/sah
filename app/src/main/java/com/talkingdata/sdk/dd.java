package com.talkingdata.sdk;

import org.json.JSONArray;

/* compiled from: td */
/* loaded from: classes.dex */
public class dd extends da {
    public dd() {
        a("manufacture", au.b());
        a("brand", au.c());
        a("model", au.d());
        JSONArray jSONArray = new JSONArray();
        for (String str : au.k()) {
            jSONArray.put(str);
        }
        a("cpuInfo", jSONArray);
        new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        for (int i : au.o()) {
            jSONArray2.put(i);
        }
        a("memoryInfo", jSONArray2);
        JSONArray jSONArray3 = new JSONArray();
        for (int i2 : au.n()) {
            jSONArray3.put(i2);
        }
        a("sdCardInfo", jSONArray3);
        au.a(ab.f, this.b);
        au.b(ab.f, this.b);
        a("totalDiskSpace", Integer.valueOf(c()));
        a("support", au.d(ab.f));
        a("cpu", au.l());
        a("nfcHce", au.a(ab.f));
    }

    private int c() {
        try {
            int[] p = au.p();
            return p[0] + p[2];
        } catch (Throwable unused) {
            return 0;
        }
    }

    public void setSlots(int i) {
        a("slots", Integer.valueOf(i));
    }

    public void b() {
        try {
            a("support", au.d(ab.f));
        } catch (Throwable unused) {
        }
    }
}
