package com.iflytek.cloud.d.a;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;

/* loaded from: classes.dex */
public class h {
    protected static h a;
    private c b;

    /* loaded from: classes.dex */
    private class a implements RecognizerListener {
        private final SpeechUnderstanderListener b;

        public a(SpeechUnderstanderListener speechUnderstanderListener) {
            this.b = speechUnderstanderListener;
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onBeginOfSpeech() {
            SpeechUnderstanderListener speechUnderstanderListener = this.b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onBeginOfSpeech();
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEndOfSpeech() {
            SpeechUnderstanderListener speechUnderstanderListener = this.b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onEndOfSpeech();
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onError(SpeechError speechError) {
            SpeechUnderstanderListener speechUnderstanderListener = this.b;
            if (speechUnderstanderListener == null || speechError == null) {
                return;
            }
            speechUnderstanderListener.onError(speechError);
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            SpeechUnderstanderListener speechUnderstanderListener = this.b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onEvent(i, i2, i3, bundle);
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onResult(RecognizerResult recognizerResult, boolean z) {
            SpeechUnderstanderListener speechUnderstanderListener = this.b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onResult(new UnderstanderResult(recognizerResult.getResultString()));
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onVolumeChanged(int i) {
            SpeechUnderstanderListener speechUnderstanderListener = this.b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onVolumeChanged(i);
            }
        }
    }

    public h(Context context) {
        this.b = null;
        this.b = new c(context);
    }

    public int a(SpeechUnderstanderListener speechUnderstanderListener) {
        a aVar = new a(speechUnderstanderListener);
        if (TextUtils.isEmpty(this.b.getParameter("asr_sch"))) {
            this.b.setParameter("asr_sch", "1");
        }
        if (TextUtils.isEmpty(this.b.getParameter(SpeechConstant.NLP_VERSION))) {
            this.b.setParameter(SpeechConstant.NLP_VERSION, "2.0");
        }
        if (TextUtils.isEmpty(this.b.getParameter(SpeechConstant.RESULT_TYPE))) {
            this.b.setParameter(SpeechConstant.RESULT_TYPE, "json");
        }
        this.b.a(aVar);
        return 0;
    }

    public int a(byte[] bArr, int i, int i2) {
        return this.b.a(bArr, i, i2);
    }

    public void a(boolean z) {
        this.b.cancel(z);
    }

    public boolean a() {
        return this.b.f();
    }

    public boolean a(com.iflytek.cloud.b.a aVar) {
        return this.b.setParameter(aVar);
    }

    public void b() {
        this.b.e();
    }

    public boolean c() {
        boolean destroy = this.b.destroy();
        if (destroy) {
            a = null;
        }
        return destroy;
    }
}
