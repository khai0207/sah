package com.unionpay;

import android.content.Context;
import android.content.DialogInterface;
import org.json.JSONArray;

/* loaded from: classes.dex */
final class g implements DialogInterface.OnClickListener {
    final /* synthetic */ Context a;

    g(Context context) {
        this.a = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        JSONArray jSONArray;
        Context context = this.a;
        jSONArray = UPPayAssistEx.I;
        UPPayAssistEx.a(context, jSONArray, UPPayAssistEx.o());
        dialogInterface.dismiss();
    }
}
