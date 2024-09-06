package com.netease.nimlib.ipc;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.h;
import com.netease.nimlib.network.g;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.StatusCodeInfo;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.model.CaptureDeviceInfoConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: RemoteAgent.java */
/* loaded from: classes.dex */
public class e {
    private final Messenger a;
    private Messenger b;
    private final List<Pair<Integer, Object>> c;
    private com.netease.nimlib.service.a d;

    /* compiled from: RemoteAgent.java */
    /* loaded from: classes.dex */
    private static class a {
        static e a = new e();
    }

    /* synthetic */ e(AnonymousClass1 anonymousClass1) {
        this();
    }

    private e() {
        this.c = new ArrayList();
        HandlerThread handlerThread = new HandlerThread("RemoteAgent");
        handlerThread.start();
        this.a = new Messenger(new b(handlerThread.getLooper()));
        this.d = new com.netease.nimlib.service.a();
    }

    private static e f() {
        return a.a;
    }

    public static IBinder a() {
        return f().a.getBinder();
    }

    public static void a(String str) {
        if (h.b()) {
            return;
        }
        h.b(true);
        com.netease.nimlib.log.b.e("UI process bound! service=" + str);
    }

    public static void a(com.netease.nimlib.ipc.a.c cVar) {
        com.netease.nimlib.log.b.e("serviceBound false, send mix push state to UI");
        f().a(17, cVar);
    }

    public static void b() {
        a.C0029a c0029a = new a.C0029a();
        c0029a.a = new com.netease.nimlib.push.packet.a((byte) 5, (byte) 1);
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        bVar.a(0L);
        c0029a.b = new com.netease.nimlib.push.packet.c.f(bVar.b());
        a(c0029a);
    }

    public static void a(a.C0029a c0029a) {
        Iterator<com.netease.nimlib.ipc.a.d> it = new com.netease.nimlib.ipc.a.d(c0029a).a().iterator();
        while (it.hasNext()) {
            f().a(14, it.next());
        }
    }

    public static void a(StatusCode statusCode) {
        if (h.b() || statusCode.wontAutoLogin()) {
            f().a(15, new com.netease.nimlib.ipc.a.e(new StatusCodeInfo(statusCode, statusCode.getDesc()), h.i(), com.netease.nimlib.c.m(), h.j()));
        }
    }

    public static void c() {
        if (h.b()) {
            f().a(16, com.netease.nimlib.push.h.b());
        }
    }

    private static void b(StatusCode statusCode) {
        f().a(15, (Object) new com.netease.nimlib.ipc.a.e(new StatusCodeInfo(statusCode, statusCode.getDesc()), h.i(), com.netease.nimlib.c.m(), h.j()), true);
    }

    private static void g() {
        f().a(16, (Object) com.netease.nimlib.push.h.b(), true);
    }

    public static void d() {
        f().a(19, (Object) com.netease.nimlib.d.c.e().c(), true);
    }

    public static void e() {
        if (h.b()) {
            f().a(2, new com.netease.nimlib.ipc.a.a(com.netease.nimlib.c.r()));
        }
    }

    public static void a(com.netease.nimlib.apm.b.b bVar) {
        if (h.b() && bVar != null) {
            com.netease.nimlib.log.b.G("sendEvent, startTime = " + bVar.b() + ",stopTime = " + bVar.c() + ",eventModel = " + bVar.m());
            f().a(22, (Object) bVar, true);
        }
    }

    public static void b(String str) {
        if (h.b() && !TextUtils.isEmpty(str)) {
            com.netease.nimlib.log.b.G("sendEvent, event = " + str);
            f().a(24, (Object) str, true);
        }
    }

    public static void a(com.netease.nimlib.apm.b.a aVar) {
        if (h.b() && aVar != null) {
            com.netease.nimlib.log.b.G("sendEventExtension, extension = " + aVar.d());
            f().a(23, (Object) aVar, true);
        }
    }

    public static void c(String str) {
        if (h.b()) {
            f().a(25, (Object) str, true);
        }
    }

    public static void d(String str) {
        if (h.b()) {
            f().a(26, (Object) str, true);
        }
    }

    public void a(int i, Object obj) {
        if (this.c.size() > 0) {
            b(i, obj);
            if (h.b()) {
                i();
                return;
            }
            return;
        }
        a(i, obj, true);
    }

