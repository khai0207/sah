package com.netease.nimlib.biz.e.l;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;

/* compiled from: UpdateMemberInfoResponse.java */
@com.netease.nimlib.biz.e.b(a = 8, b = {"19"})
/* loaded from: classes.dex */
public class w extends com.netease.nimlib.biz.e.a {
    private com.netease.nimlib.push.packet.b.c c;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        com.netease.nimlib.push.packet.a j = j();
        if (fVar != null && fVar.a() != 0) {
            this.c = com.netease.nimlib.push.packet.c.d.a(fVar);
            if (j != null) {
                com.netease.nimlib.log.b.J("************ UpdateMemberInfoResponse begin ****************");
                com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
                com.netease.nimlib.log.b.a(j.i(), j.j(), "memberInfo", this.c);
                com.netease.nimlib.log.b.J("************ UpdateMemberInfoResponse end ****************");
            }
            return null;
        }
        if (j != null) {
            com.netease.nimlib.log.b.J("************ UpdateMemberInfoResponse begin ****************");
            com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
            byte i = j.i();
            byte j2 = j.j();
            StringBuilder sb = new StringBuilder();
            sb.append("unpack == ");
            sb.append(fVar == null ? Constants.NULL_VERSION_ID : "empty");
            com.netease.nimlib.log.b.a(i, j2, sb.toString());
            com.netease.nimlib.log.b.J("************ UpdateMemberInfoResponse end ****************");
        }
        return null;
    }

    public com.netease.nimlib.push.packet.b.c a() {
        return this.c;
    }
}
