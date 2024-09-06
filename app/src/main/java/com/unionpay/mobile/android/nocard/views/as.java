package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class as implements View.OnClickListener {
    final /* synthetic */ ao a;

    as(ao aoVar) {
        this.a = aoVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a.j();
        if (com.unionpay.mobile.android.model.b.bg) {
            ao aoVar = this.a;
            aoVar.c(aoVar.a.bj, this.a.a.bk);
        }
    }
}
