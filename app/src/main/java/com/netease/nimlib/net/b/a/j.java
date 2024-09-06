package com.netease.nimlib.net.b.a;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

/* compiled from: HeadContext.java */
/* loaded from: classes.dex */
final class j extends d implements com.netease.nimlib.net.b.c.g {
    @Override // com.netease.nimlib.net.b.c.c
    public void a(d dVar) {
    }

    @Override // com.netease.nimlib.net.b.a.d
    public com.netease.nimlib.net.b.c.c j() {
        return this;
    }

    j(g gVar) {
        super(gVar, "HeadContext", false, true);
    }

    @Override // com.netease.nimlib.net.b.c.g
    public boolean a(SocketAddress socketAddress) throws Exception {
        return k().a(socketAddress);
    }

    @Override // com.netease.nimlib.net.b.c.g
    public void a(c cVar) {
        this.a.a().a(cVar);
    }

    @Override // com.netease.nimlib.net.b.c.g
    public void a(Object obj, c cVar) {
        if (obj instanceof ByteBuffer) {
            k().a((ByteBuffer) obj, cVar);
        } else {
            cVar.a((Throwable) new UnsupportedOperationException("Only ByteBuffer is supported"));
        }
    }

    @Override // com.netease.nimlib.net.b.c.c
    public void a(Throwable th) {
        if (b() == null || b().j() == null) {
            return;
        }
        b().j().a(th);
    }

    private com.netease.nimlib.net.b.d.a k() {
        return this.a.a().b();
    }
}
