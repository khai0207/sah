package com.iflytek.cloud.a.f;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.c.c;

/* loaded from: classes.dex */
public class c extends com.iflytek.cloud.a.d.e {
    public boolean f;
    public boolean g;
    public String h;
    public SpeechError i;
    private com.iflytek.cloud.c.c j;
    private com.iflytek.cloud.c.b k;
    private SynthesizerListener l;
    private SynthesizerListener m;
    private a n;
    private int o;
    private boolean p;
    private b q;
    private c.a r;
    private Handler s;
    private boolean t;

    /* loaded from: classes.dex */
    public interface a {
        void a();
    }

    public c(Context context) {
        super(context);
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = 0;
        this.p = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = null;
        this.q = new d(this);
        this.r = new e(this);
        this.s = new f(this, Looper.getMainLooper());
        this.t = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.p || this.j == null || !this.k.a(this.o)) {
            return;
        }
        this.p = true;
        com.iflytek.cloud.a.g.a.b.a("QTTSOnPlayBegin", null);
        this.j.a(this.k, this.r);
        if (this.l != null) {
            Message.obtain(this.s, 1).sendToTarget();
        }
    }

    public void a(SynthesizerListener synthesizerListener) {
        this.l = synthesizerListener;
    }

    public void a(a aVar) {
        this.n = aVar;
    }

    public void a(String str, com.iflytek.cloud.b.a aVar) {
        setParameter(aVar);
        this.h = str;
    }

    public synchronized void a(String str, com.iflytek.cloud.b.a aVar, SynthesizerListener synthesizerListener, boolean z) {
        com.iflytek.cloud.a.g.a.a.a("tts start:" + System.currentTimeMillis());
        this.l = synthesizerListener;
        this.h = str;
        setParameter(aVar);
        int a2 = aVar.a(SpeechConstant.STREAM_TYPE, 3);
        boolean a3 = aVar.a(SpeechConstant.KEY_REQUEST_FOCUS, true);
        if (z) {
            this.j = new com.iflytek.cloud.c.c(this.a, a2, a3);
        }
        this.d = new com.iflytek.cloud.a.f.a(this.a, aVar, a("tts"));
        com.iflytek.cloud.c.b bVar = new com.iflytek.cloud.c.b(this.a, this.d.q(), aVar.e(SpeechConstant.TTS_AUDIO_PATH));
        this.k = bVar;
        bVar.a(str);
        this.o = aVar.a(SpeechConstant.TTS_BUFFER_TIME, 0);
        com.iflytek.cloud.a.g.a.a.a("minPlaySec:" + this.o);
        this.p = false;
        ((com.iflytek.cloud.a.f.a) this.d).a(str, this.q);
        this.f = true;
    }

    public void a(String str, String str2, com.iflytek.cloud.b.a aVar, SynthesizerListener synthesizerListener) {
        this.t = aVar.a("message_main_thread", true);
        this.m = synthesizerListener;
        this.d = new com.iflytek.cloud.a.f.a(this.a, aVar, a("tts"));
        com.iflytek.cloud.c.b bVar = new com.iflytek.cloud.c.b(this.a, this.d.q(), str2);
        this.k = bVar;
        bVar.a(str);
        ((com.iflytek.cloud.a.f.a) this.d).a(str, new h(this, new g(this, Looper.getMainLooper()), str2));
    }

    @Override // com.iflytek.cloud.a.d.e
    public void cancel(boolean z) {
        com.iflytek.cloud.a.g.a.a.a("SpeakSession cancel notifyError:" + z);
        if (h()) {
            SynthesizerListener synthesizerListener = this.l;
            if (synthesizerListener != null) {
                synthesizerListener.onEvent(21002, 0, 0, null);
            }
            SynthesizerListener synthesizerListener2 = this.m;
            if (synthesizerListener2 != null) {
                synthesizerListener2.onEvent(21002, 0, 0, null);
            }
            if (z) {
                if (this.l != null) {
                    com.iflytek.cloud.a.g.a.a.a("tts-onCompleted-cancel");
                    this.l.onCompleted(new SpeechError(ErrorCode.ERROR_INTERRUPT));
                }
                SynthesizerListener synthesizerListener3 = this.m;
                if (synthesizerListener3 != null) {
                    synthesizerListener3.onCompleted(new SpeechError(ErrorCode.ERROR_INTERRUPT));
                }
            }
        }
        this.l = null;
        this.m = null;
        super.cancel(false);
        com.iflytek.cloud.c.c cVar = this.j;
        if (cVar != null) {
            cVar.e();
        }
    }

    @Override // com.iflytek.cloud.a.d.e
    public boolean d() {
        return super.d();
    }

    @Override // com.iflytek.cloud.a.d.e
    public boolean destroy() {
        synchronized (this.c) {
            cancel(false);
        }
        return true;
    }

    public void e() {
        if (this.g) {
            return;
        }
        a(this.h, this.b, (SynthesizerListener) null, false);
    }

    public int f() {
        com.iflytek.cloud.c.c cVar;
        if (this.k == null || (cVar = this.j) == null) {
            return 4;
        }
        return cVar.a();
    }

    public void g() {
        com.iflytek.cloud.c.c cVar;
        if (this.k == null || (cVar = this.j) == null) {
            return;
        }
        cVar.c();
    }

    public boolean h() {
        if (d()) {
            return true;
        }
        return (f() == 4 || f() == 0) ? false : true;
    }

    public void i() {
        com.iflytek.cloud.c.c cVar;
        if (this.k != null && (cVar = this.j) != null) {
            cVar.d();
        } else {
            this.j = new com.iflytek.cloud.c.c(this.a);
            j();
        }
    }
}
