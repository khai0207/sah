package com.talkingdata.sdk;

import java.util.ArrayList;
import java.util.List;

/* compiled from: td */
/* loaded from: classes.dex */
public abstract class a {
    static final String a = "JSON";
    static final String b = "PB";
    static final String c = "MP";
    public static a e;
    public static a[] f;
    private String g;
    private int h;
    private static volatile List i = new ArrayList();
    public static a d = new b("TRACKING", 1);

    public abstract String d();

    public abstract String e();

    public abstract String f();

    public abstract String g();

    public abstract String h();

    static {
        c cVar = new c("ENV", 2);
        e = cVar;
        f = new a[]{d, cVar};
    }

    protected a(String str, int i2) {
        this.g = str;
        this.h = i2;
        b(str);
    }

    private void b(String str) {
        try {
            if (bh.b(str) || i.contains(str)) {
                return;
            }
            i.add(str);
        } catch (Throwable unused) {
        }
    }

    public static a a(String str) {
        if (str.equals(d.b())) {
            return d;
        }
        if (str.equals(e.b())) {
            return e;
        }
        return null;
    }

    public static a[] a() {
        return f;
    }

    public String b() {
        return this.g;
    }

    public int c() {
        return this.h;
    }

    public static ArrayList i() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i.size(); i2++) {
            try {
                arrayList.add(a((String) i.get(i2)));
            } catch (Throwable unused) {
            }
        }
        return arrayList;
    }
}
