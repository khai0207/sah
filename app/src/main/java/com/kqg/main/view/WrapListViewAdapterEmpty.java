package com.kqg.main.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.kqg.main.utils.UiUtils;
import java.util.List;

/* loaded from: classes.dex */
public class WrapListViewAdapterEmpty extends BaseAdapter {
    private Activity act;
    private List<Number> list;
    private WrapListViewCallBack wrapListViewCallBack;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WrapListViewAdapterEmpty() {
        this.wrapListViewCallBack = null;
    }

    public WrapListViewAdapterEmpty(List<Number> list, Activity activity) {
        this.wrapListViewCallBack = null;
        this.list = list;
        this.act = activity;
    }

    public WrapListViewAdapterEmpty(List<Number> list, Activity activity, WrapListViewCallBack wrapListViewCallBack) {
        this.wrapListViewCallBack = null;
        this.list = list;
        this.act = activity;
        this.wrapListViewCallBack = wrapListViewCallBack;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.list.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = this.act.getLayoutInflater().inflate(UiUtils.getLayOut("list_user_layout_empty"), (ViewGroup) null);
        return inflate;
    }
}
