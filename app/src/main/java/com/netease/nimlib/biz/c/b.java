package com.netease.nimlib.biz.c;

import android.os.Handler;
import com.netease.nimlib.biz.e.a;

/* compiled from: ResponseDispatcher.java */
/* loaded from: classes.dex */
public class b {
    protected e a;
    private com.netease.nimlib.c.b.b b;
    private h c;

    /* compiled from: ResponseDispatcher.java */
    /* loaded from: classes.dex */
    public static class a extends com.netease.nimlib.biz.e.a {
        @Override // com.netease.nimlib.biz.e.a
        public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
            return null;
        }
    }

    protected boolean a(com.netease.nimlib.biz.e.a aVar) {
        return false;
    }

    public b(e eVar, com.netease.nimlib.c.b.b bVar, h hVar) {
        this.b = bVar;
        this.c = hVar;
        this.a = eVar;
    }

    public void a(a.C0029a c0029a) {
        b(c0029a.a, c0029a.b, c0029a.c);
    }

    private void b(com.netease.nimlib.push.packet.a aVar, com.netease.nimlib.push.packet.c.f fVar, int i) {
        c(aVar, fVar, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c(com.netease.nimlib.push.packet.a r7, com.netease.nimlib.push.packet.c.f r8, int r9) {
        /*
            r6 = this;
            com.netease.nimlib.biz.e.a r0 = r6.a(r7, r8)
            if (r0 != 0) goto L7
            return
        L7:
            r0.a(r7)
            r0.a(r9)
            boolean r1 = r0.k()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L1a
            if (r8 != 0) goto L18
            goto L1a
        L18:
            r1 = 0
            goto L1b
        L1a:
            r1 = 1
        L1b:
            r4 = 0
            if (r1 != 0) goto L3d
            com.netease.nimlib.push.packet.c.f r8 = r0.a(r8)     // Catch: java.lang.Throwable -> L36
            if (r8 == 0) goto L25
            r2 = 1
        L25:
            if (r2 == 0) goto L3f
            com.netease.nimlib.push.packet.a r5 = new com.netease.nimlib.push.packet.a     // Catch: java.lang.Throwable -> L34
            r5.<init>()     // Catch: java.lang.Throwable -> L34
            r8.a(r5)     // Catch: java.lang.Throwable -> L31
            r4 = r5
            goto L3f
        L31:
            r3 = move-exception
            r4 = r5
            goto L38
        L34:
            r3 = move-exception
            goto L38
        L36:
            r3 = move-exception
            r8 = r4
        L38:
            r3.printStackTrace()
            r3 = r1
            goto L3f
        L3d:
            r3 = r1
            r8 = r4
        L3f:
            if (r3 == 0) goto L7b
            if (r2 == 0) goto L62
            java.lang.String r1 = r7.o()
            r4.a(r1)
            long r1 = r7.p()
            r4.a(r1)
            long r1 = r7.q()
            r4.b(r1)
            long r1 = r7.r()
            r4.c(r1)
            r6.a(r4, r8, r9)
        L62:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "handle packet: "
            r8.append(r9)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            java.lang.String r8 = "core"
            com.netease.nimlib.log.b.d(r8, r7)
            r6.b(r0)
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.biz.c.b.c(com.netease.nimlib.push.packet.a, com.netease.nimlib.push.packet.c.f, int):void");
    }

    protected void a(com.netease.nimlib.push.packet.a aVar, com.netease.nimlib.push.packet.c.f fVar, int i) {
        c(aVar, fVar, i);
    }

    protected com.netease.nimlib.biz.e.a a(com.netease.nimlib.push.packet.a aVar, com.netease.nimlib.push.packet.c.f fVar) {
        com.netease.nimlib.biz.e.a d = this.a.d(aVar);
        return d == null ? new a() : d;
    }

    private Handler a() {
        return com.netease.nimlib.c.b.a.a(com.netease.nimlib.c.e());
    }

    private void b(final com.netease.nimlib.biz.e.a aVar) {
        try {
            Runnable runnable = new Runnable() { // from class: com.netease.nimlib.biz.c.-$$Lambda$b$B_pUqXHBWjqIqj1rGTH3-SYazqc
                @Override // java.lang.Runnable
                public final void run() {
                    b.this.c(aVar);
                }
            };
            if (a(aVar)) {
                a().post(runnable);
            } else {
                aVar.b(this.b.c());
                this.b.a(runnable, this.a.b(aVar.j()).intValue());
            }
        } catch (Throwable th) {
            th.printStackTrace();
            com.netease.nimlib.log.b.e("core", "process response exception: " + th + " on packet: " + aVar.j(), th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(com.netease.nimlib.biz.e.a aVar) {
        com.netease.nimlib.biz.c.a c = this.a.c(aVar.j());
        try {
            if (this.c != null) {
                this.c.a(aVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("core", "onPreProcess response exception: " + th + " on packet: " + aVar.j(), th);
        }
        if (c != null) {
            try {
                com.netease.nimlib.log.b.N("handler response " + aVar.j());
                c.a(aVar);
            } catch (Throwable th2) {
                th2.printStackTrace();
                com.netease.nimlib.log.b.e("core", "process response exception: " + th2 + " on packet: " + aVar.j(), th2);
            }
        }
        try {
            if (this.c != null) {
                this.c.b(aVar);
            }
        } catch (Throwable th3) {
            com.netease.nimlib.log.b.e("core", "onProcessed response exception: " + th3 + " on packet: " + aVar.j(), th3);
        }
    }
}
