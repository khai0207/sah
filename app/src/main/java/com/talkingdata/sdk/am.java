package com.talkingdata.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import androidx.core.os.EnvironmentCompat;
import java.io.File;

/* compiled from: td */
/* loaded from: classes.dex */
public class am {
    private static volatile am a;
    private PackageInfo b = null;

    private am() {
    }

    public static am a() {
        if (a == null) {
            synchronized (am.class) {
                if (a == null) {
                    a = new am();
                }
            }
        }
        return a;
    }

    private synchronized boolean i(Context context) {
        try {
            if (this.b == null) {
                this.b = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            }
        } catch (Throwable unused) {
            return false;
        }
        return true;
    }

    public String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            i(context);
            return context.getPackageName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public int b(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            if (i(context)) {
                return this.b.versionCode;
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public String c(Context context) {
        if (context == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        try {
            return !i(context) ? EnvironmentCompat.MEDIA_UNKNOWN : this.b.versionName;
        } catch (Throwable unused) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public long d(Context context) {
        if (context == null) {
            return -1L;
        }
        try {
            if (i(context) && bh.a(9)) {
                return this.b.firstInstallTime;
            }
            return -1L;
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public long e(Context context) {
        if (context == null) {
            return -1L;
        }
        try {
            if (i(context) && bh.a(9)) {
                return this.b.lastUpdateTime;
            }
            return -1L;
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public long f(Context context) {
        if (context == null) {
            return -1L;
        }
        try {
            i(context);
            return new File(context.getApplicationInfo().sourceDir).length();
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public String g(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (!i(context)) {
                return null;
            }
            Signature[] signatureArr = this.b.signatures;
            if (signatureArr.length < 1) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(signatureArr[0].toCharsString());
            return stringBuffer.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    public String h(Context context) {
        if (context == null) {
            return null;
        }
        try {
            i(context);
            return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
        } catch (Throwable unused) {
            return null;
        }
    }
}
