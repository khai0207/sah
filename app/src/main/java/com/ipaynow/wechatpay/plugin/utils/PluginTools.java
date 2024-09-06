package com.ipaynow.wechatpay.plugin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class PluginTools {
    private static boolean a(StringBuilder sb, boolean z, String str, Map map) {
        if (!z) {
            if (str.length() == 0) {
                return false;
            }
            map.put(str, w(sb.toString()));
            return true;
        }
        String sb2 = sb.toString();
        if (sb2.length() == 0) {
            return false;
        }
        map.put(sb2, "");
        return true;
    }

    public static native String getConstant(String str);

    public static HashMap v(String str) {
        HashMap hashMap = new HashMap();
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        String str2 = null;
        boolean z = true;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '&') {
                if (!a(sb, z, str2, hashMap)) {
                    return null;
                }
                sb.setLength(0);
                z = true;
            } else if (z && charAt == '=') {
                str2 = sb.toString();
                sb.setLength(0);
                z = false;
            } else {
                sb.append(charAt);
            }
        }
        if (a(sb, z, str2, hashMap)) {
            return hashMap;
        }
        return null;
    }

    private static String w(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }
}
