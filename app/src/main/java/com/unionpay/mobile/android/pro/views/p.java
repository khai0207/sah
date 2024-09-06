package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.unionpay.mobile.android.plugin.BaseActivity;

/* loaded from: classes.dex */
final class p implements View.OnClickListener {
    final /* synthetic */ k a;

    p(k kVar) {
        this.a = kVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Context context;
        context = this.a.d;
        ((BaseActivity) context).startActivityForResult(new Intent("android.settings.NFC_SETTINGS"), 0);
    }
}
