package com.netease.nimlib.log;

import com.iflytek.cloud.SpeechConstant;
import com.netease.nimlib.h;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;

/* compiled from: NimLog.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.log.c.b.b {
    private static boolean a;
    private static int b;

    public static boolean a() {
        return b <= 3;
    }

    public static void a(boolean z, String str, int i, boolean z2) {
        String str2;
        String str3;
        a = z;
        b = i;
        if (h.h()) {
            str2 = "ui";
        } else {
            str2 = h.g() ? "push" : null;
        }
        String str4 = str2;
        if (z) {
            str3 = h.h() ? "nim_sdk_ui.mlog" : "nim_sdk_push.mlog";
        } else {
            str3 = "nim_sdk.log";
        }
        String str5 = str3;
        if (z) {
            com.netease.nimlib.log.c.b.b.b(str4, str, str5, i, h.h() ? 16777216 : 8388608, h.h() ? 8388608 : 4194304, z2, null);
        } else {
            com.netease.nimlib.log.c.b.b.a(str4, str, str5, i, 25165824, 8388608, z2, null);
        }
    }

    public static boolean b() {
        return a;
    }

    public static void a(String str) {
        d().d(Q("ui"), R(str));
    }

    public static void a(String str, Throwable th) {
        d().c(Q("ui"), R(str), th);
    }

    public static void b(String str, Throwable th) {
        d().c(Q("core"), R(str), th);
    }

    public static void b(String str) {
        d().a(Q(com.alipay.sdk.m.g.b.m), R(str));
    }

    public static void c(String str) {
        d().d(Q(com.alipay.sdk.m.g.b.m), R(str));
    }

    public static void d(String str) {
        d().a(Q(SpeechConstant.TYPE_LOCAL), R(str));
    }

    public static void e(String str) {
        d().a(Q("remote"), R(str));
    }

    public static void f(String str) {
        d().a(Q("im_packet"), R(str));
    }

    public static void g(String str) {
        d().a(Q("room"), R(str));
    }

    public static void h(String str) {
        d().a(Q("QChat"), R(str));
    }

    public static void c(String str, Throwable th) {
        d().c(Q("QChat"), R(str), th);
    }

    public static void i(String str) {
        d().d(Q("room"), R(str));
    }

    public static void j(String str) {
        d().a(Q("room_packet"), R(str));
    }

    public static void k(String str) {
        d().a(Q("notify"), R(str));
    }

    public static void l(String str) {
        d().a(Q("mix_push"), R(str));
    }

    public static void m(String str) {
        d().c(Q("mix_push"), R(str));
    }

    public static void n(String str) {
        d().a(Q("qchat_mix_push"), R(str));
    }

    public static void o(String str) {
        d().d(Q("mix_push"), R(str));
    }

    public static void p(String str) {
        d().d(Q("qchat_push"), R(str));
    }

    public static void q(String str) {
        d().a(Q("res"), R(str));
    }

    public static void r(String str) {
        d().d(Q("res"), R(str));
    }

    public static void s(String str) {
        d().a(Q("audio"), R(str));
    }

    public static void t(String str) {
        d().a(Q("network"), R(str));
    }

    public static void u(String str) {
        d().d(Q("db"), R(str));
    }

    public static void v(String str) {
        d().a(Q("db"), R(str));
    }

    public static void d(String str, Throwable th) {
        d().c(Q("db"), R(str), th);
    }

    public static void w(String str) {
        d().a(Q("stat"), R(str));
    }

    public static void x(String str) {
        d().a(Q("ipc"), R(str));
    }

    public static void y(String str) {
        d().d(Q("session_ack"), R(str));
    }

    public static void z(String str) {
        d().a(Q("framework"), R(str));
    }

    public static void A(String str) {
        d().a(Q("mode"), R(str));
    }

    public static void B(String str) {
        d().d(Q("api"), R(str));
    }

    public static void C(String str) {
        d().a(Q("api"), R(str));
    }

    public static void D(String str) {
        d().a(Q("room_net"), R(str));
    }

    public static void E(String str) {
        d().d(Q("room_net"), R(str));
    }

    public static void F(String str) {
        d().c(Q("room_net"), R(str));
    }

    public static void G(String str) {
        if (b <= 3) {
            d().d(Q("statics"), R(str));
        }
    }

    public static void H(String str) {
        d().a(Q("statics"), R(str));
    }

    public static void a(String str, String str2) {
        if (b <= 3) {
            d().d(Q(str), R(str2));
        }
    }

    public static void I(String str) {
        if (b <= 3) {
            d().d(Q("debug"), R(str));
        }
    }

    public static void a(IMMessage iMMessage) {
        if (b <= 3) {
            d().d(Q("debug"), R("message = " + com.netease.nimlib.j.a.a(iMMessage)));
        }
    }

    public static void J(String str) {
        if (b <= 3) {
            d().d(Q("protocol"), R(str));
        }
    }

    public static void a(com.netease.nimlib.push.packet.a aVar) {
        if (b > 3 || aVar == null) {
            return;
        }
        d().d(Q("protocol"), aVar.toString());
    }

    public static void a(int i, int i2, String str) {
        if (b <= 3) {
            d().d(Q("protocol"), R("[" + i + "-" + i2 + "]," + str));
        }
    }

    public static void a(int i, int i2, String str, c cVar) {
        if (b <= 3) {
            d().d(Q("protocol"), R("[" + i + "-" + i2 + "]," + str + "=" + cVar));
        }
    }

    public static void a(int i, int i2, String str, Collection<?> collection) {
        if (b <= 3) {
            if (collection == null) {
                d().d(Q("protocol"), R("[" + i + "-" + i2 + "]," + str + "= null"));
                return;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                jSONArray.put(next != null ? next.toString() : null);
            }
            d().d(Q("protocol"), R("[" + i + "-" + i2 + "]," + str + "=" + jSONArray.toString()));
        }
    }

    public static void K(String str) {
        d().a(Q("highAvailable"), R(str));
    }

    public static int a(int i, String str, String str2, Throwable th) {
        if (b > i) {
            return 0;
        }
        d().a(i, Q("highAvailable"), g(str, str2), th);
        return 0;
    }

    public static void L(String str) {
        if (b <= 3) {
            d().d(Q("syncOpt"), str);
        }
    }

    public static void a(String str, Collection<?> collection) {
        if (b <= 3) {
            if (collection == null) {
                d().d(Q("syncOpt"), R(str + "= null"));
                return;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                jSONArray.put(next != null ? next.toString() : null);
            }
            d().d(Q("syncOpt"), R(str + "=" + jSONArray));
        }
    }

    public static void a(String str, Object... objArr) {
        if (b <= 3) {
            d().d(Q("syncOpt"), String.format(str, objArr));
        }
    }

    public static void M(String str) {
        if (b <= 3) {
            d().d(Q("ABTest"), R(str));
        }
    }
}
