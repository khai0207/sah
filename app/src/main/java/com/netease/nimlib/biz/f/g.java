package com.netease.nimlib.biz.f;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.biz.d.i.p;
import com.netease.nimlib.biz.f.g;
import com.netease.nimlib.net.a.b.a;
import com.netease.nimlib.o.f;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.migration.processor.IMsgExportProcessor;
import com.netease.nimlib.sdk.migration.processor.IMsgImportProcessor;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.DeleteTypeEnum;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CollectInfo;
import com.netease.nimlib.sdk.msg.model.CollectInfoPage;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.GetMessageDirectionEnum;
import com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyParam;
import com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyResult;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.LocalAntiSpamResult;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import com.netease.nimlib.sdk.msg.model.MsgFullKeywordSearchConfig;
import com.netease.nimlib.sdk.msg.model.MsgPinDbOption;
import com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOptionWrapper;
import com.netease.nimlib.sdk.msg.model.MsgSearchOption;
import com.netease.nimlib.sdk.msg.model.MsgTimingFullKeywordSearchConfig;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.QueryMySessionOption;
import com.netease.nimlib.sdk.msg.model.QueryThreadTalkHistoryOption;
import com.netease.nimlib.sdk.msg.model.QuickCommentOptionWrapper;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.msg.model.RecentSession;
import com.netease.nimlib.sdk.msg.model.RecentSessionList;
import com.netease.nimlib.sdk.msg.model.RoamMsgHasMoreOption;
import com.netease.nimlib.sdk.msg.model.SessionAckInfo;
import com.netease.nimlib.sdk.msg.model.ShowNotificationWhenRevokeFilter;
import com.netease.nimlib.sdk.msg.model.StickTopSessionInfo;
import com.netease.nimlib.sdk.msg.model.ThreadTalkHistory;
import com.netease.nimlib.sdk.search.model.MsgIndexRecord;
import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import com.netease.nimlib.sdk.team.model.IMMessageFilter;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.a.c;
import com.netease.nimlib.session.q;
import com.netease.nimlib.session.v;
import com.netease.nimlib.session.y;
import com.netease.nimlib.superteam.SuperTeamDBHelper;
import com.netease.nimlib.team.TeamDBHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: MsgServiceRemote.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.i.j implements MsgService {
    private int a(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > 100) {
            return 100;
        }
        return i;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> sendMessage(IMMessage iMMessage, boolean z) {
        com.netease.nimlib.session.h.a((IMMessageImpl) iMMessage, z, b(), SystemClock.elapsedRealtime());
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> replyMessage(IMMessage iMMessage, IMMessage iMMessage2, boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        com.netease.nimlib.i.k b = b();
        if (!com.netease.nimlib.session.h.a(iMMessage, iMMessage2)) {
            b.a(414).b();
            return null;
        }
        IMMessageImpl iMMessageImpl = (IMMessageImpl) iMMessage;
        iMMessageImpl.setThreadOption(iMMessage2);
        com.netease.nimlib.session.h.a(iMMessageImpl, z, b(), elapsedRealtime);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> saveMessageToLocal(IMMessage iMMessage, boolean z) {
        IMMessageImpl iMMessageImpl = (IMMessageImpl) iMMessage;
        MsgDBHelper.saveMessage(iMMessageImpl);
        com.netease.nimlib.i.b.a(com.netease.nimlib.session.j.c(iMMessageImpl));
        b().a(200).b();
        if (!z) {
            return null;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(iMMessageImpl);
        com.netease.nimlib.i.b.b(arrayList);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public AbortableFuture<FileAttachment> sendFile(FileAttachment fileAttachment) {
        final a.c a = com.netease.nimlib.session.h.a(fileAttachment, b());
        return new com.netease.nimlib.i.h<FileAttachment>(null) { // from class: com.netease.nimlib.biz.f.g.1
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                com.netease.nimlib.net.a.b.a.a().a(a);
                return false;
            }
        };
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> insertLocalMessage(IMMessage iMMessage, String str) {
        IMMessageImpl deepClone = ((IMMessageImpl) iMMessage).deepClone();
        deepClone.setFromAccount(str);
        deepClone.setDirect(MsgDirectionEnum.In);
        deepClone.setStatus(MsgStatusEnum.success);
        return saveMessageToLocal(deepClone, true);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> saveMessageToLocalEx(IMMessage iMMessage, boolean z, long j) {
        if (iMMessage == null) {
            return null;
        }
        IMMessageImpl iMMessageImpl = (IMMessageImpl) iMMessage;
        if (j >= 0) {
            iMMessageImpl.setTime(j);
        }
        MsgDBHelper.saveMessage(iMMessageImpl);
        q queryRecentContact = MsgDBHelper.queryRecentContact(iMMessage.getSessionId(), iMMessage.getSessionType());
        if (queryRecentContact == null) {
            com.netease.nimlib.i.b.a(com.netease.nimlib.session.j.c(iMMessageImpl));
        } else if (queryRecentContact.getTime() <= j) {
            q c = com.netease.nimlib.session.j.c(iMMessageImpl);
            c.a(j);
            com.netease.nimlib.i.b.a(c);
        }
        b().a(200).b();
        if (z) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(iMMessageImpl);
            com.netease.nimlib.i.b.b(arrayList);
        }
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public AbortableFuture downloadAttachment(IMMessage iMMessage, boolean z) {
        com.netease.nimlib.net.a.a.e b = com.netease.nimlib.session.g.b((IMMessageImpl) iMMessage, z, b());
        if (b == null) {
            return null;
        }
        return new com.netease.nimlib.i.h<com.netease.nimlib.net.a.a.e>(b) { // from class: com.netease.nimlib.biz.f.g.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                if (com.netease.nimlib.biz.b.e.d().a()) {
                    com.netease.nimlib.biz.b.e.d().a((com.netease.nimlib.net.a.a.e) this.c);
                    return false;
                }
                com.netease.nimlib.net.a.a.g.a().b((com.netease.nimlib.net.a.a.e) this.c);
                return false;
            }
        };
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<IMMessage> queryMessageListByUuidBlock(List<String> list) {
        List<IMMessage> queryMsgListByUuid = MsgDBHelper.queryMsgListByUuid(list);
        b(queryMsgListByUuid);
        return queryMsgListByUuid;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryMessageListByUuid(List<String> list) {
        return a(MsgDBHelper.queryMsgListByUuid(list));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<IMMessage> queryMessageListByServerIdBlock(List<String> list) {
        List<IMMessage> queryMsgListByServerId = MsgDBHelper.queryMsgListByServerId(list);
        b(queryMsgListByServerId);
        return queryMsgListByServerId;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryMessageListByType(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i) {
        return a(MsgDBHelper.queryMessageListByType(msgTypeEnum, iMMessage, i));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryMessageListByType(MsgTypeEnum msgTypeEnum, Long l, int i) {
        com.netease.nimlib.i.k b = b();
        if (i <= 0 && l != null) {
            b.a(414).b();
            return null;
        }
        List<IMMessage> queryMessageListByType = MsgDBHelper.queryMessageListByType(msgTypeEnum, l, i);
        if (queryMessageListByType == null || queryMessageListByType.size() == 0) {
            b.b((Object) null).b();
        } else {
            b(queryMessageListByType);
            b.b(queryMessageListByType).b();
        }
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<IMMessage> queryMessageListBySubtypeBlock(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i, int i2) {
        return MsgDBHelper.queryMessageListBySubtype(msgTypeEnum, iMMessage, i, i2);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryMessageListBySubtype(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i, int i2) {
        a(MsgDBHelper.queryMessageListBySubtype(msgTypeEnum, iMMessage, i, i2));
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public IMMessage queryLastMessage(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelper.queryLatestMessage(str, sessionTypeEnum.getValue());
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryMessageList(String str, SessionTypeEnum sessionTypeEnum, long j, int i) {
        return a(MsgDBHelper.queryMessageList(str, sessionTypeEnum.getValue(), j, i));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryMessageListEx(IMMessage iMMessage, QueryDirectionEnum queryDirectionEnum, int i, boolean z) {
        return a(MsgDBHelper.queryMessageListEx((IMMessageImpl) iMMessage, queryDirectionEnum, i, z));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<IMMessage> queryMessageListExBlock(IMMessage iMMessage, QueryDirectionEnum queryDirectionEnum, int i, boolean z) {
        return MsgDBHelper.queryMessageListEx((IMMessageImpl) iMMessage, queryDirectionEnum, i, z);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryMessageListByTypes(List<MsgTypeEnum> list, IMMessage iMMessage, long j, QueryDirectionEnum queryDirectionEnum, int i, boolean z) {
        return a(MsgDBHelper.queryMessageListExWrapper(list, (IMMessageImpl) iMMessage, j, queryDirectionEnum, i, z, false));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryMessageListByTypesV2(List<MsgTypeEnum> list, IMMessage iMMessage, long j, QueryDirectionEnum queryDirectionEnum, int i, boolean z) {
        return a(MsgDBHelper.queryMessageListExWrapper(list, (IMMessageImpl) iMMessage, j, queryDirectionEnum, i, z, true));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Long> queryRoamMsgHasMoreTime(String str, SessionTypeEnum sessionTypeEnum) {
        b().b(Long.valueOf(MsgDBHelper.queryRoamMsgHasMoreTime(str, sessionTypeEnum))).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public long queryRoamMsgHasMoreTimeBlock(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelper.queryRoamMsgHasMoreTime(str, sessionTypeEnum);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Long> queryRoamMsgHasMoreTagServerId(String str, SessionTypeEnum sessionTypeEnum) {
        b().b(Long.valueOf(MsgDBHelper.queryRoamMsgHasMoreServerId(str, sessionTypeEnum))).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public long queryRoamMsgHasMoreTagServerIdBlock(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelper.queryRoamMsgHasMoreServerId(str, sessionTypeEnum);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryMessageListExTime(IMMessage iMMessage, long j, QueryDirectionEnum queryDirectionEnum, int i) {
        return a(MsgDBHelper.queryMessageListEx(null, (IMMessageImpl) iMMessage, j, queryDirectionEnum, i, false));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullHistoryById(List<MessageKey> list, boolean z) {
        com.netease.nimlib.biz.d.j.h hVar = new com.netease.nimlib.biz.d.j.h(list, z);
        hVar.a(b());
        com.netease.nimlib.biz.i.a().a(hVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistory(IMMessage iMMessage, int i, boolean z) {
        return a(iMMessage, 0L, i, QueryDirectionEnum.QUERY_OLD, z, null, true, null, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistory(IMMessage iMMessage, int i, boolean z, boolean z2) {
        return a(iMMessage, 0L, i, QueryDirectionEnum.QUERY_OLD, z, null, z2, null, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistoryEx(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, boolean z) {
        return a(iMMessage, j, i, queryDirectionEnum, z, null, true, null, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr) {
        return a(iMMessage, j, i, queryDirectionEnum, false, msgTypeEnumArr, true, null, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr, boolean z) {
        return a(iMMessage, j, i, queryDirectionEnum, z, msgTypeEnumArr, true, null, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr, boolean z, boolean z2) {
        return a(iMMessage, j, i, queryDirectionEnum, z, msgTypeEnumArr, z2, null, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr, boolean z, boolean z2, IMMessageFilter iMMessageFilter) {
        return a(iMMessage, j, i, queryDirectionEnum, z, msgTypeEnumArr, z2, iMMessageFilter, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr, boolean z, boolean z2, IMMessageFilter iMMessageFilter, boolean z3) {
        return a(iMMessage, j, i, queryDirectionEnum, z, msgTypeEnumArr, z2, iMMessageFilter, z3);
    }

    private InvocationFuture<List<IMMessage>> a(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, boolean z, MsgTypeEnum[] msgTypeEnumArr, boolean z2, IMMessageFilter iMMessageFilter, boolean z3) {
        if (!(iMMessage instanceof IMMessageImpl)) {
            com.netease.nimlib.log.b.d("MsgServiceRemote", "cancel pull msg history, anchor is " + iMMessage);
            return null;
        }
        IMMessageImpl iMMessageImpl = (IMMessageImpl) iMMessage;
        if (iMMessageImpl.getSessionType() == SessionTypeEnum.Team || iMMessageImpl.getSessionType() == SessionTypeEnum.SUPER_TEAM) {
            try {
                Long.valueOf(iMMessageImpl.getSessionId());
            } catch (Exception unused) {
                throw new IllegalArgumentException("sessionID cast to long exception, team sessionID must be Long value String");
            }
        }
        if (msgTypeEnumArr != null) {
            for (MsgTypeEnum msgTypeEnum : msgTypeEnumArr) {
                if (msgTypeEnum == MsgTypeEnum.undef) {
                    throw new IllegalArgumentException("typeEnum params of this method have illegal value");
                }
            }
        }
        boolean z4 = queryDirectionEnum != QueryDirectionEnum.QUERY_OLD;
        com.netease.nimlib.biz.d.i.k kVar = new com.netease.nimlib.biz.d.i.k(iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType(), z4 ? iMMessage.getTime() : j, z4 ? j : iMMessage.getTime(), iMMessageImpl.getServerId(), i, z4, z, msgTypeEnumArr, z2, iMMessageFilter, z3);
        kVar.a(b());
        com.netease.nimlib.biz.i.a().a(kVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistory(MsgFullKeywordSearchConfig msgFullKeywordSearchConfig) {
        com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.d.i.m a = com.netease.nimlib.biz.d.i.m.a(msgFullKeywordSearchConfig);
        if (a == null) {
            com.netease.nimlib.log.b.d("MsgServiceRemote", "pullMessageHistory failed: " + msgFullKeywordSearchConfig);
            b.a(414).b();
            return null;
        }
        a.a(b);
        com.netease.nimlib.biz.i.a().a(a);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> pullMessageHistoryOrderByTime(MsgTimingFullKeywordSearchConfig msgTimingFullKeywordSearchConfig) {
        com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.d.i.n a = com.netease.nimlib.biz.d.i.n.a(msgTimingFullKeywordSearchConfig);
        if (a == null) {
            com.netease.nimlib.log.b.d("MsgServiceRemote", "pullMessageHistoryOrderByTime failed: " + msgTimingFullKeywordSearchConfig);
            b.a(414).b();
            return null;
        }
        a.a(b);
        com.netease.nimlib.biz.i.a().a(a);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<GetMessagesDynamicallyResult> getMessagesDynamically(GetMessagesDynamicallyParam getMessagesDynamicallyParam) {
        com.netease.nimlib.log.b.c("MsgServiceRemote", String.format("getMessagesDynamically with %s", getMessagesDynamicallyParam));
        final com.netease.nimlib.i.k b = b();
        if (getMessagesDynamicallyParam == null || !getMessagesDynamicallyParam.isValid()) {
            b.a(414).b();
            return null;
        }
        final GetMessagesDynamicallyParam createStandardizedParam = getMessagesDynamicallyParam.createStandardizedParam();
        final String sessionId = createStandardizedParam.getSessionId();
        final SessionTypeEnum sessionType = createStandardizedParam.getSessionType();
        long fromTime = createStandardizedParam.getFromTime();
        long toTime = createStandardizedParam.getToTime();
        final int limit = createStandardizedParam.getLimit();
        GetMessageDirectionEnum direction = createStandardizedParam.getDirection();
        if (direction == null) {
            direction = GetMessageDirectionEnum.FORWARD;
        }
        if (direction != GetMessageDirectionEnum.BACKWARD) {
            fromTime = toTime;
        }
        int i = AnonymousClass3.a[y.a(com.netease.nimlib.push.e.b()).ordinal()];
        if (i != 1) {
            if (i == 2) {
                com.netease.nimlib.session.a.c.a().a(createStandardizedParam, b);
                return null;
            }
            b.b(new com.netease.nimlib.session.c(false, com.netease.nimlib.session.a.c.a().c(createStandardizedParam))).b();
            return null;
        }
        if (direction == GetMessageDirectionEnum.BACKWARD && fromTime <= 0) {
            com.netease.nimlib.session.a.c.a().a(createStandardizedParam, b);
            return null;
        }
        com.netease.nimlib.session.a.d b2 = com.netease.nimlib.session.a.c.a().b(createStandardizedParam);
        if (b2 == null) {
            if (com.netease.nimlib.session.a.c.a().a(createStandardizedParam)) {
                com.netease.nimlib.session.a.c.a().a(sessionId, sessionType, new c.a() { // from class: com.netease.nimlib.biz.f.-$$Lambda$g$LKmR2k5g4gC96cc5GIq9pjx7SzA
                    @Override // com.netease.nimlib.session.a.c.a
                    public final Object onCallback(Object obj) {
                        Void a;
                        a = g.a(GetMessagesDynamicallyParam.this, b, sessionId, sessionType, limit, (IMMessage) obj);
                        return a;
                    }
                });
                return null;
            }
            com.netease.nimlib.session.a.c.a().a(createStandardizedParam, b);
            return null;
        }
        ArrayList<IMMessage> a = com.netease.nimlib.session.a.c.a().a(createStandardizedParam, b2);
        com.netease.nimlib.session.a.d a2 = com.netease.nimlib.session.a.d.a(createStandardizedParam);
        if (com.netease.nimlib.o.f.e(a) >= limit || b2.a(a2)) {
            b.b(new com.netease.nimlib.session.c(true, a)).b();
            return null;
        }
        com.netease.nimlib.session.a.c.a().a(createStandardizedParam, b);
        return null;
    }

    /* compiled from: MsgServiceRemote.java */
    /* renamed from: com.netease.nimlib.biz.f.g$3, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[y.values().length];
            a = iArr;
            try {
                iArr[y.DYNAMIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[y.REMOTE_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[y.LOCAL_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void a(GetMessagesDynamicallyParam getMessagesDynamicallyParam, com.netease.nimlib.i.k kVar, String str, SessionTypeEnum sessionTypeEnum, int i, IMMessage iMMessage) {
        if (iMMessage == null) {
            com.netease.nimlib.log.b.c("MsgServiceRemote", "on getLastMessage calllback, lastMessage: null");
            com.netease.nimlib.session.a.c.a().a(getMessagesDynamicallyParam, kVar);
            return null;
        }
        com.netease.nimlib.log.b.c("MsgServiceRemote", String.format("on getLastMessage calllback, lastMessage: (uuid: %s, serverId: %s, time: %s, content: %s)", iMMessage.getUuid(), Long.valueOf(iMMessage.getServerId()), Long.valueOf(iMMessage.getTime()), iMMessage.getContent()));
        ArrayList<IMMessage> a = com.netease.nimlib.session.a.c.a().a(getMessagesDynamicallyParam, com.netease.nimlib.session.a.c.a().c(str, sessionTypeEnum));
        if (com.netease.nimlib.o.f.e(a) >= i) {
            kVar.b(new com.netease.nimlib.session.c(true, a)).b();
            return null;
        }
        com.netease.nimlib.session.a.c.a().a(getMessagesDynamicallyParam, kVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> searchMessageHistory(String str, List<String> list, IMMessage iMMessage, int i) {
        return a(MsgDBHelper.searchMessageHistory(str, list, iMMessage, QueryDirectionEnum.QUERY_OLD, i));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> searchMessageHistory(String str, List<String> list, IMMessage iMMessage, QueryDirectionEnum queryDirectionEnum, int i) {
        return a(MsgDBHelper.searchMessageHistory(str, list, iMMessage, queryDirectionEnum, i));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> searchAllMessageHistory(String str, List<String> list, long j, int i) {
        return a(MsgDBHelper.searchAllMessageHistory(str, list, j, i));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> searchMessage(SessionTypeEnum sessionTypeEnum, String str, MsgSearchOption msgSearchOption) {
        if (msgSearchOption == null) {
            msgSearchOption = new MsgSearchOption();
        }
        return a(MsgDBHelper.searchMessage(sessionTypeEnum, str, msgSearchOption));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> searchAllMessage(MsgSearchOption msgSearchOption) {
        if (msgSearchOption == null) {
            msgSearchOption = new MsgSearchOption();
        }
        return a(MsgDBHelper.searchAllMessage(msgSearchOption));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<MsgIndexRecord>> searchAllSession(String str, int i) {
        b().b(searchAllSessionBlock(str, i)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<MsgIndexRecord> searchAllSessionBlock(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return com.netease.nimlib.search.a.a.a(com.netease.nimlib.search.a.a(str, i), str);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<MsgIndexRecord>> searchSession(String str, SessionTypeEnum sessionTypeEnum, String str2) {
        b().b(searchSessionBlock(str, sessionTypeEnum, str2)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<MsgIndexRecord> searchSessionBlock(String str, SessionTypeEnum sessionTypeEnum, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return com.netease.nimlib.search.a.a.a(com.netease.nimlib.search.a.a(sessionTypeEnum, str2, str, -1), str);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture queryRecentContacts() {
        com.netease.nimlib.i.k b = b();
        List<RecentContact> queryRecentContacts = MsgDBHelper.queryRecentContacts();
        c(queryRecentContacts);
        b.b(queryRecentContacts).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<RecentContact> queryRecentContactsBlock() {
        List<RecentContact> queryRecentContacts = MsgDBHelper.queryRecentContacts();
        c(queryRecentContacts);
        return queryRecentContacts;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<RecentContact>> queryRecentContacts(int i) {
        com.netease.nimlib.i.k b = b();
        List<RecentContact> queryRecentContacts = MsgDBHelper.queryRecentContacts(a(i));
        c(queryRecentContacts);
        b.b(queryRecentContacts).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<RecentContact>> queryRecentContacts(RecentContact recentContact, QueryDirectionEnum queryDirectionEnum, int i) {
        com.netease.nimlib.i.k b = b();
        List<RecentContact> queryRecentContacts = MsgDBHelper.queryRecentContacts(recentContact, queryDirectionEnum, a(i));
        c(queryRecentContacts);
        b.b(queryRecentContacts).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<RecentContact> queryRecentContactsBlock(int i) {
        List<RecentContact> queryRecentContacts = MsgDBHelper.queryRecentContacts(a(i));
        c(queryRecentContacts);
        return queryRecentContacts;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<RecentContact> queryRecentContactsBlock(RecentContact recentContact, QueryDirectionEnum queryDirectionEnum, int i) {
        List<RecentContact> queryRecentContacts = MsgDBHelper.queryRecentContacts(recentContact, queryDirectionEnum, a(i));
        c(queryRecentContacts);
        return queryRecentContacts;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<RecentContact>> queryRecentContacts(MsgTypeEnum msgTypeEnum) {
        b().b(a(com.netease.nimlib.o.f.b(msgTypeEnum))).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<RecentContact> queryRecentContactsBlock(MsgTypeEnum msgTypeEnum) {
        return a(com.netease.nimlib.o.f.b(msgTypeEnum));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<RecentContact>> queryRecentContacts(Set<MsgTypeEnum> set) {
        b().b(a(set)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<RecentContact> queryRecentContactsBlock(Set<MsgTypeEnum> set) {
        return a(set);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void updateRecent(RecentContact recentContact) {
        MsgDBHelper.updateRecent(recentContact);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void updateRecentAndNotify(RecentContact recentContact) {
        MsgDBHelper.updateRecent(recentContact);
        com.netease.nimlib.i.b.a((q) recentContact);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void updateRecentByMessage(IMMessage iMMessage, boolean z) {
        q f = com.netease.nimlib.session.j.f((IMMessageImpl) iMMessage);
        if (z) {
            com.netease.nimlib.i.b.a(f);
        }
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void updateRoamMsgHasMoreTag(IMMessage iMMessage) {
        if (iMMessage == null) {
            com.netease.nimlib.log.b.d("MsgServiceRemote", "updateRoamMsgHasMoreTag error, tag is null");
            return;
        }
        RoamMsgHasMoreOption roamMsgHasMoreOption = new RoamMsgHasMoreOption(iMMessage.getSessionId(), iMMessage.getSessionType(), iMMessage.getTime(), iMMessage.getServerId());
        com.netease.nimlib.log.b.d("MsgServiceRemote", "updateRoamMsgHasMoreTag, option is " + roamMsgHasMoreOption);
        MsgDBHelper.updateRoamMsgHasMoreTime(roamMsgHasMoreOption);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void deleteChattingHistory(IMMessage iMMessage) {
        deleteChattingHistory(iMMessage, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void deleteChattingHistory(IMMessage iMMessage, boolean z) {
        if (MsgDBHelper.deleteMessage((IMMessageImpl) iMMessage, z ^ true) > 0 && com.netease.nimlib.session.j.a(iMMessage, true)) {
            q queryRecentContact = MsgDBHelper.queryRecentContact(iMMessage.getSessionId(), iMMessage.getSessionType());
            if (queryRecentContact == null) {
                return;
            }
            queryRecentContact.a(Math.max(0, queryRecentContact.getUnreadCount() - 1));
            MsgDBHelper.saveRecent(queryRecentContact);
        }
        com.netease.nimlib.session.j.b(iMMessage);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void deleteChattingHistory(List<IMMessage> list, boolean z) {
        com.netease.nimlib.session.j.b(list, z);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void clearChattingHistory(String str, SessionTypeEnum sessionTypeEnum) {
        clearChattingHistory(str, sessionTypeEnum, true);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void clearChattingHistory(String str, SessionTypeEnum sessionTypeEnum, boolean z) {
        com.netease.nimlib.session.a.c.a().b(str, sessionTypeEnum);
        MsgDBHelper.clearMessage(str, sessionTypeEnum, !z);
        q queryRecentContact = MsgDBHelper.queryRecentContact(str, sessionTypeEnum);
        if (queryRecentContact != null) {
            com.netease.nimlib.i.b.a(com.netease.nimlib.session.j.a(str, sessionTypeEnum, queryRecentContact));
        }
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> clearMsgDatabase(boolean z) {
        com.netease.nimlib.session.a.c.a().d();
        if (z) {
            c();
        }
        MsgDBHelper.clearAllMessages(z);
        if (z) {
            com.netease.nimlib.i.b.b((q) null);
        }
        b().a(200).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Long> deleteMsgSelf(final IMMessage iMMessage, String str) {
        com.netease.nimlib.biz.d.i.f fVar = new com.netease.nimlib.biz.d.i.f(iMMessage, str);
        fVar.a(b());
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(fVar, com.netease.nimlib.biz.g.a.b) { // from class: com.netease.nimlib.biz.f.g.5
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    g.this.deleteChattingHistory(iMMessage);
                }
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Long> deleteMsgSelf(final List<IMMessage> list, String str) {
        com.netease.nimlib.biz.d.i.e eVar = new com.netease.nimlib.biz.d.i.e(list, str);
        eVar.a(b());
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(eVar, com.netease.nimlib.biz.g.a.b) { // from class: com.netease.nimlib.biz.f.g.6
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    com.netease.nimlib.session.j.b((List<IMMessage>) list, true);
                }
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public boolean everBeenDeleted(IMMessage iMMessage) {
        long time = iMMessage.getTime();
        if (time > 0 && com.netease.nimlib.biz.l.E() > time) {
            return true;
        }
        SessionTypeEnum sessionType = iMMessage.getSessionType();
        String sessionId = iMMessage.getSessionId();
        if (time > 0 && sessionType != null && !TextUtils.isEmpty(sessionId) && MsgDBHelper.isRemovedWhileClearingSession(sessionId, sessionType, time)) {
            com.netease.nimlib.log.b.c("MsgServiceRemote", "deleted by session, sessionId=" + sessionId + ", content=" + iMMessage.getContent());
            return true;
        }
        String uuid = iMMessage.getUuid();
        if (TextUtils.isEmpty(uuid) || !MsgDBHelper.hasDeleteTag(iMMessage.getUuid())) {
            return false;
        }
        com.netease.nimlib.log.b.c("MsgServiceRemote", "deleted by id, uuid=" + uuid + ", content=" + iMMessage.getContent());
        return true;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void deleteRecentContact(RecentContact recentContact) {
        deleteRecentContact(recentContact.getContactId(), recentContact.getSessionType(), DeleteTypeEnum.LOCAL_AND_REMOTE, true);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void deleteRecentContact2(String str, SessionTypeEnum sessionTypeEnum) {
        if (a(str, sessionTypeEnum)) {
            throw new IllegalArgumentException("Invalid param");
        }
        deleteRecentContact(str, sessionTypeEnum, DeleteTypeEnum.LOCAL_AND_REMOTE, true);
        q qVar = new q();
        qVar.a(str);
        qVar.a(sessionTypeEnum);
        com.netease.nimlib.i.b.b(qVar);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> deleteRoamingRecentContact(String str, SessionTypeEnum sessionTypeEnum) {
        if (a(str, sessionTypeEnum)) {
            throw new IllegalArgumentException("Invalid param");
        }
        if (sessionTypeEnum == SessionTypeEnum.SUPER_TEAM) {
            return null;
        }
        com.netease.nimlib.biz.d.i.h hVar = new com.netease.nimlib.biz.d.i.h();
        hVar.a(b());
        hVar.a(str, sessionTypeEnum);
        com.netease.nimlib.biz.i.a().a(hVar, com.netease.nimlib.biz.g.a.b);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> deleteRecentContact(String str, SessionTypeEnum sessionTypeEnum, DeleteTypeEnum deleteTypeEnum, boolean z) {
        if (z) {
            a(str, sessionTypeEnum, false, true, (com.netease.nimlib.i.k) null);
            com.netease.nimlib.l.a.a(com.netease.nimlib.l.h.MESSAGE);
        }
        if (DeleteTypeEnum.deleteLocal(deleteTypeEnum)) {
            MsgDBHelper.deleteRecentContact(str, sessionTypeEnum);
        }
        com.netease.nimlib.i.k b = b();
        if (!DeleteTypeEnum.deleteRemote(deleteTypeEnum)) {
            b.b((Object) null).b();
            return null;
        }
        if (!b(str, sessionTypeEnum)) {
            b.a(414).b();
            return null;
        }
        com.netease.nimlib.biz.d.i.h hVar = new com.netease.nimlib.biz.d.i.h();
        hVar.a(b);
        hVar.a(str, sessionTypeEnum);
        com.netease.nimlib.biz.i.a().a(hVar, com.netease.nimlib.biz.g.a.b);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void deleteRoamMsgHasMoreTag(String str, SessionTypeEnum sessionTypeEnum) {
        com.netease.nimlib.log.b.d("MsgServiceRemote", String.format("deleteRoamMsgHasMoreTag, sessionId=%s, sessionType=%s", str, sessionTypeEnum));
        MsgDBHelper.deleteRoamMsgHasMoreTime(str, sessionTypeEnum);
        b().b();
    }

    private boolean a(String str, SessionTypeEnum sessionTypeEnum) {
        return !TextUtils.isEmpty(str) && (sessionTypeEnum == SessionTypeEnum.Team || sessionTypeEnum == SessionTypeEnum.SUPER_TEAM) && !str.matches("[0-9]+");
    }

    private boolean b(String str, SessionTypeEnum sessionTypeEnum) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (sessionTypeEnum != SessionTypeEnum.P2P && sessionTypeEnum != SessionTypeEnum.Team) {
            return false;
        }
        if (sessionTypeEnum == SessionTypeEnum.P2P) {
            return true;
        }
        return str.matches("[0-9]+");
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public AbortableFuture<String> transVoiceToText(String str, String str2, long j) {
        return transVoiceToTextEnableForce(str, str2, j, NimNosSceneKeyConstant.NIM_DEFAULT_IM, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public AbortableFuture<String> transVoiceToTextAtScene(String str, String str2, long j, String str3) {
        return transVoiceToTextEnableForce(str, str2, j, str3, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public AbortableFuture<String> transVoiceToTextEnableForce(String str, String str2, final long j, String str3, boolean z) {
        if (str2 == null || !new File(str2).exists()) {
            throw new IllegalArgumentException("Invalid audio path.");
        }
        int b = com.netease.share.media.b.a(str2) ? com.netease.share.media.b.b(str2) : 16000;
        final com.netease.nimlib.i.k b2 = b();
        if (TextUtils.isEmpty(str)) {
            final int i = b;
            com.netease.nimlib.net.a.b.a.a().a(str2, com.netease.nimlib.o.m.b(str2), b2, str3, z, new com.netease.nimlib.net.a.b.c() { // from class: com.netease.nimlib.biz.f.g.7
                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj, long j2, long j3) {
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj, String str4) {
                    g.this.a(str4, j, i, b2);
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj, int i2, String str4) {
                    b2.a(i2).b();
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj) {
                    a(obj, 400, (String) null);
                }
            });
            return null;
        }
        a(str, j, b, b2);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, long j, int i, com.netease.nimlib.i.k kVar) {
        com.netease.nimlib.biz.d.d.q qVar = new com.netease.nimlib.biz.d.d.q();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(0, "AAC");
        cVar.a(1, String.valueOf(i));
        cVar.a(2, str);
        cVar.a(3, j);
        qVar.a(cVar);
        qVar.a(kVar);
        com.netease.nimlib.biz.i.a().a(qVar);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void setChattingAccount(String str, SessionTypeEnum sessionTypeEnum) {
        if ("all".equals(str)) {
            com.netease.nimlib.h.a(str);
            com.netease.nimlib.l.a.a(com.netease.nimlib.l.h.c);
            return;
        }
        if (str == null) {
            str = "";
        }
        com.netease.nimlib.h.a(com.netease.nimlib.session.j.a(str, sessionTypeEnum.getValue()));
        if (w.b((CharSequence) str)) {
            clearUnreadCount(str, sessionTypeEnum);
        }
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public int getTotalUnreadCount() {
        return MsgDBHelper.queryUnreadMsgCount();
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public int getTotalUnreadCount(boolean z) {
        int i = 0;
        for (RecentContact recentContact : queryRecentContactsBlock()) {
            if (z == a(recentContact)) {
                i += recentContact.getUnreadCount();
            }
        }
        return i;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public int getUnreadCountBySessionType(SessionTypeEnum sessionTypeEnum) {
        int i = 0;
        if (sessionTypeEnum == null) {
            com.netease.nimlib.log.b.e("MsgServiceRemote", "get unread count by session type with null");
            return 0;
        }
        List<RecentContact> queryUnreadRecentContactBySessionType = MsgDBHelper.queryUnreadRecentContactBySessionType(sessionTypeEnum);
        if (com.netease.nimlib.o.f.c((Collection) queryUnreadRecentContactBySessionType)) {
            return 0;
        }
        Iterator<RecentContact> it = queryUnreadRecentContactBySessionType.iterator();
        while (it.hasNext()) {
            i += it.next().getUnreadCount();
        }
        return i;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<IMMessage>> queryUnreadMessageList(String str, SessionTypeEnum sessionTypeEnum) {
        return a(queryUnreadMessageListBlock(str, sessionTypeEnum));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<IMMessage> queryUnreadMessageListBlock(String str, SessionTypeEnum sessionTypeEnum) {
        if (MsgDBHelper.queryRecentContact(str, sessionTypeEnum) == null) {
            return new ArrayList();
        }
        long querySessionReadTimeTag = MsgDBHelper.querySessionReadTimeTag(str, sessionTypeEnum);
        ArrayList<IMMessage> queryUnreadMessages = MsgDBHelper.queryUnreadMessages(str, sessionTypeEnum, querySessionReadTimeTag);
        if (queryUnreadMessages.isEmpty()) {
            return queryUnreadMessages;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<IMMessage> it = queryUnreadMessages.iterator();
        while (it.hasNext()) {
            IMMessage next = it.next();
            if (com.netease.nimlib.session.j.a(next, false, querySessionReadTimeTag)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private boolean a(RecentContact recentContact) {
        if (recentContact.getSessionType() == SessionTypeEnum.P2P) {
            return com.netease.nimlib.user.c.c(recentContact.getContactId());
        }
        if (recentContact.getSessionType() == SessionTypeEnum.Team) {
            return !com.netease.nimlib.team.b.a(TeamDBHelper.getMemberBits(recentContact.getContactId()));
        }
        if (recentContact.getSessionType() == SessionTypeEnum.SUPER_TEAM) {
            return !com.netease.nimlib.team.b.a(SuperTeamDBHelper.getMemberBits(recentContact.getContactId()));
        }
        return true;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> clearUnreadCount(String str, SessionTypeEnum sessionTypeEnum) {
        com.netease.nimlib.i.k b = b();
        if (TextUtils.isEmpty(str)) {
            b.a(414).b();
            return null;
        }
        a(str, sessionTypeEnum, true, true, b);
        com.netease.nimlib.l.a.a(com.netease.nimlib.l.h.MESSAGE);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<SessionAckInfo>> clearUnreadCount(List<Pair<String, SessionTypeEnum>> list) {
        com.netease.nimlib.i.k b = b();
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            b.b(new ArrayList(0)).b();
            return null;
        }
        a(list, b);
        com.netease.nimlib.l.a.a(com.netease.nimlib.l.h.MESSAGE);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<SessionAckInfo>> clearUnreadCount(SessionTypeEnum sessionTypeEnum) {
        com.netease.nimlib.i.k b = b();
        if (sessionTypeEnum == null) {
            b.a(414).b();
            return null;
        }
        ArrayList b2 = com.netease.nimlib.o.f.b(MsgDBHelper.queryUnreadRecentContactBySessionType(sessionTypeEnum), true, new f.a() { // from class: com.netease.nimlib.biz.f.-$$Lambda$g$XyFlANKPzKCRSWYdmDFKqNWsKN8
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Pair b3;
                b3 = g.b((RecentContact) obj);
                return b3;
            }
        });
        if (com.netease.nimlib.o.f.c((Collection) b2)) {
            b.b(new ArrayList(0)).b();
            return null;
        }
        a(b2, b);
        com.netease.nimlib.l.a.a(com.netease.nimlib.l.h.MESSAGE);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Pair b(RecentContact recentContact) {
        if (recentContact == null) {
            return null;
        }
        return new Pair(recentContact.getContactId(), recentContact.getSessionType());
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> sendMessageReceipt(String str, IMMessage iMMessage) {
        com.netease.nimlib.i.k b = b();
        if (iMMessage == null || iMMessage.getSessionType() != SessionTypeEnum.P2P || iMMessage.getDirect() != MsgDirectionEnum.In) {
            b.a((Throwable) new IllegalArgumentException("input message is illegal")).b();
            return null;
        }
        boolean z = false;
        long time = iMMessage.getTime();
        String uuid = iMMessage.getUuid();
        if (time > 0 && com.netease.nimlib.session.e.b().a(new MessageReceipt(str, time))) {
            z = true;
        }
        if (z) {
            com.netease.nimlib.biz.d.i.q qVar = new com.netease.nimlib.biz.d.i.q(str, uuid, time);
            qVar.a(b);
            com.netease.nimlib.biz.i.a().a(qVar, com.netease.nimlib.biz.g.a.b);
        } else {
            b.b((Object) null).b();
        }
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void updateIMMessageStatus(IMMessage iMMessage) {
        MsgDBHelper.updateMessageStatus((IMMessageImpl) iMMessage);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void updateIMMessage(IMMessage iMMessage) {
        MsgDBHelper.updateMessageLocalExt((IMMessageImpl) iMMessage);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void registerCustomAttachmentParser(MsgAttachmentParser msgAttachmentParser) {
        com.netease.nimlib.session.i.a().a(MsgTypeEnum.custom.getValue(), msgAttachmentParser);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> sendCustomNotification(CustomNotification customNotification) {
        int i;
        if (TextUtils.isEmpty(customNotification.getSessionId()) || customNotification.getSessionType() == null) {
            throw new IllegalArgumentException("illegal receiver");
        }
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(2, customNotification.getSessionId());
        if (customNotification.getSessionType() == SessionTypeEnum.P2P) {
            i = 100;
        } else if (customNotification.getSessionType() == SessionTypeEnum.Team) {
            i = 101;
        } else if (customNotification.getSessionType() == SessionTypeEnum.SUPER_TEAM) {
            i = 103;
        } else {
            i = customNotification.getSessionType() == SessionTypeEnum.Ysf ? 102 : 0;
        }
        if (i != 0) {
            cVar.a(1, i);
        }
        cVar.a(5, customNotification.getContent());
        if (!customNotification.isSendToOnlineUserOnly()) {
            cVar.a(7, 1);
        }
        if (!TextUtils.isEmpty(customNotification.getApnsText())) {
            cVar.a(8, customNotification.getApnsText());
        }
        String a = com.netease.nimlib.session.j.a(customNotification.getPushPayload());
        if (!TextUtils.isEmpty(a)) {
            cVar.a(9, a);
        }
        if (customNotification.getConfig() != null) {
            if (!customNotification.getConfig().enablePush) {
                cVar.a(107, 0);
            }
            if (customNotification.getConfig().enablePushNick) {
                cVar.a(110, 1);
            }
            if (!customNotification.getConfig().enableUnreadCount) {
                cVar.a(109, 0);
            }
        }
        if (customNotification.getNIMAntiSpamOption() != null) {
            cVar.a(12, customNotification.getNIMAntiSpamOption().enable ? 1 : 0);
            cVar.a(13, customNotification.getNIMAntiSpamOption().content);
        }
        cVar.a(21, customNotification.getEnv());
        com.netease.nimlib.biz.d.i.d dVar = new com.netease.nimlib.biz.d.i.d(i);
        dVar.a(cVar);
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(dVar, com.netease.nimlib.biz.g.a.a) { // from class: com.netease.nimlib.biz.f.g.8
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                b.a(aVar.r()).b();
            }
        });
        return null;
    }

    private void a(List<Pair<String, SessionTypeEnum>> list, com.netease.nimlib.i.k kVar) {
        SessionTypeEnum sessionTypeEnum;
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        for (Pair<String, SessionTypeEnum> pair : list) {
            if (pair != null) {
                String str = (String) pair.first;
                if (!TextUtils.isEmpty(str) && (sessionTypeEnum = (SessionTypeEnum) pair.second) != null) {
                    q a = a(str, sessionTypeEnum, false, false, (com.netease.nimlib.i.k) null);
                    if (a != null) {
                        arrayList.add(a);
                    }
                    if (com.netease.nimlib.c.i().sessionReadAck) {
                        long a2 = v.a(str, sessionTypeEnum);
                        if (a2 > 0 && v.c(str, sessionTypeEnum, a2)) {
                            arrayList2.add(pair);
                        }
                    }
                }
            }
        }
        a(arrayList2, kVar, 0, new ArrayList<>());
        if (com.netease.nimlib.o.f.d(arrayList)) {
            com.netease.nimlib.i.b.e(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<Pair<String, SessionTypeEnum>> list, com.netease.nimlib.i.k kVar, int i, ArrayList<SessionAckInfo> arrayList) {
        int min = Math.min(i + 50, list.size());
        if (min <= i) {
            kVar.b(arrayList).b();
        } else {
            com.netease.nimlib.biz.d.i.a aVar = new com.netease.nimlib.biz.d.i.a(list.subList(i, min));
            com.netease.nimlib.biz.i.a().a(new AnonymousClass9(aVar, new com.netease.nimlib.biz.g.a(1, 10), aVar, arrayList, list, kVar, min));
        }
    }

    /* compiled from: MsgServiceRemote.java */
    /* renamed from: com.netease.nimlib.biz.f.g$9, reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass9 extends com.netease.nimlib.biz.g.b {
        final /* synthetic */ com.netease.nimlib.biz.d.i.a a;
        final /* synthetic */ ArrayList b;
        final /* synthetic */ List c;
        final /* synthetic */ com.netease.nimlib.i.k d;
        final /* synthetic */ int e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass9(com.netease.nimlib.biz.d.a aVar, com.netease.nimlib.biz.g.a aVar2, com.netease.nimlib.biz.d.i.a aVar3, ArrayList arrayList, List list, com.netease.nimlib.i.k kVar, int i) {
            super(aVar, aVar2);
            this.a = aVar3;
            this.b = arrayList;
            this.c = list;
            this.d = kVar;
            this.e = i;
        }

        @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
        public void a(com.netease.nimlib.biz.e.a aVar) {
            if (aVar instanceof com.netease.nimlib.biz.e.j.a) {
                List<com.netease.nimlib.push.packet.b.c> d = this.a.d();
                com.netease.nimlib.biz.e.j.a aVar2 = (com.netease.nimlib.biz.e.j.a) aVar;
                short r = aVar.r();
                final ArrayList c = com.netease.nimlib.o.f.c((r == 200 || r == 700) ? aVar2.a() : d, new f.a() { // from class: com.netease.nimlib.biz.f.-$$Lambda$Y2qVhRpOGTs_WcTXMWEEhymPYBI
                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        return com.netease.nimlib.session.w.a((com.netease.nimlib.push.packet.b.c) obj);
                    }
                });
                this.b.addAll(c);
                com.netease.nimlib.o.f.f(com.netease.nimlib.o.f.d(d, new f.a() { // from class: com.netease.nimlib.biz.f.-$$Lambda$g$9$F32ZEic6rkJ6AgiTSa_z_g59Dxs
                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        Boolean a;
                        a = g.AnonymousClass9.a(c, (com.netease.nimlib.push.packet.b.c) obj);
                        return a;
                    }
                }), new f.a() { // from class: com.netease.nimlib.biz.f.-$$Lambda$g$9$Zns1YnN4UK7vz7vVmVqr0uOS_8U
                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        Boolean a;
                        a = g.AnonymousClass9.a((com.netease.nimlib.push.packet.b.c) obj);
                        return a;
                    }
                });
            }
            g.this.a((List<Pair<String, SessionTypeEnum>>) this.c, this.d, this.e, (ArrayList<SessionAckInfo>) this.b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Boolean a(ArrayList arrayList, com.netease.nimlib.push.packet.b.c cVar) {
            if (cVar == null) {
                return null;
            }
            com.netease.nimlib.session.w a = com.netease.nimlib.session.w.a(cVar);
            final String sessionId = a.getSessionId();
            final SessionTypeEnum sessionType = a.getSessionType();
            return Boolean.valueOf(!com.netease.nimlib.o.f.b(arrayList, new f.a() { // from class: com.netease.nimlib.biz.f.-$$Lambda$g$9$V_41h9d7c3wcqF6LdUjI2An3F7o
                @Override // com.netease.nimlib.o.f.a
                public final Object transform(Object obj) {
                    Boolean a2;
                    a2 = g.AnonymousClass9.a(sessionId, sessionType, (SessionAckInfo) obj);
                    return a2;
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Boolean a(String str, SessionTypeEnum sessionTypeEnum, SessionAckInfo sessionAckInfo) {
            return Boolean.valueOf(sessionAckInfo != null && str.equals(sessionAckInfo.getSessionId()) && sessionTypeEnum == sessionAckInfo.getSessionType());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Boolean a(com.netease.nimlib.push.packet.b.c cVar) {
            if (cVar != null) {
                com.netease.nimlib.session.w a = com.netease.nimlib.session.w.a(cVar);
                v.b(a.getSessionId(), a.getSessionType(), a.getTime());
                return true;
            }
            return true;
        }
    }

    private q a(String str, SessionTypeEnum sessionTypeEnum, boolean z, boolean z2, com.netease.nimlib.i.k kVar) {
        q qVar = null;
        if (!w.a((CharSequence) str) && sessionTypeEnum != null) {
            long a = com.netease.nimlib.c.i().sessionReadAck ? v.a(str, sessionTypeEnum) : -1L;
            q queryRecentContact = MsgDBHelper.queryRecentContact(str, sessionTypeEnum);
            if (queryRecentContact != null && queryRecentContact.getUnreadCount() > 0) {
                MsgDBHelper.setRecentRead(str, sessionTypeEnum.getValue());
                queryRecentContact.a(0);
                com.netease.nimlib.session.j.a(queryRecentContact);
                if (z) {
                    com.netease.nimlib.i.b.a(queryRecentContact);
                }
                qVar = queryRecentContact;
            }
            if (z2) {
                v.a(str, sessionTypeEnum, a, kVar);
            }
        }
        return qVar;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void clearAllUnreadCount() {
        List<RecentContact> queryAllUnreadRecentContact = MsgDBHelper.queryAllUnreadRecentContact();
        if (queryAllUnreadRecentContact == null || queryAllUnreadRecentContact.isEmpty()) {
            return;
        }
        ArrayList<q> arrayList = new ArrayList();
        Iterator<RecentContact> it = queryAllUnreadRecentContact.iterator();
        while (it.hasNext()) {
            arrayList.add((q) it.next());
        }
        for (q qVar : arrayList) {
            long j = -1;
            String contactId = qVar.getContactId();
            SessionTypeEnum sessionType = qVar.getSessionType();
            if (com.netease.nimlib.c.i().sessionReadAck) {
                j = v.a(contactId, sessionType);
            }
            MsgDBHelper.setRecentRead(contactId, sessionType.getValue());
            qVar.a(0);
            com.netease.nimlib.session.j.a(qVar);
            v.a(contactId, sessionType, j, null);
        }
        com.netease.nimlib.i.b.e(arrayList);
        com.netease.nimlib.l.a.a(com.netease.nimlib.l.h.c);
    }

    private InvocationFuture<List<IMMessage>> a(List<IMMessage> list) {
        com.netease.nimlib.i.k b = b();
        b(list);
        b.b(list).b();
        return null;
    }

    private void b(List<IMMessage> list) {
        Iterator<IMMessage> it = list.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    private void a(IMMessage iMMessage) {
        String uuid = iMMessage.getUuid();
        if (iMMessage.getStatus() == MsgStatusEnum.fail) {
            if (com.netease.nimlib.session.d.a().c(uuid)) {
                iMMessage.setStatus(MsgStatusEnum.sending);
            }
            if (com.netease.nimlib.session.d.a().h(uuid)) {
                iMMessage.setAttachStatus(AttachStatusEnum.transferring);
                return;
            }
            return;
        }
        if (com.netease.nimlib.session.d.a().f(uuid)) {
            iMMessage.setAttachStatus(AttachStatusEnum.transferring);
        }
    }

    private void c(List<RecentContact> list) {
        Iterator<RecentContact> it = list.iterator();
        while (it.hasNext()) {
            com.netease.nimlib.session.j.a((q) it.next());
        }
    }

    private void c() {
        List<RecentContact> queryRecentContacts = MsgDBHelper.queryRecentContacts();
        if (queryRecentContacts.size() == 0) {
            return;
        }
        com.netease.nimlib.biz.d.i.h hVar = new com.netease.nimlib.biz.d.i.h();
        for (RecentContact recentContact : queryRecentContacts) {
            if (recentContact.getSessionType() != SessionTypeEnum.SUPER_TEAM && recentContact.getSessionType() != SessionTypeEnum.System) {
                hVar.a(recentContact.getContactId(), recentContact.getSessionType());
            }
        }
        if (hVar.d()) {
            return;
        }
        com.netease.nimlib.biz.i.a().a(hVar, com.netease.nimlib.biz.g.a.b);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<ArrayList<IMMessage>> searchRoamingMsg(String str, long j, long j2, String str2, int i, boolean z) {
        p pVar = new p(str, j, j2, str2, i, z);
        pVar.a(b());
        com.netease.nimlib.biz.i.a().a(pVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void registerIMMessageFilter(IMMessageFilter iMMessageFilter) {
        com.netease.nimlib.session.j.a(iMMessageFilter);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void registerShouldShowNotificationWhenRevokeFilter(ShowNotificationWhenRevokeFilter showNotificationWhenRevokeFilter) {
        com.netease.nimlib.session.j.a(showNotificationWhenRevokeFilter);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> revokeMessage(IMMessage iMMessage) {
        a(iMMessage, (String) null, (Map<String, Object>) null, true, (String) null, (String) null);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> revokeMessageEx(IMMessage iMMessage, String str, Map<String, Object> map) {
        a(iMMessage, str, map, true, (String) null, (String) null);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> revokeMessage(IMMessage iMMessage, String str, Map<String, Object> map, boolean z) {
        a(iMMessage, str, map, z, (String) null, (String) null);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> revokeMessage(IMMessage iMMessage, String str, Map<String, Object> map, boolean z, String str2) {
        a(iMMessage, str, map, z, str2, (String) null);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> revokeMessage(IMMessage iMMessage, String str, Map<String, Object> map, boolean z, String str2, String str3) {
        a(iMMessage, str, map, z, str2, str3);
        return null;
    }

    private void a(IMMessage iMMessage, String str, Map<String, Object> map, boolean z, String str2, String str3) {
        String str4;
        com.netease.nimlib.biz.d.i.o oVar;
        String n = com.netease.nimlib.c.n();
        com.netease.nimlib.i.k b = b();
        IMMessageImpl iMMessageImpl = (IMMessageImpl) iMMessage;
        if (iMMessageImpl == null || iMMessageImpl.getServerId() == 0) {
            if (iMMessageImpl == null) {
                str4 = " msg == null";
            } else {
                str4 = "serverId = " + iMMessageImpl.getServerId() + " , sessionId = " + iMMessage.getSessionId() + " , self account = " + n;
            }
            com.netease.nimlib.log.b.d("MsgServiceRemote", str4);
            b.a(414).b();
            return;
        }
        String a = com.netease.nimlib.session.j.a(map);
        if (!TextUtils.equals(iMMessage.getFromAccount(), n)) {
            if (b(iMMessage)) {
                oVar = new com.netease.nimlib.biz.d.i.o(iMMessageImpl, n, str, a, z, str2, str3);
            } else {
                com.netease.nimlib.log.b.d("MsgServiceRemote", "from account = " + iMMessageImpl.getFromAccount() + " , self account = " + n + ", session type = " + iMMessage.getSessionType());
                b.a(414).b();
                return;
            }
        } else {
            oVar = new com.netease.nimlib.biz.d.i.o(iMMessageImpl, null, str, a, z, str2, str3);
        }
        oVar.a(b);
        com.netease.nimlib.biz.i.a().a(oVar);
    }

    private boolean b(IMMessage iMMessage) {
        if (iMMessage.getSessionType() != SessionTypeEnum.Team) {
            return false;
        }
        TeamMemberType memberType = TeamDBHelper.getMemberType(iMMessage.getSessionId(), com.netease.nimlib.c.n());
        return memberType == TeamMemberType.Manager || memberType == TeamMemberType.Owner;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> importRecentSessions(List<Pair<String, SessionTypeEnum>> list) {
        com.netease.nimlib.i.k b = b();
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (Pair<String, SessionTypeEnum> pair : list) {
                if (pair != null && !TextUtils.isEmpty((CharSequence) pair.first)) {
                    q qVar = new q();
                    qVar.a((String) pair.first);
                    qVar.a((SessionTypeEnum) pair.second);
                    arrayList.add(qVar);
                }
            }
            MsgDBHelper.importRecentContactByUnionKey(arrayList);
        }
        b.b((Object) null).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public LocalAntiSpamResult checkLocalAntiSpam(String str, String str2) {
        return com.netease.nimlib.b.c.a(str, str2);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public RecentContact createEmptyRecentContact(String str, SessionTypeEnum sessionTypeEnum, long j, long j2, boolean z) {
        return a(str, sessionTypeEnum, j, j2, z, false);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public RecentContact createEmptyRecentContact(String str, SessionTypeEnum sessionTypeEnum, long j, long j2, boolean z, boolean z2) {
        return a(str, sessionTypeEnum, j, j2, z, z2);
    }

    private RecentContact a(String str, SessionTypeEnum sessionTypeEnum, long j, long j2, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str) || sessionTypeEnum == null || j2 <= 0) {
            return null;
        }
        q qVar = new q();
        qVar.a(str);
        qVar.a(sessionTypeEnum);
        qVar.setTag(j);
        qVar.a(j2);
        qVar.setMsgStatus(MsgStatusEnum.success);
        if (z2) {
            qVar.setLastMsg(MsgDBHelper.queryLatestMessage(str, sessionTypeEnum.getValue()));
        }
        if (z && MsgDBHelper.queryRecentContact(str, sessionTypeEnum) == null) {
            MsgDBHelper.saveRecent(qVar);
            com.netease.nimlib.i.b.a(qVar);
        }
        return qVar;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public RecentContact queryRecentContact(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelper.queryRecentContact(str, sessionTypeEnum);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> cancelUploadAttachment(IMMessage iMMessage) {
        com.netease.nimlib.i.k b = b();
        a.c g = com.netease.nimlib.session.d.a().g(iMMessage.getUuid());
        if (g == null) {
            b.a(-1).b();
            return null;
        }
        com.netease.nimlib.net.a.b.a.a().a(g);
        b.a(200).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public AbortableFuture<Void> exportAllMessage(IMsgExportProcessor iMsgExportProcessor, boolean z) {
        if (iMsgExportProcessor == null) {
            throw new IllegalArgumentException("exportProcessor must not null");
        }
        com.netease.nimlib.i.k b = b();
        com.netease.nimlib.j.b.a().a(iMsgExportProcessor, b, null, null, z);
        return new com.netease.nimlib.i.h<com.netease.nimlib.i.k>(b) { // from class: com.netease.nimlib.biz.f.g.10
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                com.netease.nimlib.j.b.a().a((com.netease.nimlib.i.k) this.c);
                return false;
            }
        };
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public AbortableFuture<Void> importAllMessage(IMsgImportProcessor iMsgImportProcessor, boolean z) {
        if (iMsgImportProcessor == null) {
            throw new IllegalArgumentException("importProcessor must not null");
        }
        com.netease.nimlib.i.k b = b();
        com.netease.nimlib.j.b.a().a(b, iMsgImportProcessor, z);
        return new com.netease.nimlib.i.h<com.netease.nimlib.i.k>(b) { // from class: com.netease.nimlib.biz.f.g.11
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                com.netease.nimlib.j.b.a().a((com.netease.nimlib.i.k) this.c);
                return false;
            }
        };
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void clearServerHistory(String str, boolean z) {
        a(str, SessionTypeEnum.P2P, z, false, (String) null);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void clearServerHistory(String str, SessionTypeEnum sessionTypeEnum) {
        clearServerHistory(str, sessionTypeEnum, false, null);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void clearServerHistory(String str, SessionTypeEnum sessionTypeEnum, boolean z) {
        a(str, sessionTypeEnum, z, false, (String) null);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void clearServerHistory(String str, SessionTypeEnum sessionTypeEnum, boolean z, String str2) {
        a(str, sessionTypeEnum, true, z, str2);
    }

    private void a(String str, SessionTypeEnum sessionTypeEnum, boolean z, boolean z2, String str2) {
        com.netease.nimlib.session.a.c.a().b(str, sessionTypeEnum);
        MsgDBHelper.clearMessage(str, sessionTypeEnum, false);
        q queryRecentContact = MsgDBHelper.queryRecentContact(str, sessionTypeEnum);
        if (queryRecentContact != null) {
            com.netease.nimlib.i.b.a(com.netease.nimlib.session.j.a(str, sessionTypeEnum, queryRecentContact));
        }
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.d.i.c(str, sessionTypeEnum, z, z2, str2));
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void deleteRangeHistory(String str, SessionTypeEnum sessionTypeEnum, long j, long j2) {
        long j3;
        long j4;
        if (j == j2) {
            throw new IllegalArgumentException("time set error: startTime equals endTime");
        }
        if (j > j2) {
            j4 = j;
            j3 = j2;
        } else {
            j3 = j;
            j4 = j2;
        }
        MsgDBHelper.deleteRangeHistory(str, sessionTypeEnum, j3, j4);
        com.netease.nimlib.session.j.a(str, sessionTypeEnum);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<RecentSessionList> queryMySessionList(long j, Long l, Integer num, Integer num2, Integer num3) {
        com.netease.nimlib.biz.d.i.i iVar = new com.netease.nimlib.biz.d.i.i(j, l, num, num2);
        iVar.a(b());
        com.netease.nimlib.biz.i.a().a(iVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<RecentSessionList> queryMySessionList(QueryMySessionOption queryMySessionOption) {
        if (queryMySessionOption == null) {
            queryMySessionOption = new QueryMySessionOption();
        }
        com.netease.nimlib.biz.d.i.i iVar = new com.netease.nimlib.biz.d.i.i(queryMySessionOption.getMinTimestamp(), Long.valueOf(queryMySessionOption.getMaxTimestamp()), Integer.valueOf(queryMySessionOption.isNeedLastMsg() ? 1 : 0), Integer.valueOf(queryMySessionOption.getLimit()));
        iVar.a(b());
        com.netease.nimlib.biz.i.a().a(iVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<RecentSession> queryMySession(String str) {
        com.netease.nimlib.biz.d.i.j jVar = new com.netease.nimlib.biz.d.i.j(str);
        jVar.a(b());
        com.netease.nimlib.biz.i.a().a(jVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> updateMySession(String str, String str2) {
        com.netease.nimlib.biz.d.b bVar = new com.netease.nimlib.biz.d.b(str, str2);
        bVar.a(b());
        com.netease.nimlib.biz.i.a().a(bVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> deleteMySession(String[] strArr) {
        com.netease.nimlib.biz.d.i.g gVar = new com.netease.nimlib.biz.d.i.g(strArr);
        gVar.a(b());
        com.netease.nimlib.biz.i.a().a(gVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public void migrateMessages(String str, String str2, boolean z) {
        if (TextUtils.equals(str, str2)) {
            return;
        }
        MsgDBHelper.migrateMessages(com.netease.nimlib.c.e(), str, str2, z);
    }

    private List<RecentContact> a(Set<MsgTypeEnum> set) {
        List<RecentContact> queryRecentContactsBlock = queryRecentContactsBlock();
        if (com.netease.nimlib.o.f.c((Collection) queryRecentContactsBlock) || com.netease.nimlib.o.f.c((Collection) set)) {
            return queryRecentContactsBlock;
        }
        ArrayList arrayList = new ArrayList(queryRecentContactsBlock.size());
        for (RecentContact recentContact : queryRecentContactsBlock) {
            if (recentContact instanceof q) {
                q a = a((q) recentContact, set);
                if (a != null) {
                    recentContact = a;
                }
                arrayList.add(recentContact);
            }
        }
        return arrayList;
    }

    private q a(q qVar, Set<MsgTypeEnum> set) {
        if (qVar == null || com.netease.nimlib.o.f.c((Collection) set)) {
            return qVar;
        }
        String contactId = qVar.getContactId();
        SessionTypeEnum sessionType = qVar.getSessionType();
        MsgTypeEnum msgType = qVar.getMsgType();
        if (TextUtils.isEmpty(contactId) || sessionType == null) {
            return null;
        }
        if (msgType == null || !set.contains(msgType)) {
            return qVar;
        }
        IMMessage queryLatestMessageFilterMsgType = MsgDBHelper.queryLatestMessageFilterMsgType(contactId, sessionType.getValue(), com.netease.nimlib.o.f.c(set, new f.a() { // from class: com.netease.nimlib.biz.f.-$$Lambda$mY4zblUfPsezdJGeKIT5ofz-oRk
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return Integer.valueOf(((MsgTypeEnum) obj).getValue());
            }
        }));
        if (queryLatestMessageFilterMsgType == null) {
            qVar.b("");
            qVar.c("");
            qVar.b(MsgTypeEnum.text.getValue());
            qVar.setMsgStatus(MsgStatusEnum.success);
            qVar.d("");
            return qVar;
        }
        q g = com.netease.nimlib.session.j.g((IMMessageImpl) queryLatestMessageFilterMsgType);
        g.a(qVar.getUnreadCount());
        g.setTag(qVar.getTag());
        g.f(qVar.c());
        return g;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<ThreadTalkHistory> queryThreadTalkHistory(IMMessage iMMessage, long j, long j2, int i, QueryDirectionEnum queryDirectionEnum, boolean z) {
        if (!(iMMessage instanceof IMMessageImpl)) {
            b().a(1000).b();
            return null;
        }
        com.netease.nimlib.biz.d.j.j jVar = new com.netease.nimlib.biz.d.j.j((IMMessageImpl) iMMessage, j, j2, i, queryDirectionEnum, z);
        jVar.a(b());
        com.netease.nimlib.biz.i.a().a(jVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<ThreadTalkHistory> queryThreadTalkHistory(MessageKey messageKey, QueryThreadTalkHistoryOption queryThreadTalkHistoryOption) {
        if (messageKey == null || queryThreadTalkHistoryOption == null || !messageKey.isValid() || !queryThreadTalkHistoryOption.isValid()) {
            b().a(414).b();
            return null;
        }
        com.netease.nimlib.biz.d.j.j jVar = new com.netease.nimlib.biz.d.j.j(messageKey, queryThreadTalkHistoryOption);
        jVar.a(b());
        com.netease.nimlib.biz.i.a().a(jVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public int queryReplyCountInThreadTalkBlock(IMMessage iMMessage) {
        if (!(iMMessage instanceof IMMessageImpl)) {
            b().a(1000).b();
        }
        return MsgDBHelper.queryReplyCount(iMMessage.isThread() ? iMMessage.getUuid() : iMMessage.getThreadOption().getThreadMsgIdClient(), iMMessage.getSessionId(), iMMessage.getSessionType());
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> addQuickComment(IMMessage iMMessage, long j, String str) {
        a(iMMessage, j, str, false, false, "", "", null);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Long> addQuickComment(IMMessage iMMessage, long j, String str, boolean z, boolean z2, String str2, String str3, Map<String, Object> map) {
        a(iMMessage, j, str, z, z2, str2, str3, map);
        return null;
    }

    private void a(IMMessage iMMessage, long j, String str, boolean z, boolean z2, String str2, String str3, Map<String, Object> map) {
        if (!(iMMessage instanceof IMMessageImpl)) {
            b().a(414).b();
            return;
        }
        com.netease.nimlib.biz.d.j.c cVar = new com.netease.nimlib.biz.d.j.c((IMMessageImpl) iMMessage, j, str, z, z2, str2, str3, map);
        cVar.a(b());
        com.netease.nimlib.biz.i.a().a(cVar);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> removeQuickComment(IMMessage iMMessage, long j, String str) {
        b(iMMessage, j, str, false, false, "", "", null);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Long> removeQuickComment(IMMessage iMMessage, long j, String str, boolean z, boolean z2, String str2, String str3, Map<String, Object> map) {
        b(iMMessage, j, str, z, z2, str2, str3, map);
        return null;
    }

    private void b(IMMessage iMMessage, long j, String str, boolean z, boolean z2, String str2, String str3, Map<String, Object> map) {
        if (!(iMMessage instanceof IMMessageImpl)) {
            b().a(414).b();
            return;
        }
        com.netease.nimlib.biz.d.j.m mVar = new com.netease.nimlib.biz.d.j.m((IMMessageImpl) iMMessage, j, str, z, z2, str2, str3, map);
        mVar.a(b());
        com.netease.nimlib.biz.i.a().a(mVar);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<List<QuickCommentOptionWrapper>> queryQuickComment(List<IMMessage> list) {
        com.netease.nimlib.i.k b = b();
        if (list == null || list.isEmpty()) {
            b.b(new ArrayList()).b();
            return null;
        }
        com.netease.nimlib.biz.d.j.i iVar = new com.netease.nimlib.biz.d.j.i(list);
        iVar.a(b());
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(iVar) { // from class: com.netease.nimlib.biz.f.g.2
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                super.a(aVar);
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<CollectInfo> addCollect(int i, String str, String str2, String str3) {
        com.netease.nimlib.biz.d.j.a aVar = new com.netease.nimlib.biz.d.j.a(i, str, str2, str3);
        aVar.a(b());
        com.netease.nimlib.biz.i.a().a(aVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Integer> removeCollect(List<Pair<Long, Long>> list) {
        com.netease.nimlib.biz.d.j.k kVar = new com.netease.nimlib.biz.d.j.k(list);
        kVar.a(b());
        com.netease.nimlib.biz.i.a().a(kVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<CollectInfo> updateCollect(CollectInfo collectInfo, String str) {
        if (!(collectInfo instanceof com.netease.nimlib.session.a)) {
            b().a(414).b();
        }
        return updateCollect(collectInfo.getId(), collectInfo.getCreateTime(), str);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<CollectInfo> updateCollect(long j, long j2, String str) {
        com.netease.nimlib.biz.d.j.o oVar = new com.netease.nimlib.biz.d.j.o(j, j2, str);
        oVar.a(b());
        com.netease.nimlib.biz.i.a().a(oVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<CollectInfoPage> queryCollect(int i) {
        a((CollectInfo) null, 0L, i, QueryDirectionEnum.QUERY_OLD, (Integer) null, true);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<CollectInfoPage> queryCollect(CollectInfo collectInfo, long j, int i, QueryDirectionEnum queryDirectionEnum) {
        a(collectInfo, j, i, queryDirectionEnum, (Integer) null, true);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<CollectInfoPage> queryCollect(CollectInfo collectInfo, long j, int i, QueryDirectionEnum queryDirectionEnum, int i2, boolean z) {
        a(collectInfo, j, i, queryDirectionEnum, Integer.valueOf(i2), z);
        return null;
    }

    private void a(CollectInfo collectInfo, long j, int i, QueryDirectionEnum queryDirectionEnum, Integer num, boolean z) {
        com.netease.nimlib.biz.d.j.g gVar;
        boolean z2 = queryDirectionEnum != QueryDirectionEnum.QUERY_OLD;
        if (collectInfo == null) {
            gVar = new com.netease.nimlib.biz.d.j.g(null, null, null, i, z2, num, z);
        } else {
            Long valueOf = Long.valueOf(z2 ? collectInfo.getCreateTime() : j);
            if (!z2) {
                j = collectInfo.getCreateTime();
            }
            gVar = new com.netease.nimlib.biz.d.j.g(valueOf, Long.valueOf(j), Long.valueOf(collectInfo.getId()), i, z2, num, z);
        }
        gVar.a(b());
        com.netease.nimlib.biz.i.a().a(gVar);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Long> addMsgPin(IMMessage iMMessage, String str) {
        if (!(iMMessage instanceof IMMessageImpl)) {
            b().a(414).b();
            return null;
        }
        if (str == null) {
            str = "";
        }
        com.netease.nimlib.biz.d.j.b bVar = new com.netease.nimlib.biz.d.j.b(iMMessage.getSessionType(), iMMessage.getFromAccount(), com.netease.nimlib.session.g.a((IMMessageImpl) iMMessage), iMMessage.getTime(), iMMessage.getServerId(), iMMessage.getUuid(), str);
        bVar.a(b());
        com.netease.nimlib.biz.i.a().a(bVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Long> updateMsgPin(IMMessage iMMessage, String str) {
        if (!(iMMessage instanceof IMMessageImpl)) {
            b().a(414).b();
            return null;
        }
        if (str == null) {
            str = "";
        }
        com.netease.nimlib.biz.d.j.p pVar = new com.netease.nimlib.biz.d.j.p(iMMessage.getSessionType(), iMMessage.getFromAccount(), com.netease.nimlib.session.g.a((IMMessageImpl) iMMessage), iMMessage.getTime(), iMMessage.getServerId(), iMMessage.getUuid(), str);
        pVar.a(b());
        com.netease.nimlib.biz.i.a().a(pVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Long> removeMsgPin(IMMessage iMMessage, String str) {
        if (!(iMMessage instanceof IMMessageImpl)) {
            b().a(414).b();
            return null;
        }
        if (str == null) {
            str = "";
        }
        com.netease.nimlib.biz.d.j.l lVar = new com.netease.nimlib.biz.d.j.l(iMMessage.getSessionType(), iMMessage.getFromAccount(), com.netease.nimlib.session.g.a((IMMessageImpl) iMMessage), iMMessage.getTime(), iMMessage.getServerId(), iMMessage.getUuid(), str);
        lVar.a(b());
        com.netease.nimlib.biz.i.a().a(lVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<MsgPinSyncResponseOptionWrapper> syncMsgPin(SessionTypeEnum sessionTypeEnum, String str, long j) {
        if (sessionTypeEnum == null || sessionTypeEnum == SessionTypeEnum.None || TextUtils.isEmpty(str)) {
            b().a(414).b();
            return null;
        }
        com.netease.nimlib.biz.d.j.f fVar = new com.netease.nimlib.biz.d.j.f(str, sessionTypeEnum, j);
        fVar.a(b());
        com.netease.nimlib.biz.i.a().a(fVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<MsgPinDbOption> queryMsgPinBlock(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelper.queryMsgPin(str);
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<StickTopSessionInfo> addStickTopSession(String str, SessionTypeEnum sessionTypeEnum, String str2) {
        if (sessionTypeEnum == null || sessionTypeEnum == SessionTypeEnum.None || TextUtils.isEmpty(str)) {
            b().a(414).b();
            return null;
        }
        com.netease.nimlib.biz.d.j.d dVar = new com.netease.nimlib.biz.d.j.d(str, sessionTypeEnum, str2);
        dVar.a(b());
        com.netease.nimlib.biz.i.a().a(dVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<Void> removeStickTopSession(String str, SessionTypeEnum sessionTypeEnum, String str2) {
        if (sessionTypeEnum == null || sessionTypeEnum == SessionTypeEnum.None || TextUtils.isEmpty(str)) {
            b().a(414).b();
            return null;
        }
        com.netease.nimlib.biz.d.j.n nVar = new com.netease.nimlib.biz.d.j.n(str, sessionTypeEnum, str2);
        nVar.a(b());
        com.netease.nimlib.biz.i.a().a(nVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public InvocationFuture<StickTopSessionInfo> updateStickTopSession(String str, SessionTypeEnum sessionTypeEnum, String str2) {
        if (sessionTypeEnum == null || sessionTypeEnum == SessionTypeEnum.None || TextUtils.isEmpty(str)) {
            b().a(414).b();
            return null;
        }
        com.netease.nimlib.biz.d.j.q qVar = new com.netease.nimlib.biz.d.j.q(str, sessionTypeEnum, str2);
        qVar.a(b());
        com.netease.nimlib.biz.i.a().a(qVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public List<StickTopSessionInfo> queryStickTopSessionBlock() {
        return new ArrayList(MsgDBHelper.queryStickTopSession());
    }

    @Override // com.netease.nimlib.sdk.msg.MsgService
    public boolean isStickTopSession(String str, SessionTypeEnum sessionTypeEnum) {
        if (TextUtils.isEmpty(str) || sessionTypeEnum == null) {
            return false;
        }
        return MsgDBHelper.isStickTopSession(str, sessionTypeEnum);
    }
}
