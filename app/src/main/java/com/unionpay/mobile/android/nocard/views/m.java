package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class m implements View.OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ l b;

    m(l lVar, String str) {
        this.b = lVar;
        this.a = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.b.c(this.a);
    }
}
