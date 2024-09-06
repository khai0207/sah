package com.netease.nimlib.chatroom;

import android.text.TextUtils;
import com.netease.nimlib.chatroom.model.ChatRoomMessageImpl;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessageExtension;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.NIMAntiSpamOption;
import com.netease.nimlib.session.ac;

/* compiled from: ChatRoomMessageSender.java */
/* loaded from: classes.dex */
public class h extends com.netease.nimlib.session.h {
    public static void a(ChatRoomMessageImpl chatRoomMessageImpl, boolean z, com.netease.nimlib.i.k kVar, long j) {
        if (TextUtils.isEmpty(chatRoomMessageImpl.getSessionId())) {
            com.netease.nimlib.log.b.f("core", "no message receiver");
            throw new IllegalArgumentException("Receiver cannot be null");
        }
        EnterChatRoomData k = c.a().k(chatRoomMessageImpl.getSessionId());
        if (k != null) {
            String account = k.getAccount();
            if (account == null) {
                account = com.netease.nimlib.c.n();
            }
            chatRoomMessageImpl.setFromAccount(account);
        }
        com.netease.nimlib.n.g.a().a(chatRoomMessageImpl, j);
        com.netease.nimlib.session.d.a().a(chatRoomMessageImpl.getUuid());
        if (c(chatRoomMessageImpl, z, kVar)) {
            return;
        }
        b(chatRoomMessageImpl, z, kVar);
    }

    public static void b(ChatRoomMessageImpl chatRoomMessageImpl, boolean z, com.netease.nimlib.i.k kVar) {
        com.netease.nimlib.chatroom.c.p pVar = new com.netease.nimlib.chatroom.c.p(a(chatRoomMessageImpl, z));
        pVar.a(kVar);
        a(pVar, chatRoomMessageImpl.getSessionId());
    }

