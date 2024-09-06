package com.netease.nimlib.session;

import android.text.TextUtils;
import com.netease.nimlib.net.a.b.a;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.attachment.AudioAttachment;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageRobotInfo;
import com.netease.nimlib.sdk.msg.model.MsgThreadOption;
import com.netease.nimlib.sdk.msg.model.NIMAntiSpamOption;
import com.netease.nimlib.sdk.util.UriUtils;
import java.io.File;
import java.util.List;

/* compiled from: MessageSender.java */
/* loaded from: classes.dex */
public class h {
    public static void a(IMMessageImpl iMMessageImpl, boolean z, com.netease.nimlib.i.k kVar, long j) {
        a(iMMessageImpl, z, kVar, (com.netease.nimlib.biz.d.i.s) null, j);
    }

    public static void a(IMMessageImpl iMMessageImpl, boolean z, com.netease.nimlib.i.k kVar, com.netease.nimlib.biz.d.i.s sVar, long j) {
        boolean z2;
        if (TextUtils.isEmpty(iMMessageImpl.getSessionId())) {
            com.netease.nimlib.log.b.f("core", "no message receiver");
            throw new IllegalArgumentException("Receiver cannot be null");
        }
        com.netease.nimlib.n.g.a().a(iMMessageImpl, j);
        if (z) {
            long queryMessageIdByUuid = MsgDBHelper.queryMessageIdByUuid(iMMessageImpl.getUuid());
            iMMessageImpl.setMessageId(queryMessageIdByUuid);
            z2 = queryMessageIdByUuid > 0;
        } else {
            z2 = z;
        }
        iMMessageImpl.setTime(System.currentTimeMillis());
        long b = com.netease.nimlib.c.x() ? t.c().b() : -1L;
        if (z2) {
            MsgDBHelper.updateMessage(iMMessageImpl, MsgStatusEnum.fail);
        } else {
            MsgDBHelper.saveMessage(iMMessageImpl, MsgStatusEnum.fail);
        }
        com.netease.nimlib.log.b.d("MessageSender", "before send msg, uuid=" + iMMessageImpl.getUuid());
        q b2 = j.b(iMMessageImpl);
        d.a().a(iMMessageImpl.getUuid());
        b2.setMsgStatus(MsgStatusEnum.sending);
        com.netease.nimlib.i.b.a(b2);
        if (a(iMMessageImpl, z, b, kVar, sVar)) {
            return;
        }
        b(iMMessageImpl, b, z, kVar, sVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(IMMessageImpl iMMessageImpl, long j, boolean z, com.netease.nimlib.i.k kVar, com.netease.nimlib.biz.d.i.s sVar) {
        com.netease.nimlib.push.packet.b.c a = a(iMMessageImpl, j, z);
        if (sVar == null) {
            if (iMMessageImpl.getSessionType() == SessionTypeEnum.P2P) {
                sVar = new com.netease.nimlib.biz.d.i.s();
            } else if (iMMessageImpl.getSessionType() == SessionTypeEnum.Team) {
                sVar = new com.netease.nimlib.biz.d.i.t();
            }
        }
        if (sVar != null) {
            sVar.a(kVar);
            sVar.a(a);
            a(sVar);
        }
    }

    private static com.netease.nimlib.push.packet.b.c a(IMMessageImpl iMMessageImpl, long j, boolean z) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(0, iMMessageImpl.getSessionType().getValue());
        cVar.a(1, iMMessageImpl.getSessionId());
        cVar.a(9, iMMessageImpl.getContent());
        cVar.a(8, iMMessageImpl.getMsgType().getValue());
        cVar.a(11, iMMessageImpl.getUuid());
        if (iMMessageImpl.needMsgAck()) {
            cVar.a(26, 1);
        }
        String remoteExtensionStr = iMMessageImpl.getRemoteExtensionStr();
        if (!TextUtils.isEmpty(remoteExtensionStr)) {
            cVar.a(15, remoteExtensionStr);
        }
        if (!TextUtils.isEmpty(iMMessageImpl.getPushContent())) {
            cVar.a(17, iMMessageImpl.getPushContent());
        }
        if (!TextUtils.isEmpty(iMMessageImpl.getPushPayloadStr())) {
            cVar.a(16, iMMessageImpl.getPushPayloadStr());
        }
        if (j >= 0) {
            cVar.a(14, j);
        }
        String attachStr = iMMessageImpl.getAttachStr(true);
        if (!TextUtils.isEmpty(attachStr)) {
            cVar.a(10, attachStr);
        }
        if (z) {
            cVar.a(13, 1);
        }
        if (iMMessageImpl.getSessionId().equals(com.netease.nimlib.c.n())) {
            cVar.a(5, com.netease.nimlib.push.b.c());
        }
        if (iMMessageImpl.getMemberPushOption() != null) {
            cVar.a(20, iMMessageImpl.getMemberPushOption().isForcePush() ? 1 : 0);
            cVar.a(19, iMMessageImpl.getMemberPushOption().getForcePushContent());
            List<String> forcePushList = iMMessageImpl.getMemberPushOption().getForcePushList();
            cVar.a(18, forcePushList == null ? "#%@all@%#" : j.e(forcePushList));
        }
        cVar.a(28, iMMessageImpl.isSessionUpdate() ? 1 : 0);
        if (iMMessageImpl.getConfig() != null) {
            if (!iMMessageImpl.getConfig().enableHistory) {
                cVar.a(100, 0);
            }
            if (!iMMessageImpl.getConfig().enableRoaming) {
                cVar.a(101, 0);
            }
            if (!iMMessageImpl.getConfig().enableSelfSync) {
                cVar.a(102, 0);
            }
            if (!iMMessageImpl.getConfig().enablePush) {
                cVar.a(107, 0);
            }
            if (!iMMessageImpl.getConfig().enablePersist) {
                cVar.a(108, 0);
            }
            if (!iMMessageImpl.getConfig().enableUnreadCount) {
                cVar.a(109, 0);
            }
            if (!iMMessageImpl.getConfig().enablePushNick) {
                cVar.a(110, 0);
            }
            if (!iMMessageImpl.getConfig().enableRoute) {
                cVar.a(105, 0);
            }
        }
        if (iMMessageImpl.getNIMAntiSpamOption() != null) {
            NIMAntiSpamOption nIMAntiSpamOption = iMMessageImpl.getNIMAntiSpamOption();
            if (!nIMAntiSpamOption.enable) {
                cVar.a(25, 0);
            }
            if (!TextUtils.isEmpty(nIMAntiSpamOption.content)) {
                cVar.a(21, 1);
                cVar.a(22, nIMAntiSpamOption.content);
            }
            if (!TextUtils.isEmpty(nIMAntiSpamOption.antiSpamConfigId)) {
                cVar.a(23, nIMAntiSpamOption.antiSpamConfigId);
            }
        }
        if (iMMessageImpl.getClientAntiSpam()) {
            cVar.a(24, 1);
        }
        MsgThreadOption threadOption = iMMessageImpl.getThreadOption();
        if (!iMMessageImpl.isThread()) {
            cVar.a(29, threadOption.getReplyMsgFromAccount());
            cVar.a(30, threadOption.getReplyMsgToAccount());
            cVar.a(31, threadOption.getReplyMsgTime());
            cVar.a(32, threadOption.getReplyMsgIdServer());
            cVar.a(33, threadOption.getReplyMsgIdClient());
            cVar.a(34, threadOption.getThreadMsgFromAccount());
            cVar.a(35, threadOption.getThreadMsgToAccount());
            cVar.a(36, threadOption.getThreadMsgTime());
            cVar.a(37, threadOption.getThreadMsgIdServer());
            cVar.a(38, threadOption.getThreadMsgIdClient());
        }
        cVar.a(39, iMMessageImpl.isDeleted() ? 1 : 0);
        cVar.a(40, iMMessageImpl.getCallbackExtension());
        int subtype = iMMessageImpl.getSubtype();
        if (subtype > 0) {
            cVar.a(41, subtype);
        }
        cVar.a(42, iMMessageImpl.getYidunAntiCheating());
        cVar.a(43, iMMessageImpl.getEnv());
        cVar.a(44, iMMessageImpl.getYidunAntiSpamExt());
        ac timeConsumingStatistics = iMMessageImpl.getTimeConsumingStatistics();
        if (timeConsumingStatistics != null && timeConsumingStatistics.a() > 0) {
            cVar.a(46, timeConsumingStatistics.d().toString());
        }
        MessageRobotInfo robotInfo = iMMessageImpl.getRobotInfo();
        if (robotInfo != null) {
            cVar.a(47, robotInfo.getFunction());
            cVar.a(48, robotInfo.getTopic());
            cVar.a(49, robotInfo.getCustomContent());
            cVar.a(50, robotInfo.getAccount());
        }
        return cVar;
    }

    public static a.c a(final FileAttachment fileAttachment, final com.netease.nimlib.i.k kVar) {
        a(fileAttachment, 1);
        return com.netease.nimlib.net.a.b.a.a().a(fileAttachment, kVar, new com.netease.nimlib.net.a.b.c<com.netease.nimlib.i.k>() { // from class: com.netease.nimlib.session.h.1
            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, long j, long j2) {
                com.netease.nimlib.i.b.a(FileAttachment.this.getPath(), j, j2);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, String str) {
                FileAttachment.this.setUrl(str);
                kVar.b(FileAttachment.this).b();
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, int i, String str) {
                kVar.a(i).b();
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2) {
                a(kVar2, 400, (String) null);
            }
        });
    }

