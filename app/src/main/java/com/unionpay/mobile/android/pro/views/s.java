package com.unionpay.mobile.android.pro.views;

import android.view.View;
import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
final class s implements View.OnClickListener {
    final /* synthetic */ k a;

    s(k kVar) {
        this.a = kVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.unionpay.mobile.android.model.b bVar;
        this.a.j();
        bVar = this.a.a;
        bVar.D.f = Constant.CASH_LOAD_CANCEL;
        this.a.k();
    }
}
