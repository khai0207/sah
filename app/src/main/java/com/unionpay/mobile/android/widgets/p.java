package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.unionpay.mobile.android.upwidget.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class p extends y {
    private final View.OnClickListener a;
    private final AdapterView.OnItemClickListener b;
    private JSONArray n;
    private List<Map<String, Object>> o;
    private PopupWindow p;
    private com.unionpay.mobile.android.upwidget.c q;
    private com.unionpay.mobile.android.upwidget.e r;
    private int s;
    private TextView t;

    /* renamed from: u, reason: collision with root package name */
    private com.unionpay.mobile.android.upwidget.o f62u;
    private TextView v;
    private String w;
    private RelativeLayout x;
    private boolean y;

    public p(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
        this.a = new q(this);
        this.b = new r(this);
        this.y = false;
        this.s = 1;
        this.n = com.unionpay.mobile.android.utils.i.d(this.m, "options");
        String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "label");
        this.w = a;
        if (a(a)) {
            this.w = com.unionpay.mobile.android.languages.c.bD.bg;
        }
        this.o = b(this.n);
        this.q = new com.unionpay.mobile.android.upwidget.c(this.c, this.o, this.w, "", "", this.s, 0);
        com.unionpay.mobile.android.upwidget.e eVar = new com.unionpay.mobile.android.upwidget.e(this.c, this.q);
        this.r = eVar;
        eVar.a(this.b);
        this.r.a(this.a);
        a(this.l);
    }

    private String a(int i, String str) {
        Object b = com.unionpay.mobile.android.utils.i.b(this.n, i);
        return b != null ? com.unionpay.mobile.android.utils.i.a((JSONObject) b, str) : "";
    }

    private JSONObject a(String str, String str2, String str3) {
        JSONObject c;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str);
            jSONObject.put("label", str2);
            jSONObject.put("checked", str3);
            jSONObject.put("ckb_style", "small");
            jSONObject.put("required", "0");
            if ("instalment".equals(str) && (c = com.unionpay.mobile.android.utils.i.c(this.m, Constants.URL_ENCODING)) != null) {
                jSONObject.put("href_label", com.unionpay.mobile.android.utils.i.a(c, "label"));
                jSONObject.put("href_url", com.unionpay.mobile.android.utils.i.a(c, "href"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.s = i;
        int c = i - this.q.c();
        this.q.a(this.s);
        TextView textView = this.t;
        if (textView != null) {
            textView.setText(a(c, "label"));
        }
        String a = a(c, "rel_label");
        String a2 = a(c, "rel_value");
        String a3 = a(c, "rel_value_style");
        if (a(a) && a(a2)) {
            return;
        }
        if (com.unionpay.mobile.android.data.a.a(a3)) {
            a3 = Integer.toString(Color.parseColor(a3), 16);
        }
        TextView textView2 = this.v;
        int parseColor = Color.parseColor("#ff" + a3);
        SpannableString spannableString = new SpannableString(a + a2);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(parseColor);
        int length = a.length();
        spannableString.setSpan(foregroundColorSpan, length, (TextUtils.isEmpty(a2) ? 0 : a2.length()) + length, 33);
        textView2.setText(spannableString);
        com.unionpay.mobile.android.upwidget.o oVar = this.f62u;
        this.v.setVisibility(oVar != null ? oVar.b() : true ? 0 : 8);
    }

    private void a(RelativeLayout relativeLayout) {
        Drawable a = com.unionpay.mobile.android.resource.c.a(this.c).a(2014);
        LinearLayout linearLayout = new LinearLayout(this.c);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-3419943);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        String a2 = com.unionpay.mobile.android.utils.i.a(this.m, "type");
        if ("instalment".equals(a2)) {
            layoutParams.leftMargin = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        }
        relativeLayout.addView(linearLayout, layoutParams);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.c);
        this.x = relativeLayout2;
        relativeLayout2.setId(relativeLayout2.hashCode());
        this.x.setBackgroundDrawable(a);
        this.x.setOnClickListener(new s(this));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(3, linearLayout.getId());
        relativeLayout.addView(this.x, layoutParams2);
        ImageView imageView = new ImageView(this.c);
        imageView.setId(imageView.hashCode());
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.c).a(1002));
        int a3 = com.unionpay.mobile.android.utils.f.a(this.c, 15.0f);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(a3, a3);
        layoutParams3.addRule(11, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.rightMargin = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        this.x.addView(imageView, layoutParams3);
        TextView textView = new TextView(this.c);
        this.t = textView;
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.t.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.t.setSingleLine(true);
        this.t.setTextColor(-10066330);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(15, -1);
        layoutParams4.addRule(9, -1);
        layoutParams4.addRule(0, imageView.getId());
        layoutParams4.leftMargin = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        layoutParams4.rightMargin = layoutParams4.leftMargin;
        this.x.addView(this.t, layoutParams4);
        if (!"instalment".equals(a2)) {
            LinearLayout linearLayout2 = new LinearLayout(this.c);
            linearLayout2.setBackgroundColor(-3419943);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, 1);
            layoutParams5.bottomMargin = com.unionpay.mobile.android.global.a.f;
            layoutParams5.addRule(3, this.x.getId());
            relativeLayout.addView(linearLayout2, layoutParams5);
        }
        b(g());
        a(1);
    }

    static /* synthetic */ void a(p pVar, View view) {
        if (pVar.p == null) {
            pVar.p = new PopupWindow((View) pVar.r, -1, -1, true);
            pVar.p.setBackgroundDrawable(new ColorDrawable(-1342177280));
            pVar.p.update();
        }
        pVar.p.showAtLocation(view, 80, 0, 0);
    }

    private List<Map<String, Object>> b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            HashMap hashMap = new HashMap();
            hashMap.put("text1", a(i, "label"));
            hashMap.put("text2", "");
            hashMap.put("editable", Boolean.FALSE);
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final String a() {
        String a = a(this.s - this.q.c(), "value");
        com.unionpay.mobile.android.upwidget.o oVar = this.f62u;
        if (oVar != null && !oVar.b()) {
            a = null;
        }
        com.unionpay.mobile.android.utils.j.c("uppay", q() + " : " + a);
        return a;
    }

    public final void a(o.a aVar) {
        this.f62u.a(aVar);
    }

    public final void a(JSONArray jSONArray) {
        if (jSONArray.toString().equals(this.n.toString())) {
            return;
        }
        this.p = null;
        this.n = jSONArray;
        this.o = b(jSONArray);
        this.q = new com.unionpay.mobile.android.upwidget.c(this.c, this.o, this.w, "", "", 1, 0);
        com.unionpay.mobile.android.upwidget.e eVar = new com.unionpay.mobile.android.upwidget.e(this.c, this.q);
        this.r = eVar;
        eVar.a(this.b);
        this.r.a(this.a);
        a(this.l);
    }

    public final void a(boolean z) {
        this.y = z;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final boolean a(LinearLayout linearLayout, String str) {
        if (a(str)) {
            return true;
        }
        LinearLayout linearLayout2 = new LinearLayout(this.c);
        linearLayout2.setBackgroundColor(-1);
        linearLayout2.setOrientation(1);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n));
        String a = com.unionpay.mobile.android.utils.i.a(this.m, "type");
        if ("instalment".equals(a)) {
            LinearLayout linearLayout3 = new LinearLayout(this.c);
            linearLayout3.setId(linearLayout3.hashCode());
            linearLayout3.setBackgroundColor(-3419943);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
            layoutParams.leftMargin = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
            linearLayout2.addView(linearLayout3, layoutParams);
        }
        JSONObject a2 = a(a, str, com.unionpay.mobile.android.utils.i.a(this.m, "checked"));
        com.unionpay.mobile.android.upwidget.o oVar = new com.unionpay.mobile.android.upwidget.o(this.c, a2, v() + "_agree_installment");
        this.f62u = oVar;
        oVar.a();
        this.f62u.a(com.unionpay.mobile.android.global.b.k);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
        layoutParams2.gravity = 16;
        int a3 = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        layoutParams2.rightMargin = a3;
        layoutParams2.leftMargin = a3;
        linearLayout2.addView(this.f62u, layoutParams2);
        return true;
    }

    public final void b(boolean z) {
        this.f62u.a(z);
        if (!z) {
            this.y = z;
        }
        int i = (this.y && z) ? 0 : 8;
        this.l.setVisibility(i);
        TextView textView = this.v;
        if (textView != null) {
            if (TextUtils.isEmpty(textView.getText().toString())) {
                this.v.setVisibility(8);
            } else {
                this.v.setVisibility(i);
            }
        }
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return true;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final boolean b_() {
        this.v = new TextView(this.c);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        int a = com.unionpay.mobile.android.utils.f.a(this.c, 5.0f);
        layoutParams.bottomMargin = a;
        layoutParams.topMargin = a;
        this.v.setTextSize(com.unionpay.mobile.android.global.b.k);
        addView(this.v, layoutParams);
        this.v.setVisibility(8);
        return true;
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean c() {
        return true;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_select_installment";
    }

    @Override // com.unionpay.mobile.android.widgets.y, com.unionpay.mobile.android.widgets.az
    public final boolean f() {
        String a = a(this.s - this.q.c(), "available");
        return TextUtils.isEmpty(a) || !"1".equals(a);
    }

    public final boolean g() {
        com.unionpay.mobile.android.upwidget.o oVar = this.f62u;
        if (oVar != null) {
            return oVar.b();
        }
        return true;
    }
}
