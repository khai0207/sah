package com.ipaynow.wechatpay.plugin.d.b;

import android.os.Looper;
import android.widget.Toast;

/* loaded from: classes.dex */
final class b extends Thread {
    b() {
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        if (com.ipaynow.wechatpay.plugin.manager.a.a.r().getContext() != null) {
            Looper.prepare();
            Toast.makeText(com.ipaynow.wechatpay.plugin.manager.a.a.r().getContext(), "程序异常", 1).show();
            Looper.loop();
        }
    }
}
