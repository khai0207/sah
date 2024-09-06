package com.iflytek.cloud.a.b;

import android.content.Context;
import android.os.SystemClock;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class d extends com.iflytek.cloud.a.d.b {
    private MSCSessionInfo c = new MSCSessionInfo();
    private MSCSessionInfo d = new MSCSessionInfo();
    private MSCSessionInfo e = new MSCSessionInfo();
    private byte[] f = null;

    private synchronized void a(byte[] bArr, int i, int i2) throws SpeechError {
        int QISVAudioWrite = MSC.QISVAudioWrite(this.a, null, bArr, i, i2, this.c);
        if (QISVAudioWrite != 0) {
            throw new SpeechError(QISVAudioWrite);
        }
    }

    public int a(Context context, com.iflytek.cloud.a.d.a aVar) throws UnsupportedEncodingException, SpeechError {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String e = aVar.t().e(SpeechConstant.ISV_VID);
        String b = com.iflytek.cloud.b.c.b(context, aVar);
        com.iflytek.cloud.a.g.a.a.a("sendRequest enter ");
        MSC.QISVQueDelModelRelease(MSC.QISVQueDelModel(e == null ? null : e.getBytes(aVar.n()), b.getBytes(aVar.n()), this.e));
        int i = this.e.errorcode != 0 ? this.e.errorcode : "true".equals(new String(this.e.buffer)) ? 0 : -1;
        if (i != 0 && -1 != i) {
            throw new SpeechError(i);
        }
        com.iflytek.cloud.a.g.a.a.a("sendRequest leavel:" + i + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return i;
    }

    public int a(Context context, String str, com.iflytek.cloud.a.d.a aVar) throws SpeechError, UnsupportedEncodingException {
        String b = com.iflytek.cloud.b.c.b(context, aVar);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.a = MSC.QISVSessionBegin(b.getBytes(aVar.n()), str == null ? null : str.getBytes(aVar.n()), this.c);
        com.iflytek.cloud.a.g.a.a.a("sessionBegin ErrCode:" + this.c.errorcode + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        int i = this.c.errorcode;
        if (i == 0 || i == 10129 || i == 10113 || i == 10132) {
            return 0;
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
        com.iflytek.cloud.a.g.a.a.a("sessionEnd leavel:" + (MSC.QISVSessionEnd(this.a, str.getBytes()) == 0) + " time:" + (System.currentTimeMillis() - currentTimeMillis));
        this.a = null;
        this.b = null;
    }

    public synchronized void a(byte[] bArr, int i) throws SpeechError {
        a(bArr, i, 2);
    }

    public synchronized boolean b() {
        return this.c.epstatues >= 3;
    }

    public synchronized int c() {
        int i;
        int i2;
        i = 0;
        try {
            i2 = MSC.QISVGetParam(this.a, SpeechConstant.VOLUME.getBytes(), this.d);
        } catch (Exception unused) {
            i2 = 0;
        }
        try {
            if (i2 == 0) {
                i = Integer.parseInt(new String(new String(this.d.buffer)));
            } else {
                com.iflytek.cloud.a.g.a.a.a("VAD CHECK FALSE");
            }
        } catch (Exception unused2) {
            com.iflytek.cloud.a.g.a.a.a("getAudioVolume Exception vadret = " + i2);
            return i;
        }
        return i;
    }

    public byte[] d() {
        return this.f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0051, code lost:
    
        if (r0 != 5) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.iflytek.cloud.a.d.b.a e() throws com.iflytek.cloud.SpeechError {
        /*
            r7 = this;
            java.util.Date r0 = new java.util.Date
            r0.<init>()
            char[] r1 = r7.a
            com.iflytek.msc.MSCSessionInfo r2 = r7.c
            r3 = 0
            byte[] r1 = com.iflytek.msc.MSC.QISVGetResult(r1, r3, r2)
            r7.f = r1
            java.util.Date r1 = new java.util.Date
            r1.<init>()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "QISVGetResult leavel:"
            r2.append(r3)
            byte[] r3 = r7.f
            r4 = 1
            if (r3 == 0) goto L26
            r3 = 1
            goto L27
        L26:
            r3 = 0
        L27:
            r2.append(r3)
            java.lang.String r3 = " time:"
            r2.append(r3)
            long r5 = r1.getTime()
            long r0 = r0.getTime()
            long r5 = r5 - r0
            r2.append(r5)
            java.lang.String r0 = r2.toString()
            com.iflytek.cloud.a.g.a.a.a(r0)
            com.iflytek.msc.MSCSessionInfo r0 = r7.c
            int r0 = r0.errorcode
            if (r0 != 0) goto L8e
            com.iflytek.msc.MSCSessionInfo r0 = r7.c
            int r0 = r0.rsltstatus
            if (r0 == 0) goto L70
            if (r0 == r4) goto L54
            r1 = 5
            if (r0 == r1) goto L70
            goto L8b
        L54:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "ResultStatus: noResult"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.iflytek.cloud.a.g.a.a.a(r0)
            com.iflytek.cloud.SpeechError r0 = new com.iflytek.cloud.SpeechError
            r1 = 20005(0x4e25, float:2.8033E-41)
            r0.<init>(r1)
            throw r0
        L70:
            byte[] r1 = r7.f
            if (r1 == 0) goto L8b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "ResultStatus: hasResult"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.iflytek.cloud.a.g.a.a.a(r0)
            com.iflytek.cloud.a.d.b$a r0 = com.iflytek.cloud.a.d.b.a.hasResult
            return r0
        L8b:
            com.iflytek.cloud.a.d.b$a r0 = com.iflytek.cloud.a.d.b.a.noResult
            return r0
        L8e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Result: error errorcode is "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            com.iflytek.cloud.a.g.a.a.a(r1)
            com.iflytek.cloud.SpeechError r1 = new com.iflytek.cloud.SpeechError
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.a.b.d.e():com.iflytek.cloud.a.d.b$a");
    }
}
