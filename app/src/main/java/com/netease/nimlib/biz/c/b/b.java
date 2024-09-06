package com.netease.nimlib.biz.c.b;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.e.b.c;
import com.netease.nimlib.biz.e.b.d;
import com.netease.nimlib.biz.e.b.e;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.friend.FriendDBHelper;
import com.netease.nimlib.sdk.friend.model.FriendChangedNotify;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: SyncFriendResponseHandler.java */
/* loaded from: classes.dex */
public class b extends i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        byte q = aVar.q();
        if (q == 5) {
            a((d) aVar);
            return;
        }
        if (q != 6) {
            switch (q) {
                case 101:
                    a((com.netease.nimlib.biz.e.b.b) aVar);
                    return;
                case 102:
                    a((c) aVar);
                    return;
                case 103:
                    a((e) aVar);
                    return;
                default:
                    return;
            }
        }
        b((d) aVar);
    }

    private void a(d dVar) {
        List<com.netease.nimlib.push.packet.b.c> a = dVar.a();
        ArrayList arrayList = new ArrayList(a.size());
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            com.netease.nimlib.friend.b a2 = com.netease.nimlib.friend.b.a(it.next());
            arrayList.add(a2);
            if (a2.c().intValue() == 0) {
                arrayList3.add(a2.getAccount());
            } else {
                arrayList2.add(a2);
            }
        }
        if (arrayList.size() > 0) {
            FriendDBHelper.saveFriends(arrayList);
        }
        l.p(dVar.b());
        com.netease.nimlib.i.b.a(new FriendChangedNotify(arrayList2, arrayList3));
    }

    private void b(d dVar) {
        List<com.netease.nimlib.push.packet.b.c> a = dVar.a();
        ArrayList arrayList = new ArrayList(a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            arrayList.add(com.netease.nimlib.user.b.a(it.next()));
        }
        if (arrayList.size() > 0) {
            com.netease.nimlib.user.c.b(arrayList);
        }
        l.l(dVar.b());
    }

    private void a(com.netease.nimlib.biz.e.b.b bVar) {
        byte b = bVar.b();
        if (b == 1 || b == 3) {
            com.netease.nimlib.push.packet.b.c c = bVar.c();
            com.netease.nimlib.friend.a.a(bVar.a(), c.d(0) == 1 ? c.c(1) : null);
        }
    }

    private void a(c cVar) {
        com.netease.nimlib.friend.a.a(cVar.a());
    }

    private void a(e eVar) {
        if (eVar.n()) {
            com.netease.nimlib.friend.a.a(eVar.a());
        }
    }
}
