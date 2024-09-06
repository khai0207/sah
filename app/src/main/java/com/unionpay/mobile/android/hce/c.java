package com.unionpay.mobile.android.hce;

import android.content.ServiceConnection;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class c implements com.unionpay.mobile.android.model.d {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private com.unionpay.mobile.android.hce.service.a g;
    private ServiceConnection h;

    public c(JSONObject jSONObject, String str, com.unionpay.mobile.android.hce.service.a aVar, ServiceConnection serviceConnection) {
        this.a = com.unionpay.mobile.android.utils.i.a(jSONObject, "num");
        this.b = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.h.c.e);
        this.d = com.unionpay.mobile.android.utils.i.a(jSONObject, "type");
        String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "type");
        this.c = e.a.equals(a) ? e.e : e.b.equals(a) ? e.f : e.c.equals(a) ? e.g : e.d.equals(a) ? e.h : "";
        this.e = com.unionpay.mobile.android.utils.i.a(jSONObject, "instNum");
        this.f = str;
        this.g = aVar;
        this.h = serviceConnection;
    }

    @Override // com.unionpay.mobile.android.model.d
    public final String a() {
        return this.a;
    }

    @Override // com.unionpay.mobile.android.model.d
    public final String b() {
        return this.b;
    }

    @Override // com.unionpay.mobile.android.model.d
    public final String c() {
        return this.c;
    }

    @Override // com.unionpay.mobile.android.model.d
    public final String d() {
        return this.d;
    }

    @Override // com.unionpay.mobile.android.model.d
    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }

    public final com.unionpay.mobile.android.hce.service.a g() {
        return this.g;
    }

    public final ServiceConnection h() {
        return this.h;
    }
}
