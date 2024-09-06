package com.unionpay.mobile.android.pro.views;

import java.util.ArrayList;

/* loaded from: classes.dex */
final class i implements com.unionpay.mobile.android.pro.pboc.engine.a {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    @Override // com.unionpay.mobile.android.pro.pboc.engine.a
    public final void a(ArrayList<com.unionpay.mobile.android.model.c> arrayList) {
        com.unionpay.mobile.android.utils.j.a("uppay", "deviceReady +++");
        if (arrayList != null && arrayList.size() > 0) {
            if (this.a.s == null) {
                this.a.s = new ArrayList(arrayList.size());
            }
            this.a.s.addAll(arrayList);
        }
        this.a.z();
        com.unionpay.mobile.android.utils.j.a("uppay", "deviceReady ---");
    }
}
