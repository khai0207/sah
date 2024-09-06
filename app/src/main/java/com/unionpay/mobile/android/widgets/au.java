package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import java.util.Calendar;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class au extends z {
    public au(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a(new InputFilter.LengthFilter(4));
        this.b.a(2);
    }

    @Override // com.unionpay.mobile.android.widgets.z, com.unionpay.mobile.android.widgets.az.a
    public final String a() {
        return this.b.b().trim();
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        String a = a();
        if (4 == a.length()) {
            int parseInt = Integer.parseInt(a.substring(0, 2));
            int parseInt2 = Integer.parseInt(a.substring(2));
            int i = Calendar.getInstance().get(1) % 100;
            if (parseInt > 0 && parseInt <= 12 && (parseInt2 > i || (parseInt2 == i && parseInt >= Calendar.getInstance().get(2) + 1))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_select_availdata";
    }
}
