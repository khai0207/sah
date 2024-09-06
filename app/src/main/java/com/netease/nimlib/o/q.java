package com.netease.nimlib.o;

import android.content.Context;
import android.util.Log;
import com.netease.nimlib.sdk.util.NIMUtil;
import java.lang.Thread;

/* compiled from: NimCrashHandler.java */
/* loaded from: classes.dex */
public class q {
    public static void a(final Context context) {
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.netease.nimlib.o.q.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                try {
                    String stackTraceString = Log.getStackTraceString(th);
                    if (stackTraceString.contains("com.netease") && stackTraceString.contains("nim")) {
                        String str = "************* crash *************\n** Thread: " + context.getPackageName() + "/" + thread.getName() + "   Process: " + NIMUtil.getProcessName(context) + " **";
                        com.netease.nimlib.log.b.e("APP", str, th);
                        w.b();
                        com.netease.nimlib.n.e.a(com.netease.nimlib.n.b.p.kUncaughtException, th.getMessage(), stackTraceString, str);
                    }
                } catch (Throwable unused) {
                }
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = defaultUncaughtExceptionHandler;
                if (uncaughtExceptionHandler != null) {
                    uncaughtExceptionHandler.uncaughtException(thread, th);
                }
            }
        });
    }
}
