package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class as extends z {
    private int n;

    public as(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.n = 0;
        String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "maxLength");
        this.n = (a == null || a.length() <= 0) ? 23 : Integer.getInteger(a).intValue();
        this.b.a(new InputFilter.LengthFilter(this.n));
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return this.h || this.n >= a().length();
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_input_text";
    }
}
