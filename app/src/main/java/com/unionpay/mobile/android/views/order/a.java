package com.unionpay.mobile.android.views.order;

import android.view.View;

/* loaded from: classes.dex */
final class a implements View.OnClickListener {
    final /* synthetic */ AbstractMethod a;

    a(AbstractMethod abstractMethod) {
        this.a = abstractMethod;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.a.f != null) {
            this.a.f.a(this.a.b(), this.a.c() == null, this.a.d(), this.a.c());
        }
    }
}
