package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOption;
import com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOptionWrapper;
import java.util.ArrayList;

/* compiled from: MsgPinSyncResponseOptionWrapperImpl.java */
/* loaded from: classes.dex */
public class p implements MsgPinSyncResponseOptionWrapper {
    private final long a;
    private final boolean b;
    private final ArrayList<MsgPinSyncResponseOption> c;

    public p(long j, boolean z, ArrayList<o> arrayList) {
        this.a = j;
        this.b = z;
        this.c = new ArrayList<>(arrayList);
    }

    @Override // com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOptionWrapper
    public long getTime() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOptionWrapper
    public boolean isChanged() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOptionWrapper
    public ArrayList<MsgPinSyncResponseOption> getMsgPinInfoList() {
        return this.c;
    }
}
