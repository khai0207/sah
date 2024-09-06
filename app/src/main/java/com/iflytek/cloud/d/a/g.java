package com.iflytek.cloud.d.a;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.a.f.c;

/* loaded from: classes.dex */
public class g extends com.iflytek.cloud.a.d.e implements c.a {
    private Context f;
    private com.iflytek.cloud.a.f.c g;
    private com.iflytek.cloud.a.f.c h;

    public g(Context context) {
        super(context);
        this.f = null;
        this.g = null;
        this.h = null;
        this.f = context.getApplicationContext();
    }

    private void a(String str, SynthesizerListener synthesizerListener, String str2) {
        com.iflytek.cloud.a.g.a.a.a("new Session Start");
        com.iflytek.cloud.a.f.c cVar = new com.iflytek.cloud.a.f.c(this.f);
        this.g = cVar;
        cVar.a(this);
        this.g.a(str, this.b, synthesizerListener, true);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        com.iflytek.cloud.a.f.c cVar2 = new com.iflytek.cloud.a.f.c(this.f);
        this.h = cVar2;
        cVar2.a(this);
        this.h.a(str2, this.b);
    }

    public int a(String str, SynthesizerListener synthesizerListener) {
        com.iflytek.cloud.a.g.a.a.a("startSpeaking enter");
        synchronized (this) {
            String d = this.b.d(SpeechConstant.NEXT_TEXT);
            com.iflytek.cloud.a.g.a.b.a("QTTSStart", null);
            if (this.g != null && this.g.h()) {
                this.g.cancel(this.b.a(SpeechConstant.TTS_INTERRUPT_ERROR, false));
            }
            if (this.h != null) {
                if (str.equals(this.h.h)) {
                    if (this.h.i == null && this.h.f) {
                        com.iflytek.cloud.a.f.c cVar = this.h;
                        this.h = null;
                        if (!TextUtils.isEmpty(d)) {
                            com.iflytek.cloud.a.f.c cVar2 = new com.iflytek.cloud.a.f.c(this.f);
                            this.h = cVar2;
                            cVar2.a(this);
                            this.h.a(d, this.b);
                        }
                        this.g = cVar;
                        cVar.a(synthesizerListener);
                        this.g.i();
                        if (this.g.g) {
                            a();
                            com.iflytek.cloud.a.g.a.a.a("startSpeaking NextSession pause");
                        }
                    }
                    this.h.cancel(false);
                    this.h = null;
                } else {
                    this.h.cancel(false);
                    this.h = null;
                }
            }
            a(str, synthesizerListener, d);
        }
        com.iflytek.cloud.a.g.a.a.a("startSpeaking leave");
        return 0;
    }

    @Override // com.iflytek.cloud.a.f.c.a
    public void a() {
        synchronized (this) {
            if (this.h != null) {
                this.h.e();
            }
        }
    }

    public void a(String str, String str2, SynthesizerListener synthesizerListener) {
        com.iflytek.cloud.a.g.a.a.a("synthesizeToUri enter");
        synchronized (this) {
            if (this.g != null && this.g.h()) {
                this.g.cancel(this.b.a(SpeechConstant.TTS_INTERRUPT_ERROR, false));
            }
            com.iflytek.cloud.a.f.c cVar = new com.iflytek.cloud.a.f.c(this.f);
            this.g = cVar;
            cVar.a(str, str2, this.b, synthesizerListener);
        }
        com.iflytek.cloud.a.g.a.a.a("synthesizeToUri leave");
    }

    public void a(boolean z) {
        com.iflytek.cloud.a.g.a.a.a("stopSpeaking enter:" + z);
        synchronized (this) {
            if (this.g != null) {
                com.iflytek.cloud.a.g.a.a.a("-->stopSpeaking cur");
                this.g.cancel(z);
                this.g = null;
            }
            if (this.h != null) {
                com.iflytek.cloud.a.g.a.a.a("-->stopSpeaking cur next");
                this.h.cancel(false);
                this.h = null;
            }
        }
        com.iflytek.cloud.a.g.a.a.a("stopSpeaking leave");
    }

    @Override // com.iflytek.cloud.a.d.e
    public boolean destroy() {
        a(false);
        super.destroy();
        return true;
    }

    public void e() {
        com.iflytek.cloud.a.g.a.a.a("pauseSpeaking enter");
        synchronized (this) {
            if (this.g != null) {
                this.g.g();
            }
        }
        com.iflytek.cloud.a.g.a.a.a("pauseSpeaking leave");
    }

    public void f() {
        com.iflytek.cloud.a.g.a.a.a("resumeSpeaking enter");
        synchronized (this) {
            if (this.g != null) {
                this.g.i();
            }
        }
        com.iflytek.cloud.a.g.a.a.a("resumeSpeaking leave");
    }

    public boolean g() {
        boolean h;
        com.iflytek.cloud.a.g.a.a.a("isSpeaking enter");
        synchronized (this) {
            h = this.g != null ? this.g.h() : false;
        }
        com.iflytek.cloud.a.g.a.a.a("isSpeaking leave");
        return h;
    }

    public int h() {
        int f;
        com.iflytek.cloud.a.g.a.a.a("getState enter");
        synchronized (this) {
            f = this.g != null ? this.g.f() : 4;
        }
        com.iflytek.cloud.a.g.a.a.a("getState leave");
        return f;
    }
}
