package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.kqg.main.constant.KV;
import com.unionpay.sdk.UPAgent;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class az extends LinearLayout {
    private String a;
    private String b;
    protected Context c;
    protected int d;
    protected int e;
    protected String f;
    protected String g;
    protected boolean h;
    protected String i;
    protected LinearLayout j;
    protected TextView k;
    protected RelativeLayout l;
    protected JSONObject m;
    private String n;
    private String o;
    private TextView p;
    private ImageView q;
    private boolean r;
    private String s;

    /* loaded from: classes.dex */
    public interface a {
        String a();

        boolean b();

        boolean c();
    }

    public az(Context context, JSONObject jSONObject, String str) {
        super(context);
        this.c = null;
        this.d = ViewCompat.MEASURED_STATE_MASK;
        this.e = -7829368;
        this.a = null;
        this.f = null;
        this.b = null;
        this.g = null;
        this.n = null;
        this.o = null;
        this.h = false;
        this.i = null;
        this.p = null;
        this.j = null;
        this.k = null;
        this.q = null;
        this.l = null;
        this.r = false;
        this.s = "uppay";
        this.m = jSONObject;
        this.c = context;
        this.g = com.unionpay.mobile.android.utils.i.a(jSONObject, "label");
        this.o = com.unionpay.mobile.android.utils.i.a(jSONObject, "placeholder");
        this.n = com.unionpay.mobile.android.utils.i.a(jSONObject, "tip");
        this.a = com.unionpay.mobile.android.utils.i.a(jSONObject, com.alipay.sdk.m.h.c.e);
        this.f = com.unionpay.mobile.android.utils.i.a(jSONObject, "value");
        this.b = com.unionpay.mobile.android.utils.i.a(jSONObject, "type");
        this.i = com.unionpay.mobile.android.utils.i.a(jSONObject, "regexp");
        String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "readonly");
        if (a2 != null && a2.equalsIgnoreCase("true")) {
            this.h = true;
        }
        this.r = com.unionpay.mobile.android.utils.i.a(jSONObject, "margin").length() > 0;
        this.s = str;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        setBackgroundColor(0);
        setOrientation(1);
        setPadding(2, 2, 2, 2);
        if (this.b.equalsIgnoreCase("string")) {
            a();
            return;
        }
        if (!a(this, this.g)) {
            TextView textView = new TextView(this.c);
            this.p = textView;
            textView.setTextSize(20.0f);
            this.p.setText("");
            this.p.setTextColor(this.d);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.leftMargin = com.unionpay.mobile.android.global.a.f;
            addView(this.p, layoutParams);
            String str2 = this.g;
            if (str2 != null && str2.length() != 0) {
                this.p.setText(this.g);
            }
            this.p.setVisibility(8);
        }
        a();
        if (b_()) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this.c);
        this.j = linearLayout;
        linearLayout.setBackgroundColor(-267336);
        addView(this.j, new LinearLayout.LayoutParams(-1, -2));
        TextView textView2 = new TextView(this.c);
        this.k = textView2;
        textView2.setTextSize(15.0f);
        this.k.setTextColor(this.e);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        int a3 = com.unionpay.mobile.android.utils.f.a(this.c, 10.0f);
        layoutParams2.rightMargin = a3;
        layoutParams2.leftMargin = a3;
        int a4 = com.unionpay.mobile.android.utils.f.a(this.c, 5.0f);
        layoutParams2.bottomMargin = a4;
        layoutParams2.topMargin = a4;
        this.j.addView(this.k, layoutParams2);
        String str3 = this.n;
        if (str3 == null || str3.length() <= 0) {
            this.j.setVisibility(8);
            this.q.setVisibility(8);
        } else {
            this.q.setVisibility(0);
            this.k.setText(this.n);
        }
    }

    public void a() {
        FrameLayout frameLayout = new FrameLayout(this.c);
        addView(frameLayout, new LinearLayout.LayoutParams(-1, -2));
        this.l = new RelativeLayout(this.c);
        frameLayout.addView(this.l, new FrameLayout.LayoutParams(-1, -2));
        ImageView imageView = new ImageView(this.c);
        this.q = imageView;
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.c).a(KV.EVENT_OPEN_ACCOUNT_MANAGER_ACTIVITY));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(com.unionpay.mobile.android.utils.f.a(this.c, 10.0f), com.unionpay.mobile.android.utils.f.a(this.c, 5.0f));
        layoutParams.gravity = 80;
        layoutParams.leftMargin = com.unionpay.mobile.android.utils.f.a(this.c, 20.0f);
        this.q.setVisibility(8);
        frameLayout.addView(this.q, layoutParams);
    }

    public void a(Context context, String str) {
        a(context, str, null, null);
    }

    public void a(Context context, String str, String[] strArr, Object[] objArr) {
        if (com.unionpay.mobile.android.global.a.L) {
            com.unionpay.mobile.android.utils.j.a("uppay-TD", "event:" + str + ", keys:" + Arrays.toString(strArr) + ", values:" + Arrays.toString(objArr));
            if (strArr == null || objArr == null) {
                UPAgent.onEvent(context, str);
                return;
            }
            if (strArr.length != objArr.length || strArr.length > 10) {
                throw new IllegalArgumentException();
            }
            HashMap hashMap = new HashMap();
            for (int i = 0; i < strArr.length; i++) {
                hashMap.put(strArr[i], objArr[i]);
            }
            UPAgent.onEvent(context, str, str, hashMap);
        }
    }

    protected final void a(CharSequence charSequence, TextView.BufferType bufferType) {
        if (this.p == null || charSequence == null || charSequence.length() <= 0) {
            return;
        }
        this.p.setText(charSequence, bufferType);
    }

    protected boolean a(LinearLayout linearLayout, String str) {
        return false;
    }

    public boolean a(String str) {
        return str == null || str.length() == 0;
    }

    protected boolean b_() {
        return false;
    }

    protected final void c(String str) {
        if (this.k == null || str == null || str.length() <= 0) {
            return;
        }
        this.k.setText(str);
    }

    protected String d() {
        return "_input_method";
    }

    public boolean f() {
        return true;
    }

    public String i() {
        return this.f;
    }

    public final String q() {
        return this.a;
    }

    public final String r() {
        return this.b;
    }

    public final String s() {
        return this.g;
    }

    public final String t() {
        return this.n;
    }

    public final String u() {
        return this.o;
    }

    protected final String v() {
        return this.s;
    }

    protected final void w() {
        TextView textView = this.p;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }

    protected final void x() {
        TextView textView = this.k;
        if (textView != null) {
            textView.setVisibility(0);
            this.q.setVisibility(0);
        }
    }

    protected final void y() {
        TextView textView = this.p;
        if (textView != null) {
            textView.setTextSize(16.0f);
        }
    }
}
