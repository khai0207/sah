package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class g extends b implements a.b {
    private int r;
    private int s;
    private com.unionpay.mobile.android.upwidget.a t;

    /* renamed from: u, reason: collision with root package name */
    private TextView f46u;
    private com.unionpay.mobile.android.upviews.a v;
    private boolean w;
    private boolean x;
    private View.OnClickListener y;
    private View.OnClickListener z;

    public g(Context context) {
        super(context);
        this.r = 20;
        this.s = 100;
        this.t = null;
        this.f46u = null;
        this.v = null;
        this.w = false;
        this.x = true;
        this.y = new h(this);
        this.z = new i(this);
        this.f = 5;
        this.q = this.a.F ? "entrust_phoneNO_change" : "entrust";
        this.x = true ^ this.a.F;
        setBackgroundColor(-1052684);
        e();
    }

    private void d(JSONObject jSONObject) {
        j();
        this.a.f39u = com.unionpay.mobile.android.utils.i.d(jSONObject, "rules");
        this.a.v = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.s.d.v);
        this.a.x = com.unionpay.mobile.android.utils.i.c(jSONObject, "followrules_button");
        this.a.ag = com.unionpay.mobile.android.utils.i.c(jSONObject, "service_checkbox");
        this.a.ah = com.unionpay.mobile.android.utils.i.c(jSONObject, "bind_card_checkbox");
        this.a.al = com.unionpay.mobile.android.utils.i.a(jSONObject, Constant.KEY_PAN);
        if (this.a.f39u == null || this.a.f39u.length() <= 0) {
            b(2);
            return;
        }
        com.unionpay.mobile.android.upviews.a aVar = this.v;
        if (aVar != null) {
            aVar.f();
        }
        com.unionpay.mobile.android.model.e a = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
        this.a.F = false;
        a(6, a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        this.e.j(this.v.a(Constant.KEY_PAN));
        this.s = 104;
    }

    private void v() {
        this.s = 103;
        this.e.a("query", this.a.ae, 3);
        this.r--;
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(a.C0071a c0071a) {
        if (!c0071a.a()) {
            a(c0071a.b);
            return;
        }
        this.j = false;
        this.s = 101;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        com.unionpay.mobile.android.utils.j.a("uppay", "sms elements:" + c0071a.b);
        this.e.c("sms", c0071a.b);
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        this.x = false;
        switch (this.s) {
            case 101:
                this.v.a(com.unionpay.mobile.android.global.b.p);
                this.b.c();
                this.s = 100;
                return;
            case 102:
                this.a.ae = com.unionpay.mobile.android.utils.h.a(jSONObject.toString());
                if (this.a.ae == null || this.a.ae.length() <= 0) {
                    b(2);
                    return;
                } else {
                    this.r = 20;
                    v();
                    return;
                }
            case 103:
                String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
                String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "fail_msg");
                if (this.r > 0 && a.equalsIgnoreCase("01")) {
                    v();
                    return;
                }
                this.s = 100;
                if (a.equalsIgnoreCase("00")) {
                    this.w = true;
                    a(this.d, this.q + "_open_succeed");
                    u();
                    return;
                }
                j();
                a(this.d, this.q + "_open_fail", com.unionpay.mobile.android.utils.o.j, new String[]{a, a2});
                if (!a.equalsIgnoreCase("03")) {
                    if (this.r <= 0) {
                        a(this.a.af);
                        return;
                    }
                    return;
                }
                j jVar = new j(this);
                k kVar = new k(this);
                if (this.a.A) {
                    this.b.a(jVar, kVar);
                    this.b.a(com.unionpay.mobile.android.languages.c.bD.ab, a2, com.unionpay.mobile.android.languages.c.bD.ac, com.unionpay.mobile.android.languages.c.bD.ad);
                    return;
                } else {
                    this.b.a(jVar, null);
                    this.b.a(com.unionpay.mobile.android.languages.c.bD.ab, a2, com.unionpay.mobile.android.languages.c.bD.ac);
                    return;
                }
            case 104:
                if (b(jSONObject)) {
                    return;
                }
                d(jSONObject);
                return;
            case 105:
                j();
                int a3 = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, false);
                if (a3 != 0) {
                    b(a3);
                    return;
                }
                com.unionpay.mobile.android.model.e a4 = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
                if (this.a.f39u != null && this.a.f39u.length() > 0) {
                    a(6, a4);
                    return;
                } else {
                    if (this.a.y == null || this.a.y.length() <= 0) {
                        return;
                    }
                    d(5);
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(boolean z) {
        TextView textView = this.f46u;
        if (textView != null) {
            textView.setEnabled(!z);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final boolean a(String str, JSONObject jSONObject) {
        this.x = false;
        return false;
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
    protected final void b(String str, JSONObject jSONObject) {
        if ("init".equals(str)) {
            a(2);
            return;
        }
        if ("".equals(str)) {
            d(jSONObject);
            return;
        }
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        this.j = false;
        this.s = 105;
        this.e.c(str, "");
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void c() {
        this.o.a(this);
        LinearLayout linearLayout = new LinearLayout(this.d);
        boolean z = true;
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int i = com.unionpay.mobile.android.global.a.d;
        this.m.addView(linearLayout, layoutParams);
        this.v = new com.unionpay.mobile.android.upviews.a(this.d, this.a.y, this.e.c(), this, this.a.al, true, this.q);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
        linearLayout.addView(this.v, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.topMargin = i;
        layoutParams3.leftMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams3.addRule(3, linearLayout.getId());
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(0);
        linearLayout2.setId(linearLayout2.hashCode());
        this.t = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ag, this.z, this.q + "_agree_user_protocol");
        linearLayout2.addView(this.t, new LinearLayout.LayoutParams(-2, -2));
        this.m.addView(linearLayout2, layoutParams3);
        TextView textView = new TextView(this.d);
        this.f46u = textView;
        textView.setText(com.unionpay.mobile.android.utils.i.a(this.a.x, "label"));
        this.f46u.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.f46u.setTextColor(p());
        this.f46u.setGravity(17);
        int i2 = com.unionpay.mobile.android.global.a.n;
        this.f46u.setBackgroundDrawable(this.c.a(2008));
        this.f46u.setOnClickListener(this.y);
        TextView textView2 = this.f46u;
        com.unionpay.mobile.android.upviews.a aVar = this.v;
        if (aVar != null && !aVar.e()) {
            z = false;
        }
        textView2.setEnabled(z);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams4.topMargin = com.unionpay.mobile.android.global.a.f;
        int a = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        layoutParams4.rightMargin = a;
        layoutParams4.leftMargin = a;
        layoutParams4.addRule(3, linearLayout2.getId());
        this.m.addView(this.f46u, layoutParams4);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void c(String str) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
        if (this.v.d()) {
            return;
        }
        if (this.a.G) {
            a(13);
            this.a.G = false;
        } else if (this.a.F && this.x) {
            this.a.F = false;
            com.unionpay.mobile.android.nocard.utils.f.a(this.a, this.a.B);
            n();
        } else {
            this.a.F = false;
            this.a.B = null;
            a(2);
        }
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
    }
}
