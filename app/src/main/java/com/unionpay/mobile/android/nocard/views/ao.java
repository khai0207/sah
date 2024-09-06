package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.views.order.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ao extends b implements a.b {
    private Handler A;
    List<Map<String, Object>> r;
    private int s;
    private int t;

    /* renamed from: u */
    private Button f42u;
    private com.unionpay.mobile.android.upviews.a v;
    private com.unionpay.mobile.android.views.order.o w;
    private LinearLayout x;
    private boolean y;
    private Handler z;

    /* loaded from: classes.dex */
    public class a implements o.a {
        public a() {
        }

        @Override // com.unionpay.mobile.android.views.order.b.InterfaceC0074b
        public final int a() {
            ao.this.a.E = true;
            ao.this.d(2);
            return 0;
        }

        @Override // com.unionpay.mobile.android.views.order.b.InterfaceC0074b
        public final int a(int i) {
            ao.this.t = i;
            ao.this.s = 3;
            ao.this.j = false;
            ao.this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
            ao.this.e.l(ao.this.a.l.get(i).a());
            return 0;
        }

        @Override // com.unionpay.mobile.android.views.order.AbstractMethod.a
        public final void a(int i, boolean z, int i2, a.C0071a c0071a) {
            String b;
            if (ao.this.w != null) {
                ao.this.a.aL = ao.this.w.a();
                StringBuilder sb = new StringBuilder();
                sb.append(ao.this.a.aL);
                com.unionpay.mobile.android.utils.j.c("functionEx", sb.toString());
            }
            if (i == com.unionpay.mobile.android.views.order.l.e.intValue()) {
                ao.c(ao.this, i2);
                return;
            }
            if (z) {
                ao.a(ao.this.d, "bankpay_choose_bankcard_next");
                ao.this.a.I = i2;
                ao.this.j = false;
                ao.this.s = 2;
                ao.this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
                if (ao.this.a.l.get(i2).c() == 0) {
                    String a = ao.this.a.l.get(i2).a();
                    ao.this.a.H = "1";
                    b = bh.a(ao.this.a.H, a, "1", "1");
                } else {
                    ao.this.a.H = "0";
                    String b2 = ao.this.a.l.get(i2).b();
                    b = bh.b(ao.this.a.H, "\"pan\":\"" + b2 + "\"", "2", "1\",\"carrier_tp\":\"2");
                }
                ao.this.e.c(bh.a(ao.this.a.g), b);
                return;
            }
            if (!c0071a.a()) {
                ao.this.a(c0071a.b);
                return;
            }
            if (i == com.unionpay.mobile.android.views.order.l.c.intValue()) {
                ao.a(ao.this.d, "login");
                ao.this.j = false;
                ao.this.s = 4;
                ao.this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
                ao.this.e.m(c0071a.b);
                return;
            }
            ao.a(ao.this.d, "bankpay_input_cardNO_next");
            ao.this.j = false;
            ao.this.s = 2;
            ao.this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
            ao.this.a.H = "0";
            ao.this.e.c(bh.a(ao.this.a.g), bh.b(ao.this.a.H, c0071a.b, "1", "1"));
        }

        @Override // com.unionpay.mobile.android.views.order.AbstractMethod.b
        public final void a(String str, String str2) {
            ao.this.a(str, str2);
        }

        @Override // com.unionpay.mobile.android.views.order.b.InterfaceC0074b
        public final int b(int i) {
            ao.a(ao.this.d, "bankpay_choose_bankcard", com.unionpay.mobile.android.utils.o.f, new Object[]{Integer.valueOf(i)});
            return 0;
        }

        @Override // com.unionpay.mobile.android.views.order.CViewMethods.a
        public final void c(int i) {
            if (i == com.unionpay.mobile.android.views.order.l.b.intValue()) {
                ao.a(ao.this.d, "open_bankpay");
                ao.this.a.aK = com.unionpay.mobile.android.views.order.l.b.intValue();
                ao.this.d(2);
                return;
            }
            if (i == com.unionpay.mobile.android.views.order.l.c.intValue()) {
                ao.a(ao.this.d, "open_loginpay");
                ao.c(ao.this);
                return;
            }
            if (i == com.unionpay.mobile.android.views.order.l.d.intValue()) {
                ao.a(ao.this.d, "open_nfcpay");
                ao.this.d(17);
            } else if (i == com.unionpay.mobile.android.views.order.l.e.intValue()) {
                ao.e(ao.this);
            } else if (i == com.unionpay.mobile.android.views.order.l.f.intValue()) {
                ao aoVar = ao.this;
                aoVar.c(aoVar.a.bj, ao.this.a.bk);
            }
        }
    }

    public ao(Context context, com.unionpay.mobile.android.model.e eVar) {
        super(context, eVar);
        this.s = 0;
        this.t = -1;
        this.f42u = null;
        this.v = null;
        this.r = new ArrayList(1);
        this.y = false;
        this.z = null;
        this.A = new Handler(new ap(this));
        this.f = 2;
        if (this.a.az && this.a.au) {
            this.a.aK = com.unionpay.mobile.android.views.order.l.e.intValue();
        }
        this.q = "order";
        setBackgroundColor(-1052684);
        int i = this.a.ac;
        e();
        if (!TextUtils.isEmpty(this.a.p) || this.a.ax) {
            this.z = new Handler(new aq(this));
        }
    }

    public static Map<String, Object> b(com.unionpay.mobile.android.model.d dVar) {
        HashMap hashMap = new HashMap();
        String str = dVar.a().substring(0, 4) + " **** " + dVar.a().substring(dVar.a().length() - 4);
        hashMap.put("text1", dVar.b() + dVar.c());
        hashMap.put("text2", str);
        return hashMap;
    }

    static /* synthetic */ void c(ao aoVar) {
        if (aoVar.a.p == null || aoVar.a.p.length() <= 0) {
            aoVar.a.aK = com.unionpay.mobile.android.views.order.l.c.intValue();
            aoVar.d(2);
        } else {
            aoVar.s = 4;
            aoVar.j = false;
            aoVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
            aoVar.e.m(String.format("\"user_id\":\"%s\"", aoVar.a.p));
        }
    }

    static /* synthetic */ void c(ao aoVar, int i) {
        aoVar.s = 6;
        aoVar.j = false;
        aoVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        String a2 = com.unionpay.mobile.android.model.b.aW.get(i).a();
        String e = com.unionpay.mobile.android.model.b.aW.get(i).e();
        String d = com.unionpay.mobile.android.model.b.aW.get(i).d();
        aoVar.a.aX = i;
        String str = "\"pan\":\"" + a2 + "\",\"carrier_tp\":\"9\",\"issuer\":\"" + e + "\",\"type\":\"" + d + "\"";
        com.unionpay.mobile.android.utils.j.c("uppay", str);
        aoVar.e.c("cardrules", str);
    }

    private void d(JSONObject jSONObject) {
        int a2 = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, false);
        if (a2 != 0) {
            b(a2);
        } else {
            com.unionpay.mobile.android.model.e a3 = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
            if (this.a.f39u != null && this.a.f39u.length() > 0) {
                a(6, a3);
            } else if (this.a.y != null && this.a.y.length() > 0) {
                d(5);
            }
        }
        this.s = 0;
    }

    static /* synthetic */ void e(ao aoVar) {
        if (com.unionpay.mobile.android.model.b.bf && (com.unionpay.mobile.android.model.b.aW == null || com.unionpay.mobile.android.model.b.aW.size() <= 0)) {
            aoVar.a(com.unionpay.mobile.android.languages.c.bD.bq, aoVar.a.bb, (com.unionpay.mobile.android.global.a.t - com.unionpay.mobile.android.global.a.k) - (com.unionpay.mobile.android.global.a.s * 2), aoVar.a.az, true);
        } else {
            aoVar.a.aK = com.unionpay.mobile.android.views.order.l.e.intValue();
            aoVar.d(2);
        }
    }

    private void e(JSONObject jSONObject) {
        int b = com.unionpay.mobile.android.nocard.utils.f.b(this.a, jSONObject);
        if (b != 0) {
            b(b);
            return;
        }
        String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "userId");
        if (!TextUtils.isEmpty(a2)) {
            a(this.d, "_login", com.unionpay.mobile.android.utils.o.e, new Object[]{a2});
        }
        com.unionpay.mobile.android.views.order.o oVar = this.w;
        if (oVar != null) {
            String b2 = oVar.b();
            if (!TextUtils.isEmpty(b2)) {
                PreferenceUtils.d(this.d, b2);
            }
        }
        a(13, com.unionpay.mobile.android.nocard.utils.f.a(jSONObject));
        this.s = 0;
    }

    protected void a(Handler handler) {
    }

    public final void a(com.unionpay.mobile.android.model.a aVar) {
        this.a.bl = true;
        this.a.bm = aVar;
        this.s = 2;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        this.a.H = "0";
        this.e.c(bh.a(this.a.g), bh.b(this.a.H, "\"pan\":\"" + aVar.b() + "\"", "2", "1\",\"carrier_tp\":\"10"));
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(a.C0071a c0071a) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        this.b.c();
        switch (this.s) {
            case 1:
                com.unionpay.mobile.android.model.b bVar = this.a;
                bVar.S = com.unionpay.mobile.android.utils.i.d(jSONObject, "login_rules");
                bVar.T = com.unionpay.mobile.android.utils.i.c(jSONObject, "register_url");
                if (((bVar.S == null || bVar.S.length() <= 0) ? (char) 2 : (char) 0) != 0) {
                    b(2);
                } else {
                    d(12);
                }
                this.s = 0;
                return;
            case 2:
                if (b(jSONObject)) {
                    return;
                }
                d(jSONObject);
                return;
            case 3:
                com.unionpay.mobile.android.model.b bVar2 = this.a;
                int i = this.t;
                if (bVar2.l != null && i < bVar2.l.size()) {
                    bVar2.l.remove(i);
                }
                if (this.a.l == null || this.a.l.size() <= 0) {
                    this.a.I = -1;
                    c();
                } else {
                    if (this.t == this.a.I) {
                        this.a.I = 0;
                    } else if (this.t < this.a.I) {
                        com.unionpay.mobile.android.model.b bVar3 = this.a;
                        bVar3.I--;
                    }
                    this.w.c(this.a.I);
                }
                this.s = 0;
                return;
            case 4:
                if (b(jSONObject)) {
                    return;
                }
                e(jSONObject);
                return;
            case 5:
                j();
                int a2 = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, false);
                if (a2 != 0) {
                    b(a2);
                    return;
                }
                com.unionpay.mobile.android.model.e a3 = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
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
            case 6:
                this.b.c();
                int a4 = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, false);
                if (a4 != 0) {
                    b(a4);
                    return;
                } else {
                    a(18, com.unionpay.mobile.android.nocard.utils.f.a(jSONObject));
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(boolean z) {
        Button button = this.f42u;
        if (button == null || button == null) {
            return;
        }
        button.setEnabled(!z);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        String str;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        String str2 = this.a.j;
        com.unionpay.mobile.android.widgets.ax axVar = new com.unionpay.mobile.android.widgets.ax(this.d, str2, this);
        if (!this.a.E) {
            if ((this.a.az && this.a.aK == com.unionpay.mobile.android.views.order.l.e.intValue()) || this.a.aK == com.unionpay.mobile.android.views.order.l.a.intValue() || (this.a.aK == com.unionpay.mobile.android.views.order.l.c.intValue() && this.a.ax && (this.a.l == null || this.a.l.size() <= 0))) {
                axVar = new com.unionpay.mobile.android.widgets.ax(this.d, str2, this.c.a(1030), com.unionpay.mobile.android.utils.f.a(this.d, 20.0f), this);
            }
            if (this.a.aK == com.unionpay.mobile.android.views.order.l.e.intValue()) {
                str = com.unionpay.mobile.android.languages.c.bD.bq;
            }
            layoutParams.addRule(13, -1);
            this.k.addView(axVar, layoutParams);
        }
        str = com.unionpay.mobile.android.languages.c.bD.l;
        axVar.a(str);
        layoutParams.addRule(13, -1);
        this.k.addView(axVar, layoutParams);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void b(String str, String str2) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b(String str, JSONObject jSONObject) {
        if ("init".equals(str)) {
            if (this.a.E) {
                n();
            }
        } else {
            if (!"".equals(str)) {
                this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
                this.j = false;
                this.s = 5;
                this.e.c(str, "");
                return;
            }
            int i = this.s;
            if (i == 2) {
                d(jSONObject);
            } else if (i == 4) {
                e(jSONObject);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0271 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.unionpay.mobile.android.nocard.views.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void c() {
        /*
            Method dump skipped, instructions count: 818
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.ao.c():void");
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void c(String str) {
    }

    protected void c(String str, String str2) {
    }

    public final void d(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            this.a.D.f = str2;
        }
        a(str, true);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
        if (!this.a.az && this.a.E) {
            super.l();
            this.a.E = false;
            return;
        }
        if (this.a.az || ((this.a.aK == com.unionpay.mobile.android.views.order.l.a.intValue() || (this.a.aK == com.unionpay.mobile.android.views.order.l.c.intValue() && this.a.ax && (this.a.l == null || this.a.l.size() <= 0))) && this.a.aK != com.unionpay.mobile.android.views.order.l.e.intValue())) {
            u();
            return;
        }
        super.l();
        this.a.aK = com.unionpay.mobile.android.views.order.l.a.intValue();
    }

    @Override // com.unionpay.mobile.android.nocard.views.b, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (com.unionpay.mobile.android.model.b.bg && this.a.aK == com.unionpay.mobile.android.views.order.l.a.intValue() && !"1".equalsIgnoreCase(this.a.ai)) {
            com.unionpay.mobile.android.utils.j.c("spay", "out");
            c(this.a.bj, this.a.bk);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.unionpay.mobile.android.upviews.a aVar = this.v;
        if (aVar != null) {
            aVar.clearFocus();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Handler handler;
        super.onDraw(canvas);
        if (this.y || (handler = this.z) == null) {
            return;
        }
        this.y = true;
        handler.sendEmptyMessage(0);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
    }

    public final void u() {
        this.b.a(new ar(this), new as(this));
        this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, com.unionpay.mobile.android.languages.c.bD.av, com.unionpay.mobile.android.languages.c.bD.W, com.unionpay.mobile.android.languages.c.bD.X);
    }

    protected boolean v() {
        return false;
    }

    protected void w() {
    }
}
