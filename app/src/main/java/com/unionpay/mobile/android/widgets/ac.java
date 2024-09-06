package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class ac extends y {
    private int a;
    private String b;
    private TextView n;
    private TextView o;

    public ac(Context context, int i, JSONObject jSONObject, String str) {
        this(context, i, jSONObject, str, (byte) 0);
    }

    private ac(Context context, int i, JSONObject jSONObject, String str, byte b) {
        super(context, jSONObject, str);
        this.a = 0;
        this.a = i;
        if (jSONObject != null) {
            this.b = com.unionpay.mobile.android.utils.i.a(jSONObject, "style");
        }
        RelativeLayout relativeLayout = this.l;
        LinearLayout linearLayout = new LinearLayout(this.c);
        relativeLayout.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(0);
        int i2 = com.unionpay.mobile.android.global.a.f;
        TextView textView = new TextView(this.c);
        this.n = textView;
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.n.setText(s());
        this.n.setGravity(3);
        this.n.setTextColor(-13421773);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 0.3f);
        layoutParams.gravity = 3;
        linearLayout.addView(this.n, layoutParams);
        TextView textView2 = new TextView(this.c);
        this.o = textView2;
        textView2.setGravity(16);
        this.o.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.o.setText(com.unionpay.mobile.android.data.a.a(i(), this.b));
        this.o.setPadding(0, 0, com.unionpay.mobile.android.global.a.g, 0);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 0.7f);
        layoutParams2.gravity = 21;
        linearLayout.addView(this.o, layoutParams2);
    }

    public ac(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
        this.a = 0;
        int i = com.unionpay.mobile.android.global.a.f;
        String s = s();
        if (s != null && s.length() > 0) {
            TextView textView = new TextView(this.c);
            this.n = textView;
            textView.setTextSize(com.unionpay.mobile.android.global.b.k);
            this.n.setText(s());
            this.n.setTextColor(-7829368);
            addView(this.n);
        }
        String i2 = i();
        if (i2 == null || i2.length() <= 0) {
            return;
        }
        TextView textView2 = new TextView(this.c);
        this.o = textView2;
        textView2.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.o.setTextColor(-7829368);
        this.o.setText(i());
        addView(this.o);
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final String a() {
        return null;
    }

    public final void a(float f) {
        this.o.setTextSize(f);
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return true;
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean c() {
        return true;
    }

    public final void g() {
        this.o.setTextColor(-6710887);
    }
}
