package com.netease.nimlib.net.b.c;

import android.os.SystemClock;
import com.netease.nimlib.net.b.a.i;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SingleThreadEventExecutor.java */
/* loaded from: classes.dex */
public abstract class h implements Executor {
    private static final String a = h.class.getSimpleName();
    private final Queue<Runnable> c;
    private final Queue<i> d;
    private final Thread e;
    private final AtomicInteger b = new AtomicInteger(1);
    private final Semaphore f = new Semaphore(0);

    protected abstract void a(boolean z);

    protected abstract void b();

    protected abstract void c();

    protected h() {
        Thread thread = new Thread(new Runnable() { // from class: com.netease.nimlib.net.b.c.h.1
            /* JADX WARN: Code restructure failed: missing block: B:101:0x02d0, code lost:
            
                r1 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:102:0x02d1, code lost:
            
                r7.a.b.set(5);
                r7.a.f.release();
             */
            /* JADX WARN: Code restructure failed: missing block: B:103:0x02ed, code lost:
            
                if (r7.a.c.isEmpty() == false) goto L75;
             */
            /* JADX WARN: Code restructure failed: missing block: B:104:0x02ef, code lost:
            
                com.netease.nimlib.log.b.e(com.netease.nimlib.net.b.c.h.a, "An event executor terminated with non-empty task queue (" + r7.a.c.size() + ')');
             */
            /* JADX WARN: Code restructure failed: missing block: B:105:0x0312, code lost:
            
                throw r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
            
                r1 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:27:0x00b5, code lost:
            
                throw r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:63:0x019a, code lost:
            
                r1 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:64:0x019b, code lost:
            
                r7.a.b.set(5);
                r7.a.f.release();
             */
            /* JADX WARN: Code restructure failed: missing block: B:65:0x01b7, code lost:
            
                if (r7.a.c.isEmpty() == false) goto L47;
             */
            /* JADX WARN: Code restructure failed: missing block: B:66:0x01b9, code lost:
            
                com.netease.nimlib.log.b.e(com.netease.nimlib.net.b.c.h.a, "An event executor terminated with non-empty task queue (" + r7.a.c.size() + ')');
             */
            /* JADX WARN: Code restructure failed: missing block: B:67:0x01dc, code lost:
            
                throw r1;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 928
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.net.b.c.h.AnonymousClass1.run():void");
            }
        });
        this.e = thread;
        thread.setName("Socket-Thread");
        this.e.setPriority(10);
        this.c = new LinkedBlockingQueue();
        this.d = new PriorityBlockingQueue();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("task");
        }
        boolean h = h();
        if (h) {
            a(runnable);
        } else {
            o();
            a(runnable);
            if (k() && b(runnable)) {
                m();
            }
        }
        a(h);
    }

    public void a(i iVar) {
        this.d.add(iVar);
        a(h());
    }

    private Runnable a() {
        return this.c.poll();
    }

    protected boolean e() {
        return !this.c.isEmpty();
    }

    private void a(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("task");
        }
        if (k()) {
            m();
        }
        this.c.add(runnable);
    }

    private boolean b(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("task");
        }
        return this.c.remove(runnable);
    }

    protected boolean f() {
        d();
        Runnable a2 = a();
        if (a2 == null) {
            return false;
        }
        do {
            try {
                a2.run();
            } catch (Throwable th) {
                com.netease.nimlib.log.b.d(a, "A task raised an exception.", th);
            }
            a2 = a();
        } while (a2 != null);
        return true;
    }

    protected long g() {
        i iVar;
        loop0: while (true) {
            iVar = null;
            while (iVar == null && this.d.size() > 0) {
                iVar = this.d.peek();
                if (iVar.c()) {
                    break;
                }
            }
            this.d.remove();
        }
        if (iVar != null) {
            return iVar.a() - SystemClock.elapsedRealtime();
        }
        return 15000L;
    }

    public boolean h() {
        return Thread.currentThread() == this.e;
    }

    public void i() {
        boolean z;
        if (k()) {
            return;
        }
        boolean h = h();
        while (!j()) {
            int i = this.b.get();
            int i2 = 4;
            if (h || i == 1 || i == 2 || i == 3) {
                z = true;
            } else {
                i2 = i;
                z = false;
            }
            if (this.b.compareAndSet(i, i2)) {
                if (i == 1) {
                    this.e.start();
                }
                if (z) {
                    a(h);
                    return;
                }
                return;
            }
        }
    }

    public boolean j() {
        return this.b.get() >= 3;
    }

    public boolean k() {
        return this.b.get() >= 4;
    }

    protected boolean l() {
        if (!j()) {
            return false;
        }
        if (!h()) {
            throw new IllegalStateException("must be invoked from an event loop");
        }
        if (f()) {
            if (k()) {
                return true;
            }
            a(true);
            return false;
        }
        if (k()) {
        }
        return true;
    }

    private void d() {
        while (true) {
            i peek = this.d.peek();
            if (peek == null) {
                return;
            }
            if (peek.a() > SystemClock.elapsedRealtime()) {
                return;
            }
            this.d.remove();
            if (!peek.c()) {
                this.c.add(peek);
            }
        }
    }

    protected static void m() {
        throw new RejectedExecutionException("event executor terminated");
    }

    private void o() {
        if (this.b.get() == 1 && this.b.compareAndSet(1, 2)) {
            this.e.start();
        }
    }
}
