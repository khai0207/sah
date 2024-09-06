package com.iflytek.cloud.a.f;

import android.os.Handler;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.c.c;

/* loaded from: classes.dex */
final class e implements c.a {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    @Override // com.iflytek.cloud.c.c.a
    public void a() {
        SynthesizerListener synthesizerListener;
        Handler handler;
        synthesizerListener = this.a.l;
        if (synthesizerListener != null) {
            handler = this.a.s;
            Message.obtain(handler, 3).sendToTarget();
            com.iflytek.cloud.a.g.a.b.a("QTTSOnPlayPause", null);
        }
    }

    @Override // com.iflytek.cloud.c.c.a
    public void a(int i, int i2, int i3) {
        Handler handler;
        handler = this.a.s;
        Message.obtain(handler, 5, i, i2, Integer.valueOf(i3)).sendToTarget();
    }

    @Override // com.iflytek.cloud.c.c.a
    public void a(SpeechError speechError) {
        Handler handler;
        com.iflytek.cloud.c.c cVar;
        com.iflytek.cloud.c.c cVar2;
        handler = this.a.s;
        Message.obtain(handler, 6, speechError).sendToTarget();
        cVar = this.a.j;
        if (cVar != null) {
            cVar2 = this.a.j;
            cVar2.e();
        }
        this.a.cancel(false);
    }

    @Override // com.iflytek.cloud.c.c.a
    public void b() {
        SynthesizerListener synthesizerListener;
        Handler handler;
        synthesizerListener = this.a.l;
        if (synthesizerListener != null) {
            handler = this.a.s;
            Message.obtain(handler, 4).sendToTarget();
            com.iflytek.cloud.a.g.a.b.a("QTTSOnPlayResume", null);
        }
    }

    @Override // com.iflytek.cloud.c.c.a
    public void c() {
        Handler handler;
        handler = this.a.s;
        Message.obtain(handler, 6, null).sendToTarget();
        com.iflytek.cloud.a.g.a.b.a("QTTSOnPlayStop", null);
    }
}
