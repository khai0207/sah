package com.talkingdata.sdk;

import android.content.Context;
import android.os.Handler;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: td */
/* loaded from: classes.dex */
public class ab {
    public static final String A = "Android+TD+V4.0.8 gp";
    static long B = 0;
    public static final int D = 120000;
    public static final int F = 100;
    public static final String G = "TD_APP_ID";
    public static final String H = "TD_CHANNEL_ID";
    private static final String I = "+V";
    public static final boolean a = false;
    public static boolean b = false;
    public static final int h = 1;
    public static final String i = "Android+";
    public static FileChannel j = null;
    public static final String o = "TD";
    public static final String p = "TDLog";
    public static final String r = "TD_app_pefercen_profile";
    public static final String s = "TD_appId_";
    public static final String t = "TD_channelId";

    /* renamed from: u */
    public static final String f31u = "TD_sdk_last_send_time";
    public static final String v = "isDeveloper";
    public static AtomicBoolean c = new AtomicBoolean(false);
    public static final Map d = new TreeMap();
    public static boolean e = false;
    public static Context f = null;
    public static Handler g = null;
    public static long k = 0;
    public static boolean l = false;
    public static boolean m = true;
    public static boolean n = true;
    public static String q = "";
    public static String w = "Default";
    public static boolean x = false;
    public static String y = null;
    public static boolean z = false;
    public static int C = 2;
    public static long E = 30000;

    /* compiled from: td */
    /* loaded from: classes.dex */
    public static class b {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        public static final int i = 9;
        public static final int j = 10;
        public static final int k = 11;
        public static final int l = 12;
        public static final int m = 13;
        public static final int n = 15;
        public static final int o = 14;
        public static final int p = 16;
        public static final int q = 90;
        public static final int r = 91;
        public static final int s = 92;
    }

    public static String a(Context context, com.talkingdata.sdk.a aVar) {
        if (context == null || aVar == null) {
            an.dForInternal("Context or Service is null");
            return "";
        }
        if (!bh.b(q)) {
            return q;
        }
        return bd.b(context, r, s + aVar.b(), "");
    }

    public static void a(String str, com.talkingdata.sdk.a aVar) {
        if (aVar != null) {
            bd.a(f, r, s + aVar.b(), str);
        }
    }

    public static String b(Context context, com.talkingdata.sdk.a aVar) {
        if (bh.b(w) || w.equals("Default")) {
            w = bd.b(context, r, t + aVar.b(), "Default");
        }
        return w;
    }

    public static void b(String str, com.talkingdata.sdk.a aVar) {
        bd.a(f, r, t + aVar.b(), str);
    }

    public static void a(String str, String str2, com.talkingdata.sdk.a aVar) {
        if (f != null) {
            g = new Handler(f.getMainLooper());
        }
        if (str != null && !str.trim().isEmpty() && str.contains("-")) {
            String str3 = null;
            try {
                str3 = str.split("-")[1];
            } catch (Throwable unused) {
            }
            q = str3;
        } else {
            q = str;
        }
        if (str2 != null && !str2.trim().isEmpty()) {
            w = str2;
        }
        a(q, aVar);
        b(w, aVar);
        cy.a().a((Object) q, aVar);
        cy.a().b(w, aVar);
        a(dz.a);
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    public class a {
        public static final int a = 1;
        public static final int b = 2;

        public a() {
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    public class c {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = -1;

        public c() {
        }
    }

    public static void setDeveloperMode(boolean z2) {
        try {
            bd.a(f, r, v, z2 ? 1L : 0L);
        } catch (Throwable unused) {
        }
    }

    public static boolean a() {
        try {
            return bd.b(f, r, v, 0L) != 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static void a(int i2) {
        try {
            db dbVar = new db();
            if (i2 == 1) {
                dbVar.setFrameWork("Cocos2d");
            } else if (i2 == 2) {
                dbVar.setFrameWork("Unity");
            } else if (i2 == 3) {
                dbVar.setFrameWork("AIR");
            } else if (i2 == 4) {
                dbVar.setFrameWork("PhoneGap");
            } else {
                dbVar.setFrameWork("Native");
            }
        } catch (Throwable unused) {
        }
    }

    public static String b() {
        try {
            return new db().b();
        } catch (Throwable unused) {
            return "Native";
        }
    }
}
