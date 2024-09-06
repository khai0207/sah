package com.netease.nimlib.chatroom.b;

import android.os.SystemClock;
import com.netease.nimlib.chatroom.d.r;
import com.netease.nimlib.chatroom.model.ChatRoomMessageImpl;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.session.IMMessageImpl;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: RoomTalkNotifyHandler.java */
/* loaded from: classes.dex */
public class n extends e {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        boolean z;
        long p = aVar.j() != null ? aVar.j().p() : 0L;
        int s = aVar.s();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (aVar instanceof r) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(((r) aVar).a());
            String o = aVar.j().o();
            com.netease.nimlib.log.b.d("RoomTalkNotifyHandler", "RoomTalkNotify roomId = " + o);
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                IMMessageImpl a = com.netease.nimlib.chatroom.g.a((com.netease.nimlib.push.packet.b.c) it.next(), true);
                if (a == null) {
                    com.netease.nimlib.log.b.d("RoomTalkNotifyHandler", "history == null ");
                } else {
                    if (z2) {
                        z = z2;
                    } else {
                        com.netease.nimlib.chatroom.a.a a2 = com.netease.nimlib.chatroom.a.b.d().a(o);
                        if (a2 != null) {
                            a2.a(a.getTime());
                        }
                        z = true;
                    }
                    a.setDirect(MsgDirectionEnum.In);
                    arrayList2.add(a);
                    long j = p;
                    com.netease.nimlib.n.f.a().a(a, p, s, elapsedRealtime);
                    if (a instanceof ChatRoomMessageImpl) {
                        ChatRoomMessageImpl chatRoomMessageImpl = (ChatRoomMessageImpl) a;
                        com.netease.nimlib.log.b.c("RoomTalkNotifyHandler", String.format("processResponse messages %s %s %s", chatRoomMessageImpl.getMsgType(), chatRoomMessageImpl.getAttachStr(), chatRoomMessageImpl.getNotifyTargetTags()));
                        Boolean isNeedHighPriorityMsgAck = chatRoomMessageImpl.isNeedHighPriorityMsgAck();
                        com.netease.nimlib.log.b.c("RoomTalkNotifyHandler", "processResponse messages isNeedHighPriorityMsgAck = " + isNeedHighPriorityMsgAck);
                        if (isNeedHighPriorityMsgAck != null && isNeedHighPriorityMsgAck.booleanValue()) {
                            com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
                            cVar.a(1, chatRoomMessageImpl.getUuid());
                            cVar.a(2, o);
                            com.netease.nimlib.chatroom.d.e().a(new com.netease.nimlib.chatroom.o(o, new com.netease.nimlib.chatroom.c.b(cVar)) { // from class: com.netease.nimlib.chatroom.b.n.1
                                AnonymousClass1(String o2, com.netease.nimlib.biz.d.a aVar2) {
                                    super(o2, aVar2);
                                }

                                @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                                public void a(com.netease.nimlib.biz.e.a aVar2) {
                                    super.a(aVar2);
                                    com.netease.nimlib.log.b.c("RoomTalkNotifyHandler", String.format("ChatRoomMsgAckResponse result = %s", Short.valueOf(aVar2.r())));
                                }
                            }, o2);
                            z2 = z;
                            p = j;
                        }
                    }
                    z2 = z;
                    p = j;
                }
            }
            com.netease.nimlib.chatroom.c.a().u(o2).a(arrayList2);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(arrayList2);
            com.netease.nimlib.chatroom.g.a((ArrayList<IMMessageImpl>) arrayList3);
            com.netease.nimlib.chatroom.c.a().u(o2).b(arrayList2);
            return;
        }
        com.netease.nimlib.log.b.f("RoomTalkNotifyHandler", "response is not instanceof RoomTalkNotify");
    }

    /* compiled from: RoomTalkNotifyHandler.java */
    /* renamed from: com.netease.nimlib.chatroom.b.n$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends com.netease.nimlib.chatroom.o {
        AnonymousClass1(String o2, com.netease.nimlib.biz.d.a aVar2) {
            super(o2, aVar2);
        }

        @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
        public void a(com.netease.nimlib.biz.e.a aVar2) {
            super.a(aVar2);
            com.netease.nimlib.log.b.c("RoomTalkNotifyHandler", String.format("ChatRoomMsgAckResponse result = %s", Short.valueOf(aVar2.r())));
        }
    }
}
