package com.android.pc.ioc.view.listener;

import android.view.View;
import android.widget.RadioGroup;
import com.android.pc.ioc.app.Ioc;

/* loaded from: classes.dex */
public class OnRadioChecked extends OnListener implements RadioGroup.OnCheckedChangeListener {
    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        invoke(radioGroup, Integer.valueOf(i));
    }

    @Override // com.android.pc.ioc.view.listener.OnListener
    protected void listener(View view) {
        if (view instanceof RadioGroup) {
            ((RadioGroup) view).setOnCheckedChangeListener(this);
            return;
        }
        Ioc.getIoc().getLogger().e(view.getClass() + " 无法设置OnRadioChecked 请检查InjectMethod的参数\n");
    }
}
