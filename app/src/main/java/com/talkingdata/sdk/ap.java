package com.talkingdata.sdk;

import androidx.core.os.EnvironmentCompat;

/* compiled from: td */
/* loaded from: classes.dex */
public class ap {
    private static final String A = "TDtd_role_id";
    private static final String B = "TDpref.accountid.key";
    private static final String C = "TDpref.accountgame.key";
    private static final String D = "TDpref.missionid.key";
    private static final String E = "TDpref.activesessionid.key";
    private static final String F = "TDpref.game.session.start.key";
    private static final String G = "TDpref.game.session.end.key";
    private static final String H = "TDpref.game.session.startsystem.key";
    public static final String a = "TDpref.profile.key";
    public static final String b = "TDpref.session.key";
    public static final String c = "TDpref.lastactivity.key";
    public static final String d = "TDpref.start.key";
    public static final String e = "TDpref.init.key";
    public static final String f = "TDpref.actstart.key";
    public static final String g = "TDpref.end.key";
    public static final String h = "TDpref.ip";
    public static final String i = "TD_CHANNEL_ID";
    public static final String j = "TDappcontext_push";
    public static final String k = "TDpref.tokensync.key";
    public static final String l = "TDpref.push.msgid.key";
    public static final String m = "TDpref.running.app.key";
    public static final String n = "TDpref_longtime";
    public static final String o = "TDpref_shorttime";
    public static final String p = "TDaes_key";
    public static final String q = "TDpref_game";
    public static final String r = "TD_push_pref_file";
    static final String s = "TDisAppQuiting";
    public static final String t = "TDpref.last.sdk.check";

    /* renamed from: u, reason: collision with root package name */
    public static final String f32u = "TDadditionalVersionName";
    public static final String v = "TDadditionalVersionCode";
    public static final long w = 868;
    public static final long x = 0;
    private static final String y = "TDtime_set_collect_net";
    private static final String z = "TDdeep_link_url";

    public static String a() {
        if (ab.f == null) {
            return null;
        }
        try {
            return bd.b(ab.f, n, p, (String) null);
        } catch (Throwable th) {
            ce.postSDKError(th);
            return null;
        }
    }

