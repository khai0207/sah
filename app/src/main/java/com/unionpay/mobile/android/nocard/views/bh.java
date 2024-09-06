package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.widget.LinearLayout;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class bh {
    public static LinearLayout a(Context context, JSONArray jSONArray, int i, int i2) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setOrientation(1);
        new LinearLayout.LayoutParams(-1, -2).topMargin = com.unionpay.mobile.android.global.a.d;
        JSONObject jSONObject = null;
        while (i < i2 && i < jSONArray.length()) {
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            linearLayout.addView(new com.unionpay.mobile.android.widgets.ac(context, com.unionpay.mobile.android.global.a.I, jSONObject, ""));
            i++;
        }
        return linearLayout;
    }

    public static String a(Context context, String str, String str2, String str3, String str4, String str5) {
        NfcAdapter defaultAdapter;
        String str6;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tn", str);
            String c = new File("/system/bin/su").exists() ? com.unionpay.mobile.android.utils.e.c(context) : ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (c == null || c.length() == 0) {
                c = PreferenceUtils.a(context);
            }
            com.unionpay.mobile.android.utils.j.a("uppay", "user=" + c);
            jSONObject.put("user", c);
            jSONObject.put("locale", a(Locale.getDefault().toString().startsWith("zh") ? "zh_CN" : "en_US"));
            jSONObject.put("terminal_version", com.unionpay.mobile.android.utils.e.a(context));
            jSONObject.put("terminal_resolution", (com.unionpay.mobile.android.global.a.I + "*" + com.unionpay.mobile.android.global.a.t).trim());
            jSONObject.put("os_name", str2);
            jSONObject.put("os_version", Build.VERSION.RELEASE.trim());
            String trim = Build.MODEL.trim();
            String str7 = "";
            if (trim != null) {
                trim.replace(" ", "");
            }
            jSONObject.put("device_model", a(trim));
            jSONObject.put("terminal_type", str3);
            jSONObject.put("appId", str4);
            jSONObject.put("uid", PreferenceUtils.a(context));
            jSONObject.put("mac", a(com.unionpay.mobile.android.utils.e.c(context)));
            try {
                jSONObject.put("time_zone", a(TimeZone.getDefault().getDisplayName(false, 0)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                    str6 = "disConnect";
                } else if (activeNetworkInfo.getType() != 0) {
                    str6 = activeNetworkInfo.getType() == 1 ? "wifi" : "other";
                } else if (activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    str6 = "mobile:" + activeNetworkInfo.getExtraInfo();
                } else {
                    str6 = "mobile";
                }
                jSONObject.put("network_mode", str6);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                if (subscriberId != null) {
                    str7 = subscriberId;
                }
                jSONObject.put("imsi", a(str7));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            try {
                jSONObject.put("baseband_version", a(com.unionpay.mobile.android.utils.e.a()));
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            try {
                StringBuffer stringBuffer = new StringBuffer(Constant.DEFAULT_CVN2);
                if (!Constant.DEFAULT_CVN2.equals(str5)) {
                    stringBuffer.setCharAt(2, '1');
                }
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
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            try {
                jSONObject.put("se_map", str5);
            } catch (Exception e6) {
                e6.printStackTrace();
            }
            jSONObject.put("root", new File("/system/bin/su").exists() ? "1" : "0");
            jSONObject.put("country", a(Locale.getDefault().getCountry()));
            jSONObject.put("package", a(com.unionpay.mobile.android.utils.e.b(context)));
        } catch (PatternSyntaxException e7) {
            e7.printStackTrace();
        } catch (JSONException e8) {
            e8.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        String substring = jSONObject2.substring(1, jSONObject2.length() - 1);
        com.unionpay.mobile.android.utils.j.a("uppay", "init: " + substring);
        return substring;
    }

    private static String a(String str) throws PatternSyntaxException {
        return Pattern.compile("[\":,\\[\\]{}]").matcher(str).replaceAll("").trim();
    }

    public static String a(String str, String str2, String str3, String str4) {
        return String.format("\"first_pay_flag\":\"%s\",\"card\":\"%s\",\"pay_type\":\"%s\",\"pay_mode\":\"%s\"", str, str2, str3, str4);
    }

    public static String a(JSONObject jSONObject) {
        com.unionpay.mobile.android.utils.j.a("uppay", "action:" + com.unionpay.mobile.android.utils.i.a(jSONObject, "action"));
        return com.unionpay.mobile.android.utils.i.a(jSONObject, "action");
    }

    public static String b(String str, String str2, String str3, String str4) {
        return String.format("\"first_pay_flag\":\"%s\",%s,\"pay_type\":\"%s\",\"pay_mode\":\"%s\"", str, str2, str3, str4);
    }

    public static String c(String str, String str2, String str3, String str4) {
        return (str3 == null || str3.length() <= 0) ? String.format("\"pay_type\":\"%s\",\"pay_mode\":\"%s\",%s", str, str2, str4) : String.format("\"pay_type\":\"%s\",\"pay_mode\":\"%s\",%s,%s", str, str2, str3, str4);
    }
}
