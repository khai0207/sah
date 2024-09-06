package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* loaded from: classes.dex */
final class ad implements View.OnClickListener {
    final /* synthetic */ x a;

    ad(x xVar) {
        this.a = xVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.upviews.a aVar;
        Context context;
        Context context2;
        String str;
        com.unionpay.mobile.android.upwidget.a aVar2;
        com.unionpay.mobile.android.upwidget.a aVar3;
        aVar = this.a.A;
        aVar.d();
        context = this.a.d;
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        context2 = this.a.d;
        StringBuilder sb = new StringBuilder();
        str = this.a.q;
        sb.append(str);
        sb.append("_open_user_protocol");
        x.a(context2, sb.toString());
        x xVar = this.a;
        aVar2 = xVar.x;
        String d = aVar2.d();
        aVar3 = this.a.x;
        xVar.a(d, aVar3.c());
    }
}
