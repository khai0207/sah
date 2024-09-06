package com.netease.nimlib.session;

import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.app.AppForegroundWatcherCompat;
import com.netease.nimlib.sdk.ModeCode;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MemberPushOption;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.sdk.msg.model.MessageRobotInfo;
import com.netease.nimlib.sdk.msg.model.MsgThreadOption;
import com.netease.nimlib.sdk.msg.model.NIMAntiSpamOption;
import com.netease.nimlib.sdk.robot.model.RobotAttachment;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: MessageReceiver.java */
/* loaded from: classes.dex */
public class g {
    public static IMMessageImpl a(com.netease.nimlib.biz.e.j.ab abVar) {
        IMMessageImpl iMMessageImpl;
        com.netease.nimlib.push.packet.a j = abVar.j();
        long p = j != null ? j.p() : 0L;
        int s = abVar.s();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        com.netease.nimlib.push.packet.b.c a2 = abVar.a();
        boolean equals = C$r8$backportedMethods$utility$Objects$2$equals.equals(a2.c(2), com.netease.nimlib.c.n());
        IMMessageImpl a3 = a(a2, false, !equals);
        if (a3 == null) {
            return null;
        }
        com.netease.nimlib.session.a.c.a().a(a3);
        if (com.netease.nimlib.abtest.b.a()) {
            a(j, a3, s);
        }
        if (equals && (iMMessageImpl = (IMMessageImpl) MsgDBHelper.queryMessageByUuid(a3.getUuid())) != null) {
            MsgStatusEnum msgStatusEnum = MsgStatusEnum.success;
            if (com.netease.nimlib.c.i().fixMsgStatusByBlackList) {
                msgStatusEnum = a3.isInBlackList() ? MsgStatusEnum.fail : MsgStatusEnum.success;
            }
            MsgDBHelper.setMessageStatus(iMMessageImpl.getMessageId(), msgStatusEnum.getValue(), a3.getTime(), a3.getServerId());
            MsgDBHelper.setMessageBlacked(iMMessageImpl.getMessageId(), a3.isInBlackList());
            if (iMMessageImpl.getStatus() != MsgStatusEnum.success || iMMessageImpl.isInBlackList() != a3.isInBlackList()) {
                com.netease.nimlib.i.b.a(a3);
            }
            return a3;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(a3);
        if (j.c((ArrayList<IMMessageImpl>) arrayList)) {
            com.netease.nimlib.n.f.a().a(a3, p, s, elapsedRealtime);
            MsgDBHelper.saveMessage(a3);
            com.netease.nimlib.i.b.a(a((ArrayList<IMMessageImpl>) arrayList, a2.c(6)));
        } else {
            com.netease.nimlib.n.f.a().a(arrayList, p, s, elapsedRealtime);
        }
        j.a(a2);
        return a3;
    }

    private static boolean a(com.netease.nimlib.push.packet.a aVar, long j, long j2, long j3, long j4) {
        if (aVar == null) {
            return false;
        }
        if (com.netease.nimlib.n.f.a.b(com.netease.nimlib.n.f.a.a(), aVar.p()) - j > com.netease.nimlib.abtest.b.b(5000L) && j4 <= j3) {
            return j3 > 0 && j2 - j3 <= com.netease.nimlib.abtest.b.d(5000L);
        }
        return true;
    }

    private static JSONObject a(long j, String str, long j2, String str2, String str3, long j3, long j4, long j5, int i, long j6, long j7) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("desc", "receive messages with much down time");
            jSONObject.put("receiveTime", j3);
            jSONObject.put("serverTime", j);
            if (str != null) {
                jSONObject.put("clientId", str);
            }
            jSONObject.put("serverId", j2);
            if (str2 != null) {
                jSONObject.put("sessionId", str2);
            }
            if (str3 != null) {
                jSONObject.put("sessionType", str3);
            }
            jSONObject.put("downTime", j3 - j);
            jSONObject.put("readPacketHeadTime", j4);
            jSONObject.put("readPacketTailTime", j5);
            jSONObject.put("lastFgSwitchTime", j6);
            jSONObject.put("lastBgSwitchTime", j7);
            jSONObject.put("ntpRtt", com.netease.nimlib.n.f.a.b());
            jSONObject.put("queueSize", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private static void a(com.netease.nimlib.push.packet.a aVar, IMMessageImpl iMMessageImpl, int i) {
        long j;
        long j2;
        long j3;
        boolean a2 = com.netease.nimlib.n.f.a.a();
        if (aVar != null) {
            j = com.netease.nimlib.n.f.a.b(a2, aVar.p());
            j2 = com.netease.nimlib.n.f.a.b(a2, aVar.q());
            j3 = com.netease.nimlib.n.f.a.b(a2, aVar.r());
        } else {
            j = 0;
            j2 = 0;
            j3 = 0;
        }
        long a3 = AppForegroundWatcherCompat.a(a2);
        long b = AppForegroundWatcherCompat.b(a2);
        if (a(aVar, iMMessageImpl.getTime(), j, a3, b)) {
            return;
        }
        com.netease.nimlib.n.e.a(aVar, 200, com.netease.nimlib.n.b.h.kPoorPerformance, com.netease.nimlib.n.b.b.kReceivePacket, a(iMMessageImpl.getTime(), iMMessageImpl.getUuid(), iMMessageImpl.getServerId(), iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType().name(), j, j2, j3, i, a3, b));
    }

    public static IMMessageImpl b(com.netease.nimlib.push.packet.b.c cVar, boolean z) {
        return a(cVar, z, true);
    }

    public static IMMessageImpl a(com.netease.nimlib.push.packet.b.c cVar, boolean z, boolean z2) {
        if (z2 && !c(cVar)) {
            return null;
        }
        IMMessageImpl a2 = a(cVar);
        com.netease.nimlib.log.b.a("toMessage uuid = %s,persist = %s,unique = %s,timeConsumingStatistics = %s", Long.valueOf(a2.getMessageId()), Boolean.valueOf(z), Boolean.valueOf(z2), a2.getTimeConsumingStatistics());
        if (z2 && a2.getMsgFromNick() != null) {
            com.netease.nimlib.log.b.a("toMessage sessionType = %s,account = %s,nick = %s", a2.getSessionType(), a2.getFromAccount(), com.netease.nimlib.log.b.a.a((CharSequence) a2.getMsgFromNick()));
            u.c().a(a2.getSessionId(), a2.getSessionType(), a2.getFromAccount(), a2.getMsgFromNick(), true);
        }
        if (z) {
            MsgDBHelper.saveMessage(a2);
        }
        return a2;
    }

    public static IMMessageImpl a(com.netease.nimlib.push.packet.b.c cVar) {
        String c = cVar.c(2);
        int d = cVar.d(0);
        String b = b(cVar);
        int d2 = cVar.d(8);
        IMMessageImpl iMMessageImpl = new IMMessageImpl();
        iMMessageImpl.setUuid(cVar.c(11));
        iMMessageImpl.setServerId(cVar.e(12));
        iMMessageImpl.setFromAccount(c);
        iMMessageImpl.setSessionId(b);
        iMMessageImpl.setTime(cVar.e(7));
        iMMessageImpl.setSessionType(SessionTypeEnum.typeOfValue(d));
        iMMessageImpl.setMsgType(d2);
        iMMessageImpl.setContent(cVar.c(9));
        iMMessageImpl.setAttachStr(cVar.c(10));
        iMMessageImpl.setRemoteExtensionStr(cVar.c(15));
        iMMessageImpl.setPushContent(cVar.c(17));
        iMMessageImpl.setPushPayloadStr(cVar.c(16));
        iMMessageImpl.setFromClientType(cVar.d(4));
        iMMessageImpl.setInBlackList(cVar.d(106) == 1);
        iMMessageImpl.setSessionUpdate(cVar.d(28) == 1);
        if (cVar.f(26) && cVar.d(26) == 1) {
            iMMessageImpl.setMsgAck();
        }
        if (cVar.f(6)) {
            iMMessageImpl.setMsgFromNick(cVar.c(6));
        }
        a(cVar, iMMessageImpl);
        c(cVar, iMMessageImpl);
        b(cVar, iMMessageImpl);
        d(cVar, iMMessageImpl);
        iMMessageImpl.setDeleted(cVar.d(39) == 1);
        iMMessageImpl.setCallbackExtension(cVar.c(40));
        iMMessageImpl.setSubtype(cVar.d(41));
        iMMessageImpl.setYidunAntiCheating(cVar.c(42));
        iMMessageImpl.setEnv(cVar.c(43));
        iMMessageImpl.setYidunAntiSpamExt(cVar.c(44));
        iMMessageImpl.setYidunAntiSpamRes(cVar.c(45));
        if (cVar.f(46)) {
            iMMessageImpl.setTimeConsumingStatistics(ac.a(cVar.c(46)));
        }
        String c2 = cVar.c(47);
        String c3 = cVar.c(48);
        String c4 = cVar.c(49);
        String c5 = cVar.c(50);
        if (!TextUtils.isEmpty(c2) || !TextUtils.isEmpty(c3) || !TextUtils.isEmpty(c4) || !TextUtils.isEmpty(c5)) {
            iMMessageImpl.setRobotInfo(new MessageRobotInfo(c2, c3, c4, c5));
        }
        MsgStatusEnum msgStatusEnum = MsgStatusEnum.success;
        if (com.netease.nimlib.c.i().fixMsgStatusByBlackList) {
            msgStatusEnum = iMMessageImpl.isInBlackList() ? MsgStatusEnum.fail : MsgStatusEnum.success;
        }
        iMMessageImpl.setStatus(msgStatusEnum);
        iMMessageImpl.setAttachStatus(AttachStatusEnum.def);
        a(iMMessageImpl, cVar.c(5));
        return iMMessageImpl;
    }

    private static void a(com.netease.nimlib.push.packet.b.c cVar, IMMessageImpl iMMessageImpl) {
        CustomMessageConfig customMessageConfig = new CustomMessageConfig();
        if (cVar.f(100)) {
            customMessageConfig.enableHistory = cVar.d(100) == 1;
        }
        if (cVar.f(101)) {
            customMessageConfig.enableRoaming = cVar.d(101) == 1;
        }
        if (cVar.f(102)) {
            customMessageConfig.enableSelfSync = cVar.d(102) == 1;
        }
        if (cVar.f(107)) {
            customMessageConfig.enablePush = cVar.d(107) == 1;
        }
        if (cVar.f(108)) {
            customMessageConfig.enablePersist = cVar.d(108) == 1;
        }
        if (cVar.f(109)) {
            customMessageConfig.enableUnreadCount = cVar.d(109) == 1;
        }
        if (cVar.f(110)) {
            customMessageConfig.enablePushNick = cVar.d(110) == 1;
        }
        if (cVar.f(105)) {
            customMessageConfig.enableRoute = cVar.d(105) == 1;
        }
        iMMessageImpl.setConfig(customMessageConfig);
    }

    private static void b(com.netease.nimlib.push.packet.b.c cVar, IMMessageImpl iMMessageImpl) {
        NIMAntiSpamOption nIMAntiSpamOption = new NIMAntiSpamOption();
        boolean z = true;
        if (cVar.f(25)) {
            nIMAntiSpamOption.enable = cVar.d(25) == 1;
            r3 = true;
        }
        if (cVar.f(22)) {
            nIMAntiSpamOption.content = cVar.c(22);
            r3 = true;
        }
        if (cVar.f(23)) {
            nIMAntiSpamOption.antiSpamConfigId = cVar.c(23);
        } else {
            z = r3;
        }
        if (z) {
            iMMessageImpl.setNIMAntiSpamOption(nIMAntiSpamOption);
        }
    }

    private static void c(com.netease.nimlib.push.packet.b.c cVar, IMMessageImpl iMMessageImpl) {
        MemberPushOption memberPushOption = new MemberPushOption();
        boolean z = true;
        if (cVar.f(20)) {
            memberPushOption.setForcePush(cVar.d(20) == 1);
            r3 = true;
        }
        if (cVar.f(19)) {
            memberPushOption.setForcePushContent(cVar.c(19));
            r3 = true;
        }
        if (cVar.f(18)) {
            String c = cVar.c(18);
            if (c.equals("#%@all@%#")) {
                memberPushOption.setForcePushList(null);
            } else {
                memberPushOption.setForcePushList(j.b(c));
            }
        } else {
            z = r3;
        }
        if (z) {
            iMMessageImpl.setMemberPushOption(memberPushOption);
        }
    }

    private static void d(com.netease.nimlib.push.packet.b.c cVar, IMMessageImpl iMMessageImpl) {
        if (cVar.c(37) == null) {
            return;
        }
        MsgThreadOption msgThreadOption = new MsgThreadOption();
        msgThreadOption.setReplyMsgFromAccount(cVar.c(29));
        msgThreadOption.setReplyMsgToAccount(cVar.c(30));
        msgThreadOption.setReplyMsgTime(cVar.e(31));
        msgThreadOption.setReplyMsgIdServer(cVar.e(32));
        msgThreadOption.setReplyMsgIdClient(cVar.c(33));
        msgThreadOption.setThreadMsgFromAccount(cVar.c(34));
        msgThreadOption.setThreadMsgToAccount(cVar.c(35));
        msgThreadOption.setThreadMsgTime(cVar.e(36));
        msgThreadOption.setThreadMsgIdServer(cVar.e(37));
        msgThreadOption.setThreadMsgIdClient(cVar.c(38));
        iMMessageImpl.setThreadOption(msgThreadOption);
    }

    public static void b(ArrayList<IMMessageImpl> arrayList) {
        a(arrayList, new a());
    }

    public static com.netease.nimlib.net.a.a.e b(IMMessageImpl iMMessageImpl, boolean z, com.netease.nimlib.i.k kVar) {
        return a(iMMessageImpl, z, kVar, new a());
    }

    protected static void a(ArrayList<IMMessageImpl> arrayList, a aVar) {
        Iterator<IMMessageImpl> it = arrayList.iterator();
        while (it.hasNext()) {
            IMMessageImpl next = it.next();
            if (com.netease.nimlib.c.i().preloadAttach && (next.getAttachment() instanceof FileAttachment) && next.getMsgType() != MsgTypeEnum.custom && next.getMsgType() != MsgTypeEnum.file) {
                a(next, true, null, aVar);
            }
        }
    }

    protected static com.netease.nimlib.net.a.a.e a(IMMessageImpl iMMessageImpl, boolean z, com.netease.nimlib.i.k kVar, a aVar) {
        FileAttachment fileAttachment = (FileAttachment) iMMessageImpl.getAttachment();
        String url = fileAttachment.getUrl();
        if (TextUtils.isEmpty(url)) {
            b(kVar, 414);
            return null;
        }
        String pathForSave = fileAttachment.getPathForSave();
        if (z && (iMMessageImpl.getMsgType() == MsgTypeEnum.image || iMMessageImpl.getMsgType() == MsgTypeEnum.video)) {
            pathForSave = fileAttachment.getThumbPathForSave();
            url = j.a(fileAttachment, url);
        }
        String str = url;
        String str2 = pathForSave;
        if (new File(str2).exists()) {
            if (iMMessageImpl.getAttachStatus() == AttachStatusEnum.transferred) {
                b(kVar, 414);
            } else {
                b(aVar, iMMessageImpl, AttachStatusEnum.transferred);
                b(kVar, 200);
            }
            return null;
        }
        b(aVar, iMMessageImpl, AttachStatusEnum.transferring);
        return a(iMMessageImpl, z, kVar, aVar, fileAttachment, str, str2);
    }

    /* compiled from: MessageReceiver.java */
    /* renamed from: com.netease.nimlib.session.g$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 implements com.netease.nimlib.net.a.a.f {
        final /* synthetic */ a b;
        final /* synthetic */ IMMessageImpl c;
        final /* synthetic */ com.netease.nimlib.i.k d;
        private long e;

        @Override // com.netease.nimlib.net.a.a.f
        public void onStart(com.netease.nimlib.net.a.a.e eVar) {
        }

        AnonymousClass1(a aVar, IMMessageImpl iMMessageImpl, com.netease.nimlib.i.k kVar) {
            r2 = aVar;
            r3 = iMMessageImpl;
            r4 = kVar;
            this.e = FileAttachment.this.getSize();
        }

        @Override // com.netease.nimlib.net.a.a.f
        public void onOK(com.netease.nimlib.net.a.a.e eVar) {
            a aVar = r2;
            IMMessageImpl iMMessageImpl = r3;
            long j = this.e;
            aVar.a(iMMessageImpl, j, j);
            g.b(r2, r3, AttachStatusEnum.transferred);
            g.b(r4, 200);
            g.b(eVar);
        }

        @Override // com.netease.nimlib.net.a.a.f
        public void onFail(com.netease.nimlib.net.a.a.e eVar, String str) {
            g.b(r2, r3, AttachStatusEnum.fail);
            g.b(r4, 500);
            g.b(eVar);
        }

        @Override // com.netease.nimlib.net.a.a.f
        public void onCancel(com.netease.nimlib.net.a.a.e eVar) {
            g.b(r2, r3, AttachStatusEnum.fail);
            g.b(eVar);
        }

        @Override // com.netease.nimlib.net.a.a.f
        public void onProgress(com.netease.nimlib.net.a.a.e eVar, long j) {
            r2.a(r3, j, this.e);
        }

        @Override // com.netease.nimlib.net.a.a.f
        public void onGetLength(com.netease.nimlib.net.a.a.e eVar, long j) {
            this.e = j;
        }

        @Override // com.netease.nimlib.net.a.a.f
        public void onExpire(com.netease.nimlib.net.a.a.e eVar, String str) {
            g.b(r2, r3, AttachStatusEnum.fail);
            g.b(r4, 4);
            g.b(eVar);
        }
    }

    private static com.netease.nimlib.net.a.a.e a(IMMessageImpl iMMessageImpl, boolean z, com.netease.nimlib.i.k kVar, a aVar, FileAttachment fileAttachment, String str, String str2) {
        com.netease.nimlib.net.a.a.e a2;
        com.netease.nimlib.net.a.a.d dVar = new com.netease.nimlib.net.a.a.d(new com.netease.nimlib.net.a.a.f() { // from class: com.netease.nimlib.session.g.1
            final /* synthetic */ a b;
            final /* synthetic */ IMMessageImpl c;
            final /* synthetic */ com.netease.nimlib.i.k d;
            private long e;

            @Override // com.netease.nimlib.net.a.a.f
            public void onStart(com.netease.nimlib.net.a.a.e eVar) {
            }

            AnonymousClass1(a aVar2, IMMessageImpl iMMessageImpl2, com.netease.nimlib.i.k kVar2) {
                r2 = aVar2;
                r3 = iMMessageImpl2;
                r4 = kVar2;
                this.e = FileAttachment.this.getSize();
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onOK(com.netease.nimlib.net.a.a.e eVar) {
                a aVar2 = r2;
                IMMessageImpl iMMessageImpl2 = r3;
                long j = this.e;
                aVar2.a(iMMessageImpl2, j, j);
                g.b(r2, r3, AttachStatusEnum.transferred);
                g.b(r4, 200);
                g.b(eVar);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onFail(com.netease.nimlib.net.a.a.e eVar, String str3) {
                g.b(r2, r3, AttachStatusEnum.fail);
                g.b(r4, 500);
                g.b(eVar);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onCancel(com.netease.nimlib.net.a.a.e eVar) {
                g.b(r2, r3, AttachStatusEnum.fail);
                g.b(eVar);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onProgress(com.netease.nimlib.net.a.a.e eVar, long j) {
                r2.a(r3, j, this.e);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onGetLength(com.netease.nimlib.net.a.a.e eVar, long j) {
                this.e = j;
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onExpire(com.netease.nimlib.net.a.a.e eVar, String str3) {
                g.b(r2, r3, AttachStatusEnum.fail);
                g.b(r4, 4);
                g.b(eVar);
            }
        });
        if (com.netease.nimlib.biz.b.e.d().a()) {
            int i = com.netease.nimlib.c.i().thumbnailSize;
            if (i <= 0) {
                DisplayMetrics displayMetrics = com.netease.nimlib.c.e().getApplicationContext().getResources().getDisplayMetrics();
                i = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) / 2;
            }
            int i2 = i;
            if (z && (fileAttachment instanceof ImageAttachment)) {
                a2 = com.netease.nimlib.biz.b.e.d().a(str, str2, i2, i2, dVar);
            } else if (z && (fileAttachment instanceof VideoAttachment)) {
                a2 = com.netease.nimlib.biz.b.e.d().b(str, str2, i2, i2, dVar);
            } else {
                a2 = com.netease.nimlib.biz.b.e.d().a(str, str2, dVar);
            }
            if (iMMessageImpl2.getSessionType() == SessionTypeEnum.ChatRoom) {
                a2.b(iMMessageImpl2.getSessionId());
            }
            return a2;
        }
        com.netease.nimlib.net.a.a.e eVar = new com.netease.nimlib.net.a.a.e(str, str2, dVar);
        if (iMMessageImpl2.getSessionType() == SessionTypeEnum.ChatRoom) {
            eVar.b(iMMessageImpl2.getSessionId());
        }
        if (j.e(str) || (fileAttachment.getNosTokenSceneKey() != null && fileAttachment.getNosTokenSceneKey().startsWith(NimNosSceneKeyConstant.NIM_SECURITY_PREFIX))) {
            com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.d.i(str)) { // from class: com.netease.nimlib.session.g.2
                final /* synthetic */ String a;
                final /* synthetic */ com.netease.nimlib.net.a.a.e b;
                final /* synthetic */ com.netease.nimlib.i.k c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass2(com.netease.nimlib.biz.d.a aVar2, String str3, com.netease.nimlib.net.a.a.e eVar2, com.netease.nimlib.i.k kVar2) {
                    super(aVar2);
                    r2 = str3;
                    r3 = eVar2;
                    r4 = kVar2;
                }

                @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
                public void a(com.netease.nimlib.biz.e.a aVar2) {
                    super.a(aVar2);
                    if (!aVar2.n() || !(aVar2 instanceof com.netease.nimlib.biz.e.d.f)) {
                        g.b(r4, 4);
                        return;
                    }
                    String a3 = ((com.netease.nimlib.biz.e.d.f) aVar2).a();
                    String str3 = r2;
                    if (!TextUtils.isEmpty(a3)) {
                        if (r2.contains("?")) {
                            str3 = str3 + "&token=" + a3;
                        } else {
                            str3 = str3 + "?token=" + a3;
                        }
                    }
                    r3.a(str3);
                    com.netease.nimlib.net.a.a.g.a().a(r3);
                }
            });
        } else {
            com.netease.nimlib.net.a.a.g.a().a(eVar2);
        }
        return eVar2;
    }

    /* compiled from: MessageReceiver.java */
    /* renamed from: com.netease.nimlib.session.g$2 */
    /* loaded from: classes.dex */
    static class AnonymousClass2 extends com.netease.nimlib.biz.g.b {
        final /* synthetic */ String a;
        final /* synthetic */ com.netease.nimlib.net.a.a.e b;
        final /* synthetic */ com.netease.nimlib.i.k c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(com.netease.nimlib.biz.d.a aVar2, String str3, com.netease.nimlib.net.a.a.e eVar2, com.netease.nimlib.i.k kVar2) {
            super(aVar2);
            r2 = str3;
            r3 = eVar2;
            r4 = kVar2;
        }

        @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
        public void a(com.netease.nimlib.biz.e.a aVar2) {
            super.a(aVar2);
            if (!aVar2.n() || !(aVar2 instanceof com.netease.nimlib.biz.e.d.f)) {
                g.b(r4, 4);
                return;
            }
            String a3 = ((com.netease.nimlib.biz.e.d.f) aVar2).a();
            String str3 = r2;
            if (!TextUtils.isEmpty(a3)) {
                if (r2.contains("?")) {
                    str3 = str3 + "&token=" + a3;
                } else {
                    str3 = str3 + "?token=" + a3;
                }
            }
            r3.a(str3);
            com.netease.nimlib.net.a.a.g.a().a(r3);
        }
    }

    public static void b(com.netease.nimlib.net.a.a.e eVar) {
        String f = eVar.f();
        if (TextUtils.isEmpty(f)) {
            return;
        }
        String str = null;
        try {
            str = Uri.parse(f).getQueryParameter("token");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.d.d.a(str));
    }

    public static void b(com.netease.nimlib.i.k kVar, int i) {
        if (kVar != null) {
            kVar.a(i).b();
        }
    }

    public static void b(a aVar, IMMessageImpl iMMessageImpl, AttachStatusEnum attachStatusEnum) {
        iMMessageImpl.setAttachStatus(attachStatusEnum);
        if (attachStatusEnum == AttachStatusEnum.transferring) {
            d.a().d(iMMessageImpl.getUuid());
        } else {
            d.a().e(iMMessageImpl.getUuid());
            if (com.netease.nimlib.h.f() == ModeCode.IM) {
                MsgDBHelper.updateAttachStatus(iMMessageImpl.getUuid(), attachStatusEnum.getValue());
            }
        }
        aVar.a(iMMessageImpl, attachStatusEnum);
    }

    public static q a(ArrayList<IMMessageImpl> arrayList, String str) {
        return a(arrayList, str, true);
    }

    public static q a(ArrayList<IMMessageImpl> arrayList, String str, boolean z) {
        IMMessageImpl iMMessageImpl = arrayList.get(arrayList.size() - 1);
        long querySessionReadTimeTag = MsgDBHelper.querySessionReadTimeTag(iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType());
        boolean equals = j.a(iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType().getValue()).equals(com.netease.nimlib.h.d());
        b(arrayList);
        Iterator<IMMessageImpl> it = arrayList.iterator();
        int i = 0;
        long j = 0;
        while (it.hasNext()) {
            IMMessageImpl next = it.next();
            if (!equals && j.a((IMMessage) next, true, querySessionReadTimeTag)) {
                i++;
            }
            if (equals && next.getDirect() == MsgDirectionEnum.In) {
                j = Math.max(next.getTime(), j);
            }
        }
        if (j > 0 && com.netease.nimlib.c.i().sessionReadAck) {
            v.a(iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType(), j);
            v.a(iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType(), j, null);
        }
        q a2 = a(iMMessageImpl, i);
        com.netease.nimlib.i.b.b(arrayList);
        if (z) {
            a(arrayList, str, i);
        }
        return a2;
    }

    public static String b(com.netease.nimlib.push.packet.b.c cVar) {
        int d = cVar.d(0);
        String c = cVar.c(2);
        String c2 = cVar.c(1);
        if (TextUtils.isEmpty(c)) {
            com.netease.nimlib.log.b.f("MessageReceiver", "get fromId from IMMessage Property, fromId is null !!!");
        }
        return ((d == 0 || d == 2) && !TextUtils.equals(com.netease.nimlib.c.n(), c)) ? c : c2;
    }

    public static String a(MessageKey messageKey) {
        SessionTypeEnum sessionType = messageKey.getSessionType();
        String fromAccount = messageKey.getFromAccount();
        String toAccount = messageKey.getToAccount();
        if (TextUtils.isEmpty(fromAccount)) {
            com.netease.nimlib.log.b.f("MessageReceiver", "get fromId from IMMessage Property, fromId is null !!!");
        }
        return (sessionType != SessionTypeEnum.P2P || TextUtils.equals(com.netease.nimlib.c.n(), fromAccount)) ? toAccount : fromAccount;
    }

    public static String a(IMMessageImpl iMMessageImpl) {
        String n;
        if (iMMessageImpl.getSessionType() == SessionTypeEnum.None || (n = com.netease.nimlib.c.n()) == null) {
            return "";
        }
        return (!(iMMessageImpl.getSessionType() == SessionTypeEnum.P2P) || n.equals(iMMessageImpl.getFromAccount())) ? iMMessageImpl.getSessionId() : n;
    }

    private static void a(ArrayList<IMMessageImpl> arrayList, String str, int i) {
        com.netease.nimlib.l.d.a(arrayList, str, i);
    }

    private static q a(IMMessageImpl iMMessageImpl, int i) {
        return j.a(iMMessageImpl, i);
    }

    public static boolean c(com.netease.nimlib.push.packet.b.c cVar) {
        return a(cVar.c(11));
    }

    public static boolean a(String str) {
        if (!com.netease.nimlib.o.w.b((CharSequence) str) || MsgDBHelper.queryMessageIdByUuid(str) == 0) {
            return true;
        }
        com.netease.nimlib.log.b.N("msg has exist, msg_id=" + str);
        return false;
    }

    protected static void a(IMMessageImpl iMMessageImpl, String str) {
        RobotAttachment robotAttachment;
        boolean equals = TextUtils.equals(com.netease.nimlib.c.n(), iMMessageImpl.getFromAccount());
        if (equals && iMMessageImpl.getMsgType() == MsgTypeEnum.robot && (robotAttachment = (RobotAttachment) iMMessageImpl.getAttachment()) != null) {
            equals = !robotAttachment.isRobotSend();
        }
        if (equals && TextUtils.equals(com.netease.nimlib.c.n(), iMMessageImpl.getSessionId())) {
            equals = TextUtils.equals(str, com.netease.nimlib.push.b.c());
        }
        iMMessageImpl.setDirect(equals ? MsgDirectionEnum.Out : MsgDirectionEnum.In);
    }

    /* compiled from: MessageReceiver.java */
    /* loaded from: classes.dex */
    public static class a {
        protected a() {
        }

        public void a(IMMessageImpl iMMessageImpl, AttachStatusEnum attachStatusEnum) {
            com.netease.nimlib.i.b.a(iMMessageImpl);
        }

        public void a(IMMessageImpl iMMessageImpl, long j, long j2) {
            com.netease.nimlib.i.b.a(iMMessageImpl.getUuid(), j, j2);
        }
    }
}
