package com.netease.nimlib.chatroom;

import android.text.TextUtils;
import com.netease.nimlib.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.netease.nimlib.chatroom.model.ChatRoomMessageImpl;
import com.netease.nimlib.o.v;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import com.netease.nimlib.sdk.auth.ChatRoomAuthProvider;
import com.netease.nimlib.sdk.auth.ChatRoomLoginExtProvider;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.chatroom.constant.MemberType;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomCdnInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomSpatialLocation;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: ChatRoomHelper.java */
/* loaded from: classes.dex */
public class e {
    static boolean a(String str, int i) {
        if (i == 398 || i == 399 || i == 408 || i == 415 || i == 500 || i == 1001) {
            return !c.a().q(str);
        }
        return false;
    }

    static com.netease.nimlib.push.packet.b.c a(EnterChatRoomData enterChatRoomData) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(3, com.netease.nimlib.push.b.c());
        cVar.a(5, enterChatRoomData.getRoomId());
        cVar.a(8, !c.a().q(enterChatRoomData.getRoomId()) ? 1 : 0);
        if (!TextUtils.isEmpty(enterChatRoomData.getNick())) {
            cVar.a(20, enterChatRoomData.getNick());
        }
        if (!TextUtils.isEmpty(enterChatRoomData.getAvatar())) {
            cVar.a(21, enterChatRoomData.getAvatar());
        }
        if (enterChatRoomData.getExtension() != null) {
            cVar.a(22, com.netease.nimlib.session.j.a(enterChatRoomData.getExtension()));
        }
        if (enterChatRoomData.getNotifyExtension() != null) {
            cVar.a(23, com.netease.nimlib.session.j.a(enterChatRoomData.getNotifyExtension()));
        }
        com.netease.nimlib.log.b.d("ChatRoomHelper", "onConnectionEstablished, appLogin=" + cVar.d(8));
        return cVar;
    }

    static com.netease.nimlib.push.packet.b.c a(EnterChatRoomData enterChatRoomData, boolean z) {
        String g;
        String n;
        ChatRoomSpatialLocation chatRoomSpatialLocation;
        com.netease.nimlib.push.packet.b.c a = a(enterChatRoomData);
        if (z) {
            g = enterChatRoomData.getAppKey();
            if (TextUtils.isEmpty(g)) {
                g = com.netease.nimlib.c.g();
            }
            a.a(1, g);
            n = enterChatRoomData.getAccount();
            a.a(2, n);
            if (enterChatRoomData.isAnonymousMode()) {
                a.a(38, 1);
            }
        } else {
            g = com.netease.nimlib.c.g();
            a.a(1, g);
            n = com.netease.nimlib.c.n();
            a.a(2, n);
            a.a(26, com.netease.nimlib.c.r());
        }
        if (!TextUtils.isEmpty(enterChatRoomData.getTags())) {
            a.a(39, enterChatRoomData.getTags());
        }
        if (!TextUtils.isEmpty(enterChatRoomData.getNotifyTargetTags())) {
            a.a(40, enterChatRoomData.getNotifyTargetTags());
        }
        AntiSpamConfig antiSpamConfig = enterChatRoomData.getAntiSpamConfig();
        if (antiSpamConfig != null) {
            String antiSpamBusinessId = antiSpamConfig.getAntiSpamBusinessId();
            if (!TextUtils.isEmpty(antiSpamBusinessId)) {
                a.a(47, antiSpamBusinessId);
            }
        }
        Integer loginAuthType = enterChatRoomData.getLoginAuthType();
        if (loginAuthType != null) {
            a.a(41, loginAuthType.intValue());
        }
        ChatRoomLoginExtProvider chatRoomLoginExtProvider = com.netease.nimlib.c.i().chatroomLoginExtProvider;
        String str = null;
        String str2 = "";
        if (chatRoomLoginExtProvider != null && (str = chatRoomLoginExtProvider.getLoginExt(n, enterChatRoomData.getRoomId(), g)) != null && !str.isEmpty()) {
            str2 = "from ChatRoomLoginExtProvider";
        }
        if ((str == null || str.isEmpty()) && (str = enterChatRoomData.getLoginExt()) != null && !str.isEmpty()) {
            str2 = "from EnterChatRoomData";
        }
        if (str != null && !str.isEmpty()) {
            com.netease.nimlib.log.b.d("ChatRoomHelper", "loginExt " + str2 + " length:" + str.length());
            a.a(42, str);
        }
        if (com.netease.nimlib.c.i().enableChatRoomLocation && (chatRoomSpatialLocation = enterChatRoomData.getChatRoomSpatialLocation()) != null) {
            Double x = chatRoomSpatialLocation.getX();
            if (x != null) {
                a.a(43, x.doubleValue());
            }
            Double y = chatRoomSpatialLocation.getY();
            if (y != null) {
                a.a(44, y.doubleValue());
            }
            Double z2 = chatRoomSpatialLocation.getZ();
            if (z2 != null) {
                a.a(45, z2.doubleValue());
            }
            Double distance = chatRoomSpatialLocation.getDistance();
            if (distance != null) {
                a.a(46, distance.doubleValue());
            }
        }
        String a2 = com.netease.nimlib.biz.a.a(com.netease.nimlib.c.e());
        if (!TextUtils.isEmpty(a2)) {
            a.a(48, a2);
        }
        return a;
    }

    static com.netease.nimlib.push.packet.b.c a() {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(3, 1);
        cVar.a(6, 91700);
        cVar.a(25, com.netease.nimlib.c.f());
        cVar.a(9, 1);
        cVar.a(4, v.b());
        cVar.a(13, com.netease.nimlib.push.b.c());
        return cVar;
    }

    static com.netease.nimlib.push.packet.b.c b(EnterChatRoomData enterChatRoomData) {
        com.netease.nimlib.push.packet.b.c a = a();
        if (com.netease.nimlib.c.u() != null) {
            a.a(1, com.netease.nimlib.c.u().intValue());
        }
        a.a(18, com.netease.nimlib.c.g());
        LoginInfo m = com.netease.nimlib.c.m();
        if (m != null) {
            String account = m.getAccount();
            a.a(19, account);
            if (!a(enterChatRoomData, a, com.netease.nimlib.c.g(), account, enterChatRoomData.getRoomId())) {
                String token = m.getToken();
                if (!w.a((CharSequence) token)) {
                    a(a, token, "from LoginInfo getToken");
                }
            }
        }
        a.a(26, com.netease.nimlib.c.r());
        return a;
    }

    static com.netease.nimlib.push.packet.b.c c(EnterChatRoomData enterChatRoomData) {
        com.netease.nimlib.push.packet.b.c a = a();
        String appKey = enterChatRoomData.getAppKey();
        if (TextUtils.isEmpty(appKey)) {
            appKey = com.netease.nimlib.c.g();
        }
        String account = enterChatRoomData.getAccount();
        String roomId = enterChatRoomData.getRoomId();
        a.a(18, appKey);
        a.a(19, account);
        if (!a(enterChatRoomData, a, appKey, account, roomId)) {
            String token = enterChatRoomData.getToken();
            if (!w.a((CharSequence) token)) {
                a(a, token, "from EnterChatRoomData getToken");
            }
        }
        return a;
    }

    private static boolean a(EnterChatRoomData enterChatRoomData, com.netease.nimlib.push.packet.b.c cVar, String str, String str2, String str3) {
        ChatRoomAuthProvider chatRoomAuthProvider;
        int intValue = enterChatRoomData.getLoginAuthType() != null ? enterChatRoomData.getLoginAuthType().intValue() : 0;
        String str4 = null;
        String str5 = "";
        if (com.netease.nimlib.c.a(intValue) && (chatRoomAuthProvider = com.netease.nimlib.c.i().chatroomAuthProvider) != null) {
            str4 = chatRoomAuthProvider.getToken(str2, str3, str);
            if (!w.a((CharSequence) str4)) {
                str5 = "from ChatRoomAuthProvider of SDKOptions";
            }
        }
        if (w.a((CharSequence) str4) && intValue == 1) {
            str4 = enterChatRoomData.getChatRoomAuthProvider().getToken(str3, str2);
            if (!w.a((CharSequence) str4)) {
                str5 = "from ChatRoomAuthProvider of EnterChatRoomData";
            }
        }
        if (w.a((CharSequence) str4)) {
            return false;
        }
        a(cVar, str4, str5);
        return true;
    }

    private static void a(com.netease.nimlib.push.packet.b.c cVar, String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str2;
        objArr[1] = Integer.valueOf(str != null ? str.length() : 0);
        com.netease.nimlib.log.b.d("ChatRoomHelper", String.format("loginToken %s length: %s", objArr));
        cVar.a(1000, str);
    }

    public static ChatRoomMember a(com.netease.nimlib.push.packet.b.c cVar) {
        ChatRoomMember chatRoomMember = new ChatRoomMember();
        chatRoomMember.setRoomId(cVar.c(1));
        chatRoomMember.setAccount(cVar.c(2));
        if (cVar.d(9) == 1 && cVar.d(3) != MemberType.ANONYMOUS.getValue()) {
            chatRoomMember.setMemberType(MemberType.GUEST);
        } else if (cVar.f(3)) {
            chatRoomMember.setMemberType(MemberType.typeOfValue(cVar.d(3)));
        }
        chatRoomMember.setMemberLevel(cVar.d(4));
        chatRoomMember.setNick(cVar.c(5));
        chatRoomMember.setAvatar(cVar.c(6));
        chatRoomMember.setExtension(com.netease.nimlib.session.j.c(cVar.c(7)));
        chatRoomMember.setEnterTime(cVar.e(10));
        chatRoomMember.setOnline(cVar.d(8) == 1);
        chatRoomMember.setInBlackList(cVar.d(12) == 1);
        chatRoomMember.setMuted(cVar.d(13) == 1);
        chatRoomMember.setValid(cVar.d(14) == 1);
        chatRoomMember.setUpdateTime(cVar.e(15));
        chatRoomMember.setTempMuted(cVar.d(16) == 1);
        chatRoomMember.setTempMuteDuration(cVar.e(17));
        chatRoomMember.setTags(cVar.c(113));
        chatRoomMember.setNotifyTargetTags(cVar.c(114));
        return chatRoomMember;
    }

    public static ChatRoomInfo b(com.netease.nimlib.push.packet.b.c cVar) {
        ChatRoomInfo chatRoomInfo = new ChatRoomInfo();
        chatRoomInfo.setRoomId(cVar.c(1));
        chatRoomInfo.setName(cVar.c(3));
        chatRoomInfo.setAnnouncement(cVar.c(4));
        chatRoomInfo.setBroadcastUrl(cVar.c(5));
        chatRoomInfo.setCreator(cVar.c(100));
        chatRoomInfo.setValidFlag(cVar.d(9));
        chatRoomInfo.setExtension(com.netease.nimlib.session.j.c(cVar.c(12)));
        chatRoomInfo.setOnlineUserCount(cVar.d(101));
        chatRoomInfo.setMute(cVar.d(102));
        chatRoomInfo.setQueueLevel(cVar.d(16));
        return chatRoomInfo;
    }

    public static ChatRoomCdnInfo c(com.netease.nimlib.push.packet.b.c cVar) {
        ChatRoomCdnInfo chatRoomCdnInfo = new ChatRoomCdnInfo();
        chatRoomCdnInfo.setEnable(cVar.d(1) > 0);
        String c = cVar.c(2);
        if (!w.a((CharSequence) c)) {
            chatRoomCdnInfo.setCdnUrlArray(c.split("\\|"));
        }
        chatRoomCdnInfo.setTimestamp(cVar.e(3));
        chatRoomCdnInfo.setPollingInterval(cVar.d(4) * 1000);
        chatRoomCdnInfo.setDecryptKey(cVar.c(6));
        chatRoomCdnInfo.setTimeOut(cVar.d(7));
        return chatRoomCdnInfo;
    }

    public static void a(ChatRoomMessageImpl chatRoomMessageImpl, int i) {
        StringBuilder sb = new StringBuilder();
        if (i == 200) {
            sb.append("send room message ack: ");
        } else {
            sb.append("send room message failed: ");
            sb.append("[");
            sb.append("response code = ");
            sb.append(i);
            sb.append("] ");
        }
        sb.append("[");
        sb.append(chatRoomMessageImpl.getSessionId());
        sb.append(" ");
        sb.append(chatRoomMessageImpl.getUuid());
        sb.append("]");
        com.netease.nimlib.log.b.g(sb.toString());
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length == 0 || bArr2.length == 0) {
            return bArr;
        }
        try {
            String a = com.netease.nimlib.o.g.a("QUVTL0VDQi9QS0NTNVBhZGRpbmc=");
            com.netease.nimlib.log.b.I("ChatRoomHelper#decryptByAES transformation = " + a);
            Cipher cipher = Cipher.getInstance(a);
            cipher.init(2, new SecretKeySpec(bArr2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM));
            return cipher.doFinal(bArr);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