    private boolean a(int i, Object obj, boolean z) {
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            if (z2 || i2 > 3) {
                break;
            }
            i2++;
            try {
                if (h.b() && this.b != null) {
                    this.b.send(com.netease.nimlib.ipc.a.a(i, obj));
                    z2 = true;
                }
            } catch (Exception e) {
                com.netease.nimlib.log.b.e("remote send error: " + e);
                if (!(e instanceof TransactionTooLargeException)) {
                    e.printStackTrace();
                    break;
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (!z2 && z) {
            b(i, obj);
        }
        return z2;
    }

    /* compiled from: RemoteAgent.java */
    /* loaded from: classes.dex */
    private class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                int i = message.what;
                if (i == -163) {
                    System.exit(-163);
                } else if (i == 13) {
                    com.netease.nimlib.ipc.a.d dVar = (com.netease.nimlib.ipc.a.d) com.netease.nimlib.ipc.a.a(message);
                    if (dVar != null) {
                        if (com.netease.nimlib.ipc.a.b.c().a(dVar.e())) {
                            com.netease.nimlib.push.f.l().a(dVar);
                        } else if (!com.netease.nimlib.ipc.a.b.c().a()) {
                            com.netease.nimlib.ipc.a.b.c().b();
                        }
                    }
                } else if (i == 18) {
                    com.netease.nimlib.push.f.l().e();
                } else if (i == 21) {
                    com.netease.nimlib.c.a((CaptureDeviceInfoConfig) com.netease.nimlib.ipc.a.a(message));
                } else if (i == 1) {
                    e.this.a(message);
                } else if (i == 2) {
                    com.netease.nimlib.push.f.l().a((com.netease.nimlib.ipc.a.a) com.netease.nimlib.ipc.a.a(message));
                    e.this.a(3, (Object) null);
                } else if (i == 10) {
                    LoginInfo loginInfo = (LoginInfo) com.netease.nimlib.ipc.a.a(message);
                    com.netease.nimlib.c.J();
                    com.netease.nimlib.push.f.l().a(loginInfo);
                } else if (i == 11) {
                    com.netease.nimlib.push.f.l().c();
                } else if (i == 27) {
                    Boolean bool = (Boolean) com.netease.nimlib.ipc.a.b(message);
                    com.netease.nimlib.log.b.d("RemoteAgent", "receive MSG_AB_REAL_REACHABILITY isOpen = " + bool);
                    g.a().a(Boolean.TRUE.equals(bool));
                } else if (i == 28) {
                    Boolean bool2 = (Boolean) com.netease.nimlib.ipc.a.b(message);
                    com.netease.nimlib.log.b.d("RemoteAgent", "receive MSG_NETWORK_CONNECT_STATUS isConnected = " + bool2);
                    g.a().b(Boolean.TRUE.equals(bool2));
                } else {
                    super.handleMessage(message);
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("RemoteAgent", "handle message error.", th);
            }
        }
    }

    /* compiled from: RemoteAgent.java */
    /* renamed from: com.netease.nimlib.ipc.e$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements IBinder.DeathRecipient {
        AnonymousClass1() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            com.netease.nimlib.log.b.e("!!! UI binder dead !!!");
            h.b(false);
            e.this.b = null;
            com.netease.nimlib.log.b.c();
            if (!com.netease.nimlib.c.H() && !com.netease.nimlib.c.s() && !com.netease.nimlib.c.G() && !e.this.h()) {
                e.this.a(com.netease.nimlib.c.e());
            } else {
                com.netease.nimlib.log.b.O("safe quit push process!");
                com.netease.nimlib.push.g.a(com.netease.nimlib.c.e());
            }
        }
    }

    public void a(Message message) {
        try {
            Messenger messenger = message.replyTo;
            this.b = messenger;
            messenger.getBinder().linkToDeath(new IBinder.DeathRecipient() { // from class: com.netease.nimlib.ipc.e.1
                AnonymousClass1() {
                }

                @Override // android.os.IBinder.DeathRecipient
                public void binderDied() {
                    com.netease.nimlib.log.b.e("!!! UI binder dead !!!");
                    h.b(false);
                    e.this.b = null;
                    com.netease.nimlib.log.b.c();
                    if (!com.netease.nimlib.c.H() && !com.netease.nimlib.c.s() && !com.netease.nimlib.c.G() && !e.this.h()) {
                        e.this.a(com.netease.nimlib.c.e());
                    } else {
                        com.netease.nimlib.log.b.O("safe quit push process!");
                        com.netease.nimlib.push.g.a(com.netease.nimlib.c.e());
                    }
                }
            }, 0);
            com.netease.nimlib.log.b.e("IPC duplex channel established");
            j();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        i();
    }

    public boolean h() {
        String a2 = com.netease.nimlib.p.a.a();
        if (TextUtils.isEmpty(a2)) {
            return true;
        }
        String upperCase = a2.toUpperCase();
        char c = 65535;
        switch (upperCase.hashCode()) {
            case -1881642058:
                if (upperCase.equals("REALME")) {
                    c = '\t';
                    break;
                }
                break;
            case -1706170181:
                if (upperCase.equals("XIAOMI")) {
                    c = 0;
                    break;
                }
                break;
            case -1134767290:
                if (upperCase.equals("BLACKSHARK")) {
                    c = 1;
                    break;
                }
                break;
            case -602397472:
                if (upperCase.equals("ONEPLUS")) {
                    c = '\b';
                    break;
                }
                break;
            case 2432928:
                if (upperCase.equals("OPPO")) {
                    c = 7;
                    break;
                }
                break;
            case 2466086:
                if (upperCase.equals("PTAC")) {
                    c = 3;
                    break;
                }
                break;
            case 2634924:
                if (upperCase.equals("VIVO")) {
                    c = 6;
                    break;
                }
                break;
            case 68924490:
                if (upperCase.equals("HONOR")) {
                    c = 4;
                    break;
                }
                break;
            case 73239724:
                if (upperCase.equals("MEIZU")) {
                    c = 5;
                    break;
                }
                break;
            case 2141820391:
                if (upperCase.equals("HUAWEI")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
                return true;
            default:
                return false;
        }
    }

    private void b(int i, Object obj) {
        com.netease.nimlib.log.b.e("pend " + i);
        synchronized (this.c) {
            this.c.add(new Pair<>(Integer.valueOf(i), obj));
        }
        a(com.netease.nimlib.c.e());
    }

    public synchronized void a(Context context) {
        try {
            this.d.a(context, this.c.size());
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("RemoteAgent", "awakeUI error.", th);
        }
    }

    private void i() {
        synchronized (this.c) {
            Iterator<Pair<Integer, Object>> it = this.c.iterator();
            while (it.hasNext()) {
                Pair<Integer, Object> next = it.next();
                if (!a(((Integer) next.first).intValue(), next.second, false)) {
                    break;
                } else {
                    it.remove();
                }
            }
        }
    }

    private void j() {
        d();
        b(h.e());
        g();
        com.netease.nimlib.push.f.l().g();
        this.d.a();
    }
}
