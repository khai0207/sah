package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.PointerIconCompat;
import com.unionpay.mobile.android.upviews.a;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class af extends b implements a.b {
    private TextView r;
    private View.OnClickListener s;
    private com.unionpay.mobile.android.upviews.a t;

    /* renamed from: u, reason: collision with root package name */
    private int f40u;

    public af(Context context) {
        super(context);
        this.r = null;
        this.s = null;
        this.t = null;
        this.f40u = 0;
        this.f = 12;
        this.s = new ag(this);
        setBackgroundColor(-1052684);
        e();
    }

    static /* synthetic */ void a(af afVar) {
        afVar.f40u = 1;
        a.C0071a b = afVar.t.b();
        if (!b.a()) {
            afVar.a(b.b);
            return;
        }
        afVar.j = false;
        afVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        afVar.e.m(b.b);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(a.C0071a c0071a) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        if (this.f40u != 1) {
            return;
        }
        this.b.c();
        com.unionpay.mobile.android.nocard.utils.f.c(this.a, jSONObject);
        int b = com.unionpay.mobile.android.nocard.utils.f.b(this.a, jSONObject);
        if (b != 0) {
            b(b);
            return;
        }
        com.unionpay.mobile.android.upviews.a aVar = this.t;
        if (aVar != null) {
            aVar.f();
        }
        d(13);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void a(boolean z) {
        TextView textView = this.r;
        if (textView != null) {
            textView.setEnabled(!z);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        com.unionpay.mobile.android.widgets.ax axVar = new com.unionpay.mobile.android.widgets.ax(this.d, com.unionpay.mobile.android.languages.c.bD.m, this);
        layoutParams.addRule(13, -1);
        this.k.addView(axVar, layoutParams);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void b(String str, String str2) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void c() {
        this.o.a(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10, -1);
        layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
        com.unionpay.mobile.android.upviews.a aVar = new com.unionpay.mobile.android.upviews.a(this.d, this.a.S, this, "");
        this.t = aVar;
        boolean z = true;
        aVar.setOrientation(1);
        com.unionpay.mobile.android.upviews.a aVar2 = this.t;
        aVar2.setId(aVar2.hashCode());
        this.m.addView(this.t, layoutParams);
        com.unionpay.mobile.android.upwidget.u a = com.unionpay.mobile.android.upwidget.u.a(this.d, this.a.T, this.c.a(PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW));
        if (a != null) {
            a.setId(a.hashCode());
            a.a(new ah(this, a.a()));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(3, this.t.getId());
            int i = com.unionpay.mobile.android.global.a.d;
            layoutParams2.bottomMargin = i;
            layoutParams2.topMargin = i;
            layoutParams2.leftMargin = com.unionpay.mobile.android.global.a.d;
            this.m.addView(a, layoutParams2);
        }
        TextView textView = new TextView(this.d);
        this.r = textView;
        textView.setText(com.unionpay.mobile.android.languages.c.bD.n);
        this.r.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.r.setTextColor(p());
        this.r.setGravity(17);
        TextView textView2 = this.r;
        com.unionpay.mobile.android.upviews.a aVar3 = this.t;
        if (aVar3 != null && !aVar3.e()) {
            z = false;
        }
        textView2.setEnabled(z);
        int i2 = com.unionpay.mobile.android.global.a.n;
        this.r.setBackgroundDrawable(this.c.a(2008));
        this.r.setOnClickListener(this.s);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams3.addRule(3, a != null ? a.getId() : this.t.getId());
        layoutParams3.topMargin = com.unionpay.mobile.android.global.a.f;
        this.m.addView(this.r, layoutParams3);
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void c(String str) {
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
    }
}
