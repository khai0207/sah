package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class u extends LinearLayout {
    private String a;
    private v b;

    private u(Context context, String str, String str2, Drawable drawable) {
        super(context);
        this.a = null;
        this.b = null;
        setOrientation(0);
        this.a = str2;
        v a = v.a(context, drawable);
        this.b = a;
        a.a(Html.fromHtml(String.format("<u>%s</u>", str)));
        this.b.a(com.unionpay.mobile.android.utils.g.a(-13601621, -15909519));
        addView(this.b);
    }

    public static final u a(Context context, JSONObject jSONObject, Drawable drawable) {
        if (jSONObject != null) {
            return new u(context, com.unionpay.mobile.android.utils.i.a(jSONObject, "label"), com.unionpay.mobile.android.utils.i.a(jSONObject, "href"), drawable);
        }
        return null;
    }

    public final String a() {
        return this.a;
    }

    public final void a(View.OnClickListener onClickListener) {
        v vVar = this.b;
        if (vVar != null) {
            vVar.setOnClickListener(onClickListener);
        }
    }
}
