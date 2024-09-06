package com.ipaynow.wechatpay.plugin.d.c.d;

import com.ipaynow.wechatpay.plugin.c.b;
import com.ipaynow.wechatpay.plugin.utils.g;
import java.net.URLDecoder;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class a {
    private com.ipaynow.wechatpay.plugin.d.c.a.a N;

    public a(com.ipaynow.wechatpay.plugin.d.c.a.a aVar) {
        this.N = null;
        this.N = aVar;
    }

    private static String[] c(String str, String str2) {
        if (!str2.contains("#") && !g.y(str)) {
            str2 = String.valueOf(str) + "#" + str2;
        }
        String[] split = str2.split("#");
        return split.length == 2 ? split : new String[]{b.PE011.name(), str2};
    }

    public final com.ipaynow.wechatpay.plugin.d.c.a.a a(String str, HashMap hashMap) {
        String[] c = (!str.equals("A002") ? hashMap.containsKey("responseMsg") : hashMap.containsKey("responseMsg")) ? new String[]{b.PE011.name(), "未知错误"} : c(str, URLDecoder.decode((String) hashMap.get("responseMsg")));
        this.N.Y = "02";
        this.N.respCode = str;
        this.N.errorCode = c[0];
        this.N.respMsg = c[1];
        this.N.Z = hashMap;
        return this.N;
    }

    public final com.ipaynow.wechatpay.plugin.d.c.a.a b(String str, String str2) {
        this.N.Y = "02";
        this.N.respCode = "02";
        this.N.errorCode = str;
        this.N.respMsg = str2;
        return this.N;
    }

    public final com.ipaynow.wechatpay.plugin.d.c.a.a b(String str, HashMap hashMap) {
        this.N.Y = "00";
        this.N.respCode = str;
        this.N.Z = hashMap;
        return this.N;
    }

    public final com.ipaynow.wechatpay.plugin.d.c.a.a k() {
        this.N.Y = "01";
        this.N.respCode = "01";
        this.N.errorCode = b.PE002.name();
        this.N.respMsg = b.PE002.d();
        return this.N;
    }
}
