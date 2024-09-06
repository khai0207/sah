package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CViewMethods extends LinearLayout {
    private static final Integer a = -1;
    private static final Integer b = -2;
    private static final int c = com.unionpay.mobile.android.global.b.a;
    private Context d;
    private int e;
    private TextView f;
    private HashMap<Integer, String> g;
    private HashMap<Integer, Drawable> h;
    private String i;
    private a j;
    private Drawable k;

    /* loaded from: classes.dex */
    public interface a {
        void c(int i);
    }

    public CViewMethods(Context context) {
        this(context, null);
    }

    public CViewMethods(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CViewMethods(Context context, AttributeSet attributeSet, int i) {
        super(context);
        this.d = context;
        this.e = l.a.intValue();
        setOrientation(1);
    }

    private void a(LinearLayout linearLayout, int i) {
        String str;
        Drawable drawable;
        float f = com.unionpay.mobile.android.global.b.k;
        RelativeLayout relativeLayout = new RelativeLayout(this.d);
        relativeLayout.setClickable(true);
        Drawable drawable2 = this.k;
        if (drawable2 != null) {
            relativeLayout.setBackgroundDrawable(drawable2.getConstantState().newDrawable());
        }
        relativeLayout.setOnClickListener(new h(this, i));
        linearLayout.addView(relativeLayout, new LinearLayout.LayoutParams(a.intValue(), com.unionpay.mobile.android.global.b.n));
        ImageView imageView = new ImageView(this.d);
        imageView.setId(imageView.hashCode());
        HashMap<Integer, Drawable> hashMap = this.h;
        if (hashMap != null && (drawable = hashMap.get(Integer.valueOf(i))) != null) {
            imageView.setBackgroundDrawable(drawable);
        }
        int a2 = com.unionpay.mobile.android.utils.f.a(this.d, 15.0f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(11, -1);
        layoutParams.rightMargin = c;
        relativeLayout.addView(imageView, layoutParams);
        TextView textView = new TextView(this.d);
        textView.setClickable(false);
        textView.setTextSize(f);
        textView.setTextColor(-13421773);
        HashMap<Integer, String> hashMap2 = this.g;
        if (hashMap2 != null && (str = hashMap2.get(Integer.valueOf(i))) != null) {
            textView.setText(Html.fromHtml(str));
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a.intValue(), b.intValue());
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(9, -1);
        layoutParams2.addRule(0, imageView.getId());
        layoutParams2.leftMargin = c;
        relativeLayout.addView(textView, layoutParams2);
    }

    public final CViewMethods a(int i) {
        if (i > 0) {
            this.e = i;
        }
        return this;
    }

    public final CViewMethods a(Drawable drawable) {
        this.k = drawable;
        return this;
    }

    public final CViewMethods a(a aVar) {
        this.j = aVar;
        return this;
    }

    public final CViewMethods a(String str) {
        this.i = str;
        return this;
    }

    public final CViewMethods a(HashMap<Integer, String> hashMap) {
        this.g = hashMap;
        return this;
    }

    public final void a() {
        TextView textView;
        removeAllViews();
        if (this.e == l.a.intValue()) {
            setVisibility(8);
            return;
        }
        TextView textView2 = new TextView(this.d);
        this.f = textView2;
        textView2.setTextColor(-13421773);
        this.f.setTextSize(com.unionpay.mobile.android.global.b.k);
        String str = this.i;
        if (str != null && (textView = this.f) != null) {
            textView.setText(str);
        }
        this.f.setGravity(19);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a.intValue(), b.intValue());
        layoutParams.gravity = 19;
        layoutParams.topMargin = c;
        layoutParams.leftMargin = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        addView(this.f, layoutParams);
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setBackgroundColor(-3419943);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 1);
        layoutParams2.topMargin = c;
        addView(linearLayout, layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackgroundColor(-1);
        addView(linearLayout2, layoutParams3);
        if (l.b.intValue() == (l.b.intValue() & this.e)) {
            a(linearLayout2, l.b.intValue());
        }
        if (l.c.intValue() == (l.c.intValue() & this.e)) {
            a(linearLayout2, l.c.intValue());
        }
        if (l.e.intValue() == (l.e.intValue() & this.e)) {
            LinearLayout linearLayout3 = new LinearLayout(this.d);
            linearLayout3.setBackgroundColor(-3419943);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, 1);
            layoutParams4.leftMargin = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
            linearLayout2.addView(linearLayout3, layoutParams4);
            a(linearLayout2, l.e.intValue());
        }
        if (l.d.intValue() == (l.d.intValue() & this.e)) {
            LinearLayout linearLayout4 = new LinearLayout(this.d);
            linearLayout4.setBackgroundColor(-3419943);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, 1);
            layoutParams5.leftMargin = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
            linearLayout2.addView(linearLayout4, layoutParams5);
            a(linearLayout2, l.d.intValue());
        }
        if (l.f.intValue() == (l.f.intValue() & this.e)) {
            LinearLayout linearLayout5 = new LinearLayout(this.d);
            linearLayout5.setBackgroundColor(-3419943);
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, 1);
            layoutParams6.leftMargin = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
            linearLayout2.addView(linearLayout5, layoutParams6);
            a(linearLayout2, l.f.intValue());
        }
        LinearLayout linearLayout6 = new LinearLayout(this.d);
        linearLayout6.setBackgroundColor(-3419943);
        addView(linearLayout6, new LinearLayout.LayoutParams(-1, 1));
    }

    public final CViewMethods b(HashMap<Integer, Drawable> hashMap) {
        this.h = hashMap;
        return this;
    }
}
