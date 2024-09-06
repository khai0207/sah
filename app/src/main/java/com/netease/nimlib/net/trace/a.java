package com.netease.nimlib.net.trace;

import android.text.TextUtils;
import com.iflytek.speech.TextUnderstanderAidl;
import com.netease.nimlib.d.g;
import com.netease.nimlib.net.trace.TraceRoute;
import com.netease.nimlib.o.y;
import com.netease.nimlib.push.net.lbs.c;
import com.unionpay.sdk.OttoBus;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NetworkTraceManager.java */
/* loaded from: classes.dex */
public class a {
    private final ExecutorService a;
    private final TraceRoute b;
    private com.netease.nimlib.net.trace.a.a<b> c;
    private Map<String, b> d;

    private a() {
        this.b = new TraceRoute();
        this.c = com.netease.nimlib.net.trace.a.a.a(3);
        this.d = new HashMap();
        this.a = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.netease.nimlib.net.trace.-$$Lambda$a$uhZvfq9nJ4ySSurHUMLAwOccv4k
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread a;
                a = a.a(runnable);
                return a;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread a(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("NetworkTraceManager");
        return thread;
    }

    public synchronized void a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "timeout");
            jSONObject.put(TextUnderstanderAidl.SCENE, "IM");
            a(new b(c.a().c().getIp(), jSONObject));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "timeout");
            jSONObject.put(TextUnderstanderAidl.SCENE, "ChatRoom");
            jSONObject.put("roomId", str2);
            a(new b(str, jSONObject));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "disconnect");
            jSONObject.put(TextUnderstanderAidl.SCENE, "IM");
            a(new b(c.a().c().getIp(), jSONObject));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void b(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "disconnect");
            jSONObject.put(TextUnderstanderAidl.SCENE, "ChatRoom");
            jSONObject.put("roomId", str2);
            a(new b(str, jSONObject));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private synchronized void a(b bVar) {
        b d;
        Iterator<b> e = this.c.e();
        while (e.hasNext()) {
            b next = e.next();
            if (!next.a(bVar)) {
                com.netease.nimlib.log.b.t(String.format("disallow submit traceTask %s %s", next, bVar));
                return;
            }
        }
        for (b bVar2 : this.d.values()) {
            if (!bVar2.a(bVar)) {
                com.netease.nimlib.log.b.t(String.format("disallow submit finishedTask %s %s", bVar2, bVar));
                return;
            }
        }
        if (this.c.a() == 0 && (d = this.c.d()) != null) {
            d.a();
            com.netease.nimlib.log.b.t(String.format("cancel task %s", bVar));
        }
        this.c.a((com.netease.nimlib.net.trace.a.a<b>) bVar);
        this.a.submit(bVar);
    }

    public synchronized void a(b bVar, boolean z) {
        this.c.b(bVar);
        if (z) {
            this.d.put(bVar.c, bVar);
        }
    }

    /* compiled from: NetworkTraceManager.java */
    /* loaded from: classes.dex */
    private class b implements Runnable {
        private final long b = y.a();
        private final String c;
        private final String d;
        private final JSONObject e;
        private boolean f;

        public b(String str, JSONObject jSONObject) {
            try {
                jSONObject.put("server", str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                Object opt = jSONObject.opt(keys.next());
                sb.append("#");
                sb.append(opt);
            }
            this.c = sb.toString();
            this.d = str;
            this.e = jSONObject;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            this.f = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.f) {
                    return;
                }
                this.e.put("current", a(this.d));
                this.e.put(OttoBus.DEFAULT_IDENTIFIER, a(new com.netease.nimlib.push.net.lbs.b(g.e()).b));
                com.netease.nimlib.log.b.t(this.e.toString());
                a.this.a(this, true);
            } catch (Exception e) {
                e.printStackTrace();
                a.this.a(this, false);
            }
        }

        private JSONObject a(String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.netease.nimlib.log.b.t("TraceRouteResult server:" + str);
                if (!TextUtils.isEmpty(str)) {
                    TraceRoute.b a = a.this.b.a(str, false);
                    com.netease.nimlib.log.b.t("TraceRouteResult code:" + a.a());
                    jSONObject.put("code", a.a());
                    jSONObject.put("message", a.b());
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    jSONObject.put("exception", e.toString());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    com.netease.nimlib.log.b.t("TraceRouteResult JSONException:" + e2.toString());
                }
            }
            return jSONObject;
        }

        public boolean a(b bVar) {
            if (bVar == null) {
                return false;
            }
            return !TextUtils.equals(this.c, bVar.c) || bVar.b - this.b > 30000;
        }

        public String toString() {
            return "TraceTask{time=" + this.b + ", key='" + this.c + "'}";
        }
    }

    public static a c() {
        return C0050a.a;
    }

    /* compiled from: NetworkTraceManager.java */
    /* renamed from: com.netease.nimlib.net.trace.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0050a {
        private static final a a = new a();
    }
}
