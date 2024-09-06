package com.netease.nimlib.chatroom;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.push.a;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomResultData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ChatRoomAuthManager.java */
/* loaded from: classes.dex */
class b {
    private Context a;
    private n b;
    private Handler c;
    private Map<String, a> d = new ConcurrentHashMap();

    b() {
    }

    void a(Context context, n nVar) {
        this.a = context;
        this.b = nVar;
    }

    public void a() {
        this.b = null;
        this.c = null;
    }

    public void a(EnterChatRoomData enterChatRoomData) {
        com.netease.nimlib.log.b.g("enter chat room " + enterChatRoomData.getRoomId());
        c.a().d(enterChatRoomData.getRoomId());
        c.a().a(enterChatRoomData.getRoomId(), c());
        c.a().o(enterChatRoomData.getRoomId());
        c.a().a(enterChatRoomData.getRoomId(), enterChatRoomData);
        a(enterChatRoomData.getRoomId(), enterChatRoomData.isIndependentMode(), enterChatRoomData.getAppKey());
    }

    void a(String str, boolean z, String str2) {
        if (this.b == null) {
            com.netease.nimlib.log.b.d("ChatRoomAuthManager", "should call startup before connect");
        } else {
            b(str, StatusCode.CONNECTING);
            this.b.a(str, z, str2);
        }
    }

    void a(String str, int i) {
        if (i == 408) {
            com.netease.nimlib.log.b.g("on enter chat room failed, as get ip address timeout 408, room id=" + str);
        } else {
            com.netease.nimlib.log.b.g("on enter chat room failed, as get ip address failed, resCode=" + i + ", room id=" + str);
        }
        StatusCode e = c.a().e(str);
        if (e == null || e != StatusCode.CONNECTING) {
            return;
        }
        a(str, i, StatusCode.UNLOGIN, null, null);
        b(str, StatusCode.UNLOGIN);
        if (c.a().q(str)) {
            d.e().c(str);
        } else {
            c(str, i);
        }
    }

    private void a(String str, StatusCode statusCode) {
        com.netease.nimlib.log.b.g("on enter chat room failed, as link DISCONNECTED, room id=" + str);
        a(str, 415, statusCode, null, null);
        b(str, statusCode);
        d.e().c(str);
    }

    void a(String str) {
        EnterChatRoomData k = c.a().k(str);
        if (k == null || !k.isValid()) {
            return;
        }
        b(k);
    }

    private void b(EnterChatRoomData enterChatRoomData) {
        com.netease.nimlib.chatroom.c.d dVar;
        String roomId = enterChatRoomData.getRoomId();
        b(roomId, StatusCode.LOGINING);
        if (enterChatRoomData.isIndependentMode()) {
            dVar = new com.netease.nimlib.chatroom.c.d(2, e.a(enterChatRoomData, true), e.c(enterChatRoomData));
        } else {
            dVar = new com.netease.nimlib.chatroom.c.d(2, e.a(enterChatRoomData, false), e.b(enterChatRoomData));
        }
        dVar.i().a(p.a());
        d.e().a(dVar, roomId);
        a(roomId, dVar);
    }

    void a(String str, com.netease.nimlib.chatroom.d.f fVar) {
        ChatRoomInfo chatRoomInfo;
        ChatRoomMember chatRoomMember;
        com.netease.nimlib.log.b.g("on enter chat room response, resCode=" + ((int) fVar.r()) + ", room id=" + str);
        StatusCode e = c.a().e(str);
        if (e == null || e != StatusCode.LOGINING) {
            return;
        }
        d(str);
        if (fVar.n()) {
            if (c.a().l(str)) {
                d.e().c();
            }
            c.a().p(str);
            c.a().s(str);
            ChatRoomInfo b = e.b(fVar.a());
            ChatRoomMember a2 = e.a(fVar.b());
            a2.setRoomId(str);
            com.netease.nimlib.push.packet.b.c c = fVar.c();
            if (c != null) {
                com.netease.nimlib.chatroom.a.b.d().a(str, c);
            }
            chatRoomMember = a2;
            chatRoomInfo = b;
        } else {
            chatRoomInfo = null;
            chatRoomMember = null;
        }
        StatusCode statusOfResCode = StatusCode.statusOfResCode(fVar.r());
        a(str, fVar.r(), statusOfResCode, chatRoomInfo, chatRoomMember);
        b(str, statusOfResCode);
        if (fVar.n()) {
            return;
        }
        if (c.a().q(str)) {
            d.e().c(str);
        } else {
            c(str, fVar.r());
        }
    }

    private void c(String str, int i) {
        if (c.a().q(str)) {
            return;
        }
        boolean a2 = e.a(str, i);
        com.netease.nimlib.log.b.g("check and reconnect, resCode=" + i + ", needReconnect=" + a2 + ", room id=" + str);
        i r = c.a().r(str);
        if (r == null) {
            com.netease.nimlib.log.b.g("unable to check and reconnect! as task is not exist! roomId=" + str);
            return;
        }
        if (a2) {
            if (r.c()) {
                return;
            }
            com.netease.nimlib.log.b.g("chat room reconnect failed, room id=" + str);
            return;
        }
        r.b();
        com.netease.nimlib.log.b.g("cancel room auto reconnect, as resCode=" + i + ", room id=" + str);
    }

