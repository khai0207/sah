package com.netease.nimlib.net.a.b.d;

import java.io.UnsupportedEncodingException;

/* compiled from: NosUploadProtocol.java */
/* loaded from: classes.dex */
public class b {
    private static final String a = com.netease.nimlib.net.a.b.e.b.a(b.class);

    public static String a(String str, String str2, String str3, String str4) throws UnsupportedEncodingException {
        String str5;
        if (str4 != null) {
            str5 = str2 + "/" + str3 + "?uploadContext&version=1.0&context=" + str4;
        } else {
            str5 = str2 + "/" + str3 + "?uploadContext&version=1.0";
        }
        return str + "/" + str5;
    }

    public static String a(String str, String str2, String str3, String str4, long j, boolean z) throws UnsupportedEncodingException {
        String str5;
        if (str4 != null) {
            str5 = str2 + "/" + str3 + "?version=1.0&context=" + str4 + "&offset=" + j + "&complete=" + z;
        } else {
            str5 = str2 + "/" + str3 + "?version=1.0&offset=" + j + "&complete=" + z;
        }
        com.netease.nimlib.log.b.c(a, "post data url server: " + str + ", query string: " + str5);
        return str + "/" + str5;
    }
}
