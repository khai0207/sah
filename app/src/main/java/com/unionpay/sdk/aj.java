package com.unionpay.sdk;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes.dex */
final class aj {
    private static Handler a;
    private static final HandlerThread b;

    static {
        HandlerThread handlerThread = new HandlerThread("ProcessingThread");
        b = handlerThread;
        handlerThread.start();
        a = new ak(b.getLooper());
    }

    static final Handler a() {
        return a;
    }
}
