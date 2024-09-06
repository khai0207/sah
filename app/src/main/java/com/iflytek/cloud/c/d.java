package com.iflytek.cloud.c;

import android.media.AudioManager;
import com.iflytek.cloud.c.c;

/* loaded from: classes.dex */
final class d implements AudioManager.OnAudioFocusChangeListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        c.a aVar;
        c.a aVar2;
        boolean z;
        c.a aVar3;
        c.a aVar4;
        if (i == -2 || i == -3 || i == -1) {
            com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "pause start");
            if (this.a.c()) {
                com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "pause success");
                this.a.l = true;
                aVar = this.a.f;
                if (aVar != null) {
                    aVar2 = this.a.f;
                    aVar2.a();
                    return;
                }
                return;
            }
            return;
        }
        if (i == 1) {
            com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "resume start");
            z = this.a.l;
            if (z) {
                this.a.l = false;
                if (this.a.d()) {
                    com.iflytek.cloud.a.g.a.a.a("PcmPlayer", "resume success");
                    aVar3 = this.a.f;
                    if (aVar3 != null) {
                        aVar4 = this.a.f;
                        aVar4.b();
                    }
                }
            }
        }
    }
}
