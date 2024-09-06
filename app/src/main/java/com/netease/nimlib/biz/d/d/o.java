package com.netease.nimlib.biz.d.d;

import u.aly.df;

/* compiled from: StatReportRequest.java */
/* loaded from: classes.dex */
public class o extends com.netease.nimlib.biz.d.a {
    private final String a;
    private final int b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 6;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.m;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        return new com.netease.nimlib.push.packet.c.b().a(this.a);
    }

    public int d() {
        return this.b;
    }
}
