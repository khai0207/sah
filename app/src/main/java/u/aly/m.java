package u.aly;

import android.content.Context;

/* compiled from: CacheService.java */
/* loaded from: classes.dex */
public final class m implements q {
    private static m c;
    private q a;
    private Context b;

    private m(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        this.a = new l(applicationContext);
    }

    public static synchronized m a(Context context) {
        m mVar;
        synchronized (m.class) {
            if (c == null && context != null) {
                c = new m(context);
            }
            mVar = c;
        }
        return mVar;
    }

    public void a(q qVar) {
        this.a = qVar;
    }

    @Override // u.aly.q
    public void a(final r rVar) {
        com.umeng.analytics.f.b(new com.umeng.analytics.g() { // from class: u.aly.m.1
            @Override // com.umeng.analytics.g
            public void a() {
                m.this.a.a(rVar);
            }
        });
    }

    @Override // u.aly.q
    public void b(r rVar) {
        this.a.b(rVar);
    }

    @Override // u.aly.q
    public void a() {
        com.umeng.analytics.f.b(new com.umeng.analytics.g() { // from class: u.aly.m.2
            @Override // com.umeng.analytics.g
            public void a() {
                m.this.a.a();
            }
        });
    }

    @Override // u.aly.q
    public void b() {
        com.umeng.analytics.f.b(new com.umeng.analytics.g() { // from class: u.aly.m.3
            @Override // com.umeng.analytics.g
            public void a() {
                m.this.a.b();
            }
        });
    }

    @Override // u.aly.q
    public void c() {
        com.umeng.analytics.f.c(new com.umeng.analytics.g() { // from class: u.aly.m.4
            @Override // com.umeng.analytics.g
            public void a() {
                m.this.a.c();
            }
        });
    }
}
