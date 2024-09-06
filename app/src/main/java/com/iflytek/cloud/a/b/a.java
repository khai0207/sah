package com.iflytek.cloud.a.b;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;

/* loaded from: classes.dex */
public class a extends com.iflytek.cloud.a.d.a {
    private d a;
    private SpeechListener b;

    /* renamed from: com.iflytek.cloud.a.b.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0016a implements SpeechListener {
        private SpeechListener a;
        private Handler b = new b(this, Looper.getMainLooper());

        public C0016a(SpeechListener speechListener) {
            this.a = null;
            this.a = speechListener;
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onBufferReceived(byte[] bArr) {
            this.b.sendMessage(this.b.obtainMessage(1, bArr));
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onCompleted(SpeechError speechError) {
            this.b.sendMessage(this.b.obtainMessage(2, speechError));
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onEvent(int i, Bundle bundle) {
            this.b.sendMessage(this.b.obtainMessage(0, i, 0, bundle));
        }
    }

    public a(Context context, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.a = new d();
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(Message message) throws Exception {
        if (message.what != 13) {
            return;
        }
        synchronized (a.class) {
            this.b.onBufferReceived(String.format("{'ret':%d,'cmd':%s}", Integer.valueOf(this.a.a(this.r, this)), t().e(SpeechConstant.ISV_CMD)).getBytes(n()));
            a(21);
        }
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(SpeechError speechError) {
        super.a(speechError);
        SpeechListener speechListener = this.b;
        if (speechListener != null) {
            speechListener.onCompleted(speechError);
        }
    }

    public void a(com.iflytek.cloud.b.a aVar, C0016a c0016a) {
        this.b = c0016a;
        a(aVar);
        Message obtain = Message.obtain();
        obtain.what = 13;
        d(obtain);
    }
}
