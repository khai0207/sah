package com.iflytek.cloud.a.b;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.VerifierListener;
import com.iflytek.cloud.VerifierResult;
import com.iflytek.cloud.a.d.a;
import com.iflytek.cloud.a.d.b;
import com.iflytek.cloud.a.g.h;
import com.iflytek.cloud.c.f;
import com.unionpay.tsmservice.data.Constant;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
public class c extends com.iflytek.cloud.a.d.a implements f.a {
    protected volatile VerifierListener a;
    protected long b;
    protected boolean c;
    protected d d;
    protected f e;
    protected String f;
    protected String g;
    protected VerifierResult h;
    protected ConcurrentLinkedQueue<byte[]> i;
    protected int j;
    private long k;
    private int l;

    /* renamed from: com.iflytek.cloud.a.b.c$1 */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.a.values().length];
            a = iArr;
            try {
                iArr[b.a.noResult.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[b.a.hasResult.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public c(Context context, com.iflytek.cloud.b.a aVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.a = null;
        this.b = 0L;
        this.c = true;
        this.d = new d();
        this.e = null;
        this.f = "train";
        this.g = "";
        this.h = null;
        this.i = null;
        this.j = 1;
        this.k = 0L;
        this.l = 0;
        this.i = new ConcurrentLinkedQueue<>();
        a(aVar);
    }

    private boolean j() {
        return "train".equalsIgnoreCase(t().e("sst"));
    }

    private void l() throws SpeechError, IOException, InterruptedException {
        com.iflytek.cloud.a.g.a.a.a("--->onStoped: in");
        if (!j()) {
            y();
        }
        this.d.a();
        a(4);
        com.iflytek.cloud.a.g.a.a.a("--->onStoped: out");
    }

    private void w() throws SpeechError, UnsupportedEncodingException {
        com.iflytek.cloud.a.g.a.a.a("--->requestResult: in");
        if (AnonymousClass1.a[this.d.e().ordinal()] == 2) {
            x();
        }
        com.iflytek.cloud.a.g.a.a.a("--->requestResult: out");
    }

    private void x() throws SpeechError, UnsupportedEncodingException {
        this.t = SystemClock.elapsedRealtime();
        this.h = new VerifierResult(new String(this.d.d(), "utf-8"));
        if (this.f.equals("train") && this.h.ret == 0 && this.h.suc < this.h.rgn) {
            if (this.a != null) {
                this.a.onResult(this.h);
            }
            a(0);
        } else {
            if (this.a != null) {
                this.a.onResult(this.h);
            }
            c((SpeechError) null);
        }
    }

    private void y() {
        f fVar = this.e;
        if (fVar != null) {
            fVar.a(t().a("record_force_stop", false));
            this.e = null;
        }
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(Message message) throws Exception {
        super.a(message);
        int i = message.what;
        if (i == 0) {
            b();
            return;
        }
        if (i == 1) {
            d();
            return;
        }
        if (i == 2) {
            b(message);
            return;
        }
        if (i == 3) {
            l();
            return;
        }
        if (i == 4) {
            c(message);
        } else {
            if (i != 9) {
                return;
            }
            com.iflytek.cloud.a.g.a.a.a("--->on timeout vad");
            e();
        }
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(SpeechError speechError) {
        d dVar;
        String str;
        com.iflytek.cloud.a.g.a.a.a("--->onEnd: in");
        y();
        if (this.s) {
            dVar = this.d;
            str = "user abort";
        } else {
            dVar = this.d;
            if (speechError != null) {
                str = "error" + speechError.getErrorCode();
            } else {
                str = Constant.CASH_LOAD_SUCCESS;
            }
        }
        dVar.a(str);
        super.a(speechError);
        if (this.a != null && !this.s) {
            com.iflytek.cloud.a.g.a.a.a("VerifyListener#onEnd");
            if (speechError != null) {
                this.a.onError(speechError);
            }
        }
        com.iflytek.cloud.a.g.a.a.a("--->onEnd: out");
    }

    public synchronized void a(VerifierListener verifierListener) {
        com.iflytek.cloud.a.g.a.a.a("--->startVerify: in");
        this.a = verifierListener;
        c();
        com.iflytek.cloud.a.g.a.a.a("--->startVerify: out");
    }

    public void a(byte[] bArr, int i) {
        if (r()) {
            this.a.onVolumeChanged(i);
        }
    }

    @Override // com.iflytek.cloud.c.f.a
    public void a(byte[] bArr, int i, int i2) {
        if (a.b.recording == s() && i2 > 0) {
            int i3 = this.l;
            if (i3 <= 0) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, i, bArr2, 0, i2);
                d(obtainMessage(2, bArr2));
            } else {
                if (i3 >= i2) {
                    this.l = i3 - i2;
                    return;
                }
                byte[] bArr3 = new byte[i2 - i3];
                System.arraycopy(bArr, i + i3, bArr3, 0, i2 - i3);
                d(obtainMessage(2, bArr3));
                this.l = 0;
            }
        }
    }

    protected void a(byte[] bArr, boolean z) throws SpeechError {
        this.d.a(bArr, bArr.length);
        if (z) {
            if (!this.d.b()) {
                a(bArr, this.d.c());
            } else {
                com.iflytek.cloud.a.g.a.a.a("---> VadCheck Time: Vad End Point");
                e();
            }
        }
    }

    public synchronized boolean a() {
        boolean z;
        com.iflytek.cloud.a.g.a.a.a("--->stopRecord: in");
        if (s() != a.b.recording) {
            com.iflytek.cloud.a.g.a.a.a("endVerify fail  status is :" + s());
            z = false;
        } else {
            if (!j()) {
                y();
            }
            a(3);
            com.iflytek.cloud.a.g.a.a.a("--->stopRecord: out");
            z = true;
        }
        return z;
    }

    protected void b() throws Exception {
        com.iflytek.cloud.a.g.a.a.a("--->onStart: in");
        String e = t().e(SpeechConstant.ENGINE_TYPE);
        boolean a = t().a(SpeechConstant.NET_CHECK, true);
        if (SpeechConstant.TYPE_CLOUD.equals(e) && a) {
            h.a(this.r);
        }
        int a2 = t().a("record_read_rate", 40);
        if (this.j != -1 && r()) {
            com.iflytek.cloud.a.g.a.a.a("start  record");
            if (this.e == null) {
                f fVar = new f(q(), a2, this.j);
                this.e = fVar;
                fVar.a(this);
            }
        }
        if (s() != a.b.exiting && this.a != null) {
            this.a.onBeginOfSpeech();
        }
        this.b = SystemClock.elapsedRealtime();
        removeMessages(9);
        a(9, a.EnumC0017a.min, false, this.p);
        a(1, a.EnumC0017a.max, false, 0);
        com.iflytek.cloud.a.g.a.a.a("--->onStart: out");
    }

    protected void b(Message message) throws Exception {
        byte[] bArr = (byte[]) message.obj;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        this.i.add(bArr);
        a(bArr, true);
    }

    @Override // com.iflytek.cloud.c.f.a
    public void b(SpeechError speechError) {
        c(speechError);
    }

    @Override // com.iflytek.cloud.a.d.a
    public void b(boolean z) {
        if (z && r() && this.a != null) {
            this.a.onError(new SpeechError(ErrorCode.ERROR_INTERRUPT));
        }
        y();
        super.b(z);
    }

    void c(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        if (!j()) {
            y();
        }
        w();
        if (s() == a.b.waitresult) {
            a(4, a.EnumC0017a.normal, false, 20);
        }
    }

    @Override // com.iflytek.cloud.c.f.a
    public void c(boolean z) {
        com.iflytek.cloud.a.g.a.a.a("time cost: onRecordStarted:" + (SystemClock.elapsedRealtime() - this.k));
    }

    protected void d() throws Exception {
        if (this.d.a == null) {
            this.d.a(this.r, this.g, this);
        }
        a(a.b.recording);
    }

    public void e() {
        if (a.b.recording == s()) {
            com.iflytek.cloud.a.g.a.a.a("--->vadEndCall: out");
            a();
            if (this.a != null) {
                this.a.onEndOfSpeech();
            }
        }
    }

    public ConcurrentLinkedQueue<byte[]> f() {
        return this.i;
    }

    public int g() {
        return this.j;
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void h() {
        this.p = t().a(SpeechConstant.KEY_SPEECH_TIMEOUT, this.p);
        this.g = t().e(SpeechConstant.ISV_VID);
        this.j = t().a(SpeechConstant.AUDIO_SOURCE, 1);
        this.l = (((t().a(SpeechConstant.SAMPLE_RATE, this.q) / 1000) * 16) / 8) * t().a(SpeechConstant.FILTER_AUDIO_TIME, 0);
        com.iflytek.cloud.a.g.a.a.a("mSpeechTimeOut=" + this.p);
        super.h();
    }

    @Override // com.iflytek.cloud.c.f.a
    public void k() {
    }
}
