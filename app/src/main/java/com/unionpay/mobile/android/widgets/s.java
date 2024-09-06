package com.unionpay.mobile.android.widgets;

import android.view.View;

/* loaded from: classes.dex */
final class s implements View.OnClickListener {
    final /* synthetic */ p a;

    s(p pVar) {
        this.a = pVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        p.a(this.a, view);
    }
}
