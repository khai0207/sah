package com.netease.nimlib.chatroom.b;

import android.text.TextUtils;
import com.netease.nimlib.chatroom.d.aa;
import com.netease.nimlib.chatroom.d.q;
import com.netease.nimlib.chatroom.d.t;
import com.netease.nimlib.chatroom.d.u;
import com.netease.nimlib.chatroom.d.w;
import com.netease.nimlib.chatroom.d.x;
import com.netease.nimlib.chatroom.d.y;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.session.IMMessageImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: RoomResponseHandler.java */
/* loaded from: classes.dex */
public class m extends e {
    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x0022. Please report as an issue. */
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, null);
            return;
        }
        byte q = aVar.q();
        if (q == 3) {
            a((com.netease.nimlib.chatroom.d.n) aVar);
            return;
        }
        if (q != 19) {
            if (q != 36) {
                if (q != 8) {
                    if (q == 9) {
                        a((com.netease.nimlib.chatroom.d.k) aVar);
                        return;
                    }
                    switch (q) {
                        case 11:
                            a((t) aVar);
                            return;
                        case 12:
                            a((com.netease.nimlib.chatroom.d.d) aVar);
                            return;
                        case 13:
                            a((com.netease.nimlib.chatroom.d.l) aVar);
                            return;
                        case 14:
                            a((aa) aVar);
                            return;
                        case 15:
                            a((y) aVar);
                            return;
                        case 16:
                            a((com.netease.nimlib.chatroom.d.i) aVar);
                            return;
                        case 17:
                            a((com.netease.nimlib.chatroom.d.m) aVar);
                            return;
                        default:
                            switch (q) {
                                case 30:
                                    break;
                                case 31:
                                    break;
                                case 32:
                                    a((q) aVar);
                                    return;
                                case 33:
                                    a((x) aVar);
                                    return;
                                case 34:
                                    a((w) aVar);
                                    return;
                                default:
                                    return;
                            }
                    }
                }
                a((com.netease.nimlib.chatroom.d.h) aVar);
                return;
            }
            a((com.netease.nimlib.chatroom.d.j) aVar);
            return;
        }
        a((u) aVar);
    }

    private void a(com.netease.nimlib.chatroom.d.n nVar) {
        com.netease.nimlib.chatroom.d.e().a(nVar);
    }

    private void a(com.netease.nimlib.chatroom.d.d dVar) {
        if (dVar.n()) {
            com.netease.nimlib.chatroom.d.e().b(dVar.j().o());
        }
        a(dVar, null);
    }

    private void a(com.netease.nimlib.chatroom.d.h hVar) {
        List<com.netease.nimlib.push.packet.b.c> a = hVar.a();
        ArrayList arrayList = new ArrayList(a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            ChatRoomMember a2 = com.netease.nimlib.chatroom.e.a(it.next());
            if (TextUtils.isEmpty(a2.getRoomId())) {
                a2.setRoomId(hVar.j().o());
            }
            arrayList.add(a2);
        }
        a(hVar, arrayList);
    }

    private void a(com.netease.nimlib.chatroom.d.k kVar) {
        List<com.netease.nimlib.push.packet.b.c> a = kVar.a();
        ArrayList arrayList = new ArrayList(a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            IMMessageImpl a2 = com.netease.nimlib.chatroom.g.a(it.next(), false);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        arrayList2.addAll(arrayList);
        com.netease.nimlib.chatroom.g.a((ArrayList<IMMessageImpl>) arrayList2);
        a(kVar, arrayList);
    }

    private void a(t tVar) {
        a(tVar, com.netease.nimlib.chatroom.e.a(tVar.a()));
    }

    private void a(com.netease.nimlib.chatroom.d.l lVar) {
        a(lVar, com.netease.nimlib.chatroom.e.b(lVar.a()));
    }

    private void a(com.netease.nimlib.chatroom.d.i iVar) {
        List<com.netease.nimlib.push.packet.b.c> a = iVar.a();
        ArrayList arrayList = new ArrayList(a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            ChatRoomMember a2 = com.netease.nimlib.chatroom.e.a(it.next());
            if (TextUtils.isEmpty(a2.getRoomId())) {
                a2.setRoomId(iVar.j().o());
            }
            arrayList.add(a2);
        }
        a(iVar, arrayList);
    }

    private void a(com.netease.nimlib.chatroom.d.m mVar) {
        a(mVar, null);
    }

    private void a(u uVar) {
        a(uVar, null);
    }

    private void a(aa aaVar) {
        a(aaVar, null);
    }

    private void a(x xVar) {
        a(xVar, null);
    }

    private void a(w wVar) {
        a(wVar, null);
    }

    private void a(y yVar) {
        a(yVar, null);
    }

    private void a(q qVar) {
        if (qVar.n()) {
            a(qVar, Long.valueOf(qVar.a()));
        } else {
            a(qVar, null);
        }
    }

    private void a(com.netease.nimlib.chatroom.d.j jVar) {
        List<com.netease.nimlib.push.packet.b.c> a = jVar.a();
        ArrayList arrayList = new ArrayList(a.size());
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            IMMessageImpl a2 = com.netease.nimlib.chatroom.g.a(it.next(), false);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        arrayList2.addAll(arrayList);
        com.netease.nimlib.chatroom.g.a((ArrayList<IMMessageImpl>) arrayList2);
        a(jVar, arrayList);
    }
}
