package com.unionpay.mobile.android.nocard.views;

import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
final class ap implements Handler.Callback {
    final /* synthetic */ ao a;

    ap(ao aoVar) {
        this.a = aoVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        LinearLayout linearLayout;
        Map<String, Object> b;
        if (this.a.a.aK != com.unionpay.mobile.android.views.order.l.e.intValue()) {
            return true;
        }
        this.a.r.remove(new HashMap());
        if (com.unionpay.mobile.android.model.b.aW == null || com.unionpay.mobile.android.model.b.aW.size() <= 0) {
            this.a.n();
            this.a.a.aK = com.unionpay.mobile.android.views.order.l.a.intValue();
            this.a.a(com.unionpay.mobile.android.languages.c.bD.bq, this.a.a.bb, this.a.a.az);
            return true;
        }
        int size = com.unionpay.mobile.android.model.b.aW.size();
        for (int i = 0; i < size; i++) {
            com.unionpay.mobile.android.model.d dVar = com.unionpay.mobile.android.model.b.aW.get(i);
            b = ao.b(dVar);
            this.a.r.add(b);
            if (i == 0) {
                this.a.w.b(dVar.b() + dVar.c() + " " + b.get("text2"));
            }
        }
        this.a.w.setVisibility(0);
        linearLayout = this.a.x;
        linearLayout.setVisibility(8);
        return true;
    }
}
