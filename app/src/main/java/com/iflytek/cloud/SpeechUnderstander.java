package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.a.d.d;
import com.iflytek.msc.MSC;
import com.iflytek.speech.SpeechUnderstanderAidl;

/* loaded from: classes.dex */
public class SpeechUnderstander extends com.iflytek.cloud.a.d.d {
    protected static SpeechUnderstander a;
    private com.iflytek.cloud.d.a.h c;
    private SpeechUnderstanderAidl d;
    private InitListener f;
    private a e = null;
    private Handler g = new j(this, Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    final class a implements SpeechUnderstanderListener {
        private SpeechUnderstanderListener b;
        private com.iflytek.speech.SpeechUnderstanderListener c;
        private Handler d = new l(this, Looper.getMainLooper());

        public a(SpeechUnderstanderListener speechUnderstanderListener) {
            this.b = null;
            this.c = null;
            this.b = speechUnderstanderListener;
            this.c = new k(this, SpeechUnderstander.this);
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onBeginOfSpeech() {
            this.d.sendMessage(this.d.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onEndOfSpeech() {
            this.d.sendMessage(this.d.obtainMessage(3, 0, 0, null));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onError(SpeechError speechError) {
            this.d.sendMessage(this.d.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            this.d.sendMessage(this.d.obtainMessage(6, 0, 0, message));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onResult(UnderstanderResult understanderResult) {
            this.d.sendMessage(this.d.obtainMessage(4, understanderResult));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onVolumeChanged(int i) {
            this.d.sendMessage(this.d.obtainMessage(1, i, 0, null));
        }
    }

    protected SpeechUnderstander(Context context, InitListener initListener) {
        this.c = null;
        this.d = null;
        this.f = null;
        this.f = initListener;
        if (MSC.isLoaded()) {
            this.c = new com.iflytek.cloud.d.a.h(context);
        }
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.a() && utility.getEngineMode() != d.a.MSC) {
            this.d = new SpeechUnderstanderAidl(context.getApplicationContext(), initListener);
        } else if (initListener != null) {
            Message.obtain(this.g, 0, 0, 0, null).sendToTarget();
        }
    }

    public static synchronized SpeechUnderstander createUnderstander(Context context, InitListener initListener) {
        SpeechUnderstander speechUnderstander;
        synchronized (SpeechUnderstander.class) {
            if (a == null) {
                a = new SpeechUnderstander(context, initListener);
            }
            speechUnderstander = a;
        }
        return speechUnderstander;
    }

    public static SpeechUnderstander getUnderstander() {
        return a;
    }

    protected void a(Context context) {
        SpeechUnderstanderAidl speechUnderstanderAidl;
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility == null || !utility.a() || utility.getEngineMode() == d.a.MSC) {
            if (this.f == null || (speechUnderstanderAidl = this.d) == null) {
                return;
            }
            speechUnderstanderAidl.destory();
            this.d = null;
            return;
        }
        SpeechUnderstanderAidl speechUnderstanderAidl2 = this.d;
        if (speechUnderstanderAidl2 != null && !speechUnderstanderAidl2.isAvailable()) {
            this.d.destory();
            this.d = null;
        }
        this.d = new SpeechUnderstanderAidl(context.getApplicationContext(), this.f);
    }

    public void cancel() {
        com.iflytek.cloud.d.a.h hVar = this.c;
        if (hVar != null && hVar.a()) {
            this.c.a(false);
            return;
        }
        SpeechUnderstanderAidl speechUnderstanderAidl = this.d;
        if (speechUnderstanderAidl == null || !speechUnderstanderAidl.isUnderstanding()) {
            com.iflytek.cloud.a.g.a.a.b("SpeechUnderstander cancel failed, is not running");
        } else {
            this.d.cancel(this.e.c);
        }
    }

    public boolean destroy() {
        SpeechUnderstanderAidl speechUnderstanderAidl = this.d;
        if (speechUnderstanderAidl != null) {
            speechUnderstanderAidl.destory();
            this.d = null;
        }
        com.iflytek.cloud.d.a.h hVar = this.c;
        boolean c = hVar != null ? hVar.c() : true;
        if (c) {
            a = null;
        }
        return c;
    }

    @Override // com.iflytek.cloud.a.d.d
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public boolean isUnderstanding() {
        com.iflytek.cloud.d.a.h hVar = this.c;
        if (hVar != null && hVar.a()) {
            return true;
        }
        SpeechUnderstanderAidl speechUnderstanderAidl = this.d;
        return speechUnderstanderAidl != null && speechUnderstanderAidl.isUnderstanding();
    }

    @Override // com.iflytek.cloud.a.d.d
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int startUnderstanding(SpeechUnderstanderListener speechUnderstanderListener) {
        d.a a2 = a(SpeechConstant.ENG_NLU, this.d);
        com.iflytek.cloud.a.g.a.a.a("start engine mode = " + a2.toString());
        if (a2 != d.a.PLUS) {
            com.iflytek.cloud.d.a.h hVar = this.c;
            if (hVar == null) {
                return 21001;
            }
            hVar.a(this.b);
            return this.c.a(speechUnderstanderListener);
        }
        SpeechUnderstanderAidl speechUnderstanderAidl = this.d;
        if (speechUnderstanderAidl == null) {
            return 21001;
        }
        speechUnderstanderAidl.setParameter("params", null);
        this.d.setParameter("params", this.b.toString());
        a aVar = new a(speechUnderstanderListener);
        this.e = aVar;
        return this.d.startUnderstanding(aVar.c);
    }

    public void stopUnderstanding() {
        com.iflytek.cloud.d.a.h hVar = this.c;
        if (hVar != null && hVar.a()) {
            this.c.b();
            return;
        }
        SpeechUnderstanderAidl speechUnderstanderAidl = this.d;
        if (speechUnderstanderAidl == null || !speechUnderstanderAidl.isUnderstanding()) {
            com.iflytek.cloud.a.g.a.a.a("SpeechUnderstander stopUnderstanding, is not understanding");
        } else {
            this.d.stopUnderstanding(this.e.c);
        }
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        com.iflytek.cloud.d.a.h hVar = this.c;
        if (hVar != null && hVar.a()) {
            return this.c.a(bArr, i, i2);
        }
        SpeechUnderstanderAidl speechUnderstanderAidl = this.d;
        if (speechUnderstanderAidl != null && speechUnderstanderAidl.isUnderstanding()) {
            return this.d.writeAudio(bArr, i, i2);
        }
        com.iflytek.cloud.a.g.a.a.a("SpeechUnderstander writeAudio, is not understanding");
        return ErrorCode.ERROR_ENGINE_CALL_FAIL;
    }
}
