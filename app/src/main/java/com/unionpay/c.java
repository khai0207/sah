package com.unionpay;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class c extends Thread {
    c() {
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Handler handler;
        com.unionpay.a.d dVar;
        Context context;
        Handler handler2;
        Handler handler3;
        Handler handler4;
        Handler handler5;
        handler = UPPayAssistEx.E;
        handler.sendEmptyMessageDelayed(1001, 300L);
        dVar = UPPayAssistEx.D;
        context = UPPayAssistEx.t;
        com.unionpay.a.c cVar = new com.unionpay.a.c(dVar, context);
        cVar.a();
        String b = cVar.b();
        handler2 = UPPayAssistEx.E;
        if (handler2 != null) {
            handler3 = UPPayAssistEx.E;
            Message obtainMessage = handler3.obtainMessage();
            obtainMessage.what = 1002;
            obtainMessage.obj = b;
            handler4 = UPPayAssistEx.E;
            handler4.removeMessages(1001);
            handler5 = UPPayAssistEx.E;
            handler5.sendMessage(obtainMessage);
        }
    }
}
