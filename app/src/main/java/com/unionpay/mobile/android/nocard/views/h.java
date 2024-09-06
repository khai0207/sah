package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.upviews.a;

/* loaded from: classes.dex */
final class h implements View.OnClickListener {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.upviews.a aVar;
        com.unionpay.mobile.android.upviews.a aVar2;
        com.unionpay.mobile.android.upwidget.a aVar3;
        boolean z;
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
                g gVar = this.a;
                aVar5 = gVar.t;
                gVar.a(aVar5.b());
                return;
            }
        }
        this.a.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        g.a(this.a.d, this.a.q + "_open_apply");
        z = this.a.w;
        if (z) {
            this.a.u();
            this.a.s = 104;
        } else {
            this.a.e.c(com.unionpay.mobile.android.utils.i.a(this.a.a.x, "action"), b.b);
            this.a.s = 102;
        }
    }
}
