package com.netease.nimlib.session.a;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

/* compiled from: SaveSyncMessageSituation.java */
/* loaded from: classes.dex */
public class b {
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private final String e;
    private final SessionTypeEnum f;
    private final a g;

    /* compiled from: SaveSyncMessageSituation.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(String str, SessionTypeEnum sessionTypeEnum);
    }

    public b(String str, SessionTypeEnum sessionTypeEnum, a aVar) {
        this.e = str;
        this.f = sessionTypeEnum;
        this.g = aVar;
    }

    public void a() {
        this.a = true;
    }

    public synchronized void b() {
        if (this.b) {
            return;
        }
        this.b = true;
        if (e() && this.g != null) {
            this.g.a(this.e, this.f);
        }
    }

    public void c() {
        this.c = true;
    }

    public synchronized void d() {
        if (this.d) {
            return;
        }
        this.d = true;
        if (e() && this.g != null) {
            this.g.a(this.e, this.f);
        }
    }

    public boolean e() {
        return this.b && this.d;
    }
}
