package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class f extends z {
    public f(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a(new InputFilter.LengthFilter(32));
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return a().length() != 0 && a().length() <= 32;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_input_certId";
    }
}
