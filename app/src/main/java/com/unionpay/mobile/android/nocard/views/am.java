package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* loaded from: classes.dex */
final class am implements View.OnClickListener {
    final /* synthetic */ ak a;

    am(ak akVar) {
        this.a = akVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.upviews.a aVar;
        com.unionpay.mobile.android.upwidget.a aVar2;
        com.unionpay.mobile.android.upwidget.a aVar3;
        aVar = this.a.v;
        aVar.d();
        ((InputMethodManager) this.a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        ak.a(this.a.d, this.a.q + "_open_user_protocol");
        ak akVar = this.a;
        aVar2 = akVar.t;
        String d = aVar2.d();
        aVar3 = this.a.t;
        akVar.a(d, aVar3.c());
    }
}
