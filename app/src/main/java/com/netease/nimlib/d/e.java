package com.netease.nimlib.d;

import android.text.TextUtils;
import com.netease.nimlib.push.packet.asymmetric.AsymmetricType;
import com.netease.nimlib.push.packet.symmetry.SymmetryType;
import com.netease.nimlib.sdk.ServerAddresses;
import java.util.List;

/* compiled from: ServerConfig.java */
/* loaded from: classes.dex */
public final class e {

    /* compiled from: ServerConfig.java */
    /* loaded from: classes.dex */
    enum a {
        REL("link.netease.im:8080", g.a, "https://lbs.netease.im/lbs/conf.jsp", g.b, "https://check-ipv4.netease.im/", "https://check-ipv6.netease.im/");

        private final String b;
        private final List<String> c;
        private final String d;
        private final List<String> e;
        private final String f;
        private final String g;

        a(String str, List list, String str2, List list2, String str3, String str4) {
            this.b = str;
            this.c = list;
            this.d = str2;
            this.e = list2;
            this.f = str3;
            this.g = str4;
        }

        public String a() {
            return this.b;
        }

        public List<String> b() {
            return this.c;
        }

        public String c() {
            return this.d;
        }

        public List<String> d() {
            return this.e;
        }

        public String e() {
            return this.f;
        }

        public String f() {
            return this.g;
        }
    }

    public static boolean a() {
        return b() && com.netease.nimlib.c.l().test;
    }

    public static boolean b() {
        return com.netease.nimlib.c.l() != null;
    }

    public static boolean c() {
        ServerAddresses l = com.netease.nimlib.c.l();
        return (l == null || TextUtils.isEmpty(l.negoKeyEncaKeyParta) || TextUtils.isEmpty(l.negoKeyEncaKeyPartb)) ? false : true;
    }

    public static boolean d() {
        ServerAddresses l = com.netease.nimlib.c.l();
        return (l == null || TextUtils.isEmpty(l.module)) ? false : true;
    }

    public static AsymmetricType e() {
        ServerAddresses l = com.netease.nimlib.c.l();
        if (l == null || l.negoKeyNeca == null) {
            return AsymmetricType.RSA;
        }
        return l.negoKeyNeca;
    }

    public static SymmetryType f() {
        ServerAddresses l = com.netease.nimlib.c.l();
        if (l == null || l.commEnca == null) {
            return SymmetryType.RC4;
        }
        return l.commEnca;
    }
}
