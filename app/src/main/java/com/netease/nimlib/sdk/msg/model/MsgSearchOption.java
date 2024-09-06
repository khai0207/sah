package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import java.util.List;

/* loaded from: classes.dex */
public class MsgSearchOption {
    private static final int DEFAULT_LIMIT = 100;
    private List<String> fromIds;
    private long startTime = 0;
    private long endTime = 0;
    private int limit = 100;
    private SearchOrderEnum order = SearchOrderEnum.DESC;
    private List<MsgTypeEnum> messageTypes = null;
    private List<Integer> messageSubTypes = null;
    private boolean allMessageTypes = false;
    private String searchContent = null;
    private boolean enableContentTransfer = true;

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public SearchOrderEnum getOrder() {
        return this.order;
    }

    public void setOrder(SearchOrderEnum searchOrderEnum) {
        this.order = searchOrderEnum;
    }

    public List<MsgTypeEnum> getMessageTypes() {
        return this.messageTypes;
    }

    public void setMessageTypes(List<MsgTypeEnum> list) {
        this.messageTypes = list;
    }

    public List<Integer> getMessageSubTypes() {
        return this.messageSubTypes;
    }

    public void setMessageSubTypes(List<Integer> list) {
        this.messageSubTypes = list;
    }

    public boolean isAllMessageTypes() {
        return this.allMessageTypes;
    }

    public void setAllMessageTypes(boolean z) {
        this.allMessageTypes = z;
    }

    public String getSearchContent() {
        return this.searchContent;
    }

    public void setSearchContent(String str) {
        this.searchContent = str;
    }

    public List<String> getFromIds() {
        return this.fromIds;
    }

    public void setFromIds(List<String> list) {
        this.fromIds = list;
    }

    public boolean isEnableContentTransfer() {
        return this.enableContentTransfer;
    }

    public void setEnableContentTransfer(boolean z) {
        this.enableContentTransfer = z;
    }

    public String toString() {
        return "MsgSearchOption{startTime=" + this.startTime + ", endTime=" + this.endTime + ", limit=" + this.limit + ", order=" + this.order + ", messageTypes=" + this.messageTypes + ", messageSubTypes=" + this.messageSubTypes + ", allMessageTypes=" + this.allMessageTypes + ", searchContent='" + this.searchContent + "', fromIds=" + this.fromIds + ", enableContentTransfer=" + this.enableContentTransfer + '}';
    }
}
