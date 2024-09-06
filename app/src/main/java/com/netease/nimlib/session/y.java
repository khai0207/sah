package com.netease.nimlib.session;

/* compiled from: SessionReliableTypeEnum.java */
/* loaded from: classes.dex */
public enum y {
    LOCAL_ONLY(0),
    DYNAMIC(1),
    REMOTE_ONLY(2);

    private final int d;

    y(int i) {
        this.d = i;
    }

    public static y a(int i) {
        for (y yVar : values()) {
            if (yVar.b() == i) {
                return yVar;
            }
        }
        return a();
    }

    public static y a() {
        return LOCAL_ONLY;
    }

    public int b() {
        return this.d;
    }
}
