package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.nocard.views.bh;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.widgets.ax;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class x extends com.unionpay.mobile.android.nocard.views.b implements Handler.Callback, a.b {
    private com.unionpay.mobile.android.upviews.a A;
    private String B;
    private boolean C;
    private Handler D;
    private View.OnClickListener E;
    private View.OnClickListener F;
    private View.OnClickListener G;
    private View.OnClickListener H;
    private View.OnClickListener I;
    private String r;
    private int s;
    private int t;

    /* renamed from: u, reason: collision with root package name */
    private int f53u;
    private int v;
    private com.unionpay.mobile.android.upwidget.a w;
    private com.unionpay.mobile.android.upwidget.a x;
    private TextView y;
    private com.unionpay.mobile.android.upviews.a z;

    public x(Context context, com.unionpay.mobile.android.model.e eVar) {
        super(context, eVar);
        this.r = "00";
        this.s = 0;
        this.t = 0;
        this.f53u = 20;
        this.v = 5;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.C = false;
        this.D = null;
        this.E = new y(this);
        this.F = new z(this);
        this.G = new aa(this);
        this.H = new ab(this);
        this.I = new ad(this);
        this.f = 6;
        this.q = "sdpay";
        this.D = new Handler(this);
        this.C = this.a.F;
        setBackgroundColor(-1052684);
        e();
    }

    static /* synthetic */ int A(x xVar) {
        xVar.t = 0;
        return 0;
    }

    static /* synthetic */ String D(x xVar) {
        com.unionpay.mobile.android.upviews.a aVar = xVar.A;
        String str = "";
        if (aVar != null) {
            a.C0071a b = aVar.b();
            if (b.a()) {
                str = "" + b.b;
            }
        }
        com.unionpay.mobile.android.upviews.a aVar2 = xVar.z;
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

    static /* synthetic */ void a(x xVar, com.unionpay.mobile.android.model.c cVar, String str, HashMap hashMap) {
        xVar.t = 3;
        com.unionpay.mobile.android.pro.pboc.engine.b u2 = xVar.u();
        if (u2 == null) {
            xVar.b(5);
        } else {
            new Thread(new ac(xVar, u2, cVar, str, hashMap)).start();
        }
    }

    static /* synthetic */ void a(x xVar, String str, String str2) {
        xVar.t = 7;
        xVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        xVar.e.c(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        this.t = 8;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.v--;
    }

    private void f(int i) {
        this.t = 4;
        this.s = i;
        this.e.a("query", this.a.ae, 3);
        this.f53u--;
    }

    static /* synthetic */ void f(x xVar, String str) {
        xVar.j = false;
        xVar.t = 3;
        String a = bh.a(xVar.a.x);
        com.unionpay.mobile.android.upwidget.a aVar = xVar.w;
        xVar.e.c(a, bh.c("2", "1", aVar != null ? aVar.a() : null, str));
    }

    static /* synthetic */ HashMap j(x xVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        com.unionpay.mobile.android.upviews.a aVar = xVar.A;
        if (aVar != null) {
            hashMap = aVar.c();
        }
        com.unionpay.mobile.android.upviews.a aVar2 = xVar.z;
        if (aVar2 != null) {
            HashMap<String, String> c = aVar2.c();
            if (c != null && hashMap != null) {
                hashMap.putAll(c);
            } else if (c != null && hashMap == null) {
                return c;
            }
        }
        return hashMap;
    }

    static /* synthetic */ int m(x xVar) {
        xVar.v = 5;
        return 5;
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
        this.t = 1;
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        this.C = false;
        int i = this.t;
        if (i == 1) {
            j();
            this.t = 0;
            this.A.a(com.unionpay.mobile.android.global.b.p);
            return;
        }
        if (i == 3) {
            this.a.ae = com.unionpay.mobile.android.utils.h.a(jSONObject.toString());
            if (this.a.ae == null || this.a.ae.length() <= 0) {
                b(2);
                return;
            } else {
                this.f53u = 20;
                f(this.t);
                return;
            }
        }
        if (i != 4) {
            if (i == 6) {
                j();
                int a = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, false);
                if (a != 0) {
                    b(a);
                } else {
                    this.a.F = true;
                    if (this.a.f39u != null && this.a.f39u.length() > 0) {
                        d(6);
                    } else if (this.a.y != null && this.a.y.length() > 0) {
                        d(5);
                    }
                }
                this.t = 0;
                return;
            }
            if (i == 7) {
                j();
                JSONArray d = com.unionpay.mobile.android.utils.i.d(jSONObject, "options");
                com.unionpay.mobile.android.upviews.a aVar = this.z;
                if (aVar != null) {
                    aVar.a(d);
                    return;
                }
                return;
            }
            if (i != 8) {
                return;
            }
            String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
            if (a2 == null || !"01".equals(a2)) {
                JSONArray d2 = com.unionpay.mobile.android.utils.i.d(jSONObject, "options");
                String a3 = com.unionpay.mobile.android.utils.i.a(jSONObject, "empty_info");
                com.unionpay.mobile.android.upviews.a aVar2 = this.z;
                if (aVar2 != null) {
                    aVar2.a(d2, a3);
                    return;
                }
                return;
            }
            String a4 = com.unionpay.mobile.android.utils.i.a(jSONObject, "uuid");
            if (this.v >= 0) {
                c(this.B, a4);
                return;
            }
            String str = com.unionpay.mobile.android.languages.c.bD.D;
            com.unionpay.mobile.android.upviews.a aVar3 = this.z;
            if (aVar3 != null) {
                aVar3.a((JSONArray) null, str);
                return;
            }
            return;
        }
        String a5 = com.unionpay.mobile.android.utils.i.a(jSONObject, "status");
        this.r = a5;
        if (this.f53u > 0 && a5.equalsIgnoreCase("01")) {
            f(this.s);
            return;
        }
        this.t = 0;
        if (this.r.equalsIgnoreCase("00")) {
            j();
            this.t = 0;
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
            com.unionpay.mobile.android.upviews.a aVar4 = this.A;
            if (aVar4 != null) {
                aVar4.f();
            }
            a(this.d, this.q + "_succeed");
            d(8);
            return;
        }
        if (!this.r.equalsIgnoreCase("03")) {
            if (this.f53u <= 0) {
                if (this.s == 3) {
                    a(this.a.af, true);
                    return;
                } else {
                    a(this.a.af);
                    return;
                }
            }
            return;
        }
        String a6 = com.unionpay.mobile.android.utils.i.a(jSONObject, "fail_msg");
        a(this.d, this.q + "_fail", com.unionpay.mobile.android.utils.o.j, new String[]{this.r, a6});
        if (this.t == 3) {
            a(a6);
            return;
        }
        ae aeVar = new ae(this);
        af afVar = new af(this);
        if (this.a.A) {
            this.b.a(aeVar, afVar);
            this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, a6, com.unionpay.mobile.android.languages.c.bD.W, com.unionpay.mobile.android.languages.c.bD.X);
        } else {
            this.b.a(aeVar, null);
            this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, a6, com.unionpay.mobile.android.languages.c.bD.W);
        }
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(boolean z) {
        TextView textView = this.y;
        if (textView != null) {
            textView.setEnabled(!z);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final boolean a(String str, JSONObject jSONObject) {
        this.C = false;
        return false;
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        ax axVar = new ax(getContext(), this.a.v, this);
        layoutParams.addRule(13, -1);
        this.k.addView(axVar, layoutParams);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void b(String str, String str2) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void c() {
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setId(linearLayout.hashCode());
        boolean z = true;
        linearLayout.setOrientation(1);
        this.m.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -2));
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.model.f fVar = (com.unionpay.mobile.android.model.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.a.aP = fVar.a("promotion_instalment_msgbox");
        }
        if (jSONArray.length() > 0) {
            com.unionpay.mobile.android.upviews.a aVar = new com.unionpay.mobile.android.upviews.a(this.d, jSONArray, this, this.q);
            this.z = aVar;
            aVar.a(this.b, this.a.aP);
            this.z.a(this.F);
            this.z.b(this.G);
            this.z.c(this.H);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
            linearLayout.addView(this.z, layoutParams);
        }
        com.unionpay.mobile.android.upviews.a aVar2 = this.z;
        if (aVar2 != null) {
            aVar2.c("instalment");
        }
        this.A = new com.unionpay.mobile.android.upviews.a(this.d, this.a.f39u, this.e.c(), this, this.a.al, false, this.q);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
        linearLayout.addView(this.A, layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        linearLayout2.setId(linearLayout2.hashCode());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(3, linearLayout.getId());
        layoutParams3.leftMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams3.topMargin = layoutParams3.leftMargin;
        this.m.addView(linearLayout2, layoutParams3);
        if (i() || this.a.ag != null || this.a.ah != null) {
            if (this.a.ag != null) {
                com.unionpay.mobile.android.upwidget.a aVar3 = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ag, this.I, this.q + "_agree_user_protocol");
                this.x = aVar3;
                linearLayout2.addView(aVar3);
            }
            if (this.a.ah != null) {
                this.w = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ah, null, this.q + "_remember_bankNO");
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams4.topMargin = com.unionpay.mobile.android.global.a.f;
                linearLayout2.addView(this.w, layoutParams4);
            }
        }
        TextView textView = new TextView(this.d);
        this.y = textView;
        textView.setText(com.unionpay.mobile.android.utils.i.a(this.a.x, "label"));
        this.y.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.y.setTextColor(p());
        this.y.setGravity(17);
        TextView textView2 = this.y;
        com.unionpay.mobile.android.upviews.a aVar4 = this.A;
        if (aVar4 != null && !aVar4.e()) {
            z = false;
        }
        textView2.setEnabled(z);
        int i = com.unionpay.mobile.android.global.a.n;
        this.y.setBackgroundDrawable(this.c.a(2008));
        this.y.setOnClickListener(this.E);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, i);
        layoutParams5.addRule(3, linearLayout2.getId());
        layoutParams5.topMargin = com.unionpay.mobile.android.global.a.f;
        int a = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        layoutParams5.rightMargin = a;
        layoutParams5.leftMargin = a;
        this.m.addView(this.y, layoutParams5);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void c(String str) {
        this.j = false;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        String str2 = "\"card\":\"" + this.a.l.get(this.a.I).a() + "\"";
        com.unionpay.mobile.android.utils.j.a("uppay", "cmd:" + str + ", ele:" + str2);
        this.e.c(str, str2);
        this.t = 6;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            Bundle bundle = (Bundle) message.obj;
            String string = bundle.getString("action_resp_code");
            String string2 = bundle.getString("action_resp_message");
            if (!"0000".equalsIgnoreCase(string)) {
                a(this.a.ak, false);
            } else if (string2 != null) {
                a(0, string2);
            }
            return true;
        }
        b(1);
        return true;
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
        if (this.A.d()) {
            return;
        }
        if (this.a.F && this.C) {
            this.a.F = false;
            n();
        } else {
            this.a.F = false;
            this.a.bl = false;
            a(2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.A.d();
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
    }

    public com.unionpay.mobile.android.pro.pboc.engine.b u() {
        return null;
    }
}
