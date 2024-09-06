package com.netease.nimlib.sdk;

import com.netease.nimlib.sdk.msg.model.IMMessage;

/* loaded from: classes.dex */
public interface StatusBarNotificationFilter {

    /* loaded from: classes.dex */
    public enum FilterPolicy {
        PERMIT,
        DEFAULT,
        DENY
    }

    FilterPolicy apply(IMMessage iMMessage);
}
