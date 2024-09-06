package com.android.pc.ioc.event;

/* loaded from: classes.dex */
class AsyncPoster implements Runnable {
    private final EventBus eventBus;
    private final PendingPostQueue queue = new PendingPostQueue();

    AsyncPoster(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void enqueue(Subscription subscription, Object obj) {
        this.queue.enqueue(PendingPost.obtainPendingPost(subscription, obj));
        EventBus.executorService.execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        PendingPost poll = this.queue.poll();
        if (poll == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.eventBus.invokeSubscriber(poll);
    }
}
