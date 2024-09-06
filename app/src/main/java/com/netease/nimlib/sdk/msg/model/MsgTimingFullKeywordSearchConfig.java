package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import java.util.List;

/* loaded from: classes.dex */
public class MsgTimingFullKeywordSearchConfig {
    private final long fromTime;
    private final String keyword;
    private List<Integer> msgSubtypeList;
    private List<MsgTypeEnum> msgTypeList;
    private List<String> p2pList;
    private List<String> senderList;
    private List<String> teamList;
    private final long toTime;
    private int msgLimit = 20;
    private SearchOrderEnum order = SearchOrderEnum.DESC;

    public MsgTimingFullKeywordSearchConfig(String str, long j, long j2) {
        this.keyword = str;
        this.fromTime = j;
        this.toTime = j2;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public long getFromTime() {
        return this.fromTime;
    }

    public long getToTime() {
        return this.toTime;
    }

    public int getMsgLimit() {
        return this.msgLimit;
    }

    public void setMsgLimit(int i) {
        this.msgLimit = i;
    }

    public SearchOrderEnum getOrder() {
        return this.order;
    }

    public void setOrder(SearchOrderEnum searchOrderEnum) {
        this.order = searchOrderEnum;
    }

    public List<String> getP2pList() {
        return this.p2pList;
    }

    public void setP2pList(List<String> list) {
        this.p2pList = list;
    }

    public List<String> getTeamList() {
        return this.teamList;
    }

    public void setTeamList(List<String> list) {
        this.teamList = list;
    }

    public List<String> getSenderList() {
        return this.senderList;
    }

    public void setSenderList(List<String> list) {
        this.senderList = list;
    }

    public List<MsgTypeEnum> getMsgTypeList() {
        return this.msgTypeList;
    }

    public void setMsgTypeList(List<MsgTypeEnum> list) {
        this.msgTypeList = list;
    }

    public List<Integer> getMsgSubtypeList() {
        return this.msgSubtypeList;
    }

    public void setMsgSubtypeList(List<Integer> list) {
        this.msgSubtypeList = list;
    }

    public String toString() {
        return "MsgFullKeywordSearchConfig{keyword='" + this.keyword + "', fromTime=" + this.fromTime + ", toTime=" + this.toTime + ", msgLimit=" + this.msgLimit + ", order=" + this.order + ", p2pList=" + this.p2pList + ", teamList=" + this.teamList + ", senderList=" + this.senderList + ", msgTypeList=" + this.msgTypeList + ", msgSubtypeList=" + this.msgSubtypeList + '}';
    }
}
