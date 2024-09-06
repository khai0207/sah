package com.netease.nimlib.sdk.msg.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public interface RecentSessionList extends Serializable {
    List<RecentSession> getSessionList();

    boolean hasMore();
}
