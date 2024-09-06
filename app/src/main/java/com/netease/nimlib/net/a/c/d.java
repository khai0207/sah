package com.netease.nimlib.net.a.c;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.netease.nimlib.net.a.a.g;
import com.netease.nimlib.sdk.ServerAddresses;
import com.netease.nimlib.sdk.misc.model.LogDesensitizationConfig;
import com.netease.nimlib.sdk.misc.model.NosConfig;
import java.net.URLDecoder;

/* compiled from: NosUtil.java */
/* loaded from: classes.dex */
public class d {
    public static String a(com.netease.nimlib.net.a.b.c.d dVar, boolean z) {
        String str;
        LogDesensitizationConfig logDesensitizationConfig = com.netease.nimlib.c.i().logDesensitizationConfig;
        String e = dVar.e();
        if (!TextUtils.isEmpty(e)) {
            String a = g.a(dVar.a(), !e.contains("?"));
            if (!TextUtils.isEmpty(a)) {
                e = e + a;
            }
            com.netease.nimlib.log.b.d("NosUtil", "make url with short url: " + com.netease.nimlib.log.b.a.a(e, logDesensitizationConfig));
            return e;
        }
        String decode = URLDecoder.decode(dVar.d());
        String decode2 = URLDecoder.decode(dVar.c());
        NosConfig A = com.netease.nimlib.c.A();
        ServerAddresses l = com.netease.nimlib.c.l();
        if ((l == null || l.nosCdnEnable) && A != null && A.isValid()) {
            str = A.getCdnDomain() + "/" + decode;
        } else {
            String k = com.netease.nimlib.d.g.k();
            if (c.a(k)) {
                str = c.a(k, decode2, decode);
            } else {
                str = com.netease.nimlib.d.g.l() + "/" + decode2 + "/" + decode;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "https://" : "http://");
        sb.append(str);
        String sb2 = sb.toString();
        com.netease.nimlib.log.b.d("NosUtil", "replaced raw url is: " + com.netease.nimlib.log.b.a.a(sb2, logDesensitizationConfig));
        if (dVar.a() <= 0) {
            return sb2;
        }
        String a2 = g.a(dVar.a(), !sb2.contains("?"));
        if (TextUtils.isEmpty(a2)) {
            return sb2;
        }
        return sb2 + a2;
    }

    public static String a(String str) {
        return a(str, (String) null);
    }

    public static String a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        return c(str2) ? str : c.a(str, str2);
    }

    private static boolean c(String str) {
        NosConfig A = com.netease.nimlib.c.A();
        return ((A != null && A.isValid() && TextUtils.isEmpty(str)) || com.netease.nimlib.c.l() == null || com.netease.nimlib.c.l().nosAccess != null) ? false : true;
    }

    public static String a(String str, int i, int i2) {
        e eVar = e.Internal;
        if (i2 > 0 && i > 0) {
            eVar = (i > i2 ? i / i2 : i2 / i) > 4 ? e.External : e.Internal;
        }
        int i3 = com.netease.nimlib.c.i().thumbnailSize;
        if (i3 <= 0) {
            DisplayMetrics displayMetrics = com.netease.nimlib.c.e().getApplicationContext().getResources().getDisplayMetrics();
            i3 = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) / 2;
        }
        return b(str, a(eVar, i3, i3));
    }

    public static String a(String str, e eVar, int i, int i2) {
        return b(str, a(eVar, i, i2));
    }

    public static String b(String str) {
        return b(str, a());
    }

    public static String b(String str, String str2) {
        if (str == null) {
            return null;
        }
        return str + (str.contains("?") ? com.alipay.sdk.m.o.a.l : "?") + str2;
    }

    private static String a(e eVar, int i, int i2) {
        if (!b(eVar, i, i2)) {
            throw new IllegalArgumentException("width=" + i + ", height=" + i2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("thumbnail=");
        sb.append(i);
        sb.append(a(eVar));
        sb.append(i2);
        sb.append("&imageView");
        String b = b();
        if (!TextUtils.isEmpty(b)) {
            sb.append(b);
        }
        return sb.toString();
    }

    private static String a() {
        return a(1);
    }

    private static String a(int i) {
        return "vframe=" + i;
    }

    /* compiled from: NosUtil.java */
    /* renamed from: com.netease.nimlib.net.a.c.d$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[e.values().length];
            a = iArr;
            try {
                iArr[e.Internal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[e.Crop.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[e.External.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static boolean b(e eVar, int i, int i2) {
        if (i < 0 || i2 < 0) {
            return false;
        }
        int i3 = AnonymousClass1.a[eVar.ordinal()];
        return i3 != 1 ? (i3 == 2 || i3 == 3) && i > 0 && i2 > 0 : i > 0 || i2 > 0;
    }

    private static String a(e eVar) {
        int i = AnonymousClass1.a[eVar.ordinal()];
        if (i == 1) {
            return "x";
        }
        if (i == 2) {
            return "y";
        }
        if (i == 3) {
            return "z";
        }
        throw new IllegalArgumentException("thumb: " + eVar);
    }

    private static final String b() {
        if (com.netease.nimlib.c.i().animatedImageThumbnailEnabled) {
            return "&tostatic=0";
        }
        return null;
    }
}
