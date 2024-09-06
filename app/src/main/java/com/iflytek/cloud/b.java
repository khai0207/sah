package com.iflytek.cloud;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
final class b extends Handler {
    final /* synthetic */ SpeechRecognizer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    b(SpeechRecognizer speechRecognizer, Looper looper) {
        super(looper);
        this.a = speechRecognizer;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        InitListener initListener;
        InitListener initListener2;
        initListener = this.a.f;
        if (initListener == null) {
            return;
        }
        initListener2 = this.a.f;
        initListener2.onInit(0);
    }
}
