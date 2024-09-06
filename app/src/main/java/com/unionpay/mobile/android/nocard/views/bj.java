package com.unionpay.mobile.android.nocard.views;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes.dex */
final class bj implements View.OnTouchListener {
    final /* synthetic */ bi a;

    bj(bi biVar) {
        this.a = biVar;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if ((action != 0 && action != 1) || view.hasFocus()) {
            return false;
        }
        view.requestFocus();
        return false;
    }
}
