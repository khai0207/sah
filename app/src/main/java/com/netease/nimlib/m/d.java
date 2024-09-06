package com.netease.nimlib.m;

import android.os.Handler;
import android.os.Looper;
import com.netease.nimlib.biz.d.d.l;
import com.netease.nimlib.biz.e.d.i;
import com.netease.nimlib.biz.j;
import com.netease.nimlib.sdk.migration.model.MigrationConstant;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NtpDaemonUi.java */
/* loaded from: classes.dex */
public class d extends b {
    private int a;
    private final Map<Short, f> c;
    private final Map<Short, a> d;
    private final j e;
    private final com.netease.nimlib.m.a f;
    private long b = 0;
    private final Runnable g = new Runnable() { // from class: com.netease.nimlib.m.-$$Lambda$p7-MWbkVI4Yw6GlIOkdKnTvrOHY
        @Override // java.lang.Runnable
        public final void run() {
            d.this.e();
        }
    };

    /* compiled from: NtpDaemonUi.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(int i, String str);

        void a(long j);
    }

    public d(j jVar, com.netease.nimlib.m.a aVar) {
        com.netease.nimlib.log.b.d("NtpDaemon", String.format("NtpDaemon %s", aVar));
        this.e = jVar;
        this.f = aVar;
        this.a = 0;
        this.c = new HashMap();
        this.d = new HashMap();
    }

    public synchronized void d() {
        this.a = 0;
    }

    public synchronized void a(final a aVar, boolean z) {
        com.netease.nimlib.log.b.d("NtpDaemon", String.format("refreshNow with callback %s forceRequest %s", aVar, Boolean.valueOf(z)));
        if (a() && !z && System.currentTimeMillis() - this.b <= this.f.d()) {
            if (this.a > this.f.c()) {
                com.netease.nimlib.log.b.d("NtpDaemon", String.format("refreshNow skip as requestCount %s config %s %s", Integer.valueOf(this.a), this.f, aVar));
                if (aVar != null) {
                    aVar.a(c());
                }
                return;
            } else if (b().a().b() <= this.f.a()) {
                com.netease.nimlib.log.b.d("NtpDaemon", String.format("refreshNow skip as best rtt: %s originTimestamp %s config %s callback %s", Long.valueOf(b().a().b()), b(), this.f, aVar));
                if (aVar != null) {
                    aVar.a(c());
                }
                return;
            }
        }
        this.e.a(new com.netease.nimlib.biz.g.b(new l()) { // from class: com.netease.nimlib.m.d.1
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                synchronized (d.this) {
                    i iVar = (i) aVar2;
                    if (aVar2.n()) {
                        com.netease.nimlib.log.b.d("NtpDaemon", String.format("[SID 6 , CID 23] response: serialId %s code %s %s ", Short.valueOf(aVar2.j().k()), Short.valueOf(aVar2.r()), Long.valueOf(iVar.a())));
                    } else {
                        com.netease.nimlib.log.b.d("NtpDaemon", String.format("[SID 6 , CID 23] response: serialId %s code %s", Short.valueOf(aVar2.j().k()), Short.valueOf(aVar2.r())));
                    }
                    d.this.d.put(Short.valueOf(aVar2.j().k()), aVar);
                    d.this.a(iVar);
                }
            }
        });
    }

    public synchronized void a(short s, long j) {
        com.netease.nimlib.log.b.d("NtpDaemon", String.format("markNtpRequestSentUi serialId %s elapsedRealtime %s cache %s", Short.valueOf(s), Long.valueOf(j), com.netease.nimlib.o.f.f(this.c.entrySet())));
        f fVar = new f();
        fVar.a(j);
        this.c.put(Short.valueOf(s), fVar);
    }

    public synchronized boolean b(short s, long j) {
        com.netease.nimlib.log.b.d("NtpDaemon", String.format("markNtpResponseReceivedUi serialId %s elapsedRealtime %s cache %s", Short.valueOf(s), Long.valueOf(j), com.netease.nimlib.o.f.f(this.c.entrySet())));
        f fVar = this.c.get(Short.valueOf(s));
        if (fVar == null) {
            com.netease.nimlib.log.b.f("NtpDaemon", String.format("markNtpResponseReceived no record serialId %s %s %s", Short.valueOf(s), Boolean.valueOf(this.c.containsKey(Short.valueOf(s))), Long.valueOf(j)));
            return false;
        }
        fVar.b(j);
        return a(s, fVar);
    }

    public synchronized boolean a(i iVar) {
        short k = iVar.j().k();
        com.netease.nimlib.log.b.d("NtpDaemon", String.format("handleNtpResponse serialId %s resCode %s serverTime %s cache %s", Short.valueOf(k), Short.valueOf(iVar.r()), Long.valueOf(iVar.a()), com.netease.nimlib.o.f.f(this.c.entrySet())));
        f fVar = this.c.get(Short.valueOf(k));
        if (fVar == null) {
            com.netease.nimlib.log.b.f("NtpDaemon", String.format("handleNtpResponse no record serialId: %s %s", Short.valueOf(k), Boolean.valueOf(this.c.containsKey(Short.valueOf(k)))));
            return false;
        }
        if (!iVar.n()) {
            this.c.remove(Short.valueOf(k));
            a remove = this.d.remove(Short.valueOf(k));
            if (remove != null) {
                remove.a(iVar.r(), "");
                com.netease.nimlib.log.b.d("NtpDaemon", String.format("handleNtpResponse ntpCallback onFailed: %s %s", Short.valueOf(k), Short.valueOf(iVar.r())));
            }
            return false;
        }
        this.a++;
        fVar.a(iVar.a(), System.currentTimeMillis());
        return a(k, fVar);
    }

    private boolean a(short s, f fVar) {
        com.netease.nimlib.log.b.d("NtpDaemon", String.format("checkNtpRecord: %s %s", Short.valueOf(s), fVar));
        if (!fVar.a()) {
            com.netease.nimlib.log.b.e("NtpDaemon", String.format("checkNtpRecord response received timestamp invalid: %s %s", Short.valueOf(s), fVar));
            return false;
        }
        this.c.remove(Short.valueOf(s));
        a remove = this.d.remove(Short.valueOf(s));
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.b > this.f.d()) {
            this.b = currentTimeMillis;
            a(fVar.c());
            com.netease.nimlib.log.b.d("NtpDaemon", String.format("checkNtpRecord > TTL,serialId %s originTimestamp %s ntpRecord %s now %s", Short.valueOf(s), b(), fVar, Long.valueOf(c())));
            e();
            if (remove != null) {
                remove.a(c());
            }
            return true;
        }
        if (fVar.b() > this.f.b()) {
            String format = String.format("checkNtpRecord skip as rtt exceed: serialId %s ntpRecord %s ntpConfig %s", Short.valueOf(s), fVar, this.f);
            if (a()) {
                com.netease.nimlib.log.b.e("NtpDaemon", String.format("%s now %s", format, Long.valueOf(c())));
            } else {
                com.netease.nimlib.log.b.e("NtpDaemon", format);
            }
            if (remove != null) {
                if (a()) {
                    remove.a(c());
                } else {
                    remove.a(MigrationConstant.IMPORT_ERR_RECORD_EMPTY, format);
                }
            }
            return false;
        }
        if (a() && fVar.b() > b().a().b()) {
            com.netease.nimlib.log.b.e("NtpDaemon", String.format("checkNtpRecord skip as rtt worse: serialId %s %s > %s ntpRecord %s originTimestamp %s now %s", Short.valueOf(s), Long.valueOf(fVar.b()), Long.valueOf(b().a().b()), fVar, b(), Long.valueOf(c())));
            if (remove != null) {
                remove.a(c());
            }
            return false;
        }
        a(fVar.c());
        com.netease.nimlib.log.b.d("NtpDaemon", String.format("checkNtpRecord serialId %s originTimestamp %s ntpRecord %s now %s", Short.valueOf(s), b(), fVar, Long.valueOf(c())));
        e();
        if (remove != null) {
            remove.a(c());
        }
        return true;
    }

    public synchronized void e() {
        try {
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("NtpDaemon", "updateNtpToPush " + e, e);
        }
        if (a()) {
            int b = com.netease.nimlib.ipc.cp.a.a.a(com.netease.nimlib.c.e(), "NIM").b("KEY_UPDATE_NTP", b().c().toString());
            com.netease.nimlib.log.b.d("NtpDaemon", String.format("updateNtpToPush ipcUpdated %s", Integer.valueOf(b)));
            if (b <= 0) {
                new Handler(Looper.getMainLooper()).removeCallbacks(this.g);
                new Handler(Looper.getMainLooper()).postDelayed(this.g, 3000L);
            }
        }
    }
}
