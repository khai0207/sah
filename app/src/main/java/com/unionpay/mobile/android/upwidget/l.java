package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.widget.TextView;
import com.unionpay.mobile.android.widgets.ac;

/* loaded from: classes.dex */
final class l implements View.OnClickListener {
    final /* synthetic */ h a;

    l(h hVar) {
        this.a = hVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        TextView textView;
        TextView textView2;
        ac acVar;
        textView = this.a.m;
        textView.setEnabled(false);
        textView2 = this.a.l;
        textView2.setVisibility(8);
        acVar = this.a.k;
        acVar.setVisibility(0);
    }
}
