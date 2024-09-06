package com.iflytek.cloud.a.c;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import com.iflytek.cloud.a.d.a;
import com.iflytek.cloud.c.f;
import com.iflytek.msc.MSC;
import com.unionpay.tsmservice.data.Constant;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class b extends com.iflytek.cloud.a.d.a implements f.a {
    public String a;
    protected volatile WakeuperListener b;
    protected boolean c;
    protected int d;
    protected a e;
    protected f f;
    protected ArrayList<String> g;
    public boolean h;
    public boolean i;
    private int j;

    public b(Context context, com.iflytek.cloud.b.a aVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.b = null;
        this.c = false;
        this.d = 1;
        this.e = new a();
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.j = 0;
        this.c = false;
        a(aVar);
        this.g = new ArrayList<>();
    }

    private void a(boolean z, byte[] bArr, int i) throws SpeechError, UnsupportedEncodingException {
        String str;
        if (i == 1) {
            if (bArr != null && bArr.length > 0) {
                str = new String(bArr, p());
            } else {
                if (this.g.size() <= 0) {
                    String e = t().e(SpeechConstant.LOCAL_GRAMMAR);
                    if (!TextUtils.isEmpty(e) && !"sms.irf".equals(e)) {
                        throw new SpeechError(ErrorCode.ERROR_NO_MATCH);
                    }
                    throw new SpeechError(ErrorCode.MSP_ERROR_NO_DATA);
                }
                str = "";
            }
            this.g.add(str);
            if (this.b != null && r()) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(SpeechEvent.KEY_EVENT_IVW_RESULT, new RecognizerResult(str));
                this.b.onEvent(22001, z ? 1 : 0, 0, bundle);
            }
            com.iflytek.cloud.a.g.a.a.a("msc result time:" + System.currentTimeMillis());
        } else if (i == 0) {
            if (bArr == null || bArr.length <= 0) {
                throw new SpeechError(ErrorCode.MSP_ERROR_NO_DATA);
            }
            String str2 = new String(bArr, "utf-8");
            if (this.b != null && r()) {
                this.b.onResult(new WakeuperResult(str2));
            }
        }
        if (z) {
            c((SpeechError) null);
        }
    }

    private void f() throws SpeechError, IOException, InterruptedException {
        com.iflytek.cloud.a.g.a.a.a("recording stop");
        if (!this.a.equals("enroll")) {
            g();
        }
        this.e.a();
    }

    private void g() {
        f fVar = this.f;
        if (fVar != null) {
            fVar.a(t().a("record_force_stop", false));
            this.f = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0069, code lost:
    
        if (hasMessages(4) != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006b, code lost:
    
        r6 = com.iflytek.cloud.a.d.a.EnumC0017a.normal;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006e, code lost:
    
        r6 = com.iflytek.cloud.a.d.a.EnumC0017a.max;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009c, code lost:
    
        if (hasMessages(4) != false) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int MsgProcCallBack(char[] r5, int r6, int r7, int r8, byte[] r9) {
        /*
            r4 = this;
            java.lang.String r5 = "param2:"
            java.lang.String r0 = "param1:"
            java.lang.String r1 = "msg:"
            java.lang.String r2 = "MscWakeuper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            if (r9 == 0) goto L2c
            r3.<init>()
            r3.append(r1)
            r3.append(r6)
            r3.append(r0)
            r3.append(r7)
            r3.append(r5)
            r3.append(r8)
            java.lang.String r5 = "result:"
            r3.append(r5)
            java.lang.String r5 = new java.lang.String
            r5.<init>(r9)
            goto L43
        L2c:
            r3.<init>()
            r3.append(r1)
            r3.append(r6)
            r3.append(r0)
            r3.append(r7)
            r3.append(r5)
            r3.append(r8)
            java.lang.String r5 = "result:null"
        L43:
            r3.append(r5)
            java.lang.String r5 = r3.toString()
            com.iflytek.cloud.a.g.a.a.a(r2, r5)
            r5 = 1
            r8 = 4
            r0 = 0
            if (r6 == r5) goto L7d
            r1 = 2
            if (r6 == r1) goto L74
            r1 = 3
            if (r6 == r1) goto L61
            if (r6 == r8) goto L5b
            goto L9f
        L5b:
            if (r7 != r1) goto L9f
            r4.e()
            goto L9f
        L61:
            android.os.Message r5 = r4.obtainMessage(r8, r7, r5, r9)
            boolean r6 = r4.hasMessages(r8)
            if (r6 == 0) goto L6e
        L6b:
            com.iflytek.cloud.a.d.a$a r6 = com.iflytek.cloud.a.d.a.EnumC0017a.normal
            goto L70
        L6e:
            com.iflytek.cloud.a.d.a$a r6 = com.iflytek.cloud.a.d.a.EnumC0017a.max
        L70:
            r4.a(r5, r6, r0, r0)
            goto L9f
        L74:
            com.iflytek.cloud.SpeechError r5 = new com.iflytek.cloud.SpeechError
            r5.<init>(r7)
            r4.b(r5)
            goto L9f
        L7d:
            r4.h = r5
            boolean r5 = r4.i
            if (r5 != 0) goto L94
            java.lang.String r5 = r4.a
            java.lang.String r6 = "oneshot"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L8e
            goto L94
        L8e:
            r5 = 5
            android.os.Message r5 = r4.obtainMessage(r8, r5, r0, r9)
            goto L98
        L94:
            android.os.Message r5 = r4.obtainMessage(r8, r0, r0, r9)
        L98:
            boolean r6 = r4.hasMessages(r8)
            if (r6 == 0) goto L6e
            goto L6b
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.a.c.b.MsgProcCallBack(char[], int, int, int, byte[]):int");
    }

    public int a() {
        return this.d;
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
        } else if (i == 3) {
            f();
        } else {
            if (i != 4) {
                return;
            }
            c(message);
        }
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(SpeechError speechError) {
        a aVar;
        String str;
        com.iflytek.cloud.a.g.a.a.a("onSessionEnd");
        g();
        if (this.a.equals("oneshot") && this.h && this.g.size() <= 0 && speechError == null && t().a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
            speechError = new SpeechError(ErrorCode.MSP_ERROR_NO_DATA);
        }
        if (this.s) {
            aVar = this.e;
            str = "user abort";
        } else {
            aVar = this.e;
            if (speechError != null) {
                str = "error" + speechError.getErrorCode();
            } else {
                str = Constant.CASH_LOAD_SUCCESS;
            }
        }
        aVar.a(str);
        super.a(speechError);
        if (this.b != null) {
            if (this.s) {
                com.iflytek.cloud.a.g.a.a.a("WakeuperListener#onCancel");
                return;
            }
            com.iflytek.cloud.a.g.a.a.a("WakeuperListener#onEnd");
            if (speechError != null) {
                this.b.onError(speechError);
            }
        }
    }

    public synchronized void a(WakeuperListener wakeuperListener) {
        this.b = wakeuperListener;
        com.iflytek.cloud.a.g.a.a.a("startListening called");
        c();
    }

    @Override // com.iflytek.cloud.c.f.a
    public void a(byte[] bArr, int i, int i2) {
        if (bArr.length < i2 || bArr == null || i2 <= 0 || i2 <= 0 || !r()) {
            return;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        d(obtainMessage(2, bArr2));
    }

    protected void a(byte[] bArr, boolean z) throws SpeechError {
        this.e.a(bArr, bArr.length);
    }

    public synchronized boolean a(boolean z) {
        com.iflytek.cloud.a.g.a.a.a("stopListening, current status is :" + s() + " usercancel : " + z);
        if (this.a.equals("enroll")) {
            this.c = z;
        } else if (this.a.equals("oneshot") && this.h) {
            g();
            this.c = z;
        } else {
            b(false);
        }
        a(3);
        return true;
    }

    protected void b() throws Exception {
        com.iflytek.cloud.a.g.a.a.a("start connecting");
        this.h = false;
        int a = t().a("record_read_rate", 40);
        if (this.d != -1 && r()) {
            com.iflytek.cloud.a.g.a.a.a("start  record");
            if (this.f == null) {
                f fVar = new f(q(), a, this.d);
                this.f = fVar;
                fVar.a(this);
            }
            if (this.b != null) {
                this.b.onBeginOfSpeech();
            }
        }
        a(1, a.EnumC0017a.max, false, 0);
    }

    protected void b(Message message) throws Exception {
        byte[] bArr = (byte[]) message.obj;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        a(bArr, true);
    }

    @Override // com.iflytek.cloud.c.f.a
    public void b(SpeechError speechError) {
        c(speechError);
    }

    @Override // com.iflytek.cloud.a.d.a
    public void b(boolean z) {
        if (z && r() && this.b != null) {
            this.b.onError(new SpeechError(ErrorCode.ERROR_INTERRUPT));
        }
        com.iflytek.cloud.a.g.a.a.a(Constant.CASH_LOAD_CANCEL);
        g();
        if (s() == a.b.recording) {
            this.c = true;
        }
        super.b(z);
    }

    void c(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        boolean z;
        int i = message.arg1;
        byte[] bArr = (byte[]) message.obj;
        if (i == 0) {
            z = false;
        } else {
            if (i == 2) {
                throw new SpeechError(ErrorCode.ERROR_FILE_ACCESS);
            }
            if (i != 5) {
                return;
            } else {
                z = true;
            }
        }
        a(z, bArr, message.arg2);
    }

    @Override // com.iflytek.cloud.c.f.a
    public void c(boolean z) {
    }

    protected void d() throws Exception {
        if (this.e.a == null) {
            int a = this.e.a(this.r, this.a, this);
            if (a == 0 && this.e.a != null) {
                if (r()) {
                    MSC.QIVWRegisterNotify(this.e.a, "MsgProcCallBack", this);
                    a(a.b.recording);
                    return;
                }
                return;
            }
            int i = this.j + 1;
            this.j = i;
            if (i > 40) {
                throw new SpeechError(a);
            }
            if (r()) {
                Thread.sleep(15L);
                a(1, a.EnumC0017a.max, false, 0);
            }
        }
    }

    public void e() {
        if (a.b.recording == s()) {
            a(false);
        }
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void h() {
        this.a = t().b("sst", "wakeup");
        this.i = t().a(SpeechConstant.KEEP_ALIVE, false);
        this.d = t().a(SpeechConstant.AUDIO_SOURCE, 1);
        super.h();
    }

    @Override // com.iflytek.cloud.a.d.a
    public boolean i() {
        return false;
    }

    @Override // com.iflytek.cloud.c.f.a
    public void k() {
    }
}
