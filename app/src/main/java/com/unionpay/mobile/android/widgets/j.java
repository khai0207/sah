package com.unionpay.mobile.android.widgets;

import android.view.View;

/* loaded from: classes.dex */
final class j implements View.OnClickListener {
    final /* synthetic */ g a;

    j(g gVar) {
        this.a = gVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        g.a(this.a, view);
    }
}
