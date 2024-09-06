package com.unionpay.mobile.android.hce;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class h extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    h(f fVar, String str, String str2) {
        this.c = fVar;
        this.a = str;
        this.b = str2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Handler handler;
        Handler handler2;
        int i;
        f.a(this.c, this.a, this.b);
        handler = this.c.y;
        Message obtainMessage = handler.obtainMessage(2006, this.a);
        handler2 = this.c.y;
        i = this.c.i;
        handler2.sendMessageDelayed(obtainMessage, i);
    }
}
