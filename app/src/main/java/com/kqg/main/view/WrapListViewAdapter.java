package com.kqg.main.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.kqg.main.model.User;
import com.kqg.main.model.UserManager;
import com.kqg.main.utils.UiUtils;
import java.util.List;

/* loaded from: classes.dex */
public class WrapListViewAdapter extends BaseAdapter {
    private Activity act;
    private List<User> users;
    private WrapListViewCallBack wrapListViewCallBack;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WrapListViewAdapter() {
        this.wrapListViewCallBack = null;
    }

    public WrapListViewAdapter(List<User> list, Activity activity) {
        this.wrapListViewCallBack = null;
        this.users = list;
        this.act = activity;
    }

    public WrapListViewAdapter(List<User> list, Activity activity, WrapListViewCallBack wrapListViewCallBack) {
        this.wrapListViewCallBack = null;
        this.users = list;
        this.act = activity;
        this.wrapListViewCallBack = wrapListViewCallBack;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<User> list = this.users;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.users.get(i).getUsername();
    }

    public String getUserId(int i) {
        return this.users.get(i).getUid();
    }

    public String getUserPassword(int i) {
        return this.users.get(i).getPassword();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = this.act.getLayoutInflater().inflate(UiUtils.getLayOut("list_user_layout"), (ViewGroup) null);
        ((TextView) inflate.findViewById(UiUtils.getId("dropview_edit"))).setText(this.users.get(i).getUsername());
        ImageView imageView = (ImageView) inflate.findViewById(UiUtils.getId("dropview_image"));
        imageView.setTag(Integer.valueOf(i));
        imageView.setVisibility(0);
        final List<User> list = this.users;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.kqg.main.view.WrapListViewAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Integer num = (Integer) view2.getTag();
                UserManager.getInstance().removeUser((User) list.get(num.intValue()));
                list.remove(num.intValue());
                WrapListViewAdapter.this.notifyDataSetChanged();
                if (WrapListViewAdapter.this.wrapListViewCallBack != null) {
                    WrapListViewAdapter.this.wrapListViewCallBack.onDeleteItem(list);
                }
            }
        });
        return inflate;
    }
}
