package com.iflytek.cloud.d.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class j extends com.iflytek.cloud.a.d.e {
    private boolean f;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    final class a implements WakeuperListener {
        private WakeuperListener b;
        private Handler c = new k(this, Looper.getMainLooper());

        public a(WakeuperListener wakeuperListener) {
            this.b = null;
            this.b = wakeuperListener;
        }

        protected void a() {
            com.iflytek.cloud.a.g.f.b(j.this.a, Boolean.valueOf(j.this.f), null);
        }

        @Override // com.iflytek.cloud.WakeuperListener
        public void onBeginOfSpeech() {
            com.iflytek.cloud.a.g.a.a.a("onBeginOfSpeech");
            this.c.sendMessage(this.c.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.WakeuperListener
        public void onError(SpeechError speechError) {
            a();
            this.c.sendMessage(this.c.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.WakeuperListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = i2;
            obtain.arg2 = i3;
            obtain.obj = bundle;
            this.c.sendMessage(this.c.obtainMessage(6, 0, 0, obtain));
        }

        @Override // com.iflytek.cloud.WakeuperListener
        public void onResult(WakeuperResult wakeuperResult) {
            try {
                JSONObject jSONObject = new JSONObject(wakeuperResult.getResultString());
                if (!jSONObject.getString("sst").equals("enroll") || jSONObject.getString("status").equals("done")) {
                    a();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.c.sendMessage(this.c.obtainMessage(4, 1, 0, wakeuperResult));
        }
    }

    public j(Context context) {
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
                if (((com.iflytek.cloud.a.c.b) this.d).a() != -1) {
                    return ErrorCode.MSP_ERROR_INVALID_PARA;
                }
                ((com.iflytek.cloud.a.c.b) this.d).a(bArr, i, i2);
                return 0;
            }
            com.iflytek.cloud.a.g.a.a.a("writeAudio error,buffer is null.");
            return ErrorCode.MSP_ERROR_INVALID_DATA;
        }
    }

    public void a(WakeuperListener wakeuperListener) {
        synchronized (this.c) {
            this.f = this.b.a(SpeechConstant.KEY_REQUEST_FOCUS, true);
            if (this.d != null && this.d.r()) {
                ((com.iflytek.cloud.a.c.b) this.d).b(false);
            }
            this.d = new com.iflytek.cloud.a.c.b(this.a, this.b, a("wakeuper"));
            com.iflytek.cloud.a.g.f.a(this.a, Boolean.valueOf(this.f), null);
            ((com.iflytek.cloud.a.c.b) this.d).a(new a(wakeuperListener));
        }
    }

    @Override // com.iflytek.cloud.a.d.e
    public void cancel(boolean z) {
        com.iflytek.cloud.a.g.f.b(this.a, Boolean.valueOf(this.f), null);
        super.cancel(z);
    }

    public void e() {
        synchronized (this.c) {
            if (this.d != null) {
                ((com.iflytek.cloud.a.c.b) this.d).a(true);
            }
        }
    }

    public boolean f() {
        return d();
    }
}
