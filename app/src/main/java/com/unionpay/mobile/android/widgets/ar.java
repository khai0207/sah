package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

/* loaded from: classes.dex */
public final class ar extends LinearLayout {
    private com.unionpay.mobile.android.resource.c a;
    private ImageView b;
    private ImageView c;

    public ar(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = com.unionpay.mobile.android.resource.c.a(context);
        setBackgroundColor(0);
        setOrientation(1);
        this.c = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.C);
        layoutParams.gravity = 80;
        addView(this.c, layoutParams);
        Drawable a = this.a.a(1001);
        ImageView imageView = this.b;
        if (imageView != null) {
            imageView.setBackgroundDrawable(a);
        }
    }
}
