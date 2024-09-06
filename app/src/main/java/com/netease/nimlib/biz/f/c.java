package com.netease.nimlib.biz.f;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.event.EventSubscribeService;
import com.netease.nimlib.sdk.event.model.Event;
import com.netease.nimlib.sdk.event.model.EventSubscribeRequest;
import com.netease.nimlib.sdk.event.model.EventSubscribeResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* compiled from: EventSubscribeServiceRemote.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.i.j implements EventSubscribeService {
    @Override // com.netease.nimlib.sdk.event.EventSubscribeService
    public InvocationFuture<Event> publishEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("invalid params!");
        }
        com.netease.nimlib.biz.d.a.b bVar = new com.netease.nimlib.biz.d.a.b(event);
        bVar.a(b());
        com.netease.nimlib.biz.i.a().a(bVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.event.EventSubscribeService
    public InvocationFuture<List<String>> subscribeEvent(EventSubscribeRequest eventSubscribeRequest) {
        a(eventSubscribeRequest, true);
        return null;
    }

    @Override // com.netease.nimlib.sdk.event.EventSubscribeService
    public InvocationFuture<List<String>> unSubscribeEvent(EventSubscribeRequest eventSubscribeRequest) {
        if (eventSubscribeRequest == null) {
            throw new IllegalArgumentException("invalid params!");
        }
        a(eventSubscribeRequest, false);
        return null;
    }

    @Override // com.netease.nimlib.sdk.event.EventSubscribeService
    public InvocationFuture<Void> batchUnSubscribeEvent(EventSubscribeRequest eventSubscribeRequest) {
        if (eventSubscribeRequest == null) {
            throw new IllegalArgumentException("invalid params!");
        }
        com.netease.nimlib.biz.d.a.a aVar = new com.netease.nimlib.biz.d.a.a(eventSubscribeRequest);
        aVar.a(b());
        com.netease.nimlib.biz.i.a().a(aVar);
        return null;
    }

    private void a(EventSubscribeRequest eventSubscribeRequest, boolean z) {
        if (eventSubscribeRequest == null || eventSubscribeRequest.getPublishers() == null || eventSubscribeRequest.getPublishers().isEmpty()) {
            throw new IllegalArgumentException("invalid params!");
        }
        ArrayList arrayList = new ArrayList(new HashSet(eventSubscribeRequest.getPublishers()));
        final com.netease.nimlib.i.k b = b();
        if (arrayList.size() <= 100) {
            com.netease.nimlib.biz.d.a.d dVar = new com.netease.nimlib.biz.d.a.d(eventSubscribeRequest, arrayList, z);
            dVar.a(b);
            com.netease.nimlib.biz.i.a().a(dVar);
            return;
        }
        a(eventSubscribeRequest, arrayList, z, new ArrayList<>(), new RequestCallback<ArrayList<String>>() { // from class: com.netease.nimlib.biz.f.c.1
            @Override // com.netease.nimlib.sdk.RequestCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ArrayList<String> arrayList2) {
                com.netease.nimlib.i.k kVar = b;
                if (kVar != null) {
                    kVar.b(arrayList2).b();
                }
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
                com.netease.nimlib.i.k kVar = b;
                if (kVar != null) {
                    kVar.a(i).b();
                }
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
                com.netease.nimlib.i.k kVar = b;
                if (kVar != null) {
                    kVar.a(th).b();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final EventSubscribeRequest eventSubscribeRequest, final List<String> list, final boolean z, final ArrayList<String> arrayList, final RequestCallback<ArrayList<String>> requestCallback) {
        if (eventSubscribeRequest == null || list == null || list.isEmpty()) {
            return;
        }
        final boolean z2 = list.size() > 100;
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.a.d(eventSubscribeRequest, list.subList(0, z2 ? 100 : list.size()), z)) { // from class: com.netease.nimlib.biz.f.c.2
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    ArrayList<String> a = ((com.netease.nimlib.biz.e.a.f) aVar).a();
                    if (a != null && !a.isEmpty()) {
                        arrayList.addAll(a);
                    }
                    if (z2) {
                        c cVar = c.this;
                        EventSubscribeRequest eventSubscribeRequest2 = eventSubscribeRequest;
                        List list2 = list;
                        cVar.a(eventSubscribeRequest2, (List<String>) list2.subList(100, list2.size()), z, (ArrayList<String>) arrayList, (RequestCallback<ArrayList<String>>) requestCallback);
                        return;
                    }
                    RequestCallback requestCallback2 = requestCallback;
                    if (requestCallback2 != null) {
                        requestCallback2.onSuccess(arrayList);
                        return;
                    }
                    return;
                }
                RequestCallback requestCallback3 = requestCallback;
                if (requestCallback3 != null) {
                    requestCallback3.onFailed(aVar.r());
                }
            }
        });
    }

    @Override // com.netease.nimlib.sdk.event.EventSubscribeService
    public InvocationFuture<List<EventSubscribeResult>> querySubscribeEvent(EventSubscribeRequest eventSubscribeRequest) {
        if (eventSubscribeRequest == null || eventSubscribeRequest.getPublishers() == null || eventSubscribeRequest.getPublishers().isEmpty()) {
            throw new IllegalArgumentException("invalid params!");
        }
        ArrayList arrayList = new ArrayList(new HashSet(eventSubscribeRequest.getPublishers()));
        final com.netease.nimlib.i.k b = b();
        a(eventSubscribeRequest, arrayList, new ArrayList<>(), new RequestCallback<ArrayList<EventSubscribeResult>>() { // from class: com.netease.nimlib.biz.f.c.3
            @Override // com.netease.nimlib.sdk.RequestCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ArrayList<EventSubscribeResult> arrayList2) {
                com.netease.nimlib.i.k kVar = b;
                if (kVar != null) {
                    kVar.b(arrayList2).b();
                }
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
                com.netease.nimlib.i.k kVar = b;
                if (kVar != null) {
                    kVar.a(i).b();
                }
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
                com.netease.nimlib.i.k kVar = b;
                if (kVar != null) {
                    kVar.a(th).b();
                }
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final EventSubscribeRequest eventSubscribeRequest, final List<String> list, final ArrayList<EventSubscribeResult> arrayList, final RequestCallback<ArrayList<EventSubscribeResult>> requestCallback) {
        if (eventSubscribeRequest == null || list == null || list.isEmpty()) {
            return;
        }
        final boolean z = list.size() > 100;
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.a.c(eventSubscribeRequest, list.subList(0, z ? 100 : list.size()))) { // from class: com.netease.nimlib.biz.f.c.4
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    ArrayList<EventSubscribeResult> a = ((com.netease.nimlib.biz.e.a.e) aVar).a();
                    if (a != null && !a.isEmpty()) {
                        arrayList.addAll(a);
                    }
                    if (z) {
                        c cVar = c.this;
                        EventSubscribeRequest eventSubscribeRequest2 = eventSubscribeRequest;
                        List list2 = list;
                        cVar.a(eventSubscribeRequest2, list2.subList(100, list2.size()), arrayList, requestCallback);
                        return;
                    }
                    RequestCallback requestCallback2 = requestCallback;
                    if (requestCallback2 != null) {
                        requestCallback2.onSuccess(arrayList);
                        return;
                    }
                    return;
                }
                RequestCallback requestCallback3 = requestCallback;
                if (requestCallback3 != null) {
                    requestCallback3.onFailed(aVar.r());
                }
            }
        });
    }
}
