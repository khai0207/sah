package com.unionpay.mobile.android.widgets;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class aq extends Thread {
    final /* synthetic */ int a;
    final /* synthetic */ ao b;

    aq(ao aoVar, int i) {
        this.b = aoVar;
        this.a = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Handler handler;
        Handler handler2;
        int i;
        Handler handler3;
        Handler handler4;
        handler = this.b.p;
        if (handler != null) {
            long currentTimeMillis = System.currentTimeMillis() + (this.a * 1000);
            while (true) {
                long currentTimeMillis2 = System.currentTimeMillis();
                if (currentTimeMillis2 > currentTimeMillis || (i = (int) (((currentTimeMillis - currentTimeMillis2) + this.a) / 1000)) <= 0) {
                    break;
                }
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.arg1 = i;
                handler3 = this.b.p;
                handler3.sendMessage(obtain);
                try {
                    sleep(1000L);
                } catch (InterruptedException unused) {
                    handler4 = this.b.p;
                    handler4.sendEmptyMessage(1);
                    return;
                }
            }
            handler2 = this.b.p;
            handler2.sendEmptyMessage(1);
        }
    }
}
