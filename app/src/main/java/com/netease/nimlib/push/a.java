package com.netease.nimlib.push;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.alipay.sdk.app.OpenAuthTask;
import com.netease.nim.highavailable.NativeHighAvailableGetLbsResponseCallback;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.n.j;
import com.netease.nimlib.n.l;
import com.netease.nimlib.o.o;
import com.netease.nimlib.o.p;
import com.netease.nimlib.o.y;
import com.netease.nimlib.plugin.interact.IMixPushInteract;
import com.netease.nimlib.push.a;
import com.netease.nimlib.push.b.c;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.LoginInfo;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: AuthManager.java */
/* loaded from: classes.dex */
public class a implements c.a {
    private Context b;
    private Handler c;
    private com.netease.nimlib.push.c.c d;
    private com.netease.nimlib.push.net.d e;
    private com.netease.nimlib.network.d f;
    private com.netease.nimlib.push.b.c g;
    private com.netease.nimlib.push.a.b.c i;
    private AtomicReference<StatusCode> a = new AtomicReference<>(StatusCode.UNLOGIN);
    private boolean h = true;
    private volatile String j = null;
    private final AbstractRunnableC0052a k = new AbstractRunnableC0052a() { // from class: com.netease.nimlib.push.a.3
        AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.netease.nimlib.net.trace.a.c().a();
            com.netease.nimlib.push.a.b.c cVar = a.this.i;
            if (cVar != null) {
                if (a.this.a.get() == StatusCode.LOGINING) {
                    com.netease.nimlib.log.b.O("login request 30s timeout");
                    l.a().c();
                    f.l().a(a.C0029a.a(cVar.i(), ResponseCode.RES_ETIMEOUT));
                    if (a.this.e != null) {
                        a.this.e.c();
                    }
                }
                if (cVar.d()) {
                    com.netease.nimlib.log.b.d("AuthManager", String.format("onQuickConnectFinished: %s %s %s", Boolean.valueOf(a.this.d.b()), a.this.a, cVar));
                }
            }
        }
    };

    public String a() {
        return this.j;
    }

    a() {
    }

    public void a(Context context, com.netease.nimlib.push.net.d dVar) {
        this.b = context;
        this.e = dVar;
        this.d = new com.netease.nimlib.push.c.c(dVar);
        this.f = new com.netease.nimlib.network.d();
        this.g = new com.netease.nimlib.push.b.c(this);
        if (k()) {
            a(com.netease.nimlib.c.m(), true, false);
        }
    }

    public synchronized void b() {
        com.netease.nimlib.c.a((LoginInfo) null);
        this.b = null;
        this.e = null;
        this.d = null;
        if (this.f != null) {
            this.f.d();
            this.f = null;
        }
        if (this.g != null) {
            this.g.a();
            this.g = null;
        }
    }

    public void a(LoginInfo loginInfo, boolean z, boolean z2) {
        if (loginInfo == null || !loginInfo.valid()) {
            throw new IllegalArgumentException("LoginInfo is invalid!");
        }
        if (z) {
            com.netease.nimlib.log.b.O("do SDK auto login, account=" + loginInfo.getAccount() + ", customClientType=" + loginInfo.getCustomClientType());
        } else {
            this.j = null;
            com.netease.nimlib.log.b.O("do user manual login, account=" + loginInfo.getAccount() + ", customClientType=" + loginInfo.getCustomClientType());
        }
        l.a().a(loginInfo, z, z2);
        if (this.a.get() == StatusCode.LOGINED) {
            com.netease.nimlib.log.b.O("SDK status is LOGINED, current account=" + com.netease.nimlib.c.n() + ", reset !!!");
            com.netease.nimlib.c.a((LoginInfo) null);
            f.l().d();
        }
        this.a.set(StatusCode.UNLOGIN);
        this.h = z;
        com.netease.nimlib.c.a(loginInfo);
        com.netease.nimlib.a.a(this.b, com.netease.nimlib.c.g());
        o.c();
        j();
    }

    void c() {
        com.netease.nimlib.push.b.c cVar = this.g;
        if (cVar != null) {
            cVar.b();
        }
    }

    public void d() {
        this.j = null;
        com.netease.nimlib.c.a((LoginInfo) null);
        h();
        com.netease.nimlib.push.net.d dVar = this.e;
        if (dVar != null) {
            dVar.b(true, "normal logout");
        }
        f.l().a(new com.netease.nimlib.push.a.b.d());
        a(StatusCode.UNLOGIN);
        com.netease.nimlib.job.a.a().b(com.netease.nimlib.c.e());
    }

    private void h() {
        try {
            com.netease.nimlib.push.a.b.c cVar = this.i;
            if (cVar != null) {
                com.netease.nimlib.log.b.O("login is close");
                this.i = null;
                m().removeCallbacks(this.k);
                l.a().a(415, com.netease.nimlib.n.b.e.CLOSE);
                f.l().a(a.C0029a.a(cVar.i(), ResponseCode.RES_ETIMEOUT));
                if (cVar.d()) {
                    this.d.b();
                }
                com.netease.nimlib.n.e.a(cVar.i(), 415, com.netease.nimlib.n.b.b.kSendAwaitablePacket, com.netease.nimlib.n.b.e.CLOSE);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("AuthManager", "logoutCloseLogin error", th);
        }
    }

    void a(int i, int i2, String str, int i3) {
        this.j = null;
        StatusCode statusCode = StatusCode.KICKOUT;
        if (i == 2) {
            statusCode = StatusCode.FORBIDDEN;
        } else if (i == 3) {
            statusCode = StatusCode.KICK_BY_OTHER_CLIENT;
        }
        statusCode.setDesc(str);
        com.netease.nimlib.h.b(i2);
        com.netease.nimlib.h.c(i3);
        a(statusCode);
    }

    @Override // com.netease.nimlib.push.b.c.a
    public boolean e() {
        if (this.a.get() == StatusCode.LOGINING || this.a.get() == StatusCode.LOGINED) {
            return false;
        }
        l.a().a(com.netease.nimlib.c.m(), true, true);
        return j();
    }

    @Override // com.netease.nimlib.push.b.c.a
    public void f() {
        com.netease.nimlib.log.b.O("on network unavailable");
        this.e.c();
        a(StatusCode.NET_BROKEN);
    }

    void a(int i, Throwable th) {
        if (i == 0) {
            com.netease.nimlib.log.b.d("core", "on connection changed to DISCONNECTED");
            a(th);
        } else {
            if (i != 2) {
                return;
            }
            com.netease.nimlib.log.b.d("core", "on connection changed to CONNECTED");
            i();
        }
    }

    void a(com.netease.nimlib.push.a.c.b bVar) {
        short r = bVar.r();
        StatusCode statusOfResCode = StatusCode.statusOfResCode(r);
        m().removeCallbacks(this.k);
        com.netease.nimlib.push.net.lbs.b a = this.e.a();
        com.netease.nimlib.push.a.b.c cVar = this.i;
        if (cVar != null && cVar.d()) {
            if (r == 200) {
                this.d.a(a);
            } else {
                this.e.c();
                if (a == null) {
                    d(bVar);
                } else if (statusOfResCode.wontAutoLogin()) {
                    d(bVar);
                } else if (r != 399) {
                    if (this.d.b(a)) {
                        return;
                    } else {
                        d(bVar);
                    }
                }
            }
        }
        this.i = null;
        if (r == 200) {
            com.netease.nimlib.push.net.lbs.c.a().a(a);
        }
        if (r == 200) {
            this.j = bVar.d().d();
        }
        b(bVar);
        c(bVar);
        if (!this.h && r != 200) {
            com.netease.nimlib.push.b.c cVar2 = this.g;
            if (cVar2 != null) {
                cVar2.a();
            }
            com.netease.nimlib.c.a((LoginInfo) null);
        }
        this.h = true;
        if (statusOfResCode.wontAutoLogin()) {
            this.e.c();
            com.netease.nimlib.c.a((LoginInfo) null);
        }
        a(statusOfResCode);
        if (statusOfResCode == StatusCode.LOGINED) {
            com.netease.nimlib.job.a.a().a(com.netease.nimlib.c.e());
        }
        if (com.netease.nimlib.c.i().enableLoseConnection && statusOfResCode == StatusCode.FORBIDDEN) {
            a(StatusCode.UNLOGIN, true);
        }
    }

    void b(com.netease.nimlib.push.a.c.b bVar) {
        if (bVar.r() != 398) {
            return;
        }
        LoginInfo m = com.netease.nimlib.c.m();
        String g = com.netease.nimlib.c.g();
        boolean z = this.h;
        if (!z && m != null && m.valid() && !com.netease.nimlib.c.a(398, g, m.getAccount())) {
            com.netease.nimlib.c.b.a.a(this.b).postDelayed(new Runnable() { // from class: com.netease.nimlib.push.-$$Lambda$a$SAwJelX1HvxhdotqqEDBF1U0KfI
                private final /* synthetic */ LoginInfo f$1;
                private final /* synthetic */ com.netease.nimlib.push.a.c.b f$2;

                public /* synthetic */ $$Lambda$a$SAwJelX1HvxhdotqqEDBF1U0KfI(LoginInfo m2, com.netease.nimlib.push.a.c.b bVar2) {
                    r2 = m2;
                    r3 = bVar2;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    a.this.a(r2, r3);
                }
            }, new Random().nextInt(10000) + OpenAuthTask.Duplex);
        } else if (!z) {
            d(bVar2);
        }
        if (m2 == null || !m2.valid()) {
            com.netease.nimlib.log.b.O(String.format("cancel recording met %s, login info is not valid", (short) 398));
        } else {
            com.netease.nimlib.c.a(398, g, m2);
        }
    }

    public /* synthetic */ void a(LoginInfo loginInfo, com.netease.nimlib.push.a.c.b bVar) {
        try {
            a(loginInfo, false, true);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.b(String.format("retry manual login with %s error", (short) 398), th);
            d(bVar);
        }
    }

    void c(com.netease.nimlib.push.a.c.b bVar) {
        if (bVar.r() != 399) {
            return;
        }
        LoginInfo m = com.netease.nimlib.c.m();
        String g = com.netease.nimlib.c.g();
        com.netease.nimlib.push.net.lbs.c.a().a(new AnonymousClass1(this.h, m, bVar, g));
        com.netease.nimlib.c.d(true);
        com.netease.nimlib.push.net.lbs.c.a().h();
    }

    /* compiled from: AuthManager.java */
    /* renamed from: com.netease.nimlib.push.a$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements NativeHighAvailableGetLbsResponseCallback {
        final /* synthetic */ boolean a;
        final /* synthetic */ LoginInfo b;
        final /* synthetic */ com.netease.nimlib.push.a.c.b c;
        final /* synthetic */ String d;

        AnonymousClass1(boolean z, LoginInfo loginInfo, com.netease.nimlib.push.a.c.b bVar, String str) {
            this.a = z;
            this.b = loginInfo;
            this.c = bVar;
            this.d = str;
        }

        @Override // com.netease.nim.highavailable.NativeHighAvailableGetLbsResponseCallback
        public void onGetLbsResponse(int i, String str) {
            LoginInfo loginInfo;
            if (com.netease.nimlib.c.I()) {
                com.netease.nimlib.push.net.lbs.c.a().b(this);
                com.netease.nimlib.c.d(false);
                if (!this.a && (loginInfo = this.b) != null && loginInfo.valid() && !com.netease.nimlib.c.a(399, com.netease.nimlib.c.g(), this.b.getAccount())) {
                    com.netease.nimlib.c.b.a.a(a.this.b).post(new Runnable() { // from class: com.netease.nimlib.push.-$$Lambda$a$1$ymja8xXHnh53JGCTuiIqrUo7NjY
                        private final /* synthetic */ LoginInfo f$1;
                        private final /* synthetic */ int f$2;
                        private final /* synthetic */ com.netease.nimlib.push.a.c.b f$3;

                        public /* synthetic */ $$Lambda$a$1$ymja8xXHnh53JGCTuiIqrUo7NjY(LoginInfo loginInfo2, int i2, com.netease.nimlib.push.a.c.b bVar) {
                            r2 = loginInfo2;
                            r3 = i2;
                            r4 = bVar;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            a.AnonymousClass1.this.a(r2, r3, r4);
                        }
                    });
                } else if (!this.a) {
                    a.this.d(this.c);
                }
                LoginInfo loginInfo2 = this.b;
                if (loginInfo2 == null || !loginInfo2.valid()) {
                    com.netease.nimlib.log.b.O(String.format("cancel recording met %s, login info is not valid", (short) 398));
                } else {
                    com.netease.nimlib.c.a(399, this.d, this.b);
                }
            }
        }

        public /* synthetic */ void a(LoginInfo loginInfo, int i, com.netease.nimlib.push.a.c.b bVar) {
            try {
                a.this.a(loginInfo, false, true);
            } catch (Throwable th) {
                com.netease.nimlib.log.b.b(String.format("retry manual login with %s error", Integer.valueOf(i)), th);
                a.this.d(bVar);
            }
        }
    }

    void d(com.netease.nimlib.push.a.c.b bVar) {
        a.C0029a c0029a = new a.C0029a();
        c0029a.a = bVar.j();
        c0029a.b = bVar.a();
        c0029a.c = bVar.l();
        com.netease.nimlib.ipc.e.a(c0029a);
    }

    private void a(StatusCode statusCode) {
        a(statusCode, false);
    }

    public void a(StatusCode statusCode, boolean z) {
        com.netease.nimlib.push.b.c cVar;
        if (this.a.get() != statusCode) {
            if (z || !this.a.get().wontAutoLogin()) {
                if (statusCode.wontAutoLogin() && (cVar = this.g) != null) {
                    cVar.a();
                }
                this.a.set(statusCode);
                com.netease.nimlib.push.b.c cVar2 = this.g;
                if (cVar2 != null) {
                    cVar2.a(statusCode);
                }
                b(statusCode);
                com.netease.nimlib.log.b.O("SDK status change to " + statusCode);
                if (statusCode == StatusCode.LOGINED || statusCode == StatusCode.NET_BROKEN || statusCode == StatusCode.UNLOGIN) {
                    com.netease.nimlib.log.b.c();
                }
            }
        }
    }

    private void b(StatusCode statusCode) {
        com.netease.nimlib.h.a(statusCode);
        com.netease.nimlib.ipc.e.a(statusCode);
    }

    private void i() {
        if (k()) {
            com.netease.nimlib.push.net.d dVar = this.e;
            com.netease.nimlib.push.net.lbs.b a = dVar == null ? null : dVar.a();
            if (a != null) {
                this.d.c(a);
                a(a.a());
            } else {
                com.netease.nimlib.log.b.e("AuthManager", "onConnectionEstablished without currentLinkAddress");
                a(false);
            }
        }
    }

    private void a(boolean z) {
        a(StatusCode.LOGINING);
        l.a().b();
        this.i = new com.netease.nimlib.push.a.b.c(z);
        com.netease.nimlib.push.packet.b.c l = l();
        if (l == null) {
            com.netease.nimlib.push.a.c.b bVar = new com.netease.nimlib.push.a.c.b();
            com.netease.nimlib.push.packet.a i = this.i.i();
            i.b((short) 398);
            bVar.a(i);
            bVar.a(this.i.k());
            f.l().a(bVar);
            return;
        }
        this.i.a(l);
        f.l().a(this.i);
        m().removeCallbacks(this.k);
        this.k.a();
        m().postDelayed(this.k, r0.b());
    }

    private synchronized boolean j() {
        boolean z = false;
        if (!k()) {
            com.netease.nimlib.log.b.O("cancel connect, as auth info is invalid!");
            return false;
        }
        if (this.e == null) {
            com.netease.nimlib.log.b.O("auth connect, linkClient===null!!");
            return false;
        }
        this.e.c();
        a(StatusCode.CONNECTING, false);
        if (this.g != null) {
            this.g.a(this.b);
        }
        if (this.d != null && this.d.a()) {
            z = true;
        }
        if (!z) {
            com.netease.nimlib.push.net.lbs.c.a().a(new com.netease.nimlib.c.a<com.netease.nimlib.push.net.lbs.b>() { // from class: com.netease.nimlib.push.a.2
                AnonymousClass2() {
                }

                @Override // com.netease.nimlib.c.a
                /* renamed from: a */
                public void onCallback(com.netease.nimlib.push.net.lbs.b bVar) {
                    com.netease.nimlib.push.net.d dVar = a.this.e;
                    StringBuilder sb = new StringBuilder();
                    sb.append("connect server ");
                    sb.append(bVar);
                    sb.append(", rel=");
                    sb.append(!com.netease.nimlib.d.e.a());
                    com.netease.nimlib.log.b.O(sb.toString());
                    if (dVar == null) {
                        com.netease.nimlib.log.b.e("AuthManager", "getLinkAddress onCallback linkClient == null");
                    } else {
                        dVar.a(bVar);
                    }
                }
            });
        }
        return true;
    }

    /* compiled from: AuthManager.java */
    /* renamed from: com.netease.nimlib.push.a$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements com.netease.nimlib.c.a<com.netease.nimlib.push.net.lbs.b> {
        AnonymousClass2() {
        }

        @Override // com.netease.nimlib.c.a
        /* renamed from: a */
        public void onCallback(com.netease.nimlib.push.net.lbs.b bVar) {
            com.netease.nimlib.push.net.d dVar = a.this.e;
            StringBuilder sb = new StringBuilder();
            sb.append("connect server ");
            sb.append(bVar);
            sb.append(", rel=");
            sb.append(!com.netease.nimlib.d.e.a());
            com.netease.nimlib.log.b.O(sb.toString());
            if (dVar == null) {
                com.netease.nimlib.log.b.e("AuthManager", "getLinkAddress onCallback linkClient == null");
            } else {
                dVar.a(bVar);
            }
        }
    }

    private void a(Throwable th) {
        boolean z;
        com.netease.nimlib.push.b.c cVar;
        boolean c = p.c(this.b);
        String str = "on connection broken, network connected=" + c;
        com.netease.nimlib.log.b.O(str);
        com.netease.nimlib.push.net.d dVar = this.e;
        com.netease.nimlib.push.net.lbs.b a = dVar == null ? null : dVar.a();
        if (c) {
            com.netease.nimlib.network.d dVar2 = this.f;
            if (dVar2 != null && a != null) {
                dVar2.a(a.b, 2, a.b, a.c, 5, new com.netease.nimlib.network.b() { // from class: com.netease.nimlib.push.-$$Lambda$a$PxvZLABaLhzOONguMVCIXmzd-VQ
                    public /* synthetic */ $$Lambda$a$PxvZLABaLhzOONguMVCIXmzdVQ() {
                    }

                    @Override // com.netease.nimlib.network.b
                    public final void onNetworkCheckResult(boolean z2) {
                        a.a(com.netease.nimlib.push.net.lbs.b.this, z2);
                    }
                });
            }
            com.netease.nimlib.net.trace.a.c().b();
        }
        if (this.i != null) {
            com.netease.nimlib.log.b.O("login is broken");
            this.i = null;
            m().removeCallbacks(this.k);
            z = true;
        } else {
            com.netease.nimlib.push.net.d dVar3 = this.e;
            l.a().b(j.a().a(str, dVar3 != null ? dVar3.a() : null));
            z = false;
        }
        com.netease.nimlib.log.b.d("AuthManager", String.format("onConnectionBroken %s %s %s", Boolean.valueOf(k()), com.netease.nimlib.push.net.lbs.b.b(a), th));
        if (a != null) {
            if (k()) {
                this.d.a(a, th);
                if (a.a() && this.d.b(a)) {
                    return;
                }
            } else {
                com.netease.nimlib.log.b.e("AuthManager", String.format("onConnectionBroken isAccountValid false %s", th));
            }
        } else {
            com.netease.nimlib.log.b.e("AuthManager", String.format("onConnectionBroken without currentLinkAddress %s", th));
        }
        a(c ? StatusCode.UNLOGIN : StatusCode.NET_BROKEN);
        if (z) {
            l.a().a(415, com.netease.nimlib.n.b.e.BROKEN);
        } else {
            l.a().a(415, str);
        }
        if (this.h || (cVar = this.g) == null) {
            return;
        }
        cVar.a();
    }

    public static /* synthetic */ void a(com.netease.nimlib.push.net.lbs.b bVar, boolean z) {
        com.netease.nimlib.log.b.d("AuthManager", "onConnectionBroken networkChecker linkAddress:" + com.netease.nimlib.push.net.lbs.b.b(bVar) + " isConnected:" + z);
    }

    private boolean k() {
        return com.netease.nimlib.c.m() != null && com.netease.nimlib.c.m().valid();
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02d3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.netease.nimlib.push.packet.b.c l() {
        /*
            Method dump skipped, instructions count: 774
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.push.a.l():com.netease.nimlib.push.packet.b.c");
    }

    public String a(String str) {
        com.netease.nimlib.log.b.d("AuthManager", "getDynamicLoginToken account " + str);
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.b == null) {
            return null;
        }
        Cursor query = this.b.getContentResolver().query(Uri.parse(String.format("content://%s/string/%s/%s", this.b.getPackageName() + ".ipc.provider.preference", "PARAMS", "KEY_GET_DYNAMIC_LOGIN_TOKEN")), null, null, null, null);
        if (query != null && query.moveToFirst()) {
            String string = query.getString(0);
            query.close();
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(string == null ? 0 : string.length());
            com.netease.nimlib.log.b.d("AuthManager", String.format("getDynamicLoginToken get length %s", objArr));
            return string;
        }
        com.netease.nimlib.log.b.d("AuthManager", "getDynamicLoginToken value null");
        return null;
    }

    public String b(String str) {
        com.netease.nimlib.log.b.d("AuthManager", "getDynamicLoginExt account " + str);
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.b == null) {
            return null;
        }
        Cursor query = this.b.getContentResolver().query(Uri.parse(String.format("content://%s/string/%s/%s", this.b.getPackageName() + ".ipc.provider.preference", "PARAMS", "KEY_GET_DYNAMIC_LOGIN_EXT")), null, null, null, null);
        if (query != null && query.moveToFirst()) {
            String string = query.getString(0);
            query.close();
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(string == null ? 0 : string.length());
            com.netease.nimlib.log.b.d("AuthManager", String.format("getDynamicLoginExt get length %s", objArr));
            return string;
        }
        com.netease.nimlib.log.b.d("AuthManager", "getDynamicLoginExt value null");
        return null;
    }

    void a(com.netease.nimlib.ipc.a.a aVar) {
        com.netease.nimlib.push.b.c cVar = this.g;
        if (cVar != null) {
            cVar.a(aVar);
        }
        if (this.e == null || !aVar.a()) {
            return;
        }
        this.e.e();
    }

    private Handler m() {
        if (this.c == null) {
            this.c = new Handler(Looper.getMainLooper());
        }
        return this.c;
    }

    /* compiled from: AuthManager.java */
    /* renamed from: com.netease.nimlib.push.a$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractRunnableC0052a implements Runnable {
        private long a;
        final int b;
        final int c;

        public AbstractRunnableC0052a() {
            int a = com.netease.nimlib.c.k().a();
            this.b = a;
            this.c = a / 2;
            this.a = y.a();
        }

        public void a() {
            this.a = y.a();
        }

        public int b() {
            return this.b;
        }
    }

    /* compiled from: AuthManager.java */
    /* renamed from: com.netease.nimlib.push.a$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 extends AbstractRunnableC0052a {
        AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.netease.nimlib.net.trace.a.c().a();
            com.netease.nimlib.push.a.b.c cVar = a.this.i;
            if (cVar != null) {
                if (a.this.a.get() == StatusCode.LOGINING) {
                    com.netease.nimlib.log.b.O("login request 30s timeout");
                    l.a().c();
                    f.l().a(a.C0029a.a(cVar.i(), ResponseCode.RES_ETIMEOUT));
                    if (a.this.e != null) {
                        a.this.e.c();
                    }
                }
                if (cVar.d()) {
                    com.netease.nimlib.log.b.d("AuthManager", String.format("onQuickConnectFinished: %s %s %s", Boolean.valueOf(a.this.d.b()), a.this.a, cVar));
                }
            }
        }
    }

    private String n() {
        IMixPushInteract iMixPushInteract = (IMixPushInteract) com.netease.nimlib.plugin.interact.b.a().a(IMixPushInteract.class);
        return iMixPushInteract != null ? iMixPushInteract.f() : c.a();
    }

    boolean g() {
        com.netease.nimlib.push.a.b.c cVar = this.i;
        return cVar != null && cVar.d();
    }
}
