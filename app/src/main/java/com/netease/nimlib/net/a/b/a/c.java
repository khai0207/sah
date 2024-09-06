package com.netease.nimlib.net.a.b.a;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.security.NetworkSecurityPolicy;
import android.text.TextUtils;
import com.netease.nimlib.d.g;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NosLinkLbs.java */
/* loaded from: classes.dex */
public class c {
    private static c h = new c();
    private d a;
    private long c;
    private long d;
    private boolean e;
    private boolean b = true;
    private Handler f = com.netease.nimlib.c.b.a.c().a();
    private AtomicBoolean g = new AtomicBoolean(false);

    public static c a() {
        return h;
    }

    private c() {
        this.e = true;
        if (Build.VERSION.SDK_INT >= 28) {
            this.e = NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
            com.netease.nimlib.log.b.d("NOS_LBS", "isSupportHttp:" + this.e);
        } else {
            this.e = true;
            com.netease.nimlib.log.b.d("NOS_LBS", "isSupportHttp is true!");
        }
        a(true);
    }

    private void a(boolean z) {
        String[] a = b.a(com.netease.nimlib.c.e(), com.netease.nimlib.net.a.b.d.a.a);
        String i = g.i();
        this.a = new d(a, !TextUtils.isEmpty(i) ? new String[]{i} : null);
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "load cached nos upload server addresses from SP" : "update nos upload server addresses from lbs");
        sb.append(", ip count=");
        sb.append(this.a.c());
        sb.append(", default ip count=");
        sb.append(this.a.b());
        com.netease.nimlib.log.b.d("NOS_LBS", sb.toString());
    }

    public synchronized void b() {
        Context e = com.netease.nimlib.c.e();
        com.netease.nimlib.log.b.d("NOS_LBS", "fetch NOS LBS on SDK init...,thread = " + Thread.currentThread());
        this.b = true;
        b.d(e);
        b(false);
        b.a(e);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028 A[Catch: all -> 0x0035, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000f, B:8:0x0020, B:10:0x0028, B:12:0x002d, B:18:0x0019), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000f, B:8:0x0020, B:10:0x0028, B:12:0x002d, B:18:0x0019), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized java.lang.String[] c() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.netease.nimlib.net.a.b.a.d r0 = r3.a     // Catch: java.lang.Throwable -> L35
            java.lang.String[] r0 = r0.a()     // Catch: java.lang.Throwable -> L35
            com.netease.nimlib.sdk.SDKOptions r1 = com.netease.nimlib.c.i()     // Catch: java.lang.Throwable -> L35
            boolean r1 = r1.preLoadServers     // Catch: java.lang.Throwable -> L35
            if (r1 != 0) goto L17
            com.netease.nimlib.net.a.b.a.d r1 = r3.a     // Catch: java.lang.Throwable -> L35
            int r1 = r1.c()     // Catch: java.lang.Throwable -> L35
            if (r1 <= 0) goto L1f
        L17:
            if (r0 == 0) goto L1f
            int r1 = r0.length     // Catch: java.lang.Throwable -> L35
            if (r1 != 0) goto L1d
            goto L1f
        L1d:
            r1 = 0
            goto L20
        L1f:
            r1 = 1
        L20:
            com.netease.nimlib.net.a.b.a.d r2 = r3.a     // Catch: java.lang.Throwable -> L35
            int r2 = r2.c()     // Catch: java.lang.Throwable -> L35
            if (r2 != 0) goto L2b
            r3.b(r1)     // Catch: java.lang.Throwable -> L35
        L2b:
            if (r1 == 0) goto L33
            com.netease.nimlib.net.a.b.a.d r0 = r3.a     // Catch: java.lang.Throwable -> L35
            java.lang.String[] r0 = r0.a()     // Catch: java.lang.Throwable -> L35
        L33:
            monitor-exit(r3)
            return r0
        L35:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.net.a.b.a.c.c():java.lang.String[]");
    }

    public synchronized void d() {
        if (System.currentTimeMillis() - this.d >= 300000) {
            this.b = true;
            com.netease.nimlib.log.b.d("NOS_LBS", "nos lbs reset all, should fetch nos lbs...");
            b(false);
            this.d = System.currentTimeMillis();
        }
    }

    private void b(boolean z) {
        if (z || this.b || System.currentTimeMillis() - this.c >= com.umeng.analytics.a.i) {
            $$Lambda$c$MBDuZjAWlO42vzEjOYPtC_q_gI __lambda_c_mbduzjawlo42vzejoyptc_q_gi = new Runnable() { // from class: com.netease.nimlib.net.a.b.a.-$$Lambda$c$MBDu-ZjAWlO42vzEjOYPtC_q_gI
                private final /* synthetic */ boolean f$1;

                public /* synthetic */ $$Lambda$c$MBDuZjAWlO42vzEjOYPtC_q_gI(boolean z2) {
                    r2 = z2;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    c.this.c(r2);
                }
            };
            if (z2) {
                __lambda_c_mbduzjawlo42vzejoyptc_q_gi.run();
            } else {
                if (this.g.get()) {
                    return;
                }
                this.f.post(__lambda_c_mbduzjawlo42vzejoyptc_q_gi);
                this.g.set(true);
            }
        }
    }

    public /* synthetic */ void c(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("fetch nos upload server addresses from lbs ");
        sb.append(z ? "now" : "on background");
        com.netease.nimlib.log.b.d("NOS_LBS", sb.toString());
        e();
        if (!this.b) {
            this.c = System.currentTimeMillis();
        }
        if (z) {
            return;
        }
        this.g.set(false);
    }

    private boolean e() {
        String c;
        boolean d;
        Context e = com.netease.nimlib.c.e();
        b.b(e);
        boolean z = false;
        try {
            c = b.c(e);
            d = !TextUtils.isEmpty(c) ? d(c) : false;
        } catch (Exception e2) {
            e = e2;
        }
        try {
            if (!TextUtils.isEmpty(c) && d) {
                return d;
            }
            String h2 = g.h();
            if (TextUtils.isEmpty(h2)) {
                return d;
            }
            for (String str : h2.split(";")) {
                d = a(str);
                if (d) {
                    return d;
                }
            }
            return d;
        } catch (Exception e3) {
            e = e3;
            z = d;
            com.netease.nimlib.log.b.f("NOS_LBS", "fetch nos lbs error, e=" + e.getMessage());
            e.printStackTrace();
            return z;
        }
    }

    private boolean a(String str) throws JSONException {
        String c = c(str);
        com.netease.nimlib.log.b.d("NOS_LBS", "fetch nos lbs, url=" + c);
        com.netease.nimlib.net.a.b.c.c b = b(c);
        if (b.a() == 200) {
            JSONObject b2 = b.b();
            com.netease.nimlib.log.b.d("NOS_LBS", "fetch nos lbs result: " + b2.toString());
            b.a(com.netease.nimlib.c.e(), b2, this.e);
            a(false);
            this.b = false;
            return true;
        }
        com.netease.nimlib.log.b.f("NOS_LBS", "fetch nos lbs failed, code=" + b.a());
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 13, insn: 0x0134: MOVE (r9 I:??[OBJECT, ARRAY]) = (r13 I:??[OBJECT, ARRAY]), block:B:81:0x0134 */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.netease.nimlib.net.a.b.c.c b(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.net.a.b.a.c.b(java.lang.String):com.netease.nimlib.net.a.b.c.c");
    }

    private String c(String str) {
        return String.format("%s?version=%s&bucketname=%s", str, "1.0", "nim");
    }

    private boolean d(String str) {
        try {
            boolean a = str.startsWith("http://") ? a(str.replace("http://", "https://")) : false;
            return !a ? a(str) : a;
        } catch (Throwable unused) {
            return false;
        }
    }
}
