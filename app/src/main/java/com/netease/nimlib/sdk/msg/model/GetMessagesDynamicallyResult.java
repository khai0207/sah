package com.netease.nimlib.sdk.msg.model;

import java.util.List;

/* loaded from: classes.dex */
public interface GetMessagesDynamicallyResult {
    List<IMMessage> getMessages();

    boolean isReliable();
}
