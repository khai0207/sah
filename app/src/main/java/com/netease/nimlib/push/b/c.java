package com.netease.nimlib.push.b;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import com.kqg.main.constant.KV;
import com.netease.nimlib.h;
import com.netease.nimlib.o.p;
import com.netease.nimlib.push.b.a;
import com.netease.nimlib.push.b.b;
import com.netease.nimlib.sdk.StatusCode;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: NetworkKeeper.java */
/* loaded from: classes.dex */
public class c {
    private Timer c;
    private com.netease.nimlib.push.b.a f;
    private a g;
    private final int a = 0;
    private final int b = 1;
    private AtomicInteger d = new AtomicInteger();
    private AtomicBoolean e = new AtomicBoolean(false);
    private boolean h = false;
    private AtomicInteger i = new AtomicInteger(0);
    private long j = SystemClock.elapsedRealtime();
    private final Runnable k = new Runnable() { // from class: com.netease.nimlib.push.b.c.2
        @Override // java.lang.Runnable
        public void run() {
            synchronized (c.this) {
                if (c.this.c == null) {
                    return;
                }
                c.this.h();
                int b = c.this.b(com.netease.nimlib.c.e());
                if (b <= 0) {
                    b = 10000;
                }
                com.netease.nimlib.c.b.a.c().a("NetworkKeeper").postDelayed(this, b);
                com.netease.nimlib.log.b.O("start reconnect strategy from SDKOptions , delay=" + b);
            }
        }
    };

    /* compiled from: NetworkKeeper.java */
    /* loaded from: classes.dex */
    public interface a {
        boolean e();

        void f();
    }

    public c(a aVar) {
        this.g = aVar;
    }

    public void a(Context context) {
        if (this.i.compareAndSet(0, 1) && this.f == null) {
            com.netease.nimlib.push.b.a aVar = new com.netease.nimlib.push.b.a(context, new a.InterfaceC0053a() { // from class: com.netease.nimlib.push.b.c.1
                @Override // com.netease.nimlib.push.b.a.InterfaceC0053a
                public void onNetworkEvent(b.a aVar2) {
                    int i = AnonymousClass4.a[aVar2.ordinal()];
                    if (i == 1) {
                        c.this.f();
                        return;
                    }
                    if (i != 2) {
                        if (i != 3) {
                            return;
                        }
                        c.this.g.f();
                        c.this.g();
                        return;
                    }
                    com.netease.nimlib.log.b.O("network change to " + p.i(com.netease.nimlib.c.e()));
                    c.this.g.f();
                    c.this.f();
                }
            });
            this.f = aVar;
            aVar.c();
        }
    }

