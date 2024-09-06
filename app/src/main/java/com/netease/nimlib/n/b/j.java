package com.netease.nimlib.n.b;

/* compiled from: EMHTTPOperationType.java */
/* loaded from: classes.dex */
public enum j {
    kGet(0),
    kPost(1),
    kPut(2),
    kDelete(3);

    private int e;

    j(int i) {
        this.e = i;
    }

    public int a() {
        return this.e;
    }
}
