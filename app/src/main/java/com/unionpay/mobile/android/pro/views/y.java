package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.view.View;
import com.unionpay.mobile.android.upviews.a;
import java.util.List;

/* loaded from: classes.dex */
final class y implements View.OnClickListener {
    final /* synthetic */ x a;

    y(x xVar) {
        this.a = xVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean o;
        com.unionpay.mobile.android.upviews.a aVar;
        com.unionpay.mobile.android.upviews.a aVar2;
        com.unionpay.mobile.android.upwidget.a aVar3;
        com.unionpay.mobile.android.upwidget.a aVar4;
        com.unionpay.mobile.android.widgets.m mVar;
        Context context;
        String str;
        com.unionpay.mobile.android.model.b bVar;
        com.unionpay.mobile.android.model.b bVar2;
        com.unionpay.mobile.android.model.b bVar3;
        com.unionpay.mobile.android.upviews.a aVar5;
        com.unionpay.mobile.android.model.b bVar4;
        com.unionpay.mobile.android.upviews.a aVar6;
        com.unionpay.mobile.android.upwidget.a aVar7;
        com.unionpay.mobile.android.upwidget.a aVar8;
        com.unionpay.mobile.android.upwidget.a aVar9;
        com.unionpay.mobile.android.upwidget.a aVar10;
        o = this.a.o();
        if (o) {
            return;
        }
        aVar = this.a.A;
        aVar.d();
        aVar2 = this.a.A;
        a.C0071a a = aVar2.a();
        if (!a.a()) {
            this.a.a(a.b);
            return;
        }
        aVar3 = this.a.x;
        if (aVar3 != null) {
            aVar9 = this.a.x;
            if (!aVar9.e()) {
                x xVar = this.a;
                aVar10 = xVar.x;
                xVar.a(aVar10.b());
                return;
            }
        }
        aVar4 = this.a.w;
        if (aVar4 != null) {
            aVar7 = this.a.w;
            if (!aVar7.e()) {
                x xVar2 = this.a;
                aVar8 = xVar2.w;
                xVar2.a(aVar8.b());
                return;
            }
        }
        mVar = this.a.b;
        mVar.a(com.unionpay.mobile.android.languages.c.bD.U);
        context = this.a.d;
        StringBuilder sb = new StringBuilder();
        str = this.a.q;
        sb.append(str);
        sb.append("_apply");
        x.a(context, sb.toString());
        bVar = this.a.a;
        if (bVar.bl) {
            x xVar3 = this.a;
            bVar4 = xVar3.a;
            com.unionpay.mobile.android.model.c cVar = bVar4.bm;
            aVar6 = this.a.A;
            x.a(xVar3, cVar, aVar6.a().b, x.j(this.a));
            return;
        }
        x xVar4 = this.a;
        bVar2 = xVar4.a;
        List<com.unionpay.mobile.android.model.c> list = bVar2.l;
        bVar3 = this.a.a;
        com.unionpay.mobile.android.model.c cVar2 = list.get(bVar3.I);
        aVar5 = this.a.A;
        x.a(xVar4, cVar2, aVar5.a().b, x.j(this.a));
    }
}