    /* compiled from: NetworkKeeper.java */
    /* renamed from: com.netease.nimlib.push.b.c$4, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.a.values().length];
            a = iArr;
            try {
                iArr[b.a.NETWORK_AVAILABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[b.a.NETWORK_CHANGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[b.a.NETWORK_UNAVAILABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void a() {
        com.netease.nimlib.log.b.O(String.format("shutdown network keeper, current state is %s", this.i));
        if (this.i.compareAndSet(1, 0)) {
            com.netease.nimlib.push.b.a aVar = this.f;
            if (aVar != null) {
                aVar.d();
                this.f = null;
            }
            e();
        }
    }

    private boolean c() {
        com.netease.nimlib.push.b.a aVar = this.f;
        return aVar != null && aVar.b();
    }

    public void a(StatusCode statusCode) {
        if (this.i.get() != 1) {
            return;
        }
        if (statusCode == StatusCode.LOGINED) {
            e();
        } else if (statusCode.shouldReLogin()) {
            d();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004c A[Catch: all -> 0x0093, TryCatch #0 {, blocks: (B:7:0x000a, B:9:0x000e, B:11:0x0010, B:13:0x0017, B:14:0x001c, B:16:0x0029, B:23:0x003b, B:24:0x0091, B:27:0x004c), top: B:6:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void d() {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = r8.i
            int r0 = r0.get()
            if (r0 != 0) goto L9
            return
        L9:
            monitor-enter(r8)
            java.util.Timer r0 = r8.c     // Catch: java.lang.Throwable -> L93
            if (r0 == 0) goto L10
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L93
            return
        L10:
            boolean r0 = r8.k()     // Catch: java.lang.Throwable -> L93
            r1 = 0
            if (r0 == 0) goto L1c
            java.util.concurrent.atomic.AtomicInteger r0 = r8.d     // Catch: java.lang.Throwable -> L93
            r0.set(r1)     // Catch: java.lang.Throwable -> L93
        L1c:
            java.util.Timer r0 = new java.util.Timer     // Catch: java.lang.Throwable -> L93
            r0.<init>()     // Catch: java.lang.Throwable -> L93
            r8.c = r0     // Catch: java.lang.Throwable -> L93
            android.content.Context r0 = com.netease.nimlib.c.e()     // Catch: java.lang.Throwable -> L93
            if (r0 == 0) goto L33
            int r0 = r8.b(r0)     // Catch: java.lang.Throwable -> L93
            r2 = -1
            if (r0 != r2) goto L31
            goto L33
        L31:
            r1 = 1
            goto L34
        L33:
            r0 = 0
        L34:
            if (r1 == 0) goto L4c
            if (r0 <= 0) goto L39
            goto L3b
        L39:
            r0 = 10000(0x2710, float:1.4013E-41)
        L3b:
            com.netease.nimlib.c.b.a r1 = com.netease.nimlib.c.b.a.c()     // Catch: java.lang.Throwable -> L93
            java.lang.String r2 = "NetworkKeeper"
            android.os.Handler r1 = r1.a(r2)     // Catch: java.lang.Throwable -> L93
            java.lang.Runnable r2 = r8.k     // Catch: java.lang.Throwable -> L93
            long r3 = (long) r0     // Catch: java.lang.Throwable -> L93
            r1.postDelayed(r2, r3)     // Catch: java.lang.Throwable -> L93
            goto L91
        L4c:
            java.util.Random r0 = new java.util.Random     // Catch: java.lang.Throwable -> L93
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L93
            java.util.UUID r3 = java.util.UUID.randomUUID()     // Catch: java.lang.Throwable -> L93
            int r3 = r3.hashCode()     // Catch: java.lang.Throwable -> L93
            long r3 = (long) r3     // Catch: java.lang.Throwable -> L93
            long r1 = r1 + r3
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L93
            r1 = 500(0x1f4, float:7.0E-43)
            int r0 = r0.nextInt(r1)     // Catch: java.lang.Throwable -> L93
            int r0 = r0 + 100
            r1 = 2000(0x7d0, float:2.803E-42)
            com.netease.nimlib.push.b.c$3 r3 = new com.netease.nimlib.push.b.c$3     // Catch: java.lang.Throwable -> L93
            r3.<init>()     // Catch: java.lang.Throwable -> L93
            java.util.Timer r2 = r8.c     // Catch: java.lang.Throwable -> L93
            long r4 = (long) r0     // Catch: java.lang.Throwable -> L93
            long r6 = (long) r1     // Catch: java.lang.Throwable -> L93
            r2.schedule(r3, r4, r6)     // Catch: java.lang.Throwable -> L93
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L93
            r2.<init>()     // Catch: java.lang.Throwable -> L93
            java.lang.String r3 = "start reconnect strategy , delay="
            r2.append(r3)     // Catch: java.lang.Throwable -> L93
            r2.append(r0)     // Catch: java.lang.Throwable -> L93
            java.lang.String r0 = ", period="
            r2.append(r0)     // Catch: java.lang.Throwable -> L93
            r2.append(r1)     // Catch: java.lang.Throwable -> L93
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L93
            com.netease.nimlib.log.b.O(r0)     // Catch: java.lang.Throwable -> L93
        L91:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L93
            return
        L93:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L93
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.push.b.c.d():void");
    }

    private void e() {
        synchronized (this) {
            if (this.c != null) {
                this.c.cancel();
                this.c = null;
                com.netease.nimlib.c.b.a.c().a("NetworkKeeper").removeCallbacks(this.k);
                com.netease.nimlib.log.b.O("stop reconnect strategy");
            }
        }
    }

    public int b(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(String.format("content://%s/integer/%s/%s", context.getPackageName() + ".ipc.provider.preference", "PARAMS", "KEY_RECONNECT_STRATEGY")), null, null, null, null);
            if (query != null && query.moveToFirst()) {
                int i = query.getInt(0);
                query.close();
                return i;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        boolean z = SystemClock.elapsedRealtime() - this.j > 900;
        boolean z2 = this.h;
        this.h = p.f(com.netease.nimlib.c.e());
        com.netease.nimlib.push.net.lbs.c.a().j();
        boolean z3 = this.h;
        if (z3 || z2 != z3) {
            com.netease.nimlib.log.b.O("network available, state is wifi = " + this.h + ", old state is wifi = " + z2);
            com.netease.nimlib.net.a.b.a.c.a().d();
        }
        if (this.c != null && l() && !z) {
            com.netease.nimlib.log.b.O("background mode, wait for reconnect timer");
        } else {
            com.netease.nimlib.log.b.O("network available, do reconnect directly...");
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        com.netease.nimlib.push.net.lbs.c.a().k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i) {
        if (!com.netease.nimlib.c.i().enableBackOffReconnectStrategy) {
            return true;
        }
        int i2 = k() ? 16 : 32;
        if (i <= 0) {
            return false;
        }
        return i < i2 * 2 ? (i & (i + (-1))) == 0 : i % i2 == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        if (!c()) {
            com.netease.nimlib.log.b.O("cancel reconnect task, as network is not connected");
            return false;
        }
        com.netease.nimlib.log.b.O("check should reconnect");
        if (this.g.e()) {
            com.netease.nimlib.log.b.O("reconnect task run, do reconnect...");
        } else {
            com.netease.nimlib.log.b.O("should not reconnect");
        }
        this.j = SystemClock.elapsedRealtime();
        return true;
    }

    private void i() {
        if (this.g.e()) {
            com.netease.nimlib.log.b.O("reconnect task run, do reconnect...");
        }
        this.j = SystemClock.elapsedRealtime();
    }

    public void a(com.netease.nimlib.ipc.a.a aVar) {
        if (aVar == null) {
            return;
        }
        this.e.set(aVar.a());
        if (aVar.a()) {
            com.netease.nimlib.log.b.O("app on foreground");
            if (this.c == null) {
                this.d.set(0);
            }
            b();
            return;
        }
        com.netease.nimlib.log.b.O("app in background");
    }

    public void b() {
        if (h.e().shouldReLogin() || j()) {
            e();
            d();
        }
    }

    private boolean j() {
        boolean z;
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.j;
        StatusCode e = h.e();
        StringBuilder sb = new StringBuilder();
        sb.append("checkLinkStateShouldReLogin: ");
        sb.append("SDKState=");
        sb.append(e);
        sb.append(",reconnectTimer=");
        sb.append(this.c);
        sb.append(",reconnectCount=");
        sb.append(this.d.get());
        sb.append(",deltaTime=");
        sb.append(elapsedRealtime);
        if ((e != StatusCode.CONNECTING || elapsedRealtime <= 30000) && (e != StatusCode.LOGINING || elapsedRealtime <= KV.GET_CODE_INTERVAL)) {
            z = false;
        } else {
            com.netease.nimlib.log.b.O("check current SDK State should relogin, SDKState=" + e + ",deltaTime=" + elapsedRealtime);
            z = true;
        }
        sb.append(",status=");
        sb.append(e);
        sb.append(",shouldRelogin=");
        sb.append(z);
        com.netease.nimlib.log.b.c("core", sb.toString());
        return z;
    }

    private boolean k() {
        if (Build.VERSION.SDK_INT >= 14) {
            return this.e.get();
        }
        return h.b();
    }

    private boolean l() {
        return !k();
    }
}
