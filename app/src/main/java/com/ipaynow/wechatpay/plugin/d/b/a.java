package com.ipaynow.wechatpay.plugin.d.b;

import android.os.Looper;
import com.ipaynow.wechatpay.plugin.c.g;
import com.ipaynow.wechatpay.plugin.utils.PluginTools;
import com.unionpay.tsmservice.data.Constant;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class a {
    private static a H;
    private static int I;

    public static void a(String str) {
        int i;
        while (true) {
            com.ipaynow.wechatpay.plugin.e.b.b("post exception = " + str);
            String h = com.ipaynow.wechatpay.plugin.utils.c.h(g.g(), str);
            com.ipaynow.wechatpay.plugin.e.b.b(h);
            if (com.ipaynow.wechatpay.plugin.utils.g.y(h)) {
                i = I;
                if (i >= 2) {
                    return;
                }
            } else {
                HashMap v = PluginTools.v(h);
                if (!com.ipaynow.wechatpay.plugin.d.a.a.a(com.ipaynow.wechatpay.plugin.manager.b.a.a(v), (String) v.get(Constant.KEY_SIGNATURE), com.ipaynow.wechatpay.plugin.c.e.e()) || com.ipaynow.wechatpay.plugin.utils.g.i("A001", (String) v.get("responseCode")) || (i = I) >= 2) {
                    return;
                }
            }
            I = i + 1;
        }
    }

    public static void a(Throwable th) {
        if (b(th)) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            com.ipaynow.wechatpay.plugin.manager.route.a.I();
            com.ipaynow.wechatpay.plugin.manager.route.a.a(com.ipaynow.wechatpay.plugin.manager.a.a.r().v(), com.ipaynow.wechatpay.plugin.c.b.PE005.name(), com.ipaynow.wechatpay.plugin.c.b.PE005.d());
            if (Looper.myLooper() == null) {
                Looper.loop();
            }
        }
    }

    private static boolean b(Throwable th) {
        String e;
        if (th == null) {
            return false;
        }
        try {
            new b().start();
            com.ipaynow.wechatpay.plugin.e.b.b("deviceInfo:" + com.ipaynow.wechatpay.plugin.manager.a.a.r().u());
            String str = "";
            if (th != null) {
                th.printStackTrace();
                str = c(th);
            }
            com.ipaynow.wechatpay.plugin.e.b.b("exception:" + str);
            e = com.ipaynow.wechatpay.plugin.manager.b.a.e(com.ipaynow.wechatpay.plugin.manager.a.a.r().u(), str);
        } catch (Exception unused) {
        }
        if (com.ipaynow.wechatpay.plugin.utils.g.isEmpty(e)) {
            return true;
        }
        com.ipaynow.wechatpay.plugin.e.b.b(e);
        new Thread(new c(e), "ExceptionPost").start();
        return true;
    }

    private static String c(Throwable th) {
        StackTraceElement stackTraceElement;
        StringBuilder sb;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ThrowableName:" + th.toString() + "\n");
        if (th.getCause() != null) {
            stringBuffer.append(String.valueOf(th.getCause().toString()) + "\n");
        }
        stringBuffer.append("ThrowableThreadName:" + Thread.currentThread().getName() + "\n");
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace == null) {
            return stringBuffer.toString();
        }
        stringBuffer.append("AppMaxMemory:" + ((((int) Runtime.getRuntime().maxMemory()) / 1024) / 1024) + "\n");
        stringBuffer.append("AppUseMemory:" + ((long) ((((int) Runtime.getRuntime().totalMemory()) / 1024) / 1024)) + "\n");
        stringBuffer.append("ThrowableContent:\n");
        for (int i = 0; i < stackTrace.length && (stackTraceElement = stackTrace[i]) != null; i++) {
            if (stackTraceElement.getClassName().contains("ipaynow")) {
                sb = new StringBuilder("[");
                sb.append(stackTraceElement.toString());
                sb.append("]\n");
            } else {
                sb = new StringBuilder(String.valueOf(stackTraceElement.toString()));
                sb.append("\n");
            }
            stringBuffer.append(sb.toString());
        }
        return stringBuffer.toString();
    }
}
