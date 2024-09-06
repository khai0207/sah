package com.unionpay.mobile.android.views.order;

import android.view.View;
import com.unionpay.mobile.android.views.order.AbstractMethod;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class j implements View.OnClickListener {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        if (this.a.e != null) {
            i.a(this.a.b, "login_forget_pwd");
            AbstractMethod.b bVar = this.a.e;
            jSONObject = this.a.g;
            String a = i.a(jSONObject, "label");
            jSONObject2 = this.a.g;
            bVar.a(a, i.a(jSONObject2, "href"));
        }
    }
}
