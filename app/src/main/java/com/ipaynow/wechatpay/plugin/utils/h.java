package com.ipaynow.wechatpay.plugin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* loaded from: classes.dex */
public final class h {
    public static String w(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            com.ipaynow.wechatpay.plugin.e.b.d("URL解码失败");
            return "";
        }
    }
}
