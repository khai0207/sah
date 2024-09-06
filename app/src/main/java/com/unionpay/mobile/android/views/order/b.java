package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.upviews.a;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b extends AbstractMethod {
    private TextView A;
    private boolean B;
    private int C;
    private JSONObject g;
    private JSONArray h;
    private boolean i;
    private com.unionpay.mobile.android.upviews.a j;
    private List<Map<String, Object>> k;
    private Drawable l;
    private PopupWindow m;
    private com.unionpay.mobile.android.upwidget.e n;
    private com.unionpay.mobile.android.upwidget.c o;
    private String p;
    private final View.OnClickListener q;
    private final View.OnClickListener r;
    private final AdapterView.OnItemClickListener s;
    private a t;

    /* renamed from: u, reason: collision with root package name */
    private int f56u;
    private int v;
    private InterfaceC0074b w;
    private Drawable x;
    private Drawable y;
    private Drawable z;

    /* loaded from: classes.dex */
    private class a {
        View a;
        TextView b;

        private a() {
        }

        /* synthetic */ a(b bVar, byte b) {
            this();
        }
    }

    /* renamed from: com.unionpay.mobile.android.views.order.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0074b {
        int a();

        int a(int i);

        int b(int i);
    }

    public b(Context context, List<Map<String, Object>> list, String str) {
        super(context);
        this.q = new c(this);
        this.r = new d(this);
        this.s = new e(this);
        this.B = false;
        this.C = l.b.intValue();
        this.v = 1;
        this.f56u = -1;
        this.k = list;
        this.p = str;
        com.unionpay.mobile.android.upwidget.c cVar = new com.unionpay.mobile.android.upwidget.c(this.b, this.k, com.unionpay.mobile.android.languages.c.bD.bh, this.p, com.unionpay.mobile.android.languages.c.bD.bi, this.v, 0);
        this.o = cVar;
        cVar.a(this.q);
        com.unionpay.mobile.android.upwidget.e eVar = new com.unionpay.mobile.android.upwidget.e(this.b, this.o);
        this.n = eVar;
        eVar.a(this.s);
        this.n.a(this.r);
    }

    static /* synthetic */ void a(b bVar, View view) {
        if (bVar.m == null) {
            bVar.m = new PopupWindow((View) bVar.n, -1, -1, true);
            bVar.m.setBackgroundDrawable(new ColorDrawable(-1342177280));
            bVar.m.update();
        }
        bVar.m.showAtLocation(view, 80, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(int i) {
        int c = i - this.o.c();
        if (i == 0) {
            return;
        }
        List<Map<String, Object>> list = this.k;
        if (list != null && i == list.size() + this.o.c()) {
            com.unionpay.mobile.android.utils.j.a("direct", " new ");
            InterfaceC0074b interfaceC0074b = this.w;
            if (interfaceC0074b != null) {
                interfaceC0074b.a();
            }
        } else if (this.o.b() && this.o.c(i)) {
            com.unionpay.mobile.android.utils.j.a("direct", " delete " + i);
            i();
            InterfaceC0074b interfaceC0074b2 = this.w;
            if (interfaceC0074b2 != null) {
                this.f56u = c;
                interfaceC0074b2.a(c);
            }
        } else {
            this.v = i;
            this.o.a(i);
            com.unionpay.mobile.android.utils.j.a("direct", " pay with " + i);
            a aVar = this.t;
            if (aVar != null) {
                aVar.b.setText(this.o.b(this.v));
            }
            InterfaceC0074b interfaceC0074b3 = this.w;
            if (interfaceC0074b3 != null) {
                interfaceC0074b3.b(c);
            }
        }
        this.m.dismiss();
    }

    private boolean h() {
        List<Map<String, Object>> list;
        return this.i || (list = this.k) == null || list.size() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        com.unionpay.mobile.android.upwidget.c cVar = this.o;
        if (cVar != null) {
            cVar.a();
            String str = this.o.b() ? com.unionpay.mobile.android.languages.c.bD.bj : com.unionpay.mobile.android.languages.c.bD.bh;
            String str2 = this.o.b() ? com.unionpay.mobile.android.languages.c.bD.bk : com.unionpay.mobile.android.languages.c.bD.bi;
            this.o.a(str);
            this.o.b(str2);
            this.o.notifyDataSetChanged();
        }
    }

    public final b a(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        this.x = drawable;
        this.y = drawable2;
        this.z = drawable3;
        return this;
    }

    public final b a(InterfaceC0074b interfaceC0074b) {
        this.w = interfaceC0074b;
        return this;
    }

    public final b a(JSONArray jSONArray) {
        this.h = jSONArray;
        return this;
    }

    public final b a(JSONObject jSONObject) {
        this.g = jSONObject;
        TextView textView = this.A;
        if (textView != null) {
            textView.setText(Html.fromHtml(a(jSONObject, "label")));
        }
        return this;
    }

    public final void a(int i) {
        int i2;
        List<Map<String, Object>> list = this.k;
        int size = list != null ? list.size() : 0;
        if (size > 0 && (i2 = this.f56u) >= 0 && i2 < size) {
            this.k.remove(i2);
            this.f56u = -1;
            this.o.notifyDataSetChanged();
        }
        c(i + this.o.c());
    }

    @Override // com.unionpay.mobile.android.views.order.AbstractMethod
    public final void a(RelativeLayout relativeLayout) {
        TextView textView = new TextView(this.b);
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        textView.setTextColor(-13421773);
        textView.setText(this.c);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        layoutParams.leftMargin = com.unionpay.mobile.android.utils.f.a(this.b, 10.0f);
        relativeLayout.addView(textView, layoutParams);
        if (TextUtils.isEmpty(this.c)) {
            relativeLayout.setVisibility(8);
        }
        if (h()) {
            String a2 = a(this.g, "label");
            TextView textView2 = new TextView(this.b);
            this.A = textView2;
            textView2.setOnClickListener(new f(this));
            if (!a(a2)) {
                this.A.setText(Html.fromHtml(a2));
            }
            a(this.A);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(11, -1);
            layoutParams2.rightMargin = com.unionpay.mobile.android.utils.f.a(this.b, 10.0f);
            layoutParams2.addRule(15, -1);
            relativeLayout.addView(this.A, layoutParams2);
        }
    }

    @Override // com.unionpay.mobile.android.views.order.AbstractMethod
    public final int b() {
        return this.C;
    }

    public final b b(Drawable drawable) {
        this.l = drawable;
        return this;
    }

    public final b b(boolean z) {
        this.B = z;
        return this;
    }

    public final void b(int i) {
        this.C = i;
    }

    @Override // com.unionpay.mobile.android.views.order.AbstractMethod
    public final void b(RelativeLayout relativeLayout) {
        if (h() || this.B) {
            if (this.B) {
                g();
            }
            this.j = new com.unionpay.mobile.android.upviews.a(this.b, this.h, this, "bankpay");
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
            relativeLayout.addView(this.j, layoutParams);
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this.b);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-3419943);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
        relativeLayout.addView(linearLayout, layoutParams2);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.b);
        relativeLayout2.setId(relativeLayout2.hashCode());
        relativeLayout2.setBackgroundDrawable(this.l);
        relativeLayout2.setOnClickListener(new g(this));
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.b.n);
        layoutParams3.addRule(3, linearLayout.getId());
        relativeLayout.addView(relativeLayout2, layoutParams3);
        ImageView imageView = new ImageView(this.b);
        imageView.setId(imageView.hashCode());
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.b).a(1002));
        int a2 = com.unionpay.mobile.android.utils.f.a(this.b, 15.0f);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams4.addRule(11, -1);
        layoutParams4.addRule(15, -1);
        layoutParams4.rightMargin = com.unionpay.mobile.android.utils.f.a(this.b, 10.0f);
        relativeLayout2.addView(imageView, layoutParams4);
        TextView textView = new TextView(this.b);
        textView.setText(this.o.b(this.v));
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        textView.setTextColor(-10066330);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(9, -1);
        layoutParams5.addRule(15, -1);
        layoutParams5.addRule(0, imageView.getId());
        layoutParams5.leftMargin = com.unionpay.mobile.android.utils.f.a(this.b, 10.0f);
        relativeLayout2.addView(textView, layoutParams5);
        LinearLayout linearLayout2 = new LinearLayout(this.b);
        linearLayout2.setBackgroundColor(-3419943);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams6.bottomMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams6.addRule(3, relativeLayout2.getId());
        relativeLayout.addView(linearLayout2, layoutParams6);
        a aVar = new a(this, (byte) 0);
        this.t = aVar;
        aVar.a = relativeLayout2;
        this.t.b = textView;
    }

    public final void b(String str) {
        a aVar = this.t;
        if (aVar != null) {
            aVar.b.setText(str);
        }
    }

    @Override // com.unionpay.mobile.android.views.order.AbstractMethod
    public final a.C0071a c() {
        com.unionpay.mobile.android.upviews.a aVar = this.j;
        if (aVar != null) {
            return aVar.b();
        }
        return null;
    }

    @Override // com.unionpay.mobile.android.views.order.AbstractMethod
    public final void c(RelativeLayout relativeLayout) {
        relativeLayout.setVisibility(8);
    }

    @Override // com.unionpay.mobile.android.views.order.AbstractMethod
    public final int d() {
        return this.v - this.o.c();
    }

    public final b d(String str) {
        this.c = str;
        return this;
    }

    public final b e(String str) {
        this.d = str;
        return this;
    }

    @Override // com.unionpay.mobile.android.views.order.AbstractMethod
    public final String e() {
        return this.d;
    }

    public final void f(String str) {
        this.o.b(str);
    }

    @Override // com.unionpay.mobile.android.views.order.AbstractMethod
    public final boolean f() {
        com.unionpay.mobile.android.upviews.a aVar = this.j;
        return aVar == null || aVar.e();
    }

    @Override // com.unionpay.mobile.android.upviews.a.b
    public final void t() {
    }
}
