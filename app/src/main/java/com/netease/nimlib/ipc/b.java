package com.netease.nimlib.ipc;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.c;
import com.netease.nimlib.h;
import com.netease.nimlib.n.g;
import com.netease.nimlib.n.q;
import com.netease.nimlib.n.r;
import com.netease.nimlib.n.s;
import com.netease.nimlib.plugin.interact.IMixPushInteract;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.service.NimService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: LocalAgent.java */
/* loaded from: classes.dex */
public class b {
    private Messenger a;
    private Messenger b;
    private a c;
    private IBinder d;
    private d e;
    private d f;
    private List<Message> g;

    public b(Context context) {
        if (!h.h()) {
            if (com.netease.nimlib.log.b.a.a()) {
                Log.i(NIMClient.TAG, "LocalAgent only lives in main process");
            }
            com.netease.nimlib.log.b.d("LocalAgent only lives in main process");
            return;
        }
        this.g = new ArrayList();
        HandlerThread handlerThread = new HandlerThread("LocalAgent");
        handlerThread.start();
        this.c = new a(handlerThread.getLooper());
        this.b = new Messenger(this.c);
        if (!com.netease.nimlib.c.s()) {
            d();
        } else {
            com.netease.nimlib.log.b.N("reduced IM, delay start push process!");
        }
    }

    private void d() {
        if ((this.e == null || this.f == null) && NimService.a(com.netease.nimlib.c.e(), 1)) {
            a(com.netease.nimlib.c.e());
        }
    }

    public void a(LoginInfo loginInfo) {
        a(10, loginInfo);
    }

    public void a() {
        if (this.a == null) {
            return;
        }
        a(11, (Parcelable) null);
    }

