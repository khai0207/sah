package com.netease.nimlib.n;

import android.content.Context;
import com.netease.nimlib.sdk.StatusCode;
import java.util.Locale;

/* compiled from: SyncEventManager.java */
/* loaded from: classes.dex */
public class p {
    public static void a() {
        try {
            if (com.netease.nimlib.h.h()) {
                t.a().b();
            } else {
                m.a().b();
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("SyncEventManager", "startTrackEvent51 exception", th);
        }
    }

    public static void a(boolean z, int i) {
        try {
            if (com.netease.nimlib.h.h()) {
                t.a().a(z, i);
            } else {
                m.a().a(z, i);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("SyncEventManager", "stopTrackEvent51 exception, isSuccess = " + z + ", code = " + i, th);
        }
    }

    public static void a(boolean z, String str) {
        try {
            if (com.netease.nimlib.h.h()) {
                t.a().a(z, str);
            } else {
                m.a().a(z, str);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("SyncEventManager", "stopTrackEvent51 exception, isSuccess = " + z + ", description = " + str, th);
        }
    }

    public static boolean a(StatusCode statusCode) {
        String str;
        if (!com.netease.nimlib.c.z()) {
            return false;
        }
        try {
            if (statusCode == StatusCode.LOGOUT || statusCode == StatusCode.UNLOGIN || statusCode == StatusCode.NET_BROKEN) {
                if (statusCode == StatusCode.LOGOUT) {
                    str = "user logout during sync";
                } else {
                    Context e = com.netease.nimlib.c.e();
                    if (e != null) {
                        str = String.format(Locale.ENGLISH, "connect broken during sync, status: %s, isNetAvailable: %s isNetworkConnected: %s", statusCode, Boolean.valueOf(com.netease.nimlib.o.p.b(e)), Boolean.valueOf(com.netease.nimlib.network.f.a(e)));
                    } else {
                        str = "connect broken during syn, context is null,status = " + statusCode;
                    }
                }
                a(false, str);
                return true;
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("SyncEventManager", "brokenTrackEvent exception, status = " + statusCode, th);
        }
        return false;
    }

    public static void a(com.netease.nimlib.push.packet.a aVar, int i) {
        try {
            if (com.netease.nimlib.h.h()) {
                t.a().a(aVar, i);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("SyncEventManager", "addTrackEventItem exception", th);
        }
    }
}
