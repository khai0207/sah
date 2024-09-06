package com.netease.nimlib.net.b.c;

import java.net.SocketAddress;

/* compiled from: BaseOutboundHandler.java */
/* loaded from: classes.dex */
public class b implements g {
    protected com.netease.nimlib.net.b.a.d a;

    @Override // com.netease.nimlib.net.b.c.g
    public boolean a(SocketAddress socketAddress) throws Exception {
        g f = this.a.f();
        if (f == null) {
            return false;
        }
        try {
            return f.a(socketAddress);
        } catch (Throwable th) {
            f.a(th);
            return false;
        }
    }

    @Override // com.netease.nimlib.net.b.c.g
    public void a(com.netease.nimlib.net.b.a.c cVar) {
        g f = this.a.f();
        if (f != null) {
            try {
                f.a(cVar);
            } catch (Throwable th) {
                this.a.j().a(th);
            }
        }
    }

    @Override // com.netease.nimlib.net.b.c.g
    public void a(Object obj, com.netease.nimlib.net.b.a.c cVar) {
        g f = this.a.f();
        if (f != null) {
            try {
                f.a(obj, cVar);
            } catch (Throwable th) {
                this.a.j().a(th);
            }
        }
    }

    @Override // com.netease.nimlib.net.b.c.c
    public void a(Throwable th) {
        if (this.a.b() == null || this.a.b().j() == null) {
            return;
        }
        this.a.b().j().a(th);
    }

    @Override // com.netease.nimlib.net.b.c.c
    public void a(com.netease.nimlib.net.b.a.d dVar) {
        this.a = dVar;
    }
}
