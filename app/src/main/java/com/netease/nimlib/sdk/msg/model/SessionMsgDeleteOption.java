package com.netease.nimlib.sdk.msg.model;

import android.text.TextUtils;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

/* loaded from: classes.dex */
public class SessionMsgDeleteOption {
    private String ext;
    private String sessionId;
    private SessionTypeEnum sessionType;
    private long time;

    public SessionMsgDeleteOption(String str, SessionTypeEnum sessionTypeEnum, long j, String str2) {
        this.sessionId = str;
        this.sessionType = sessionTypeEnum;
        this.time = j;
        this.ext = str2;
    }

    public static SessionMsgDeleteOption fromProperty(c cVar) {
        SessionTypeEnum sessionTypeEnum;
        String c;
        String c2 = cVar.c(0);
        if (TextUtils.isEmpty(c2) || "1".equals(c2)) {
            sessionTypeEnum = SessionTypeEnum.P2P;
            c = cVar.c(1);
        } else {
            sessionTypeEnum = SessionTypeEnum.Team;
            c = cVar.c(3);
        }
        return new SessionMsgDeleteOption(c, sessionTypeEnum, cVar.e(6), cVar.c(7));
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public SessionTypeEnum getSessionType() {
        return this.sessionType;
    }

    public long getTime() {
        return this.time;
    }

    public String getExt() {
        return this.ext;
    }
}