    private static com.netease.nimlib.push.packet.b.c a(ChatRoomMessageImpl chatRoomMessageImpl, boolean z) {
        String content;
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(1, chatRoomMessageImpl.getUuid());
        cVar.a(2, chatRoomMessageImpl.getMsgType().getValue());
        if (chatRoomMessageImpl.getMsgType() == MsgTypeEnum.text || chatRoomMessageImpl.getMsgType() == MsgTypeEnum.tip) {
            content = chatRoomMessageImpl.getContent();
        } else {
            content = chatRoomMessageImpl.getAttachStr(true);
        }
        if (!TextUtils.isEmpty(content)) {
            cVar.a(3, content);
        } else {
            cVar.a(3, "");
        }
        if (!TextUtils.isEmpty(chatRoomMessageImpl.getRemoteExtensionStr())) {
            cVar.a(4, chatRoomMessageImpl.getRemoteExtensionStr());
        }
        if (z) {
            cVar.a(5, 1);
        }
        ChatRoomMessageExtension chatRoomMessageExtension = chatRoomMessageImpl.getChatRoomMessageExtension();
        if (chatRoomMessageExtension != null) {
            if (chatRoomMessageExtension.getRoleInfoTimeTag() >= 0) {
                cVar.a(6, chatRoomMessageExtension.getRoleInfoTimeTag());
            }
            if (!TextUtils.isEmpty(chatRoomMessageExtension.getSenderNick())) {
                cVar.a(7, chatRoomMessageExtension.getSenderNick());
            }
            if (!TextUtils.isEmpty(chatRoomMessageExtension.getSenderAvatar())) {
                cVar.a(8, chatRoomMessageExtension.getSenderAvatar());
            }
            if (chatRoomMessageExtension.getSenderExtension() != null) {
                cVar.a(9, com.netease.nimlib.session.j.a(chatRoomMessageExtension.getSenderExtension()));
            }
        }
        if (chatRoomMessageImpl.getNIMAntiSpamOption() != null) {
            NIMAntiSpamOption nIMAntiSpamOption = chatRoomMessageImpl.getNIMAntiSpamOption();
            if (!nIMAntiSpamOption.enable) {
                cVar.a(16, 0);
            }
            if (!TextUtils.isEmpty(nIMAntiSpamOption.content)) {
                cVar.a(10, 1);
                cVar.a(11, nIMAntiSpamOption.content);
            }
            if (!TextUtils.isEmpty(nIMAntiSpamOption.antiSpamConfigId)) {
                cVar.a(14, chatRoomMessageImpl.getNIMAntiSpamOption().antiSpamConfigId);
            }
        }
        if (chatRoomMessageImpl.getClientAntiSpam()) {
            cVar.a(15, 1);
        }
        if (chatRoomMessageImpl.getChatRoomConfig() != null && chatRoomMessageImpl.getChatRoomConfig().skipHistory) {
            cVar.a(12, 1);
        }
        if (chatRoomMessageImpl.getMsgType() == MsgTypeEnum.robot) {
            cVar.a(13, chatRoomMessageImpl.getContent());
        }
        if (chatRoomMessageImpl.getSubtype() > 0) {
            cVar.a(28, chatRoomMessageImpl.getSubtype());
        }
        if (!TextUtils.isEmpty(chatRoomMessageImpl.getYidunAntiCheating())) {
            cVar.a(29, chatRoomMessageImpl.getYidunAntiCheating());
        }
        if (!TextUtils.isEmpty(chatRoomMessageImpl.getEnv())) {
            cVar.a(30, chatRoomMessageImpl.getEnv());
        }
        if (!TextUtils.isEmpty(chatRoomMessageImpl.getNotifyTargetTags())) {
            cVar.a(31, chatRoomMessageImpl.getNotifyTargetTags());
        }
        if (!TextUtils.isEmpty(chatRoomMessageImpl.getYidunAntiSpamExt())) {
            cVar.a(32, chatRoomMessageImpl.getYidunAntiSpamExt());
        }
        if (com.netease.nimlib.c.i().enableChatRoomLocation) {
            Double locX = chatRoomMessageImpl.getLocX();
            if (locX != null) {
                cVar.a(34, locX.doubleValue());
            }
            Double locY = chatRoomMessageImpl.getLocY();
            if (locY != null) {
                cVar.a(35, locY.doubleValue());
            }
            Double locZ = chatRoomMessageImpl.getLocZ();
            if (locZ != null) {
                cVar.a(36, locZ.doubleValue());
            }
        }
        String toAccounts = chatRoomMessageImpl.getToAccounts();
        if (!TextUtils.isEmpty(toAccounts)) {
            cVar.a(37, toAccounts);
        }
        ac timeConsumingStatistics = chatRoomMessageImpl.getTimeConsumingStatistics();
        if (timeConsumingStatistics != null && timeConsumingStatistics.a() > 0) {
            cVar.a(39, timeConsumingStatistics.d().toString());
        }
        return cVar;
    }

    private static boolean c(ChatRoomMessageImpl chatRoomMessageImpl, boolean z, com.netease.nimlib.i.k kVar) {
        MsgAttachment attachment = chatRoomMessageImpl.getAttachment();
        if (attachment == null || !(attachment instanceof FileAttachment)) {
            return false;
        }
        FileAttachment fileAttachment = (FileAttachment) attachment;
        if (!TextUtils.isEmpty(fileAttachment.getUrl())) {
            return false;
        }
        chatRoomMessageImpl.setAttachStatus(AttachStatusEnum.transferring);
        if (TextUtils.isEmpty(fileAttachment.getExtension())) {
            fileAttachment.setExtension(com.netease.nimlib.session.h.a(fileAttachment));
        }
        if (TextUtils.isEmpty(fileAttachment.getMd5())) {
            com.netease.nimlib.session.h.a(chatRoomMessageImpl);
        }
        d(chatRoomMessageImpl, z, kVar);
        return true;
    }

