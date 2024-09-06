package com.talkingdata.sdk;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.CellLocation;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class az {
    static TelephonyManager a = null;
    static final String b = "www.talkingdata.net";
    static final int c = 80;
    static boolean d = false;
    static final long e = 300000;
    static long f = -300000;
    private static final String[] g = {"UNKNOWN", "GPRS", "EDGE", "UMTS", "CDMA", "EVDO_0", "EVDO_A", "1xRTT", "HSDPA", "HSUPA", "HSPA", "IDEN", "EVDO_B", "LTE", "EHRPD", "HSPAP"};
    private static final String[] h = {"NONE", "GSM", "CDMA", "SIP"};

    static void a(Context context) {
        try {
            a = (TelephonyManager) context.getSystemService("phone");
        } catch (Throwable unused) {
        }
    }

    public static String b(Context context) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x006c, code lost:
    
        if (r6 == null) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean c(android.content.Context r6) {
        /*
            java.lang.String r0 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r0 = com.talkingdata.sdk.bh.b(r6, r0)     // Catch: java.lang.Throwable -> L76
            r1 = 0
            if (r0 == 0) goto L30
            java.lang.String r0 = "connectivity"
            java.lang.Object r6 = r6.getSystemService(r0)     // Catch: java.lang.Throwable -> L76
            android.net.ConnectivityManager r6 = (android.net.ConnectivityManager) r6     // Catch: java.lang.Throwable -> L76
            android.net.NetworkInfo r0 = r6.getActiveNetworkInfo()     // Catch: java.lang.Throwable -> L76
            if (r0 == 0) goto L1c
            boolean r6 = r0.isConnected()     // Catch: java.lang.Throwable -> L76
            return r6
        L1c:
            android.net.NetworkInfo r6 = r6.getNetworkInfo(r1)     // Catch: java.lang.Throwable -> L76
            if (r6 == 0) goto L2f
            android.net.NetworkInfo$State r6 = r6.getState()     // Catch: java.lang.Throwable -> L76
            android.net.NetworkInfo$State r0 = android.net.NetworkInfo.State.UNKNOWN     // Catch: java.lang.Throwable -> L76
            boolean r6 = r6.equals(r0)     // Catch: java.lang.Throwable -> L76
            if (r6 == 0) goto L2f
            goto L30
        L2f:
            return r1
        L30:
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L76
            long r4 = com.talkingdata.sdk.az.f     // Catch: java.lang.Throwable -> L76
            long r2 = r2 - r4
            r4 = 300000(0x493e0, double:1.482197E-318)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L7a
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L76
            com.talkingdata.sdk.az.f = r2     // Catch: java.lang.Throwable -> L76
            r6 = 0
            boolean r0 = a()     // Catch: java.lang.Throwable -> L6a
            if (r0 == 0) goto L59
            java.net.Socket r0 = new java.net.Socket     // Catch: java.lang.Throwable -> L6a
            java.lang.String r2 = android.net.Proxy.getDefaultHost()     // Catch: java.lang.Throwable -> L6a
            int r3 = android.net.Proxy.getDefaultPort()     // Catch: java.lang.Throwable -> L6a
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L6a
            goto L62
        L59:
            java.net.Socket r0 = new java.net.Socket     // Catch: java.lang.Throwable -> L6a
            java.lang.String r2 = "www.talkingdata.net"
            r3 = 80
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L6a
        L62:
            r6 = r0
            r0 = 1
            com.talkingdata.sdk.az.d = r0     // Catch: java.lang.Throwable -> L6a
        L66:
            r6.close()     // Catch: java.lang.Throwable -> L7a
            goto L7a
        L6a:
            com.talkingdata.sdk.az.d = r1     // Catch: java.lang.Throwable -> L6f
            if (r6 == 0) goto L7a
            goto L66
        L6f:
            r0 = move-exception
            if (r6 == 0) goto L75
            r6.close()     // Catch: java.lang.Throwable -> L75
        L75:
            throw r0     // Catch: java.lang.Throwable -> L76
        L76:
            r6 = move-exception
            com.talkingdata.sdk.ce.postSDKError(r6)
        L7a:
            boolean r6 = com.talkingdata.sdk.az.d
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.talkingdata.sdk.az.c(android.content.Context):boolean");
    }

    public static boolean d(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            if (!bh.b(context, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isAvailable();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean e(Context context) {
        try {
            if (g(context)) {
                return true;
            }
            return ((WifiManager) context.getSystemService("wifi")).isWifiEnabled();
        } catch (Throwable th) {
            ce.postSDKError(th);
            return false;
        }
    }

    public static boolean f(Context context) {
        try {
            if (a == null) {
                a(context);
            }
            return a.getSimState() == 5;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean g(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            if (bh.b(context, "android.permission.ACCESS_NETWORK_STATE") && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && 1 == activeNetworkInfo.getType()) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean h(Context context) {
        try {
            if (a == null) {
                a(context);
            }
            return a.getDataState() == 2;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a() {
        try {
            if (bh.a(11)) {
                return !TextUtils.isEmpty(System.getProperty("http.proxyHost"));
            }
            return !TextUtils.isEmpty(Proxy.getDefaultHost());
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String i(Context context) {
        return !c(context) ? "OFFLINE" : g(context) ? "WIFI" : a(j(context));
    }

    public static int j(Context context) {
        try {
            if (a == null) {
                a(context);
            }
            return a.getNetworkType();
        } catch (Throwable unused) {
            return 0;
        }
    }

    private static String a(int i) {
        if (i >= 0) {
            String[] strArr = g;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return String.valueOf(i);
    }

    private static String b(int i) {
        if (i >= 0) {
            String[] strArr = h;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return String.valueOf(i);
    }

    public static String k(Context context) {
        try {
            if (a == null) {
                a(context);
            }
            return a.getNetworkOperator();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String l(Context context) {
        try {
            if (a == null) {
                a(context);
            }
            return a.getSimOperator();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String m(Context context) {
        try {
            if (a == null) {
                a(context);
            }
            return a.getNetworkCountryIso();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String n(Context context) {
        try {
            if (a == null) {
                a(context);
            }
            return a.getSimCountryIso();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String o(Context context) {
        try {
            if ((!bh.a(23) || context.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) && bh.b(context, "android.permission.READ_PHONE_STATE") && Build.VERSION.SDK_INT >= 18) {
                if (a == null) {
                    a(context);
                }
                return a.getGroupIdLevel1();
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static String p(Context context) {
        try {
            if (a == null) {
                a(context);
            }
            return a.getNetworkOperatorName();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String q(Context context) {
        try {
            if (a == null) {
                a(context);
            }
            return a.getSimOperatorName();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static JSONArray r(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "wifi");
            jSONObject.put("available", e(context));
            jSONObject.put("connected", g(context));
            jSONObject.put("current", v(context));
            jSONObject.put("scannable", w(context));
            jSONObject.put("configured", u(context));
            jSONArray.put(jSONObject);
        } catch (Throwable unused) {
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "cellular");
            jSONObject2.put("available", f(context));
            jSONObject2.put("connected", h(context));
            jSONObject2.put("current", s(context));
            jSONObject2.put("scannable", t(context));
            jSONArray.put(jSONObject2);
        } catch (Throwable unused2) {
        }
        if (jSONArray.length() > 0) {
            return jSONArray;
        }
        return null;
    }

    public static JSONArray s(Context context) {
        CdmaCellLocation cdmaCellLocation;
        try {
            if (!bh.b) {
                return null;
            }
            boolean z = true;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", j(context));
            jSONObject.put("mcc", k(context));
            jSONObject.put("operator", p(context));
            jSONObject.put("country", m(context));
            boolean z2 = false;
            if (bh.a(23) && context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0 && context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
                z = false;
            }
            if (bh.b(context, "android.permission.ACCESS_COARSE_LOCATION") || bh.b(context, "android.permission.ACCESS_FINE_LOCATION")) {
                z2 = z;
            }
            if (z2) {
                if (a == null) {
                    a(context);
                }
                if (bh.d || bh.e) {
                    CellLocation cellLocation = a.getCellLocation();
                    if (cellLocation instanceof GsmCellLocation) {
                        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                        if (gsmCellLocation != null) {
                            jSONObject.put("systemId", gsmCellLocation.getLac());
                            jSONObject.put("networkId", gsmCellLocation.getCid());
                            if (bh.a(9)) {
                                jSONObject.put("basestationId", gsmCellLocation.getPsc());
                            }
                        }
                    } else if ((cellLocation instanceof CdmaCellLocation) && (cdmaCellLocation = (CdmaCellLocation) cellLocation) != null) {
                        jSONObject.put("systemId", cdmaCellLocation.getSystemId());
                        jSONObject.put("networkId", cdmaCellLocation.getNetworkId());
                        jSONObject.put("basestationId", cdmaCellLocation.getBaseStationId());
                        jSONObject.put("location", a(cdmaCellLocation.getBaseStationLatitude(), cdmaCellLocation.getBaseStationLongitude()));
                    }
                }
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            return jSONArray;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0154 A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:30:0x0061, B:32:0x007c, B:35:0x0154, B:37:0x0159, B:39:0x0160, B:41:0x0165, B:43:0x016c, B:45:0x0173, B:46:0x0183, B:49:0x009c, B:51:0x00a0, B:52:0x00ff, B:54:0x0103, B:56:0x0125, B:58:0x0129), top: B:29:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0159 A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:30:0x0061, B:32:0x007c, B:35:0x0154, B:37:0x0159, B:39:0x0160, B:41:0x0165, B:43:0x016c, B:45:0x0173, B:46:0x0183, B:49:0x009c, B:51:0x00a0, B:52:0x00ff, B:54:0x0103, B:56:0x0125, B:58:0x0129), top: B:29:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0160 A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:30:0x0061, B:32:0x007c, B:35:0x0154, B:37:0x0159, B:39:0x0160, B:41:0x0165, B:43:0x016c, B:45:0x0173, B:46:0x0183, B:49:0x009c, B:51:0x00a0, B:52:0x00ff, B:54:0x0103, B:56:0x0125, B:58:0x0129), top: B:29:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0165 A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:30:0x0061, B:32:0x007c, B:35:0x0154, B:37:0x0159, B:39:0x0160, B:41:0x0165, B:43:0x016c, B:45:0x0173, B:46:0x0183, B:49:0x009c, B:51:0x00a0, B:52:0x00ff, B:54:0x0103, B:56:0x0125, B:58:0x0129), top: B:29:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x016c A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:30:0x0061, B:32:0x007c, B:35:0x0154, B:37:0x0159, B:39:0x0160, B:41:0x0165, B:43:0x016c, B:45:0x0173, B:46:0x0183, B:49:0x009c, B:51:0x00a0, B:52:0x00ff, B:54:0x0103, B:56:0x0125, B:58:0x0129), top: B:29:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0173 A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:30:0x0061, B:32:0x007c, B:35:0x0154, B:37:0x0159, B:39:0x0160, B:41:0x0165, B:43:0x016c, B:45:0x0173, B:46:0x0183, B:49:0x009c, B:51:0x00a0, B:52:0x00ff, B:54:0x0103, B:56:0x0125, B:58:0x0129), top: B:29:0x0061 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONArray t(android.content.Context r16) {
        /*
            Method dump skipped, instructions count: 497
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.talkingdata.sdk.az.t(android.content.Context):org.json.JSONArray");
    }

    public static JSONObject a(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("lat", i);
            jSONObject.put("lng", i2);
            jSONObject.put("unit", "qd");
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public static JSONArray u(Context context) {
        List<WifiConfiguration> configuredNetworks;
        try {
            if (!bh.b || !bh.b(context, "android.permission.ACCESS_WIFI_STATE") || (configuredNetworks = ((WifiManager) context.getSystemService("wifi")).getConfiguredNetworks()) == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("networkId", wifiConfiguration.networkId);
                    jSONObject.put("priority", wifiConfiguration.priority);
                    jSONObject.put(com.alipay.sdk.m.h.c.e, wifiConfiguration.SSID);
                    jSONObject.put("id", wifiConfiguration.BSSID);
                    jSONObject.put("allowedKeyManagement", a(wifiConfiguration.allowedKeyManagement));
                    jSONObject.put("allowedAuthAlgorithms", a(wifiConfiguration.allowedAuthAlgorithms));
                    jSONObject.put("allowedGroupCiphers", a(wifiConfiguration.allowedGroupCiphers));
                    jSONObject.put("allowedPairwiseCiphers", a(wifiConfiguration.allowedPairwiseCiphers));
                    jSONArray.put(jSONObject);
                } catch (Throwable unused) {
                }
            }
            if (jSONArray.length() > 0) {
                return jSONArray;
            }
            return null;
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static JSONArray v(Context context) {
        WifiInfo connectionInfo;
        try {
            if (bh.b && bh.b(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager.isWifiEnabled() && (connectionInfo = wifiManager.getConnectionInfo()) != null && connectionInfo.getBSSID() != null) {
                    String bssid = connectionInfo.getBSSID();
                    JSONArray jSONArray = new JSONArray();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(com.alipay.sdk.m.h.c.e, connectionInfo.getSSID());
                        jSONObject.put("id", bssid);
                        jSONObject.put("level", connectionInfo.getRssi());
                        jSONObject.put("hidden", connectionInfo.getHiddenSSID());
                        jSONObject.put("ip", connectionInfo.getIpAddress());
                        jSONObject.put("speed", connectionInfo.getLinkSpeed());
                        jSONObject.put("networkId", connectionInfo.getNetworkId());
                        jSONObject.put("mac", connectionInfo.getMacAddress());
                        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
                        if (dhcpInfo != null) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("dns1", dhcpInfo.dns1);
                            jSONObject2.put("dns2", dhcpInfo.dns2);
                            jSONObject2.put("gw", dhcpInfo.gateway);
                            jSONObject2.put("ip", dhcpInfo.ipAddress);
                            jSONObject2.put("mask", dhcpInfo.netmask);
                            jSONObject2.put("server", dhcpInfo.serverAddress);
                            jSONObject2.put("leaseDuration", dhcpInfo.leaseDuration);
                            jSONObject.put("dhcp", jSONObject2);
                        }
                    } catch (Throwable unused) {
                    }
                    jSONArray.put(jSONObject);
                    return jSONArray;
                }
            }
        } catch (Throwable unused2) {
        }
        return null;
    }

    public static JSONArray w(Context context) {
        if (bh.b && (bh.d || bh.e)) {
            try {
                if (bh.b(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled() || (Build.VERSION.SDK_INT >= 18 && wifiManager.isScanAlwaysAvailable())) {
                        if (bh.b(context, "android.permission.CHANGE_WIFI_STATE")) {
                            try {
                                Object obj = new Object();
                                context.registerReceiver(new ba(obj, context), new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                                wifiManager.startScan();
                                synchronized (obj) {
                                    obj.wait(2000L);
                                }
                            } catch (Throwable unused) {
                            }
                        }
                        List<ScanResult> scanResults = wifiManager.getScanResults();
                        if (scanResults != null) {
                            ArrayList arrayList = new ArrayList();
                            for (ScanResult scanResult : scanResults) {
                                if (scanResult.level >= -85) {
                                    JSONObject jSONObject = new JSONObject();
                                    try {
                                        jSONObject.put("id", scanResult.BSSID);
                                        jSONObject.put(com.alipay.sdk.m.h.c.e, scanResult.SSID);
                                        jSONObject.put("level", scanResult.level);
                                        jSONObject.put("freq", scanResult.frequency);
                                        if (bh.a(17)) {
                                            jSONObject.put("ts", scanResult.timestamp);
                                            jSONObject.put("scanTs", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) + (scanResult.timestamp / 1000));
                                        }
                                        arrayList.add(jSONObject);
                                    } catch (Throwable unused2) {
                                    }
                                }
                            }
                            return a(arrayList, 20);
                        }
                    }
                }
            } catch (Throwable unused3) {
            }
        }
        return null;
    }

    public static JSONArray a(ArrayList arrayList, int i) {
        try {
            Collections.sort(arrayList, new bc());
            return new JSONArray((Collection) arrayList.subList(0, i));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static JSONArray a(BitSet bitSet) {
        if (bitSet == null || bitSet.cardinality() < 1) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (true) {
            int nextSetBit = bitSet.nextSetBit(i);
            if (nextSetBit < 0) {
                return null;
            }
            jSONArray.put(nextSetBit);
            i = nextSetBit + 1;
        }
    }

    public static JSONArray x(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            ArrayList arrayList = new ArrayList();
            if (bh.a(22)) {
                SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
                try {
                    JSONObject a2 = a(telephonyManager, subscriptionManager, 0);
                    a2.put("imei", telephonyManager.getDeviceId());
                    jSONArray.put(a2);
                } catch (Throwable unused) {
                }
                JSONObject a3 = a(telephonyManager, subscriptionManager, 1);
                a3.put("imei", (bh.a(23) && telephonyManager.getPhoneCount() == 2) ? telephonyManager.getDeviceId(1) : null);
                if (a3.length() > 0) {
                    jSONArray.put(a3);
                }
            } else {
                String deviceId = telephonyManager.getDeviceId();
                if (b(deviceId.trim()).booleanValue()) {
                    arrayList.add(deviceId.trim());
                    JSONObject a4 = a(telephonyManager, deviceId);
                    if (a4 != null) {
                        jSONArray.put(a4);
                    }
                }
                try {
                    TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService("phone1");
                    String deviceId2 = telephonyManager2.getDeviceId();
                    if (deviceId2 != null && b(deviceId2).booleanValue() && !arrayList.contains(deviceId2)) {
                        arrayList.add(deviceId2);
                        JSONObject a5 = a(telephonyManager2, deviceId2);
                        if (a5 != null) {
                            jSONArray.put(a5);
                        }
                    }
                } catch (Throwable unused2) {
                }
                try {
                    TelephonyManager telephonyManager3 = (TelephonyManager) context.getSystemService("phone2");
                    String deviceId3 = telephonyManager3.getDeviceId();
                    if (deviceId3 != null && b(deviceId3).booleanValue() && !arrayList.contains(deviceId3)) {
                        arrayList.add(deviceId3);
                        JSONObject a6 = a(telephonyManager3, deviceId3);
                        if (a6 != null) {
                            jSONArray.put(a6);
                        }
                    }
                } catch (Throwable unused3) {
                }
                JSONArray C = C(context);
                JSONArray B = B(context);
                if (B != null) {
                    C = B;
                }
                JSONArray A = A(context);
                if (A != null) {
                    C = A;
                }
                JSONArray z = z(context);
                if (z != null) {
                    C = z;
                }
                if (C != null && C.length() > 0) {
                    for (int i = 0; i < C.length(); i++) {
                        JSONObject jSONObject = C.getJSONObject(i);
                        String string = jSONObject.getString("imei");
                        if (!arrayList.contains(string)) {
                            arrayList.add(string);
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            }
        } catch (Throwable unused4) {
        }
        return jSONArray;
    }

    private static JSONObject a(TelephonyManager telephonyManager, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("imei", str.trim());
            String str2 = "";
            jSONObject.put("subscriberId", telephonyManager.getSubscriberId() == null ? "" : telephonyManager.getSubscriberId());
            jSONObject.put("simSerialNumber", telephonyManager.getSimSerialNumber() == null ? "" : telephonyManager.getSimSerialNumber());
            jSONObject.put("dataState", telephonyManager.getDataState());
            jSONObject.put("networkType", a(telephonyManager.getNetworkType()));
            jSONObject.put("networkOperator", telephonyManager.getNetworkOperator());
            jSONObject.put("phoneType", b(telephonyManager.getPhoneType()));
            jSONObject.put("simOperator", telephonyManager.getSimOperator() == null ? "" : telephonyManager.getSimOperator());
            jSONObject.put("simOperatorName", telephonyManager.getSimOperatorName() == null ? "" : telephonyManager.getSimOperatorName());
            if (telephonyManager.getSimCountryIso() != null) {
                str2 = telephonyManager.getSimCountryIso();
            }
            jSONObject.put("simCountryIso", str2);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static JSONObject a(TelephonyManager telephonyManager, SubscriptionManager subscriptionManager, int i) {
        SubscriptionInfo activeSubscriptionInfoForSimSlotIndex;
        JSONObject jSONObject = new JSONObject();
        try {
            if (bh.a(22) && (activeSubscriptionInfoForSimSlotIndex = subscriptionManager.getActiveSubscriptionInfoForSimSlotIndex(i)) != null) {
                Object obj = "";
                jSONObject.put("simSerialNumber", activeSubscriptionInfoForSimSlotIndex.getIccId() == null ? "" : activeSubscriptionInfoForSimSlotIndex.getIccId());
                jSONObject.put("simOperator", activeSubscriptionInfoForSimSlotIndex.getMcc() + "0" + activeSubscriptionInfoForSimSlotIndex.getMnc());
                jSONObject.put("simOperatorName", activeSubscriptionInfoForSimSlotIndex.getCarrierName() == null ? "" : activeSubscriptionInfoForSimSlotIndex.getCarrierName());
                jSONObject.put("simCountryIso", activeSubscriptionInfoForSimSlotIndex.getCountryIso() == null ? "" : activeSubscriptionInfoForSimSlotIndex.getCountryIso());
                int subscriptionId = activeSubscriptionInfoForSimSlotIndex.getSubscriptionId();
                Method method = telephonyManager.getClass().getMethod("getSubscriberId", Integer.TYPE);
                method.setAccessible(true);
                Object invoke = method.invoke(telephonyManager, Integer.valueOf(subscriptionId));
                if (invoke != null) {
                    obj = invoke;
                }
                jSONObject.put("subscriberId", obj);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public static JSONObject y(Context context) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        try {
            jSONObject = new JSONObject();
        } catch (Throwable unused) {
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            jSONObject.put("dataState", telephonyManager.getDataState());
            jSONObject.put("networkType", a(telephonyManager.getNetworkType()));
            jSONObject.put("networkOperator", telephonyManager.getNetworkOperator());
            jSONObject.put("phoneType", b(telephonyManager.getPhoneType()));
            return jSONObject;
        } catch (Throwable unused2) {
            jSONObject2 = jSONObject;
            return jSONObject2;
        }
    }

    private static Boolean a(String str) {
        try {
            char charAt = str.length() > 0 ? str.charAt(0) : '0';
            for (int i = 0; i < str.length(); i++) {
                if (charAt != str.charAt(i)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Boolean b(String str) {
        try {
            Integer valueOf = Integer.valueOf(str.length());
            if (valueOf.intValue() > 10 && valueOf.intValue() < 20 && !a(str.trim()).booleanValue()) {
                return true;
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private static JSONArray z(Context context) {
        int i;
        int i2;
        try {
            JSONArray jSONArray = new JSONArray();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Class<?> cls = Class.forName("com.android.internal.telephony.Phone");
            try {
                Field field = cls.getField("GEMINI_SIM_1");
                field.setAccessible(true);
                i = (Integer) field.get(null);
                Field field2 = cls.getField("GEMINI_SIM_2");
                field2.setAccessible(true);
                i2 = (Integer) field2.get(null);
            } catch (Throwable unused) {
                i = 0;
                i2 = 1;
            }
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", Integer.TYPE);
            if (telephonyManager != null && declaredMethod != null) {
                String trim = ((String) declaredMethod.invoke(telephonyManager, i)).trim();
                String trim2 = ((String) declaredMethod.invoke(telephonyManager, i2)).trim();
                if (b(trim).booleanValue()) {
                    jSONArray.put(a(TelephonyManager.class, telephonyManager, i, trim, "Gemini"));
                }
                if (b(trim2).booleanValue()) {
                    jSONArray.put(a(TelephonyManager.class, telephonyManager, i2, trim2, "Gemini"));
                }
                return jSONArray;
            }
        } catch (Throwable unused2) {
        }
        return null;
    }

    private static JSONArray A(Context context) {
        int i;
        int i2;
        JSONObject a2;
        JSONObject a3;
        try {
            JSONArray jSONArray = new JSONArray();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Class<?> cls = Class.forName("com.android.internal.telephony.Phone");
            try {
                Field field = cls.getField("GEMINI_SIM_1");
                field.setAccessible(true);
                i = (Integer) field.get(null);
                Field field2 = cls.getField("GEMINI_SIM_2");
                field2.setAccessible(true);
                i2 = (Integer) field2.get(null);
            } catch (Throwable unused) {
                i = 0;
                i2 = 1;
            }
            Method method = TelephonyManager.class.getMethod("getDefault", Integer.TYPE);
            TelephonyManager telephonyManager2 = (TelephonyManager) method.invoke(telephonyManager, i);
            TelephonyManager telephonyManager3 = (TelephonyManager) method.invoke(telephonyManager, i2);
            String trim = telephonyManager2.getDeviceId().trim();
            String trim2 = telephonyManager3.getDeviceId().trim();
            if (b(trim).booleanValue() && (a3 = a(telephonyManager2, trim)) != null) {
                jSONArray.put(a3);
            }
            if (b(trim2).booleanValue() && (a2 = a(telephonyManager3, trim2)) != null) {
                jSONArray.put(a2);
            }
            return jSONArray;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static JSONArray B(Context context) {
        JSONObject a2;
        JSONObject a3;
        try {
            JSONArray jSONArray = new JSONArray();
            Class<?> cls = Class.forName("com.android.internal.telephony.PhoneFactory");
            String str = (String) cls.getMethod("getServiceName", String.class, Integer.TYPE).invoke(cls, "phone", 1);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String trim = telephonyManager.getDeviceId().trim();
            TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService(str);
            String trim2 = telephonyManager2.getDeviceId().trim();
            if (b(trim).booleanValue() && (a3 = a(telephonyManager, trim)) != null) {
                jSONArray.put(a3);
            }
            if (b(trim2).booleanValue() && (a2 = a(telephonyManager2, trim2)) != null) {
                jSONArray.put(a2);
            }
            return jSONArray;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static JSONArray C(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            Class<?> cls = Class.forName("android.telephony.MSimTelephonyManager");
            Object systemService = context.getSystemService("phone_msim");
            Method method = cls.getMethod("getDeviceId", Integer.TYPE);
            String trim = ((String) method.invoke(systemService, 0)).trim();
            String trim2 = ((String) method.invoke(systemService, 1)).trim();
            if (b(trim).booleanValue()) {
                jSONArray.put(a(cls, systemService, 0, trim, ""));
            }
            if (b(trim2).booleanValue()) {
                jSONArray.put(a(cls, systemService, 1, trim2, ""));
            }
            return jSONArray;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static JSONObject a(Class cls, Object obj, Integer num, String str, String str2) {
        String str3 = "";
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("imei", str);
        try {
            Method method = cls.getMethod("getSubscriberId" + str2, Integer.TYPE);
            jSONObject.put("subscriberId", method.invoke(obj, num) == null ? "" : ((String) method.invoke(obj, num)).trim());
        } catch (Throwable unused) {
        }
        try {
            Method method2 = cls.getMethod("getSimSerialNumber" + str2, Integer.TYPE);
            jSONObject.put("simSerialNumber", method2.invoke(obj, num) == null ? "" : ((String) method2.invoke(obj, num)).trim());
        } catch (Throwable unused2) {
        }
        try {
            jSONObject.put("dataState", (Integer) cls.getMethod("getDataState" + str2, Integer.TYPE).invoke(obj, num));
        } catch (Throwable unused3) {
        }
        try {
            jSONObject.put("networkType", a(((Integer) cls.getMethod("getNetworkType" + str2, Integer.TYPE).invoke(obj, num)).intValue()));
        } catch (Throwable unused4) {
        }
        try {
            jSONObject.put("networkOperator", (String) cls.getMethod("getNetworkOperator" + str2, Integer.TYPE).invoke(obj, num));
        } catch (Throwable unused5) {
        }
        try {
            jSONObject.put("phoneType", b(((Integer) cls.getMethod("getPhoneType" + str2, Integer.TYPE).invoke(obj, num)).intValue()));
        } catch (Throwable unused6) {
        }
        try {
            Method method3 = cls.getMethod("getSimOperator" + str2, Integer.TYPE);
            jSONObject.put("simOperator", method3.invoke(obj, num) == null ? "" : ((String) method3.invoke(obj, num)).trim());
        } catch (Throwable unused7) {
        }
        try {
            Method method4 = cls.getMethod("getSimOperatorName" + str2, Integer.TYPE);
            if (method4.invoke(obj, num) != null) {
                str3 = ((String) method4.invoke(obj, num)).trim();
            }
            jSONObject.put("simOperatorName", str3);
        } catch (Throwable unused8) {
        }
        return jSONObject;
    }
}
