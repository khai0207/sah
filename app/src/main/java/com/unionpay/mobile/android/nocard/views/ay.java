package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* loaded from: classes.dex */
final class ay implements View.OnClickListener {
    final /* synthetic */ at a;

    ay(at atVar) {
        this.a = atVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.upviews.a aVar;
        com.unionpay.mobile.android.upwidget.a aVar2;
        com.unionpay.mobile.android.upwidget.a aVar3;
        aVar = this.a.B;
        aVar.d();
        ((InputMethodManager) this.a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        at.a(this.a.d, this.a.q + "_open_user_protocol");
        at atVar = this.a;
        aVar2 = atVar.y;
        String d = aVar2.d();
        aVar3 = this.a.y;
        atVar.a(d, aVar3.c());
    }
}
