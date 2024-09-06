package com.netease.nimlib.plugin.interact;

import com.netease.nimlib.i.k;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.qchat.param.QChatPushConfigParam;

/* loaded from: classes.dex */
public interface IQChatInteract extends a {
    StatusCode getStatus();

    void updatePushConfig(k kVar, QChatPushConfigParam qChatPushConfigParam);

    void updatePushToken(String str, String str2, com.netease.nimlib.plugin.b bVar);
}
