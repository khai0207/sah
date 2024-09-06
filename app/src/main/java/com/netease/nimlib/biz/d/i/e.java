package com.netease.nimlib.biz.d.i;

import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import java.util.Iterator;
import java.util.List;

/* compiled from: DeleteMsgSelfBatchRequest.java */
/* loaded from: classes.dex */
public class e extends com.netease.nimlib.biz.d.a {
    private List<IMMessage> a;
    private String b;

    @Override // com.netease.nimlib.biz.d.a
    public byte b() {
        return (byte) 7;
    }

    @Override // com.netease.nimlib.biz.d.a
    public byte c() {
        return (byte) 24;
    }

    public e(List<IMMessage> list, String str) {
        this.a = list;
        this.b = str;
    }

    @Override // com.netease.nimlib.biz.d.a
    public com.netease.nimlib.push.packet.c.b a() {
        com.netease.nimlib.push.packet.c.b bVar = new com.netease.nimlib.push.packet.c.b();
        List<IMMessage> list = this.a;
        if (list != null && !list.isEmpty()) {
            bVar.b(this.a.size());
            Iterator<IMMessage> it = this.a.iterator();
            while (it.hasNext()) {
                com.netease.nimlib.push.packet.b.c a = a(it.next());
                if (a != null) {
                    bVar.a(a);
                }
            }
        }
        return bVar;
    }

    private com.netease.nimlib.push.packet.b.c a(IMMessage iMMessage) {
        int i;
        if (iMMessage == null) {
            com.netease.nimlib.log.b.d("DeleteMsgSelfBatchRequest", "msg is null");
            return null;
        }
        String sessionId = iMMessage.getSessionId();
        int i2 = AnonymousClass1.a[iMMessage.getSessionType().ordinal()];
        if (i2 == 1) {
            if (iMMessage.getDirect() == MsgDirectionEnum.In) {
                sessionId = com.netease.nimlib.c.n();
            }
            i = 1;
        } else {
            if (i2 != 2 && i2 != 3) {
                com.netease.nimlib.log.b.d("DeleteMsgSelfBatchRequest", "msg type unsupported, type=" + iMMessage.getSessionType().name());
                return null;
            }
            i = 2;
        }
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, i);
        cVar.a(2, iMMessage.getFromAccount());
        cVar.a(3, sessionId);
        cVar.a(4, iMMessage.getServerId());
        cVar.a(5, iMMessage.getUuid());
        cVar.a(6, iMMessage.getTime());
        cVar.a(7, System.currentTimeMillis());
        cVar.a(8, this.b);
        return cVar;
    }

    /* compiled from: DeleteMsgSelfBatchRequest.java */
    /* renamed from: com.netease.nimlib.biz.d.i.e$1, reason: invalid class name */
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
