package com.netease.nimlib.biz.e.d;

import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

/* compiled from: UploadLogNotify.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {Constant.APPLY_MODE_DECIDED_BY_BANK})
/* loaded from: classes.dex */
public class q extends com.netease.nimlib.biz.e.a {
    private int c = 0;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        String c = com.netease.nimlib.push.packet.c.d.a(fVar).c(5);
        if (c == null || c.length() <= 0) {
            return null;
        }
        this.c = new JSONObject(c).optInt("logUploadType", 0);
        return null;
    }

    public int a() {
        return this.c;
    }
}
