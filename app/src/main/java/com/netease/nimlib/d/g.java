package com.netease.nimlib.d;

import android.text.TextUtils;
import com.netease.nimlib.sdk.NimHandshakeType;
import com.netease.nimlib.sdk.ServerAddresses;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: Servers.java */
/* loaded from: classes.dex */
public class g {
    static final List<String> a = new ArrayList(0);
    static final List<String> b = new ArrayList(0);

    public static String a() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().lbs;
        return TextUtils.isEmpty(str) ? f.a.c() : str;
    }

    public static List<String> b() {
        List<String> list = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().lbsBackup;
        return com.netease.nimlib.o.f.c((Collection) list) ? f.a.d() : list;
    }

    public static String c() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().probeIpv4Url;
        return TextUtils.isEmpty(str) ? f.a.e() : str;
    }

    public static String d() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().probeIpv6Url;
        return TextUtils.isEmpty(str) ? f.a.f() : str;
    }

    public static String e() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().defaultLink;
        return TextUtils.isEmpty(str) ? f.a.a() : str;
    }

    public static List<String> f() {
        List<String> list = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().defaultLinkBackup;
        return com.netease.nimlib.o.f.c((Collection) list) ? f.a.b() : list;
    }

    public static String g() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().linkIpv6;
        return TextUtils.isEmpty(str) ? f.a.a() : str;
    }

    public static String h() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().nosUploadLbs;
        return TextUtils.isEmpty(str) ? "https://wannos.127.net/lbs" : str;
    }

    public static String i() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().nosUploadDefaultLink;
        return TextUtils.isEmpty(str) ? "https://nosup-hz1.127.net" : str;
    }

    public static String j() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().nosUpload;
        return TextUtils.isEmpty(str) ? "nosup-hz1.127.net" : str;
    }

    public static String k() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().nosDownloadUrlFormat;
        return TextUtils.isEmpty(str) ? "{bucket}-nosdn.netease.im/{object}" : str;
    }

    public static String l() {
        String str = com.netease.nimlib.c.l() == null ? null : com.netease.nimlib.c.l().nosDownload;
        return TextUtils.isEmpty(str) ? "nos.netease.com" : str;
    }

    public static String m() {
        if (com.netease.nimlib.c.l() == null || TextUtils.isEmpty(com.netease.nimlib.c.l().nosAccess)) {
            return null;
        }
        return com.netease.nimlib.c.l().nosAccess;
    }

    public static NimHandshakeType n() {
        NimHandshakeType nimHandshakeType = NimHandshakeType.V1;
        ServerAddresses l = com.netease.nimlib.c.l();
        return (l == null || l.handshakeType == null) ? nimHandshakeType : l.handshakeType;
    }
}
