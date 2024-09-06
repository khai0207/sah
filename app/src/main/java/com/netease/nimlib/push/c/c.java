package com.netease.nimlib.push.c;

import com.netease.nim.highavailable.NativeHighAvailableGetLbsResponseCallback;
import com.netease.nimlib.o.p;
import com.netease.nimlib.o.y;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

/* compiled from: QuickConnectManager.java */
/* loaded from: classes.dex */
public class c {
    private final com.netease.nimlib.push.net.d a;
    private final b b = b.a();
    private final a c = a.a();
    private boolean d = false;
    private long e;

    public c(com.netease.nimlib.push.net.d dVar) {
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("constructor: %s", dVar));
        this.a = dVar;
        com.netease.nimlib.push.net.lbs.c.a().a(new NativeHighAvailableGetLbsResponseCallback() { // from class: com.netease.nimlib.push.c.c.1
            @Override // com.netease.nim.highavailable.NativeHighAvailableGetLbsResponseCallback
            public void onGetLbsResponse(int i, String str) {
                com.netease.nimlib.log.b.d("QuickConnectManager", String.format("onGetLbsResponse: %s %s", Integer.valueOf(i), str));
                if (i != 200) {
                    return;
                }
                c.this.b.a(str);
            }
        });
    }

    public synchronized boolean a() {
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("quickConnect: %s", Boolean.valueOf(this.d)));
        return a(false);
    }

    private synchronized boolean a(boolean z) {
        boolean b = this.c.b();
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("quickConnect(%s): %s %s", Boolean.valueOf(z), Boolean.valueOf(b), Boolean.valueOf(this.d)));
        if (!b) {
            com.netease.nimlib.log.b.d("QuickConnectManager", "quickConnect skip as isQuickConnectEnabled false");
            return false;
        }
        if (!p.b(com.netease.nimlib.c.e())) {
            com.netease.nimlib.log.b.d("QuickConnectManager", "quickConnect skip as isNetAvailable false");
            return false;
        }
        com.netease.nimlib.push.net.lbs.b b2 = this.b.b();
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("quickConnect currentLink: %s", com.netease.nimlib.push.net.lbs.b.b(b2)));
        if (b2 == null) {
            return false;
        }
        if (!z) {
            this.e = y.d();
        }
        long d = y.d();
        long j = d - this.e;
        long f = this.c.f();
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("quickConnect: %s - %s = %s timeout: %s", Long.valueOf(d), Long.valueOf(this.e), Long.valueOf(j), Long.valueOf(f)));
        if (j > f) {
            com.netease.nimlib.log.b.d("QuickConnectManager", "quickConnect skip as timeout");
            return false;
        }
        InetSocketAddress a = d.a(b2.b, b2.c, b2.b(), TimeUnit.MILLISECONDS);
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("quickConnect after dns: %s", a));
        if (a != null && !a.isUnresolved() && a.getAddress() != null) {
            b2.a(a);
            if (!this.b.a(b2, a.getAddress().getHostAddress(), a.getPort())) {
                this.b.a(b2);
                return a(true);
            }
            this.a.a(b2);
            this.d = true;
            return true;
        }
        this.b.a(b2);
        return a(true);
    }

    public synchronized void a(com.netease.nimlib.push.net.lbs.b bVar) {
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("onQuickConnectSucceed: %s %s", com.netease.nimlib.push.net.lbs.b.b(bVar), Boolean.valueOf(this.d)));
        b();
        if (bVar == null) {
            return;
        }
        InetSocketAddress c = bVar.c();
        if (c != null && !c.isUnresolved() && c.getAddress() != null) {
            this.b.a(c.getAddress().getHostAddress(), c.getPort());
            return;
        }
        com.netease.nimlib.log.b.e("QuickConnectManager", "onQuickConnectSucceed skip as InetSocketAddress invalid");
    }

    public synchronized boolean b(com.netease.nimlib.push.net.lbs.b bVar) {
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("onQuickConnectFailed: %s %s", com.netease.nimlib.push.net.lbs.b.b(bVar), Boolean.valueOf(this.d)));
        if (!b()) {
            return false;
        }
        this.b.a(bVar);
        return a(true);
    }

    public synchronized boolean b() {
        com.netease.nimlib.log.b.d("QuickConnectManager", "onQuickConnectFinished: " + this.d);
        if (!this.d) {
            return false;
        }
        this.d = false;
        return true;
    }

    public synchronized void c(com.netease.nimlib.push.net.lbs.b bVar) {
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("reportConnectSucceed: %s %s", com.netease.nimlib.push.net.lbs.b.b(bVar), Boolean.valueOf(this.d)));
        if (bVar == null) {
            return;
        }
        InetSocketAddress c = bVar.c();
        if (c != null && !c.isUnresolved() && c.getAddress() != null) {
            this.b.b(c.getAddress().getHostAddress(), c.getPort());
            return;
        }
        com.netease.nimlib.log.b.e("QuickConnectManager", "reportConnectSucceed skip as InetSocketAddress invalid");
    }

    public synchronized void a(com.netease.nimlib.push.net.lbs.b bVar, Throwable th) {
        com.netease.nimlib.log.b.d("QuickConnectManager", String.format("reportConnectFailed: %s %s %s", com.netease.nimlib.push.net.lbs.b.b(bVar), th, Boolean.valueOf(this.d)));
        if (a(th)) {
            InetSocketAddress c = bVar.c();
            if (c != null && !c.isUnresolved() && c.getAddress() != null) {
                this.b.c(c.getAddress().getHostAddress(), c.getPort());
            }
            com.netease.nimlib.log.b.e("QuickConnectManager", "reportConnectFailed skip as InetSocketAddress invalid");
        }
    }

    private static boolean a(Throwable th) {
        if (th == null) {
            return false;
        }
        if (th instanceof SocketTimeoutException) {
            return true;
        }
        if (th.getMessage() == null || !th.getMessage().contains("connect timeout")) {
            return a(th.getCause());
        }
        return true;
    }
}
