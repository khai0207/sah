package com.iflytek.cloud;

import android.os.RemoteException;
import com.iflytek.speech.GrammarListener;

/* loaded from: classes.dex */
final class c extends GrammarListener.Stub {
    final /* synthetic */ GrammarListener a;
    final /* synthetic */ SpeechRecognizer b;

    c(SpeechRecognizer speechRecognizer, GrammarListener grammarListener) {
        this.b = speechRecognizer;
        this.a = grammarListener;
    }

    @Override // com.iflytek.speech.GrammarListener
    public void onBuildFinish(String str, int i) throws RemoteException {
        GrammarListener grammarListener = this.a;
        if (grammarListener != null) {
            grammarListener.onBuildFinish(str, i == 0 ? null : new SpeechError(i));
        }
    }
}
