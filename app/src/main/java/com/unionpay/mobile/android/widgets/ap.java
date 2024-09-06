package com.unionpay.mobile.android.widgets;

import android.view.View;
import com.unionpay.mobile.android.widgets.ao;

/* loaded from: classes.dex */
final class ap implements View.OnClickListener {
    final /* synthetic */ ao a;

    ap(ao aoVar) {
        this.a = aoVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ao.a aVar;
        ao.a aVar2;
        aVar = this.a.n;
        if (aVar != null) {
            ao aoVar = this.a;
            aoVar.a(aoVar.c, this.a.v() + "_click_get_msg");
            aVar2 = this.a.n;
            aVar2.a(this.a);
        }
    }
}
