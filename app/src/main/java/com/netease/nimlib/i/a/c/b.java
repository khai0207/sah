package com.netease.nimlib.i.a.c;

/* compiled from: Waitable.java */
/* loaded from: classes.dex */
public abstract class b {
    private boolean a;

    public abstract boolean a();

    public boolean a(long j) throws InterruptedException, com.netease.nimlib.i.a.a.b {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this) {
            if (a()) {
                return true;
            }
            if (j <= 0) {
                return a();
            }
            do {
                try {
                    com.netease.nimlib.log.b.B("waiting...");
                    this.a = true;
                    wait(j);
                    com.netease.nimlib.log.b.B("wait done!");
                    if (a()) {
                        this.a = false;
                        return true;
                    }
                    j -= System.currentTimeMillis() - currentTimeMillis;
                } catch (InterruptedException e) {
                    this.a = false;
                    com.netease.nimlib.log.b.B("wait done as interrupted! e=" + e.getMessage());
                    throw e;
                }
            } while (j > 0);
            this.a = false;
            com.netease.nimlib.log.b.B("wait done as timeout!");
            throw new com.netease.nimlib.i.a.a.b("wait time out");
        }
    }

    public void b() {
        if (this.a) {
            com.netease.nimlib.log.b.B("onEvent on thread=" + Thread.currentThread().getId());
            synchronized (this) {
                if (a()) {
                    com.netease.nimlib.log.b.B("notify all...");
                    notifyAll();
                }
            }
        }
    }
}
