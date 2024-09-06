package com.netease.nimlib.session;

/* compiled from: SessionReliableSyncStatusEnum.java */
/* loaded from: classes.dex */
public enum x {
    NONE(0),
    INCREMENTAL_FULL(1),
    INCREMENTAL_NOT_ENOUGH(2),
    INCREMENTAL_EMPTY(3);

    private final int e;

    x(int i) {
        this.e = i;
    }

    public static x a(int i) {
        for (x xVar : values()) {
            if (xVar.a() == i) {
                return xVar;
            }
        }
        return NONE;
    }

    public int a() {
        return this.e;
    }
}
