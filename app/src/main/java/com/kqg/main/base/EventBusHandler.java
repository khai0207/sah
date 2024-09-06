package com.kqg.main.base;

import com.kqg.main.model.AsyncMessage;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.MainThreadMessage;
import com.kqg.main.model.Message;

/* loaded from: classes.dex */
public interface EventBusHandler {
    void onEvent(Message message);

    void onEventAsync(AsyncMessage asyncMessage);

    void onEventBackgroundThread(BackGroundMessage backGroundMessage);

    void onEventMainThread(MainThreadMessage mainThreadMessage);
}
