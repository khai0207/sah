package com.unionpay.mobile.android.pro.pboc.engine;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class d implements com.unionpay.mobile.android.pboctransaction.b {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.b
    public final void a() {
        b.a(this.a, 2);
    }

    @Override // com.unionpay.mobile.android.pboctransaction.b
    public final void b() {
        Handler handler;
        Handler handler2;
        handler = this.a.h;
        Message obtainMessage = handler.obtainMessage(2, null);
        handler2 = this.a.h;
        handler2.sendMessage(obtainMessage);
    }
}
