package com.iflytek.cloud.a.f;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class i extends com.iflytek.cloud.a.d.b {
    private MSCSessionInfo c = new MSCSessionInfo();

    public int a(Context context, String str, com.iflytek.cloud.a.d.a aVar) throws SpeechError, UnsupportedEncodingException {
        this.a = null;
        String d = com.iflytek.cloud.b.c.d(context, aVar);
        long currentTimeMillis = System.currentTimeMillis();
        byte[] bytes = d.getBytes(aVar.n());
        synchronized (i.class) {
            this.a = MSC.QTTSSessionBegin(bytes, this.c);
        }
        com.iflytek.cloud.a.g.a.a.a("QTTSSessionBegin leave:" + (System.currentTimeMillis() - currentTimeMillis) + " ErrorCode:" + this.c.errorcode);
        int i = this.c.errorcode;
        if (i == 0 || i == 10129 || i == 10113 || i == 10132) {
            return i;
        }
        throw new SpeechError(i);
    }

    public void a(String str) {
        if (this.a == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        com.iflytek.cloud.a.g.a.a.a("QTTSSessionEnd enter");
        com.iflytek.cloud.a.g.a.a.a("QTTSSessionEnd leavel:" + MSC.QTTSSessionEnd(this.a, str.getBytes()));
        this.a = null;
        this.b = null;
    }

    public synchronized void a(byte[] bArr) throws SpeechError {
        com.iflytek.cloud.a.g.a.a.a("QTTSTextPut enter");
        int QTTSTextPut = MSC.QTTSTextPut(this.a, bArr);
        com.iflytek.cloud.a.g.a.a.a("QTTSTextPut leavel:" + QTTSTextPut);
        if (QTTSTextPut != 0) {
            throw new SpeechError(QTTSTextPut);
        }
    }

    public synchronized byte[] a() throws SpeechError {
        byte[] QTTSAudioGet;
        if (this.a == null) {
            throw new SpeechError(ErrorCode.ERROR_NET_EXPECTION);
        }
        com.iflytek.cloud.a.g.a.a.a("QTTSAudioGet enter");
        QTTSAudioGet = MSC.QTTSAudioGet(this.a, this.c);
        StringBuilder sb = new StringBuilder();
        sb.append("QTTSAudioGet leavel:");
        sb.append(this.c.errorcode);
        sb.append("value len = ");
        sb.append(QTTSAudioGet == null ? 0 : QTTSAudioGet.length);
        com.iflytek.cloud.a.g.a.a.a(sb.toString());
        int i = this.c.errorcode;
        if (i != 0) {
            throw new SpeechError(i);
        }
        return QTTSAudioGet;
    }

    public int b() {
        try {
            return new com.iflytek.cloud.b.a(new String(MSC.QTTSAudioInfo(this.a)), (String[][]) null).a("ced", 0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public synchronized int b(String str) {
        int i = 0;
        if (this.a == null) {
            return 0;
        }
        try {
            String c = c(str);
            if (!TextUtils.isEmpty(c)) {
                i = Integer.parseInt(new String(c));
            }
        } catch (Exception unused) {
        }
        return i;
    }

    public String c() {
        String str;
        Exception e;
        try {
            str = new String(MSC.QTTSAudioInfo(this.a));
            try {
                return new String(str.getBytes("iso8859-1"), "gb2312");
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return str;
            }
        } catch (Exception e3) {
            str = "";
            e = e3;
        }
    }

    public synchronized String c(String str) {
        if (this.a == null) {
            return null;
        }
        try {
            if (MSC.QTTSGetParam(this.a, str.getBytes(), this.c) == 0) {
                return new String(this.c.buffer);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public synchronized boolean d() {
        return 2 == this.c.sesstatus;
    }

    protected String e() {
        if (this.b == null) {
            this.b = c(SpeechConstant.IST_SESSION_ID);
        }
        return this.b;
    }
}
