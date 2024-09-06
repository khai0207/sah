package com.netease.nimlib.biz.d.j;

import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.sdk.msg.model.MsgThreadOption;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.QueryThreadTalkHistoryOption;
import com.netease.nimlib.session.IMMessageImpl;

/* compiled from: QueryThreadTalkHistoryRequest.java */
/* loaded from: classes.dex */
public class j extends com.netease.nimlib.biz.d.a {
    private final String a;
    private final String b;
    private final long c;
    private final long d;
    private final String e;
    private final SessionTypeEnum f;
    private final long g;
    private final long h;
    private final long i;
    private final int j;
    private final boolean k;
    private final boolean l;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 23;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 1;
    }

    public j(IMMessageImpl iMMessageImpl, long j, long j2, int i, QueryDirectionEnum queryDirectionEnum, boolean z) {
        if (iMMessageImpl.isThread()) {
            this.a = iMMessageImpl.getFromAccount();
            this.b = com.netease.nimlib.session.g.a(iMMessageImpl);
            this.c = iMMessageImpl.getTime();
            this.d = iMMessageImpl.getServerId();
            this.e = iMMessageImpl.getUuid();
        } else {
            MsgThreadOption threadOption = iMMessageImpl.getThreadOption();
            this.a = threadOption.getThreadMsgFromAccount();
            this.b = threadOption.getThreadMsgToAccount();
            this.c = threadOption.getThreadMsgTime();
            this.d = threadOption.getThreadMsgIdServer();
            this.e = threadOption.getThreadMsgIdClient();
        }
        this.f = iMMessageImpl.getSessionType();
        this.i = iMMessageImpl.getServerId();
        this.j = i;
        boolean z2 = queryDirectionEnum == QueryDirectionEnum.QUERY_NEW;
        this.k = z2;
        this.g = z2 ? iMMessageImpl.getTime() : j;
        this.h = this.k ? j2 : iMMessageImpl.getTime();
        this.l = z;
    }

    public j(MessageKey messageKey, QueryThreadTalkHistoryOption queryThreadTalkHistoryOption) {
        this.a = messageKey.getFromAccount();
        this.b = messageKey.getToAccount();
        this.c = messageKey.getTime();
        this.d = messageKey.getServerId();
        this.e = messageKey.getUuid();
        this.f = messageKey.getSessionType();
        this.i = queryThreadTalkHistoryOption.getExcludeMessageServerId();
        this.j = queryThreadTalkHistoryOption.getLimit();
        this.k = queryThreadTalkHistoryOption.getDirection() == QueryDirectionEnum.QUERY_NEW;
        this.g = queryThreadTalkHistoryOption.getFromTime();
        this.h = queryThreadTalkHistoryOption.getToTime();
        this.l = queryThreadTalkHistoryOption.isPersist();
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(0, this.f.getValue());
        cVar.a(2, this.a);
        cVar.a(1, this.b);
        cVar.a(7, this.c);
        cVar.a(12, this.d);
        cVar.a(11, this.e);
        bVar.a(cVar);
        com.netease.nimlib.push.packet.b.c cVar2 = new com.netease.nimlib.push.packet.b.c();
        cVar2.a(1, this.g);
        long j = this.h;
        if (j <= 0) {
            j = y.a() + com.umeng.analytics.a.i;
        }
        cVar2.a(2, j);
        cVar2.a(3, this.i);
        cVar2.a(4, this.j);
        cVar2.a(5, this.k ? 1 : 0);
        bVar.a(cVar2);
        return bVar;
    }

    public boolean d() {
        return this.l;
    }
}
