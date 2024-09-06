package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class e implements View.OnClickListener {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ b b;

    e(b bVar, JSONObject jSONObject) {
        this.b = bVar;
        this.a = jSONObject;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.b.j();
        b bVar = this.b;
        bVar.b(bVar.a.aE, this.a);
    }
}
