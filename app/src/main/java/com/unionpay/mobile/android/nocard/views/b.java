package com.unionpay.mobile.android.nocard.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.iflytek.cloud.SpeechConstant;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.widgets.UPWidget;
import com.unionpay.mobile.android.widgets.ax;
import com.unionpay.mobile.android.widgets.z;
import com.unionpay.sdk.UPAgent;
import com.unionpay.tsmservice.data.Constant;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class b extends RelativeLayout implements UPPayEngine.a, a, UPScrollView.a, ax.a, z.a {
    protected com.unionpay.mobile.android.model.b a;
    protected com.unionpay.mobile.android.widgets.m b;
    protected com.unionpay.mobile.android.resource.c c;
    protected Context d;
    protected UPPayEngine e;
    protected int f;
    protected String g;
    protected String h;
    protected String i;
    protected boolean j;
    protected RelativeLayout k;
    protected ViewGroup l;
    protected RelativeLayout m;
    protected com.unionpay.mobile.android.widgets.ar n;
    protected UPScrollView o;
    protected com.unionpay.mobile.android.model.e p;
    protected String q;
    private LinearLayout r;
    private LinearLayout s;
    private LinearLayout t;

    /* renamed from: u */
    private int f44u;
    private int v;
    private Activity w;

    public b(Context context) {
        this(context, null);
    }

    public b(Context context, com.unionpay.mobile.android.model.e eVar) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = true;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.q = "uppay";
        this.w = null;
        this.f = 0;
        this.d = context;
        if (context instanceof Activity) {
            this.w = (Activity) context;
        }
        this.p = eVar;
        BaseActivity baseActivity = (BaseActivity) context;
        this.e = (UPPayEngine) baseActivity.a(UPPayEngine.class.toString());
        this.a = (com.unionpay.mobile.android.model.b) baseActivity.a((String) null);
        this.b = (com.unionpay.mobile.android.widgets.m) baseActivity.a(com.unionpay.mobile.android.widgets.m.class.toString());
        this.c = com.unionpay.mobile.android.resource.c.a(context);
        setId(8888);
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        setBackgroundColor(-1);
        com.unionpay.mobile.android.utils.j.b("uppayEx", "UPViewBase:" + toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(int r4, int r5, boolean r6, boolean r7) {
        /*
            r3 = this;
            android.content.Context r0 = r3.d
            com.unionpay.mobile.android.plugin.BaseActivity r0 = (com.unionpay.mobile.android.plugin.BaseActivity) r0
            r1 = 2
            r2 = 0
            if (r4 == r1) goto L98
            r1 = 8
            if (r4 == r1) goto L90
            r1 = 5
            if (r4 == r1) goto L88
            r1 = 6
            if (r4 == r1) goto L4a
            r1 = 17
            if (r4 == r1) goto L98
            r1 = 18
            if (r4 == r1) goto L98
            switch(r4) {
                case 10: goto L42;
                case 11: goto L3a;
                case 12: goto L32;
                case 13: goto L28;
                case 14: goto L1f;
                default: goto L1d;
            }
        L1d:
            goto L9c
        L1f:
            com.unionpay.mobile.android.nocard.views.bi r2 = new com.unionpay.mobile.android.nocard.views.bi
            android.content.Context r4 = r3.d
            r2.<init>(r4, r5, r6, r7)
            goto L9c
        L28:
            com.unionpay.mobile.android.nocard.views.o r4 = new com.unionpay.mobile.android.nocard.views.o
            android.content.Context r5 = r3.d
            r4.<init>(r5, r2)
            r2 = r4
            goto L9c
        L32:
            com.unionpay.mobile.android.nocard.views.af r2 = new com.unionpay.mobile.android.nocard.views.af
            android.content.Context r4 = r3.d
            r2.<init>(r4)
            goto L9c
        L3a:
            com.unionpay.mobile.android.nocard.views.ai r2 = new com.unionpay.mobile.android.nocard.views.ai
            android.content.Context r4 = r3.d
            r2.<init>(r4)
            goto L9c
        L42:
            com.unionpay.mobile.android.nocard.views.ak r2 = new com.unionpay.mobile.android.nocard.views.ak
            android.content.Context r4 = r3.d
            r2.<init>(r4)
            goto L9c
        L4a:
            r4 = 0
            com.unionpay.mobile.android.model.b r5 = r3.a
            java.util.List<com.unionpay.mobile.android.model.c> r5 = r5.l
            if (r5 == 0) goto L6d
            com.unionpay.mobile.android.model.b r5 = r3.a
            java.util.List<com.unionpay.mobile.android.model.c> r5 = r5.l
            int r5 = r5.size()
            if (r5 <= 0) goto L6d
            com.unionpay.mobile.android.model.b r4 = r3.a
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.l
            com.unionpay.mobile.android.model.b r5 = r3.a
            int r5 = r5.I
            java.lang.Object r4 = r4.get(r5)
            com.unionpay.mobile.android.model.c r4 = (com.unionpay.mobile.android.model.c) r4
            int r4 = r4.c()
        L6d:
            boolean r5 = r3.i()
            if (r5 != 0) goto L75
            if (r4 != 0) goto L83
        L75:
            com.unionpay.mobile.android.model.b r4 = r3.a
            boolean r4 = r4.bl
            if (r4 != 0) goto L83
            com.unionpay.mobile.android.nocard.views.at r2 = new com.unionpay.mobile.android.nocard.views.at
            android.content.Context r4 = r3.d
            r2.<init>(r4)
            goto L9c
        L83:
            com.unionpay.mobile.android.nocard.views.b r2 = r0.a(r1, r2)
            goto L9c
        L88:
            com.unionpay.mobile.android.nocard.views.g r2 = new com.unionpay.mobile.android.nocard.views.g
            android.content.Context r4 = r3.d
            r2.<init>(r4)
            goto L9c
        L90:
            com.unionpay.mobile.android.nocard.views.bd r2 = new com.unionpay.mobile.android.nocard.views.bd
            android.content.Context r4 = r3.d
            r2.<init>(r4)
            goto L9c
        L98:
            com.unionpay.mobile.android.nocard.views.b r2 = r0.a(r4, r2)
        L9c:
            if (r2 == 0) goto La1
            r0.a(r2)
        La1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.b.a(int, int, boolean, boolean):void");
    }

    public static void a(Context context, String str) {
        a(context, str, (String[]) null, (Object[]) null);
    }

    public static void a(Context context, String str, String[] strArr, Object[] objArr) {
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

    protected static boolean b(String str) {
        return str != null && str.length() > 0;
    }

    protected static ColorStateList p() {
        return com.unionpay.mobile.android.utils.g.a(com.unionpay.mobile.android.global.b.b, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.d);
    }

    private final RelativeLayout t() {
        LinearLayout linearLayout;
        int i;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        RelativeLayout relativeLayout = this.k;
        if (relativeLayout != null) {
            layoutParams.addRule(3, relativeLayout.getId());
            layoutParams.addRule(12, -1);
        }
        FrameLayout frameLayout = new FrameLayout(this.d);
        addView(frameLayout, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        UPScrollView uPScrollView = new UPScrollView(this.d);
        this.o = uPScrollView;
        uPScrollView.setPadding(0, 0, 0, 0);
        frameLayout.addView(this.o, layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        int a = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        this.t = linearLayout2;
        linearLayout2.setId(linearLayout2.hashCode());
        this.t.setOrientation(1);
        if (!this.a.aH || com.unionpay.mobile.android.model.b.bg) {
            linearLayout = this.t;
            i = -267336;
        } else {
            linearLayout = this.t;
            i = -34177;
        }
        linearLayout.setBackgroundColor(i);
        this.t.setPadding(a, a, a, a);
        String str = "";
        if (b(this.a.am)) {
            str = "" + this.a.am;
        }
        if (b(str)) {
            TextView textView = new TextView(this.d);
            if (!this.a.aH || com.unionpay.mobile.android.model.b.bg) {
                textView.setTextColor(-10066330);
            } else {
                textView.setTextColor(-1);
            }
            textView.setText(str);
            textView.setTextSize(com.unionpay.mobile.android.global.b.k);
            this.t.addView(textView);
        } else {
            this.t.setVisibility(8);
        }
        this.t.setVisibility(8);
        frameLayout.addView(this.t, layoutParams3);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.d);
        relativeLayout2.setBackgroundColor(-1052684);
        this.o.addView(relativeLayout2, new RelativeLayout.LayoutParams(-1, -1));
        return relativeLayout2;
    }

    protected final RelativeLayout a() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setId(relativeLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10, -1);
        addView(relativeLayout, layoutParams);
        return relativeLayout;
    }

    protected final com.unionpay.mobile.android.widgets.y a(JSONObject jSONObject, String str) {
        String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "type");
        int i = com.unionpay.mobile.android.global.a.I - (com.unionpay.mobile.android.global.a.f * 4);
        com.unionpay.mobile.android.widgets.y aeVar = Constant.KEY_PAN.equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.ae(this.d, i, jSONObject, str) : "mobile".equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.ag(this.d, i, jSONObject, str) : "sms".equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.ao(this.d, i, jSONObject, str) : Constant.KEY_CVN2.equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.e(this.d, i, jSONObject, str) : "expire".equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.au(this.d, i, jSONObject, str) : "pwd".equalsIgnoreCase(a) ? new UPWidget(this.d, this.e.c(), i, jSONObject, str) : SpeechConstant.TEXT.equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.as(this.d, i, jSONObject, str) : "string".equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.ac(this.d, jSONObject, str) : "cert_id".equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.f(this.d, i, jSONObject, str) : "cert_type".equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.g(this.d, jSONObject, str) : com.alipay.sdk.m.h.c.e.equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.ad(this.d, i, jSONObject, str) : "hidden".equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.x(this.d, jSONObject, str) : "user_name".equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.at(this.d, i, jSONObject, str) : "password".equalsIgnoreCase(a) ? new com.unionpay.mobile.android.widgets.an(this.d, i, jSONObject, str) : null;
        if (aeVar != null && (aeVar instanceof com.unionpay.mobile.android.widgets.z)) {
            ((com.unionpay.mobile.android.widgets.z) aeVar).a((z.a) this);
        }
        return aeVar;
    }

    public final void a(int i) {
        ((BaseActivity) this.d).a(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void a(int r4, com.unionpay.mobile.android.model.e r5) {
        /*
            r3 = this;
            android.content.Context r0 = r3.d
            com.unionpay.mobile.android.plugin.BaseActivity r0 = (com.unionpay.mobile.android.plugin.BaseActivity) r0
            r1 = 2
            if (r4 == r1) goto La0
            r1 = 8
            if (r4 == r1) goto L98
            r1 = 18
            if (r4 == r1) goto La0
            r1 = 5
            if (r4 == r1) goto L90
            r1 = 6
            if (r4 == r1) goto L46
            switch(r4) {
                case 10: goto L3e;
                case 11: goto L36;
                case 12: goto L2d;
                case 13: goto L24;
                case 14: goto L1b;
                default: goto L18;
            }
        L18:
            r4 = 0
            goto La4
        L1b:
            com.unionpay.mobile.android.nocard.views.bi r4 = new com.unionpay.mobile.android.nocard.views.bi
            android.content.Context r5 = r3.d
            r4.<init>(r5)
            goto La4
        L24:
            com.unionpay.mobile.android.nocard.views.o r4 = new com.unionpay.mobile.android.nocard.views.o
            android.content.Context r1 = r3.d
            r4.<init>(r1, r5)
            goto La4
        L2d:
            com.unionpay.mobile.android.nocard.views.af r4 = new com.unionpay.mobile.android.nocard.views.af
            android.content.Context r5 = r3.d
            r4.<init>(r5)
            goto La4
        L36:
            com.unionpay.mobile.android.nocard.views.ai r4 = new com.unionpay.mobile.android.nocard.views.ai
            android.content.Context r5 = r3.d
            r4.<init>(r5)
            goto La4
        L3e:
            com.unionpay.mobile.android.nocard.views.ak r4 = new com.unionpay.mobile.android.nocard.views.ak
            android.content.Context r5 = r3.d
            r4.<init>(r5)
            goto La4
        L46:
            r4 = 0
            com.unionpay.mobile.android.model.b r2 = r3.a
            java.util.List<com.unionpay.mobile.android.model.c> r2 = r2.l
            if (r2 == 0) goto L69
            com.unionpay.mobile.android.model.b r2 = r3.a
            java.util.List<com.unionpay.mobile.android.model.c> r2 = r2.l
            int r2 = r2.size()
            if (r2 <= 0) goto L69
            com.unionpay.mobile.android.model.b r4 = r3.a
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.l
            com.unionpay.mobile.android.model.b r2 = r3.a
            int r2 = r2.I
            java.lang.Object r4 = r4.get(r2)
            com.unionpay.mobile.android.model.c r4 = (com.unionpay.mobile.android.model.c) r4
            int r4 = r4.c()
        L69:
            boolean r2 = r3.i()
            if (r2 != 0) goto L7d
            if (r4 == 0) goto L7d
            com.unionpay.mobile.android.model.b r4 = r3.a
            int r4 = r4.aK
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.c
            int r2 = r2.intValue()
            if (r4 != r2) goto L8b
        L7d:
            com.unionpay.mobile.android.model.b r4 = r3.a
            boolean r4 = r4.bl
            if (r4 != 0) goto L8b
            com.unionpay.mobile.android.nocard.views.at r4 = new com.unionpay.mobile.android.nocard.views.at
            android.content.Context r1 = r3.d
            r4.<init>(r1, r5)
            goto La4
        L8b:
            com.unionpay.mobile.android.nocard.views.b r4 = r0.a(r1, r5)
            goto La4
        L90:
            com.unionpay.mobile.android.nocard.views.g r4 = new com.unionpay.mobile.android.nocard.views.g
            android.content.Context r5 = r3.d
            r4.<init>(r5)
            goto La4
        L98:
            com.unionpay.mobile.android.nocard.views.bd r4 = new com.unionpay.mobile.android.nocard.views.bd
            android.content.Context r5 = r3.d
            r4.<init>(r5)
            goto La4
        La0:
            com.unionpay.mobile.android.nocard.views.b r4 = r0.a(r4, r5)
        La4:
            if (r4 == 0) goto La9
            r0.a(r4)
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.b.a(int, com.unionpay.mobile.android.model.e):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0125  */
    @Override // com.unionpay.mobile.android.nocard.utils.UPPayEngine.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(int r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.b.a(int, java.lang.String):void");
    }

    @Override // com.unionpay.mobile.android.widgets.z.a
    public final void a(com.unionpay.mobile.android.widgets.t tVar, String str) {
    }

    public final void a(String str) {
        a(str, false);
    }

    protected final void a(String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.b.a(onClickListener, onClickListener2);
        this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, str, com.unionpay.mobile.android.languages.c.bD.W, com.unionpay.mobile.android.languages.c.bD.X, false);
    }

    public final void a(String str, String str2) {
        a(str, str2, 0, false, false);
    }

    protected final void a(String str, String str2, int i, boolean z, boolean z2) {
        ((InputMethodManager) this.d.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        this.a.ab = str2;
        this.a.aa = str;
        a(14, i, z, z2);
    }

    protected final void a(String str, String str2, boolean z) {
        a(str, str2, 0, z, true);
    }

    public final void a(String str, boolean z) {
        d dVar = new d(this, z);
        com.unionpay.mobile.android.utils.j.a("uppay", " showErrDialog(msg, boolean)  ");
        this.b.a(dVar, null);
        this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, str, com.unionpay.mobile.android.languages.c.bD.W);
    }

    protected boolean a(String str, JSONObject jSONObject) {
        return false;
    }

    protected void b() {
    }

    public void b(int i) {
        String c;
        boolean z;
        if (i == 8 || i == 17 || i == 19) {
            this.a.D.f = Constant.CASH_LOAD_FAIL;
            com.unionpay.mobile.android.utils.j.a("uppay", "showErrDialog 1");
            c = c(i);
            z = true;
        } else {
            com.unionpay.mobile.android.utils.j.a("uppay", "showErrDialog 2");
            c = c(i);
            z = false;
        }
        a(c, z);
    }

    protected void b(String str, JSONObject jSONObject) {
    }

    protected final boolean b(JSONObject jSONObject) {
        if (!com.unionpay.mobile.android.nocard.utils.f.c(this.a, jSONObject)) {
            return false;
        }
        c(jSONObject);
        return true;
    }

    protected final String c(int i) {
        switch (i) {
            case 2:
                return com.unionpay.mobile.android.languages.c.bD.aB;
            case 3:
                break;
            case 4:
                return com.unionpay.mobile.android.languages.c.bD.az;
            case 5:
                return com.unionpay.mobile.android.languages.c.bD.aH;
            case 6:
                return com.unionpay.mobile.android.languages.c.bD.aI;
            case 7:
                return com.unionpay.mobile.android.languages.c.bD.aG;
            case 8:
                return com.unionpay.mobile.android.languages.c.bD.aJ;
            case 9:
                return com.unionpay.mobile.android.languages.c.bD.aK;
            default:
                switch (i) {
                    case 16:
                        return com.unionpay.mobile.android.languages.c.bD.aM;
                    case 17:
                        break;
                    case 18:
                        return com.unionpay.mobile.android.languages.c.bD.aP;
                    case 19:
                        return com.unionpay.mobile.android.languages.c.bD.aN;
                    case 20:
                        return com.unionpay.mobile.android.languages.c.bD.aO;
                    case 21:
                        return com.unionpay.mobile.android.languages.c.bD.aL;
                    default:
                        return com.unionpay.mobile.android.languages.c.bD.aA;
                }
        }
        return this.h;
    }

    protected void c() {
    }

    protected final void c(JSONObject jSONObject) {
        this.b.a(new e(this, jSONObject), new f(this, jSONObject));
        this.b.a(this.a.aB, this.a.aC, this.a.aD, this.a.aF);
    }

    protected void d() {
        this.m = t();
    }

    public final void d(int i) {
        a(i, 0, false, false);
    }

    protected final void e() {
        this.k = a();
        b();
        RelativeLayout t = t();
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-1114114);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10, -1);
        t.addView(linearLayout, layoutParams);
        this.l = linearLayout;
        linearLayout.setBackgroundColor(0);
        f();
        int id = this.l.getId();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(12, -1);
        layoutParams2.addRule(3, id);
        RelativeLayout relativeLayout = new RelativeLayout(this.d);
        t.addView(relativeLayout, layoutParams2);
        this.m = relativeLayout;
        c();
    }

    @Override // com.unionpay.mobile.android.upwidget.UPScrollView.a
    public final void e(int i) {
        LinearLayout linearLayout;
        int i2 = this.v;
        if (i >= i2) {
            if (this.t.getVisibility() == 0 || this.t == null || this.r.getVisibility() != 0) {
                return;
            }
            this.t.setVisibility(0);
            return;
        }
        if (i > i2 + this.f44u || this.t.getVisibility() != 0 || (linearLayout = this.t) == null) {
            return;
        }
        linearLayout.setVisibility(8);
    }

    protected void f() {
        String str;
        LinearLayout linearLayout = new LinearLayout(this.d);
        this.s = linearLayout;
        linearLayout.setOrientation(1);
        if (!this.a.aH || com.unionpay.mobile.android.model.b.bg) {
            this.s.setBackgroundColor(-267336);
        } else {
            this.s.setBackgroundColor(-34177);
        }
        int a = com.unionpay.mobile.android.utils.f.a(this.d, 10.0f);
        if (b(this.a.am)) {
            this.s.setPadding(a, a, a, 0);
        } else {
            this.s.setPadding(a, a, a, a);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = 0;
        this.l.addView(this.s, layoutParams);
        String str2 = "";
        if (b(this.a.ao)) {
            str = "" + this.a.ao;
        } else {
            str = "";
        }
        if (b(str)) {
            TextView textView = new TextView(this.d);
            if (!this.a.aH || com.unionpay.mobile.android.model.b.bg) {
                textView.setTextColor(-10066330);
            } else {
                textView.setTextColor(-1);
            }
            textView.setText(str);
            textView.setTextSize(com.unionpay.mobile.android.global.b.k);
            this.s.addView(textView);
        } else {
            this.s.setVisibility(8);
        }
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        this.r = linearLayout2;
        linearLayout2.setOrientation(1);
        if (!this.a.aH || com.unionpay.mobile.android.model.b.bg) {
            this.r.setBackgroundColor(-267336);
        } else {
            this.r.setBackgroundColor(-34177);
        }
        this.r.setPadding(a, a, a, a);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = 0;
        this.l.addView(this.r, layoutParams2);
        if (b(this.a.am)) {
            str2 = "" + this.a.am;
        }
        if (b(str2)) {
            TextView textView2 = new TextView(this.d);
            if (!this.a.aH || com.unionpay.mobile.android.model.b.bg) {
                textView2.setTextColor(-10066330);
            } else {
                textView2.setTextColor(-1);
            }
            textView2.setText(str2);
            textView2.setTextSize(com.unionpay.mobile.android.global.b.k);
            this.r.addView(textView2);
        } else {
            this.r.setVisibility(8);
        }
        this.r.getViewTreeObserver().addOnPreDrawListener(new c(this));
        com.unionpay.mobile.android.views.order.m mVar = new com.unionpay.mobile.android.views.order.m(this.d);
        mVar.a(this.c.a(1003), this.c.a(1001));
        mVar.a(true ^ (this instanceof ao), this.a.e, this.a.f);
        this.l.addView(mVar, new LinearLayout.LayoutParams(-1, -2));
        Drawable a2 = this.c.a(1026);
        LinearLayout linearLayout3 = new LinearLayout(this.d);
        if (a2 != null) {
            linearLayout3.setBackgroundDrawable(a2);
        }
        this.l.addView(linearLayout3, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.utils.f.a(this.d, 2.0f)));
    }

    protected final void g() {
    }

    public final int h() {
        return this.f;
    }

    protected final boolean i() {
        return this.a.E || this.a.l == null || this.a.l.size() == 0;
    }

    public final void j() {
        com.unionpay.mobile.android.widgets.m mVar = this.b;
        if (mVar == null || !mVar.a()) {
            return;
        }
        this.b.c();
    }

    public final void k() {
        com.unionpay.mobile.android.nocard.utils.d.a(this.d, this.a);
    }

    public void l() {
        if (this.j) {
            n();
        }
    }

    @Override // com.unionpay.mobile.android.widgets.ax.a
    public final void m() {
        l();
    }

    public final void n() {
        ((BaseActivity) this.d).b();
    }

    public final boolean o() {
        com.unionpay.mobile.android.widgets.m mVar = this.b;
        boolean z = mVar != null && mVar.a();
        com.unionpay.mobile.android.utils.j.a("uppay", " dialog showing:" + z);
        return z;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        com.unionpay.mobile.android.utils.j.b("uppayEx", toString() + " onAttachedToWindow()");
        super.onAttachedToWindow();
        this.e.a(this);
    }

    protected final boolean q() {
        return !this.a.c;
    }

    public final void r() {
        if (!com.unionpay.mobile.android.global.a.L || TextUtils.isEmpty(this.q) || this.w == null) {
            return;
        }
        com.unionpay.mobile.android.utils.j.a("uppay-TD", "page start: " + this.q + "_View");
        UPAgent.onPageStart(this.w, this.q + "_View");
    }

    public final void s() {
        if (!com.unionpay.mobile.android.global.a.L || TextUtils.isEmpty(this.q) || this.w == null) {
            return;
        }
        com.unionpay.mobile.android.utils.j.a("uppay-TD", "page end: " + this.q + "_View");
        UPAgent.onPageEnd(this.w, this.q + "_View");
    }
}
