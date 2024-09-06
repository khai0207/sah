package com.umeng.analytics.social;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.umeng.analytics.social.UMPlatformData;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: UMUtils.java */
/* loaded from: classes.dex */
public abstract class f {
    private static Map<String, String> a;

    protected static String[] a(Context context, String str, UMPlatformData... uMPlatformDataArr) throws a {
        if (uMPlatformDataArr == null || uMPlatformDataArr.length == 0) {
            throw new a("platform data is null");
        }
        String a2 = a(context);
        if (TextUtils.isEmpty(a2)) {
            throw new a("can`t get appkey.");
        }
        ArrayList arrayList = new ArrayList();
        String str2 = "http://log.umsns.com/share/api/" + a2 + "/";
        Map<String, String> map = a;
        if (map == null || map.isEmpty()) {
            a = c(context);
        }
        Map<String, String> map2 = a;
        if (map2 != null && !map2.isEmpty()) {
            for (Map.Entry<String, String> entry : a.entrySet()) {
                arrayList.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        arrayList.add("date=" + String.valueOf(System.currentTimeMillis()));
        arrayList.add("channel=" + e.e);
        if (!TextUtils.isEmpty(str)) {
            arrayList.add("topic=" + str);
        }
        arrayList.addAll(a(uMPlatformDataArr));
        String b = b(uMPlatformDataArr);
        if (b == null) {
            b = Constants.NULL_VERSION_ID;
        }
        String str3 = str2 + "?" + a(arrayList);
        while (str3.contains("%2C+")) {
            str3 = str3.replace("%2C+", com.alipay.sdk.m.o.a.l);
        }
        while (str3.contains("%3D")) {
            str3 = str3.replace("%3D", "=");
        }
        while (str3.contains("%5B")) {
            str3 = str3.replace("%5B", "");
        }
        while (str3.contains("%5D")) {
            str3 = str3.replace("%5D", "");
        }
        b.c(com.umeng.analytics.a.e, "URL:" + str3);
        b.c(com.umeng.analytics.a.e, "BODY:" + b);
        return new String[]{str3, b};
    }

    private static String a(List<String> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(URLEncoder.encode(list.toString()).getBytes());
            return byteArrayOutputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> a(UMPlatformData... uMPlatformDataArr) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (UMPlatformData uMPlatformData : uMPlatformDataArr) {
            sb.append(uMPlatformData.getMeida().toString());
            sb.append(',');
            sb2.append(uMPlatformData.getUsid());
            sb2.append(',');
            sb3.append(uMPlatformData.getWeiboId());
            sb3.append(',');
        }
        if (uMPlatformDataArr.length > 0) {
            sb.deleteCharAt(sb.length() - 1);
            sb2.deleteCharAt(sb2.length() - 1);
            sb3.deleteCharAt(sb3.length() - 1);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("platform=" + sb.toString());
        arrayList.add("usid=" + sb2.toString());
        if (sb3.length() > 0) {
            arrayList.add("weiboid=" + sb3.toString());
        }
        return arrayList;
    }

    private static String b(UMPlatformData... uMPlatformDataArr) {
        int i;
        JSONObject jSONObject = new JSONObject();
        int length = uMPlatformDataArr.length;
        while (i < length) {
            UMPlatformData uMPlatformData = uMPlatformDataArr[i];
            UMPlatformData.GENDER gender = uMPlatformData.getGender();
            String name = uMPlatformData.getName();
            if (gender == null) {
                try {
                    i = TextUtils.isEmpty(name) ? i + 1 : 0;
                } catch (Exception e) {
                    throw new a("build body exception", e);
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            String str = "";
            jSONObject2.put("gender", gender == null ? "" : String.valueOf(gender.value));
            if (name != null) {
                str = String.valueOf(name);
            }
            jSONObject2.put(com.alipay.sdk.m.h.c.e, str);
            jSONObject.put(uMPlatformData.getMeida().toString(), jSONObject2);
        }
        if (jSONObject.length() == 0) {
            return null;
        }
        return jSONObject.toString();
    }

    private static Map<String, String> c(Context context) throws a {
        HashMap hashMap = new HashMap();
        Map<String, String> b = b(context);
        if (b != null && !b.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry<String, String> entry : b.entrySet()) {
                if (!TextUtils.isEmpty(entry.getValue())) {
                    sb2.append(entry.getKey());
                    sb2.append(",");
                    sb.append(entry.getValue());
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                hashMap.put("deviceid", sb.toString());
            }
            if (sb2.length() > 0) {
                sb2.deleteCharAt(sb2.length() - 1);
                hashMap.put("idtype", sb2.toString());
            }
            return hashMap;
        }
        throw new a("can`t get device id.");
    }

    protected static String a(Context context) {
        String str = e.d;
        if (!TextUtils.isEmpty(str)) {
            b.b(com.umeng.analytics.a.e, "use usefully appkey from constant field.");
            return str;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return null;
            }
            String string = applicationInfo.metaData.getString("UMENG_APPKEY");
            if (string != null) {
                return string.trim();
            }
            b.b(com.umeng.analytics.a.e, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            return null;
        } catch (Exception e) {
            b.b(com.umeng.analytics.a.e, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e);
            return null;
        }
    }

    public static Map<String, String> b(Context context) {
        HashMap hashMap = new HashMap();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            b.e(com.umeng.analytics.a.e, "No IMEI.");
        }
        String str = null;
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                str = telephonyManager.getDeviceId();
            }
        } catch (Exception e) {
            b.e(com.umeng.analytics.a.e, "No IMEI.", e);
        }
        String d = d(context);
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (!TextUtils.isEmpty(d)) {
            hashMap.put("mac", d);
        }
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("imei", str);
        }
        if (!TextUtils.isEmpty(string)) {
            hashMap.put("android_id", string);
        }
        return hashMap;
    }

    private static boolean a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    private static String d(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            b.e(com.umeng.analytics.a.e, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e) {
            b.e(com.umeng.analytics.a.e, "Could not get mac address." + e.toString());
            return "";
        }
    }
}
