package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class i implements View.OnClickListener {
    final /* synthetic */ g a;

    i(g gVar) {
        this.a = gVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.upviews.a aVar;
        com.unionpay.mobile.android.upwidget.a aVar2;
        com.unionpay.mobile.android.upwidget.a aVar3;
        aVar = this.a.v;
        aVar.d();
        g.a(this.a.d, this.a.q + "_open_user_protocol");
        g gVar = this.a;
        aVar2 = gVar.t;
        String d = aVar2.d();
        aVar3 = this.a.t;
        gVar.a(d, aVar3.c());
    }
}
