package com.netease.nimlib.net.trace;

import android.os.Handler;
import android.os.Looper;
import com.netease.nimlib.o.x;

/* loaded from: classes.dex */
public class TraceRoute {
    private StringBuilder a = null;
    private a b = null;
    private Handler c = new Handler(Looper.getMainLooper());

    /* loaded from: classes.dex */
    public interface a {
        void a(int i, String str);

        void a(b bVar);

        void a(String str);
    }

    native int execute(Object[] objArr);

    static {
        x.a("traceroute");
    }

    public void clearResult() {
        this.a = null;
    }

    public void appendResult(final String str) {
        if (this.a == null) {
            this.a = new StringBuilder();
        }
        this.a.append(str);
        if (this.b != null) {
            this.c.post(new Runnable() { // from class: com.netease.nimlib.net.trace.-$$Lambda$TraceRoute$MqbS8gpK-9qKR66URj3349PzhLY
                @Override // java.lang.Runnable
                public final void run() {
                    TraceRoute.this.a(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(str);
        }
    }

    public synchronized b a(String str, boolean z) {
        final String[] strArr = {"traceroute", str};
        if (z) {
            new Thread(new Runnable() { // from class: com.netease.nimlib.net.trace.-$$Lambda$TraceRoute$UBb_xfQPBoBDE6LaMm_VKK7CN4M
                @Override // java.lang.Runnable
                public final void run() {
                    TraceRoute.this.b(strArr);
                }
            }, "trace_route_thread").start();
            return null;
        }
        return b(strArr);
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public synchronized b b(String... strArr) {
        final b bVar;
        bVar = new b();
        bVar.a = execute(strArr);
        if (bVar.a == 0) {
            bVar.b = this.a.toString();
            this.c.post(new Runnable() { // from class: com.netease.nimlib.net.trace.-$$Lambda$TraceRoute$yXScKiiQZOkGSAeGjrmpwWY8vQg
                @Override // java.lang.Runnable
                public final void run() {
                    TraceRoute.this.b(bVar);
                }
            });
        } else {
            bVar.b = "execute traceroute failed.";
            this.c.post(new Runnable() { // from class: com.netease.nimlib.net.trace.-$$Lambda$TraceRoute$vVzS0BlePoiOyrQ64678sqplhtM
                @Override // java.lang.Runnable
                public final void run() {
                    TraceRoute.this.a(bVar);
                }
            });
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(b bVar) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(b bVar) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(bVar.a, bVar.b);
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        private int a;
        private String b;

        public b() {
            this(-1, "");
        }

        public b(int i, String str) {
            this.a = i;
            this.b = str;
        }

        public int a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }
    }
}
