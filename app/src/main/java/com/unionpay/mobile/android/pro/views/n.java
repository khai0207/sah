package com.unionpay.mobile.android.pro.views;

import android.view.View;

/* loaded from: classes.dex */
final class n implements View.OnClickListener {
    final /* synthetic */ k a;

    n(k kVar) {
        this.a = kVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.P = str;
        k.m(this.a);
        this.a.c(str, "");
    }
}
