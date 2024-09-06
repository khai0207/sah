package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.upviews.a;

/* loaded from: classes.dex */
final class v implements View.OnClickListener {
    final /* synthetic */ o a;

    v(o oVar) {
        this.a = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.upviews.a aVar;
        com.unionpay.mobile.android.upviews.a aVar2;
        com.unionpay.mobile.android.upwidget.a aVar3;
        com.unionpay.mobile.android.upviews.a aVar4;
        String str;
        com.unionpay.mobile.android.upviews.a aVar5;
        com.unionpay.mobile.android.upwidget.a aVar6;
        com.unionpay.mobile.android.upwidget.a aVar7;
        if (this.a.o()) {
            return;
        }
        aVar = this.a.t;
        aVar.d();
        aVar2 = this.a.t;
        a.C0071a b = aVar2.b();
        if (!b.a()) {
            this.a.a(b.b);
            return;
        }
        aVar3 = this.a.C;
        if (aVar3 != null) {
            aVar6 = this.a.C;
            if (!aVar6.e()) {
                o oVar = this.a;
                aVar7 = oVar.C;
                oVar.a(aVar7.b());
                return;
            }
        }
        aVar4 = this.a.D;
        if (aVar4 != null) {
            aVar5 = this.a.D;
            a.C0071a b2 = aVar5.b();
            if (!b2.a()) {
                this.a.a(b2.b);
                return;
            }
            str = b2.b;
        } else {
            str = "";
        }
        String str2 = b.b;
        if (o.b(str)) {
            str2 = str2 + "," + str;
        }
        this.a.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        o.a(this.a.d, this.a.q + "_apply");
        o.b(this.a, str2);
    }
}
