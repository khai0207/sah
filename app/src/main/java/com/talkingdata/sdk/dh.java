package com.talkingdata.sdk;

import android.os.SystemClock;

/* compiled from: td */
/* loaded from: classes.dex */
public class dh extends da {
    private int a = 0;

    public dh() {
        a("bootTime", Long.valueOf(System.currentTimeMillis() - SystemClock.elapsedRealtime()));
        a("activeTime", Long.valueOf(SystemClock.elapsedRealtime()));
        a("freeDiskSpace", Integer.valueOf(b()));
    }

    private int b() {
        try {
            return au.p()[1];
        } catch (Throwable unused) {
            return 0;
        }
    }
}
