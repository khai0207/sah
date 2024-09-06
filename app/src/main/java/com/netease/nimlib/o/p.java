package com.netease.nimlib.o;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;

/* compiled from: NetworkUtil.java */
/* loaded from: classes.dex */
public class p {
    private static final Uri a = Uri.parse("content://telephony/carriers/preferapn");
    private static String b = null;
    private static String c = null;

    public static int a(Context context) {
        NetworkInfo d = d(context);
        if (d == null) {
            return -1;
        }
        return d.getType();
    }

    public static boolean b(Context context) {
        NetworkInfo d = d(context);
        return d != null && d.isAvailable();
    }

    public static boolean c(Context context) {
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        try {
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("NetworkUtil", "isNetworkConnected error ", e);
        }
        if (!h(context)) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (Build.VERSION.SDK_INT < 23) {
            return z;
        }
        if (z && (activeNetwork = connectivityManager.getActiveNetwork()) != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) != null) {
            return networkCapabilities.hasCapability(16);
        }
        return false;
    }

    public static NetworkInfo d(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (h(context)) {
                return connectivityManager.getActiveNetworkInfo();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean e(Context context) {
        int j = j(context);
        return j == 1 || j == 2 || j == 3 || j == 4;
    }

    public static boolean f(Context context) {
        try {
            NetworkInfo d = d(context);
            if (d != null) {
                if (d.getType() == 1) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static String g(Context context) {
        NetworkInfo d = d(context);
        if (d == null) {
            return "";
        }
        if (d.getType() == 1) {
            return d.getTypeName();
        }
        return d.getTypeName() + " [" + l(context) + "#" + d.getSubtypeName() + "]";
    }

    public static boolean h(Context context) {
        if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getApplicationInfo().packageName) == 0) {
            return true;
        }
        com.netease.nimlib.log.b.e("NetworkUtil", "without permission to ACCESS_NETWORK_STATE");
        return false;
    }

    public static String i(Context context) {
        int j = j(context);
        return j == 1 ? "2G" : j == 2 ? "3G" : j == 3 ? "4G" : j == 4 ? "5G" : j == 10 ? "WIFI" : "UNKNOWN";
    }

    public static int j(Context context) {
        NetworkInfo d = d(context);
        if (d == null) {
            return 0;
        }
        if (d.getType() != 0) {
            return d.getType() == 1 ? 10 : 0;
        }
        int subtype = d.getSubtype();
        if (subtype == 20) {
            return 4;
        }
        switch (subtype) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return 1;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return 2;
            case 13:
                return 3;
            default:
                return 0;
        }
    }

    public static String k(Context context) {
        if (context == null) {
            return "";
        }
        if (!s.a(context, "android.permission.READ_PHONE_STATE")) {
            com.netease.nimlib.log.b.e("NetworkUtil", "getSimOperator without permission to READ_PHONE_STATE");
            return "";
        }
        if (c != null) {
            com.netease.nimlib.log.b.d("NetworkUtil", "getSimOperator simOperator from cache = " + c);
            return c;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String simOperator = telephonyManager.getSimOperator();
                c = simOperator;
                if (simOperator == null) {
                    c = "";
                }
                com.netease.nimlib.log.b.d("NetworkUtil", "first getSimOperator simOperator = " + c);
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("NetworkUtil", "getSimOperator exception", e);
            c = "";
        }
        return c;
    }

    public static String l(Context context) {
        if (context == null) {
            return "";
        }
        if (!s.a(context, "android.permission.READ_PHONE_STATE")) {
            com.netease.nimlib.log.b.e("NetworkUtil", "getNetworkOperatorName without permission to READ_PHONE_STATE");
            return "";
        }
        if (b != null) {
            com.netease.nimlib.log.b.d("NetworkUtil", "getNetworkOperatorName networkOperatorName from cache = " + b);
            return b;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String networkOperatorName = telephonyManager.getNetworkOperatorName();
                b = networkOperatorName;
                if (networkOperatorName == null) {
                    b = "";
                }
                com.netease.nimlib.log.b.d("NetworkUtil", "first getNetworkOperatorName networkOperatorName = " + b);
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("NetworkUtil", "getNetworkOperatorName exception", e);
            b = "";
        }
        return b;
    }

    public static com.netease.nimlib.network.a.a m(Context context) {
        com.netease.nimlib.network.a.a aVar;
        com.netease.nimlib.network.a.a aVar2 = com.netease.nimlib.network.a.a.UNKNOWN;
        try {
            if (Build.VERSION.SDK_INT < 23) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
                if (networkInfo != null && networkInfo.isAvailable()) {
                    aVar = com.netease.nimlib.network.a.a.MOBILE;
                } else if (networkInfo2 != null && networkInfo2.isAvailable()) {
                    aVar = com.netease.nimlib.network.a.a.WIFI;
                } else {
                    aVar = com.netease.nimlib.network.a.a.NONE;
                }
            } else if (p(context)) {
                aVar = com.netease.nimlib.network.a.a.MOBILE;
            } else if (o(context)) {
                aVar = com.netease.nimlib.network.a.a.WIFI;
            } else {
                aVar = com.netease.nimlib.network.a.a.NONE;
            }
            aVar2 = aVar;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkUtil", "getNetWorkState exception", th);
        }
        com.netease.nimlib.log.b.d("NetworkUtil", "getNetWorkState result = " + aVar2);
        return aVar2;
    }

    public static boolean n(Context context) {
        NetworkCapabilities networkCapabilities;
        if (context == null) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT < 23) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
                return false;
            }
            return networkCapabilities.hasCapability(16);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkUtil", "isOnline exception", th);
            return false;
        }
    }

    public static boolean o(Context context) {
        NetworkCapabilities networkCapabilities;
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 23) {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) != null) {
                return networkCapabilities.hasTransport(1);
            }
        }
        return false;
    }

    public static boolean p(Context context) {
        NetworkCapabilities networkCapabilities;
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 23) {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0);
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) != null) {
                return networkCapabilities.hasTransport(0);
            }
        }
        return false;
    }
}
