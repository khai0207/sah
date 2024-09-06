package com.talkingdata.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.talkingdata.sdk.af;
import com.tendcloud.appcpa.Order;
import com.tendcloud.appcpa.ShoppingCart;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: td */
/* loaded from: classes.dex */
public class zz implements ao {
    public static final int A = 102;
    public static final int B = 103;
    public static final HandlerThread C;
    private static Handler E = null;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    static Context h = null;
    static String i = null;
    static String j = null;
    static String k = null;
    static Handler n = null;
    static final String r = "TDGA";
    static final long s = 300000;
    public static d x = null;
    public static final int y = 9999;
    public static final int z = 101;
    public static boolean a = ac.a;
    public static String b = "CoreLog";
    public static String c = "2.3.8";
    public static boolean l = false;
    static boolean m = false;
    public static AtomicBoolean o = new AtomicBoolean(false);
    static boolean p = false;
    static boolean q = false;
    private static volatile zz D = null;
    public static volatile boolean t = false;

    /* renamed from: u */
    public static volatile boolean f33u = false;
    static boolean v = false;
    public static boolean w = false;

    /* compiled from: td */
    /* loaded from: classes.dex */
    public static class a {
        public HashMap a = new HashMap();
    }

    static {
        E = null;
        HandlerThread handlerThread = new HandlerThread("ProcessingThread");
        C = handlerThread;
        handlerThread.start();
        E = new e(C.getLooper());
    }

    public static Context a() {
        return h;
    }

    public zz() {
        D = this;
    }

    static synchronized zz b() {
        zz zzVar;
        synchronized (zz.class) {
            if (D == null) {
                synchronized (zz.class) {
                    if (D == null) {
                        D = new zz();
                    }
                }
            }
            zzVar = D;
        }
        return zzVar;
    }

    public static Handler c() {
        return E;
    }

    @Override // com.talkingdata.sdk.ao
    public void a(Context context, com.talkingdata.sdk.a aVar) {
        a(context, (String) null, (String) null, aVar);
    }

