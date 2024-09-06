package com.iflytek.cloud.d.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.iflytek.cloud.DataDownloader;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.VerifierListener;
import com.iflytek.cloud.VerifierResult;
import com.iflytek.cloud.a.b.a;
import java.util.Random;

/* loaded from: classes.dex */
public class a extends com.iflytek.cloud.a.d.e {
    private boolean f;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.d.a.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    final class C0018a implements VerifierListener {
        private VerifierListener b;
        private Handler c = new b(this, Looper.getMainLooper());

        public C0018a(VerifierListener verifierListener) {
            this.b = null;
            this.b = verifierListener;
        }

        protected void a() {
            String e = a.this.d.t().e(SpeechConstant.ISV_AUDIOPATH);
            if (!TextUtils.isEmpty(e)) {
                com.iflytek.cloud.a.g.e.a(((com.iflytek.cloud.a.b.c) a.this.d).f(), e);
            }
            com.iflytek.cloud.a.g.f.b(a.this.a, Boolean.valueOf(a.this.f), null);
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onBeginOfSpeech() {
            this.c.sendMessage(this.c.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onEndOfSpeech() {
            this.c.sendMessage(this.c.obtainMessage(3, 0, 0, null));
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onError(SpeechError speechError) {
            a();
            this.c.sendMessage(this.c.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onResult(VerifierResult verifierResult) {
            a();
            this.c.sendMessage(this.c.obtainMessage(4, verifierResult));
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onVolumeChanged(int i) {
            this.c.sendMessage(this.c.obtainMessage(1, i, 0, null));
        }
    }

    public a(Context context) {
        super(context);
        this.f = false;
    }

    public int a(byte[] bArr, int i, int i2) {
        synchronized (this.c) {
            if (this.d == null) {
                com.iflytek.cloud.a.g.a.a.a("writeAudio error, no active session.");
                return ErrorCode.ERROR_ENGINE_CALL_FAIL;
            }
            if (bArr != null && bArr.length > 0) {
                if (bArr.length < i2 + i) {
                    com.iflytek.cloud.a.g.a.a.a("writeAudio error,buffer length < length.");
                    return ErrorCode.MSP_ERROR_INVALID_DATA;
                }
                if (((com.iflytek.cloud.a.b.c) this.d).g() != -1) {
                    return ErrorCode.MSP_ERROR_INVALID_PARA;
                }
                ((com.iflytek.cloud.a.b.c) this.d).a(bArr, i, i2);
                return 0;
            }
            com.iflytek.cloud.a.g.a.a.a("writeAudio error,buffer is null.");
            return ErrorCode.MSP_ERROR_INVALID_DATA;
        }
    }

    public String a(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        String str = "023456789".charAt(random.nextInt(9)) + "";
        stringBuffer.append(str);
        for (int i2 = 0; i2 < i - 1; i2++) {
            while (true) {
                for (Boolean bool = false; !bool.booleanValue(); bool = true) {
                    str = "023456789".charAt(random.nextInt(9)) + "";
                    if (stringBuffer.indexOf(str) >= 0) {
                        break;
                    }
                    if (Integer.parseInt(stringBuffer.charAt(stringBuffer.length() - 1) + "") * Integer.parseInt(str) == 10) {
                        break;
                    }
                }
            }
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public void a(SpeechListener speechListener) {
        DataDownloader dataDownloader = new DataDownloader(this.a);
        dataDownloader.setParameter(this.b);
        dataDownloader.downloadData(speechListener);
    }

    public void a(VerifierListener verifierListener) {
        synchronized (this.c) {
            this.f = this.b.a(SpeechConstant.KEY_REQUEST_FOCUS, true);
            if (this.d != null && this.d.r()) {
                this.d.b(this.b.a(SpeechConstant.ISV_INTERRUPT_ERROR, false));
            }
            this.d = new com.iflytek.cloud.a.b.c(this.a, this.b, a("verify"));
            com.iflytek.cloud.a.g.f.a(this.a, Boolean.valueOf(this.f), null);
            ((com.iflytek.cloud.a.b.c) this.d).a(new C0018a(verifierListener));
        }
    }

    public void a(String str, String str2, SpeechListener speechListener) {
        this.b.a(SpeechConstant.ISV_CMD, str);
        this.b.a("auth_id", str2);
        new com.iflytek.cloud.a.b.a(this.a, a("manager")).a(this.b, new a.C0016a(speechListener));
    }

    public void e() {
        synchronized (this.c) {
            if (this.d != null) {
                ((com.iflytek.cloud.a.b.c) this.d).a();
            }
        }
    }

    public boolean f() {
        return d();
    }
}
