package com.netease.nimlib.j.a;

import android.content.Context;
import android.text.TextUtils;
import com.netease.nimlib.c;
import com.netease.nimlib.o.k;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: IndexInfo.java */
/* loaded from: classes.dex */
public class b {
    private int a;
    private String b;
    private int c;
    private String d;
    private int e;

    public b(String str) {
        JSONObject a = k.a(str);
        if (a == null) {
            return;
        }
        this.a = a.optInt("terminal");
        this.b = a.optString("sdk_version");
        this.c = a.optInt("db_version");
        this.d = a.optString("app_version");
        this.e = a.optInt("message_count");
    }

    public b(int i) {
        this.a = 1;
        this.b = "9.17.0";
        this.c = 24;
        this.e = i;
        Context e = c.e();
        try {
            this.d = e.getPackageManager().getPackageInfo(e.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean a() {
        return this.a == 0 || TextUtils.isEmpty(this.b) || this.c == 0 || this.e == 0;
    }

    public String b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("terminal", this.a);
            jSONObject.put("sdk_version", this.b);
            jSONObject.put("db_version", this.c);
            if (!TextUtils.isEmpty(this.d)) {
                jSONObject.put("app_version", this.d);
            }
            jSONObject.put("message_count", this.e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String toString() {
        return b();
    }

    public int c() {
        return this.e;
    }
}
