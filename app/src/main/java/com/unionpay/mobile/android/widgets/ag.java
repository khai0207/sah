package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class ag extends z {
    private a n;
    private String o;
    private String p;

    /* loaded from: classes.dex */
    public interface a {
        void e(String str);
    }

    public ag(Context context, int i, JSONObject jSONObject, String str) {
        this(context, i, jSONObject, str, (byte) 0);
    }

    private ag(Context context, int i, JSONObject jSONObject, String str, byte b) {
        super(context, i, jSONObject, str);
        this.n = null;
        this.o = null;
        this.p = null;
        this.o = com.unionpay.mobile.android.utils.i.a(jSONObject, "button_label");
        this.p = com.unionpay.mobile.android.utils.i.a(jSONObject, "button_action");
        this.b.a(new InputFilter.LengthFilter(11));
        this.b.a(2);
        String str2 = this.o;
        if (str2 == null || str2.length() <= 0) {
            return;
        }
        int i2 = this.a;
        int i3 = com.unionpay.mobile.android.global.a.b;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.b.n);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        this.b.setLayoutParams(layoutParams);
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setText(this.o);
        textView.setTextColor(-7829368);
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        textView.setOnClickListener(new ah(this));
        this.b.a(textView, new LinearLayout.LayoutParams(-2, -1));
    }

    @Override // com.unionpay.mobile.android.widgets.z, com.unionpay.mobile.android.widgets.az.a
    public final String a() {
        return this.b.b();
    }

    public final void a(a aVar) {
        this.n = aVar;
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        if (this.h) {
            return true;
        }
        return (this.i == null || TextUtils.isEmpty(this.i)) ? 11 == a().length() && a().startsWith("1") : a().matches(this.i);
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_input_phoneNO";
    }
}
