package com.unionpay.mobile.android.hce;

import org.json.JSONObject;

/* loaded from: classes.dex */
public final class k {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private JSONObject g;

    public k(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.g = jSONObject;
            this.a = com.unionpay.mobile.android.utils.i.a(jSONObject, "package");
            this.b = com.unionpay.mobile.android.utils.i.a(jSONObject, "issuer");
            this.c = com.unionpay.mobile.android.utils.i.a(jSONObject, "syn_key");
            this.d = com.unionpay.mobile.android.utils.i.a(jSONObject, "pub_key");
            this.e = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
            this.f = com.unionpay.mobile.android.utils.i.a(jSONObject, "priority");
        }
    }

    public final boolean a() {
        return this.e.equals("D");
    }

    public final String b() {
        return this.a;
    }

    public final String c() {
        return this.b;
    }

    public final String d() {
        return this.c;
    }

    public final String e() {
        return this.d;
    }

    public final JSONObject f() {
        return this.g;
    }
}
