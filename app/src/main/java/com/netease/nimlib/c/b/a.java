package com.netease.nimlib.c.b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;

/* compiled from: Handlers.java */
/* loaded from: classes.dex */
public final class a {
    private static a b;
    private static Handler c;
    private final HashMap<String, HandlerThread> a = new HashMap<>();

    public static Handler a(Context context) {
        Handler handler = c;
        if (handler != null) {
            return handler;
        }
        if (context != null) {
            return new Handler(context.getMainLooper());
        }
        return new Handler(Looper.getMainLooper());
    }

    public final Handler a() {
        return a("DEFAULT");
    }

    public final Handler b() {
        return a("MISC");
    }

    public static Handler b(Context context) {
        if (context != null) {
            return new Handler(context.getMainLooper());
        }
        return new Handler(Looper.getMainLooper());
    }

    public final Handler a(String str) {
        return new Handler(c(str).getLooper());
    }

    public final void b(String str) {
        synchronized (this.a) {
            HandlerThread remove = this.a.remove(str);
            if (remove == null) {
                return;
            }
            Looper looper = remove.getLooper();
            if (looper == null) {
                return;
            }
            try {
                looper.quit();
            } catch (Exception unused) {
            }
        }
    }

    private HandlerThread c(String str) {
        HandlerThread handlerThread;
        synchronized (this.a) {
            handlerThread = this.a.get(str);
            if (handlerThread != null && handlerThread.getLooper() == null) {
                this.a.remove(str);
                handlerThread = null;
            }
            if (handlerThread == null) {
                handlerThread = new HandlerThread(d(str));
                handlerThread.start();
                this.a.put(str, handlerThread);
            }
        }
        return handlerThread;
    }

    private static String d(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("NIM-HT-");
        if (TextUtils.isEmpty(str)) {
            str = "DEFAULT";
        }
        sb.append(str);
        return sb.toString();
    }

    private a() {
    }

    public static synchronized a c() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }
}
