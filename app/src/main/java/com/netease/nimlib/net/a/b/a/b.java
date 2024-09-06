package com.netease.nimlib.net.a.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.netease.nimlib.o.w;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NosLbsStorage.java */
/* loaded from: classes.dex */
public class b {
    static void a(Context context) {
        a(context, "nos_preload_time", System.currentTimeMillis());
    }

    static boolean b(Context context) {
        long a = a(context, "netease_pomelo_nos_last_save_time_new");
        if (a <= 0) {
            return false;
        }
        boolean z = System.currentTimeMillis() - a <= com.umeng.analytics.a.h;
        if (!z) {
            d(context);
        }
        return z;
    }

    static void a(Context context, JSONObject jSONObject, boolean z) {
        try {
            String string = jSONObject.getString("lbs");
            com.netease.nimlib.log.b.c("NOS_LBS", "get nos lbs ip: " + string);
            String a = a(jSONObject.getJSONArray("upload"));
            com.netease.nimlib.log.b.c("NOS_LBS", "get nos upload server ip string: " + a);
            if (!TextUtils.isEmpty(string)) {
                a(context, "netease_pomelo_nos_lbs", string);
            }
            if (!TextUtils.isEmpty(a) && z) {
                a(context, "netease_pomelo_nos_server", a);
                com.netease.nimlib.log.b.d("NOS_LBS", "save http upload server ip: " + a);
            } else {
                a(context, "netease_pomelo_nos_server", (String) null);
            }
            if (!TextUtils.isEmpty(a)) {
                String a2 = a(a);
                a(context, "netease_pomelo_nos_https_server", a2);
                com.netease.nimlib.log.b.c("NOS_LBS", "save https upload server ip: " + a2);
            } else {
                a(context, "netease_pomelo_nos_https_server", (String) null);
            }
            a(context, "netease_pomelo_nos_last_save_time_new", System.currentTimeMillis());
            com.netease.nimlib.log.b.c("NOS_LBS", "save nos lbs response data");
        } catch (JSONException e) {
            com.netease.nimlib.log.b.e("NOS_LBS", "get json array exception", e);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NOS_LBS", "save nos lbs response data exception", th);
        }
    }

    static String c(Context context) {
        return b(context, "netease_pomelo_nos_lbs");
    }

    public static void d(Context context) {
        a(context, "netease_pomelo_nos_lbs", (String) null);
    }

    static String[] a(Context context, boolean z) {
        String[] strArr = new String[0];
        if (z) {
            String b = b(context, "netease_pomelo_nos_https_server");
            if (w.b((CharSequence) b)) {
                strArr = b.split(";");
            }
        }
        String[] strArr2 = new String[0];
        String b2 = b(context, "netease_pomelo_nos_server");
        if (w.b((CharSequence) b2)) {
            strArr2 = b2.split(";");
        }
        String[] strArr3 = new String[strArr.length + strArr2.length];
        System.arraycopy(strArr, 0, strArr3, 0, strArr.length);
        System.arraycopy(strArr2, 0, strArr3, strArr.length, strArr2.length);
        return strArr3;
    }

    private static String a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        String str = "";
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                str = str + jSONArray.getString(i);
                if (i != jSONArray.length() - 1) {
                    str = str + ";";
                }
            } catch (JSONException e) {
                com.netease.nimlib.log.b.e("NOS_LBS", "get json string exception", e);
            }
        }
        return str;
    }

    private static String a(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("http://", "https://");
    }

    private static void a(Context context, String str, long j) {
        SharedPreferences.Editor edit = e(context).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    private static long a(Context context, String str) {
        return e(context).getLong(str, 0L);
    }

    private static void a(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor edit = e(context).edit();
            if (str2 == null) {
                str2 = "";
            }
            edit.putString(str, Base64.encodeToString(str2.getBytes(), 2));
            edit.apply();
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("NOS_LBS", "error base 64", e);
        }
    }

    private static String b(Context context, String str) {
        try {
            String string = e(context).getString(str, null);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return new String(Base64.decode(string, 2));
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("NOS_LBS", "error base 64", e);
            return null;
        }
    }

    private static SharedPreferences e(Context context) {
        return context.getSharedPreferences("xx_NOS_LBS", 0);
    }
}
