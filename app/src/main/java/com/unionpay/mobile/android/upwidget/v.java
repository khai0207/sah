package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;

/* loaded from: classes.dex */
public final class v extends LinearLayout {
    private Context a;
    private TextView b;

    private v(Context context, Drawable drawable) {
        super(context);
        this.a = null;
        this.b = null;
        this.a = context;
        setOrientation(0);
        Context context2 = this.a;
        if (drawable != null) {
            ImageView imageView = new ImageView(context2);
            imageView.setBackgroundDrawable(drawable);
            int i = com.unionpay.mobile.android.global.b.o;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
            layoutParams.gravity = 16;
            addView(imageView, layoutParams);
        }
        this.b = new TextView(context2);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 16;
        if (drawable != null) {
            layoutParams2.leftMargin = com.unionpay.mobile.android.global.a.d;
        }
        addView(this.b, layoutParams2);
    }

    public static v a(Context context, Drawable drawable) {
        v vVar = new v(context, drawable);
        TextView textView = vVar.b;
        if (textView != null) {
            textView.setTextSize(16.0f);
        }
        vVar.a(com.unionpay.mobile.android.utils.g.a(-16758391, SupportMenu.CATEGORY_MASK));
        return vVar;
    }

    public final void a(ColorStateList colorStateList) {
        TextView textView = this.b;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public final void a(CharSequence charSequence) {
        TextView textView = this.b;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        TextView textView = this.b;
        if (textView != null) {
            textView.setOnClickListener(onClickListener);
        }
    }
}
