package com.iflytek.cloud.a.a;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.GrammarListener;
import com.iflytek.cloud.LexiconListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.b.c;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class a extends com.iflytek.cloud.a.d.b {
    private static GrammarListener f;
    private static LexiconListener g;
    private MSCSessionInfo c = new MSCSessionInfo();
    private MSCSessionInfo d = new MSCSessionInfo();
    private byte[] e = null;
    private String h = "";

    private synchronized void a(byte[] bArr, int i, int i2) throws SpeechError {
        int QISRAudioWrite = MSC.QISRAudioWrite(this.a, bArr, i, i2, this.d);
        this.c.sesstatus = this.d.sesstatus;
        com.iflytek.cloud.a.g.a.a.a("QISRAudioWrite length:" + i);
        if (QISRAudioWrite != 0) {
            throw new SpeechError(this.d.errorcode);
        }
    }

    public int a(Context context, String str, com.iflytek.cloud.a.d.a aVar) throws SpeechError, UnsupportedEncodingException {
        String a = c.a(context, str, aVar);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        synchronized (a.class) {
            if (TextUtils.isEmpty(str)) {
                com.iflytek.cloud.a.g.a.a.a(a);
                this.a = MSC.QISRSessionBegin(null, a.getBytes(aVar.n()), this.c);
            } else {
                this.a = MSC.QISRSessionBegin(str.getBytes(aVar.n()), a.getBytes(aVar.n()), this.c);
                com.iflytek.cloud.a.g.a.a.a("sessionBegin grammarId:" + str);
            }
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
        com.iflytek.cloud.a.g.a.a.a("sessionEnd leavel:" + (MSC.QISRSessionEnd(this.a, str.getBytes()) == 0) + " time:" + (System.currentTimeMillis() - currentTimeMillis));
        this.a = null;
        this.b = null;
    }

    public synchronized void a(byte[] bArr, int i) throws SpeechError {
        a(bArr, i, 2);
    }

    public synchronized boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.a == null) {
            return false;
        }
        int i = -1;
        try {
            i = MSC.QISRSetParam(this.a, str.getBytes("utf-8"), str2.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return i == 0;
    }

    public synchronized int b() {
        int i;
        int i2;
        i = 0;
        try {
            i2 = MSC.QISRGetParam(this.a, SpeechConstant.VOLUME.getBytes(), this.d);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    protected String c() {
        if (this.b == null) {
            this.b = c(SpeechConstant.IST_SESSION_ID);
        }
        return this.b;
    }

    public synchronized String c(String str) {
        if (this.a == null) {
            return null;
        }
        try {
            if (MSC.QISRGetParam(this.a, str.getBytes(), this.c) == 0) {
                return new String(this.c.buffer);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    protected String d() {
        return c(SpeechEvent.KEY_EVENT_AUDIO_URL);
    }

    int grammarCallBack(int i, char[] cArr) {
        String valueOf;
        GrammarListener grammarListener = f;
        if (grammarListener == null) {
            return 0;
        }
        if (i != 0) {
            r1 = i != 0 ? new SpeechError(i) : null;
            valueOf = "";
        } else {
            valueOf = String.valueOf(cArr);
        }
        grammarListener.onBuildFinish(valueOf, r1);
        return 0;
    }

    int lexiconCallBack(int i, char[] cArr) {
        LexiconListener lexiconListener = g;
        if (lexiconListener == null) {
            return 0;
        }
        if (i != 0) {
            lexiconListener.onLexiconUpdated(this.h, i != 0 ? new SpeechError(i) : null);
            return 0;
        }
        lexiconListener.onLexiconUpdated(this.h, null);
        return 0;
    }
}
