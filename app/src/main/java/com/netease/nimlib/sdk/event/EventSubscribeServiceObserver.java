package com.netease.nimlib.sdk.event;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.event.model.Event;
import java.util.List;

@d
/* loaded from: classes.dex */
public interface EventSubscribeServiceObserver {
    void observeEventChanged(Observer<List<Event>> observer, boolean z);
}
