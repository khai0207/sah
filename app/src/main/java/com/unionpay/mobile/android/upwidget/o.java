package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import com.unionpay.sdk.UPAgent;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class o extends RelativeLayout {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private RelativeLayout k;
    private Button l;
    private boolean m;
    private Context n;
    private float o;
    private View.OnClickListener p;
    private View.OnClickListener q;
    private String r;
    private TextView s;
    private String t;

    /* renamed from: u, reason: collision with root package name */
    private a f55u;

    /* loaded from: classes.dex */
    public interface a {
        void a(String str, String str2);

        void a(String str, boolean z);
    }

    public o(Context context, JSONObject jSONObject, String str) {
        this(context, jSONObject, str, (byte) 0);
    }

    private o(Context context, JSONObject jSONObject, String str, byte b) {
        super(context);
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.l = null;
        this.m = false;
        this.n = null;
        this.o = 0.0f;
        this.p = new p(this);
        this.q = new q(this);
        this.n = context;
        this.o = 16.0f;
        this.t = str;
        this.a = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.h.c.e);
        this.b = com.unionpay.mobile.android.utils.i.a(jSONObject, "type");
        this.c = com.unionpay.mobile.android.utils.i.a(jSONObject, "value");
        this.d = com.unionpay.mobile.android.utils.i.a(jSONObject, "label");
        this.e = com.unionpay.mobile.android.utils.i.a(jSONObject, "href_label");
        this.f = com.unionpay.mobile.android.utils.i.a(jSONObject, "href_url");
        this.g = com.unionpay.mobile.android.utils.i.a(jSONObject, "href_title");
        this.h = com.unionpay.mobile.android.utils.i.a(jSONObject, "checked");
        this.i = com.unionpay.mobile.android.utils.i.a(jSONObject, "required");
        this.j = com.unionpay.mobile.android.utils.i.a(jSONObject, "error_info");
        this.r = com.unionpay.mobile.android.utils.i.a(jSONObject, "ckb_style");
        this.k = new RelativeLayout(this.n);
        addView(this.k, new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n));
        Button button = new Button(this.n);
        this.l = button;
        button.setId(button.hashCode());
        if (a(this.h) && this.h.equalsIgnoreCase("0")) {
            this.m = true;
        } else {
            this.m = false;
        }
        this.l.setOnClickListener(this.p);
        c();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.unionpay.mobile.android.utils.f.a(this.n, 60.0f), com.unionpay.mobile.android.utils.f.a(this.n, 34.0f));
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        this.k.addView(this.l, layoutParams);
        a aVar = this.f55u;
        if (aVar != null) {
            aVar.a(this.b, this.m);
        }
        if (a(this.e) && a(this.f)) {
            TextView textView = new TextView(this.n);
            textView.setText(Html.fromHtml(this.e));
            textView.setTextSize(this.o);
            textView.setOnClickListener(this.q);
            textView.setTextColor(com.unionpay.mobile.android.utils.g.a(-10705958, -5846275, -5846275, -6710887));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(0, this.l.getId());
            layoutParams2.addRule(15, -1);
            layoutParams2.rightMargin = com.unionpay.mobile.android.utils.f.a(this.n, 10.0f);
            this.k.addView(textView, layoutParams2);
        }
        if (a(this.d)) {
            TextView textView2 = new TextView(this.n);
            this.s = textView2;
            textView2.setText(this.d);
            this.s.setTextSize(this.o);
            this.s.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(9, -1);
            layoutParams3.addRule(15, -1);
            this.k.addView(this.s, layoutParams3);
        }
    }

    static /* synthetic */ void a(o oVar) {
        boolean z = !oVar.m;
        oVar.m = z;
        String str = z ? "y" : "n";
        Context context = oVar.n;
        String str2 = oVar.t;
        String[] strArr = com.unionpay.mobile.android.utils.o.g;
        String[] strArr2 = {str};
        if (com.unionpay.mobile.android.global.a.L) {
            com.unionpay.mobile.android.utils.j.a("uppay-TD", "event:" + str2 + ", keys:" + Arrays.toString(strArr) + ", values:" + Arrays.toString(strArr2));
            if (strArr == null) {
                UPAgent.onEvent(context, str2);
            } else {
                if (strArr.length != 1 || strArr.length > 10) {
                    throw new IllegalArgumentException();
                }
                HashMap hashMap = new HashMap();
                for (int i = 0; i < strArr.length; i++) {
                    hashMap.put(strArr[i], strArr2[i]);
                }
                UPAgent.onEvent(context, str2, str2, hashMap);
            }
        }
        a aVar = oVar.f55u;
        if (aVar != null) {
            aVar.a(oVar.b, oVar.m);
        }
        oVar.c();
    }

    private static boolean a(String str) {
        return str != null && str.length() > 0;
    }

    static /* synthetic */ void b(o oVar) {
        a aVar = oVar.f55u;
        if (aVar != null) {
            aVar.a(oVar.e, oVar.f);
        }
    }

    private void c() {
        if (this.l == null) {
            return;
        }
        this.l.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.n).a(this.m ? 1010 : PointerIconCompat.TYPE_VERTICAL_TEXT, com.unionpay.mobile.android.utils.f.a(this.n, 60.0f), com.unionpay.mobile.android.utils.f.a(this.n, 34.0f)));
    }

    public final void a() {
        TextView textView = this.s;
        if (textView != null) {
            textView.setTextColor(-13421773);
        }
    }

    public final void a(float f) {
        TextView textView = this.s;
        if (textView != null) {
            textView.setTextSize(f);
        }
    }

    public final void a(a aVar) {
        this.f55u = aVar;
    }

    public final void a(boolean z) {
        this.m = z;
        c();
    }

    public final boolean b() {
        if (a(this.i) && this.i.equalsIgnoreCase("0")) {
            return this.m;
        }
        return true;
    }
}
