package com.iflytek.cloud;

import android.content.Context;

/* loaded from: classes.dex */
public class VoiceWakeuper extends com.iflytek.cloud.a.d.d {
    private static VoiceWakeuper a;
    private com.iflytek.cloud.d.a.j c;

    private VoiceWakeuper(Context context, InitListener initListener) {
        this.c = null;
        this.c = new com.iflytek.cloud.d.a.j(context);
    }

    public static synchronized VoiceWakeuper createWakeuper(Context context, InitListener initListener) {
        VoiceWakeuper voiceWakeuper;
        synchronized (VoiceWakeuper.class) {
            if (a == null) {
                a = new VoiceWakeuper(context, initListener);
            }
            voiceWakeuper = a;
        }
        return voiceWakeuper;
    }

    public static VoiceWakeuper getWakeuper() {
        return a;
    }

    public void cancel() {
        this.c.cancel(false);
    }

    public boolean destroy() {
        com.iflytek.cloud.d.a.j jVar = this.c;
        boolean destroy = jVar != null ? jVar.destroy() : true;
        if (destroy) {
            a = null;
        }
        return destroy;
    }

    @Override // com.iflytek.cloud.a.d.d
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public boolean isListening() {
        return this.c.f();
    }

    @Override // com.iflytek.cloud.a.d.d
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public void startListening(WakeuperListener wakeuperListener) {
        this.c.setParameter("params", null);
        this.c.setParameter(this.b);
        this.c.a(wakeuperListener);
    }

    public void stopListening() {
        this.c.e();
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        com.iflytek.cloud.d.a.j jVar = this.c;
        if (jVar != null && jVar.f()) {
            return this.c.a(bArr, i, i2);
        }
        com.iflytek.cloud.a.g.a.a.b("VoiceWakeup writeAudio failed, is not running");
        return ErrorCode.ERROR_ENGINE_CALL_FAIL;
    }
}
