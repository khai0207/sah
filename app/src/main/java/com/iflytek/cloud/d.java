package com.iflytek.cloud;

import android.os.RemoteException;
import com.iflytek.speech.LexiconListener;

/* loaded from: classes.dex */
final class d extends LexiconListener.Stub {
    final /* synthetic */ LexiconListener a;
    final /* synthetic */ SpeechRecognizer b;

    d(SpeechRecognizer speechRecognizer, LexiconListener lexiconListener) {
        this.b = speechRecognizer;
        this.a = lexiconListener;
    }

    @Override // com.iflytek.speech.LexiconListener
    public void onLexiconUpdated(String str, int i) throws RemoteException {
        LexiconListener lexiconListener = this.a;
        if (lexiconListener != null) {
            lexiconListener.onLexiconUpdated(str, i == 0 ? null : new SpeechError(i));
        }
    }
}
