package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kqg.main.constant.KV;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class bd extends b {
    private TextView r;
    private View.OnClickListener s;
    private a t;

    /* loaded from: classes.dex */
    public class a extends LinearLayout {
        private Context b;

        public a(Context context) {
            super(context);
            this.b = context;
            setOrientation(1);
        }

        private void a(JSONArray jSONArray) {
            if (jSONArray == null || jSONArray.length() <= 0) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            LinearLayout linearLayout = new LinearLayout(bd.this.d);
            linearLayout.setOrientation(1);
            linearLayout.setBackgroundColor(-1);
            addView(linearLayout, layoutParams);
            ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.b.n);
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "label");
                    String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, Constants.URL_ENCODING);
                    LinearLayout linearLayout2 = new LinearLayout(bd.this.d);
                    linearLayout2.setBackgroundColor(-3419943);
                    LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, 1);
                    if (i != 0) {
                        layoutParams3.leftMargin = com.unionpay.mobile.android.utils.f.a(bd.this.d, 12.0f);
                    }
                    linearLayout.addView(linearLayout2, layoutParams3);
                    Context context = this.b;
                    RelativeLayout relativeLayout = new RelativeLayout(this.b);
                    relativeLayout.setBackgroundDrawable(bd.this.c.a(2014));
                    relativeLayout.setOnClickListener(new bg(this, i, a, a2));
                    TextView textView = new TextView(context);
                    textView.setText(a);
                    textView.setTextSize(com.unionpay.mobile.android.global.b.k);
                    textView.setTextColor(-13421773);
                    RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams4.addRule(15, -1);
                    layoutParams4.addRule(9, -1);
                    layoutParams4.leftMargin = com.unionpay.mobile.android.utils.f.a(context, 12.0f);
                    relativeLayout.addView(textView, layoutParams4);
                    int a3 = com.unionpay.mobile.android.utils.f.a(context, 20.0f);
                    Drawable a4 = bd.this.c.a(1002);
                    ImageView imageView = new ImageView(context);
                    imageView.setBackgroundDrawable(a4);
                    RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(a3, a3);
                    layoutParams5.addRule(15, -1);
                    layoutParams5.addRule(11, -1);
                    layoutParams5.rightMargin = com.unionpay.mobile.android.utils.f.a(context, 12.0f);
                    relativeLayout.addView(imageView, layoutParams5);
                    linearLayout.addView(relativeLayout, layoutParams2);
                    if (i == jSONArray.length() - 1) {
                        LinearLayout linearLayout3 = new LinearLayout(bd.this.d);
                        linearLayout3.setBackgroundColor(-3419943);
                        linearLayout.addView(linearLayout3, new LinearLayout.LayoutParams(-1, 1));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        public final void a(JSONObject jSONObject) {
            removeAllViews();
            String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "label");
            if (bd.b(a)) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.leftMargin = com.unionpay.mobile.android.utils.f.a(bd.this.d, 12.0f);
                layoutParams.bottomMargin = com.unionpay.mobile.android.global.a.f;
                TextView textView = new TextView(this.b);
                textView.setText(a);
                textView.setTextSize(com.unionpay.mobile.android.global.b.k);
                textView.setTextColor(-25009);
                addView(textView, layoutParams);
            }
            a(com.unionpay.mobile.android.utils.i.d(jSONObject, "options"));
        }
    }

    public bd(Context context) {
        super(context);
        this.r = null;
        this.s = new be(this);
        e();
        this.q = "result";
        this.a.al = null;
        if (this.a.aQ) {
            this.e.c("bingopromotion", "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a.aL);
        String sb2 = sb.toString();
        com.unionpay.mobile.android.utils.j.c("functionEx", sb2);
        if (this.a.aL == com.unionpay.mobile.android.views.order.l.b.intValue() || this.a.aL == com.unionpay.mobile.android.views.order.l.c.intValue()) {
            PreferenceUtils.c(this.d, sb2);
        }
        this.a.D.f = Constant.CASH_LOAD_SUCCESS;
        k();
    }

    @Override // com.unionpay.mobile.android.nocard.views.a
    public final void a(JSONObject jSONObject) {
        JSONObject c = com.unionpay.mobile.android.utils.i.c(jSONObject, "luck_draw");
        if (c != null) {
            this.t.setVisibility(0);
            this.t.a(c);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        com.unionpay.mobile.android.widgets.ax axVar = new com.unionpay.mobile.android.widgets.ax(getContext(), this.a.v, this);
        axVar.a();
        layoutParams.addRule(13, -1);
        this.k.addView(axVar, layoutParams);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void b(int i) {
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void c() {
        g();
        this.m.invalidate();
        this.o.setBackgroundColor(-1052684);
        RelativeLayout relativeLayout = new RelativeLayout(this.d);
        relativeLayout.setBackgroundColor(-1052684);
        relativeLayout.setId(relativeLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(10, -1);
        layoutParams.topMargin = com.unionpay.mobile.android.global.a.d;
        this.m.addView(relativeLayout, layoutParams);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.d);
        relativeLayout2.setId(relativeLayout2.hashCode());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.d;
        relativeLayout.addView(relativeLayout2, layoutParams2);
        int i = com.unionpay.mobile.android.global.a.n;
        Drawable a2 = this.c.a(2008);
        TextView textView = new TextView(this.d);
        this.r = textView;
        textView.setId(textView.hashCode());
        this.r.setText(com.unionpay.mobile.android.languages.c.bD.E);
        this.r.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.r.setTextColor(p());
        this.r.setGravity(17);
        this.r.setOnClickListener(this.s);
        this.r.setBackgroundDrawable(a2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, i);
        int a3 = com.unionpay.mobile.android.utils.f.a(this.d, 12.0f);
        layoutParams3.rightMargin = a3;
        layoutParams3.leftMargin = a3;
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        relativeLayout2.addView(this.r, layoutParams3);
        a aVar = new a(this.d);
        this.t = aVar;
        aVar.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(3, relativeLayout2.getId());
        layoutParams4.topMargin = com.unionpay.mobile.android.utils.f.a(this.d, 20.0f);
        relativeLayout.addView(this.t, layoutParams4);
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    protected final void f() {
        SpannableString spannableString;
        String str;
        String str2;
        String[] split;
        if (this.a.C != null) {
            LinearLayout linearLayout = new LinearLayout(this.d);
            linearLayout.setOrientation(1);
            int a2 = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
            if (!TextUtils.isEmpty(this.a.w)) {
                TextView textView = new TextView(this.d);
                textView.setText(this.a.w);
                textView.setTextSize(24.0f);
                textView.setTextColor(-15365480);
                textView.setGravity(1);
                textView.setPadding(0, com.unionpay.mobile.android.global.a.d, 0, 0);
                textView.getPaint().setFakeBoldText(true);
                linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
                LinearLayout linearLayout2 = new LinearLayout(this.d);
                linearLayout2.setOrientation(0);
                linearLayout2.setBackgroundColor(-6958338);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
                int i = com.unionpay.mobile.android.global.a.d;
                layoutParams.bottomMargin = i;
                layoutParams.topMargin = i;
                linearLayout.addView(linearLayout2, layoutParams);
                this.l.addView(linearLayout);
            }
            JSONArray jSONArray = this.a.C;
            if (jSONArray != null) {
                int length = jSONArray.length() < 2 ? jSONArray.length() : 2;
                LinearLayout a3 = bh.a(this.d, jSONArray, 0, length);
                a3.setBackgroundColor(0);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams2.rightMargin = a2;
                layoutParams2.leftMargin = a2;
                linearLayout.addView(a3, layoutParams2);
                LinearLayout a4 = bh.a(this.d, jSONArray, length, jSONArray.length());
                a4.setBackgroundColor(0);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.rightMargin = a2;
                layoutParams3.leftMargin = a2;
                this.l.addView(a4, layoutParams3);
            }
            com.unionpay.mobile.android.widgets.ar arVar = new com.unionpay.mobile.android.widgets.ar(this.d);
            arVar.setId(arVar.hashCode());
            this.l.setOnClickListener(new bf(this));
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams4.bottomMargin = 0;
            this.l.addView(arVar, layoutParams4);
            return;
        }
        int a5 = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        LinearLayout linearLayout3 = new LinearLayout(this.d);
        linearLayout3.setPadding(a5, a5, a5, a5);
        linearLayout3.setOrientation(1);
        linearLayout3.setBackgroundColor(-1);
        this.l.addView(linearLayout3);
        LinearLayout linearLayout4 = new LinearLayout(this.d);
        linearLayout4.setOrientation(0);
        linearLayout3.addView(linearLayout4);
        int a6 = com.unionpay.mobile.android.utils.f.a(this.d, 25.0f);
        ImageView imageView = new ImageView(this.d);
        imageView.setBackgroundDrawable(this.c.a(KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_BIND));
        linearLayout4.addView(imageView, new LinearLayout.LayoutParams(a6, a6));
        LinearLayout linearLayout5 = new LinearLayout(this.d);
        linearLayout5.setOrientation(1);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.leftMargin = a5;
        linearLayout4.addView(linearLayout5, layoutParams5);
        String str3 = this.a.aM;
        if (com.unionpay.mobile.android.utils.h.b(str3)) {
            spannableString = null;
        } else {
            int indexOf = str3.indexOf(60);
            String substring = -1 != indexOf ? str3.substring(0, indexOf) : null;
            int indexOf2 = str3.indexOf(62) + 1;
            String substring2 = -1 != indexOf2 ? str3.substring(indexOf2) : null;
            int i2 = indexOf + 1;
            int i3 = indexOf2 - 1;
            if (-1 != indexOf && i2 < i3 && indexOf2 != -1) {
                String substring3 = str3.substring(i2, i3);
                if (!com.unionpay.mobile.android.utils.h.b(substring3) && (split = substring3.split("#")) != null && split.length == 2) {
                    str2 = split[0];
                    str = split[1];
                    spannableString = com.unionpay.mobile.android.utils.h.a(substring, str2, str, substring2);
                }
            }
            str = null;
            str2 = null;
            spannableString = com.unionpay.mobile.android.utils.h.a(substring, str2, str, substring2);
        }
        if (spannableString != null) {
            TextView textView2 = new TextView(this.d);
            textView2.setTextSize(20.0f);
            textView2.setText(spannableString);
            linearLayout5.addView(textView2);
        }
        if (b(this.a.aN)) {
            TextView textView3 = new TextView(this.d);
            textView3.setTextSize(com.unionpay.mobile.android.global.b.l);
            textView3.setText(this.a.aN);
            textView3.setTextColor(-10066330);
            linearLayout5.addView(textView3);
        }
        if (b(this.a.aO)) {
            TextView textView4 = new TextView(this.d);
            textView4.setTextSize(com.unionpay.mobile.android.global.b.l);
            textView4.setTextColor(-10066330);
            textView4.setText(this.a.aO);
            linearLayout5.addView(textView4);
        }
        int a7 = com.unionpay.mobile.android.utils.f.a(this.d, 5.0f);
        LinearLayout linearLayout6 = new LinearLayout(this.d);
        linearLayout6.setOrientation(1);
        linearLayout6.setBackgroundColor(-1052684);
        linearLayout6.setPadding(a7, a7, a7, a7);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams6.topMargin = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        linearLayout3.addView(linearLayout6, layoutParams6);
        linearLayout6.setVisibility(8);
        if ("0".equals(this.a.aR) && b(this.a.aS)) {
            TextView textView5 = new TextView(this.d);
            textView5.setTextSize(com.unionpay.mobile.android.global.b.l);
            textView5.setText(this.a.aS);
            linearLayout6.addView(textView5);
            linearLayout6.setVisibility(0);
        }
        Drawable a8 = this.c.a(1026);
        LinearLayout linearLayout7 = new LinearLayout(this.d);
        if (a8 != null) {
            linearLayout7.setBackgroundDrawable(a8);
        }
        this.l.addView(linearLayout7, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.utils.f.a(this.d, 2.0f)));
    }

    @Override // com.unionpay.mobile.android.nocard.views.b
    public final void l() {
        t();
    }
}
