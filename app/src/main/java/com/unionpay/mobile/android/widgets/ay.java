package com.unionpay.mobile.android.widgets;

import android.view.View;
import com.unionpay.mobile.android.widgets.ax;

/* loaded from: classes.dex */
final class ay implements View.OnClickListener {
    final /* synthetic */ ax a;

    ay(ax axVar) {
        this.a = axVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ax.a aVar;
        ax.a aVar2;
        aVar = this.a.k;
        if (aVar != null) {
            aVar2 = this.a.k;
            aVar2.m();
        }
    }
}
