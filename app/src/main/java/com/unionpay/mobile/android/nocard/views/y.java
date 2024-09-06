package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class y implements View.OnClickListener {
    final /* synthetic */ o a;

    y(o oVar) {
        this.a = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        o.a(this.a.d, "loginpay_support_bank");
        this.a.a(com.unionpay.mobile.android.languages.c.bD.k, this.a.a.n);
    }
}
