package com.unionpay.mobile.android.pro.views;

import android.text.TextUtils;
import android.view.View;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class z implements View.OnClickListener {
    final /* synthetic */ x a;

    z(x xVar) {
        this.a = xVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "errMsg");
        if (a != null && !TextUtils.isEmpty(a)) {
            this.a.a(a);
            return;
        }
        x.a(this.a, com.unionpay.mobile.android.utils.i.a(jSONObject, "action"), com.unionpay.mobile.android.utils.i.a(jSONObject, "value"));
    }
}
