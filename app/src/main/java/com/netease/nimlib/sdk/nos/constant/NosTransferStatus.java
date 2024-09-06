package com.netease.nimlib.sdk.nos.constant;

/* loaded from: classes.dex */
public enum NosTransferStatus {
    def(0),
    transferring(1),
    transferred(2),
    fail(3);

    private int value;

    NosTransferStatus(int i) {
        this.value = i;
    }

    public static NosTransferStatus statusOfValue(int i) {
        for (NosTransferStatus nosTransferStatus : values()) {
            if (nosTransferStatus.getValue() == i) {
                return nosTransferStatus;
            }
        }
        return def;
    }

    public int getValue() {
        return this.value;
    }
}
