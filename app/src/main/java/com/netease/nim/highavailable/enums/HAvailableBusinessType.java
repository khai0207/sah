package com.netease.nim.highavailable.enums;

/* loaded from: classes.dex */
public enum HAvailableBusinessType {
    BIZ_LBS(0),
    BIZ_FCS(1);

    private int value;

    HAvailableBusinessType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static HAvailableBusinessType typeOfValue(int i) {
        for (HAvailableBusinessType hAvailableBusinessType : values()) {
            if (hAvailableBusinessType.getValue() == i) {
                return hAvailableBusinessType;
            }
        }
        return null;
    }
}
