package com.android.pc.ioc.view.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.core.kernel.KernelDyna;

/* loaded from: classes.dex */
public class OnItemLongClick extends OnListener implements AdapterView.OnItemLongClickListener {
    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return ((Boolean) KernelDyna.to(invoke(adapterView, view, Integer.valueOf(i), Long.valueOf(j)), Boolean.TYPE)).booleanValue();
    }

    @Override // com.android.pc.ioc.view.listener.OnListener
    protected void listener(View view) {
        if (view instanceof ListView) {
            ((ListView) view).setOnItemLongClickListener(this);
            return;
        }
        Ioc.getIoc().getLogger().e(view.getClass() + " 无法设置onItemLongClick 请检查InjectMethod的参数\n");
    }
}
