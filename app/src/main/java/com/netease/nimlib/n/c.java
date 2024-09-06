package com.netease.nimlib.n;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ChatRoomLoginEventManager.java */
/* loaded from: classes.dex */
public class c {
    private final Map<String, com.netease.nimlib.n.e.b> a = new HashMap();

    /* compiled from: ChatRoomLoginEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final c a = new c();
    }

    public static c a() {
        return a.a;
    }

    public void a(String str, String str2, boolean z) {
        try {
            com.netease.nimlib.n.e.b bVar = new com.netease.nimlib.n.e.b();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            bVar.a(a2);
            bVar.c(Long.parseLong(str));
            bVar.d(com.netease.nimlib.n.f.a.a(a2));
            if (str2 == null) {
                str2 = com.netease.nimlib.c.n();
            }
            bVar.d(str2);
            bVar.g(com.netease.nimlib.o.p.i(com.netease.nimlib.c.e()));
            bVar.c(z ? "auto_login" : "manual_login");
            com.netease.nimlib.log.b.G("ChatRoomEventManager startTrackEvent model = " + bVar.m());
            this.a.put(str, bVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ChatRoomEventManager", " startTrackEvent Exception", th);
        }
    }

    public void a(String str, List<String> list) {
        try {
            com.netease.nimlib.log.b.G("ChatRoomEventManager updateLinkAddressList linkList = " + list);
            com.netease.nimlib.n.e.b bVar = this.a.get(str);
            if (bVar == null || list == null) {
                return;
            }
            bVar.e(com.netease.nimlib.o.f.f(list));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ChatRoomEventManager", " updateLinkAddressList Exception", th);
        }
    }

    public void a(String str, String str2) {
        try {
            com.netease.nimlib.log.b.G("ChatRoomEventManager updateSuccessLinkAddress currentLinkAddress = " + str2);
            com.netease.nimlib.n.e.b bVar = this.a.get(str);
            if (bVar != null) {
                bVar.f(str2);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ChatRoomEventManager", " updateCurrentLinkAddress Exception", th);
        }
    }

    public void a(String str, int i) {
        try {
            com.netease.nimlib.log.b.G("ChatRoomEventManagerstopTrackEvent resultCode = " + i);
            com.netease.nimlib.n.e.b remove = this.a.remove(str);
            if (remove != null) {
                remove.b(i);
                remove.b(com.netease.nimlib.n.f.a.a(remove.a()));
                if (i != 200) {
                    if (com.netease.nimlib.n.a.a.b.containsKey(Integer.valueOf(i))) {
                        remove.h(com.netease.nimlib.n.a.a.b.get(Integer.valueOf(i)));
                    } else {
                        remove.h(com.netease.nimlib.n.a.a.a);
                    }
                }
                com.netease.nimlib.apm.a.a("chatroomLogin", (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) remove);
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ChatRoomEventManager", " stopTrackEvent Exception", e);
        }
    }
}
