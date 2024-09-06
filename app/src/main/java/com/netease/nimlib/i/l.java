package com.netease.nimlib.i;

import android.os.Handler;
import android.util.SparseArray;
import com.netease.nimlib.biz.f.n;
import com.netease.nimlib.biz.f.o;
import com.netease.nimlib.o.z;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.event.EventSubscribeService;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.generic.CustomizedAPIService;
import com.netease.nimlib.sdk.misc.MiscService;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.nos.NosService;
import com.netease.nimlib.sdk.passthrough.PassthroughService;
import com.netease.nimlib.sdk.redpacket.RedPacketService;
import com.netease.nimlib.sdk.robot.RobotService;
import com.netease.nimlib.sdk.settings.SettingsService;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.test.MockTestService;
import com.netease.nimlib.sdk.uinfo.UserService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* compiled from: TransactionExecutor.java */
/* loaded from: classes.dex */
class l {
    private final Map<String, a> a = new HashMap();
    private final SparseArray<h> b = new SparseArray<>();
    private final Handler c = com.netease.nimlib.c.b.a.c().a("bk_executor");
    private final Handler d = com.netease.nimlib.c.b.a.c().a("bk_executor_high");

    /* compiled from: TransactionExecutor.java */
    /* loaded from: classes.dex */
    private static class a {
        private final Map<String, Method> a = new HashMap();
        private j b;

        public a(Class<?> cls, Class<? extends j> cls2) {
            for (Method method : cls.getDeclaredMethods()) {
                this.a.put(a(method), method);
            }
            try {
                this.b = cls2.newInstance();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        private String a(Method method) {
            StringBuilder sb = new StringBuilder();
            sb.append(method.getName());
            for (Class<?> cls : method.getParameterTypes()) {
                sb.append("_");
                sb.append(cls.getSimpleName());
            }
            return sb.toString();
        }

        public Object a(k kVar) throws Throwable {
            return this.a.get(a(kVar.c())).invoke(this.b, kVar.g());
        }
    }

    l() {
        a(AuthService.class, com.netease.nimlib.biz.f.a.class);
        a(MsgService.class, com.netease.nimlib.biz.f.g.class);
        a(TeamService.class, n.class);
        a(SystemMessageService.class, com.netease.nimlib.biz.f.m.class);
        a(UserService.class, o.class);
        a(FriendService.class, com.netease.nimlib.biz.f.d.class);
        a(NosService.class, com.netease.nimlib.biz.f.h.class);
        a(SettingsService.class, com.netease.nimlib.biz.f.l.class);
        a(EventSubscribeService.class, com.netease.nimlib.biz.f.c.class);
        a(RobotService.class, com.netease.nimlib.biz.f.k.class);
        a(RedPacketService.class, com.netease.nimlib.biz.f.j.class);
        a(MockTestService.class, com.netease.nimlib.biz.f.f.class);
        a(MiscService.class, com.netease.nimlib.biz.f.e.class);
        a(PassthroughService.class, com.netease.nimlib.biz.f.i.class);
        a(CustomizedAPIService.class, com.netease.nimlib.biz.f.b.class);
        for (Map.Entry<Class<?>, Class<? extends j>> entry : com.netease.nimlib.plugin.c.a().d().entrySet()) {
            a(entry.getKey(), entry.getValue());
        }
        com.netease.nimlib.log.b.d("TransExec", "register service completed, total size=" + this.a.size());
    }

    public Object a(final k kVar) {
        a aVar = this.a.get(kVar.e());
        if (aVar == null) {
            return null;
        }
        j.a(kVar);
        try {
            com.netease.nimlib.log.b.d("TransExec", "execute " + kVar);
            long a2 = z.a();
            Object a3 = aVar.a(kVar);
            z.a(a2, 2147483647L, new z.a() { // from class: com.netease.nimlib.i.l.1
                @Override // com.netease.nimlib.o.z.a
                public void a(long j) {
                    com.netease.nimlib.log.b.d("TransExec", "execute(cost=" + j + ") " + kVar);
                }
            });
            return a3;
        } catch (Throwable th) {
            th = th;
            try {
                if ((th instanceof InvocationTargetException) && th.getCause() != null) {
                    th = th.getCause();
                }
                com.netease.nimlib.log.b.c("TransExec", "execute " + kVar + " exception", th);
                kVar.a(th).b();
                return null;
            } finally {
                j.a();
            }
        }
    }

    Handler b(k kVar) {
        return kVar.m() > 0 ? this.d : this.c;
    }

    void c(final k kVar) {
        b(kVar).post(z.a(new Runnable() { // from class: com.netease.nimlib.i.l.2
            @Override // java.lang.Runnable
            public void run() {
                Object a2 = l.this.a(kVar);
                if (kVar.l()) {
                    com.netease.nimlib.n.a.a().c(kVar);
                }
                if (a2 instanceof h) {
                    synchronized (l.this.b) {
                        l.this.b.put(kVar.h(), (h) a2);
                    }
                }
            }
        }, 2147483647L, new z.a() { // from class: com.netease.nimlib.i.l.3
            @Override // com.netease.nimlib.o.z.a
            public void a(long j) {
                com.netease.nimlib.log.b.d("TransExec", "execute(elapse=" + j + ") " + kVar);
            }
        }));
    }

    public void d(k kVar) {
        h hVar;
        com.netease.nimlib.log.b.d("TransExec", "abort " + kVar);
        synchronized (this.b) {
            hVar = this.b.get(kVar.h());
            this.b.remove(kVar.h());
        }
        if (hVar != null) {
            hVar.abort();
        }
        com.netease.nimlib.n.a.a().b(kVar);
    }

    void e(k kVar) {
        synchronized (this.b) {
            this.b.remove(kVar.h());
        }
    }

    private void a(Class<?> cls, Class<? extends j> cls2) {
        this.a.put(cls.getSimpleName(), new a(cls, cls2));
    }
}
