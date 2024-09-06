package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class aw implements View.OnClickListener {
    final /* synthetic */ at a;

    aw(at atVar) {
        this.a = atVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.E = str;
        at.f(this.a);
        this.a.c(str, "");
    }
}
