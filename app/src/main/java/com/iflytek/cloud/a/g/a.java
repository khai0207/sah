package com.iflytek.cloud.a.g;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.core.os.EnvironmentCompat;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class a {
    public static String[][] a = {new String[]{"os.manufact", Build.MANUFACTURER}, new String[]{"os.model", Build.MODEL}, new String[]{"os.product", Build.PRODUCT}, new String[]{"os.display", Build.DISPLAY}, new String[]{"os.host", Build.HOST}, new String[]{"os.id", Build.ID}, new String[]{"os.device", Build.DEVICE}, new String[]{"os.board", Build.BOARD}, new String[]{"os.brand", Build.BRAND}, new String[]{"os.user", Build.USER}, new String[]{"os.type", Build.TYPE}};
    private static String[][] b = {new String[]{"os.cpu", "CPU_ABI"}, new String[]{"os.cpu2", "CPU_ABI2"}, new String[]{"os.serial", "SERIAL"}, new String[]{"os.hardware", "HARDWARE"}, new String[]{"os.bootloader", "BOOTLOADER"}, new String[]{"os.radio", "RADIO"}};
    private static com.iflytek.cloud.b.a c = new com.iflytek.cloud.b.a();
    private static boolean d = false;

    public static synchronized com.iflytek.cloud.b.a a(Context context) {
        synchronized (a.class) {
            if (d) {
                return c;
            }
            c(context);
            return c;
        }
    }

    private static String a(String str) {
        try {
            Field field = Build.class.getField(str);
            if (field != null) {
                return field.get(new Build()).toString();
            }
        } catch (Exception unused) {
        }
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public static void a(com.iflytek.cloud.b.a aVar, Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            aVar.a("app.ver.name", packageInfo.versionName);
            aVar.a("app.ver.code", "" + packageInfo.versionCode);
            aVar.a("app.pkg", applicationInfo.packageName);
            aVar.a("app.path", applicationInfo.dataDir);
            aVar.a("app.name", applicationInfo.loadLabel(context.getPackageManager()).toString());
        } catch (Exception unused) {
        }
    }

    public static com.iflytek.cloud.b.a b(Context context) {
        com.iflytek.cloud.b.a a2 = a(context);
        com.iflytek.cloud.b.a aVar = new com.iflytek.cloud.b.a();
        aVar.a(a2, "app.name");
        aVar.a(a2, "app.path");
        aVar.a(a2, "app.pkg");
        aVar.a(a2, "app.ver.name");
        aVar.a(a2, "app.ver.code");
        aVar.a(a2, "os.system");
        aVar.a(a2, "os.resolution");
        aVar.a(a2, "os.density");
        aVar.a(a2, "net.mac");
        aVar.a(a2, "os.imei");
        aVar.a(a2, "os.imsi");
        aVar.a(a2, "os.version");
        aVar.a(a2, "os.release");
        aVar.a(a2, "os.incremental");
        aVar.a(a2, "os.android_id");
        aVar.a(a2, a[0][0]);
        aVar.a(a2, a[1][0]);
        aVar.a(a2, a[2][0]);
        aVar.a(a2, a[3][0]);
        return aVar;
    }

    private static void c(Context context) {
        try {
            c.a();
            c.a("os.system", "Android");
            a(c, context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            c.a("os.resolution", displayMetrics.widthPixels + "*" + displayMetrics.heightPixels);
            c.a("os.density", "" + displayMetrics.density);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            c.a("os.imei", telephonyManager.getDeviceId());
            c.a("os.imsi", telephonyManager.getSubscriberId());
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string)) {
                c.a("os.android_id", string);
            }
            c.a("os.version", Build.VERSION.SDK);
            c.a("os.release", Build.VERSION.RELEASE);
            c.a("os.incremental", Build.VERSION.INCREMENTAL);
            for (int i = 0; i < a.length; i++) {
                c.a(a[i][0], a[i][1]);
            }
            for (int i2 = 0; i2 < b.length; i2++) {
                c.a(b[i2][0], a(b[i2][1]));
            }
            c.a("net.mac", ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress());
            c.d();
            d = true;
        } catch (Exception unused) {
            com.iflytek.cloud.a.g.a.a.a("Failed to get prop Info");
            d = false;
        }
    }
}
