package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public interface NIMMessage extends Serializable {
    AttachStatusEnum getAttachStatus();

    String getAttachStr();

    MsgAttachment getAttachment();

    String getCallbackExtension();

    CustomMessageConfig getConfig();

    String getContent();

    MsgDirectionEnum getDirect();

    String getEnv();

    String getFromAccount();

    int getFromClientType();

    String getFromNick();

    Map<String, Object> getLocalExtension();

    MemberPushOption getMemberPushOption();

    MessageKey getMessageKey();

    MsgTypeEnum getMsgType();

    NIMAntiSpamOption getNIMAntiSpamOption();

    String getPushContent();

    Map<String, Object> getPushPayload();

    long getQuickCommentUpdateTime();

    Map<String, Object> getRemoteExtension();

    long getServerId();

    MsgStatusEnum getStatus();

    int getSubtype();

    int getTeamMsgAckCount();

    int getTeamMsgUnAckCount();

    MsgThreadOption getThreadOption();

    long getTime();

    String getUuid();

    String getYidunAntiCheating();

    String getYidunAntiSpamExt();

    String getYidunAntiSpamRes();

    boolean hasSendAck();

    Boolean isChecked();

    boolean isDeleted();

    boolean isInBlackList();

    boolean isRemoteRead();

    boolean isSessionUpdate();

    boolean isTheSame(NIMMessage nIMMessage);

    boolean isThread();

    boolean needMsgAck();

    void setAttachStatus(AttachStatusEnum attachStatusEnum);

    void setAttachment(MsgAttachment msgAttachment);

    void setChecked(Boolean bool);

    void setClientAntiSpam(boolean z);

    void setConfig(CustomMessageConfig customMessageConfig);

    void setContent(String str);

    void setDirect(MsgDirectionEnum msgDirectionEnum);

    void setEnv(String str);

    void setForceUploadFile(boolean z);

    void setFromAccount(String str);

    void setLocalExtension(Map<String, Object> map);

    void setMemberPushOption(MemberPushOption memberPushOption);

    void setMsgAck();

    void setNIMAntiSpamOption(NIMAntiSpamOption nIMAntiSpamOption);

    void setPushContent(String str);

    void setPushPayload(Map<String, Object> map);

    void setRemoteExtension(Map<String, Object> map);

    void setSessionUpdate(boolean z);

    void setStatus(MsgStatusEnum msgStatusEnum);

    void setSubtype(int i);

    void setThreadOption(NIMMessage nIMMessage);

    void setYidunAntiCheating(String str);

    void setYidunAntiSpamExt(String str);
}
