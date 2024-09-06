package com.netease.nimlib.sdk.qchat.param;

import com.netease.nimlib.sdk.qchat.enums.QChatPushMsgType;

/* loaded from: classes.dex */
public class QChatPushConfigParam {
    private Boolean isNoDisturbOpen;
    private Boolean isPushShowNoDetail;
    private QChatPushMsgType pushMsgType;
    private String startNoDisturbTime;
    private String stopNoDisturbTime;

    public QChatPushConfigParam(Boolean bool, Boolean bool2, String str, String str2, QChatPushMsgType qChatPushMsgType) {
        this.isPushShowNoDetail = bool;
        this.isNoDisturbOpen = bool2;
        this.startNoDisturbTime = str;
        this.stopNoDisturbTime = str2;
        this.pushMsgType = qChatPushMsgType;
    }

    public QChatPushConfigParam(Boolean bool, String str, String str2) {
        this.isNoDisturbOpen = bool;
        this.startNoDisturbTime = str;
        this.stopNoDisturbTime = str2;
    }

    public QChatPushConfigParam(Boolean bool) {
        this.isPushShowNoDetail = bool;
    }

    public QChatPushConfigParam(QChatPushMsgType qChatPushMsgType) {
        this.pushMsgType = qChatPushMsgType;
    }

    public Boolean getPushShowNoDetail() {
        return this.isPushShowNoDetail;
    }

    public Boolean getNoDisturbOpen() {
        return this.isNoDisturbOpen;
    }

    public String getStartNoDisturbTime() {
        return this.startNoDisturbTime;
    }

    public String getStopNoDisturbTime() {
        return this.stopNoDisturbTime;
    }

    public QChatPushMsgType getPushMsgType() {
        return this.pushMsgType;
    }

    public void setPushShowNoDetail(Boolean bool) {
        this.isPushShowNoDetail = bool;
    }

    public void setNoDisturbOpen(Boolean bool) {
        this.isNoDisturbOpen = bool;
    }

    public void setStartNoDisturbTime(String str) {
        this.startNoDisturbTime = str;
    }

    public void setStopNoDisturbTime(String str) {
        this.stopNoDisturbTime = str;
    }

    public void setPushMsgType(QChatPushMsgType qChatPushMsgType) {
        this.pushMsgType = qChatPushMsgType;
    }
}
