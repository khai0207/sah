package com.talkingdata.sdk;

/* compiled from: td */
/* loaded from: classes.dex */
public class df extends da {
    public static final String a = "type";
    public static final String c = "deviceId";
    public static final String d = "runtimeConfig";
    public static final String e = "hardwareConfig";
    public static final String f = "softwareConfig";
    public static de g = null;
    public static final String h = "tags";
    dg i = new dg();
    dd j = new dd();

    public df() {
        d();
    }

    private void d() {
        a("type", "mobile");
        de deVar = new de();
        g = deVar;
        a(c, deVar.a_());
        a(d, new dh().a_());
        a(e, this.j.a_());
        a(f, this.i.a_());
    }

    public dg b() {
        return this.i;
    }

    public dd c() {
        return this.j;
    }
}
