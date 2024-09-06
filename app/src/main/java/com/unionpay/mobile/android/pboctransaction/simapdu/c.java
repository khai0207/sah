package com.unionpay.mobile.android.pboctransaction.simapdu;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class c implements Handler.Callback {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        com.unionpay.mobile.android.pboctransaction.b bVar;
        com.unionpay.mobile.android.pboctransaction.b bVar2;
        com.unionpay.mobile.android.pboctransaction.b bVar3;
        com.unionpay.mobile.android.pboctransaction.b bVar4;
        int i = message.what;
        if (i == 1) {
            bVar = this.a.c;
            if (bVar != null) {
                bVar2 = this.a.c;
                bVar2.a();
                b.b(this.a);
            }
        } else if (i == 2) {
            bVar3 = this.a.c;
            if (bVar3 != null) {
                bVar4 = this.a.c;
                bVar4.b();
                b.b(this.a);
            }
        }
        return true;
    }
}
