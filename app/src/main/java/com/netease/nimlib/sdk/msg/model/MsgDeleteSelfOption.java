package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

/* loaded from: classes.dex */
public class MsgDeleteSelfOption {
    public static final int SESSION_TYPE_P2P = 1;
    public static final int SESSION_TYPE_TEAM = 2;
    private String deleteMsgClientId;
    private long deleteMsgCreateTime;
    private String deleteMsgServerId;
    private String ext;
    private String from;
    private long time;
    private String to;
    private SessionTypeEnum type;

    public MsgDeleteSelfOption() {
    }

    public MsgDeleteSelfOption(SessionTypeEnum sessionTypeEnum, String str, String str2, String str3, String str4, long j) {
        this(sessionTypeEnum, str, str2, str3, str4, j, 0L, "");
    }

    public MsgDeleteSelfOption(SessionTypeEnum sessionTypeEnum, String str, String str2, String str3, String str4, long j, String str5) {
        this(sessionTypeEnum, str, str2, str3, str4, j, 0L, str5);
    }

    public MsgDeleteSelfOption(SessionTypeEnum sessionTypeEnum, String str, String str2, String str3, String str4, long j, long j2, String str5) {
        this.type = sessionTypeEnum;
        this.from = str;
        this.to = str2;
        this.deleteMsgServerId = str3;
        this.deleteMsgClientId = str4;
        this.deleteMsgCreateTime = j;
        this.time = j2;
        this.ext = str5;
    }

    public SessionTypeEnum getType() {
        return this.type;
    }

    public void setType(SessionTypeEnum sessionTypeEnum) {
        this.type = sessionTypeEnum;
    }

    public void setType(int i) {
        if (i == 1) {
            this.type = SessionTypeEnum.P2P;
        } else if (i == 2) {
            this.type = SessionTypeEnum.Team;
        } else {
            this.type = SessionTypeEnum.None;
        }
    }

    public MsgDeleteSelfOption(c cVar) {
        setType(cVar.d(1));
        this.from = cVar.c(2);
        this.to = cVar.c(3);
        this.deleteMsgServerId = cVar.c(4);
        this.deleteMsgClientId = cVar.c(5);
        this.deleteMsgCreateTime = cVar.e(6);
        this.time = cVar.e(7);
        this.ext = cVar.c(8);
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public String getDeleteMsgServerId() {
        return this.deleteMsgServerId;
    }

    public void setDeleteMsgServerId(String str) {
        this.deleteMsgServerId = str;
    }

    public String getDeleteMsgClientId() {
        return this.deleteMsgClientId;
    }

    public void setDeleteMsgClientId(String str) {
        this.deleteMsgClientId = str;
    }

    public long getDeleteMsgCreateTime() {
        return this.deleteMsgCreateTime;
    }

    public void setDeleteMsgCreateTime(long j) {
        this.deleteMsgCreateTime = j;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public String getExt() {
        return this.ext;
    }

    public void setExt(String str) {
        this.ext = str;
    }

    public String getSessionId() {
        String n = com.netease.nimlib.c.n();
        if (n == null) {
            return null;
        }
        return n.equals(this.to) ? this.from : this.to;
    }
}
