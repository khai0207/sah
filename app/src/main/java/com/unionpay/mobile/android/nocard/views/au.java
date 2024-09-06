package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.upviews.a;

/* loaded from: classes.dex */
final class au implements View.OnClickListener {
    final /* synthetic */ at a;

    au(at atVar) {
        this.a = atVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.upviews.a aVar;
        String str;
        com.unionpay.mobile.android.upviews.a aVar2;
        com.unionpay.mobile.android.upviews.a aVar3;
        com.unionpay.mobile.android.upwidget.a aVar4;
        com.unionpay.mobile.android.upwidget.a aVar5;
        boolean z;
        com.unionpay.mobile.android.upwidget.a aVar6;
        com.unionpay.mobile.android.upwidget.a aVar7;
        com.unionpay.mobile.android.upwidget.a aVar8;
        com.unionpay.mobile.android.upwidget.a aVar9;
        com.unionpay.mobile.android.upviews.a aVar10;
        com.unionpay.mobile.android.upviews.a aVar11;
        if (this.a.o()) {
            return;
        }
        aVar = this.a.A;
        if (aVar != null) {
            aVar10 = this.a.A;
            aVar10.d();
            aVar11 = this.a.A;
            a.C0071a b = aVar11.b();
            if (!b.a()) {
                this.a.a(b.b);
                return;
            }
            str = b.b;
        } else {
            str = "";
        }
        aVar2 = this.a.B;
        aVar2.d();
        aVar3 = this.a.B;
        a.C0071a b2 = aVar3.b();
        if (!b2.a()) {
            this.a.a(b2.b);
            return;
        }
        aVar4 = this.a.y;
        if (aVar4 != null) {
            aVar8 = this.a.y;
            if (!aVar8.e()) {
                at atVar = this.a;
                aVar9 = atVar.y;
                atVar.a(aVar9.b());
                return;
            }
        }
        aVar5 = this.a.x;
        if (aVar5 != null) {
            aVar6 = this.a.x;
            if (!aVar6.e()) {
                at atVar2 = this.a;
                aVar7 = atVar2.x;
                atVar2.a(aVar7.b());
                return;
            }
        }
        String str2 = b2.b;
        if (at.b(str)) {
            str2 = str2 + "," + str;
        }
        this.a.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        at.a(this.a.d, this.a.q + "_apply");
        if (this.a.a.z == null || this.a.a.z.length() <= 0) {
            this.a.e(str2);
            return;
        }
        at atVar3 = this.a;
        z = atVar3.C;
        at.a(atVar3, z, str2);
    }
}
