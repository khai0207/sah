package com.netease.nimlib.sdk.event;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.event.model.Event;
import com.netease.nimlib.sdk.event.model.EventSubscribeRequest;
import com.netease.nimlib.sdk.event.model.EventSubscribeResult;
import java.util.List;

/* loaded from: classes.dex */
public interface EventSubscribeService {
    InvocationFuture<Void> batchUnSubscribeEvent(EventSubscribeRequest eventSubscribeRequest);

    InvocationFuture<Event> publishEvent(Event event);

    InvocationFuture<List<EventSubscribeResult>> querySubscribeEvent(EventSubscribeRequest eventSubscribeRequest);

    InvocationFuture<List<String>> subscribeEvent(EventSubscribeRequest eventSubscribeRequest);

    InvocationFuture<List<String>> unSubscribeEvent(EventSubscribeRequest eventSubscribeRequest);
}
