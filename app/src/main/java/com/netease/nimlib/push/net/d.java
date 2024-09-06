package com.netease.nimlib.push.net;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.view.InputDeviceCompat;
import com.netease.nimlib.biz.d.a;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.h;
import com.netease.nimlib.n.b.s;
import com.netease.nimlib.n.j;
import com.netease.nimlib.n.l;
import com.netease.nimlib.net.b.a.f;
import com.netease.nimlib.o.p;
import com.netease.nimlib.o.u;
import com.netease.nimlib.push.net.e;
import com.netease.nimlib.push.packet.c.g;
import java.net.SocketException;
import java.nio.channels.UnresolvedAddressException;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: LinkClient.java */
/* loaded from: classes.dex */
public class d {
    private a a;
    private b b;
    private String c;
    private String d;
    private AtomicInteger e;
    private com.netease.nimlib.net.b.a.a f;
    private com.netease.nimlib.net.b.a.c g;
    private e h;
    private com.netease.nimlib.net.b.a i;
    private com.netease.nimlib.push.net.b j;
    private com.netease.nimlib.push.net.lbs.b k;
    private boolean l;
    private long m;
    private f n;

    /* compiled from: LinkClient.java */
    /* loaded from: classes.dex */
    public interface a {
        void a();

        void a(int i, Throwable th);

        void a(a.C0029a c0029a);

        void b();
    }

    /* compiled from: LinkClient.java */
    /* loaded from: classes.dex */
    public enum b {
        MAIN,
        ROOM,
        QCHAT
    }

    public void a(boolean z) {
        this.l = z;
        if (z) {
            this.m = SystemClock.elapsedRealtime();
        }
        com.netease.nimlib.log.b.d("LinkClient", "setAppForeground " + z + " appForegroundTime: " + this.m);
    }

    public com.netease.nimlib.push.net.lbs.b a() {
        return this.k;
    }

    public String b() {
        com.netease.nimlib.push.net.lbs.b bVar = this.k;
        return bVar != null ? bVar.toString() : "";
    }

