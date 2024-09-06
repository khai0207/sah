package com.netease.nimlib.chatroom;

import android.text.TextUtils;
import com.netease.nimlib.chatroom.model.ChatRoomMessageImpl;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessageExtension;
import com.netease.nimlib.sdk.chatroom.model.CustomChatRoomMessageConfig;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.NIMAntiSpamOption;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.ac;
import com.netease.nimlib.session.g;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: ChatRoomMessageReceiver.java */
/* loaded from: classes.dex */
public class g extends com.netease.nimlib.session.g {
    public static IMMessageImpl a(com.netease.nimlib.push.packet.b.c cVar, boolean z) {
        if (z && !d(cVar)) {
            return null;
        }
        ChatRoomMessageImpl chatRoomMessageImpl = new ChatRoomMessageImpl();
        chatRoomMessageImpl.setUuid(cVar.c(1));
        chatRoomMessageImpl.setMsgType(cVar.d(2));
        chatRoomMessageImpl.setAttachStr(cVar.c(3));
        chatRoomMessageImpl.setRemoteExtensionStr(cVar.c(4));
        ChatRoomMessageExtension chatRoomMessageExtension = new ChatRoomMessageExtension();
        chatRoomMessageExtension.setRoleInfoTimeTag(cVar.e(6));
        chatRoomMessageExtension.setSenderNick(cVar.c(7));
        chatRoomMessageExtension.setSenderAvatar(cVar.c(8));
        chatRoomMessageExtension.setSenderExtension(com.netease.nimlib.session.j.c(cVar.c(9)));
        chatRoomMessageImpl.setChatRoomMessageExtension(chatRoomMessageExtension);
        chatRoomMessageImpl.setTime(cVar.e(20));
        chatRoomMessageImpl.setFromAccount(cVar.c(21));
        chatRoomMessageImpl.setFromClientType(cVar.d(23));
        chatRoomMessageImpl.setSessionId(cVar.c(22));
        chatRoomMessageImpl.setSessionType(SessionTypeEnum.ChatRoom);
        chatRoomMessageImpl.setStatus(MsgStatusEnum.success);
        chatRoomMessageImpl.setAttachStatus(AttachStatusEnum.def);
        NIMAntiSpamOption nIMAntiSpamOption = new NIMAntiSpamOption();
        if (cVar.f(16)) {
            nIMAntiSpamOption.enable = cVar.d(16) == 1;
            chatRoomMessageImpl.setNIMAntiSpamOption(nIMAntiSpamOption);
        }
        if (cVar.f(11)) {
            nIMAntiSpamOption.content = cVar.c(11);
            chatRoomMessageImpl.setNIMAntiSpamOption(nIMAntiSpamOption);
        }
        if (cVar.f(14)) {
            nIMAntiSpamOption.antiSpamConfigId = cVar.c(14);
            chatRoomMessageImpl.setNIMAntiSpamOption(nIMAntiSpamOption);
        }
        if (chatRoomMessageImpl.getMsgType() == MsgTypeEnum.text || chatRoomMessageImpl.getMsgType() == MsgTypeEnum.tip) {
            chatRoomMessageImpl.setContent(chatRoomMessageImpl.getAttachStr(false));
        } else if (chatRoomMessageImpl.getMsgType() == MsgTypeEnum.robot && cVar.f(13)) {
            chatRoomMessageImpl.setContent(cVar.c(13));
        }
        CustomChatRoomMessageConfig customChatRoomMessageConfig = new CustomChatRoomMessageConfig();
        if (cVar.f(12)) {
            customChatRoomMessageConfig.skipHistory = cVar.d(12) == 1;
        }
        if (cVar.f(25)) {
            chatRoomMessageImpl.setHighPriorityMessage();
        }
        if (cVar.f(27)) {
            chatRoomMessageImpl.setCallbackExtension(cVar.c(27));
        }
        if (cVar.f(28)) {
            chatRoomMessageImpl.setSubtype(cVar.d(28));
        }
        if (cVar.f(29)) {
            chatRoomMessageImpl.setYidunAntiCheating(cVar.c(29));
        }
        if (cVar.f(30)) {
            chatRoomMessageImpl.setEnv(cVar.c(30));
        }
        if (cVar.f(31)) {
            chatRoomMessageImpl.setNotifyTargetTags(cVar.c(31));
        }
        if (cVar.f(32)) {
            chatRoomMessageImpl.setYidunAntiSpamExt(cVar.c(32));
        }
        if (cVar.f(33)) {
            chatRoomMessageImpl.setYidunAntiSpamRes(cVar.c(33));
        }
        if (cVar.f(38)) {
            chatRoomMessageImpl.setNeedHighPriorityMsgAck(Boolean.valueOf(cVar.d(38) == 1));
        }
        if (cVar.f(39)) {
            chatRoomMessageImpl.setTimeConsumingStatistics(ac.a(cVar.c(39)));
        }
        com.netease.nimlib.session.g.a(chatRoomMessageImpl, cVar.c(-3));
        return chatRoomMessageImpl;
    }

