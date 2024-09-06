package com.talkingdata.sdk;

import android.util.Log;

/* compiled from: td */
/* loaded from: classes.dex */
public class an {
    public static boolean a = true;

    public static void dForInternal(String... strArr) {
    }

    public static void eForInternal(Throwable th) {
    }

    public static void eForInternal(String... strArr) {
    }

    public static void iForInternal(String... strArr) {
    }

    public static void iForDeveloper(String str) {
        if (a) {
            a(str, 4);
        }
    }

    public static void dForDeveloper(String str) {
        if (a) {
            a(str, 3);
        }
    }

    public static void eForDeveloper(String str) {
        if (a) {
            a(str, 6);
        }
    }

    public static void a(String str, Throwable th) {
        if (a) {
            a(str, 4);
            th.printStackTrace();
        }
    }

    private static void a(String str, int i) {
        if (str == null) {
            return;
        }
        try {
            int length = str.length();
            int i2 = 2000;
            int i3 = 0;
            int i4 = 0;
            while (i3 < 100) {
                if (length > i2) {
                    b(str.substring(i4, i2), i);
                    i3++;
                    i4 = i2;
                    i2 += 2000;
                } else {
                    b(str.substring(i4, length), i);
                    return;
                }
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private static void b(String str, int i) {
        String a2 = a();
        if (i == 2) {
            Log.v(a2, str);
            return;
        }
        if (i == 3) {
            Log.d(a2, str);
            return;
        }
        if (i == 4) {
            Log.i(a2, str);
        } else if (i == 5) {
            Log.w(a2, str);
        } else {
            if (i != 6) {
                return;
            }
            Log.e(a2, str);
        }
    }

    private static synchronized String a() {
        synchronized (an.class) {
            try {
                String className = new Exception().getStackTrace()[4].getClassName();
                className.substring(className.lastIndexOf(".") + 1, className.length());
            } catch (Throwable th) {
                ce.postSDKError(th);
                return ab.p;
            }
        }
        return ab.p;
    }
}
