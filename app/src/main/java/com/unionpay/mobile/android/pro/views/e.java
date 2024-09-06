package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.view.View;

/* loaded from: classes.dex */
final class e implements View.OnClickListener {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Context context;
        com.unionpay.mobile.android.model.b bVar;
        com.unionpay.mobile.android.model.b bVar2;
        context = this.a.d;
        bVar = this.a.a;
        int a = com.unionpay.mobile.android.nocard.utils.c.a(context, bVar);
        this.a.n();
        this.a.n();
        if (a != com.unionpay.mobile.android.views.order.l.c.intValue()) {
            bVar2 = this.a.a;
            bVar2.aK = com.unionpay.mobile.android.views.order.l.c.intValue();
            this.a.d(2);
        }
    }
}
