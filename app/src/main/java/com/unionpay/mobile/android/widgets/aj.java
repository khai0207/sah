package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
final class aj implements View.OnClickListener {
    final /* synthetic */ ai a;

    aj(ai aiVar) {
        this.a = aiVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        PopupWindow popupWindow;
        PopupWindow popupWindow2;
        popupWindow = this.a.p;
        if (popupWindow != null) {
            popupWindow2 = this.a.p;
            popupWindow2.dismiss();
        }
    }
}
