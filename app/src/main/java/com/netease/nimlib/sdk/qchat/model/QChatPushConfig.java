package com.netease.nimlib.sdk.qchat.model;

import com.netease.nimlib.sdk.qchat.enums.QChatPushMsgType;

/* loaded from: classes.dex */
public interface QChatPushConfig {
    QChatPushMsgType getPushMsgType();

    String getStartTimeString();

    String getStopTimeString();

    boolean isNoDisturbOpen();

    boolean isPushShowNoDetail();

    void setNoDisturbOpen(boolean z);

    void setPushMsgType(QChatPushMsgType qChatPushMsgType);

    void setPushShowNoDetail(boolean z);

    void setStartTime(String str);

    void setStopTime(String str);
}
