package com.netease.nimlib.net.b.a;

/* compiled from: DefaultChannelHandlerContext.java */
/* loaded from: classes.dex */
public class h extends d {
    private com.netease.nimlib.net.b.c.c d;

    public h(g gVar, String str, com.netease.nimlib.net.b.c.c cVar) {
        super(gVar, str, cVar instanceof com.netease.nimlib.net.b.c.d, cVar instanceof com.netease.nimlib.net.b.c.g);
        this.d = cVar;
    }

    @Override // com.netease.nimlib.net.b.a.d
    public com.netease.nimlib.net.b.c.c j() {
        return this.d;
    }
}
