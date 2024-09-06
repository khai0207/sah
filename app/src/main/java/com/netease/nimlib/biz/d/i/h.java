package com.netease.nimlib.biz.d.i;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: DeleteRecentContactRequest.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.biz.d.a {
    private List<String> a = new ArrayList(1);

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 9;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.c.d.a(bVar, (Collection<?>) this.a);
        return bVar;
    }

    public void a(String str, SessionTypeEnum sessionTypeEnum) {
        this.a.add(a(sessionTypeEnum) + str);
    }

    public boolean d() {
        return this.a.isEmpty();
    }

    private String a(SessionTypeEnum sessionTypeEnum) {
        if (sessionTypeEnum == SessionTypeEnum.P2P) {
            return "p2p|";
        }
        if (sessionTypeEnum == SessionTypeEnum.Team) {
            return "team|";
        }
        if (sessionTypeEnum == SessionTypeEnum.Ysf) {
            return "ysf|";
        }
        throw new IllegalArgumentException("only support p2p and team.");
    }
}
