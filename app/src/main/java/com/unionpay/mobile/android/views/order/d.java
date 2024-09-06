package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
final class d implements View.OnClickListener {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        PopupWindow popupWindow;
        PopupWindow popupWindow2;
        popupWindow = this.a.m;
        if (popupWindow != null) {
            popupWindow2 = this.a.m;
            popupWindow2.dismiss();
        }
    }
}
