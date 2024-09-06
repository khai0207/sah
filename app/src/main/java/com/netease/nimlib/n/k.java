package com.netease.nimlib.n;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PushExceptionEventManager.java */
/* loaded from: classes.dex */
public class k extends b {
    private final Map<String, com.netease.nimlib.n.e.d> a = new HashMap();

    /* compiled from: PushExceptionEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final k a = new k();
    }

    public static k a() {
        return a.a;
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, String str, com.netease.nimlib.n.b.g gVar) {
        a(fVar, str, gVar, (com.netease.nimlib.n.c.d) null);
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, String str, com.netease.nimlib.n.b.g gVar, com.netease.nimlib.n.c.d dVar) {
        if (fVar != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                com.netease.nimlib.n.e.d dVar2 = new com.netease.nimlib.n.e.d();
                dVar2.a(com.netease.nimlib.n.f.a.a());
                dVar2.a(com.netease.nimlib.n.f.a.a(dVar2.a()));
                dVar2.a(com.netease.nimlib.c.n());
                dVar2.a(fVar);
                dVar2.b(com.netease.nimlib.push.f.l().k());
                dVar2.a(gVar);
                if (dVar != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(dVar);
                    dVar2.a(arrayList);
                }
                com.netease.nimlib.log.b.G("PushExceptionEventManager startTrackEvent model = " + dVar2.m());
                this.a.put(fVar.a() + "_" + str, dVar2);
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("PushExceptionEventManager", " startTrackEvent Exception", th);
            }
        }
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, String str, com.netease.nimlib.n.c.d dVar, com.netease.nimlib.n.b.h hVar) {
        a(fVar, str, "", dVar, hVar);
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, String str, String str2, com.netease.nimlib.n.c.d dVar, com.netease.nimlib.n.b.h hVar) {
        if (fVar != null) {
            try {
                if (!TextUtils.isEmpty(str) && hVar != null) {
                    com.netease.nimlib.log.b.G("PushExceptionEventManager stopTrackEvent state = " + hVar);
                    if (hVar == com.netease.nimlib.n.b.h.kSucceed) {
                        this.a.remove(fVar.a() + "_" + str);
                        return;
                    }
                    com.netease.nimlib.n.e.d remove = this.a.remove(fVar.a() + "_" + str);
                    if (remove == null) {
                        return;
                    }
                    if (remove.f() == null) {
                        remove.a(fVar);
                    }
                    remove.a(hVar.a());
                    remove.b(com.netease.nimlib.n.f.a.a(remove.a()));
                    remove.d(str2);
                    if (dVar != null) {
                        List<com.netease.nimlib.n.c.d> l = remove.l();
                        if (l == null) {
                            l = new ArrayList<>();
                            remove.a(l);
                        }
                        l.add(dVar);
                    }
                    com.netease.nimlib.ipc.e.a(remove);
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("PushExceptionEventManager", " stopTrackEvent Exception", th);
            }
        }
    }

    @Override // com.netease.nimlib.n.b
    public void a(com.netease.nimlib.n.b.f fVar, com.netease.nimlib.n.c.d dVar, com.netease.nimlib.n.b.h hVar) {
        if (fVar == null || hVar == null) {
            return;
        }
        try {
            com.netease.nimlib.log.b.G("PushExceptionEventManager recordTrackEvent state = " + hVar);
            if (hVar == com.netease.nimlib.n.b.h.kSucceed) {
                return;
            }
            com.netease.nimlib.n.e.d dVar2 = new com.netease.nimlib.n.e.d();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            dVar2.a(a2);
            long a3 = com.netease.nimlib.n.f.a.a(a2);
            dVar2.a(a3);
            dVar2.a(com.netease.nimlib.c.n());
            dVar2.a(fVar);
            dVar2.b(com.netease.nimlib.push.f.l().k());
            dVar2.a(hVar.a());
            dVar2.b(a3);
            dVar2.a(com.netease.nimlib.n.b.g.UNKNOWN);
            if (dVar != null) {
                List<com.netease.nimlib.n.c.d> l = dVar2.l();
                if (l == null) {
                    l = new ArrayList<>();
                    dVar2.a(l);
                }
                l.add(dVar);
            }
            com.netease.nimlib.ipc.e.a(dVar2);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushExceptionEventManager", " recordTrackEvent Exception", th);
        }
    }
}
