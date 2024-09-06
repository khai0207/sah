package com.alipay.sdk.m.i;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ConditionVariable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.sdk.app.OpenAuthTask;
import com.alipay.sdk.m.q.d;
import com.alipay.sdk.m.q.m;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class b {
    public static final String d = "virtualImeiAndImsi";
    public static final String e = "virtual_imei";
    public static final String f = "virtual_imsi";
    public static volatile b g;
    public String a;
    public String b = "sdk-and-lite";
    public String c;

    /* loaded from: classes.dex */
    public static class a implements APSecuritySdk.InitResultListener {
        public final /* synthetic */ String[] a;
        public final /* synthetic */ ConditionVariable b;

        public a(String[] strArr, ConditionVariable conditionVariable) {
            this.a = strArr;
            this.b = conditionVariable;
        }

        @Override // com.alipay.apmobilesecuritysdk.face.APSecuritySdk.InitResultListener
        public void onResult(APSecuritySdk.TokenResult tokenResult) {
            if (tokenResult != null) {
                this.a[0] = tokenResult.apdidToken;
            }
            this.b.open();
        }
    }

    /* renamed from: com.alipay.sdk.m.i.b$b */
    /* loaded from: classes.dex */
    public static class CallableC0008b implements Callable<String> {
        public final /* synthetic */ com.alipay.sdk.m.o.a a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ HashMap c;

        public CallableC0008b(com.alipay.sdk.m.o.a aVar, Context context, HashMap hashMap) {
            this.a = aVar;
            this.b = context;
            this.c = hashMap;
        }

        @Override // java.util.concurrent.Callable
        public String call() throws Exception {
            return b.c(this.a, this.b, this.c);
        }
    }

    public b() {
        String a2 = com.alipay.sdk.m.f.a.a();
        if (com.alipay.sdk.m.f.a.b()) {
            return;
        }
        this.b += '_' + a2;
    }

    public static synchronized b b() {
        b bVar;
        synchronized (b.class) {
            if (g == null) {
                g = new b();
            }
            bVar = g;
        }
        return bVar;
    }

    public static String c() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(OpenAuthTask.OK) + 1000);
    }

    public static String d() {
        return "-1;-1";
    }

    public static String e() {
        return "1";
    }

    public static String f() {
        String b;
        Context b2 = com.alipay.sdk.m.o.b.d().b();
        SharedPreferences sharedPreferences = b2.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(e, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (TextUtils.isEmpty(com.alipay.sdk.m.p.a.a(b2).d())) {
            b = c();
        } else {
            b = com.alipay.sdk.m.q.b.b(b2).b();
        }
        String str = b;
        sharedPreferences.edit().putString(e, str).apply();
        return str;
    }

    public static String g() {
        String c;
        Context b = com.alipay.sdk.m.o.b.d().b();
        SharedPreferences sharedPreferences = b.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(f, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (TextUtils.isEmpty(com.alipay.sdk.m.p.a.a(b).d())) {
            String c2 = com.alipay.sdk.m.o.b.d().c();
            if (!TextUtils.isEmpty(c2) && c2.length() >= 18) {
                c = c2.substring(3, 18);
            } else {
                c = c();
            }
        } else {
            c = com.alipay.sdk.m.q.b.b(b).c();
        }
        String str = c;
        sharedPreferences.edit().putString(f, str).apply();
        return str;
    }

    public String a() {
        return this.c;
    }

    public static synchronized void a(String str) {
        synchronized (b.class) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            PreferenceManager.getDefaultSharedPreferences(com.alipay.sdk.m.o.b.d().b()).edit().putString(com.alipay.sdk.m.h.b.i, str).apply();
            com.alipay.sdk.m.h.a.e = str;
        }
    }

    public static String b(Context context, boolean z) {
        WifiInfo connectionInfo;
        return (z || (connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo()) == null) ? "-1" : connectionInfo.getSSID();
    }

    public static String c(com.alipay.sdk.m.o.a aVar, Context context, HashMap<String, String> hashMap) {
        String[] strArr = {""};
        try {
            APSecuritySdk aPSecuritySdk = APSecuritySdk.getInstance(context);
            ConditionVariable conditionVariable = new ConditionVariable();
            aPSecuritySdk.initToken(0, hashMap, new a(strArr, conditionVariable));
            conditionVariable.block(3000L);
        } catch (Throwable th) {
            d.a(th);
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.o, com.alipay.sdk.m.g.b.r, th);
        }
        if (TextUtils.isEmpty(strArr[0])) {
            com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.o, com.alipay.sdk.m.g.b.s, "missing token");
        }
        d.b(com.alipay.sdk.m.h.a.z, "ap:" + strArr[0]);
        return strArr[0];
    }

    public static String b(com.alipay.sdk.m.o.a aVar, Context context, HashMap<String, String> hashMap) {
        try {
            return (String) Executors.newFixedThreadPool(2).submit(new CallableC0008b(aVar, context, hashMap)).get(3000L, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.o, com.alipay.sdk.m.g.b.t, th);
            return "";
        }
    }

    public static String a(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    public String a(com.alipay.sdk.m.o.a aVar, com.alipay.sdk.m.p.a aVar2, boolean z) {
        Context b = com.alipay.sdk.m.o.b.d().b();
        com.alipay.sdk.m.q.b b2 = com.alipay.sdk.m.q.b.b(b);
        if (TextUtils.isEmpty(this.a)) {
            this.a = "Msp/15.8.06 (" + m.f() + ";" + m.e() + ";" + m.c(b) + ";" + m.e(b) + ";" + m.f(b) + ";" + a(b);
        }
        String b3 = com.alipay.sdk.m.q.b.d(b).b();
        String b4 = m.b(b);
        String e2 = e();
        String c = b2.c();
        String b5 = b2.b();
        String g2 = g();
        String f2 = f();
        if (aVar2 != null) {
            this.c = aVar2.c();
        }
        String replace = Build.MANUFACTURER.replace(";", " ");
        String replace2 = Build.MODEL.replace(";", " ");
        boolean e3 = com.alipay.sdk.m.o.b.e();
        String d2 = b2.d();
        String b6 = b(b, z);
        String a2 = a(b, z);
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append(";");
        sb.append(b3);
        sb.append(";");
        sb.append(b4);
        sb.append(";");
        sb.append(e2);
        sb.append(";");
        sb.append(c);
        sb.append(";");
        sb.append(b5);
        sb.append(";");
        sb.append(this.c);
        sb.append(";");
        sb.append(replace);
        sb.append(";");
        sb.append(replace2);
        sb.append(";");
        sb.append(e3);
        sb.append(";");
        sb.append(d2);
        sb.append(";");
        sb.append(d());
        sb.append(";");
        sb.append(this.b);
        sb.append(";");
        sb.append(g2);
        sb.append(";");
        sb.append(f2);
        sb.append(";");
        sb.append(b6);
        sb.append(";");
        sb.append(a2);
        if (aVar2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("tid", com.alipay.sdk.m.p.a.a(b).d());
            hashMap.put(com.alipay.sdk.m.h.b.g, com.alipay.sdk.m.o.b.d().c());
            String b7 = b(aVar, b, hashMap);
            if (!TextUtils.isEmpty(b7)) {
                sb.append(";;;");
                sb.append(b7);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static String b(Context context) {
        if (context == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            sb.append("(");
            sb.append(packageName);
            sb.append(";");
            sb.append(packageInfo.versionCode);
            sb.append(")");
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Context context, boolean z) {
        WifiInfo connectionInfo;
        return (z || (connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo()) == null) ? "00" : connectionInfo.getBSSID();
    }
}
