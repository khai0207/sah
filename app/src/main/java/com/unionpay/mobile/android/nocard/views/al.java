package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.upviews.a;

/* loaded from: classes.dex */
final class al implements View.OnClickListener {
    final /* synthetic */ ak a;

    al(ak akVar) {
        this.a = akVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.upviews.a aVar;
        com.unionpay.mobile.android.upviews.a aVar2;
        com.unionpay.mobile.android.upwidget.a aVar3;
        com.unionpay.mobile.android.upwidget.a aVar4;
        com.unionpay.mobile.android.upwidget.a aVar5;
        if (this.a.o()) {
            return;
        }
        aVar = this.a.v;
        aVar.d();
        aVar2 = this.a.v;
        a.C0071a b = aVar2.b();
        if (!b.a()) {
            this.a.a(b.b);
            return;
        }
        aVar3 = this.a.t;
        if (aVar3 != null) {
            aVar4 = this.a.t;
            if (!aVar4.e()) {
                ak akVar = this.a;
                aVar5 = akVar.t;
                akVar.a(aVar5.b());
                return;
            }
        }
        String str = b.b;
        this.a.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        this.a.e.k(str);
        ak.c(this.a);
    }
}
