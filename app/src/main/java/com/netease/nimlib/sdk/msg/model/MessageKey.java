package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.o.w;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.io.Serializable;

/* loaded from: classes.dex */
public class MessageKey implements Serializable {
    private final String fromAccount;
    private final long serverId;
    private final SessionTypeEnum sessionType;
    private final long time;
    private final String toAccount;
    private final String uuid;

    public MessageKey(SessionTypeEnum sessionTypeEnum, String str, String str2, long j, long j2, String str3) {
        this.sessionType = sessionTypeEnum;
        this.fromAccount = str;
        this.toAccount = str2;
        this.time = j;
        this.serverId = j2;
        this.uuid = str3;
    }

    public MessageKey(c cVar) {
        this.sessionType = SessionTypeEnum.typeOfValue(cVar.d(0));
        this.fromAccount = cVar.c(2);
        this.toAccount = cVar.c(1);
        this.time = cVar.e(7);
        this.serverId = cVar.e(12);
        this.uuid = cVar.c(11);
    }

    public SessionTypeEnum getSessionType() {
        return this.sessionType;
    }

    public String getFromAccount() {
        return this.fromAccount;
    }

    public String getToAccount() {
        return this.toAccount;
    }

    public long getTime() {
        return this.time;
    }

    public long getServerId() {
        return this.serverId;
    }

    public String getUuid() {
        return this.uuid;
    }

    public boolean isValid() {
        return (this.sessionType == SessionTypeEnum.P2P || this.sessionType == SessionTypeEnum.Team || this.sessionType == SessionTypeEnum.SUPER_TEAM) && this.fromAccount != null && this.toAccount != null && this.time > 0 && this.serverId >= 0 && !w.a((CharSequence) this.uuid);
    }

    public String toString() {
        return "MessageKey{sessionType=" + this.sessionType + ", fromAccount='" + this.fromAccount + "', toAccount='" + this.toAccount + "', time=" + this.time + ", serverId=" + this.serverId + ", uuid='" + this.uuid + "'}";
    }
}
