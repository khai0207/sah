package com.netease.nimlib.session;

import android.text.TextUtils;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MemberPushOption;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.sdk.msg.model.MessageRobotInfo;
import com.netease.nimlib.sdk.msg.model.MsgThreadOption;
import com.netease.nimlib.sdk.msg.model.NIMAntiSpamOption;
import com.netease.nimlib.sdk.msg.model.NIMMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class IMMessageImpl implements IMMessage {
    private static final String KEY_FORCE_PUSH_CONTENT = "k_p2";
    private static final String KEY_FORCE_PUSH_LIST = "k_p3";
    private static final String KEY_IS_FORCE_PUSH = "k_p1";
    private static final String TAG = "IMMessage";
    private static final long serialVersionUID = -1949246189525361810L;
    private int ackCount;
    private AttachStatusEnum attachStatus;
    private String attachStr;
    private MsgAttachment attachment;
    private String callbackExtension;
    private boolean clientAntiSpam;
    private CustomMessageConfig config;
    private String configStr;
    private String content;
    private MsgDirectionEnum direct;
    private String env;
    private String fromAccount;
    private String fromNick;
    private boolean hasSendAck;
    private boolean isInBlackList;
    private String localExtension;
    private MemberPushOption memberPushOption;
    private String memberPushOptionStr;
    private boolean msgAck;
    private int msgType;
    private NIMAntiSpamOption nimAntiSpamOption;
    private String nimAntiSpamOptionStr;
    private String pushContent;
    private String pushPayload;
    private long quickCommentUpdateTime;
    private String remoteExtension;
    private MessageRobotInfo robotInfo;
    private long serverId;
    private String sessionId;
    private SessionTypeEnum sessionType;
    private MsgStatusEnum status;
    private int subtype;
    private MsgThreadOption threadOption;
    private long time;
    private ac timeConsumingStatistics;
    private int unAckCount;
    private String uuid;
    private String yidunAntiCheating;
    private String yidunAntiSpamExt;
    private String yidunAntiSpamRes;
    private long messageId = -1;
    private int fromClient = 1;
    private Boolean isChecked = null;
    private boolean sessionUpdate = true;
    private boolean isDeleted = false;
    private Serializable realMsgObj = null;

    public long getMessageId() {
        return this.messageId;
    }

    public void setMessageId(long j) {
        this.messageId = j;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.IMMessage
    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.IMMessage
    public SessionTypeEnum getSessionType() {
        return this.sessionType;
    }

    @Override // com.netease.nimlib.sdk.msg.model.IMMessage
    public Serializable getRealMsgObj() {
        Serializable serializable = this.realMsgObj;
        return serializable != null ? serializable : this;
    }

    public void setRealMsgObj(Serializable serializable) {
        this.realMsgObj = serializable;
    }

    public void setSessionType(SessionTypeEnum sessionTypeEnum) {
        this.sessionType = sessionTypeEnum;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getFromNick() {
        if (TextUtils.isEmpty(this.fromAccount)) {
            com.netease.nimlib.log.b.d(TAG, " fromAccount is null and account is" + com.netease.nimlib.c.n());
            return " ";
        }
        return u.c().a(this.sessionId, this.sessionType, this.fromAccount);
    }

    public void setMsgFromNick(String str) {
        this.fromNick = str;
    }

    public String getMsgFromNick() {
        return this.fromNick;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public MsgTypeEnum getMsgType() {
        return j.a(this.msgType);
    }

    public int getMsgTypeInner() {
        return this.msgType;
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public int getSubtype() {
        return this.subtype;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setSubtype(int i) {
        this.subtype = i;
    }

    @Override // com.netease.nimlib.sdk.msg.model.IMMessage
    public MessageRobotInfo getRobotInfo() {
        return this.robotInfo;
    }

    @Override // com.netease.nimlib.sdk.msg.model.IMMessage
    public void setRobotInfo(MessageRobotInfo messageRobotInfo) {
        this.robotInfo = messageRobotInfo;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public MsgStatusEnum getStatus() {
        return this.status;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setStatus(MsgStatusEnum msgStatusEnum) {
        this.status = msgStatusEnum;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public MsgDirectionEnum getDirect() {
        return this.direct;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setDirect(MsgDirectionEnum msgDirectionEnum) {
        this.direct = msgDirectionEnum;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getContent() {
        return this.content;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setContent(String str) {
        this.content = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getFromAccount() {
        return this.fromAccount;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setFromAccount(String str) {
        this.fromAccount = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public MsgAttachment getAttachment() {
        return this.attachment;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setAttachment(MsgAttachment msgAttachment) {
        this.attachment = msgAttachment;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public AttachStatusEnum getAttachStatus() {
        AttachStatusEnum attachStatusEnum = this.attachStatus;
        return attachStatusEnum == null ? AttachStatusEnum.def : attachStatusEnum;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setAttachStatus(AttachStatusEnum attachStatusEnum) {
        this.attachStatus = attachStatusEnum;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public boolean isTheSame(NIMMessage nIMMessage) {
        if (!(nIMMessage instanceof IMMessageImpl)) {
            return false;
        }
        long messageId = ((IMMessageImpl) nIMMessage).getMessageId();
        long j = this.messageId;
        if (j <= 0 || messageId <= 0) {
            return TextUtils.equals(this.uuid, nIMMessage.getUuid());
        }
        return j == messageId;
    }

    public void setAttachStr(String str) {
        this.attachStr = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.sessionType == SessionTypeEnum.Ysf && this.msgType == MsgTypeEnum.custom.getValue()) {
            this.attachment = i.a().a(MsgTypeEnum.qiyuCustom.getValue(), str);
        } else {
            this.attachment = i.a().a(this.msgType, str);
        }
    }

    public void setAttachStrOnly(String str) {
        this.attachStr = str;
    }

    public String getAttachStrOnly() {
        return this.attachStr;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getAttachStr() {
        return getAttachStr(false);
    }

    public String getAttachStr(boolean z) {
        MsgAttachment msgAttachment;
        if (z && (msgAttachment = this.attachment) != null) {
            return msgAttachment.toJson(true);
        }
        String str = this.attachStr;
        if (str != null) {
            return str;
        }
        MsgAttachment msgAttachment2 = this.attachment;
        if (msgAttachment2 == null) {
            return null;
        }
        return msgAttachment2.toJson(false);
    }

    public void setRobotInfoStr(String str) {
        MessageRobotInfo a = m.a(str);
        if (a != null) {
            setRobotInfo(a);
        }
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public long getServerId() {
        return this.serverId;
    }

    public void setServerId(long j) {
        this.serverId = j;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public boolean needMsgAck() {
        return this.msgAck;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setMsgAck() {
        setMsgAck(true);
    }

    public void setMsgAck(boolean z) {
        this.msgAck = z;
    }

    void setHasSendAck() {
        setHasSendAck(true);
    }

    public void setHasSendAck(boolean z) {
        this.hasSendAck = z;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public boolean hasSendAck() {
        return this.hasSendAck;
    }

    public void setTeamMsgAckCount(int i) {
        this.ackCount = i;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public int getTeamMsgAckCount() {
        int b = com.netease.nimlib.team.h.c().b(this.uuid);
        return b >= 0 ? b : this.ackCount;
    }

    public void setTeamMsgUnAckCount(int i) {
        this.unAckCount = i;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public int getTeamMsgUnAckCount() {
        int c = com.netease.nimlib.team.h.c().c(this.uuid);
        return c >= 0 ? c : this.unAckCount;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public CustomMessageConfig getConfig() {
        return this.config;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setConfig(CustomMessageConfig customMessageConfig) {
        this.config = customMessageConfig;
        if (customMessageConfig != null) {
            HashMap hashMap = new HashMap();
            if (!customMessageConfig.enableHistory) {
                hashMap.put(CustomMessageConfig.KEY_ENABLE_HISTORY, Boolean.valueOf(customMessageConfig.enableHistory));
            }
            if (!customMessageConfig.enableRoaming) {
                hashMap.put(CustomMessageConfig.KEY_ENABLE_ROAMING, Boolean.valueOf(customMessageConfig.enableRoaming));
            }
            if (!customMessageConfig.enableSelfSync) {
                hashMap.put(CustomMessageConfig.KEY_ENABLE_SELF_SYNC, Boolean.valueOf(customMessageConfig.enableSelfSync));
            }
            if (!customMessageConfig.enablePush) {
                hashMap.put(CustomMessageConfig.KEY_ENABLE_PUSH, Boolean.valueOf(customMessageConfig.enablePush));
            }
            if (!customMessageConfig.enablePersist) {
                hashMap.put(CustomMessageConfig.KEY_ENABLE_PERSIST, Boolean.valueOf(customMessageConfig.enablePersist));
            }
            if (!customMessageConfig.enablePushNick) {
                hashMap.put(CustomMessageConfig.KEY_ENABLE_PUSH_NICK, Boolean.valueOf(customMessageConfig.enablePushNick));
            }
            if (!customMessageConfig.enableUnreadCount) {
                hashMap.put(CustomMessageConfig.KEY_ENABLE_UNREAD_COUNT, Boolean.valueOf(customMessageConfig.enableUnreadCount));
            }
            if (!customMessageConfig.enableRoute) {
                hashMap.put(CustomMessageConfig.KEY_ENABLE_ROUTE, Boolean.valueOf(customMessageConfig.enableRoute));
            }
            String a = j.a(hashMap);
            this.configStr = a != null ? a : "";
            return;
        }
        this.configStr = "";
    }

    public String getConfigStr() {
        return this.configStr;
    }

    public void setConfigStr(String str) {
        this.configStr = str;
        if (this.config == null) {
            this.config = new CustomMessageConfig();
        }
        Map<String, Object> c = j.c(str);
        if (c == null || c.isEmpty()) {
            return;
        }
        if (c.containsKey(CustomMessageConfig.KEY_ENABLE_HISTORY)) {
            this.config.enableHistory = ((Boolean) c.get(CustomMessageConfig.KEY_ENABLE_HISTORY)).booleanValue();
        }
        if (c.containsKey(CustomMessageConfig.KEY_ENABLE_ROAMING)) {
            this.config.enableRoaming = ((Boolean) c.get(CustomMessageConfig.KEY_ENABLE_ROAMING)).booleanValue();
        }
        if (c.containsKey(CustomMessageConfig.KEY_ENABLE_SELF_SYNC)) {
            this.config.enableSelfSync = ((Boolean) c.get(CustomMessageConfig.KEY_ENABLE_SELF_SYNC)).booleanValue();
        }
        if (c.containsKey(CustomMessageConfig.KEY_ENABLE_PUSH)) {
            this.config.enablePush = ((Boolean) c.get(CustomMessageConfig.KEY_ENABLE_PUSH)).booleanValue();
        }
        if (c.containsKey(CustomMessageConfig.KEY_ENABLE_PERSIST)) {
            this.config.enablePersist = ((Boolean) c.get(CustomMessageConfig.KEY_ENABLE_PERSIST)).booleanValue();
        }
        if (c.containsKey(CustomMessageConfig.KEY_ENABLE_PUSH_NICK)) {
            this.config.enablePushNick = ((Boolean) c.get(CustomMessageConfig.KEY_ENABLE_PUSH_NICK)).booleanValue();
        }
        if (c.containsKey(CustomMessageConfig.KEY_ENABLE_UNREAD_COUNT)) {
            this.config.enableUnreadCount = ((Boolean) c.get(CustomMessageConfig.KEY_ENABLE_UNREAD_COUNT)).booleanValue();
        }
        if (c.containsKey(CustomMessageConfig.KEY_ENABLE_ROUTE)) {
            this.config.enableRoute = ((Boolean) c.get(CustomMessageConfig.KEY_ENABLE_ROUTE)).booleanValue();
        }
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public MemberPushOption getMemberPushOption() {
        return this.memberPushOption;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setMemberPushOption(MemberPushOption memberPushOption) {
        this.memberPushOption = memberPushOption;
        if (memberPushOption != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(KEY_IS_FORCE_PUSH, Boolean.valueOf(memberPushOption.isForcePush()));
            hashMap.put(KEY_FORCE_PUSH_CONTENT, memberPushOption.getForcePushContent());
            hashMap.put(KEY_FORCE_PUSH_LIST, j.e(memberPushOption.getForcePushList()));
            String a = j.a(hashMap);
            this.memberPushOptionStr = a != null ? a : "";
            return;
        }
        this.memberPushOptionStr = "";
    }

    public String getMemberPushOptionStr() {
        return this.memberPushOptionStr;
    }

    public void setMemberPushOptionStr(String str) {
        this.memberPushOptionStr = str;
        if (this.memberPushOption == null && !TextUtils.isEmpty(str)) {
            this.memberPushOption = new MemberPushOption();
        }
        Map<String, Object> c = j.c(str);
        if (c == null || c.isEmpty() || this.memberPushOption == null) {
            return;
        }
        if (c.containsKey(KEY_IS_FORCE_PUSH)) {
            this.memberPushOption.setForcePush(((Boolean) c.get(KEY_IS_FORCE_PUSH)).booleanValue());
        }
        if (c.containsKey(KEY_FORCE_PUSH_CONTENT)) {
            this.memberPushOption.setForcePushContent((String) c.get(KEY_FORCE_PUSH_CONTENT));
        }
        if (c.containsKey(KEY_FORCE_PUSH_LIST)) {
            this.memberPushOption.setForcePushList(j.b((String) c.get(KEY_FORCE_PUSH_LIST)));
        }
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public Map<String, Object> getRemoteExtension() {
        return j.c(this.remoteExtension);
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setRemoteExtension(Map<String, Object> map) {
        this.remoteExtension = j.a(map);
    }

    public String getRemoteExtensionStr() {
        return this.remoteExtension;
    }

    public void setRemoteExtensionStr(String str) {
        this.remoteExtension = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public Map<String, Object> getLocalExtension() {
        return j.c(this.localExtension);
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setLocalExtension(Map<String, Object> map) {
        this.localExtension = j.a(map);
    }

    public String getLocalExtensionStr() {
        return this.localExtension;
    }

    public void setLocalExtensionStr(String str) {
        this.localExtension = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getCallbackExtension() {
        return this.callbackExtension;
    }

    public void setCallbackExtension(String str) {
        this.callbackExtension = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public Map<String, Object> getPushPayload() {
        return j.c(this.pushPayload);
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setPushPayload(Map<String, Object> map) {
        this.pushPayload = j.a(map);
    }

    public String getPushPayloadStr() {
        return this.pushPayload;
    }

    public void setPushPayloadStr(String str) {
        this.pushPayload = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getPushContent() {
        return this.pushContent;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setPushContent(String str) {
        this.pushContent = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public boolean isRemoteRead() {
        return getDirect() == MsgDirectionEnum.Out && getSessionType() == SessionTypeEnum.P2P && getStatus() == MsgStatusEnum.success && getTime() <= e.b().a(getSessionId());
    }

    public IMMessageImpl deepClone() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            return (IMMessageImpl) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (Exception e) {
            com.netease.nimlib.log.b.e(TAG, "deep clone error, e=" + e.getMessage(), e);
            return null;
        }
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public int getFromClientType() {
        return this.fromClient;
    }

    public void setFromClientType(int i) {
        this.fromClient = i;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public NIMAntiSpamOption getNIMAntiSpamOption() {
        return this.nimAntiSpamOption;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setNIMAntiSpamOption(NIMAntiSpamOption nIMAntiSpamOption) {
        this.nimAntiSpamOption = nIMAntiSpamOption;
        this.nimAntiSpamOptionStr = j.a(nIMAntiSpamOption);
    }

    public String getNimAntiSpamOptionStr() {
        return this.nimAntiSpamOptionStr;
    }

    public void setNimAntiSpamOptionStr(String str) {
        this.nimAntiSpamOptionStr = str;
        this.nimAntiSpamOption = j.a(str);
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setClientAntiSpam(boolean z) {
        this.clientAntiSpam = z;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setForceUploadFile(boolean z) {
        MsgAttachment msgAttachment = this.attachment;
        if (msgAttachment instanceof FileAttachment) {
            ((FileAttachment) msgAttachment).setForceUpload(z);
        }
    }

    public void setInBlackList(boolean z) {
        this.isInBlackList = z;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public boolean isInBlackList() {
        return this.isInBlackList;
    }

    public boolean getClientAntiSpam() {
        return this.clientAntiSpam;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setChecked(Boolean bool) {
        this.isChecked = bool;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public Boolean isChecked() {
        return this.isChecked;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public boolean isSessionUpdate() {
        return this.sessionUpdate;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setSessionUpdate(boolean z) {
        this.sessionUpdate = z;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public MsgThreadOption getThreadOption() {
        return this.threadOption;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setThreadOption(NIMMessage nIMMessage) {
        if (!(nIMMessage instanceof IMMessageImpl)) {
            this.threadOption = new MsgThreadOption();
            return;
        }
        IMMessageImpl iMMessageImpl = (IMMessageImpl) nIMMessage;
        MsgThreadOption msgThreadOption = this.threadOption;
        if (msgThreadOption == null) {
            msgThreadOption = new MsgThreadOption();
        }
        this.threadOption = msgThreadOption;
        msgThreadOption.setReplyMsgFromAccount(iMMessageImpl.getFromAccount());
        this.threadOption.setReplyMsgToAccount(g.a(iMMessageImpl));
        this.threadOption.setReplyMsgTime(iMMessageImpl.getTime());
        this.threadOption.setReplyMsgIdServer(iMMessageImpl.getServerId());
        this.threadOption.setReplyMsgIdClient(iMMessageImpl.getUuid());
        if (iMMessageImpl.isThread()) {
            this.threadOption.setThreadMsgFromAccount(iMMessageImpl.getFromAccount());
            this.threadOption.setThreadMsgToAccount(g.a(iMMessageImpl));
            this.threadOption.setThreadMsgTime(iMMessageImpl.getTime());
            this.threadOption.setThreadMsgIdServer(iMMessageImpl.getServerId());
            this.threadOption.setThreadMsgIdClient(iMMessageImpl.getUuid());
            return;
        }
        this.threadOption.setThreadMsgFromAccount(iMMessageImpl.getThreadOption().getThreadMsgFromAccount());
        this.threadOption.setThreadMsgToAccount(iMMessageImpl.getThreadOption().getThreadMsgToAccount());
        this.threadOption.setThreadMsgTime(iMMessageImpl.getThreadOption().getThreadMsgTime());
        this.threadOption.setThreadMsgIdServer(iMMessageImpl.getThreadOption().getThreadMsgIdServer());
        this.threadOption.setThreadMsgIdClient(iMMessageImpl.getThreadOption().getThreadMsgIdClient());
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public boolean isThread() {
        MsgThreadOption msgThreadOption = this.threadOption;
        return msgThreadOption == null || msgThreadOption.getThreadMsgIdServer() <= 0;
    }

    public void setThreadOption(MsgThreadOption msgThreadOption) {
        this.threadOption = msgThreadOption;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public long getQuickCommentUpdateTime() {
        return this.quickCommentUpdateTime;
    }

    public void setQuickCommentUpdateTime(long j) {
        this.quickCommentUpdateTime = j;
    }

    public boolean hasPulledQuickComment() {
        return com.netease.nimlib.biz.l.G() < this.quickCommentUpdateTime;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean z) {
        this.isDeleted = z;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public MessageKey getMessageKey() {
        return new MessageKey(this.sessionType, this.fromAccount, g.a(this), this.time, this.serverId, this.uuid);
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getYidunAntiCheating() {
        return this.yidunAntiCheating;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setYidunAntiCheating(String str) {
        this.yidunAntiCheating = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getEnv() {
        return this.env;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setEnv(String str) {
        this.env = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getYidunAntiSpamExt() {
        return this.yidunAntiSpamExt;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public void setYidunAntiSpamExt(String str) {
        this.yidunAntiSpamExt = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.NIMMessage
    public String getYidunAntiSpamRes() {
        return this.yidunAntiSpamRes;
    }

    public void setYidunAntiSpamRes(String str) {
        this.yidunAntiSpamRes = str;
    }

    public ac getTimeConsumingStatistics() {
        return this.timeConsumingStatistics;
    }

    public void setTimeConsumingStatistics(ac acVar) {
        this.timeConsumingStatistics = acVar;
    }

    public static String toStringSimple(IMMessage iMMessage) {
        if (iMMessage == null) {
            return "IMMessageImpl{null}";
        }
        return "IMMessageImpl{sessionId='" + iMMessage.getSessionId() + "', sessionType=" + iMMessage.getSessionType() + ", time=" + iMMessage.getTime() + ", uuid='" + iMMessage.getUuid() + "', serverId='" + iMMessage.getServerId() + "'}";
    }
}
