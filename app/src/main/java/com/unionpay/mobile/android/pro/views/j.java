package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import com.unionpay.uppay.PayActivity;

/* loaded from: classes.dex */
public final class j extends h {
    public j(Context context) {
        super(context);
    }

    @Override // com.unionpay.mobile.android.pro.views.h
    public final com.unionpay.mobile.android.pro.pboc.engine.b C() {
        Object a = ((PayActivity) this.d).a(com.unionpay.mobile.android.pro.pboc.engine.b.class.toString());
        if (a != null) {
            return (com.unionpay.mobile.android.pro.pboc.engine.b) a;
        }
        return null;
    }

    @Override // com.unionpay.mobile.android.nocard.views.l
    public final void a(int i, int i2, String str, String str2, int i3, String str3) {
        Object a = ((PayActivity) this.d).a(com.unionpay.mobile.android.hce.f.class.toString());
        if (a != null) {
            com.unionpay.mobile.android.hce.f fVar = (com.unionpay.mobile.android.hce.f) a;
            fVar.a();
            fVar.a(i);
            fVar.b(i2);
            fVar.a(str);
            fVar.b(str2);
            fVar.c(str3);
            fVar.c(i3);
            fVar.c();
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.l
    public final boolean v() {
        return true;
    }

    @Override // com.unionpay.mobile.android.nocard.views.l
    public final boolean w() {
        return true;
    }
}
