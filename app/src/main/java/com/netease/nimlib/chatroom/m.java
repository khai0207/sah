package com.netease.nimlib.chatroom;

import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: RoomLinkLbs.java */
/* loaded from: classes.dex */
public class m {
    private static m d = new m();
    private final Map<String, com.netease.nimlib.push.net.lbs.d> a = new HashMap();
    private final Map<String, b> c = new HashMap();
    private final Map<String, String> b = new HashMap();

    /* compiled from: RoomLinkLbs.java */
    /* loaded from: classes.dex */
    interface a {
        void onGetRoomToken(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RoomLinkLbs.java */
    /* loaded from: classes.dex */
    class b {
        String a;
        a b;

        private b() {
        }
    }

    private m() {
    }

    public static m a() {
        return d;
    }

    void a(String str, a aVar, boolean z) {
        final b bVar = new b();
        bVar.a = str;
        bVar.b = aVar;
        a(str, bVar);
        int a2 = com.netease.nimlib.c.k().a() / 1000;
        if (a2 < 15) {
            a2 = 15;
        }
        if (z) {
            final EnterChatRoomData k = c.a().k(str);
            com.netease.nimlib.c.b.a.c().b().post(new Runnable() { // from class: com.netease.nimlib.chatroom.-$$Lambda$m$MMYTjsNNKAtODXWookzO9M4ulQM
                @Override // java.lang.Runnable
                public final void run() {
                    m.this.a(k, bVar);
                }
            });
        } else {
            com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.push.a.b.f(str), new com.netease.nimlib.biz.g.a(0, a2)) { // from class: com.netease.nimlib.chatroom.m.1
                @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                public void a(com.netease.nimlib.biz.e.a aVar2) {
                    m.this.a((com.netease.nimlib.push.a.c.f) aVar2, bVar);
                }
            });
        }
        com.netease.nimlib.log.b.g("send room token request, set timeout=" + (a2 * 1000) + ", room id=" + str + ", independent=" + z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(EnterChatRoomData enterChatRoomData, b bVar) {
        a(a(enterChatRoomData), bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.netease.nimlib.push.a.c.f fVar, b bVar) {
        if (fVar.n()) {
            a(bVar.a, fVar.a());
        }
        if (bVar.b != null) {
            bVar.b.onGetRoomToken(fVar.r());
            bVar.b = null;
        }
        e(bVar.a);
    }

    com.netease.nimlib.push.net.lbs.b a(String str) {
        com.netease.nimlib.push.net.lbs.d dVar;
        synchronized (this.a) {
            dVar = this.a.get(str);
        }
        if (dVar == null) {
            return null;
        }
        String b2 = dVar.b();
        this.b.put(str, b2);
        return new com.netease.nimlib.push.net.lbs.b(b2);
    }

    public String b(String str) {
        if (this.b.containsKey(str)) {
            return this.b.get(str);
        }
        return null;
    }

    void c(String str) {
        com.netease.nimlib.push.net.lbs.d dVar;
        synchronized (this.a) {
            dVar = this.a.get(str);
        }
        if (dVar != null) {
            dVar.a();
        }
    }

    void d(String str) {
        synchronized (this.a) {
            this.a.remove(str);
        }
    }

    public void b() {
        synchronized (this.a) {
            this.a.clear();
            com.netease.nimlib.log.b.g("enter chat room with %s, clear room link");
        }
    }

    private void a(String str, b bVar) {
        synchronized (this.c) {
            this.c.put(str, bVar);
        }
    }

    private void e(String str) {
        synchronized (this.c) {
            this.c.remove(str);
        }
    }

    private void a(String str, List<String> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        com.netease.nimlib.n.c.a().a(str, list);
        com.netease.nimlib.push.net.lbs.d dVar = new com.netease.nimlib.push.net.lbs.d(str, null, null, 3);
        dVar.a((String[]) list.toArray(new String[list.size()]));
        synchronized (this.a) {
            this.a.put(str, dVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.netease.nimlib.push.a.c.f a(com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData r7) {
        /*
            r6 = this;
            com.netease.nimlib.push.a.c.f r0 = new com.netease.nimlib.push.a.c.f
            r0.<init>()
            com.netease.nimlib.push.packet.a r1 = new com.netease.nimlib.push.packet.a
            r1.<init>()
            r0.a(r1)
            r2 = 1
            com.netease.nimlib.sdk.chatroom.model.ChatRoomIndependentCallback r3 = r7.getIndependentModeCallback()     // Catch: java.lang.Throwable -> L6a
            java.lang.String r4 = r7.getRoomId()     // Catch: java.lang.Throwable -> L6a
            boolean r5 = r7.isIndependentMode()     // Catch: java.lang.Throwable -> L6a
            if (r5 == 0) goto L1e
            r5 = 0
            goto L22
        L1e:
            java.lang.String r5 = r7.getAccount()     // Catch: java.lang.Throwable -> L6a
        L22:
            java.util.List r3 = r3.getChatRoomLinkAddresses(r4, r5)     // Catch: java.lang.Throwable -> L6a
            if (r3 == 0) goto L51
            boolean r4 = r3.isEmpty()     // Catch: java.lang.Throwable -> L6a
            if (r4 == 0) goto L2f
            goto L51
        L2f:
            r0.a(r3)     // Catch: java.lang.Throwable -> L6a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a
            r4.<init>()     // Catch: java.lang.Throwable -> L6a
            java.lang.String r5 = "get room token from app success, tokens are "
            r4.append(r5)     // Catch: java.lang.Throwable -> L6a
            java.lang.Object[] r3 = r3.toArray()     // Catch: java.lang.Throwable -> L6a
            java.lang.String r3 = java.util.Arrays.toString(r3)     // Catch: java.lang.Throwable -> L6a
            r4.append(r3)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L6a
            com.netease.nimlib.log.b.g(r3)     // Catch: java.lang.Throwable -> L6a
            r7 = 0
            r2 = 0
            goto L9f
        L51:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a
            r3.<init>()     // Catch: java.lang.Throwable -> L6a
            java.lang.String r4 = "get room token from app failed, as get empty, roomId="
            r3.append(r4)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r4 = r7.getRoomId()     // Catch: java.lang.Throwable -> L6a
            r3.append(r4)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L6a
            com.netease.nimlib.log.b.g(r3)     // Catch: java.lang.Throwable -> L6a
            goto L9f
        L6a:
            r3 = move-exception
            r3.printStackTrace()
            if (r7 != 0) goto L76
            java.lang.String r7 = "get room token from app exception, enter room data is null! Maybe the room has been reset before"
            com.netease.nimlib.log.b.g(r7)
            goto L9f
        L76:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "get room token from app exception, roomId="
            r4.append(r5)
            java.lang.String r7 = r7.getRoomId()
            r4.append(r7)
            java.lang.String r7 = ", error="
            r4.append(r7)
            java.lang.String r7 = r3.getMessage()
            r4.append(r7)
            java.lang.String r7 = ", maybe the callback throws exception!"
            r4.append(r7)
            java.lang.String r7 = r4.toString()
            com.netease.nimlib.log.b.g(r7)
        L9f:
            if (r2 == 0) goto La6
            r7 = 1001(0x3e9, float:1.403E-42)
            r1.b(r7)
        La6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.chatroom.m.a(com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData):com.netease.nimlib.push.a.c.f");
    }
}
