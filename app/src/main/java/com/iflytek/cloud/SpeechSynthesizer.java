package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.a.d.d;
import com.iflytek.msc.MSC;
import com.iflytek.speech.SpeechSynthesizerAidl;

/* loaded from: classes.dex */
public class SpeechSynthesizer extends com.iflytek.cloud.a.d.d {
    private static SpeechSynthesizer c;
    InitListener a;
    private com.iflytek.cloud.d.a.g d;
    private SpeechSynthesizerAidl e;
    private a f = null;
    private Handler g = new g(this, Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    final class a implements SynthesizerListener {
        private SynthesizerListener b;
        private com.iflytek.speech.SynthesizerListener c;
        private Handler d = new i(this, Looper.getMainLooper());

        public a(SynthesizerListener synthesizerListener) {
            this.b = null;
            this.c = null;
            this.b = synthesizerListener;
            this.c = new h(this, SpeechSynthesizer.this);
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onBufferProgress(int i, int i2, int i3, String str) {
            if (this.b != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("percent", i);
                bundle.putInt("begpos", i2);
                bundle.putInt("endpos", i3);
                bundle.putString("spellinfo", str);
                if (this.b != null) {
                    Message.obtain(this.d, 2, bundle).sendToTarget();
                }
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onCompleted(SpeechError speechError) {
            if (this.b != null) {
                Message.obtain(this.d, 6, speechError).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            if (this.b != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.arg1 = 0;
                obtain.arg2 = 0;
                obtain.obj = bundle;
                Message.obtain(this.d, 7, 0, 0, obtain).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onSpeakBegin() {
            if (this.b != null) {
                Message.obtain(this.d, 1).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onSpeakPaused() {
            if (this.b != null) {
                Message.obtain(this.d, 3).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onSpeakProgress(int i, int i2, int i3) {
            if (this.b != null) {
                Message.obtain(this.d, 5, i, i2, Integer.valueOf(i3)).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onSpeakResumed() {
            if (this.b != null) {
                Message.obtain(this.d, 4).sendToTarget();
            }
        }
    }

    protected SpeechSynthesizer(Context context, InitListener initListener) {
        this.d = null;
        this.e = null;
        this.a = null;
        this.a = initListener;
        if (MSC.isLoaded()) {
            this.d = new com.iflytek.cloud.d.a.g(context);
        }
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility == null || !utility.a() || utility.getEngineMode() == d.a.MSC) {
            Message.obtain(this.g, 0, 0, 0, null).sendToTarget();
        } else {
            this.e = new SpeechSynthesizerAidl(context.getApplicationContext(), initListener);
        }
    }

    public static SpeechSynthesizer createSynthesizer(Context context, InitListener initListener) {
        if (c == null) {
            c = new SpeechSynthesizer(context, initListener);
        }
        return c;
    }

    public static SpeechSynthesizer getSynthesizer() {
        return c;
    }

    protected void a(Context context) {
        SpeechSynthesizerAidl speechSynthesizerAidl;
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility == null || !utility.a() || utility.getEngineMode() == d.a.MSC) {
            if (this.a == null || (speechSynthesizerAidl = this.e) == null) {
                return;
            }
            speechSynthesizerAidl.destory();
            this.e = null;
            return;
        }
        SpeechSynthesizerAidl speechSynthesizerAidl2 = this.e;
        if (speechSynthesizerAidl2 != null && !speechSynthesizerAidl2.isAvailable()) {
            this.e.destory();
            this.e = null;
        }
        this.e = new SpeechSynthesizerAidl(context.getApplicationContext(), this.a);
    }

    public boolean destroy() {
        SpeechSynthesizerAidl speechSynthesizerAidl = this.e;
        if (speechSynthesizerAidl != null) {
            speechSynthesizerAidl.destory();
        }
        com.iflytek.cloud.d.a.g gVar = this.d;
        boolean destroy = gVar != null ? gVar.destroy() : true;
        if (destroy) {
            c = null;
        }
        return destroy;
    }

    @Override // com.iflytek.cloud.a.d.d
    public String getParameter(String str) {
        SpeechSynthesizerAidl speechSynthesizerAidl;
        SpeechSynthesizerAidl speechSynthesizerAidl2;
        if (SpeechConstant.LOCAL_SPEAKERS.equals(str) && (speechSynthesizerAidl2 = this.e) != null) {
            return speechSynthesizerAidl2.getParameter(str);
        }
        if (SpeechConstant.TTS_PLAY_STATE.equals(str)) {
            if (a("tts", this.e) == d.a.PLUS && (speechSynthesizerAidl = this.e) != null) {
                return speechSynthesizerAidl.getParameter(str);
            }
            if (this.d != null) {
                return "" + this.d.h();
            }
        }
        return super.getParameter(str);
    }

    public boolean isSpeaking() {
        com.iflytek.cloud.d.a.g gVar = this.d;
        if (gVar != null && gVar.g()) {
            return true;
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.e;
        return speechSynthesizerAidl != null && speechSynthesizerAidl.isSpeaking();
    }

    public void pauseSpeaking() {
        com.iflytek.cloud.d.a.g gVar = this.d;
        if (gVar != null && gVar.g()) {
            this.d.e();
            return;
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.e;
        if (speechSynthesizerAidl == null || !speechSynthesizerAidl.isSpeaking()) {
            return;
        }
        this.e.pauseSpeaking(this.f.c);
    }

    public void resumeSpeaking() {
        com.iflytek.cloud.d.a.g gVar = this.d;
        if (gVar != null && gVar.g()) {
            this.d.f();
            return;
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.e;
        if (speechSynthesizerAidl == null || !speechSynthesizerAidl.isSpeaking()) {
            return;
        }
        this.e.resumeSpeaking(this.f.c);
    }

    @Override // com.iflytek.cloud.a.d.d
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int startSpeaking(String str, SynthesizerListener synthesizerListener) {
        if (a("tts", this.e) != d.a.PLUS) {
            com.iflytek.cloud.d.a.g gVar = this.d;
            if (gVar == null) {
                return 21001;
            }
            gVar.setParameter(this.b);
            this.b.c(SpeechConstant.NEXT_TEXT);
            return this.d.a(str, synthesizerListener);
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.e;
        if (speechSynthesizerAidl == null) {
            return 21001;
        }
        speechSynthesizerAidl.setParameter("params", null);
        this.e.setParameter("params", this.b.toString());
        this.b.c(SpeechConstant.NEXT_TEXT);
        a aVar = new a(synthesizerListener);
        this.f = aVar;
        return this.e.startSpeaking(str, aVar.c);
    }

    public void stopSpeaking() {
        com.iflytek.cloud.d.a.g gVar = this.d;
        if (gVar != null && gVar.g()) {
            this.d.a(false);
            return;
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.e;
        if (speechSynthesizerAidl == null || !speechSynthesizerAidl.isSpeaking()) {
            return;
        }
        this.e.stopSpeaking(this.f.c);
    }

    public int synthesizeToUri(String str, String str2, SynthesizerListener synthesizerListener) {
        if (a("tts", this.e) != d.a.PLUS) {
            com.iflytek.cloud.d.a.g gVar = this.d;
            if (gVar == null) {
                return 21001;
            }
            gVar.setParameter(this.b);
            this.d.a(str, str2, synthesizerListener);
            return 0;
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.e;
        if (speechSynthesizerAidl == null) {
            return 21001;
        }
        speechSynthesizerAidl.setParameter("params", null);
        this.e.setParameter("params", this.b.toString());
        this.e.setParameter("tts_audio_uri", str2);
        a aVar = new a(synthesizerListener);
        this.f = aVar;
        return this.e.synthesizeToUrl(str, aVar.c);
    }
}
