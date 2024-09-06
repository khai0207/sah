package com.unionpay.mobile.android.pro.views;

import android.text.TextUtils;
import android.view.View;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class m implements View.OnClickListener {
    final /* synthetic */ k a;

    m(k kVar) {
        this.a = kVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "errMsg");
        if (a != null && !TextUtils.isEmpty(a)) {
            this.a.a(a);
            return;
        }
        k.a(this.a, com.unionpay.mobile.android.utils.i.a(jSONObject, "action"), com.unionpay.mobile.android.utils.i.a(jSONObject, "value"));
    }
}
