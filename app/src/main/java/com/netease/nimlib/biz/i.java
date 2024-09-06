package com.netease.nimlib.biz;

import android.content.Context;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.netease.nimlib.app.AppForegroundWatcherCompat;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.friend.FriendDBHelper;
import com.netease.nimlib.n.s;
import com.netease.nimlib.o.o;
import com.netease.nimlib.o.p;
import com.netease.nimlib.o.w;
import com.netease.nimlib.robot.RobotDBHelper;
import com.netease.nimlib.sdk.ModeCode;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.model.CaptureDeviceInfoConfig;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.t;
import com.netease.nimlib.session.u;
import com.netease.nimlib.superteam.SuperTeamDBHelper;
import com.netease.nimlib.team.TeamDBHelper;
import com.netease.nimlib.user.UserDBHelper;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: UICore.java */
/* loaded from: classes.dex */
public class i implements j {
    private static i a = new i();
    private com.netease.nimlib.i.k b;
    private LoginInfo c;
    private com.netease.nimlib.o.i f;
    private AppForegroundWatcherCompat.a g;
    private AppForegroundWatcherCompat.a h;
    private final com.netease.nimlib.m.a l;
    private final com.netease.nimlib.m.d m;
    private com.netease.nimlib.c.b.b n;
    private com.netease.nimlib.biz.c.h o;
    private com.netease.nimlib.biz.c.d p;
    private boolean q;
    private long d = 0;
    private volatile String e = null;
    private boolean i = true;
    private com.netease.nimlib.ipc.b j = new com.netease.nimlib.ipc.b(com.netease.nimlib.c.e());
    private com.netease.nimlib.biz.g.d k = new com.netease.nimlib.biz.g.d();

    public i() {
        com.netease.nimlib.m.a aVar = new com.netease.nimlib.m.a(30L, 3000L, 10L, 1800000L);
        this.l = aVar;
        this.m = new com.netease.nimlib.m.d(this, aVar);
        this.n = new com.netease.nimlib.c.b.b("Response", com.netease.nimlib.c.b.b.c, false);
        this.o = new com.netease.nimlib.biz.c.h() { // from class: com.netease.nimlib.biz.i.1
            AnonymousClass1() {
            }

            @Override // com.netease.nimlib.biz.c.h
            public boolean a(com.netease.nimlib.biz.e.a aVar2) {
                return i.this.k.a(aVar2);
            }

            @Override // com.netease.nimlib.biz.c.h
            public boolean b(com.netease.nimlib.biz.e.a aVar2) {
                return i.this.k.b(aVar2);
            }
        };
        this.q = false;
    }

    public static i a() {
        return a;
    }

    public com.netease.nimlib.m.d b() {
        return this.m;
    }

    public com.netease.nimlib.c.b.b c() {
        return this.n;
    }