    /* compiled from: LinkClient.java */
    /* renamed from: com.netease.nimlib.push.net.d$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends com.netease.nimlib.net.b.c.e {
        AnonymousClass1() {
        }

        @Override // com.netease.nimlib.net.b.c.b, com.netease.nimlib.net.b.c.c
        public void a(Throwable th) {
            boolean z;
            if (this.a.i() == d.this.f || d.this.f == null) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - d.this.m;
                Context e = com.netease.nimlib.c.e();
                String format = e != null ? String.format("network exception caught: %s isNetAvailable: %s isNetworkConnected: %s isAppForeground: %s", th, Boolean.valueOf(p.b(e)), Boolean.valueOf(com.netease.nimlib.network.f.a(e)), Boolean.valueOf(d.this.l)) : String.format("network exception caught: %s context is null", th);
                boolean z2 = th instanceof UnresolvedAddressException;
                if (z2) {
                    format = u.a(th.getStackTrace()) ? String.format("%s StackTrace: regular", format) : String.format("%s StackTrace: %s", format, u.b(th.getStackTrace()));
                }
                com.netease.nimlib.log.b.e("LinkClient", format, th);
                th.printStackTrace();
                if (!format.contains("java.net.SocketException: Software caused connection abort") || (d.this.l && elapsedRealtime >= 100)) {
                    z = true;
                } else {
                    com.netease.nimlib.log.b.d("LinkClient", "in background or back to foreground 100ms, not report exception");
                    z = false;
                }
                d.this.a(false, z, format);
                if ((th instanceof SocketException) || z2) {
                    d.a(String.format("on link channel throw socket exception %s, on disconnected", th), d.this.b);
                    d.this.b(false, format);
                    d.this.a(th);
                    return;
                } else {
                    if ((th instanceof com.netease.nimlib.net.b.b.b) && (th.getCause() instanceof g)) {
                        d.a(String.format("on link channel throw unpack exception %s, on disconnected and setup netty", th), d.this.b);
                        d.this.b(false, format);
                        d.this.a(th);
                        d.this.k();
                        return;
                    }
                    return;
                }
            }
            com.netease.nimlib.log.b.c(com.alipay.sdk.m.g.b.k, "on link channel exception, but not current one, cause: " + th);
        }

        @Override // com.netease.nimlib.net.b.c.e, com.netease.nimlib.net.b.c.d
        public void k() {
            if (d.this.f != null && d.this.f == this.a.i()) {
                d.a("on link channel inactive, on disconnected", d.this.b);
                d.this.a(false, "on link channel inactive, on disconnected");
                d.this.b(false, "on link channel inactive, on disconnected");
                d.this.a(new Throwable("InoutBoundHandler channelInactive"));
                return;
            }
            d.a("on link channel inactive, mChannel = " + d.this.f, d.this.b);
        }

        @Override // com.netease.nimlib.net.b.c.e, com.netease.nimlib.net.b.c.d
        public void a(Object obj) {
            if (d.this.f == null || d.this.f != this.a.i()) {
                d.a("on link channel read after disconnected, mChannel = " + d.this.f, d.this.b);
                return;
            }
            if (obj instanceof a.C0029a) {
                a.C0029a c0029a = (a.C0029a) obj;
                if (c0029a.a.i() == 6 && c0029a.a.j() == 23) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (com.netease.nimlib.log.b.a.a()) {
                        Log.e("LinkClient", String.format("channelRead %s %s", Short.valueOf(c0029a.a.k()), Long.valueOf(elapsedRealtime)));
                    }
                    com.netease.nimlib.m.e.b(c0029a.a.k(), elapsedRealtime);
                }
                d.this.a(c0029a);
                d.this.j.a(false);
            }
        }

        @Override // com.netease.nimlib.net.b.c.b, com.netease.nimlib.net.b.c.g
        public void a(Object obj, com.netease.nimlib.net.b.a.c cVar) {
            super.a(obj, cVar);
            d.this.j.a(true);
        }
    }

    public com.netease.nimlib.net.b.c.e h() {
        return new com.netease.nimlib.net.b.c.e() { // from class: com.netease.nimlib.push.net.d.1
            AnonymousClass1() {
            }

            @Override // com.netease.nimlib.net.b.c.b, com.netease.nimlib.net.b.c.c
            public void a(Throwable th) {
                boolean z;
                if (this.a.i() == d.this.f || d.this.f == null) {
                    long elapsedRealtime = SystemClock.elapsedRealtime() - d.this.m;
                    Context e = com.netease.nimlib.c.e();
                    String format = e != null ? String.format("network exception caught: %s isNetAvailable: %s isNetworkConnected: %s isAppForeground: %s", th, Boolean.valueOf(p.b(e)), Boolean.valueOf(com.netease.nimlib.network.f.a(e)), Boolean.valueOf(d.this.l)) : String.format("network exception caught: %s context is null", th);
                    boolean z2 = th instanceof UnresolvedAddressException;
                    if (z2) {
                        format = u.a(th.getStackTrace()) ? String.format("%s StackTrace: regular", format) : String.format("%s StackTrace: %s", format, u.b(th.getStackTrace()));
                    }
                    com.netease.nimlib.log.b.e("LinkClient", format, th);
                    th.printStackTrace();
                    if (!format.contains("java.net.SocketException: Software caused connection abort") || (d.this.l && elapsedRealtime >= 100)) {
                        z = true;
                    } else {
                        com.netease.nimlib.log.b.d("LinkClient", "in background or back to foreground 100ms, not report exception");
                        z = false;
                    }
                    d.this.a(false, z, format);
                    if ((th instanceof SocketException) || z2) {
                        d.a(String.format("on link channel throw socket exception %s, on disconnected", th), d.this.b);
                        d.this.b(false, format);
                        d.this.a(th);
                        return;
                    } else {
                        if ((th instanceof com.netease.nimlib.net.b.b.b) && (th.getCause() instanceof g)) {
                            d.a(String.format("on link channel throw unpack exception %s, on disconnected and setup netty", th), d.this.b);
                            d.this.b(false, format);
                            d.this.a(th);
                            d.this.k();
                            return;
                        }
                        return;
                    }
                }
                com.netease.nimlib.log.b.c(com.alipay.sdk.m.g.b.k, "on link channel exception, but not current one, cause: " + th);
            }

            @Override // com.netease.nimlib.net.b.c.e, com.netease.nimlib.net.b.c.d
            public void k() {
                if (d.this.f != null && d.this.f == this.a.i()) {
                    d.a("on link channel inactive, on disconnected", d.this.b);
                    d.this.a(false, "on link channel inactive, on disconnected");
                    d.this.b(false, "on link channel inactive, on disconnected");
                    d.this.a(new Throwable("InoutBoundHandler channelInactive"));
                    return;
                }
                d.a("on link channel inactive, mChannel = " + d.this.f, d.this.b);
            }

            @Override // com.netease.nimlib.net.b.c.e, com.netease.nimlib.net.b.c.d
            public void a(Object obj) {
                if (d.this.f == null || d.this.f != this.a.i()) {
                    d.a("on link channel read after disconnected, mChannel = " + d.this.f, d.this.b);
                    return;
                }
                if (obj instanceof a.C0029a) {
                    a.C0029a c0029a = (a.C0029a) obj;
                    if (c0029a.a.i() == 6 && c0029a.a.j() == 23) {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        if (com.netease.nimlib.log.b.a.a()) {
                            Log.e("LinkClient", String.format("channelRead %s %s", Short.valueOf(c0029a.a.k()), Long.valueOf(elapsedRealtime)));
                        }
                        com.netease.nimlib.m.e.b(c0029a.a.k(), elapsedRealtime);
                    }
                    d.this.a(c0029a);
                    d.this.j.a(false);
                }
            }

            @Override // com.netease.nimlib.net.b.c.b, com.netease.nimlib.net.b.c.g
            public void a(Object obj, com.netease.nimlib.net.b.a.c cVar) {
                super.a(obj, cVar);
                d.this.j.a(true);
            }
        };
    }

    /* compiled from: LinkClient.java */
    /* renamed from: com.netease.nimlib.push.net.d$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements f {
        AnonymousClass2() {
        }

        @Override // com.netease.nimlib.net.b.a.f
        public void onComplete(com.netease.nimlib.net.b.a.c cVar) {
            d.this.a(cVar);
        }
    }

    public d(a aVar, String str) {
        this(aVar, b.MAIN, "", str);
    }

    public d(a aVar, b bVar, String str, String str2) {
        this.d = null;
        this.e = new AtomicInteger(0);
        this.k = null;
        this.n = new f() { // from class: com.netease.nimlib.push.net.d.2
            AnonymousClass2() {
            }

            @Override // com.netease.nimlib.net.b.a.f
            public void onComplete(com.netease.nimlib.net.b.a.c cVar) {
                d.this.a(cVar);
            }
        };
        this.a = aVar;
        this.b = bVar;
        this.c = str;
        this.d = str2;
        k();
        int i = AnonymousClass8.a[bVar.ordinal()];
        if (i == 1) {
            this.j = new com.netease.nimlib.push.net.a() { // from class: com.netease.nimlib.push.net.d.3
                final /* synthetic */ b a;

