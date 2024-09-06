package com.unionpay.mobile.android.upwidget;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.kqg.main.constant.KV;
import com.unionpay.mobile.android.widgets.ac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class h extends LinearLayout {
    private View.OnClickListener A;
    private View.OnClickListener B;
    private View.OnClickListener C;
    private View.OnClickListener D;
    private Context a;
    private JSONArray b;
    private int c;
    private int d;
    private boolean e;
    private a[] f;
    private ArrayList<Object> g;
    private LinearLayout h;
    private HorizontalScrollView i;
    private com.unionpay.mobile.android.widgets.k j;
    private ac k;
    private TextView l;
    private TextView m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private String s;
    private ArrayList<AdapterView.OnItemClickListener> t;

    /* renamed from: u, reason: collision with root package name */
    private ArrayList<View.OnClickListener> f54u;
    private ArrayList<View.OnClickListener> v;
    private ArrayList<View.OnClickListener> w;
    private ArrayList<View.OnClickListener> x;
    private AdapterView.OnItemClickListener y;
    private View.OnClickListener z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    class a {
        TextView a;
        LinearLayout b;
        View c;
        String d;

        private a() {
        }

        /* synthetic */ a(h hVar, byte b) {
            this();
        }
    }

    public h(Context context, JSONArray jSONArray, int i, String str) {
        super(context);
        this.e = true;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = 0;
        this.o = 0;
        this.p = -1;
        this.t = new ArrayList<>();
        this.f54u = new ArrayList<>();
        this.v = new ArrayList<>();
        this.w = new ArrayList<>();
        this.x = new ArrayList<>();
        this.y = new i(this);
        this.z = new j(this);
        this.A = new k(this);
        this.B = new l(this);
        this.C = new m(this);
        this.D = new n(this);
        this.a = context;
        this.b = jSONArray;
        this.o = i;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.a).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.q = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        ((Activity) this.a).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
        this.r = displayMetrics2.heightPixels;
        this.s = str;
        if (this.b != null) {
            FrameLayout frameLayout = new FrameLayout(this.a);
            RelativeLayout relativeLayout = new RelativeLayout(this.a);
            frameLayout.addView(relativeLayout, new FrameLayout.LayoutParams(-1, -1));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (this.r / 3) * 2);
            layoutParams.addRule(12, -1);
            LinearLayout linearLayout = new LinearLayout(this.a);
            linearLayout.setOrientation(1);
            linearLayout.setBackgroundColor(-1);
            linearLayout.setId(linearLayout.hashCode());
            relativeLayout.addView(linearLayout, layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.a);
            layoutParams2.addRule(10, -1);
            layoutParams2.addRule(2, linearLayout.getId());
            relativeLayout.addView(linearLayout2, layoutParams2);
            linearLayout2.setOnClickListener(this.z);
            LinearLayout linearLayout3 = new LinearLayout(this.a);
            this.h = linearLayout3;
            linearLayout3.setBackgroundColor(-1);
            this.h.setOrientation(0);
            linearLayout.addView(this.h, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n));
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.utils.f.a(this.a, 1.0f));
            LinearLayout linearLayout4 = new LinearLayout(this.a);
            linearLayout4.setBackgroundColor(-3355444);
            linearLayout.addView(linearLayout4, layoutParams3);
            HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this.a);
            this.i = horizontalScrollView;
            horizontalScrollView.setBackgroundColor(-1052684);
            linearLayout.addView(this.i, new LinearLayout.LayoutParams(-2, -1));
            int a2 = com.unionpay.mobile.android.utils.f.a(this.a, 40.0f);
            ImageView imageView = new ImageView(this.a);
            imageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.a).a(KV.EVENT_BIND_ACCOUNT_BY_EMAIL));
            imageView.setOnClickListener(this.z);
            FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(a2, a2);
            layoutParams4.gravity = 85;
            layoutParams4.rightMargin = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
            layoutParams4.bottomMargin = ((this.r / 3) * 2) - (a2 / 2);
            frameLayout.addView(imageView, layoutParams4);
            addView(frameLayout);
            a();
        }
    }

    private View a(LinearLayout linearLayout, JSONObject jSONObject) {
        c cVar = new c(this.a, b(com.unionpay.mobile.android.utils.i.d(jSONObject, "options")), "", "", "", this.p, 1);
        this.g.add(cVar);
        ListView listView = new ListView(this.a);
        listView.setDivider(null);
        listView.setAdapter((ListAdapter) cVar);
        listView.setOnItemClickListener(this.y);
        listView.setCacheColorHint(-1);
        linearLayout.addView(listView, new LinearLayout.LayoutParams(this.q, -1));
        return listView;
    }

    private static String a(JSONArray jSONArray, int i, String str) {
        Object b = com.unionpay.mobile.android.utils.i.b(jSONArray, i);
        return b != null ? com.unionpay.mobile.android.utils.i.a((JSONObject) b, str) : "";
    }

    private void a() {
        View a2;
        int length = this.b.length();
        this.f = new a[length];
        byte b = 0;
        for (int i = 0; i < length; i++) {
            this.f[i] = new a(this, b);
            if (this.f[i].a == null) {
                this.f[i].a = new TextView(this.a);
            }
            if (this.f[i].b == null) {
                this.f[i].b = new LinearLayout(this.a);
            }
            if (this.f[i].c == null) {
                this.f[i].c = new ListView(this.a);
            }
            if (this.f[i].d == null) {
                this.f[i].d = "";
            }
        }
        this.g = new ArrayList<>(this.b.length());
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(0);
        this.i.addView(linearLayout, new LinearLayout.LayoutParams(-2, -1));
        for (int i2 = 0; i2 < this.b.length(); i2++) {
            JSONObject jSONObject = (JSONObject) com.unionpay.mobile.android.utils.i.b(this.b, i2);
            String a3 = com.unionpay.mobile.android.utils.i.a(jSONObject, "action");
            String a4 = com.unionpay.mobile.android.utils.i.a(jSONObject, "label");
            RelativeLayout relativeLayout = new RelativeLayout(this.a);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            layoutParams.leftMargin = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
            this.h.addView(relativeLayout, layoutParams);
            int a5 = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
            TextView textView = new TextView(this.a);
            textView.setText(a4);
            textView.setTextSize(com.unionpay.mobile.android.global.b.k);
            textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            textView.setSingleLine(true);
            textView.setTextColor(-10066330);
            textView.setPadding(a5, 0, a5, 0);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(15, -1);
            relativeLayout.addView(textView, layoutParams2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(((int) textView.getPaint().measureText(a4)) + a5 + a5, com.unionpay.mobile.android.utils.f.a(this.a, 2.0f));
            layoutParams3.addRule(12, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.a);
            linearLayout2.setBackgroundColor(-16730965);
            if (this.o != i2) {
                linearLayout2.setVisibility(8);
            }
            relativeLayout.addView(linearLayout2, layoutParams3);
            relativeLayout.setTag(Integer.valueOf(i2));
            relativeLayout.setOnClickListener(this.D);
            this.f[i2].a = textView;
            this.f[i2].b = linearLayout2;
            this.f[i2].d = a3;
            if (this.o == i2) {
                this.p = 0;
            } else {
                this.p = -1;
            }
            String a6 = com.unionpay.mobile.android.utils.i.a(jSONObject, "type");
            if ("coupon".equals(a6)) {
                this.d = i2;
                a2 = b(linearLayout, jSONObject);
            } else {
                if ("point".equals(a6)) {
                    this.c = i2;
                } else if (!"upoint".equals(a6)) {
                    a2 = a(linearLayout, jSONObject);
                }
                a2 = c(linearLayout, jSONObject);
            }
            this.f[i2].c = a2;
            this.f[i2].c.setVisibility(8);
        }
        a(this.o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.f[this.o].b.setVisibility(8);
        this.f[this.o].a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f[this.o].c.setVisibility(8);
        this.f[i].b.setVisibility(0);
        this.f[i].a.setTextColor(-16730965);
        this.f[i].c.setVisibility(0);
        this.o = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(LinearLayout linearLayout, boolean z, String str, JSONObject jSONObject, c cVar) {
        linearLayout.removeAllViews();
        ListView listView = new ListView(this.a);
        listView.setDivider(null);
        listView.setAdapter((ListAdapter) cVar);
        listView.setOnItemClickListener(this.y);
        this.g.add(cVar);
        linearLayout.addView(listView, new LinearLayout.LayoutParams(this.q, -2));
        if (cVar != null) {
            ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).gravity = 48;
        }
        if (z) {
            int i = com.unionpay.mobile.android.global.a.p;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
            layoutParams.bottomMargin = com.unionpay.mobile.android.utils.f.a(this.a, 12.0f);
            layoutParams.gravity = 17;
            linearLayout.addView(new ProgressBar(this.a), layoutParams);
        }
        TextView textView = new TextView(this.a);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
            textView.setTextSize(com.unionpay.mobile.android.global.b.k);
            textView.setTextColor(-13421773);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 17;
            linearLayout.addView(textView, layoutParams2);
        }
        if (jSONObject != null) {
            TextView textView2 = new TextView(this.a);
            textView2.setText(com.unionpay.mobile.android.utils.i.a(jSONObject, "label"));
            textView2.setTextSize(com.unionpay.mobile.android.global.b.i);
            textView2.setTextColor(com.unionpay.mobile.android.utils.g.a(com.unionpay.mobile.android.global.b.b, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.d));
            textView2.setGravity(17);
            textView2.setEnabled(true);
            int i2 = com.unionpay.mobile.android.global.a.n;
            textView2.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.a).a(2008));
            float measureText = textView.getPaint().measureText(str);
            textView2.setOnClickListener(this.A);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams((int) measureText, i2);
            int i3 = com.unionpay.mobile.android.global.a.f;
            layoutParams3.bottomMargin = i3;
            layoutParams3.topMargin = i3;
            int a2 = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
            layoutParams3.rightMargin = a2;
            layoutParams3.leftMargin = a2;
            linearLayout.addView(textView2, layoutParams3);
        }
    }

    private View b(LinearLayout linearLayout, JSONObject jSONObject) {
        JSONObject jSONObject2;
        RelativeLayout relativeLayout = new RelativeLayout(this.a);
        ListView listView = new ListView(this.a);
        JSONObject jSONObject3 = null;
        listView.setDivider(null);
        listView.setAdapter((ListAdapter) null);
        this.g.add(listView);
        JSONArray d = com.unionpay.mobile.android.utils.i.d(jSONObject, "rules");
        if (d == null || d.length() <= 0) {
            jSONObject2 = null;
        } else {
            jSONObject2 = null;
            for (int i = 0; i < d.length(); i++) {
                Object b = com.unionpay.mobile.android.utils.i.b(d, i);
                if (b != null) {
                    JSONObject jSONObject4 = (JSONObject) b;
                    String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject4, "type");
                    if ("coupon_code".equals(a2)) {
                        jSONObject3 = jSONObject4;
                    } else if ("string".equals(a2)) {
                        jSONObject2 = jSONObject4;
                    }
                }
            }
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams.addRule(10, -1);
        relativeLayout.addView(listView, layoutParams);
        int i2 = com.unionpay.mobile.android.global.a.I - (com.unionpay.mobile.android.global.a.f * 4);
        com.unionpay.mobile.android.widgets.k kVar = new com.unionpay.mobile.android.widgets.k(this.a, i2, jSONObject3, this.s);
        this.j = kVar;
        kVar.setId(kVar.hashCode());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams2.addRule(10, -1);
        int a3 = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
        layoutParams2.topMargin = a3;
        layoutParams2.rightMargin = a3;
        layoutParams2.leftMargin = a3;
        relativeLayout.addView(this.j, layoutParams2);
        this.k = new ac(this.a, i2, jSONObject2, this.s);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams3.addRule(3, this.j.getId());
        int a4 = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
        layoutParams3.topMargin = a4;
        layoutParams3.rightMargin = a4;
        layoutParams3.leftMargin = a4;
        relativeLayout.addView(this.k, layoutParams3);
        TextView textView = new TextView(this.a);
        this.l = textView;
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.l.setTextColor(-10066330);
        this.l.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams4.addRule(3, this.j.getId());
        int a5 = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
        layoutParams4.topMargin = a5;
        layoutParams4.rightMargin = a5;
        layoutParams4.leftMargin = a5;
        relativeLayout.addView(this.l, layoutParams4);
        JSONObject c = com.unionpay.mobile.android.utils.i.c(jSONObject, "use_button");
        LinearLayout linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackgroundColor(-1);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.utils.f.a(this.a, 1.0f));
        LinearLayout linearLayout3 = new LinearLayout(this.a);
        linearLayout3.setBackgroundColor(-3355444);
        linearLayout2.addView(linearLayout3, layoutParams5);
        TextView textView2 = new TextView(this.a);
        this.m = textView2;
        textView2.setText(com.unionpay.mobile.android.utils.i.a(c, "label"));
        this.m.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.m.setTextColor(com.unionpay.mobile.android.utils.g.a(com.unionpay.mobile.android.global.b.b, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.d));
        this.m.setGravity(17);
        this.m.setEnabled(false);
        int i3 = com.unionpay.mobile.android.global.a.n;
        this.m.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.a).a(2008));
        this.m.setTag(Integer.valueOf(this.d));
        this.m.setOnClickListener(this.C);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, i3);
        int i4 = com.unionpay.mobile.android.global.a.f;
        layoutParams6.bottomMargin = i4;
        layoutParams6.topMargin = i4;
        int a6 = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
        layoutParams6.rightMargin = a6;
        layoutParams6.leftMargin = a6;
        linearLayout2.addView(this.m, layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams7.addRule(12, -1);
        relativeLayout.addView(linearLayout2, layoutParams7);
        linearLayout.addView(relativeLayout, new LinearLayout.LayoutParams(this.q, -2));
        return relativeLayout;
    }

    private static List<Map<String, Object>> b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            HashMap hashMap = new HashMap();
            hashMap.put("text1", a(jSONArray, i, "label"));
            hashMap.put("text2", "");
            hashMap.put("editable", Boolean.FALSE);
            String a2 = a(jSONArray, i, "available");
            boolean booleanValue = Boolean.TRUE.booleanValue();
            if (!TextUtils.isEmpty(a2) && "1".equals(a2)) {
                booleanValue = Boolean.FALSE.booleanValue();
            }
            hashMap.put("available", Boolean.valueOf(booleanValue));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private View c(LinearLayout linearLayout, JSONObject jSONObject) {
        LinearLayout.LayoutParams layoutParams;
        LinearLayout linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setOrientation(1);
        String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "tip");
        String a3 = com.unionpay.mobile.android.utils.i.a(jSONObject, "empty_info");
        JSONObject c = com.unionpay.mobile.android.utils.i.c(jSONObject, "button");
        if (c != null) {
            a(linearLayout2, false, a2, c, null);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
        } else if (!"upoint".equals(com.unionpay.mobile.android.utils.i.a(jSONObject, "type"))) {
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
        } else {
            if (a3 == null || TextUtils.isEmpty(a3)) {
                return a(linearLayout, jSONObject);
            }
            a(linearLayout2, false, a3, null, null);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
        }
        layoutParams.gravity = 17;
        linearLayout2.setGravity(17);
        linearLayout.addView(linearLayout2, layoutParams);
        return linearLayout2;
    }

    static /* synthetic */ boolean o(h hVar) {
        hVar.e = false;
        return false;
    }

    public final void a(View.OnClickListener onClickListener) {
        this.f54u.add(onClickListener);
    }

    public final void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.t.add(onItemClickListener);
    }

    public final void a(JSONArray jSONArray) {
        Object b = com.unionpay.mobile.android.utils.i.b(jSONArray, 0);
        if (b != null) {
            this.l.setText(com.unionpay.mobile.android.utils.i.a((JSONObject) b, "label"));
            this.l.setVisibility(0);
            this.k.setVisibility(8);
        }
        this.m.setEnabled(true);
    }

    public final void a(JSONArray jSONArray, String str) {
        c cVar;
        if (jSONArray == null || jSONArray.length() <= 0) {
            cVar = null;
        } else {
            cVar = new c(this.a, b(jSONArray), "", "", "", -1, 1);
            this.g.add(this.c, cVar);
        }
        a((LinearLayout) this.f[this.c].c, false, str, null, cVar);
    }

    public final void b(View.OnClickListener onClickListener) {
        this.v.add(onClickListener);
    }

    public final void c(View.OnClickListener onClickListener) {
        this.w.add(onClickListener);
    }

    public final void d(View.OnClickListener onClickListener) {
        this.x.add(onClickListener);
    }

    public final void e(View.OnClickListener onClickListener) {
        com.unionpay.mobile.android.widgets.k kVar = this.j;
        if (kVar != null) {
            kVar.a(onClickListener);
            this.j.b(this.B);
        }
    }
}
