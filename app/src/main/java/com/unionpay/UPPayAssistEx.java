package com.unionpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.utils.UPUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UPPayAssistEx {
    private static String A = "";
    private static int B = 0;
    private static boolean C = false;
    private static com.unionpay.a.d D = null;
    private static Handler E = null;
    private static String F = "application/vnd.android.package-archive";
    private static String G = "http://mobile.unionpay.com/getclient?platform=android&type=securepayplugin";
    private static String H = "[{\"type\":\"app\",\"sort\":100,\"package_info\":[{\"schema\":\"com.unionpay.uppay\",\"version\":\".*\",\"sign\":\"23137B5BE6AEF6682B41E6536F08367E0949A1CC\",\"sort\":101}],\"need_install\":false,\"install_msg\":\"请先安装银联在线支付服务，安装完成后重新发起付款\",\"url\":\"https://mobile.unionpay.com/getclient?platform=android&type=securepayplugin\",\"download_app\":\"UPPayPluginEx.apk\",\"download_title\":\"银联在线支付服务\",\"download_desp\":\"正在下载银联在线支付服务…\",\"md5\":\"D75BB2802E61738A9A03BF014F927D9A\"},{\"type\":\"jar\",\"sort\":200}]";
    private static JSONArray I = null;
    private static IntentFilter J = new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE");
    private static BroadcastReceiver K = new a();
    private static final Handler.Callback L = new b();
    public static final int PLUGIN_NOT_FOUND = -1;
    public static final int PLUGIN_VALID = 0;
    private static String a = "SpId";
    private static String b = "paydata";
    private static String c = "SysProvide";
    private static String d = "UseTestMode";
    private static String e = "SecurityChipType";
    private static String f = "uppayuri";
    private static String g = "resultIntentAction";
    private static String h = "reqOriginalId";
    private static String i = "wapurl";
    private static String j = "dlgstyle";
    private static String k = "com.unionpay.uppay";
    private static String l = "com.unionpay.uppay.PayActivity";
    private static String m = "ex_mode";
    private static String n = "server";
    private static String o = "source";
    private static String p = "samsung_out";
    private static String q = "";
    private static boolean r = false;
    private static int s = 10;
    private static Context t = null;

    /* renamed from: u, reason: collision with root package name */
    private static String f35u = "";
    private static String v = null;
    private static String w = null;
    private static String x = "";
    private static String y = "";
    private static boolean z = false;

    private static int a(Activity activity, String str, String str2) {
        try {
            String[] strArr = {"30820267308201d0a00302010202044", "ecb7d98300d06092a8", "64886f70d01010505003078310b30090603550", "406130238363111300f060355040813085", "368616e676", "861693111300f060355040713085368616e67686169311730", "15060355040a130e4368696e6120556e696f6e50617931173015060355040b130e4", "368696e612055", "6e696f6e5061793111300f06035504031308556e696f6e5061", "79301e170d3131313132323130343634385a170d333631313135313034", "3634385a3078310b300906035504061302383631", "11300f060355040813085368616e67686169311130", "0f060355040713085368616e676861693117", "3015060355040a130e4368696e6120556e696", "f6e50617931173015060355040b130e4368696e6120556e696", "f6e5061793111300f06035504031308556e696f6e50617930819f300d060", "92a864886f70d010101050003818d0030818902818100c42e6236d5054ffccaa", "a430ecd929d562", "b1ff56cef0e21c87260c63ce3ca868bf5974c14", "d9255940da7b6cd07483f4b4243fd1825b2705", "08eb9b5c67474d027fa03ce35109b11604083ab6bb4df2c46240f879f", "8cc1d6ed5e1b2cc00489215aec3fc2eac008e767b0215981cb5e", "e94ddc285669ec06b8a405dd4341eac4ea7030203010001300d06092a864886f70d010105050003818", "1001a3e74c601e3beb1b7ae4f9ab2872a0aaf1dbc2cba89c7528cd", "54aa526e7a37d8ba2311a1d3d2ab79b3fbeaf3ebb9e7da9e7cdd9be1ae5a53595f47", "b1fdf62b0f540fca5458b063af9354925a6c3505a18ff164b6b195f6e517eaee1fb783", "64c2f89fdffa16729c9779f99562bc189d2ce4722ba0faedb11aa22d0d9db228fda"};
            PackageManager packageManager = activity.getPackageManager();
            packageManager.getActivityInfo(activity.getComponentName(), 128);
            packageManager.getApplicationInfo("com.unionpay.uppay", 8192);
            PackageInfo packageInfo = packageManager.getPackageInfo("com.unionpay.uppay", 4160);
            String charsString = packageInfo.signatures[0].toCharsString();
            String str3 = "";
            for (int i2 = 0; i2 < 27; i2++) {
                str3 = str3 + strArr[i2];
            }
            if (!(charsString != null && charsString.equals(str3) && packageInfo.versionCode >= 31)) {
                return -1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt(h, 1);
            bundle.putString(f, str);
            bundle.putString(g, str2);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClassName(k, l);
            activity.startActivity(intent);
            return 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    private static String a(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            String absolutePath = context.getFilesDir().getAbsolutePath();
            if (absolutePath == null) {
                return "";
            }
            String str2 = absolutePath + File.separator + str;
            if (!new File(str2).exists()) {
                FileOutputStream openFileOutput = context.openFileOutput(str, 1);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    openFileOutput.write(bArr, 0, read);
                }
                openFileOutput.close();
                open.close();
            }
            return str2;
        } catch (Exception unused) {
            return "";
        }
    }

    static String a(Context context, boolean z2) {
        int i2;
        NfcAdapter defaultAdapter;
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            try {
                i2 = Integer.parseInt(x);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } catch (NumberFormatException unused) {
            i2 = 0;
        }
        if (!z2) {
            jSONObject.put("tn", UPUtils.forWap(i2, com.unionpay.utils.b.a(f35u)));
        }
        jSONObject.put("imei", ((TelephonyManager) context.getSystemService("phone")).getDeviceId());
        jSONObject.put("locale", Locale.getDefault().toString().startsWith("zh") ? "zh_CN" : "en_US");
        jSONObject.put("terminal_version", "3.3.2");
        jSONObject.put("terminal_resolution", (context.getResources().getDisplayMetrics().widthPixels + "*" + context.getResources().getDisplayMetrics().heightPixels).trim());
        jSONObject.put("os_name", "android");
        jSONObject.put("os_version", Build.VERSION.RELEASE.trim());
        String trim = Build.MODEL.trim();
        String str2 = "";
        if (trim != null) {
            trim.replace(" ", "");
        }
        jSONObject.put("device_model", trim);
        jSONObject.put("mac", com.unionpay.utils.d.a(context));
        try {
            jSONObject.put("time_zone", TimeZone.getDefault().getDisplayName(false, 0));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                str = "disConnect";
            } else if (activeNetworkInfo.getType() != 0) {
                str = activeNetworkInfo.getType() == 1 ? "wifi" : "other";
            } else if (activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                str = "mobile:" + activeNetworkInfo.getExtraInfo();
            } else {
                str = "mobile";
            }
            jSONObject.put("network_mode", str);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            if (subscriberId != null) {
                str2 = subscriberId;
            }
            jSONObject.put("imsi", str2);
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        try {
            jSONObject.put("baseband_version", com.unionpay.utils.d.a());
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        try {
            jSONObject.put("root", new File("/system/bin/su").exists() ? "1" : "0");
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        try {
            StringBuffer stringBuffer = new StringBuffer(Constant.DEFAULT_CVN2);
            if (Build.VERSION.SDK_INT >= 10 && (defaultAdapter = ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter()) != null) {
                if (defaultAdapter.isEnabled()) {
                    stringBuffer.setCharAt(0, '1');
                } else {
                    stringBuffer.setCharAt(0, '2');
                }
                if (Build.VERSION.SDK_INT >= 19 && context.getPackageManager().hasSystemFeature("android.hardware.nfc.hce")) {
                    stringBuffer.setCharAt(1, '1');
                }
            }
            jSONObject.put("support_map", stringBuffer.toString());
        } catch (Exception e8) {
            e8.printStackTrace();
        }
        if (z2) {
            try {
                Class.forName("com.unionpay.uppay.PayActivity");
                jSONObject.put("has_sdk", "1");
            } catch (ClassNotFoundException unused2) {
                jSONObject.put("has_sdk", "0");
            }
        }
        String jSONObject2 = jSONObject.toString();
        com.unionpay.utils.g.b("uppay", "init: " + jSONObject2);
        return jSONObject2;
    }

    static /* synthetic */ void a(Context context) {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:com.android.providers.downloads"));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
            context.startActivity(new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS"));
        }
    }

    static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        boolean z2;
        String a2 = a(context, str2);
        if (a2 == null || TextUtils.isEmpty(a2)) {
            z2 = false;
        } else {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse("file:" + a2), "application/vnd.android.package-archive");
            context.startActivity(intent);
            z2 = true;
        }
        if (z2) {
            return;
        }
        y = str5;
        A = str2;
        if (z) {
            return;
        }
        try {
            context.registerReceiver(K, J);
            DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            request.setMimeType(F);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str2);
            request.setTitle(str3);
            request.setDescription(str4);
            request.setNotificationVisibility(1);
            UPUtils.a(context, downloadManager.enqueue(request), "id");
            z = true;
        } catch (Exception e2) {
            if (!(e2 instanceof IllegalArgumentException)) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(com.unionpay.utils.h.a().d);
            builder.setMessage(com.unionpay.utils.h.a().f);
            builder.setPositiveButton(com.unionpay.utils.h.a().b, new f(context));
            builder.setNegativeButton(com.unionpay.utils.h.a().c, new g(context));
            builder.create().show();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x014a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ void a(android.content.Context r16, org.json.JSONArray r17, int r18) {
        /*
            Method dump skipped, instructions count: 464
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.UPPayAssistEx.a(android.content.Context, org.json.JSONArray, int):void");
    }

    static void a(String str) {
        Bundle bundle = new Bundle();
        a(f35u, bundle, x);
        bundle.putString(a, v);
        bundle.putString(c, w);
        bundle.putString(b, f35u);
        bundle.putString(o, q);
        bundle.putString(n, str);
        bundle.putBoolean(j, r);
        bundle.putInt(h, 2);
        try {
            Class<?> cls = Class.forName("com.unionpay.uppay.PayActivity");
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(t, cls);
            if (t instanceof Activity) {
                ((Activity) t).startActivityForResult(intent, s);
            } else {
                intent.addFlags(268435456);
                t.startActivity(intent);
            }
        } catch (ClassNotFoundException unused) {
        }
    }

    private static void a(String str, Bundle bundle, String str2) {
        if (str == null || str.trim().length() <= 0) {
            return;
        }
        if (str.trim().charAt(0) != '<') {
            bundle.putString(m, str2);
        } else if (str2 == null || !str2.trim().equalsIgnoreCase("00")) {
            bundle.putBoolean(d, true);
        } else {
            bundle.putBoolean(d, false);
        }
    }

    private static int b(Context context, String str, String str2, String str3, String str4, String str5) {
        int i2;
        int i3;
        t = context;
        f35u = str3;
        v = str;
        w = str2;
        x = str4;
        q = str5;
        if (TextUtils.isEmpty(str5)) {
            r = false;
        } else {
            r = true;
        }
        B = 0;
        C = false;
        System.loadLibrary("entryexpro");
        String a2 = UPUtils.a(t, "configs");
        String a3 = UPUtils.a(context, "mode");
        String a4 = UPUtils.a(context, "or");
        if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4)) {
            try {
                JSONObject jSONObject = new JSONObject(a2);
                String a5 = com.unionpay.utils.f.a(jSONObject, "sign");
                try {
                    i3 = Integer.parseInt(a3);
                } catch (NumberFormatException unused) {
                    i3 = 0;
                }
                String str6 = new String(Base64.decode(jSONObject.getString("configs"), 2));
                if (UPUtils.forConfig(i3, a5).equals(com.unionpay.utils.b.a(UPUtils.a(str6 + a4)))) {
                    H = str6;
                }
            } catch (JSONException unused2) {
            }
        }
        try {
            I = b(new JSONArray(H), "sort");
        } catch (JSONException unused3) {
        }
        try {
            i2 = Integer.parseInt(x);
        } catch (NumberFormatException unused4) {
            i2 = 0;
        }
        String forUrl = UPUtils.forUrl(i2);
        com.unionpay.utils.g.b("uppay", "url: " + forUrl);
        D = new com.unionpay.a.d(forUrl);
        String a6 = a(context, false);
        com.unionpay.utils.g.b("uppay", "postdata: " + a6);
        D.a(a6);
        E = new Handler(L);
        new c().start();
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONArray b(JSONArray jSONArray, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            arrayList.add(jSONArray.optJSONObject(i2));
        }
        Collections.sort(arrayList, new h(str));
        JSONArray jSONArray2 = new JSONArray();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            jSONArray2.put((JSONObject) arrayList.get(i3));
        }
        return jSONArray2;
    }

    public static boolean checkInstalled(Context context) {
        JSONArray b2;
        int i2;
        String str = H;
        String a2 = UPUtils.a(context, "configs");
        String a3 = UPUtils.a(context, "mode");
        String a4 = UPUtils.a(context, "or");
        if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4)) {
            try {
                System.loadLibrary("entryexpro");
                JSONObject jSONObject = new JSONObject(a2);
                String a5 = com.unionpay.utils.f.a(jSONObject, "sign");
                try {
                    i2 = Integer.parseInt(a3);
                } catch (NumberFormatException unused) {
                    i2 = 0;
                }
                String str2 = new String(Base64.decode(jSONObject.getString("configs"), 2));
                if (UPUtils.forConfig(i2, a5).equals(com.unionpay.utils.b.a(UPUtils.a(str2 + a4)))) {
                    str = str2;
                }
            } catch (JSONException unused2) {
            }
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i3 = 0; i3 < length; i3++) {
                Object a6 = com.unionpay.utils.f.a(jSONArray, i3);
                if (a6 != null) {
                    JSONObject jSONObject2 = (JSONObject) a6;
                    if ("app".equals(com.unionpay.utils.f.a(jSONObject2, "type")) && (b2 = b(com.unionpay.utils.f.c(jSONObject2, "package_info"), "sort")) != null && b2.length() > 0) {
                        int length2 = b2.length();
                        for (int i4 = 0; i4 < length2; i4++) {
                            Object a7 = com.unionpay.utils.f.a(b2, i4);
                            if (a7 != null) {
                                JSONObject jSONObject3 = (JSONObject) a7;
                                String a8 = com.unionpay.utils.f.a(jSONObject3, "schema");
                                String a9 = com.unionpay.utils.f.a(jSONObject3, "sign");
                                String a10 = com.unionpay.utils.f.a(jSONObject3, "version");
                                if (com.unionpay.utils.b.a(context, a8) && a9.equalsIgnoreCase(com.unionpay.utils.b.b(context, a8)) && com.unionpay.utils.b.c(context, a8).matches(a10)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException unused3) {
        }
        return false;
    }

    static /* synthetic */ boolean f() {
        z = false;
        return false;
    }

    public static boolean installUPPayPlugin(Context context) {
        try {
            InputStream open = context.getAssets().open("UPPayPluginEx.apk");
            String absolutePath = context.getFilesDir().getAbsolutePath();
            if (absolutePath == null) {
                return false;
            }
            String str = absolutePath + File.separator + "UPPayPluginEx.apk";
            if (!new File(str).exists()) {
                FileOutputStream openFileOutput = context.openFileOutput("UPPayPluginEx.apk", 1);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    openFileOutput.write(bArr, 0, read);
                }
                openFileOutput.close();
                open.close();
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse("file:" + str), "application/vnd.android.package-archive");
            context.startActivity(intent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    static /* synthetic */ boolean l() {
        C = true;
        return true;
    }

    static /* synthetic */ int o() {
        int i2 = B + 1;
        B = i2;
        return i2;
    }

    public static int startPay(Context context, String str, String str2, String str3, String str4) {
        return b(context, str, str2, str3, str4, "");
    }

    public static void startPayByJAR(Context context, Class cls, String str, String str2, String str3, String str4) {
        startPay(context, str, str2, str3, str4);
    }

    public static int startPayFromBrowser(Activity activity, String str, String str2) {
        return a(activity, str, str2);
    }

    public static void startSamsungPay(Context context, Class cls, String str, String str2, String str3, String str4) {
        b(context, str, str2, str3, str4, p);
    }
}
