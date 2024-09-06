package com.unionpay.mobile.android.widgets;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class ad extends z {
    public ad(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return this.h || a().length() != 0;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_input_name";
    }
}
