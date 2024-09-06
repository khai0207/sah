package com.netease.nimlib.biz.d.i;

import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/* compiled from: DeleteMsgSelfRequest.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.biz.d.a {
    private IMMessage a;
    private String b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 23;
    }

    public f(IMMessage iMMessage, String str) {
        this.a = iMMessage;
        this.b = str;
    }

    /* compiled from: DeleteMsgSelfRequest.java */
    /* renamed from: com.netease.nimlib.biz.d.i.f$1, reason: invalid class name */
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
        }
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        int i;
        String sessionId = this.a.getSessionId();
        int i2 = AnonymousClass1.a[this.a.getSessionType().ordinal()];
        if (i2 != 1) {
            i = i2 != 2 ? -1 : 2;
        } else {
            if (this.a.getDirect() == MsgDirectionEnum.In) {
                sessionId = com.netease.nimlib.c.n();
            }
            i = 1;
        }
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, i);
        cVar.a(2, this.a.getFromAccount());
        cVar.a(3, sessionId);
        cVar.a(4, this.a.getServerId());
        cVar.a(5, this.a.getUuid());
        cVar.a(6, this.a.getTime());
        cVar.a(7, System.currentTimeMillis());
        cVar.a(8, this.b);
        return new com.netease.nimlib.push.packet.c.b().a(cVar);
    }
}
