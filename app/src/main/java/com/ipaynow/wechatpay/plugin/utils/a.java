package com.ipaynow.wechatpay.plugin.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class a {
    public static String a(Object obj, ArrayList arrayList) {
        HashMap hashMap = new HashMap();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                String name = field.getName();
                String str = (String) field.get(obj);
                if (str != null && !str.equals("")) {
                    hashMap.put(field.getName(), (String) field.get(obj));
                }
                if (!arrayList.contains(name)) {
                    return null;
                }
            }
            return b(hashMap);
        } catch (IllegalAccessException | IllegalArgumentException unused) {
            return null;
        }
    }

    private static String b(HashMap hashMap) {
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            String str2 = (String) hashMap.get(str);
            int size = arrayList.size() - 1;
            sb.append(str);
            sb.append("=");
            sb.append(str2);
            if (i != size) {
                sb.append(com.alipay.sdk.m.o.a.l);
            }
        }
        return sb.toString();
    }
}
