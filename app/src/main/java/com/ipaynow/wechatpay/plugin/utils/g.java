package com.ipaynow.wechatpay.plugin.utils;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class g {
    private static boolean a(StringBuilder sb, boolean z, String str, Map map) {
        if (!z) {
            if (str.length() == 0) {
                return false;
            }
            map.put(str, h.w(sb.toString()));
            return true;
        }
        String sb2 = sb.toString();
        if (sb2.length() == 0) {
            return false;
        }
        map.put(sb2, "");
        return true;
    }

    public static boolean i(String str, String str2) {
        if (str != str2) {
            return str == null ? str2 == null : str.equals(str2);
        }
        return true;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean y(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static HashMap z(String str) {
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
}
