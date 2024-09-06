package com.netease.nimlib.chatroom.e;

import android.os.SystemClock;
import android.text.TextUtils;
import com.netease.nimlib.chatroom.c;
import com.netease.nimlib.chatroom.c.f;
import com.netease.nimlib.chatroom.c.i;
import com.netease.nimlib.chatroom.c.l;
import com.netease.nimlib.chatroom.c.m;
import com.netease.nimlib.chatroom.c.n;
import com.netease.nimlib.chatroom.c.o;
import com.netease.nimlib.chatroom.c.r;
import com.netease.nimlib.chatroom.c.s;
import com.netease.nimlib.chatroom.c.t;
import com.netease.nimlib.chatroom.c.u;
import com.netease.nimlib.chatroom.c.v;
import com.netease.nimlib.chatroom.c.w;
import com.netease.nimlib.chatroom.c.x;
import com.netease.nimlib.chatroom.c.y;
import com.netease.nimlib.chatroom.d;
import com.netease.nimlib.chatroom.g;
import com.netease.nimlib.chatroom.k;
import com.netease.nimlib.chatroom.model.ChatRoomMessageImpl;
import com.netease.nimlib.i.h;
import com.netease.nimlib.i.j;
import com.netease.nimlib.log.b;
import com.netease.nimlib.net.a.a.e;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import com.netease.nimlib.sdk.chatroom.ChatRoomService;
import com.netease.nimlib.sdk.chatroom.constant.ChatRoomModeEnum;
import com.netease.nimlib.sdk.chatroom.constant.MemberQueryType;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMemberUpdate;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomSpatialLocation;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomTagsInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomUpdateInfo;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomResultData;
import com.netease.nimlib.sdk.chatroom.model.GetMessagesByTagsParam;
import com.netease.nimlib.sdk.chatroom.model.MemberOption;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.robot.model.NimRobotInfo;
import com.netease.nimlib.sdk.util.Entry;
import com.netease.nimlib.session.IMMessageImpl;
import java.util.List;
import java.util.Map;

