package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.upviews.a;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class ak extends b implements a.b {
    private int r;
    private int s;
    private com.unionpay.mobile.android.upwidget.a t;

    /* renamed from: u, reason: collision with root package name */
    private TextView f41u;
    private com.unionpay.mobile.android.upviews.a v;
    private View.OnClickListener w;
    private View.OnClickListener x;

    public ak(Context context) {
        super(context);
        this.r = 100;
        this.s = 20;
        this.t = null;
        this.f41u = null;
        this.v = null;
        this.w = new al(this);
        this.x = new am(this);
        this.f = 10;
        this.q = "openupgrade";
        setBackgroundColor(-1052684);
        this.k = a();
        b();
        super.d();
        c();
    }

    static /* synthetic */ int c(ak akVar) {
        akVar.r = 102;
        return 102;
    }

    private void u() {
        this.r = 103;
        StringBuilder sb = new StringBuilder();
        sb.append(this.s);
        com.unionpay.mobile.android.utils.j.c("uppay", sb.toString());
        this.e.a("query", this.a.ae, 3);
        this.s--;
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(a.C0071a c0071a) {
        if (!c0071a.a()) {
            a(c0071a.b);
            return;
        }
        this.j = false;
        this.r = 101;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        com.unionpay.mobile.android.utils.j.a("uppay", "sms elements:" + c0071a.b);
        this.e.c("sms", c0071a.b);
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        switch (this.r) {
            case 101:
                this.v.a(com.unionpay.mobile.android.global.b.p);
                this.b.c();
                this.r = 100;
                return;
            case 102:
                this.a.ae = com.unionpay.mobile.android.utils.h.a(jSONObject.toString());
                if (this.a.ae == null || this.a.ae.length() <= 0) {
                    b(2);
                    return;
                } else {
                    this.s = 20;
                    u();
                    return;
                }
            case 103:
                String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
                String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "fail_msg");
                this.a.N = com.unionpay.mobile.android.utils.i.a(jSONObject, "open_info");
                this.a.v = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.s.d.v);
                this.a.w = com.unionpay.mobile.android.utils.i.a(jSONObject, "succ_info");
                if (this.s > 0 && a.equalsIgnoreCase("01")) {
                    u();
                    return;
                }
                this.r = 100;
                j();
                if (a.equalsIgnoreCase("00")) {
                    String str = this.a.N;
                    d(11);
                    return;
                } else if (a.equalsIgnoreCase("03")) {
                    this.b.a(new an(this), null);
                    this.b.a(com.unionpay.mobile.android.languages.c.bD.ab, a2, com.unionpay.mobile.android.languages.c.bD.ac);
                    return;
                } else {
                    if (this.s <= 0) {
                        b(20);
                        return;
                    }
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(boolean z) {
        TextView textView = this.f41u;
        if (textView != null) {
            textView.setEnabled(!z);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        com.unionpay.mobile.android.widgets.ax axVar = new com.unionpay.mobile.android.widgets.ax(getContext(), this.a.v, this);
        layoutParams.addRule(13, -1);
        this.k.addView(axVar, layoutParams);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void b(String str, String str2) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void c() {
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setBackgroundColor(-1);
        boolean z = true;
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int i = com.unionpay.mobile.android.global.a.f;
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
        this.m.addView(linearLayout, layoutParams);
        this.v = new com.unionpay.mobile.android.upviews.a(this.d, this.a.O, this.e.c(), this, this.a.al, true, this.q);
        new LinearLayout.LayoutParams(-1, -1).topMargin = com.unionpay.mobile.android.global.a.f;
        linearLayout.addView(this.v);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        linearLayout2.setId(linearLayout2.hashCode());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.d;
        layoutParams2.leftMargin = com.unionpay.mobile.android.global.a.d;
        layoutParams2.addRule(3, linearLayout.getId());
        this.m.addView(linearLayout2, layoutParams2);
        com.unionpay.mobile.android.upwidget.a aVar = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ag, this.x, this.q + "_agree_user_protocol");
        this.t = aVar;
        linearLayout2.addView(aVar);
        TextView textView = new TextView(this.d);
        this.f41u = textView;
        textView.setText(com.unionpay.mobile.android.utils.i.a(this.a.x, "label"));
        this.f41u.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.f41u.setTextColor(p());
        this.f41u.setGravity(17);
        int i2 = com.unionpay.mobile.android.global.a.n;
        this.f41u.setBackgroundDrawable(this.c.a(2008));
        this.f41u.setOnClickListener(this.w);
        TextView textView2 = this.f41u;
        com.unionpay.mobile.android.upviews.a aVar2 = this.v;
        if (aVar2 != null && !aVar2.e()) {
            z = false;
        }
        textView2.setEnabled(z);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams3.topMargin = com.unionpay.mobile.android.global.a.d;
        int i3 = com.unionpay.mobile.android.global.a.d;
        layoutParams3.rightMargin = i3;
        layoutParams3.leftMargin = i3;
        layoutParams3.addRule(3, linearLayout2.getId());
        this.m.addView(this.f41u, layoutParams3);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void c(String str) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
        if (this.v.d()) {
            return;
        }
        n();
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
    }
}
