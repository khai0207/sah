package com.iflytek.cloud.d.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.VerifierListener;
import com.iflytek.cloud.VerifierResult;
import com.iflytek.cloud.d.a.a;

/* loaded from: classes.dex */
final class b extends Handler {
    final /* synthetic */ a.C0018a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    b(a.C0018a c0018a, Looper looper) {
        super(looper);
        this.a = c0018a;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        VerifierListener verifierListener;
        VerifierListener verifierListener2;
        VerifierListener verifierListener3;
        VerifierListener verifierListener4;
        VerifierListener verifierListener5;
        VerifierListener verifierListener6;
        verifierListener = this.a.b;
        if (verifierListener == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            verifierListener2 = this.a.b;
            verifierListener2.onError((SpeechError) message.obj);
        } else if (i == 1) {
            verifierListener3 = this.a.b;
            verifierListener3.onVolumeChanged(message.arg1);
        } else if (i == 2) {
            verifierListener4 = this.a.b;
            verifierListener4.onBeginOfSpeech();
        } else if (i == 3) {
            verifierListener5 = this.a.b;
            verifierListener5.onEndOfSpeech();
        } else if (i == 4) {
            verifierListener6 = this.a.b;
            verifierListener6.onResult((VerifierResult) message.obj);
        }
        super.handleMessage(message);
    }
}
