package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.os.Handler;
import com.unionpay.mobile.android.nocard.views.ao;
import com.unionpay.uppay.PayActivity;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class u extends ao {
    public u(Context context, com.unionpay.mobile.android.model.e eVar) {
        super(context, eVar);
    }

    @Override // com.unionpay.mobile.android.nocard.views.ao
    protected final void a(Handler handler) {
        Object a = ((PayActivity) this.d).a(com.unionpay.mobile.android.hce.f.class.toString());
        if (a != null) {
            ((com.unionpay.mobile.android.hce.f) a).a(handler);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.ao
    protected final void c(String str, String str2) {
        if (com.unionpay.mobile.android.model.b.bh) {
            a(this.a.ak, false);
            return;
        }
        Object a = ((PayActivity) this.d).a(com.unionpay.mobile.android.pro.pboc.engine.b.class.toString());
        if (a != null) {
            ((com.unionpay.mobile.android.pro.pboc.engine.b) a).a(new Handler(new v(this)), str, str2);
        }
    }

    @Override // com.unionpay.mobile.android.nocard.views.ao
    protected final boolean v() {
        return true;
    }

    @Override // com.unionpay.mobile.android.nocard.views.ao
    protected final void w() {
        if (com.unionpay.mobile.android.model.b.aW != null) {
            Iterator<com.unionpay.mobile.android.model.d> it = com.unionpay.mobile.android.model.b.aW.iterator();
            while (it.hasNext()) {
                try {
                    this.d.unbindService(((com.unionpay.mobile.android.hce.c) it.next()).h());
                } catch (IllegalArgumentException unused) {
                }
            }
        }
    }
}
