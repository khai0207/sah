package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.kqg.main.constant.KV;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class ba {
    private static int a = com.unionpay.mobile.android.global.a.r;
    private static int b = 40;
    private static List<Integer> i = new ArrayList(10);
    private Context c;
    private View.OnClickListener d;
    private PopupWindow e;
    private View f;
    private ScrollView g;
    private int h = -1;
    private PopupWindow.OnDismissListener j = new bb(this);

    /* loaded from: classes.dex */
    private class a extends BaseAdapter {
        private a() {
        }

        /* synthetic */ a(ba baVar, byte b) {
            this();
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return ba.i.size() + 2;
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            LinearLayout linearLayout = new LinearLayout(ba.this.c);
            Drawable a = com.unionpay.mobile.android.resource.c.a(ba.this.c).a(KV.EVENT_SHOW_REGIST);
            Drawable a2 = com.unionpay.mobile.android.resource.c.a(ba.this.c).a(KV.EVENT_QUICK_REGIST);
            linearLayout.setBackgroundDrawable(com.unionpay.mobile.android.utils.g.a(a, a2, a2, a));
            linearLayout.setMinimumHeight(com.unionpay.mobile.android.utils.f.a(ba.this.c, 55.0f));
            linearLayout.setClickable(true);
            linearLayout.setOnClickListener(ba.this.d);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            if (i == 9 || i == 11) {
                ImageView imageView = new ImageView(ba.this.c);
                int i2 = i != 9 ? 20 : 10;
                imageView.setImageDrawable(com.unionpay.mobile.android.resource.c.a(ba.this.c).a(i == 9 ? 1024 : 1025, -1, com.unionpay.mobile.android.utils.f.a(ba.this.c, 20.0f)));
                linearLayout.setId(i2);
                linearLayout.addView(imageView, layoutParams);
            } else {
                TextView textView = new TextView(ba.this.c);
                textView.setTextColor(-1);
                textView.getPaint().setFakeBoldText(true);
                textView.setTextSize(30.0f);
                textView.setGravity(17);
                if (i == 10) {
                    i = 9;
                }
                int intValue = ((Integer) ba.i.get(i)).intValue();
                linearLayout.setId(intValue);
                StringBuilder sb = new StringBuilder();
                sb.append(intValue);
                textView.setText(sb.toString());
                linearLayout.addView(textView, layoutParams);
            }
            return linearLayout;
        }
    }

    /* loaded from: classes.dex */
    private class b extends LinearLayout {
        public b(Context context) {
            super(context);
            setOrientation(1);
            setBackgroundColor(-11316397);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 17;
            LinearLayout linearLayout = new LinearLayout(context);
            int a = com.unionpay.mobile.android.utils.f.a(context, 5.0f);
            linearLayout.setPadding(0, a, 0, a);
            linearLayout.setGravity(17);
            linearLayout.setBackgroundColor(-13816531);
            linearLayout.setOrientation(0);
            Drawable a2 = com.unionpay.mobile.android.resource.c.a(context).a(1020, -1, com.unionpay.mobile.android.utils.f.a(context, 24.0f));
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(a2);
            linearLayout.addView(imageView);
            addView(linearLayout, layoutParams);
            Collections.shuffle(ba.i);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.gravity = 17;
            GridView gridView = new GridView(context);
            gridView.setNumColumns(3);
            gridView.setAdapter((ListAdapter) new a(ba.this, (byte) 0));
            gridView.setGravity(17);
            gridView.setStretchMode(2);
            gridView.setHorizontalScrollBarEnabled(false);
            gridView.setVerticalScrollBarEnabled(false);
            gridView.setEnabled(true);
            int a3 = com.unionpay.mobile.android.utils.f.a(ba.this.c, 1.0f);
            gridView.setHorizontalSpacing(a3);
            gridView.setVerticalSpacing(a3);
            int i = -a3;
            gridView.setPadding(i, a3, i, i);
            addView(gridView, layoutParams2);
        }
    }

    static {
        for (int i2 = 0; i2 < 10; i2++) {
            i.add(Integer.valueOf(i2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ba(Context context, View.OnClickListener onClickListener, View view) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.c = context;
        this.d = onClickListener;
        a = com.unionpay.mobile.android.utils.f.a(context, 55.0f);
        b = com.unionpay.mobile.android.utils.f.a(this.c, 40.0f);
        ViewParent viewParent = (ViewParent) view;
        while (true) {
            if (viewParent == null) {
                break;
            }
            if (viewParent instanceof ScrollView) {
                ScrollView scrollView = (ScrollView) viewParent;
                this.g = scrollView;
                com.unionpay.mobile.android.utils.j.a("UPWidgetKeyBoard", "mSV : " + this.g.toString());
                com.unionpay.mobile.android.utils.j.a("UPWidgetKeyBoard", "mSV H:" + this.g.getHeight());
                this.f = scrollView.getChildAt(0);
                break;
            }
            viewParent = viewParent.getParent();
        }
        RelativeLayout relativeLayout = new RelativeLayout(context);
        new RelativeLayout.LayoutParams(-1, -2).setMargins(0, 0, 0, 0);
        relativeLayout.setBackgroundColor(-1342177280);
        RelativeLayout relativeLayout2 = new RelativeLayout(context);
        relativeLayout2.setBackgroundColor(-13290188);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        relativeLayout.addView(relativeLayout2, layoutParams);
        relativeLayout2.addView(new b(this.c), layoutParams);
        this.e = new PopupWindow((View) relativeLayout, -1, -2, true);
        new RelativeLayout.LayoutParams(-1, -2);
        this.e.setBackgroundDrawable(new BitmapDrawable());
        this.e.setOutsideTouchable(false);
        this.e.setFocusable(false);
        this.e.setOnDismissListener(this.j);
    }

    private static int d() {
        int i2 = (a * 4) + b;
        com.unionpay.mobile.android.utils.j.c("UPWidgetKeyBoard", "kbH=" + i2);
        return i2;
    }

    public final void a() {
        PopupWindow popupWindow = this.e;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public final void a(View view) {
        PopupWindow popupWindow = this.e;
        if (popupWindow != null) {
            popupWindow.showAtLocation(view, 80, 0, 0);
            this.e.update();
            if (this.f != null) {
                view.setVisibility(0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.g.getLayoutParams();
                this.h = marginLayoutParams.height;
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                marginLayoutParams.height = ((com.unionpay.mobile.android.global.a.t - rect.top) - com.unionpay.mobile.android.global.a.k) - d();
                com.unionpay.mobile.android.utils.j.a("UPWidgetKeyBoard", "height = " + marginLayoutParams.height);
                marginLayoutParams.bottomMargin = d();
                this.g.setLayoutParams(marginLayoutParams);
            }
        }
    }

    public final boolean b() {
        return this.e.isShowing();
    }
}
