package com.unionpay.mobile.android.pro.pboc.engine;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class i implements Runnable {
    final /* synthetic */ b a;

    i(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.unionpay.mobile.android.pboctransaction.d dVar;
        Handler handler;
        Handler handler2;
        Handler handler3;
        synchronized (this.a) {
            com.unionpay.mobile.android.utils.j.c("UPCardEngine", " ic_return : 4");
            dVar = this.a.t;
            ArrayList<com.unionpay.mobile.android.model.c> b = dVar.b();
            handler = this.a.h;
            if (handler != null) {
                handler2 = this.a.h;
                Message obtainMessage = handler2.obtainMessage(4, b);
                handler3 = this.a.h;
                handler3.sendMessage(obtainMessage);
            }
        }
    }
}
