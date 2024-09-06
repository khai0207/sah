package com.netease.nimlib.n;

import java.util.HashMap;
import java.util.Map;

/* compiled from: ResourceUploadEventManager.java */
/* loaded from: classes.dex */
public class o {
    private final Map<String, com.netease.nimlib.n.e.i> a = new HashMap();

    /* compiled from: ResourceUploadEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final o a = new o();
    }

    public static o a() {
        return a.a;
    }

    public void a(String str) {
        try {
            com.netease.nimlib.n.e.i r = com.netease.nimlib.n.e.i.r();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            r.a(a2);
            r.a(com.netease.nimlib.n.f.a.a(a2));
            r.a(com.netease.nimlib.c.n());
            r.c(String.valueOf(com.netease.nimlib.n.b.o.kResourcesUpload.a()));
            com.netease.nimlib.log.b.G("startTrackUploadEvent resourceEventModel = " + r.m());
            if (this.a.containsKey(str)) {
                return;
            }
            this.a.put(str, r);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ResourceUploadEventManager", "startTrackUploadEvent Exception", th);
        }
    }

    public void a(String str, int i, long j, long j2) {
        com.netease.nimlib.n.e.i iVar = this.a.get(str);
        if (iVar == null || iVar.u() > 0) {
            return;
        }
        iVar.b(i);
        iVar.d(j);
        if (j2 >= 0) {
            iVar.c(j2);
        }
        iVar.e(0L);
    }

    public void a(String str, long j) {
        com.netease.nimlib.n.e.i iVar = this.a.get(str);
        if (iVar != null) {
            iVar.e(j - iVar.t());
        }
    }

    public void a(String str, int i, String str2) {
        try {
            com.netease.nimlib.log.b.G("stopTrackUploadEvent state = " + i);
            com.netease.nimlib.n.e.i remove = this.a.remove(str);
            if (remove != null) {
                com.netease.nimlib.log.b.G("stopTrackUploadEvent model is not empty");
                remove.a(i);
                remove.e(str2);
                remove.b(com.netease.nimlib.n.f.a.a(remove.a()));
                com.netease.nimlib.apm.a.a("nim_sdk_resources", (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) remove);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ResourceUploadEventManager", "stopTrackUploadEvent Exception", th);
        }
    }
}
