package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum MsgDirectionEnum {
    Out(0),
    In(1);

    private int value;

    MsgDirectionEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static MsgDirectionEnum directionOfValue(int i) {
        for (MsgDirectionEnum msgDirectionEnum : values()) {
            if (msgDirectionEnum.getValue() == i) {
                return msgDirectionEnum;
            }
        }
        return Out;
    }
}
