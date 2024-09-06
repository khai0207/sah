package com.netease.nimlib.i.a.a;

import android.os.Looper;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.util.api.RequestResult;

/* compiled from: APISyncHelper.java */
/* loaded from: classes.dex */
public class a {
    public static <T> RequestResult<T> a(InvocationFuture<T> invocationFuture, long j) {
        if (j < 0 || j > 30000) {
            j = 30000;
        }
        com.netease.nimlib.i.a.b.b bVar = new com.netease.nimlib.i.a.b.b();
        if (Looper.myLooper() != null) {
            com.netease.nimlib.log.b.C("api sync request should not run on looper thread");
            bVar.a(1003);
            return bVar.b();
        }
        invocationFuture.setCallback(new com.netease.nimlib.i.a.b.a(bVar));
        com.netease.nimlib.i.a.c.a aVar = new com.netease.nimlib.i.a.c.a(bVar);
        long currentTimeMillis = System.currentTimeMillis();
        try {
            com.netease.nimlib.log.b.C("api sync request on thread=" + Thread.currentThread().getId() + ", max wait time=" + j);
            aVar.a(j);
            com.netease.nimlib.log.b.C("api sync request done on thread=" + Thread.currentThread().getId() + ", response code=" + bVar.b().code + ", cost time=" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (b unused) {
            bVar.a(1002);
            com.netease.nimlib.log.b.C("api sync request timeout! cost time=" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Throwable th) {
            bVar.a(1004);
            com.netease.nimlib.log.b.C("api sync request error, e=" + th.getMessage());
        }
        return bVar.b();
    }
}
