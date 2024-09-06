package com.android.pc.ioc.view.listener;

import android.view.View;
import android.widget.CompoundButton;
import com.android.pc.ioc.app.Ioc;

/* loaded from: classes.dex */
public class OnCompoundChecked extends OnListener implements CompoundButton.OnCheckedChangeListener {
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        invoke(compoundButton, Boolean.valueOf(z));
    }

    @Override // com.android.pc.ioc.view.listener.OnListener
    protected void listener(View view) {
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setOnCheckedChangeListener(this);
            return;
        }
        Ioc.getIoc().getLogger().e(view.getClass() + " 无法设置OnCompoundChecked 请检查InjectMethod的参数\n");
    }
}
