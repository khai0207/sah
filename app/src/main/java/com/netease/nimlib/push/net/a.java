package com.netease.nimlib.push.net;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: AlarmKeepAlive.java */
/* loaded from: classes.dex */
public abstract class a extends b {
    private static ScheduledExecutorService a = Executors.newSingleThreadScheduledExecutor();

    @Override // com.netease.nimlib.push.net.b
    public /* bridge */ /* synthetic */ void a(boolean z) {
        super.a(z);
    }

    @Override // com.netease.nimlib.push.net.b
    public /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    @Override // com.netease.nimlib.push.net.b
    public void a() {
        if (a == null) {
            a = Executors.newSingleThreadScheduledExecutor();
        }
        super.a();
    }

    @Override // com.netease.nimlib.push.net.b
    protected void a(long j) {
        b(j);
    }

    @Override // com.netease.nimlib.push.net.b
    protected void b() {
        c();
    }

    private synchronized void b(long j) {
        com.netease.nimlib.log.b.d("AlarmKeepAlive", "start keep alive alarm, delay=" + j + " executor:" + a);
        if (a == null) {
            return;
        }
        a.schedule(new Runnable() { // from class: com.netease.nimlib.push.net.-$$Lambda$a$aZnoFQU5y0gJ7efmWmEV0KpItBM
            @Override // java.lang.Runnable
            public final void run() {
                a.this.i();
            }
        }, j, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i() {
        com.netease.nimlib.log.b.d("AlarmKeepAlive", "do keep alive");
        g();
    }

    public static synchronized void c() {
        synchronized (a.class) {
            com.netease.nimlib.log.b.d("AlarmKeepAlive", "stop keep alive alarm executor:" + a);
            if (a == null) {
                return;
            }
            if (!a.isShutdown()) {
                a.shutdownNow();
            }
            a = null;
        }
    }
}
