package com.android.pc.ioc.view.listener;

import android.view.MotionEvent;
import android.view.View;
import com.android.pc.ioc.core.kernel.KernelDyna;

/* loaded from: classes.dex */
public class OnTouch extends OnListener implements View.OnTouchListener {
    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return ((Boolean) KernelDyna.to(invoke(view, motionEvent), Boolean.TYPE)).booleanValue();
    }

    @Override // com.android.pc.ioc.view.listener.OnListener
    protected void listener(View view) {
        view.setOnTouchListener(this);
    }
}
