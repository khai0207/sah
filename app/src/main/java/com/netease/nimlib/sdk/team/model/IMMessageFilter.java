package com.netease.nimlib.sdk.team.model;

import com.netease.nimlib.sdk.msg.model.IMMessage;

/* loaded from: classes.dex */
public interface IMMessageFilter {
    boolean shouldIgnore(IMMessage iMMessage);
}
