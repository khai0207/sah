package com.iflytek.cloud.a.g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;

/* loaded from: classes.dex */
public class h {
    public static String a(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return "none";
        }
        try {
            if (networkInfo.getType() == 1) {
                return "wifi";
            }
            String lowerCase = networkInfo.getExtraInfo().toLowerCase();
            if (TextUtils.isEmpty(lowerCase)) {
                return "none";
            }
            if (!lowerCase.startsWith("3gwap") && !lowerCase.startsWith("uniwap")) {
                return lowerCase.startsWith("cmwap") ? "cmwap" : lowerCase.startsWith("ctwap") ? "ctwap" : lowerCase.startsWith("ctnet") ? "ctnet" : lowerCase;
            }
            return "uniwap";
        } catch (Exception e) {
            com.iflytek.cloud.a.g.a.a.a(e.toString());
            return "none";
        }
    }

    public static void a(Context context) throws SpeechError {
        if (context == null) {
            return;
        }
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo();
        if (allNetworkInfo != null && allNetworkInfo.length > 0) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo == null || networkInfo.isConnectedOrConnecting()) {
                    return;
                }
            }
        }
        throw new SpeechError(20001);
    }

    public static String b(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return "none";
        }
        try {
            if (networkInfo.getType() == 1) {
                return "none";
            }
            return ("" + networkInfo.getSubtypeName()) + ";" + networkInfo.getSubtype();
        } catch (Exception e) {
            com.iflytek.cloud.a.g.a.a.a(e.toString());
            return "none";
        }
    }
}
