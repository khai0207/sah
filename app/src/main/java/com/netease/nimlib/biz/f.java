package com.netease.nimlib.biz;

import android.text.TextUtils;
import com.netease.nimlib.sdk.auth.OnlineClient;

/* compiled from: OnlineClientImpl.java */
/* loaded from: classes.dex */
public class f implements OnlineClient {
    private static final long serialVersionUID = 1;
    private int a;
    private String b;
    private long c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof f)) {
            return false;
        }
        return TextUtils.equals(this.f, ((f) obj).f);
    }

    public int hashCode() {
        String str = this.f;
        return str != null ? str.hashCode() : super.hashCode();
    }

    @Override // com.netease.nimlib.sdk.auth.OnlineClient
    public String getOs() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.auth.OnlineClient
    public int getClientType() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.auth.OnlineClient
    public long getLoginTime() {
        return this.c;
    }

    @Override // com.netease.nimlib.sdk.auth.OnlineClient
    public String getCustomTag() {
        return this.g;
    }

    @Override // com.netease.nimlib.sdk.auth.OnlineClient
    public int getCustomClientType() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(int i) {
        this.a = i;
    }

    public void a(long j) {
        this.c = j;
    }

    public void b(String str) {
        this.g = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public void d(String str) {
        this.d = str;
    }

    @Override // com.netease.nimlib.sdk.auth.OnlineClient
    public String getClientIp() {
        return this.d;
    }

    public void e(String str) {
        this.f = str;
    }

    public String a() {
        return this.f;
    }

    public static f a(com.netease.nimlib.push.packet.b.c cVar) {
        f fVar = new f();
        fVar.b(cVar.d(3));
        fVar.a(cVar.c(4));
        fVar.a(cVar.e(109));
        fVar.d(cVar.c(103));
        fVar.c(cVar.c(102));
        fVar.e(cVar.c(13));
        fVar.b(cVar.c(38));
        fVar.a(cVar.d(39));
        return fVar;
    }
}
