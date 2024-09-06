package u.aly;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: StatTracer.java */
/* loaded from: classes.dex */
public class ab implements s {
    private static final String h = "successful_request";
    private static final String i = "failed_requests ";
    private static final String j = "last_request_spent_ms";
    private static final String k = "last_request_time";
    private static final String l = "first_activate_time";
    private static final String m = "last_req";
    public int a;
    public int b;
    public long c;
    private int e;
    private Context n;
    private final int d = 3600000;
    private long f = 0;
    private long g = 0;

    public ab(Context context) {
        b(context);
    }

    private void b(Context context) {
        this.n = context.getApplicationContext();
        SharedPreferences a = y.a(context);
        this.a = a.getInt(h, 0);
        this.b = a.getInt(i, 0);
        this.e = a.getInt(j, 0);
        this.c = a.getLong(k, 0L);
        this.f = a.getLong(m, 0L);
    }

    public int e() {
        int i2 = this.e;
        if (i2 > 3600000) {
            return 3600000;
        }
        return i2;
    }

    public boolean f() {
        return ((this.c > 0L ? 1 : (this.c == 0L ? 0 : -1)) == 0) && (com.umeng.analytics.h.a(this.n).g() ^ true);
    }

    public void g() {
        this.a++;
        this.c = this.f;
    }

    public void h() {
        this.b++;
    }

    public void i() {
        this.f = System.currentTimeMillis();
    }

    public void j() {
        this.e = (int) (System.currentTimeMillis() - this.f);
    }

    public void k() {
        y.a(this.n).edit().putInt(h, this.a).putInt(i, this.b).putInt(j, this.e).putLong(k, this.c).putLong(m, this.f).commit();
    }

    public void l() {
        y.a(this.n).edit().putLong(l, System.currentTimeMillis()).commit();
    }

    public boolean m() {
        if (this.g == 0) {
            this.g = y.a(this.n).getLong(l, 0L);
        }
        return this.g == 0;
    }

    public long n() {
        return m() ? System.currentTimeMillis() : this.g;
    }

    public long o() {
        return this.f;
    }

    public static as a(Context context) {
        SharedPreferences a = y.a(context);
        as asVar = new as();
        asVar.c(a.getInt(i, 0));
        asVar.d(a.getInt(j, 0));
        asVar.a(a.getInt(h, 0));
        return asVar;
    }

    @Override // u.aly.s
    public void a() {
        i();
    }

    @Override // u.aly.s
    public void b() {
        j();
    }

    @Override // u.aly.s
    public void c() {
        g();
    }

    @Override // u.aly.s
    public void d() {
        h();
    }
}
