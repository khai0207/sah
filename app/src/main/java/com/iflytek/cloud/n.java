package com.iflytek.cloud;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.iflytek.cloud.TextUnderstander;
import com.iflytek.speech.TextUnderstanderListener;

/* loaded from: classes.dex */
final class n extends TextUnderstanderListener.Stub {
    final /* synthetic */ TextUnderstander a;
    final /* synthetic */ TextUnderstander.a b;

    n(TextUnderstander.a aVar, TextUnderstander textUnderstander) {
        this.b = aVar;
        this.a = textUnderstander;
    }

    @Override // com.iflytek.speech.TextUnderstanderListener
    public void onError(int i) throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(0, new SpeechError(i));
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }

    @Override // com.iflytek.speech.TextUnderstanderListener
    public void onResult(com.iflytek.speech.UnderstanderResult understanderResult) throws RemoteException {
        Handler handler;
        Handler handler2;
        handler = this.b.d;
        Message obtainMessage = handler.obtainMessage(4, new UnderstanderResult(understanderResult.getResultString()));
        handler2 = this.b.d;
        handler2.sendMessage(obtainMessage);
    }
}
