package com.netease.nimlib.n.b;

/* compiled from: EMExceptionActions.java */
/* loaded from: classes.dex */
public enum f {
    kTCP(0),
    kHTTP(1),
    kBusiness(2),
    kLibrary(3),
    kDatabase(4),
    kFile(5),
    kRuntime(6),
    kLinkKeep(7);

    private int i;

    f(int i) {
        this.i = i;
    }

    public int a() {
        return this.i;
    }
}
