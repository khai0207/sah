package com.iflytek.cloud.a.g.a;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes.dex */
public class b {
    public static HashMap<String, String> a = new HashMap<>();
    private static long b = 0;
    private static String c = "=";
    private static String d = ",";
    private static String e = ":";
    private static String f = ";";
    private static String g = "=========================================================\n";
    private static boolean h = false;

    public static void a(String str, String str2) {
        if (h) {
            a.c("appendInfo:" + str + "," + str2);
            if (b == 0) {
                a.put(str, "" + System.currentTimeMillis());
                b = System.currentTimeMillis();
                return;
            }
            long currentTimeMillis = System.currentTimeMillis() - b;
            String str3 = "" + currentTimeMillis;
            if (!TextUtils.isEmpty(str2)) {
                str3 = str2 + e + currentTimeMillis;
            }
            if (!a.containsKey(str) || TextUtils.isEmpty(a.get(str))) {
                a.put(str, str3);
                return;
            }
            a.put(str, a.get(str) + f + str3);
        }
    }

    public static void b(String str, String str2) {
        if (h) {
            if (a.containsKey(str) && !TextUtils.isEmpty(a.get(str))) {
                str2 = a.get(str) + f + str2;
            }
            a.put(str, str2);
        }
    }
}
