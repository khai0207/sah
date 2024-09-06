package com.netease.nimlib.apm.event;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.netease.nimlib.apm.event.a;
import com.netease.nimlib.c;
import com.netease.nimlib.net.a.d.a;
import com.netease.nimlib.network.f;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: EventReporter.java */
/* loaded from: classes.dex */
public class a {
    private final Handler a = new Handler(Looper.getMainLooper());
    private final Handler b = com.netease.nimlib.c.b.a.c().a("EventReporter");
    private com.netease.nimlib.apm.event.d.b c = null;
    private final com.netease.nimlib.apm.a.a d = new com.netease.nimlib.apm.a.a();
    private final com.netease.nimlib.apm.event.d.a e = new com.netease.nimlib.apm.event.d.a();
    private ScheduledExecutorService f = null;

    /* compiled from: EventReporter.java */
    /* loaded from: classes.dex */
    private static class b {
        private static final a a = new a();
    }

    public Handler a() {
        return this.a;
    }

    public static a b() {
        return b.a;
    }

    public static com.netease.nimlib.apm.a.a c() {
        return b().d;
    }

    public void a(final Context context) {
        this.b.post(new Runnable() { // from class: com.netease.nimlib.apm.event.-$$Lambda$a$M5CFxLoYFcpYmd3vrrIUL6haGS4
            @Override // java.lang.Runnable
            public final void run() {
                a.this.c(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void c(Context context) throws Exception {
        com.netease.nimlib.apm.event.b.a.a(context);
        int c = com.netease.nimlib.apm.event.b.a.c();
        com.netease.nimlib.log.b.v("event db currentEventCount = " + c);
        if (c >= 10000) {
            com.netease.nimlib.log.b.u("clear event db");
            com.netease.nimlib.apm.event.b.a.a();
        }
    }

    public void a(final String str, final Map<String, Object> map, final long j) {
        if (c.i().disableReport) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.netease.nimlib.apm.event.-$$Lambda$a$CREfSGUDSupvTShg2FU5fm9jrVU
            @Override // java.lang.Runnable
            public final void run() {
                a.this.c(str, map, j);
            }
        });
    }

    public void d() {
        this.b.post(new Runnable() { // from class: com.netease.nimlib.apm.event.-$$Lambda$a$xfBKC-gD7ae4i5HTbmI0wUfLXbs
            @Override // java.lang.Runnable
            public final void run() {
                a.k();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void k() {
        try {
            com.netease.nimlib.apm.event.b.a.a();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void c(String str, Map<String, Object> map, long j) {
        if (TextUtils.isEmpty(str)) {
            com.netease.nimlib.log.b.G("recordEvent eventId is isEmpty");
            return;
        }
        if (map == null) {
            com.netease.nimlib.log.b.G("recordEvent event data is null");
            return;
        }
        String jSONObject = new JSONObject(map).toString();
        if (jSONObject.length() >= 2048) {
            com.netease.nimlib.log.b.H("event " + str + " data length = " + jSONObject.length());
        }
        if (this.f == null) {
            e();
        }
        com.netease.nimlib.apm.event.b.a.a(new com.netease.nimlib.apm.event.c.a(str, jSONObject, j));
        b(true);
    }

    public void e() {
        this.b.post(new Runnable() { // from class: com.netease.nimlib.apm.event.-$$Lambda$a$4KEtXBhkmga-z8HaNNeJ0uf5Uic
            @Override // java.lang.Runnable
            public final void run() {
                a.this.j();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j() {
        try {
            a(false);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(boolean z) {
        com.netease.nimlib.log.b.G("start report event，isReportMinInterval = " + z);
        h();
        com.netease.nimlib.apm.event.c.b d = this.d.d();
        if (d == null || !d.f()) {
            return false;
        }
        long d2 = z ? d.d() : d.c();
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new ThreadFactory() { // from class: com.netease.nimlib.apm.event.-$$Lambda$a$T1vzCSI1NM3usFBj6-lbCckH7J8
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread a;
                a = a.a(runnable);
                return a;
            }
        });
        this.f = newScheduledThreadPool;
        newScheduledThreadPool.scheduleWithFixedDelay(new Runnable() { // from class: com.netease.nimlib.apm.event.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.b(false);
                } catch (Throwable unused) {
                }
            }
        }, d2, d2, TimeUnit.MILLISECONDS);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread a(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("EventReporter_SingleThreadPool");
        return thread;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(boolean z) {
        com.netease.nimlib.log.b.G("check report condition");
        if (!f.a(c.e())) {
            com.netease.nimlib.log.b.G("unable to report event, as network is unavailable!");
            this.e.a();
            int c = com.netease.nimlib.apm.event.b.a.c();
            com.netease.nimlib.log.b.G("unable to report event, currentEventCount = " + c);
            if (c >= 10000) {
                com.netease.nimlib.log.b.u("clear event db");
                com.netease.nimlib.apm.event.b.a.a();
            }
            return;
        }
        com.netease.nimlib.apm.event.c.b d = this.d.d();
        if (d == null) {
            com.netease.nimlib.log.b.G("EventReportStrategy is null!");
            h();
            return;
        }
        if (!d.f()) {
            com.netease.nimlib.log.b.G("EventReportStrategy is invalid!");
            h();
            com.netease.nimlib.apm.b.a().b();
            return;
        }
        com.netease.nimlib.apm.event.b.a.a(System.currentTimeMillis() - d.e());
        int c2 = com.netease.nimlib.apm.event.b.a.c();
        com.netease.nimlib.log.b.G("current totalEventCount = " + c2);
        if (c2 >= d.b()) {
            List<com.netease.nimlib.apm.event.c.a> a = com.netease.nimlib.apm.event.b.a.a(d.b());
            com.netease.nimlib.log.b.G("event >= maxsize");
            a(d, a, c2);
        } else if (!z) {
            List<com.netease.nimlib.apm.event.c.a> b2 = com.netease.nimlib.apm.event.b.a.b();
            if (b2.size() > 0) {
                a(d, b2, c2);
            } else {
                h();
            }
        }
    }

    private void a(com.netease.nimlib.apm.event.c.b bVar, List<com.netease.nimlib.apm.event.c.a> list, int i) {
        if (bVar == null) {
            com.netease.nimlib.log.b.G("reportEventList reportStrategy == null");
            return;
        }
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            com.netease.nimlib.log.b.G("reportEventList is null");
            return;
        }
        int b2 = bVar.b();
        com.netease.nimlib.log.b.G("report event size = " + list.size());
        C0024a c0024a = new C0024a(list, new AnonymousClass2(list, b2, i));
        this.e.a();
        com.netease.nimlib.apm.event.d.b bVar2 = this.c;
        if (bVar2 == null) {
            com.netease.nimlib.log.b.G("last report task is null,do current task");
            this.c = c0024a;
            this.e.a(c0024a);
        } else if (bVar2.d() <= 0) {
            com.netease.nimlib.log.b.G("last report task not execute,do current task");
            this.c = c0024a;
            this.e.a(c0024a);
        } else {
            if (System.currentTimeMillis() - this.c.d() > bVar.d()) {
                com.netease.nimlib.log.b.G("last report task execute before minInterval time,do current task");
                this.c = c0024a;
                this.e.a(c0024a);
                return;
            }
            com.netease.nimlib.log.b.G("last report task execute in minInterval time,wait");
        }
    }

    /* compiled from: EventReporter.java */
    /* renamed from: com.netease.nimlib.apm.event.a$2, reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements com.netease.nimlib.apm.event.a.a {
        final /* synthetic */ List a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;

        AnonymousClass2(List list, int i, int i2) {
            this.a = list;
            this.b = i;
            this.c = i2;
        }

        @Override // com.netease.nimlib.apm.event.a.a
        public void a(int i, String str, Throwable th) {
            com.netease.nimlib.log.b.G("report event result = " + i + ",Response = " + str);
            if (i == 200) {
                Handler handler = a.this.b;
                final List list = this.a;
                final int i2 = this.b;
                final int i3 = this.c;
                handler.post(new Runnable() { // from class: com.netease.nimlib.apm.event.-$$Lambda$a$2$eUz-GgtlpxnKqml7RbnRlHiS4eU
                    @Override // java.lang.Runnable
                    public final void run() {
                        a.AnonymousClass2.this.a(list, i2, i3);
                    }
                });
                return;
            }
            a.this.b.post(new Runnable() { // from class: com.netease.nimlib.apm.event.-$$Lambda$a$2$Beh16DRa2_gwdbTt4lnp8LW3zV4
                @Override // java.lang.Runnable
                public final void run() {
                    a.AnonymousClass2.a();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(List list, int i, int i2) {
            try {
                com.netease.nimlib.apm.event.b.a.a((List<com.netease.nimlib.apm.event.c.a>) list);
                int c = com.netease.nimlib.apm.event.b.a.c();
                com.netease.nimlib.log.b.G("after report event success, currentEventCount = " + c);
                if (c >= 10000) {
                    com.netease.nimlib.log.b.u("clear event db");
                    com.netease.nimlib.apm.event.b.a.a();
                    a.this.i();
                } else if (c >= i) {
                    a.this.a(true);
                } else if (i2 > 0) {
                    a.this.a(false);
                } else {
                    a.this.i();
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.a("EventReporter report success, onResult error", th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a() {
            try {
                int c = com.netease.nimlib.apm.event.b.a.c();
                com.netease.nimlib.log.b.G("after report event failed, currentEventCount = " + c);
                if (c >= 10000) {
                    com.netease.nimlib.log.b.u("clear event db");
                    com.netease.nimlib.apm.event.b.a.a();
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.a("EventReporter report failed, onResult error", th);
            }
        }
    }

    public void f() {
        this.b.post(new Runnable() { // from class: com.netease.nimlib.apm.event.-$$Lambda$a$Kwf59EQXqL67NormrQE9bDUKxXo
            @Override // java.lang.Runnable
            public final void run() {
                a.this.i();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public void i() {
        h();
    }

    private void h() {
        ScheduledExecutorService scheduledExecutorService = this.f;
        if (scheduledExecutorService == null) {
            return;
        }
        scheduledExecutorService.shutdown();
        this.f = null;
    }

    /* compiled from: EventReporter.java */
    /* renamed from: com.netease.nimlib.apm.event.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0024a extends com.netease.nimlib.apm.event.d.b {
        private com.netease.nimlib.apm.event.a.a a;
        private List<com.netease.nimlib.apm.event.c.a> b;

        public C0024a(List<com.netease.nimlib.apm.event.c.a> list) {
            this.a = null;
            this.b = null;
            this.b = list;
        }

        public C0024a(List<com.netease.nimlib.apm.event.c.a> list, com.netease.nimlib.apm.event.a.a aVar) {
            this(list);
            this.a = aVar;
        }

        public String a() {
            String a = a.c().a();
            if (a == null) {
                return null;
            }
            if (!a.endsWith("/")) {
                a = a + "/";
            }
            return a + "statics/report/common/form";
        }

        public Map<String, String> b() {
            return a.c().b();
        }

        public String c() {
            List<com.netease.nimlib.apm.event.c.a> list = this.b;
            if (list == null || list.size() == 0) {
                return "";
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("common", new JSONObject(a.c().c()));
                HashMap hashMap = new HashMap();
                for (int i = 0; i < this.b.size(); i++) {
                    com.netease.nimlib.apm.event.c.a aVar = this.b.get(i);
                    String a = aVar.a();
                    JSONArray jSONArray = (JSONArray) hashMap.get(a);
                    if (jSONArray == null) {
                        jSONArray = new JSONArray();
                        hashMap.put(a, jSONArray);
                    }
                    jSONArray.put(new JSONObject(aVar.b()));
                }
                jSONObject.put(NotificationCompat.CATEGORY_EVENT, new JSONObject(hashMap));
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return jSONObject.toString().replace("\\/", "/");
        }

        @Override // com.netease.nimlib.apm.event.d.b, java.lang.Runnable
        public void run() {
            super.run();
            String a = a();
            Map<String, String> b = b();
            String c = c();
            com.netease.nimlib.log.b.G("report event url= " + a);
            com.netease.nimlib.log.b.G("report event header= " + b);
            com.netease.nimlib.log.b.G("report event body= " + c);
            final a.C0047a<String> b2 = com.netease.nimlib.net.a.d.a.b(a, b, c);
            a.b().a().post(new Runnable() { // from class: com.netease.nimlib.apm.event.a.a.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    if (b2.a != 200) {
                        C0024a.this.a(false);
                    } else {
                        C0024a.this.a(true);
                    }
                    if (C0024a.this.a != null) {
                        C0024a.this.a.a(b2.a, (String) b2.c, b2.b);
                    }
                }
            });
        }
    }
}