    /* compiled from: UICore.java */
    /* renamed from: com.netease.nimlib.biz.i$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements com.netease.nimlib.biz.c.h {
        AnonymousClass1() {
        }

        @Override // com.netease.nimlib.biz.c.h
        public boolean a(com.netease.nimlib.biz.e.a aVar2) {
            return i.this.k.a(aVar2);
        }

        @Override // com.netease.nimlib.biz.c.h
        public boolean b(com.netease.nimlib.biz.e.a aVar2) {
            return i.this.k.b(aVar2);
        }
    }

    public String d() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(boolean z) {
        com.netease.nimlib.log.b.d("UICore", "setABRealReachability isOpen=" + z);
        this.j.a(Boolean.valueOf(z));
    }

    public void b(boolean z) {
        com.netease.nimlib.log.b.d("UICore", "setNetworkConnectedChanged isConnected=" + z);
        this.j.b(Boolean.valueOf(z));
    }

    public void e() {
        this.k.a();
        this.n.a();
        this.p = new com.netease.nimlib.biz.c.d(this.n, this.o);
        o();
        com.netease.nimlib.o.i iVar = new com.netease.nimlib.o.i(5000L, 0);
        this.f = iVar;
        iVar.a();
        r();
        e(true);
    }

    public void f() {
        try {
            com.netease.nimlib.log.b.N("shutdown");
        } catch (Throwable unused) {
            if (com.netease.nimlib.log.b.a.a()) {
                Log.i("ui", "shutdown");
            }
        }
        com.netease.nimlib.c.a((LoginInfo) null);
        com.netease.nimlib.c.b((String) null);
        com.netease.nimlib.session.d.a().b();
        this.n.b();
        this.k.b();
        p();
        d(false);
        e(false);
        com.netease.nimlib.log.b.c();
    }

    public boolean g() {
        return this.b != null;
    }

    public synchronized void a(com.netease.nimlib.i.k kVar, LoginInfo loginInfo) {
        if (loginInfo != null) {
            if (loginInfo.valid() && a(loginInfo)) {
                if (com.netease.nimlib.c.i().rollbackSQLCipher) {
                    com.netease.nimlib.database.encrypt.c.a(loginInfo);
                } else {
                    com.netease.nimlib.database.encrypt.d.a(loginInfo);
                }
                if (com.netease.nimlib.h.e() == StatusCode.DATA_UPGRADE) {
                    com.netease.nimlib.h.a(StatusCode.UNLOGIN);
                }
                String appKey = loginInfo.getAppKey();
                String g = com.netease.nimlib.c.g();
                String p = com.netease.nimlib.c.p();
                if (!TextUtils.isEmpty(p)) {
                    if (!p.equals(loginInfo.getAccount())) {
                        m();
                    } else if (!TextUtils.isEmpty(appKey) && !appKey.equals(g)) {
                        m();
                    }
                }
                String n = com.netease.nimlib.c.n();
                String account = loginInfo.getAccount();
                s.a().a(account);
                if (!TextUtils.isEmpty(n)) {
                    if (n != null && !n.equals(account)) {
                        com.netease.nimlib.h.a(StatusCode.UNLOGIN);
                        com.netease.nimlib.h.a((ArrayList<f>) null);
                        n();
                    } else if (!TextUtils.isEmpty(appKey) && !appKey.equals(g)) {
                        com.netease.nimlib.h.a(StatusCode.UNLOGIN);
                        com.netease.nimlib.h.a((ArrayList<f>) null);
                        n();
                    }
                }
                if (com.netease.nimlib.database.f.a().b() && (!TextUtils.equals(account, com.netease.nimlib.database.f.a().h()) || (!TextUtils.isEmpty(appKey) && !appKey.equals(g)))) {
                    p();
                }
                com.netease.nimlib.c.a(loginInfo);
                s.a().a(g, com.netease.nimlib.c.g());
                com.netease.nimlib.a.a(com.netease.nimlib.c.e(), com.netease.nimlib.c.g());
                if (!TextUtils.isEmpty(appKey) && !appKey.equals(g)) {
                    try {
                        com.netease.nimlib.apm.a.a(com.alipay.sdk.m.h.b.h, appKey);
                        com.netease.nimlib.log.b.H("login change appkey ,appkey = " + appKey);
                    } catch (Exception unused) {
                    }
                }
                com.netease.nimlib.log.b.N("user manual login, account=" + loginInfo.getAccount() + ", customClientType=" + loginInfo.getCustomClientType());
                a((String) null);
                this.b = kVar;
                this.c = loginInfo;
                this.d = SystemClock.elapsedRealtime();
                a.a(com.netease.nimlib.c.e());
                com.netease.nimlib.session.a.c.a().b();
                this.j.a(loginInfo);
            }
        }
        throw new IllegalArgumentException("LoginInfo is invalid!");
    }

    public boolean a(LoginInfo loginInfo) {
        if (loginInfo == null) {
            return false;
        }
        String token = loginInfo.getToken();
        String loginExt = loginInfo.getLoginExt();
        int authType = loginInfo.getAuthType();
        if (authType == 0) {
            return w.b((CharSequence) token);
        }
        if (authType == 1) {
            return (com.netease.nimlib.c.i().authProvider == null && token == null) ? false : true;
        }
        if (authType != 2) {
            return false;
        }
        return (com.netease.nimlib.c.i().authProvider == null && token == null && com.netease.nimlib.c.i().loginExtProvider == null && loginExt == null) ? false : true;
    }

    public void h() {
        a((String) null);
        this.b = null;
        this.j.a();
        List<com.netease.nimlib.biz.g.c> e = this.k.e();
        if (e != null && !e.isEmpty()) {
            com.netease.nimlib.log.b.N("logout,pending task size = " + e.size());
            for (com.netease.nimlib.biz.g.c cVar : e) {
                com.netease.nimlib.biz.d.a b = cVar.b();
                if (b != null) {
                    com.netease.nimlib.n.e.a(b.i(), 415, com.netease.nimlib.n.b.b.kSendAwaitablePacket, com.netease.nimlib.n.b.e.CLOSE);
                }
                cVar.a(ResponseCode.RES_ETIMEOUT, false);
            }
        }
        com.netease.nimlib.h.a(ModeCode.INIT);
        com.netease.nimlib.h.a(StatusCode.UNLOGIN);
        com.netease.nimlib.h.a((ArrayList<f>) null);
        com.netease.nimlib.session.a.c.a().c();
        n();
        com.netease.nimlib.i.b.a(StatusCode.LOGOUT);
    }

    public void i() {
        this.j.a(-163, (Parcelable) null);
    }

    public void a(CaptureDeviceInfoConfig captureDeviceInfoConfig) {
        this.j.a(21, captureDeviceInfoConfig);
    }

    public synchronized void a(short s) {
        com.netease.nimlib.log.b.d("UICore", String.format("onLoginDone %s %s %s %s", Short.valueOf(s), this.b, this.c, com.netease.nimlib.c.m()));
        if (s == 417) {
            return;
        }
        if (this.b != null) {
            com.netease.nimlib.i.k kVar = this.b;
            if (this.c == null || (com.netease.nimlib.c.m() != null && !this.c.equals(com.netease.nimlib.c.m()))) {
                com.netease.nimlib.log.b.f("UICore", String.format("SDKCache.getAuthInfo() == %s && loginInfo == %s", com.netease.nimlib.c.m(), this.c));
                s = ResponseCode.RES_INVALID;
            }
            kVar.a(s);
            if (s == 200) {
                o();
                kVar.a(this.c);
            } else {
                com.netease.nimlib.c.a((LoginInfo) null);
            }
            s.a().a(s);
            kVar.b();
            this.b = null;
            this.c = null;
            if (s == 408 || s == 415) {
                o.a().b();
            }
        } else {
            s.a().a(s);
        }
        if (s == 200) {
            l();
            com.netease.nimlib.plugin.c.a().d(com.netease.nimlib.c.e());
        }
    }

    private void l() {
        try {
            if (l.y() == 0) {
                FriendDBHelper.clearAll();
                com.netease.nimlib.log.b.N("clear friend list dirty data");
            }
            if (l.z() == 0) {
                UserDBHelper.clearAll();
                com.netease.nimlib.log.b.N("clear relation dirty data");
            }
            if (l.v() == 0) {
                List<String> queryAllTeamId = TeamDBHelper.queryAllTeamId();
                Iterator<String> it = queryAllTeamId.iterator();
                while (it.hasNext()) {
                    l.a(it.next(), 0L);
                }
                TeamDBHelper.clearAllTeams();
                com.netease.nimlib.log.b.N("clear team info dirty data ， dirty size = " + queryAllTeamId.size());
            }
            if (l.i() == 0) {
                RobotDBHelper.clearAll();
                com.netease.nimlib.log.b.N("clear robot list dirty data");
            }
            if (l.w() == 0) {
                List<String> queryAllSuperTeamId = SuperTeamDBHelper.queryAllSuperTeamId();
                Iterator<String> it2 = queryAllSuperTeamId.iterator();
                while (it2.hasNext()) {
                    l.b(it2.next(), 0L);
                }
                SuperTeamDBHelper.clearAllSuperTeams();
                com.netease.nimlib.log.b.N("clear team info dirty data ， dirty size = " + queryAllSuperTeamId.size());
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.N("clear dirty data error, e=" + th.getMessage());
        }
    }

    public boolean a(com.netease.nimlib.biz.d.a aVar) {
        return a(aVar, com.netease.nimlib.biz.g.a.a);
    }

    public boolean a(com.netease.nimlib.biz.d.a aVar, com.netease.nimlib.biz.g.a aVar2) {
        return a(new com.netease.nimlib.biz.g.b(aVar, aVar2));
    }

    @Override // com.netease.nimlib.biz.j
    public boolean a(com.netease.nimlib.biz.g.c cVar) {
        boolean z;
        Context e;
        if (cVar == null) {
            return false;
        }
        com.netease.nimlib.biz.d.a b = cVar.b();
        b.i().a(h.a(false));
        com.netease.nimlib.n.e.a(b);
        com.netease.nimlib.n.g.a().a(b);
        boolean z2 = com.netease.nimlib.h.e() == StatusCode.LOGINED;
        com.netease.nimlib.log.b.O("add send task: " + b);
        if (cVar.e() > 0) {
            z = this.k.a(cVar);
            com.netease.nimlib.log.b.O("pend task: " + b);
        } else {
            z = z2;
        }
        if (z2) {
            try {
                if (g.b().a(b)) {
                    this.j.a(new com.netease.nimlib.ipc.a.d(b));
                }
            } catch (Exception e2) {
                com.netease.nimlib.log.b.O("send request exception" + e2.toString());
                cVar.a(ResponseCode.RES_EXCEPTION);
            }
        }
        if (!z) {
            com.netease.nimlib.log.b.O("pend task failed");
            cVar.a(ResponseCode.RES_ECONNECTION);
        } else if (!z2 && (e = com.netease.nimlib.c.e()) != null && !p.c(e)) {
            com.netease.nimlib.log.b.O("send task failed,because network is not connected");
            cVar.a(ResponseCode.RES_ECONNECTION);
            return false;
        }
        return z;
    }

    public com.netease.nimlib.biz.d.a a(com.netease.nimlib.biz.e.a aVar) {
        return this.k.c(aVar);
    }

    public void a(a.C0029a c0029a) {
        a(c0029a, true);
    }

    public void a(a.C0029a c0029a, boolean z) {
        this.p.a(c0029a);
        if (z) {
            this.j.b();
        }
    }

    public void a(com.netease.nimlib.ipc.a.d dVar) {
        a.C0029a c0029a = new a.C0029a();
        c0029a.a = dVar.b();
        if (c0029a.a != null) {
            c0029a.a.a(dVar.f());
            c0029a.a.b(dVar.g());
            c0029a.a.c(dVar.h());
        }
        if (dVar.c() != null) {
            c0029a.b = new com.netease.nimlib.push.packet.c.f(dVar.c());
        }
        c0029a.c = dVar.e();
        g.b().a(c0029a);
        this.p.a(c0029a);
    }

    public void a(com.netease.nimlib.ipc.a.e eVar) {
        List<com.netease.nimlib.biz.g.c> e;
        com.netease.nimlib.h.b(eVar.c);
        com.netease.nimlib.h.c(eVar.d);
        StatusCode status = eVar.a.getStatus();
        if (TextUtils.isEmpty(com.netease.nimlib.c.p()) && status != StatusCode.NEED_CHANGE_LBS && status != StatusCode.NEED_RECONNECT) {
            com.netease.nimlib.c.a(eVar.b);
        }
        status.setDesc(eVar.a.getDesc());
        StatusCode e2 = com.netease.nimlib.h.e();
        if (status == e2) {
            com.netease.nimlib.log.b.N("status not change，status =" + status);
            return;
        }
        if (e2 == StatusCode.DATA_UPGRADE) {
            com.netease.nimlib.log.b.N("return because old == StatusCode.DATA_UPGRADE");
            return;
        }
        if ((status == StatusCode.CONNECTING || status == StatusCode.LOGINING) && e2 == StatusCode.LOGINED) {
            return;
        }
        com.netease.nimlib.log.b.N("set status from " + e2 + " to " + status);
        if (com.netease.nimlib.n.p.a(status)) {
            com.netease.nimlib.c.c(false);
        }
        com.netease.nimlib.h.a(status);
        com.netease.nimlib.apm.b.a().b();
        if (status == StatusCode.KICKOUT || status == StatusCode.KICK_BY_OTHER_CLIENT) {
            a((String) null);
            List<com.netease.nimlib.biz.g.c> e3 = this.k.e();
            if (e3 != null && !e3.isEmpty()) {
                com.netease.nimlib.log.b.N("kick out,pending task size = " + e3.size());
                for (com.netease.nimlib.biz.g.c cVar : e3) {
                    com.netease.nimlib.biz.d.a b = cVar.b();
                    if (b != null) {
                        com.netease.nimlib.n.e.a(b.i(), 415, com.netease.nimlib.n.b.b.kSendAwaitablePacket, com.netease.nimlib.n.b.e.KICKED);
                    }
                    cVar.a(ResponseCode.RES_ETIMEOUT, false);
                }
            }
        } else if ((status == StatusCode.UNLOGIN || status == StatusCode.NET_BROKEN) && (e = this.k.e()) != null && !e.isEmpty()) {
            com.netease.nimlib.log.b.N("connect broken,pending task size = " + e.size());
            for (com.netease.nimlib.biz.g.c cVar2 : e) {
                com.netease.nimlib.biz.d.a b2 = cVar2.b();
                if (b2 != null) {
                    com.netease.nimlib.n.e.a(b2.i(), 415, com.netease.nimlib.n.b.b.kSendAwaitablePacket, com.netease.nimlib.n.b.e.BROKEN);
                }
                cVar2.a(ResponseCode.RES_ETIMEOUT, false);
            }
        }
        if (e2 == StatusCode.LOGINED) {
            this.k.c();
        } else if (status == StatusCode.LOGINED) {
            this.k.d();
            o();
            com.netease.nimlib.search.b.g().b();
            d(true);
            f(AppForegroundWatcherCompat.isBackground());
            com.netease.nimlib.h.a(ModeCode.IM);
        }
        if (status.wontAutoLogin()) {
            n();
        } else if (status != StatusCode.NEED_RECONNECT && status != StatusCode.NEED_CHANGE_LBS && this.b != null && !e2.shouldReLogin() && status.shouldReLogin()) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.d;
            com.netease.nimlib.log.b.d("UICore", "login failed, cost time = " + elapsedRealtime);
            if (elapsedRealtime >= com.netease.nimlib.c.k().b()) {
                a(ResponseCode.RES_ETIMEOUT);
            } else {
                a(ResponseCode.RES_ECONNECTION);
            }
        }
        if (status.shouldReLogin() && com.netease.nimlib.c.i().enableTeamMsgAck) {
            com.netease.nimlib.team.h.c().b();
        }
        com.netease.nimlib.i.b.a(status);
    }

    public void a(ArrayList<f> arrayList) {
        com.netease.nimlib.h.a(arrayList);
        com.netease.nimlib.i.b.a(arrayList);
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!TextUtils.isEmpty(com.netease.nimlib.c.n()) && !com.netease.nimlib.c.n().equals(str)) {
            com.netease.nimlib.log.b.N("open local cache failed : account is different from manual login account");
            return false;
        }
        String p = com.netease.nimlib.c.p();
        if (!TextUtils.isEmpty(p) && !p.equals(str)) {
            m();
        }
        c(str);
        boolean b = com.netease.nimlib.database.f.a().b();
        StringBuilder sb = new StringBuilder();
        sb.append("open local cache ");
        sb.append(b ? Constant.CASH_LOAD_SUCCESS : com.alipay.sdk.m.q.g.j);
        com.netease.nimlib.log.b.N(sb.toString());
        if (b) {
            com.netease.nimlib.c.b(str);
            com.netease.nimlib.c.a(new LoginInfo(str, null));
        }
        return b;
    }

    private void m() {
        p();
        com.netease.nimlib.c.b((String) null);
    }

    private void n() {
        com.netease.nimlib.plugin.c.a().e(com.netease.nimlib.c.e());
        g.b().a();
        if (com.netease.nimlib.c.i().enableTeamMsgAck) {
            com.netease.nimlib.team.h.c().a();
            com.netease.nimlib.team.j.b().a();
            com.netease.nimlib.team.k.b().a();
        }
        f();
        e();
    }

    private synchronized void o() {
        c(com.netease.nimlib.c.n());
    }

    private synchronized void c(String str) {
        if (!TextUtils.isEmpty(str) && !com.netease.nimlib.database.f.a().b()) {
            synchronized (com.netease.nimlib.database.f.a()) {
                com.netease.nimlib.log.b.N("before open database");
                boolean a2 = com.netease.nimlib.database.f.a().a(com.netease.nimlib.c.e(), str);
                com.netease.nimlib.log.b.N("open database result = " + a2);
                if (a2) {
                    q();
                }
            }
        }
    }

    private void p() {
        com.netease.nimlib.database.f.a().e();
    }

    private void q() {
        com.netease.nimlib.c.y();
        com.netease.nimlib.session.e.b().a();
        u.c().a();
        t.c().a();
        com.netease.nimlib.i.b.a();
    }

    /* compiled from: UICore.java */
    /* renamed from: com.netease.nimlib.biz.i$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements AppForegroundWatcherCompat.a {
        AnonymousClass2() {
        }

        @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
        public void a() {
            i.this.f(false);
            com.netease.nimlib.log.b.c();
        }

        @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
        public void b() {
            i.this.f(true);
            com.netease.nimlib.log.b.c();
            com.netease.nimlib.c.b.a.c().b().post($$Lambda$i$2$vxRI6gexMUcrexMYYSVVgFd7Pg.INSTANCE);
        }

        public static /* synthetic */ void c() {
            try {
                com.netease.nimlib.log.b.N("current unread: " + MsgDBHelper.queryUnreadMsgCount() + " comes from " + com.netease.nimlib.o.f.a(MsgDBHelper.queryAllUnreadRecentContact(), ", ", "{", com.alipay.sdk.m.q.h.d, $$Lambda$i$2$ndbG5E9rkh2eetuFqnHymfZLhqc.INSTANCE));
            } catch (Throwable th) {
                com.netease.nimlib.log.b.a("log unread when in background error", th);
            }
        }

