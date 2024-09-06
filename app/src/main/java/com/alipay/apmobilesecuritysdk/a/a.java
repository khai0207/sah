package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.os.Environment;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.e.b;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.sdk.m.a0.c;
import com.alipay.sdk.m.a0.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/* loaded from: classes.dex */
public final class a {
    public Context a;
    public com.alipay.apmobilesecuritysdk.b.a b = com.alipay.apmobilesecuritysdk.b.a.a();
    public int c = 4;

    public a(Context context) {
        this.a = context;
    }

    public static String a(Context context) {
        String b = b(context);
        return com.alipay.sdk.m.u.a.a(b) ? h.f(context) : b;
    }

    public static String a(Context context, String str) {
        try {
            b();
            String a = i.a(str);
            if (!com.alipay.sdk.m.u.a.a(a)) {
                return a;
            }
            String a2 = g.a(context, str);
            i.a(str, a2);
            return !com.alipay.sdk.m.u.a.a(a2) ? a2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int random = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        for (int i = 0; i < 3; i++) {
            try {
                String[] split = strArr[i].split(" ");
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(parse2);
                    calendar.add(13, random);
                    Date time = calendar.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private c b(Map<String, String> map) {
        String str;
        String str2;
        String str3;
        b b;
        b c;
        String str4 = "";
        try {
            Context context = this.a;
            d dVar = new d();
            String a = com.alipay.sdk.m.u.a.a(map, "appName", "");
            String a2 = com.alipay.sdk.m.u.a.a(map, "sessionId", "");
            String a3 = com.alipay.sdk.m.u.a.a(map, "rpcVersion", "");
            String a4 = a(context, a);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String d = h.d(context);
            if (com.alipay.sdk.m.u.a.b(a2)) {
                dVar.c = a2;
            } else {
                dVar.c = a4;
            }
            dVar.d = securityToken;
            dVar.e = d;
            dVar.a = "android";
            com.alipay.apmobilesecuritysdk.e.c c2 = com.alipay.apmobilesecuritysdk.e.d.c(context);
            if (c2 != null) {
                str2 = c2.a;
                str = c2.c;
            } else {
                str = "";
                str2 = str;
            }
            if (com.alipay.sdk.m.u.a.a(str2) && (c = com.alipay.apmobilesecuritysdk.e.a.c(context)) != null) {
                str2 = c.a;
                str = c.c;
            }
            com.alipay.apmobilesecuritysdk.e.c b2 = com.alipay.apmobilesecuritysdk.e.d.b();
            if (b2 != null) {
                str4 = b2.a;
                str3 = b2.c;
            } else {
                str3 = "";
            }
            if (com.alipay.sdk.m.u.a.a(str4) && (b = com.alipay.apmobilesecuritysdk.e.a.b()) != null) {
                str4 = b.a;
                str3 = b.c;
            }
            dVar.h = str2;
            dVar.g = str4;
            dVar.j = a3;
            if (com.alipay.sdk.m.u.a.a(str2)) {
                dVar.b = str4;
                str = str3;
            } else {
                dVar.b = str2;
            }
            dVar.i = str;
            dVar.f = e.a(context, map);
            return com.alipay.sdk.m.y.d.b(this.a, this.b.c()).a(dVar);
        } catch (Throwable th) {
            th.printStackTrace();
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }

    public static String b(Context context) {
        try {
            String b = i.b();
            if (!com.alipay.sdk.m.u.a.a(b)) {
                return b;
            }
            com.alipay.apmobilesecuritysdk.e.c b2 = com.alipay.apmobilesecuritysdk.e.d.b(context);
            if (b2 != null) {
                i.a(b2);
                String str = b2.a;
                if (com.alipay.sdk.m.u.a.b(str)) {
                    return str;
                }
            }
            b b3 = com.alipay.apmobilesecuritysdk.e.a.b(context);
            if (b3 == null) {
                return "";
            }
            i.a(b3);
            String str2 = b3.a;
            return com.alipay.sdk.m.u.a.b(str2) ? str2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File file = new File(Environment.getExternalStorageDirectory(), ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x00b9, code lost:
    
        if (com.alipay.sdk.m.u.a.a(b(r9.a)) != false) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01fd A[Catch: Exception -> 0x023a, TryCatch #0 {Exception -> 0x023a, blocks: (B:3:0x0006, B:5:0x0037, B:8:0x0040, B:12:0x00be, B:16:0x01e2, B:18:0x01fd, B:20:0x0203, B:22:0x0209, B:26:0x0212, B:28:0x0218, B:33:0x00d2, B:35:0x00ea, B:40:0x00f7, B:41:0x0107, B:42:0x010e, B:47:0x0120, B:49:0x0170, B:51:0x017a, B:52:0x0182, B:54:0x018f, B:56:0x0199, B:57:0x01a1, B:58:0x019d, B:59:0x017e, B:61:0x0055, B:63:0x0063, B:66:0x006e, B:68:0x0074, B:71:0x007f, B:74:0x0088, B:77:0x0095, B:81:0x00a2, B:84:0x00af), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0203 A[Catch: Exception -> 0x023a, TryCatch #0 {Exception -> 0x023a, blocks: (B:3:0x0006, B:5:0x0037, B:8:0x0040, B:12:0x00be, B:16:0x01e2, B:18:0x01fd, B:20:0x0203, B:22:0x0209, B:26:0x0212, B:28:0x0218, B:33:0x00d2, B:35:0x00ea, B:40:0x00f7, B:41:0x0107, B:42:0x010e, B:47:0x0120, B:49:0x0170, B:51:0x017a, B:52:0x0182, B:54:0x018f, B:56:0x0199, B:57:0x01a1, B:58:0x019d, B:59:0x017e, B:61:0x0055, B:63:0x0063, B:66:0x006e, B:68:0x0074, B:71:0x007f, B:74:0x0088, B:77:0x0095, B:81:0x00a2, B:84:0x00af), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0212 A[Catch: Exception -> 0x023a, TryCatch #0 {Exception -> 0x023a, blocks: (B:3:0x0006, B:5:0x0037, B:8:0x0040, B:12:0x00be, B:16:0x01e2, B:18:0x01fd, B:20:0x0203, B:22:0x0209, B:26:0x0212, B:28:0x0218, B:33:0x00d2, B:35:0x00ea, B:40:0x00f7, B:41:0x0107, B:42:0x010e, B:47:0x0120, B:49:0x0170, B:51:0x017a, B:52:0x0182, B:54:0x018f, B:56:0x0199, B:57:0x01a1, B:58:0x019d, B:59:0x017e, B:61:0x0055, B:63:0x0063, B:66:0x006e, B:68:0x0074, B:71:0x007f, B:74:0x0088, B:77:0x0095, B:81:0x00a2, B:84:0x00af), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d2 A[Catch: Exception -> 0x023a, TryCatch #0 {Exception -> 0x023a, blocks: (B:3:0x0006, B:5:0x0037, B:8:0x0040, B:12:0x00be, B:16:0x01e2, B:18:0x01fd, B:20:0x0203, B:22:0x0209, B:26:0x0212, B:28:0x0218, B:33:0x00d2, B:35:0x00ea, B:40:0x00f7, B:41:0x0107, B:42:0x010e, B:47:0x0120, B:49:0x0170, B:51:0x017a, B:52:0x0182, B:54:0x018f, B:56:0x0199, B:57:0x01a1, B:58:0x019d, B:59:0x017e, B:61:0x0055, B:63:0x0063, B:66:0x006e, B:68:0x0074, B:71:0x007f, B:74:0x0088, B:77:0x0095, B:81:0x00a2, B:84:0x00af), top: B:2:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(java.util.Map<java.lang.String, java.lang.String> r10) {
        /*
            Method dump skipped, instructions count: 577
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.a.a.a(java.util.Map):int");
    }
}
