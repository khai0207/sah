package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class r implements View.OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ o b;

    r(o oVar, String str) {
        this.b = oVar;
        this.a = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.b.a(com.unionpay.mobile.android.languages.c.bD.s, this.a);
    }
}
