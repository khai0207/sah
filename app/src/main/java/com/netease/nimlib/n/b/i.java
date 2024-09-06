package com.netease.nimlib.n.b;

/* compiled from: EMFileOperationType.java */
/* loaded from: classes.dex */
public enum i {
    kCreateDirectory(0),
    kOpen(1),
    kClose(2),
    kCreate(3),
    kRead(4),
    kWrite(5),
    kMove(6),
    kCopy(7),
    kDelete(8);

    private int j;

    i(int i) {
        this.j = i;
    }

    public int a() {
        return this.j;
    }
}
