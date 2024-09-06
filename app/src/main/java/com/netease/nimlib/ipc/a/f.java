package com.netease.nimlib.ipc.a;

import android.text.TextUtils;
import com.netease.nimlib.biz.l;
import java.io.Serializable;
import org.json.JSONObject;

/* compiled from: SyncTimeTagData.java */
/* loaded from: classes.dex */
public class f implements Serializable {
    private long a = 0;
    private long b = 0;
    private long c = 0;
    private long d = 0;
    private long e = 0;
    private long f = 0;
    private long g = 0;
    private long h = 0;
    private long i = 0;
    private long j = 0;
    private long k = 0;
    private long l = 0;
    private long m = 0;
    private long n = 0;
    private long o = 0;
    private long p = 0;
    private long q = 0;
    private long r = 0;
    private long s = 0;
    private long t = 0;

    /* renamed from: u */
    private long f24u = 0;
    private long v = 0;
    private long w = 0;
    private long x = 0;
    private long y = 0;

    public String toString() {
        return "SyncTimeTagData{myUserInfoTimeTag=" + this.a + ", unreadMsgTimeTag=" + this.b + ", teamInfoTimeTag=" + this.c + ", noDisturbConfigTimeTag=" + this.d + ", avchatRecordsTimeTag=" + this.e + ", roamingMsgTimeTag=" + this.f + ", blackAndMuteListTimeTag=" + this.g + ", friendListTimeTag=" + this.h + ", friendInfoTimeTag=" + this.i + ", p2pSessionMsgReadTimeTag=" + this.j + ", myTeamMemberListTimeTag=" + this.k + ", dontPushConfigTimeTag=" + this.l + ", revokeMsgTimeTag=" + this.m + ", sessionAckListTimeTag=" + this.n + ", robotListTimeTag=" + this.o + ", lastBroadcastMsgId=" + this.p + ", signallingMsgTimeTag=" + this.q + ", superTeamInfoTimeTag=" + this.r + ", mySuperTeamMemberListTimeTag=" + this.s + ", superTeamRoamingMsgTimeTag=" + this.t + ", superTeamRevokeMsgTimeTag=" + this.f24u + ", superTeamSessionAckListTimeTag=" + this.v + ", deleteMsgSelfTimeTag=" + this.w + ", stickTopSessionTimeTag=" + this.x + ", sessionHistoryMsgDeleteTimeTag=" + this.y + '}';
    }

    public void a() {
        this.a = l.t();
        this.b = 0L;
        this.c = l.v();
        this.d = l.o();
        this.e = 0L;
        long x = l.x();
        this.f = x;
        this.g = l.z();
        this.h = l.y();
        this.i = l.u();
        this.j = l.A();
        this.k = l.B();
        this.l = l.s();
        this.m = l.p();
        if (com.netease.nimlib.c.i().sessionReadAck) {
            this.n = l.l();
        }
        this.o = l.i();
        this.p = l.j();
        this.q = 0L;
        this.r = l.w();
        this.s = l.C();
        this.t = x;
        this.f24u = l.q();
        if (com.netease.nimlib.c.i().sessionReadAck) {
            this.v = l.m();
        }
        this.w = l.F();
        if (com.netease.nimlib.c.i().notifyStickTopSession) {
            this.x = l.J();
        }
        this.y = l.K();
    }

