package com.ipaynow.wechatpay.plugin.c;

import android.util.Log;
import com.ipaynow.wechatpay.plugin.utils.PluginTools;

/* loaded from: classes.dex */
public final class g {
    private static final String A;
    private static final String B;
    private static final String z;

    static {
        String str;
        String str2;
        String str3 = "";
        try {
            System.loadLibrary("onlywechat_plugin");
            str = PluginTools.getConstant("IU");
            try {
                str2 = PluginTools.getConstant("ITU");
                try {
                    str3 = PluginTools.getConstant("IPU");
                } catch (Throwable unused) {
                    Log.e("ipaynow error ", "获取服务器地址失败");
                    z = str;
                    A = str2;
                    B = str3;
                }
            } catch (Throwable unused2) {
                str2 = "";
            }
        } catch (Throwable unused3) {
            str = "";
            str2 = str;
        }
        z = str;
        A = str2;
        B = str3;
    }

    public static String f() {
        int i = d.w;
        return i != 0 ? i != 1 ? i != 2 ? z : A : B : z;
    }

    public static String g() {
        int i = d.w;
        return i != 1 ? i != 2 ? "https://pay.ipaynow.cn/sdk/syncException" : "https://dby.ipaynow.cn/api/payment/sdk/syncException" : "https://pay.ipaynow.cn/api_release/sdk/syncException";
    }
}
