package com.netease.nimlib.n.b;

/* compiled from: EMBusinessOperationType.java */
/* loaded from: classes.dex */
public enum b {
    kSendPacket(0),
    kSendAwaitablePacket(1),
    kReceivePacket(2);

    private int d;

    b(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }
}