    private void a(String str, int i, StatusCode statusCode, ChatRoomInfo chatRoomInfo, ChatRoomMember chatRoomMember) {
        b(str, i);
        d.e().a(new EnterChatRoomResultData(str, i, statusCode, chatRoomInfo, chatRoomMember, chatRoomMember != null ? chatRoomMember.getAccount() : null));
    }

    private boolean b(String str, StatusCode statusCode) {
        StatusCode e = c.a().e(str);
        if (e != null && e == statusCode) {
            return false;
        }
        com.netease.nimlib.log.b.g("chat room " + str + " status changed to " + statusCode);
        c.a().a(str, statusCode);
        d.e().a(e, statusCode, str);
        return true;
    }

    void b(String str) {
        i r;
        if (c.a().c(str)) {
            boolean c = com.netease.nimlib.o.p.c(this.a);
            if (c) {
                String b = m.a().b(str);
                if (TextUtils.isEmpty(b)) {
                    com.netease.nimlib.log.b.t(String.format("onConnectionBroken traceTask linkAddress == null roomId == " + str, new Object[0]));
                } else {
                    com.netease.nimlib.net.trace.a.c().b(b, str);
                }
            }
            StatusCode e = c.a().e(str);
            StatusCode statusCode = c ? StatusCode.UNLOGIN : StatusCode.NET_BROKEN;
            if (e != null && ((e == StatusCode.CONNECTING || e == StatusCode.LOGINING) && statusCode.shouldReLogin() && c.a().q(str))) {
                a(str, statusCode);
                return;
            }
            b(str, 415);
            b(str, statusCode);
            if (!e.a(str, 415) || (r = c.a().r(str)) == null) {
                return;
            }
            r.c();
        }
    }

    void a(boolean z) {
        i r;
        if (z && com.netease.nimlib.h.e() == StatusCode.LOGINED) {
            List<String> c = c.a().c();
            if (c.size() > 0) {
                com.netease.nimlib.log.b.g("app on foreground, sdk logined, should reconnect room counts=" + c.size());
                for (String str : c) {
                    if (e.a(str, 415) && (r = c.a().r(str)) != null) {
                        r.c();
                    }
                }
            }
        }
    }

    void b(String str, int i) {
        c.a().a(str, i);
        com.netease.nimlib.log.b.g("on save enter room error code, roomId=" + str + ", code=" + i);
    }

    public void c(String str) {
        com.netease.nimlib.chatroom.a.a a2 = com.netease.nimlib.chatroom.a.b.d().a(str);
        if (a2 != null) {
            a2.b();
        }
        com.netease.nimlib.chatroom.c.e eVar = new com.netease.nimlib.chatroom.c.e();
        eVar.i().a(p.a());
        d.e().a(eVar, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ChatRoomAuthManager.java */
    /* loaded from: classes.dex */
    static class a extends a.AbstractRunnableC0052a {
        private String a;
        private com.netease.nimlib.chatroom.c.d d;

        a(String str, com.netease.nimlib.chatroom.c.d dVar) {
            this.a = str;
            this.d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            String b = m.a().b(this.a);
            if (TextUtils.isEmpty(b)) {
                com.netease.nimlib.log.b.t(String.format("StopEnterChatRoomRunnable traceTask linkAddress == null roomId == " + this.a, new Object[0]));
            } else {
                com.netease.nimlib.net.trace.a.c().a(b, this.a);
            }
            if (c.a().e(this.a) == StatusCode.LOGINING) {
                com.netease.nimlib.log.b.g("chat room login request timeout");
                a.C0029a a = a.C0029a.a(this.d.i(), ResponseCode.RES_ETIMEOUT);
                a.a.a(this.a);
                d.e().a(a);
                m.a().c(this.a);
            }
        }
    }

    private void a(String str, com.netease.nimlib.chatroom.c.d dVar) {
        d(str);
        a aVar = new a(str, dVar);
        this.d.put(str, aVar);
        com.netease.nimlib.log.b.g("send enter room request, set timeout=" + aVar.b() + ", room id=" + str);
        aVar.a();
        c().postDelayed(aVar, (long) aVar.b());
    }

    a d(String str) {
        a aVar = this.d.get(str);
        if (aVar != null) {
            c().removeCallbacks(aVar);
            this.d.remove(str);
        }
        return aVar;
    }

    void a(List<String> list) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            d(it.next());
        }
    }

    void b() {
        ArrayList arrayList = new ArrayList(this.d.keySet());
        if (arrayList.isEmpty()) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            d((String) it.next());
        }
    }

    private Handler c() {
        if (this.c == null) {
            this.c = com.netease.nimlib.c.b.a.b(this.a);
        }
        return this.c;
    }
}
