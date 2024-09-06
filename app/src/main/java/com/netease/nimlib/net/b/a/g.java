package com.netease.nimlib.net.b.a;

import android.os.SystemClock;
import android.util.SparseArray;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

/* compiled from: ChannelPipeline.java */
/* loaded from: classes.dex */
public class g {
    private com.netease.nimlib.net.b.a.a a;
    private j b = new j(this);
    private l c;
    private com.netease.nimlib.net.b.c.f d;
    private c e;
    private i f;

    public g(com.netease.nimlib.net.b.a.a aVar, com.netease.nimlib.net.b.c.f fVar) {
        this.a = aVar;
        this.d = fVar;
        l lVar = new l(this);
        this.c = lVar;
        this.b.c = lVar;
        this.c.b = this.b;
    }

    public com.netease.nimlib.net.b.a.a a() {
        return this.a;
    }

    public void a(String str, com.netease.nimlib.net.b.c.c cVar) {
        h hVar = new h(this, str, cVar);
        cVar.a(hVar);
        hVar.c = this.c;
        hVar.b = this.c.b;
        this.c.b.c = hVar;
        this.c.b = hVar;
    }

    /* compiled from: ChannelPipeline.java */
    /* renamed from: com.netease.nimlib.net.b.a.g$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ c a;
        final /* synthetic */ SparseArray b;
        final /* synthetic */ e c;
        final /* synthetic */ com.netease.nimlib.push.net.lbs.b d;
        final /* synthetic */ long e;

