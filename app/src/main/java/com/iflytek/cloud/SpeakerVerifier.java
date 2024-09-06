package com.iflytek.cloud;

import android.content.Context;

/* loaded from: classes.dex */
public class SpeakerVerifier extends com.iflytek.cloud.a.d.d {
    private static SpeakerVerifier a;
    private com.iflytek.cloud.d.a.a c;

    protected SpeakerVerifier(Context context, InitListener initListener) {
        this.c = null;
        this.c = new com.iflytek.cloud.d.a.a(context);
    }

    public static SpeakerVerifier createVerifier(Context context, InitListener initListener) {
        if (a == null) {
            a = new SpeakerVerifier(context, initListener);
        }
        return a;
    }

    public static SpeakerVerifier getVerifier() {
        return a;
    }

    public void cancel(boolean z) {
        this.c.cancel(z);
    }

    public boolean destroy() {
        com.iflytek.cloud.d.a.a aVar = this.c;
        boolean destroy = aVar != null ? aVar.destroy() : true;
        if (destroy) {
            a = null;
        }
        return destroy;
    }

    public String generatePassword(int i) {
        return this.c.a(i);
    }

    @Override // com.iflytek.cloud.a.d.d
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public void getPasswordList(SpeechListener speechListener) {
        this.c.setParameter("params", null);
        this.b.a(SpeechConstant.SUBJECT, SpeechConstant.ENG_IVP, true);
        this.b.a("rse", "gb2312", false);
        this.c.setParameter(this.b);
        this.c.a(speechListener);
    }

    public boolean isListening() {
        return this.c.f();
    }

    public void sendRequest(String str, String str2, SpeechListener speechListener) {
        this.c.setParameter(this.b);
        this.c.a(str, str2, speechListener);
    }

    @Override // com.iflytek.cloud.a.d.d
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public void startListening(VerifierListener verifierListener) {
        this.c.setParameter(this.b);
        this.c.a(verifierListener);
    }

    public void stopListening() {
        this.c.e();
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        com.iflytek.cloud.d.a.a aVar = this.c;
        if (aVar != null && aVar.f()) {
            return this.c.a(bArr, i, i2);
        }
        com.iflytek.cloud.a.g.a.a.b("SpeakerVerifier writeAudio failed, is not running");
        return ErrorCode.ERROR_ENGINE_CALL_FAIL;
    }
}
