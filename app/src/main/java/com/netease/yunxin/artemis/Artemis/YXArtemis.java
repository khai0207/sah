package com.netease.yunxin.artemis.Artemis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisDig;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisHttp;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisIcmp;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisIcmpIpv6;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisTcp;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisTelnet;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisTraceRouter;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisTraceRouterIpv6;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisUdp;
import com.netease.yunxin.artemis.a.f;
import com.netease.yunxin.artemis.a.g;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class YXArtemis {
    private static YXArtemis artemis;

    static /* synthetic */ void lambda$runTask$0(String str) {
    }

    static {
        System.loadLibrary("artemis");
    }

    private YXArtemis(Context context) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        Context applicationContext = context.getApplicationContext();
        com.netease.yunxin.artemis.Network.b.a().a = applicationContext;
        c.a().c = applicationContext;
        g.a().a = applicationContext;
        c a = c.a();
        a.d = a.a(a.c);
        f.a("artemis engine start");
        com.netease.yunxin.artemis.Network.b a2 = com.netease.yunxin.artemis.Network.b.a();
        ConnectivityManager connectivityManager = (ConnectivityManager) a2.a.getSystemService("connectivity");
        String str = "NONETWORK";
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || (state = networkInfo.getState()) == null || !(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
                TelephonyManager telephonyManager = (TelephonyManager) a2.a.getSystemService("phone");
                if (telephonyManager != null && Build.VERSION.SDK_INT >= 23 && a2.a.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
                    int networkType = telephonyManager.getNetworkType();
                    if (networkType != 19) {
                        if (networkType != 20) {
                            switch (networkType) {
                                case 1:
                                case 2:
                                case 4:
                                case 7:
                                case 11:
                                    str = "2G";
                                    break;
                                case 3:
                                case 5:
                                case 6:
                                case 8:
                                case 9:
                                case 10:
                                case 12:
                                case 14:
                                case 15:
                                    str = "3G";
                                    break;
                            }
                        } else {
                            str = "5G";
                        }
                    }
                    str = "4G";
                }
            } else {
                str = "WIFI";
            }
        }
        com.netease.yunxin.artemis.a.c.b = str;
    }

    public static synchronized void startWithAppKey(Context context, String str, String str2, String str3, String str4, String str5, boolean z) {
        synchronized (YXArtemis.class) {
            if (artemis == null) {
                artemis = new YXArtemis(context);
                if (str == null) {
                    str = "";
                }
                com.netease.yunxin.artemis.a.c.a = str;
                if (str2 == null) {
                    str2 = "";
                }
                com.netease.yunxin.artemis.a.c.c = str2;
                if (str3 == null) {
                    str3 = "";
                }
                com.netease.yunxin.artemis.a.c.d = str3;
                if (str4 == null) {
                    str4 = "";
                }
                com.netease.yunxin.artemis.a.c.e = str4;
                if (str5 == null) {
                    str5 = "";
                }
                com.netease.yunxin.artemis.a.c.f = str5;
                com.netease.yunxin.artemis.a.c.g = z;
                return;
            }
            f.a("artemis already started");
        }
    }

    public static void runTask(Map<String, Object> map) {
        runTask(map, new YXArtemisRunTaskCallback() { // from class: com.netease.yunxin.artemis.Artemis.-$$Lambda$YXArtemis$OQ1PNup8h-9u__qg1natiwu7QMk
            @Override // com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback
            public final void onCompleted(String str) {
                YXArtemis.lambda$runTask$0(str);
            }
        });
    }

    public static void runTask(Map<String, Object> map, YXArtemisRunTaskCallback yXArtemisRunTaskCallback) {
        if (artemis == null) {
            f.a("artemis is null, please call startWithAppKey first");
            return;
        }
        c a = c.a();
        try {
            Object obj = map.get("task_type");
            if (!(obj instanceof Integer)) {
                throw new Exception("task_type 'null' or 'not int'");
            }
            switch (((Integer) obj).intValue()) {
                case 1:
                    Context context = a.c;
                    Object obj2 = map.get("task_id");
                    if (!(obj2 instanceof String)) {
                        throw new IllegalArgumentException("task_id 'null' or 'not string'");
                    }
                    String str = (String) map.get("hostname");
                    String str2 = (String) map.get("port");
                    Integer num = (Integer) map.get("count");
                    JSONObject jSONObject = new JSONObject();
                    if (str == null) {
                        str = "";
                    }
                    jSONObject.put("hostname", str);
                    if (str2 == null) {
                        str2 = "";
                    }
                    jSONObject.put("port", str2);
                    jSONObject.put("count", num != null ? num.intValue() : 0);
                    c.a().a(new YXArtemisTcp("0.0.0.0", "0.0.0.0", (String) obj2, 1, jSONObject, null, null, 0, null, context, null, yXArtemisRunTaskCallback));
                    return;
                case 2:
                    Context context2 = a.c;
                    Object obj3 = map.get("task_id");
                    if (!(obj3 instanceof String)) {
                        throw new IllegalArgumentException("task_id 'null' or 'not String'");
                    }
                    String str3 = (String) map.get("hostname");
                    String str4 = (String) map.get("port");
                    Integer num2 = (Integer) map.get("count");
                    JSONObject jSONObject2 = new JSONObject();
                    if (str3 == null) {
                        str3 = "";
                    }
                    jSONObject2.put("hostname", str3);
                    if (str4 == null) {
                        str4 = "";
                    }
                    jSONObject2.put("port", str4);
                    jSONObject2.put("count", num2 != null ? num2.intValue() : 0);
                    c.a().a(new YXArtemisUdp("0.0.0.0", "0.0.0.0", (String) obj3, 2, jSONObject2, null, null, 0, null, context2, null, yXArtemisRunTaskCallback));
                    return;
                case 3:
                    Context context3 = a.c;
                    Object obj4 = map.get("task_id");
                    if (!(obj4 instanceof String)) {
                        throw new IllegalArgumentException("task_id 'null' or 'not String'");
                    }
                    String str5 = (String) map.get("hostname");
                    Integer num3 = (Integer) map.get("count");
                    JSONObject jSONObject3 = new JSONObject();
                    if (str5 == null) {
                        str5 = "";
                    }
                    jSONObject3.put("hostname", str5);
                    jSONObject3.put("count", num3 != null ? num3.intValue() : 0);
                    c.a().a(new YXArtemisIcmp("0.0.0.0", "0.0.0.0", (String) obj4, 3, jSONObject3, null, null, 0, null, context3, null, yXArtemisRunTaskCallback));
                    return;
                case 4:
                    Context context4 = a.c;
                    Object obj5 = map.get("task_id");
                    if (!(obj5 instanceof String)) {
                        throw new IllegalArgumentException("task_id 'null' or 'not String'");
                    }
                    String str6 = (String) map.get("hostname");
                    String str7 = (String) map.get("port");
                    JSONObject jSONObject4 = new JSONObject();
                    if (str6 == null) {
                        str6 = "";
                    }
                    jSONObject4.put("hostname", str6);
                    if (str7 == null) {
                        str7 = "";
                    }
                    jSONObject4.put("port", str7);
                    c.a().a(new YXArtemisTelnet("0.0.0.0", "0.0.0.0", (String) obj5, 4, jSONObject4, null, null, 0, null, context4, null, yXArtemisRunTaskCallback));
                    return;
                case 5:
                    Context context5 = a.c;
                    Object obj6 = map.get("task_id");
                    if (!(obj6 instanceof String)) {
                        throw new IllegalArgumentException("task_id 'null' or 'not String'");
                    }
                    String str8 = (String) map.get("hostname");
                    Integer num4 = (Integer) map.get("max_ttl");
                    Integer num5 = (Integer) map.get("nflag");
                    Integer num6 = (Integer) map.get("port");
                    Integer num7 = (Integer) map.get("nprobes");
                    String str9 = (String) map.get("route");
                    Integer num8 = (Integer) map.get("tos");
                    Integer num9 = (Integer) map.get("waittime");
                    Integer num10 = (Integer) map.get("datalen");
                    JSONObject jSONObject5 = new JSONObject();
                    if (str8 == null) {
                        str8 = "";
                    }
                    jSONObject5.put("hostname", str8);
                    jSONObject5.put("max_ttl", num4 != null ? num4.intValue() : 30);
                    jSONObject5.put("nflag", num5 != null ? num5.intValue() : 0);
                    jSONObject5.put("port", num6 != null ? num6.intValue() : 33434);
                    jSONObject5.put("nprobes", num7 != null ? num7.intValue() : 3);
                    jSONObject5.put("route", str9 != null ? str9 : "");
                    jSONObject5.put("tos", num8 != null ? num8.intValue() : 0);
                    jSONObject5.put("waittime", num9 != null ? num9.intValue() : 0);
                    jSONObject5.put("datalen", num10 != null ? num10.intValue() : 0);
                    c.a().a(new YXArtemisTraceRouter("0.0.0.0", "0.0.0.0", (String) obj6, 5, jSONObject5, null, null, 0, null, context5, null, yXArtemisRunTaskCallback));
                    return;
                case 6:
                    Context context6 = a.c;
                    Object obj7 = map.get("task_id");
                    if (!(obj7 instanceof String)) {
                        throw new IllegalArgumentException("task_id 'null' or 'not String'");
                    }
                    String str10 = (String) map.get("method");
                    String str11 = (String) map.get(Constants.URL_ENCODING);
                    JSONObject jSONObject6 = (JSONObject) map.get("params");
                    JSONObject jSONObject7 = (JSONObject) map.get("headers");
                    JSONObject jSONObject8 = new JSONObject();
                    if (str10 == null) {
                        str10 = "";
                    }
                    jSONObject8.put("method", str10);
                    if (str11 == null) {
                        str11 = "";
                    }
                    jSONObject8.put(Constants.URL_ENCODING, str11);
                    if (jSONObject6 == null) {
                        jSONObject6 = new JSONObject();
                    }
                    jSONObject8.put("params", jSONObject6);
                    if (jSONObject7 == null) {
                        jSONObject7 = new JSONObject();
                    }
                    jSONObject8.put("headers", jSONObject7);
                    c.a().a(new YXArtemisHttp("0.0.0.0", "0.0.0.0", (String) obj7, 6, jSONObject8, null, null, 0, null, context6, null, yXArtemisRunTaskCallback));
                    return;
                case 7:
                    Context context7 = a.c;
                    Object obj8 = map.get("task_id");
                    if (!(obj8 instanceof String)) {
                        throw new IllegalArgumentException("task_id 'null' or 'not String'");
                    }
                    String str12 = (String) map.get("hostname");
                    JSONObject jSONObject9 = new JSONObject();
                    if (str12 == null) {
                        str12 = "";
                    }
                    jSONObject9.put("hostname", str12);
                    c.a().a(new YXArtemisDig("0.0.0.0", "0.0.0.0", (String) obj8, 7, jSONObject9, null, null, 0, null, context7, null, yXArtemisRunTaskCallback));
                    return;
                case 8:
                default:
                    return;
                case 9:
                    Context context8 = a.c;
                    Object obj9 = map.get("task_id");
                    if (!(obj9 instanceof String)) {
                        throw new IllegalArgumentException("task_id 'null' or 'not String'");
                    }
                    String str13 = (String) map.get("hostname");
                    Integer num11 = (Integer) map.get("count");
                    JSONObject jSONObject10 = new JSONObject();
                    if (str13 == null) {
                        str13 = "";
                    }
                    jSONObject10.put("hostname", str13);
                    jSONObject10.put("count", num11 != null ? num11.intValue() : 0);
                    c.a().a(new YXArtemisIcmpIpv6("0.0.0.0", "0.0.0.0", (String) obj9, 9, jSONObject10, null, null, 0, null, context8, null, yXArtemisRunTaskCallback));
                    return;
                case 10:
                    Context context9 = a.c;
                    Object obj10 = map.get("task_id");
                    if (!(obj10 instanceof String)) {
                        throw new IllegalArgumentException("task_id 'null' or 'not String'");
                    }
                    String str14 = (String) map.get("hostname");
                    Integer num12 = (Integer) map.get("max_ttl");
                    Integer num13 = (Integer) map.get("nflag");
                    Integer num14 = (Integer) map.get("port");
                    Integer num15 = (Integer) map.get("nprobes");
                    String str15 = (String) map.get("route");
                    Integer num16 = (Integer) map.get("tos");
                    Integer num17 = (Integer) map.get("waittime");
                    Integer num18 = (Integer) map.get("datalen");
                    JSONObject jSONObject11 = new JSONObject();
                    if (str14 == null) {
                        str14 = "";
                    }
                    jSONObject11.put("hostname", str14);
                    jSONObject11.put("max_ttl", num12 != null ? num12.intValue() : 30);
                    jSONObject11.put("nflag", num13 != null ? num13.intValue() : 0);
                    jSONObject11.put("port", num14 != null ? num14.intValue() : 33434);
                    jSONObject11.put("nprobes", num15 != null ? num15.intValue() : 3);
                    jSONObject11.put("route", str15 != null ? str15 : "");
                    jSONObject11.put("tos", num16 != null ? num16.intValue() : 0);
                    jSONObject11.put("waittime", num17 != null ? num17.intValue() : 0);
                    jSONObject11.put("datalen", num18 != null ? num18.intValue() : 0);
                    c.a().a(new YXArtemisTraceRouterIpv6("0.0.0.0", "0.0.0.0", (String) obj10, 10, jSONObject11, null, null, 0, null, context9, null, yXArtemisRunTaskCallback));
                    return;
            }
        } catch (Throwable unused) {
        }
    }

    public static void setLogCallback(YXArtemisLogCallback yXArtemisLogCallback) {
        f.a = yXArtemisLogCallback;
    }
}
