package com.netease.nimlib.biz.e.h;

import com.netease.nimlib.biz.e.b;
import com.netease.nimlib.push.packet.c.d;
import com.netease.nimlib.push.packet.c.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: QChatTokenResponse.java */
@b(a = 24, b = {"1#2"})
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.biz.e.a {
    private List<String> c = new ArrayList();

    @Override // com.netease.nimlib.biz.e.a
    public f a(f fVar) throws Exception {
        this.c.addAll(d.b(fVar));
        com.netease.nimlib.push.packet.a j = j();
        if (j == null) {
            return null;
        }
        com.netease.nimlib.log.b.J("************ QChatTokenResponse begin ****************");
        com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
        com.netease.nimlib.log.b.a(j.i(), j.j(), "linkList = " + this.c);
        com.netease.nimlib.log.b.J("************ QChatTokenResponse end ****************");
        return null;
    }

    public List<String> a() {
        return this.c;
    }

    public void a(List<String> list) {
        this.c.clear();
        this.c.addAll(list);
    }
}
