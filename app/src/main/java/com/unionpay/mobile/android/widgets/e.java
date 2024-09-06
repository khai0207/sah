package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class e extends z {
    public e(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a(new InputFilter.LengthFilter(3));
        this.b.a(18);
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return 3 == a().length();
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_input_cvn2";
    }
}
