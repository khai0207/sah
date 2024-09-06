package com.unionpay.utils;

import java.util.Locale;

/* loaded from: classes.dex */
public class h {
    private static h g;
    public String a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public String e = "";
    public String f = "";

    public static h a() {
        if (g == null) {
            g = Locale.getDefault().toString().startsWith("zh") ? new i() : new j();
        }
        return g;
    }
}