    @Override // com.talkingdata.sdk.ao
    public void a(Context context, String str, String str2, com.talkingdata.sdk.a aVar) {
        try {
            if (context == null) {
                an.iForDeveloper("Init failed Context is null");
                return;
            }
            if (!bh.b(context, "android.permission.INTERNET")) {
                an.eForDeveloper("[SDKInit] Permission \"android.permission.INTERNET\" is needed.");
                return;
            }
            if (aVar == null) {
                an.eForDeveloper("Failed to initialize!");
                return;
            }
            try {
                if (!t) {
                    ab.f = context.getApplicationContext();
                    a(context);
                    t = true;
                }
                bh.execute(new p(this, str, str2, aVar));
            } catch (Throwable th) {
                an.a("[SDKInit] Failed to initialize!", th);
                ce.postSDKError(th);
            }
        } catch (Throwable th2) {
            ce.postSDKError(th2);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public String b(Context context, com.talkingdata.sdk.a aVar) {
        if (context != null) {
            try {
                if (!t) {
                    a(context, ab.a(context, aVar), ab.b(context, aVar), aVar);
                }
            } catch (Throwable unused) {
                return null;
            }
        }
        return as.a(context);
    }

    @Override // com.talkingdata.sdk.ao
    public Context d() {
        try {
            return ab.f;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.talkingdata.sdk.ao
    public String c(Context context, com.talkingdata.sdk.a aVar) {
        return ab.a(context, aVar);
    }

    @Override // com.talkingdata.sdk.ao
    public String d(Context context, com.talkingdata.sdk.a aVar) {
        return ab.b(context, aVar);
    }

    @Override // com.talkingdata.sdk.ao
    public void a(Activity activity, com.talkingdata.sdk.a aVar) {
        try {
            if (w && ab.z) {
                return;
            }
            ae.a(activity, aVar);
        } catch (Throwable unused) {
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void a(Activity activity, String str, String str2, com.talkingdata.sdk.a aVar) {
        try {
            if (!t) {
                a((Context) activity, str, str2, aVar);
            }
            a(activity);
            a(activity, aVar);
        } catch (Throwable unused) {
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void b(Activity activity, com.talkingdata.sdk.a aVar) {
        try {
            if (t && !w) {
                a(activity);
                ae.b(activity, aVar);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void e() {
        try {
            an.a = false;
        } catch (Throwable unused) {
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, com.talkingdata.sdk.a aVar) {
        try {
            if (TextUtils.isEmpty(str)) {
                an.eForDeveloper("onLogin: account could not be null or empty");
                return;
            }
            an.iForDeveloper("onLogin called --> account is " + str);
            bh.execute(new u(this, aVar, str));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, af.AccountType accountType, String str2, com.talkingdata.sdk.a aVar) {
        try {
            if (TextUtils.isEmpty(str)) {
                an.eForDeveloper("onRegister: account could not be null or empty");
                return;
            }
            an.iForDeveloper("onRegister called --> account is " + str + " ，type is " + accountType + " , name is " + str2);
            bh.execute(new v(this, aVar, str, accountType, str2));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void b(String str, af.AccountType accountType, String str2, com.talkingdata.sdk.a aVar) {
        try {
            if (TextUtils.isEmpty(str)) {
                an.eForDeveloper("onLogin: account could not be null or empty");
                return;
            }
            an.iForDeveloper("onLogin called --> account is " + str + " ，type is " + accountType + " , name is " + str2);
            bh.execute(new w(this, aVar, str, accountType, str2));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, af.AccountType accountType, com.talkingdata.sdk.a aVar) {
        try {
            if (TextUtils.isEmpty(str)) {
                an.eForDeveloper("onLogin: account could not be null or empty");
                return;
            }
            an.iForDeveloper("onLogin called --> account is " + str + " ，type is " + accountType);
            bh.execute(new x(this, aVar, str, accountType));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void b(String str, com.talkingdata.sdk.a aVar) {
        try {
            if (TextUtils.isEmpty(str)) {
                an.eForDeveloper("onRegister: account could not be null or empty");
                return;
            }
            an.iForDeveloper("onRegister called --> account is " + str);
            bh.execute(new y(this, aVar, str));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void b(String str, af.AccountType accountType, com.talkingdata.sdk.a aVar) {
        try {
            if (TextUtils.isEmpty(str)) {
                an.eForDeveloper("onApply: account could not be null or empty");
                return;
            }
            an.iForDeveloper("onApply called --> account is " + str + " ,type is " + accountType);
            bh.execute(new z(this, aVar, str, accountType));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void c(String str, af.AccountType accountType, com.talkingdata.sdk.a aVar) {
        try {
            if (TextUtils.isEmpty(str)) {
                an.eForDeveloper("onActivate: account could not be null or empty");
                return;
            }
            an.iForDeveloper("onActivate called --> account is " + str + " ,type is " + accountType);
            bh.execute(new ad(this, aVar, str, accountType));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void onLogout(com.talkingdata.sdk.a aVar) {
        try {
            an.iForDeveloper("ModuleAccount.logout ");
            bh.execute(new f(this, aVar));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, String str2, String str3, int i2, int i3, com.talkingdata.sdk.a aVar) {
        try {
            an.iForDeveloper("onAddItemToShoppingCart called --> itemId: " + str + " ,category: " + str2 + " ,name: " + str3 + " ,unitPrice: " + i2 + " ,amount: " + i3);
            bh.execute(new g(this, aVar, str, str2, str3, i2, i3));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, String str2, int i2, String str3, String str4, com.talkingdata.sdk.a aVar) {
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    an.iForDeveloper("onOrderPaySucc called --> account: " + str + " ,orderid: " + str2 + " ,amount: " + i2 + " ,currencyType: " + str3 + " ,payType: " + str4);
                    if (str3.trim().length() != 3) {
                        an.eForDeveloper("currencyType length must be 3 likes CNY so so");
                        return;
                    } else {
                        bh.execute(new h(this, aVar, str, str2, i2, str3, str4));
                        return;
                    }
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
                return;
            }
        }
        an.eForDeveloper("onOrderPaySucc: account could not be null or empty");
    }

    @Override // com.talkingdata.sdk.ao
    public void b(String str, String str2, int i2, String str3, String str4, com.talkingdata.sdk.a aVar) {
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    an.iForDeveloper("onPay called --> account: " + str + " ,orderid: " + str2 + " ,amount: " + i2 + " ,currencyType: " + str3 + " ,payType: " + str4);
                    if (str3.trim().length() != 3) {
                        an.eForDeveloper("currencyType length must be 3 likes CNY so so");
                        return;
                    } else {
                        bh.execute(new i(this, aVar, str, str2, i2, str3, str4));
                        return;
                    }
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
                return;
            }
        }
        an.eForDeveloper("onPay: account could not be null or empty");
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, String str2, int i2, String str3, String str4, String str5, int i3, com.talkingdata.sdk.a aVar) {
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    an.iForDeveloper("onPay called --> account: " + str + " ,orderid: " + str2 + " ,amount: " + i2 + " ,currencyType: " + str3 + " ,payType: " + str4 + " ,itemId: " + str5 + " ,itemCount: " + i3);
                    if (str3.trim().length() != 3) {
                        an.eForDeveloper("currencyType length must be 3 likes CNY so so");
                        return;
                    } else {
                        bh.execute(new j(this, aVar, str, str2, i2, str3, str4, str5, i3));
                        return;
                    }
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
                return;
            }
        }
        an.eForDeveloper("onPay: account could not be null or empty");
    }

    @Override // com.talkingdata.sdk.ao
    public void c(String str, com.talkingdata.sdk.a aVar) {
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    an.iForDeveloper("onPay called --> accountID: " + str);
                    bh.execute(new k(this, aVar, str));
                    return;
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
                return;
            }
        }
        an.eForDeveloper("onPay: account could not be null or empty");
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, String str2, int i2, String str3, String str4, Order order, com.talkingdata.sdk.a aVar) {
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    if (order == null) {
                        an.eForDeveloper("onPay: order could not be null");
                        return;
                    }
                    an.iForDeveloper("onPay called --> account: " + str + " ,orderid: " + str2 + " ,amount: " + i2 + " ,currencyType: " + str3 + " ,payType: " + str4 + " ,order: " + order.toString());
                    if (str3.trim().length() != 3) {
                        an.eForDeveloper("currencyType length must be 3 likes CNY so so");
                        return;
                    } else {
                        bh.execute(new l(this, aVar, str, str2, i2, str3, str4, order));
                        return;
                    }
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
                return;
            }
        }
        an.eForDeveloper("onPay: account could not be null or empty");
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, String str2, Order order, com.talkingdata.sdk.a aVar) {
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    if (order != null && !order.optString(Order.keyOrderId).isEmpty()) {
                        an.iForDeveloper("onPay called --> account: " + str + " ,payType: " + str2 + " ,order: " + order.toString());
                        bh.execute(new m(this, aVar, str, order, str2));
                        return;
                    }
                    an.eForDeveloper("onPay: order or orderID could not be null or empty");
                    return;
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
                return;
            }
        }
        an.eForDeveloper("onPay: account could not be null or empty");
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, Order order, com.talkingdata.sdk.a aVar) {
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    if (order != null && !order.optString(Order.keyOrderId).isEmpty()) {
                        an.iForDeveloper("onPlaceOrder called --> account: " + str + " ,order: " + order.toString());
                        bh.execute(new n(this, aVar, str, order));
                        return;
                    }
                    an.eForDeveloper("onPlaceOrder: order or orderID could not be null or empty");
                    return;
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
                return;
            }
        }
        an.eForDeveloper("onPlaceOrder: account could not be null or empty");
    }

    @Override // com.talkingdata.sdk.ao
    public void a(String str, String str2, String str3, int i2, com.talkingdata.sdk.a aVar) {
        try {
            an.iForDeveloper("onViewItem called --> itemId: " + str + " ,category: " + str2 + " ,name: " + str3 + " ,unitPrice: " + i2);
            bh.execute(new o(this, aVar, str, str2, str3, i2));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void a(ShoppingCart shoppingCart, com.talkingdata.sdk.a aVar) {
        try {
            an.iForDeveloper("onViewShoppingCart called --> shoppingCart: " + shoppingCart);
            if (shoppingCart != null && shoppingCart.length() > 0) {
                bh.execute(new q(this, aVar, shoppingCart));
                return;
            }
            an.eForDeveloper("viewShoppingCart# shoppingCart can't be null or empty");
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void d(String str, com.talkingdata.sdk.a aVar) {
        try {
            if (TextUtils.isEmpty(str)) {
                an.eForDeveloper("onReceiveDeepLink: url could not be null or empty");
                return;
            }
            an.iForDeveloper("onReceiveDeepLink --> link: " + str);
            bh.execute(new r(this, str, aVar));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    @Override // com.talkingdata.sdk.ao
    public void e(String str, com.talkingdata.sdk.a aVar) {
        try {
            an.iForDeveloper("createRole called --> roleName: " + str);
            bh.execute(new s(this, str, aVar));
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void a(Context context) {
        if (bh.a(14)) {
            Application application = null;
            try {
                if (ab.f instanceof Activity) {
                    application = ((Activity) ab.f).getApplication();
                } else if (ab.f instanceof Application) {
                    application = (Application) ab.f;
                }
                if (application == null || w) {
                    return;
                }
                Method method = application.getClass().getMethod("registerActivityLifecycleCallbacks", Class.forName("android.app.Application$ActivityLifecycleCallbacks"));
                d dVar = new d();
                x = dVar;
                method.invoke(application, dVar);
                w = true;
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        try {
            bh.a((Class) Class.forName("android.app.ActivityManagerNative"), (bf) new t(this, context), "gDefault", "android.app.IActivityManager");
            w = true;
        } catch (Throwable th) {
            an.eForDeveloper("registerActivityLifecycleListener " + th.getMessage());
        }
    }

    public static boolean f() {
        return o.get();
    }
}
