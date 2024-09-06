package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.ThreadTalkHistory;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ThreadTalkHistoryImpl.java */
/* loaded from: classes.dex */
public class ab implements ThreadTalkHistory {
    private final IMMessageImpl a;
    private final long b;
    private final int c;
    private final List<IMMessage> d;

    public ab(IMMessageImpl iMMessageImpl, long j, int i, List<IMMessageImpl> list) {
        this.a = iMMessageImpl;
        this.b = j;
        this.c = i;
        this.d = new ArrayList(list);
    }

    @Override // com.netease.nimlib.sdk.msg.model.ThreadTalkHistory
    public IMMessage getThread() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.ThreadTalkHistory
    public long getTime() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.msg.model.ThreadTalkHistory
    public int getReplyAmount() {
        return this.c;
    }

    @Override // com.netease.nimlib.sdk.msg.model.ThreadTalkHistory
    public List<IMMessage> getReplyList() {
        return this.d;
    }
}
