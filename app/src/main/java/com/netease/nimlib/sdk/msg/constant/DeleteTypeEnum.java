package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum DeleteTypeEnum {
    REMAIN(0),
    LOCAL(1),
    REMOTE(2),
    LOCAL_AND_REMOTE(3);

    private int value;

    DeleteTypeEnum(int i) {
        this.value = i;
    }

    public static DeleteTypeEnum statusOfValue(int i) {
        for (DeleteTypeEnum deleteTypeEnum : values()) {
            if (deleteTypeEnum.getValue() == i) {
                return deleteTypeEnum;
            }
        }
        return REMAIN;
    }

    public int getValue() {
        return this.value;
    }

    public static boolean doNotDelete(DeleteTypeEnum deleteTypeEnum) {
        return deleteTypeEnum == null || deleteTypeEnum == REMAIN;
    }

    public static boolean deleteLocal(DeleteTypeEnum deleteTypeEnum) {
        return deleteTypeEnum == LOCAL || deleteTypeEnum == LOCAL_AND_REMOTE;
    }

    public static boolean deleteRemote(DeleteTypeEnum deleteTypeEnum) {
        return deleteTypeEnum == REMOTE || deleteTypeEnum == LOCAL_AND_REMOTE;
    }
}
