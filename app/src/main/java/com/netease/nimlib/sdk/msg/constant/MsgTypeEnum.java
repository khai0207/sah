package com.netease.nimlib.sdk.msg.constant;

/* loaded from: classes.dex */
public enum MsgTypeEnum {
    undef(-1, "Unknown"),
    text(0, ""),
    image(1, "图片"),
    audio(2, "语音"),
    video(3, "视频"),
    location(4, "位置"),
    file(6, "文件"),
    avchat(7, "音视频通话"),
    notification(5, "通知消息"),
    tip(10, "提醒消息"),
    robot(11, "机器人消息"),
    nrtc_netcall(12, "通话记录"),
    custom(100, "自定义消息"),
    appCustom(101, "七鱼接入方自定义消息"),
    qiyuCustom(102, "七鱼custom消息"),
    qchatCustom(103, "圈组自定义消息");

    final String sendMessageTip;
    private final int value;

    MsgTypeEnum(int i, String str) {
        this.value = i;
        this.sendMessageTip = str;
    }

    public static MsgTypeEnum typeOfValue(int i) {
        for (MsgTypeEnum msgTypeEnum : values()) {
            if (msgTypeEnum.getValue() == i) {
                return msgTypeEnum;
            }
        }
        return undef;
    }

    public final int getValue() {
        return this.value;
    }

    public final String getSendMessageTip() {
        return this.sendMessageTip;
    }
}
