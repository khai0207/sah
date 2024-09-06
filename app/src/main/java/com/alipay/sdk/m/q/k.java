package com.alipay.sdk.m.q;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class k {
    public static final String a = "resultStatus";
    public static final String b = "memo";
    public static final String c = "result";

    public static Map<String, String> a(com.alipay.sdk.m.o.a aVar, String str) {
        Map<String, String> a2 = a();
        try {
            return a(str);
        } catch (Throwable th) {
            com.alipay.sdk.m.g.a.a(aVar, com.alipay.sdk.m.g.b.l, com.alipay.sdk.m.g.b.q, th);
            return a2;
        }
    }

    public static String b(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(h.d));
    }

    public static Map<String, String> a() {
        com.alipay.sdk.m.f.c b2 = com.alipay.sdk.m.f.c.b(com.alipay.sdk.m.f.c.CANCELED.b());
        HashMap hashMap = new HashMap();
        hashMap.put(a, Integer.toString(b2.b()));
        hashMap.put(b, b2.a());
        hashMap.put("result", "");
        return hashMap;
    }

    public static Map<String, String> a(String str) {
        String[] split = str.split(";");
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            String substring = str2.substring(0, str2.indexOf("={"));
            hashMap.put(substring, b(str2, substring));
        }
        return hashMap;
    }

    public static String a(String str, String str2) {
        try {
            Matcher matcher = Pattern.compile("(^|;)" + str2 + "=\\{([^}]*?)\\}").matcher(str);
            if (matcher.find()) {
                return matcher.group(2);
            }
        } catch (Throwable th) {
            d.a(th);
        }
        return "?";
    }
}
