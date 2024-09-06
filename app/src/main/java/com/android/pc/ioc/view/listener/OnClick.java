package com.android.pc.ioc.view.listener;

import android.view.View;

/* loaded from: classes.dex */
public class OnClick extends OnListener implements View.OnClickListener {
    public long lastTime;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (System.currentTimeMillis() - this.lastTime < 200) {
            return;
        }
        this.lastTime = System.currentTimeMillis();
        invoke(view);
    }

    @Override // com.android.pc.ioc.view.listener.OnListener
    public void listener(View view) {
        view.setOnClickListener(this);
    }
}
