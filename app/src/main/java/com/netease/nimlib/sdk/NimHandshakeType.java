package com.netease.nimlib.sdk;

/* loaded from: classes.dex */
public enum NimHandshakeType {
    V0(0),
    V1(1);

    private int value;

    NimHandshakeType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static NimHandshakeType value(int i) {
        for (NimHandshakeType nimHandshakeType : values()) {
            if (nimHandshakeType.value == i) {
                return nimHandshakeType;
            }
        }
        return V1;
    }
}
