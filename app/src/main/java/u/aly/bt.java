package u.aly;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: DeviceConfig.java */
/* loaded from: classes.dex */
public class bt {
    protected static final String a = bt.class.getName();
    public static final String b = "";
    public static final String c = "2G/3G";
    public static final String d = "Wi-Fi";
    public static final int e = 8;

    public static boolean a(String str, Context context) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean a(Context context) {
        return context.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
    }

    public static boolean b(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static String c(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static String d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static boolean a(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(str) == 0) {
                return true;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            return true;
        }
        return false;
    }

    public static String e(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "");
    }

    public static String[] a(GL10 gl10) {
        try {
            return new String[]{gl10.glGetString(7936), gl10.glGetString(7937)};
        } catch (Exception e2) {
            bv.e(a, "Could not read gpu infor:", e2);
            return new String[0];
        }
    }

    private static String d() {
        String b2;
        if (!com.umeng.analytics.a.f) {
            return null;
        }
        String[] strArr = {"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
        for (int i = 0; i < 3; i++) {
            try {
                b2 = b(strArr[i]);
            } catch (Exception e2) {
                bv.e(a, "open file  Failed", e2);
            }
            if (b2 != null) {
                return b2;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0058 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x001e -> B:9:0x004b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String b(java.lang.String r7) throws java.io.FileNotFoundException {
        /*
            java.io.FileReader r0 = new java.io.FileReader
            r0.<init>(r7)
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            r3 = 1024(0x400, float:1.435E-42)
            r2.<init>(r0, r3)     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L22 java.lang.Throwable -> L4c
            r0.close()     // Catch: java.io.IOException -> L15
            goto L19
        L15:
            r7 = move-exception
            r7.printStackTrace()
        L19:
            r2.close()     // Catch: java.io.IOException -> L1d
            goto L4b
        L1d:
            r7 = move-exception
            r7.printStackTrace()
            goto L4b
        L22:
            r3 = move-exception
            goto L28
        L24:
            r7 = move-exception
            goto L4e
        L26:
            r3 = move-exception
            r2 = r1
        L28:
            java.lang.String r4 = u.aly.bt.a     // Catch: java.lang.Throwable -> L4c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4c
            r5.<init>()     // Catch: java.lang.Throwable -> L4c
            java.lang.String r6 = "Could not read from file "
            r5.append(r6)     // Catch: java.lang.Throwable -> L4c
            r5.append(r7)     // Catch: java.lang.Throwable -> L4c
            java.lang.String r7 = r5.toString()     // Catch: java.lang.Throwable -> L4c
            u.aly.bv.e(r4, r7, r3)     // Catch: java.lang.Throwable -> L4c
            r0.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r7 = move-exception
            r7.printStackTrace()
        L46:
            if (r2 == 0) goto L4b
            r2.close()     // Catch: java.io.IOException -> L1d
        L4b:
            return r1
        L4c:
            r7 = move-exception
            r1 = r2
        L4e:
            r0.close()     // Catch: java.io.IOException -> L52
            goto L56
        L52:
            r0 = move-exception
            r0.printStackTrace()
        L56:
            if (r1 == 0) goto L60
            r1.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r0 = move-exception
            r0.printStackTrace()
        L60:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.bt.b(java.lang.String):java.lang.String");
    }

    public static String a() {
        String str = null;
        try {
            FileReader fileReader = new FileReader("/proc/cpuinfo");
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                str = bufferedReader.readLine();
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e2) {
                bv.e(a, "Could not read from file /proc/cpuinfo", e2);
            }
        } catch (FileNotFoundException e3) {
            bv.e(a, "Could not open file /proc/cpuinfo", e3);
        }
        return str != null ? str.substring(str.indexOf(58) + 1).trim() : "";
    }

    public static String f(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String str = "";
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (a(context, "android.permission.READ_PHONE_STATE")) {
                    str = telephonyManager.getDeviceId();
                    bv.a(a, "getDeviceId, IMEI: " + str);
                }
            } catch (Exception e2) {
                bv.d(a, "No IMEI.", e2);
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            String d2 = d();
            bv.a(a, "getDeviceId, mc: " + d2);
            if (!TextUtils.isEmpty(d2)) {
                return d2;
            }
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            bv.a(a, "getDeviceId, android_id: " + string);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            if (Build.VERSION.SDK_INT >= 9) {
                string = Build.SERIAL;
            }
            bv.a(a, "getDeviceId, serial no: " + string);
            return string;
        }
        if (telephonyManager == null) {
            bv.e(a, "No IMEI.");
        }
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                str = telephonyManager.getDeviceId();
            }
        } catch (Exception e3) {
            bv.d(a, "No IMEI.", e3);
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        bv.e(a, "No IMEI.");
        String q = q(context);
        if (!TextUtils.isEmpty(q)) {
            return q;
        }
        bv.e(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
        bv.a(a, "getDeviceId: Secure.ANDROID_ID: " + string2);
        return string2;
    }

    public static String g(Context context) {
        return bu.b(f(context));
    }

    public static String h(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return telephonyManager.getSubscriberId();
        }
        return null;
    }

    public static String i(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (a(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) ? telephonyManager.getNetworkOperatorName() : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(i);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String[] k(Context context) {
        String[] strArr = {"", ""};
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            strArr[0] = "";
            return strArr;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            strArr[0] = "";
            return strArr;
        }
        if (connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            strArr[0] = d;
            return strArr;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            strArr[0] = c;
            strArr[1] = networkInfo.getSubtypeName();
            return strArr;
        }
        return strArr;
    }

    public static boolean l(Context context) {
        return d.equals(k(context)[0]);
    }

    public static boolean m(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean b() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static int n(Context context) {
        try {
            Calendar calendar = Calendar.getInstance(A(context));
            if (calendar != null) {
                return calendar.getTimeZone().getRawOffset() / 3600000;
            }
            return 8;
        } catch (Exception e2) {
            bv.c(a, "error in getTimeZone", e2);
            return 8;
        }
    }

    public static String[] o(Context context) {
        String[] strArr = new String[2];
        try {
            Locale A = A(context);
            if (A != null) {
                strArr[0] = A.getCountry();
                strArr[1] = A.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = "Unknown";
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = "Unknown";
            }
            return strArr;
        } catch (Exception e2) {
            bv.e(a, "error in getLocaleInfo", e2);
            return strArr;
        }
    }

    private static Locale A(Context context) {
        Locale locale;
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            locale = configuration.locale;
        } catch (Exception unused) {
            bv.c(a, "fail to read user config locale");
            locale = null;
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static String p(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return null;
            }
            String string = applicationInfo.metaData.getString("UMENG_APPKEY");
            if (string != null) {
                return string.trim();
            }
            bv.c(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            return null;
        } catch (Exception e2) {
            bv.e(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e2);
            return null;
        }
    }

    public static String q(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            String d2 = d();
            return d2 == null ? B(context) : d2;
        }
        return B(context);
    }

    private static String B(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            bv.e(a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e2) {
            bv.e(a, "Could not get mac address." + e2.toString());
            return "";
        }
    }

    public static String r(Context context) {
        int[] s = s(context);
        if (s == null) {
            return "Unknown";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s[0]);
        stringBuffer.append("*");
        stringBuffer.append(s[1]);
        return stringBuffer.toString();
    }

    public static int[] s(Context context) {
        int i;
        int i2;
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                i = a(displayMetrics, "noncompatWidthPixels");
                i2 = a(displayMetrics, "noncompatHeightPixels");
            } else {
                i = -1;
                i2 = -1;
            }
            if (i == -1 || i2 == -1) {
                i = displayMetrics.widthPixels;
                i2 = displayMetrics.heightPixels;
            }
            int[] iArr = new int[2];
            if (i > i2) {
                iArr[0] = i2;
                iArr[1] = i;
            } else {
                iArr[0] = i;
                iArr[1] = i2;
            }
            return iArr;
        } catch (Exception e2) {
            bv.e(a, "read resolution fail", e2);
            return null;
        }
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static String t(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e2) {
            bv.c(a, "read carrier fail", e2);
            return "Unknown";
        }
    }

    public static String a(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
    }

    public static String c() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    public static Date a(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static int a(Date date, Date date2) {
        if (date.after(date2)) {
            date2 = date;
            date = date2;
        }
        return (int) ((date2.getTime() - date.getTime()) / 1000);
    }

    public static String u(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null) {
                return "Unknown";
            }
            String obj2 = obj.toString();
            if (obj2 != null) {
                return obj2;
            }
            bv.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            return "Unknown";
        } catch (Exception e2) {
            bv.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            e2.printStackTrace();
            return "Unknown";
        }
    }

    public static String v(Context context) {
        return context.getPackageName();
    }

    public static String w(Context context) {
        PackageInfo packageInfo;
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(v(context), 64);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException e3) {
            e3.printStackTrace();
            certificateFactory = null;
        }
        try {
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e4) {
            e4.printStackTrace();
            x509Certificate = null;
        }
        try {
            return a(MessageDigest.getInstance("MD5").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e5) {
            e5.printStackTrace();
            return null;
        } catch (CertificateEncodingException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static String x(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static boolean y(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String z(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Exception e2) {
            bv.a(a, e2);
            return null;
        }
    }
}
