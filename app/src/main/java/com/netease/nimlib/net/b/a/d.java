package com.netease.nimlib.net.b.a;

/* compiled from: ChannelHandlerContext.java */
/* loaded from: classes.dex */
public abstract class d {
    protected g a;
    volatile d b;
    volatile d c;
    private boolean d;
    private boolean e;

    public abstract com.netease.nimlib.net.b.c.c j();

    public d(g gVar, String str, boolean z, boolean z2) {
        this.a = gVar;
        this.d = z;
        this.e = z2;
    }

    public d a() {
        return this.b;
    }

    public d b() {
        return this.c;
    }

    public d c() {
        for (d b = b(); b != null; b = b.b()) {
            if (b.g()) {
                return b;
            }
        }
        return null;
    }

    public d d() {
        for (d a = a(); a != null; a = a.a()) {
            if (a.h()) {
                return a;
            }
        }
        return null;
    }

    public com.netease.nimlib.net.b.c.d e() {
        d c = c();
        if (c == null) {
            return null;
        }
        return (com.netease.nimlib.net.b.c.d) c.j();
    }

    public com.netease.nimlib.net.b.c.g f() {
        d d = d();
        if (d == null) {
            return null;
        }
        return (com.netease.nimlib.net.b.c.g) d.j();
    }

    public boolean g() {
        return this.d;
    }

    public boolean h() {
        return this.e;
    }

    public a i() {
        return this.a.a();
    }
}
