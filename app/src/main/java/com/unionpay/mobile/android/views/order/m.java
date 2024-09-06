package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.unionpay.mobile.android.nocard.views.bh;
import com.unionpay.mobile.android.widgets.ac;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class m extends RelativeLayout {
    private Context a;
    private ImageView b;
    private LinearLayout c;
    private LinearLayout d;
    private Drawable e;
    private Drawable f;

    public m(Context context) {
        super(context);
        this.a = context;
        int a = com.unionpay.mobile.android.utils.f.a(context, 10.0f);
        setPadding(a, a, a, a);
        setBackgroundColor(-1);
        setOnClickListener(new n(this));
        int a2 = com.unionpay.mobile.android.utils.f.a(context, 15.0f);
        ImageView imageView = new ImageView(context);
        this.b = imageView;
        imageView.setId(imageView.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(12, -1);
        addView(this.b, layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        this.c = linearLayout;
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.rightMargin = a;
        layoutParams2.addRule(9, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(0, this.b.getId());
        addView(this.c, layoutParams2);
    }

    static /* synthetic */ void a(m mVar) {
        int i = mVar.d.getVisibility() == 8 ? 0 : 8;
        mVar.d.setVisibility(i);
        mVar.b.setBackgroundDrawable(i == 0 ? mVar.e : mVar.f);
    }

    public final void a(Drawable drawable, Drawable drawable2) {
        this.e = drawable;
        this.f = drawable2;
    }

    public final void a(boolean z, JSONArray jSONArray, JSONObject jSONObject) {
        this.c.removeAllViews();
        Drawable drawable = this.f;
        if (drawable != null) {
            this.b.setBackgroundDrawable(drawable);
        }
        int i = (jSONArray == null || jSONArray.length() == 0) ? 0 : 1;
        if (!z && jSONArray != null) {
            i = 2;
            if (jSONArray.length() <= 2) {
                i = jSONArray.length();
            }
        }
        if (jSONArray == null || i == 0) {
            com.unionpay.mobile.android.utils.j.c("uppay", "init order detail = null!!!");
            return;
        }
        this.c.addView(bh.a(this.a, jSONArray, 0, i), new LinearLayout.LayoutParams(-1, -2));
        this.d = bh.a(this.a, jSONArray, i, jSONArray.length());
        if (jSONObject != null) {
            ac acVar = new ac(this.a, jSONObject, "");
            acVar.g();
            acVar.a(com.unionpay.mobile.android.global.b.m);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = com.unionpay.mobile.android.utils.f.a(this.a, 8.0f);
            this.d.addView(acVar, layoutParams);
        }
        this.d.setVisibility(8);
        this.c.addView(this.d, new LinearLayout.LayoutParams(-1, -2));
    }
}
