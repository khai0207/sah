package com.android.pc.ioc.view.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.pc.ioc.app.Ioc;

/* loaded from: classes.dex */
public class OnItemClick extends OnListener implements AdapterView.OnItemClickListener {
    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        invoke(adapterView, view, Integer.valueOf(i), Long.valueOf(j));
    }

    @Override // com.android.pc.ioc.view.listener.OnListener
    protected void listener(View view) {
        if (view instanceof ListView) {
            ((ListView) view).setOnItemClickListener(this);
            return;
        }
        Ioc.getIoc().getLogger().e(view.getClass() + " 无法设置OnItemClick 请检查InjectMethod的参数\n");
    }
}
