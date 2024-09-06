package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public interface RecentContact extends Serializable {
    MsgAttachment getAttachment();

    String getContactId();

    String getContent();

    Map<String, Object> getExtension();

    String getFromAccount();

    String getFromNick();

    MsgStatusEnum getMsgStatus();

    MsgTypeEnum getMsgType();

    String getRecentMessageId();

    SessionTypeEnum getSessionType();

    long getTag();

    long getTime();

    int getUnreadCount();

    void setExtension(Map<String, Object> map);

    boolean setLastMsg(IMMessage iMMessage);

    void setMsgStatus(MsgStatusEnum msgStatusEnum);

    void setTag(long j);
}
