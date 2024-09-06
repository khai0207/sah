package com.ipaynow.wechatpay.plugin.utils;

import java.lang.reflect.Field;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class e {
    public static void a(Object obj, JSONObject jSONObject) {
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                String name = field.getName();
                if (!g.y((String) field.get(obj)) || !com.ipaynow.wechatpay.plugin.c.c.v.contains(name)) {
                    jSONObject.put(name, (String) field.get(obj));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
