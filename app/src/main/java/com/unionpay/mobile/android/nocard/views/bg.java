package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.nocard.views.bd;

/* loaded from: classes.dex */
final class bg implements View.OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ bd.a d;

    bg(bd.a aVar, int i, String str, String str2) {
        this.d = aVar;
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        bd bdVar = bd.this;
        bd.a(bd.this.d, "pay_success_click_activity", com.unionpay.mobile.android.utils.o.i, new Object[]{Integer.valueOf(this.a), this.b});
        bd.this.a("", this.c);
    }
}
