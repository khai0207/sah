package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.io.Serializable;

/* loaded from: classes.dex */
public interface IMMessage extends NIMMessage {
    Serializable getRealMsgObj();

    MessageRobotInfo getRobotInfo();

    String getSessionId();

    SessionTypeEnum getSessionType();

    void setRobotInfo(MessageRobotInfo messageRobotInfo);
}