    public static IMMessageImpl a(JSONObject jSONObject, boolean z) {
        if (z && !a(jSONObject)) {
            return null;
        }
        ChatRoomMessageImpl chatRoomMessageImpl = new ChatRoomMessageImpl();
        chatRoomMessageImpl.setUuid(jSONObject.optString(String.valueOf(1)));
        chatRoomMessageImpl.setMsgType(jSONObject.optInt(String.valueOf(2)));
        chatRoomMessageImpl.setAttachStr(jSONObject.optString(String.valueOf(3)));
        chatRoomMessageImpl.setRemoteExtensionStr(jSONObject.optString(String.valueOf(4)));
        ChatRoomMessageExtension chatRoomMessageExtension = new ChatRoomMessageExtension();
        chatRoomMessageExtension.setRoleInfoTimeTag(jSONObject.optLong(String.valueOf(6)));
        chatRoomMessageExtension.setSenderNick(jSONObject.optString(String.valueOf(7)));
        chatRoomMessageExtension.setSenderAvatar(jSONObject.optString(String.valueOf(8)));
        chatRoomMessageExtension.setSenderExtension(com.netease.nimlib.session.j.c(jSONObject.optString(String.valueOf(9))));
        chatRoomMessageImpl.setChatRoomMessageExtension(chatRoomMessageExtension);
        chatRoomMessageImpl.setTime(jSONObject.optLong(String.valueOf(20)));
        chatRoomMessageImpl.setFromAccount(jSONObject.optString(String.valueOf(21)));
        chatRoomMessageImpl.setFromClientType(jSONObject.optInt(String.valueOf(23)));
        chatRoomMessageImpl.setSessionId(jSONObject.optString(String.valueOf(22)));
        chatRoomMessageImpl.setSessionType(SessionTypeEnum.ChatRoom);
        chatRoomMessageImpl.setStatus(MsgStatusEnum.success);
        chatRoomMessageImpl.setAttachStatus(AttachStatusEnum.def);
        NIMAntiSpamOption nIMAntiSpamOption = new NIMAntiSpamOption();
        if (jSONObject.has(String.valueOf(16))) {
            nIMAntiSpamOption.enable = jSONObject.optInt(String.valueOf(16)) == 1;
            chatRoomMessageImpl.setNIMAntiSpamOption(nIMAntiSpamOption);
        }
        if (jSONObject.has(String.valueOf(11))) {
            nIMAntiSpamOption.content = jSONObject.optString(String.valueOf(11));
            chatRoomMessageImpl.setNIMAntiSpamOption(nIMAntiSpamOption);
        }
        if (jSONObject.has(String.valueOf(14))) {
            nIMAntiSpamOption.antiSpamConfigId = jSONObject.optString(String.valueOf(14));
            chatRoomMessageImpl.setNIMAntiSpamOption(nIMAntiSpamOption);
        }
        if (chatRoomMessageImpl.getMsgType() == MsgTypeEnum.text || chatRoomMessageImpl.getMsgType() == MsgTypeEnum.tip) {
            chatRoomMessageImpl.setContent(chatRoomMessageImpl.getAttachStr(false));
        } else if (chatRoomMessageImpl.getMsgType() == MsgTypeEnum.robot && jSONObject.has(String.valueOf(13))) {
            chatRoomMessageImpl.setContent(jSONObject.optString(String.valueOf(13)));
        }
        CustomChatRoomMessageConfig customChatRoomMessageConfig = new CustomChatRoomMessageConfig();
        if (jSONObject.has(String.valueOf(12))) {
            customChatRoomMessageConfig.skipHistory = jSONObject.optInt(String.valueOf(12)) == 1;
        }
        if (jSONObject.has(String.valueOf(25))) {
            chatRoomMessageImpl.setHighPriorityMessage();
        }
        if (jSONObject.has(String.valueOf(38))) {
            chatRoomMessageImpl.setNeedHighPriorityMsgAck(Boolean.valueOf(jSONObject.optInt(String.valueOf(38)) == 1));
        }
        if (jSONObject.has(String.valueOf(39))) {
            chatRoomMessageImpl.setTimeConsumingStatistics(ac.a(jSONObject.optString(String.valueOf(39))));
        }
        com.netease.nimlib.session.g.a(chatRoomMessageImpl, jSONObject.optString(String.valueOf(-3)));
        return chatRoomMessageImpl;
    }

    public static void a(ArrayList<IMMessageImpl> arrayList) {
        com.netease.nimlib.session.g.a(arrayList, new a());
    }

    public static com.netease.nimlib.net.a.a.e a(IMMessageImpl iMMessageImpl, boolean z, com.netease.nimlib.i.k kVar) {
        return com.netease.nimlib.session.g.a(iMMessageImpl, z, kVar, new a());
    }

    private static boolean d(com.netease.nimlib.push.packet.b.c cVar) {
        String c = cVar.c(22);
        String c2 = cVar.c(1);
        return TextUtils.isEmpty(c2) || !c.a().u(c).b(c2);
    }

    private static boolean a(JSONObject jSONObject) {
        String optString = jSONObject.optString(String.valueOf(22));
        String optString2 = jSONObject.optString(String.valueOf(1));
        return TextUtils.isEmpty(optString2) || !c.a().u(optString).b(optString2);
    }

    /* compiled from: ChatRoomMessageReceiver.java */
    /* loaded from: classes.dex */
    protected static class a extends g.a {
        protected a() {
        }

        @Override // com.netease.nimlib.session.g.a
        public void a(IMMessageImpl iMMessageImpl, AttachStatusEnum attachStatusEnum) {
            l.a(iMMessageImpl);
        }

        @Override // com.netease.nimlib.session.g.a
        public void a(IMMessageImpl iMMessageImpl, long j, long j2) {
            l.a(iMMessageImpl.getUuid(), j, j2);
        }
    }
}
