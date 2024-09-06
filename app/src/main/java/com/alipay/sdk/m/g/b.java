package com.alipay.sdk.m.g;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.m.g.a;
import com.alipay.sdk.m.q.d;
import com.alipay.sdk.m.q.m;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {
    public static final String A = "H5AuthDataAnalysisError";
    public static final String B = "PublicKeyUnmatch";
    public static final String C = "ClientBindFailed";
    public static final String D = "TriDesEncryptError";
    public static final String E = "TriDesDecryptError";
    public static final String F = "ClientBindException";
    public static final String G = "SaveTradeTokenError";
    public static final String H = "ClientBindServiceFailed";
    public static final String I = "TryStartServiceEx";
    public static final String J = "BindWaitTimeoutEx";
    public static final String K = "CheckClientExistEx";
    public static final String L = "CheckClientSignEx";
    public static final String M = "GetInstalledAppEx";
    public static final String N = "ParserTidClientKeyEx";
    public static final String O = "PgApiInvoke";
    public static final String P = "PgBindStarting";
    public static final String Q = "PgBinded";
    public static final String R = "PgBindEnd";
    public static final String S = "PgBindPay";
    public static final String T = "PgReturn";
    public static final String U = "PgReturnV";
    public static final String V = "PgWltVer";
    public static final String W = "PgOpenStarting";
    public static final String X = "ErrIntentEx";
    public static final String Y = "ErrActNull";
    public static final String Z = "ErrActNull";
    public static final String a0 = "GetInstalledAppEx";
    public static final String b0 = "StartLaunchAppTransEx";
    public static final String c0 = "CheckLaunchAppExistEx";
    public static final String d0 = "LogBindCalledH5";
    public static final String e0 = "LogCalledH5";
    public static final String f0 = "LogHkLoginByIntent";
    public static final String g0 = "SchemePayWrongHashEx";
    public static final String h0 = "LogAppFetchConfigTimeout";
    public static final String i0 = "H5CbUrlEmpty";
    public static final String j0 = "H5CbEx";
    public static final String k = "net";
    public static final String k0 = "StartActivityEx";
    public static final String l = "biz";
    public static final String l0 = "JSONEx";
    public static final String m = "cp";
    public static final String m0 = "ParseBundleSerializableError";
    public static final String n = "auth";
    public static final String n0 = "ParseSchemeQueryError";
    public static final String o = "third";
    public static final String o0 = "TbChk";
    public static final String p = "wlt";
    public static final String p0 = "TbStart";
    public static final String q = "FormatResultEx";
    public static final String q0 = "TbCancel";
    public static final String r = "GetApdidEx";
    public static final String r0 = "TbUnknown";
    public static final String s = "GetApdidNull";
    public static final String s0 = "TbOk";
    public static final String t = "GetApdidTimeout";
    public static final String t0 = "TbActFail";

    /* renamed from: u, reason: collision with root package name */
    public static final String f13u = "GetPackageInfoEx";
    public static final String u0 = "partner";
    public static final String v = "NotIncludeSignatures";
    public static final String v0 = "out_trade_no";
    public static final String w = "GetPublicKeyFromSignEx";
    public static final String w0 = "trade_no";
    public static final String x = "SSLError";
    public static final String x0 = "biz_content";
    public static final String y = "SSLDenied";
    public static final String y0 = "app_id";
    public static final String z = "H5PayDataAnalysisError";
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h = "";
    public String i = "";
    public String j;

    public b(Context context, boolean z2) {
        context = context != null ? context.getApplicationContext() : context;
        this.a = b();
        this.c = a(context);
        this.d = a(z2 ? 0L : a.e.a(context));
        this.e = a();
        this.f = b(context);
        this.g = "-";
        this.j = "-";
    }

    private synchronized void c(String str, String str2, String str3) {
        d.d(com.alipay.sdk.m.h.a.z, String.format("event %s %s %s", str, str2, str3));
        String str4 = TextUtils.isEmpty(this.h) ? "" : "^";
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = TextUtils.isEmpty(str) ? "-" : c(str);
        objArr[1] = c(str2);
        objArr[2] = c(str3);
        objArr[3] = c(c());
        sb.append(String.format("%s,%s,%s,-,-,-,-,-,-,-,-,-,-,%s", objArr));
        this.h += sb.toString();
    }

    private boolean d() {
        return TextUtils.isEmpty(this.i);
    }

    public static String e() {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable unused) {
            return "12345678uuid";
        }
    }

    public void a(String str, String str2, Throwable th) {
        d(str, str2, a(th));
    }

    public void b(String str, String str2, String str3) {
        d(str, str2, str3);
    }

    public static String b() {
        return String.format("%s,%s", e(), new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    }

    private synchronized void d(String str, String str2, String str3) {
        d.c(com.alipay.sdk.m.h.a.z, String.format("err %s %s %s", str, str2, str3));
        String str4 = TextUtils.isEmpty(this.i) ? "" : "^";
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = str;
        objArr[1] = str2;
        objArr[2] = TextUtils.isEmpty(str3) ? "-" : c(str3);
        objArr[3] = c(c());
        sb.append(String.format("%s,%s,%s,%s", objArr));
        this.i += sb.toString();
    }

    public void a(String str, String str2, Throwable th, String str3) {
        d(str, str2, str3 + ": " + a(th));
    }

    public static String b(String str) {
        String str2;
        String str3;
        if (str == null) {
            str = "";
        }
        String[] split = str.split(com.alipay.sdk.m.o.a.l);
        String str4 = null;
        if (split != null) {
            str2 = null;
            str3 = null;
            for (String str5 : split) {
                String[] split2 = str5.split("=");
                if (split2 != null && split2.length == 2) {
                    if (split2[0].equalsIgnoreCase(u0)) {
                        str4 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(v0)) {
                        str2 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(w0)) {
                        str3 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(x0)) {
                        try {
                            JSONObject jSONObject = new JSONObject(m.e(com.alipay.sdk.m.o.a.f(), split2[1]));
                            if (TextUtils.isEmpty(str2)) {
                                str2 = jSONObject.getString(v0);
                            }
                        } catch (Throwable unused) {
                        }
                    } else if (split2[0].equalsIgnoreCase(y0) && TextUtils.isEmpty(str4)) {
                        str4 = split2[1];
                    }
                }
            }
        } else {
            str2 = null;
            str3 = null;
        }
        return String.format("%s,%s,-,%s,-,-,-", c(str3), c(str2), c(str4));
    }

    public void a(String str, String str2, String str3) {
        c("", str, str2 + "|" + str3);
    }

    public void a(String str, String str2) {
        c("", str, str2);
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                int i = 0;
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString());
                    stringBuffer.append(" 》 ");
                    i++;
                    if (i > 5) {
                        break;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    public static String d(String str) {
        return TextUtils.isEmpty(str) ? "-" : str;
    }

    public static String c() {
        return new SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("^", "~").replace("#", "＃");
    }

    public String a(String str) {
        String b = b(str);
        this.b = b;
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", this.a, b, this.c, this.d, this.e, this.f, this.g, d(this.h), d(this.i), this.j);
    }

    public static String a(Context context) {
        String packageName;
        String str = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                packageName = applicationContext.getPackageName();
                try {
                    PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 64);
                    str = packageInfo.versionName + "|" + a(packageInfo);
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
            return String.format("%s,%s,-,-,-", c(packageName), c(str));
        }
        packageName = "-";
        return String.format("%s,%s,-,-,-", c(packageName), c(str));
    }

    public static String b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", c(com.alipay.sdk.m.q.b.c(context)), "android", c(Build.VERSION.RELEASE), c(Build.MODEL), "-", c("0"), c(com.alipay.sdk.m.q.b.d(context).b()), "gw", c("0"));
    }

    public static String a(PackageInfo packageInfo) {
        Signature[] signatureArr;
        String str;
        String a;
        if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length == 0) {
            return "0";
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(packageInfo.signatures.length);
            for (Signature signature : packageInfo.signatures) {
                try {
                    a = m.a((com.alipay.sdk.m.o.a) null, signature.toByteArray());
                } catch (Throwable unused) {
                }
                if (TextUtils.isEmpty(a)) {
                    str = "?";
                    sb.append("-");
                    sb.append(str);
                } else {
                    str = m.g(a).substring(0, 8);
                    sb.append("-");
                    sb.append(str);
                }
            }
            return sb.toString();
        } catch (Throwable unused2) {
            return "?";
        }
    }

    public static String a(long j) {
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,%s,-", c("15.8.06"), c("h.a.3.8.06"), "~" + j);
    }

    public static String a() {
        return String.format("%s,%s,-,-,-", c(com.alipay.sdk.m.p.a.a(com.alipay.sdk.m.o.b.d().b()).d()), c(com.alipay.sdk.m.o.b.d().c()));
    }
}
