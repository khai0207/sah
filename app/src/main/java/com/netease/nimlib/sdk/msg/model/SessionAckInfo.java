package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.io.Serializable;

/* loaded from: classes.dex */
public interface SessionAckInfo extends Serializable {
    String getSessionId();

    SessionTypeEnum getSessionType();

    long getTime();
}
