package com.netease.nimlib.apm.b;

/* compiled from: NetTypeForApmEnum.java */
/* loaded from: classes.dex */
public enum d {
    STAT_NET_TYPE_UNKNOWN(0),
    STAT_NET_TYPE_ETHERNET(1),
    STAT_NET_TYPE_WIFI(2),
    STAT_NET_TYPE_2_G(3),
    STAT_NET_TYPE_3_G(4),
    STAT_NET_TYPE_4_G(5),
    STAT_NET_TYPE_5_G(6);

    final int h;

    d(int i2) {
        this.h = i2;
    }

    public static d a(int i2) {
        for (d dVar : values()) {
            if (dVar.a() == i2) {
                return dVar;
            }
        }
        return STAT_NET_TYPE_UNKNOWN;
    }

    public int a() {
        return this.h;
    }

    public static d b(int i2) {
        if (i2 == 1) {
            return STAT_NET_TYPE_2_G;
        }
        if (i2 == 2) {
            return STAT_NET_TYPE_3_G;
        }
        if (i2 == 3) {
            return STAT_NET_TYPE_4_G;
        }
        if (i2 == 4) {
            return STAT_NET_TYPE_5_G;
        }
        if (i2 == 10) {
            return STAT_NET_TYPE_WIFI;
        }
        return STAT_NET_TYPE_UNKNOWN;
    }
}
