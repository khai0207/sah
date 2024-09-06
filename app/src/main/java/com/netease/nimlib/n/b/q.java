package com.netease.nimlib.n.b;

/* compiled from: EMSyncAction.java */
/* loaded from: classes.dex */
public enum q {
    K_SYNC_ACTION_5_1(0),
    K_SYNC_ACTION_5_2(1),
    K_SYNC_ACTION_5_3(2);

    private int d;

    q(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public static q a(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            for (q qVar : values()) {
                if (qVar.a() == parseInt) {
                    return qVar;
                }
            }
            return null;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.H("EMSyncAction.valueOfStringInt" + th);
            return null;
        }
    }
}
