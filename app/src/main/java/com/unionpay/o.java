package com.unionpay;

import android.app.AlertDialog;
import android.content.DialogInterface;

/* loaded from: classes.dex */
final class o implements DialogInterface.OnClickListener {
    final /* synthetic */ m a;

    o(m mVar) {
        this.a = mVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AlertDialog alertDialog;
        alertDialog = this.a.a.d;
        alertDialog.dismiss();
    }
}
