package com.iflytek.cloud.a.d;

import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.msc.MSC;
import com.iflytek.speech.ISpeechModule;

/* loaded from: classes.dex */
public abstract class d {
    protected com.iflytek.cloud.b.a b = new com.iflytek.cloud.b.a();

    /* loaded from: classes.dex */
    public enum a {
        MSC,
        PLUS,
        AUTO
    }

    protected a a(String str, ISpeechModule iSpeechModule) {
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility == null) {
            return a.MSC;
        }
        if (utility.getEngineMode() != a.AUTO) {
            return utility.getEngineMode();
        }
        if (!MSC.isLoaded()) {
            return a.PLUS;
        }
        String parameter = getParameter(SpeechConstant.ENGINE_TYPE);
        return SpeechConstant.TYPE_LOCAL.equals(parameter) ? a.PLUS : SpeechConstant.TYPE_MIX.equals(parameter) ? (iSpeechModule == null || !iSpeechModule.isAvailable()) ? a.MSC : a.PLUS : ("tts".equals(str) && iSpeechModule != null && iSpeechModule.isAvailable()) ? a.PLUS : a.MSC;
    }

    public String getParameter(String str) {
        return "params".equals(str) ? this.b.toString() : this.b.e(str);
    }

    public boolean setParameter(com.iflytek.cloud.b.a aVar) {
        this.b = aVar.clone();
        return true;
    }

    public boolean setParameter(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equals("params")) {
            if (TextUtils.isEmpty(str2)) {
                this.b.a();
            } else {
                this.b.b(str2);
            }
            return true;
        }
        if (TextUtils.isEmpty(str2)) {
            return this.b.c(str).booleanValue();
        }
        this.b.a(str, str2);
        return true;
    }
}
