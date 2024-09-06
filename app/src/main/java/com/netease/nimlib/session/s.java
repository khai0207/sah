package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.model.RecentSession;
import com.netease.nimlib.sdk.msg.model.RecentSessionList;
import java.util.List;

/* compiled from: RecentSessionListImpl.java */
/* loaded from: classes.dex */
public class s implements RecentSessionList {
    private final boolean a;
    private final List<RecentSession> b;

    public s(boolean z, List<RecentSession> list) {
        this.a = z;
        this.b = list;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentSessionList
    public boolean hasMore() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentSessionList
    public List<RecentSession> getSessionList() {
        return this.b;
    }
}
