package com.iflytek.common.c;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
final class f implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    f() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "CommonTask#" + this.a.getAndIncrement());
        thread.setPriority(5);
        return thread;
    }
}
