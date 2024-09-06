package com.unionpay.mobile.android.nocard.views;

import android.view.View;

/* loaded from: classes.dex */
final class d implements View.OnClickListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ b b;

    d(b bVar, boolean z) {
        this.b = bVar;
        this.a = z;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.b.j();
        if (this.a) {
            this.b.k();
        }
    }
}
