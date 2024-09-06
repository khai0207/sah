package com.netease.nimlib.n;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

/* compiled from: UISyncEventManager.java */
/* loaded from: classes.dex */
public class t {
    private com.netease.nimlib.n.e.j a;

    /* compiled from: UISyncEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final t a = new t();
    }

    public static t a() {
        return a.a;
    }

    public boolean b() {
        if (this.a != null) {
            return false;
        }
        try {
            this.a = new com.netease.nimlib.n.e.j();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            this.a.a(a2);
            this.a.a(com.netease.nimlib.c.n());
            this.a.b(com.netease.nimlib.biz.i.a().d());
            this.a.a(com.netease.nimlib.n.b.q.K_SYNC_ACTION_5_1);
            this.a.c(com.netease.nimlib.n.f.a.a(a2));
            com.netease.nimlib.log.b.G("UISyncEventManager startTrackEvent51 model = " + this.a.m());
            return true;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UISyncEventManager", " startTrackEvent51 Exception", th);
            return false;
        }
    }

    public boolean a(boolean z, int i) {
        String str;
        if (this.a == null) {
            return false;
        }
        Context e = com.netease.nimlib.c.e();
        if (e != null) {
            str = String.format(Locale.ENGLISH, "stopTrackEvent51 code: %d, isNetAvailable: %s isNetworkConnected: %s", Integer.valueOf(i), Boolean.valueOf(com.netease.nimlib.o.p.b(e)), Boolean.valueOf(com.netease.nimlib.network.f.a(e)));
        } else {
            str = "stopTrackEvent51 context is null,code = " + i;
        }
        return a(z, str);
    }

    public boolean a(boolean z, String str) {
        com.netease.nimlib.n.e.j jVar = this.a;
        if (jVar == null) {
            return false;
        }
        if (!z) {
            com.netease.nimlib.log.b.G("UISyncEventManager stopTrackEvent51 error," + str);
            this.a = null;
            return false;
        }
        try {
            jVar.b(z);
            this.a.d(str);
            long c = c();
            this.a.e(c);
            if (this.a.s() <= 0) {
                this.a.d(c);
            }
            com.netease.nimlib.log.b.G("UISyncEventManager stopTrackEvent51 model = " + this.a.m());
            com.netease.nimlib.apm.a.a(this.a.o(), (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) this.a);
            this.a = null;
            return true;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UISyncEventManager", " stopTrackEvent51 Exception", th);
            return false;
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            com.netease.nimlib.log.b.G("UISyncEventManager receiveEventString = " + jSONObject);
            com.netease.nimlib.n.b.q a2 = com.netease.nimlib.n.b.q.a(jSONObject.optString("action"));
            if (a2 == null) {
                return;
            }
            long optLong = jSONObject.optLong("sync_end_time", -1L);
            long optLong2 = jSONObject.optLong("sync_begin_time", -1L);
            if (optLong <= 0) {
                if (this.a == null) {
                    com.netease.nimlib.n.e.j jVar = new com.netease.nimlib.n.e.j();
                    this.a = jVar;
                    jVar.a(com.netease.nimlib.n.f.a.a());
                    this.a.a(a2);
                    this.a.a(jSONObject.optString("user_id"));
                    this.a.b(jSONObject.optString("trace_id"));
                    if (optLong2 > 0) {
                        this.a.c(com.netease.nimlib.n.f.a.a(this.a.a(), optLong2));
                        return;
                    } else {
                        this.a.c(c());
                        return;
                    }
                }
                return;
            }
            if (this.a == null) {
                return;
            }
            if (this.a.r() <= 0 && optLong2 > 0) {
                this.a.c(com.netease.nimlib.n.f.a.a(this.a.a(), optLong2));
            }
            this.a.d(com.netease.nimlib.n.f.a.a(this.a.a(), optLong));
            this.a.e(c());
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UISyncEventManager", "receiveEventString Exception", th);
        }
    }

    public boolean a(com.netease.nimlib.push.packet.a aVar, int i) {
        com.netease.nimlib.n.b.r a2;
        if (this.a == null || aVar == null || (a2 = com.netease.nimlib.n.b.r.a(aVar.i(), aVar.j())) == null) {
            return false;
        }
        if (a2 != com.netease.nimlib.n.b.r.K_SYNC_TYPE_4_4 && a2 != com.netease.nimlib.n.b.r.K_SYNC_TYPE_4_9) {
            return false;
        }
        List l = this.a.l();
        if (l == null) {
            l = new ArrayList();
            this.a.a(l);
        }
        Iterator it = l.iterator();
        com.netease.nimlib.n.c.l lVar = null;
        com.netease.nimlib.n.c.l lVar2 = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            com.netease.nimlib.n.c.l lVar3 = (com.netease.nimlib.n.c.l) it.next();
            if (lVar3.e() == a2.a()) {
                lVar = lVar3;
                break;
            }
            lVar2 = lVar3;
        }
        if (lVar == null) {
            lVar = new com.netease.nimlib.n.c.l();
            lVar.a();
            lVar.a(a2);
            lVar.b("[SID " + ((int) aVar.i()) + " , CID " + ((int) aVar.j()) + "]");
            l.add(lVar);
        }
        com.netease.nimlib.n.c.m mVar = new com.netease.nimlib.n.c.m();
        mVar.a(i);
        mVar.b(a(aVar.p()));
        mVar.c(c());
        if (com.netease.nimlib.o.f.c((Collection) lVar.f())) {
            if (lVar2 == null) {
                mVar.a(this.a.r());
            } else {
                List<com.netease.nimlib.n.c.m> f = lVar2.f();
                if (com.netease.nimlib.o.f.c((Collection) f)) {
                    mVar.a(this.a.r());
                } else {
                    mVar.a(f.get(f.size() - 1).c());
                }
            }
        }
        com.netease.nimlib.log.b.G("UISyncEventManager addTrackEventItem, syncEventItem = " + mVar);
        lVar.a(mVar);
        com.netease.nimlib.log.b.G("UISyncEventManager addTrackEventItem, currentSyncEventExtension = " + lVar);
        return true;
    }

    private long c() {
        com.netease.nimlib.n.e.j jVar = this.a;
        if (jVar == null) {
            com.netease.nimlib.log.b.f("UISyncEventManager", " getServerNow syncEventModel51==null ");
            return 0L;
        }
        return com.netease.nimlib.n.f.a.a(jVar.a());
    }

    private long a(long j) {
        com.netease.nimlib.n.e.j jVar = this.a;
        if (jVar == null) {
            com.netease.nimlib.log.b.f("UISyncEventManager", " getServerTimestamp syncEventModel51==null ");
            return 0L;
        }
        return com.netease.nimlib.n.f.a.b(jVar.a(), j);
    }
}
