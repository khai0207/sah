package com.netease.nimlib.apm.event.d;

import android.os.Handler;
import android.os.HandlerThread;

/* compiled from: EventReportExecutor.java */
/* loaded from: classes.dex */
public class a {
    private HandlerThread a;
    private Handler b;

    public a() {
        this.a = null;
        this.b = null;
        HandlerThread handlerThread = new HandlerThread("EventReportExecutor");
        this.a = handlerThread;
        handlerThread.start();
        this.b = new Handler(this.a.getLooper());
    }

    public void a(b bVar) {
        this.b.post(bVar);
    }

    public void a() {
        this.b.removeCallbacksAndMessages(null);
    }
}
