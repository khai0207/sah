package com.netease.nimlib.chatroom;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SerialGenerator.java */
/* loaded from: classes.dex */
public class p {
    private static AtomicInteger a = new AtomicInteger(1);

    public static short a() {
        short addAndGet = (short) a.addAndGet(1);
        if (addAndGet >= 2) {
            return addAndGet;
        }
        a.set(2);
        return (short) 2;
    }
}
