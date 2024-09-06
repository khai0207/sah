package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class w implements View.OnClickListener {
    final /* synthetic */ o a;

    w(o oVar) {
        this.a = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        o.a(this.a.d, "loginpay_input_cardNO_next");
        o.e(this.a);
    }
}
