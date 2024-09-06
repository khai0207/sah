package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class q implements Handler.Callback, Runnable {
    private com.unionpay.mobile.android.net.d a;
    private Handler b;
    private WeakReference<a> c;
    private Context d;

    /* loaded from: classes.dex */
    public interface a {
        void a(int i, byte[] bArr);
    }

    public q(Context context, String str, a aVar) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = new com.unionpay.mobile.android.net.d(0, str, null);
        this.b = new Handler(this);
        this.c = new WeakReference<>(aVar);
        this.d = context;
    }

    public final void a() {
        new Thread(this).start();
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        WeakReference<a> weakReference;
        if (message.what != 0 || (weakReference = this.c) == null || weakReference.get() == null) {
            return true;
        }
        this.c.get().a(message.arg1, message.obj != null ? (byte[]) message.obj : null);
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.unionpay.mobile.android.net.c cVar = new com.unionpay.mobile.android.net.c(this.a, this.d);
        int a2 = cVar.a();
        Handler handler = this.b;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 0;
            obtainMessage.arg1 = a2;
            obtainMessage.obj = cVar.b();
            this.b.sendMessage(obtainMessage);
        }
    }
}
