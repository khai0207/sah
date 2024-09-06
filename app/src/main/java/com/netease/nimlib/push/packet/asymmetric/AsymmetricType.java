package com.netease.nimlib.push.packet.asymmetric;

/* loaded from: classes.dex */
public enum AsymmetricType {
    RSA(1),
    SM2(2),
    RSA_OAEP_1(4),
    RSA_OAEP_256(8);

    private int value;

    AsymmetricType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static AsymmetricType value(int i) {
        for (AsymmetricType asymmetricType : values()) {
            if (asymmetricType.value == i) {
                return asymmetricType;
            }
        }
        return RSA;
    }
}
