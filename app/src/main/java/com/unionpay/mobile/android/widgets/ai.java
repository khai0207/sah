package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.upwidget.o;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class ai extends y {
    private String A;
    private boolean B;
    private String C;
    private a D;
    private final View.OnClickListener a;
    private final View.OnClickListener b;
    private final AdapterView.OnItemClickListener n;
    private JSONArray o;
    private PopupWindow p;
    private com.unionpay.mobile.android.upwidget.h q;
    private int r;
    private int s;
    private JSONArray t;

    /* renamed from: u, reason: collision with root package name */
    private JSONArray f59u;
    private TextView v;
    private com.unionpay.mobile.android.upwidget.o w;
    private TextView x;
    private String y;
    private RelativeLayout z;

    /* loaded from: classes.dex */
    public interface a {
        void g();
    }

    public ai(Context context, JSONObject jSONObject, String str, a aVar) {
        super(context, jSONObject, str);
        this.a = new aj(this);
        this.b = new ak(this);
        this.n = new al(this);
        this.s = 0;
        this.t = null;
        this.f59u = null;
        this.A = "";
        this.B = true;
        this.C = "";
        this.D = null;
        this.D = aVar;
        this.r = 0;
        this.o = com.unionpay.mobile.android.utils.i.d(this.m, "items");
        String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "label");
        this.y = a2;
        if (a(a2)) {
            this.y = com.unionpay.mobile.android.languages.c.bD.bg;
        }
        if (!TextUtils.isEmpty(com.unionpay.mobile.android.utils.i.a(jSONObject, "default_item_idx"))) {
            this.s = Integer.parseInt(com.unionpay.mobile.android.utils.i.a(jSONObject, "default_item_idx"));
        }
        com.unionpay.mobile.android.upwidget.h hVar = new com.unionpay.mobile.android.upwidget.h(context, this.o, this.s, str);
        this.q = hVar;
        hVar.a(this.n);
        this.q.a(this.a);
        this.q.d(this.b);
        RelativeLayout relativeLayout = this.l;
        Drawable a3 = com.unionpay.mobile.android.resource.c.a(this.c).a(2014);
        LinearLayout linearLayout = new LinearLayout(this.c);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-3419943);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        com.unionpay.mobile.android.utils.i.a(this.m, "type");
        relativeLayout.addView(linearLayout, layoutParams);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.c);
        this.z = relativeLayout2;
        relativeLayout2.setId(relativeLayout2.hashCode());
        this.z.setBackgroundDrawable(a3);
        this.z.setOnClickListener(new am(this));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(3, linearLayout.getId());
        relativeLayout.addView(this.z, layoutParams2);
        ImageView imageView = new ImageView(this.c);
        imageView.setId(imageView.hashCode());
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.c).a(1002));
        int a4 = com.unionpay.mobile.android.utils.f.a(this.c, 15.0f);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(a4, a4);
        layoutParams3.addRule(11, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.rightMargin = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        this.z.addView(imageView, layoutParams3);
        TextView textView = new TextView(this.c);
        this.v = textView;
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.v.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.v.setSingleLine(true);
        this.v.setTextColor(-10066330);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(15, -1);
        layoutParams4.addRule(9, -1);
        layoutParams4.addRule(0, imageView.getId());
        layoutParams4.leftMargin = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        layoutParams4.rightMargin = layoutParams4.leftMargin;
        this.z.addView(this.v, layoutParams4);
        LinearLayout linearLayout2 = new LinearLayout(this.c);
        linearLayout2.setBackgroundColor(-3419943);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams5.bottomMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams5.addRule(3, this.z.getId());
        relativeLayout.addView(linearLayout2, layoutParams5);
        com.unionpay.mobile.android.upwidget.o oVar = this.w;
        a(oVar != null ? oVar.b() : true);
        a(this.s, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i, int i2, String str) {
        Object b = com.unionpay.mobile.android.utils.i.b(this.o, i);
        if (b != null) {
            JSONObject jSONObject = (JSONObject) b;
            String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "type");
            Object b2 = com.unionpay.mobile.android.utils.i.b("coupon".equals(a2) ? this.t : "point".equals(a2) ? this.f59u : com.unionpay.mobile.android.utils.i.d(jSONObject, "options"), i2);
            if (b2 != null) {
                return com.unionpay.mobile.android.utils.i.a((JSONObject) b2, str);
            }
        }
        return "";
    }

    static /* synthetic */ String a(ai aiVar, int i, String str) {
        Object b = com.unionpay.mobile.android.utils.i.b(aiVar.o, i);
        return b != null ? com.unionpay.mobile.android.utils.i.a((JSONObject) b, str) : "";
    }

    private static JSONObject a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str);
            jSONObject.put("label", str2);
            jSONObject.put("checked", str3);
            jSONObject.put("ckb_style", "small");
            jSONObject.put("required", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        this.s = i;
        this.r = i2;
        TextView textView = this.v;
        if (textView != null) {
            textView.setText(a(i, i2, "label"));
        }
        String a2 = a(i, i2, "rel_label");
        String a3 = a(i, i2, "rel_value");
        String a4 = a(i, i2, "rel_value_style");
        this.C = a3;
        if (!this.B) {
            this.D.g();
        }
        this.B = false;
        this.A = a(i, i2, "value");
        if (a(a2) && a(a3)) {
            this.x.setVisibility(8);
            return;
        }
        if (com.unionpay.mobile.android.data.a.a(a4)) {
            a4 = Integer.toString(Color.parseColor(a4), 16);
        }
        String str = "#ff" + a4;
        TextView textView2 = this.x;
        int parseColor = Color.parseColor(str);
        int length = a2.length();
        int length2 = TextUtils.isEmpty(a3) ? 0 : a3.length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(a2 + a3);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-13421773);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(parseColor);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, length, 33);
        spannableStringBuilder.setSpan(foregroundColorSpan2, length, length2 + length, 18);
        textView2.setText(spannableStringBuilder);
        com.unionpay.mobile.android.upwidget.o oVar = this.w;
        this.x.setVisibility(oVar != null ? oVar.b() : true ? 0 : 8);
    }

    static /* synthetic */ void a(ai aiVar, View view) {
        if (aiVar.p == null) {
            aiVar.p = new PopupWindow((View) aiVar.q, -1, -1, true);
            aiVar.p.setBackgroundDrawable(new ColorDrawable(-1342177280));
            aiVar.p.update();
        }
        aiVar.p.showAtLocation(view, 80, 0, 0);
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final String a() {
        String a2 = a(this.s, this.r, "value");
        if (a2 != null) {
            a2 = a2.replace("\"", "\\\"");
        }
        com.unionpay.mobile.android.upwidget.o oVar = this.w;
        if (oVar != null && !oVar.b()) {
            a2 = null;
        }
        com.unionpay.mobile.android.utils.j.c("uppay", q() + " : " + a2);
        return a2;
    }

    public final void a(View.OnClickListener onClickListener) {
        this.q.b(this.a);
        this.q.b(onClickListener);
    }

    public final void a(o.a aVar) {
        com.unionpay.mobile.android.upwidget.o oVar = this.w;
        if (oVar != null) {
            oVar.a(aVar);
        }
    }

    public final void a(JSONArray jSONArray) {
        this.t = jSONArray;
        this.q.a(jSONArray);
    }

    public final void a(JSONArray jSONArray, String str) {
        this.f59u = jSONArray;
        this.q.a(jSONArray, str);
    }

    public final void a(boolean z) {
        this.A = !z ? "" : a(this.s, this.r, "value");
        com.unionpay.mobile.android.upwidget.o oVar = this.w;
        if (oVar != null) {
            oVar.a(z);
        }
        int i = z ? 0 : 8;
        this.l.setVisibility(i);
        TextView textView = this.x;
        if (textView != null) {
            if (TextUtils.isEmpty(textView.getText().toString())) {
                this.x.setVisibility(8);
            } else {
                this.x.setVisibility(i);
            }
        }
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
        JSONObject a2 = a(com.unionpay.mobile.android.utils.i.a(this.m, "type"), str, com.unionpay.mobile.android.utils.i.a(this.m, "checked"));
        com.unionpay.mobile.android.upwidget.o oVar = new com.unionpay.mobile.android.upwidget.o(this.c, a2, v() + "_agree_reduce_activity");
        this.w = oVar;
        oVar.a();
        this.w.a(com.unionpay.mobile.android.global.b.k);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
        layoutParams.gravity = 16;
        int a3 = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        layoutParams.rightMargin = a3;
        layoutParams.leftMargin = a3;
        linearLayout2.addView(this.w, layoutParams);
        return true;
    }

    public final void b(View.OnClickListener onClickListener) {
        this.q.e(onClickListener);
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return true;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final boolean b_() {
        this.x = new TextView(this.c);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        int a2 = com.unionpay.mobile.android.utils.f.a(this.c, 5.0f);
        layoutParams.bottomMargin = a2;
        layoutParams.topMargin = a2;
        this.x.setTextSize(com.unionpay.mobile.android.global.b.k);
        addView(this.x, layoutParams);
        this.x.setVisibility(8);
        return true;
    }

    public final void c(View.OnClickListener onClickListener) {
        this.q.c(onClickListener);
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean c() {
        return true;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_select_reduce_activity";
    }

    @Override // com.unionpay.mobile.android.widgets.y, com.unionpay.mobile.android.widgets.az
    public final boolean f() {
        String a2 = a(this.s, this.r, "available");
        return TextUtils.isEmpty(a2) || !"1".equals(a2);
    }

    public final String g() {
        return this.A;
    }
}
