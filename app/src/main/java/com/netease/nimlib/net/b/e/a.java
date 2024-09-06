package com.netease.nimlib.net.b.e;

import com.netease.nimlib.log.b;
import com.netease.nimlib.net.b.c.f;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Utils.java */
/* loaded from: classes.dex */
public class a {
    static final ClosedChannelException a = new ClosedChannelException();
    static final NotYetConnectedException b = new NotYetConnectedException();

    public static void a(f fVar, Runnable runnable) {
        try {
            fVar.execute(runnable);
        } catch (RejectedExecutionException unused) {
            b.f("socket", "execute task in terminated event loog");
        }
    }

    public static Exception a(com.netease.nimlib.net.b.a.a aVar) {
        if (aVar.b().b()) {
            return null;
        }
        if (aVar.d()) {
            return b;
        }
        return a;
    }
}
