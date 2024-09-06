package com.netease.nim.highavailable.enums;

/* loaded from: classes.dex */
public enum HAvailableFCSErrorCode {
    kOK(0),
    kError(1),
    kCancel(2),
    kErrorMoveFile(3);

    private int value;

    HAvailableFCSErrorCode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static HAvailableFCSErrorCode typeOfValue(int i) {
        for (HAvailableFCSErrorCode hAvailableFCSErrorCode : values()) {
            if (hAvailableFCSErrorCode.getValue() == i) {
                return hAvailableFCSErrorCode;
            }
        }
        return null;
    }
}
