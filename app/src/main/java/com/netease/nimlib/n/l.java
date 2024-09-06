package com.netease.nimlib.n;

import android.os.Handler;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.auth.LoginInfo;
import org.json.JSONObject;

/* compiled from: PushLoginEventManager.java */
/* loaded from: classes.dex */
public class l {
    private com.netease.nimlib.n.e.e b;
    private final Handler a = com.netease.nimlib.c.b.a.c().a("event_thread");
    private volatile long c = 0;
    private boolean d = false;

    /* compiled from: PushLoginEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final l a = new l();
    }

    public static l a() {
        return a.a;
    }

    public void a(final LoginInfo loginInfo, final boolean z, final boolean z2) {
        if (loginInfo == null) {
            return;
        }
        this.a.post(new Runnable() { // from class: com.netease.nimlib.n.-$$Lambda$l$NDmcigsDTqt4liPFys0prkF-MJI
            @Override // java.lang.Runnable
            public final void run() {
                l.this.b(loginInfo, z, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(LoginInfo loginInfo, boolean z, boolean z2) {
        try {
            com.netease.nimlib.n.e.e eVar = new com.netease.nimlib.n.e.e();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            this.d = a2;
            eVar.a(a2);
            eVar.a(loginInfo.getAccount());
            eVar.c(z ? "auto_login" : "manual_login");
            eVar.d((!z || z2) ? null : w.b());
            eVar.a(a(this.d));
            this.b = eVar;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushLoginEventManager", "startTrackLoginEvent Exception", th);
        }
    }

    public void a(final com.netease.nimlib.n.c.i iVar) {
        if (iVar == null) {
            return;
        }
        this.a.post(new Runnable() { // from class: com.netease.nimlib.n.-$$Lambda$l$2m1tVnTZNUauaww0VFCbZLjlKi8
            @Override // java.lang.Runnable
            public final void run() {
                l.this.d(iVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(com.netease.nimlib.n.c.i iVar) {
        try {
            if (this.b != null) {
                com.netease.nimlib.apm.a.a(this.b, iVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushLoginEventManager", "lbsExtension Exception", th);
        }
    }

    public void b(final com.netease.nimlib.n.c.i iVar) {
        if (iVar == null) {
            return;
        }
        this.a.post(new Runnable() { // from class: com.netease.nimlib.n.-$$Lambda$l$zUfICwKbjCMaDnBrVfQiADSWzAs
            @Override // java.lang.Runnable
            public final void run() {
                l.this.c(iVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(com.netease.nimlib.n.c.i iVar) {
        try {
            if (this.b != null) {
                com.netease.nimlib.apm.a.a(this.b, iVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushLoginEventManager", "linkExtension Exception", th);
        }
    }

    public void b() {
        this.c = a(this.d);
        com.netease.nimlib.log.b.G("startCheckRealLogin currentLoginStartTime = " + this.c);
    }

    public void a(int i, com.netease.nimlib.n.b.e eVar) {
        String str = null;
        if (eVar != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("disconnect_reason", eVar.a());
                str = jSONObject.toString();
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("PushLoginEventManager", "loginBreak json Exception", th);
            }
        }
        a(i, str);
    }

    public void c() {
        a(408, "login request 30s timeout");
    }

    public void a(com.netease.nimlib.push.packet.a aVar) {
        if (aVar == null) {
            return;
        }
        a(aVar.l(), aVar.l() == 200 ? "login response success" : "login response error");
    }

    public void a(final int i, final String str) {
        this.a.post(new Runnable() { // from class: com.netease.nimlib.n.-$$Lambda$l$HkRakdUyL7eQUVbRsA4gtaJd7uA
            @Override // java.lang.Runnable
            public final void run() {
                l.this.b(i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, String str) {
        com.netease.nimlib.n.e.e eVar = this.b;
        this.b = null;
        boolean z = i == 200;
        if (eVar != null) {
            try {
                com.netease.nimlib.n.c.i g = com.netease.nimlib.n.c.i.g();
                g.a(z);
                g.a(i);
                g.d(str);
                g.b("protocol");
                g.c("2_2");
                g.a(this.c);
                g.b(a(this.d));
                com.netease.nimlib.apm.a.a(eVar, g);
                eVar.b(z);
                long a2 = a(eVar.a());
                eVar.b(a2);
                com.netease.nimlib.log.b.G("loginEnd stopTime = " + a2);
                com.netease.nimlib.ipc.e.a(eVar);
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("PushLoginEventManager", "loginEnd Exception,code=" + i + ",description=" + str, th);
            }
        }
    }

    private long a(boolean z) {
        return com.netease.nimlib.n.f.a.a(z);
    }
}
