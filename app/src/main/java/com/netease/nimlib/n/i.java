package com.netease.nimlib.n;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* compiled from: NosUploadEventManager.java */
/* loaded from: classes.dex */
public class i {
    private final Map<String, com.netease.nimlib.n.e.h> a = new HashMap();

    /* compiled from: NosUploadEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final i a = new i();
    }

    public static i a() {
        return a.a;
    }

    public void a(String str) {
        try {
            com.netease.nimlib.n.e.h hVar = new com.netease.nimlib.n.e.h();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            hVar.a(a2);
            hVar.a(com.netease.nimlib.n.f.a.a(a2));
            hVar.a(com.netease.nimlib.c.n());
            hVar.c("upload");
            com.netease.nimlib.log.b.G("startTrackNosUploadEvent nosUploadEventModel = " + hVar.m());
            this.a.put(str, hVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NosUploadEventManager", "startTrackNosUploadEvent Exception", th);
        }
    }

    public void b(String str) {
        com.netease.nimlib.n.e.h hVar = this.a.get(str);
        if (hVar != null) {
            hVar.c(System.currentTimeMillis());
        }
    }

    public void a(String str, String str2, int i) {
        try {
            com.netease.nimlib.n.e.h hVar = this.a.get(str);
            if (hVar != null) {
                List<com.netease.nimlib.n.c.j> l = hVar.l();
                if (l == null) {
                    l = new LinkedList<>();
                    hVar.a(l);
                }
                com.netease.nimlib.n.c.j jVar = new com.netease.nimlib.n.c.j();
                jVar.c(str2);
                jVar.a(i);
                jVar.a(false);
                jVar.b("HTTP");
                jVar.a(hVar.r());
                jVar.b(System.currentTimeMillis());
                if (!a(l, jVar)) {
                    com.netease.nimlib.log.b.G("updateNosUploadError error is same");
                }
                com.netease.nimlib.log.b.G("updateNosUploadError error = " + jVar.d());
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NosUploadEventManager", "updateNosUploadError Exception", th);
        }
    }

    public void c(String str) {
        com.netease.nimlib.n.e.h hVar = this.a.get(str);
        if (hVar != null) {
            hVar.d(System.currentTimeMillis());
        }
    }

    public void a(String str, int i) {
        try {
            com.netease.nimlib.n.e.h hVar = this.a.get(str);
            if (hVar != null) {
                List<com.netease.nimlib.n.c.j> l = hVar.l();
                if (l == null) {
                    l = new LinkedList<>();
                    hVar.a(l);
                }
                com.netease.nimlib.n.c.j jVar = new com.netease.nimlib.n.c.j();
                jVar.c("6_18");
                jVar.a(i);
                jVar.a(false);
                jVar.b("protocol");
                jVar.a(hVar.s());
                jVar.b(System.currentTimeMillis());
                if (!a(l, jVar)) {
                    com.netease.nimlib.log.b.G("updateFileQuickTransferResponseError error is same");
                }
                com.netease.nimlib.log.b.G("updateFileQuickTransferResponseError error = " + jVar.d());
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NosUploadEventManager", "updateFileQuickTransferResponseError Exception", th);
        }
    }

    private boolean a(List<com.netease.nimlib.n.c.j> list, com.netease.nimlib.n.c.j jVar) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            list.add(jVar);
        } else {
            if (list.get(list.size() - 1).equals(jVar)) {
                return false;
            }
            list.add(jVar);
        }
        return true;
    }

    public void b(String str, int i) {
        try {
            com.netease.nimlib.log.b.G("stopTrackNosUploadEvent state = " + i);
            com.netease.nimlib.n.e.h remove = this.a.remove(str);
            if (remove != null) {
                com.netease.nimlib.log.b.G("stopTrackNosUploadEvent model is not empty");
                remove.a(i);
                remove.b(com.netease.nimlib.biz.i.a().b().c());
                com.netease.nimlib.apm.a.a("nos", (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) remove);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NosUploadEventManager", "stopTrackNosUploadEvent Exception", th);
        }
    }
}
