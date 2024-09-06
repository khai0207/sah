package com.netease.nimlib.net.b.c;

/* compiled from: InoutBoundHandler.java */
/* loaded from: classes.dex */
public class e extends b implements d {
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
}