        public static /* synthetic */ String a(RecentContact recentContact) {
            return recentContact == null ? "" : String.format("%s-%s: %s", recentContact.getSessionType(), recentContact.getContactId(), Integer.valueOf(recentContact.getUnreadCount()));
        }
    }

    private void d(boolean z) {
        if (z) {
            if (this.g == null) {
                this.g = new AnonymousClass2();
            }
            AppForegroundWatcherCompat.a(this.g);
            return;
        }
        AppForegroundWatcherCompat.b(this.g);
    }

    /* compiled from: UICore.java */
    /* renamed from: com.netease.nimlib.biz.i$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 implements AppForegroundWatcherCompat.a {
        AnonymousClass3() {
        }

        @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
        public void a() {
            com.netease.nimlib.network.h.a().a(true);
            i.this.j.a(new com.netease.nimlib.ipc.a.a(true));
        }

        @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
        public void b() {
            com.netease.nimlib.network.h.a().a(false);
            i.this.j.a(new com.netease.nimlib.ipc.a.a(false));
        }
    }

    private void e(boolean z) {
        if (z) {
            if (this.h == null) {
                this.h = new AppForegroundWatcherCompat.a() { // from class: com.netease.nimlib.biz.i.3
                    AnonymousClass3() {
                    }

                    @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
                    public void a() {
                        com.netease.nimlib.network.h.a().a(true);
                        i.this.j.a(new com.netease.nimlib.ipc.a.a(true));
                    }

                    @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
                    public void b() {
                        com.netease.nimlib.network.h.a().a(false);
                        i.this.j.a(new com.netease.nimlib.ipc.a.a(false));
                    }
                };
            }
            AppForegroundWatcherCompat.a(this.h);
            return;
        }
        AppForegroundWatcherCompat.b(this.h);
    }

    private void r() {
        this.i = l.g();
    }

    public void f(boolean z) {
        if (this.i ^ z) {
            this.i = z;
            a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.l.e(z)) { // from class: com.netease.nimlib.biz.i.4
                final /* synthetic */ boolean a;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass4(com.netease.nimlib.biz.d.a aVar, boolean z2) {
                    super(aVar);
                    r3 = z2;
                }

                @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                public void a(com.netease.nimlib.biz.e.a aVar) {
                    if (aVar.n()) {
                        l.c(r3);
                    }
                }
            });
        }
    }

    /* compiled from: UICore.java */
    /* renamed from: com.netease.nimlib.biz.i$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 extends com.netease.nimlib.biz.g.b {
        final /* synthetic */ boolean a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(com.netease.nimlib.biz.d.a aVar, boolean z2) {
            super(aVar);
            r3 = z2;
        }

        @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
        public void a(com.netease.nimlib.biz.e.a aVar) {
            if (aVar.n()) {
                l.c(r3);
            }
        }
    }

    public void j() {
        com.netease.nimlib.o.i iVar = this.f;
        if (iVar == null || !iVar.b()) {
            return;
        }
        this.j.c();
        this.f.a();
    }

    public void c(boolean z) {
        this.q = z;
    }

    public boolean k() {
        return this.q;
    }
}
