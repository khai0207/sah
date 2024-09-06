package com.unionpay.mobile.android.widgets;

import android.view.View;

/* loaded from: classes.dex */
final class am implements View.OnClickListener {
    final /* synthetic */ ai a;

    am(ai aiVar) {
        this.a = aiVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ai.a(this.a, view);
    }
}
