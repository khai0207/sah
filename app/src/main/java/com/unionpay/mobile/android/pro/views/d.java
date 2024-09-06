package com.unionpay.mobile.android.pro.views;

import android.view.View;

/* loaded from: classes.dex */
final class d implements View.OnClickListener {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.E = str;
        a.i(this.a);
        this.a.d(str, "");
    }
}
