package com.unionpay.mobile.android.nocard.views;

import android.text.TextUtils;
import android.view.View;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class p implements View.OnClickListener {
    final /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "errMsg");
        if (a != null && !TextUtils.isEmpty(a)) {
            this.a.a(a);
            return;
        }
        o.a(this.a, com.unionpay.mobile.android.utils.i.a(jSONObject, "action"), com.unionpay.mobile.android.utils.i.a(jSONObject, "value"));
    }
}
