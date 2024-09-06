package com.unionpay.mobile.android.widgets;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class an extends z {
    public an(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a(129);
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        String a = a();
        return a != null && a.length() > 0;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "login_pwd";
    }
}
