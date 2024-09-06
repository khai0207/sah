package com.iflytek.cloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.iflytek.cloud.SpeechUnderstander;
import com.iflytek.speech.SpeechUnderstanderListener;

/* loaded from: classes.dex */
final class k extends SpeechUnderstanderListener.Stub {
    final /* synthetic */ SpeechUnderstander a;
    final /* synthetic */ SpeechUnderstander.a b;

    k(SpeechUnderstander.a aVar, SpeechUnderstander speechUnderstander) {
        this.b = aVar;
        this.a = speechUnderstander;
    }

    @Override // com.iflytek.speech.SpeechUnderstanderListener
    public void onBeginOfSpeech() throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(2);
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.SpeechUnderstanderListener
    public void onEndOfSpeech() throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(3);
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.SpeechUnderstanderListener
    public void onError(int i) throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(0, new SpeechError(i));
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.SpeechUnderstanderListener
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

    @Override // com.iflytek.speech.SpeechUnderstanderListener
    public void onResult(com.iflytek.speech.UnderstanderResult understanderResult) throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(4, new UnderstanderResult(understanderResult.getResultString()));
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.SpeechUnderstanderListener
    public void onVolumeChanged(int i) throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(1, i, 0, null);
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }
}
