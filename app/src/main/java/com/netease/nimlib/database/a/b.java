package com.netease.nimlib.database.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.netease.nimlib.session.MsgDBHelper;

/* compiled from: DataBaseWrapper.java */
/* loaded from: classes.dex */
public class b extends a {
    private final a a;
    private final String b;
    private final boolean c;
    private String d;
    private boolean e;

    public static /* synthetic */ Object a(Object obj) {
        return obj == null ? "" : obj;
    }

    public static /* synthetic */ String c(String str) {
        return str == null ? "" : str;
    }

    public static /* synthetic */ String d(String str) {
        return str == null ? "" : str;
    }

    protected b(a aVar) {
        this.a = aVar;
        this.b = null;
        this.c = false;
    }

    protected b(a aVar, String str) {
        this.a = aVar;
        this.b = str;
        this.c = true;
    }

    @Override // com.netease.nimlib.database.a.a
    public boolean a(Context context, String str, String str2, d[] dVarArr, int i) {
        this.d = str;
        this.e = this.a instanceof com.netease.nimlib.database.encrypt.b;
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        boolean a2 = this.a.a(context, str, str2, dVarArr, i);
        com.netease.nimlib.n.d.a().a(a, this.e);
        return a2;
    }

    @Override // com.netease.nimlib.database.a.a
    public boolean e() {
        return this.a.e();
    }

    @Override // com.netease.nimlib.database.a.a
    public void a(String str) {
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        this.a.a(str);
        com.netease.nimlib.n.d.a().a(a, this.e);
    }

    @Override // com.netease.nimlib.database.a.a
    public void a(String str, Object[] objArr) {
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        if (MsgDBHelper.abTestSelected) {
            com.netease.nimlib.o.a.a(objArr, $$Lambda$b$1SiITP4tRMZCQw5UuTrKV5dexAQ.INSTANCE);
        }
        this.a.a(str, objArr);
        com.netease.nimlib.n.d.a().a(a, this.e);
    }

    @Override // com.netease.nimlib.database.a.a
    public int a(String str, String str2) {
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        int a2 = this.a.a(str, str2);
        com.netease.nimlib.n.d.a().a(a, this.e);
        return a2;
    }

    @Override // com.netease.nimlib.database.a.a
    public int a(String str, String str2, String[] strArr) {
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        if (MsgDBHelper.abTestSelected) {
            com.netease.nimlib.o.a.a(strArr, $$Lambda$b$WN9KfI1hG_tpO7tqtFkYCXtPdDU.INSTANCE);
        }
        int a2 = this.a.a(str, str2, strArr);
        com.netease.nimlib.n.d.a().a(a, this.e);
        return a2;
    }

    @Override // com.netease.nimlib.database.a.a
    public Cursor b(String str) {
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        Cursor b = this.a.b(str);
        com.netease.nimlib.n.d.a().a(a, this.e);
        return b;
    }

    @Override // com.netease.nimlib.database.a.a
    public Cursor a(String str, String[] strArr) {
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        if (MsgDBHelper.abTestSelected) {
            com.netease.nimlib.o.a.a(strArr, $$Lambda$b$JTjyQrGxIAbrvvZ5MirDj7tDhE.INSTANCE);
        }
        Cursor a2 = this.a.a(str, strArr);
        com.netease.nimlib.n.d.a().a(a, this.e);
        return a2;
    }

    @Override // com.netease.nimlib.database.a.a
    public long a(String str, String str2, ContentValues contentValues) {
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        long a2 = this.a.a(str, str2, contentValues);
        com.netease.nimlib.n.d.a().a(a, this.e);
        return a2;
    }

    @Override // com.netease.nimlib.database.a.a
    public long b(String str, String str2, ContentValues contentValues) {
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        long b = this.a.b(str, str2, contentValues);
        com.netease.nimlib.n.d.a().a(a, this.e);
        return b;
    }

    @Override // com.netease.nimlib.database.a.a
    public long c(String str, String str2, ContentValues contentValues) {
        com.netease.nimlib.n.e.c a = com.netease.nimlib.n.d.a().a(this.d);
        long c = this.a.c(str, str2, contentValues);
        com.netease.nimlib.n.d.a().a(a, this.e);
        return c;
    }

    @Override // com.netease.nimlib.database.a.a
    public void f() {
        this.a.f();
    }

    @Override // com.netease.nimlib.database.a.a
    public void g() {
        this.a.g();
    }

    @Override // com.netease.nimlib.database.a.a
    public void h() {
        this.a.h();
    }

    @Override // com.netease.nimlib.database.a.a
    public void i() {
        this.a.i();
    }

    @Override // com.netease.nimlib.database.g
    public boolean a() {
        return this.a.a();
    }

    @Override // com.netease.nimlib.database.g
    public void b() {
        this.a.b();
    }

    @Override // com.netease.nimlib.database.g
    public void c() {
        this.a.c();
    }

    @Override // com.netease.nimlib.database.g
    public boolean d() {
        return this.a.d();
    }

    public String j() {
        return this.b;
    }
}
