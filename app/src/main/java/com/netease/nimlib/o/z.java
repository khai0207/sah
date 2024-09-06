package com.netease.nimlib.o;

/* compiled from: TraceUtil.java */
/* loaded from: classes.dex */
public class z {

    /* compiled from: TraceUtil.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(long j);
    }

    public static long a() {
        return System.nanoTime();
    }

    public static long a(long j, long j2) {
        long nanoTime = (System.nanoTime() - j) / 1000000;
        if (nanoTime >= j2) {
            return nanoTime;
        }
        return 0L;
    }

    public static void a(long j, long j2, a aVar) {
        long a2 = a(j, j2);
        if (a2 <= 0 || aVar == null) {
            return;
        }
        try {
            aVar.a(a2);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("TraceUtil", th);
        }
    }

    /* compiled from: TraceUtil.java */
    /* loaded from: classes.dex */
    private static final class b implements Runnable {
        final Runnable a;
        final long b;
        final a c;
        final boolean d;
        long e;

        private b(Runnable runnable, long j, a aVar, boolean z) {
            this.a = runnable;
            this.b = j;
            this.c = aVar;
            this.d = z;
            if (z) {
                a();
            }
        }

        void a() {
            this.e = z.a();
        }

        void b() {
            z.a(this.e, this.b, this.c);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.d) {
                a();
            } else {
                b();
            }
            Runnable runnable = this.a;
            if (runnable != null) {
                runnable.run();
            }
            if (this.d) {
                return;
            }
            b();
        }
    }

    public static Runnable a(Runnable runnable, long j, a aVar) {
        return new b(runnable, j, aVar, true);
    }

    public static Runnable b(Runnable runnable, long j, a aVar) {
        return new b(runnable, j, aVar, false);
    }
}