    public String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("myUserInfoTimeTag", this.a);
            jSONObject.put("unreadMsgTimeTag", this.b);
            jSONObject.put("teamInfoTimeTag", this.c);
            jSONObject.put("noDisturbConfigTimeTag", this.d);
            jSONObject.put("avchatRecordsTimeTag", this.e);
            jSONObject.put("roamingMsgTimeTag", this.f);
            jSONObject.put("blackAndMuteListTimeTag", this.g);
            jSONObject.put("friendListTimeTag", this.h);
            jSONObject.put("friendInfoTimeTag", this.i);
            jSONObject.put("p2pSessionMsgReadTimeTag", this.j);
            jSONObject.put("myTeamMemberListTimeTag", this.k);
            jSONObject.put("dontPushConfigTimeTag", this.l);
            jSONObject.put("revokeMsgTimeTag", this.m);
            jSONObject.put("sessionAckListTimeTag", this.n);
            jSONObject.put("robotListTimeTag", this.o);
            jSONObject.put("lastBroadcastMsgId", this.p);
            jSONObject.put("signallingMsgTimeTag", this.q);
            jSONObject.put("superTeamInfoTimeTag", this.r);
            jSONObject.put("mySuperTeamMemberListTimeTag", this.s);
            jSONObject.put("superTeamRoamingMsgTimeTag", this.t);
            jSONObject.put("superTeamRevokeMsgTimeTag", this.f24u);
            jSONObject.put("superTeamSessionAckListTimeTag", this.v);
            jSONObject.put("deleteMsgSelfTimeTag", this.w);
            jSONObject.put("stickTopSessionTimeTag", this.x);
            jSONObject.put("sessionHistoryMsgDeleteTimeTag", this.y);
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static f a(String str) {
        f fVar = new f();
        if (TextUtils.isEmpty(str)) {
            return fVar;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            fVar.a = jSONObject.optLong("myUserInfoTimeTag", 0L);
            fVar.b = jSONObject.optLong("unreadMsgTimeTag", 0L);
            fVar.c = jSONObject.optLong("teamInfoTimeTag", 0L);
            fVar.d = jSONObject.optLong("noDisturbConfigTimeTag", 0L);
            fVar.e = jSONObject.optLong("avchatRecordsTimeTag", 0L);
            fVar.f = jSONObject.optLong("roamingMsgTimeTag", 0L);
            fVar.g = jSONObject.optLong("blackAndMuteListTimeTag", 0L);
            fVar.h = jSONObject.optLong("friendListTimeTag", 0L);
            fVar.i = jSONObject.optLong("friendInfoTimeTag", 0L);
            fVar.j = jSONObject.optLong("p2pSessionMsgReadTimeTag", 0L);
            fVar.k = jSONObject.optLong("myTeamMemberListTimeTag", 0L);
            fVar.l = jSONObject.optLong("dontPushConfigTimeTag", 0L);
            fVar.m = jSONObject.optLong("revokeMsgTimeTag", 0L);
            fVar.n = jSONObject.optLong("sessionAckListTimeTag", 0L);
            fVar.o = jSONObject.optLong("robotListTimeTag", 0L);
            fVar.p = jSONObject.optLong("lastBroadcastMsgId", 0L);
            fVar.q = jSONObject.optLong("signallingMsgTimeTag", 0L);
            fVar.r = jSONObject.optLong("superTeamInfoTimeTag", 0L);
            fVar.s = jSONObject.optLong("mySuperTeamMemberListTimeTag", 0L);
            fVar.t = jSONObject.optLong("superTeamRoamingMsgTimeTag", 0L);
            fVar.f24u = jSONObject.optLong("superTeamRevokeMsgTimeTag", 0L);
            fVar.v = jSONObject.optLong("superTeamSessionAckListTimeTag", 0L);
            fVar.w = jSONObject.optLong("deleteMsgSelfTimeTag", 0L);
            fVar.x = jSONObject.optLong("stickTopSessionTimeTag", 0L);
            fVar.y = jSONObject.optLong("sessionHistoryMsgDeleteTimeTag", 0L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fVar;
    }

    public long c() {
        return this.a;
    }

    public long d() {
        return this.b;
    }

    public long e() {
        return this.c;
    }

    public long f() {
        return this.d;
    }

    public long g() {
        return this.e;
    }

    public long h() {
        return this.f;
    }

    public long i() {
        return this.g;
    }

    public long j() {
        return this.h;
    }

    public long k() {
        return this.i;
    }

    public long l() {
        return this.j;
    }

    public long m() {
        return this.k;
    }

    public long n() {
        return this.l;
    }

    public long o() {
        return this.m;
    }

    public long p() {
        return this.n;
    }

    public long q() {
        return this.o;
    }

    public long r() {
        return this.p;
    }

    public long s() {
        return this.q;
    }

    public long t() {
        return this.r;
    }

    public long u() {
        return this.s;
    }

    public long v() {
        return this.t;
    }

    public long w() {
        return this.f24u;
    }

    public long x() {
        return this.v;
    }

    public long y() {
        return this.w;
    }

    public long z() {
        return this.x;
    }

    public long A() {
        return this.y;
    }
}
