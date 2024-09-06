package com.netease.nimlib.push.net.lbs;

import android.text.TextUtils;
import java.net.InetSocketAddress;

/* compiled from: LinkAddress.java */
/* loaded from: classes.dex */
public class b {
    public String a;
    public String b;
    public int c;
    private boolean d = false;
    private long e = 30000;
    private InetSocketAddress f;

    public b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int indexOf = str.charAt(0) == '[' ? str.indexOf(93) + 1 : str.indexOf(58);
        if (indexOf >= 0 && indexOf < str.length()) {
            this.b = str.substring(0, indexOf);
            try {
                this.c = Integer.parseInt(str.substring(indexOf + 1));
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.b = str;
    }

    public b(String str, String str2, int i) {
        this.a = str;
        this.b = str2;
        this.c = i;
    }

    public boolean a() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public long b() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public InetSocketAddress c() {
        return this.f;
    }

    public void a(InetSocketAddress inetSocketAddress) {
        this.f = inetSocketAddress;
    }

    public boolean d() {
        return !TextUtils.isEmpty(this.b);
    }

    public boolean a(b bVar) {
        return bVar != null && TextUtils.equals(this.b, bVar.b) && this.c == bVar.c;
    }

    public static String b(b bVar) {
        if (bVar == null) {
            return "LinkAddress{NULL}";
        }
        return "LinkAddress{sn='" + bVar.a + "', ip='" + bVar.b + "', port=" + bVar.c + ", isQuickConnect=" + bVar.d + ", timeout=" + bVar.e + ", inetSocketAddress=" + bVar.f + ", isValid=" + bVar.d() + '}';
    }

    public String toString() {
        String str;
        if (!d()) {
            return "INVALID";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        if (this.c > 0) {
            str = ":" + this.c;
        } else {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }
}
