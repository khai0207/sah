package com.netease.nimlib.n;

import java.util.HashMap;
import java.util.Map;

/* compiled from: ResourceDownloadEventManager.java */
/* loaded from: classes.dex */
public class n {
    private final Map<String, com.netease.nimlib.n.e.i> a = new HashMap();

    /* compiled from: ResourceDownloadEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final n a = new n();
    }

    public static n a() {
        return a.a;
    }

    public void a(String str, int i) {
        try {
            com.netease.nimlib.n.e.i r = com.netease.nimlib.n.e.i.r();
            boolean a2 = com.netease.nimlib.n.f.a.a();
            r.a(a2);
            r.a(com.netease.nimlib.n.f.a.a(a2));
            r.a(com.netease.nimlib.c.n());
            r.c(String.valueOf(com.netease.nimlib.n.b.o.kResourceDownload.a()));
            r.b(i);
            r.e(str);
            com.netease.nimlib.log.b.G("startTrackDownloadEvent resourceEventModel = " + r.m());
            this.a.put(str, r);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ResourceDownloadEventManager", "startTrackDownloadEvent Exception", th);
        }
    }

    public void a(String str, long j) {
        com.netease.nimlib.n.e.i iVar = this.a.get(str);
        if (iVar != null) {
            iVar.c(j);
        }
    }

    public void a(String str, long j, long j2) {
        com.netease.nimlib.n.e.i iVar = this.a.get(str);
        if (iVar != null) {
            iVar.e(j - iVar.t());
            iVar.d(j2);
        }
    }

    public void b(String str, int i) {
        try {
            com.netease.nimlib.log.b.G("stopTrackNosDownloadEvent state = " + i);
            com.netease.nimlib.n.e.i remove = this.a.remove(str);
            if (remove != null) {
                com.netease.nimlib.log.b.G("stopTrackNosDownloadEvent model is not empty");
                remove.a(i);
                remove.b(com.netease.nimlib.n.f.a.a(remove.a()));
                com.netease.nimlib.apm.a.a("nim_sdk_resources", (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) remove);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ResourceDownloadEventManager", "stopTrackNosDownloadEvent Exception", th);
        }
    }
}
