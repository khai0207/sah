package com.netease.nimlib.n;

/* compiled from: PushEventSender.java */
/* loaded from: classes.dex */
public class j {
    private long a = 0;
    private boolean b = false;
    private long c = 0;
    private boolean d = false;

    /* compiled from: PushEventSender.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final j a = new j();
    }

    public static j a() {
        return a.a;
    }

    public void b() {
        if (com.netease.nimlib.h.h()) {
            return;
        }
        boolean a2 = com.netease.nimlib.n.f.a.a();
        this.b = a2;
        this.a = com.netease.nimlib.n.f.a.a(a2);
        com.netease.nimlib.log.b.G("startTrackLBS time = " + this.a);
    }

    public com.netease.nimlib.n.c.i a(int i, String str, String str2) {
        String str3;
        if (com.netease.nimlib.h.h() || this.a == 0) {
            return null;
        }
        try {
            com.netease.nimlib.n.c.i g = com.netease.nimlib.n.c.i.g();
            g.a(this.a);
            g.b(com.netease.nimlib.n.f.a.a(this.b));
            g.a(i);
            boolean z = i == 200;
            g.a(z);
            if (z) {
                str3 = "lbs success";
            } else {
                str3 = "lbs error, body: " + str2;
            }
            g.d(str3);
            g.b("HTTP");
            g.c(str);
            com.netease.nimlib.ipc.e.a(g);
            return g;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushEventSender", "stopTrackLBSExtension Exception", th);
            return null;
        }
    }

    public void c() {
        if (com.netease.nimlib.h.h()) {
            return;
        }
        boolean a2 = com.netease.nimlib.n.f.a.a();
        this.d = a2;
        this.c = com.netease.nimlib.n.f.a.a(a2);
        com.netease.nimlib.log.b.G("startTrackLink time = " + this.c);
    }

    public com.netease.nimlib.n.c.i a(String str, com.netease.nimlib.push.net.lbs.b bVar) {
        if (com.netease.nimlib.h.h() || this.c == 0) {
            return null;
        }
        try {
            com.netease.nimlib.n.c.i g = com.netease.nimlib.n.c.i.g();
            boolean z = false;
            g.a(false);
            g.a(this.c);
            g.b(com.netease.nimlib.n.f.a.a(this.d));
            g.b("TCP");
            g.c(bVar != null ? bVar.toString() : null);
            g.d(str);
            if (bVar != null && bVar.a()) {
                z = true;
            }
            g.b(z);
            com.netease.nimlib.ipc.e.a(g);
            return g;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushEventSender", "sendLinkException Exception", th);
            return null;
        }
    }

    public com.netease.nimlib.n.c.i a(com.netease.nimlib.push.net.lbs.b bVar) {
        if (com.netease.nimlib.h.h() || this.c == 0) {
            return null;
        }
        try {
            com.netease.nimlib.n.c.i g = com.netease.nimlib.n.c.i.g();
            boolean z = true;
            g.a(true);
            g.a(this.c);
            g.b(com.netease.nimlib.n.f.a.a(this.d));
            g.d("link success");
            g.b("TCP");
            g.c(bVar != null ? bVar.toString() : null);
            if (bVar == null || !bVar.a()) {
                z = false;
            }
            g.b(z);
            com.netease.nimlib.ipc.e.a(g);
            return g;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("PushEventSender", "sendLinkSuccess Exception", th);
            return null;
        }
    }
}
