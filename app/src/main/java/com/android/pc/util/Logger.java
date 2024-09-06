package com.android.pc.util;

import android.util.Log;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.core.kernel.KernelClass;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class Logger {
    private static boolean debug = false;
    private static final int logLevel = 2;
    private static Hashtable<String, Logger> logger = new Hashtable<>();
    public static String tag = "Inject_android";
    private String name;

    private Logger(String str) {
        this.name = str;
        Class forName = KernelClass.forName(Ioc.getIoc().getApplication().getPackageName() + ".BuildConfig");
        if (forName == null) {
            debug = false;
        } else {
            try {
                debug = Boolean.valueOf(forName.getDeclaredField("DEBUG").get(null).toString()).booleanValue();
            } catch (Exception unused) {
            }
        }
    }

    public static Logger getLogger(String str) {
        Logger logger2 = logger.get(str);
        if (logger2 != null) {
            return logger2;
        }
        Logger logger3 = new Logger(str);
        logger.put(str, logger3);
        return logger3;
    }

    private String getFunctionName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return this.name + "[ " + Thread.currentThread().getName() + ": " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " " + stackTraceElement.getMethodName() + " ]";
            }
        }
        return null;
    }

    public static boolean isDebug() {
        return debug;
    }

    public void i(Object obj) {
        if (debug) {
            String functionName = getFunctionName();
            if (functionName != null) {
                Log.i(tag, functionName + "\n" + obj + "\n------------------------------------------------------------------------------");
                return;
            }
            Log.i(tag, obj.toString());
        }
    }

    public void s(Object obj) {
        if (debug) {
            String functionName = getFunctionName();
            if (functionName != null) {
                System.out.println(functionName + "\n" + obj + "\n------------------------------------------------------------------------------");
                return;
            }
            System.out.println(obj.toString());
        }
    }

    public void d(Object obj) {
        if (debug) {
            String functionName = getFunctionName();
            if (functionName != null) {
                Log.d(tag, functionName + "\n" + obj + "\n------------------------------------------------------------------------------");
                return;
            }
            Log.d(tag, obj.toString());
        }
    }

    public void v(Object obj) {
        if (debug) {
            String functionName = getFunctionName();
            if (functionName != null) {
                Log.v(tag, functionName + "\n" + obj + "\n------------------------------------------------------------------------------");
                return;
            }
            Log.v(tag, obj.toString());
        }
    }

    public void w(Object obj) {
        if (debug) {
            String functionName = getFunctionName();
            if (functionName != null) {
                Log.w(tag, functionName + "\n" + obj + "\n------------------------------------------------------------------------------");
                return;
            }
            Log.w(tag, obj.toString());
        }
    }

    public void e(Object obj) {
        if (debug) {
            String functionName = getFunctionName();
            if (functionName != null) {
                Log.e(tag, functionName + "\n" + obj + "\n------------------------------------------------------------------------------");
                return;
            }
            Log.e(tag, obj.toString());
        }
    }

    public void e(Exception exc) {
        if (debug) {
            Log.e(tag, "error", exc);
        }
    }

    public void e(String str, Throwable th) {
        if (debug) {
            String functionName = getFunctionName();
            Log.e(tag, "{Thread:" + Thread.currentThread().getName() + "}[" + this.name + functionName + ":] " + str + "\n", th);
        }
    }
}
