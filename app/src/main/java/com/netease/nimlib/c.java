package com.netease.nimlib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.nimlib.app.AppForegroundWatcherCompat;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.i.i;
import com.netease.nimlib.o.p;
import com.netease.nimlib.o.q;
import com.netease.nimlib.o.w;
import com.netease.nimlib.plugin.interact.IChatRoomInteract;
import com.netease.nimlib.sdk.FcsDownloadAuthStrategy;
import com.netease.nimlib.sdk.NimStrings;
import com.netease.nimlib.sdk.NosTokenSceneConfig;
import com.netease.nimlib.sdk.NotificationFoldStyle;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.ServerAddresses;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.misc.model.NosConfig;
import com.netease.nimlib.sdk.msg.model.CaptureDeviceInfoConfig;
import com.netease.nimlib.sdk.sync.SyncConfig;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.netease.nimlib.sdk.util.NIMUtil;
import com.netease.nimlib.user.UserInfoDBHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: SDKCache.java */
/* loaded from: classes.dex */
public class c {
    private static c w = null;
    private static volatile boolean x = false;
    private static volatile boolean y = true;
    private UserInfoProvider A;
    private NosConfig B;
    private com.netease.nimlib.network.a F;
    private Context a;
    private LoginInfo b;
    private Integer c;
    private SDKOptions d;
    private g e;
    private ServerAddresses f;
    private i g;
    private String h;
    private String i;
    private String j;
    private String k;
    private NimStrings l;
    private String o;
    private String p;
    private LoginInfo r;
    private CountDownLatch t;

    /* renamed from: u */
    private boolean f22u;
    private long v;
    private boolean m = true;
    private boolean n = false;
    private boolean q = false;
    private boolean s = false;
    private boolean z = true;
    private boolean C = false;
    private final AtomicBoolean D = new AtomicBoolean(false);
    private final Set<String> E = new HashSet();
    private Set<a> G = new HashSet();

    /* compiled from: SDKCache.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(boolean z);
    }

    public static boolean a(int i) {
        return i == 1 || i == 2;
    }

    private c() {
    }

    public static void a(Context context, LoginInfo loginInfo, SDKOptions sDKOptions) {
        a(context, loginInfo, sDKOptions, false);
    }

    public static void a(Context context, LoginInfo loginInfo, SDKOptions sDKOptions, boolean z) {
        ServerAddresses a2;
        if (x) {
            com.netease.nimlib.log.b.c("SDKCache", "config again, ignore config()");
            return;
        }
        w = new c();
        y = z;
        w.a = context.getApplicationContext();
        if (w.a == null) {
            NullPointerException nullPointerException = new NullPointerException("config context is null");
            nullPointerException.printStackTrace();
            if (com.netease.nimlib.log.b.a.a()) {
                Log.e("SDKCache", "config context is null", nullPointerException);
            }
        }
        c cVar = w;
        cVar.d = sDKOptions;
        cVar.e = g.c();
        c cVar2 = w;
        cVar2.b = loginInfo;
        cVar2.f22u = loginInfo == null && i().reducedIM;
        w.v = System.currentTimeMillis();
        c cVar3 = w;
        if (cVar3.f22u) {
            cVar3.d.improveSDKProcessPriority = false;
        }
        if (sDKOptions != null) {
            if (sDKOptions.serverConfig != null) {
                a(sDKOptions.serverConfig);
            } else if (sDKOptions.useAssetServerAddressConfig && (a2 = com.netease.nimlib.d.a.a()) != null) {
                a(a2);
                if (!TextUtils.isEmpty(com.netease.nimlib.d.a.b())) {
                    w.d.appKey = com.netease.nimlib.d.a.b();
                }
            }
        }
        j(context);
        c(loginInfo);
    }

    public static void a() {
        if (x) {
            return;
        }
        synchronized (c.class) {
            if (x) {
                return;
            }
            x = true;
            if (w == null) {
                throw new IllegalStateException("SDK should be config on Application#onCreate()!");
            }
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("SDK should be inited on main looper!");
            }
            h.a(2);
            c cVar = w;
            a(cVar.a, cVar.d);
            e(w.a);
        }
    }

    public static void a(String str) {
        if (x) {
            return;
        }
        synchronized (c.class) {
            if (x) {
                return;
            }
            x = true;
            if (w == null) {
                throw new IllegalStateException("SDK should be config on Application#onCreate()!");
            }
            h.a(1);
            c cVar = w;
            a(cVar.a, cVar.d);
            a(w.a, str);
        }
    }

    private static void a(Context context, SDKOptions sDKOptions) {
        q.a(context);
        com.netease.nimlib.o.b.c.a(context, sDKOptions == null ? null : sDKOptions.sdkStorageRootPath);
        d.a(sDKOptions != null && sDKOptions.useXLog);
        com.netease.nimlib.a.a(context, w.j);
        d(context);
        com.netease.nimlib.c.b.a.c().b().post(new Runnable() { // from class: com.netease.nimlib.-$$Lambda$c$-Bo-UJgoSQq3fU7EtLaKvQyr8Vo
            private final /* synthetic */ Context f$0;