    public static void setAESKey(String str) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, n, p, str);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public static String a(a aVar) {
        if (ab.f != null && aVar != null) {
            try {
                return bd.b(ab.f, n + aVar.c(), b, (String) null);
            } catch (Throwable th) {
                ce.postSDKError(th);
            }
        }
        return null;
    }

    public static void a(String str, a aVar) {
        if (ab.f == null || aVar == null) {
            return;
        }
        try {
            bd.a(ab.f, n + aVar.c(), b, str);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public static void b() {
        if (ab.f == null) {
            return;
        }
        try {
            ab.f.getSharedPreferences("TD_CHANNEL_ID", 0).edit().putBoolean("location_called", true).commit();
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public static boolean c() {
        if (ab.f == null) {
            return false;
        }
        try {
            return ab.f.getSharedPreferences("TD_CHANNEL_ID", 0).getBoolean("location_called", false);
        } catch (Throwable th) {
            ce.postSDKError(th);
            return false;
        }
    }

    public static void setLastActivity(String str) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, o, c, str);
        } catch (Throwable unused) {
        }
    }

    public static String d() {
        if (ab.f == null) {
            return "";
        }
        try {
            return bd.b(ab.f, o, c, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static long b(a aVar) {
        if (ab.f != null && aVar != null) {
            try {
                return bd.b(ab.f, n + aVar.c(), d, 0L);
            } catch (Throwable unused) {
            }
        }
        return 0L;
    }

    public static void a(long j2, a aVar) {
        if (ab.f == null || aVar == null) {
            return;
        }
        try {
            bd.a(ab.f, n + aVar.c(), d, j2);
        } catch (Throwable unused) {
        }
    }

    public static void b(long j2, a aVar) {
        if (ab.f == null || aVar == null) {
            return;
        }
        try {
            bd.a(ab.f, n + aVar.c(), e, j2);
        } catch (Throwable unused) {
        }
    }

    public static long c(a aVar) {
        if (ab.f != null && aVar != null) {
            try {
                return bd.b(ab.f, n + aVar.c(), e, 0L);
            } catch (Throwable unused) {
            }
        }
        return 0L;
    }

    public static void setInitTime(long j2) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, n, e, j2);
        } catch (Throwable unused) {
        }
    }

    public static long e() {
        if (ab.f == null) {
            return 0L;
        }
        try {
            return bd.b(ab.f, n, e, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static void setActivityStartTime(long j2) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, o, f, j2);
        } catch (Throwable unused) {
        }
    }

    public static long f() {
        if (ab.f == null) {
            return 0L;
        }
        try {
            return bd.b(ab.f, o, f, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static long d(a aVar) {
        if (ab.f != null && aVar != null) {
            try {
                return bd.b(ab.f, o + aVar.c(), g, 0L);
            } catch (Throwable unused) {
            }
        }
        return 0L;
    }

    public static void c(long j2, a aVar) {
        if (ab.f == null || aVar == null) {
            return;
        }
        try {
            bd.a(ab.f, o + aVar.c(), g, j2);
        } catch (Throwable unused) {
        }
    }

    public static void setPostProfile(boolean z2) {
        try {
            bd.a(ab.f, n, a, z2 ? 1L : 0L);
        } catch (Throwable unused) {
        }
    }

    public static void setCollectRunningTime(long j2) {
        try {
            bd.a(ab.f, n, m, j2);
        } catch (Throwable unused) {
        }
    }

    public static long g() {
        if (ab.f == null) {
            return 0L;
        }
        try {
            return bd.b(ab.f, n, m, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static void b(String str, a aVar) {
        if (ab.f == null || aVar == null) {
            return;
        }
        try {
            bd.a(ab.f, n + aVar.c(), s, str);
        } catch (Throwable unused) {
        }
    }

    public static String e(a aVar) {
        if (ab.f != null && aVar != null) {
            try {
                return bd.b(ab.f, n + aVar.c(), s, "-1");
            } catch (Throwable unused) {
            }
        }
        return "-1";
    }

    public static void setAdditionalVersionName(String str) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, n, f32u, str);
        } catch (Throwable unused) {
        }
    }

    public static String h() {
        if (ab.f == null) {
            return null;
        }
        try {
            return bd.b(ab.f, n, f32u, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void setAdditionalVersionCode(long j2) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, n, v, j2);
        } catch (Throwable unused) {
        }
    }

    public static long i() {
        if (ab.f == null) {
            return -1L;
        }
        try {
            return bd.b(ab.f, n, v, -1L);
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public static int j() {
        try {
            if (i() != -1) {
                return Integer.parseInt(String.valueOf(i()));
            }
            return am.a().b(ab.f);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static String k() {
        try {
            if (h() != null) {
                return h();
            }
            return am.a().c(ab.f);
        } catch (Throwable unused) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static void a(String str, String str2) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, o, str, str2);
        } catch (Throwable unused) {
        }
    }

    public static String a(String str) {
        if (ab.f == null) {
            return null;
        }
        try {
            return bd.b(ab.f, o, str, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void b(String str, String str2) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, o, str, str2);
        } catch (Throwable unused) {
        }
    }

    public static String b(String str) {
        if (ab.f == null) {
            return null;
        }
        try {
            return bd.b(ab.f, o, str, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void setLastRoleName(String str) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, o, A, str);
        } catch (Throwable unused) {
        }
    }

    public static String l() {
        if (ab.f == null) {
            return null;
        }
        try {
            return bd.b(ab.f, o, A, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static long m() {
        if (ab.f == null) {
            return 0L;
        }
        try {
            return bd.b(ab.f, o, y, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static void setCollectNetInfoTime(long j2) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, o, y, j2);
        } catch (Throwable unused) {
        }
    }

    public static String n() {
        if (ab.f == null) {
            return null;
        }
        try {
            return bd.b(ab.f, o, z, (String) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void setDeepLink(String str) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, o, z, str);
        } catch (Throwable unused) {
        }
    }

    public static String o() {
        if (ab.f == null) {
            return "";
        }
        try {
            return bd.b(ab.f, q, B, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void setAccountId(String str) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, q, B, str);
        } catch (Throwable unused) {
        }
    }

    public static String c(String str) {
        if (ab.f == null) {
            return "";
        }
        try {
            return bd.b(ab.f, q, str + C, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void c(String str, String str2) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, q, str + C, str2);
        } catch (Throwable unused) {
        }
    }

    public static void setMissionId(String str) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, q, D, str);
        } catch (Throwable unused) {
        }
    }

    public static String p() {
        if (ab.f == null) {
            return "";
        }
        try {
            return bd.b(ab.f, q, D, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void q() {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, q, H, System.currentTimeMillis());
        } catch (Throwable unused) {
        }
    }

    public static void setPushAppContext(String str) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, r, j, str);
        } catch (Throwable unused) {
        }
    }

    public static String r() {
        if (ab.f == null) {
            return "";
        }
        try {
            return bd.b(ab.f, r, j, "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void setPushSyncTokenLastTime(long j2) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, r, k, j2);
        } catch (Throwable unused) {
        }
    }

    public static long s() {
        if (ab.f == null) {
            return 0L;
        }
        try {
            return bd.b(ab.f, r, k, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static void setPushLastMsgId(String str) {
        if (ab.f == null) {
            return;
        }
        try {
            bd.a(ab.f, r, l, str);
        } catch (Throwable unused) {
        }
    }

    public static String t() {
        if (ab.f == null) {
            return "";
        }
        try {
            return bd.b(ab.f, r, l, "");
        } catch (Throwable unused) {
            return "";
        }
    }
}
