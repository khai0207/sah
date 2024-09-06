package com.talkingdata.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;

/* compiled from: td */
/* loaded from: classes.dex */
class bb extends Thread {
    final /* synthetic */ ba a;

    bb(ba baVar) {
        this.a = baVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Context context;
        BroadcastReceiver broadcastReceiver;
        try {
            synchronized (this.a.b) {
                try {
                    this.a.b.notifyAll();
                    context = this.a.c;
                    broadcastReceiver = this.a.a;
                } catch (Throwable unused) {
                    context = this.a.c;
                    broadcastReceiver = this.a.a;
                }
                context.unregisterReceiver(broadcastReceiver);
            }
        } catch (Throwable unused2) {
        }
    }
}
