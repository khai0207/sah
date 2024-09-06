package com.netease.nimlib.abtest.a;

/* compiled from: ABRealReachability.java */
/* loaded from: classes.dex */
public class a {
    private boolean a = false;
    private String b;
    private String c;
    private int d;
    private int e;
    private int f;
    private int g;

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        try {
            int indexOf = str.charAt(0) == '[' ? str.indexOf(93) + 1 : str.indexOf(58);
            if (indexOf >= 0 && indexOf < str.length()) {
                this.c = str.substring(0, indexOf);
                this.d = com.netease.nimlib.o.b.a(str.substring(indexOf + 1));
            } else {
                this.c = str;
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ABRealReachability", "telnetAddress is invalid, telnetAddress=" + str, th);
        }
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int e() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }

    public int f() {
        return this.g;
    }

    public void c(int i) {
        this.g = i;
    }

    public boolean g() {
        return this.a;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public String toString() {
        return "ABRealReachability{isOpen=" + this.a + ", pingHost='" + this.b + "', telnetHost='" + this.c + "', telnetPort=" + this.d + ", autoCheckMin=" + this.e + ", pingTimeOut=" + this.f + ", telnetTimeOut=" + this.g + '}';
    }
}
