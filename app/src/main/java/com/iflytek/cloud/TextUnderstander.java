package com.iflytek.cloud;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.a.d.d;
import com.iflytek.msc.MSC;
import com.iflytek.speech.TextUnderstanderAidl;

/* loaded from: classes.dex */
public class TextUnderstander extends com.iflytek.cloud.a.d.d {
    private static TextUnderstander d;
    private com.iflytek.cloud.d.a.i a;
    private TextUnderstanderAidl c;
    private InitListener f;
    private a e = null;
    private Handler g = new m(this, Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    final class a implements TextUnderstanderListener {
        private TextUnderstanderListener b;
        private com.iflytek.speech.TextUnderstanderListener c;
        private Handler d = new o(this, Looper.getMainLooper());

        public a(TextUnderstanderListener textUnderstanderListener) {
            this.b = null;
            this.c = null;
            this.b = textUnderstanderListener;
            this.c = new n(this, TextUnderstander.this);
        }

        @Override // com.iflytek.cloud.TextUnderstanderListener
        public void onError(SpeechError speechError) {
            this.d.sendMessage(this.d.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.TextUnderstanderListener
        public void onResult(UnderstanderResult understanderResult) {
            this.d.sendMessage(this.d.obtainMessage(4, understanderResult));
        }
    }

    protected TextUnderstander(Context context, InitListener initListener) {
        this.a = null;
        this.c = null;
        this.f = null;
        this.f = initListener;
        if (MSC.isLoaded()) {
            this.a = new com.iflytek.cloud.d.a.i(context);
        }
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.a() && utility.getEngineMode() != d.a.MSC) {
            this.c = new TextUnderstanderAidl(context.getApplicationContext(), initListener);
        } else if (initListener != null) {
            Message.obtain(this.g, 0, 0, 0, null).sendToTarget();
        }
    }

    public static synchronized TextUnderstander createTextUnderstander(Context context, InitListener initListener) {
        TextUnderstander textUnderstander;
        synchronized (TextUnderstander.class) {
            if (d == null) {
                d = new TextUnderstander(context, initListener);
            }
            textUnderstander = d;
        }
        return textUnderstander;
    }

    public static TextUnderstander getTextUnderstander() {
        return d;
    }

    protected void a(Context context) {
        TextUnderstanderAidl textUnderstanderAidl;
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility == null || !utility.a() || utility.getEngineMode() == d.a.MSC) {
            if (this.f == null || (textUnderstanderAidl = this.c) == null) {
                return;
            }
            textUnderstanderAidl.destory();
            this.c = null;
            return;
        }
        TextUnderstanderAidl textUnderstanderAidl2 = this.c;
        if (textUnderstanderAidl2 != null && !textUnderstanderAidl2.isAvailable()) {
            this.c.destory();
            this.c = null;
        }
        this.c = new TextUnderstanderAidl(context.getApplicationContext(), this.f);
    }

    public void cancel() {
        com.iflytek.cloud.d.a.i iVar = this.a;
        if (iVar == null || !iVar.e()) {
            TextUnderstanderAidl textUnderstanderAidl = this.c;
            if (textUnderstanderAidl == null || !textUnderstanderAidl.isUnderstanding()) {
                com.iflytek.cloud.a.g.a.a.b("SpeechUnderstander cancel failed, is not running");
            } else {
                this.c.cancel(this.e.c);
            }
        } else {
            this.a.cancel(false);
        }
        this.a.cancel(false);
    }

    public boolean destroy() {
        TextUnderstanderAidl textUnderstanderAidl = this.c;
        if (textUnderstanderAidl != null) {
            textUnderstanderAidl.destory();
            this.c = null;
        }
        com.iflytek.cloud.d.a.i iVar = this.a;
        boolean destroy = iVar != null ? iVar.destroy() : true;
        if (destroy) {
            d = null;
        }
        return destroy;
    }

    @Override // com.iflytek.cloud.a.d.d
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public boolean isUnderstanding() {
        com.iflytek.cloud.d.a.i iVar = this.a;
        if (iVar != null && iVar.e()) {
            return true;
        }
        TextUnderstanderAidl textUnderstanderAidl = this.c;
        return textUnderstanderAidl != null && textUnderstanderAidl.isUnderstanding();
    }

    @Override // com.iflytek.cloud.a.d.d
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int understandText(String str, TextUnderstanderListener textUnderstanderListener) {
        d.a a2 = a(SpeechConstant.ENG_NLU, this.c);
        com.iflytek.cloud.a.g.a.a.a("start engine mode = " + a2.toString());
        if (a2 != d.a.PLUS) {
            com.iflytek.cloud.d.a.i iVar = this.a;
            if (iVar == null) {
                return 21001;
            }
            iVar.setParameter(this.b);
            return this.a.a(str, textUnderstanderListener);
        }
        TextUnderstanderAidl textUnderstanderAidl = this.c;
        if (textUnderstanderAidl == null) {
            return 21001;
        }
        textUnderstanderAidl.setParameter("params", null);
        this.c.setParameter("params", this.b.toString());
        a aVar = new a(textUnderstanderListener);
        this.e = aVar;
        return this.c.understandText(str, aVar.c);
    }
}