        AnonymousClass1(c cVar, SparseArray sparseArray, e eVar, com.netease.nimlib.push.net.lbs.b bVar, long j) {
            r2 = cVar;
            r3 = sparseArray;
            r4 = eVar;
            r5 = bVar;
            r6 = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (g.this.e != null) {
                r2.a((Throwable) new b("Already in connection progress"));
            }
            try {
                g.this.a((SparseArray<Object>) r3, r4);
            } catch (Throwable th) {
                g.this.a.e();
                r2.a((Throwable) new b("Throwable in ChannelPipeline connect", th));
            }
            if (r5.c() != null) {
                g.this.a(r5.c(), r2, r6);
                return;
            }
            InetSocketAddress inetSocketAddress = new InetSocketAddress(r5.b, r5.c);
            r5.a(inetSocketAddress);
            g.this.a(inetSocketAddress, r2, r6);
        }
    }

    public c a(com.netease.nimlib.push.net.lbs.b bVar, SparseArray<Object> sparseArray, e eVar, long j) {
        c cVar = new c(this.a);
        this.d.execute(new Runnable() { // from class: com.netease.nimlib.net.b.a.g.1
            final /* synthetic */ c a;
            final /* synthetic */ SparseArray b;
            final /* synthetic */ e c;
            final /* synthetic */ com.netease.nimlib.push.net.lbs.b d;
            final /* synthetic */ long e;

            AnonymousClass1(c cVar2, SparseArray sparseArray2, e eVar2, com.netease.nimlib.push.net.lbs.b bVar2, long j2) {
                r2 = cVar2;
                r3 = sparseArray2;
                r4 = eVar2;
                r5 = bVar2;
                r6 = j2;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (g.this.e != null) {
                    r2.a((Throwable) new b("Already in connection progress"));
                }
                try {
                    g.this.a((SparseArray<Object>) r3, r4);
                } catch (Throwable th) {
                    g.this.a.e();
                    r2.a((Throwable) new b("Throwable in ChannelPipeline connect", th));
                }
                if (r5.c() != null) {
                    g.this.a(r5.c(), r2, r6);
                    return;
                }
                InetSocketAddress inetSocketAddress = new InetSocketAddress(r5.b, r5.c);
                r5.a(inetSocketAddress);
                g.this.a(inetSocketAddress, r2, r6);
            }
        });
        return cVar2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0030, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r1 != null) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b() {
        /*
            r3 = this;
            r0 = 0
            com.netease.nimlib.net.b.a.a r1 = r3.a     // Catch: java.lang.Throwable -> L21
            com.netease.nimlib.net.b.d.a r1 = r1.b()     // Catch: java.lang.Throwable -> L21
            r1.f()     // Catch: java.lang.Throwable -> L21
            r3.b(r0)     // Catch: java.lang.Throwable -> L21
            com.netease.nimlib.net.b.a.a r1 = r3.a     // Catch: java.lang.Throwable -> L21
            com.netease.nimlib.net.b.d.a r1 = r1.b()     // Catch: java.lang.Throwable -> L21
            boolean r1 = r1.b()     // Catch: java.lang.Throwable -> L21
            if (r1 == 0) goto L1c
            r3.g()     // Catch: java.lang.Throwable -> L21
        L1c:
            com.netease.nimlib.net.b.a.i r1 = r3.f
            if (r1 == 0) goto L2e
            goto L29
        L21:
            r1 = move-exception
            r3.b(r1)     // Catch: java.lang.Throwable -> L31
            com.netease.nimlib.net.b.a.i r1 = r3.f
            if (r1 == 0) goto L2e
        L29:
            r1.b()
            r3.f = r0
        L2e:
            r3.e = r0
            return
        L31:
            r1 = move-exception
            com.netease.nimlib.net.b.a.i r2 = r3.f
            if (r2 == 0) goto L3b
            r2.b()
            r3.f = r0
        L3b:
            r3.e = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.net.b.a.g.b():void");
    }

    public void c() {
        if (this.d.h()) {
            this.c.f().a(new c(this.a));
        } else {
            com.netease.nimlib.net.b.e.a.a(this.d, new Runnable() { // from class: com.netease.nimlib.net.b.a.g.2
                AnonymousClass2() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    g.this.c.f().a(new c(g.this.a));
                }
            });
        }
    }

    /* compiled from: ChannelPipeline.java */
    /* renamed from: com.netease.nimlib.net.b.a.g$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.c.f().a(new c(g.this.a));
        }
    }

    public void a(Throwable th) {
        this.b.a(th);
    }

    public c a(Object obj) {
        c cVar = new c(this.a);
        com.netease.nimlib.net.b.e.a.a(this.d, new a(this.c, obj, cVar));
        return cVar;
    }

    public com.netease.nimlib.net.b.c.f d() {
        return this.d;
    }

    void a(ByteBuffer byteBuffer) {
        d c = this.b.c();
        if (c != null) {
            ((com.netease.nimlib.net.b.c.d) c.j()).a(byteBuffer);
        }
    }

    void e() {
        d c = this.b.c();
        if (c != null) {
            ((com.netease.nimlib.net.b.c.d) c.j()).l();
        }
    }

    void f() {
        d c = this.b.c();
        if (c != null) {
            ((com.netease.nimlib.net.b.c.d) c.j()).k();
        }
        i iVar = this.f;
        if (iVar != null) {
            iVar.b();
            this.f = null;
        }
    }

    public void a(SparseArray<Object> sparseArray, e eVar) throws Exception {
        this.a.a(sparseArray);
        eVar.a(this);
    }

    public void a(SocketAddress socketAddress, c cVar, long j) {
        d d = this.c.d();
        if (d != null) {
            try {
                if (((com.netease.nimlib.net.b.c.g) d.j()).a(socketAddress)) {
                    g();
                    cVar.a((Object) null);
                } else {
                    this.e = cVar;
                    a(j);
                }
            } catch (Exception e) {
                cVar.a((Throwable) new b("Throwable in ChannelPipeline doConnect", e));
            }
        }
    }

    public void b(Throwable th) {
        c cVar = this.e;
        this.e = null;
        if (cVar != null) {
            if (th != null) {
                cVar.a((Throwable) new b("Throwable in ChannelPipeline fulfillConnectFuture", th));
            } else {
                cVar.a((Object) null);
            }
        }
    }

    private void a(long j) {
        i iVar = this.f;
        if (iVar != null) {
            iVar.b();
        }
        AnonymousClass3 anonymousClass3 = new i(SystemClock.elapsedRealtime() + j) { // from class: com.netease.nimlib.net.b.a.g.3
            AnonymousClass3(long j2) {
                super(j2);
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c()) {
                    return;
                }
                g.this.b(new ConnectException("connect timeout"));
            }
        };
        this.f = anonymousClass3;
        this.d.a((i) anonymousClass3);
    }

    /* compiled from: ChannelPipeline.java */
    /* renamed from: com.netease.nimlib.net.b.a.g$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 extends i {
        AnonymousClass3(long j2) {
            super(j2);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c()) {
                return;
            }
            g.this.b(new ConnectException("connect timeout"));
        }
    }

    private void g() {
        try {
            SelectionKey f = this.a.f();
            if (f.isValid()) {
                int interestOps = f.interestOps();
                if ((interestOps & 1) == 0) {
                    f.interestOps(interestOps | 1);
                }
            }
        } catch (Exception e) {
            this.d.execute(new Runnable() { // from class: com.netease.nimlib.net.b.a.g.4
                final /* synthetic */ Exception a;

                AnonymousClass4(Exception e2) {
                    r2 = e2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    g.this.b.a(r2);
                }
            });
            this.a.e();
        }
    }

    /* compiled from: ChannelPipeline.java */
    /* renamed from: com.netease.nimlib.net.b.a.g$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ Exception a;

        AnonymousClass4(Exception e2) {
            r2 = e2;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.b.a(r2);
        }
    }

    /* compiled from: ChannelPipeline.java */
    /* loaded from: classes.dex */
    private class a implements Runnable {
        private d b;
        private Object c;
        private c d;

        public a(d dVar, Object obj, c cVar) {
            this.b = dVar;
            this.c = obj;
            this.d = cVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                a(this.b, this.c, this.d);
            } finally {
                this.b = null;
                this.c = null;
                this.d = null;
            }
        }

        protected void a(d dVar, Object obj, c cVar) {
            if (!g.this.a.b().b()) {
                cVar.a((Throwable) new b("Throwable in ChannelPipeline write", com.netease.nimlib.net.b.e.a.a(g.this.a)));
                return;
            }
            com.netease.nimlib.net.b.c.g f = dVar.f();
            if (f != null) {
                f.a(obj, cVar);
            }
        }
    }
}
