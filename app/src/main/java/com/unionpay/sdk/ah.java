package com.unionpay.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
final class ah {
    static String a;
    private static final Pattern b = Pattern.compile("^([0-9A-F]{2}:){5}([0-9A-F]{2})$");
    private static final Pattern c = Pattern.compile("[0-3][0-9a-f]{24,32}");
    private static final Pattern d = Pattern.compile("[0-3][0-9a-f]{32}");
    private static String e = null;

    private static String a() {
        String str = null;
        try {
            for (File file : new File("/").listFiles()) {
                if (file.isDirectory() && !"/sdcard".equals(file.getAbsolutePath())) {
                    if (file.canWrite()) {
                        str = a(new File(file, ".tcookieid"));
                        if (!aw.b(str)) {
                            return str;
                        }
                    }
                    if (file.listFiles() != null) {
                        for (File file2 : file.listFiles()) {
                            if (file2.isDirectory()) {
                                str = a(new File(file2, ".tcookieid"));
                                if (!aw.b(str)) {
                                    return str;
                                }
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        } catch (Throwable th) {
            al.a(th);
        }
        return str;
    }

    public static synchronized String a(Context context) {
        String str;
        String str2;
        File file;
        String str3;
        FileOutputStream fileOutputStream;
        String str4;
        synchronized (ah.class) {
            if (a == null) {
                String b2 = an.b(context, "unionpayid", "pref.deviceid.key");
                FileOutputStream fileOutputStream2 = null;
                if (aw.b(b2)) {
                    b2 = PreferenceManager.getDefaultSharedPreferences(context).getString("pref.deviceid.key", null);
                }
                String a2 = a();
                boolean b3 = b();
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File externalStorageDirectory = Environment.getExternalStorageDirectory();
                    if (b3) {
                        str4 = ".tcookieid";
                    } else {
                        str4 = ".tcookieid" + d(context);
                    }
                    str = a(new File(externalStorageDirectory, str4));
                    if (aw.b(str)) {
                        str = a(new File(Environment.getExternalStorageDirectory(), ".tid" + d(context)));
                    }
                } else {
                    str = "";
                }
                String[] strArr = {b2, a2, str};
                int i = 0;
                while (true) {
                    if (i >= 3) {
                        str2 = null;
                        break;
                    }
                    str2 = strArr[i];
                    if (!aw.b(str2) && d.matcher(str2).matches()) {
                        break;
                    }
                    i++;
                }
                if (aw.b(str2) && !aw.b(b2) && Math.random() < 0.99d) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= 3) {
                            break;
                        }
                        String str5 = strArr[i2];
                        if (!aw.b(str5) && c.matcher(str5).matches()) {
                            str2 = str5;
                            break;
                        }
                        i2++;
                    }
                }
                if (aw.b(str2)) {
                    str2 = Constant.APPLY_MODE_DECIDED_BY_BANK + aw.c(((String) null) + '-' + c(context) + '-' + b(context));
                }
                if (!str2.equals(b2)) {
                    try {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("unionpayid", 0);
                        if (sharedPreferences != null) {
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putString("pref.deviceid.key", str2);
                            edit.commit();
                        }
                    } catch (Throwable unused) {
                    }
                }
                if (!str2.equals(str)) {
                    File externalStorageDirectory2 = Environment.getExternalStorageDirectory();
                    if (b3) {
                        str3 = ".tcookieid";
                    } else {
                        str3 = ".tcookieid" + d(context);
                    }
                    try {
                        file = new File(externalStorageDirectory2, str3);
                        try {
                            fileOutputStream = new FileOutputStream(file);
                        } catch (Throwable unused2) {
                        }
                    } catch (Throwable unused3) {
                    }
                    try {
                        fileOutputStream.write(str2.getBytes());
                        if (aw.a(9)) {
                            file.getClass().getMethod("setReadable", Boolean.TYPE, Boolean.TYPE).invoke(file, true, false);
                        } else {
                            Runtime.getRuntime().exec("chmod 444 " + file.getAbsolutePath());
                        }
                        fileOutputStream.close();
                    } catch (Throwable unused4) {
                        fileOutputStream2 = fileOutputStream;
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        str2.equals(a2);
                        a = str2;
                        return a;
                    }
                }
                str2.equals(a2);
                a = str2;
            }
        }
        return a;
    }

    private static String a(File file) {
        FileInputStream fileInputStream;
        try {
            if (file.exists() && file.canRead()) {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[128];
                    String str = new String(bArr, 0, fileInputStream.read(bArr));
                    try {
                        fileInputStream.close();
                    } catch (Throwable unused) {
                    }
                    return str;
                } catch (Throwable unused2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable unused3) {
                        }
                    }
                    return null;
                }
            }
        } catch (Throwable unused4) {
            fileInputStream = null;
        }
        return null;
    }

    private static String b(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0025 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean b() {
        /*
            r0 = 9
            r1 = 1
            r2 = 0
            boolean r0 = com.unionpay.sdk.aw.a(r0)     // Catch: java.lang.Throwable -> L22
            if (r0 == 0) goto L22
            java.lang.Class<android.os.Environment> r0 = android.os.Environment.class
            java.lang.String r3 = "isExternalStorageRemovable"
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L22
            java.lang.reflect.Method r0 = r0.getMethod(r3, r4)     // Catch: java.lang.Throwable -> L22
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L22
            java.lang.Object r0 = r0.invoke(r3, r4)     // Catch: java.lang.Throwable -> L22
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Throwable -> L22
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L22
            goto L23
        L22:
            r0 = 1
        L23:
            if (r0 != 0) goto L26
            return r1
        L26:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.sdk.ah.b():boolean");
    }

    private static String c(Context context) {
        WifiInfo connectionInfo;
        if (!aw.a) {
            return null;
        }
        try {
            if (!aw.b(context, "android.permission.ACCESS_WIFI_STATE")) {
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
                    if (com.alipay.sdk.m.q.b.b.equals(macAddress)) {
                        return null;
                    }
                    if (!b.matcher(macAddress).matches()) {
                        return null;
                    }
                } catch (Throwable unused) {
                }
            }
            return macAddress;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static String d(Context context) {
        if (e == null) {
            Sensor[] sensorArr = new Sensor[64];
            for (Sensor sensor : ((SensorManager) context.getSystemService("sensor")).getSensorList(-1)) {
                if (sensor.getType() < 64 && sensor.getType() >= 0) {
                    sensorArr[sensor.getType()] = sensor;
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 64; i++) {
                if (sensorArr[i] != null) {
                    stringBuffer.append(i);
                    stringBuffer.append('.');
                    stringBuffer.append(sensorArr[i].getVendor());
                    stringBuffer.append('-');
                    stringBuffer.append(sensorArr[i].getName());
                    stringBuffer.append('-');
                    stringBuffer.append(sensorArr[i].getVersion());
                    stringBuffer.append('\n');
                }
            }
            e = String.valueOf(stringBuffer.toString().hashCode());
        }
        return e;
    }
}
