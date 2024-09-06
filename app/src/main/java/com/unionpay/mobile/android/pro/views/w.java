package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import com.unionpay.uppay.PayActivity;

/* loaded from: classes.dex */
public final class w extends x {
    public w(Context context, com.unionpay.mobile.android.model.e eVar) {
        super(context, eVar);
    }

    @Override // com.unionpay.mobile.android.pro.views.x
    public final com.unionpay.mobile.android.pro.pboc.engine.b u() {
        Object a = ((PayActivity) this.d).a(com.unionpay.mobile.android.pro.pboc.engine.b.class.toString());
        if (a != null) {
            return (com.unionpay.mobile.android.pro.pboc.engine.b) a;
        }
        return null;
    }
}
