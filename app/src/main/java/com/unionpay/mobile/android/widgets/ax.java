package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kqg.main.constant.KV;

/* loaded from: classes.dex */
public final class ax extends RelativeLayout {
    private Context a;
    private TextView b;
    private ImageView c;
    private TextView d;
    private Drawable e;
    private ImageView f;
    private LinearLayout g;
    private int h;
    private int i;
    private int j;
    private a k;

    /* loaded from: classes.dex */
    public interface a {
        void m();
    }

    public ax(Context context, String str, Drawable drawable, int i, a aVar) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.a = context;
        this.k = aVar;
        this.j = com.unionpay.mobile.android.utils.f.a(context, 10.0f);
        this.e = drawable;
        this.h = i;
        b(str);
    }

    public ax(Context context, String str, a aVar) {
        this(context, str, aVar, (byte) 0);
    }

    private ax(Context context, String str, a aVar, byte b) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.a = context;
        this.k = aVar;
        this.j = com.unionpay.mobile.android.utils.f.a(context, 10.0f);
        b(str);
    }

    private void b(String str) {
        this.i = com.unionpay.mobile.android.global.a.k;
        setLayoutParams(new RelativeLayout.LayoutParams(-1, this.i));
        setBackgroundColor(com.unionpay.mobile.android.global.a.M);
        com.unionpay.mobile.android.resource.c a2 = com.unionpay.mobile.android.resource.c.a(this.a);
        LinearLayout linearLayout = new LinearLayout(this.a);
        this.g = linearLayout;
        linearLayout.setOnClickListener(new ay(this));
        LinearLayout linearLayout2 = this.g;
        int i = this.j;
        linearLayout2.setPadding(i, i, i, i);
        this.g.setGravity(16);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        addView(this.g, layoutParams);
        int a3 = com.unionpay.mobile.android.utils.f.a(this.a, 20.0f);
        int a4 = com.unionpay.mobile.android.utils.f.a(this.a, 11.0f);
        int i2 = this.h;
        if (i2 != 0) {
            a4 = i2;
        }
        ImageView imageView = new ImageView(this.a);
        Drawable drawable = this.e;
        if (drawable == null) {
            drawable = a2.a(1029);
        }
        imageView.setBackgroundDrawable(drawable);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a4, a3);
        layoutParams2.addRule(15, -1);
        this.g.addView(imageView, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(com.unionpay.mobile.android.global.a.l, this.i);
        layoutParams3.addRule(13, -1);
        TextView textView = new TextView(this.a);
        this.d = textView;
        textView.setTextSize(20.0f);
        this.d.setTextColor(-1);
        this.d.setText(str);
        this.d.setGravity(17);
        this.d.setSingleLine(true);
        this.d.setEllipsize(TextUtils.TruncateAt.END);
        addView(this.d, layoutParams3);
        if (!TextUtils.isEmpty(null)) {
            int i3 = com.unionpay.mobile.android.global.a.e;
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, this.i);
            layoutParams4.addRule(11, -1);
            layoutParams4.addRule(15, -1);
            layoutParams4.rightMargin = i3;
            TextView textView2 = new TextView(this.a);
            this.b = textView2;
            textView2.setTextSize(16.0f);
            this.b.setTextColor(-1);
            this.b.setText((CharSequence) null);
            this.b.setGravity(16);
            TextView textView3 = this.b;
            textView3.setId(textView3.hashCode());
            addView(this.b, layoutParams4);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(com.unionpay.mobile.android.global.a.H, com.unionpay.mobile.android.global.a.m);
            layoutParams5.addRule(0, this.b.getId());
            layoutParams5.addRule(15, -1);
            layoutParams5.rightMargin = i3;
            addView(new o(this.a, com.unionpay.mobile.android.global.a.N, 1), layoutParams5);
        }
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(com.unionpay.mobile.android.global.a.E, com.unionpay.mobile.android.global.a.D);
        layoutParams6.addRule(11, -1);
        layoutParams6.addRule(15, -1);
        layoutParams6.rightMargin = this.j;
        Drawable a5 = a2.a(KV.EVENT_BIND_EMAIL_GET_CODE1);
        ImageView imageView2 = new ImageView(this.a);
        this.f = imageView2;
        imageView2.setBackgroundDrawable(a5);
        addView(this.f, layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(com.unionpay.mobile.android.global.a.D, com.unionpay.mobile.android.global.a.D);
        layoutParams7.addRule(11, -1);
        layoutParams7.addRule(15, -1);
        layoutParams7.rightMargin = this.j;
        ImageView imageView3 = new ImageView(this.a);
        this.c = imageView3;
        addView(imageView3, layoutParams7);
    }

    public final void a() {
        LinearLayout linearLayout = this.g;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    public final void a(int i) {
        ImageView imageView;
        ImageView imageView2 = this.c;
        if (imageView2 != null) {
            int i2 = 8;
            if (i == 0) {
                this.f.setVisibility(8);
                imageView = this.c;
            } else {
                imageView2.setVisibility(8);
                imageView = this.f;
                i2 = 0;
            }
            imageView.setVisibility(i2);
            this.c.setVisibility(i);
        }
    }

    public final void a(String str) {
        TextView textView = this.d;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
