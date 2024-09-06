package com.netease.nimlib.push;

import android.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DeviceInfo.java */
/* loaded from: classes.dex */
public final class c {
    public static String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("PRODUCT", Build.PRODUCT);
            jSONObject.put("DEVICE", Build.DEVICE);
            jSONObject.put("MANUFACTURER", com.netease.nimlib.p.a.a());
            jSONObject.put("BRAND", com.netease.nimlib.p.a.c());
            jSONObject.put("MODEL", com.netease.nimlib.p.a.b());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
