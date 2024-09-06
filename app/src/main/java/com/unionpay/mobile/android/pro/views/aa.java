package com.unionpay.mobile.android.pro.views;

import android.view.View;

/* loaded from: classes.dex */
final class aa implements View.OnClickListener {
    final /* synthetic */ x a;

    aa(x xVar) {
        this.a = xVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.B = str;
        x.m(this.a);
        this.a.c(str, "");
    }
}
