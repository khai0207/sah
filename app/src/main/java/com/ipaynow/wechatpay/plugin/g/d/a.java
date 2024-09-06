package com.ipaynow.wechatpay.plugin.g.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.ipaynow.wechatpay.plugin.g.a.c;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;
import u.aly.df;

/* loaded from: classes.dex */
public final class a {
    private static int cb = -1;

    private static String a(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i = 0;
            for (byte b : digest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & df.m];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String ae() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress()) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
            return "192.168.1.1";
        } catch (SocketException unused) {
            return "192.168.1.1";
        }
    }

    public static c af() {
        int i = cb;
        if (i == 1) {
            return c.ISROOT;
        }
        if (i == 0) {
            return c.UNROOT;
        }
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i2 = 0; i2 < 5; i2++) {
            try {
                if (new File(String.valueOf(strArr[i2]) + "su").exists()) {
                    cb = 1;
                    return c.ISROOT;
                }
            } catch (Exception unused) {
            }
        }
        cb = 0;
        return c.UNROOT;
    }

    public static String e(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }

    public static String f(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return "192.168.1.1";
        }
        if (activeNetworkInfo.getType() != 1) {
            return ae();
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        return String.valueOf(ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
    }

    public static String g(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    public static String h(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return String.valueOf(displayMetrics.widthPixels) + "*" + displayMetrics.heightPixels;
    }

    public static String i(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return String.valueOf(displayMetrics.density);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004e, code lost:
    
        if (r2.equalsIgnoreCase("CDMA2000") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x008a, code lost:
    
        if (r8 == 15) goto L48;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00a4 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String j(android.content.Context r8) {
        /*
            java.lang.String r0 = "connectivity"
            java.lang.Object r8 = r8.getSystemService(r0)
            android.net.ConnectivityManager r8 = (android.net.ConnectivityManager) r8
            android.net.NetworkInfo r8 = r8.getActiveNetworkInfo()
            java.lang.String r0 = ""
            java.lang.String r1 = "4G"
            if (r8 == 0) goto L9d
            boolean r2 = r8.isConnected()
            if (r2 == 0) goto L9d
            int r2 = r8.getType()
            r3 = 1
            if (r2 != r3) goto L23
            java.lang.String r1 = "WIFI"
            goto L9e
        L23:
            int r2 = r8.getType()
            if (r2 != 0) goto L9d
            java.lang.String r2 = r8.getSubtypeName()
            int r8 = r8.getSubtype()
            java.lang.String r3 = "2G"
            java.lang.String r4 = "3G"
            switch(r8) {
                case 1: goto L55;
                case 2: goto L55;
                case 3: goto L53;
                case 4: goto L55;
                case 5: goto L53;
                case 6: goto L53;
                case 7: goto L55;
                case 8: goto L53;
                case 9: goto L53;
                case 10: goto L53;
                case 11: goto L55;
                case 12: goto L53;
                case 13: goto L51;
                case 14: goto L53;
                case 15: goto L53;
                default: goto L38;
            }
        L38:
            java.lang.String r5 = "TD-SCDMA"
            boolean r5 = r2.equalsIgnoreCase(r5)
            if (r5 != 0) goto L53
            java.lang.String r5 = "WCDMA"
            boolean r5 = r2.equalsIgnoreCase(r5)
            if (r5 != 0) goto L53
            java.lang.String r5 = "CDMA2000"
            boolean r5 = r2.equalsIgnoreCase(r5)
            if (r5 == 0) goto L56
            goto L53
        L51:
            r2 = r1
            goto L56
        L53:
            r2 = r4
            goto L56
        L55:
            r2 = r3
        L56:
            boolean r5 = r2.equals(r3)
            r6 = 11
            if (r5 == 0) goto L67
            int r5 = android.os.Build.VERSION.SDK_INT
            r7 = 8
            if (r5 >= r7) goto L67
            if (r8 != r6) goto L67
            goto L68
        L67:
            r3 = r2
        L68:
            boolean r2 = r3.equals(r4)
            r5 = 13
            if (r2 == 0) goto L8d
            int r2 = android.os.Build.VERSION.SDK_INT
            r7 = 9
            if (r2 >= r7) goto L7b
            r2 = 14
            if (r8 != r2) goto L7b
            r3 = r4
        L7b:
            int r2 = android.os.Build.VERSION.SDK_INT
            if (r2 >= r6) goto L84
            r2 = 12
            if (r8 != r2) goto L84
            r3 = r4
        L84:
            int r2 = android.os.Build.VERSION.SDK_INT
            if (r2 >= r5) goto L8d
            r2 = 15
            if (r8 != r2) goto L8d
            goto L8e
        L8d:
            r4 = r3
        L8e:
            boolean r2 = r4.equals(r1)
            if (r2 == 0) goto L9b
            int r2 = android.os.Build.VERSION.SDK_INT
            if (r2 >= r6) goto L9b
            if (r8 != r5) goto L9b
            goto L9e
        L9b:
            r1 = r4
            goto L9e
        L9d:
            r1 = r0
        L9e:
            boolean r8 = r1.equals(r0)
            if (r8 == 0) goto La6
            java.lang.String r1 = "UNCONNECTION"
        La6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ipaynow.wechatpay.plugin.g.d.a.j(android.content.Context):java.lang.String");
    }

    public static com.ipaynow.wechatpay.plugin.g.a.a k(Context context) {
        String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
        return simOperator == null ? com.ipaynow.wechatpay.plugin.g.a.a.UNKNOWN : (simOperator.equals("46000") || simOperator.equals("46002")) ? com.ipaynow.wechatpay.plugin.g.a.a.YIDONG : simOperator.equals("46001") ? com.ipaynow.wechatpay.plugin.g.a.a.LIANTONG : simOperator.equals("46003") ? com.ipaynow.wechatpay.plugin.g.a.a.DIANXIN : com.ipaynow.wechatpay.plugin.g.a.a.UNKNOWN;
    }

    public static String l(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static String m(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static String n(Context context) {
        Signature[] o = o(context);
        if (o == null || o.length == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Signature signature : o) {
            stringBuffer.append(a(signature.toByteArray()));
        }
        return stringBuffer.toString();
    }

    private static Signature[] o(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String p(Context context) {
        return context.getPackageName();
    }
}
