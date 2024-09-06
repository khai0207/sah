package u.aly;

import android.content.Context;
import com.kqg.main.constant.KV;
import java.util.Arrays;
import java.util.List;
import u.aly.g;

/* compiled from: Defcon.java */
/* loaded from: classes.dex */
public class am implements x {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final long e = 14400000;
    private static final long f = 28800000;
    private static final long g = 86400000;
    private static am j;
    private int h = 0;
    private final long i = KV.GET_CODE_INTERVAL;

    public static synchronized am a(Context context) {
        am amVar;
        synchronized (am.class) {
            if (j == null) {
                j = new am();
                j.a(g.a(context).b().a(0));
            }
            amVar = j;
        }
        return amVar;
    }

    private am() {
    }

    public bp a(Context context, bp bpVar) {
        if (bpVar == null) {
            return null;
        }
        int i = this.h;
        if (i == 1) {
            bpVar.a((List<be>) null);
        } else if (i == 2) {
            bpVar.b(Arrays.asList(b(context)));
            bpVar.a((List<be>) null);
        } else if (i == 3) {
            bpVar.b((List<bn>) null);
            bpVar.a((List<be>) null);
        }
        return bpVar;
    }

    public bn b(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        bn bnVar = new bn();
        bnVar.a(aa.g(context));
        bnVar.a(currentTimeMillis);
        bnVar.b(currentTimeMillis + KV.GET_CODE_INTERVAL);
        bnVar.c(KV.GET_CODE_INTERVAL);
        return bnVar;
    }

    public long a() {
        int i = this.h;
        return i != 1 ? i != 2 ? i != 3 ? 0L : 86400000L : f : e;
    }

    public long b() {
        return this.h == 0 ? 0L : 300000L;
    }

    public void a(int i) {
        if (i < 0 || i > 3) {
            return;
        }
        this.h = i;
    }

    public boolean c() {
        return this.h != 0;
    }

    @Override // u.aly.x
    public void a(g.a aVar) {
        a(aVar.a(0));
    }
}
