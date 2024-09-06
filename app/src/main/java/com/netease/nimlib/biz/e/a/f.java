package com.netease.nimlib.biz.e.a;

import com.talkingdata.sdk.aj;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.List;
import u.aly.df;

/* compiled from: SubscribeEventResponse.java */
@com.netease.nimlib.biz.e.b(a = df.l, b = {Constant.APPLY_MODE_DECIDED_BY_BANK, aj.a})
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.e.a {
    private List<String> c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        this.c = com.netease.nimlib.push.packet.c.d.b(fVar);
        return null;
    }

    public ArrayList<String> a() {
        if (this.c == null) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>(this.c.size());
        arrayList.addAll(this.c);
        return arrayList;
    }
}
