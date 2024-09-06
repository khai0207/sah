package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class MsgPinNotifyOption implements Serializable {
    private final MessageKey key;
    private final MsgPinOption pin;

    public MsgPinNotifyOption(MessageKey messageKey, MsgPinOption msgPinOption) {
        this.key = messageKey;
        this.pin = msgPinOption;
    }

    public MessageKey getKey() {
        return this.key;
    }

    public MsgPinOption getPin() {
        return this.pin;
    }
}
