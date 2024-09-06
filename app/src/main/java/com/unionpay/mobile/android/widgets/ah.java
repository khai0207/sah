package com.unionpay.mobile.android.widgets;

import android.view.View;
import com.unionpay.mobile.android.widgets.ag;

/* loaded from: classes.dex */
final class ah implements View.OnClickListener {
    final /* synthetic */ ag a;

    ah(ag agVar) {
        this.a = agVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ag.a aVar;
        ag.a aVar2;
        String str;
        aVar = this.a.n;
        if (aVar != null) {
            ag agVar = this.a;
            agVar.a(agVar.c, this.a.v() + "_change_phoneNO");
            aVar2 = this.a.n;
            str = this.a.p;
            aVar2.e(str);
        }
    }
}
