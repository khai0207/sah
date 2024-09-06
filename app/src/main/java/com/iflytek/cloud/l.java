package com.iflytek.cloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechUnderstander;

/* loaded from: classes.dex */
final class l extends Handler {
    final /* synthetic */ SpeechUnderstander.a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    l(SpeechUnderstander.a aVar, Looper looper) {
        super(looper);
        this.a = aVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SpeechUnderstanderListener speechUnderstanderListener;
        SpeechUnderstanderListener speechUnderstanderListener2;
        SpeechUnderstanderListener speechUnderstanderListener3;
        SpeechUnderstanderListener speechUnderstanderListener4;
        SpeechUnderstanderListener speechUnderstanderListener5;
        SpeechUnderstanderListener speechUnderstanderListener6;
        SpeechUnderstanderListener speechUnderstanderListener7;
        speechUnderstanderListener = this.a.b;
        if (speechUnderstanderListener == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            speechUnderstanderListener2 = this.a.b;
            speechUnderstanderListener2.onError((SpeechError) message.obj);
        } else if (i == 1) {
            speechUnderstanderListener3 = this.a.b;
            speechUnderstanderListener3.onVolumeChanged(message.arg1);
        } else if (i == 2) {
            speechUnderstanderListener4 = this.a.b;
            speechUnderstanderListener4.onBeginOfSpeech();
        } else if (i == 3) {
            speechUnderstanderListener5 = this.a.b;
            speechUnderstanderListener5.onEndOfSpeech();
        } else if (i == 4) {
            speechUnderstanderListener6 = this.a.b;
            speechUnderstanderListener6.onResult((UnderstanderResult) message.obj);
        } else if (i == 6) {
            Message message2 = (Message) message.obj;
            speechUnderstanderListener7 = this.a.b;
            speechUnderstanderListener7.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
        }
        super.handleMessage(message);
    }
}
