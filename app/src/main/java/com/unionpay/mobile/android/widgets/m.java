package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kqg.main.constant.KV;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class m {
    private static final float k = com.unionpay.mobile.android.global.b.k;
    private static final float l = com.unionpay.mobile.android.global.b.k;
    private static final float m = com.unionpay.mobile.android.global.b.j;
    private Context a;
    private TextView b;
    private WeakReference<View.OnClickListener> c;
    private TextView d;
    private WeakReference<View.OnClickListener> e;
    private int f;
    private com.unionpay.mobile.android.resource.c g;
    private Dialog h;
    private Drawable i;
    private WeakReference<DialogInterface.OnDismissListener> j;

    public m(Context context) {
        this(context, (byte) 0);
    }

    private m(Context context, byte b) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.a = context;
        this.j = new WeakReference<>(null);
        this.g = com.unionpay.mobile.android.resource.c.a(context);
        int i = com.unionpay.mobile.android.global.a.I - (com.unionpay.mobile.android.global.a.b * 4);
        this.f = i;
        this.i = this.g.a(1028, i / 2, -1);
    }

    private RelativeLayout a(Context context) {
        c();
        this.h = new n(this, context);
        WeakReference<DialogInterface.OnDismissListener> weakReference = this.j;
        if (weakReference != null && weakReference.get() != null) {
            this.h.setOnDismissListener(this.j.get());
        }
        this.h.setCanceledOnTouchOutside(false);
        this.h.setOwnerActivity((Activity) context);
        this.h.requestWindowFeature(1);
        this.h.getWindow().setBackgroundDrawable(this.g.a(KV.EVENT_FIND_PSD_GET_CODE));
        RelativeLayout relativeLayout = new RelativeLayout(this.a);
        this.h.getWindow().setBackgroundDrawable(this.g.a(KV.EVENT_FIND_PSD_GET_CODE));
        this.h.setContentView(relativeLayout, new RelativeLayout.LayoutParams(this.f, -2));
        RelativeLayout relativeLayout2 = new RelativeLayout(this.a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        relativeLayout.addView(relativeLayout2, layoutParams);
        return relativeLayout;
    }

    private boolean d() {
        return ((Activity) this.a).isFinishing();
    }

    public final void a(View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.c = new WeakReference<>(onClickListener);
        this.e = new WeakReference<>(onClickListener2);
    }

    public final void a(String str) {
        int a = com.unionpay.mobile.android.utils.f.a(this.a, 12.0f);
        com.unionpay.mobile.android.utils.f.a(this.a, 20.0f);
        RelativeLayout a2 = a(this.a);
        a2.setBackgroundColor(com.unionpay.mobile.android.global.a.M);
        Dialog dialog = this.h;
        if (dialog != null) {
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.alpha = 0.7f;
            this.h.getWindow().setAttributes(attributes);
        }
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        int i = this.f - (com.unionpay.mobile.android.global.a.j << 1);
        ImageView imageView = new ImageView(this.a);
        imageView.setImageDrawable(this.i);
        linearLayout.addView(imageView, new LinearLayout.LayoutParams(i, -2));
        TextView textView = new TextView(this.a);
        textView.setTextSize(l);
        textView.setTextColor(-1);
        textView.setText(str);
        textView.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = a;
        linearLayout.addView(textView, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(17);
        int i2 = com.unionpay.mobile.android.global.a.p;
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i2, i2);
        layoutParams2.topMargin = a;
        linearLayout.addView(new ProgressBar(this.a), layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(10, -1);
        layoutParams3.addRule(14, -1);
        int a3 = com.unionpay.mobile.android.utils.f.a(this.a, 20.0f);
        a2.setPadding(a3, a3, a3, a3);
        a2.addView(linearLayout, layoutParams3);
        Dialog dialog2 = this.h;
        if (dialog2 == null || dialog2.isShowing() || d()) {
            return;
        }
        this.h.show();
    }

    public final void a(String str, String str2, String str3) {
        RelativeLayout a = a(this.a);
        int i = com.unionpay.mobile.android.global.a.b;
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        a.addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        if (str != null && str.length() != 0) {
            TextView textView = new TextView(this.a);
            textView.getPaint().setFakeBoldText(true);
            textView.setTextSize(k);
            textView.setTextColor(-13421773);
            textView.setText(str);
            textView.setGravity(17);
            textView.setPadding(i, i << 1, i, i);
            linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        }
        TextView textView2 = new TextView(this.a);
        textView2.setTextSize(l);
        textView2.setTextColor(-13421773);
        textView2.setText(str2);
        textView2.setPadding(i, i, i, 0);
        textView2.setGravity(17);
        linearLayout.addView(textView2, new LinearLayout.LayoutParams(-1, -2));
        FrameLayout frameLayout = new FrameLayout(this.a);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = com.unionpay.mobile.android.global.a.b << 1;
        linearLayout.addView(frameLayout, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setOrientation(1);
        frameLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout3 = new LinearLayout(this.a);
        linearLayout3.setOrientation(0);
        linearLayout3.setBackgroundColor(-7829368);
        new LinearLayout.LayoutParams(-1, -2);
        linearLayout2.addView(linearLayout3);
        LinearLayout linearLayout4 = new LinearLayout(this.a);
        linearLayout2.addView(linearLayout4, new LinearLayout.LayoutParams(-1, -2));
        linearLayout4.setOrientation(0);
        linearLayout4.setGravity(17);
        TextView textView3 = new TextView(this.a);
        this.b = textView3;
        textView3.setPadding(5, 5, 5, 5);
        this.b.getPaint().setFakeBoldText(true);
        this.b.setText(str3);
        this.b.setTextSize(m);
        this.b.setTextColor(com.unionpay.mobile.android.utils.g.a(-15364869, -5846275));
        this.b.setGravity(17);
        int i2 = com.unionpay.mobile.android.global.a.o;
        WeakReference<View.OnClickListener> weakReference = this.c;
        if (weakReference != null && weakReference.get() != null) {
            this.b.setOnClickListener(this.c.get());
        }
        linearLayout4.addView(this.b, new LinearLayout.LayoutParams(-1, i2));
        frameLayout.addView(new o(this.a), new FrameLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.H));
        Dialog dialog = this.h;
        if (dialog == null || dialog.isShowing() || d()) {
            return;
        }
        this.h.show();
    }

    public final void a(String str, String str2, String str3, String str4) {
        a(str, str2, str3, str4, true);
    }

    public final void a(String str, String str2, String str3, String str4, boolean z) {
        RelativeLayout a = a(this.a);
        int i = com.unionpay.mobile.android.global.a.b;
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        a.addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        if (str != null && str.length() != 0) {
            TextView textView = new TextView(this.a);
            textView.getPaint().setFakeBoldText(true);
            textView.setTextSize(k);
            textView.setTextColor(-13421773);
            textView.setText(str);
            textView.setGravity(17);
            textView.setPadding(i, i << 1, i, i);
            linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        }
        TextView textView2 = new TextView(this.a);
        textView2.setTextSize(l);
        textView2.setTextColor(-13421773);
        textView2.setText(str2);
        textView2.setPadding(i, i, i, 0);
        textView2.setGravity(17);
        linearLayout.addView(textView2, new LinearLayout.LayoutParams(-1, -2));
        int a2 = com.unionpay.mobile.android.utils.f.a(this.a, 1.0f);
        LinearLayout linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setOrientation(0);
        linearLayout2.setBackgroundColor(-7829368);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, a2);
        layoutParams.topMargin = com.unionpay.mobile.android.global.a.b << 1;
        linearLayout.addView(linearLayout2, layoutParams);
        LinearLayout linearLayout3 = new LinearLayout(this.a);
        linearLayout3.setBackgroundColor(-1);
        linearLayout.addView(linearLayout3, new LinearLayout.LayoutParams(-1, -2));
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(17);
        int i2 = (this.f - com.unionpay.mobile.android.global.a.H) >> 1;
        TextView textView3 = new TextView(this.a);
        this.b = textView3;
        if (!z) {
            textView3.getPaint().setFakeBoldText(true);
        }
        this.b.setText(str3);
        this.b.setTextSize(m);
        this.b.setTextColor(com.unionpay.mobile.android.utils.g.a(-15364869, -5846275));
        this.b.setGravity(17);
        int i3 = com.unionpay.mobile.android.global.a.o;
        WeakReference<View.OnClickListener> weakReference = this.c;
        if (weakReference != null && weakReference.get() != null) {
            this.b.setOnClickListener(this.c.get());
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i2, i3);
        layoutParams2.leftMargin = 5;
        layoutParams2.bottomMargin = 5;
        linearLayout3.addView(this.b, layoutParams2);
        LinearLayout linearLayout4 = new LinearLayout(this.a);
        linearLayout4.setOrientation(1);
        linearLayout4.setBackgroundColor(-7829368);
        linearLayout3.addView(linearLayout4, new LinearLayout.LayoutParams(a2, -1));
        TextView textView4 = new TextView(this.a);
        this.d = textView4;
        if (z) {
            textView4.getPaint().setFakeBoldText(true);
        }
        this.d.setText(str4);
        this.d.setTextSize(m);
        this.d.setTextColor(com.unionpay.mobile.android.utils.g.a(-15364869, -5846275));
        this.d.setGravity(17);
        WeakReference<View.OnClickListener> weakReference2 = this.e;
        if (weakReference2 != null && weakReference2.get() != null) {
            this.d.setOnClickListener(this.e.get());
        }
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(i2, i3);
        layoutParams3.leftMargin = 5;
        layoutParams3.bottomMargin = 5;
        linearLayout3.addView(this.d, layoutParams3);
        Dialog dialog = this.h;
        if (dialog == null || dialog.isShowing() || d()) {
            return;
        }
        this.h.show();
    }

    public final boolean a() {
        Dialog dialog = this.h;
        return dialog != null && dialog.isShowing();
    }

    public final void b() {
        Dialog dialog = this.h;
        if (dialog != null) {
            dialog.hide();
            this.h.show();
        }
    }

    public final void c() {
        Dialog dialog = this.h;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.h.dismiss();
        this.h = null;
    }
}
