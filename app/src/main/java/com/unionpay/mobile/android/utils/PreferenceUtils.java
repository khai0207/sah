package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class PreferenceUtils {
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003b, code lost:
    
        r1 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
    
        if (com.unionpay.mobile.android.utils.c.a(r3) != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0027, code lost:
    
        if (com.unionpay.mobile.android.utils.c.a(r3) != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r7) {
        /*
            java.lang.String r0 = "UnionPayPluginEx.pref"
            r1 = 0
            android.content.SharedPreferences r0 = r7.getSharedPreferences(r0, r1)
            java.lang.String r1 = ""
            java.lang.String r2 = "uid"
            java.lang.String r3 = r0.getString(r2, r1)
            java.lang.String r4 = "tag1"
            java.lang.String r5 = r0.getString(r4, r1)
            boolean r6 = android.text.TextUtils.isEmpty(r3)
            if (r6 != 0) goto L50
            int r5 = r3.length()
            r6 = 32
            if (r5 != r6) goto L2a
            boolean r5 = com.unionpay.mobile.android.utils.c.a(r3)
            if (r5 == 0) goto L3c
            goto L3b
        L2a:
            java.lang.String r3 = e(r7, r3)
            int r5 = r3.length()
            if (r5 != r6) goto L3c
            boolean r5 = com.unionpay.mobile.android.utils.c.a(r3)
            if (r5 != 0) goto L3b
            goto L3c
        L3b:
            r1 = r3
        L3c:
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r0.remove(r2)
            r0.commit()
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L5a
            a(r7, r1, r4)
            goto L5a
        L50:
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L5a
            java.lang.String r1 = e(r7, r5)
        L5a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.utils.PreferenceUtils.a(android.content.Context):java.lang.String");
    }

    public static String a(Context context, String str) {
        return e(context, context.getSharedPreferences("UnionPayPluginEx.pref", 0).getString(str, ""));
    }

    private static String a(String str, String str2) {
        try {
            return b.a(d.a(b.a(str2), str.getBytes("utf-8")));
        } catch (Exception unused) {
            return "";
        }
    }

    public static void a(Context context, String str, String str2) {
        String c = e.c(context);
        String a = a(str + c, (c + "23456789abcdef12123456786789abcd").substring(0, 32));
        if (a == null) {
            a = "";
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
        edit.putString(str2, a);
        edit.commit();
    }

    public static String b(Context context) {
        return b(context, "last_pay_method", "tag2");
    }

    private static String b(Context context, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UnionPayPluginEx.pref", 0);
        String string = sharedPreferences.getString(str, "");
        String string2 = sharedPreferences.getString(str2, "");
        if (TextUtils.isEmpty(string)) {
            return !TextUtils.isEmpty(string2) ? e(context, string2) : "";
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(str);
        edit.commit();
        return "";
    }

    private static String b(String str, String str2) {
        try {
            return new String(d.b(b.a(str2), b.a(str)), "utf-8").trim();
        } catch (Exception unused) {
            return "";
        }
    }

    public static void b(Context context, String str) {
        a(context, str, "tag1");
    }

    public static String c(Context context) {
        return b(context, "last_user", "tag3");
    }

    public static void c(Context context, String str) {
        a(context, str, "tag2");
    }

    public static void d(Context context, String str) {
        a(context, str, "tag3");
    }

    public static native String decPrefData(String str, String str2);

    private static String e(Context context, String str) {
        String c = e.c(context);
        String b = b(str, (c + "23456789abcdef12123456786789abcd").substring(0, 32));
        return (b != null && b.endsWith(c)) ? b.substring(0, b.length() - c.length()) : "";
    }

    public static native String forConfig(int i, String str);
}
