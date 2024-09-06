package com.netease.nimlib.session.a;

import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.x;

/* compiled from: SyncSessionReliableInfo.java */
/* loaded from: classes.dex */
public class f {
    private final String a;
    private final SessionTypeEnum b;
    private final x c;
    private long d;
    private String e;
    private long f;
    private long g;
    private String h;
    private long i;
    private long j;
    private String k;
    private long l;
    private boolean m;
    private boolean n;
    private boolean o;

    public static f a(com.netease.nimlib.push.packet.b.c cVar) {
        f fVar = new f(cVar.c(2), cVar.d(1) == 2 ? SessionTypeEnum.Team : SessionTypeEnum.P2P, x.a(cVar.d(3)));
        fVar.a(cVar.e(4));
        fVar.a(cVar.c(5));
        fVar.b(cVar.e(6));
        fVar.c(cVar.e(7));
        fVar.b(cVar.c(8));
        fVar.d(cVar.e(9));
        fVar.e(cVar.e(10));
        fVar.c(cVar.c(11));
        fVar.f(cVar.e(12));
        fVar.a(cVar.d(13) > 0);
        fVar.b(cVar.d(14) > 0);
        fVar.c(cVar.d(15) > 0);
        return fVar;
    }

    public d a() {
        boolean z;
        d queryLastSessionReliableInfo;
        String b = b();
        SessionTypeEnum c = c();
        d dVar = new d(b, c);
        if (w.a((CharSequence) b) || c == null || !o() || (queryLastSessionReliableInfo = MsgDBHelper.queryLastSessionReliableInfo(b, c)) == null || !queryLastSessionReliableInfo.a(d.a(b, c, l(), j(), k()))) {
            z = false;
        } else {
            dVar.a(l(), j(), k());
            z = true;
        }
        if (!z) {
            if (!m()) {
                return dVar;
            }
            dVar.a(i(), g(), h());
        }
        if (n()) {
            dVar.b(f(), d(), e());
        } else if (z) {
            dVar.b(l(), j(), k());
        }
        return dVar;
    }

    private f(String str, SessionTypeEnum sessionTypeEnum, x xVar) {
        this.a = str;
        this.b = sessionTypeEnum;
        this.c = xVar;
    }

    public String b() {
        return this.a;
    }

    public SessionTypeEnum c() {
        return this.b;
    }

    public long d() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public String e() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public long f() {
        return this.f;
    }

    public void b(long j) {
        this.f = j;
    }

    public long g() {
        return this.g;
    }

    public void c(long j) {
        this.g = j;
    }

    public String h() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public long i() {
        return this.i;
    }

    public void d(long j) {
        this.i = j;
    }

    public long j() {
        return this.j;
    }

    public void e(long j) {
        this.j = j;
    }

    public String k() {
        return this.k;
    }

    public void c(String str) {
        this.k = str;
    }

    public long l() {
        return this.l;
    }

    public void f(long j) {
        this.l = j;
    }

    public boolean m() {
        return this.i > 0 && w.b((CharSequence) this.h) && this.g > 0;
    }

    public boolean n() {
        return this.f > 0 && w.b((CharSequence) this.e) && this.d > 0;
    }

    public boolean o() {
        return this.l > 0 && w.b((CharSequence) this.k) && this.j > 0;
    }

    public boolean p() {
        return this.m;
    }

    private void a(boolean z) {
        this.m = z;
    }

    public boolean q() {
        return this.n;
    }

    private void b(boolean z) {
        this.n = z;
    }

    private void c(boolean z) {
        this.o = z;
    }

    public String toString() {
        return "SyncSessionReliableInfo{sessionId='" + this.a + "', sessionType=" + this.b + ", syncStatus=" + this.c + ", syncStartMessageTime=" + this.i + ", syncStartMessageIdServer=" + this.g + ", syncStartMessageIdClient='" + this.h + "', syncStopMessageTime=" + this.f + ", syncStopMessageIdServer=" + this.d + ", syncStopMessageIdClient='" + this.e + "', nextMessageTime=" + this.l + ", nextMessageIdServer=" + this.j + ", nextMessageIdClient='" + this.k + "', syncRoamMsg=" + this.m + ", syncOfflineMsg=" + this.n + ", syncNetCallOfflineMsg=" + this.o + '}';
    }
}
