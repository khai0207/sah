package com.unionpay.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.android.pc.ioc.internet.FastHttp;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class r {
    static boolean a = false;
    static volatile boolean b = false;
    static volatile boolean c = false;
    static boolean d = false;
    static List e = new ArrayList();
    static boolean f = false;
    private static final HandlerThread g;
    private static Handler h;

    /* loaded from: classes.dex */
    static class a {
        HashMap a = new HashMap();
        boolean b = true;

        a() {
        }
    }

    /* loaded from: classes.dex */
    static class b {
        private static Handler a;
        private static final HandlerThread b;

        static {
            HandlerThread handlerThread = new HandlerThread("AdditionalHandlerThread");
            b = handlerThread;
            handlerThread.start();
            a = new ac(b.getLooper());
        }

        static final Handler a() {
            return a;
        }
    }

    /* loaded from: classes.dex */
    static class c implements Thread.UncaughtExceptionHandler {
        private Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

        c() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable th) {
            if (ad.b) {
                r.a(th, String.valueOf(System.currentTimeMillis()));
                Log.w("unionpayLog", "UncaughtException in Thread " + thread.getName(), th);
            }
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.a;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    static {
        HandlerThread handlerThread = new HandlerThread("PauseEventThread");
        g = handlerThread;
        h = null;
        handlerThread.start();
        h = new s(g.getLooper());
    }

    private static String a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(str)) {
                return String.valueOf(bundle.get(str));
            }
        }
        return null;
    }

    private static final String a(Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(th.toString());
        sb.append(FastHttp.LINEND);
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length <= 50 ? stackTrace.length : 50;
        for (int i = 0; i < length; i++) {
            sb.append("\t" + stackTrace[i] + FastHttp.LINEND);
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            a(sb, stackTrace, cause, 1);
        }
        return sb.toString();
    }

    static /* synthetic */ void a() {
        try {
            al.a("Determine if app is quiting, after being waited for " + String.valueOf(ad.j / 1000) + " sec, now isAppQuiting = " + am.c());
            if (am.c().equals("1")) {
                b.a().removeMessages(8);
                a aVar = new a();
                aVar.a.put("apiType", 3);
                aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
                aVar.a.put("sessionEnd", 1);
                Message.obtain(aj.a(), 102, aVar).sendToTarget();
                am.a("2");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    static void a(Context context) {
        if (b) {
            d();
            e();
            return;
        }
        if (context == null) {
            return;
        }
        try {
            ad.c = context.getApplicationContext();
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            String a2 = a(bundle, "unionpay_APP_ID");
            String a3 = a(bundle, "unionpay_CHANNEL_ID");
            if (a2 != null && !a2.trim().isEmpty()) {
                if (a3 == null || a3.trim().isEmpty()) {
                    a3 = "Default";
                }
                a(context, a2, a3, true);
                return;
            }
            al.b("[SDKInit] unionpay_APP_ID not found in AndroidManifest.xml!");
        } catch (Throwable th) {
            al.a("[SDKInit] Failed to initialize!", th);
        }
    }

    static void a(Context context, String str) {
        al.a("onPageStart being called! pageName: " + str);
        if (d) {
            d = false;
            return;
        }
        if (!(context instanceof Activity) || (str != null && !str.isEmpty())) {
            a(context, str, 6);
        } else {
            Activity activity = (Activity) context;
            a(activity, activity.getLocalClassName(), 6);
        }
    }

    private static void a(Context context, String str, int i) {
        if (context != null && !b) {
            a(context);
        }
        aw.a(new z(i, str));
    }

    static void a(Context context, String str, String str2) {
        if (context == null) {
            return;
        }
        ad.c = context.getApplicationContext();
        a(context, str, str2, false);
    }

    static void a(Context context, String str, String str2, Map map) {
        aw.a(new t(context, str, str2, map));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001b A[Catch: all -> 0x00df, TryCatch #2 {all -> 0x00df, blocks: (B:8:0x000c, B:12:0x001b, B:18:0x0021, B:20:0x0029, B:21:0x00dc, B:22:0x0030, B:24:0x0038, B:28:0x0044, B:40:0x00d4, B:45:0x00a1, B:47:0x00a5, B:48:0x00a9, B:53:0x00bf, B:32:0x005e, B:34:0x0064, B:36:0x0079, B:38:0x007d, B:41:0x006d, B:43:0x0073, B:50:0x00ae), top: B:7:0x000c, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(android.content.Context r4, java.lang.String r5, java.lang.String r6, boolean r7) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.sdk.r.a(android.content.Context, java.lang.String, java.lang.String, boolean):void");
    }

    static void a(Context context, String str, boolean z) {
        String str2;
        if (am.c() != null && am.c().equals("2")) {
            e();
        }
        am.a("0");
        if (z && f) {
            return;
        }
        Activity activity = null;
        if (context instanceof Activity) {
            activity = (Activity) context;
            if (str.isEmpty()) {
                str2 = activity.getLocalClassName();
                if (activity == null && (activity.getChangingConfigurations() & 128) == 128) {
                    d = true;
                    return;
                } else {
                    aw.a(new u(str, context, str2));
                }
            }
        }
        str2 = str;
        if (activity == null) {
        }
        aw.a(new u(str, context, str2));
    }

    static void a(Context context, Throwable th) {
        aw.a(new y(th, context));
    }

    static final void a(String str, long j) {
        if (ad.c != null) {
            an.a(ad.c, "unionpaypref_longtime", "unionpayadditionalVersionName", str);
        }
        if (ad.c != null) {
            an.a(ad.c, "unionpaypref_longtime", "unionpayadditionalVersionCode", j);
        }
    }

    private static final void a(StringBuilder sb, StackTraceElement[] stackTraceElementArr, Throwable th, int i) {
        while (true) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length - 1;
            for (int length2 = stackTraceElementArr.length - 1; length >= 0 && length2 >= 0 && stackTrace[length].equals(stackTraceElementArr[length2]); length2--) {
                length--;
            }
            if (length > 50) {
                length = 50;
            }
            sb.append("Caused by : " + th + FastHttp.LINEND);
            for (int i2 = 0; i2 <= length; i2++) {
                sb.append("\t" + stackTrace[i2] + FastHttp.LINEND);
            }
            if (i >= 5 || th.getCause() == null) {
                return;
            }
            i++;
            stackTraceElementArr = stackTrace;
        }
    }

    static final void a(Throwable th, String str) {
        if (ad.c == null) {
            return;
        }
        Throwable th2 = th;
        while (th2.getCause() != null) {
            th2 = th2.getCause();
        }
        StackTraceElement[] stackTrace = th2.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(th2.getClass().getName());
        sb.append(":");
        String packageName = ad.c.getPackageName();
        int i = 0;
        for (int i2 = 0; i < 3 && i2 < stackTrace.length; i2++) {
            String className = stackTrace[i2].getClassName();
            if ((!className.startsWith("java.") || packageName.startsWith("java.")) && ((!className.startsWith("javax.") || packageName.startsWith("javax.")) && ((!className.startsWith("android.") || packageName.startsWith("android.")) && (!className.startsWith("com.android.") || packageName.startsWith("com.android."))))) {
                sb.append(stackTrace[i2].toString());
                sb.append(":");
                i++;
            }
        }
        long currentTimeMillis = str.trim().isEmpty() ? System.currentTimeMillis() : Long.valueOf(str).longValue();
        ar.c().a();
        ar.c().a(currentTimeMillis, a(th));
        ar.c().b();
        long currentTimeMillis2 = System.currentTimeMillis();
        if (ad.c != null) {
            an.a(ad.c, "unionpaypref_shorttime", "unionpaypref.end.key", currentTimeMillis2);
        }
    }

    static /* synthetic */ void b() {
        long b2 = am.b();
        long currentTimeMillis = System.currentTimeMillis();
        if (b2 == 0) {
            if (ad.c != null) {
                an.a(ad.c, "unionpaypref_longtime", "unionpaypref.init.key", currentTimeMillis);
            }
        } else if (currentTimeMillis - am.b() > com.umeng.analytics.a.h) {
            aw.c = true;
        }
    }

    static void b(Context context) {
        a(context, "", true);
    }

    static void b(Context context, String str) {
        Activity activity;
        al.a("onPageEnd being called! pageName: " + str);
        if (context instanceof Activity) {
            activity = (Activity) context;
            if (str.isEmpty()) {
                str = activity.getLocalClassName();
            }
        } else {
            activity = null;
        }
        if (activity == null || (activity.getChangingConfigurations() & 128) != 128) {
            a(context, str, 7);
        } else {
            d = true;
        }
    }

    static void b(Context context, String str, String str2) {
        if (am.c() != null && am.c().equals("2")) {
            e();
        }
        am.a("0");
        if (!b) {
            a(context, str, str2);
        }
        aw.a(new w(context));
    }

    static void b(Context context, String str, boolean z) {
        am.a("1");
        if (z && f) {
            return;
        }
        aw.a(new x(str, context));
        h.removeMessages(0);
        h.sendEmptyMessageDelayed(0, ad.j);
    }

    static void c(Context context) {
        b(context, "", true);
    }

    static String d(Context context) {
        if (context != null && !b) {
            a(context);
        }
        return ah.a(context);
    }

    private static void d() {
        e.clear();
        e.add(h.a());
        e.add(f.a());
        e.add(g.a());
        e.add(d.a());
    }

    public static void e() {
        a aVar = new a();
        aVar.a.put("apiType", 1);
        aVar.a.put("controller", h.a());
        Message obtain = Message.obtain();
        obtain.what = 102;
        obtain.obj = aVar;
        aj.a().sendMessageDelayed(obtain, 100L);
    }
}
