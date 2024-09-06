package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class q implements View.OnClickListener {
    final /* synthetic */ o a;

    q(o oVar) {
        this.a = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.upwidget.a aVar;
        com.unionpay.mobile.android.upwidget.a aVar2;
        o.a(this.a.d, this.a.q + "_open_user_protocol");
        o oVar = this.a;
        aVar = oVar.C;
        String d = aVar.d();
        aVar2 = this.a.C;
        oVar.a(d, aVar2.c());
    }
}
