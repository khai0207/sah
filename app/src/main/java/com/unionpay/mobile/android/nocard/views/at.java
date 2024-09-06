package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.upviews.a;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class at extends b implements a.b {
    private com.unionpay.mobile.android.upviews.a A;
    private com.unionpay.mobile.android.upviews.a B;
    private boolean C;
    private boolean D;
    private String E;
    private View.OnClickListener F;
    private View.OnClickListener G;
    private View.OnClickListener H;
    private View.OnClickListener I;
    private View.OnClickListener J;
    LinearLayout r;
    private String s;
    private int t;

    /* renamed from: u, reason: collision with root package name */
    private int f43u;
    private int v;
    private int w;
    private com.unionpay.mobile.android.upwidget.a x;
    private com.unionpay.mobile.android.upwidget.a y;
    private TextView z;

    public at(Context context) {
        this(context, null);
    }

    public at(Context context, com.unionpay.mobile.android.model.e eVar) {
        super(context, eVar);
        this.s = "00";
        this.t = 0;
        this.f43u = 0;
        this.v = 20;
        this.w = 5;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = false;
        this.D = false;
        this.r = new LinearLayout(this.d);
        this.F = new au(this);
        this.G = new av(this);
        this.H = new aw(this);
        this.I = new ax(this);
        this.J = new ay(this);
        this.f = 6;
        this.q = this.a.F ? "bankpay_phoneNO_change" : "bankpay";
        this.D = !this.a.F;
        setBackgroundColor(-1052684);
        e();
    }

    static /* synthetic */ void a(at atVar, String str, String str2) {
        atVar.f43u = 8;
        atVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        atVar.e.c(str, str2);
    }

    static /* synthetic */ void a(at atVar, boolean z, String str) {
        atVar.j = false;
        if (z) {
            atVar.e(str);
        } else {
            atVar.f43u = 2;
            atVar.e.c(atVar.a.z, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        this.f43u = 9;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.w--;
    }

    private void d(String str) {
        a(str, new az(this), new ba(this));
    }

    private static boolean d(JSONObject jSONObject) {
        return "0".equalsIgnoreCase(com.unionpay.mobile.android.utils.i.a(jSONObject, "reopen_flag"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        this.j = false;
        this.f43u = 3;
        String a = bh.a(this.a.x);
        com.unionpay.mobile.android.upwidget.a aVar = this.x;
        this.e.c(a, bh.c("1", "1", aVar != null ? aVar.a() : null, str));
    }

    static /* synthetic */ int f(at atVar) {
        atVar.w = 5;
        return 5;
    }

    private void f(int i) {
        this.f43u = 4;
        this.t = i;
        this.e.a("query", this.a.ae, 3);
        this.v--;
    }

    static /* synthetic */ void g(at atVar) {
        atVar.a.F = true;
        atVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        atVar.j = false;
        atVar.f43u = 7;
        atVar.e.c("reopenrules", "");
    }

    static /* synthetic */ int h(at atVar) {
        atVar.f43u = 0;
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String u() {
        com.unionpay.mobile.android.upviews.a aVar = this.B;
        String str = "";
        if (aVar != null) {
            a.C0071a b = aVar.b();
            if (b.a()) {
                str = "" + b.b;
            }
        }
        com.unionpay.mobile.android.upviews.a aVar2 = this.A;
        if (aVar2 == null) {
            return str;
        }
        a.C0071a b2 = aVar2.b();
        if (!b2.a()) {
            return str;
        }
        String str2 = b2.b;
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (!TextUtils.isEmpty(str)) {
            str = str + ",";
        }
        return str + str2;
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(a.C0071a c0071a) {
        if (!c0071a.a()) {
            a(c0071a.b);
            return;
        }
        com.unionpay.mobile.android.utils.j.a("uppay", "sms elements:" + c0071a.b);
        this.j = false;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        this.e.c("sms", c0071a.b);
        this.f43u = 1;
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        this.D = false;
        int i = this.f43u;
        if (i == 1) {
            j();
            this.f43u = 0;
            this.B.a(com.unionpay.mobile.android.global.b.p);
            return;
        }
        if (i == 2 || i == 3) {
            this.a.ae = com.unionpay.mobile.android.utils.h.a(jSONObject.toString());
            if (this.a.ae == null || this.a.ae.length() <= 0) {
                b(2);
                return;
            } else {
                this.v = 20;
                f(this.f43u);
                return;
            }
        }
        if (i != 4) {
            if (i == 16) {
                if (this.b.a()) {
                    this.b.c();
                }
                new JSONObject();
                if (TextUtils.isEmpty(com.unionpay.mobile.android.utils.i.a(jSONObject, "instalment_empty_info"))) {
                    jSONObject = com.unionpay.mobile.android.utils.i.c(jSONObject, "instalment");
                }
                this.A.a(jSONObject);
                this.f43u = 0;
                return;
            }
            switch (i) {
                case 6:
                    j();
                    int a = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, true);
                    if (a != 0) {
                        b(a);
                    } else {
                        this.a.F = true;
                        com.unionpay.mobile.android.model.e a2 = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
                        if (this.a.f39u != null && this.a.f39u.length() > 0) {
                            a(6, a2);
                        } else if (this.a.y != null && this.a.y.length() > 0) {
                            d(5);
                        }
                    }
                    this.f43u = 0;
                    return;
                case 7:
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
                case 8:
                    j();
                    JSONArray d = com.unionpay.mobile.android.utils.i.d(jSONObject, "options");
                    com.unionpay.mobile.android.upviews.a aVar = this.A;
                    if (aVar != null) {
                        aVar.a(d);
                        return;
                    }
                    return;
                case 9:
                    String a5 = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
                    if (a5 == null || !"01".equals(a5)) {
                        JSONArray d2 = com.unionpay.mobile.android.utils.i.d(jSONObject, "options");
                        String a6 = com.unionpay.mobile.android.utils.i.a(jSONObject, "empty_info");
                        com.unionpay.mobile.android.upviews.a aVar2 = this.A;
                        if (aVar2 != null) {
                            aVar2.a(d2, a6);
                            return;
                        }
                        return;
                    }
                    String a7 = com.unionpay.mobile.android.utils.i.a(jSONObject, "uuid");
                    if (this.w >= 0) {
                        c(this.E, a7);
                        return;
                    }
                    String str = com.unionpay.mobile.android.languages.c.bD.D;
                    com.unionpay.mobile.android.upviews.a aVar3 = this.A;
                    if (aVar3 != null) {
                        aVar3.a((JSONArray) null, str);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
        this.s = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
        if (d(jSONObject)) {
            d(com.unionpay.mobile.android.utils.i.a(jSONObject, "fail_msg"));
            return;
        }
        if (this.v > 0 && this.s.equalsIgnoreCase("01")) {
            f(this.t);
            return;
        }
        this.f43u = 0;
        if (this.s.equalsIgnoreCase("00")) {
            if (this.t == 2) {
                this.C = true;
                e(u());
                return;
            }
            j();
            this.f43u = 0;
            this.a.C = com.unionpay.mobile.android.utils.i.d(jSONObject, "result");
            this.a.K = com.unionpay.mobile.android.utils.i.a(jSONObject, "openupgrade_flag");
            this.a.L = com.unionpay.mobile.android.utils.i.a(jSONObject, "temporary_pay_flag");
            this.a.M = com.unionpay.mobile.android.utils.i.a(jSONObject, "temporary_pay_info");
            this.a.Q = com.unionpay.mobile.android.utils.i.a(jSONObject, "front_url");
            this.a.R = com.unionpay.mobile.android.utils.i.a(jSONObject, "front_request");
            this.a.v = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.s.d.v);
            this.a.w = com.unionpay.mobile.android.utils.i.a(jSONObject, "succ_info");
            com.unionpay.mobile.android.nocard.utils.b.b(jSONObject, this.a);
            com.unionpay.mobile.android.nocard.utils.b.a(jSONObject, this.a);
            com.unionpay.mobile.android.upviews.a aVar4 = this.B;
            if (aVar4 != null) {
                aVar4.f();
            }
            a(this.d, this.q + "_succeed");
            d(8);
            return;
        }
        if (!this.s.equalsIgnoreCase("03")) {
            if (this.v <= 0) {
                String c = c(19);
                if (this.a.af != null && !TextUtils.isEmpty(this.a.af)) {
                    c = this.a.af;
                }
                if (this.t == 3) {
                    a(c, true);
                    return;
                } else {
                    a(c);
                    return;
                }
            }
            return;
        }
        String a8 = com.unionpay.mobile.android.utils.i.a(jSONObject, "fail_msg");
        a(this.d, this.q + "_fail", com.unionpay.mobile.android.utils.o.j, new String[]{this.s, a8});
        if (this.t == 3) {
            a(a8);
            return;
        }
        bb bbVar = new bb(this);
        bc bcVar = new bc(this);
        if (this.a.A) {
            this.b.a(bbVar, bcVar);
            this.b.a(com.unionpay.mobile.android.languages.c.bD.ab, a8, com.unionpay.mobile.android.languages.c.bD.ac, com.unionpay.mobile.android.languages.c.bD.ad);
        } else {
            this.b.a(bbVar, null);
            this.b.a(com.unionpay.mobile.android.languages.c.bD.ab, a8, com.unionpay.mobile.android.languages.c.bD.ac);
        }
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(boolean z) {
        TextView textView = this.z;
        if (textView != null) {
            textView.setEnabled(!z);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final boolean a(String str, JSONObject jSONObject) {
        this.D = false;
        if (this.f43u != 1 || !d(jSONObject)) {
            return false;
        }
        d(str);
        return true;
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        com.unionpay.mobile.android.widgets.ax axVar = new com.unionpay.mobile.android.widgets.ax(getContext(), this.a.v, this);
        layoutParams.addRule(13, -1);
        this.k.addView(axVar, layoutParams);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void b(int i) {
        if (this.f43u == 16) {
            if (this.b != null) {
                this.b.c();
            }
            com.unionpay.mobile.android.widgets.y c = this.A.c("instalment");
            if (c != null) {
                com.unionpay.mobile.android.widgets.p pVar = (com.unionpay.mobile.android.widgets.p) c;
                pVar.a(false);
                pVar.b(false);
            }
        }
        super.b(i);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void b(String str, String str2) {
        a(str, str2);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void c() {
        this.m.removeAllViews();
        this.o.a(this);
        LinearLayout linearLayout = this.r;
        linearLayout.setId(linearLayout.hashCode());
        this.r.setOrientation(1);
        this.r.setBackgroundColor(0);
        this.m.addView(this.r, new RelativeLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout2 = this.r;
        linearLayout2.removeAllViews();
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.model.f fVar = (com.unionpay.mobile.android.model.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.a.aP = fVar.a("promotion_instalment_msgbox");
        }
        if (jSONArray.length() > 0) {
            com.unionpay.mobile.android.upviews.a aVar = new com.unionpay.mobile.android.upviews.a(this.d, jSONArray, this, this.q);
            this.A = aVar;
            aVar.a(this.b, this.a.aP);
            this.A.d(this.a.bn);
            this.A.a(this.G);
            this.A.b(this.H);
            this.A.c(this.I);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
            linearLayout2.addView(this.A, layoutParams);
        }
        com.unionpay.mobile.android.upviews.a aVar2 = this.A;
        this.B = new com.unionpay.mobile.android.upviews.a(this.d, this.a.f39u, this.e.c(), this, this.a.al, true, false, aVar2 != null ? aVar2.c("instalment") : null, this.a.Y, this.q);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
        linearLayout2.addView(this.B, layoutParams2);
        LinearLayout linearLayout3 = new LinearLayout(this.d);
        linearLayout3.setOrientation(1);
        linearLayout3.setId(linearLayout3.hashCode());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(3, this.r.getId());
        layoutParams3.leftMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams3.topMargin = layoutParams3.leftMargin;
        this.m.addView(linearLayout3, layoutParams3);
        if (i() || this.a.ag != null || this.a.ah != null) {
            if (this.a.ag != null) {
                com.unionpay.mobile.android.upwidget.a aVar3 = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ag, this.J, this.q + "_agree_user_protocol");
                this.y = aVar3;
                linearLayout3.addView(aVar3);
            }
            if (this.a.ah != null) {
                this.x = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ah, null, this.q + "_remember_bankNO");
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams4.topMargin = com.unionpay.mobile.android.global.a.f;
                linearLayout3.addView(this.x, layoutParams4);
            }
        }
        TextView textView = new TextView(this.d);
        this.z = textView;
        textView.setText(com.unionpay.mobile.android.utils.i.a(this.a.x, "label"));
        this.z.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.z.setTextColor(p());
        this.z.setGravity(17);
        TextView textView2 = this.z;
        com.unionpay.mobile.android.upviews.a aVar4 = this.B;
        textView2.setEnabled(aVar4 == null || aVar4.e());
        int i = com.unionpay.mobile.android.global.a.n;
        this.z.setBackgroundDrawable(this.c.a(2008));
        this.z.setOnClickListener(this.F);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, i);
        layoutParams5.addRule(3, linearLayout3.getId());
        layoutParams5.topMargin = com.unionpay.mobile.android.global.a.f;
        int a = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        layoutParams5.rightMargin = a;
        layoutParams5.leftMargin = a;
        this.m.addView(this.z, layoutParams5);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void c(String str) {
        StringBuilder sb;
        String a;
        this.j = false;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        if (i()) {
            sb = new StringBuilder("\"card\":\"");
            a = this.a.al;
        } else {
            sb = new StringBuilder("\"card\":\"");
            a = this.a.l.get(this.a.I).a();
        }
        sb.append(a);
        sb.append("\"");
        String sb2 = sb.toString();
        com.unionpay.mobile.android.utils.j.a("uppay", "cmd:" + str + ", ele:" + sb2);
        this.e.c(str, sb2);
        this.f43u = 6;
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
        if (this.B.d()) {
            return;
        }
        if (this.a.G) {
            a(13);
            this.a.G = false;
        } else if (this.a.F && this.D) {
            this.a.F = false;
            com.unionpay.mobile.android.nocard.utils.f.a(this.a, this.a.B);
            n();
        } else {
            this.a.F = false;
            this.a.B = null;
            a(2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.B.d();
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
        String str;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        com.unionpay.mobile.android.widgets.y c = this.A.c("promotion");
        if (c != null) {
            str = "\"" + ((com.unionpay.mobile.android.widgets.ai) c).g() + "\"";
        } else {
            str = "\"\"";
        }
        this.e.c("instalment", "\"promotion\":" + str);
        this.f43u = 16;
    }
}
