package com.iflytek.cloud.d.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import com.iflytek.cloud.d.a.j;

/* loaded from: classes.dex */
final class k extends Handler {
    final /* synthetic */ j.a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    k(j.a aVar, Looper looper) {
        super(looper);
        this.a = aVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        WakeuperListener wakeuperListener;
        WakeuperListener wakeuperListener2;
        WakeuperListener wakeuperListener3;
        WakeuperListener wakeuperListener4;
        Message message2;
        WakeuperListener wakeuperListener5;
        wakeuperListener = this.a.b;
        if (wakeuperListener == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            wakeuperListener2 = this.a.b;
            wakeuperListener2.onError((SpeechError) message.obj);
        } else if (i == 2) {
            wakeuperListener3 = this.a.b;
            wakeuperListener3.onBeginOfSpeech();
        } else if (i == 4) {
            wakeuperListener4 = this.a.b;
            wakeuperListener4.onResult((WakeuperResult) message.obj);
        } else if (i == 6 && (message2 = (Message) message.obj) != null) {
            wakeuperListener5 = this.a.b;
            wakeuperListener5.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
        }
        super.handleMessage(message);
    }
}
