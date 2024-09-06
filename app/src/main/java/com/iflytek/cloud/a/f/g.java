package com.iflytek.cloud.a.f;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;

/* loaded from: classes.dex */
final class g extends Handler {
    final /* synthetic */ c a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    g(c cVar, Looper looper) {
        super(looper);
        this.a = cVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SynthesizerListener synthesizerListener;
        SynthesizerListener synthesizerListener2;
        SynthesizerListener synthesizerListener3;
        Message message2;
        SynthesizerListener synthesizerListener4;
        try {
            synthesizerListener = this.a.m;
            if (synthesizerListener == null) {
                return;
            }
            int i = message.what;
            if (i == 2) {
                Bundle bundle = (Bundle) message.obj;
                int i2 = bundle.getInt("percent");
                int i3 = bundle.getInt("begpos");
                int i4 = bundle.getInt("endpos");
                String string = bundle.getString("spellinfo");
                synthesizerListener2 = this.a.m;
                synthesizerListener2.onBufferProgress(i2, i3, i4, string);
            } else if (i == 6) {
                synthesizerListener3 = this.a.m;
                synthesizerListener3.onCompleted((SpeechError) message.obj);
            } else if (i == 7 && (message2 = (Message) message.obj) != null) {
                synthesizerListener4 = this.a.m;
                synthesizerListener4.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
            }
        } catch (Exception e) {
            com.iflytek.cloud.a.g.a.a.b("SpeakSession mUiHandler error:" + e);
        }
    }
}
