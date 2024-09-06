package com.netease.nimlib.sdk.msg;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.model.SystemMessage;

@d
/* loaded from: classes.dex */
public interface SystemMessageObserver {
    void observeReceiveSystemMsg(Observer<SystemMessage> observer, boolean z);

    void observeUnreadCountChange(Observer<Integer> observer, boolean z);
}
