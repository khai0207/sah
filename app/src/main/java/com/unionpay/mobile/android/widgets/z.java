package com.unionpay.mobile.android.widgets;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class z extends y {
    protected int a;
    protected t b;
    private a n;

    /* loaded from: classes.dex */
    public interface a {
        void a(t tVar, String str);
    }

    public z(Context context, int i, JSONObject jSONObject, String str) {
        this(context, i, jSONObject, str, (byte) 0);
    }

    public z(Context context, int i, JSONObject jSONObject, String str, byte b) {
        super(context, jSONObject, str);
        t tVar;
        String str2;
        t tVar2;
        com.unionpay.mobile.android.resource.c a2;
        int i2;
        this.b = null;
        this.n = null;
        this.a = i;
        com.unionpay.mobile.android.resource.c.a(this.c);
        this.b = new t(getContext());
        if (this.h) {
            this.b.a();
            this.b.d();
        }
        this.b.c(i());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
        layoutParams.addRule(15, -1);
        this.l.addView(this.b, layoutParams);
        this.b.b(com.unionpay.mobile.android.utils.i.a(jSONObject, "placeholder"));
        this.b.setFocusable(true);
        this.b.a(new aa(this));
        this.b.a(new ab(this));
        this.b.a(com.unionpay.mobile.android.resource.c.a(this.c).a(2000, -1, com.unionpay.mobile.android.global.a.v));
        if (!(this instanceof ae)) {
            if (this instanceof ao) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.aR;
            } else if (this instanceof ag) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.aT;
            } else if (this instanceof UPWidget) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.aS;
            } else if (this instanceof at) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.aU;
            } else if (this instanceof an) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.aV;
            } else if (this instanceof e) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.aW;
            } else if (this instanceof ad) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.aX;
            } else if (this instanceof bc) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.aY;
            } else if (this instanceof as) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.aZ;
            } else if (this instanceof au) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.ba;
            } else if (this instanceof f) {
                tVar = this.b;
                str2 = com.unionpay.mobile.android.languages.c.bD.bb;
            }
            tVar.a(str2);
        } else if (this.h) {
            this.b.c(this.g + " " + this.f);
        } else {
            tVar = this.b;
            str2 = com.unionpay.mobile.android.languages.c.bD.aQ;
            tVar.a(str2);
        }
        if (this instanceof k) {
            tVar2 = this.b;
            a2 = com.unionpay.mobile.android.resource.c.a(this.c);
            i2 = 1011;
        } else {
            tVar2 = this.b;
            a2 = com.unionpay.mobile.android.resource.c.a(this.c);
            i2 = 1013;
        }
        tVar2.setBackgroundDrawable(a2.a(i2));
    }

    public String a() {
        return this.b.b();
    }

    public void a(Editable editable) {
    }

    public final void a(a aVar) {
        this.n = aVar;
    }

    protected final boolean a(View view) {
        if (view == null) {
            throw null;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        Log.e("uppay", "v getGlobalVisibleRect():" + rect.toString());
        Rect rect2 = new Rect();
        ((Activity) this.c).getWindow().getDecorView().findViewById(R.id.content).getGlobalVisibleRect(rect2);
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        Log.e("uppay", " locationW = [" + iArr[0] + "," + iArr[1] + "]");
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        Log.e("uppay", " locationS = [" + iArr2[0] + "," + iArr2[1] + "]");
        boolean z = (iArr[1] + view.getHeight()) + 10 > rect2.bottom;
        View findViewById = ((Activity) this.c).getWindow().getDecorView().findViewById(R.id.content);
        Rect rect3 = new Rect();
        findViewById.getLocalVisibleRect(rect3);
        Log.e("uppay", " getLocalVisibleRect = " + rect3.toString());
        Rect rect4 = new Rect();
        findViewById.getGlobalVisibleRect(rect4);
        Log.e("uppay", " getGlobalVisibleRect = " + rect4.toString());
        return z;
    }

    public final boolean a(t tVar) {
        return tVar != null && this.b == tVar;
    }

    public boolean c() {
        return (a() == null || a().length() == 0) ? false : true;
    }

    public final void g() {
        if (this.b == null || this.h) {
            return;
        }
        this.b.e();
    }
}
