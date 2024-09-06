package com.talkingdata.sdk;

import android.app.Activity;
import android.content.Context;
import com.talkingdata.sdk.af;
import com.tendcloud.appcpa.Order;
import com.tendcloud.appcpa.ShoppingCart;

/* compiled from: td */
/* loaded from: classes.dex */
public final class ac {
    public static boolean a = true;
    private static ao b;

    public static synchronized void a(Context context, a aVar) {
        synchronized (ac.class) {
            try {
                e(context, aVar);
                b.a(context, aVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static synchronized void a(Context context, String str, String str2, a aVar) {
        synchronized (ac.class) {
            try {
                e(context, aVar);
                b.a(context, str, str2, aVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static String b(Context context, a aVar) {
        return ab.a(context, aVar);
    }

    public static String c(Context context, a aVar) {
        return ab.b(context, aVar);
    }

    public static boolean a() {
        return zz.t;
    }

    public static synchronized String d(Context context, a aVar) {
        String b2;
        synchronized (ac.class) {
            try {
                e(context, aVar);
                b2 = b.b(context, aVar);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
        return b2;
    }

    public static final Context b() {
        return b.d();
    }

    public static void setVerboseLogDisable(a aVar) {
        try {
            an.a = false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Activity activity, String str, String str2, a aVar) {
        try {
            e(activity, aVar);
            b.a(activity, str, str2, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Activity activity, a aVar) {
        try {
            e(activity, aVar);
            b.a(activity, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(Activity activity, a aVar) {
        try {
            e(activity, aVar);
            b.b(activity, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static int a(Context context) {
        return au.b(context);
    }

    private static synchronized void e(Context context, a aVar) {
        synchronized (ac.class) {
            if (context != null) {
                ab.f = context.getApplicationContext();
            }
            if (ab.f == null) {
                an.eForDeveloper("Init failed Context is null ");
                return;
            }
            if (b == null) {
                System.currentTimeMillis();
                b = zz.b();
            }
        }
    }

    public static void a(String str, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, af.AccountType accountType, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, accountType, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, af.AccountType accountType, String str2, a aVar) {
        try {
            e(ab.f, aVar);
            b.b(str, accountType, str2, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(String str, a aVar) {
        try {
            e(ab.f, aVar);
            b.b(str, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(String str, af.AccountType accountType, String str2, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, accountType, str2, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(String str, af.AccountType accountType, a aVar) {
        try {
            e(ab.f, aVar);
            b.b(str, accountType, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void c(String str, af.AccountType accountType, a aVar) {
        try {
            e(ab.f, aVar);
            b.c(str, accountType, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void c(String str, a aVar) {
        try {
            e(ab.f, aVar);
            b.e(str, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, String str2, int i, String str3, String str4, a aVar) {
        try {
            e(ab.f, aVar);
            b.b(str, str2, i, str3, str4, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, String str2, Order order, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, str2, order, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, String str2, int i, String str3, String str4, Order order, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, str2, i, str3, str4, order, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, String str2, int i, String str3, String str4, String str5, int i2, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, str2, i, str3, str4, str5, i2, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void d(String str, a aVar) {
        try {
            e(ab.f, aVar);
            b.c(str, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, Order order, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, order, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(String str, String str2, int i, String str3, String str4, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, str2, i, str3, str4, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, String str2, String str3, int i, int i2, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, str2, str3, i, i2, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, String str2, String str3, int i, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(str, str2, str3, i, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(ShoppingCart shoppingCart, a aVar) {
        try {
            e(ab.f, aVar);
            b.a(shoppingCart, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void e(String str, a aVar) {
        try {
            e(ab.f, aVar);
            b.d(str, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
