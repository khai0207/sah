package com.umeng.analytics;

/* compiled from: SafeRunnable.java */
/* loaded from: classes.dex */
public abstract class g implements Runnable {
    public abstract void a();

    @Override // java.lang.Runnable
    public void run() {
        try {
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
