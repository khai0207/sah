package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.m.i.a;
import com.alipay.sdk.m.o.a;
import com.alipay.sdk.m.o.b;
import com.alipay.sdk.m.q.g;
import com.alipay.sdk.m.q.k;
import com.alipay.sdk.m.q.m;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AuthTask {
    public static final Object c = g.class;
    public Activity a;
    public com.alipay.sdk.m.s.a b;

    /* loaded from: classes.dex */
    public class a implements g.e {
        public a() {
        }

        @Override // com.alipay.sdk.m.q.g.e
        public void a() {
            AuthTask.this.a();
        }

        @Override // com.alipay.sdk.m.q.g.e
        public void b() {
        }
    }

    public AuthTask(Activity activity) {
        this.a = activity;
        b.d().a(this.a);
        this.b = new com.alipay.sdk.m.s.a(activity, com.alipay.sdk.m.s.a.k);
    }

    private g.e b() {
        return new a();
    }

    private void c() {
        com.alipay.sdk.m.s.a aVar = this.b;
        if (aVar != null) {
            aVar.d();
        }
    }

    public synchronized String auth(String str, boolean z) {
        return innerAuth(new com.alipay.sdk.m.o.a(this.a, str, com.alipay.sdk.m.g.b.n), str, z);
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        com.alipay.sdk.m.o.a aVar;
        aVar = new com.alipay.sdk.m.o.a(this.a, str, "authV2");
        return k.a(aVar, innerAuth(aVar, str, z));
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x006b, code lost:
    
        if (com.alipay.sdk.m.i.a.x().r() == false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String innerAuth(com.alipay.sdk.m.o.a r7, java.lang.String r8, boolean r9) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.AuthTask.innerAuth(com.alipay.sdk.m.o.a, java.lang.String, boolean):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        com.alipay.sdk.m.s.a aVar = this.b;
        if (aVar != null) {
            aVar.a();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(android.app.Activity r4, java.lang.String r5, com.alipay.sdk.m.o.a r6) {
        /*
            r3 = this;
            r3.c()
            r0 = 0
            com.alipay.sdk.m.m.a r1 = new com.alipay.sdk.m.m.a     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            r1.<init>()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            com.alipay.sdk.m.l.b r4 = r1.a(r6, r4, r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            org.json.JSONObject r4 = r4.c()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            java.lang.String r5 = "form"
            org.json.JSONObject r4 = r4.optJSONObject(r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            java.lang.String r5 = "onload"
            org.json.JSONObject r4 = r4.optJSONObject(r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            java.util.List r4 = com.alipay.sdk.m.n.b.a(r4)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            r3.a()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            r5 = 0
        L25:
            int r1 = r4.size()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            if (r5 >= r1) goto L4a
            java.lang.Object r1 = r4.get(r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            com.alipay.sdk.m.n.b r1 = (com.alipay.sdk.m.n.b) r1     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            com.alipay.sdk.m.n.a r1 = r1.a()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            com.alipay.sdk.m.n.a r2 = com.alipay.sdk.m.n.a.WapPay     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            if (r1 != r2) goto L47
            java.lang.Object r4 = r4.get(r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            com.alipay.sdk.m.n.b r4 = (com.alipay.sdk.m.n.b) r4     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            java.lang.String r4 = r3.a(r6, r4)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            r3.a()
            return r4
        L47:
            int r5 = r5 + 1
            goto L25
        L4a:
            r3.a()
            goto L6b
        L4e:
            r4 = move-exception
            java.lang.String r5 = "biz"
            java.lang.String r1 = "H5AuthDataAnalysisError"
            com.alipay.sdk.m.g.a.a(r6, r5, r1, r4)     // Catch: java.lang.Throwable -> L86
            goto L68
        L57:
            r4 = move-exception
            com.alipay.sdk.m.f.c r5 = com.alipay.sdk.m.f.c.NETWORK_ERROR     // Catch: java.lang.Throwable -> L86
            int r5 = r5.b()     // Catch: java.lang.Throwable -> L86
            com.alipay.sdk.m.f.c r5 = com.alipay.sdk.m.f.c.b(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r0 = "net"
            com.alipay.sdk.m.g.a.a(r6, r0, r4)     // Catch: java.lang.Throwable -> L86
            r0 = r5
        L68:
            r3.a()
        L6b:
            if (r0 != 0) goto L77
            com.alipay.sdk.m.f.c r4 = com.alipay.sdk.m.f.c.FAILED
            int r4 = r4.b()
            com.alipay.sdk.m.f.c r0 = com.alipay.sdk.m.f.c.b(r4)
        L77:
            int r4 = r0.b()
            java.lang.String r5 = r0.a()
            java.lang.String r6 = ""
            java.lang.String r4 = com.alipay.sdk.m.f.b.a(r4, r5, r6)
            return r4
        L86:
            r4 = move-exception
            r3.a()
            goto L8c
        L8b:
            throw r4
        L8c:
            goto L8b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.AuthTask.b(android.app.Activity, java.lang.String, com.alipay.sdk.m.o.a):java.lang.String");
    }

    private String a(Activity activity, String str, com.alipay.sdk.m.o.a aVar) {
        String a2 = aVar.a(str);
        List<a.b> l = com.alipay.sdk.m.i.a.x().l();
        if (!com.alipay.sdk.m.i.a.x().g || l == null) {
            l = com.alipay.sdk.m.f.a.d;
        }
        if (m.a(aVar, this.a, l, true)) {
            g gVar = new g(activity, aVar, b());
            String a3 = gVar.a(a2, false);
            gVar.a();
            if (!TextUtils.equals(a3, g.j) && !TextUtils.equals(a3, g.k)) {
                return TextUtils.isEmpty(a3) ? com.alipay.sdk.m.f.b.a() : a3;
            }
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.d0);
            return b(activity, a2, aVar);
        }
        com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.e0);
        return b(activity, a2, aVar);
    }

    private String a(com.alipay.sdk.m.o.a aVar, com.alipay.sdk.m.n.b bVar) {
        String[] c2 = bVar.c();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL_ENCODING, c2[0]);
        Intent intent = new Intent(this.a, (Class<?>) H5AuthActivity.class);
        intent.putExtras(bundle);
        a.C0010a.a(aVar, intent);
        this.a.startActivity(intent);
        synchronized (c) {
            try {
                c.wait();
            } catch (InterruptedException unused) {
                return com.alipay.sdk.m.f.b.a();
            }
        }
        String d = com.alipay.sdk.m.f.b.d();
        return TextUtils.isEmpty(d) ? com.alipay.sdk.m.f.b.a() : d;
    }
}
