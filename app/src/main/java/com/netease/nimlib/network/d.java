package com.netease.nimlib.network;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: NetworkChecker.java */
/* loaded from: classes.dex */
public class d {
    private ScheduledExecutorService a = null;
    private ExecutorService b = null;
    private c c = null;

    private boolean e() {
        c cVar = this.c;
        return cVar != null && cVar.a();
    }

    public void a(int i, String str, int i2, String str2, int i3, int i4, b bVar) {
        try {
            if (e()) {
                return;
            }
            b();
            if (i <= 3) {
                i = 3;
            }
            this.c = new c(str, i2, str2, i3, i4, bVar);
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() { // from class: com.netease.nimlib.network.-$$Lambda$d$6cwHZaM6qDZQp-Z47br619w5rdc
                @Override // java.util.concurrent.ThreadFactory
                public final Thread newThread(Runnable runnable) {
                    Thread b;
                    b = d.b(runnable);
                    return b;
                }
            });
            this.a = scheduledThreadPoolExecutor;
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(this.c, 0L, i, TimeUnit.SECONDS);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkChecker", "startSchedule error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread b(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("NetworkChecker_ScheduledThreadPool");
        return thread;
    }

    public boolean a() {
        if (this.a != null) {
            return !r0.isShutdown();
        }
        return false;
    }

    public void b() {
        try {
            if (this.a != null) {
                if (this.c != null) {
                    this.c.b();
                }
                this.a.shutdown();
                this.a = null;
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkChecker", "stopSchedule error", th);
        }
    }

    public void a(String str, int i, String str2, int i2, int i3, b bVar) {
        try {
            if (this.b == null || this.b.isShutdown()) {
                this.b = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(3), new ThreadFactory() { // from class: com.netease.nimlib.network.-$$Lambda$d$1NW_RFEEQAX7Nr9ASC_Antqqm64
                    @Override // java.util.concurrent.ThreadFactory
                    public final Thread newThread(Runnable runnable) {
                        Thread a;
                        a = d.a(runnable);
                        return a;
                    }
                }, new ThreadPoolExecutor.DiscardOldestPolicy());
            }
            this.b.execute(new c(str, i, str2, i2, i3, bVar));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkChecker", "startOnce error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread a(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("NetworkChecker_SingleThreadPool");
        return thread;
    }

    public void c() {
        try {
            if (this.b != null) {
                this.b.shutdown();
                this.b = null;
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkChecker", "stopOnce error", th);
        }
    }

    public void d() {
        b();
        c();
    }
}
