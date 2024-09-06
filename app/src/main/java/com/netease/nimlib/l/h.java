package com.netease.nimlib.l;

/* compiled from: NotificationTag.java */
/* loaded from: classes.dex */
public enum h {
    MESSAGE("MESSAGE", 1),
    ADD_BUDDY("ADD_BUDDY", 2);

    public static final h[] c;
    public static final h[] d;
    private String e;
    private int f;

    static {
        h hVar = ADD_BUDDY;
        h hVar2 = MESSAGE;
        c = new h[]{hVar2, hVar};
        d = new h[]{hVar2, hVar};
    }

    h(String str, int i) {
        this.e = str;
        this.f = i;
    }

    public String a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public int c() {
        return ordinal();
    }
}
