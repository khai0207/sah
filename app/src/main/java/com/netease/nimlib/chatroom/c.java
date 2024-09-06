package com.netease.nimlib.chatroom;

import android.os.Handler;
import android.text.TextUtils;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMemberUpdate;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ChatRoomCache.java */
/* loaded from: classes.dex */
public class c {
    private final Map<String, StatusCode> a = new ConcurrentHashMap();
    private final Map<String, Integer> b = new ConcurrentHashMap();
    private final Map<String, com.netease.nimlib.i.k> c = new ConcurrentHashMap();
    private final Map<String, Boolean> d = new ConcurrentHashMap();
    private final Map<String, f> e = new ConcurrentHashMap();
    private final Map<String, EnterChatRoomData> f = new ConcurrentHashMap();
    private final Map<String, Runnable> g = new ConcurrentHashMap();
    private final Map<String, Boolean> h = new ConcurrentHashMap();
    private final Map<String, i> i = new ConcurrentHashMap();

    /* compiled from: ChatRoomCache.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final c a = new c();
    }

    public static c a() {
        return a.a;
    }

    public void b() {
        this.a.clear();
        this.b.clear();
        this.c.clear();
        this.f.clear();
        this.g.clear();
        this.h.clear();
        this.d.clear();
        Iterator<Map.Entry<String, i>> it = this.i.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().a();
        }
        this.i.clear();
        Iterator<Map.Entry<String, f>> it2 = this.e.entrySet().iterator();
        while (it2.hasNext()) {
            it2.next().getValue().a();
        }
        this.e.clear();
    }

    void a(List<String> list) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            com.netease.nimlib.log.b.g("cancel clear chat rooms, room list is empty");
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    void a(String str) {
        if (str == null) {
            com.netease.nimlib.log.b.g("clear chat room cache throw exception, room id is null");
            return;
        }
        this.a.remove(str);
        this.b.remove(str);
        this.c.remove(str);
        this.f.remove(str);
        this.g.remove(str);
        this.h.remove(str);
        this.d.remove(str);
        s(str);
        this.i.remove(str);
    }

    public void b(String str) {
        com.netease.nimlib.log.b.g("clearRoomEnterCache roomId = " + str);
        this.c.remove(str);
        this.f.remove(str);
        s(str);
        this.i.remove(str);
    }

    public boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return this.a.containsKey(str);
    }

    void d(String str) {
        this.a.put(str, StatusCode.UNLOGIN);
        this.b.put(str, 200);
        this.d.put(str, false);
    }

    void a(String str, StatusCode statusCode) {
        this.a.put(str, statusCode);
    }

    public StatusCode e(String str) {
        return this.a.get(str);
    }

    List<String> c() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, StatusCode> entry : this.a.entrySet()) {
            if (entry.getValue().shouldReLogin()) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    void a(String str, int i) {
        this.b.put(str, Integer.valueOf(i));
    }

    public int f(String str) {
        Integer num = this.b.get(str);
        if (num == null) {
            return 200;
        }
        return num.intValue();
    }

    void g(String str) {
        this.d.put(str, true);
    }

    boolean h(String str) {
        return Boolean.TRUE.equals(this.d.get(str));
    }

    void a(String str, com.netease.nimlib.i.k kVar) {
        this.c.put(str, kVar);
    }

    com.netease.nimlib.i.k i(String str) {
        return this.c.get(str);
    }

    void j(String str) {
        this.c.remove(str);
    }

    void a(String str, EnterChatRoomData enterChatRoomData) {
        this.f.put(str, enterChatRoomData);
    }

    public void a(String str, ChatRoomMemberUpdate chatRoomMemberUpdate) {
        EnterChatRoomData k = k(str);
        if (k == null || chatRoomMemberUpdate == null) {
            return;
        }
        k.setNick(chatRoomMemberUpdate.getNick());
        k.setAvatar(chatRoomMemberUpdate.getAvatar());
        k.setExtension(chatRoomMemberUpdate.getExtension());
    }

    EnterChatRoomData k(String str) {
        return this.f.get(str);
    }

    public boolean l(String str) {
        EnterChatRoomData enterChatRoomData;
        return (str == null || (enterChatRoomData = this.f.get(str)) == null || !enterChatRoomData.isIndependentMode()) ? false : true;
    }

    public String m(String str) {
        EnterChatRoomData enterChatRoomData = this.f.get(str);
        if (enterChatRoomData == null) {
            return null;
        }
        return enterChatRoomData.getAppKey();
    }

    public String n(String str) {
        Collection<EnterChatRoomData> values = this.f.values();
        if (com.netease.nimlib.o.f.c((Collection) values)) {
            return null;
        }
        for (EnterChatRoomData enterChatRoomData : values) {
            if (TextUtils.equals(str, enterChatRoomData.getAppKey())) {
                return enterChatRoomData.getRoomId();
            }
        }
        return null;
    }

    void o(String str) {
        this.h.put(str, true);
    }

    void p(String str) {
        this.h.put(str, false);
    }

    boolean q(String str) {
        return Boolean.TRUE.equals(this.h.get(str));
    }

    void a(String str, Handler handler) {
        s(str);
        this.i.put(str, new i(str, handler));
    }

    i r(String str) {
        return this.i.get(str);
    }

    void s(String str) {
        i iVar = this.i.get(str);
        if (iVar != null) {
            iVar.a();
        }
    }

    void d() {
        Iterator<Map.Entry<String, i>> it = this.i.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().b();
        }
    }

    void a(String str, Runnable runnable) {
        this.g.put(str, runnable);
    }

    Runnable t(String str) {
        return this.g.get(str);
    }

    List<Runnable> b(List<String> list) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            Runnable runnable = this.g.get(it.next());
            if (runnable != null) {
                arrayList.add(runnable);
            }
        }
        return arrayList;
    }

    Collection<Runnable> e() {
        return this.g.values();
    }

    public f u(String str) {
        if (str == null) {
            com.netease.nimlib.log.b.g("getMessageManager, room id is null");
        }
        if (!this.e.containsKey(str)) {
            this.e.put(str, new f(str));
        }
        return this.e.get(str);
    }
}
