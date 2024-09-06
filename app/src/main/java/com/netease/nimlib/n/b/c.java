package com.netease.nimlib.n.b;

/* compiled from: EMDatabaseOperationType.java */
/* loaded from: classes.dex */
public enum c {
    kOpen(0),
    kClose(1),
    kWalCheckPoint(2),
    kExecuteSQL(3),
    kTransaction(4);

    private int f;

    c(int i) {
        this.f = i;
    }

    public int a() {
        return this.f;
    }
}
