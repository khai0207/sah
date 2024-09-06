package com.iflytek.cloud;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
final class j extends Handler {
    final /* synthetic */ SpeechUnderstander a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    j(SpeechUnderstander speechUnderstander, Looper looper) {
        super(looper);
        this.a = speechUnderstander;
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
