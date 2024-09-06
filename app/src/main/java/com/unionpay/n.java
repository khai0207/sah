package com.unionpay;

import android.content.DialogInterface;

/* loaded from: classes.dex */
final class n implements DialogInterface.OnClickListener {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.finish();
    }
}
