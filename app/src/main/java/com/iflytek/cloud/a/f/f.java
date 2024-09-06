package com.iflytek.cloud.a.f;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;

/* loaded from: classes.dex */
final class f extends Handler {
    final /* synthetic */ c a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    f(c cVar, Looper looper) {
        super(looper);
        this.a = cVar;
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
        SynthesizerListener synthesizerListener9;
        try {
            synthesizerListener = this.a.l;
            if (synthesizerListener == null) {
                return;
            }
            switch (message.what) {
                case 1:
                    com.iflytek.cloud.a.g.a.a.a("tts-onSpeakBegin");
                    synthesizerListener2 = this.a.l;
                    synthesizerListener2.onSpeakBegin();
                    break;
                case 2:
                    Bundle bundle = (Bundle) message.obj;
                    int i = bundle.getInt("percent");
                    int i2 = bundle.getInt("begpos");
                    int i3 = bundle.getInt("endpos");
                    String string = bundle.getString("spellinfo");
                    synthesizerListener3 = this.a.l;
                    if (synthesizerListener3 != null) {
                        com.iflytek.cloud.a.g.a.a.a("tts-onBufferProgress");
                        synthesizerListener4 = this.a.l;
                        synthesizerListener4.onBufferProgress(i, i2, i3, string);
                        break;
                    }
                    break;
                case 3:
                    com.iflytek.cloud.a.g.a.a.a("tts-onSpeakPaused");
                    synthesizerListener5 = this.a.l;
                    synthesizerListener5.onSpeakPaused();
                    break;
                case 4:
                    com.iflytek.cloud.a.g.a.a.a("tts-onSpeakResumed");
                    synthesizerListener6 = this.a.l;
                    synthesizerListener6.onSpeakResumed();
                    break;
                case 5:
                    int intValue = ((Integer) message.obj).intValue();
                    synthesizerListener7 = this.a.l;
                    if (synthesizerListener7 != null) {
                        com.iflytek.cloud.a.g.a.a.a("tts-onSpeakProgress");
                        synthesizerListener8 = this.a.l;
                        synthesizerListener8.onSpeakProgress(message.arg1, message.arg2, intValue);
                        break;
                    }
                    break;
                case 6:
                    com.iflytek.cloud.a.g.a.a.a("tts-onCompleted");
                    synthesizerListener9 = this.a.l;
                    synthesizerListener9.onCompleted((SpeechError) message.obj);
                    break;
            }
        } catch (Exception e) {
            com.iflytek.cloud.a.g.a.a.b("SpeakSession mUiHandler error:" + e);
        }
    }
}
