package com.netease.nimlib.session;

/* compiled from: MsgIndexCheck.java */
/* loaded from: classes.dex */
public final class k {
    private boolean a = false;

    public final k a(IMMessageImpl iMMessageImpl) {
        if (com.netease.nimlib.search.b.g().a() && !this.a) {
            this.a = com.netease.nimlib.search.a.a.c(iMMessageImpl);
        }
        return this;
    }

    public final void a() {
        if (com.netease.nimlib.search.b.g().a()) {
            boolean z = this.a;
            this.a = false;
            if (z) {
                com.netease.nimlib.search.b.g().c();
            }
        }
    }
}
