package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public interface ThreadTalkHistory extends Serializable {
    int getReplyAmount();

    List<IMMessage> getReplyList();

    IMMessage getThread();

    long getTime();
}
