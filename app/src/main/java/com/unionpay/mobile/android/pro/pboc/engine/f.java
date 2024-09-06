package com.unionpay.mobile.android.pro.pboc.engine;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class f implements com.unionpay.mobile.android.pboctransaction.b {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.b
    public final void a() {
        com.unionpay.mobile.android.pboctransaction.samsung.f fVar;
        com.unionpay.mobile.android.pboctransaction.samsung.f fVar2;
        com.unionpay.mobile.android.utils.j.c("uppay-spay", "tsmservice  init success");
        com.unionpay.mobile.android.model.b.bh = false;
        fVar = this.a.y;
        if (fVar != null) {
            fVar2 = this.a.y;
            com.unionpay.mobile.android.model.b.aw = fVar2.e();
        }
        b.a(this.a, 8);
    }

    @Override // com.unionpay.mobile.android.pboctransaction.b
    public final void b() {
        Handler handler;
        Handler handler2;
        Handler handler3;
        com.unionpay.mobile.android.utils.j.c("UPCardEngine", "mSE init failed");
        com.unionpay.mobile.android.utils.j.c("uppay-spay", "tsmservice  init fail");
        handler = this.a.h;
        if (handler != null) {
            handler2 = this.a.h;
            Message obtainMessage = handler2.obtainMessage(8, null);
            handler3 = this.a.h;
            handler3.sendMessage(obtainMessage);
        }
    }
}
