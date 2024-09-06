package com.netease.nimlib.biz.c.i;

import android.text.TextUtils;
import com.netease.nimlib.biz.e.j.aa;
import com.netease.nimlib.sdk.friend.model.AddFriendNotify;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SystemMessageStatus;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.CustomNotificationConfig;
import com.netease.nimlib.sdk.msg.model.NIMAntiSpamOption;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: SystemMsgNotifyHandler.java */
/* loaded from: classes.dex */
public class n extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (aVar.n()) {
            if (aVar instanceof aa) {
                a(((aa) aVar).a(), false);
            } else if (aVar instanceof com.netease.nimlib.biz.e.f.i) {
                List<com.netease.nimlib.push.packet.b.c> a = ((com.netease.nimlib.biz.e.f.i) aVar).a();
                Collections.sort(a, new Comparator<com.netease.nimlib.push.packet.b.c>() { // from class: com.netease.nimlib.biz.c.i.n.1
                    @Override // java.util.Comparator
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(com.netease.nimlib.push.packet.b.c cVar, com.netease.nimlib.push.packet.b.c cVar2) {
                        return (cVar.e(0) > cVar2.e(0) ? 1 : (cVar.e(0) == cVar2.e(0) ? 0 : -1));
                    }
                });
                ArrayList arrayList = new ArrayList();
                ArrayList<Long> M = com.netease.nimlib.biz.l.M();
                if (com.netease.nimlib.o.f.c((Collection) M)) {
                    M = new ArrayList<>();
                }
                for (com.netease.nimlib.push.packet.b.c cVar : a) {
                    long e = cVar.e(6);
                    if (!M.contains(Long.valueOf(e))) {
                        a(cVar, true);
                        if (e > 0 && b(cVar)) {
                            M.add(Long.valueOf(e));
                        }
                    }
                    if (e > 0) {
                        arrayList.add(Long.valueOf(e));
                    }
                }
                a(arrayList);
                M.retainAll(arrayList);
                com.netease.nimlib.biz.l.a(M);
            }
            com.netease.nimlib.i.b.a(MsgDBHelper.querySystemMessageUnreadNum());
        }
    }

    private void a(com.netease.nimlib.push.packet.b.c cVar, boolean z) {
        int d = cVar.d(1);
        if (d == 100 || d == 101 || d == 103 || d == 102) {
            a(cVar);
        } else {
            a(cVar, d != 6, z);
        }
    }

    private void a(com.netease.nimlib.push.packet.b.c cVar) {
        long e = cVar.e(6);
        if (e > 0 && !com.netease.nimlib.session.aa.a().a(Long.valueOf(e))) {
            com.netease.nimlib.log.b.N("receive repeated custom notification，msgId = " + e);
            return;
        }
        CustomNotification customNotification = new CustomNotification();
        customNotification.setTime(cVar.e(0));
        customNotification.setContent(cVar.c(5));
        customNotification.setFromAccount(cVar.c(3));
        com.netease.nimlib.log.b.P("receive custom notification: sessionId: " + cVar.c(3) + ", content: " + cVar.c(5));
        customNotification.setApnsText(cVar.c(8));
        String c = cVar.c(9);
        if (!TextUtils.isEmpty(c)) {
            customNotification.setPushPayload(com.netease.nimlib.session.j.c(c));
        }
        if (e > 0) {
            customNotification.setSendToOnlineUserOnly(false);
        }
        int d = cVar.d(1);
        if (d == 100) {
            customNotification.setSessionType(SessionTypeEnum.P2P);
            customNotification.setSessionId(cVar.c(3));
        } else if (d == 101) {
            customNotification.setSessionType(SessionTypeEnum.Team);
            customNotification.setSessionId(cVar.c(2));
        } else if (d == 103) {
            customNotification.setSessionType(SessionTypeEnum.SUPER_TEAM);
            customNotification.setSessionId(cVar.c(2));
        } else if (d == 102) {
            customNotification.setSessionType(SessionTypeEnum.Ysf);
            customNotification.setSessionId(cVar.c(3));
        }
        CustomNotificationConfig customNotificationConfig = new CustomNotificationConfig();
        customNotificationConfig.enablePush = cVar.d(107) == 1;
        customNotificationConfig.enablePushNick = cVar.d(110) == 1;
        customNotificationConfig.enableUnreadCount = cVar.d(109) == 1;
        customNotification.setConfig(customNotificationConfig);
        NIMAntiSpamOption nIMAntiSpamOption = new NIMAntiSpamOption();
        if (cVar.f(12)) {
            nIMAntiSpamOption.enable = cVar.d(12) == 1;
            customNotification.setNIMAntiSpamOption(nIMAntiSpamOption);
        }
        if (cVar.f(13)) {
            nIMAntiSpamOption.content = cVar.c(13);
            customNotification.setNIMAntiSpamOption(nIMAntiSpamOption);
        }
        com.netease.nimlib.i.b.a(customNotification);
    }

    private void a(com.netease.nimlib.push.packet.b.c cVar, boolean z, boolean z2) {
        SystemMessage systemMessage = new SystemMessage();
        systemMessage.setFromAccount(cVar.c(3));
        systemMessage.setTargetId(cVar.c(2));
        systemMessage.setTime(cVar.e(0));
        systemMessage.setContent(cVar.c(4));
        systemMessage.setAttach(cVar.c(5));
        systemMessage.setStatus(SystemMessageStatus.init);
        systemMessage.setUnread(z);
        int d = cVar.d(1);
        systemMessage.setType(d);
        a(systemMessage);
        a(systemMessage, d, z2);
        if (d == 2) {
            b(systemMessage);
        }
        if (d != 6) {
            MsgDBHelper.saveSystemMessage(systemMessage, d);
            com.netease.nimlib.i.b.a(systemMessage);
        }
    }

    private void a(SystemMessage systemMessage) {
        if (TextUtils.isEmpty(systemMessage.getAttach())) {
            return;
        }
        systemMessage.setAttachObject(com.netease.nimlib.team.c.b(systemMessage.getAttach()));
    }

    private void a(SystemMessage systemMessage, int i, boolean z) {
        if (i != 5) {
            if (z || i != 6) {
                return;
            }
            com.netease.nimlib.friend.a.b(systemMessage.getFromAccount());
            return;
        }
        com.netease.nimlib.session.j.a(systemMessage);
        if (z || systemMessage.getAttachObject() == null) {
            return;
        }
        AddFriendNotify addFriendNotify = (AddFriendNotify) systemMessage.getAttachObject();
        if (addFriendNotify.getEvent() == AddFriendNotify.Event.RECV_ADD_FRIEND_DIRECT || addFriendNotify.getEvent() == AddFriendNotify.Event.RECV_AGREE_ADD_FRIEND) {
            com.netease.nimlib.friend.a.a(systemMessage.getFromAccount(), addFriendNotify.getServerExt());
        }
    }

    private void b(SystemMessage systemMessage) {
        com.netease.nimlib.session.j.b(systemMessage);
    }

    protected void a(List<Long> list) {
        com.netease.nimlib.biz.d.e.a aVar = new com.netease.nimlib.biz.d.e.a();
        aVar.a((byte) 7);
        aVar.b((byte) 3);
        aVar.a(list);
        com.netease.nimlib.biz.i.a().a(aVar, com.netease.nimlib.biz.g.a.d);
    }

    private boolean b(com.netease.nimlib.push.packet.b.c cVar) {
        int d = cVar.d(1);
        return d == 100 || d == 101 || d == 103 || d == 102;
    }
}
