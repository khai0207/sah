package com.netease.nimlib.chatroom;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.netease.nimlib.app.AppForegroundWatcherCompat;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.biz.g;
import com.netease.nimlib.chatroom.n;
import com.netease.nimlib.o.f;
import com.netease.nimlib.push.b.a;
import com.netease.nimlib.push.b.b;
import com.netease.nimlib.sdk.ModeCode;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomKickOutEvent;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomStatusChangeData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomResultData;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* compiled from: ChatRoomCore.java */
/* loaded from: classes.dex */
public class d {
    private Handler a;
    private com.netease.nimlib.push.b.a b;
    private b c = new b();
    private j d = new j();
    private com.netease.nimlib.c.b.b e = new com.netease.nimlib.c.b.b("Response-Room", com.netease.nimlib.c.b.b.c, false);
    private com.netease.nimlib.biz.c.h f = new com.netease.nimlib.biz.c.h() { // from class: com.netease.nimlib.chatroom.d.1
        AnonymousClass1() {
        }

        @Override // com.netease.nimlib.biz.c.h
        public boolean a(com.netease.nimlib.biz.e.a aVar) {
            return d.e().b(aVar);
        }

        @Override // com.netease.nimlib.biz.c.h
        public boolean b(com.netease.nimlib.biz.e.a aVar) {
            return d.e().c(aVar);
        }
    };
    private com.netease.nimlib.chatroom.b.i g = new com.netease.nimlib.chatroom.b.i(this.e, this.f);
    private n h = new n(new n.a() { // from class: com.netease.nimlib.chatroom.d.2
        AnonymousClass2() {
        }

        /* compiled from: ChatRoomCore.java */
        /* renamed from: com.netease.nimlib.chatroom.d$2$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ int b;

            AnonymousClass1(String str, int i) {
                r2 = str;
                r3 = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    d.this.a(r2, r3, true);
                } catch (Throwable th) {
                    com.netease.nimlib.log.b.g("handle connection change error, e=" + th.getMessage());
                }
            }
        }

        @Override // com.netease.nimlib.chatroom.n.a
        public void a(String str, int i) {
            d.this.e.execute(new Runnable() { // from class: com.netease.nimlib.chatroom.d.2.1
                final /* synthetic */ String a;
                final /* synthetic */ int b;

                AnonymousClass1(String str2, int i2) {
                    r2 = str2;
                    r3 = i2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.this.a(r2, r3, true);
                    } catch (Throwable th) {
                        com.netease.nimlib.log.b.g("handle connection change error, e=" + th.getMessage());
                    }
                }
            });
        }

        @Override // com.netease.nimlib.chatroom.n.a
        public void a(a.C0029a c0029a) {
            String o = c0029a.a.o();
            if (d.e().e(o)) {
                return;
            }
            com.netease.nimlib.biz.g.b().a(c0029a, o);
            d.this.g.a(c0029a);
        }

        /* compiled from: ChatRoomCore.java */
        /* renamed from: com.netease.nimlib.chatroom.d$2$2 */
        /* loaded from: classes.dex */
        class RunnableC00312 implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ int b;

            RunnableC00312(String str, int i) {
                r2 = str;
                r3 = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.a(r2, r3);
            }
        }

        @Override // com.netease.nimlib.chatroom.n.a
        public void b(String str, int i) {
            d.this.e.execute(new Runnable() { // from class: com.netease.nimlib.chatroom.d.2.2
                final /* synthetic */ String a;
                final /* synthetic */ int b;

                RunnableC00312(String str2, int i2) {
                    r2 = str2;
                    r3 = i2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    d.this.a(r2, r3);
                }
            });
        }
    });
    private g.c i = new g.c() { // from class: com.netease.nimlib.chatroom.d.5
        AnonymousClass5() {
        }

        @Override // com.netease.nimlib.biz.g.c
        public void a(a.C0029a c0029a) {
            d.this.a(c0029a);
        }
    };
    private Observer<StatusCode> j;
    private AppForegroundWatcherCompat.a k;

    /* compiled from: ChatRoomCore.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final d a = new d();
    }

    /* compiled from: ChatRoomCore.java */
    /* renamed from: com.netease.nimlib.chatroom.d$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements com.netease.nimlib.biz.c.h {
        AnonymousClass1() {
        }

        @Override // com.netease.nimlib.biz.c.h
        public boolean a(com.netease.nimlib.biz.e.a aVar) {
            return d.e().b(aVar);
        }

        @Override // com.netease.nimlib.biz.c.h
        public boolean b(com.netease.nimlib.biz.e.a aVar) {
            return d.e().c(aVar);
        }
    }

    /* compiled from: ChatRoomCore.java */
    /* renamed from: com.netease.nimlib.chatroom.d$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements n.a {
        AnonymousClass2() {
        }

        /* compiled from: ChatRoomCore.java */
        /* renamed from: com.netease.nimlib.chatroom.d$2$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ int b;

            AnonymousClass1(String str2, int i2) {
                r2 = str2;
                r3 = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    d.this.a(r2, r3, true);
                } catch (Throwable th) {
                    com.netease.nimlib.log.b.g("handle connection change error, e=" + th.getMessage());
                }
            }
        }

        @Override // com.netease.nimlib.chatroom.n.a
        public void a(String str2, int i2) {
            d.this.e.execute(new Runnable() { // from class: com.netease.nimlib.chatroom.d.2.1
                final /* synthetic */ String a;
                final /* synthetic */ int b;

                AnonymousClass1(String str22, int i22) {
                    r2 = str22;
                    r3 = i22;
                }

                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.this.a(r2, r3, true);
                    } catch (Throwable th) {
                        com.netease.nimlib.log.b.g("handle connection change error, e=" + th.getMessage());
                    }
                }
            });
        }

        @Override // com.netease.nimlib.chatroom.n.a
        public void a(a.C0029a c0029a) {
            String o = c0029a.a.o();
            if (d.e().e(o)) {
                return;
            }
            com.netease.nimlib.biz.g.b().a(c0029a, o);
            d.this.g.a(c0029a);
        }

        /* compiled from: ChatRoomCore.java */
        /* renamed from: com.netease.nimlib.chatroom.d$2$2 */
        /* loaded from: classes.dex */
        class RunnableC00312 implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ int b;

            RunnableC00312(String str2, int i2) {
                r2 = str2;
                r3 = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.a(r2, r3);
            }
        }

        @Override // com.netease.nimlib.chatroom.n.a
        public void b(String str2, int i2) {
            d.this.e.execute(new Runnable() { // from class: com.netease.nimlib.chatroom.d.2.2
                final /* synthetic */ String a;
                final /* synthetic */ int b;

                RunnableC00312(String str22, int i22) {
                    r2 = str22;
                    r3 = i22;
                }

                @Override // java.lang.Runnable
                public void run() {
                    d.this.a(r2, r3);
                }
            });
        }
    }

    public void a(Context context) {
        this.a = com.netease.nimlib.c.b.a.b(context);
        this.c.a(context, this.h);
        this.d.a();
        this.e.a();
        e(true);
        d(true);
        com.netease.nimlib.log.b.i("chat room startup");
    }

    public void a() {
        this.c.a();
        this.d.b();
        this.e.b();
        g();
        e(false);
        d(false);
        this.a = null;
        com.netease.nimlib.log.b.i("chat room shutdown");
    }

    public void a(boolean z) {
        c(z);
    }

    public int b() {
        return this.h.e();
    }

    void c() {
        if (this.b == null) {
            com.netease.nimlib.push.b.a aVar = new com.netease.nimlib.push.b.a(com.netease.nimlib.c.e(), new a.InterfaceC0053a() { // from class: com.netease.nimlib.chatroom.-$$Lambda$d$0jy5qTYVyBzvkN0DkN-fVq_wgdw
                public /* synthetic */ $$Lambda$d$0jy5qTYVyBzvkN0DkNfVq_wgdw() {
                }

                @Override // com.netease.nimlib.push.b.a.InterfaceC0053a
                public final void onNetworkEvent(b.a aVar2) {
                    d.this.a(aVar2);
                }
            });
            this.b = aVar;
            aVar.c();
            com.netease.nimlib.log.b.g("start connectivity watcher in INDEPENDENT mode");
        }
    }

    /* compiled from: ChatRoomCore.java */
    /* renamed from: com.netease.nimlib.chatroom.d$8 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.a.values().length];
            a = iArr;
            try {
                iArr[b.a.NETWORK_AVAILABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public /* synthetic */ void a(b.a aVar) {
        if (AnonymousClass8.a[aVar.ordinal()] != 1) {
            return;
        }
        i();
    }

    private void f() {
        com.netease.nimlib.push.b.a aVar = this.b;
        if (aVar != null) {
            aVar.d();
            this.b = null;
            com.netease.nimlib.log.b.g("stop connectivity watcher in INDEPENDENT mode");
        }
    }

    boolean d() {
        com.netease.nimlib.push.b.a aVar = this.b;
        if (aVar != null) {
            return aVar.a();
        }
        return com.netease.nimlib.o.p.b(com.netease.nimlib.c.e());
    }

    public void a(com.netease.nimlib.i.k kVar, EnterChatRoomData enterChatRoomData) {
        if (enterChatRoomData == null || !enterChatRoomData.isValid()) {
            throw new IllegalArgumentException("EnterChatRoomData is invalid!");
        }
        if (enterChatRoomData.isIndependentMode()) {
            if (enterChatRoomData.getIndependentModeCallback() == null) {
                throw new IllegalArgumentException("EnterChatRoomData is invalid! IndependentModeCallback should not be null");
            }
            if (enterChatRoomData.isAnonymousMode() && (TextUtils.isEmpty(enterChatRoomData.getNick()) || TextUtils.isEmpty(enterChatRoomData.getAvatar()))) {
                throw new IllegalArgumentException("EnterChatRoomData is invalid! Nick or avatar should not be null when do anonymous login");
            }
        } else if (com.netease.nimlib.h.f() != ModeCode.IM) {
            throw new IllegalArgumentException("can not enter chatroom by IM mode, because im not logined");
        }
        if (!enterChatRoomData.isIndependentMode() && com.netease.nimlib.h.e() != StatusCode.LOGINED) {
            com.netease.nimlib.log.b.g("on enter chat room failed, as SDK state is not LOGINED");
            EnterChatRoomResultData a2 = a(enterChatRoomData);
            this.c.b(a2.getRoomId(), a2.getResCode());
            com.netease.nimlib.log.b.g("reply enter room result, room id=" + enterChatRoomData.getRoomId() + ", code=" + a2.getResCode());
            kVar.a(a2.getResCode()).a(a2).b();
            return;
        }
        if (enterChatRoomData.isIndependentMode() && !enterChatRoomData.isAnonymousMode() && TextUtils.isEmpty(enterChatRoomData.getAccount())) {
            com.netease.nimlib.log.b.g("independent mode but not anonymous mode,the account can not be null");
            kVar.a(414).b();
            return;
        }
        if (enterChatRoomData.isIndependentMode() && enterChatRoomData.isAnonymousMode() && TextUtils.isEmpty(enterChatRoomData.getAccount())) {
            String a3 = com.netease.nimlib.chatroom.a.a();
            com.netease.nimlib.log.b.g("generate chat room anonymous account=" + a3 + ", roomId=" + enterChatRoomData.getRoomId());
            enterChatRoomData.setIndependentMode(enterChatRoomData.getIndependentModeCallback(), a3, enterChatRoomData.getToken(), enterChatRoomData.isAnonymousMode());
        }
        if (c.a().c(enterChatRoomData.getRoomId())) {
            a(enterChatRoomData.getRoomId(), true);
        }
        if (enterChatRoomData.isIndependentMode()) {
            com.netease.nimlib.c.b(new LoginInfo(enterChatRoomData.getAccount(), enterChatRoomData.getToken()));
        }
        com.netease.nimlib.n.c.a().a(enterChatRoomData.getRoomId(), enterChatRoomData.getAccount(), false);
        c.a().a(enterChatRoomData.getRoomId(), kVar);
        this.c.a(enterChatRoomData);
    }

    public void a(String str, int i) {
        this.c.a(str, i);
    }

    public void a(com.netease.nimlib.chatroom.d.f fVar) {
        this.c.a(fVar.j().o(), fVar);
    }

    void a(EnterChatRoomResultData enterChatRoomResultData) {
        if (enterChatRoomResultData == null || TextUtils.isEmpty(enterChatRoomResultData.getRoomId())) {
            return;
        }
        String roomId = enterChatRoomResultData.getRoomId();
        com.netease.nimlib.n.c.a().a(roomId, enterChatRoomResultData.getResCode());
        com.netease.nimlib.i.k i = c.a().i(roomId);
        boolean h = c.a().h(roomId);
        if (i == null || h) {
            return;
        }
        int resCode = enterChatRoomResultData.getResCode();
        if (((resCode == 415 || resCode == 408) && i.o() > 0) || ((resCode == 399 || resCode == 398) && i.o() >= 0)) {
            long j = 100;
            if (resCode == 398) {
                long nextInt = 5000 + new Random().nextInt(10000);
                com.netease.nimlib.log.b.g(String.format("enter chat room with %s, reconnectDelay is %s", Integer.valueOf(resCode), Long.valueOf(nextInt)));
                j = nextInt;
            } else if (resCode == 399) {
                m.a().b();
            }
            EnterChatRoomData k = c.a().k(roomId);
            c.a().g(roomId);
            this.a.postDelayed(new Runnable() { // from class: com.netease.nimlib.chatroom.d.3
                final /* synthetic */ String a;
                final /* synthetic */ com.netease.nimlib.i.k b;
                final /* synthetic */ EnterChatRoomData c;

                AnonymousClass3(String roomId2, com.netease.nimlib.i.k i2, EnterChatRoomData k2) {
                    r2 = roomId2;
                    r3 = i2;
                    r4 = k2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    com.netease.nimlib.log.b.g("retry enter chat room, room id=" + r2);
                    try {
                        d.this.a(r3, r4);
                    } catch (Throwable th) {
                        r3.a(th).b();
                        c.a().j(r2);
                    }
                }
            }, j);
            return;
        }
        com.netease.nimlib.log.b.g("reply enter room result, room id=" + roomId2 + ", code=" + enterChatRoomResultData.getResCode());
        i2.a(enterChatRoomResultData.getResCode()).a(enterChatRoomResultData).b();
        c.a().j(roomId2);
    }

    /* compiled from: ChatRoomCore.java */
    /* renamed from: com.netease.nimlib.chatroom.d$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ com.netease.nimlib.i.k b;
        final /* synthetic */ EnterChatRoomData c;

        AnonymousClass3(String roomId2, com.netease.nimlib.i.k i2, EnterChatRoomData k2) {
            r2 = roomId2;
            r3 = i2;
            r4 = k2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.netease.nimlib.log.b.g("retry enter chat room, room id=" + r2);
            try {
                d.this.a(r3, r4);
            } catch (Throwable th) {
                r3.a(th).b();
                c.a().j(r2);
            }
        }
    }

    public void a(String str) {
        if (!c.a().c(str)) {
            com.netease.nimlib.log.b.i("exit chat room return, as cache is empty");
            return;
        }
        com.netease.nimlib.log.b.g("exit chat room, room id=" + str);
        Runnable t = c.a().t(str);
        if (t != null) {
            this.a.removeCallbacks(t);
        }
        c.a().b(str);
        AnonymousClass4 anonymousClass4 = new Runnable() { // from class: com.netease.nimlib.chatroom.d.4
            final /* synthetic */ String a;

            AnonymousClass4(String str2) {
                r2 = str2;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.netease.nimlib.log.b.g("on exit chat room timeout, room id=" + r2);
                d.this.a(r2, false);
            }
        };
        c.a().a(str2, anonymousClass4);
        this.c.c(str2);
        this.a.postDelayed(anonymousClass4, 3500L);
    }

    /* compiled from: ChatRoomCore.java */
    /* renamed from: com.netease.nimlib.chatroom.d$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass4(String str2) {
            r2 = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.netease.nimlib.log.b.g("on exit chat room timeout, room id=" + r2);
            d.this.a(r2, false);
        }
    }

    public void a(String str, boolean z) {
        if (!z) {
            com.netease.nimlib.log.b.g("on exit chat room, room id=" + str);
        } else {
            com.netease.nimlib.log.b.g("reset chat room before enter, room id=" + str);
        }
        b(str, z);
    }

    public void a(com.netease.nimlib.chatroom.d.n nVar) {
        String o = nVar.j().o();
        int a2 = nVar.a();
        c(o);
        l.a(new ChatRoomKickOutEvent(o, a2, com.netease.nimlib.session.j.c(nVar.b())));
        l.a(new ChatRoomStatusChangeData(StatusCode.KICKOUT, o));
        com.netease.nimlib.log.b.g("on chat room kick out, room id=" + o + ", reason=" + a2);
    }

    public void b(String str) {
        c(str);
        com.netease.nimlib.log.b.g("on chat room closed, room id=" + str);
    }

    void c(String str) {
        b(str, false);
    }

    private void b(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.d.c(str);
        this.c.d(str);
        Runnable t = c.a().t(str);
        if (t != null) {
            this.a.removeCallbacks(t);
        }
        c.a().a(str);
        com.netease.nimlib.chatroom.a.b.d().b(str);
        this.h.a(str);
        if (z) {
            return;
        }
        com.netease.nimlib.log.b.g("reset chat room, room id=" + str);
        if (!c.a().l(str) || this.h.c()) {
            return;
        }
        f();
    }

    private void c(boolean z) {
        List<String> d = com.netease.nimlib.o.f.d(this.h.d(), new f.a() { // from class: com.netease.nimlib.chatroom.-$$Lambda$d$AZFQ2HidY-hzaOvnlNx7WvwI68w
            private final /* synthetic */ boolean f$0;

            public /* synthetic */ $$Lambda$d$AZFQ2HidYhzaOvnlNx7WvwI68w(boolean z2) {
                r1 = z2;
            }

            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Boolean a2;
                a2 = d.a(r1, (String) obj);
                return a2;
            }
        });
        if (com.netease.nimlib.o.f.c((Collection) d)) {
            return;
        }
        this.c.a(d);
        List<Runnable> b = c.a().b(d);
        if (b != null && !b.isEmpty()) {
            Iterator<Runnable> it = b.iterator();
            while (it.hasNext()) {
                this.a.removeCallbacks(it.next());
            }
        }
        c.a().a(d);
        this.h.a(d);
        StringBuilder sb = new StringBuilder();
        sb.append("reset ");
        sb.append(z2 ? "independent" : "dependent");
        sb.append("chat room");
        com.netease.nimlib.log.b.g(sb.toString());
    }

    public static /* synthetic */ Boolean a(boolean z, String str) {
        return Boolean.valueOf(z == c.a().l(str));
    }

    private void g() {
        this.c.b();
        Collection<Runnable> e = c.a().e();
        if (e != null && !e.isEmpty()) {
            Iterator<Runnable> it = e.iterator();
            while (it.hasNext()) {
                this.a.removeCallbacks(it.next());
            }
        }
        c.a().b();
        com.netease.nimlib.chatroom.a.b.d().a();
        this.h.b();
        com.netease.nimlib.log.b.g("reset all chat room");
    }

    void a(StatusCode statusCode, StatusCode statusCode2, String str) {
        if ((statusCode2 == StatusCode.CONNECTING || statusCode2 == StatusCode.LOGINING) && statusCode == StatusCode.LOGINED) {
            return;
        }
        if (statusCode2 != statusCode && statusCode == StatusCode.LOGINED) {
            this.d.a(str);
        } else if (statusCode2 != statusCode && statusCode2 == StatusCode.LOGINED) {
            this.d.b(str);
        }
        l.a(new ChatRoomStatusChangeData(statusCode2, str));
    }

    private EnterChatRoomResultData a(EnterChatRoomData enterChatRoomData) {
        return new EnterChatRoomResultData(enterChatRoomData.getRoomId(), com.netease.nimlib.h.e() != StatusCode.LOGINED ? 1000 : 415, StatusCode.UNLOGIN, null, null, enterChatRoomData.getAccount());
    }

    public boolean a(com.netease.nimlib.biz.d.a aVar) {
        String f = this.h.f();
        if (TextUtils.isEmpty(f)) {
            return false;
        }
        return a(new o(f, aVar), f);
    }

    public boolean a(String str, com.netease.nimlib.biz.d.a aVar) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return a(new o(str, aVar), str);
    }

    boolean a(com.netease.nimlib.biz.d.a aVar, String str) {
        if (!com.netease.nimlib.biz.g.b().a(aVar, str, this.i)) {
            return true;
        }
        try {
            this.h.a(new com.netease.nimlib.ipc.a.d(aVar), str);
            return true;
        } catch (Exception e) {
            com.netease.nimlib.log.b.g("send room request exception" + e.toString());
            return false;
        }
    }

    public boolean a(com.netease.nimlib.biz.g.c cVar, String str) {
        StatusCode e;
        boolean z = false;
        if (cVar == null || TextUtils.isEmpty(str)) {
            return false;
        }
        com.netease.nimlib.biz.d.a b = cVar.b();
        b.i().a(p.a());
        b.i().a(str);
        com.netease.nimlib.n.g.a().a(b);
        if ((c.a().l(str) || com.netease.nimlib.h.e() == StatusCode.LOGINED) && (e = c.a().e(str)) != null && e == StatusCode.LOGINED) {
            z = true;
        }
        boolean a2 = cVar.e() > 0 ? this.d.a(cVar) : z;
        if (z && !e().a(b, str)) {
            cVar.a(ResponseCode.RES_EXCEPTION);
        }
        if (!a2) {
            cVar.a(ResponseCode.RES_ECONNECTION);
        }
        return a2;
    }

    public com.netease.nimlib.biz.d.a a(com.netease.nimlib.biz.e.a aVar) {
        return this.d.c(aVar);
    }

    public boolean b(com.netease.nimlib.biz.e.a aVar) {
        return this.d.a(aVar);
    }

    public boolean c(com.netease.nimlib.biz.e.a aVar) {
        return this.d.b(aVar);
    }

    public boolean e(String str) {
        return (TextUtils.isEmpty(str) || c.a().c(str)) ? false : true;
    }

    void a(a.C0029a c0029a) {
        this.g.a(c0029a);
    }

    /* compiled from: ChatRoomCore.java */
    /* renamed from: com.netease.nimlib.chatroom.d$5 */
    /* loaded from: classes.dex */
    class AnonymousClass5 implements g.c {
        AnonymousClass5() {
        }

        @Override // com.netease.nimlib.biz.g.c
        public void a(a.C0029a c0029a) {
            d.this.a(c0029a);
        }
    }

    /* compiled from: ChatRoomCore.java */
    /* renamed from: com.netease.nimlib.chatroom.d$6 */
    /* loaded from: classes.dex */
    class AnonymousClass6 implements Observer<StatusCode> {
        AnonymousClass6() {
        }

        @Override // com.netease.nimlib.sdk.Observer
        /* renamed from: a */
        public void onEvent(StatusCode statusCode) {
            if (statusCode == StatusCode.LOGINED) {
                d.this.i();
            } else if (statusCode == StatusCode.NET_BROKEN) {
                d.this.h();
            } else {
                statusCode.wontAutoLogin();
            }
        }
    }

    private void d(boolean z) {
        if (z && this.j == null) {
            this.j = new Observer<StatusCode>() { // from class: com.netease.nimlib.chatroom.d.6
                AnonymousClass6() {
                }

                @Override // com.netease.nimlib.sdk.Observer
                /* renamed from: a */
                public void onEvent(StatusCode statusCode) {
                    if (statusCode == StatusCode.LOGINED) {
                        d.this.i();
                    } else if (statusCode == StatusCode.NET_BROKEN) {
                        d.this.h();
                    } else {
                        statusCode.wontAutoLogin();
                    }
                }
            };
        }
        if (this.j != null) {
            ((AuthServiceObserver) NIMClient.getService(AuthServiceObserver.class)).observeOnlineStatus(this.j, z);
        }
    }

    public void h() {
        this.h.a(false);
        Iterator<String> it = this.h.d().iterator();
        while (it.hasNext()) {
            a(it.next(), 0, false);
        }
    }

    public void a(String str, int i, boolean z) {
        if (i != 0) {
            if (i != 2) {
                return;
            }
            com.netease.nimlib.log.b.g("on chat room link CONNECTED, room id=" + str);
            this.c.a(str);
            return;
        }
        if (z) {
            com.netease.nimlib.log.b.g("on chat room connection broken as link DISCONNECTED, room id=" + str);
        } else {
            com.netease.nimlib.log.b.g("on chat room connection broken as system network UNAVAILABLE, room id=" + str);
        }
        this.c.b(str);
    }

    public void i() {
        c.a().d();
        this.h.a();
    }

    /* compiled from: ChatRoomCore.java */
    /* renamed from: com.netease.nimlib.chatroom.d$7 */
    /* loaded from: classes.dex */
    class AnonymousClass7 implements AppForegroundWatcherCompat.a {
        AnonymousClass7() {
        }

        @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
        public void a() {
            d.e().b(true);
        }

        @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
        public void b() {
            d.e().b(false);
        }
    }

    private void e(boolean z) {
        if (z) {
            if (this.k == null) {
                this.k = new AppForegroundWatcherCompat.a() { // from class: com.netease.nimlib.chatroom.d.7
                    AnonymousClass7() {
                    }

                    @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
                    public void a() {
                        d.e().b(true);
                    }

                    @Override // com.netease.nimlib.app.AppForegroundWatcherCompat.a
                    public void b() {
                        d.e().b(false);
                    }
                };
            }
            AppForegroundWatcherCompat.a(this.k);
            return;
        }
        AppForegroundWatcherCompat.b(this.k);
    }

    void b(boolean z) {
        this.c.a(z);
    }

    void d(String str) {
        this.c.a(str, c.a().l(str), c.a().m(str));
    }

    public static d e() {
        return a.a;
    }
}
