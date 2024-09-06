package com.netease.nimlib.net.a.b.c;

import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.Arrays;
import java.util.Map;

/* compiled from: WanNOSObject.java */
/* loaded from: classes.dex */
public class e {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Map<String, String> f;

    public e() {
    }

    public e(String str, String str2, String str3, String str4, Map<String, String> map) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.f = map;
    }

    public String a() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String b() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public Map<String, String> c() {
        return this.f;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public String f() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        return C$r8$backportedMethods$utility$Objects$2$equals.equals(this.a, eVar.a) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.b, eVar.b) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.c, eVar.c) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.d, eVar.d) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.e, eVar.e) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.f, eVar.f);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.c, this.d, this.e, this.f});
    }
}
