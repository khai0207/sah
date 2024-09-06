package com.unionpay.mobile.android.views.order;

import android.view.View;
import com.unionpay.mobile.android.views.order.CViewMethods;

/* loaded from: classes.dex */
final class h implements View.OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ CViewMethods b;

    h(CViewMethods cViewMethods, int i) {
        this.b = cViewMethods;
        this.a = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        CViewMethods.a aVar;
        CViewMethods.a aVar2;
        com.unionpay.mobile.android.utils.j.c("uppay", " touched " + this.a);
        aVar = this.b.j;
        if (aVar != null) {
            aVar2 = this.b.j;
            aVar2.c(this.a);
        }
    }
}
