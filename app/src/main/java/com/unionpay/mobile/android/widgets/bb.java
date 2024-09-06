package com.unionpay.mobile.android.widgets;

import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ScrollView;

/* loaded from: classes.dex */
final class bb implements PopupWindow.OnDismissListener {
    final /* synthetic */ ba a;

    bb(ba baVar) {
        this.a = baVar;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        ScrollView scrollView;
        int i;
        ScrollView scrollView2;
        scrollView = this.a.g;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) scrollView.getLayoutParams();
        marginLayoutParams.bottomMargin = com.unionpay.mobile.android.global.a.b;
        i = this.a.h;
        marginLayoutParams.height = i;
        scrollView2 = this.a.g;
        scrollView2.setLayoutParams(marginLayoutParams);
    }
}
