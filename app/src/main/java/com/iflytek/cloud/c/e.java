package com.iflytek.cloud.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.c.c;

/* loaded from: classes.dex */
final class e extends Handler {
    final /* synthetic */ c a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    e(c cVar, Looper looper) {
        super(looper);
        this.a = cVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        c.a aVar;
        c.a aVar2;
        c.a aVar3;
        c.a aVar4;
        c.a aVar5;
        c.a aVar6;
        c.a aVar7;
        c.a aVar8;
        int i;
        c.a aVar9;
        c.a aVar10;
        int i2 = message.what;
        if (i2 == 0) {
            aVar = this.a.f;
            if (aVar == null) {
                return;
            }
            aVar2 = this.a.f;
            aVar2.a((SpeechError) message.obj);
        } else {
            if (i2 == 1) {
                aVar3 = this.a.f;
                if (aVar3 != null) {
                    aVar4 = this.a.f;
                    aVar4.a();
                    return;
                }
                return;
            }
            if (i2 == 2) {
                aVar5 = this.a.f;
                if (aVar5 != null) {
                    aVar6 = this.a.f;
                    aVar6.b();
                    return;
                }
                return;
            }
            if (i2 == 3) {
                aVar7 = this.a.f;
                if (aVar7 != null) {
                    aVar8 = this.a.f;
                    int i3 = message.arg1;
                    int i4 = message.arg2;
                    i = this.a.n;
                    aVar8.a(i3, i4, i);
                    return;
                }
                return;
            }
            if (i2 != 4) {
                return;
            }
            aVar9 = this.a.f;
            if (aVar9 == null) {
                return;
            }
            aVar10 = this.a.f;
            aVar10.c();
        }
        this.a.f = null;
    }
}
