package com.netease.nimlib.biz.c.i;

import android.os.Handler;
import android.os.Looper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: TalkResponseHandler.java */
/* loaded from: classes.dex */
public class p extends com.netease.nimlib.biz.c.i {
    private Map<Short, com.netease.nimlib.biz.d.a> a = new ConcurrentHashMap();
    private final Handler b;

    public p() {
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            Looper.prepare();
            myLooper = Looper.myLooper();
        }
        if (myLooper != null) {
            this.b = new Handler(myLooper);
        } else {
            this.b = com.netease.nimlib.c.b.a.c().a();
            com.netease.nimlib.log.b.N("myLooper stays null after prepare");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    @Override // com.netease.nimlib.biz.c.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.netease.nimlib.biz.e.a r25) {
        /*
            Method dump skipped, instructions count: 475
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.biz.c.i.p.a(com.netease.nimlib.biz.e.a):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread a(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("TalkResponseSingleThread");
        return thread;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(short s) {
        this.a.remove(Short.valueOf(s));
    }
}
