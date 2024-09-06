package com.netease.nimlib.amazonaws.util;

import com.netease.nimlib.amazonaws.logging.LogFactory;

/* loaded from: classes.dex */
public enum Throwables {
    ;

    private static final int MAX_RETRY = 1000;

    public static Throwable getRootCause(Throwable th) {
        if (th == null) {
            return th;
        }
        int i = 0;
        Throwable th2 = th;
        while (i < 1000) {
            Throwable cause = th2.getCause();
            if (cause == null) {
                return th2;
            }
            i++;
            th2 = cause;
        }
        LogFactory.getLog((Class<?>) Throwables.class).debug("Possible circular reference detected on " + th.getClass() + ": [" + th + "]");
        return th;
    }
}
