package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class n implements ThreadFactory {
    public final AtomicInteger a = new AtomicInteger(1);

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #" + this.a.getAndIncrement());
        thread.setPriority(4);
        return thread;
    }
}
