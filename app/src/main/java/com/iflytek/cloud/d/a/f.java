package com.iflytek.cloud.d.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.d.a.c;

/* loaded from: classes.dex */
final class f extends Handler {
    final /* synthetic */ c.a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    f(c.a aVar, Looper looper) {
        super(looper);
        this.a = aVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        RecognizerListener recognizerListener;
        RecognizerListener recognizerListener2;
        RecognizerListener recognizerListener3;
        RecognizerListener recognizerListener4;
        RecognizerListener recognizerListener5;
        RecognizerListener recognizerListener6;
        boolean z;
        RecognizerListener recognizerListener7;
        recognizerListener = this.a.b;
        if (recognizerListener == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            recognizerListener2 = this.a.b;
            recognizerListener2.onError((SpeechError) message.obj);
        } else if (i == 1) {
            recognizerListener3 = this.a.b;
            recognizerListener3.onVolumeChanged(message.arg1);
        } else if (i == 2) {
            recognizerListener4 = this.a.b;
            recognizerListener4.onBeginOfSpeech();
        } else if (i == 3) {
            recognizerListener5 = this.a.b;
            recognizerListener5.onEndOfSpeech();
        } else if (i == 4) {
            recognizerListener6 = this.a.b;
            recognizerListener6.onResult((RecognizerResult) message.obj, message.arg1 == 1);
            z = this.a.c;
            if (!z) {
                c.this.b("ui_frs");
                this.a.c = true;
            }
            if (1 == message.arg1) {
                c.this.b("ui_lrs");
            }
        } else if (i == 6) {
            Message message2 = (Message) message.obj;
            recognizerListener7 = this.a.b;
            recognizerListener7.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
        }
        super.handleMessage(message);
    }
}
