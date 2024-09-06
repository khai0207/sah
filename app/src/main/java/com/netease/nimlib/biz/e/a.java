package com.netease.nimlib.biz.e;

import com.netease.nimlib.push.packet.c.f;
import com.netease.nimlib.sdk.ResponseCode;

/* compiled from: Response.java */
/* loaded from: classes.dex */
public abstract class a {
    protected com.netease.nimlib.push.packet.a a;
    protected int b;
    private int c;

    public abstract f a(f fVar) throws Exception;

    protected boolean m() {
        return false;
    }

    /* compiled from: Response.java */
    /* renamed from: com.netease.nimlib.biz.e.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0029a {
        public com.netease.nimlib.push.packet.a a;
        public f b;
        public int c;

        public static C0029a a(com.netease.nimlib.push.packet.a aVar, short s) {
            if (aVar == null) {
                return null;
            }
            C0029a c0029a = new C0029a();
            com.netease.nimlib.push.packet.a a = aVar.a();
            c0029a.a = a;
            a.b(s);
            return c0029a;
        }

        public boolean a() {
            com.netease.nimlib.push.packet.a aVar = this.a;
            return aVar != null && aVar.n() <= 20971520 && this.a.i() >= 0 && this.a.i() <= Byte.MAX_VALUE && this.a.j() >= 0 && this.a.l() >= 0;
        }
    }

    public com.netease.nimlib.push.packet.a j() {
        return this.a;
    }

    public void a(com.netease.nimlib.push.packet.a aVar) {
        this.a = aVar;
    }

    public boolean k() {
        return m() || n();
    }

    public void a(int i) {
        this.b = i;
    }

    public int l() {
        return this.b;
    }

    public boolean n() {
        com.netease.nimlib.push.packet.a aVar = this.a;
        return aVar != null && aVar.l() == 200;
    }

    public boolean o() {
        com.netease.nimlib.push.packet.a aVar = this.a;
        return aVar != null && aVar.k() == 0;
    }

    public short p() {
        com.netease.nimlib.push.packet.a aVar = this.a;
        if (aVar != null) {
            return aVar.k();
        }
        return (short) 0;
    }

    public byte q() {
        com.netease.nimlib.push.packet.a aVar = this.a;
        if (aVar != null) {
            return aVar.j();
        }
        return (byte) 0;
    }

    public short r() {
        com.netease.nimlib.push.packet.a aVar = this.a;
        return aVar != null ? aVar.l() : ResponseCode.RES_EUNKNOWN;
    }

    public int s() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }
}
