package com.netease.nimlib.push.net;

import android.os.Handler;

/* compiled from: KeepAlive.java */
/* loaded from: classes.dex */
abstract class c extends b {
    private Handler a;
    private Runnable b = new Runnable() { // from class: com.netease.nimlib.push.net.c.1
        @Override // java.lang.Runnable
        public void run() {
            c.this.g();
        }
    };

    c() {
    }

    @Override // com.netease.nimlib.push.net.b
    protected synchronized void a(long j) {
        if (this.a == null) {
            this.a = com.netease.nimlib.c.b.a.c().a("Keep-Alive-Room");
        }
        this.a.postDelayed(this.b, j);
    }

    @Override // com.netease.nimlib.push.net.b
    protected synchronized void b() {
        if (this.a != null) {
            this.a.removeCallbacksAndMessages(null);
        }
    }
}
