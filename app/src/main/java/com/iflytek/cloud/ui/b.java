package com.iflytek.cloud.ui;

import android.os.Bundle;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.ui.a.f;

/* loaded from: classes.dex */
final class b implements RecognizerListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // com.iflytek.cloud.RecognizerListener
    public void onBeginOfSpeech() {
    }

    @Override // com.iflytek.cloud.RecognizerListener
    public void onEndOfSpeech() {
        this.a.j();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    @Override // com.iflytek.cloud.RecognizerListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onError(com.iflytek.cloud.SpeechError r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L11
            com.iflytek.cloud.ui.a r0 = r1.a
            boolean r0 = com.iflytek.cloud.ui.a.e(r0)
            if (r0 != 0) goto Lb
            goto L11
        Lb:
            com.iflytek.cloud.ui.a r0 = r1.a
            com.iflytek.cloud.ui.a.a(r0, r2)
            goto L16
        L11:
            com.iflytek.cloud.ui.a r0 = r1.a
            r0.f()
        L16:
            com.iflytek.cloud.ui.a r0 = r1.a
            com.iflytek.cloud.ui.RecognizerDialogListener r0 = com.iflytek.cloud.ui.a.d(r0)
            if (r0 == 0) goto L27
            com.iflytek.cloud.ui.a r0 = r1.a
            com.iflytek.cloud.ui.RecognizerDialogListener r0 = com.iflytek.cloud.ui.a.d(r0)
            r0.onError(r2)
        L27:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.ui.b.onError(com.iflytek.cloud.SpeechError):void");
    }

    @Override // com.iflytek.cloud.RecognizerListener
    public void onEvent(int i, int i2, int i3, Bundle bundle) {
    }

    @Override // com.iflytek.cloud.RecognizerListener
    public void onResult(RecognizerResult recognizerResult, boolean z) {
        RecognizerDialogListener recognizerDialogListener;
        RecognizerDialogListener recognizerDialogListener2;
        if (z) {
            this.a.f();
        }
        recognizerDialogListener = this.a.h;
        if (recognizerDialogListener != null) {
            recognizerDialogListener2 = this.a.h;
            recognizerDialogListener2.onResult(recognizerResult, z);
        }
    }

    @Override // com.iflytek.cloud.RecognizerListener
    public void onVolumeChanged(int i) {
        int i2;
        f fVar;
        f fVar2;
        i2 = this.a.k;
        if (i2 == 1) {
            fVar = this.a.e;
            fVar.a((i + 2) / 5);
            fVar2 = this.a.e;
            fVar2.invalidate();
        }
    }
}