    private static void d(ChatRoomMessageImpl chatRoomMessageImpl, boolean z, com.netease.nimlib.i.k kVar) {
        FileAttachment fileAttachment = (FileAttachment) chatRoomMessageImpl.getAttachment();
        com.netease.nimlib.n.g.a().a((IMMessage) chatRoomMessageImpl);
        com.netease.nimlib.net.a.b.a.a().a(chatRoomMessageImpl.getSessionId(), fileAttachment, kVar, new com.netease.nimlib.net.a.b.c<com.netease.nimlib.i.k>() { // from class: com.netease.nimlib.chatroom.h.1
            final /* synthetic */ FileAttachment b;
            final /* synthetic */ boolean c;
            final /* synthetic */ com.netease.nimlib.i.k d;

            AnonymousClass1(FileAttachment fileAttachment2, boolean z2, com.netease.nimlib.i.k kVar2) {
                r2 = fileAttachment2;
                r3 = z2;
                r4 = kVar2;
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, long j, long j2) {
                l.a(ChatRoomMessageImpl.this.getUuid(), j, j2);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, String str) {
                com.netease.nimlib.n.g.a().b(ChatRoomMessageImpl.this);
                r2.setUrl(str);
                ChatRoomMessageImpl.this.setAttachment(r2);
                ChatRoomMessageImpl.this.setAttachStatus(AttachStatusEnum.transferred);
                ChatRoomMessageImpl.this.setStatus(MsgStatusEnum.success);
                h.b(ChatRoomMessageImpl.this, r3, r4);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, int i, String str) {
                ChatRoomMessageImpl.this.setStatus(MsgStatusEnum.fail);
                com.netease.nimlib.n.g.a().b(ChatRoomMessageImpl.this);
                ChatRoomMessageImpl.this.setAttachStatus(AttachStatusEnum.fail);
                l.a(ChatRoomMessageImpl.this);
                com.netease.nimlib.session.d.a().b(ChatRoomMessageImpl.this.getUuid());
                if (kVar2 != null) {
                    kVar2.a(7).b();
                }
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2) {
                a(kVar2, 400, (String) null);
            }
        });
    }

    /* compiled from: ChatRoomMessageSender.java */
    /* renamed from: com.netease.nimlib.chatroom.h$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 implements com.netease.nimlib.net.a.b.c<com.netease.nimlib.i.k> {
        final /* synthetic */ FileAttachment b;
        final /* synthetic */ boolean c;
        final /* synthetic */ com.netease.nimlib.i.k d;

        AnonymousClass1(FileAttachment fileAttachment2, boolean z2, com.netease.nimlib.i.k kVar2) {
            r2 = fileAttachment2;
            r3 = z2;
            r4 = kVar2;
        }

        @Override // com.netease.nimlib.net.a.b.c
        public void a(com.netease.nimlib.i.k kVar2, long j, long j2) {
            l.a(ChatRoomMessageImpl.this.getUuid(), j, j2);
        }

        @Override // com.netease.nimlib.net.a.b.c
        public void a(com.netease.nimlib.i.k kVar2, String str) {
            com.netease.nimlib.n.g.a().b(ChatRoomMessageImpl.this);
            r2.setUrl(str);
            ChatRoomMessageImpl.this.setAttachment(r2);
            ChatRoomMessageImpl.this.setAttachStatus(AttachStatusEnum.transferred);
            ChatRoomMessageImpl.this.setStatus(MsgStatusEnum.success);
            h.b(ChatRoomMessageImpl.this, r3, r4);
        }

        @Override // com.netease.nimlib.net.a.b.c
        public void a(com.netease.nimlib.i.k kVar2, int i, String str) {
            ChatRoomMessageImpl.this.setStatus(MsgStatusEnum.fail);
            com.netease.nimlib.n.g.a().b(ChatRoomMessageImpl.this);
            ChatRoomMessageImpl.this.setAttachStatus(AttachStatusEnum.fail);
            l.a(ChatRoomMessageImpl.this);
            com.netease.nimlib.session.d.a().b(ChatRoomMessageImpl.this.getUuid());
            if (kVar2 != null) {
                kVar2.a(7).b();
            }
        }

        @Override // com.netease.nimlib.net.a.b.c
        public void a(com.netease.nimlib.i.k kVar2) {
            a(kVar2, 400, (String) null);
        }
    }

    private static void a(com.netease.nimlib.chatroom.c.p pVar, String str) {
        d.e().a(new com.netease.nimlib.chatroom.c.q(str, pVar, com.netease.nimlib.biz.g.a.b), str);
    }
}
