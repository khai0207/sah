package com.iflytek.cloud.a.f;

import android.os.Handler;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;

/* loaded from: classes.dex */
final class h implements b {
    final /* synthetic */ Handler a;
    final /* synthetic */ String b;
    final /* synthetic */ c c;

    h(c cVar, Handler handler, String str) {
        this.c = cVar;
        this.a = handler;
        this.b = str;
    }

    @Override // com.iflytek.cloud.a.f.b
    public void a(SpeechError speechError) {
        SynthesizerListener synthesizerListener;
        boolean z;
        SynthesizerListener synthesizerListener2;
        synthesizerListener = this.c.m;
        if (synthesizerListener == null || speechError == null) {
            return;
        }
        z = this.c.t;
        if (z) {
            Message.obtain(this.a, 6, speechError).sendToTarget();
        } else {
            synthesizerListener2 = this.c.m;
            synthesizerListener2.onCompleted(speechError);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00fc A[Catch: IOException -> 0x0120, TryCatch #0 {IOException -> 0x0120, blocks: (B:21:0x006a, B:23:0x007b, B:25:0x009c, B:26:0x00a7, B:29:0x00b4, B:31:0x00bd, B:33:0x00cd, B:35:0x00d5, B:38:0x00e2, B:39:0x00e7, B:41:0x00f4, B:43:0x00fc, B:45:0x0105, B:47:0x010f, B:50:0x00e8, B:52:0x0119, B:53:0x011e), top: B:20:0x006a }] */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    @Override // com.iflytek.cloud.a.f.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.util.ArrayList<byte[]> r7, int r8, int r9, int r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.a.f.h.a(java.util.ArrayList, int, int, int, java.lang.String):void");
    }
}
