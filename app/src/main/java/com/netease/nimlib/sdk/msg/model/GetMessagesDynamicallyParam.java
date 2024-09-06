package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import java.io.Serializable;

/* loaded from: classes.dex */
public class GetMessagesDynamicallyParam implements Serializable {
    public static final int MAX_LIMIT = 100;
    private String anchorClientId;
    private long anchorServerId;
    private GetMessageDirectionEnum direction;
    private long fromTime;
    private int limit;
    private final String sessionId;
    private final SessionTypeEnum sessionType;
    private long toTime;

    public GetMessagesDynamicallyParam(String str, SessionTypeEnum sessionTypeEnum) {
        this(str, sessionTypeEnum, 0L, 0L, 0L, null, 0, GetMessageDirectionEnum.FORWARD);
    }

    public GetMessagesDynamicallyParam(String str, SessionTypeEnum sessionTypeEnum, long j, long j2, long j3, String str2, int i, GetMessageDirectionEnum getMessageDirectionEnum) {
        this.sessionId = str;
        this.sessionType = sessionTypeEnum;
        this.fromTime = j;
        this.toTime = j2;
        this.anchorServerId = j3;
        this.anchorClientId = str2;
        this.limit = i;
        this.direction = getMessageDirectionEnum;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public SessionTypeEnum getSessionType() {
        return this.sessionType;
    }

    public long getFromTime() {
        return this.fromTime;
    }

    public void setFromTime(long j) {
        this.fromTime = j;
    }

    public long getToTime() {
        return this.toTime;
    }

    public void setToTime(long j) {
        this.toTime = j;
    }

    public long getAnchorServerId() {
        return this.anchorServerId;
    }

    public void setAnchorServerId(long j) {
        this.anchorServerId = j;
    }

    public String getAnchorClientId() {
        return this.anchorClientId;
    }

    public void setAnchorClientId(String str) {
        this.anchorClientId = str;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public GetMessageDirectionEnum getDirection() {
        return this.direction;
    }

    public void setDirection(GetMessageDirectionEnum getMessageDirectionEnum) {
        this.direction = getMessageDirectionEnum;
    }

    public IMMessage getAnchor() {
        String anchorClientId = getAnchorClientId();
        if (w.b((CharSequence) anchorClientId)) {
            IMMessage queryMessageByUuid = MsgDBHelper.queryMessageByUuid(anchorClientId);
            if (queryMessageByUuid instanceof IMMessageImpl) {
                return queryMessageByUuid;
            }
        }
        IMMessageImpl iMMessageImpl = (IMMessageImpl) MessageBuilder.createEmptyMessage(getSessionId(), getSessionType(), getDirection() == GetMessageDirectionEnum.BACKWARD ? getFromTime() : getToTime());
        iMMessageImpl.setUuid(getAnchorClientId());
        iMMessageImpl.setServerId(getAnchorServerId());
        return iMMessageImpl;
    }

    public GetMessagesDynamicallyParam createStandardizedParam() {
        String str = this.sessionId;
        SessionTypeEnum sessionTypeEnum = this.sessionType;
        if (sessionTypeEnum == null) {
            sessionTypeEnum = SessionTypeEnum.None;
        }
        SessionTypeEnum sessionTypeEnum2 = sessionTypeEnum;
        long j = this.fromTime;
        long j2 = this.toTime;
        long j3 = this.anchorServerId;
        String str2 = this.anchorClientId;
        int i = this.limit;
        if (i <= 0) {
            i = 100;
        }
        int min = Math.min(i, 100);
        GetMessageDirectionEnum getMessageDirectionEnum = this.direction;
        if (getMessageDirectionEnum == null) {
            getMessageDirectionEnum = GetMessageDirectionEnum.FORWARD;
        }
        return new GetMessagesDynamicallyParam(str, sessionTypeEnum2, j, j2, j3, str2, min, getMessageDirectionEnum);
    }

    public boolean isValid() {
        int i;
        return w.b((CharSequence) this.sessionId) && (this.sessionType == SessionTypeEnum.P2P || this.sessionType == SessionTypeEnum.Team || this.sessionType == SessionTypeEnum.SUPER_TEAM) && this.fromTime >= 0 && this.toTime >= 0 && (i = this.limit) >= 0 && i <= 100 && (this.anchorServerId == 0 || w.b((CharSequence) this.anchorClientId));
    }

    public String toString() {
        return "GetMessagesDynamicallyParam{sessionId='" + this.sessionId + "', sessionType=" + this.sessionType + ", fromTime=" + this.fromTime + ", toTime=" + this.toTime + ", excludeServerId=" + this.anchorServerId + ", excludeClientId='" + this.anchorClientId + "', limit=" + this.limit + ", direction=" + this.direction + '}';
    }
}
