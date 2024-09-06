package com.netease.nimlib.chatroom.c;

import java.util.Collection;
import java.util.List;
import u.aly.df;

/* compiled from: GetMemberRoleListRequest.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.biz.d.a {
    private List<String> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return df.k;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return df.n;
    }

    public h(List<String> list) {
        this.a = list;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.a);
        return bVar;
    }
}
