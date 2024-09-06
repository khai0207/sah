package com.netease.nimlib.net.b.a;

import android.util.SparseArray;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;

/* compiled from: NioSocketChannel.java */
/* loaded from: classes.dex */
public class k implements a {
    private volatile SelectionKey a;
    private g b;
    private com.netease.nimlib.net.b.d.a c = new com.netease.nimlib.net.b.d.a(this);
    private c d = new c(this);

    public k(com.netease.nimlib.net.b.c.f fVar) {
        this.b = new g(this, fVar);
    }

    @Override // com.netease.nimlib.net.b.a.a
    public g a() {
        return this.b;
    }

    @Override // com.netease.nimlib.net.b.a.a
    public com.netease.nimlib.net.b.d.a b() {
        return this.c;
    }

    @Override // com.netease.nimlib.net.b.a.a
    public com.netease.nimlib.net.b.c.f c() {
        return this.b.d();
    }

    @Override // com.netease.nimlib.net.b.a.a
    public boolean d() {
        return this.c.a();
    }

    public void g() {
        boolean z = false;
        int i = 0;
        while (true) {
            try {
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                int a = this.c.a(allocate);
                if (a <= 0) {
                    com.netease.nimlib.log.b.d("NioSocketChannel", "socket read amount: " + a);
                    if (a < 0) {
                        z = true;
                    }
                } else {
                    com.netease.nimlib.push.net.a.c.b().a(a, allocate);
                    this.b.a(allocate);
                    if (a < 1024 || (i = i + 1) >= 16) {
                        break;
                    }
                }
            } catch (Throwable th) {
                a(th, z);
                return;
            }
        }
        this.b.e();
        if (z && d()) {
            e();
        }
    }

    @Override // com.netease.nimlib.net.b.a.a
    public void a(SparseArray<Object> sparseArray) throws Exception {
        b().a(sparseArray);
        h();
    }

    @Override // com.netease.nimlib.net.b.a.a
    public void e() {
        this.b.c();
    }

    @Override // com.netease.nimlib.net.b.a.a
    public c a(Object obj) {
        return this.b.a(obj);
    }

    private void h() throws IOException {
        boolean z = false;
        while (true) {
            try {
                this.a = b().a(c().a(), this);
                return;
            } catch (CancelledKeyException e) {
                if (z) {
                    throw e;
                }
                c().d();
                z = true;
            }
        }
    }

    private void a(Throwable th, boolean z) {
        this.b.e();
        this.b.a(th);
        if (d()) {
            if (z || (th instanceof IOException)) {
                e();
            }
        }
    }

    @Override // com.netease.nimlib.net.b.a.a
    public SelectionKey f() {
        return this.a;
    }

    public void a(SelectionKey selectionKey) {
        this.a = selectionKey;
    }

    /* compiled from: NioSocketChannel.java */
    /* renamed from: com.netease.nimlib.net.b.a.k$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k.this.e();
        }
    }

    @Override // com.netease.nimlib.net.b.a.a
    public void a(c cVar) {
        com.netease.nimlib.net.b.c.f c;
        AnonymousClass2 anonymousClass2;
        if (b().e()) {
            c().execute(new Runnable() { // from class: com.netease.nimlib.net.b.a.k.1
                AnonymousClass1() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    k.this.e();
                }
            });
            return;
        }
        if (this.d.c()) {
            return;
        }
        boolean b = this.c.b();
        try {
            b().c();
            this.d.a((Object) null);
        } catch (Throwable th) {
            try {
                this.d.a((Throwable) new b("Throwable in NioSocketChannel doClose", th));
                if (b && !b().b()) {
                    c = c();
                    anonymousClass2 = new Runnable() { // from class: com.netease.nimlib.net.b.a.k.2
                        AnonymousClass2() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            k.this.b.f();
                        }
                    };
                }
            } catch (Throwable th2) {
                if (b && !b().b()) {
                    com.netease.nimlib.net.b.e.a.a(c(), new Runnable() { // from class: com.netease.nimlib.net.b.a.k.2
                        AnonymousClass2() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            k.this.b.f();
                        }
                    });
                }
                this.a.cancel();
                throw th2;
            }
        }
        if (b && !b().b()) {
            c = c();
            anonymousClass2 = new Runnable() { // from class: com.netease.nimlib.net.b.a.k.2
                AnonymousClass2() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    k.this.b.f();
                }
            };
            com.netease.nimlib.net.b.e.a.a(c, anonymousClass2);
        }
        this.a.cancel();
    }

    /* compiled from: NioSocketChannel.java */
    /* renamed from: com.netease.nimlib.net.b.a.k$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k.this.b.f();
        }
    }
}
