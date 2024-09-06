package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.a.d.d;
import com.iflytek.msc.MSC;
import com.iflytek.speech.SpeechRecognizerAidl;

/* loaded from: classes.dex */
public final class SpeechRecognizer extends com.iflytek.cloud.a.d.d {
    private static SpeechRecognizer a;
    private com.iflytek.cloud.d.a.c c;
    private SpeechRecognizerAidl d;
    private InitListener f;
    private a e = null;
    private Handler g = new b(this, Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    final class a implements RecognizerListener {
        private RecognizerListener b;
        private com.iflytek.speech.RecognizerListener c;
        private Handler d = new f(this, Looper.getMainLooper());

        public a(RecognizerListener recognizerListener) {
            this.b = null;
            this.c = null;
            this.b = recognizerListener;
            this.c = new e(this, SpeechRecognizer.this);
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onBeginOfSpeech() {
            this.d.sendMessage(this.d.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEndOfSpeech() {
            this.d.sendMessage(this.d.obtainMessage(3, 0, 0, null));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onError(SpeechError speechError) {
            this.d.sendMessage(this.d.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            this.d.sendMessage(this.d.obtainMessage(6, 0, 0, message));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onResult(RecognizerResult recognizerResult, boolean z) {
            this.d.sendMessage(this.d.obtainMessage(4, !z ? 0 : 1, 0, recognizerResult));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onVolumeChanged(int i) {
            this.d.sendMessage(this.d.obtainMessage(1, i, 0, null));
        }
    }

    protected SpeechRecognizer(Context context, InitListener initListener) {
        this.c = null;
        this.d = null;
        this.f = null;
        this.f = initListener;
        if (MSC.isLoaded()) {
            this.c = new com.iflytek.cloud.d.a.c(context);
        }
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.a() && utility.getEngineMode() != d.a.MSC) {
            this.d = new SpeechRecognizerAidl(context.getApplicationContext(), initListener);
        } else if (initListener != null) {
            Message.obtain(this.g, 0, 0, 0, null).sendToTarget();
        }
    }

    public static synchronized SpeechRecognizer createRecognizer(Context context, InitListener initListener) {
        SpeechRecognizer speechRecognizer;
        synchronized (SpeechRecognizer.class) {
            if (a == null) {
                a = new SpeechRecognizer(context, initListener);
            }
            speechRecognizer = a;
        }
        return speechRecognizer;
    }

    public static SpeechRecognizer getRecognizer() {
        return a;
    }

    protected void a(Context context) {
        SpeechRecognizerAidl speechRecognizerAidl;
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility == null || !utility.a() || utility.getEngineMode() == d.a.MSC) {
            if (this.f == null || (speechRecognizerAidl = this.d) == null) {
                return;
            }
            speechRecognizerAidl.destory();
            this.d = null;
            return;
        }
        SpeechRecognizerAidl speechRecognizerAidl2 = this.d;
        if (speechRecognizerAidl2 != null && !speechRecognizerAidl2.isAvailable()) {
            this.d.destory();
            this.d = null;
        }
        this.d = new SpeechRecognizerAidl(context.getApplicationContext(), this.f);
    }

    public int buildGrammar(String str, String str2, GrammarListener grammarListener) {
        d.a a2 = a("asr", this.d);
        com.iflytek.cloud.a.g.a.a.a("start engine mode = " + a2.toString());
        if (a2 != d.a.PLUS) {
            com.iflytek.cloud.d.a.c cVar = this.c;
            if (cVar == null) {
                return 21001;
            }
            cVar.setParameter(this.b);
            return this.c.a(str, str2, grammarListener);
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.d;
        if (speechRecognizerAidl == null) {
            return 21001;
        }
        speechRecognizerAidl.setParameter("params", null);
        this.d.setParameter("params", this.b.toString());
        return this.d.buildGrammar(str, str2, new c(this, grammarListener));
    }

    public void cancel() {
        com.iflytek.cloud.d.a.c cVar = this.c;
        if (cVar != null && cVar.f()) {
            this.c.cancel(false);
            return;
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.d;
        if (speechRecognizerAidl == null || !speechRecognizerAidl.isListening()) {
            com.iflytek.cloud.a.g.a.a.b("SpeechRecognizer cancel failed, is not running");
        } else {
            this.d.cancel(this.e.c);
        }
    }

    public boolean destroy() {
        SpeechRecognizerAidl speechRecognizerAidl = this.d;
        if (speechRecognizerAidl != null) {
            speechRecognizerAidl.destory();
            this.d = null;
        }
        com.iflytek.cloud.d.a.c cVar = this.c;
        boolean destroy = cVar != null ? cVar.destroy() : true;
        if (destroy) {
            a = null;
        }
        return destroy;
    }

    @Override // com.iflytek.cloud.a.d.d
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public boolean isListening() {
        com.iflytek.cloud.d.a.c cVar = this.c;
        if (cVar != null && cVar.f()) {
            return true;
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.d;
        return speechRecognizerAidl != null && speechRecognizerAidl.isListening();
    }

    @Override // com.iflytek.cloud.a.d.d
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int startListening(RecognizerListener recognizerListener) {
        d.a a2 = a("asr", this.d);
        com.iflytek.cloud.a.g.a.a.a("start engine mode = " + a2.toString());
        if (a2 != d.a.PLUS) {
            com.iflytek.cloud.d.a.c cVar = this.c;
            if (cVar == null) {
                return 21001;
            }
            cVar.setParameter(this.b);
            return this.c.a(recognizerListener);
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.d;
        if (speechRecognizerAidl == null) {
            return 21001;
        }
        speechRecognizerAidl.setParameter("params", null);
        this.d.setParameter("params", this.b.toString());
        a aVar = new a(recognizerListener);
        this.e = aVar;
        return this.d.startListening(aVar.c);
    }

    public void stopListening() {
        com.iflytek.cloud.d.a.c cVar = this.c;
        if (cVar != null && cVar.f()) {
            this.c.e();
            return;
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.d;
        if (speechRecognizerAidl == null || !speechRecognizerAidl.isListening()) {
            com.iflytek.cloud.a.g.a.a.b("SpeechRecognizer stopListening failed, is not running");
        } else {
            this.d.stopListening(this.e.c);
        }
    }

    public int updateLexicon(String str, String str2, LexiconListener lexiconListener) {
        d.a a2 = a("asr", this.d);
        com.iflytek.cloud.a.g.a.a.a("start engine mode = " + a2.toString());
        if (a2 != d.a.PLUS) {
            com.iflytek.cloud.d.a.c cVar = this.c;
            if (cVar == null) {
                return 21001;
            }
            cVar.setParameter(this.b);
            return this.c.a(str, str2, lexiconListener);
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.d;
        if (speechRecognizerAidl == null) {
            return 21001;
        }
        speechRecognizerAidl.setParameter("params", null);
        this.d.setParameter("params", this.b.toString());
        return this.d.updateLexicon(str, str2, new d(this, lexiconListener));
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        com.iflytek.cloud.d.a.c cVar = this.c;
        if (cVar != null && cVar.f()) {
            return this.c.a(bArr, i, i2);
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.d;
        if (speechRecognizerAidl != null && speechRecognizerAidl.isListening()) {
            return this.d.writeAudio(bArr, i, i2);
        }
        com.iflytek.cloud.a.g.a.a.b("SpeechRecognizer writeAudio failed, is not running");
        return ErrorCode.ERROR_ENGINE_CALL_FAIL;
    }
}
