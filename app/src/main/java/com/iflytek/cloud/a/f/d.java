package com.iflytek.cloud.a.f;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.a.f.c;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class d implements b {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    @Override // com.iflytek.cloud.a.f.b
    public void a(SpeechError speechError) {
        SynthesizerListener synthesizerListener;
        Handler handler;
        com.iflytek.cloud.c.c cVar;
        com.iflytek.cloud.c.c cVar2;
        com.iflytek.cloud.c.b bVar;
        c.a aVar;
        c.a aVar2;
        this.a.i = speechError;
        if (speechError == null) {
            this.a.g = true;
            bVar = this.a.k;
            bVar.b();
            aVar = this.a.n;
            if (aVar != null) {
                aVar2 = this.a.n;
                aVar2.a();
                com.iflytek.cloud.a.g.a.a.a("onCompleted NextSession pause");
            }
        }
        synthesizerListener = this.a.l;
        if (synthesizerListener == null || speechError == null) {
            return;
        }
        handler = this.a.s;
        Message.obtain(handler, 6, speechError).sendToTarget();
        cVar = this.a.j;
        if (cVar != null) {
            cVar2 = this.a.j;
            cVar2.e();
        }
    }

    @Override // com.iflytek.cloud.a.f.b
    public void a(ArrayList<byte[]> arrayList, int i, int i2, int i3, String str) {
        SynthesizerListener synthesizerListener;
        Handler handler;
        com.iflytek.cloud.c.b bVar;
        Handler handler2;
        Bundle bundle = new Bundle();
        bundle.putInt("percent", i);
        bundle.putInt("begpos", i2);
        bundle.putInt("endpos", i3);
        bundle.putString("spellinfo", str);
        synthesizerListener = this.a.l;
        if (synthesizerListener != null) {
            handler2 = this.a.s;
            Message.obtain(handler2, 2, bundle).sendToTarget();
        }
        try {
            bVar = this.a.k;
            bVar.a(arrayList, i, i2, i3);
            this.a.j();
        } catch (IOException e) {
            e.printStackTrace();
            this.a.i = new SpeechError(ErrorCode.ERROR_FILE_ACCESS);
            handler = this.a.s;
            Message.obtain(handler, 6, this.a.i).sendToTarget();
            this.a.cancel(false);
        }
    }
}