    public void a(com.netease.nimlib.ipc.a.a aVar) {
        if (aVar == null) {
            return;
        }
        boolean a2 = a(2, aVar);
        com.netease.nimlib.log.b.d("LocalAgent", String.format("sendAppStatus isAppOnForeground:%s sent:%s", Boolean.valueOf(aVar.a()), Boolean.valueOf(a2)));
        if (aVar.a() && a2) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            this.c.sendMessageDelayed(obtain, 5000L);
        }
    }

    public void a(Boolean bool) {
        if (bool == null) {
            return;
        }
        com.netease.nimlib.log.b.d("LocalAgent", String.format("sendABRealReachability isOpen:%s sent:%s", bool, Boolean.valueOf(a(com.netease.nimlib.ipc.a.a(27, bool)))));
    }

    public void b(Boolean bool) {
        if (bool == null) {
            return;
        }
        com.netease.nimlib.log.b.d("LocalAgent", String.format("sendNetworkConnectStatus isConnected:%s sent:%s", bool, Boolean.valueOf(a(com.netease.nimlib.ipc.a.a(28, bool)))));
    }

    public void b() {
        if (i.a().c().c() <= 4) {
            a(18, (Parcelable) null);
        }
    }

    public void a(com.netease.nimlib.ipc.a.d dVar) {
        Iterator<com.netease.nimlib.ipc.a.d> it = dVar.a().iterator();
        while (it.hasNext()) {
            a(13, it.next());
        }
    }

    public boolean a(int i, Parcelable parcelable) {
        return a(com.netease.nimlib.ipc.a.a(i, parcelable));
    }

    private boolean a(Message message) {
        d();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= 3) {
                break;
            }
            try {
                com.netease.nimlib.log.b.d("LocalAgent", "sender = " + this.a);
                if (this.a == null) {
                    break;
                }
                this.a.send(message);
                z = true;
                break;
            } catch (DeadObjectException e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.e("LocalAgent", "DeadObjectException when send", e);
                e();
            } catch (Exception e2) {
                com.netease.nimlib.log.b.e("LocalAgent", "Exception when send", e2);
                if (!f.a(e2)) {
                    a(false);
                    break;
                }
                i++;
            }
        }
        if (!z) {
            b(message);
            d dVar = this.e;
            if (dVar != null) {
                dVar.b();
            }
        }
        return z;
    }

    private void a(Context context) {
        d dVar;
        if (com.netease.nimlib.log.b.a.a()) {
            Log.i(NIMClient.TAG, "bindService context = " + context);
        }
        if (context == null) {
            return;
        }
        this.e = new d(context, NimService.a(context), "main_conn") { // from class: com.netease.nimlib.ipc.b.1
            @Override // com.netease.nimlib.ipc.d
            protected void a(IBinder iBinder) {
                super.a(iBinder);
                b.this.a(iBinder);
                if (com.netease.nimlib.c.H() || com.netease.nimlib.c.s() || com.netease.nimlib.c.G() || b.this.e == null) {
                    return;
                }
                b.this.e.c();
            }

            @Override // com.netease.nimlib.ipc.d
            protected void a() {
                super.a();
                if ((com.netease.nimlib.c.H() || com.netease.nimlib.c.s() || com.netease.nimlib.c.G()) && b.this.e != null) {
                    b.this.e.b();
                }
            }
        };
        this.f = new d(context, NimService.b(context), "aux_conn") { // from class: com.netease.nimlib.ipc.b.2
            @Override // com.netease.nimlib.ipc.d
            protected void a() {
                super.a();
                if (com.netease.nimlib.c.H() || com.netease.nimlib.c.s() || com.netease.nimlib.c.G() || b.this.f == null) {
                    return;
                }
                b.this.f.b();
            }

            @Override // com.netease.nimlib.ipc.d
            protected void a(IBinder iBinder) {
                super.a(iBinder);
                if (com.netease.nimlib.c.H() || com.netease.nimlib.c.s() || com.netease.nimlib.c.G()) {
                    return;
                }
                if (b.this.d == null || b.this.a == null) {
                    com.netease.nimlib.log.b.d("AuxService onConnected, reconnect NimService...");
                    if (b.this.e != null) {
                        b.this.e.b();
                    }
                }
            }
        };
        d dVar2 = this.e;
        if (dVar2 != null) {
            dVar2.b();
        }
        if (com.netease.nimlib.c.H() || com.netease.nimlib.c.s() || com.netease.nimlib.c.G() || (dVar = this.f) == null) {
            return;
        }
        dVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IBinder iBinder) {
        this.d = iBinder;
        try {
            iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: com.netease.nimlib.ipc.-$$Lambda$b$kwjCLjfotxuQH---9Q0jLRz0lsY
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    b.this.i();
                }
            }, 0);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.f("LocalAgent", "binder linkToDeath exception " + th);
        }
        a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i() {
        d dVar;
        com.netease.nimlib.log.b.d("LocalAgent", "binderDied");
        e();
        if (com.netease.nimlib.c.H() || (dVar = this.e) == null) {
            return;
        }
        dVar.b();
    }

    private void a(boolean z) {
        if (z && this.d != null) {
            this.a = new Messenger(this.d);
            h();
            g();
            return;
        }
        this.a = null;
    }

    private void e() {
        com.netease.nimlib.log.b.d("!!! Push binder dead !!!");
        this.d = null;
        a(false);
        com.netease.nimlib.log.b.c();
    }

    private void b(Message message) {
        f();
        synchronized (this.g) {
            this.g.add(message);
        }
    }

    private void f() {
        if (this.g == null) {
            this.g = new ArrayList();
        }
    }

    private void g() {
        ArrayList arrayList;
        f();
        synchronized (this.g) {
            if (this.g.size() > 0) {
                arrayList = new ArrayList(this.g);
                this.g.clear();
            } else {
                arrayList = null;
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a((Message) it.next());
            }
        }
    }

    private void h() {
        Message obtain = Message.obtain((Handler) null, 1);
        obtain.replyTo = this.b;
        try {
            this.a.send(obtain);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d("ipc register exception : " + th);
            a(false);
        }
    }

    /* compiled from: LocalAgent.java */
    /* loaded from: classes.dex */
    private class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                int i = message.what;
                if (i == 2) {
                    com.netease.nimlib.c.c(((com.netease.nimlib.ipc.a.a) com.netease.nimlib.ipc.a.a(message)).b());
                } else if (i == 3) {
                    removeMessages(4);
                } else if (i == 4) {
                    com.netease.nimlib.log.b.e("LocalAgent", String.format("LocalHandler handleMessage MSG_APP_STATUS_ACK_TIMEOUT main:%s sender:%s binder:%s", b.this.e, b.this.a, b.this.d));
                    if (b.this.e != null) {
                        b.this.e.a(0);
                    } else {
                        b.this.c();
                    }
                } else if (i != 19) {
                    switch (i) {
                        case 14:
                            com.netease.nimlib.ipc.a.d dVar = (com.netease.nimlib.ipc.a.d) com.netease.nimlib.ipc.a.a(message);
                            if (dVar != null) {
                                i.a().a(dVar);
                                break;
                            }
                            break;
                        case 15:
                            final com.netease.nimlib.ipc.a.e eVar = (com.netease.nimlib.ipc.a.e) com.netease.nimlib.ipc.a.b(message);
                            if (com.netease.nimlib.c.b()) {
                                i.a().a(eVar);
                                break;
                            } else {
                                com.netease.nimlib.c.a(new c.a() { // from class: com.netease.nimlib.ipc.b.a.1
                                    @Override // com.netease.nimlib.c.a
                                    public void a(boolean z) {
                                        if (z) {
                                            a.this.post(new Runnable() { // from class: com.netease.nimlib.ipc.b.a.1.1
                                                @Override // java.lang.Runnable
                                                public void run() {
                                                    i.a().a(eVar);
                                                }
                                            });
                                            com.netease.nimlib.c.b(this);
                                        }
                                    }
                                });
                                break;
                            }
                        case 16:
                            i.a().a((ArrayList<com.netease.nimlib.biz.f>) com.netease.nimlib.ipc.a.b(message));
                            break;
                        case 17:
                            b.this.a((com.netease.nimlib.ipc.a.c) com.netease.nimlib.ipc.a.a(message));
                            break;
                        default:
                            switch (i) {
                                case 22:
                                    com.netease.nimlib.apm.b.b bVar = (com.netease.nimlib.apm.b.b) com.netease.nimlib.ipc.a.a(message);
                                    if ("login".equals(bVar.o())) {
                                        s.a().a((com.netease.nimlib.n.e.e) bVar);
                                        break;
                                    } else if ("exceptions".equals(bVar.o())) {
                                        r.a().a((com.netease.nimlib.n.e.d) bVar);
                                        break;
                                    }
                                    break;
                                case 23:
                                    q.a().a((com.netease.nimlib.apm.b.a) com.netease.nimlib.ipc.a.a(message));
                                    break;
                                case 24:
                                    q.a().a(new JSONObject((String) com.netease.nimlib.ipc.a.b(message)));
                                    break;
                                case 25:
                                    String str = (String) com.netease.nimlib.ipc.a.b(message);
                                    com.netease.nimlib.log.b.d("LocalAgent", String.format("handleNtpIpcEvent %s %s", Boolean.valueOf(com.netease.nimlib.m.e.a(i.a().b(), str)), str));
                                    break;
                                case 26:
                                    JSONObject jSONObject = new JSONObject((String) com.netease.nimlib.ipc.a.b(message));
                                    if ("msgSend".equals(jSONObject.optString("eventKey"))) {
                                        g.a().a(jSONObject);
                                        break;
                                    }
                                    break;
                                default:
                                    super.handleMessage(message);
                                    break;
                            }
                    }
                } else {
                    com.netease.nimlib.d.d.e().a((String) com.netease.nimlib.ipc.a.b(message));
                }
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("LocalAgent", "handle push message error.", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.netease.nimlib.ipc.a.c cVar) {
        IMixPushInteract iMixPushInteract = (IMixPushInteract) com.netease.nimlib.plugin.interact.b.a().a(IMixPushInteract.class);
        if (iMixPushInteract != null) {
            iMixPushInteract.a(cVar);
        }
    }

    public void c() {
        if (this.a == null || this.d == null) {
            d dVar = this.e;
            if (dVar == null || !dVar.d()) {
                com.netease.nimlib.log.b.d("IPC has not established while awaking UI, start rebinding...");
                if (NimService.a(com.netease.nimlib.c.e(), 1)) {
                    a(com.netease.nimlib.c.e());
                }
            }
        }
    }
}
