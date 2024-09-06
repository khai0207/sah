package com.iflytek.cloud;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.TextUnderstander;

/* loaded from: classes.dex */
final class o extends Handler {
    final /* synthetic */ TextUnderstander.a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    o(TextUnderstander.a aVar, Looper looper) {
        super(looper);
        this.a = aVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        TextUnderstanderListener textUnderstanderListener;
        TextUnderstanderListener textUnderstanderListener2;
        TextUnderstanderListener textUnderstanderListener3;
        textUnderstanderListener = this.a.b;
        if (textUnderstanderListener == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            textUnderstanderListener2 = this.a.b;
            textUnderstanderListener2.onError((SpeechError) message.obj);
        } else if (i == 4) {
            textUnderstanderListener3 = this.a.b;
            textUnderstanderListener3.onResult((UnderstanderResult) message.obj);
        }
        super.handleMessage(message);
    }
}
