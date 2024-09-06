package com.netease.nimlib.o;

import android.text.TextUtils;
import android.util.Base64;

/* compiled from: EncryptUtil.java */
/* loaded from: classes.dex */
public class g {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return new String(Base64.decode(str, 2), "UTF-8");
        } catch (Throwable unused) {
            return "";
        }
    }
}
