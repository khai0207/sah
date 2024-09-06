package com.netease.nimlib.net.b.c;

/* compiled from: BaseInboundHandler.java */
/* loaded from: classes.dex */
public class a implements d {
    protected com.netease.nimlib.net.b.a.d a;

    @Override // com.netease.nimlib.net.b.c.d
    public void k() {
        d e = this.a.e();
        if (e != null) {
            try {
                e.k();
            } catch (Throwable th) {
                e.a(th);
            }
        }
    }

    @Override // com.netease.nimlib.net.b.c.d
    public void a(Object obj) {
        d e = this.a.e();
        if (e != null) {
            try {
                e.a(obj);
            } catch (Throwable th) {
                e.a(th);
            }
        }
    }

    @Override // com.netease.nimlib.net.b.c.d
    public void l() {
        d e = this.a.e();
        if (e != null) {
            try {
                e.l();
            } catch (Throwable th) {
                e.a(th);
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
