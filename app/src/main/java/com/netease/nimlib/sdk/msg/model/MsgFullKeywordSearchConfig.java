package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import java.util.List;

/* loaded from: classes.dex */
public class MsgFullKeywordSearchConfig {
    private boolean asc = false;
    private final long fromTime;
    private final String keyword;
    private int msgLimit;
    private List<Integer> msgSubtypeList;
    private List<MsgTypeEnum> msgTypeList;
    private List<String> p2pList;
    private List<String> senderList;
    private int sessionLimit;
    private List<String> teamList;
    private final long toTime;

    public MsgFullKeywordSearchConfig(String str, long j, long j2) {
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

    public int getSessionLimit() {
        return this.sessionLimit;
    }

    public void setSessionLimit(int i) {
        this.sessionLimit = i;
    }

    public int getMsgLimit() {
        return this.msgLimit;
    }

    public void setMsgLimit(int i) {
        this.msgLimit = i;
    }

    public boolean isAsc() {
        return this.asc;
    }

    public void setAsc(boolean z) {
        this.asc = z;
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
        return "MsgFullKeywordSearchConfig{keyword='" + this.keyword + "', fromTime=" + this.fromTime + ", toTime=" + this.toTime + ", sessionLimit=" + this.sessionLimit + ", msgLimit=" + this.msgLimit + ", asc=" + this.asc + ", p2pList=" + this.p2pList + ", teamList=" + this.teamList + ", senderList=" + this.senderList + ", msgTypeList=" + this.msgTypeList + ", msgSubtypeList=" + this.msgSubtypeList + '}';
    }
}
