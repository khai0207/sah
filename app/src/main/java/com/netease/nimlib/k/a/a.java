package com.netease.nimlib.k.a;

/* compiled from: NimDetectType.java */
/* loaded from: classes.dex */
public enum a {
    ping_pong(1),
    telnet(2),
    mtr(3),
    ping(4),
    http_test(5);

    private final int f;

    a(int i) {
        this.f = i;
    }

    public int a() {
        return this.f;
    }

    public static a a(int i) {
        for (a aVar : values()) {
            if (aVar.a() == i) {
                return aVar;
            }
        }
        return null;
    }
}
