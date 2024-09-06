package com.netease.nimlib.push.packet.symmetry;

/* loaded from: classes.dex */
public enum SymmetryType {
    RC4(1),
    AES(2),
    SM4(4);

    private int value;

    SymmetryType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static SymmetryType value(int i) {
        for (SymmetryType symmetryType : values()) {
            if (symmetryType.value == i) {
                return symmetryType;
            }
        }
        return RC4;
    }
}
