package com.netease.nimlib.net.a.b.c;

import org.json.JSONObject;

/* compiled from: HttpResult.java */
/* loaded from: classes.dex */
public class c {
    private int a;
    private JSONObject b;
    private Exception c;

    public c(int i, JSONObject jSONObject, Exception exc) {
        this.a = i;
        this.b = jSONObject;
        this.c = exc;
    }

    public int a() {
        return this.a;
    }

    public JSONObject b() {
        return this.b;
    }
}
