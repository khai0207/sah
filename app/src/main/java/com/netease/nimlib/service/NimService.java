package com.netease.nimlib.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.netease.nimlib.app.AppForegroundWatcherCompat;
import com.netease.nimlib.c;
import com.netease.nimlib.h;
import com.netease.nimlib.ipc.e;
import com.netease.nimlib.o.aa;
import com.netease.nimlib.o.w;
import com.netease.nimlib.o.x;
import com.netease.nimlib.push.g;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NimService extends Service {
    private static int a = 0;
    private static int b = 3;
    private static a c;

    /* loaded from: classes.dex */
    private interface a {
        void a();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (!c.O()) {
            com.netease.nimlib.b a2 = a();
            if (a2 == null) {
                g.a(this);
                return;
            }
            LoginInfo a3 = a2.a();
            SDKOptions b2 = a2.b();
            if (b2 == null) {
                g.a(this);
                return;
            } else {
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.i("core", "config in nim service");
                }
                c.a(this, a3, b2, true);
            }
        }
        c.a("NimService");
        h.a(true);
        b.b(this);
        com.netease.nimlib.log.b.O("nim service startup");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int i3 = -1;
        if (intent != null) {
            i3 = intent.getIntExtra("EXTRA_FROM", -1);
            if (aa.a()) {
                a(intent.getBooleanExtra("EXTRA_FOREGROUND", false));
            }
        }
        com.netease.nimlib.log.b.O("NimService onStartCommand from#" + i3 + " flags#" + i + " startId#" + i2);
        if (i3 != 2) {
            NimReceiver.a(this);
        }
        if (i3 == 1) {
            e.a("NimService");
        }
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        e.a("NimService");
        return e.a();
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        e.a("NimService");
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        com.netease.nimlib.log.b.O("NimService onDestroy");
        h.a(false);
        super.onDestroy();
        com.netease.nimlib.log.b.c();
    }

    private void a(boolean z) {
        com.netease.nimlib.log.b.O("start NimService onStartForeground " + z);
        if (z) {
            b.a((Service) this);
            com.netease.nimlib.c.b.a.a(this).postDelayed(new Runnable() { // from class: com.netease.nimlib.service.NimService.1
                AnonymousClass1() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    NimService.this.stopForeground(true);
                }
            }, 1000L);
        }
    }

    /* renamed from: com.netease.nimlib.service.NimService$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NimService.this.stopForeground(true);
        }
    }

    public static boolean a(Context context, int i) {
        com.netease.nimlib.log.b.O("start NimService from " + i);
        if (com.netease.nimlib.log.b.a.a()) {
            Log.i("NimService", "start NimService context = " + context);
        }
        if (context == null) {
            return false;
        }
        Intent intent = new Intent(context, (Class<?>) NimService.class);
        intent.putExtra("EXTRA_FROM", i);
        try {
            context.startService(intent);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d(NotificationCompat.CATEGORY_SERVICE, "start NimService error: " + th);
            if (!aa.a() || !c.i().enableForegroundService) {
                return false;
            }
            try {
                if (!c(context)) {
                    return false;
                }
                com.netease.nimlib.log.b.O("startForegroundService NimService from " + i);
                intent.putExtra("EXTRA_FOREGROUND", true);
                context.startForegroundService(intent);
                a = a + 1;
            } catch (Throwable th2) {
                com.netease.nimlib.log.b.d(NotificationCompat.CATEGORY_SERVICE, "startForegroundService NimService error: " + th2);
                return false;
            }
        }
        return true;
    }

    public com.netease.nimlib.b a() {
        try {
            Cursor query = getContentResolver().query(Uri.parse(String.format("content://%s/string/%s/%s", getPackageName() + ".ipc.provider.preference", "PARAMS", "KEY_GET_LOGIN_INFO_AND_SDK_OPTIONS")), null, null, null, null);
            if (query != null && query.moveToFirst()) {
                String string = query.getString(0);
                query.close();
                if (w.a((CharSequence) string)) {
                    return null;
                }
                return com.netease.nimlib.b.a(new JSONObject(string));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    private static boolean c(Context context) {
        com.netease.nimlib.log.b.d("NimService", "startForegroundServiceCount:" + a);
        if (!AppForegroundWatcherCompat.isBackground() || !aa.a(context)) {
            return a < b || AppForegroundWatcherCompat.a();
        }
        com.netease.nimlib.log.b.d("NimService", "android 12 can not startForegroundService in background");
        return false;
    }

    public static Intent a(Context context) {
        Intent intent = new Intent(context, (Class<?>) NimService.class);
        intent.putExtra("EXTRA_FROM", 0);
        return intent;
    }

    public static Intent b(Context context) {
        return new Intent(context, (Class<?>) Aux.class);
    }

    /* loaded from: classes.dex */
    public static final class Aux extends Service implements a {
        private static final Binder a = new Binder();

        @Override // android.app.Service
        public boolean onUnbind(Intent intent) {
            return true;
        }

        @Override // android.app.Service
        public void onCreate() {
            super.onCreate();
            if (c.H()) {
                return;
            }
            c.a("Aux");
            try {
                b.b(this);
                com.netease.nimlib.log.b.O("aux service startup");
            } catch (IllegalStateException unused) {
                x.a(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.app.Service
        public IBinder onBind(Intent intent) {
            if (!c.H()) {
                e.a("AuxService");
            }
            return a;
        }

        @Override // android.app.Service
        public int onStartCommand(Intent intent, int i, int i2) {
            if (c.H()) {
                return super.onStartCommand(intent, i, i2);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("AuxService onStartCommand flags#");
            sb.append(i);
            sb.append(" startId#");
            sb.append(i2);
            sb.append(" sticky=");
            sb.append(!c.s());
            com.netease.nimlib.log.b.O(sb.toString());
            if (c.s()) {
                return 2;
            }
            a unused = NimService.c = this;
            return super.onStartCommand(intent, i, i2);
        }

        @Override // android.app.Service
        public void onRebind(Intent intent) {
            if (!c.H()) {
                e.a("AuxService");
            }
            super.onRebind(intent);
        }

        @Override // android.app.Service
        public void onDestroy() {
            super.onDestroy();
            com.netease.nimlib.log.b.O("NimService onDestroy");
            if (c.H()) {
                return;
            }
            a unused = NimService.c = null;
            com.netease.nimlib.log.b.c();
        }

        @Override // com.netease.nimlib.service.NimService.a
        public void a() {
            stopSelf();
        }
    }

    public static void b() {
        a aVar = c;
        if (aVar != null) {
            aVar.a();
            com.netease.nimlib.log.b.O("quit sticky service!");
        }
    }
}
