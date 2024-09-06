package com.iflytek.cloud.a.e;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import java.io.IOException;

/* loaded from: classes.dex */
public class b extends com.iflytek.cloud.a.d.a {
    private SpeechListener a;
    private com.iflytek.cloud.a.e.a b;

    /* loaded from: classes.dex */
    private class a {
        private byte[] b;
        private String c;

        public a(byte[] bArr, String str) {
            this.b = null;
            this.c = "";
            this.b = bArr;
            this.c = str;
        }

        public byte[] a() {
            return this.b;
        }

        public String b() {
            return this.c;
        }
    }

    public b(Context context, com.iflytek.cloud.b.a aVar) {
        super(context);
        this.a = null;
        this.b = new com.iflytek.cloud.a.e.a();
        a(aVar);
    }

    public b(Context context, com.iflytek.cloud.b.a aVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.a = null;
        this.b = new com.iflytek.cloud.a.e.a();
        a(aVar);
    }

    public SpeechError a(String str, String str2) {
        StringBuilder sb;
        SpeechError speechError = null;
        com.iflytek.cloud.a.g.a.b.a("QMSPLogin", null);
        try {
            com.iflytek.cloud.a.e.a.a(this.r, str, str2, this);
        } catch (SpeechError e) {
            speechError = e;
            speechError.printStackTrace();
            sb = new StringBuilder();
            sb.append(v());
            sb.append(" occur Error = ");
            sb.append(speechError.toString());
            com.iflytek.cloud.a.g.a.a.a(sb.toString());
            return speechError;
        } catch (IOException e2) {
            e2.printStackTrace();
            speechError = new SpeechError(ErrorCode.ERROR_FILE_ACCESS);
            sb = new StringBuilder();
            sb.append(v());
            sb.append(" occur Error = ");
            sb.append(speechError.toString());
            com.iflytek.cloud.a.g.a.a.a(sb.toString());
            return speechError;
        }
        return speechError;
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(Message message) throws Exception {
        byte[] a2;
        super.a(message);
        switch (message.what) {
            case 10:
                a aVar = (a) message.obj;
                if (aVar.a() != null && aVar.a().length > 0) {
                    com.iflytek.cloud.a.g.a.b.a("QMSPUploadData", null);
                    a2 = this.b.a(this.r, aVar.b(), aVar.a(), this);
                    break;
                } else {
                    throw new SpeechError(ErrorCode.ERROR_EMPTY_UTTERANCE);
                }
                break;
            case 11:
                com.iflytek.cloud.a.g.a.b.a("QMSPDownloadData", null);
                a2 = this.b.a(this.r, this);
                break;
            case 12:
                String str = (String) message.obj;
                if (!TextUtils.isEmpty(str)) {
                    com.iflytek.cloud.a.g.a.b.a("QMSPSearch", null);
                    a2 = this.b.a(this.r, this, str);
                    break;
                } else {
                    throw new SpeechError(ErrorCode.ERROR_EMPTY_UTTERANCE);
                }
            default:
                a2 = null;
                break;
        }
        if (a2 == null) {
            throw new SpeechError(ErrorCode.ERROR_INVALID_RESULT);
        }
        SpeechListener speechListener = this.a;
        if (speechListener != null) {
            speechListener.onBufferReceived(a2);
        }
        c(null);
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(SpeechError speechError) {
        super.a(speechError);
        if (this.a == null || this.s) {
            return;
        }
        this.a.onCompleted(speechError);
    }

    public void a(SpeechListener speechListener) {
        this.a = speechListener;
        a(11);
    }

    public void a(SpeechListener speechListener, String str) {
        this.a = speechListener;
        d(obtainMessage(12, str));
    }

    public void a(SpeechListener speechListener, String str, byte[] bArr) {
        this.a = speechListener;
        d(obtainMessage(10, new a(bArr, str)));
    }
}
