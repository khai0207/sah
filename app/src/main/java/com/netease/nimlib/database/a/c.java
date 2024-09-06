package com.netease.nimlib.database.a;

import android.text.TextUtils;
import com.alipay.sdk.m.q.h;

/* compiled from: DatabaseHelper.java */
/* loaded from: classes.dex */
public class c {
    public static String a(String str) {
        return (str == null || str.length() == 0) ? "" : str.replace("'", "''");
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return "'%" + str.replace("'", "''").replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_") + "%' ESCAPE '\\'";
    }

    public static String c(String str) {
        return "%" + str + "%";
    }

    public static String d(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] strArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", h.d, "|"};
            for (int i = 0; i < 14; i++) {
                String str2 = strArr[i];
                if (str.contains(str2)) {
                    str = str.replace(str2, "\\" + str2);
                }
            }
        }
        return str;
    }
}
