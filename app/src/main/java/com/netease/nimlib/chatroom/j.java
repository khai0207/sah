package com.netease.nimlib.chatroom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: ChatRoomSendTaskManager.java */
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.g.d {
    private Map<String, com.netease.nimlib.biz.g.d> a;

    @Override // com.netease.nimlib.biz.g.d
    public void a() {
    }

    @Override // com.netease.nimlib.biz.g.d
    public void b() {
        if (this.a != null) {
            synchronized (this) {
                Iterator<com.netease.nimlib.biz.g.d> it = this.a.values().iterator();
                while (it.hasNext()) {
                    it.next().b();
                }
                this.a.clear();
            }
        }
    }

    @Override // com.netease.nimlib.biz.g.d
    public com.netease.nimlib.biz.d.a c(com.netease.nimlib.biz.e.a aVar) {
        if (this.a == null) {
            return null;
        }
        synchronized (this) {
            Iterator<com.netease.nimlib.biz.g.d> it = this.a.values().iterator();
            while (it.hasNext()) {
                com.netease.nimlib.biz.d.a c = it.next().c(aVar);
                if (c != null) {
                    return c;
                }
            }
            return null;
        }
    }

    @Override // com.netease.nimlib.biz.g.d
    public boolean a(com.netease.nimlib.biz.g.c cVar) {
        if (cVar instanceof o) {
            return d(((o) cVar).h()).a(cVar);
        }
        return false;
    }

    @Override // com.netease.nimlib.biz.g.d, com.netease.nimlib.biz.c.h
    public boolean a(com.netease.nimlib.biz.e.a aVar) {
        if (this.a == null) {
            return false;
        }
        synchronized (this) {
            Iterator<com.netease.nimlib.biz.g.d> it = this.a.values().iterator();
            while (it.hasNext()) {
                if (it.next().a(aVar)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.netease.nimlib.biz.g.d, com.netease.nimlib.biz.c.h
    public boolean b(com.netease.nimlib.biz.e.a aVar) {
        if (this.a == null) {
            return false;
        }
        synchronized (this) {
            Iterator<com.netease.nimlib.biz.g.d> it = this.a.values().iterator();
            while (it.hasNext()) {
                if (it.next().b(aVar)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void a(String str) {
        d(str).c();
    }

    public void b(String str) {
        d(str).d();
    }

    public void c(String str) {
        if (this.a != null) {
            synchronized (this) {
                if (this.a.containsKey(str)) {
                    this.a.get(str).b();
                    this.a.remove(str);
                }
            }
        }
    }

    private Map<String, com.netease.nimlib.biz.g.d> f() {
        if (this.a == null) {
            synchronized (this) {
                if (this.a == null) {
                    this.a = new HashMap(1);
                }
            }
        }
        return this.a;
    }

    private com.netease.nimlib.biz.g.d d(String str) {
        com.netease.nimlib.biz.g.d dVar = f().get(str);
        if (dVar == null) {
            synchronized (this) {
                dVar = this.a.get(str);
                if (dVar == null) {
                    dVar = new com.netease.nimlib.biz.g.d();
                    dVar.a();
                    this.a.put(str, dVar);
                }
            }
        }
        return dVar;
    }
}
