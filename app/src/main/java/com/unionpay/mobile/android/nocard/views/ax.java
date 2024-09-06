package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class ax implements View.OnClickListener {
    final /* synthetic */ at a;

    ax(at atVar) {
        this.a = atVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (com.unionpay.mobile.android.nocard.utils.c.a(this.a.d, this.a.a) == com.unionpay.mobile.android.views.order.l.c.intValue()) {
            this.a.n();
            this.a.n();
            return;
        }
        this.a.n();
        if (this.a.a.E) {
            this.a.n();
            this.a.a.E = false;
        }
        this.a.a.aK = com.unionpay.mobile.android.views.order.l.c.intValue();
        this.a.d(2);
    }
}
