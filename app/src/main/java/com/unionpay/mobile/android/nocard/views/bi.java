package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.unionpay.mobile.android.upviews.b;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class bi extends b implements b.a, b.InterfaceC0072b {
    private static String w = "download://";
    private com.unionpay.mobile.android.upviews.b r;
    private ViewGroup s;
    private int t;

    /* renamed from: u, reason: collision with root package name */
    private boolean f45u;
    private boolean v;

    public bi(Context context) {
        this(context, 0, false, false);
    }

    public bi(Context context, int i, boolean z, boolean z2) {
        super(context);
        this.r = null;
        this.s = null;
        this.t = 0;
        this.f45u = false;
        this.v = false;
        this.f = 14;
        this.t = i;
        this.f45u = z;
        this.v = z2;
        this.k = a();
        b();
        d();
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        com.unionpay.mobile.android.widgets.ax axVar = new com.unionpay.mobile.android.widgets.ax(this.d, this.a.aa, this);
        if (this.f45u) {
            axVar = new com.unionpay.mobile.android.widgets.ax(this.d, this.a.aa, this.c.a(1030), com.unionpay.mobile.android.utils.f.a(this.d, 20.0f), this);
        }
        layoutParams.addRule(13, -1);
        this.k.addView(axVar, layoutParams);
    }

    @Override // com.unionpay.mobile.android.upviews.b.InterfaceC0072b
    public final void c(String str) {
        if (str == null || str.length() <= 0 || !str.startsWith(w)) {
            return;
        }
        String substring = str.substring(w.length());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(substring));
        this.d.startActivity(intent);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void d() {
        super.d();
        com.unionpay.mobile.android.upviews.b bVar = new com.unionpay.mobile.android.upviews.b(this.d, this);
        this.r = bVar;
        bVar.setOnTouchListener(new bj(this));
        if (this.v) {
            this.r.a(w);
        }
        RelativeLayout.LayoutParams layoutParams = this.t == 0 ? new RelativeLayout.LayoutParams(-1, -1) : new RelativeLayout.LayoutParams(-1, this.t);
        layoutParams.addRule(3, this.k.getId());
        layoutParams.addRule(12, -1);
        this.m.addView(this.r, layoutParams);
        this.s = new RelativeLayout(this.d);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.t - com.unionpay.mobile.android.global.a.k);
        layoutParams2.addRule(3, this.k.getId());
        layoutParams2.addRule(12, -1);
        layoutParams2.addRule(10, -1);
        layoutParams2.bottomMargin = 0;
        layoutParams2.topMargin = 0;
        this.m.addView(this.s, layoutParams2);
        ProgressBar progressBar = new ProgressBar(this.d);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13, -1);
        this.s.addView(progressBar, layoutParams3);
        this.r.b(this.a.ab);
        if (this.f45u) {
            a(this.a.bc, false);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
        ((InputMethodManager) this.d.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        if (!this.f45u) {
            super.l();
            return;
        }
        this.b.a(new bk(this), new bl(this));
        this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, com.unionpay.mobile.android.languages.c.bD.av, com.unionpay.mobile.android.languages.c.bD.W, com.unionpay.mobile.android.languages.c.bD.X);
    }

    @Override // com.unionpay.mobile.android.upviews.b.a
    public final void t() {
        this.r.setVisibility(8);
        this.s.setVisibility(0);
    }

    @Override // com.unionpay.mobile.android.upviews.b.a
    public final void u() {
        this.r.setVisibility(0);
        this.s.setVisibility(8);
    }
}
