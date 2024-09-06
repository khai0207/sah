package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
final class h implements View.OnClickListener {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        PopupWindow popupWindow;
        PopupWindow popupWindow2;
        popupWindow = this.a.r;
        if (popupWindow != null) {
            popupWindow2 = this.a.r;
            popupWindow2.dismiss();
        }
    }
}
