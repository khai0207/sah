package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class be implements View.OnClickListener {
    final /* synthetic */ bd a;

    be(bd bdVar) {
        this.a = bdVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        bd.a(this.a.d, "pay_success_back_merchant");
        this.a.t();
    }
}
