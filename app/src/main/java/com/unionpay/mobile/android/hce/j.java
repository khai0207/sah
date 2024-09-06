package com.unionpay.mobile.android.hce;

import android.os.Bundle;
import android.os.Handler;
import com.kqg.main.constant.KV;

/* loaded from: classes.dex */
final class j implements Runnable {
    final /* synthetic */ f a;

    j(f fVar) {
        this.a = fVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        int i;
        int i2;
        Bundle a;
        Handler handler;
        Handler handler2;
        f fVar = this.a;
        str = fVar.l;
        i = this.a.f;
        i2 = this.a.h;
        a = fVar.a(str, i, i2);
        f.k(this.a);
        handler = this.a.y;
        handler2 = this.a.y;
        if (a == null) {
            a = null;
        }
        handler.sendMessage(handler2.obtainMessage(KV.INIT_ERROR, a));
    }
}
