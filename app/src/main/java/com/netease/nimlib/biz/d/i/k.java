package com.netease.nimlib.biz.d.i;

import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.team.model.IMMessageFilter;
import u.aly.df;

/* compiled from: GetRoamingMessageRequest.java */
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.biz.d.a {
    private SessionTypeEnum a;
    private String b;
    private long c;
    private long d;
    private long e;
    private int f;
    private boolean g;
    private boolean h;
    private MsgTypeEnum[] i;
    private boolean j;
    private IMMessageFilter k;
    private boolean l;

    public k(String str, SessionTypeEnum sessionTypeEnum, long j, long j2, long j3, int i, boolean z, boolean z2, MsgTypeEnum[] msgTypeEnumArr, boolean z3, IMMessageFilter iMMessageFilter, boolean z4) {
        this.b = str;
        this.a = sessionTypeEnum;
        this.c = j;
        this.d = j2;
        this.e = j3;
        this.f = i;
        this.g = z;
        this.h = z2;
        this.i = msgTypeEnumArr;
        this.j = z3;
        this.k = iMMessageFilter;
        this.l = z4;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        if (this.a == SessionTypeEnum.Team || this.a == SessionTypeEnum.SUPER_TEAM) {
            bVar.b(this.b);
        } else {
            bVar.a(this.b);
        }
        bVar.a(this.c);
        bVar.a(this.d);
        bVar.a(this.e);
        bVar.a(this.f);
        bVar.a(this.g);
        MsgTypeEnum[] msgTypeEnumArr = this.i;
        if (msgTypeEnumArr != null && msgTypeEnumArr.length > 0) {
            bVar.b(msgTypeEnumArr.length);
            int length = this.i.length;
            for (int i = 0; i < length; i++) {
                bVar.a(r1[i].getValue());
            }
        }
        return bVar;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        if (this.a == SessionTypeEnum.Team) {
            return (byte) 8;
        }
        return this.a == SessionTypeEnum.SUPER_TEAM ? (byte) 21 : (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        if (this.a == SessionTypeEnum.Team) {
            return (byte) 23;
        }
        if (this.a == SessionTypeEnum.SUPER_TEAM) {
            return df.l;
        }
        return (byte) 6;
    }

    public boolean d() {
        return this.g;
    }

    public boolean e() {
        return this.h;
    }

    public MsgTypeEnum[] f() {
        return this.i;
    }

    public boolean g() {
        return this.j;
    }

    public IMMessageFilter h() {
        return this.k;
    }

    public boolean m() {
        return this.l;
    }
}
