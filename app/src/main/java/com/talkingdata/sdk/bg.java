package com.talkingdata.sdk;

import android.accounts.Account;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
public class bg {
    public static int a;

    public static Long[][] f(Context context) {
        return new Long[3];
    }

    public static List a(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            if (bh.b && bh.a(23)) {
                if (context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
                }
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    public static String b(Context context) {
        try {
            List<Location> a2 = a(context);
            StringBuffer stringBuffer = new StringBuffer();
            for (Location location : a2) {
                stringBuffer.append(location.getLatitude());
                stringBuffer.append(',');
                stringBuffer.append(location.getLongitude());
                stringBuffer.append(',');
                Object obj = "";
                stringBuffer.append(location.hasAltitude() ? Double.valueOf(location.getAltitude()) : "");
                stringBuffer.append(',');
                stringBuffer.append(location.getTime());
                stringBuffer.append(',');
                stringBuffer.append(location.hasAccuracy() ? Float.valueOf(location.getAccuracy()) : "");
                stringBuffer.append(',');
                stringBuffer.append(location.hasBearing() ? Float.valueOf(location.getBearing()) : "");
                stringBuffer.append(',');
                if (location.hasSpeed()) {
                    obj = Float.valueOf(location.getSpeed());
                }
                stringBuffer.append(obj);
                stringBuffer.append(',');
                stringBuffer.append(location.getProvider());
                stringBuffer.append(':');
            }
            return stringBuffer.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static JSONArray c(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (!bh.b) {
            return jSONArray;
        }
        if (bh.a(23) && context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return jSONArray;
        }
        try {
            Context context2 = ab.f;
            Context context3 = ab.f;
            LocationManager locationManager = (LocationManager) context2.getSystemService("location");
            boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
            boolean isProviderEnabled2 = locationManager.isProviderEnabled("network");
            if (isProviderEnabled || isProviderEnabled2) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("passive");
                if (lastKnownLocation != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("lat", lastKnownLocation.getLatitude());
                        jSONObject.put("lng", lastKnownLocation.getLongitude());
                        jSONObject.put("ts", lastKnownLocation.getTime());
                        if (bh.a(17)) {
                            jSONObject.put("elapsed", lastKnownLocation.getElapsedRealtimeNanos());
                        }
                        if (lastKnownLocation.hasAltitude()) {
                            jSONObject.put("altitude", lastKnownLocation.getAltitude());
                        }
                        if (lastKnownLocation.hasAccuracy()) {
                            jSONObject.put("hAccuracy", lastKnownLocation.getAccuracy());
                        }
                        if (lastKnownLocation.hasBearing()) {
                            jSONObject.put("bearing", lastKnownLocation.getBearing());
                        }
                        if (lastKnownLocation.hasSpeed()) {
                            jSONObject.put("speed", lastKnownLocation.getSpeed());
                        }
                        jSONObject.put("provider", lastKnownLocation.getProvider());
                        jSONArray.put(jSONObject);
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
        return jSONArray;
    }

    public static JSONArray d(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            Account[] e = e(context);
            if (e != null) {
                for (Account account : e) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("type", account.type);
                        jSONObject.put(com.alipay.sdk.m.h.c.e, account.name);
                        jSONArray.put(jSONObject);
                    } catch (Throwable unused) {
                    }
                }
            }
            JSONArray x = az.x(context);
            a = x.length();
            if (x.length() > 0) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", "sim");
                jSONObject2.put("extra", x);
                jSONArray.put(jSONObject2);
            }
            if (jSONArray.length() > 0) {
                return jSONArray;
            }
            return null;
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    public static Account[] e(Context context) {
        if (bh.b(context, "android.permission.GET_ACCOUNTS")) {
        }
        return null;
    }
}
