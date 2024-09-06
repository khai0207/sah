package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.h;
import java.io.File;
import java.io.FileInputStream;
import u.aly.cr;

/* compiled from: Sender.java */
/* loaded from: classes.dex */
public class z {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private e d;
    private g e;
    private Context g;
    private ab h;
    private u i;
    private bp j;
    private boolean l;
    private final int f = 1;
    private boolean k = false;

    public z(Context context, ab abVar) {
        this.d = e.a(context);
        this.e = g.a(context);
        this.g = context;
        this.h = abVar;
        u uVar = new u(context);
        this.i = uVar;
        uVar.a(this.h);
    }

    public void a(bp bpVar) {
        this.j = bpVar;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public void a(x xVar) {
        this.e.a(xVar);
    }

    public void a() {
        if (this.j != null) {
            c();
        } else {
            b();
        }
    }

    private void b() {
        com.umeng.analytics.h.a(this.g).h().a(new h.b() { // from class: u.aly.z.1
            @Override // com.umeng.analytics.h.b
            public void a(File file) {
            }

            @Override // com.umeng.analytics.h.b
            public boolean b(File file) {
                FileInputStream fileInputStream;
                FileInputStream fileInputStream2 = null;
                try {
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        byte[] b2 = bu.b(fileInputStream);
                        bu.c(fileInputStream);
                        byte[] a2 = z.this.i.a(b2);
                        int a3 = a2 == null ? 1 : z.this.a(a2);
                        if (a3 == 2 && z.this.h.m()) {
                            z.this.h.l();
                        }
                        return z.this.l || a3 != 1;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream;
                        bu.c(fileInputStream2);
                        throw th;
                    }
                } catch (Exception unused) {
                    return false;
                }
            }

            @Override // com.umeng.analytics.h.b
            public void c(File file) {
                z.this.h.k();
            }
        });
    }

    private void c() {
        c b2;
        this.d.a();
        bp bpVar = this.j;
        bpVar.a(this.d.b());
        byte[] b3 = b(bpVar);
        if (b3 == null) {
            bv.d("message is null");
            return;
        }
        if (!this.k) {
            Context context = this.g;
            b2 = c.a(context, AnalyticsConfig.getAppkey(context), b3);
        } else {
            Context context2 = this.g;
            b2 = c.b(context2, AnalyticsConfig.getAppkey(context2), b3);
        }
        byte[] c2 = b2.c();
        com.umeng.analytics.h.a(this.g).f();
        byte[] a2 = this.i.a(c2);
        int a3 = a2 == null ? 1 : a(a2);
        if (a3 == 1) {
            if (!this.l) {
                com.umeng.analytics.h.a(this.g).b(c2);
            }
            bv.e("connection error");
        } else if (a3 != 2) {
            if (a3 != 3) {
                return;
            }
            this.h.k();
        } else {
            if (this.h.m()) {
                this.h.l();
            }
            this.d.d();
            this.h.k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(byte[] bArr) {
        bl blVar = new bl();
        try {
            new cc(new cr.a()).a(blVar, bArr);
            if (blVar.a == 1) {
                this.e.b(blVar.j());
                this.e.d();
            }
            bv.c("send log:" + blVar.f());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blVar.a == 1 ? 2 : 3;
    }

    private byte[] b(bp bpVar) {
        if (bpVar == null) {
            return null;
        }
        try {
            byte[] a2 = new ci().a(bpVar);
            if (bv.a) {
                bv.b(bpVar.toString());
            }
            return a2;
        } catch (Exception e) {
            bv.e("Fail to serialize log ...", e);
            return null;
        }
    }
}
