package com.iflytek.cloud.a.a;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.a.d.a;
import com.iflytek.cloud.a.d.c;
import com.iflytek.cloud.a.g.h;
import com.iflytek.cloud.c.f;
import com.iflytek.msc.MSC;
import com.unionpay.tsmservice.data.Constant;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
public class b extends com.iflytek.cloud.a.d.a implements f.a {
    public static int j;
    public static int k;
    protected volatile RecognizerListener a;
    protected boolean b;
    protected boolean c;
    protected boolean d;
    protected boolean e;
    protected int f;
    protected boolean g;
    protected a h;
    protected f i;
    protected String l;
    protected ConcurrentLinkedQueue<byte[]> m;
    protected ArrayList<String> n;
    protected c o;
    private int v;
    private int w;

    public b(Context context, com.iflytek.cloud.b.a aVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = 1;
        this.g = true;
        this.h = new a();
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = new c();
        this.v = 0;
        this.w = 0;
        this.m = new ConcurrentLinkedQueue<>();
        this.n = new ArrayList<>();
        this.e = false;
        a(aVar);
    }

    private void a(boolean z, byte[] bArr) throws SpeechError, UnsupportedEncodingException {
        String str;
        com.iflytek.cloud.a.g.a.b.a("QISRGetResult", null);
        this.t = SystemClock.elapsedRealtime();
        if (bArr != null && bArr.length > 0) {
            str = new String(bArr, "utf-8");
        } else {
            if (this.n.size() <= 0) {
                String e = t().e(SpeechConstant.LOCAL_GRAMMAR);
                if (!TextUtils.isEmpty(e) && !"sms.irf".equals(e)) {
                    throw new SpeechError(ErrorCode.ERROR_NO_MATCH);
                }
                throw new SpeechError(ErrorCode.MSP_ERROR_NO_DATA);
            }
            str = "";
        }
        this.n.add(str);
        if (this.a != null && r()) {
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, l());
            this.a.onEvent(20001, 0, 0, bundle);
            if (z && t().a("request_audio_url", false)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString(SpeechEvent.KEY_EVENT_AUDIO_URL, this.h.d());
                this.a.onEvent(23001, 0, 0, bundle2);
            }
            this.a.onResult(new RecognizerResult(str), z);
        }
        com.iflytek.cloud.a.g.a.a.a("msc result time:" + System.currentTimeMillis());
        if (z) {
            c((SpeechError) null);
        }
    }

    private void w() throws SpeechError, IOException, InterruptedException {
        com.iflytek.cloud.a.g.a.a.a("recording stop");
        x();
        this.o.a("app_lau");
        this.h.a();
        m();
        a(8, a.EnumC0017a.min, false, this.f18u);
    }

    private void x() {
        f fVar = this.i;
        if (fVar != null) {
            fVar.a(t().a("record_force_stop", false));
            this.i = null;
            this.o.a("rec_close");
            if (this.a != null) {
                this.a.onEvent(22003, 0, 0, null);
            }
        }
    }

    public int a() {
        return this.f;
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(Message message) throws Exception {
        super.a(message);
        int i = message.what;
        if (i == 0) {
            d();
            return;
        }
        if (i == 1) {
            e();
            return;
        }
        if (i == 2) {
            b(message);
            return;
        }
        if (i == 3) {
            w();
            return;
        }
        if (i == 4) {
            c(message);
        } else if (i == 7) {
            f();
        } else {
            if (i != 9) {
                return;
            }
            g();
        }
    }

    public synchronized void a(RecognizerListener recognizerListener) {
        this.a = recognizerListener;
        com.iflytek.cloud.a.g.a.a.a("startListening called");
        c();
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(SpeechError speechError) {
        a aVar;
        String str;
        com.iflytek.cloud.a.g.a.a.a("onSessionEnd");
        x();
        j = this.h.b("upflow");
        k = this.h.b("downflow");
        l();
        if (this.n.size() <= 0 && speechError == null && t().a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
            speechError = new SpeechError(ErrorCode.MSP_ERROR_NO_DATA);
        }
        this.o.a("app_ret", speechError != null ? speechError.getErrorCode() : 0L, false);
        com.iflytek.cloud.a.g.a.b.a("QISRSessionEnd", null);
        this.o.a("rec_ustop", this.e ? "1" : "0", false);
        this.h.a("sessinfo", this.o.a());
        if (this.s) {
            aVar = this.h;
            str = "user abort";
        } else {
            aVar = this.h;
            if (speechError != null) {
                str = "error" + speechError.getErrorCode();
            } else {
                str = Constant.CASH_LOAD_SUCCESS;
            }
        }
        aVar.a(str);
        super.a(speechError);
        if (this.a != null) {
            if (this.s) {
                com.iflytek.cloud.a.g.a.a.a("RecognizerListener#onCancel");
                return;
            }
            com.iflytek.cloud.a.g.a.a.a("RecognizerListener#onEnd");
            if (speechError != null) {
                this.a.onError(speechError);
            }
        }
    }

    public void a(byte[] bArr, int i) {
        if (this.a == null || !r()) {
            return;
        }
        this.a.onVolumeChanged(i);
    }

    @Override // com.iflytek.cloud.c.f.a
    public void a(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 <= 0 || bArr.length < i2 || i2 <= 0 || !r()) {
            return;
        }
        if (!this.b) {
            this.b = true;
            this.o.a("rec_start");
        }
        int i3 = this.v;
        if (i3 <= 0) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            d(obtainMessage(2, bArr2));
        } else {
            if (i3 >= i2) {
                this.v = i3 - i2;
                return;
            }
            byte[] bArr3 = new byte[i2 - i3];
            System.arraycopy(bArr, i + i3, bArr3, 0, i2 - i3);
            d(obtainMessage(2, bArr3));
            this.v = 0;
        }
    }

    protected void a(byte[] bArr, boolean z) throws SpeechError {
        if (!this.c) {
            this.c = true;
            this.o.a("app_fau");
            if (this.a != null) {
                this.a.onEvent(22002, 0, 0, null);
            }
        }
        com.iflytek.cloud.a.g.a.b.a("QISRAudioWrite", "" + bArr.length);
        this.h.a(bArr, bArr.length);
        if (z) {
            int b = this.h.b();
            com.iflytek.cloud.a.g.a.a.a("QISRAudioWrite volume:" + b);
            a(bArr, b);
        }
    }

    public synchronized boolean a(boolean z) {
        com.iflytek.cloud.a.g.a.a.a("stopRecognize, current status is :" + s() + " usercancel : " + z);
        this.o.a("app_stop");
        x();
        this.e = z;
        a(3);
        return true;
    }

    public ConcurrentLinkedQueue<byte[]> b() {
        return this.m;
    }

    protected void b(Message message) throws Exception {
        byte[] bArr = (byte[]) message.obj;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        this.m.add(bArr);
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
        x();
        if (s() == a.b.recording) {
            this.e = true;
        }
        super.b(z);
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void c() {
        this.o.a(t());
        super.c();
    }

    void c(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        int i = message.arg1;
        byte[] bArr = (byte[]) message.obj;
        if (i == 0) {
            if (!this.d) {
                this.d = true;
                this.o.a("app_frs");
            }
            a(false, bArr);
            return;
        }
        if (i != 5) {
            return;
        }
        if (!this.d) {
            this.d = true;
            this.o.a("app_frs");
        }
        this.o.a("app_lrs");
        a(true, bArr);
    }

    @Override // com.iflytek.cloud.c.f.a
    public void c(boolean z) {
        this.o.a("rec_ready");
    }

    protected void d() throws Exception {
        com.iflytek.cloud.a.g.a.a.a("start connecting");
        String e = t().e(SpeechConstant.ENGINE_TYPE);
        if (t().a(SpeechConstant.NET_CHECK, true)) {
            if (SpeechConstant.TYPE_CLOUD.equals(e)) {
                h.a(this.r);
            } else if (SpeechConstant.TYPE_MIX.equals(e) || "mixed".equals(e)) {
                try {
                    h.a(this.r);
                } catch (Exception unused) {
                    t().a(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
                }
            }
        }
        int a = t().a("record_read_rate", 40);
        if (hasMessages(3)) {
            throw new SpeechError(ErrorCode.MSP_ERROR_NO_DATA);
        }
        if (this.f != -1 && r()) {
            com.iflytek.cloud.a.g.a.a.a("start  record");
            if (this.f == -2) {
                this.i = new com.iflytek.cloud.c.a(q(), a, this.f, t().e(SpeechConstant.ASR_SOURCE_PATH));
            } else {
                this.i = new f(q(), a, this.f);
            }
            this.o.a("rec_open");
            this.i.a(this);
            a(9, a.EnumC0017a.min, false, this.p);
            if (this.a != null) {
                this.a.onBeginOfSpeech();
            }
        }
        com.iflytek.cloud.a.g.a.b.a("QISRSessionBegin", null);
        this.o.a("app_ssb");
        a(1, a.EnumC0017a.max, false, 0);
    }

    protected void e() throws Exception {
        int a = this.h.a(this.r, this.l, this);
        if (a == 0 && this.h.a != null) {
            if (r()) {
                MSC.QISRRegisterNotify(this.h.a, "rsltCb", "stusCb", "errCb", this);
                a(a.b.recording);
                if (t().a(SpeechConstant.ASR_NET_PERF, false)) {
                    a(7, a.EnumC0017a.max, false, 0);
                    return;
                }
                return;
            }
            return;
        }
        int i = this.w + 1;
        this.w = i;
        if (i > 40) {
            throw new SpeechError(a);
        }
        if (r()) {
            Thread.sleep(15L);
            a(1, a.EnumC0017a.max, false, 0);
        }
    }

    void errCb(char[] cArr, int i, byte[] bArr) {
        b(new SpeechError(i));
    }

    public void f() {
        if (r()) {
            int b = this.h.b("netperf");
            if (this.a != null) {
                this.a.onEvent(10001, b, 0, null);
            }
            a(7, a.EnumC0017a.normal, false, 100);
        }
    }

    public void g() {
        if (a.b.recording == s()) {
            if (this.a != null) {
                this.a.onEndOfSpeech();
            }
            a(false);
        }
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void h() {
        this.l = t().e(SpeechConstant.CLOUD_GRAMMAR);
        this.f = t().a(SpeechConstant.AUDIO_SOURCE, 1);
        this.g = com.iflytek.cloud.b.c.a(t().e(SpeechConstant.DOMAIN));
        this.v = (((t().a(SpeechConstant.SAMPLE_RATE, this.q) / 1000) * 16) / 8) * t().a(SpeechConstant.FILTER_AUDIO_TIME, 0);
        this.p = t().a(SpeechConstant.KEY_SPEECH_TIMEOUT, this.p);
        com.iflytek.cloud.a.g.a.a.a("mSpeechTimeOut=" + this.p);
        super.h();
    }

    @Override // com.iflytek.cloud.a.d.a
    public boolean i() {
        return this.g;
    }

    public c j() {
        return this.o;
    }

    @Override // com.iflytek.cloud.c.f.a
    public void k() {
        f fVar = this.i;
        if (fVar == null || !(fVar instanceof com.iflytek.cloud.c.a)) {
            return;
        }
        a(true);
    }

    public String l() {
        return this.h.c();
    }

    void rsltCb(char[] cArr, byte[] bArr, int i, int i2) {
        StringBuilder sb;
        String str;
        if (bArr != null) {
            sb = new StringBuilder();
            sb.append("rsltCb:");
            sb.append(i2);
            sb.append("result:");
            str = new String(bArr);
        } else {
            sb = new StringBuilder();
            sb.append("rsltCb:");
            sb.append(i2);
            str = "result:null";
        }
        sb.append(str);
        com.iflytek.cloud.a.g.a.a.a("MscRecognizer", sb.toString());
        a(obtainMessage(4, i2, 0, bArr), hasMessages(4) ? a.EnumC0017a.normal : a.EnumC0017a.max, false, 0);
    }

    void stusCb(char[] cArr, int i, int i2, int i3, byte[] bArr) {
        if (i == 0 && i2 == 3) {
            g();
        }
    }
}
