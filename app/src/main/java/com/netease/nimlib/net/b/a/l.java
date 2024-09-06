package com.netease.nimlib.net.b.a;

/* compiled from: TailContext.java */
/* loaded from: classes.dex */
final class l extends d implements com.netease.nimlib.net.b.c.d {
    @Override // com.netease.nimlib.net.b.c.c
    public void a(d dVar) {
    }

    @Override // com.netease.nimlib.net.b.a.d
    public com.netease.nimlib.net.b.c.c j() {
        return this;
    }

    @Override // com.netease.nimlib.net.b.c.d
    public void k() {
    }

    @Override // com.netease.nimlib.net.b.c.d
    public void l() {
    }

    l(g gVar) {
        super(gVar, "TailContext", true, false);
    }

    @Override // com.netease.nimlib.net.b.c.c
    public void a(Throwable th) {
        com.netease.nimlib.log.b.d("DefaultChannelPipeline", "An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception.", th);
    }

    @Override // com.netease.nimlib.net.b.c.d
    public void a(Object obj) {
        com.netease.nimlib.log.b.c("DefaultChannelPipeline", "Discarded inbound message " + obj + "  that reached at the tail of the pipeline. Please check your pipeline configuration.");
    }
}
