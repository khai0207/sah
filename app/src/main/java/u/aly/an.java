package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import u.aly.g;

/* compiled from: ImLatent.java */
/* loaded from: classes.dex */
public class an implements x {
    private static an l;
    private com.umeng.analytics.h e;
    private ab f;
    private Context k;
    private final long a = 1296000000;
    private final long b = 129600000;
    private final int c = 1800000;
    private final int d = 10000;
    private long g = 1296000000;
    private int h = 10000;
    private long i = 0;
    private long j = 0;

    public static synchronized an a(Context context, ab abVar) {
        an anVar;
        synchronized (an.class) {
            if (l == null) {
                an anVar2 = new an(context, abVar);
                l = anVar2;
                anVar2.a(g.a(context).b());
            }
            anVar = l;
        }
        return anVar;
    }

    private an(Context context, ab abVar) {
        this.k = context;
        this.e = com.umeng.analytics.h.a(context);
        this.f = abVar;
    }

    public boolean a() {
        if (this.e.g() || this.f.f()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f.o();
        if (currentTimeMillis > this.g) {
            this.i = com.umeng.analytics.b.a(this.h, c.a(this.k));
            this.j = currentTimeMillis;
            return true;
        }
        if (currentTimeMillis <= 129600000) {
            return false;
        }
        this.i = 0L;
        this.j = currentTimeMillis;
        return true;
    }

    public long b() {
        return this.i;
    }

    public long c() {
        return this.j;
    }

    @Override // u.aly.x
    public void a(g.a aVar) {
        this.g = aVar.a(1296000000L);
        int b = aVar.b(0);
        if (b == 0) {
            if (AnalyticsConfig.sLatentWindow <= 0 || AnalyticsConfig.sLatentWindow > 1800000) {
                this.h = 10000;
                return;
            } else {
                this.h = AnalyticsConfig.sLatentWindow;
                return;
            }
        }
        this.h = b;
    }
}
