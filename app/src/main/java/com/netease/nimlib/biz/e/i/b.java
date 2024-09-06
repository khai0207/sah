package com.netease.nimlib.biz.e.i;

import com.netease.nimlib.push.packet.c.d;
import com.netease.nimlib.push.packet.c.f;
import com.talkingdata.sdk.aj;
import java.util.List;

/* compiled from: KickSelfResponse.java */
@com.netease.nimlib.biz.e.b(a = 2, b = {aj.c})
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.e.a {
    private List<String> c;

    @Override // com.netease.nimlib.biz.e.a
    public f a(f fVar) throws Exception {
        this.c = d.b(fVar);
        return null;
    }

    public List<String> a() {
        return this.c;
    }
}
