package com.talkingdata.sdk;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.media.AudioManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.core.os.EnvironmentCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class au {
    private static final int a = 3600000;
    private static final String b = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
    private static final String c = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";
    private static final String d = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";
    private static final String e = "/sys/class/switch/h2w/state";
    private static final FileFilter f = new av();
    private static BroadcastReceiver g = new aw();

    /* compiled from: td */
    /* loaded from: classes.dex */
    public static class a {
        static final int a = 0;
        static final int b = 1;
        static final int c = 2;
        static final int d = 3;
    }

    public static String[] m() {
        return null;
    }

    public static String a() {
        return ab.i + Build.VERSION.RELEASE;
    }

    public static String b() {
        return Build.MANUFACTURER.trim();
    }

    public static String c() {
        return Build.BRAND.trim();
    }

    public static String d() {
        return Build.MODEL.trim();
    }

    public static int e() {
        return TimeZone.getDefault().getRawOffset() / a;
    }

    public static String f() {
        try {
            String trim = Build.MODEL.trim();
            String a2 = a(Build.MANUFACTURER.trim(), trim);
            if (TextUtils.isEmpty(a2)) {
                a2 = a(Build.BRAND.trim(), trim);
            }
            if (a2 == null) {
                a2 = "";
            }
            return a2 + ":" + trim;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static JSONObject a(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("nfcStatus", b(context));
            jSONObject.put("appsRegistedHCE", e(context));
            jSONObject.put("ssMode", f(context));
            return jSONObject;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    public static int b(Context context) {
        NfcAdapter defaultAdapter;
        if (context == null) {
            return 0;
        }
        try {
            if (!bh.a(10) || (defaultAdapter = ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter()) == null) {
                return 0;
            }
            if (!defaultAdapter.isEnabled()) {
                return 1;
            }
            if (bh.a(19)) {
                if (context.getPackageManager().hasSystemFeature("android.hardware.nfc.hce")) {
                    return 3;
                }
            }
            return 2;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return 0;
        }
    }

    private static JSONArray e(Context context) {
        ServiceInfo[] serviceInfoArr;
        if (!bh.a(19)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            List<PackageInfo> a2 = a(context, 4);
            if (a2 != null) {
                for (PackageInfo packageInfo : a2) {
                    if (packageInfo != null && (serviceInfoArr = packageInfo.services) != null) {
                        int length = serviceInfoArr.length;
                        int i = 0;
                        while (true) {
                            if (i < length) {
                                ServiceInfo serviceInfo = serviceInfoArr[i];
                                try {
                                    Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(serviceInfo.packageName, serviceInfo.name), 128).metaData;
                                    if (bundle != null && bundle.containsKey("android.nfc.cardemulation.host_apdu_service")) {
                                        jSONArray.put(packageInfo.packageName);
                                        break;
                                    }
                                } catch (Throwable unused) {
                                }
                                i++;
                            }
                        }
                    }
                }
            }
            return jSONArray;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static List a(Context context, int i) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getInstalledPackages(i);
        } catch (Throwable unused) {
            ArrayList arrayList = new ArrayList();
            BufferedReader bufferedReader = null;
            try {
                try {
                    Process exec = Runtime.getRuntime().exec("pm list packages");
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            arrayList.add(packageManager.getPackageInfo(readLine.substring(readLine.indexOf(58) + 1), i));
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            try {
                                ce.postSDKError(th);
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                return arrayList;
                            } catch (Throwable th2) {
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable unused2) {
                                    }
                                }
                                throw th2;
                            }
                        }
                    }
                    exec.waitFor();
                    bufferedReader2.close();
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable unused3) {
                return arrayList;
            }
            return arrayList;
        }
    }

    private static int f(Context context) {
        try {
            if (bh.a(19)) {
                return CardEmulation.getInstance(((NfcManager) context.getSystemService("nfc")).getDefaultAdapter()).getSelectionModeForCategory("payment");
            }
            return -1;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return -1;
        }
    }

    private static String a(String str, String str2) {
        try {
            String lowerCase = str.toLowerCase();
            if (!lowerCase.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) && !lowerCase.startsWith("alps") && !lowerCase.startsWith("android") && !lowerCase.startsWith("sprd") && !lowerCase.startsWith("spreadtrum") && !lowerCase.startsWith("rockchip") && !lowerCase.startsWith("wondermedia") && !lowerCase.startsWith("mtk") && !lowerCase.startsWith("mt65") && !lowerCase.startsWith("nvidia") && !lowerCase.startsWith("brcm") && !lowerCase.startsWith("marvell")) {
                if (!str2.toLowerCase().contains(lowerCase)) {
                    return str;
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static int g() {
        return Build.VERSION.SDK_INT;
    }

    public static String h() {
        return Build.VERSION.RELEASE;
    }

    public static String i() {
        return Locale.getDefault().getLanguage();
    }

    public static String j() {
        return Locale.getDefault().getCountry();
    }

    public static JSONObject a(Context context, JSONObject jSONObject) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics != null) {
                int i = displayMetrics.widthPixels;
                int i2 = displayMetrics.heightPixels;
                jSONObject.put("pixel", Math.min(i, i2) + "*" + Math.max(i, i2) + "*" + displayMetrics.densityDpi);
                jSONObject.put("densityDpi", displayMetrics.densityDpi);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public static JSONObject b(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put("brightness", Settings.System.getInt(context.getContentResolver(), "screen_brightness"));
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public static String c(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics == null) {
                return "";
            }
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            return Math.min(i, i2) + "*" + Math.max(i, i2) + "*" + displayMetrics.densityDpi;
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004d A[Catch: all -> 0x0086, TryCatch #2 {all -> 0x0086, blocks: (B:7:0x0013, B:14:0x002c, B:17:0x003c, B:19:0x004d, B:21:0x0054, B:23:0x005d, B:25:0x006d, B:27:0x0077, B:30:0x007a, B:32:0x007d, B:39:0x0034), top: B:6:0x0013 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] k() {
        /*
            r0 = 4
            java.lang.String[] r1 = new java.lang.String[r0]
            r2 = 0
            r3 = 0
        L5:
            if (r3 >= r0) goto Le
            java.lang.String r4 = ""
            r1[r3] = r4
            int r3 = r3 + 1
            goto L5
        Le:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L86
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L86
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L86
            r5 = 1024(0x400, float:1.435E-42)
            r4.<init>(r3, r5)     // Catch: java.lang.Throwable -> L86
        L21:
            r5 = 1
            java.lang.String r6 = r4.readLine()     // Catch: java.lang.Throwable -> L34
            if (r6 == 0) goto L2c
            r0.add(r6)     // Catch: java.lang.Throwable -> L34
            goto L21
        L2c:
            r4.close()     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L86
            r3.close()     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L86
        L32:
            r3 = 1
            goto L3b
        L34:
            r4.close()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L86
            r3.close()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L86
        L3a:
            r3 = 0
        L3b:
            r4 = 3
            java.lang.String[] r6 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L86
            java.lang.String r7 = "Processor\\s*:\\s*(.*)"
            r6[r2] = r7     // Catch: java.lang.Throwable -> L86
            java.lang.String r7 = "CPU\\s*variant\\s*:\\s*0x(.*)"
            r6[r5] = r7     // Catch: java.lang.Throwable -> L86
            r7 = 2
            java.lang.String r8 = "Hardware\\s*:\\s*(.*)"
            r6[r7] = r8     // Catch: java.lang.Throwable -> L86
            if (r3 == 0) goto L7d
            int r3 = r0.size()     // Catch: java.lang.Throwable -> L86
            r7 = 0
        L52:
            if (r7 >= r4) goto L7d
            r8 = r6[r7]     // Catch: java.lang.Throwable -> L86
            java.util.regex.Pattern r8 = java.util.regex.Pattern.compile(r8)     // Catch: java.lang.Throwable -> L86
            r9 = 0
        L5b:
            if (r9 >= r3) goto L7a
            java.lang.Object r10 = r0.get(r9)     // Catch: java.lang.Throwable -> L86
            java.lang.String r10 = (java.lang.String) r10     // Catch: java.lang.Throwable -> L86
            java.util.regex.Matcher r10 = r8.matcher(r10)     // Catch: java.lang.Throwable -> L86
            boolean r11 = r10.find()     // Catch: java.lang.Throwable -> L86
            if (r11 == 0) goto L77
            java.util.regex.MatchResult r10 = r10.toMatchResult()     // Catch: java.lang.Throwable -> L86
            java.lang.String r10 = r10.group(r5)     // Catch: java.lang.Throwable -> L86
            r1[r7] = r10     // Catch: java.lang.Throwable -> L86
        L77:
            int r9 = r9 + 1
            goto L5b
        L7a:
            int r7 = r7 + 1
            goto L52
        L7d:
            java.lang.String r0 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            java.lang.String r0 = c(r0)     // Catch: java.lang.Throwable -> L86
            r1[r4] = r0     // Catch: java.lang.Throwable -> L86
            goto L8a
        L86:
            r0 = move-exception
            com.talkingdata.sdk.ce.postSDKError(r0)
        L8a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.talkingdata.sdk.au.k():java.lang.String[]");
    }

    public static JSONObject l() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.alipay.sdk.m.h.c.e, k()[2]);
            jSONObject.put("coreNum", r());
            jSONObject.put("maxFreq", a(b));
            jSONObject.put("minFreq", a(c));
            jSONObject.put("curFreq", a(d));
            return jSONObject;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0031, code lost:
    
        if (r3 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int a(java.lang.String r3) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            r1 = -1
            if (r0 == 0) goto L8
            return r1
        L8:
            r0 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L29
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L29
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L26
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L26
            java.lang.String r0 = r3.readLine()     // Catch: java.lang.Throwable -> L27
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Throwable -> L27
            int r1 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L27
            r2.close()     // Catch: java.lang.Throwable -> L22
        L22:
            r3.close()     // Catch: java.lang.Throwable -> L34
            goto L34
        L26:
            r3 = r0
        L27:
            r0 = r2
            goto L2a
        L29:
            r3 = r0
        L2a:
            if (r0 == 0) goto L31
            r0.close()     // Catch: java.lang.Throwable -> L30
            goto L31
        L30:
        L31:
            if (r3 == 0) goto L34
            goto L22
        L34:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.talkingdata.sdk.au.a(java.lang.String):int");
    }

    private static int r() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(f).length;
        } catch (Throwable unused) {
            return 1;
        }
    }

    public static int[] n() {
        int[] iArr = {0, 0};
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                int blockSize = statFs.getBlockSize();
                int blockCount = statFs.getBlockCount();
                int availableBlocks = statFs.getAvailableBlocks();
                iArr[0] = (blockCount * (blockSize / 512)) / 2;
                iArr[1] = (availableBlocks * (blockSize / 512)) / 2;
            }
        } catch (Throwable unused) {
        }
        return iArr;
    }

    public static int[] o() {
        int[] iArr = {0, 0};
        int[] iArr2 = new int[4];
        for (int i = 0; i < 4; i++) {
            iArr2[i] = 0;
        }
        try {
            FileReader fileReader = new FileReader("/proc/meminfo");
            BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
            for (int i2 = 0; i2 < 4; i2++) {
                try {
                    try {
                        iArr2[i2] = b(bufferedReader.readLine());
                    } catch (IOException unused) {
                        bufferedReader.close();
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                            fileReader.close();
                        } catch (IOException unused2) {
                        }
                        throw th;
                    }
                } catch (IOException unused3) {
                }
            }
            iArr[0] = iArr2[0];
            iArr[1] = iArr2[1] + iArr2[2] + iArr2[3];
            bufferedReader.close();
            fileReader.close();
        } catch (Throwable th2) {
            ce.postSDKError(th2);
        }
        return iArr;
    }

    public static int[] p() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            return new int[]{(statFs.getBlockCount() * (statFs.getBlockSize() / 512)) / 2, (statFs.getAvailableBlocks() * (statFs.getBlockSize() / 512)) / 2, (statFs2.getBlockCount() * (statFs2.getBlockSize() / 512)) / 2, (statFs2.getAvailableBlocks() * (statFs2.getBlockSize() / 512)) / 2};
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    private static int b(String str) {
        try {
            Matcher matcher = Pattern.compile("([0-9]+)").matcher(str);
            return Integer.valueOf(matcher.find() ? matcher.toMatchResult().group(0) : "").intValue();
        } catch (Exception e2) {
            ce.postSDKError(e2);
            return 0;
        }
    }

    public static int q() {
        try {
            Matcher matcher = Pattern.compile("\\s*([0-9]+)").matcher(c("/sys/class/power_supply/battery/full_bat"));
            if (matcher.find()) {
                return Integer.valueOf(matcher.toMatchResult().group(0)).intValue();
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static String c(String str) {
        String str2 = null;
        try {
            FileReader fileReader = new FileReader(str);
            char[] cArr = new char[1024];
            BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
            while (true) {
                int read = bufferedReader.read(cArr, 0, 1024);
                if (-1 == read) {
                    break;
                }
                str2 = str2 + new String(cArr, 0, read);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Throwable unused) {
        }
        return str2;
    }

    public static JSONObject d(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mobile", n(context));
            jSONObject.put("wifi", i(context));
            jSONObject.put("gps", h(context));
            jSONObject.put("telephone", m(context));
            jSONObject.put("nfc", k(context));
            jSONObject.put("bluetooth", j(context));
            jSONObject.put("otg", g(context));
            jSONObject.put("insertEarphones", l(context));
            return jSONObject;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    private static boolean g(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.usb.host");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean h(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.location.gps");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean i(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.wifi");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean j(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.bluetooth");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean k(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.nfc");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean l(Context context) {
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (audioManager != null) {
                return audioManager.isWiredHeadsetOn();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean m(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getPhoneType() != 0;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean n(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.telephony");
        } catch (Throwable unused) {
            return false;
        }
    }
}
