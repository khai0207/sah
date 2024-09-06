package com.netease.nimlib.n.b;

/* compiled from: EMLibraryOperationType.java */
/* loaded from: classes.dex */
public enum k {
    kLoad(0),
    kGetAddress(1),
    kFree(2);

    private int d;

    k(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }
}
