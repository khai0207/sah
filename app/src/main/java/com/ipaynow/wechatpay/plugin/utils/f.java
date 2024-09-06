package com.ipaynow.wechatpay.plugin.utils;

import com.ipaynow.wechatpay.plugin.manager.route.dto.RequestParams;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public final class f {
    public static RequestParams x(String str) {
        RequestParams requestParams = new RequestParams();
        try {
            HashMap z = g.z(str);
            for (Field field : requestParams.getClass().getDeclaredFields()) {
                String name = field.getName();
                Iterator it = z.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (g.i(name, (String) entry.getKey())) {
                        field.set(requestParams, entry.getValue());
                        break;
                    }
                }
            }
            return requestParams;
        } catch (IllegalAccessException | IllegalArgumentException unused) {
            return null;
        }
    }
}
