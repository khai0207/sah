package com.alipay.sdk.m.q;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import androidx.core.os.EnvironmentCompat;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.APayEntranceActivity;
import com.alipay.sdk.app.AlipayResultActivity;
import com.alipay.sdk.m.o.a;
import com.alipay.sdk.m.q.m;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class g {
    public static final String j = "failed";
    public static final String k = "scheme_failed";
    public Activity a;
    public volatile IAlixPay b;
    public boolean d;
    public e e;
    public final com.alipay.sdk.m.o.a f;
    public final Object c = IAlixPay.class;
    public boolean g = false;
    public String h = null;
    public String i = null;

    /* loaded from: classes.dex */
    public class a implements AlipayResultActivity.a {
        public final /* synthetic */ CountDownLatch a;

        public a(CountDownLatch countDownLatch) {
            this.a = countDownLatch;
        }

        @Override // com.alipay.sdk.app.AlipayResultActivity.a
        public void a(int i, String str, String str2) {
            g.this.h = com.alipay.sdk.m.f.b.a(i, str, str2);
            this.a.countDown();
        }
    }

    /* loaded from: classes.dex */
    public class b implements APayEntranceActivity.a {
        public final /* synthetic */ Object a;

        public b(Object obj) {
            this.a = obj;
        }

        @Override // com.alipay.sdk.app.APayEntranceActivity.a
        public void a(String str) {
            g.this.i = str;
            synchronized (this.a) {
                try {
                    this.a.notify();
                } finally {
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends IRemoteServiceCallback.Stub {
        public c() {
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public int getVersion() throws RemoteException {
            return 4;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public boolean isHideLoadingScreen() throws RemoteException {
            return false;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void payEnd(boolean z, String str) throws RemoteException {
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void r03(String str, String str2, Map map) throws RemoteException {
            com.alipay.sdk.m.g.a.a(g.this.f, com.alipay.sdk.m.g.b.p, str, str2);
            if (TextUtils.equals(str2, "ActivityStartSuccess")) {
                if (g.this.e != null) {
                    g.this.e.a();
                }
                if (g.this.f != null) {
                    g.this.f.a(true);
                }
            }
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            if (bundle == null) {
                bundle = new Bundle();
            }
            try {
                bundle.putInt("CallingPid", i);
                intent.putExtras(bundle);
            } catch (Exception e) {
                com.alipay.sdk.m.g.a.a(g.this.f, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.X, e);
            }
            intent.setClassName(str, str2);
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                    ActivityManager.getMyMemoryState(runningAppProcessInfo);
                    com.alipay.sdk.m.g.a.a(g.this.f, com.alipay.sdk.m.g.b.l, "isFg", runningAppProcessInfo.processName + "|" + runningAppProcessInfo.importance + "|");
                }
            } catch (Throwable unused) {
            }
            try {
                if (g.this.a == null) {
                    com.alipay.sdk.m.g.a.b(g.this.f, com.alipay.sdk.m.g.b.l, "ErrActNull", "");
                    Context a = g.this.f.a();
                    if (a != null) {
                        a.startActivity(intent);
                        return;
                    }
                    return;
                }
                long elapsedRealtime = SystemClock.elapsedRealtime();
                g.this.a.startActivity(intent);
                com.alipay.sdk.m.g.a.a(g.this.f, com.alipay.sdk.m.g.b.l, "stAct2", "" + (SystemClock.elapsedRealtime() - elapsedRealtime));
            } catch (Throwable th) {
                com.alipay.sdk.m.g.a.a(g.this.f, com.alipay.sdk.m.g.b.l, "ErrActNull", th);
                throw th;
            }
        }

        public /* synthetic */ c(g gVar, a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public class d implements ServiceConnection {
        public d() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.alipay.sdk.m.g.a.a(g.this.f, com.alipay.sdk.m.g.b.l, "srvCon");
            synchronized (g.this.c) {
                g.this.b = IAlixPay.Stub.asInterface(iBinder);
                g.this.c.notify();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            com.alipay.sdk.m.g.a.a(g.this.f, com.alipay.sdk.m.g.b.l, "srvDis");
            g.this.b = null;
        }

        public /* synthetic */ d(g gVar, a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        void a();

        void b();
    }

    public g(Activity activity, com.alipay.sdk.m.o.a aVar, e eVar) {
        this.a = activity;
        this.f = aVar;
        this.e = eVar;
        com.alipay.sdk.m.q.d.d(com.alipay.sdk.m.h.a.z, com.alipay.sdk.m.e.a.e);
    }

    private String b(String str, String str2) {
        JSONObject jSONObject;
        String str3;
        Object obj = new Object();
        String a2 = m.a(32);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSAStart", a2 + "|" + elapsedRealtime);
        a.C0010a.a(this.f, a2);
        APayEntranceActivity.h.put(a2, new b(obj));
        try {
            HashMap<String, String> a3 = com.alipay.sdk.m.o.a.a(this.f);
            a3.put("ts_intent", String.valueOf(elapsedRealtime));
            jSONObject = new JSONObject(a3);
        } catch (Throwable th) {
            try {
                com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSALocEx", th);
                jSONObject = null;
            } catch (InterruptedException e2) {
                com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSAWaiting", e2);
                return com.alipay.sdk.m.f.b.a(com.alipay.sdk.m.f.c.PAY_WAITTING.b(), com.alipay.sdk.m.f.c.PAY_WAITTING.a(), "");
            } catch (Throwable th2) {
                com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSAEx", th2);
                return k;
            }
        }
        Intent intent = new Intent(this.a, (Class<?>) APayEntranceActivity.class);
        intent.putExtra(APayEntranceActivity.d, str);
        intent.putExtra(APayEntranceActivity.e, str2);
        intent.putExtra(APayEntranceActivity.f, a2);
        if (jSONObject != null) {
            intent.putExtra(APayEntranceActivity.g, jSONObject.toString());
        }
        com.alipay.sdk.m.g.a.a(this.a, this.f, str, this.f.d);
        try {
            if (this.a != null) {
                this.a.startActivity(intent);
            } else {
                com.alipay.sdk.m.g.a.b(this.f, com.alipay.sdk.m.g.b.l, "ErrActNull", "");
                Context a4 = this.f.a();
                if (a4 != null) {
                    a4.startActivity(intent);
                }
            }
            synchronized (obj) {
                obj.wait();
            }
            String str4 = this.i;
            try {
                str3 = k.a(this.f, str4).get(k.a);
                if (str3 == null) {
                    str3 = Constants.NULL_VERSION_ID;
                }
            } catch (Throwable th3) {
                com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSAStatEx", th3);
                str3 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSADone-" + str3);
            if (!TextUtils.isEmpty(str4)) {
                return str4;
            }
            com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSAEmpty");
            return k;
        } catch (Throwable th4) {
            com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "ErrActNull", th4);
            throw th4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x005a A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:12:0x0023, B:14:0x002b, B:17:0x0033, B:20:0x003c, B:23:0x0042, B:26:0x004d, B:27:0x0056, B:29:0x005a, B:30:0x005c, B:32:0x0066, B:75:0x0052), top: B:11:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0066 A[Catch: all -> 0x00b0, TRY_LEAVE, TryCatch #0 {all -> 0x00b0, blocks: (B:12:0x0023, B:14:0x002b, B:17:0x0033, B:20:0x003c, B:23:0x0042, B:26:0x004d, B:27:0x0056, B:29:0x005a, B:30:0x005c, B:32:0x0066, B:75:0x0052), top: B:11:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(java.lang.String r9, boolean r10) {
        /*
            r8 = this;
            java.lang.String r0 = ""
            r1 = 0
            com.alipay.sdk.m.i.a r2 = com.alipay.sdk.m.i.a.x()     // Catch: java.lang.Throwable -> Lb3
            java.util.List r2 = r2.l()     // Catch: java.lang.Throwable -> Lb3
            com.alipay.sdk.m.i.a r3 = com.alipay.sdk.m.i.a.x()     // Catch: java.lang.Throwable -> Lb3
            boolean r3 = r3.g     // Catch: java.lang.Throwable -> Lb3
            if (r3 == 0) goto L15
            if (r2 != 0) goto L17
        L15:
            java.util.List<com.alipay.sdk.m.i.a$b> r2 = com.alipay.sdk.m.f.a.d     // Catch: java.lang.Throwable -> Lb3
        L17:
            com.alipay.sdk.m.o.a r3 = r8.f     // Catch: java.lang.Throwable -> Lb3
            android.app.Activity r4 = r8.a     // Catch: java.lang.Throwable -> Lb3
            com.alipay.sdk.m.q.m$c r2 = com.alipay.sdk.m.q.m.a(r3, r4, r2)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r3 = "failed"
            if (r2 == 0) goto Lb2
            com.alipay.sdk.m.o.a r4 = r8.f     // Catch: java.lang.Throwable -> Lb0
            boolean r4 = r2.a(r4)     // Catch: java.lang.Throwable -> Lb0
            if (r4 != 0) goto Lb2
            boolean r4 = r2.a()     // Catch: java.lang.Throwable -> Lb0
            if (r4 == 0) goto L33
            goto Lb2
        L33:
            android.content.pm.PackageInfo r4 = r2.a     // Catch: java.lang.Throwable -> Lb0
            boolean r4 = com.alipay.sdk.m.q.m.a(r4)     // Catch: java.lang.Throwable -> Lb0
            if (r4 == 0) goto L3c
            return r3
        L3c:
            android.content.pm.PackageInfo r3 = r2.a     // Catch: java.lang.Throwable -> Lb0
            if (r3 == 0) goto L52
            java.lang.String r3 = "com.eg.android.AlipayGphone"
            android.content.pm.PackageInfo r4 = r2.a     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r4 = r4.packageName     // Catch: java.lang.Throwable -> Lb0
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> Lb0
            if (r3 == 0) goto L4d
            goto L52
        L4d:
            android.content.pm.PackageInfo r3 = r2.a     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r0 = r3.packageName     // Catch: java.lang.Throwable -> Lb0
            goto L56
        L52:
            java.lang.String r0 = com.alipay.sdk.m.q.m.b()     // Catch: java.lang.Throwable -> Lb0
        L56:
            android.content.pm.PackageInfo r3 = r2.a     // Catch: java.lang.Throwable -> Lb0
            if (r3 == 0) goto L5c
            android.content.pm.PackageInfo r1 = r2.a     // Catch: java.lang.Throwable -> Lb0
        L5c:
            com.alipay.sdk.m.i.a r3 = com.alipay.sdk.m.i.a.x()     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r3 = r3.c()     // Catch: java.lang.Throwable -> Lb0
            if (r3 == 0) goto Lbe
            int r4 = r3.length()     // Catch: java.lang.Throwable -> Lb0
            if (r4 <= 0) goto Lbe
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Lae
            r4.<init>(r3)     // Catch: java.lang.Throwable -> Lae
            org.json.JSONObject r3 = r4.optJSONObject(r0)     // Catch: java.lang.Throwable -> Lae
            if (r3 == 0) goto Lbe
            int r4 = r3.length()     // Catch: java.lang.Throwable -> Lae
            if (r4 <= 0) goto Lbe
            java.util.Iterator r4 = r3.keys()     // Catch: java.lang.Throwable -> Lae
        L81:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> Lae
            if (r5 == 0) goto Lbe
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> Lae
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> Lae
            int r6 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.Throwable -> Lae
            if (r1 == 0) goto L81
            int r7 = r1.versionCode     // Catch: java.lang.Throwable -> Lae
            if (r7 < r6) goto L81
            java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lae
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lae
            com.alipay.sdk.m.i.a r6 = com.alipay.sdk.m.i.a.x()     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lae
            android.app.Activity r7 = r8.a     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lae
            boolean r5 = r6.a(r7, r5)     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lae
            r8.g = r5     // Catch: java.lang.Exception -> L81 java.lang.Throwable -> Lae
            if (r5 == 0) goto L81
            goto Lbe
        Lae:
            goto Lbe
        Lb0:
            r3 = move-exception
            goto Lb5
        Lb2:
            return r3
        Lb3:
            r3 = move-exception
            r2 = r1
        Lb5:
            com.alipay.sdk.m.o.a r4 = r8.f
            java.lang.String r5 = "biz"
            java.lang.String r6 = "CheckClientSignEx"
            com.alipay.sdk.m.g.a.a(r4, r5, r6, r3)
        Lbe:
            r3 = 0
            com.alipay.sdk.m.o.a r4 = r8.f
            if (r4 == 0) goto Ld9
            java.lang.String r4 = r4.g
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto Ld9
            com.alipay.sdk.m.o.a r3 = r8.f
            java.lang.String r3 = r3.g
            java.lang.String r3 = r3.toLowerCase()
            java.lang.String r4 = "auth"
            boolean r3 = r3.contains(r4)
        Ld9:
            if (r10 != 0) goto Ldf
            boolean r10 = r8.g
            if (r10 == 0) goto Le6
        Ldf:
            if (r3 != 0) goto Le6
            java.lang.String r9 = r8.a(r9, r0, r1)
            return r9
        Le6:
            java.lang.String r9 = r8.a(r9, r0, r1, r2)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.m.q.g.a(java.lang.String, boolean):java.lang.String");
    }

    private void a(m.c cVar) throws InterruptedException {
        PackageInfo packageInfo;
        if (cVar == null || (packageInfo = cVar.a) == null) {
            return;
        }
        String str = packageInfo.packageName;
        Intent intent = new Intent();
        intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
        try {
            this.a.startActivity(intent);
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.b0, th);
        }
        Thread.sleep(200L);
    }

    private String a(String str, String str2, PackageInfo packageInfo) {
        String str3 = packageInfo != null ? packageInfo.versionName : "";
        com.alipay.sdk.m.q.d.d(com.alipay.sdk.m.h.a.z, "pay payInvokeAct");
        com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.V, str2 + "|" + str3);
        Activity activity = this.a;
        com.alipay.sdk.m.o.a aVar = this.f;
        com.alipay.sdk.m.g.a.a(activity, aVar, str, aVar.d);
        return b(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(java.lang.String r7, java.lang.String r8, android.content.pm.PackageInfo r9, com.alipay.sdk.m.q.m.c r10) {
        /*
            Method dump skipped, instructions count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.m.q.g.a(java.lang.String, java.lang.String, android.content.pm.PackageInfo, com.alipay.sdk.m.q.m$c):java.lang.String");
    }

    private String a(String str, String str2) {
        String str3;
        JSONObject jSONObject;
        String str4;
        String str5;
        String str6;
        String str7 = str;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String a2 = m.a(32);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSPStart", a2 + "|" + elapsedRealtime);
        a.C0010a.a(this.f, a2);
        AlipayResultActivity.a.put(a2, new a(countDownLatch));
        try {
            try {
                String[] split = str7.split(com.alipay.sdk.m.o.a.l, -1);
                int length = split.length;
                int i = 0;
                while (true) {
                    jSONObject = null;
                    if (i >= length) {
                        str4 = "";
                        str5 = str4;
                        str6 = null;
                        break;
                    }
                    str6 = split[i];
                    if (str6.startsWith(com.alipay.sdk.m.o.a.n)) {
                        String substring = str6.substring(str6.indexOf("{"), str6.lastIndexOf(h.d) + 1);
                        int indexOf = str6.indexOf(substring);
                        str5 = str6.substring(0, indexOf);
                        str4 = str6.substring(indexOf + substring.length());
                        JSONObject jSONObject2 = new JSONObject(substring);
                        if (jSONObject2.optString("sc").equals("h5tonative")) {
                            jSONObject2.put("sc", "h5tonative_scheme");
                        } else {
                            jSONObject2.put("sc", "h5tonative_sdkscheme");
                        }
                        jSONObject = jSONObject2;
                    } else {
                        i++;
                    }
                }
            } catch (Exception e2) {
                try {
                    com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSPSCReplaceEx", e2, Base64.encodeToString(str.getBytes(), 2));
                } catch (InterruptedException e3) {
                    com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSPWaiting", e3);
                    return com.alipay.sdk.m.f.b.a(com.alipay.sdk.m.f.c.PAY_WAITTING.b(), com.alipay.sdk.m.f.c.PAY_WAITTING.a(), "");
                }
            }
            if (!TextUtils.isEmpty(str6)) {
                if (str7.indexOf(str6) == str7.lastIndexOf(str6)) {
                    str7 = str7.replace(str6, str5 + jSONObject.toString() + str4);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("sourcePid", Binder.getCallingPid());
                    jSONObject3.put(com.alipay.sdk.m.h.b.d, str7);
                    jSONObject3.put("pkgName", this.a.getPackageName());
                    jSONObject3.put("session", a2);
                    String encodeToString = Base64.encodeToString(jSONObject3.toString().getBytes("UTF-8"), 2);
                    Uri.Builder appendQueryParameter = new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", "20000125");
                    appendQueryParameter.appendQueryParameter("mqpSchemePay", encodeToString);
                    try {
                        HashMap<String, String> a3 = com.alipay.sdk.m.o.a.a(this.f);
                        a3.put("ts_scheme", String.valueOf(elapsedRealtime));
                        appendQueryParameter.appendQueryParameter("mqpLoc", new JSONObject(a3).toString());
                    } catch (Throwable th) {
                        com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSPLocEx", th);
                    }
                    String uri = appendQueryParameter.build().toString();
                    Intent intent = new Intent();
                    intent.setPackage(str2);
                    intent.addFlags(268435456);
                    intent.setData(Uri.parse(uri));
                    com.alipay.sdk.m.g.a.a(this.a, this.f, str7, this.f.d);
                    this.a.startActivity(intent);
                    com.alipay.sdk.m.q.d.d(com.alipay.sdk.m.h.a.z, "pay scheme waiting " + uri);
                    countDownLatch.await();
                    String str8 = this.h;
                    try {
                        str3 = k.a(this.f, str8).get(k.a);
                        if (str3 == null) {
                            str3 = Constants.NULL_VERSION_ID;
                        }
                    } catch (Throwable th2) {
                        com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSPStatEx", th2);
                        str3 = EnvironmentCompat.MEDIA_UNKNOWN;
                    }
                    com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSPDone-" + str3);
                    if (!TextUtils.isEmpty(str8)) {
                        return str8;
                    }
                    com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSPEmpty");
                    return k;
                }
                throw new RuntimeException("multi ctx_args");
            }
            throw new RuntimeException("empty ctx_args");
        } catch (Throwable th3) {
            com.alipay.sdk.m.g.a.a(this.f, com.alipay.sdk.m.g.b.l, "BSPEx", th3);
            return k;
        }
    }

    public static boolean a(String str, Context context, com.alipay.sdk.m.o.a aVar) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.setClassName(str, "com.alipay.android.msp.ui.views.MspContainerActivity");
            if (intent.resolveActivityInfo(context.getPackageManager(), 0) != null) {
                return true;
            }
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "BSPDetectFail");
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "BSPDetectFail", th);
            return false;
        }
    }

    private Pair<String, Boolean> a(String str, String str2, com.alipay.sdk.m.o.a aVar) {
        int i;
        d dVar;
        IRemoteServiceCallback iRemoteServiceCallback;
        Activity activity;
        int i2;
        long elapsedRealtime;
        StringBuilder sb;
        String a2;
        boolean z;
        Activity activity2;
        Activity activity3;
        Intent intent = new Intent();
        intent.setPackage(str2);
        intent.setAction(m.c(str2));
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        sb2.append(elapsedRealtime2);
        sb2.append("|");
        sb2.append(str != null ? str.length() : 0);
        com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.P, sb2.toString());
        com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
        try {
            try {
                if (!com.alipay.sdk.m.i.a.x().f()) {
                    ComponentName startService = this.a.getApplication().startService(intent);
                    com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "stSrv", startService != null ? startService.getPackageName() : Constants.NULL_VERSION_ID);
                } else {
                    com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "stSrv", "skipped");
                }
            } catch (Throwable th) {
                com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.I, th);
            }
            if (com.alipay.sdk.m.i.a.x().b()) {
                i = 65;
                com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "bindFlg", "imp");
            } else {
                i = 1;
            }
            d dVar2 = new d(this, null);
            if (this.a.getApplicationContext().bindService(intent, dVar2, i)) {
                synchronized (this.c) {
                    if (this.b == null) {
                        try {
                            this.c.wait(com.alipay.sdk.m.i.a.x().k());
                        } catch (InterruptedException e2) {
                            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.J, e2);
                        }
                    }
                }
                IAlixPay iAlixPay = this.b;
                try {
                    if (iAlixPay == null) {
                        com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.C, "");
                        Pair<String, Boolean> pair = new Pair<>(j, true);
                        try {
                            this.a.getApplicationContext().unbindService(dVar2);
                        } catch (Throwable th2) {
                            com.alipay.sdk.m.q.d.a(th2);
                        }
                        com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.R, "" + SystemClock.elapsedRealtime());
                        com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
                        this.b = null;
                        if (this.d && (activity3 = this.a) != null) {
                            activity3.setRequestedOrientation(0);
                            this.d = false;
                        }
                        return pair;
                    }
                    long elapsedRealtime3 = SystemClock.elapsedRealtime();
                    com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.Q, "" + elapsedRealtime3);
                    if (this.e != null) {
                        this.e.b();
                    }
                    if (this.a.getRequestedOrientation() == 0) {
                        this.a.setRequestedOrientation(1);
                        this.d = true;
                    }
                    try {
                        i2 = iAlixPay.getVersion();
                    } catch (Throwable th3) {
                        com.alipay.sdk.m.q.d.a(th3);
                        i2 = 0;
                    }
                    iRemoteServiceCallback = new c(this, null);
                    try {
                        if (i2 >= 3) {
                            iAlixPay.registerCallback03(iRemoteServiceCallback, str, null);
                        } else {
                            iAlixPay.registerCallback(iRemoteServiceCallback);
                        }
                        elapsedRealtime = SystemClock.elapsedRealtime();
                        sb = new StringBuilder();
                    } catch (Throwable th4) {
                        th = th4;
                        dVar = dVar2;
                    }
                    try {
                        sb.append("");
                        sb.append(elapsedRealtime);
                        com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.S, sb.toString());
                        if (i2 >= 3) {
                            iAlixPay.r03(com.alipay.sdk.m.g.b.l, "bind_pay", null);
                        }
                        try {
                            if (i2 >= 2) {
                                Map a3 = com.alipay.sdk.m.o.a.a(aVar);
                                a3.put("ts_bind", String.valueOf(elapsedRealtime2));
                                a3.put("ts_bend", String.valueOf(elapsedRealtime3));
                                a3.put("ts_pay", String.valueOf(elapsedRealtime));
                                a2 = iAlixPay.pay02(str, a3);
                            } else {
                                a2 = iAlixPay.Pay(str);
                            }
                        } catch (Throwable th5) {
                            if (this.f != null && !this.f.d()) {
                                com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.F, th5);
                            }
                            a2 = com.alipay.sdk.m.f.b.a();
                        }
                        String str3 = a2;
                        try {
                            iAlixPay.unregisterCallback(iRemoteServiceCallback);
                        } catch (Throwable th6) {
                            com.alipay.sdk.m.q.d.a(th6);
                        }
                        try {
                            this.a.getApplicationContext().unbindService(dVar2);
                        } catch (Throwable th7) {
                            com.alipay.sdk.m.q.d.a(th7);
                        }
                        com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.R, "" + SystemClock.elapsedRealtime());
                        com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
                        this.b = null;
                        if (!this.d || (activity2 = this.a) == null) {
                            z = false;
                        } else {
                            z = false;
                            activity2.setRequestedOrientation(0);
                            this.d = false;
                        }
                        return new Pair<>(str3, Boolean.valueOf(z));
                    } catch (Throwable th8) {
                        th = th8;
                        dVar = dVar2;
                        try {
                            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.C, th, "in_bind");
                            Pair<String, Boolean> pair2 = new Pair<>(j, true);
                            if (iRemoteServiceCallback != null) {
                                try {
                                    iAlixPay.unregisterCallback(iRemoteServiceCallback);
                                } catch (Throwable th9) {
                                    com.alipay.sdk.m.q.d.a(th9);
                                }
                            }
                            try {
                                this.a.getApplicationContext().unbindService(dVar);
                            } catch (Throwable th10) {
                                com.alipay.sdk.m.q.d.a(th10);
                            }
                            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.R, "" + SystemClock.elapsedRealtime());
                            com.alipay.sdk.m.g.a.a(this.a, aVar, str, aVar.d);
                            this.b = null;
                            if (this.d && (activity = this.a) != null) {
                                activity.setRequestedOrientation(0);
                                this.d = false;
                            }
                            return pair2;
                        } finally {
                        }
                    }
                } catch (Throwable th11) {
                    th = th11;
                    dVar = dVar2;
                    iRemoteServiceCallback = null;
                }
            } else {
                throw new Throwable("bindService fail");
            }
        } catch (Throwable th12) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.H, th12);
            return new Pair<>(j, true);
        }
    }

    public void a() {
        this.a = null;
        this.e = null;
    }
}
