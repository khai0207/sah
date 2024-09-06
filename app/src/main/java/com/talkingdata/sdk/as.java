package com.talkingdata.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/* compiled from: td */
/* loaded from: classes.dex */
public class as {
    static TelephonyManager a = null;
    static String b = null;
    private static final String c = "pref.deviceid.key";
    private static final String d = "00:00:00:00:00:00";
    private static final String h = ".tcookieid";
    private static final Pattern e = Pattern.compile("^([0-9A-F]{2}:){5}([0-9A-F]{2})$");
    private static final Pattern f = Pattern.compile("[0-3][0-9a-f]{24,32}");
    private static final Pattern g = Pattern.compile("[0-3][0-9a-f]{32}");
    private static String i = null;
    private static boolean j = false;
    private static String k = null;

    public static void init(Context context) {
        try {
            a = (TelephonyManager) context.getSystemService("phone");
        } catch (Throwable unused) {
        }
    }

    public static synchronized String a(Context context) {
        String str;
        synchronized (as.class) {
            if (b == null) {
                b = k(context);
            }
            str = b;
        }
        return str;
    }

    public static String b(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003b A[Catch: all -> 0x0042, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0042, blocks: (B:3:0x0003, B:6:0x000b, B:10:0x0012, B:12:0x0018, B:14:0x001c, B:15:0x001f, B:17:0x0025, B:22:0x002d, B:24:0x003b), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String c(android.content.Context r3) {
        /*
            r0 = 23
            r1 = 0
            boolean r0 = com.talkingdata.sdk.bh.a(r0)     // Catch: java.lang.Throwable -> L42
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            if (r0 == 0) goto L12
            int r0 = r3.checkSelfPermission(r2)     // Catch: java.lang.Throwable -> L42
            if (r0 == 0) goto L12
            return r1
        L12:
            boolean r0 = com.talkingdata.sdk.bh.b(r3, r2)     // Catch: java.lang.Throwable -> L42
            if (r0 == 0) goto L42
            android.telephony.TelephonyManager r0 = com.talkingdata.sdk.as.a     // Catch: java.lang.Throwable -> L42
            if (r0 != 0) goto L1f
            init(r3)     // Catch: java.lang.Throwable -> L42
        L1f:
            org.json.JSONArray r3 = com.talkingdata.sdk.az.x(r3)     // Catch: java.lang.Throwable -> L42
            if (r3 == 0) goto L38
            int r0 = r3.length()     // Catch: java.lang.Throwable -> L42
            r2 = 2
            if (r0 != r2) goto L38
            r0 = 1
            org.json.JSONObject r3 = r3.getJSONObject(r0)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L42
            java.lang.String r0 = "imei"
            java.lang.String r3 = r3.getString(r0)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L42
            goto L39
        L38:
            r3 = r1
        L39:
            if (r3 != 0) goto L41
            android.telephony.TelephonyManager r3 = com.talkingdata.sdk.as.a     // Catch: java.lang.Throwable -> L42
            java.lang.String r3 = r3.getDeviceId()     // Catch: java.lang.Throwable -> L42
        L41:
            return r3
        L42:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.talkingdata.sdk.as.c(android.content.Context):java.lang.String");
    }

    public static String d(Context context) {
        try {
            if ((!bh.a(23) || context.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) && bh.b(context, "android.permission.READ_PHONE_STATE")) {
                if (a == null) {
                    init(context);
                }
                return a.getSimSerialNumber();
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static String e(Context context) {
        try {
            if ((!bh.a(23) || context.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) && bh.b(context, "android.permission.READ_PHONE_STATE")) {
                if (a == null) {
                    init(context);
                }
                return a.getSubscriberId();
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static String f(Context context) {
        ArrayList<NetworkInterface> list;
        WifiInfo connectionInfo;
        String str = null;
        if (!bh.b) {
            return null;
        }
        try {
            if (bh.a(23)) {
                try {
                    list = Collections.list(NetworkInterface.getNetworkInterfaces());
                } catch (Throwable unused) {
                }
                if (list != null && list.size() > 0) {
                    for (NetworkInterface networkInterface : list) {
                        if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                            byte[] hardwareAddress = networkInterface.getHardwareAddress();
                            if (hardwareAddress == null) {
                                return "";
                            }
                            StringBuilder sb = new StringBuilder();
                            for (byte b2 : hardwareAddress) {
                                sb.append(String.format("%02X:", Byte.valueOf(b2)));
                            }
                            if (sb.length() > 0) {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            str = sb.toString().toUpperCase().trim();
                        }
                    }
                    return !bh.b(str) ? str : "02:00:00:00:00:00";
                }
                return "02:00:00:00:00:00";
            }
            if (!bh.b(context, "android.permission.ACCESS_WIFI_STATE")) {
                return null;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (!wifiManager.isWifiEnabled() || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return null;
            }
            String macAddress = connectionInfo.getMacAddress();
            if (macAddress != null) {
                try {
                    macAddress = macAddress.toUpperCase().trim();
                    if ("00:00:00:00:00:00".equals(macAddress)) {
                        return null;
                    }
                    if (!e.matcher(macAddress).matches()) {
                        return null;
                    }
                } catch (Throwable th) {
                    str = macAddress;
                    th = th;
                    ce.postSDKError(th);
                    return str;
                }
            }
            return macAddress;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static final String g(Context context) {
        try {
            if (!j) {
                at atVar = new at(context);
                atVar.setName("TD_ADID");
                atVar.start();
            }
            return i;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    public static final String a() {
        try {
            if (bh.a(9)) {
                return Build.SERIAL;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final String h(Context context) {
        try {
            String f2 = f(context);
            if (!TextUtils.isEmpty(f2)) {
                f2 = String.valueOf(Long.parseLong(f2.replaceAll(":", ""), 16));
            }
            return "2|" + f2 + "|" + b(context) + "|" + c(context) + "|" + e(context) + "|" + d(context) + "|" + a(context) + "|" + g(context) + "|" + a();
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String k(Context context) {
        String str;
        String i2 = i(context);
        String b2 = b();
        boolean c2 = c();
        String a2 = a(context, c2);
        int i3 = 0;
        String[] strArr = {i2, b2, a2};
        int i4 = 0;
        while (true) {
            if (i4 >= 3) {
                str = null;
                break;
            }
            str = strArr[i4];
            if (!bh.b(str) && g.matcher(str).matches()) {
                break;
            }
            i4++;
        }
        if (bh.b(str) && !bh.b(i2) && Math.random() < 0.99d) {
            while (true) {
                if (i3 >= 3) {
                    break;
                }
                String str2 = strArr[i3];
                if (!bh.b(str2) && f.matcher(str2).matches()) {
                    str = str2;
                    break;
                }
                i3++;
            }
        }
        if (bh.b(str)) {
            str = l(context);
        }
        if (!str.equals(i2)) {
            b(context, str);
        }
        if (!str.equals(a2)) {
            a(context, str, c2);
        }
        if (!str.equals(b2)) {
            a(context, str);
        }
        return str;
    }

    static String a(Context context, boolean z) {
        if (bh.a(23) && context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
            return null;
        }
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return "";
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String str = h;
        if (!z) {
            str = h + j(context);
        }
        String a2 = a(new File(externalStorageDirectory, str));
        if (!bh.b(a2)) {
            return a2;
        }
        return a(new File(Environment.getExternalStorageDirectory(), ".tid" + j(context)));
    }

    static String b() {
        String str = null;
        try {
            File[] listFiles = new File("/").listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file : listFiles) {
                    if (file.isDirectory() && !"/sdcard".equals(file.getAbsolutePath())) {
                        if (file.canWrite()) {
                            str = a(new File(file, h));
                            if (!bh.b(str)) {
                                return str;
                            }
                        }
                        if (file.listFiles() != null) {
                            for (File file2 : file.listFiles()) {
                                if (file2.isDirectory()) {
                                    str = a(new File(file2, h));
                                    if (!bh.b(str)) {
                                        return str;
                                    }
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    private static String a(File file) {
        try {
            if (!file.exists() || !file.canRead()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[128];
            int read = fileInputStream.read(bArr);
            fileInputStream.close();
            return new String(bArr, 0, read);
        } catch (Throwable unused) {
            return null;
        }
    }

    static String i(Context context) {
        try {
            String b2 = bd.b(context, "tdid", c, (String) null);
            return bh.b(b2) ? PreferenceManager.getDefaultSharedPreferences(context).getString(c, null) : b2;
        } catch (Throwable unused) {
            return "";
        }
    }

    private static void a(Context context, String str, boolean z) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            String str2 = h;
            if (!z) {
                str2 = h + j(context);
            }
            a(new File(externalStorageDirectory, str2), str);
        } catch (Throwable unused) {
        }
    }

    private static void a(Context context, String str) {
        try {
            File[] listFiles = new File("/").listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file : listFiles) {
                    if (file.isDirectory() && !"/sdcard".equals(file.getAbsolutePath())) {
                        if (file.canWrite()) {
                            if (!new File(file, h + j(context)).exists()) {
                                a(new File(file, h), str);
                            }
                        }
                        if (file.listFiles() != null) {
                            for (File file2 : file.listFiles()) {
                                if (file2.isDirectory() && file2.canWrite()) {
                                    if (!new File(file2, h + j(context)).exists()) {
                                        a(new File(file2, h), str);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            if (bh.a(9)) {
                file.getClass().getMethod("setReadable", Boolean.TYPE, Boolean.TYPE).invoke(file, true, false);
            } else {
                Runtime.getRuntime().exec("chmod 444 " + file.getAbsolutePath());
            }
        } catch (Throwable unused) {
        }
    }

    private static void b(Context context, String str) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("tdid", 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(c, str);
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    private static String l(Context context) {
        return Constant.APPLY_MODE_DECIDED_BY_BANK + bh.c(m(context));
    }

    private static String m(Context context) {
        try {
            return c(context) + '-' + f(context) + '-' + b(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    static boolean c() {
        boolean z;
        if (bh.a(9)) {
            z = ((Boolean) Environment.class.getMethod("isExternalStorageRemovable", new Class[0]).invoke(null, new Object[0])).booleanValue();
            return !z;
        }
        z = true;
        return !z;
    }

    static String j(Context context) {
        if (k == null) {
            try {
                Sensor[] sensorArr = new Sensor[64];
                for (Sensor sensor : ((SensorManager) context.getSystemService("sensor")).getSensorList(-1)) {
                    if (sensor.getType() < 64 && sensor.getType() >= 0) {
                        sensorArr[sensor.getType()] = sensor;
                    }
                }
                StringBuffer stringBuffer = new StringBuffer();
                for (int i2 = 0; i2 < 64; i2++) {
                    if (sensorArr[i2] != null) {
                        stringBuffer.append(i2);
                        stringBuffer.append('.');
                        stringBuffer.append(sensorArr[i2].getVendor());
                        stringBuffer.append('-');
                        stringBuffer.append(sensorArr[i2].getName());
                        stringBuffer.append('-');
                        stringBuffer.append(sensorArr[i2].getVersion());
                        stringBuffer.append('\n');
                    }
                }
                k = String.valueOf(stringBuffer.toString().hashCode());
            } catch (Throwable unused) {
            }
        }
        return k;
    }
}
