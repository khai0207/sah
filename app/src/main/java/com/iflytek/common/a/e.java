package com.iflytek.common.a;

/* loaded from: classes.dex */
public class e {
    private long a;
    private long b;
    private String[] c;
    private long d;
    private long e;
    private String f;
    private com.iflytek.common.c.d g;

    public e(com.iflytek.common.c.d dVar) {
        this.d = 0L;
        this.e = 0L;
        this.g = dVar;
        this.a = dVar.b("KEY_PERIOD_RUN", 0L);
        this.b = this.g.b("KEY_PERIOD_UPDATE", 259200000L);
        String b = this.g.b("KEY_PKG_RUN", (String) null);
        if (b != null && b.length() > 0) {
            this.c = b.split(",");
        }
        this.d = this.g.b("KEY_LAST_UPDATE", 0L);
        this.e = this.g.b("KEY_LAST_LAUNCH", 0L);
        this.f = this.g.b("KEY_APPID", (String) null);
    }

    public long a() {
        return this.a;
    }

    public void a(long j) {
        this.g.a("KEY_PERIOD_UPDATE", j);
        this.b = j;
    }

    public void a(String str) {
        this.f = str;
        this.g.a("KEY_APPID", str);
    }

    public void a(String[] strArr) {
        String str = "";
        if (strArr != null) {
            for (String str2 : strArr) {
                if (str2 != null && str2.length() > 0) {
                    if (str.length() > 0) {
                        str = str + ",";
                    }
                    str = str + str2;
                }
            }
        }
        this.g.a("KEY_PKG_RUN", str);
        this.c = strArr;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.g.a("KEY_PERIOD_RUN", j);
        this.a = j;
    }

    public void c(long j) {
        this.d = j;
        this.g.a("KEY_LAST_UPDATE", j);
    }

    public String[] c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public void d(long j) {
        this.e = j;
        this.g.a("KEY_LAST_LAUNCH", j);
    }

    public long e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        String[] strArr = this.c;
        String str = "";
        if (strArr != null) {
            for (String str2 : strArr) {
                str = str + str2 + ",";
            }
        }
        return str;
    }
}
