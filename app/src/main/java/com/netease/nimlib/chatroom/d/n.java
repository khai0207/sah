package com.netease.nimlib.chatroom.d;

import com.unionpay.tsmservice.data.Constant;
import u.aly.df;

/* compiled from: KickOutRoomNotify.java */
@com.netease.nimlib.biz.e.b(a = df.k, b = {Constant.APPLY_MODE_DECIDED_BY_BANK})
/* loaded from: classes.dex */
public class n extends com.netease.nimlib.biz.e.a {
    private int c;
    private String d;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.f();
        this.d = fVar.e();
        return null;
    }

    public int a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }
}
