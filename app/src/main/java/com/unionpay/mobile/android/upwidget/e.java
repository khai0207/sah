package com.unionpay.mobile.android.upwidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class e extends LinearLayout {
    private Context a;
    private c b;
    private ArrayList<AdapterView.OnItemClickListener> c;
    private ArrayList<View.OnClickListener> d;
    private AdapterView.OnItemClickListener e;
    private View.OnClickListener f;

    public e(Context context, c cVar) {
        super(context);
        this.c = new ArrayList<>();
        this.d = new ArrayList<>();
        this.e = new f(this);
        this.f = new g(this);
        this.a = context;
        this.b = cVar;
        RelativeLayout relativeLayout = new RelativeLayout(this.a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setId(linearLayout.hashCode());
        relativeLayout.addView(linearLayout, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        LinearLayout linearLayout2 = new LinearLayout(this.a);
        layoutParams2.addRule(10, -1);
        layoutParams2.addRule(2, linearLayout.getId());
        relativeLayout.addView(linearLayout2, layoutParams2);
        linearLayout2.setOnClickListener(this.f);
        int a = com.unionpay.mobile.android.utils.f.a(this.a, 1.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        int i = com.unionpay.mobile.android.global.b.a;
        layoutParams3.bottomMargin = i;
        layoutParams3.topMargin = i;
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, a);
        LinearLayout linearLayout3 = new LinearLayout(this.a);
        linearLayout3.setBackgroundColor(-3355444);
        linearLayout.addView(linearLayout3, layoutParams4);
        new LinearLayout.LayoutParams(-1, -2);
        ListView listView = new ListView(this.a);
        listView.setDivider(null);
        a(listView, this.b);
        listView.setAdapter(this.b);
        listView.setCacheColorHint(-1);
        listView.setOnItemClickListener(this.e);
        new LinearLayout.LayoutParams(-1, -2);
        linearLayout.addView(listView);
        addView(relativeLayout);
    }

    private void a(ListView listView, ListAdapter listAdapter) {
        ViewGroup.LayoutParams layoutParams;
        if (listView == null || listAdapter == null || (layoutParams = listView.getLayoutParams()) == null) {
            return;
        }
        Rect rect = new Rect();
        ((Activity) this.a).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int height = rect.height();
        int i = 0;
        for (int i2 = 0; i2 < listAdapter.getCount(); i2++) {
            View view = listAdapter.getView(i2, null, listView);
            view.measure(0, 0);
            i += view.getMeasuredHeight();
            if (i > height) {
                break;
            }
        }
        layoutParams.height = Math.min(i, height);
        listView.setLayoutParams(layoutParams);
    }

    public final void a(View.OnClickListener onClickListener) {
        this.d.add(onClickListener);
    }

    public final void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.c.add(onItemClickListener);
    }
}
