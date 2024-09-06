package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import java.util.List;

/* loaded from: classes.dex */
public class GetMessagesByTagsParam {
    private Long fromTime;
    private Integer limit;
    private Boolean reverse;
    private final long roomId;
    private final List<String> tags;
    private Long toTime;
    private List<MsgTypeEnum> types;

    public GetMessagesByTagsParam(long j, List<String> list) {
        this.roomId = j;
        this.tags = list;
    }

    public long getRoomId() {
        return this.roomId;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public List<MsgTypeEnum> getTypes() {
        return this.types;
    }

    public void setTypes(List<MsgTypeEnum> list) {
        this.types = list;
    }

    public Long getFromTime() {
        return this.fromTime;
    }

    public void setFromTime(Long l) {
        this.fromTime = l;
    }

    public Long getToTime() {
        return this.toTime;
    }

    public void setToTime(Long l) {
        this.toTime = l;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public Boolean getReverse() {
        return this.reverse;
    }

    public void setReverse(Boolean bool) {
        this.reverse = bool;
    }

    public String toString() {
        return "GetMessagesByTagsParam{roomId=" + this.roomId + ", tags=" + this.tags + ", messageTypes=" + this.types + ", fromTime=" + this.fromTime + ", toTime=" + this.toTime + ", limit=" + this.limit + ", reverse=" + this.reverse + '}';
    }
}