    private static void a(FileAttachment fileAttachment, int i) {
        if (fileAttachment == null) {
            com.netease.nimlib.log.b.d("MessageSender", "calculateMd5 FileAttachment == null");
            return;
        }
        boolean z = fileAttachment instanceof VideoAttachment;
        String str = null;
        String thumbPath = z ? fileAttachment.getThumbPath() : null;
        String extension = fileAttachment.getExtension();
        if (fileAttachment.getUri() != null) {
            str = com.netease.nimlib.o.m.a(com.netease.nimlib.c.e(), fileAttachment.getUri());
            if (TextUtils.isEmpty(extension)) {
                extension = UriUtils.getFileExtensionFromUri(com.netease.nimlib.c.e(), fileAttachment.getUri());
            }
        } else if (!TextUtils.isEmpty(fileAttachment.getPath())) {
            str = com.netease.nimlib.o.m.b(fileAttachment.getPath());
            if (TextUtils.isEmpty(extension)) {
                extension = com.netease.nimlib.o.w.c(fileAttachment.getPath());
            }
        }
        fileAttachment.setMd5(str);
        if (i == 0) {
            return;
        }
        com.netease.nimlib.o.b.b bVar = com.netease.nimlib.o.b.b.TYPE_FILE;
        if (fileAttachment instanceof AudioAttachment) {
            bVar = com.netease.nimlib.o.b.b.TYPE_AUDIO;
        } else if (fileAttachment instanceof ImageAttachment) {
            bVar = com.netease.nimlib.o.b.b.TYPE_IMAGE;
        } else if (z) {
            bVar = com.netease.nimlib.o.b.b.TYPE_VIDEO;
        }
        String str2 = com.netease.nimlib.o.b.c.a(str, bVar) + "." + extension;
        if (!com.netease.nimlib.net.a.c.a.d(str2)) {
            if (fileAttachment.getUri() != null) {
                com.netease.nimlib.net.a.c.a.a(com.netease.nimlib.c.e(), fileAttachment.getUri(), str2);
                fileAttachment.setPath(str2);
                fileAttachment.setSize(new File(str2).length());
            } else if (!TextUtils.isEmpty(fileAttachment.getPath()) && i == 2) {
                com.netease.nimlib.net.a.c.a.a(fileAttachment.getPath(), str2);
                fileAttachment.setPath(str2);
                fileAttachment.setSize(new File(str2).length());
            }
        } else {
            fileAttachment.setPath(str2);
            fileAttachment.setSize(new File(str2).length());
        }
        if (z) {
            String thumbPathForSave = ((VideoAttachment) fileAttachment).getThumbPathForSave();
            if (TextUtils.isEmpty(thumbPath) || com.netease.nimlib.net.a.c.a.d(thumbPathForSave)) {
                return;
            }
            com.netease.nimlib.log.b.d("MessageSender", "move thumb " + thumbPath + " to " + thumbPathForSave + " result = " + com.netease.nimlib.net.a.c.a.b(thumbPath, thumbPathForSave));
        }
    }

