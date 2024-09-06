package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: classes.dex */
final class av implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ UPWidget a;

    av(UPWidget uPWidget) {
        this.a = uPWidget;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        View A;
        View A2;
        int i;
        int i2;
        com.unionpay.mobile.android.utils.j.a("uppay", "onGlobalLayout() +++");
        A = this.a.A();
        int height = A.getRootView().getHeight();
        A2 = this.a.A();
        int height2 = height - A2.getHeight();
        i = UPWidget.n;
        if (height2 <= i) {
            i2 = UPWidget.n;
            if (height2 <= i2) {
                this.a.l();
            }
        }
        com.unionpay.mobile.android.utils.j.a("uppay", "onGlobalLayout() ---");
    }
}
