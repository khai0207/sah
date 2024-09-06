package com.iflytek.cloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.speech.SynthesizerListener;

/* loaded from: classes.dex */
final class h extends SynthesizerListener.Stub {
    final /* synthetic */ SpeechSynthesizer a;
    final /* synthetic */ SpeechSynthesizer.a b;

    h(SpeechSynthesizer.a aVar, SpeechSynthesizer speechSynthesizer) {
        this.b = aVar;
        this.a = speechSynthesizer;
    }

    @Override // com.iflytek.speech.SynthesizerListener
    public void onBufferProgress(int i, int i2, int i3, String str) throws RemoteException {
        SynthesizerListener synthesizerListener;
        SynthesizerListener synthesizerListener2;
        Handler handler;
        synthesizerListener = this.b.b;
        if (synthesizerListener != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("percent", i);
            bundle.putInt("begpos", i2);
            bundle.putInt("endpos", i3);
            bundle.putString("spellinfo", "");
            synthesizerListener2 = this.b.b;
            if (synthesizerListener2 != null) {
                handler = this.b.d;
                Message.obtain(handler, 2, bundle).sendToTarget();
            }
        }
    }

    @Override // com.iflytek.speech.SynthesizerListener
    public void onCompleted(int i) throws RemoteException {
        SynthesizerListener synthesizerListener;
        Handler handler;
        synthesizerListener = this.b.b;
        if (synthesizerListener != null) {
            handler = this.b.d;
            Message.obtain(handler, 6, i == 0 ? null : new SpeechError(i)).sendToTarget();
        }
    }

    @Override // com.iflytek.speech.SynthesizerListener
    public void onEvent(int i, int i2, int i3, Bundle bundle) throws RemoteException {
        SynthesizerListener synthesizerListener;
        Handler handler;
        synthesizerListener = this.b.b;
        if (synthesizerListener != null) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 0;
            obtain.arg2 = 0;
            obtain.obj = bundle;
            handler = this.b.d;
            Message.obtain(handler, 7, 0, 0, obtain).sendToTarget();
        }
    }

    @Override // com.iflytek.speech.SynthesizerListener
    public void onSpeakBegin() throws RemoteException {
        SynthesizerListener synthesizerListener;
        Handler handler;
        synthesizerListener = this.b.b;
        if (synthesizerListener != null) {
            handler = this.b.d;
            Message.obtain(handler, 1).sendToTarget();
        }
    }

    @Override // com.iflytek.speech.SynthesizerListener
    public void onSpeakPaused() throws RemoteException {
        SynthesizerListener synthesizerListener;
        Handler handler;
        synthesizerListener = this.b.b;
        if (synthesizerListener != null) {
            handler = this.b.d;
            Message.obtain(handler, 3).sendToTarget();
        }
    }

    @Override // com.iflytek.speech.SynthesizerListener
    public void onSpeakProgress(int i, int i2, int i3) throws RemoteException {
        SynthesizerListener synthesizerListener;
        Handler handler;
        synthesizerListener = this.b.b;
        if (synthesizerListener != null) {
            handler = this.b.d;
            Message.obtain(handler, 5, i, i2, Integer.valueOf(i3)).sendToTarget();
        }
    }

    @Override // com.iflytek.speech.SynthesizerListener
    public void onSpeakResumed() throws RemoteException {
        SynthesizerListener synthesizerListener;
        Handler handler;
        synthesizerListener = this.b.b;
        if (synthesizerListener != null) {
            handler = this.b.d;
            Message.obtain(handler, 4, 0, 0, null).sendToTarget();
        }
    }
}
