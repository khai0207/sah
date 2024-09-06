package com.netease.nimlib.sdk.passthrough;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.passthrough.model.PassthroughNotifyData;

@d
/* loaded from: classes.dex */
public interface PassthroughServiceObserve {
    void observePassthroughNotify(Observer<PassthroughNotifyData> observer, boolean z);
}
