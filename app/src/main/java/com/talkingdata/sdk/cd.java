package com.talkingdata.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: td */
/* loaded from: classes.dex */
class cd extends Handler {
    final /* synthetic */ cc a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    cd(cc ccVar, Looper looper) {
        super(looper);
        this.a = ccVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.a.b();
            return;
        }
        if (i == 2) {
            this.a.c();
            return;
        }
        if (i == 3) {
            this.a.d();
        } else {
            if (i != 4) {
                return;
            }
            this.a.d();
            this.a.b();
            this.a.a(2, 600000L);
        }
    }
}
