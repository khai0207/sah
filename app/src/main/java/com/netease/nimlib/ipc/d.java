package com.netease.nimlib.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: NimServiceConnection.java */
/* loaded from: classes.dex */
class d extends Handler implements ServiceConnection {
    private final Context a;
    private final Intent b;
    private final AtomicBoolean c;
    private final String d;
    private int e;

    d(Context context, Intent intent, String str) {
        super(Looper.getMainLooper());
        this.c = new AtomicBoolean();
        this.e = 15000;
        this.a = context.getApplicationContext();
        this.b = intent;
        this.d = str;
        if (com.netease.nimlib.c.i().coreProcessStartTimeout > 0) {
            this.e = com.netease.nimlib.c.i().coreProcessStartTimeout;
        }
    }

    protected void a(IBinder iBinder) {
        com.netease.nimlib.log.b.d(this.d, "onConnected");
    }

    protected void a() {
        com.netease.nimlib.log.b.d(this.d, "onDisconnected");
    }

    public final void b() {
        if (!this.c.compareAndSet(false, true)) {
            com.netease.nimlib.log.b.c(this.d, "connect: connecting...");
        } else {
            b(0);
        }
    }

    public final void c() {
        try {
            this.a.unbindService(this);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.netease.nimlib.log.b.d(this.d, "onServiceConnected: binder#" + iBinder);
        if (iBinder == null) {
            return;
        }
        removeMessages(1);
        this.c.set(false);
        a(iBinder);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        com.netease.nimlib.log.b.d(this.d, "onServiceDisconnected#" + componentName.getClassName());
        a();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 1) {
            a(message.arg1);
        }
    }

    void a(int i) {
        com.netease.nimlib.log.b.d(this.d, "doTimeout: count#" + i);
        if (i >= 1) {
            this.c.set(false);
        } else {
            b(i + 1);
        }
    }

    private void b(int i) {
        try {
            c(i);
        } catch (Throwable th) {
            removeMessages(1);
            this.c.set(false);
            com.netease.nimlib.log.b.d(this.d, "connect core error: " + th);
        }
    }

    private void c(int i) {
        com.netease.nimlib.log.b.d(this.d, "doConnect: tag#" + this.d + " count#" + i);
        if (i > 0) {
            com.netease.nimlib.log.b.e(this.d, "doConnect: unbind & stop service#" + this.b);
            try {
                this.a.unbindService(this);
                this.a.stopService(this.b);
            } catch (Throwable th) {
                com.netease.nimlib.log.b.d(this.d, "unbindService or stopService error: " + th);
            }
        }
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.arg1 = i;
        com.netease.nimlib.log.b.d(this.d, "doConnect: start & bind service#" + this.b);
        sendMessageDelayed(obtain, (long) this.e);
        this.a.startService(this.b);
        if (this.a.bindService(this.b, this, 1)) {
            return;
        }
        com.netease.nimlib.log.b.e(this.d, "doConnect: stop & bind service#" + this.b);
        this.a.stopService(this.b);
        this.a.bindService(this.b, this, 1);
    }

    public boolean d() {
        return this.c.get();
    }
}
