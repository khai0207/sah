package com.ipaynow.wechatpay.plugin.d.c;

import android.util.Log;
import com.ipaynow.wechatpay.plugin.c.e;
import com.ipaynow.wechatpay.plugin.c.g;
import com.ipaynow.wechatpay.plugin.utils.c;
import com.unionpay.tsmservice.data.Constant;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class a implements com.ipaynow.wechatpay.plugin.d.c.c.a {
    private int I;
    private com.ipaynow.wechatpay.plugin.d.c.a.a N;
    private b O;
    private com.ipaynow.wechatpay.plugin.d.c.d.a P;
    private boolean Q;

    public a(b bVar) {
        this.N = null;
        this.O = null;
        this.P = null;
        com.ipaynow.wechatpay.plugin.d.c.a.a aVar = new com.ipaynow.wechatpay.plugin.d.c.a.a();
        this.N = aVar;
        this.P = new com.ipaynow.wechatpay.plugin.d.c.d.a(aVar);
        this.O = bVar;
        this.I = 0;
    }

    private com.ipaynow.wechatpay.plugin.d.c.a.a a(String... strArr) {
        try {
            String str = strArr[0];
            com.ipaynow.wechatpay.plugin.e.b.b("调用了WP001接口");
            String f = com.ipaynow.wechatpay.plugin.manager.b.a.f(str);
            com.ipaynow.wechatpay.plugin.e.b.b("设备信息 = " + f);
            com.ipaynow.wechatpay.plugin.e.b.b("请求的原始报文 = " + f);
            String a = com.ipaynow.wechatpay.plugin.d.a.b.a(e.e(), f.getBytes());
            com.ipaynow.wechatpay.plugin.e.b.b("请求的加密报文 = " + a);
            String a2 = a(g.f(), a);
            if (com.ipaynow.wechatpay.plugin.utils.g.y(a2)) {
                return this.P.k();
            }
            HashMap g = com.ipaynow.wechatpay.plugin.manager.b.a.g(a2);
            if (!com.ipaynow.wechatpay.plugin.d.a.a.a(com.ipaynow.wechatpay.plugin.manager.b.a.a(g), (String) g.get(Constant.KEY_SIGNATURE), e.e())) {
                return this.P.b(com.ipaynow.wechatpay.plugin.c.b.PE011.name(), com.ipaynow.wechatpay.plugin.c.b.PE011.d());
            }
            com.ipaynow.wechatpay.plugin.e.b.b("收到的报文 = " + a2);
            String str2 = (String) g.get("responseCode");
            return !com.ipaynow.wechatpay.plugin.utils.g.i("A001", str2) ? this.P.a(str2, g) : this.P.b(str2, g);
        } catch (Throwable th) {
            th.printStackTrace();
            Thread.currentThread();
            com.ipaynow.wechatpay.plugin.d.b.a.a(th);
            return this.P.k();
        }
    }

    private static String a(String str, String str2) {
        if (com.ipaynow.wechatpay.plugin.utils.g.isEmpty(str)) {
            return "";
        }
        String h = c.h(str, str2);
        if (h == null || h.equals("中小开发者HTTPS服务通讯失败")) {
            return null;
        }
        return h.trim();
    }

    private static void a(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException unused) {
            com.ipaynow.wechatpay.plugin.e.b.c("延迟时间方法异常");
        }
    }

    private com.ipaynow.wechatpay.plugin.d.c.a.a b(String... strArr) {
        try {
            this.O.b("查询交易结果...");
            String str = strArr[0];
            if (!this.Q) {
                str = com.ipaynow.wechatpay.plugin.d.a.b.a(e.e(), str.getBytes());
                this.Q = true;
            }
            com.ipaynow.wechatpay.plugin.e.b.b("请求的原始报文 = " + str);
            String a = a(g.f(), str);
            if (a == null) {
                if (this.I >= 2) {
                    return this.P.k();
                }
                int i = this.I + 1;
                this.I = i;
                a(i * 100);
                return b(str);
            }
            HashMap g = com.ipaynow.wechatpay.plugin.manager.b.a.g(a);
            String a2 = com.ipaynow.wechatpay.plugin.manager.b.a.a(g);
            com.ipaynow.wechatpay.plugin.e.b.b("验签的报文 = " + a2);
            if (!com.ipaynow.wechatpay.plugin.d.a.a.a(a2, (String) g.get(Constant.KEY_SIGNATURE), e.e())) {
                return this.P.b(com.ipaynow.wechatpay.plugin.c.b.PE011.name(), com.ipaynow.wechatpay.plugin.c.b.PE011.d());
            }
            String str2 = (String) g.get("responseCode");
            if (!"A001".equals(str2)) {
                return this.P.a(str2, g);
            }
            String str3 = (String) g.get("tradeStatus");
            if (!"A003".equals(str3) && !"A004".equals(str3)) {
                return this.P.b(str2, g);
            }
            if (this.I >= 2) {
                return this.P.b(str2, g);
            }
            a(200);
            this.I++;
            return b(str);
        } catch (Throwable th) {
            th.printStackTrace();
            Thread.currentThread();
            com.ipaynow.wechatpay.plugin.d.b.a.a(th);
            return this.P.k();
        }
    }

    @Override // com.ipaynow.wechatpay.plugin.d.c.c.a
    public final com.ipaynow.wechatpay.plugin.d.c.a.a a(int i, String... strArr) {
        if (i == 1) {
            return a(strArr);
        }
        if (i == 3) {
            return b(strArr);
        }
        Log.i("ipaynow", "空指针-IPRemoteServices-45");
        return null;
    }
}
