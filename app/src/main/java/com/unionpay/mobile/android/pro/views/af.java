package com.unionpay.mobile.android.pro.views;

import android.view.View;

/* loaded from: classes.dex */
final class af implements View.OnClickListener {
    final /* synthetic */ x a;

    af(x xVar) {
        this.a = xVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.widgets.m mVar;
        this.a.j();
        mVar = this.a.b;
        mVar.a(com.unionpay.mobile.android.languages.c.bD.U);
        x xVar = this.a;
        x.f(xVar, x.D(xVar));
    }
}