    private static boolean a(IMMessageImpl iMMessageImpl, boolean z, long j, com.netease.nimlib.i.k kVar, com.netease.nimlib.biz.d.i.s sVar) {
        MsgAttachment attachment = iMMessageImpl.getAttachment();
        if (attachment == null || !(attachment instanceof FileAttachment)) {
            return false;
        }
        FileAttachment fileAttachment = (FileAttachment) attachment;
        if (!TextUtils.isEmpty(fileAttachment.getUrl())) {
            if (fileAttachment instanceof AudioAttachment) {
                AudioAttachment audioAttachment = (AudioAttachment) fileAttachment;
                if (audioAttachment.getAutoTransform()) {
                    iMMessageImpl.setAttachStatus(AttachStatusEnum.transferring);
                    if (TextUtils.isEmpty(fileAttachment.getMd5())) {
                        a(fileAttachment, 1);
                    }
                    b(audioAttachment, iMMessageImpl, z, kVar, sVar, j);
                    return true;
                }
            }
            return false;
        }
        MsgTypeEnum msgType = iMMessageImpl.getMsgType();
        if ((msgType == MsgTypeEnum.audio || msgType == MsgTypeEnum.image || msgType == MsgTypeEnum.video) && fileAttachment.getSize() == 0) {
            com.netease.nimlib.log.b.f("core", "the size of file attachment is 0");
            throw new IllegalArgumentException("the size of file attachment is 0");
        }
        iMMessageImpl.setAttachStatus(AttachStatusEnum.transferring);
        if (TextUtils.isEmpty(fileAttachment.getExtension())) {
            fileAttachment.setExtension(a(fileAttachment));
        }
        if (!z || TextUtils.isEmpty(fileAttachment.getMd5())) {
            a(iMMessageImpl);
        }
        b(iMMessageImpl, z, j, kVar, sVar);
        return true;
    }

