package com.netease.nimlib.n;

import android.util.Log;
import android.util.SparseArray;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: ApiTraceEventManager.java */
/* loaded from: classes.dex */
public class a {
    private static String d;
    private final SparseArray<com.netease.nimlib.n.e.a> a = new SparseArray<>();
    private final List<com.netease.nimlib.n.e.a> b = new CopyOnWriteArrayList();
    private Boolean c;

    /* compiled from: ApiTraceEventManager.java */
    /* renamed from: com.netease.nimlib.n.a$a */
    /* loaded from: classes.dex */
    private static class C0043a {
        private static final a a = new a();
    }

    public static a a() {
        return C0043a.a;
    }

    private static String b() {
        if (d == null) {
            d = UUID.randomUUID().toString();
        }
        return d;
    }

    public void a(boolean z) {
        this.c = Boolean.valueOf(z);
        com.netease.nimlib.log.b.H("setApiTraceOpen isApiTraceOpen = " + this.c);
        try {
            if (!Boolean.TRUE.equals(this.c) || this.b.isEmpty()) {
                return;
            }
            com.netease.nimlib.log.b.H("setApiTraceOpen pendingEventList.size = " + this.b.size());
            for (com.netease.nimlib.n.e.a aVar : this.b) {
                com.netease.nimlib.apm.a.a(aVar.o(), (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) aVar);
            }
            this.b.clear();
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ApiTraceEventManager", " setApiTraceOpen Exception", e);
        }
    }

    public void a(com.netease.nimlib.i.k kVar) {
        if (d(kVar)) {
            try {
                com.netease.nimlib.n.e.a aVar = new com.netease.nimlib.n.e.a();
                boolean a = com.netease.nimlib.n.f.a.a();
                aVar.a(a);
                aVar.a(com.netease.nimlib.n.f.a.a(a));
                aVar.a(com.netease.nimlib.c.n());
                aVar.a(kVar.k() ? com.netease.nimlib.n.b.a.K_SYNC : com.netease.nimlib.n.b.a.K_ASYNC);
                aVar.b(com.netease.nimlib.biz.i.a().d());
                aVar.d(kVar.e());
                aVar.e(kVar.f());
                aVar.g(b());
                this.a.put(kVar.h(), aVar);
                com.netease.nimlib.log.b.G(String.format(Locale.ENGLISH, "startTrackEvent ApiTraceEvent,id = %d,model = %s", Integer.valueOf(kVar.h()), aVar.m()));
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("ApiTraceEventManager", " startTrackEvent ApiTraceEvent Exception", th);
            }
        }
    }

    public void b(com.netease.nimlib.i.k kVar) {
        if (d(kVar)) {
            try {
                com.netease.nimlib.n.e.a aVar = this.a.get(kVar.h());
                if (aVar == null) {
                    return;
                }
                this.a.remove(kVar.h());
                aVar.b(com.netease.nimlib.n.f.a.a(aVar.a()));
                aVar.a(com.netease.nimlib.n.b.h.kAborted);
                com.netease.nimlib.log.b.G(String.format(Locale.ENGLISH, "abortTrackEvent ApiTraceEvent,id = %d,model = %s", Integer.valueOf(kVar.h()), aVar.m()));
                com.netease.nimlib.apm.a.a(aVar.o(), (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) aVar);
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("ApiTraceEventManager", " abortTrackEvent ApiTraceEvent Exception", th);
            }
        }
    }

    public void c(com.netease.nimlib.i.k kVar) {
        if (d(kVar)) {
            try {
                com.netease.nimlib.n.e.a aVar = this.a.get(kVar.h());
                if (aVar == null) {
                    return;
                }
                this.a.remove(kVar.h());
                aVar.a(com.netease.nimlib.c.n());
                aVar.b(com.netease.nimlib.n.f.a.a(aVar.a()));
                int i = kVar.i();
                Object j = kVar.j();
                if (j instanceof Throwable) {
                    aVar.f(Log.getStackTraceString((Throwable) j));
                }
                if (!kVar.k()) {
                    if (i == 0) {
                        i = 200;
                    }
                    aVar.b(i);
                    aVar.a(i == 200 ? com.netease.nimlib.n.b.h.kSucceed : com.netease.nimlib.n.b.h.kFailed);
                } else {
                    com.netease.nimlib.n.b.h hVar = i != 1000 ? com.netease.nimlib.n.b.h.kSucceed : com.netease.nimlib.n.b.h.kFailed;
                    if (hVar == com.netease.nimlib.n.b.h.kSucceed) {
                        i = 200;
                    }
                    aVar.b(i);
                    aVar.a(hVar);
                }
                if ("login".equals(aVar.s()) && aVar.h()) {
                    aVar.b(com.netease.nimlib.biz.i.a().d());
                }
                com.netease.nimlib.log.b.G(String.format(Locale.ENGLISH, "stopTrackEvent ApiTraceEvent,id = %d,model = %s", Integer.valueOf(kVar.h()), aVar.m()));
                com.netease.nimlib.apm.a.a(aVar.o(), (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) aVar);
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("ApiTraceEventManager", " stopTrackEvent ApiTraceEvent Exception", th);
            }
        }
    }

    public void a(long j, String str, String str2) {
        try {
            com.netease.nimlib.n.e.a aVar = new com.netease.nimlib.n.e.a();
            aVar.a(j);
            aVar.a(com.netease.nimlib.c.n());
            aVar.a(com.netease.nimlib.n.b.a.K_SYNC);
            aVar.b(com.netease.nimlib.biz.i.a().d());
            aVar.d(str);
            aVar.e(str2);
            aVar.g(b());
            aVar.b(System.currentTimeMillis());
            com.netease.nimlib.n.b.h hVar = com.netease.nimlib.n.b.h.kSucceed;
            aVar.b(200);
            aVar.a(hVar);
            if (this.c == null) {
                this.b.add(aVar);
            } else if (Boolean.TRUE.equals(this.c)) {
                com.netease.nimlib.apm.a.a(aVar.o(), (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) aVar);
            }
            com.netease.nimlib.log.b.G(String.format(Locale.ENGLISH, "recordPendingTrackEvent ApiTraceEvent,model = %s", aVar.m()));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ApiTraceEventManager", " recordPendingTrackEvent ApiTraceEvent Exception", th);
        }
    }

    private boolean d(com.netease.nimlib.i.k kVar) {
        return Boolean.TRUE.equals(this.c) && kVar != null;
    }
}
