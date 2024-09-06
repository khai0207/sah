package com.unionpay;

import android.app.AlertDialog;
import android.view.View;

/* loaded from: classes.dex */
final class m implements View.OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    m(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.a);
        this.a.d = builder.create();
        builder.setMessage(com.unionpay.utils.h.a().a);
        builder.setTitle(com.unionpay.utils.h.a().d);
        builder.setPositiveButton(com.unionpay.utils.h.a().b, new n(this));
        builder.setNegativeButton(com.unionpay.utils.h.a().c, new o(this));
        builder.create().show();
    }
}
