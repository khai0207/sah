package com.unionpay.mobile.android.pboctransaction.samsung;

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
        if (message.what == 1) {
            this.a.a(false);
        }
        return true;
    }
}
