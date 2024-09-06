package com.kqg.main.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.kqg.main.model.PaySelectType;
import com.kqg.main.utils.UiUtils;
import java.util.List;

/* loaded from: classes.dex */
public class PaySelectAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<PaySelectType> types;

    /* loaded from: classes.dex */
    public static class ViewHolder {
        public TextView pay_select_bg;
        public TextView pay_select_des;
        public ImageView pay_select_logo;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public PaySelectAdapter(Context context, List<PaySelectType> list) {
        this.types = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.types.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = this.inflater.inflate(UiUtils.getLayOut("kqg_pay_select_item"), (ViewGroup) null);
            viewHolder.pay_select_des = (TextView) view2.findViewById(UiUtils.getId("pay_select_des"));
            viewHolder.pay_select_logo = (ImageView) view2.findViewById(UiUtils.getId("pay_select_logo"));
            viewHolder.pay_select_bg = (TextView) view2.findViewById(UiUtils.getId("pay_select_bg"));
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        PaySelectType paySelectType = this.types.get(i);
        viewHolder.pay_select_des.setText(paySelectType.getPaySelectDes());
        viewHolder.pay_select_logo.setImageResource(paySelectType.getPaySelectLogo());
        if (paySelectType.isSelected()) {
            viewHolder.pay_select_bg.setBackgroundResource(UiUtils.getDrawable("sdk_ui_select_has"));
        } else {
            viewHolder.pay_select_bg.setBackgroundResource(UiUtils.getDrawable("sdk_ui_select_no"));
        }
        return view2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean isEmpty() {
        List<PaySelectType> list = this.types;
        return list == null || list.size() == 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.types.get(i);
    }
}
