package com.iflytek.cloud.a.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.a.b.a;

/* loaded from: classes.dex */
final class b extends Handler {
    final /* synthetic */ a.C0016a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    b(a.C0016a c0016a, Looper looper) {
        super(looper);
        this.a = c0016a;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SpeechListener speechListener;
        SpeechListener speechListener2;
        SpeechListener speechListener3;
        SpeechListener speechListener4;
        speechListener = this.a.a;
        if (speechListener == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            speechListener2 = this.a.a;
            speechListener2.onEvent(message.arg1, (Bundle) message.obj);
        } else if (i == 1) {
            speechListener3 = this.a.a;
            speechListener3.onBufferReceived((byte[]) message.obj);
        } else {
            if (i != 2) {
                return;
            }
            speechListener4 = this.a.a;
            speechListener4.onCompleted((SpeechError) message.obj);
        }
    }
}
