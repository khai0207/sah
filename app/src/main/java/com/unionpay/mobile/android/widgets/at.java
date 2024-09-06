package com.unionpay.mobile.android.widgets;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class at extends z {
    public at(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        if (this.h) {
            return true;
        }
        String a = a() != null ? a() : "";
        return (this.i == null || this.i.length() <= 0) ? a.length() > 0 && a.length() <= 64 : a.matches(this.i);
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "login_user";
    }
}
