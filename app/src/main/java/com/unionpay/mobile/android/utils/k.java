package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.os.Handler;
import org.simalliance.openmobileapi.SEService;

/* loaded from: classes.dex */
public final class k implements SEService.CallBack {
    private static SEService b;
    private Context a;
    private com.unionpay.mobile.android.nocard.views.b c;
    private Handler.Callback d = new l(this);
    private Handler e = new Handler(this.d);

    public k() {
    }

    public k(Context context, com.unionpay.mobile.android.nocard.views.b bVar) {
        this.a = context;
        this.c = bVar;
        if (b != null) {
            ((com.unionpay.mobile.android.nocard.views.l) bVar).u();
            return;
        }
        try {
            b = new SEService(this.a, this);
            new m(this).start();
        } catch (Throwable th) {
            th.printStackTrace();
            j.c("uppay", " service ERROR!!!");
            this.e.sendEmptyMessage(2);
        }
    }

    public static SEService a() {
        return b;
    }

    public final void serviceConnected(SEService sEService) {
        j.c("uppay", "se service connected");
        j.c("uppay", "mSEService:" + b);
        j.c("uppay", "mSEService.isConnected:" + b.isConnected());
        this.e.sendEmptyMessage(1);
    }
}
