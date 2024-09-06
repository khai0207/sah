package com.iflytek.cloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechSynthesizer;

/* loaded from: classes.dex */
final class i extends Handler {
    final /* synthetic */ SpeechSynthesizer.a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    i(SpeechSynthesizer.a aVar, Looper looper) {
        super(looper);
        this.a = aVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SynthesizerListener synthesizerListener;
        SynthesizerListener synthesizerListener2;
        SynthesizerListener synthesizerListener3;
        SynthesizerListener synthesizerListener4;
        SynthesizerListener synthesizerListener5;
        SynthesizerListener synthesizerListener6;
        SynthesizerListener synthesizerListener7;
        SynthesizerListener synthesizerListener8;
        synthesizerListener = this.a.b;
        if (synthesizerListener == null) {
            return;
        }
        switch (message.what) {
            case 1:
                synthesizerListener2 = this.a.b;
                synthesizerListener2.onSpeakBegin();
                return;
            case 2:
                Bundle bundle = (Bundle) message.obj;
                int i = bundle.getInt("percent");
                int i2 = bundle.getInt("begpos");
                int i3 = bundle.getInt("endpos");
                String string = bundle.getString("spellinfo");
                synthesizerListener3 = this.a.b;
                synthesizerListener3.onBufferProgress(i, i2, i3, string);
                return;
            case 3:
                synthesizerListener4 = this.a.b;
                synthesizerListener4.onSpeakPaused();
                return;
            case 4:
                synthesizerListener5 = this.a.b;
                synthesizerListener5.onSpeakResumed();
                return;
            case 5:
                int intValue = ((Integer) message.obj).intValue();
                synthesizerListener6 = this.a.b;
                synthesizerListener6.onSpeakProgress(message.arg1, message.arg2, intValue);
                return;
            case 6:
                synthesizerListener7 = this.a.b;
                synthesizerListener7.onCompleted((SpeechError) message.obj);
                return;
            case 7:
                Message message2 = (Message) message.obj;
                if (message2 != null) {
                    synthesizerListener8 = this.a.b;
                    synthesizerListener8.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
