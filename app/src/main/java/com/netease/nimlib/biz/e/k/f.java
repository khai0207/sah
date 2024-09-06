package com.netease.nimlib.biz.e.k;

import com.unionpay.tsmservice.data.Constant;

/* compiled from: AddQuickCommentResponse.java */
@com.netease.nimlib.biz.e.b(a = 23, b = {Constant.APPLY_MODE_DECIDED_BY_BANK})
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.e.a {
    private long c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = fVar.h();
        return null;
    }

    public long a() {
        return this.c;
    }
}
