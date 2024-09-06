package com.netease.nimlib.biz.c.i;

import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MsgDeleteSelfOption;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* compiled from: DeleteMsgSelfResponseHandler.java */
/* loaded from: classes.dex */
public class c extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, (Object) null);
            return;
        }
        if (aVar instanceof com.netease.nimlib.biz.e.j.e) {
            a((com.netease.nimlib.biz.e.j.e) aVar);
            return;
        }
        if (aVar instanceof com.netease.nimlib.biz.e.j.d) {
            a((com.netease.nimlib.biz.e.j.d) aVar);
            return;
        }
        if (aVar instanceof com.netease.nimlib.biz.e.j.g) {
            a((com.netease.nimlib.biz.e.j.g) aVar);
        } else if (aVar instanceof com.netease.nimlib.biz.e.j.f) {
            a((com.netease.nimlib.biz.e.j.f) aVar);
        } else if (aVar instanceof com.netease.nimlib.biz.e.f.b) {
            a((com.netease.nimlib.biz.e.f.b) aVar);
        }
    }

    private void a(com.netease.nimlib.biz.e.j.e eVar) {
        long a = eVar.a();
        com.netease.nimlib.biz.l.v(a);
        a(eVar, Long.valueOf(a));
    }

    private void a(com.netease.nimlib.biz.e.j.d dVar) {
        List<com.netease.nimlib.push.packet.b.c> a = dVar.a();
        if (com.netease.nimlib.o.f.c((Collection) a)) {
            com.netease.nimlib.i.b.f(new ArrayList(0));
            return;
        }
        ArrayList c = com.netease.nimlib.o.f.c(a, $$Lambda$Pxx4BD9B0CaylUSN2aof1RjBAAg.INSTANCE);
        com.netease.nimlib.biz.l.v(c.get(0).getTime());
        com.netease.nimlib.i.b.f(a(c));
    }

    private void a(com.netease.nimlib.biz.e.j.g gVar) {
        long a = gVar.a();
        com.netease.nimlib.biz.l.v(a);
        a(gVar, Long.valueOf(a));
    }

    private void a(com.netease.nimlib.biz.e.j.f fVar) {
        MsgDeleteSelfOption msgDeleteSelfOption = new MsgDeleteSelfOption(fVar.a());
        com.netease.nimlib.biz.l.v(msgDeleteSelfOption.getTime());
        a(msgDeleteSelfOption);
    }

    private void a(com.netease.nimlib.biz.e.f.b bVar) {
        MsgDeleteSelfOption msgDeleteSelfOption;
        List<com.netease.nimlib.push.packet.b.c> a = bVar.a();
        ArrayList b = com.netease.nimlib.o.f.b(a, true, $$Lambda$Pxx4BD9B0CaylUSN2aof1RjBAAg.INSTANCE);
        int a2 = com.netease.nimlib.o.f.a(b, new Comparator() { // from class: com.netease.nimlib.biz.c.i.-$$Lambda$c$J51oNtio6KLMk4b3PhM9HRdrFQY
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int a3;
                a3 = c.a((MsgDeleteSelfOption) obj, (MsgDeleteSelfOption) obj2);
                return a3;
            }
        });
        if (a2 >= 0 && a2 < b.size() && (msgDeleteSelfOption = (MsgDeleteSelfOption) b.get(a2)) != null) {
            com.netease.nimlib.biz.l.v(msgDeleteSelfOption.getTime());
        }
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            a(new MsgDeleteSelfOption(it.next()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int a(MsgDeleteSelfOption msgDeleteSelfOption, MsgDeleteSelfOption msgDeleteSelfOption2) {
        if (msgDeleteSelfOption == null && msgDeleteSelfOption2 == null) {
            return 0;
        }
        if (msgDeleteSelfOption == null) {
            return -1;
        }
        if (msgDeleteSelfOption2 == null) {
            return 1;
        }
        return (msgDeleteSelfOption.getTime() > msgDeleteSelfOption2.getTime() ? 1 : (msgDeleteSelfOption.getTime() == msgDeleteSelfOption2.getTime() ? 0 : -1));
    }

    private void a(MsgDeleteSelfOption msgDeleteSelfOption) {
        IMMessageImpl b = b(msgDeleteSelfOption);
        if (b == null) {
            com.netease.nimlib.log.b.d("DeleteMsgSelfResponseHandler", "deleteLocalMsgAndNotify with empty message");
            com.netease.nimlib.i.b.b((IMMessageImpl) null);
        } else {
            MsgDBHelper.deleteMessage(b);
            com.netease.nimlib.session.j.b((IMMessage) b);
            com.netease.nimlib.i.b.b(b);
        }
    }

    private List<IMMessage> a(List<MsgDeleteSelfOption> list) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return new ArrayList(0);
        }
        MsgDeleteSelfOption msgDeleteSelfOption = list.get(0);
        if (MsgDBHelper.queryRecentContact(msgDeleteSelfOption.getSessionId(), msgDeleteSelfOption.getType()) == null) {
            return new ArrayList(0);
        }
        ArrayList c = com.netease.nimlib.o.f.c(list, new f.a() { // from class: com.netease.nimlib.biz.c.i.-$$Lambda$c$LY-GU_UVFE1INELCc9W8bcjZ_nw
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                IMMessageImpl b;
                b = c.this.b((MsgDeleteSelfOption) obj);
                return b;
            }
        });
        com.netease.nimlib.session.j.b((List<IMMessage>) c, false);
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IMMessageImpl b(MsgDeleteSelfOption msgDeleteSelfOption) {
        IMMessageImpl iMMessageImpl = null;
        if (msgDeleteSelfOption == null) {
            return null;
        }
        String sessionId = msgDeleteSelfOption.getSessionId();
        SessionTypeEnum type = msgDeleteSelfOption.getType();
        String deleteMsgClientId = msgDeleteSelfOption.getDeleteMsgClientId();
        if (sessionId != null && type != null && deleteMsgClientId != null) {
            iMMessageImpl = (IMMessageImpl) MessageBuilder.createEmptyMessage(sessionId, type, msgDeleteSelfOption.getDeleteMsgCreateTime());
            iMMessageImpl.setUuid(msgDeleteSelfOption.getDeleteMsgClientId());
            iMMessageImpl.setFromAccount(msgDeleteSelfOption.getFrom());
            try {
                iMMessageImpl.setServerId(Long.parseLong(msgDeleteSelfOption.getDeleteMsgServerId()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return iMMessageImpl;
    }
}
