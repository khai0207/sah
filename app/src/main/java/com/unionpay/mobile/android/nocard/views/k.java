package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class k implements View.OnClickListener {
    final /* synthetic */ g a;

    k(g gVar) {
        this.a = gVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a.j();
        this.a.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        this.a.u();
    }
}
