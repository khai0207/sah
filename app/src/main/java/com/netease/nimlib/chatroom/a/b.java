package com.netease.nimlib.chatroom.a;

import android.text.TextUtils;
import com.netease.nimlib.chatroom.e;
import com.netease.nimlib.chatroom.l;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.chatroom.model.CdnRequestData;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomCdnInfo;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: CdnHandlerManager.java */
/* loaded from: classes.dex */
public class b {
    private static final b f = new b();
    private final ConcurrentHashMap<String, a> a = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, com.netease.nimlib.chatroom.model.a> b = new ConcurrentHashMap<>();
    private final AtomicInteger c = new AtomicInteger(0);
    private final AtomicInteger d = new AtomicInteger(0);
    private ScheduledExecutorService e;

    public synchronized a a(String str, c cVar) {
        com.netease.nimlib.log.b.d("CdnHandlerManager", "to create or update, roomId=" + str);
        if (!TextUtils.isEmpty(str) && cVar != null) {
            a aVar = this.a.get(str);
            if (aVar != null) {
                ChatRoomCdnInfo d = aVar.d();
                a(d, cVar);
                aVar.a(d);
                return aVar;
            }
            a aVar2 = new a(str, e.c(cVar)) { // from class: com.netease.nimlib.chatroom.a.b.1
                @Override // com.netease.nimlib.chatroom.a.a
                synchronized void a(String str2, boolean z, int i) {
                    if (TextUtils.isEmpty(str2)) {
                        return;
                    }
                    com.netease.nimlib.chatroom.model.a aVar3 = (com.netease.nimlib.chatroom.model.a) b.this.b.get(str2);
                    if (aVar3 == null) {
                        aVar3 = new com.netease.nimlib.chatroom.model.a();
                        b.this.b.put(str2, aVar3);
                    }
                    aVar3.a(z, i);
                }

                @Override // com.netease.nimlib.chatroom.a.a
                synchronized void e() {
                    b.this.d.incrementAndGet();
                }

                @Override // com.netease.nimlib.chatroom.a.a
                synchronized void a(boolean z) {
                    int incrementAndGet = z ? b.this.c.incrementAndGet() : b.this.c.decrementAndGet();
                    if (incrementAndGet == 0) {
                        b.this.c();
                        b.this.c.set(0);
                    } else if (incrementAndGet > 0) {
                        b.this.b();
                    } else {
                        com.netease.nimlib.log.b.e("CdnHandlerManager", "polling amount less than 0, pollingAmount=" + incrementAndGet);
                        b.this.c();
                    }
                }
            };
            this.a.put(str, aVar2);
            return aVar2;
        }
        com.netease.nimlib.log.b.d("CdnHandlerManager", "cancel create or update");
        return null;
    }

    private void a(ChatRoomCdnInfo chatRoomCdnInfo, c cVar) {
        if (cVar.f(1)) {
            chatRoomCdnInfo.setEnable(cVar.d(1) > 0);
        }
        if (cVar.f(2)) {
            String c = cVar.c(2);
            if (!TextUtils.isEmpty(c)) {
                chatRoomCdnInfo.setCdnUrlArray(c.split("\\|"));
            }
        }
        if (cVar.f(3)) {
            chatRoomCdnInfo.setTimestamp(cVar.e(3));
        } else {
            chatRoomCdnInfo.setTimestamp(0L);
        }
        if (cVar.f(4)) {
            chatRoomCdnInfo.setPollingInterval(cVar.d(4) * 1000);
        }
        if (cVar.f(6)) {
            chatRoomCdnInfo.setDecryptKey(cVar.c(6));
        }
        if (cVar.f(7)) {
            chatRoomCdnInfo.setTimeOut(cVar.d(7));
        }
    }

    public synchronized a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.a.get(str);
    }

    public synchronized void a() {
        for (a aVar : this.a.values()) {
            if (aVar != null) {
                aVar.c();
            }
        }
        this.a.clear();
        c();
    }

    public synchronized void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a aVar = this.a.get(str);
        if (aVar == null) {
            return;
        }
        aVar.c();
        this.a.remove(str);
        if (this.a.isEmpty()) {
            c();
        }
    }

    public void b() {
        ScheduledExecutorService scheduledExecutorService = this.e;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            int i = com.netease.nimlib.c.i().cdnRequestDataInterval;
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
            this.e = newScheduledThreadPool;
            long j = i;
            newScheduledThreadPool.scheduleAtFixedRate(new Runnable() { // from class: com.netease.nimlib.chatroom.a.b.2
                @Override // java.lang.Runnable
                public void run() {
                    b.this.e();
                }
            }, j, j, TimeUnit.MILLISECONDS);
        }
    }

    public void c() {
        ScheduledExecutorService scheduledExecutorService = this.e;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        try {
            this.e.shutdown();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        CdnRequestData cdnRequestData;
        synchronized (this.b) {
            cdnRequestData = new CdnRequestData(this.b, this.d.getAndSet(0));
            this.b.clear();
        }
        l.a(cdnRequestData);
    }

    private b() {
    }

    public static b d() {
        return f;
    }
}
