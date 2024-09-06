package com.netease.nimlib.m;

/* compiled from: NtpDaemon.java */
/* loaded from: classes.dex */
public class b {
    private h a;

    public synchronized boolean a() {
        return this.a != null;
    }

    public synchronized h b() {
        return this.a;
    }

    public synchronized void a(h hVar) {
        this.a = hVar;
    }

    public synchronized long c() {
        com.netease.nimlib.log.b.d("NtpDaemonCommon", String.format("getServerNow %s", this.a));
        if (this.a == null) {
            return -1L;
        }
        return this.a.b();
    }

    public synchronized long a(long j) {
        com.netease.nimlib.log.b.d("NtpDaemonCommon", String.format("getServerTimestamp %s", this.a));
        if (this.a == null) {
            return -1L;
        }
        return this.a.a(j);
    }
}
