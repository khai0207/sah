package com.unionpay.mobile.android.pro.views;

import android.text.TextUtils;
import android.view.View;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class c implements View.OnClickListener {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "errMsg");
        if (a != null && !TextUtils.isEmpty(a)) {
            this.a.a(a);
            return;
        }
        String a2 = com.unionpay.mobile.android.utils.i.a(jSONObject, "action");
        String a3 = com.unionpay.mobile.android.utils.i.a(jSONObject, "value");
        a.a(this.a, a2, a3 + ",\"carrier_tp\":\"9\"");
    }
}
