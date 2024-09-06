package com.netease.nimlib.o.a;

/* compiled from: PriorityRunnable.java */
/* loaded from: classes.dex */
public class a implements Runnable {
    private static int a;
    private Runnable b;
    private int c;
    private int d;

    public a(Runnable runnable, int i) {
        int i2 = a;
        a = i2 + 1;
        this.d = i2;
        this.b = runnable;
        this.c = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.b;
        if (runnable != null) {
            runnable.run();
        }
    }

    public static int a(a aVar, a aVar2) {
        int i = aVar.c;
        int i2 = aVar2.c;
        return i != i2 ? i2 - i : aVar.d - aVar2.d;
    }
}
