package com.kqg.main.utils;

import com.android.pc.ioc.app.Ioc;
import com.kqg.main.base.KaiQiGuSdk;

/* loaded from: classes.dex */
public class LogUtil {
    public static void log(Object obj) {
        if (obj == null || !KaiQiGuSdk.getInstance().isDebug()) {
            return;
        }
        Ioc.getIoc().getLogger().d(obj.toString());
    }

    public static void logE(Object obj) {
        if (obj == null || !KaiQiGuSdk.getInstance().isDebug()) {
            return;
        }
        Ioc.getIoc().getLogger().e(obj.toString());
    }
}
