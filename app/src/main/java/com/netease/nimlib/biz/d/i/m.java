package com.netease.nimlib.biz.d.i;

import com.netease.nimlib.o.f;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.model.MsgFullKeywordSearchConfig;
import java.util.Collection;
import java.util.List;

/* compiled from: MsgFullKeywordSearchRequest.java */
/* loaded from: classes.dex */
public class m extends com.netease.nimlib.biz.d.a {
    private final String a;
    private final long b;
    private final long c;
    private final int d;
    private final int e;
    private final int f;
    private final List<String> g;
    private final List<String> h;
    private final List<String> i;
    private final List<MsgTypeEnum> j;
    private final List<Integer> k;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 26;
    }

    public static m a(MsgFullKeywordSearchConfig msgFullKeywordSearchConfig) {
        if (msgFullKeywordSearchConfig == null) {
            return null;
        }
        return new m(w.b(msgFullKeywordSearchConfig.getKeyword()), Math.max(0L, msgFullKeywordSearchConfig.getFromTime()), Math.max(0L, msgFullKeywordSearchConfig.getToTime()), Math.max(0, msgFullKeywordSearchConfig.getSessionLimit()), Math.max(0, msgFullKeywordSearchConfig.getMsgLimit()), msgFullKeywordSearchConfig.isAsc() ? 1 : 2, msgFullKeywordSearchConfig.getP2pList(), msgFullKeywordSearchConfig.getTeamList(), msgFullKeywordSearchConfig.getSenderList(), msgFullKeywordSearchConfig.getMsgTypeList(), msgFullKeywordSearchConfig.getMsgSubtypeList());
    }

    public m(String str, long j, long j2, int i, int i2, int i3, List<String> list, List<String> list2, List<String> list3, List<MsgTypeEnum> list4, List<Integer> list5) {
        this.a = str;
        this.b = j;
        this.c = j2;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = list;
        this.h = list2;
        this.i = list3;
        this.j = list4;
        this.k = list5;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, this.a);
        cVar.a(2, this.b);
        cVar.a(3, this.c);
        cVar.a(4, this.d);
        cVar.a(5, this.e);
        cVar.a(6, this.f);
        if (!com.netease.nimlib.o.f.c((Collection) this.g)) {
            cVar.a(7, com.netease.nimlib.o.f.a(this.g, ","));
        }
        if (!com.netease.nimlib.o.f.c((Collection) this.h)) {
            cVar.a(8, com.netease.nimlib.o.f.a(this.h, ","));
        }
        if (!com.netease.nimlib.o.f.c((Collection) this.i)) {
            cVar.a(9, com.netease.nimlib.o.f.a(this.i, ","));
        }
        if (!com.netease.nimlib.o.f.c((Collection) this.j)) {
            cVar.a(10, com.netease.nimlib.o.f.a(this.j, ",", new f.a() { // from class: com.netease.nimlib.biz.d.i.-$$Lambda$m$Sb38S9ZnyPtATmGlbT7EGiaM-3M
                @Override // com.netease.nimlib.o.f.a
                public final Object transform(Object obj) {
                    String a;
                    a = m.a((MsgTypeEnum) obj);
                    return a;
                }
            }));
        }
        if (!com.netease.nimlib.o.f.c((Collection) this.k)) {
            cVar.a(11, com.netease.nimlib.o.f.a(this.k, ","));
        }
        return new com.netease.nimlib.push.packet.c.b().a(cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String a(MsgTypeEnum msgTypeEnum) {
        return String.valueOf(msgTypeEnum == null ? null : Integer.valueOf(msgTypeEnum.getValue()));
    }
}
