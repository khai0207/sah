package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.io.Serializable;

/* loaded from: classes.dex */
public class RoamMsgHasMoreOption implements Serializable {
    private final long serverId;
    private final String sessionId;
    private final SessionTypeEnum sessionType;
    private final long time;

    public RoamMsgHasMoreOption(String str, SessionTypeEnum sessionTypeEnum, long j, long j2) {
        this.sessionId = str;
        this.sessionType = sessionTypeEnum;
        this.time = j;
        this.serverId = j2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0041, code lost:
    
        if (r1.equals("1") != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public RoamMsgHasMoreOption(com.netease.nimlib.push.packet.b.c r7) {
        /*
            r6 = this;
            r6.<init>()
            r0 = 7
            java.lang.String r0 = r7.c(r0)
            long r0 = java.lang.Long.parseLong(r0)
            r6.time = r0
            r0 = 12
            java.lang.String r0 = r7.c(r0)
            long r0 = java.lang.Long.parseLong(r0)
            r6.serverId = r0
            r0 = 0
            java.lang.String r1 = r7.c(r0)
            r2 = 1
            java.lang.String r3 = r7.c(r2)
            r4 = 2
            java.lang.String r7 = r7.c(r4)
            int r5 = r1.hashCode()
            switch(r5) {
                case 48: goto L44;
                case 49: goto L3b;
                case 50: goto L31;
                default: goto L30;
            }
        L30:
            goto L4e
        L31:
            java.lang.String r0 = "2"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 1
            goto L4f
        L3b:
            java.lang.String r4 = "1"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L4e
            goto L4f
        L44:
            java.lang.String r0 = "0"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 2
            goto L4f
        L4e:
            r0 = -1
        L4f:
            if (r0 == 0) goto L68
            if (r0 == r2) goto L68
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r0 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.P2P
            r6.sessionType = r0
            java.lang.String r0 = com.netease.nimlib.c.n()
            if (r0 == 0) goto L64
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L64
            goto L65
        L64:
            r3 = r7
        L65:
            r6.sessionId = r3
            goto L6e
        L68:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r7 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.Team
            r6.sessionType = r7
            r6.sessionId = r3
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.sdk.msg.model.RoamMsgHasMoreOption.<init>(com.netease.nimlib.push.packet.b.c):void");
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

    public long getServerId() {
        return this.serverId;
    }

    public String toString() {
        return String.format("{sessionId: %s, sessionType: %s, time: %s, serverId: %s}", this.sessionId, this.sessionType, Long.valueOf(this.time), Long.valueOf(this.serverId));
    }
}
