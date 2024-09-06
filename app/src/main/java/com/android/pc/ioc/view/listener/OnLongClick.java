package com.android.pc.ioc.view.listener;

import android.view.View;
import com.android.pc.ioc.core.kernel.KernelDyna;

/* loaded from: classes.dex */
public class OnLongClick extends OnListener implements View.OnLongClickListener {
    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        return ((Boolean) KernelDyna.to(invoke(view), Boolean.TYPE)).booleanValue();
    }

    @Override // com.android.pc.ioc.view.listener.OnListener
    protected void listener(View view) {
        view.setOnLongClickListener(this);
    }
}
