package com.ipaynow.wechatpay.plugin.c;

import android.util.Log;
import com.ipaynow.wechatpay.plugin.utils.PluginTools;

/* loaded from: classes.dex */
public final class e {
    private static final String x;

    static {
        String str;
        try {
            System.loadLibrary("onlywechat_plugin");
            str = PluginTools.getConstant("RSAK");
        } catch (Throwable unused) {
            Log.e("ipaynow error", "获取秘钥失败");
            str = "";
        }
        x = str;
    }

    public static String e() {
        int i = d.w;
        return (i == 0 || i == 1) ? x : i != 2 ? x : "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1MLevZ7IPgEcx9hQANi1s/jqAYSk//uRoXBXixhMBAj/F07g/PgvFOZ79mR0E8pT1Jhyj1WN/HlahIYenieciJ1P/L8KHZoZR0hKeZ4K8g951AaZbL5B1HnrPpggZ8rNgeo0TsoK3uaWC9eSRW6zEhSR8hE/EE7CrI+eUlji8dFm3kw6MwUfbZpfH/+fePLijTMWl9KL8Nmb+kYjYtQdjiaee/IjNdjnrxlMFJ1ymXU0v4BvCn9uc7JL0PiHDwpyjtYZPX+wtimRNzOfuyXrZr/7yElKnsO3q/OmQ6cmC9qUyf0iaIGbtnVknZtVmozvFMLjxQGUK+z/hGk09pZ0SQIDAQAB";
    }
}
