package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.a.d.e;

/* loaded from: classes.dex */
public class DataDownloader extends com.iflytek.cloud.a.d.e {
    public DataDownloader(Context context) {
        super(context);
    }

    @Override // com.iflytek.cloud.a.d.e
    protected boolean a_() {
        return true;
    }

    public void downloadData(SpeechListener speechListener) {
        this.d = new com.iflytek.cloud.a.e.b(this.a, this.b, a("download"));
        ((com.iflytek.cloud.a.e.b) this.d).a(new e.a(speechListener));
    }
}