/* compiled from: ChatRoomServiceRemote.java */
/* loaded from: classes.dex */
public class a extends j implements ChatRoomService {
    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public AbortableFuture<EnterChatRoomResultData> enterChatRoom(final EnterChatRoomData enterChatRoomData) {
        d.e().a(b(), enterChatRoomData);
        return new h<EnterChatRoomData>(enterChatRoomData) { // from class: com.netease.nimlib.chatroom.e.a.1
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                a.this.exitChatRoom(enterChatRoomData.getRoomId());
                return false;
            }
        };
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public AbortableFuture<EnterChatRoomResultData> enterChatRoomEx(EnterChatRoomData enterChatRoomData, int i) {
        if (i > 0) {
            b().c(i);
            b.g("set enter chat room retry count=" + i);
        }
        return enterChatRoom(enterChatRoomData);
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public int getEnterErrorCode(String str) {
        if (c.a().c(str)) {
            return c.a().f(str);
        }
        return -1;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public void exitChatRoom(String str) {
        d.e().a(str);
    }

    /* compiled from: ChatRoomServiceRemote.java */
    /* renamed from: com.netease.nimlib.chatroom.e.a$3, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ChatRoomModeEnum.values().length];
            a = iArr;
            try {
                iArr[ChatRoomModeEnum.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ChatRoomModeEnum.DEPENDENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ChatRoomModeEnum.INDEPENDENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public void exitChatRooms(ChatRoomModeEnum chatRoomModeEnum) {
        if (chatRoomModeEnum == null) {
            return;
        }
        int i = AnonymousClass3.a[chatRoomModeEnum.ordinal()];
        if (i == 1) {
            d.e().a();
            d.e().a(com.netease.nimlib.c.e());
        } else if (i == 2) {
            d.e().a(false);
        } else {
            if (i != 3) {
                return;
            }
            d.e().a(true);
        }
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> sendMessage(ChatRoomMessage chatRoomMessage, boolean z) {
        com.netease.nimlib.chatroom.h.a((ChatRoomMessageImpl) chatRoomMessage, z, b(), SystemClock.elapsedRealtime());
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public AbortableFuture<Void> downloadAttachment(ChatRoomMessage chatRoomMessage, boolean z) {
        e a = g.a((IMMessageImpl) chatRoomMessage, z, b());
        if (a == null) {
            return null;
        }
        return new h<e>(a) { // from class: com.netease.nimlib.chatroom.e.a.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.netease.nimlib.sdk.AbortableFuture
            public boolean abort() {
                if (com.netease.nimlib.biz.b.e.d().a()) {
                    com.netease.nimlib.biz.b.e.d().a((e) this.c);
                    return false;
                }
                com.netease.nimlib.net.a.a.g.a().b((e) this.c);
                return false;
            }
        };
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<ChatRoomMessage>> pullMessageHistory(String str, long j, int i) {
        a(new com.netease.nimlib.chatroom.c.j(j, i), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<ChatRoomMessage>> pullMessageHistoryEx(String str, long j, int i, QueryDirectionEnum queryDirectionEnum) {
        a(new com.netease.nimlib.chatroom.c.j(j, i, queryDirectionEnum == QueryDirectionEnum.QUERY_NEW), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<ChatRoomMessage>> pullMessageHistoryExType(String str, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr) {
        if (msgTypeEnumArr == null) {
            return pullMessageHistoryEx(str, j, i, queryDirectionEnum);
        }
        int[] iArr = new int[msgTypeEnumArr.length];
        for (int i2 = 0; i2 < msgTypeEnumArr.length; i2++) {
            if (msgTypeEnumArr[i2] == MsgTypeEnum.undef || msgTypeEnumArr[i2] == MsgTypeEnum.avchat) {
                throw new IllegalArgumentException("typeEnum params of this method have illegal value");
            }
            iArr[i2] = msgTypeEnumArr[i2].getValue();
        }
        a(new com.netease.nimlib.chatroom.c.j(j, i, queryDirectionEnum == QueryDirectionEnum.QUERY_NEW, iArr), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<ChatRoomMessage>> getMessagesByTags(GetMessagesByTagsParam getMessagesByTagsParam) {
        if (getMessagesByTagsParam == null) {
            b().a(414).b();
            return null;
        }
        a(new i(k.a(getMessagesByTagsParam)), String.valueOf(getMessagesByTagsParam.getRoomId()));
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<ChatRoomInfo> fetchRoomInfo(String str) {
        a(new com.netease.nimlib.chatroom.c.k(), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<ChatRoomMember>> fetchRoomMembers(String str, MemberQueryType memberQueryType, long j, int i) {
        a(new f((byte) memberQueryType.getValue(), j, i), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<ChatRoomMember>> fetchRoomMembersByTag(String str, String str2, long j, int i) {
        a(new com.netease.nimlib.chatroom.c.g(str2, j, i), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<ChatRoomMember> markChatRoomBlackList(boolean z, MemberOption memberOption) {
        a(z, memberOption, -1);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<ChatRoomMember> markChatRoomMutedList(boolean z, MemberOption memberOption) {
        a(z, memberOption, -2);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> markChatRoomTempMute(boolean z, long j, MemberOption memberOption) {
        a(new t(memberOption.getAccount(), j, z, com.netease.nimlib.session.j.a(memberOption.getNotifyExtension())), memberOption.getRoomId());
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> markChatRoomTempMuteByTag(String str, String str2, boolean z, long j, Map<String, Object> map, String str3) {
        a(new s(str2, j, z, com.netease.nimlib.session.j.a(map), str3), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<ChatRoomMember> markChatRoomManager(boolean z, MemberOption memberOption) {
        a(z, memberOption, 1);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<ChatRoomMember> markNormalMember(boolean z, MemberOption memberOption) {
        a(z, memberOption, 2);
        return null;
    }

    private void a(boolean z, MemberOption memberOption, int i) {
        a(new r(memberOption.getAccount(), i, z, 0, com.netease.nimlib.session.j.a(memberOption.getNotifyExtension())), memberOption.getRoomId());
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<ChatRoomMember>> fetchRoomMembersByIds(String str, List<String> list) {
        a(new com.netease.nimlib.chatroom.c.h(list), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Long> queryTagMembersCount(String str, String str2) {
        a(new o(str2), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> kickMember(String str, String str2, Map<String, Object> map) {
        a(new l(str2, a(map, 1024)), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> updateRoomInfo(String str, ChatRoomUpdateInfo chatRoomUpdateInfo, boolean z, Map<String, Object> map) {
        a(new y(str, chatRoomUpdateInfo, z, com.netease.nimlib.session.j.a(map)), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> updateRoomInfo(String str, ChatRoomUpdateInfo chatRoomUpdateInfo, boolean z, Map<String, Object> map, AntiSpamConfig antiSpamConfig) {
        a(new y(str, chatRoomUpdateInfo, z, com.netease.nimlib.session.j.a(map), antiSpamConfig), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> updateMyRoomRole(String str, ChatRoomMemberUpdate chatRoomMemberUpdate, boolean z, Map<String, Object> map) {
        w wVar = new w(chatRoomMemberUpdate, z, a(map, 2048));
        c.a().a(str, chatRoomMemberUpdate);
        a(wVar, str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> updateMyRoomRole(String str, ChatRoomMemberUpdate chatRoomMemberUpdate, boolean z, Map<String, Object> map, AntiSpamConfig antiSpamConfig) {
        w wVar = new w(chatRoomMemberUpdate, z, a(map, 2048), antiSpamConfig);
        c.a().a(str, chatRoomMemberUpdate);
        a(wVar, str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> updateQueue(String str, String str2, String str3) {
        updateQueueEx(str, str2, str3, false);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> updateQueueEx(String str, String str2, String str3, boolean z) {
        updateQueue(str, str2, str3, z, null);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> updateQueue(String str, String str2, String str3, boolean z, String str4) {
        a(new x(str2, str3, z, str4), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<String>> batchUpdateQueue(String str, List<Entry<String, String>> list, boolean z, Map<String, Object> map) {
        com.netease.nimlib.push.packet.b.e eVar = new com.netease.nimlib.push.packet.b.e();
        for (Entry<String, String> entry : list) {
            eVar.a(entry.key, entry.value);
        }
        a(new com.netease.nimlib.chatroom.c.a(eVar, z, a(map, 2048)), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Entry<String, String>> pollQueue(String str, String str2) {
        a(new n(str2), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<Entry<String, String>>> fetchQueue(String str) {
        a(new m(), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> dropQueue(String str) {
        a(new com.netease.nimlib.chatroom.c.c(), str);
        return null;
    }

    private void a(com.netease.nimlib.biz.d.a aVar, String str) {
        a(aVar, str, (com.netease.nimlib.i.k) null);
    }

    private void a(com.netease.nimlib.biz.d.a aVar, String str, com.netease.nimlib.i.k kVar) {
        if (kVar == null) {
            kVar = b();
        }
        aVar.a(kVar);
        d.e().a(new com.netease.nimlib.chatroom.o(str, aVar), str);
    }

    private String a(Map<String, Object> map, int i) {
        String a = com.netease.nimlib.session.j.a(map);
        if (TextUtils.isEmpty(a) || a.length() <= i) {
            return a;
        }
        throw new IllegalArgumentException("length over limit " + i);
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<List<NimRobotInfo>> pullAllRobots(String str) {
        com.netease.nimlib.biz.d.l.a aVar = new com.netease.nimlib.biz.d.l.a(0L);
        aVar.a(b());
        a(aVar, str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> updateChatRoomTags(String str, ChatRoomTagsInfo chatRoomTagsInfo) {
        u uVar = new u(chatRoomTagsInfo);
        a(uVar, str);
        com.netease.nimlib.biz.g.b().a(uVar.b(), uVar.c(), str);
        return null;
    }

    @Override // com.netease.nimlib.sdk.chatroom.ChatRoomService
    public InvocationFuture<Void> updateLocation(String str, ChatRoomSpatialLocation chatRoomSpatialLocation) {
        a(new v(chatRoomSpatialLocation), str);
        return null;
    }
}
