package com.iflytek.cloud.a.c;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.b.c;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class a extends com.iflytek.cloud.a.d.b {
    private MSCSessionInfo c = new MSCSessionInfo();
    private MSCSessionInfo d = new MSCSessionInfo();

    private synchronized void a(byte[] bArr, int i, int i2) throws SpeechError {
        int QIVWAudioWrite = MSC.QIVWAudioWrite(this.a, bArr, i, i2, this.d);
        this.c.sesstatus = this.d.sesstatus;
        com.iflytek.cloud.a.g.a.a.a("QISRAudioWrite length:" + i);
        if (QIVWAudioWrite != 0) {
            throw new SpeechError(this.d.errorcode);
        }
    }

    public int a(Context context, String str, com.iflytek.cloud.a.d.a aVar) throws SpeechError, UnsupportedEncodingException {
        String b = c.b(context, str, aVar);
        String e = aVar.t().e(SpeechConstant.CLOUD_GRAMMAR);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (TextUtils.isEmpty(e)) {
            this.a = MSC.QIVWSessionBegin(null, b.getBytes(aVar.n()), this.c);
        } else {
            this.a = MSC.QIVWSessionBegin(e.getBytes(aVar.n()), b.getBytes(aVar.n()), this.c);
        }
        com.iflytek.cloud.a.g.a.a.a("sessionBegin ErrCode:" + this.c.errorcode + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        int i = this.c.errorcode;
        if (i == 0 || i == 10129 || i == 10113 || i == 10132) {
            return i;
        }
        throw new SpeechError(i);
    }

    public synchronized void a() throws SpeechError {
        a(new byte[0], 0, 4);
    }

    public void a(String str) {
        if (this.a == null) {
            return;
        }
        com.iflytek.cloud.a.g.a.a.a("sessionEnd enter ");
        long currentTimeMillis = System.currentTimeMillis();
        com.iflytek.cloud.a.g.a.a.a("sessionEnd leavel:" + (MSC.QIVWSessionEnd(this.a, str.getBytes()) == 0) + " time:" + (System.currentTimeMillis() - currentTimeMillis));
        this.a = null;
        this.b = null;
    }

    public synchronized void a(byte[] bArr, int i) throws SpeechError {
        a(bArr, i, 2);
    }
}
