package com.netease.nimlib.i;

import android.content.Context;
import android.os.Handler;
import android.util.Pair;
import android.util.SparseArray;
import com.netease.nimlib.o.z;
import java.util.HashSet;
import java.util.Set;

/* compiled from: InvocationManager.java */
/* loaded from: classes.dex */
public class a {
    private static a f;
    private final e b;
    private final l c;
    private final Handler d;
    private final SparseArray<m> a = new SparseArray<>();
    private final Set<Pair<String, String>> e = new HashSet();

    private a(Context context) {
        Handler b = com.netease.nimlib.c.b.a.b(context);
        this.d = b;
        this.b = new e(b);
        this.c = new l();
        b();
    }

    public static void a(Context context) {
        f = new a(context);
    }

    static Object a(k kVar) {
        a();
        return f.d(kVar);
    }

    static boolean b(k kVar) {
        a();
        return f.f(kVar);
    }

    static void c(k kVar) {
        a();
        f.g(kVar);
    }

    public static void a(String str, Object obj) {
        a();
        k kVar = new k();
        kVar.a(str).a(new Object[]{obj});
        f.h(kVar);
    }

    private static void a() {
        if (f == null) {
            throw new IllegalStateException("SDK not inited!");
        }
    }

    private Object d(k kVar) {
        m mVar;
        if (this.b.a(kVar)) {
            return null;
        }
        com.netease.nimlib.n.a.a().a(kVar);
        if (!com.netease.nimlib.c.b() && !e(kVar)) {
            com.netease.nimlib.c.d();
        }
        if (kVar.k()) {
            Object a = this.c.a(kVar);
            com.netease.nimlib.n.a.a().c(kVar);
            return a;
        }
        synchronized (this.a) {
            mVar = new m(kVar);
            this.a.put(kVar.h(), mVar);
            this.c.c(kVar);
        }
        return mVar;
    }

    private void b() {
        this.e.add(new Pair<>("MsgService", "registerCustomAttachmentParser"));
        this.e.add(new Pair<>("MsgService", "registerIMMessageFilter"));
    }

    private boolean e(k kVar) {
        return this.e.contains(new Pair(kVar.e(), kVar.f()));
    }

    private boolean f(k kVar) {
        synchronized (this.a) {
            if (this.a.get(kVar.h()) == null) {
                return false;
            }
            this.a.remove(kVar.h());
            try {
                this.c.d(kVar);
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
    }

    private void g(k kVar) {
        m mVar;
        com.netease.nimlib.log.b.d("InvocationMgr", "execution result: " + kVar);
        synchronized (this.a) {
            mVar = this.a.get(kVar.h());
            this.a.remove(kVar.h());
        }
        if (mVar != null) {
            mVar.a(kVar.i(), kVar.j());
            this.c.e(kVar);
            Handler n = kVar.n();
            (n == null ? this.d : n).post(z.a(new Runnable() { // from class: com.netease.nimlib.i.a.1
                final /* synthetic */ m a;

                AnonymousClass1(m mVar2) {
                    r2 = mVar2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    r2.a();
                }
            }, 2147483647L, new z.a() { // from class: com.netease.nimlib.i.a.2
                final /* synthetic */ boolean a;
                final /* synthetic */ k b;

                AnonymousClass2(boolean z, k kVar2) {
                    r2 = z;
                    r3 = kVar2;
                }

                @Override // com.netease.nimlib.o.z.a
                public void a(long j) {
                    com.netease.nimlib.log.b.d("InvocationMgr", "execution result(elapse=" + j + " calling=" + r2 + "): " + r3);
                }
            }));
        }
    }

    /* compiled from: InvocationManager.java */
    /* renamed from: com.netease.nimlib.i.a$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ m a;

        AnonymousClass1(m mVar2) {
            r2 = mVar2;
        }

        @Override // java.lang.Runnable
        public void run() {
            r2.a();
        }
    }

    /* compiled from: InvocationManager.java */
    /* renamed from: com.netease.nimlib.i.a$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements z.a {
        final /* synthetic */ boolean a;
        final /* synthetic */ k b;

        AnonymousClass2(boolean z, k kVar2) {
            r2 = z;
            r3 = kVar2;
        }

        @Override // com.netease.nimlib.o.z.a
        public void a(long j) {
            com.netease.nimlib.log.b.d("InvocationMgr", "execution result(elapse=" + j + " calling=" + r2 + "): " + r3);
        }
    }

    private void h(k kVar) {
        com.netease.nimlib.log.b.d("InvocationMgr", "on notify: " + kVar);
        this.d.post(z.a(new Runnable() { // from class: com.netease.nimlib.i.a.3
            final /* synthetic */ k a;

            AnonymousClass3(k kVar2) {
                r2 = kVar2;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.b(r2);
            }
        }, 2147483647L, new z.a() { // from class: com.netease.nimlib.i.a.4
            final /* synthetic */ k a;

            AnonymousClass4(k kVar2) {
                r2 = kVar2;
            }

            @Override // com.netease.nimlib.o.z.a
            public void a(long j) {
                com.netease.nimlib.log.b.d("InvocationMgr", "on notify(elapse=" + j + "): " + r2);
            }
        }));
    }

    /* compiled from: InvocationManager.java */
    /* renamed from: com.netease.nimlib.i.a$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ k a;

        AnonymousClass3(k kVar2) {
            r2 = kVar2;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.b.b(r2);
        }
    }

    /* compiled from: InvocationManager.java */
    /* renamed from: com.netease.nimlib.i.a$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 implements z.a {
        final /* synthetic */ k a;

        AnonymousClass4(k kVar2) {
            r2 = kVar2;
        }

        @Override // com.netease.nimlib.o.z.a
        public void a(long j) {
            com.netease.nimlib.log.b.d("InvocationMgr", "on notify(elapse=" + j + "): " + r2);
        }
    }
}
