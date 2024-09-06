package com.netease.nimlib.biz.c.b;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.d.b.c;

/* compiled from: FriendResponseHandler.java */
/* loaded from: classes.dex */
public class a extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        byte q = aVar.q();
        if (q == 1) {
            a((com.netease.nimlib.biz.e.b.a) aVar);
        } else if (q == 2) {
            b((com.netease.nimlib.biz.e.b.a) aVar);
        } else {
            if (q != 3) {
                return;
            }
            c((com.netease.nimlib.biz.e.b.a) aVar);
        }
    }

    private void a(com.netease.nimlib.biz.e.b.a aVar) {
        if (aVar.n()) {
            com.netease.nimlib.biz.d.b.a aVar2 = (com.netease.nimlib.biz.d.b.a) b((com.netease.nimlib.biz.e.a) aVar);
            if (aVar2.e() == 1 || aVar2.e() == 3) {
                com.netease.nimlib.friend.a.a(aVar2.d(), (String) null);
            }
        }
        a(aVar, null);
    }

    private void b(com.netease.nimlib.biz.e.b.a aVar) {
        if (aVar.n()) {
            com.netease.nimlib.biz.d.b.b bVar = (com.netease.nimlib.biz.d.b.b) b((com.netease.nimlib.biz.e.a) aVar);
            com.netease.nimlib.friend.a.a(bVar.d(), bVar.e());
        }
        a(aVar, null);
    }

    private void c(com.netease.nimlib.biz.e.b.a aVar) {
        if (aVar.n()) {
            com.netease.nimlib.friend.a.a(((c) b((com.netease.nimlib.biz.e.a) aVar)).d());
        }
        a(aVar, null);
    }
}
