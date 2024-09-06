package com.unionpay.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
final class ac extends Handler {
    ac(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message.obj == null || !(message.obj instanceof ap)) {
            return;
        }
        ai.a().post((ap) message.obj);
    }
}
