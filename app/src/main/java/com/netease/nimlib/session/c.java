package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyResult;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import java.util.List;

/* compiled from: GetMessagesDynamicallyResultImpl.java */
/* loaded from: classes.dex */
public class c implements GetMessagesDynamicallyResult {
    private final boolean a;
    private final List<IMMessage> b;

    public c(boolean z, List<IMMessage> list) {
        this.a = z;
        this.b = list;
    }

    @Override // com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyResult
    public boolean isReliable() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyResult
    public List<IMMessage> getMessages() {
        return this.b;
    }
}
