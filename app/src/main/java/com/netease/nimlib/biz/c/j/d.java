package com.netease.nimlib.biz.c.j;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.d.j.n;
import com.netease.nimlib.biz.e.k.q;
import com.netease.nimlib.biz.e.k.r;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.z;

/* compiled from: StickTopSessionResponseHandler.java */
/* loaded from: classes.dex */
public class d extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, null);
            return;
        }
        byte q = aVar.q();
        switch (q) {
            case 12:
                a((com.netease.nimlib.biz.e.k.d) aVar);
                return;
            case 13:
                a((r) aVar);
                return;
            case 14:
                b((com.netease.nimlib.biz.e.k.d) aVar);
                return;
            default:
                switch (q) {
                    case 112:
                        c((com.netease.nimlib.biz.e.k.d) aVar);
                        return;
                    case 113:
                        a((q) aVar);
                        return;
                    case 114:
                        d((com.netease.nimlib.biz.e.k.d) aVar);
                        return;
                    default:
                        return;
                }
        }
    }

    private void a(com.netease.nimlib.biz.e.k.d dVar) {
        z a = dVar.a();
        MsgDBHelper.saveStickTopSession(a);
        a(a.getUpdateTime());
        a(dVar, a);
    }

    private void a(r rVar) {
        long a = rVar.a();
        MsgDBHelper.deleteStickTopSession(((n) b(rVar)).d());
        a(a);
        a(rVar, null);
    }

    private void b(com.netease.nimlib.biz.e.k.d dVar) {
        z a = dVar.a();
        MsgDBHelper.updateStickTopSession(a.getSessionId(), a.getSessionType(), a.getExt(), a.getUpdateTime());
        a(a.getUpdateTime());
        a(dVar, a);
    }

    private void c(com.netease.nimlib.biz.e.k.d dVar) {
        z a = dVar.a();
        MsgDBHelper.saveStickTopSession(a);
        a(a.getUpdateTime());
        com.netease.nimlib.i.b.a(a);
    }

    private void a(q qVar) {
        long a = qVar.a();
        z b = qVar.b();
        MsgDBHelper.deleteStickTopSession(b.getSessionId());
        a(a);
        com.netease.nimlib.i.b.b(b);
    }

    private void d(com.netease.nimlib.biz.e.k.d dVar) {
        z a = dVar.a();
        MsgDBHelper.updateStickTopSession(a.getSessionId(), a.getSessionType(), a.getExt(), a.getUpdateTime());
        a(a.getUpdateTime());
        com.netease.nimlib.i.b.c(a);
    }

    private void a(long j) {
        if (j > l.J()) {
            l.x(j);
        }
    }
}
