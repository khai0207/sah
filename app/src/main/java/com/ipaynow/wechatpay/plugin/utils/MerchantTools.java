package com.ipaynow.wechatpay.plugin.utils;

import com.ipaynow.wechatpay.plugin.manager.route.dto.RequestParams;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes.dex */
public class MerchantTools {
    public static String generatePreSignMessage(RequestParams requestParams) {
        com.ipaynow.wechatpay.plugin.manager.a.a.r().b(true);
        String a = a.a(requestParams, com.ipaynow.wechatpay.plugin.c.c.v);
        if (a == null) {
            com.ipaynow.wechatpay.plugin.manager.a.a.r().g(false);
            a = null;
        } else {
            com.ipaynow.wechatpay.plugin.manager.a.a.r().g(true);
        }
        if (a == null) {
            com.ipaynow.wechatpay.plugin.manager.a.a.r().g(false);
        } else {
            com.ipaynow.wechatpay.plugin.manager.a.a.r().g(true);
        }
        return a;
    }

    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }
}
