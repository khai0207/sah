package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.app.OpenAuthTask;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.ReportPolicy;
import java.util.Iterator;
import java.util.List;
import u.aly.g;

/* compiled from: CacheImpl.java */
/* loaded from: classes.dex */
public final class l implements q, x {
    private t c;
    private com.umeng.analytics.h d;
    private ab e;
    private am f;
    private al g;
    private an h;
    private a i;
    private g.a j;
    private long l;
    private int m;
    private int n;
    private Context o;
    private final long a = 28800000;
    private final int b = OpenAuthTask.Duplex;
    private int k = 10;

    public l(Context context) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.l = 0L;
        this.m = 0;
        this.n = 0;
        this.o = context;
        this.c = new t(context);
        this.e = new ab(context);
        this.d = com.umeng.analytics.h.a(context);
        this.j = g.a(context).b();
        this.i = new a();
        this.g = al.a(this.o);
        this.f = am.a(this.o);
        this.h = an.a(this.o, this.e);
        SharedPreferences a2 = y.a(this.o);
        this.l = a2.getLong("thtstart", 0L);
        this.m = a2.getInt("gkvc", 0);
        this.n = a2.getInt("ekvc", 0);
    }

    @Override // u.aly.q
    public void a() {
        if (bt.m(this.o)) {
            e();
        } else {
            bv.b("network is unavailable");
        }
    }

    @Override // u.aly.q
    public void a(r rVar) {
        if (rVar != null) {
            this.c.a(rVar);
        }
        a(rVar instanceof bn);
    }

    @Override // u.aly.q
    public void b(r rVar) {
        this.c.a(rVar);
    }

    @Override // u.aly.q
    public void b() {
        if (this.c.b() > 0) {
            try {
                byte[] b = b(a(new int[0]));
                if (b != null) {
                    this.d.a(b);
                }
            } catch (Throwable th) {
                if (th instanceof OutOfMemoryError) {
                    this.d.f();
                }
                th.printStackTrace();
            }
        }
        y.a(this.o).edit().putLong("thtstart", this.l).putInt("gkvc", this.m).putInt("ekvc", this.n).commit();
    }

    @Override // u.aly.q
    public void c() {
        a(a(new int[0]));
    }

    private void a(boolean z) {
        boolean f = this.e.f();
        if (f) {
            this.c.a(new ap(this.e.n()));
        }
        if (b(z)) {
            e();
        } else if (f || d()) {
            b();
        }
    }

    private void a(int i) {
        a(a(i, (int) (System.currentTimeMillis() - this.e.o())));
        com.umeng.analytics.f.a(new com.umeng.analytics.g() { // from class: u.aly.l.1
            AnonymousClass1() {
            }

            @Override // com.umeng.analytics.g
            public void a() {
                l.this.a();
            }
        }, i);
    }

    /* compiled from: CacheImpl.java */
    /* renamed from: u.aly.l$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends com.umeng.analytics.g {
        AnonymousClass1() {
        }

        @Override // com.umeng.analytics.g
        public void a() {
            l.this.a();
        }
    }

    private void a(bp bpVar) {
        c a2;
        if (bpVar != null) {
            e a3 = e.a(this.o);
            a3.a();
            bpVar.a(a3.b());
            byte[] b = b(d(bpVar));
            if (b == null) {
                return;
            }
            if (f()) {
                Context context = this.o;
                a2 = c.b(context, AnalyticsConfig.getAppkey(context), b);
            } else {
                Context context2 = this.o;
                a2 = c.a(context2, AnalyticsConfig.getAppkey(context2), b);
            }
            byte[] c = a2.c();
            com.umeng.analytics.h a4 = com.umeng.analytics.h.a(this.o);
            a4.f();
            a4.b(c);
            a3.d();
        }
    }

    protected bp a(int... iArr) {
        try {
            if (TextUtils.isEmpty(AnalyticsConfig.getAppkey(this.o))) {
                bv.e("Appkey is missing ,Please check AndroidManifest.xml");
                return null;
            }
            byte[] e = com.umeng.analytics.h.a(this.o).e();
            bp a2 = e == null ? null : a(e);
            if (a2 == null && this.c.b() == 0) {
                return null;
            }
            if (a2 == null) {
                a2 = new bp();
            }
            this.c.a(a2);
            if (bv.a && a2.B()) {
                Iterator<bn> it = a2.z().iterator();
                boolean z = false;
                while (it.hasNext()) {
                    if (it.next().p() > 0) {
                        z = true;
                    }
                }
                if (!z) {
                    bv.d("missing Activities or PageViews");
                }
            }
            bp a3 = this.f.a(this.o, a2);
            if (iArr != null && iArr.length == 2) {
                at atVar = new at();
                atVar.a(new bf(iArr[0] / 1000, iArr[1]));
                a3.a(atVar);
            }
            return a3;
        } catch (Exception e2) {
            bv.e("Fail to construct message ...", e2);
            com.umeng.analytics.h.a(this.o).f();
            return null;
        }
    }

    private bp a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            bp bpVar = new bp();
            new cc().a(bpVar, bArr);
            return bpVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] b(bp bpVar) {
        if (bpVar == null) {
            return null;
        }
        try {
            byte[] a2 = new ci().a(bpVar);
            bv.b(bpVar.toString());
            return a2;
        } catch (Exception e) {
            bv.e("Fail to serialize log ...", e);
            return null;
        }
    }

    private boolean b(boolean z) {
        if (!bt.m(this.o)) {
            bv.b("network is unavailable");
            return false;
        }
        if (this.e.f()) {
            return true;
        }
        return this.i.b(z).a(z);
    }

    private boolean d() {
        return this.c.b() > this.k;
    }

    private void e() {
        try {
            if (this.d.g()) {
                z zVar = new z(this.o, this.e);
                zVar.a(this);
                if (this.f.c()) {
                    zVar.b(true);
                }
                zVar.a();
                return;
            }
            bp a2 = a(new int[0]);
            if (!c(a2)) {
                bv.e(" not legitimate!");
                return;
            }
            z zVar2 = new z(this.o, this.e);
            zVar2.a(this);
            if (this.f.c()) {
                zVar2.b(true);
            }
            zVar2.a(d(a2));
            zVar2.a(f());
            zVar2.a();
        } catch (Throwable th) {
            boolean z = th instanceof OutOfMemoryError;
            th.printStackTrace();
        }
    }

    private boolean c(bp bpVar) {
        if (bpVar != null) {
            return (bpVar.c() == null || bpVar.f() == null || bpVar.j() == null || bpVar.m() == null) ? false : true;
        }
        bv.e("No data to report");
        return false;
    }

    private bp d(bp bpVar) {
        int i;
        int size;
        List<be> u2 = bpVar.u();
        int i2 = 0;
        if (u2 == null || (size = u2.size()) <= 0) {
            i = 0;
        } else {
            int i3 = 0;
            i = 0;
            while (i2 < size) {
                i3 += u2.get(i2).q();
                i += u2.get(i2).l();
                i2++;
            }
            i2 = i3;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - this.l;
        int i4 = OpenAuthTask.Duplex;
        if (j > 28800000) {
            int i5 = i2 - 5000;
            int i6 = i - 5000;
            if (i5 > 0 || i6 > 0) {
                a(i5, i6, u2);
            }
            if (i5 > 0) {
                i2 = OpenAuthTask.Duplex;
            }
            this.m = i2;
            if (i6 > 0) {
                i = OpenAuthTask.Duplex;
            }
            this.n = i;
            this.l = currentTimeMillis;
        } else {
            int i7 = this.m;
            int i8 = i7 > 5000 ? i2 : (i7 + i2) - OpenAuthTask.Duplex;
            int i9 = this.n;
            int i10 = i9 > 5000 ? i : (i9 + i) - OpenAuthTask.Duplex;
            if (i8 > 0 || i10 > 0) {
                a(i8, i10, u2);
            }
            this.m = i8 > 0 ? OpenAuthTask.Duplex : this.m + i2;
            if (i10 <= 0) {
                i4 = this.n + i;
            }
            this.n = i4;
        }
        return bpVar;
    }

    private void a(int i, int i2, List<be> list) {
        int size = list.size();
        if (i > 0) {
            int i3 = size - 1;
            while (true) {
                if (i3 < 0) {
                    break;
                }
                List<ax> s = list.get(i3).s();
                if (s.size() >= i) {
                    int size2 = s.size() - i;
                    for (int size3 = s.size() - 1; size3 >= size2; size3--) {
                        s.remove(size3);
                    }
                } else {
                    i -= s.size();
                    s.clear();
                    i3--;
                }
            }
        }
        if (i2 > 0) {
            for (int i4 = size - 1; i4 >= 0; i4--) {
                List<ax> n = list.get(i4).n();
                if (n.size() >= i2) {
                    int size4 = n.size() - i2;
                    for (int size5 = n.size() - 1; size5 >= size4; size5--) {
                        n.remove(size5);
                    }
                    return;
                }
                i2 -= n.size();
                n.clear();
            }
        }
    }

    private boolean f() {
        int c = this.j.c(-1);
        if (c != -1) {
            return c == 1;
        }
        return AnalyticsConfig.sEncrypt;
    }

    public void b(int i) {
        a(i);
    }

    @Override // u.aly.x
    public void a(g.a aVar) {
        this.g.a(aVar);
        this.f.a(aVar);
        this.h.a(aVar);
        this.i.a(aVar);
    }

    /* compiled from: CacheImpl.java */
    /* loaded from: classes.dex */
    public class a {
        private ReportPolicy.i b;
        private int c;
        private int d;
        private int e = -1;
        private int f = -1;

        public a() {
            this.c = -1;
            this.d = -1;
            int[] a = l.this.j.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }

        protected void a(boolean z) {
            ReportPolicy.i bVar;
            int i = 0;
            if (l.this.f.c()) {
                ReportPolicy.i iVar = this.b;
                if (!((iVar instanceof ReportPolicy.b) && iVar.a())) {
                    bVar = new ReportPolicy.b(l.this.e, l.this.f);
                } else {
                    bVar = this.b;
                }
                this.b = bVar;
            } else {
                ReportPolicy.i iVar2 = this.b;
                if (!((iVar2 instanceof ReportPolicy.c) && iVar2.a())) {
                    if (z && l.this.h.a()) {
                        this.b = new ReportPolicy.c((int) l.this.h.b());
                        l lVar = l.this;
                        lVar.b((int) lVar.h.b());
                    } else if (!bv.a || !l.this.j.b()) {
                        if (l.this.g.a()) {
                            bv.b("Start A/B Test");
                            if (l.this.g.b() == 6) {
                                if (l.this.j.a()) {
                                    i = l.this.j.d(90000);
                                } else {
                                    i = this.d;
                                    if (i <= 0) {
                                        i = this.f;
                                    }
                                }
                            }
                            this.b = b(l.this.g.b(), i);
                        } else {
                            int i2 = this.e;
                            int i3 = this.f;
                            int i4 = this.c;
                            if (i4 != -1) {
                                i3 = this.d;
                                i2 = i4;
                            }
                            this.b = b(i2, i3);
                        }
                    } else {
                        bv.b("Debug: send log every 15 seconds");
                        this.b = new ReportPolicy.a(l.this.e);
                    }
                }
            }
            bv.b("Report policy : " + this.b.getClass().getSimpleName());
        }

        public ReportPolicy.i b(boolean z) {
            a(z);
            return this.b;
        }

        private ReportPolicy.i b(int i, int i2) {
            if (i == 0) {
                ReportPolicy.i iVar = this.b;
                return iVar instanceof ReportPolicy.h ? iVar : new ReportPolicy.h();
            }
            if (i == 1) {
                ReportPolicy.i iVar2 = this.b;
                return iVar2 instanceof ReportPolicy.d ? iVar2 : new ReportPolicy.d();
            }
            if (i == 4) {
                ReportPolicy.i iVar3 = this.b;
                return iVar3 instanceof ReportPolicy.g ? iVar3 : new ReportPolicy.g(l.this.e);
            }
            if (i == 5) {
                ReportPolicy.i iVar4 = this.b;
                return iVar4 instanceof ReportPolicy.j ? iVar4 : new ReportPolicy.j(l.this.o);
            }
            if (i != 6) {
                if (i == 8) {
                    ReportPolicy.i iVar5 = this.b;
                    return iVar5 instanceof ReportPolicy.k ? iVar5 : new ReportPolicy.k(l.this.e);
                }
                ReportPolicy.i iVar6 = this.b;
                return iVar6 instanceof ReportPolicy.d ? iVar6 : new ReportPolicy.d();
            }
            ReportPolicy.i iVar7 = this.b;
            if (iVar7 instanceof ReportPolicy.e) {
                ((ReportPolicy.e) iVar7).a(i2);
                return iVar7;
            }
            return new ReportPolicy.e(l.this.e, i2);
        }

        public void a(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        public void a(g.a aVar) {
            int[] a = aVar.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }
    }
}
