package com.alipay.sdk.m.q;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.m.i.a;
import com.netease.nimlib.sdk.migration.model.MigrationConstant;
import com.talkingdata.sdk.aa;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import u.aly.df;

/* loaded from: classes.dex */
public class m {
    public static final String a = "com.alipay.android.app";
    public static final String b = "com.eg.android.AlipayGphone";
    public static final String c = "com.eg.android.AlipayGphoneRC";
    public static final int d = 99;
    public static final int f = 125;
    public static final String[] e = {"10.1.5.1013151", "10.1.5.1013148"};
    public static final char[] g = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '+', '/'};

    /* loaded from: classes.dex */
    public static class a implements Runnable {
        public final /* synthetic */ Activity a;

        public a(Activity activity) {
            this.a = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.finish();
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {
        public final /* synthetic */ Runnable a;
        public final /* synthetic */ ConditionVariable b;

        public b(Runnable runnable, ConditionVariable conditionVariable) {
            this.a = runnable;
            this.b = conditionVariable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.a.run();
            } finally {
                this.b.open();
            }
        }
    }

    public static String a(String str, String str2, String str3) {
        try {
            int indexOf = str3.indexOf(str) + str.length();
            if (indexOf <= str.length()) {
                return "";
            }
            int indexOf2 = TextUtils.isEmpty(str2) ? 0 : str3.indexOf(str2, indexOf);
            if (indexOf2 < 1) {
                return str3.substring(indexOf);
            }
            return str3.substring(indexOf, indexOf2);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b() {
        if (EnvUtils.isSandBox()) {
            return c;
        }
        try {
            return com.alipay.sdk.m.f.a.d.get(0).a;
        } catch (Throwable unused) {
            return b;
        }
    }

    public static String b(Context context) {
        return "-1;-1";
    }

    public static String c(String str) {
        return (EnvUtils.isSandBox() && TextUtils.equals(str, c)) ? "com.eg.android.AlipayGphoneRC.IAlixPay" : "com.eg.android.AlipayGphone.IAlixPay";
    }

    public static int d(String str) {
        for (int i = 0; i < 64; i++) {
            if (str.equals(String.valueOf(g[i]))) {
                return i;
            }
        }
        return 0;
    }

    public static String e(com.alipay.sdk.m.o.a aVar, String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.z, e2);
            return "";
        }
    }

    public static String f() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static String g(Context context) {
        return " (" + f() + ";" + e() + ";" + c(context) + ";;" + f(context) + ")(sdk android)";
    }

    public static JSONObject h(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    public static boolean i(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a, 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static String c(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String f(Context context) {
        DisplayMetrics d2 = d(context);
        return d2.widthPixels + "*" + d2.heightPixels;
    }

    public static String d() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
                if (!matcher.matches() || matcher.groupCount() < 4) {
                    return "Unavailable";
                }
                return matcher.group(1) + "\n" + matcher.group(2) + " " + matcher.group(3) + "\n" + matcher.group(4);
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        } catch (IOException unused) {
            return "Unavailable";
        }
    }

    public static String i(String str) {
        try {
            Uri parse = Uri.parse(str);
            return String.format("%s%s", parse.getAuthority(), parse.getPath());
        } catch (Throwable th) {
            d.a(th);
            return "-";
        }
    }

    public static String e() {
        String d2 = d();
        int indexOf = d2.indexOf("-");
        if (indexOf != -1) {
            d2 = d2.substring(0, indexOf);
        }
        int indexOf2 = d2.indexOf("\n");
        if (indexOf2 != -1) {
            d2 = d2.substring(0, indexOf2);
        }
        return "Linux " + d2;
    }

    public static boolean h(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(b(), 128);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode < 99;
        } catch (Throwable th) {
            d.a(th);
            return false;
        }
    }

    public static String c(com.alipay.sdk.m.o.a aVar, String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.l, "rflex", e2.getClass().getSimpleName());
            return null;
        }
    }

    public static Map<String, String> b(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split(com.alipay.sdk.m.o.a.l)) {
            int indexOf = str2.indexOf("=", 1);
            if (-1 != indexOf) {
                hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
            }
        }
        return hashMap;
    }

    public static boolean f(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    /* loaded from: classes.dex */
    public static final class c {
        public final PackageInfo a;
        public final int b;
        public final String c;

        public c(PackageInfo packageInfo, int i, String str) {
            this.a = packageInfo;
            this.b = i;
            this.c = str;
        }

        public boolean a(com.alipay.sdk.m.o.a aVar) {
            Signature[] signatureArr = this.a.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return false;
            }
            for (Signature signature : signatureArr) {
                String a = m.a(aVar, signature.toByteArray());
                if (a != null && !TextUtils.equals(a, this.c)) {
                    com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.B, String.format("Got %s, expected %s", a, this.c));
                    return true;
                }
            }
            return false;
        }

        public boolean a() {
            return this.a.versionCode < this.b;
        }
    }

    public static int c() {
        try {
            String lowerCase = Build.BRAND.toLowerCase();
            String lowerCase2 = Build.MANUFACTURER.toLowerCase();
            if (a("huawei", lowerCase, lowerCase2)) {
                return 1;
            }
            if (a("oppo", lowerCase, lowerCase2)) {
                return 2;
            }
            if (a("vivo", lowerCase, lowerCase2)) {
                return 4;
            }
            if (a("lenovo", lowerCase, lowerCase2)) {
                return 8;
            }
            if (a("xiaomi", lowerCase, lowerCase2)) {
                return 16;
            }
            return a("oneplus", lowerCase, lowerCase2) ? 32 : 0;
        } catch (Exception unused) {
            return 61440;
        }
    }

    public static String a(com.alipay.sdk.m.o.a aVar, byte[] bArr) {
        BigInteger modulus;
        try {
            PublicKey publicKey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey();
            if (!(publicKey instanceof RSAPublicKey) || (modulus = ((RSAPublicKey) publicKey).getModulus()) == null) {
                return null;
            }
            return modulus.toString(16);
        } catch (Exception e2) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.n, com.alipay.sdk.m.g.b.w, e2);
            return null;
        }
    }

    public static String e(Context context) {
        String b2 = l.b(context);
        return b2.substring(0, b2.indexOf(aa.a));
    }

    public static int e(String str) {
        try {
            String j = com.alipay.sdk.m.i.a.x().j();
            if (TextUtils.isEmpty(j)) {
                return 0;
            }
            return (b(j, "").contains(str) ? 2 : 0) | 1;
        } catch (Throwable unused) {
            return 61440;
        }
    }

    public static String g(String str) {
        return a(str, true);
    }

    public static int g() {
        try {
            return Process.myUid();
        } catch (Throwable th) {
            d.a(th);
            return MigrationConstant.IMPORT_ERR_RECORD_EMPTY;
        }
    }

    public static boolean h() {
        try {
            String[] split = com.alipay.sdk.m.i.a.x().g().split("\\|");
            String str = Build.MODEL;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            for (String str2 : split) {
                if (TextUtils.equals(str, str2) || TextUtils.equals(str2, "all")) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            d.a(th);
            return false;
        }
    }

    public static Map<String, String> b(com.alipay.sdk.m.o.a aVar, String str) {
        HashMap hashMap = new HashMap(4);
        int indexOf = str.indexOf(63);
        if (indexOf != -1 && indexOf < str.length() - 1) {
            for (String str2 : str.substring(indexOf + 1).split(com.alipay.sdk.m.o.a.l)) {
                int indexOf2 = str2.indexOf(61, 1);
                if (indexOf2 != -1 && indexOf2 < str2.length() - 1) {
                    hashMap.put(str2.substring(0, indexOf2), e(aVar, str2.substring(indexOf2 + 1)));
                }
            }
        }
        return hashMap;
    }

    public static c a(com.alipay.sdk.m.o.a aVar, Context context, List<a.b> list) {
        c a2;
        if (list == null) {
            return null;
        }
        for (a.b bVar : list) {
            if (bVar != null && (a2 = a(aVar, context, bVar.a, bVar.b, bVar.c)) != null && !a2.a(aVar) && !a2.a()) {
                return a2;
            }
        }
        return null;
    }

    public static c a(com.alipay.sdk.m.o.a aVar, Context context, String str, int i, String str2) {
        PackageInfo packageInfo;
        if (EnvUtils.isSandBox() && b.equals(str)) {
            str = c;
        }
        try {
            packageInfo = a(context, str);
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.n, com.alipay.sdk.m.g.b.f13u, th.getMessage());
            packageInfo = null;
        }
        if (a(aVar, packageInfo)) {
            return a(packageInfo, i, str2);
        }
        return null;
    }

    public static DisplayMetrics d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int b(int i) {
        return i / 100000;
    }

    public static String b(String str, String str2) {
        String string = Settings.Secure.getString(((Application) com.alipay.sdk.m.o.b.d().b()).getContentResolver(), str);
        return string != null ? string : str2;
    }

    public static boolean d(com.alipay.sdk.m.o.a aVar, String str) {
        try {
            int e2 = e(str);
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "bindExt", "" + e2);
            return com.alipay.sdk.m.i.a.x().o() && (e2 & 2) == 2;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a(com.alipay.sdk.m.o.a aVar, PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = "info == null";
        } else {
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null) {
                str = "info.signatures == null";
            } else if (signatureArr.length <= 0) {
                str = "info.signatures.length <= 0";
            } else {
                z = true;
            }
        }
        if (!z) {
            com.alipay.sdk.m.g.a.b(aVar, com.alipay.sdk.m.g.b.n, com.alipay.sdk.m.g.b.v, str);
        }
        return z;
    }

    public static PackageInfo a(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    public static c a(PackageInfo packageInfo, int i, String str) {
        if (packageInfo == null) {
            return null;
        }
        return new c(packageInfo, i, str);
    }

    public static long a(String str) {
        return a(str, 6);
    }

    public static long a(String str, int i) {
        int pow = (int) Math.pow(2.0d, i);
        int length = str.length();
        long j = 0;
        int i2 = length;
        for (int i3 = 0; i3 < length; i3++) {
            j += Integer.parseInt(String.valueOf(d(str.substring(i3, r5)))) * ((long) Math.pow(pow, i2 - 1));
            i2--;
        }
        return j;
    }

    public static int a() {
        String c2 = com.alipay.sdk.m.o.b.d().c();
        if (TextUtils.isEmpty(c2)) {
            return -1;
        }
        String replaceAll = c2.replaceAll("=", "");
        if (replaceAll.length() >= 5) {
            replaceAll = replaceAll.substring(0, 5);
        }
        int a2 = (int) (a(replaceAll) % 10000);
        return a2 < 0 ? a2 * (-1) : a2;
    }

    public static boolean a(com.alipay.sdk.m.o.a aVar, Context context, List<a.b> list, boolean z) {
        try {
            for (a.b bVar : list) {
                if (bVar != null) {
                    String str = bVar.a;
                    if (EnvUtils.isSandBox() && b.equals(str)) {
                        str = c;
                    }
                    try {
                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 128);
                        if (packageInfo != null) {
                            if (!z) {
                                return true;
                            }
                            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.V, packageInfo.packageName + "|" + packageInfo.versionName);
                            return true;
                        }
                        continue;
                    } catch (PackageManager.NameNotFoundException unused) {
                        continue;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.c0, th);
            return false;
        }
    }

    public static boolean a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            String str = packageInfo.versionName;
            if (!TextUtils.equals(str, e[0])) {
                if (!TextUtils.equals(str, e[1])) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            int nextInt = random.nextInt(3);
            if (nextInt == 0) {
                sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 65.0d)));
            } else if (nextInt == 1) {
                sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 97.0d)));
            } else if (nextInt == 2) {
                sb.append(String.valueOf(new Random().nextInt(10)));
            }
        }
        return sb.toString();
    }

    public static boolean a(com.alipay.sdk.m.o.a aVar, String str, Activity activity) {
        int parseInt;
        String substring;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        if (!str.toLowerCase().startsWith(com.alipay.sdk.m.h.a.m.toLowerCase()) && !str.toLowerCase().startsWith(com.alipay.sdk.m.h.a.n.toLowerCase())) {
            if (!TextUtils.equals(str, com.alipay.sdk.m.h.a.p) && !TextUtils.equals(str, a(com.alipay.sdk.m.h.a.q, com.alipay.sdk.m.h.a.r))) {
                if (!str.startsWith(com.alipay.sdk.m.h.a.o)) {
                    return false;
                }
                try {
                    String substring2 = str.substring(str.indexOf(com.alipay.sdk.m.h.a.o) + 24);
                    parseInt = Integer.parseInt(substring2.substring(substring2.lastIndexOf(com.alipay.sdk.m.h.a.s) + 10));
                } catch (Exception unused) {
                    com.alipay.sdk.m.f.b.a(com.alipay.sdk.m.f.b.e());
                }
                if (parseInt != com.alipay.sdk.m.f.c.SUCCEEDED.b() && parseInt != com.alipay.sdk.m.f.c.PAY_WAITTING.b()) {
                    com.alipay.sdk.m.f.c b2 = com.alipay.sdk.m.f.c.b(com.alipay.sdk.m.f.c.FAILED.b());
                    com.alipay.sdk.m.f.b.a(com.alipay.sdk.m.f.b.a(b2.b(), b2.a(), ""));
                    activity.runOnUiThread(new a(activity));
                    return true;
                }
                if (com.alipay.sdk.m.h.a.w) {
                    StringBuilder sb = new StringBuilder();
                    String decode = URLDecoder.decode(str);
                    String decode2 = URLDecoder.decode(decode);
                    String str2 = decode2.substring(decode2.indexOf(com.alipay.sdk.m.h.a.o) + 24, decode2.lastIndexOf(com.alipay.sdk.m.h.a.s)).split(com.alipay.sdk.m.h.a.f14u)[0];
                    int indexOf = decode.indexOf(com.alipay.sdk.m.h.a.f14u) + 12;
                    sb.append(str2);
                    sb.append(com.alipay.sdk.m.h.a.f14u);
                    sb.append(decode.substring(indexOf, decode.indexOf(com.alipay.sdk.m.o.a.l, indexOf)));
                    sb.append(decode.substring(decode.indexOf(com.alipay.sdk.m.o.a.l, indexOf)));
                    substring = sb.toString();
                } else {
                    String decode3 = URLDecoder.decode(str);
                    substring = decode3.substring(decode3.indexOf(com.alipay.sdk.m.h.a.o) + 24, decode3.lastIndexOf(com.alipay.sdk.m.h.a.s));
                }
                com.alipay.sdk.m.f.c b3 = com.alipay.sdk.m.f.c.b(parseInt);
                com.alipay.sdk.m.f.b.a(com.alipay.sdk.m.f.b.a(b3.b(), b3.a(), substring));
                activity.runOnUiThread(new a(activity));
                return true;
            }
            com.alipay.sdk.m.f.b.a(com.alipay.sdk.m.f.b.a());
            activity.finish();
            return true;
        }
        try {
            c a2 = a(aVar, activity, com.alipay.sdk.m.f.a.d);
            if (a2 != null && !a2.a() && !a2.a(aVar)) {
                if (str.startsWith("intent://platformapi/startapp")) {
                    str = str.replaceFirst("intent://platformapi/startapp\\?", com.alipay.sdk.m.h.a.m);
                }
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
        } catch (Throwable unused2) {
        }
        return true;
    }

    public static String a(com.alipay.sdk.m.o.a aVar, Context context) {
        return a(aVar, context, context.getPackageName());
    }

    public static String a(com.alipay.sdk.m.o.a aVar, Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128).versionName;
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.f13u, th);
            return "";
        }
    }

    public static String a(String str, boolean z) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            if (z && digest.length > 16) {
                byte[] bArr = new byte[16];
                System.arraycopy(digest, 0, bArr, 0, 16);
                return a(bArr);
            }
            return a(digest);
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            sb.append(Character.forDigit((b2 & 240) >> 4, 16));
            sb.append(Character.forDigit(b2 & df.m, 16));
        }
        return sb.toString();
    }

    public static ActivityInfo a(Context context) {
        try {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities) {
                    if (TextUtils.equals(activityInfo.name, activity.getClass().getName())) {
                        return activityInfo;
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            d.a(th);
            return null;
        }
    }

    public static String a(com.alipay.sdk.m.o.a aVar) {
        return c(aVar, "ro.build.fingerprint");
    }

    public static <T> T a(WeakReference<T> weakReference) {
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static boolean a(com.alipay.sdk.m.o.a aVar, String str) {
        try {
            String host = new URL(str).getHost();
            if (host.endsWith(com.alipay.sdk.m.h.a.A)) {
                return true;
            }
            return host.endsWith(com.alipay.sdk.m.h.a.B);
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, "ckUrlErr", th);
            return false;
        }
    }

    public static JSONObject a(Intent intent) {
        Bundle extras;
        JSONObject jSONObject = new JSONObject();
        if (intent != null && (extras = intent.getExtras()) != null) {
            for (String str : extras.keySet()) {
                try {
                    jSONObject.put(str, String.valueOf(extras.get(str)));
                } catch (Throwable unused) {
                }
            }
        }
        return jSONObject;
    }

    public static Map<String, String> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                hashMap.put(next, jSONObject.optString(next));
            } catch (Throwable th) {
                d.a(th);
            }
        }
        return hashMap;
    }

    public static boolean a(Object obj, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return obj == null;
        }
        for (Object obj2 : objArr) {
            if ((obj == null && obj2 == null) || (obj != null && obj.equals(obj2))) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(long j, Runnable runnable, String str) {
        if (runnable == null) {
            return false;
        }
        ConditionVariable conditionVariable = new ConditionVariable();
        Thread thread = new Thread(new b(runnable, conditionVariable));
        if (!TextUtils.isEmpty(str)) {
            thread.setName(str);
        }
        thread.start();
        boolean z = true;
        try {
            if (j <= 0) {
                conditionVariable.block();
            } else {
                z = conditionVariable.block(j);
            }
        } catch (Throwable unused) {
        }
        return z;
    }

    public static String a(String str, String str2) {
        return str + str2;
    }
}
