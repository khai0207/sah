package com.netease.nimlib.net.a.a;

import com.alipay.sdk.app.OpenAuthTask;
import com.netease.nimlib.o.m;
import java.util.HashMap;
import java.util.Map;

/* compiled from: HttpDownloadInfo.java */
/* loaded from: classes.dex */
public class e {
    private String a;
    private String b;
    private String c;
    private String d;
    private long e;
    private boolean f;
    private f g;
    private boolean h;
    private final Map<String, String> i;
    private int j;
    private int k;
    private com.netease.nimlib.n.b.g l;

    public boolean b() {
        return this.h;
    }

    public Map<String, String> c() {
        return this.i;
    }

    public int d() {
        return this.j;
    }

    public int e() {
        return this.k;
    }

    public e(String str, String str2, f fVar) {
        this(str, str2, fVar, 0L);
    }

    public e(String str, String str2, f fVar, long j) {
        this.f = false;
        this.h = true;
        this.i = new HashMap();
        this.j = 3;
        this.k = OpenAuthTask.Duplex;
        this.l = com.netease.nimlib.n.b.g.CDN;
        this.b = str;
        this.c = str2;
        this.g = fVar;
        this.e = j;
        this.d = str2 + "@url#" + m.a(str);
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(String str, boolean z, Map<String, String> map, int i, int i2, f fVar, com.netease.nimlib.n.b.g gVar) {
        this.b = str;
        this.h = z;
        if (map != null) {
            this.i.putAll(map);
        }
        this.j = i;
        this.k = i2;
        this.g = fVar;
        this.l = gVar;
    }

    public String f() {
        return this.b;
    }

    public String g() {
        return this.c;
    }

    public String h() {
        return this.d;
    }

    public long i() {
        return this.e;
    }

    public void b(long j) {
        this.e = j;
    }

    public void j() {
        this.f = true;
        f fVar = this.g;
        if (fVar != null) {
            fVar.onCancel(this);
        }
    }

    public boolean k() {
        return this.f;
    }

    public f l() {
        return this.g;
    }

    public com.netease.nimlib.n.b.g m() {
        return this.l;
    }

    public String n() {
        return this.a;
    }

    public void b(String str) {
        this.a = str;
    }
}
