package com.unionpay.mobile.android.nocard.views;

import android.text.TextUtils;
import android.view.View;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class av implements View.OnClickListener {
    final /* synthetic */ at a;

    av(at atVar) {
        this.a = atVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a = com.unionpay.mobile.android.utils.i.a(jSONObject, "errMsg");
        if (a != null && !TextUtils.isEmpty(a)) {
            this.a.a(a);
            return;
        }
        at.a(this.a, com.unionpay.mobile.android.utils.i.a(jSONObject, "action"), com.unionpay.mobile.android.utils.i.a(jSONObject, "value"));
    }
}
