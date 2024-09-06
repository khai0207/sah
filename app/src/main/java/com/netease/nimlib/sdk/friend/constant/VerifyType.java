package com.netease.nimlib.sdk.friend.constant;

/* loaded from: classes.dex */
public enum VerifyType {
    DIRECT_ADD((byte) 1),
    VERIFY_REQUEST((byte) 2);

    private byte value;

    VerifyType(byte b) {
        this.value = b;
    }

    public static VerifyType verifyTypeOfValue(byte b) {
        for (VerifyType verifyType : values()) {
            if (verifyType.getValue() == b) {
                return verifyType;
            }
        }
        return null;
    }

    public byte getValue() {
        return this.value;
    }
}
