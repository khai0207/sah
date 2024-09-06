package com.netease.nimlib.n;

import android.os.SystemClock;
import com.netease.nimlib.o.w;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: DatabaseTraceEventManager.java */
/* loaded from: classes.dex */
public class d {
    private boolean a = false;
    private int b = 1000;

    /* compiled from: DatabaseTraceEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final d a = new d();
    }

    public static d a() {
        return a.a;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public void a(int i) {
        this.b = i;
    }

    public com.netease.nimlib.n.e.c a(String str) {
        if (!this.a || str.contains("event.db")) {
            return null;
        }
        try {
            String[] b = b();
            com.netease.nimlib.n.e.c cVar = new com.netease.nimlib.n.e.c();
            cVar.a(false);
            cVar.a(SystemClock.uptimeMillis());
            cVar.a(com.netease.nimlib.c.n());
            cVar.a(com.netease.nimlib.n.b.a.K_SYNC);
            cVar.b(com.netease.nimlib.biz.i.a().d());
            cVar.g(com.netease.nimlib.c.r());
            cVar.d(b[0]);
            cVar.e(b[1]);
            cVar.i(b[2]);
            cVar.h(str);
            cVar.c(SystemClock.currentThreadTimeMillis());
            com.netease.nimlib.log.b.G(String.format(Locale.ENGLISH, "startTrackEvent DatabaseTraceEvent,model = %s", cVar.m()));
            return cVar;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("DatabaseTraceEventManager", " startTrackEvent ApiTraceEvent Exception", th);
            return null;
        }
    }

    public void a(com.netease.nimlib.n.e.c cVar, boolean z) {
        if (cVar == null) {
            return;
        }
        try {
            cVar.b(SystemClock.uptimeMillis());
            long g = cVar.g();
            if (g < this.b) {
                com.netease.nimlib.log.b.c("DatabaseTraceEventManager", String.format(Locale.ENGLISH, "stopTrackEvent DatabaseTraceEvent skip as duration %s < apiTraceThreshold %s", Long.valueOf(g), Integer.valueOf(this.b)));
                return;
            }
            cVar.b(200);
            cVar.a(com.netease.nimlib.n.b.h.kSucceed);
            long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis() - cVar.y();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("durationThreadTime", currentThreadTimeMillis);
            jSONObject.put("isEncryptedDatabase", z);
            jSONObject.put("stackTrace", cVar.z());
            cVar.f(jSONObject.toString());
            com.netease.nimlib.log.b.G(String.format(Locale.ENGLISH, "stopTrackEvent DatabaseTraceEvent,model = %s, durationThreadTime = %s", cVar.m(), Long.valueOf(currentThreadTimeMillis)));
            com.netease.nimlib.apm.a.a(cVar.o(), (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) cVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("DatabaseTraceEventManager", "stopTrackEvent DatabaseTraceEvent Exception", th);
        }
    }

    private static String[] b() {
        Throwable th = new Throwable("getCallerApi");
        StackTraceElement[] stackTrace = th.getStackTrace();
        try {
            StackTraceElement stackTraceElement = stackTrace[2];
            com.netease.nimlib.log.b.d("DatabaseTraceEventManager", "trace database getCallerApi from stackTrace[2]:" + stackTraceElement);
            JSONArray jSONArray = new JSONArray();
            if (stackTrace.length > 3) {
                jSONArray.put(stackTrace[3].toString());
            }
            if (stackTrace.length > 4) {
                jSONArray.put(stackTrace[4].toString());
            }
            if (stackTrace.length > 5) {
                jSONArray.put(stackTrace[5].toString());
            }
            return new String[]{w.g(stackTraceElement.getClassName()), stackTraceElement.getMethodName(), jSONArray.toString()};
        } catch (Throwable th2) {
            com.netease.nimlib.log.b.e("DatabaseTraceEventManager", "trace database getCallerApi catch", th2);
            com.netease.nimlib.log.b.e("DatabaseTraceEventManager", "trace database getCallerApi error", th);
            return new String[]{"", "", ""};
        }
    }
}
