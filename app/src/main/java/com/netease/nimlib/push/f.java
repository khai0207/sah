package com.netease.nimlib.push;

import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.push.net.d;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.LoginInfo;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PushClient.java */
/* loaded from: classes.dex */
public class f {
    private static f i = new f();
    private Context b;
    private com.netease.nimlib.ipc.a.a c;
    private com.netease.nimlib.biz.c.h g;
    private com.netease.nimlib.biz.c.c h;
    private AtomicInteger a = new AtomicInteger(1);
    private final a d = new a();
    private com.netease.nimlib.push.net.d e = new com.netease.nimlib.push.net.d(new d.a() { // from class: com.netease.nimlib.push.f.1
        @Override // com.netease.nimlib.push.net.d.a
        public void a() {
        }

        @Override // com.netease.nimlib.push.net.d.a
        public void b() {
        }

        @Override // com.netease.nimlib.push.net.d.a
        public void a(final int i2, final Throwable th) {
            if (!f.this.m()) {
                Context e = f.this.b != null ? f.this.b : com.netease.nimlib.c.e();
                if (e == null) {
                    return;
                }
                com.netease.nimlib.c.b.a.a(e).post(new Runnable() { // from class: com.netease.nimlib.push.f.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        b(i2, th);
                    }
                });
                return;
            }
            b(i2, th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i2, Throwable th) {
            try {
                f.this.d.a(i2, th);
            } catch (Throwable th2) {
                com.netease.nimlib.log.b.d("core", "handle connection change error", th2);
            }
        }

        @Override // com.netease.nimlib.push.net.d.a
        public void a(a.C0029a c0029a) {
            if (c0029a != null && c0029a.a != null) {
                c0029a.a.a(SystemClock.elapsedRealtime());
            }
            f.this.h.a(c0029a);
        }
    }, null);
    private com.netease.nimlib.c.b.b f = new com.netease.nimlib.c.b.b("Response", com.netease.nimlib.c.b.b.c, false);

