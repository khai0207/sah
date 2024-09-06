package com.netease.nimlib.n.b;

/* compiled from: EMDetectTaskType.java */
/* loaded from: classes.dex */
public enum d {
    UDP_PING(2),
    TELNET(4),
    TRACEROUTE(5);

    final int d;

    d(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }
}
