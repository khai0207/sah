package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import com.unionpay.mobile.android.widgets.t;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class UPWidget extends z implements t.b {
    private static final int n = com.unionpay.mobile.android.global.a.t / 3;
    private static int r = 0;
    private long o;
    private boolean p;
    private String q;
    private boolean s;
    private ViewTreeObserver.OnGlobalLayoutListener t;

    /* renamed from: u, reason: collision with root package name */
    private ba f57u;
    private View.OnClickListener v;

    public UPWidget(Context context, long j, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.p = true;
        this.q = null;
        this.s = false;
        this.t = new av(this);
        this.f57u = null;
        this.v = new aw(this);
        this.o = j;
        this.b.a((t.b) this);
        this.b.a(new InputFilter.LengthFilter(6));
        this.b.f();
        this.b.d();
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View A() {
        return ((Activity) this.c).findViewById(8888);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void appendOnce(long j, String str);

    static /* synthetic */ void b(UPWidget uPWidget) {
        com.unionpay.mobile.android.utils.j.a("kb", "pwdInputFinished() +++");
        com.unionpay.mobile.android.utils.j.a("kb", "size = " + r);
        uPWidget.k();
        com.unionpay.mobile.android.utils.j.a("kb", "pwdInputFinished() ---");
    }

    private native void clearAll(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void deleteOnce(long j);

    private native String getMsg(long j);

    private native String getMsgExtra(long j, String str);

    static /* synthetic */ int o() {
        int i = r;
        r = i - 1;
        return i;
    }

    static /* synthetic */ int p() {
        int i = r;
        r = i + 1;
        return i;
    }

    private void z() {
        if (A() != null) {
            A().getViewTreeObserver().removeGlobalOnLayoutListener(this.t);
        }
        ba baVar = this.f57u;
        if (baVar == null || !baVar.b()) {
            return;
        }
        this.f57u.a();
    }

    @Override // com.unionpay.mobile.android.widgets.z, com.unionpay.mobile.android.widgets.az.a
    public final String a() {
        return this.p ? getMsgExtra(this.o, this.q) : getMsg(this.o);
    }

    public final void a(long j) {
        this.o = j;
    }

    @Override // com.unionpay.mobile.android.widgets.t.a
    public final void a(boolean z) {
        this.s = z;
        if (!z) {
            z();
            return;
        }
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.b.getWindowToken(), 0);
        int height = A().getRootView().getHeight() - A().getHeight();
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        if (height == rect.top) {
            l();
            return;
        }
        if (j()) {
            return;
        }
        com.unionpay.mobile.android.utils.j.a("uppay", "key board is closing..");
        com.unionpay.mobile.android.utils.j.a("uppay", "registerKeyboardDissmisslisner() +++");
        if (A() != null) {
            A().getViewTreeObserver().addOnGlobalLayoutListener(this.t);
        }
        com.unionpay.mobile.android.utils.j.a("uppay", "registerKeyboardDissmisslisner() ---");
    }

    @Override // com.unionpay.mobile.android.widgets.t.b
    public final void a_() {
        if (!this.s || j()) {
            return;
        }
        l();
    }

    public final void b(String str) {
        this.q = str;
    }

    public final void b(boolean z) {
        this.p = z;
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return r == 6;
    }

    @Override // com.unionpay.mobile.android.widgets.z, com.unionpay.mobile.android.widgets.az.a
    public final boolean c() {
        com.unionpay.mobile.android.utils.j.a("uppay", "emptyCheck() +++ ");
        com.unionpay.mobile.android.utils.j.a("uppay", "mPINCounts =  " + r);
        com.unionpay.mobile.android.utils.j.a("uppay", "emptyCheck() --- ");
        return r != 0;
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_bank_pwd";
    }

    @Override // com.unionpay.mobile.android.widgets.t.b
    public final void e() {
        if (r > 0) {
            clearAll(this.o);
            r = 0;
        }
    }

    public final boolean j() {
        ba baVar = this.f57u;
        return baVar != null && baVar.b();
    }

    public final void k() {
        com.unionpay.mobile.android.utils.j.a("uppay", "closeCustomKeyboard() +++");
        if (j()) {
            z();
        }
        com.unionpay.mobile.android.utils.j.a("uppay", "closeCustomKeyboard() ---");
    }

    public final void l() {
        if (!this.s || j()) {
            return;
        }
        ba baVar = new ba(getContext(), this.v, this);
        this.f57u = baVar;
        baVar.a(this);
        String str = "";
        for (int i = 0; i < r; i++) {
            str = str + "*";
        }
        this.b.c(str);
        this.b.b(str.length());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k();
    }
}
