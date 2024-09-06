package com.netease.nimlib.team;

/* compiled from: TeamMemberCacheKey.java */
/* loaded from: classes.dex */
public class f {
    private String a;
    private String b;

    public f(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public int hashCode() {
        String str = this.a;
        if (str == null || this.b == null) {
            return 0;
        }
        return str.hashCode() + this.b.hashCode();
    }

    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj == null || (str = this.a) == null || this.b == null || !(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return str.equals(fVar.b()) && this.b.equals(fVar.a());
    }
}
