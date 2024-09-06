package com.iflytek.cloud.a.d;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.a.d.e;

/* loaded from: classes.dex */
final class f extends Handler {
    final /* synthetic */ e.a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    f(e.a aVar, Looper looper) {
        super(looper);
        this.a = aVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SpeechListener speechListener;
        SpeechListener speechListener2;
        SpeechListener speechListener3;
        SpeechListener speechListener4;
        speechListener = this.a.b;
        if (speechListener == null) {
            return;
        }
        com.iflytek.cloud.a.g.a.a.a("SpeechListener onMsg = " + message.what);
        int i = message.what;
        if (i == 0) {
            speechListener2 = this.a.b;
            speechListener2.onEvent(message.arg1, (Bundle) message.obj);
        } else if (i == 1) {
            speechListener3 = this.a.b;
            speechListener3.onBufferReceived((byte[]) message.obj);
        } else if (i == 2) {
            speechListener4 = this.a.b;
            speechListener4.onCompleted((SpeechError) message.obj);
        }
        super.handleMessage(message);
    }
}
