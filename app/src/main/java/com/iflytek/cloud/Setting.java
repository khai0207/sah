package com.iflytek.cloud;

import com.iflytek.msc.MSC;

/* loaded from: classes.dex */
public class Setting {
    public static boolean a = true;
    public static boolean b = true;
    public static LOG_LEVEL c = LOG_LEVEL.none;
    public static String d = null;

    /* loaded from: classes.dex */
    public enum LOG_LEVEL {
        all,
        detail,
        normal,
        low,
        none
    }

    private Setting() {
    }

    public static void saveLogFile(LOG_LEVEL log_level, String str) {
        c = log_level;
        d = str;
    }

    public static void setLocationEnable(boolean z) {
        b = z;
    }

    public static void showLogcat(boolean z) {
        a = z;
        if (MSC.isLoaded()) {
            try {
                MSC.DebugLog(a);
            } catch (UnsatisfiedLinkError unused) {
            }
        }
    }
}