            public /* synthetic */ $$Lambda$c$BoUJgoSQq3fU7EtLaKvQyr8Vo(Context context2) {
                r1 = context2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                c.k(r1);
            }
        });
    }

    public static /* synthetic */ void k(Context context) {
        com.netease.nimlib.abtest.c.a().a(context);
        com.netease.nimlib.biz.b.a(context);
    }

    private static void d(Context context) {
        try {
            com.netease.nimlib.apm.a.a(new com.netease.nimlib.n.h());
            if (NIMUtil.isMainProcess(context)) {
                com.netease.nimlib.apm.a.a(context);
                HashMap hashMap = new HashMap();
                hashMap.put("Content-Type", "application/json");
                hashMap.put("sdktype", "IM");
                hashMap.put("Content-Encoding", "gzip");
                HashMap hashMap2 = new HashMap();
                hashMap2.put(com.alipay.sdk.m.h.b.h, g());
                hashMap2.put("sdk_ver", "9.17.0");
                hashMap2.put("env", com.netease.nimlib.d.e.a() ? "test" : "online");
                hashMap2.put("bundle_id", context.getPackageName());
                hashMap2.put("platform", "AOS");
                hashMap2.put("dev_id", com.netease.nimlib.push.b.c());
                hashMap2.put("model", com.netease.nimlib.p.a.b());
                hashMap2.put("os_name", "Android");
                hashMap2.put("os_ver", Integer.valueOf(Build.VERSION.SDK_INT));
                hashMap2.put("manufactor", com.netease.nimlib.p.a.a());
                hashMap2.put("net_type", p.i(context));
                hashMap2.put("net_carrier", p.l(context));
                com.netease.nimlib.log.b.G("initNimEventReporter ,commonData = " + hashMap2);
                com.netease.nimlib.apm.a.a(hashMap, hashMap2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            com.netease.nimlib.log.b.G("initNimEventReporter Exception = " + th);
        }
    }

    private static void e(Context context) {
        com.netease.nimlib.log.b.N("********** SDK UI Process Start **** Version: 9.17.0/91700/1/9283ce1120 **** APPKEY: " + g() + "/" + n() + " **** BUILD Version:" + Build.VERSION.SDK_INT + "/" + context.getApplicationInfo().targetSdkVersion + "/" + com.netease.nimlib.p.a.a() + "/" + com.netease.nimlib.p.a.b() + " **** reduced IM:" + s() + " **********");
        f(context);
        if (i().asyncInitSDK) {
            com.netease.nimlib.log.b.N("async init SDK...");
            R().t = new CountDownLatch(1);
            com.netease.nimlib.c.b.a.c().b().post(new Runnable() { // from class: com.netease.nimlib.c.1
                final /* synthetic */ Context a;

                AnonymousClass1(Context context2) {
                    r1 = context2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    c.h(r1);
                    c.R().t.countDown();
                    com.netease.nimlib.log.b.N("async init SDK done!");
                }
            });
            return;
        }
        h(context2);
    }

    /* compiled from: SDKCache.java */
    /* renamed from: com.netease.nimlib.c$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context2) {
            r1 = context2;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.h(r1);
            c.R().t.countDown();
            com.netease.nimlib.log.b.N("async init SDK done!");
        }
    }

    private static void f(Context context) {
        com.netease.nimlib.plugin.c.a().a(context);
        com.netease.nimlib.i.a.a(context);
        w.g = new i();
        AppForegroundWatcherCompat.a(context);
        w.g(context);
    }

    private void g(Context context) {
        com.netease.nimlib.network.h.a().a(context);
        if (this.F == null) {
            this.F = new com.netease.nimlib.network.a() { // from class: com.netease.nimlib.c.2
                AnonymousClass2() {
                }

                @Override // com.netease.nimlib.network.a
                public void onNetworkChanged(boolean z, com.netease.nimlib.network.a.a aVar) {
                    com.netease.nimlib.log.b.G("initNetworkListener onNetworkChanged,isConnected = " + z + ",networkStatus = " + aVar.a());
                    HashMap hashMap = new HashMap();
                    hashMap.put("net_type", p.i(c.e()));
                    hashMap.put("net_carrier", p.l(c.e()));
                    com.netease.nimlib.log.b.G("initNetworkListener onNetworkChanged,commonData = " + hashMap);
                    com.netease.nimlib.apm.a.a(hashMap);
                }
            };
        }
        com.netease.nimlib.network.h.a().a(this.F);
    }

    /* compiled from: SDKCache.java */
    /* renamed from: com.netease.nimlib.c$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements com.netease.nimlib.network.a {
        AnonymousClass2() {
        }

        @Override // com.netease.nimlib.network.a
        public void onNetworkChanged(boolean z, com.netease.nimlib.network.a.a aVar) {
            com.netease.nimlib.log.b.G("initNetworkListener onNetworkChanged,isConnected = " + z + ",networkStatus = " + aVar.a());
            HashMap hashMap = new HashMap();
            hashMap.put("net_type", p.i(c.e()));
            hashMap.put("net_carrier", p.l(c.e()));
            com.netease.nimlib.log.b.G("initNetworkListener onNetworkChanged,commonData = " + hashMap);
            com.netease.nimlib.apm.a.a(hashMap);
        }
    }

    public static void h(Context context) {
        com.netease.nimlib.log.b.d("SDKCache", "initMainProcess1,thread = " + Thread.currentThread());
        com.netease.nimlib.biz.i.a().e();
        com.netease.nimlib.plugin.c.a().b(context);
        com.netease.nimlib.c.b.a.c().a().postDelayed(new Runnable() { // from class: com.netease.nimlib.c.3
            final /* synthetic */ Context a;

            AnonymousClass3(Context context2) {
                r1 = context2;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.i(r1);
            }
        }, 500L);
        R().s = true;
        R().c();
        com.netease.nimlib.i.b.d(true);
        com.netease.nimlib.log.b.N("main process init done!");
        if (i().checkManifestConfig) {
            e.a(context2);
        }
        e.a(context2, i().checkManifestConfig);
    }

    /* compiled from: SDKCache.java */
    /* renamed from: com.netease.nimlib.c$3 */
    /* loaded from: classes.dex */
    static class AnonymousClass3 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass3(Context context2) {
            r1 = context2;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.i(r1);
        }
    }

    public static void i(Context context) {
        if (i().preLoadServers) {
            com.netease.nimlib.net.a.b.a.c.a().b();
        }
    }

    private static void a(Context context, String str) {
        w.p = UUID.randomUUID().toString();
        com.netease.nimlib.log.b.O("********** SDK Push Process Start **** sessionId:" + r() + " **** reduced IM:" + s() + " **** from:" + str + " ************");
        com.netease.nimlib.plugin.c.a().c(context);
        com.netease.nimlib.plugin.c.a().b(context);
        LoginInfo m = m();
        if ((m == null || !m.valid()) && i().preLoadServers) {
            com.netease.nimlib.log.b.O("fetch LBS on SDK init...");
            com.netease.nimlib.push.net.lbs.c.a().g();
        }
        com.netease.nimlib.network.g.a().a(context);
        com.netease.nimlib.push.f.l().a(context);
    }

    public static boolean b() {
        return R().s;
    }

    public static void a(a aVar) {
        synchronized (R()) {
            R().G.add(aVar);
        }
    }

    public static void b(a aVar) {
        synchronized (R()) {
            R().G.remove(aVar);
        }
    }

    public void c() {
        synchronized (R()) {
            Iterator it = new ArrayList(this.G).iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(b());
            }
        }
    }

    public static void d() {
        if (R().s) {
            return;
        }
        try {
            com.netease.nimlib.log.b.a("await SDK init ready...");
            long currentTimeMillis = System.currentTimeMillis();
            R().t.await(200L, TimeUnit.MILLISECONDS);
            com.netease.nimlib.log.b.a("release waiting! SDK ready! wait time=" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Throwable th) {
            th.printStackTrace();
            com.netease.nimlib.log.b.a("await SDK ready error", th);
        }
    }

    public static <T> T a(Class<T> cls) {
        i iVar;
        c cVar = w;
        if (cVar == null || (iVar = cVar.g) == null) {
            throw new IllegalStateException("SDK not initialized or invoked in wrong process!");
        }
        return (T) iVar.a(cls);
    }

    public static Context e() {
        c cVar = w;
        if (cVar == null) {
            if (com.netease.nimlib.log.b.a.a()) {
                Log.e("SDKCache", "getContext instance is null");
            }
            return null;
        }
        Context context = cVar.a;
        if (context != null) {
            return context;
        }
        NullPointerException nullPointerException = new NullPointerException("getContext instance.context is null");
        nullPointerException.printStackTrace();
        if (com.netease.nimlib.log.b.a.a()) {
            Log.e("SDKCache", "getContext instance.context is null", nullPointerException);
        }
        return null;
    }

    public static String f() {
        return R().h;
    }

    private static void c(LoginInfo loginInfo) {
        if (loginInfo == null || TextUtils.isEmpty(loginInfo.getAppKey())) {
            return;
        }
        R().j = loginInfo.getAppKey();
    }

    public static String g() {
        return R().j;
    }

    public static String h() {
        return R().k;
    }

    private static void j(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (TextUtils.isEmpty(i().appKey) && applicationInfo.metaData != null) {
                w.j = applicationInfo.metaData.getString("com.netease.nim.appKey");
            } else {
                w.j = i().appKey;
            }
            if (TextUtils.isEmpty(i().flutterSdkVersion) && applicationInfo.metaData != null) {
                w.k = applicationInfo.metaData.getString("com.netease.nim.flutterSdkVersion");
            } else {
                w.k = i().flutterSdkVersion;
            }
            w.h = applicationInfo.packageName;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            if (TextUtils.isEmpty(w.i)) {
                w.i = a(context);
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static String a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            try {
                return packageManager.getApplicationLabel(applicationInfo).toString();
            } catch (Throwable unused) {
                com.netease.nimlib.log.b.d("SDKCache", "failed to read app name from application label, to try other ways");
                if (applicationInfo.labelRes > 0) {
                    try {
                        return context.getString(applicationInfo.labelRes);
                    } catch (Throwable unused2) {
                        return String.valueOf(applicationInfo.nonLocalizedLabel);
                    }
                }
                return String.valueOf(applicationInfo.nonLocalizedLabel);
            }
        } catch (Throwable unused3) {
            com.netease.nimlib.log.b.d("SDKCache", "read app name error, failed to get application info" + packageManager);
            return null;
        }
    }

    public static SDKOptions i() {
        return R().d == null ? SDKOptions.DEFAULT : w.d;
    }

    public static SDKOptions j() {
        if (w == null) {
            return null;
        }
        return i();
    }

    public static g k() {
        return w.e;
    }

    public static ServerAddresses l() {
        return R().f;
    }

    private static void a(ServerAddresses serverAddresses) {
        R().f = serverAddresses;
        com.netease.nimlib.net.a.b.d.a.a = serverAddresses.nosSupportHttps;
        if (com.netease.nimlib.log.b.a.a()) {
            Log.i("SDKCache", "config server address " + w.a(serverAddresses));
        }
    }

    public static LoginInfo m() {
        c cVar = w;
        if (cVar == null) {
            return null;
        }
        return cVar.b;
    }

    public static void a(LoginInfo loginInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("set login info, ");
        sb.append(loginInfo == null ? Constants.NULL_VERSION_ID : String.format("account=%s, appKey=%s", loginInfo.getAccount(), loginInfo.getAppKey()));
        com.netease.nimlib.log.b.d("SDKCache", sb.toString());
        R().b = loginInfo;
        c(loginInfo);
    }

    public static void b(LoginInfo loginInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("set independent login info, ");
        sb.append(loginInfo == null ? Constants.NULL_VERSION_ID : String.format("account=%s, appKey=%s", loginInfo.getAccount(), loginInfo.getAppKey()));
        com.netease.nimlib.log.b.d("SDKCache", sb.toString());
        R().r = loginInfo;
    }

    public static String n() {
        LoginInfo loginInfo;
        c cVar = w;
        if (cVar == null || (loginInfo = cVar.b) == null) {
            return null;
        }
        return loginInfo.getAccount();
    }

    public static String o() {
        c cVar = w;
        if (cVar == null) {
            return null;
        }
        LoginInfo loginInfo = cVar.r;
        if (loginInfo != null) {
            return loginInfo.getAccount();
        }
        LoginInfo loginInfo2 = cVar.b;
        if (loginInfo2 == null) {
            return null;
        }
        return loginInfo2.getAccount();
    }

    public static String p() {
        return R().o;
    }

    public static void b(String str) {
        R().o = str;
    }

    public static String q() {
        return l.h();
    }

    public static String r() {
        if (TextUtils.isEmpty(R().p)) {
            R().p = UUID.randomUUID().toString();
        }
        return R().p;
    }

    public static void c(String str) {
        R().p = str;
        com.netease.nimlib.log.b.N("UI save sessionId from Push, sessionId=" + str);
    }

    public static boolean s() {
        return R().f22u;
    }

    public static boolean t() {
        return R().C;
    }

    public static Integer u() {
        return R().c;
    }

    public static void a(Integer num) {
        R().c = num;
    }

    public static boolean v() {
        c cVar = w;
        return cVar != null && cVar.m;
    }

    private static void e(boolean z) {
        R().m = z;
    }

    public static void a(boolean z) {
        e(z);
    }

    public static boolean w() {
        c cVar = w;
        return cVar != null && cVar.z;
    }

    public static void b(boolean z) {
        R().z = z;
    }

    public static void a(StatusBarNotificationConfig statusBarNotificationConfig) {
        if (statusBarNotificationConfig == null) {
            return;
        }
        statusBarNotificationConfig.notificationFoldStyle = statusBarNotificationConfig.notificationFoldStyle == null ? NotificationFoldStyle.ALL : statusBarNotificationConfig.notificationFoldStyle;
        i().statusBarNotificationConfig = statusBarNotificationConfig;
        com.netease.nimlib.l.d.a(statusBarNotificationConfig.downTimeBegin, statusBarNotificationConfig.downTimeEnd);
        com.netease.nimlib.l.d.a(statusBarNotificationConfig.notificationFoldStyle);
    }

    public static boolean x() {
        return R().n;
    }

    public static void y() {
        R().n = UserInfoDBHelper.queryUserInfo(n()) != null;
    }

    public static void c(boolean z) {
        R().q = z;
    }

    public static boolean z() {
        return R().q;
    }

    public static NosConfig A() {
        return R().B;
    }

    public static void a(NosConfig nosConfig) {
        R().B = nosConfig.isValid() ? nosConfig : null;
        com.netease.nimlib.log.b.d("SDKCache", "update nos download config: " + nosConfig);
    }

    public static void a(NimStrings nimStrings) {
        R().l = nimStrings;
    }

    public static NimStrings B() {
        return R().l == null ? NimStrings.DEFAULT : w.l;
    }

    public static NosTokenSceneConfig C() {
        NosTokenSceneConfig nosTokenSceneConfig = i().mNosTokenSceneConfig;
        return nosTokenSceneConfig != null ? nosTokenSceneConfig : NosTokenSceneConfig.defaultConfig();
    }

    public static boolean D() {
        return i().enableFcs;
    }

    public static FcsDownloadAuthStrategy E() {
        return i().fcsDownloadAuthStrategy;
    }

    public static void a(NosTokenSceneConfig nosTokenSceneConfig) {
        if (nosTokenSceneConfig == null) {
            return;
        }
        i().mNosTokenSceneConfig = nosTokenSceneConfig;
        com.netease.nimlib.net.a.b.a.a().d();
    }

    public static void a(CaptureDeviceInfoConfig captureDeviceInfoConfig) {
        if (captureDeviceInfoConfig == null) {
            return;
        }
        i().captureDeviceInfoConfig = captureDeviceInfoConfig;
        if (NIMUtil.isMainProcess(e())) {
            com.netease.nimlib.biz.i.a().a(captureDeviceInfoConfig);
        }
    }

    public static String d(String str) {
        IChatRoomInteract iChatRoomInteract;
        if (TextUtils.isEmpty(str) || (iChatRoomInteract = (IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class)) == null) {
            return null;
        }
        return iChatRoomInteract.getAppKeyByRoomId(str);
    }

    public static UserInfoProvider F() {
        return R().A;
    }

    public static void a(UserInfoProvider userInfoProvider) {
        R().A = userInfoProvider;
    }

    public static boolean G() {
        SDKOptions sDKOptions = w.d;
        return sDKOptions != null && sDKOptions.disableAwake;
    }

    public static boolean H() {
        return y;
    }

    public static void d(boolean z) {
        R().D.set(z);
    }

    public static boolean I() {
        return R().D.get();
    }

    public static boolean a(int i, String str, LoginInfo loginInfo) {
        if (loginInfo == null || !loginInfo.valid()) {
            return false;
        }
        return b(i, str, loginInfo.getAccount());
    }

    private static boolean b(int i, String str, String str2) {
        if (w.a((CharSequence) str) || w.a((CharSequence) str2)) {
            return false;
        }
        return R().E.add(c(i, str, str2));
    }

    public static void J() {
        R().E.clear();
    }

    public static boolean a(int i, String str, String str2) {
        return R().E.contains(c(i, str, str2));
    }

    private static String c(int i, String str, String str2) {
        return i + "_" + str + "_" + str2;
    }

    public static boolean K() {
        SyncConfig syncConfig = i().syncConfig;
        return syncConfig == null || syncConfig.isEnableSyncTeamMember();
    }

    public static boolean L() {
        SyncConfig syncConfig = i().syncConfig;
        return syncConfig == null || syncConfig.isEnableSyncSuperTeamMember();
    }

    public static boolean M() {
        SyncConfig syncConfig = i().syncConfig;
        return syncConfig == null || syncConfig.isEnableSyncTeamMemberUserInfo();
    }

    public static boolean N() {
        SyncConfig syncConfig = i().syncConfig;
        return syncConfig == null || syncConfig.isEnableSyncSuperTeamMemberUserInfo();
    }

    public static c R() {
        c cVar = w;
        if (cVar != null) {
            return cVar;
        }
        throw new IllegalStateException("SDK not initialized, call NimClient.init() first!");
    }

    public static boolean O() {
        return w != null;
    }

    public static boolean P() {
        return TextUtils.equals(g(), "45c6af3c98409b18a84451215d0bdd6e") || TextUtils.equals(g(), "fe416640c8e8a72734219e1847ad2547") || TextUtils.equals(g(), "c9dd38f1bc660ce9440d44a6876d3f5d");
    }
}
