package com.netease.nimlib.ipc;

import android.os.Build;
import android.os.TransactionTooLargeException;

/* compiled from: RemoteExceptionHelper.java */
/* loaded from: classes.dex */
public class f {
    public static boolean a(Exception exc) {
        if (!b(exc)) {
            return false;
        }
        try {
            Thread.sleep(20L);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean b(Exception exc) {
        if (Build.VERSION.SDK_INT >= 15) {
            return c(exc);
        }
        return false;
    }

    private static boolean c(Exception exc) {
        return exc instanceof TransactionTooLargeException;
    }
}
