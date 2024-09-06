package com.netease.nimlib.net.a.d;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.netease.nimlib.c.b.b;
import com.netease.nimlib.net.a.d.a;
import java.util.Map;

/* compiled from: NimHttpClient.java */
/* loaded from: classes.dex */
public class b {
    private static b a;
    private boolean b = false;
    private com.netease.nimlib.c.b.b c;
    private Handler d;

    /* compiled from: NimHttpClient.java */
    /* loaded from: classes.dex */
    public interface a {
        void onResponse(String str, int i, Throwable th);
    }

    /* compiled from: NimHttpClient.java */
    /* renamed from: com.netease.nimlib.net.a.d.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0048b implements Runnable {
        private String b;
        private Map<String, String> c;
        private String d;
        private a e;
        private boolean f;
        private boolean g;
        private Object h;

        public RunnableC0048b(String str, Map<String, String> map, String str2, a aVar, boolean z, boolean z2, Object obj) {
            this.b = str;
            this.c = map;
            this.d = str2;
            this.e = aVar;
            this.f = z;
            this.g = z2;
            this.h = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            final a.C0047a<String> a = this.f ? com.netease.nimlib.net.a.d.a.a(this.b, this.c, this.d, this.h) : com.netease.nimlib.net.a.d.a.a(this.b, this.c, this.h);
            if (this.g) {
                b.this.d.post(new Runnable() { // from class: com.netease.nimlib.net.a.d.b.b.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        if (RunnableC0048b.this.e != null) {
                            RunnableC0048b.this.e.onResponse((String) a.c, a.a, a.b);
                        }
                    }
                });
                return;
            }
            a aVar = this.e;
            if (aVar != null) {
                aVar.onResponse(a.c, a.a, a.b);
            }
        }
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
            bVar = a;
        }
        return bVar;
    }

    private b() {
    }

    public void a(Context context) {
        if (this.b) {
            return;
        }
        this.c = new com.netease.nimlib.c.b.b("NIM_SDK_HTTP", new b.a(1, 2, 30000, true));
        this.d = new Handler(Looper.getMainLooper());
        this.b = true;
    }

    public void a(String str, Map<String, String> map, a aVar) {
        a(str, map, true, null, aVar);
    }

    public void a(String str, Map<String, String> map, boolean z, Object obj, a aVar) {
        a(com.netease.nimlib.net.a.c.b.a(str, map), null, null, false, z, obj, aVar);
    }

    public void a(String str, Map<String, String> map, String str2, boolean z, Object obj, a aVar) {
        a(str, map, str2, true, z, obj, aVar);
    }

    public void a(String str, Map<String, String> map, String str2, boolean z, boolean z2, Object obj, a aVar) {
        if (this.b) {
            this.c.execute(new RunnableC0048b(str, map, str2, aVar, z, z2, obj));
        }
    }
}
