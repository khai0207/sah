package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface MsgPinSyncResponseOption extends Serializable {
    MessageKey getKey();

    MsgPinOption getPinOption();
}
