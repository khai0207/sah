package com.iflytek.cloud.a.d;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class a extends Handler {
    private com.iflytek.cloud.b.a a;
    private volatile b b;
    private HandlerThread c;
    protected int p;
    protected int q;
    protected Context r;
    protected volatile boolean s;
    protected long t;

    /* renamed from: u, reason: collision with root package name */
    protected int f18u;

    /* renamed from: com.iflytek.cloud.a.d.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    protected enum EnumC0017a {
        max,
        normal,
        min
    }

    /* loaded from: classes.dex */
    protected enum b {
        init,
        start,
        recording,
        waitresult,
        exiting,
        exited
    }

    public a(Context context) {
        this.p = 60000;
        this.q = 16000;
        this.r = null;
        this.a = new com.iflytek.cloud.b.a();
        this.s = false;
        this.b = b.init;
        this.t = 0L;
        this.f18u = 20000;
        this.r = context;
        this.s = false;
    }

    public a(Context context, HandlerThread handlerThread) {
        super(handlerThread.getLooper());
        this.p = 60000;
        this.q = 16000;
        this.r = null;
        this.a = new com.iflytek.cloud.b.a();
        this.s = false;
        this.b = b.init;
        this.t = 0L;
        this.f18u = 20000;
        this.c = handlerThread;
        this.r = context;
        this.s = false;
    }

    private void a() {
        if (this.c.isAlive()) {
            u();
            this.c.quit();
            this.c = null;
        }
    }

    protected void a(int i) {
        a(obtainMessage(i), EnumC0017a.normal, false, 0);
    }

    protected void a(int i, EnumC0017a enumC0017a, boolean z, int i2) {
        a(obtainMessage(i), enumC0017a, z, i2);
    }

    protected void a(Message message) throws Exception {
    }

    protected void a(Message message, EnumC0017a enumC0017a, boolean z, int i) {
        b bVar;
        if (s() == b.exited || s() == b.exiting) {
            return;
        }
        int i2 = message.what;
        if (i2 == 0) {
            bVar = b.start;
        } else {
            if (i2 != 3) {
                if (i2 == 21) {
                    bVar = b.exiting;
                }
                if (enumC0017a == EnumC0017a.max || i > 0) {
                    sendMessageDelayed(message, i);
                } else {
                    sendMessageAtFrontOfQueue(message);
                    return;
                }
            }
            bVar = b.waitresult;
        }
        a(bVar);
        if (enumC0017a == EnumC0017a.max) {
        }
        sendMessageDelayed(message, i);
    }

    protected void a(SpeechError speechError) {
        a(b.exited);
        u();
    }

    protected synchronized void a(b bVar) {
        com.iflytek.cloud.a.g.a.a.a("curStatus=" + this.b + ",setStatus=" + bVar);
        if (this.b == b.exited) {
            return;
        }
        if (this.b != b.exiting || bVar == b.exited) {
            com.iflytek.cloud.a.g.a.a.a("setStatus success=" + bVar);
            this.b = bVar;
            this.t = SystemClock.elapsedRealtime();
        }
    }

    protected void a(com.iflytek.cloud.b.a aVar) {
        this.a = aVar.clone();
        h();
    }

    public void b(boolean z) {
        this.s = true;
        u();
        c(null);
    }

    protected void c() {
        a(0, EnumC0017a.max, false, 0);
    }

    protected synchronized void c(SpeechError speechError) {
        if (speechError != null) {
            u();
        }
        d(obtainMessage(21, speechError));
    }

    protected void d(Message message) {
        a(message, EnumC0017a.normal, false, 0);
    }

    protected void h() {
        this.f18u = this.a.a("timeout", this.f18u);
        this.q = this.a.a(SpeechConstant.SAMPLE_RATE, this.q);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        StringBuilder sb;
        if (message.what == 21) {
            a((SpeechError) message.obj);
            a();
            return;
        }
        try {
            try {
                if (message.what == 8) {
                    throw new SpeechError(ErrorCode.ERROR_NETWORK_TIMEOUT);
                }
                a(message);
            } catch (SpeechError e) {
                e = e;
                e.printStackTrace();
                sb = new StringBuilder();
                sb.append(v());
                sb.append(" occur Error = ");
                sb.append(e.toString());
                com.iflytek.cloud.a.g.a.a.a(sb.toString());
                c(e);
            } catch (Exception e2) {
                e2.printStackTrace();
                SpeechError speechError = new SpeechError(e2);
                com.iflytek.cloud.a.g.a.a.a(v() + " occur Error = " + speechError.toString());
                c(speechError);
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            e = new SpeechError(ErrorCode.ERROR_FILE_ACCESS);
            sb = new StringBuilder();
            sb.append(v());
            sb.append(" occur Error = ");
            sb.append(e.toString());
            com.iflytek.cloud.a.g.a.a.a(sb.toString());
            c(e);
        }
    }

    public boolean i() {
        return false;
    }

    protected void m() {
        removeMessages(8);
        a(8, EnumC0017a.normal, false, this.f18u);
    }

    public String n() {
        return this.a.b("pte", "utf-8");
    }

    public String o() {
        return this.a.b(SpeechConstant.TEXT_ENCODING, "utf-8");
    }

    public String p() {
        return this.a.b("rse", "utf-8");
    }

    public int q() {
        return this.q;
    }

    public boolean r() {
        return (this.b == b.exited || this.b == b.exiting || this.b == b.init) ? false : true;
    }

    protected synchronized b s() {
        return this.b;
    }

    public com.iflytek.cloud.b.a t() {
        return this.a;
    }

    protected void u() {
        com.iflytek.cloud.a.g.a.a.a("clear all message");
        for (int i = 0; i < 20; i++) {
            removeMessages(i);
        }
    }

    protected String v() {
        return getClass().toString();
    }
}
