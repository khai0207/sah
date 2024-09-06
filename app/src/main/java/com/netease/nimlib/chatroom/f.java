package com.netease.nimlib.chatroom;

import android.os.Handler;
import android.text.TextUtils;
import com.netease.nimlib.o.y;
import com.netease.nimlib.session.IMMessageImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: ChatRoomMessageManager.java */
/* loaded from: classes.dex */
public class f {
    private final String a;
    private Handler f;
    private Queue<String> b = new ConcurrentLinkedQueue();
    private List<IMMessageImpl> c = Collections.synchronizedList(new ArrayList());
    private long d = 0;
    private boolean e = false;
    private Runnable g = new Runnable() { // from class: com.netease.nimlib.chatroom.f.2
        @Override // java.lang.Runnable
        public void run() {
            com.netease.nimlib.log.b.d("ChatRoomMessageManager", "notifyRunnable run");
            f.this.e().removeCallbacks(f.this.g);
            f.this.d();
            f.this.e = false;
        }
    };

    public f(String str) {
        this.a = str;
    }

    public synchronized void a() {
        b();
        c();
    }

    public void a(List<IMMessageImpl> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (IMMessageImpl iMMessageImpl : list) {
            if (this.b.size() >= 500) {
                this.b.poll();
            }
            this.b.add(iMMessageImpl.getUuid());
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.b.size() >= 500) {
            this.b.poll();
        }
        this.b.add(str);
    }

    public boolean b(String str) {
        return this.b.contains(str);
    }

    private void b() {
        this.b.clear();
    }

    private void c() {
        com.netease.nimlib.log.b.d("ChatRoomMessageManager", "clearMessages start");
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            f();
        }
        this.c.clear();
        this.d = 0L;
        this.e = false;
        com.netease.nimlib.log.b.d("ChatRoomMessageManager", "clearMessages end");
    }

    public void b(final List<IMMessageImpl> list) {
        if (list == null || list.isEmpty()) {
            com.netease.nimlib.log.b.f("ChatRoomMessageManager", "notifyChatRoomMessages messages is empty");
            return;
        }
        com.netease.nimlib.log.b.d("ChatRoomMessageManager", "notifyChatRoomMessages message.size = " + list.size());
        e().post(new Runnable() { // from class: com.netease.nimlib.chatroom.f.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    com.netease.nimlib.log.b.d("ChatRoomMessageManager", "notifyChatRoomMessages cache message");
                    f.this.c.addAll(list);
                    if (y.a() - f.this.d >= 300) {
                        com.netease.nimlib.log.b.d("ChatRoomMessageManager", "notifyChatRoomMessages >= FREQUENCY");
                        f.this.d();
                        return;
                    }
                    com.netease.nimlib.log.b.d("ChatRoomMessageManager", "notifyChatRoomMessages hasPostDelay = " + f.this.e);
                    if (f.this.e) {
                        return;
                    }
                    f.this.e().postDelayed(f.this.g, 300L);
                    f.this.e = true;
                } catch (ConcurrentModificationException e) {
                    com.netease.nimlib.log.b.f("ChatRoomMessageManager", "notify chat room messages error: " + e.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.c.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.c.size());
        arrayList.addAll(this.c);
        this.c.clear();
        l.a(arrayList);
        this.d = y.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Handler e() {
        com.netease.nimlib.log.b.d("ChatRoomMessageManager", "notifyThread start,notifyHandler = " + this.f);
        if (this.f == null) {
            this.f = com.netease.nimlib.c.b.a.c().a("RoomMessage_" + this.a);
        }
        com.netease.nimlib.log.b.d("ChatRoomMessageManager", "notifyThread end");
        return this.f;
    }

    private synchronized void f() {
        com.netease.nimlib.log.b.d("ChatRoomMessageManager", "clearThread start");
        com.netease.nimlib.c.b.a.c().b("RoomMessage_" + this.a);
        this.f = null;
        com.netease.nimlib.log.b.d("ChatRoomMessageManager", "clearThread end");
    }
}
