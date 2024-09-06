package com.netease.nim.highavailable.enums;

/* loaded from: classes.dex */
public enum HAvailableFCSChannelFunID {
    kInvalidFunID(0),
    kFILE_QUICK_TRANSFER(18),
    kGET_SAFE_URL(22),
    kGET_SERVER_TIME(23),
    kFCS_POLICY(28),
    kFCS_AUTHORIZATION(29),
    kFCS_BACK_SOURCE_TOKEN(30);

    private int value;

    HAvailableFCSChannelFunID(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static HAvailableFCSChannelFunID typeOfValue(int i) {
        for (HAvailableFCSChannelFunID hAvailableFCSChannelFunID : values()) {
            if (hAvailableFCSChannelFunID.getValue() == i) {
                return hAvailableFCSChannelFunID;
            }
        }
        return kInvalidFunID;
    }
}
