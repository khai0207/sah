package com.talkingdata.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: td */
/* loaded from: classes.dex */
class cq extends Handler {
    final /* synthetic */ co a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    cq(co coVar, Looper looper) {
        super(looper);
        this.a = coVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            if (message.what == 5 && message.obj != null && (message.obj instanceof a)) {
                this.a.a((a) message.obj);
            }
            this.a.a();
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }
}
