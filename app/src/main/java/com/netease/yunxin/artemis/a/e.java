package com.netease.yunxin.artemis.a;

import java.lang.Thread;

/* compiled from: ExceptionHandler.java */
/* loaded from: classes.dex */
public final class e implements Thread.UncaughtExceptionHandler {
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        if (th == null || th.getMessage() == null) {
            return;
        }
        f.a("artemis throw exception:".concat(String.valueOf(th)));
    }
}
