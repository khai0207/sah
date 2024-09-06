package com.iflytek.cloud;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
final class g extends Handler {
    final /* synthetic */ SpeechSynthesizer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    g(SpeechSynthesizer speechSynthesizer, Looper looper) {
        super(looper);
        this.a = speechSynthesizer;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (this.a.a == null) {
            return;
        }
        this.a.a.onInit(0);
    }
}
