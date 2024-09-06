package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.SessionAckInfo;

/* compiled from: SessionAckInfoImpl.java */
/* loaded from: classes.dex */
public class w implements SessionAckInfo {
    private final SessionTypeEnum a;
    private final String b;
    private final long c;

    public w(SessionTypeEnum sessionTypeEnum, String str, long j) {
        this.b = str;
        this.a = sessionTypeEnum;
        this.c = j;
    }

    @Override // com.netease.nimlib.sdk.msg.model.SessionAckInfo
    public SessionTypeEnum getSessionType() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.SessionAckInfo
    public String getSessionId() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.msg.model.SessionAckInfo
    public long getTime() {
        return this.c;
    }

    public String toString() {
        return "SessionAckInfo{sessionType=" + this.a + ", sessionId='" + this.b + "', time=" + this.c + '}';
    }

    public static w a(com.netease.nimlib.push.packet.b.c cVar) {
        SessionTypeEnum sessionTypeEnum;
        int d = cVar.d(1);
        if (d == 1) {
            sessionTypeEnum = SessionTypeEnum.Team;
        } else if (d == 2) {
            sessionTypeEnum = SessionTypeEnum.SUPER_TEAM;
        } else {
            sessionTypeEnum = SessionTypeEnum.P2P;
        }
        return new w(sessionTypeEnum, cVar.c(2), cVar.e(3));
    }
}
