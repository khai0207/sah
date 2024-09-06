package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class u implements View.OnClickListener {
    final /* synthetic */ o a;

    u(o oVar) {
        this.a = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.F = str;
        o.a(this.a);
        this.a.c(str, "");
    }
}
