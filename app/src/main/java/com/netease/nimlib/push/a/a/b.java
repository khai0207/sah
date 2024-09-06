package com.netease.nimlib.push.a.a;

import com.netease.nimlib.biz.a.a;
import com.netease.nimlib.biz.d.d.m;
import com.netease.nimlib.biz.d.d.p;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.biz.k;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.h;
import com.netease.nimlib.o.y;
import com.netease.nimlib.plugin.interact.IMixPushInteract;
import com.netease.nimlib.push.a.b.g;
import com.netease.nimlib.push.f;
import com.netease.nimlib.sdk.ModeCode;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: LoginResponseHandler.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.c.a {
    private final boolean a;

    public b(boolean z) {
        this.a = z;
    }

    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (this.a) {
            a((com.netease.nimlib.push.a.c.b) aVar);
        } else {
            c((com.netease.nimlib.push.a.c.b) aVar);
        }
    }

    private void a(com.netease.nimlib.push.a.c.b bVar) {
        if (h.e() != StatusCode.LOGINING) {
            return;
        }
        if (bVar.n()) {
            com.netease.nimlib.log.b.O("onLoginPush SDK login success, account=" + com.netease.nimlib.c.n());
            if (!f.l().j()) {
                com.netease.nimlib.log.b.O("onLoginPush SDK login success, but link is disconnect, account=" + com.netease.nimlib.c.n());
                bVar.j().b(ResponseCode.RES_INVALID);
            }
        } else {
            com.netease.nimlib.log.b.O("onLoginPush SDK login failed, code=" + ((int) bVar.r()));
        }
        f.l().a(bVar);
        if (bVar.n()) {
            f(bVar);
        }
        ArrayList<com.netease.nimlib.biz.f> c = bVar.c();
        if (c != null && c.size() > 0) {
            String c2 = com.netease.nimlib.push.b.c();
            Iterator<com.netease.nimlib.biz.f> it = c.iterator();
            while (it.hasNext()) {
                if (it.next().a().equals(c2)) {
                    it.remove();
                }
            }
            com.netease.nimlib.push.h.a(c);
            return;
        }
        com.netease.nimlib.push.h.a();
    }

    private void b(com.netease.nimlib.push.a.c.b bVar) {
        if (h.b()) {
            return;
        }
        com.netease.nimlib.push.d d = bVar.d();
        com.netease.nimlib.ipc.a.c cVar = new com.netease.nimlib.ipc.a.c(d.c(), d.a() == 1, d.b());
        com.netease.nimlib.ipc.e.a(cVar);
        com.netease.nimlib.log.b.l("sdk sync MixPushState = " + cVar.toString());
    }

    private void c(com.netease.nimlib.push.a.c.b bVar) {
        if (i.a().k()) {
            com.netease.nimlib.log.b.d("LoginResponseHandler", "onLoginUIRaw synchronized true");
            synchronized (i.a()) {
                d(bVar);
            }
            return;
        }
        com.netease.nimlib.log.b.d("LoginResponseHandler", "onLoginUIRaw synchronized false");
        d(bVar);
    }

    private void d(com.netease.nimlib.push.a.c.b bVar) {
        if (bVar.n()) {
            com.netease.nimlib.log.b.O("onLoginUI SDK login success, account=" + com.netease.nimlib.c.n());
            i.a().a(bVar.d().d());
            com.netease.nimlib.biz.a.a();
            i.a().b().d();
            h.a(ModeCode.IM);
            e(bVar);
            com.netease.nimlib.c.c(true);
            com.netease.nimlib.log.b.N("notify LoginSyncDataStatus: BEGIN_SYNC");
            com.netease.nimlib.i.b.a(LoginSyncStatus.BEGIN_SYNC);
            com.netease.nimlib.b.a a = k.a();
            i.a().a(new p(a.a(), a.b(), a.c()));
            i.a().a(new m());
        } else {
            com.netease.nimlib.log.b.O("onLoginUI SDK login failed, code=" + ((int) bVar.r()));
        }
        i.a().a(bVar.r());
        if (bVar.n()) {
            l.b(bVar.b().c(103));
            l.w(y.a());
        }
    }

    private void e(com.netease.nimlib.push.a.c.b bVar) {
        com.netease.nimlib.push.d d = bVar.d();
        int c = d.c();
        boolean z = d.a() == 1;
        String b = d.b();
        IMixPushInteract iMixPushInteract = (IMixPushInteract) com.netease.nimlib.plugin.interact.b.a().a(IMixPushInteract.class);
        if (iMixPushInteract != null) {
            iMixPushInteract.a(new com.netease.nimlib.ipc.a.c(c, z, b));
        }
    }

    private void f(com.netease.nimlib.push.a.c.b bVar) {
        com.netease.nimlib.m.e.a(true);
        com.netease.nimlib.push.b.a();
        b(bVar);
        a();
    }

    private void a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        long currentTimeMillis = System.currentTimeMillis();
        com.netease.nimlib.log.b.I("syncData before get Data");
        com.netease.nimlib.ipc.a.f N = l.N();
        com.netease.nimlib.log.b.I("syncData after get Data,cost time = " + (System.currentTimeMillis() - currentTimeMillis));
        cVar.a(a.EnumC0026a.UNREAD_MESSAGE.a(), N.d());
        cVar.a(a.EnumC0026a.YSF_UNREAD_MSG.a(), 0);
        cVar.a(a.EnumC0026a.TINFO.a(), N.e());
        cVar.a(a.EnumC0026a.DND_PUSH.a(), N.f());
        cVar.a(a.EnumC0026a.AVCHAT.a(), N.g());
        cVar.a(a.EnumC0026a.ROAMING_MSG.a(), N.h());
        cVar.a(a.EnumC0026a.BLACK_AND_MUTE.a(), N.i());
        cVar.a(a.EnumC0026a.FREIND_LIST.a(), N.j());
        cVar.a(a.EnumC0026a.MY_INFO.a(), N.c());
        cVar.a(a.EnumC0026a.FRIEND_INFO.a(), N.k());
        cVar.a(a.EnumC0026a.MSG_READ.a(), N.l());
        cVar.a(a.EnumC0026a.DONNOP_PUSH.a(), N.n());
        cVar.a(a.EnumC0026a.MY_TLIST.a(), N.m());
        cVar.a(a.EnumC0026a.ROAM_DELETE_MSG.a(), N.o());
        if (com.netease.nimlib.c.i().sessionReadAck) {
            long p = N.p();
            cVar.a(a.EnumC0026a.SESSION_ACK_LIST.a(), p);
            com.netease.nimlib.log.b.y("sync session ack list, syncTimeTag=" + p);
        }
        cVar.a(a.EnumC0026a.ROBOT_LIST.a(), N.q());
        cVar.a(a.EnumC0026a.BROADCAST_MSG.a(), N.r());
        cVar.a(a.EnumC0026a.SIGNALLING_MSG.a(), N.s());
        cVar.a(a.EnumC0026a.SUPER_TINFO.a(), N.t());
        cVar.a(a.EnumC0026a.MY_SUPER_TLIST.a(), N.u());
        if (com.netease.nimlib.c.i().sessionReadAck) {
            long x = N.x();
            com.netease.nimlib.log.b.y("sync super team session ack list, syncTimeTag=" + x);
            cVar.a(a.EnumC0026a.SUPERTEAM_SESSION_ACK_LIST.a(), x);
        }
        cVar.a(a.EnumC0026a.MSG_DELETE_SELF.a(), N.y());
        if (com.netease.nimlib.c.i().notifyStickTopSession) {
            cVar.a(a.EnumC0026a.STICK_TOP_SESSION.a(), N.z());
        }
        cVar.a(a.EnumC0026a.SESSION_HISTORY_MSGS_DELETE.a(), N.A());
        cVar.a(a.EnumC0026a.SUPER_ROAMING_MSG.a(), N.v());
        cVar.a(a.EnumC0026a.ROAM_SUPERTEAM_DELETE_MSG.a(), N.w());
        com.netease.nimlib.log.b.I("syncData before send request,cost time = " + (System.currentTimeMillis() - currentTimeMillis));
        com.netease.nimlib.n.p.a();
        g gVar = new g();
        gVar.a(cVar);
        f.l().a(gVar);
        com.netease.nimlib.log.b.O("SDK send login sync data request");
        com.netease.nimlib.log.b.O("request sync time tags : " + N);
    }
}
