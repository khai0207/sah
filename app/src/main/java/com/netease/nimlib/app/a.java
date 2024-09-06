package com.netease.nimlib.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import com.netease.nimlib.app.AppForegroundWatcherCompat;
import com.netease.nimlib.log.b;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: AppForegroundWatcher.java */
/* loaded from: classes.dex */
class a implements Application.ActivityLifecycleCallbacks {
    private static a a;
    private Runnable e;
    private boolean b = false;
    private boolean c = true;
    private Handler d = new Handler();
    private long f = 0;
    private long g = 0;
    private List<AppForegroundWatcherCompat.a> h = new CopyOnWriteArrayList();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    a() {
    }

    public static void a(Context context) {
        if (Build.VERSION.SDK_INT >= 14 && (context instanceof Application) && a == null) {
            a aVar = new a();
            a = aVar;
            ((Application) context).registerActivityLifecycleCallbacks(aVar);
            b.c("AppForegroundWatcher", "app register activity lifecycle callbacks success");
            return;
        }
        b.f("AppForegroundWatcher", "app can not register activity lifecycle callbacks, sdk version=" + Build.VERSION.SDK_INT);
    }

    public static void a(AppForegroundWatcherCompat.a aVar) {
        if (c() || aVar == null || a.h.contains(aVar)) {
            return;
        }
        a.h.add(aVar);
        b.c("AppForegroundWatcher", "add AppForegroundObserver");
    }

    public static void b(AppForegroundWatcherCompat.a aVar) {
        if (c() || aVar == null) {
            return;
        }
        a.h.remove(aVar);
        b.c("AppForegroundWatcher", "remove AppForegroundObserver");
    }

    public static boolean a() {
        if (c()) {
            return false;
        }
        return a.b;
    }

    public static boolean b() {
        if (c()) {
            return false;
        }
        return !a.b;
    }

    public static long a(boolean z) {
        if (c()) {
            return 0L;
        }
        long j = a.f;
        if (j == 0) {
            return 0L;
        }
        return com.netease.nimlib.n.f.a.b(z, j);
    }

    public static long b(boolean z) {
        if (c()) {
            return 0L;
        }
        long j = a.g;
        if (j == 0) {
            return 0L;
        }
        return com.netease.nimlib.n.f.a.b(z, j);
    }

    private static boolean c() {
        return a == null;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.c = false;
        boolean z = !this.b;
        this.b = true;
        Runnable runnable = this.e;
        if (runnable != null) {
            this.d.removeCallbacks(runnable);
        }
        if (z) {
            this.f = SystemClock.elapsedRealtime();
            b.d("AppForegroundWatcher", "app on foreground");
            Iterator<AppForegroundWatcherCompat.a> it = this.h.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a();
                } catch (Exception e) {
                    b.e("AppForegroundWatcher", "AppForegroundObserver threw exception!", e);
                }
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.c = true;
        Runnable runnable = this.e;
        if (runnable != null) {
            this.d.removeCallbacks(runnable);
        } else {
            this.e = new Runnable() { // from class: com.netease.nimlib.app.a.1
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.b && a.this.c) {
                        a.this.b = false;
                        a.this.g = SystemClock.elapsedRealtime();
                        b.d("AppForegroundWatcher", "app in background");
                        Iterator it = a.this.h.iterator();
                        while (it.hasNext()) {
                            try {
                                ((AppForegroundWatcherCompat.a) it.next()).b();
                            } catch (Exception e) {
                                b.e("AppForegroundWatcher", "AppForegroundObserver threw exception!", e);
                            }
                        }
                    }
                }
            };
        }
        this.d.postDelayed(this.e, 500L);
    }
}
