package com.unionpay.mobile.android.upwidget;

import android.os.Handler;
import android.view.ViewTreeObserver;

/* loaded from: classes.dex */
final class s implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ UPScrollView a;

    s(UPScrollView uPScrollView) {
        this.a = uPScrollView;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        Handler handler;
        Handler handler2;
        handler = this.a.d;
        handler2 = this.a.d;
        handler.sendMessageDelayed(handler2.obtainMessage(), 5L);
    }
}
