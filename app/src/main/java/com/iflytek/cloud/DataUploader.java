package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.a.d.e;

/* loaded from: classes.dex */
public class DataUploader extends com.iflytek.cloud.a.d.e {
    public DataUploader(Context context) {
        super(context);
    }

    @Override // com.iflytek.cloud.a.d.e
    protected boolean a_() {
        return true;
    }

    public void uploadData(SpeechListener speechListener, String str, byte[] bArr) {
        this.d = new com.iflytek.cloud.a.e.b(this.a, this.b, a("upload"));
        ((com.iflytek.cloud.a.e.b) this.d).a(new e.a(speechListener), str, bArr);
    }
}
