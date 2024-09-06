package com.netease.nim.highavailable.enums;

/* loaded from: classes.dex */
public enum HAvailableDownloadAuthType {
    NULL(-1),
    REFER(1),
    TIME_TOKEN(2),
    URL_TOKEN(3),
    CUSTOM_TOKEN(4);

    private int value;

    HAvailableDownloadAuthType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static HAvailableDownloadAuthType typeOfValue(int i) {
        for (HAvailableDownloadAuthType hAvailableDownloadAuthType : values()) {
            if (hAvailableDownloadAuthType.getValue() == i) {
                return hAvailableDownloadAuthType;
            }
        }
        return null;
    }
}
