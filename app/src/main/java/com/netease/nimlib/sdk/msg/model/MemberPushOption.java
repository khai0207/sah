package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public class MemberPushOption implements Serializable {
    private List<String> forcePushList = null;
    private String forcePushContent = null;
    private boolean isForcePush = true;

    public List<String> getForcePushList() {
        return this.forcePushList;
    }

    public String getForcePushContent() {
        return this.forcePushContent;
    }

    public boolean isForcePush() {
        return this.isForcePush;
    }

    public void setForcePush(boolean z) {
        this.isForcePush = z;
    }

    public void setForcePushList(List<String> list) {
        this.forcePushList = list;
    }

    public void setForcePushContent(String str) {
        this.forcePushContent = str;
    }
}
