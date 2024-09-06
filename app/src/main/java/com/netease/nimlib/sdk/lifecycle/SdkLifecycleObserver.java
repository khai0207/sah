package com.netease.nimlib.sdk.lifecycle;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;

@d
/* loaded from: classes.dex */
public interface SdkLifecycleObserver {
    void observeMainProcessInitCompleteResult(Observer<Boolean> observer, boolean z);
}
