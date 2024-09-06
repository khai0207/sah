package com.iflytek.cloud.d.a;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.TextUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;
import com.iflytek.cloud.a.d.e;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class i extends com.iflytek.cloud.a.d.e {

    /* loaded from: classes.dex */
    private class a implements SpeechListener {
        private TextUnderstanderListener b;

        public a(TextUnderstanderListener textUnderstanderListener) {
            this.b = textUnderstanderListener;
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onBufferReceived(byte[] bArr) {
            if (bArr != null) {
                try {
                    this.b.onResult(new UnderstanderResult(new String(bArr, "utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onCompleted(SpeechError speechError) {
            TextUnderstanderListener textUnderstanderListener = this.b;
            if (textUnderstanderListener == null || speechError == null) {
                return;
            }
            textUnderstanderListener.onError(speechError);
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onEvent(int i, Bundle bundle) {
        }
    }

    public i(Context context) {
        super(context);
    }

    public int a(String str, TextUnderstanderListener textUnderstanderListener) {
        if (TextUtils.isEmpty(getParameter("asr_sch"))) {
            setParameter("asr_sch", "1");
        }
        if (TextUtils.isEmpty(getParameter(SpeechConstant.NLP_VERSION))) {
            setParameter(SpeechConstant.NLP_VERSION, "2.0");
        }
        if (TextUtils.isEmpty(getParameter(SpeechConstant.RESULT_TYPE))) {
            setParameter(SpeechConstant.RESULT_TYPE, "json");
        }
        this.d = new com.iflytek.cloud.a.e.b(this.a, this.b, a("textunderstand"));
        ((com.iflytek.cloud.a.e.b) this.d).a(new e.a(new a(textUnderstanderListener)), str);
        return 0;
    }

    @Override // com.iflytek.cloud.a.d.e
    public void cancel(boolean z) {
        super.cancel(z);
    }

    @Override // com.iflytek.cloud.a.d.e
    public boolean destroy() {
        return super.destroy();
    }

    public boolean e() {
        return d();
    }

    @Override // com.iflytek.cloud.a.d.d
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    @Override // com.iflytek.cloud.a.d.d
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }
}
