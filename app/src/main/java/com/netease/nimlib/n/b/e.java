package com.netease.nimlib.n.b;

/* compiled from: EMDisconnectReason.java */
/* loaded from: classes.dex */
public enum e {
    CLOSE(1, "close"),
    BROKEN(2, "broken"),
    KICKED(3, "kicked");

    private int d;
    private String e;

    e(int i, String str) {
        this.d = i;
        this.e = str;
    }

    public String a() {
        return this.e;
    }
}
