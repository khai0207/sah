package com.netease.nimlib.c.b;

import android.os.Build;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: TaskExecutor.java */
/* loaded from: classes.dex */
public class b implements Executor {
    public static final Executor a = new Executor() { // from class: com.netease.nimlib.c.b.b.1
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    };
    public static a b = new a(3, 5, 30000, true);
    public static a c = new a(1, 1, 30000, false);
    Comparator<Runnable> d;
    private final String e;
    private final a f;
    private ExecutorService g;

    /* compiled from: TaskExecutor.java */
    /* loaded from: classes.dex */
    public static class a {
        public int a;
        public int b;
        public int c;
        public boolean d;

        public a(int i, int i2, int i3, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = z;
        }
    }

    public b(String str, a aVar) {
        this(str, aVar, true);
    }

    public b(String str, a aVar, boolean z) {
        this.d = new Comparator<Runnable>() { // from class: com.netease.nimlib.c.b.b.2
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(Runnable runnable, Runnable runnable2) {
                return com.netease.nimlib.o.a.a.a((com.netease.nimlib.o.a.a) runnable, (com.netease.nimlib.o.a.a) runnable2);
            }
        };
        this.e = str;
        this.f = aVar;
        if (z) {
            a();
        }
    }

    public void a() {
        synchronized (this) {
            if (this.g == null || this.g.isShutdown()) {
                this.g = a(this.f);
            }
        }
    }

    public void b() {
        ExecutorService executorService;
        synchronized (this) {
            executorService = null;
            if (this.g != null) {
                ExecutorService executorService2 = this.g;
                this.g = null;
                executorService = executorService2;
            }
        }
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        executorService.shutdown();
    }

    public int c() {
        ExecutorService executorService = this.g;
        if (executorService != null && (executorService instanceof ThreadPoolExecutor)) {
            BlockingQueue<Runnable> queue = ((ThreadPoolExecutor) executorService).getQueue();
            r1 = queue != null ? queue.size() : 0;
            com.netease.nimlib.log.b.N("response queue size = " + r1);
        }
        return r1;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        a(new com.netease.nimlib.o.a.a(runnable, 0));
    }

    public void a(Runnable runnable, int i) {
        a(new com.netease.nimlib.o.a.a(runnable, i));
    }

    private void a(com.netease.nimlib.o.a.a aVar) {
        synchronized (this) {
            if (this.g != null && !this.g.isShutdown()) {
                this.g.execute(aVar);
            }
        }
    }

    private ExecutorService a(a aVar) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(aVar.a, aVar.b, aVar.c, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(11, this.d), new ThreadFactoryC0030b(this.e), new ThreadPoolExecutor.DiscardPolicy());
        a(threadPoolExecutor, aVar.d);
        return threadPoolExecutor;
    }

    /* compiled from: TaskExecutor.java */
    /* renamed from: com.netease.nimlib.c.b.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class ThreadFactoryC0030b implements ThreadFactory {
        private final ThreadGroup a;
        private final AtomicInteger b = new AtomicInteger(1);
        private final String c;

        ThreadFactoryC0030b(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.c = str + "#";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.a, runnable, this.c + this.b.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    private static final void a(ThreadPoolExecutor threadPoolExecutor, boolean z) {
        if (Build.VERSION.SDK_INT >= 9) {
            b(threadPoolExecutor, z);
        }
    }

    private static final void b(ThreadPoolExecutor threadPoolExecutor, boolean z) {
        threadPoolExecutor.allowCoreThreadTimeOut(z);
    }
}
