package com.iflytek.common.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.iflytek.common.LaunchService;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class c {
    private static d a = null;
    private static int b = -1;

    public static synchronized void a(Context context) {
        synchronized (c.class) {
            com.iflytek.common.c.c.a(context, "============start=============");
            d a2 = d.a(context);
            a = a2;
            a2.a();
            if (a.b()) {
                a.a(System.currentTimeMillis());
                com.iflytek.common.c.e.a.execute(new f(context));
            }
            d(context);
        }
    }

    private static void a(Context context, ResolveInfo resolveInfo) {
        try {
            String str = resolveInfo.serviceInfo.packageName;
            if (!a.a(str)) {
                com.iflytek.common.c.c.a("LaunchImpl", "startService not need:" + str);
            } else {
                if (context.getPackageName().equals(str)) {
                    return;
                }
                Intent intent = new Intent("com.iflytek.common.ACTION_LAUNCH");
                intent.setPackage(str);
                com.iflytek.common.c.c.a(context, "start app:" + context.startService(intent));
            }
        } catch (Exception e) {
            com.iflytek.common.c.c.b("LaunchImpl", "", e);
        }
    }

    public static synchronized void a(Context context, String str, String str2) {
        synchronized (c.class) {
            if (a == null) {
                a = d.a(context);
            }
            a.a(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context) {
        List<ResolveInfo> list;
        try {
            list = context.getPackageManager().queryIntentServices(new Intent("com.iflytek.common.ACTION_LAUNCH"), 224);
        } catch (Exception e) {
            com.iflytek.common.c.c.b("LaunchImpl", "", e);
            list = null;
        }
        if (list == null) {
            com.iflytek.common.c.c.b("LaunchImpl", "query action null");
            return;
        }
        Iterator<ResolveInfo> it = list.iterator();
        while (it.hasNext()) {
            a(context, it.next());
        }
    }

    private static void d(Context context) {
        if (b == 0) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClass(context, LaunchService.class);
            if (context.startService(intent) != null) {
                b = 1;
            }
        } catch (Exception unused) {
            b = 0;
            com.iflytek.common.c.c.b("LaunchImpl", "start self Service error");
        }
    }
}
