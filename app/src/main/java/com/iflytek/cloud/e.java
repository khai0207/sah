package com.iflytek.cloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.speech.RecognizerListener;

/* loaded from: classes.dex */
final class e extends RecognizerListener.Stub {
    final /* synthetic */ SpeechRecognizer a;
    final /* synthetic */ SpeechRecognizer.a b;

    e(SpeechRecognizer.a aVar, SpeechRecognizer speechRecognizer) {
        this.b = aVar;
        this.a = speechRecognizer;
    }

    @Override // com.iflytek.speech.RecognizerListener
    public void onBeginOfSpeech() throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(2);
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.RecognizerListener
    public void onEndOfSpeech() throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(3);
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.RecognizerListener
    public void onError(int i) throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(0, new SpeechError(i));
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.RecognizerListener
    public void onEvent(int i, int i2, int i3, Bundle bundle) throws RemoteException {
        Handler handler;
        Handler handler2;
        Message message = new Message();
        message.what = i;
        message.arg1 = i2;
        message.arg2 = i3;
        message.obj = bundle;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(6, 0, 0, message);
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.RecognizerListener
    public void onResult(com.iflytek.speech.RecognizerResult recognizerResult, boolean z) throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(4, !z ? 0 : 1, 0, new RecognizerResult(recognizerResult.getResultString()));
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.RecognizerListener
    public void onVolumeChanged(int i) throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(1, i, 0, null);
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }
}
