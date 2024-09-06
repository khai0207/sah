package com.netease.nimlib.sdk;

/* loaded from: classes.dex */
public enum StatusCode {
    INVALID(0, ""),
    UNLOGIN(1, ""),
    NET_BROKEN(2, ""),
    CONNECTING(3, ""),
    LOGINING(4, ""),
    SYNCING(5, ""),
    LOGINED(6, ""),
    KICKOUT(7, ""),
    KICK_BY_OTHER_CLIENT(8, ""),
    FORBIDDEN(9, ""),
    VER_ERROR(10, ""),
    PWD_ERROR(11, ""),
    DATA_UPGRADE(12, ""),
    NEED_RECONNECT(13, ""),
    NEED_CHANGE_LBS(14, ""),
    LOGOUT(15, "");

    private String desc;
    private int value;

    public boolean wontAutoLogin() {
        return this == KICKOUT || this == KICK_BY_OTHER_CLIENT || this == FORBIDDEN || this == PWD_ERROR || this == DATA_UPGRADE;
    }

    public boolean wontAutoLoginForever() {
        return this == KICKOUT || this == KICK_BY_OTHER_CLIENT;
    }

    public boolean shouldReLogin() {
        return this == UNLOGIN || this == NET_BROKEN || this == NEED_RECONNECT || this == NEED_CHANGE_LBS;
    }

    StatusCode(int i, String str) {
        this.value = i;
        this.desc = str;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public static StatusCode typeOfValue(int i) {
        for (StatusCode statusCode : values()) {
            if (statusCode.getValue() == i) {
                return statusCode;
            }
        }
        return INVALID;
    }

    public static StatusCode statusOfResCode(int i) {
        if (i == 200) {
            return LOGINED;
        }
        if (i != 302) {
            if (i == 317) {
                return VER_ERROR;
            }
            if (i != 414) {
                if (i != 417) {
                    if (i != 422) {
                        if (i == 398) {
                            return NEED_RECONNECT;
                        }
                        if (i == 399) {
                            return NEED_CHANGE_LBS;
                        }
                        if (i != 403) {
                            if (i != 404) {
                                return UNLOGIN;
                            }
                        }
                    }
                    return FORBIDDEN;
                }
                return KICKOUT;
            }
        }
        return PWD_ERROR;
    }
}
