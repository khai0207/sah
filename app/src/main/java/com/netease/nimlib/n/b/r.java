package com.netease.nimlib.n.b;

import com.iflytek.cloud.ErrorCode;
import u.aly.df;

/* compiled from: EMSyncType.java */
/* loaded from: classes.dex */
public enum r {
    K_SYNC_TYPE_3_8(30008),
    K_SYNC_TYPE_3_109(30109),
    K_SYNC_TYPE_4_4(40004),
    K_SYNC_TYPE_4_6(40006),
    K_SYNC_TYPE_4_7(40007),
    K_SYNC_TYPE_4_8(40008),
    K_SYNC_TYPE_4_9(40009),
    K_SYNC_TYPE_4_12(40012),
    K_SYNC_TYPE_4_13(40013),
    K_SYNC_TYPE_4_14(40014),
    K_SYNC_TYPE_4_15(40015),
    K_SYNC_TYPE_4_16(40016),
    K_SYNC_TYPE_4_17(40017),
    K_SYNC_TYPE_4_18(40018),
    K_SYNC_TYPE_4_19(40019),
    K_SYNC_TYPE_4_20(40020),
    K_SYNC_TYPE_4_21(40021),
    K_SYNC_TYPE_4_22(40022),
    K_SYNC_TYPE_4_23(40023),
    K_SYNC_TYPE_4_24(40024),
    K_SYNC_TYPE_4_25(40025),
    K_SYNC_TYPE_4_100(40100),
    K_SYNC_TYPE_7_5(70005),
    K_SYNC_TYPE_7_6(70006),
    K_SYNC_TYPE_7_15(70016),
    K_SYNC_TYPE_8_109(80109),
    K_SYNC_TYPE_8_111(80111),
    K_SYNC_TYPE_8_126(80126),
    K_SYNC_TYPE_15_13(ErrorCode.MSP_ERROR_MMP_STORE_MNR_POOL_FULL),
    K_SYNC_TYPE_21_109(21109),
    K_SYNC_TYPE_21_111(21111);

    private int F;

    r(int i) {
        this.F = i;
    }

    public int a() {
        return this.F;
    }

    public static r a(byte b, byte b2) {
        int i = (b < 10 ? b * df.n : b * 1000) + b2;
        for (r rVar : values()) {
            if (rVar.a() == i) {
                return rVar;
            }
        }
        return null;
    }
}
