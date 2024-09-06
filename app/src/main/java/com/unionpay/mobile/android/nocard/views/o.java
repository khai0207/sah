package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.upviews.a;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class o extends com.unionpay.mobile.android.nocard.views.b implements a.b {
    private int A;
    private int B;
    private com.unionpay.mobile.android.upwidget.a C;
    private com.unionpay.mobile.android.upviews.a D;
    private b E;
    private String F;
    private View.OnClickListener G;
    private View.OnClickListener H;
    private boolean I;
    LinearLayout r;
    private int s;
    private com.unionpay.mobile.android.upviews.a t;

    /* renamed from: u, reason: collision with root package name */
    private View.OnClickListener f48u;
    private View.OnClickListener v;
    private View.OnClickListener w;
    private TextView x;
    private int y;
    private int z;

    /* loaded from: classes.dex */
    public interface a {
        void a(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    class b extends LinearLayout {
        private PopupWindow b;
        private com.unionpay.mobile.android.upwidget.c c;
        private com.unionpay.mobile.android.upwidget.e d;
        private String e;
        private TextView f;
        private int g;
        private final View.OnClickListener h;
        private final AdapterView.OnItemClickListener i;
        private List<Map<String, Object>> j;
        private a k;
        private String l;

        /* JADX WARN: Multi-variable type inference failed */
        public b(Context context, a aVar, List<Map<String, Object>> list, JSONArray jSONArray, String str) {
            super(context);
            this.g = 1;
            this.h = new ac(this);
            this.i = new ad(this);
            setOrientation(1);
            this.k = aVar;
            this.j = list;
            this.e = jSONArray;
            this.l = str;
            this.c = new com.unionpay.mobile.android.upwidget.c(o.this.d, this.j, this.e, this.l, "", this.g, 0);
            com.unionpay.mobile.android.upwidget.e eVar = new com.unionpay.mobile.android.upwidget.e(o.this.d, this.c);
            this.d = eVar;
            eVar.a(this.i);
            this.d.a(this.h);
            if (list == null || list.size() <= 0) {
                return;
            }
            Drawable a = com.unionpay.mobile.android.resource.c.a(o.this.d).a(2014);
            RelativeLayout relativeLayout = new RelativeLayout(o.this.d);
            relativeLayout.setBackgroundDrawable(a);
            relativeLayout.setOnClickListener(new ae(this));
            addView(relativeLayout, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n));
            ImageView imageView = new ImageView(o.this.d);
            imageView.setId(imageView.hashCode());
            imageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(o.this.d).a(1002));
            int a2 = com.unionpay.mobile.android.utils.f.a(o.this.d, 15.0f);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a2);
            layoutParams.addRule(11, -1);
            layoutParams.addRule(15, -1);
            layoutParams.rightMargin = com.unionpay.mobile.android.utils.f.a(o.this.d, 10.0f);
            relativeLayout.addView(imageView, layoutParams);
            TextView textView = new TextView(o.this.d);
            this.f = textView;
            textView.setTextSize(com.unionpay.mobile.android.global.b.k);
            this.f.setTextColor(-10066330);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            this.f.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            this.f.setSingleLine(true);
            layoutParams2.leftMargin = com.unionpay.mobile.android.utils.f.a(o.this.d, 10.0f);
            layoutParams2.rightMargin = layoutParams2.leftMargin;
            layoutParams2.addRule(15, -1);
            layoutParams2.addRule(9, -1);
            layoutParams2.addRule(0, imageView.getId());
            relativeLayout.addView(this.f, layoutParams2);
            a(0);
        }

        static /* synthetic */ void a(b bVar, View view) {
            if (bVar.b == null) {
                bVar.b = new PopupWindow((View) bVar.d, -1, -1, true);
                bVar.b.setBackgroundDrawable(new ColorDrawable(-1342177280));
                bVar.b.update();
            }
            bVar.b.showAtLocation(view, 80, 0, 0);
        }

        public final void a(int i) {
            int c = i + this.c.c();
            TextView textView = this.f;
            if (textView != null) {
                textView.setText(this.c.b(c));
            }
        }
    }

    public o(Context context, com.unionpay.mobile.android.model.e eVar) {
        super(context, eVar);
        this.s = 0;
        this.t = null;
        this.f48u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.r = null;
        this.y = 0;
        this.z = 0;
        this.A = 20;
        this.B = 5;
        this.C = null;
        this.D = null;
        this.G = new p(this);
        this.H = new u(this);
        this.I = false;
        this.f = 13;
        this.q = this.a.F ? "loginpay_phoneNO_change" : "loginpay";
        this.f48u = new v(this);
        this.v = new w(this);
        this.w = new x(this);
        if (!q() && !v() && !this.a.aU) {
            this.I = true;
        }
        setBackgroundColor(-1052684);
        e();
        if (this.a.aA != null) {
            c((JSONObject) null);
        }
    }

    static /* synthetic */ int a(o oVar) {
        oVar.B = 5;
        return 5;
    }

    private void a(LinearLayout linearLayout) {
        JSONArray jSONArray = this.a.X;
        if (jSONArray == null) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            com.unionpay.mobile.android.widgets.y a2 = a((JSONObject) com.unionpay.mobile.android.utils.i.b(jSONArray, i), this.q);
            if (a2 != null) {
                linearLayout.addView(a2);
            }
        }
    }

    static /* synthetic */ void a(o oVar, int i) {
        if (oVar.a.W != null && i == oVar.a.W.size()) {
            oVar.a.aU = true;
            oVar.I = true;
            oVar.d(13);
            return;
        }
        a(oVar.d, "loginpay_choose_bankcard", com.unionpay.mobile.android.utils.o.f, new Object[]{Integer.valueOf(i)});
        oVar.I = false;
        oVar.z = oVar.y;
        oVar.y = i;
        String a2 = oVar.a.W.get(i).a();
        oVar.j = false;
        oVar.s = 1;
        oVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        oVar.e.i(bh.a("1", a2, "1", "2"));
    }

    static /* synthetic */ void a(o oVar, String str, String str2) {
        oVar.s = 8;
        oVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        oVar.e.c(str, str2);
    }

    static /* synthetic */ void b(o oVar, String str) {
        oVar.j = false;
        oVar.s = 3;
        oVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        oVar.e.a("1", "2", "yes", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        this.s = 9;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.B--;
    }

    private void d(JSONObject jSONObject) {
        int a2 = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, false);
        if (a2 != 0) {
            b(a2);
            if (1 == this.s) {
                f(this.z);
                return;
            }
            return;
        }
        com.unionpay.mobile.android.model.e a3 = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
        if (5 == this.s) {
            if (this.a.f39u != null && this.a.f39u.length() > 0) {
                a(6, a3);
                return;
            } else {
                if (this.a.y == null || this.a.y.length() <= 0) {
                    return;
                }
                d(5);
                return;
            }
        }
        this.p = a3;
        f(this.y);
        this.D.a(u(), this.a.al, true, null, this.a.Y, this.q);
        this.D.a(this.G);
        this.D.b(this.H);
        this.D.a(this.b, this.a.aP);
        this.D.d(this.a.bn);
        com.unionpay.mobile.android.upviews.a aVar = this.D;
        this.t.a(this.a.f39u, this.a.al, true, aVar != null ? aVar.c("instalment") : null, this.a.Y, this.q);
        TextView textView = this.x;
        com.unionpay.mobile.android.upviews.a aVar2 = this.t;
        textView.setEnabled(aVar2 == null || aVar2.e());
    }

    static /* synthetic */ void e(o oVar) {
        com.unionpay.mobile.android.upviews.a aVar = oVar.t;
        if (aVar != null) {
            a.C0071a b2 = aVar.b();
            if (!b2.a()) {
                oVar.a(b2.b);
                return;
            }
            oVar.j = false;
            oVar.s = 5;
            oVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
            oVar.e.c("bindcardrules", b2.b);
        }
    }

    private void f(int i) {
        this.y = i;
        this.E.a(i);
    }

    private JSONArray u() {
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.model.f fVar = (com.unionpay.mobile.android.model.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.a.aP = fVar.a("promotion_instalment_msgbox");
        }
        return jSONArray;
    }

    private final boolean v() {
        return (this.a.aU || this.a.W == null || this.a.W.size() <= 0) ? false : true;
    }

    private void w() {
        this.s = 4;
        this.e.a("query", this.a.ae, 3);
        this.A--;
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(a.C0071a c0071a) {
        this.t.d();
        if (!c0071a.a()) {
            a(c0071a.b);
            return;
        }
        this.j = false;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        this.e.c("sms", c0071a.b);
        this.s = 2;
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        int i = this.s;
        if (i == 16) {
            if (this.b.a()) {
                this.b.c();
            }
            new JSONObject();
            if (TextUtils.isEmpty(com.unionpay.mobile.android.utils.i.a(jSONObject, "instalment_empty_info"))) {
                jSONObject = com.unionpay.mobile.android.utils.i.c(jSONObject, "instalment");
            }
            this.D.a(jSONObject);
            this.s = 0;
            return;
        }
        switch (i) {
            case 1:
            case 5:
                j();
                if (b(jSONObject)) {
                    return;
                }
                if (this.s == 5) {
                    this.a.G = true;
                }
                d(jSONObject);
                return;
            case 2:
                j();
                this.t.a(com.unionpay.mobile.android.global.b.p);
                return;
            case 3:
                this.a.ae = com.unionpay.mobile.android.utils.h.a(jSONObject.toString());
                if (this.a.ae == null) {
                    b(2);
                    return;
                } else {
                    this.A = 20;
                    w();
                    return;
                }
            case 4:
                String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
                if (this.A > 0 && a2.equalsIgnoreCase("01")) {
                    w();
                    return;
                }
                j();
                if (!a2.equalsIgnoreCase("00")) {
                    if (!a2.equalsIgnoreCase("03")) {
                        if (this.A <= 0) {
                            b(19);
                            return;
                        }
                        return;
                    }
                    String a3 = com.unionpay.mobile.android.utils.i.a(jSONObject, "fail_msg");
                    a(this.d, this.q + "_fail", com.unionpay.mobile.android.utils.o.j, new String[]{a2, a3});
                    StringBuilder sb = new StringBuilder();
                    sb.append(a3);
                    a(sb.toString());
                    return;
                }
                this.s = 0;
                this.a.C = com.unionpay.mobile.android.utils.i.d(jSONObject, "result");
                this.a.K = com.unionpay.mobile.android.utils.i.a(jSONObject, "openupgrade_flag");
                this.a.L = com.unionpay.mobile.android.utils.i.a(jSONObject, "temporary_pay_flag");
                this.a.M = com.unionpay.mobile.android.utils.i.a(jSONObject, "temporary_pay_info");
                this.a.Q = com.unionpay.mobile.android.utils.i.a(jSONObject, "front_url");
                this.a.R = com.unionpay.mobile.android.utils.i.a(jSONObject, "front_request");
                this.a.v = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.s.d.v);
                this.a.w = com.unionpay.mobile.android.utils.i.a(jSONObject, "succ_info");
                com.unionpay.mobile.android.nocard.utils.b.a(jSONObject, this.a);
                com.unionpay.mobile.android.nocard.utils.b.b(jSONObject, this.a);
                a(this.d, this.q + "_succeed");
                d(8);
                return;
            case 6:
                j();
                int a4 = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, true);
                if (a4 != 0) {
                    b(a4);
                } else {
                    this.a.F = true;
                    com.unionpay.mobile.android.model.e a5 = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
                    if (this.a.f39u != null && this.a.f39u.length() > 0) {
                        a(6, a5);
                    } else if (this.a.y != null && this.a.y.length() > 0) {
                        d(5);
                    }
                }
                this.s = 0;
                return;
            case 7:
                j();
                int a6 = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, false);
                if (a6 != 0) {
                    b(a6);
                    return;
                }
                com.unionpay.mobile.android.model.e a7 = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
                if (this.a.f39u != null && this.a.f39u.length() > 0) {
                    a(6, a7);
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
                com.unionpay.mobile.android.upviews.a aVar = this.D;
                if (aVar != null) {
                    aVar.a(d);
                    return;
                }
                return;
            case 9:
                String a8 = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
                if (a8 == null || !"01".equals(a8)) {
                    JSONArray d2 = com.unionpay.mobile.android.utils.i.d(jSONObject, "options");
                    String a9 = com.unionpay.mobile.android.utils.i.a(jSONObject, "empty_info");
                    com.unionpay.mobile.android.upviews.a aVar2 = this.D;
                    if (aVar2 != null) {
                        aVar2.a(d2, a9);
                        return;
                    }
                    return;
                }
                String a10 = com.unionpay.mobile.android.utils.i.a(jSONObject, "uuid");
                if (this.B >= 0) {
                    c(this.F, a10);
                    return;
                }
                String str = com.unionpay.mobile.android.languages.c.bD.D;
                com.unionpay.mobile.android.upviews.a aVar3 = this.D;
                if (aVar3 != null) {
                    aVar3.a((JSONArray) null, str);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(boolean z) {
        this.x.setEnabled(!z);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final boolean a(String str, JSONObject jSONObject) {
        if (this.s != 1) {
            return false;
        }
        f(this.z);
        j();
        a(str);
        return true;
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        String str = com.unionpay.mobile.android.languages.c.bD.o;
        com.unionpay.mobile.android.widgets.ax axVar = new com.unionpay.mobile.android.widgets.ax(this.d, str, this);
        if (this.a.ax && ((this.a.l == null || this.a.l.size() == 0) && !this.a.aU && !TextUtils.isEmpty(this.a.p))) {
            axVar = new com.unionpay.mobile.android.widgets.ax(this.d, str, this.c.a(1030), com.unionpay.mobile.android.utils.f.a(this.d, 20.0f), this);
        }
        layoutParams.addRule(13, -1);
        this.k.addView(axVar, layoutParams);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void b(int i) {
        if (this.s == 16) {
            if (this.b != null) {
                this.b.c();
            }
            com.unionpay.mobile.android.widgets.y c = this.D.c("instalment");
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
    protected final void b(String str, JSONObject jSONObject) {
        if ("init".equals(str)) {
            a(2);
            return;
        }
        if (!"".equals(str)) {
            this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
            this.j = false;
            this.s = 7;
            this.e.c(str, "");
            return;
        }
        if (this.s == 5) {
            this.a.G = true;
        }
        if (jSONObject != null) {
            d(jSONObject);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0369, code lost:
    
        if (r1.e() == false) goto L68;
     */
    @Override // com.unionpay.mobile.android.nocard.views.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void c() {
        /*
            Method dump skipped, instructions count: 1047
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.o.c():void");
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void c(String str) {
        StringBuilder sb;
        String a2;
        this.j = false;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        if (this.a.aU) {
            sb = new StringBuilder("\"card\":\"");
            a2 = this.a.al;
        } else {
            sb = new StringBuilder("\"card\":\"");
            a2 = this.a.W.get(this.y).a();
        }
        sb.append(a2);
        sb.append("\"");
        String sb2 = sb.toString();
        com.unionpay.mobile.android.utils.j.a("uppay", "cmd:" + str + ", ele:" + sb2);
        this.e.c(str, sb2);
        this.s = 6;
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
        if (!TextUtils.isEmpty(this.a.p) && this.a.ax && (this.a.l == null || this.a.l.size() == 0)) {
            this.b.a(new s(this), new t(this));
            this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, com.unionpay.mobile.android.languages.c.bD.av, com.unionpay.mobile.android.languages.c.bD.W, com.unionpay.mobile.android.languages.c.bD.X);
            return;
        }
        if (this.a.aU) {
            this.a.aU = false;
        }
        com.unionpay.mobile.android.upviews.a aVar = this.t;
        if (aVar == null || !aVar.d()) {
            if (this.a.p == null || this.a.p.length() <= 0) {
                n();
            } else {
                a(2);
            }
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b, android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
        String str;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        com.unionpay.mobile.android.widgets.y c = this.D.c("promotion");
        if (c != null) {
            str = "\"" + ((com.unionpay.mobile.android.widgets.ai) c).g() + "\"";
        } else {
            str = "\"\"";
        }
        this.e.c("instalment", "\"promotion\":" + str);
        this.s = 16;
    }
}
