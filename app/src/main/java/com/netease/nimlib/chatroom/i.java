package com.netease.nimlib.chatroom;

import android.os.Handler;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ChatRoomReconnectTask.java */
/* loaded from: classes.dex */
class i implements Runnable {
    private final String a;
    private AtomicInteger b;
    private final Handler c;

    i(String str, Handler handler) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        this.b = atomicInteger;
        this.c = handler;
        this.a = str;
        atomicInteger.set(0);
    }

    public void a() {
        b();
        this.b.set(0);
    }

    void b() {
        Handler handler = this.c;
        if (handler != null) {
            handler.removeCallbacks(this);
        }
    }

    boolean c() {
        if (!d() || !f() || !g()) {
            return false;
        }
        int h = (h() * 1000) + 1000;
        this.c.postDelayed(this, h);
        com.netease.nimlib.log.b.g("schedule room reconnect task, room id=" + this.a + ", delay=" + h);
        return true;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.removeCallbacks(this);
        if (d() && e()) {
            com.netease.nimlib.log.b.g("do room reconnect, room id=" + this.a + ", reconnect count=" + i());
            EnterChatRoomData k = c.a().k(this.a);
            com.netease.nimlib.n.c.a().a(this.a, k == null ? null : k.getAccount(), true);
            d.e().d(this.a);
        }
    }

    private boolean d() {
        if (!c.a().l(this.a) && com.netease.nimlib.h.e().shouldReLogin()) {
            com.netease.nimlib.log.b.g("cancel room reconnect as SDK should relogin, room id=" + this.a);
            return false;
        }
        if (d.e().d()) {
            return true;
        }
        com.netease.nimlib.log.b.g("cancel room reconnect as network is unavailable in INDEPENDENT MODE, room id=" + this.a);
        return false;
    }

    private boolean e() {
        StatusCode e = c.a().e(this.a);
        if (e != null && e.shouldReLogin()) {
            return true;
        }
        com.netease.nimlib.log.b.g("cancel room reconnect, as room status is " + e + ", room id=" + this.a);
        return false;
    }

    private boolean f() {
        if (h() < 20) {
            return true;
        }
        com.netease.nimlib.log.b.g("cancel room reconnect, as reconnect count over limit, room id=" + this.a);
        return false;
    }

    private boolean g() {
        if (this.c != null) {
            return true;
        }
        com.netease.nimlib.log.b.g("cancel room reconnect, as handler is null, room id=" + this.a);
        return false;
    }

    private int h() {
        return this.b.get();
    }

    private int i() {
        return this.b.addAndGet(1);
    }
}