    public boolean a() {
        return this.d.g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private f() {
        com.netease.nimlib.biz.c.h hVar = new com.netease.nimlib.biz.c.h() { // from class: com.netease.nimlib.push.f.2
            @Override // com.netease.nimlib.biz.c.h
            public boolean a(com.netease.nimlib.biz.e.a aVar) {
                return true;
            }

            @Override // com.netease.nimlib.biz.c.h
            public boolean b(com.netease.nimlib.biz.e.a aVar) {
                if (aVar == null || aVar.j() == null) {
                    return false;
                }
                try {
                    com.netease.nimlib.n.e.a(aVar.j(), aVar.r(), com.netease.nimlib.n.b.b.kSendAwaitablePacket);
                    return true;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return true;
                }
            }
        };
        this.g = hVar;
        this.h = new com.netease.nimlib.biz.c.c(this.f, hVar);
    }

    public void a(Context context) {
        if (this.a.compareAndSet(1, 2)) {
            com.netease.nimlib.log.b.O("push client startup");
            LoginInfo m = com.netease.nimlib.c.m();
            if (m == null || !m.valid()) {
                StatusCode e = com.netease.nimlib.h.e();
                if (e != null && e.wontAutoLogin()) {
                    com.netease.nimlib.log.b.e("status = " + e + ",and don't recovery login info");
                } else {
                    com.netease.nimlib.biz.d L = l.L();
                    if (L != null) {
                        com.netease.nimlib.log.b.e("push client startup and check UI client login info = " + L.b());
                        if (L.a()) {
                            com.netease.nimlib.log.b.e("push client startup and check UI client is manual logging");
                        } else {
                            com.netease.nimlib.log.b.e("push client startup and recovery login info");
                            com.netease.nimlib.c.a(L.b());
                        }
                    }
                }
            }
            this.b = context;
            this.f.a();
            this.d.a(context, this.e);
            this.a.compareAndSet(2, 3);
        }
    }

    public void b() {
        if (this.a.compareAndSet(3, 4)) {
            com.netease.nimlib.log.b.O("push client shutdown");
            this.e.c();
            this.d.b();
            this.f.b();
            com.netease.nimlib.log.b.c();
            this.a.compareAndSet(4, 1);
        }
    }

    public void a(final LoginInfo loginInfo) {
        com.netease.nimlib.c.b.a.a(this.b).post(new Runnable() { // from class: com.netease.nimlib.push.f.3
            @Override // java.lang.Runnable
            public void run() {
                f.this.d.a(loginInfo, false, false);
            }
        });
    }

    public void c() {
        com.netease.nimlib.c.b.a.a(this.b).post(new Runnable() { // from class: com.netease.nimlib.push.f.4
            @Override // java.lang.Runnable
            public void run() {
                com.netease.nimlib.log.b.O("do SDK logout...");
                f.this.d.d();
            }
        });
        com.netease.nimlib.c.b.a.a(this.b).postDelayed(new Runnable() { // from class: com.netease.nimlib.push.f.5
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(com.netease.nimlib.c.n()) && com.netease.nimlib.h.e() == StatusCode.UNLOGIN) {
                    com.netease.nimlib.log.b.O("do SDK logout, restart...");
                    f.this.d();
                    return;
                }
                com.netease.nimlib.log.b.O("do SDK logout, cancel restart!!! find current state changed to " + com.netease.nimlib.h.e() + ", account=" + com.netease.nimlib.c.n());
            }
        }, 100L);
    }

    public void a(com.netease.nimlib.push.a.c.b bVar) {
        com.netease.nimlib.ipc.e.e();
        this.d.a(bVar);
    }

    public void a(int i2, int i3, String str, int i4) {
        com.netease.nimlib.log.b.O("SDK on kick out...");
        this.d.a(i2, i3, str, i4);
        com.netease.nimlib.log.b.O("SDK on kick out, restart...");
        d();
        Context context = this.b;
        if (context == null) {
            context = com.netease.nimlib.c.e();
        }
        if (context == null) {
            return;
        }
        com.netease.nimlib.c.b.a.a(context).post(new Runnable() { // from class: com.netease.nimlib.push.f.6
            @Override // java.lang.Runnable
            public void run() {
                com.netease.nimlib.log.b.O("SDKCache.getOptions().enableLoseConnection = " + com.netease.nimlib.c.i().enableLoseConnection);
                if (com.netease.nimlib.c.i().enableLoseConnection) {
                    f.this.d.a(StatusCode.NET_BROKEN, true);
                }
            }
        });
    }

    public void a(com.netease.nimlib.biz.d.a aVar) {
        com.netease.nimlib.push.net.d dVar = this.e;
        if (dVar == null) {
            com.netease.nimlib.log.b.O("LinkClient is null when sendRequest");
            return;
        }
        aVar.i().a(com.netease.nimlib.biz.h.a(true));
        com.netease.nimlib.n.e.a(aVar);
        dVar.a(aVar);
    }

    public void a(com.netease.nimlib.ipc.a.d dVar) {
        com.netease.nimlib.push.net.d dVar2 = this.e;
        if (dVar2 == null) {
            com.netease.nimlib.log.b.O("LinkClient is null when sendPacket");
        } else {
            com.netease.nimlib.n.g.a().b(dVar);
            dVar2.a(dVar);
        }
    }

    void a(a.C0029a c0029a) {
        this.h.a(c0029a);
    }

    void d() {
        b();
        a(this.b);
    }

    public void a(final com.netease.nimlib.ipc.a.a aVar) {
        com.netease.nimlib.c.b.a.a(this.b).post(new Runnable() { // from class: com.netease.nimlib.push.f.7
            @Override // java.lang.Runnable
            public void run() {
                f.this.c = aVar;
                f.this.d.a(aVar);
                f.this.e.a(aVar.a());
                com.netease.nimlib.log.b.c();
            }
        });
    }

    public void e() {
        if (this.a.get() == 3) {
            this.e.e();
        }
    }

    public boolean f() {
        com.netease.nimlib.ipc.a.a aVar = this.c;
        if (aVar == null) {
            return true;
        }
        return aVar.a();
    }

    public void g() {
        com.netease.nimlib.log.b.c();
        this.d.c();
    }

    public void h() {
        this.e.g();
    }

    public void i() {
        if (com.netease.nimlib.h.b()) {
            return;
        }
        com.netease.nimlib.ipc.e.b();
    }

    public boolean j() {
        com.netease.nimlib.push.net.d dVar = this.e;
        return dVar != null && dVar.d();
    }

    public String k() {
        a aVar = this.d;
        if (aVar == null) {
            return null;
        }
        return aVar.a();
    }

    public static f l() {
        return i;
    }
}
