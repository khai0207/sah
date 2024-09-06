package com.netease.nimlib.search;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.session.l;

/* compiled from: SearchPluginManager.java */
/* loaded from: classes.dex */
public class b {
    private l a;

    /* compiled from: SearchPluginManager.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final b a = new b();
    }

    public boolean a() {
        return f();
    }

    public void b() {
        if (f()) {
            e().a();
        }
    }

    public void c() {
        if (f()) {
            e().c();
        }
    }

    public void a(long j) {
        if (f()) {
            e().a(j);
        }
    }

    public void a(SessionTypeEnum sessionTypeEnum, String str) {
        if (f()) {
            e().a(sessionTypeEnum, str);
        }
    }

    public void a(SessionTypeEnum sessionTypeEnum, String str, long j, long j2) {
        if (f()) {
            e().a(sessionTypeEnum, str, j, j2);
        }
    }

    public void d() {
        if (f()) {
            e().d();
        }
    }

    public synchronized void a(l lVar) {
        this.a = lVar;
    }

    public l e() {
        return this.a;
    }

    public boolean f() {
        return this.a != null;
    }

    public static b g() {
        return a.a;
    }
}
