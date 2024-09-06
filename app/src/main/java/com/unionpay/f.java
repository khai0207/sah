package com.unionpay;

import android.content.Context;
import android.content.DialogInterface;

/* loaded from: classes.dex */
final class f implements DialogInterface.OnClickListener {
    final /* synthetic */ Context a;

    f(Context context) {
        this.a = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        UPPayAssistEx.a(this.a);
    }
}
