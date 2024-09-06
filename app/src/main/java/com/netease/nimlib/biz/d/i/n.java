package com.netease.nimlib.biz.d.i;

import com.netease.nimlib.o.f;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.model.MsgTimingFullKeywordSearchConfig;
import com.netease.nimlib.sdk.msg.model.SearchOrderEnum;
import java.util.Collection;
import java.util.List;

/* compiled from: MsgTimingFullKeywordSearchRequest.java */
/* loaded from: classes.dex */
public class n extends com.netease.nimlib.biz.d.a {
    private final String a;
    private final long b;
    private final long c;
    private final int d;
    private final int e;
    private final List<String> f;
    private final List<String> g;
    private final List<String> h;
    private final List<MsgTypeEnum> i;
    private final List<Integer> j;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 27;
    }

    public static n a(MsgTimingFullKeywordSearchConfig msgTimingFullKeywordSearchConfig) {
        if (msgTimingFullKeywordSearchConfig == null) {
            return null;
        }
        return new n(w.b(msgTimingFullKeywordSearchConfig.getKeyword()), Math.max(0L, msgTimingFullKeywordSearchConfig.getFromTime()), Math.max(0L, msgTimingFullKeywordSearchConfig.getToTime()), Math.max(0, msgTimingFullKeywordSearchConfig.getMsgLimit()), msgTimingFullKeywordSearchConfig.getOrder() == SearchOrderEnum.ASC ? 1 : 2, msgTimingFullKeywordSearchConfig.getP2pList(), msgTimingFullKeywordSearchConfig.getTeamList(), msgTimingFullKeywordSearchConfig.getSenderList(), msgTimingFullKeywordSearchConfig.getMsgTypeList(), msgTimingFullKeywordSearchConfig.getMsgSubtypeList());
    }

    public n(String str, long j, long j2, int i, int i2, List<String> list, List<String> list2, List<String> list3, List<MsgTypeEnum> list4, List<Integer> list5) {
        this.a = str;
        this.b = j;
        this.c = j2;
        this.d = i;
        this.e = i2;
        this.f = list;
        this.g = list2;
        this.h = list3;
        this.i = list4;
        this.j = list5;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a);
        cVar.a(2, this.b);
        cVar.a(3, this.c);
        cVar.a(5, this.d);
        cVar.a(6, this.e);
        if (!com.netease.nimlib.o.f.c((Collection) this.f)) {
            cVar.a(7, com.netease.nimlib.o.f.a(this.f, ","));
        }
        if (!com.netease.nimlib.o.f.c((Collection) this.g)) {
            cVar.a(8, com.netease.nimlib.o.f.a(this.g, ","));
        }
        if (!com.netease.nimlib.o.f.c((Collection) this.h)) {
            cVar.a(9, com.netease.nimlib.o.f.a(this.h, ","));
        }
        if (!com.netease.nimlib.o.f.c((Collection) this.i)) {
            cVar.a(10, com.netease.nimlib.o.f.a(this.i, ",", new f.a() { // from class: com.netease.nimlib.biz.d.i.-$$Lambda$n$KECe75zDIv9pIKfCCgggibx-18E
                @Override // com.netease.nimlib.o.f.a
                public final Object transform(Object obj) {
                    String a;
                    a = n.a((MsgTypeEnum) obj);
                    return a;
                }
            }));
        }
        if (!com.netease.nimlib.o.f.c((Collection) this.j)) {
            cVar.a(11, com.netease.nimlib.o.f.a(this.j, ","));
        }
        return new com.netease.nimlib.push.packet.c.b().a(cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String a(MsgTypeEnum msgTypeEnum) {
        return String.valueOf(msgTypeEnum == null ? null : Integer.valueOf(msgTypeEnum.getValue()));
    }
}
