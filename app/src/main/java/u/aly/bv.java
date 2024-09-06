package u.aly;

import android.util.Log;
import java.util.Formatter;
import java.util.Locale;

/* compiled from: MLog.java */
/* loaded from: classes.dex */
public class bv {
    public static boolean a = false;
    private static String b = "MobclickAgent";

    private bv() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void a(Locale locale, String str, Object... objArr) {
        try {
            c(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void b(Locale locale, String str, Object... objArr) {
        try {
            b(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void c(Locale locale, String str, Object... objArr) {
        try {
            e(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(Locale locale, String str, Object... objArr) {
        try {
            a(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(Locale locale, String str, Object... objArr) {
        try {
            d(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void a(String str, Object... objArr) {
        try {
            if (str.contains("%")) {
                c(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                c(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void b(String str, Object... objArr) {
        try {
            if (str.contains("%")) {
                b(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                b(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void c(String str, Object... objArr) {
        try {
            if (str.contains("%")) {
                e(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                e(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(String str, Object... objArr) {
        try {
            if (str.contains("%")) {
                a(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                a(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(String str, Object... objArr) {
        try {
            if (str.contains("%")) {
                d(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                d(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void a(Throwable th) {
        c(b, (String) null, th);
    }

    public static void b(Throwable th) {
        a(b, (String) null, th);
    }

    public static void c(Throwable th) {
        d(b, (String) null, th);
    }

    public static void d(Throwable th) {
        b(b, (String) null, th);
    }

    public static void e(Throwable th) {
        e(b, (String) null, th);
    }

    public static void a(String str, Throwable th) {
        c(b, str, th);
    }

    public static void b(String str, Throwable th) {
        a(b, str, th);
    }

    public static void c(String str, Throwable th) {
        d(b, str, th);
    }

    public static void d(String str, Throwable th) {
        b(b, str, th);
    }

    public static void e(String str, Throwable th) {
        e(b, str, th);
    }

    public static void a(String str) {
        a(b, str, (Throwable) null);
    }

    public static void b(String str) {
        b(b, str, (Throwable) null);
    }

    public static void c(String str) {
        c(b, str, (Throwable) null);
    }

    public static void d(String str) {
        d(b, str, (Throwable) null);
    }

    public static void e(String str) {
        e(b, str, (Throwable) null);
    }

    public static void a(String str, String str2, Throwable th) {
        if (a) {
            try {
                if (th == null) {
                    if (str2 == null) {
                        Log.w(str, "the msg is null!");
                        return;
                    } else {
                        Log.v(str, str2);
                        return;
                    }
                }
                if (str2 != null) {
                    Log.v(str, th.toString() + ":  [" + str2 + "]");
                } else {
                    Log.v(str, th.toString());
                }
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    Log.v(str, "        at  " + stackTraceElement.toString());
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (a) {
            try {
                if (th == null) {
                    if (str2 == null) {
                        Log.w(str, "the msg is null!");
                        return;
                    } else {
                        Log.d(str, str2);
                        return;
                    }
                }
                if (str2 != null) {
                    Log.d(str, th.toString() + ":  [" + str2 + "]");
                } else {
                    Log.d(str, th.toString());
                }
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    Log.d(str, "        at  " + stackTraceElement.toString());
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (a) {
            try {
                if (th == null) {
                    if (str2 == null) {
                        Log.w(str, "the msg is null!");
                        return;
                    } else {
                        Log.i(str, str2);
                        return;
                    }
                }
                if (str2 != null) {
                    Log.i(str, th.toString() + ":  [" + str2 + "]");
                } else {
                    Log.i(str, th.toString());
                }
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    Log.i(str, "        at  " + stackTraceElement.toString());
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (a) {
            try {
                if (th == null) {
                    if (str2 == null) {
                        Log.w(str, "the msg is null!");
                        return;
                    } else {
                        Log.w(str, str2);
                        return;
                    }
                }
                if (str2 != null) {
                    Log.w(str, th.toString() + ":  [" + str2 + "]");
                } else {
                    Log.w(str, th.toString());
                }
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    Log.w(str, "        at  " + stackTraceElement.toString());
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (a) {
            try {
                if (th == null) {
                    if (str2 == null) {
                        Log.w(str, "the msg is null!");
                        return;
                    } else {
                        Log.e(str, str2);
                        return;
                    }
                }
                if (str2 != null) {
                    Log.e(str, th.toString() + ":  [" + str2 + "]");
                } else {
                    Log.e(str, th.toString());
                }
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    Log.e(str, "        at  " + stackTraceElement.toString());
                }
            } catch (Throwable unused) {
            }
        }
    }
}
