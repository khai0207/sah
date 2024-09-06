package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
final class ar implements View.OnClickListener {
    final /* synthetic */ ao a;

    ar(ao aoVar) {
        this.a = aoVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a.j();
        this.a.a.D.f = Constant.CASH_LOAD_CANCEL;
        this.a.w();
        this.a.k();
    }
}
