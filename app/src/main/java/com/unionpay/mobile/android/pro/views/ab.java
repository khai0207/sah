package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.view.View;

/* loaded from: classes.dex */
final class ab implements View.OnClickListener {
    final /* synthetic */ x a;

    ab(x xVar) {
        this.a = xVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Context context;
        com.unionpay.mobile.android.model.b bVar;
        com.unionpay.mobile.android.model.b bVar2;
        com.unionpay.mobile.android.model.b bVar3;
        com.unionpay.mobile.android.model.b bVar4;
        context = this.a.d;
        bVar = this.a.a;
        if (com.unionpay.mobile.android.nocard.utils.c.a(context, bVar) == com.unionpay.mobile.android.views.order.l.c.intValue()) {
            this.a.n();
            this.a.n();
            return;
        }
        this.a.n();
        bVar2 = this.a.a;
        if (bVar2.E) {
            this.a.n();
            bVar4 = this.a.a;
            bVar4.E = false;
        }
        bVar3 = this.a.a;
        bVar3.aK = com.unionpay.mobile.android.views.order.l.c.intValue();
        this.a.d(2);
    }
}
