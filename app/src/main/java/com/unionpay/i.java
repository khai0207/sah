package com.unionpay;

import android.view.View;

/* loaded from: classes.dex */
final class i implements View.OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    i(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a.finish();
    }
}
