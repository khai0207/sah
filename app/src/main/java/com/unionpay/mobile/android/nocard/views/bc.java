package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class bc implements View.OnClickListener {
    final /* synthetic */ at a;

    bc(at atVar) {
        this.a = atVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String u2;
        this.a.j();
        this.a.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        at atVar = this.a;
        u2 = atVar.u();
        atVar.e(u2);
    }
}