    private static void b(final IMMessageImpl iMMessageImpl, final boolean z, final long j, final com.netease.nimlib.i.k kVar, final com.netease.nimlib.biz.d.i.s sVar) {
        final FileAttachment fileAttachment = (FileAttachment) iMMessageImpl.getAttachment();
        final d a = d.a();
        final String uuid = iMMessageImpl.getUuid();
        com.netease.nimlib.n.g.a().a((IMMessage) iMMessageImpl);
        a.a(iMMessageImpl.getUuid(), com.netease.nimlib.net.a.b.a.a().a(fileAttachment, kVar, new com.netease.nimlib.net.a.b.c<com.netease.nimlib.i.k>() { // from class: com.netease.nimlib.session.h.2
            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, long j2, long j3) {
                com.netease.nimlib.i.b.a(uuid, j2, j3);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, String str) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(str.contains("?") ? com.alipay.sdk.m.o.a.l : "?");
                sb.append("createTime=");
                sb.append(com.netease.nimlib.o.y.a());
                String sb2 = sb.toString();
                a.g(uuid);
                com.netease.nimlib.n.g.a().b(iMMessageImpl);
                fileAttachment.setUrl(sb2);
                iMMessageImpl.setAttachment(fileAttachment);
                iMMessageImpl.setAttachStatus(AttachStatusEnum.transferred);
                try {
                    MsgDBHelper.updateMessage(iMMessageImpl, MsgStatusEnum.fail);
                    if (!(fileAttachment instanceof AudioAttachment) || !((AudioAttachment) fileAttachment).getAutoTransform()) {
                        h.b(iMMessageImpl, j, z, kVar, sVar);
                    } else {
                        h.b((AudioAttachment) fileAttachment, iMMessageImpl, z, kVar, sVar, j);
                    }
                } catch (Exception unused) {
                    com.netease.nimlib.log.b.f("ui", "db already close");
                }
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2, int i, String str) {
                a.g(uuid);
                com.netease.nimlib.n.g.a().b(iMMessageImpl);
                a.b(uuid);
                iMMessageImpl.setStatus(MsgStatusEnum.fail);
                iMMessageImpl.setAttachStatus(AttachStatusEnum.fail);
                try {
                    MsgDBHelper.updateMessage(iMMessageImpl, MsgStatusEnum.fail);
                    com.netease.nimlib.i.b.a(iMMessageImpl);
                    if (kVar != null) {
                        kVar.a(i).b();
                    }
                } catch (Exception unused) {
                    com.netease.nimlib.log.b.f("ui", "db already close");
                }
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(com.netease.nimlib.i.k kVar2) {
                a.g(uuid);
                com.netease.nimlib.n.g.a().b(iMMessageImpl);
                a.b(uuid);
                iMMessageImpl.setStatus(MsgStatusEnum.fail);
                iMMessageImpl.setAttachStatus(AttachStatusEnum.cancel);
                try {
                    MsgDBHelper.updateMessage(iMMessageImpl, MsgStatusEnum.fail);
                    com.netease.nimlib.i.b.a(iMMessageImpl);
                    if (kVar != null) {
                        kVar.a(400).b();
                    }
                } catch (Exception unused) {
                    com.netease.nimlib.log.b.f("ui", "db already close");
                }
            }
        }));
    }

    protected static void a(IMMessageImpl iMMessageImpl) {
        FileAttachment fileAttachment = (FileAttachment) iMMessageImpl.getAttachment();
        if (fileAttachment == null) {
            com.netease.nimlib.log.b.d("MessageSender", "calculateMd5 FileAttachment == null,uuid = " + iMMessageImpl.getUuid());
            return;
        }
        a(fileAttachment, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final AudioAttachment audioAttachment, final IMMessageImpl iMMessageImpl, final boolean z, final com.netease.nimlib.i.k kVar, final com.netease.nimlib.biz.d.i.s sVar, final long j) {
        ((MsgService) NIMClient.getService(MsgService.class)).transVoiceToText(audioAttachment.getUrl(), audioAttachment.getPath(), audioAttachment.getDuration()).setCallback(new RequestCallback() { // from class: com.netease.nimlib.session.h.3
            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onSuccess(Object obj) {
                AudioAttachment.this.setText(obj.toString());
                iMMessageImpl.setAttachment(AudioAttachment.this);
                ((MsgService) NIMClient.getService(MsgService.class)).updateIMMessage(iMMessageImpl);
                IMMessageImpl iMMessageImpl2 = new IMMessageImpl();
                iMMessageImpl2.setUuid(iMMessageImpl.getUuid());
                iMMessageImpl2.setSessionId(iMMessageImpl.getSessionId());
                iMMessageImpl2.setFromAccount(com.netease.nimlib.c.n());
                iMMessageImpl2.setDirect(MsgDirectionEnum.Out);
                iMMessageImpl2.setStatus(MsgStatusEnum.sending);
                iMMessageImpl2.setSessionType(iMMessageImpl.getSessionType());
                iMMessageImpl2.setTime(iMMessageImpl.getTime());
                iMMessageImpl2.setMessageId(iMMessageImpl.getMessageId());
                iMMessageImpl2.setMsgType(MsgTypeEnum.text.getValue());
                iMMessageImpl2.setContent(obj.toString());
                h.b(iMMessageImpl2, j, z, kVar, sVar);
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
                iMMessageImpl.setStatus(MsgStatusEnum.fail);
                iMMessageImpl.setAttachStatus(AttachStatusEnum.fail);
                com.netease.nimlib.i.b.a(iMMessageImpl);
                d.a().b(iMMessageImpl.getUuid());
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
                iMMessageImpl.setStatus(MsgStatusEnum.fail);
                iMMessageImpl.setAttachStatus(AttachStatusEnum.fail);
                com.netease.nimlib.i.b.a(iMMessageImpl);
                d.a().b(iMMessageImpl.getUuid());
            }
        });
    }

    protected static String a(FileAttachment fileAttachment) {
        return fileAttachment instanceof ImageAttachment ? "jpg" : fileAttachment instanceof VideoAttachment ? "mp4" : "";
    }

    private static void a(com.netease.nimlib.biz.d.i.s sVar) {
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.d.i.r(sVar, com.netease.nimlib.biz.g.a.b));
    }

    public static boolean a(IMMessage iMMessage, IMMessage iMMessage2) {
        if ((iMMessage instanceof IMMessageImpl) && (iMMessage2 instanceof IMMessageImpl)) {
            String sessionId = iMMessage.getSessionId();
            if (!TextUtils.isEmpty(sessionId) && sessionId.equals(iMMessage2.getSessionId())) {
                return true;
            }
        }
        return false;
    }
}
