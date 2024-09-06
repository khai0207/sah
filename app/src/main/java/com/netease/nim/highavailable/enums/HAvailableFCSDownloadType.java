package com.netease.nim.highavailable.enums;

/* loaded from: classes.dex */
public enum HAvailableFCSDownloadType {
    kSource(0),
    kThumbnail(1),
    kVideoCover(2);

    private int value;

    HAvailableFCSDownloadType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static HAvailableFCSDownloadType typeOfValue(int i) {
        for (HAvailableFCSDownloadType hAvailableFCSDownloadType : values()) {
            if (hAvailableFCSDownloadType.getValue() == i) {
                return hAvailableFCSDownloadType;
            }
        }
        return null;
    }
}
