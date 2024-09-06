package com.netease.nimlib.chatroom;

import android.os.SystemClock;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.chatroom.m;
import com.netease.nimlib.push.net.d;
import com.netease.nimlib.sdk.ModeCode;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: RoomLinkManager.java */
/* loaded from: classes.dex */
class n {
    private final HashMap<String, com.netease.nimlib.push.net.d> a = new HashMap<>();
    private a b;

    /* compiled from: RoomLinkManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(a.C0029a c0029a);

        void a(String str, int i);

        void b(String str, int i);
    }

    n(a aVar) {
        this.b = aVar;
    }

    void a() {
        if (this.a.size() == 0) {
            return;
        }
        synchronized (this.a) {
            String str = com.netease.nimlib.h.f() == ModeCode.IM ? "SDK logined" : "network available";
            for (Map.Entry<String, com.netease.nimlib.push.net.d> entry : this.a.entrySet()) {
                String key = entry.getKey();
                if (entry.getValue().d()) {
                    com.netease.nimlib.log.b.g("no need to reconnect room link after" + str + ", as link is always connected, room id=" + key);
                } else {
                    com.netease.nimlib.log.b.g("reconnect room link after " + str + ", room id=" + key);
                    entry.getValue().c();
                    EnterChatRoomData k = c.a().k(key);
                    com.netease.nimlib.n.c.a().a(key, k == null ? null : k.getAccount(), true);
                    com.netease.nimlib.push.net.lbs.b a2 = m.a().a(key);
                    if (a2 != null && a2.d()) {
                        com.netease.nimlib.log.b.g("reconnect room link " + key + ", address=" + a2.toString() + ", total room links count is " + this.a.size());
                        entry.getValue().a(a2);
                    }
                    b(key, c.a().l(key), c.a().m(key));
                }
            }
        }
    }

    boolean a(String str, boolean z, String str2) {
        com.netease.nimlib.push.net.lbs.b a2 = m.a().a(str);
        if (a2 == null || !a2.d()) {
            b(str, z, str2);
            return true;
        }
        a(str);
        com.netease.nimlib.push.net.d dVar = new com.netease.nimlib.push.net.d(c(str), d.b.ROOM, str, str2);
        synchronized (this.a) {
            this.a.put(str, dVar);
            com.netease.nimlib.log.b.g("connect room link " + str + ", address=" + a2.toString() + ", total room links count is " + this.a.size());
        }
        return dVar.a(a2);
    }

    private void b(String str) {
        com.netease.nimlib.push.net.d dVar;
        synchronized (this.a) {
            dVar = this.a.get(str);
        }
        if (dVar != null) {
            dVar.c();
        }
    }

    private void b(List<String> list) {
        synchronized (this.a) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                com.netease.nimlib.push.net.d dVar = this.a.get(it.next());
                if (dVar != null) {
                    dVar.c();
                }
            }
        }
    }

    void a(boolean z) {
        synchronized (this.a) {
            Iterator<com.netease.nimlib.push.net.d> it = this.a.values().iterator();
            while (it.hasNext()) {
                it.next().c();
            }
            if (z) {
                this.a.clear();
            }
        }
    }

    void a(String str) {
        b(str);
        synchronized (this.a) {
            com.netease.nimlib.push.net.d remove = this.a.remove(str);
            if (remove != null) {
                remove.f();
                com.netease.nimlib.log.b.g("quit room link " + str + ", total room links count is " + this.a.size());
            }
        }
    }

    void a(List<String> list) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return;
        }
        b(list);
        synchronized (this.a) {
            for (String str : list) {
                com.netease.nimlib.push.net.d remove = this.a.remove(str);
                if (remove != null) {
                    remove.f();
                    com.netease.nimlib.log.b.g("quit room link " + str);
                }
            }
            com.netease.nimlib.log.b.g("total room links count is " + this.a.size());
        }
    }

    void b() {
        synchronized (this.a) {
            Iterator<com.netease.nimlib.push.net.d> it = this.a.values().iterator();
            while (it.hasNext()) {
                it.next().c();
            }
            this.a.clear();
            com.netease.nimlib.log.b.g("quit all room links");
        }
    }

    boolean c() {
        return !this.a.isEmpty();
    }

    List<String> d() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            arrayList.addAll(this.a.keySet());
        }
        return arrayList;
    }

    int e() {
        int size;
        synchronized (this.a) {
            size = this.a.size();
        }
        return size;
    }

    String f() {
        if (!c()) {
            return null;
        }
        List<String> d = d();
        if (d.isEmpty()) {
            return null;
        }
        return d.get(0);
    }

    void a(com.netease.nimlib.ipc.a.d dVar, String str) {
        com.netease.nimlib.push.net.d a2 = a(dVar.b(), str);
        if (a2 != null) {
            com.netease.nimlib.n.g.a().a(dVar);
            a2.a(dVar);
        } else {
            com.netease.nimlib.log.b.d("LM", "can not find link client to send");
        }
    }

    private d.a c(final String str) {
        return new d.a() { // from class: com.netease.nimlib.chatroom.n.1
            @Override // com.netease.nimlib.push.net.d.a
            public void a() {
            }

            @Override // com.netease.nimlib.push.net.d.a
            public void a(int i, Throwable th) {
                if (n.this.b != null) {
                    n.this.b.a(str, i);
                }
                if (i == 2) {
                    m.a().d(str);
                }
                com.netease.nimlib.push.net.d dVar = (com.netease.nimlib.push.net.d) n.this.a.get(str);
                if (dVar != null) {
                    com.netease.nimlib.n.c.a().a(str, dVar.b());
                }
            }

            @Override // com.netease.nimlib.push.net.d.a
            public void a(a.C0029a c0029a) {
                if (n.this.b != null) {
                    if (c0029a != null && c0029a.a != null) {
                        c0029a.a.a(SystemClock.elapsedRealtime());
                        c0029a.a.a(str);
                    }
                    n.this.b.a(c0029a);
                }
            }

            @Override // com.netease.nimlib.push.net.d.a
            public void b() {
                m.a().c(str);
            }
        };
    }

    private void b(final String str, boolean z, final String str2) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(str, 1);
        }
        m.a().a(str, new m.a() { // from class: com.netease.nimlib.chatroom.-$$Lambda$n$bhW6xb5oH6aaa5xLcUEqy9DxHp8
            @Override // com.netease.nimlib.chatroom.m.a
            public final void onGetRoomToken(int i) {
                n.this.a(str, str2, i);
            }
        }, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2, int i) {
        if (i == 200) {
            a(str, false, str2);
            return;
        }
        com.netease.nimlib.log.b.g("request room link ip addresses failed, resCode=" + i + ", room id=" + str);
        a aVar = this.b;
        if (aVar != null) {
            aVar.b(str, i);
        }
    }

    private com.netease.nimlib.push.net.d a(com.netease.nimlib.push.packet.a aVar, String str) {
        com.netease.nimlib.push.net.d dVar;
        if ((aVar.i() != 13 || aVar.j() == 1) && !q.a(aVar)) {
            return null;
        }
        synchronized (this.a) {
            dVar = this.a.get(str);
        }
        return dVar;
    }
}
