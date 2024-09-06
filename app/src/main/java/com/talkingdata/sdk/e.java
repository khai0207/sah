package com.talkingdata.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.talkingdata.sdk.zz;

/* compiled from: td */
/* loaded from: classes.dex */
final class e extends Handler {
    e(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        cr.b();
        co.b();
        if (message.obj == null || !(message.obj instanceof zz.a)) {
            return;
        }
        try {
            bk.a().post((zz.a) message.obj);
        } catch (Throwable unused) {
        }
    }
}
