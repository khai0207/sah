package com.unionpay.mobile.android.widgets;

import android.view.View;

/* loaded from: classes.dex */
final class d implements View.OnClickListener {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        a.a(this.a, view);
    }
}
