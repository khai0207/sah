package com.netease.yunxin.artemis.Artemis;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.MessageQueue;
import com.netease.yunxin.artemis.ArtemisTask.YXArtemisPullTask;
import com.netease.yunxin.artemis.a.e;

/* compiled from: YXArtemisEngine.java */
/* loaded from: classes.dex */
public final class a extends HandlerThread {
    private static a a;
    private static Handler b;
    private static Handler c;

    private a(String str, Context context) {
        super(str);
        c = new Handler(context.getMainLooper());
        a(YXArtemisPullTask.mIdlePullTask);
    }

    public static void a(b bVar) {
        Handler handler;
        if (bVar == null || (handler = b) == null) {
            return;
        }
        handler.postDelayed(bVar, bVar.getDelay());
        Looper.myLooper();
        Looper.getMainLooper();
    }

    public static void a() {
        Handler handler = b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            Looper.myLooper();
            Looper.getMainLooper();
        }
    }

    public static a a(Context context) {
        a aVar = a;
        if (aVar != null && aVar.isAlive()) {
            return a;
        }
        a aVar2 = new a("YXArtemisEngine", context);
        a = aVar2;
        aVar2.setUncaughtExceptionHandler(new e());
        a.start();
        b = new Handler(a.getLooper());
        return a;
    }

    /* compiled from: YXArtemisEngine.java */
    /* renamed from: com.netease.yunxin.artemis.Artemis.a$1 */
    /* loaded from: classes.dex */
    final class AnonymousClass1 implements Runnable {
        final /* synthetic */ MessageQueue.IdleHandler a;

        AnonymousClass1(MessageQueue.IdleHandler idleHandler) {
            r2 = idleHandler;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Looper.myQueue().addIdleHandler(r2);
        }
    }

    public final void a(MessageQueue.IdleHandler idleHandler) {
        Handler handler = c;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.netease.yunxin.artemis.Artemis.a.1
                final /* synthetic */ MessageQueue.IdleHandler a;

                AnonymousClass1(MessageQueue.IdleHandler idleHandler2) {
                    r2 = idleHandler2;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    Looper.myQueue().addIdleHandler(r2);
                }
            });
        }
    }

    public static boolean a(Runnable runnable) {
        if (b == null || Build.VERSION.SDK_INT < 29) {
            return false;
        }
        return b.hasCallbacks(runnable);
    }
}
