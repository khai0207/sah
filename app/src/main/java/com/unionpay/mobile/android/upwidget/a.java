package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import com.unionpay.sdk.UPAgent;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a extends LinearLayout {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private Button j;
    private boolean k;
    private Context l;
    private float m;
    private View.OnClickListener n;
    private String o;
    private TextView p;
    private InterfaceC0073a q;
    private String r;

    /* renamed from: com.unionpay.mobile.android.upwidget.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0073a {
    }

    public a(Context context, JSONObject jSONObject, View.OnClickListener onClickListener, String str) {
        this(context, jSONObject, onClickListener, str, (byte) 0);
    }

    private a(Context context, JSONObject jSONObject, View.OnClickListener onClickListener, String str, byte b) {
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
        this.j = null;
        this.k = false;
        this.l = null;
        this.m = 0.0f;
        this.n = new b(this);
        this.l = context;
        this.m = 16.0f;
        this.r = str;
        this.a = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.h.c.e);
        this.b = com.unionpay.mobile.android.utils.i.a(jSONObject, "value");
        this.c = com.unionpay.mobile.android.utils.i.a(jSONObject, "label");
        this.d = com.unionpay.mobile.android.utils.i.a(jSONObject, "href_label");
        this.e = com.unionpay.mobile.android.utils.i.a(jSONObject, "href_url");
        this.f = com.unionpay.mobile.android.utils.i.a(jSONObject, "href_title");
        this.g = com.unionpay.mobile.android.utils.i.a(jSONObject, "checked");
        this.h = com.unionpay.mobile.android.utils.i.a(jSONObject, "required");
        this.i = com.unionpay.mobile.android.utils.i.a(jSONObject, "error_info");
        this.o = com.unionpay.mobile.android.utils.i.a(jSONObject, "ckb_style");
        this.j = new Button(this.l);
        if (a(this.g) && this.g.equalsIgnoreCase("0")) {
            this.k = true;
        } else {
            this.k = false;
        }
        this.j.setOnClickListener(this.n);
        g();
        f();
        int a = com.unionpay.mobile.android.utils.f.a(this.l, 20.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        layoutParams.gravity = 16;
        addView(this.j, layoutParams);
        InterfaceC0073a interfaceC0073a = this.q;
        if (a(this.c)) {
            TextView textView = new TextView(this.l);
            this.p = textView;
            textView.setText(this.c);
            this.p.setTextSize(this.m);
            this.p.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.p.setOnClickListener(this.n);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 16;
            layoutParams2.leftMargin = com.unionpay.mobile.android.global.a.d;
            addView(this.p, layoutParams2);
        }
        if (a(this.d) && a(this.e)) {
            TextView textView2 = new TextView(this.l);
            textView2.setText(Html.fromHtml(this.d));
            textView2.setTextColor(com.unionpay.mobile.android.utils.g.a(-10705958, -5846275, -5846275, -6710887));
            String.format("<u>%s</u>", this.d);
            textView2.setTextSize(this.m);
            textView2.setOnClickListener(onClickListener);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.gravity = 16;
            addView(textView2, layoutParams3);
        }
    }

    static /* synthetic */ void a(a aVar) {
        boolean z = !aVar.k;
        aVar.k = z;
        String str = z ? "y" : "n";
        Context context = aVar.l;
        String str2 = aVar.r;
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
        if (aVar.q != null) {
            boolean z2 = aVar.k;
        }
        aVar.g();
    }

    private static boolean a(String str) {
        return str != null && str.length() > 0;
    }

    private boolean f() {
        return "small".equalsIgnoreCase(this.o);
    }

    private void g() {
        if (this.j == null) {
            return;
        }
        int i = this.k ? 1008 : PointerIconCompat.TYPE_CROSSHAIR;
        int a = f() ? com.unionpay.mobile.android.utils.f.a(this.l, 15.0f) : com.unionpay.mobile.android.global.a.w;
        this.j.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.l).a(i, a, a));
    }

    public final String a() {
        return String.format("\"%s\":\"%s\"", this.a, this.k ? this.b : "");
    }

    public final String b() {
        return this.i;
    }

    public final String c() {
        return this.e;
    }

    public final String d() {
        return this.f;
    }

    public final boolean e() {
        if (a(this.h) && this.h.equalsIgnoreCase("0")) {
            return this.k;
        }
        return true;
    }
}
