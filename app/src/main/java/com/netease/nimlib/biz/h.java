package com.netease.nimlib.biz;

import com.netease.nimlib.sdk.ResponseCode;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SerialIdGenerator.java */
/* loaded from: classes.dex */
public class h {
    private static AtomicInteger a = new AtomicInteger(1);

    public static short a(boolean z) {
        int addAndGet = a.addAndGet(1);
        if (!z || addAndGet < 1000) {
            return (z || (addAndGet >= 1000 && addAndGet <= 32767)) ? (short) addAndGet : a(addAndGet, ResponseCode.RES_EXCEPTION, false);
        }
        return a(addAndGet, (short) 2, true);
    }

    private static short a(int i, short s, boolean z) {
        return a.compareAndSet(i, s) ? s : a(z);
    }
}
