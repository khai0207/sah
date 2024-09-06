package com.netease.nimlib.sdk;

/* loaded from: classes.dex */
public enum NotificationFoldStyle {
    ALL(0, "all"),
    EXPAND(1, "expand"),
    CONTACT(2, "contact");

    private String msg;
    private int value;

    NotificationFoldStyle(int i, String str) {
        this.value = i;
        this.msg = str;
    }

    public int getValue() {
        return this.value;
    }

    public String getMsg() {
        return this.msg;
    }

    public static NotificationFoldStyle value(int i) {
        for (NotificationFoldStyle notificationFoldStyle : values()) {
            if (notificationFoldStyle.value == i) {
                return notificationFoldStyle;
            }
        }
        return ALL;
    }
}
