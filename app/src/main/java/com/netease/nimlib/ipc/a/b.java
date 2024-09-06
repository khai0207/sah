package com.netease.nimlib.ipc.a;

import com.netease.nimlib.biz.a.a;
import com.netease.nimlib.push.a.b.g;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: IPCAckIdManager.java */
/* loaded from: classes.dex */
public final class b {
    private final Object a = new Object();
    private AtomicInteger b = new AtomicInteger(1);
    private List<Integer> c = new LinkedList();
    private boolean d = false;
    private int e;
    private long f;

    public static boolean a(com.netease.nimlib.push.packet.a aVar, com.netease.nimlib.push.packet.c.f fVar) {
        if (com.netease.nimlib.d.c.e().a() && aVar.i() == 4) {
            if (aVar.j() == 4) {
                return true;
            }
            if (aVar.j() == 1 && fVar != null) {
                try {
                    com.netease.nimlib.push.packet.c.f fVar2 = new com.netease.nimlib.push.packet.c.f(fVar.b().duplicate());
                    fVar2.h();
                    com.netease.nimlib.push.packet.a aVar2 = new com.netease.nimlib.push.packet.a();
                    aVar2.a(fVar2);
                    if (aVar2.i() == 8) {
                        if (aVar2.j() != 3) {
                            if (aVar2.j() == 4) {
                            }
                        }
                        return true;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    com.netease.nimlib.log.b.x("IPCAckIdManager should ipc ack check throw exception, header=" + aVar + ", e=" + th.getMessage());
                }
            }
        }
        return false;
    }

    public int a(com.netease.nimlib.push.packet.a aVar) {
        int incrementAndGet;
        synchronized (this.a) {
            incrementAndGet = this.b.incrementAndGet();
            this.c.add(Integer.valueOf(incrementAndGet));
            if (this.d && aVar.i() == 4 && aVar.j() == 4) {
                this.e = incrementAndGet;
                com.netease.nimlib.log.b.x("received sync unread response, record ack id=" + incrementAndGet);
            }
            com.netease.nimlib.log.b.x("Push wait ack id=" + incrementAndGet);
        }
        return incrementAndGet;
    }

    public boolean a(int i) {
        if (i <= 0) {
            return true;
        }
        synchronized (this.a) {
            if (b(i)) {
                return true;
            }
            Integer num = null;
            for (Integer num2 : this.c) {
                if (i > num2.intValue()) {
                    com.netease.nimlib.log.b.x("IPC ack handleIPCError!!! current ack id=" + i + ", remain waiting ack id=" + num2);
                    return false;
                }
                if (i == num2.intValue()) {
                    num = num2;
                }
            }
            if (num != null) {
                this.c.remove(num);
                com.netease.nimlib.log.b.x("UI ack id=" + num);
            }
            return true;
        }
    }

    private boolean b(int i) {
        if (!this.d || i != this.e) {
            return false;
        }
        com.netease.nimlib.log.b.x("handle sync ack id=" + i + ", waiting ack id list length=" + this.c.size());
        Iterator<Integer> it = this.c.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue <= i) {
                it.remove();
                com.netease.nimlib.log.b.x("remove invalid ack id=" + intValue);
            }
        }
        this.d = false;
        this.e = 0;
        com.netease.nimlib.log.b.x("IPC error handle done, now waiting ack id list length=" + this.c.size());
        return true;
    }

    public boolean a() {
        boolean z;
        synchronized (this.a) {
            z = this.d && System.currentTimeMillis() - this.f <= 30000;
        }
        return z;
    }

    public void b() {
        synchronized (this.a) {
            com.netease.nimlib.log.b.x("begin handle ipc error...");
            this.d = true;
            this.f = System.currentTimeMillis();
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            cVar.a(a.EnumC0026a.UNREAD_MESSAGE.a(), 0);
            g gVar = new g();
            gVar.a(cVar);
            com.netease.nimlib.push.f.l().a(gVar);
            com.netease.nimlib.log.b.x("send sync unread request when ipc error");
        }
    }

    public static b c() {
        return a.a;
    }

    /* compiled from: IPCAckIdManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final b a = new b();
    }
}