                AnonymousClass3(b bVar2) {
                    r2 = bVar2;
                }

                @Override // com.netease.nimlib.push.net.b
                protected void e() {
                    d.this.a((com.netease.nimlib.biz.d.a) new com.netease.nimlib.push.a.b.b());
                }

                @Override // com.netease.nimlib.push.net.b
                protected void f() {
                    d.a("MAIN keep alive on timeout", r2);
                    d.this.b(false, "Heartbeat-discovered link failure");
                    d.this.a(new Throwable("MAIN KeepAlive onTimeout"));
                }
            };
            return;
        }
        if (i == 2) {
            this.j = new c() { // from class: com.netease.nimlib.push.net.d.4
                final /* synthetic */ b a;

                AnonymousClass4(b bVar2) {
                    r2 = bVar2;
                }

                @Override // com.netease.nimlib.push.net.b
                protected void e() {
                    d.this.a((com.netease.nimlib.biz.d.a) new com.netease.nimlib.push.a.b.b());
                }

                @Override // com.netease.nimlib.push.net.b
                protected void f() {
                    d.a("ROOM keep alive on timeout", r2);
                    d.this.b(false, "Heartbeat-discovered link failure");
                    d.this.a(new Throwable("ROOM KeepAlive onTimeout"));
                }
            };
        } else if (i == 3) {
            this.j = new c() { // from class: com.netease.nimlib.push.net.d.5
                final /* synthetic */ b a;

                AnonymousClass5(b bVar2) {
                    r2 = bVar2;
                }

                @Override // com.netease.nimlib.push.net.b
                protected void e() {
                    d.this.a((com.netease.nimlib.biz.d.a) new com.netease.nimlib.push.a.b.b());
                }

                @Override // com.netease.nimlib.push.net.b
                protected void f() {
                    d.a("QCHAT keep alive on timeout", r2);
                    d.this.b(false, "Heartbeat-discovered link failure");
                    d.this.a(new Throwable("QCHAT KeepAlive onTimeout"));
                }
            };
        } else {
            a(String.format("LinkClient error LinkType:%s ExtraInfo:%s", bVar2, str), bVar2);
        }
    }

    /* compiled from: LinkClient.java */
    /* renamed from: com.netease.nimlib.push.net.d$8 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.values().length];
            a = iArr;
            try {
                iArr[b.MAIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[b.ROOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[b.QCHAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* compiled from: LinkClient.java */
    /* renamed from: com.netease.nimlib.push.net.d$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 extends com.netease.nimlib.push.net.a {
        final /* synthetic */ b a;

        AnonymousClass3(b bVar2) {
            r2 = bVar2;
        }

        @Override // com.netease.nimlib.push.net.b
        protected void e() {
            d.this.a((com.netease.nimlib.biz.d.a) new com.netease.nimlib.push.a.b.b());
        }

        @Override // com.netease.nimlib.push.net.b
        protected void f() {
            d.a("MAIN keep alive on timeout", r2);
            d.this.b(false, "Heartbeat-discovered link failure");
            d.this.a(new Throwable("MAIN KeepAlive onTimeout"));
        }
    }

    /* compiled from: LinkClient.java */
    /* renamed from: com.netease.nimlib.push.net.d$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 extends c {
        final /* synthetic */ b a;

        AnonymousClass4(b bVar2) {
            r2 = bVar2;
        }

        @Override // com.netease.nimlib.push.net.b
        protected void e() {
            d.this.a((com.netease.nimlib.biz.d.a) new com.netease.nimlib.push.a.b.b());
        }

        @Override // com.netease.nimlib.push.net.b
        protected void f() {
            d.a("ROOM keep alive on timeout", r2);
            d.this.b(false, "Heartbeat-discovered link failure");
            d.this.a(new Throwable("ROOM KeepAlive onTimeout"));
        }
    }

    /* compiled from: LinkClient.java */
    /* renamed from: com.netease.nimlib.push.net.d$5 */
    /* loaded from: classes.dex */
    class AnonymousClass5 extends c {
        final /* synthetic */ b a;

        AnonymousClass5(b bVar2) {
            r2 = bVar2;
        }

        @Override // com.netease.nimlib.push.net.b
        protected void e() {
            d.this.a((com.netease.nimlib.biz.d.a) new com.netease.nimlib.push.a.b.b());
        }

        @Override // com.netease.nimlib.push.net.b
        protected void f() {
            d.a("QCHAT keep alive on timeout", r2);
            d.this.b(false, "Heartbeat-discovered link failure");
            d.this.a(new Throwable("QCHAT KeepAlive onTimeout"));
        }
    }

    public boolean a(com.netease.nimlib.push.net.lbs.b bVar) {
        if (!this.e.compareAndSet(0, 1)) {
            return false;
        }
        b(bVar);
        return true;
    }

    public void c() {
        if (this.e.get() != 0) {
            b(true, "");
            i();
        }
    }

    public boolean d() {
        return this.e.get() == 2 || this.e.get() == 3;
    }

    public boolean a(com.netease.nimlib.biz.d.a aVar) {
        if (!d()) {
            return false;
        }
        a((Object) aVar);
        return true;
    }

    public boolean a(com.netease.nimlib.ipc.a.d dVar) {
        if (!d()) {
            return false;
        }
        a((Object) dVar);
        return true;
    }

    public void e() {
        if (d()) {
            this.j.h();
        }
    }

    public void f() {
        com.netease.nimlib.net.b.a aVar = this.i;
        if (aVar != null) {
            aVar.a();
        }
    }

    private void b(com.netease.nimlib.push.net.lbs.b bVar) {
        c(bVar);
        this.k = bVar;
        if (this.h == null) {
            this.h = new e(com.netease.nimlib.c.e(), this.b, this.d, new e.b() { // from class: com.netease.nimlib.push.net.d.6
                AnonymousClass6() {
                }

                @Override // com.netease.nimlib.push.net.e.b
                public void a(a.C0028a c0028a, boolean z) {
                    d.this.e.compareAndSet(2, 3);
                    if (!z) {
                        d.this.j.a();
                        return;
                    }
                    a.C0029a a2 = a.C0029a.a(c0028a.a, (short) 201);
                    if (a2 == null || d.this.a == null) {
                        return;
                    }
                    d.this.a(a2, "need update public key");
                    d.this.a.a(a2);
                }

                @Override // com.netease.nimlib.push.net.e.b
                public void a() {
                    if (d.this.a != null) {
                        d.this.a.a();
                    }
                    d.this.a(false, "link Low version");
                    d.this.b(false, "link Low version");
                    d.this.a(new Throwable("PackagePacker onFail"));
                }
            });
        }
        this.h.a(bVar);
        this.h.a();
        try {
            this.i.a(bVar.b());
            com.netease.nimlib.net.b.a.c a2 = this.i.a(bVar);
            synchronized (this) {
                this.g = a2;
            }
            a2.a(this.n);
        } catch (Exception e) {
            e.printStackTrace();
            String str = "connect link address failed: " + e;
            a(false, str);
            a(str, this.b);
            a((Throwable) e);
            if (e instanceof com.netease.nimlib.net.b.a.b) {
                a("connect server failed, e=ChannelException " + e.getMessage(), this.b);
            }
        }
    }

    /* compiled from: LinkClient.java */
    /* renamed from: com.netease.nimlib.push.net.d$6 */
    /* loaded from: classes.dex */
    class AnonymousClass6 implements e.b {
        AnonymousClass6() {
        }

        @Override // com.netease.nimlib.push.net.e.b
        public void a(a.C0028a c0028a, boolean z) {
            d.this.e.compareAndSet(2, 3);
            if (!z) {
                d.this.j.a();
                return;
            }
            a.C0029a a2 = a.C0029a.a(c0028a.a, (short) 201);
            if (a2 == null || d.this.a == null) {
                return;
            }
            d.this.a(a2, "need update public key");
            d.this.a.a(a2);
        }

        @Override // com.netease.nimlib.push.net.e.b
        public void a() {
            if (d.this.a != null) {
                d.this.a.a();
            }
            d.this.a(false, "link Low version");
            d.this.b(false, "link Low version");
            d.this.a(new Throwable("PackagePacker onFail"));
        }
    }

    private void c(com.netease.nimlib.push.net.lbs.b bVar) {
        if (bVar.d()) {
            j.a().c();
            com.netease.nimlib.n.e.a(bVar, com.netease.nimlib.n.b.g.LINK);
        }
    }

    public void a(a.C0029a c0029a, String str) {
        b(c0029a);
        com.netease.nimlib.push.packet.a aVar = c0029a.a;
        if (aVar == null) {
            return;
        }
        short l = aVar.l();
        com.netease.nimlib.n.e.a(this.k, s.kConnect, l, str, l == 200);
    }

    public void a(boolean z, String str) {
        if (this.b == b.MAIN) {
            if (z) {
                l.a().b(j.a().a(this.k));
            } else {
                l.a().b(j.a().a(str, this.k));
            }
        }
        com.netease.nimlib.n.e.a(this.k, s.kConnect, 0, str, z);
    }

    public void a(boolean z, boolean z2, String str) {
        com.netease.nimlib.log.b.d("LinkClient", "stopTrackConnectEvent isSuccess: " + z + " reportException: " + z2 + " description: " + str);
        if (this.b == b.MAIN) {
            if (z) {
                l.a().b(j.a().a(this.k));
            } else {
                l.a().b(j.a().a(str, this.k));
            }
        }
        if (z2) {
            com.netease.nimlib.n.e.a(this.k, s.kConnect, 0, str, z);
        }
    }

    private void d(com.netease.nimlib.push.net.lbs.b bVar) {
        if (bVar == null || !bVar.d()) {
            return;
        }
        com.netease.nimlib.n.e.b(bVar, com.netease.nimlib.n.b.g.LINK);
    }

    public void b(boolean z, String str) {
        com.netease.nimlib.n.e.a(this.k, 0, str, z);
    }

    private int i() {
        com.netease.nimlib.net.b.a.a aVar = this.f;
        com.netease.nimlib.net.b.a.c cVar = this.g;
        synchronized (this) {
            this.f = null;
            this.g = null;
        }
        if (cVar != null) {
            cVar.b(this.n);
            cVar.f();
        }
        if (aVar != null) {
            aVar.e();
        }
        int andSet = this.e.getAndSet(0);
        if (andSet == 1) {
            a("on link channel unreachable", this.b);
            this.a.b();
        }
        this.j.d();
        com.netease.nimlib.log.b.d("linkClient", "do disconnect from " + andSet);
        return andSet;
    }

    public void a(com.netease.nimlib.net.b.a.c cVar) {
        String str;
        String str2;
        synchronized (this) {
            this.g = null;
        }
        int i = cVar.d() ? 2 : 0;
        Throwable b2 = cVar.b();
        StringBuilder sb = new StringBuilder();
        sb.append("on connect completed, state=");
        if (cVar.d()) {
            str = "CONNECTED";
        } else {
            str = "DISCONNECTED,cause = " + b2;
        }
        sb.append(str);
        a(sb.toString(), this.b);
        if (this.e.get() != 1) {
            if (i == 2) {
                cVar.a().e();
                return;
            }
            return;
        }
        if (i == 2) {
            this.e.set(i);
            synchronized (this) {
                this.f = cVar.a();
            }
            a(true, (String) null);
            d(this.k);
            j();
            return;
        }
        if (b2 != null) {
            str2 = "link connect failed,cause = " + b2;
            com.netease.nimlib.log.b.e("LinkClient", "", b2);
        } else {
            str2 = "link connect failed,cause = null";
            b2 = new Throwable(str2);
        }
        a(false, str2);
        a(b2);
    }

    public void a(Throwable th) {
        int i = i();
        a aVar = this.a;
        if (aVar == null || i == 0) {
            return;
        }
        aVar.a(0, th);
    }

    public void g() {
        a(new Throwable("LinkClient mockOnDisconnect"));
    }

    private void j() {
        a aVar = this.a;
        if (aVar != null) {
            aVar.a(2, null);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.netease.nimlib.push.net.-$$Lambda$d$96YfGpP6MjqaEmkR9i7VI3PD-n0.<init>(short):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:289)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:193)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:64)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    private void a(java.lang.Object r7) {
        /*
            r6 = this;
            com.netease.nimlib.net.b.a.a r0 = r6.f
            if (r0 != 0) goto L5
            return
        L5:
            boolean r1 = r7 instanceof com.netease.nimlib.push.a.b.b
            r2 = 0
            if (r1 == 0) goto Ld
            com.netease.nimlib.m.e.a(r2)
        Ld:
            boolean r1 = r7 instanceof com.netease.nimlib.ipc.a.d
            if (r1 == 0) goto L5d
            r1 = r7
            com.netease.nimlib.ipc.a.d r1 = (com.netease.nimlib.ipc.a.d) r1
            com.netease.nimlib.push.packet.a r1 = r1.b()
            byte r3 = r1.i()
            r4 = 6
            if (r3 != r4) goto L5d
            byte r3 = r1.j()
            r4 = 23
            if (r3 != r4) goto L5d
            short r1 = r1.k()
            boolean r3 = com.netease.nimlib.log.b.a.a()
            if (r3 == 0) goto L50
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Short r4 = java.lang.Short.valueOf(r1)
            r3[r2] = r4
            r2 = 1
            long r4 = android.os.SystemClock.elapsedRealtime()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r3[r2] = r4
            java.lang.String r2 = "writeRequest %s %s"
            java.lang.String r2 = java.lang.String.format(r2, r3)
            java.lang.String r3 = "LinkClient"
            android.util.Log.e(r3, r2)
        L50:
            com.netease.nimlib.net.b.a.c r7 = r0.a(r7)
            com.netease.nimlib.push.net.-$$Lambda$d$96YfGpP6MjqaEmkR9i7VI3PD-n0 r0 = new com.netease.nimlib.push.net.-$$Lambda$d$96YfGpP6MjqaEmkR9i7VI3PD-n0
            r0.<init>()
            r7.a(r0)
            return
        L5d:
            r0.a(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.push.net.d.a(java.lang.Object):void");
    }

    public static /* synthetic */ void a(short s, com.netease.nimlib.net.b.a.c cVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (com.netease.nimlib.log.b.a.a()) {
            Log.e("LinkClient", String.format("writeRequest %s %s isCanceled: %s  isDone: %s isSuccess: %s", Short.valueOf(s), Long.valueOf(elapsedRealtime), Boolean.valueOf(cVar.e()), Boolean.valueOf(cVar.c()), Boolean.valueOf(cVar.d())));
        }
        com.netease.nimlib.m.e.a(s, elapsedRealtime);
    }

    public void a(a.C0029a c0029a) {
        b(c0029a);
        a aVar = this.a;
        if (aVar != null) {
            aVar.a(c0029a);
        }
    }

    private void b(a.C0029a c0029a) {
        if (h.h()) {
            return;
        }
        try {
            com.netease.nimlib.push.packet.a aVar = c0029a.a;
            if (aVar.i() == 2 && aVar.j() == 2) {
                l.a().a(aVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void k() {
        f();
        int b2 = com.netease.nimlib.c.k().b();
        com.netease.nimlib.net.b.a aVar = new com.netease.nimlib.net.b.a();
        this.i = aVar;
        aVar.a(1, true).a(b2).a(InputDeviceCompat.SOURCE_TOUCHSCREEN, 65536).a(new com.netease.nimlib.net.b.a.e() { // from class: com.netease.nimlib.push.net.d.7
            AnonymousClass7() {
            }

            @Override // com.netease.nimlib.net.b.a.e
            public void a(com.netease.nimlib.net.b.a.g gVar) {
                gVar.a("decoder", new com.netease.nimlib.push.net.b.a(d.this.h));
                gVar.a("encoder", new com.netease.nimlib.push.net.b.b(d.this.h));
                gVar.a("handler", d.this.h());
            }
        });
    }

    /* compiled from: LinkClient.java */
    /* renamed from: com.netease.nimlib.push.net.d$7 */
    /* loaded from: classes.dex */
    class AnonymousClass7 implements com.netease.nimlib.net.b.a.e {
        AnonymousClass7() {
        }

        @Override // com.netease.nimlib.net.b.a.e
        public void a(com.netease.nimlib.net.b.a.g gVar) {
            gVar.a("decoder", new com.netease.nimlib.push.net.b.a(d.this.h));
            gVar.a("encoder", new com.netease.nimlib.push.net.b.b(d.this.h));
            gVar.a("handler", d.this.h());
        }
    }

    public static void a(String str, b bVar) {
        int i = AnonymousClass8.a[bVar.ordinal()];
        if (i == 2) {
            com.netease.nimlib.log.b.g(str);
        } else if (i == 3) {
            com.netease.nimlib.log.b.h(str);
        } else {
            com.netease.nimlib.log.b.O(str);
        }
    }
}
