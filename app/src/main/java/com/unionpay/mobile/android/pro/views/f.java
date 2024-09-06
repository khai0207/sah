package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.view.View;
import com.unionpay.mobile.android.upviews.a;

/* loaded from: classes.dex */
final class f implements View.OnClickListener {
    final /* synthetic */ a a;

    f(a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean o;
        com.unionpay.mobile.android.upviews.a aVar;
        boolean z;
        com.unionpay.mobile.android.model.b bVar;
        com.unionpay.mobile.android.upviews.a aVar2;
        Context context;
        String str;
        com.unionpay.mobile.android.widgets.m mVar;
        com.unionpay.mobile.android.model.b bVar2;
        o = this.a.o();
        if (o) {
            return;
        }
        aVar = this.a.x;
        aVar.d();
        z = this.a.F;
        if (z) {
            a.p(this.a);
            return;
        }
        bVar = this.a.a;
        if (bVar.k != null) {
            aVar2 = this.a.x;
            a.C0071a a = aVar2.a();
            if (!a.a()) {
                this.a.a(a.b);
                return;
            }
            context = this.a.d;
            StringBuilder sb = new StringBuilder();
            str = this.a.q;
            sb.append(str);
            sb.append("_apply");
            a.a(context, sb.toString());
            mVar = this.a.b;
            mVar.a(com.unionpay.mobile.android.languages.c.bD.U);
            a.u(this.a);
            a aVar3 = this.a;
            bVar2 = aVar3.a;
            aVar3.a(bVar2.k);
        }
    }
}
