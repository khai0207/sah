package com.netease.nimlib.biz.d.i;

import android.util.Pair;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.util.Collection;
import java.util.List;

/* compiled from: GetSessionReliableInfoRequest.java */
/* loaded from: classes.dex */
public class l extends com.netease.nimlib.biz.d.a {
    private List<Pair<SessionTypeEnum, String>> a;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 28;
    }

    public l(List<Pair<SessionTypeEnum, String>> list) {
        this.a = list;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        if (com.netease.nimlib.o.f.c((Collection) this.a)) {
            return bVar;
        }
        bVar.b(this.a.size());
        for (Pair<SessionTypeEnum, String> pair : this.a) {
            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
            SessionTypeEnum sessionTypeEnum = (SessionTypeEnum) pair.first;
            if (sessionTypeEnum != null) {
                int i = AnonymousClass1.a[sessionTypeEnum.ordinal()];
                int i2 = 3;
                if (i == 1) {
                    i2 = 1;
                } else if (i == 2) {
                    i2 = 2;
                } else if (i != 3) {
                }
                cVar.a(1, i2);
                cVar.a(2, (String) pair.second);
                bVar.a(cVar);
            }
        }
        return bVar;
    }

    /* compiled from: GetSessionReliableInfoRequest.java */
    /* renamed from: com.netease.nimlib.biz.d.i.l$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[SessionTypeEnum.values().length];
            a = iArr;
            try {
                iArr[SessionTypeEnum.P2P.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[SessionTypeEnum.Team.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[SessionTypeEnum.SUPER_TEAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
