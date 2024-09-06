package com.netease.nimlib.push.net;

import android.os.Handler;
import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* compiled from: IKeepAlive.java */
/* loaded from: classes.dex */
abstract class b {
    private long a;
    private long b;
    private boolean c;
    private int d;
    private boolean e;
    private Handler f;
    private Runnable g;

    protected abstract void a(long j);

    protected abstract void b();

    protected abstract void e();

    protected abstract void f();

    b() {
    }

    public void a() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.b = elapsedRealtime;
        this.a = elapsedRealtime;
        this.c = false;
        this.e = false;
        this.d = 0;
        a(c());
    }

    public void d() {
        this.a = 0L;
        this.b = 0L;
        this.c = false;
        this.e = false;
        this.d = 0;
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        b();
    }

    public void a(boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (z) {
            this.b = elapsedRealtime;
            return;
        }
        this.a = elapsedRealtime;
        this.c = false;
        this.e = false;
    }

    void g() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!this.c) {
            long c = c();
            long i = i();
            long j = this.a;
            if (elapsedRealtime - j < c) {
                long j2 = this.b;
                if (elapsedRealtime - j2 < i) {
                    this.d = 0;
                    long j3 = i - (elapsedRealtime - j2);
                    long j4 = c - (elapsedRealtime - j);
                    a(Math.max(Math.min(j3, j4), 10000L));
                    com.netease.nimlib.log.b.O(String.format("!waitingHeartBeat <= %s %s", Long.valueOf(j3), Long.valueOf(j4)));
                    return;
                }
            }
            com.netease.nimlib.log.b.O(String.format("!waitingHeartBeat > %s %s", Long.valueOf(elapsedRealtime - this.a), Long.valueOf(elapsedRealtime - this.b)));
            b(15000L);
            return;
        }
        if (this.d == 5) {
            com.netease.nimlib.log.b.O("reader idle timeout, link is not alive!");
            f();
            return;
        }
        com.netease.nimlib.log.b.O("reader idle timeout, begin to retry " + (this.d + 1) + "/5");
        this.d = this.d + 1;
        b(3000L);
    }

    private void b(long j) {
        this.c = true;
        e();
        a(j);
    }

    void h() {
        if (this.e) {
            com.netease.nimlib.log.b.O("force check heart is waiting result,no need to repeat operations");
            return;
        }
        com.netease.nimlib.log.b.O("force check heart...");
        this.e = true;
        e();
        if (this.g == null) {
            this.g = new Runnable() { // from class: com.netease.nimlib.push.net.b.1
                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.e) {
                        b.this.f();
                    }
                }
            };
        }
        if (this.f == null) {
            this.f = com.netease.nimlib.c.b.a.c().a("Keep-Alive-Force-Check");
        }
        this.f.postDelayed(this.g, 5000L);
    }

    private long c() {
        if (com.netease.nimlib.abtest.b.b()) {
            return j();
        }
        return 210000L;
    }

    private long i() {
        if (com.netease.nimlib.abtest.b.b()) {
            return j();
        }
        return 210000L;
    }

    private long j() {
        return Math.max(TimeUnit.SECONDS.toMillis(com.netease.nimlib.abtest.b.c()), 15000L);
    }
}
