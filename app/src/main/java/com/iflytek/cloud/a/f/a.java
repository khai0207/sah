package com.iflytek.cloud.a.f;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.a.d.a;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class a extends com.iflytek.cloud.a.d.a {
    public static int a;
    public static int b;
    private String c;
    private i d;
    private b e;
    private ArrayList<byte[]> f;
    private int g;
    private int h;
    private StringBuilder i;
    private boolean j;
    private int k;
    private int l;

    public a(Context context, com.iflytek.cloud.b.a aVar, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.c = "";
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = false;
        this.k = 0;
        this.l = 0;
        this.d = new i();
        this.f = new ArrayList<>();
        this.i = new StringBuilder();
        a(aVar);
    }

    private void f() {
        this.e.a(this.f, Math.min(99, (this.g * 100) / this.c.length()), this.h, this.g, this.i.toString());
        StringBuilder sb = this.i;
        sb.delete(0, sb.length());
        this.f = new ArrayList<>();
        this.h = Math.min(this.g + 1, this.c.length() - 1);
    }

    protected void a() throws Exception {
        com.iflytek.cloud.a.g.a.a.a("tts msg start:" + System.currentTimeMillis());
        String e = t().e(SpeechConstant.ENGINE_TYPE);
        boolean a2 = t().a(SpeechConstant.NET_CHECK, true);
        if (SpeechConstant.TYPE_CLOUD.equals(e) && a2) {
            com.iflytek.cloud.a.g.h.a(this.r);
        }
        a(1);
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void a(Message message) throws Exception {
        int i = message.what;
        if (i == 0) {
            a();
        } else if (i == 1) {
            b();
        } else {
            if (i != 5) {
                return;
            }
            d();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0068  */
    @Override // com.iflytek.cloud.a.d.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void a(com.iflytek.cloud.SpeechError r4) {
        /*
            r3 = this;
            com.iflytek.cloud.a.f.i r0 = r3.d
            java.lang.String r1 = "upflow"
            int r0 = r0.b(r1)
            com.iflytek.cloud.a.f.a.a = r0
            com.iflytek.cloud.a.f.i r0 = r3.d
            java.lang.String r1 = "downflow"
            int r0 = r0.b(r1)
            com.iflytek.cloud.a.f.a.b = r0
            r3.e()
            java.lang.String r0 = "QTTSSessionEnd"
            r1 = 0
            com.iflytek.cloud.a.g.a.b.a(r0, r1)
            com.iflytek.cloud.a.f.b r0 = r3.e
            if (r0 != 0) goto L29
            com.iflytek.cloud.a.f.i r0 = r3.d
            java.lang.String r1 = "user abort"
        L25:
            r0.a(r1)
            goto L61
        L29:
            com.iflytek.cloud.a.f.i r0 = r3.d
            if (r4 == 0) goto L5e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "error"
            r1.append(r2)
            int r2 = r4.getErrorCode()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.a(r1)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "QTts Error Code = "
            r0.append(r1)
            int r1 = r4.getErrorCode()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.iflytek.cloud.a.g.a.a.a(r0)
            goto L61
        L5e:
            java.lang.String r1 = "success"
            goto L25
        L61:
            super.a(r4)
            com.iflytek.cloud.a.f.b r0 = r3.e
            if (r0 == 0) goto L7c
            boolean r0 = r3.s
            if (r0 == 0) goto L72
            java.lang.String r4 = "MscSynthesizer#onCancel"
            com.iflytek.cloud.a.g.a.a.a(r4)
            goto L7c
        L72:
            java.lang.String r0 = "MscSynthesizer#onEnd"
            com.iflytek.cloud.a.g.a.a.a(r0)
            com.iflytek.cloud.a.f.b r0 = r3.e
            r0.a(r4)
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.a.f.a.a(com.iflytek.cloud.SpeechError):void");
    }

    public void a(String str, b bVar) {
        this.c = str;
        this.e = bVar;
        if (str == null || TextUtils.isEmpty(str)) {
            this.e.a(new SpeechError(ErrorCode.ERROR_EMPTY_UTTERANCE));
        } else {
            this.j = t().a(SpeechConstant.TTS_SPELL_INFO, false);
            c();
        }
    }

    protected void b() throws Exception {
        com.iflytek.cloud.a.g.a.b.a("QTTSSessionBegin", null);
        int a2 = this.d.a(this.r, null, this);
        com.iflytek.cloud.a.g.a.b.a("QTTSSessionBeginEnd", null);
        if (a2 != 0) {
            int i = this.l + 1;
            this.l = i;
            if (i > 40) {
                throw new SpeechError(a2);
            }
            if (r()) {
                a(1, a.EnumC0017a.normal, false, 15);
                return;
            }
            return;
        }
        com.iflytek.cloud.a.g.a.b.a("QTTSTextPut", null);
        com.iflytek.cloud.a.g.a.b.b("QTTSTextLen", "" + this.c.length());
        this.d.a(this.c.getBytes(o()));
        a(a.b.waitresult);
        a(5);
        m();
    }

    @Override // com.iflytek.cloud.a.d.a
    public void b(boolean z) {
        b bVar;
        if (z && r() && (bVar = this.e) != null) {
            bVar.a(new SpeechError(ErrorCode.ERROR_INTERRUPT));
        }
        super.b(z);
    }

    protected void d() throws Exception {
        int i;
        if (this.d.d()) {
            b bVar = this.e;
            if (bVar != null) {
                bVar.a(this.f, 100, this.h, this.c.length() - 1, this.i.toString());
            }
            c(null);
            return;
        }
        byte[] a2 = this.d.a();
        if (a2 == null || this.e == null) {
            a(5, a.EnumC0017a.normal, false, 10);
        } else {
            com.iflytek.cloud.a.g.a.b.a("QTTSAudioGet", "" + a2.length);
            int b2 = (this.d.b() / 2) + (-1);
            if (this.j) {
                String c = this.d.c();
                if (!TextUtils.isEmpty(c)) {
                    this.i.append(c);
                    this.i.append("#\n");
                }
            }
            if (this.k > 0 && (i = this.g) != 0 && b2 != i && this.f.size() > 0) {
                f();
            }
            m();
            this.g = b2;
            this.f.add(a2);
            if (this.k <= 0) {
                f();
            }
            a(5, a.EnumC0017a.normal, false, 0);
        }
        e();
    }

    public String e() {
        return this.d.e();
    }

    @Override // com.iflytek.cloud.a.d.a
    protected void h() {
        com.iflytek.cloud.b.a t;
        int i;
        if (SpeechConstant.TYPE_LOCAL.equals(t().e(SpeechConstant.ENGINE_TYPE))) {
            t = t();
            i = 100;
        } else {
            t = t();
            i = 2000;
        }
        this.k = t.a(SpeechConstant.TTS_BUFFER_TIME, i);
        if (this.k <= 0) {
            com.iflytek.cloud.a.g.a.b.a("QTTSRealTime", null);
        }
        super.h();
    }

    @Override // com.iflytek.cloud.a.d.a
    public String o() {
        return t().b(SpeechConstant.TEXT_ENCODING, "unicode");
    }
}
