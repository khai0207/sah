package com.unionpay.mobile.android.views.order;

import android.view.View;
import com.unionpay.mobile.android.views.order.AbstractMethod;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class f implements View.OnClickListener {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        if (this.a.e != null) {
            b.a(this.a.b, "bankpay_support_bank");
            AbstractMethod.b bVar = this.a.e;
            jSONObject = this.a.g;
            String a = b.a(jSONObject, com.alipay.sdk.m.s.d.v);
            jSONObject2 = this.a.g;
            bVar.a(a, b.a(jSONObject2, "href"));
        }
    }
}
