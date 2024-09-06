package com.netease.nimlib.session;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.RecentContactContentSource;
import com.netease.nimlib.sdk.friend.model.AddFriendNotify;
import com.netease.nimlib.sdk.friend.model.TeamInviteNotify;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.NIMAntiSpamOption;
import com.netease.nimlib.sdk.msg.model.RecentSession;
import com.netease.nimlib.sdk.msg.model.RevokeMsgNotification;
import com.netease.nimlib.sdk.msg.model.ShowNotificationWhenRevokeFilter;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.netease.nimlib.sdk.team.model.IMMessageFilter;
import com.netease.nimlib.team.TeamDBHelper;
import com.netease.nimlib.user.UserInfoDBHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: MsgHelper.java */
/* loaded from: classes.dex */
public class j {
    private static IMMessageFilter a;
    private static ShowNotificationWhenRevokeFilter b;

    public static q a(IMMessageImpl iMMessageImpl) {
        return a(iMMessageImpl, iMMessageImpl.getStatus(), 0);
    }

    public static q a(IMMessageImpl iMMessageImpl, int i) {
        return a(iMMessageImpl, i, false);
    }

    public static q a(IMMessageImpl iMMessageImpl, int i, boolean z) {
        return a(iMMessageImpl, iMMessageImpl.getStatus(), i, z);
    }

    public static q b(IMMessageImpl iMMessageImpl) {
        return a(iMMessageImpl, MsgStatusEnum.fail, 0);
    }

    public static q c(IMMessageImpl iMMessageImpl) {
        return a(iMMessageImpl, a((IMMessage) iMMessageImpl, true) ? 1 : 0, true);
    }

    public static q d(IMMessageImpl iMMessageImpl) {
        return e(iMMessageImpl);
    }

    public static void a(IMMessage iMMessage) {
        q queryRecentContact;
        if (!com.netease.nimlib.c.i().shouldConsiderRevokedMessageUnreadCount || (queryRecentContact = MsgDBHelper.queryRecentContact(iMMessage.getSessionId(), iMMessage.getSessionType())) == null) {
            return;
        }
        queryRecentContact.a(Math.max(queryRecentContact.getUnreadCount() - 1, 0));
        MsgDBHelper.saveRecent(queryRecentContact);
        com.netease.nimlib.i.b.a(queryRecentContact);
    }

    public static void b(IMMessage iMMessage) {
        q d;
        String sessionId = iMMessage.getSessionId();
        SessionTypeEnum sessionType = iMMessage.getSessionType();
        q queryRecentContact = MsgDBHelper.queryRecentContact(sessionId, sessionType);
        if (queryRecentContact == null || !TextUtils.equals(queryRecentContact.getRecentMessageId(), iMMessage.getUuid())) {
            return;
        }
        IMMessageImpl queryLatestMessage = MsgDBHelper.queryLatestMessage(sessionId, sessionType.getValue());
        if (queryLatestMessage == null) {
            boolean z = com.netease.nimlib.c.i().shouldConsiderRevokedMessageUnreadCount;
            int unreadCount = queryRecentContact.getUnreadCount();
            com.netease.nimlib.log.b.v(String.format("unreadCount is %s when last message is deleted (option:%s)", Integer.valueOf(unreadCount), Boolean.valueOf(z)));
            d = a(sessionId, sessionType, queryRecentContact);
            if (!z) {
                d.a(unreadCount);
                MsgDBHelper.saveRecent(d);
            }
        } else {
            d = d(queryLatestMessage);
            a(d);
        }
        com.netease.nimlib.i.b.a(d);
    }

    public static void a(List<IMMessage> list) {
        IMMessage iMMessage;
        String sessionId;
        SessionTypeEnum sessionType;
        q queryRecentContact;
        q d;
        if (com.netease.nimlib.o.f.c((Collection) list) || (queryRecentContact = MsgDBHelper.queryRecentContact((sessionId = (iMMessage = list.get(0)).getSessionId()), (sessionType = iMMessage.getSessionType()))) == null || !com.netease.nimlib.o.f.b(list, new f.a() { // from class: com.netease.nimlib.session.-$$Lambda$j$lBMYHpvETIuSOhF-8cv1Z5MUu8E
            private final /* synthetic */ String f$0;

            public /* synthetic */ $$Lambda$j$lBMYHpvETIuSOhF8cv1Z5MUu8E(String str) {
                r1 = str;
            }

            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Boolean a2;
                a2 = j.a(r1, (IMMessage) obj);
                return a2;
            }
        })) {
            return;
        }
        IMMessageImpl queryLatestMessage = MsgDBHelper.queryLatestMessage(sessionId, sessionType.getValue());
        if (queryLatestMessage == null) {
            d = a(sessionId, sessionType, queryRecentContact);
        } else {
            d = d(queryLatestMessage);
            a(d);
        }
        com.netease.nimlib.i.b.a(d);
    }

    public static /* synthetic */ Boolean a(String str, IMMessage iMMessage) {
        return Boolean.valueOf(TextUtils.equals(str, iMMessage.getUuid()));
    }

    public static void a(q qVar) {
        if (qVar != null && qVar.getMsgStatus() == MsgStatusEnum.fail && d.a().c(qVar.getRecentMessageId())) {
            qVar.setMsgStatus(MsgStatusEnum.sending);
        }
    }

    public static q a(String str, SessionTypeEnum sessionTypeEnum, q qVar) {
        q qVar2 = new q();
        qVar2.a(str);
        qVar2.c("");
        qVar2.d("");
        qVar2.a(sessionTypeEnum);
        qVar2.setMsgStatus(MsgStatusEnum.success);
        qVar2.e("");
        qVar2.a(0);
        qVar2.a(qVar.getTime());
        qVar2.setTag(qVar.getTag());
        qVar2.f(qVar.c());
        MsgDBHelper.saveRecent(qVar2);
        return qVar2;
    }

    public static q e(IMMessageImpl iMMessageImpl) {
        return a(iMMessageImpl, iMMessageImpl.getStatus(), iMMessageImpl.getTime());
    }

    public static q a(IMMessageImpl iMMessageImpl, MsgStatusEnum msgStatusEnum, long j) {
        String sessionId = iMMessageImpl.getSessionId();
        if (TextUtils.isEmpty(sessionId)) {
            return null;
        }
        q queryRecentContact = MsgDBHelper.queryRecentContact(sessionId, iMMessageImpl.getSessionType());
        q b2 = b(iMMessageImpl, msgStatusEnum, j);
        if (queryRecentContact != null) {
            b2.a(queryRecentContact.getUnreadCount());
            b2.setTag(queryRecentContact.getTag());
            b2.f(queryRecentContact.c());
        } else {
            b2.a(0);
        }
        MsgDBHelper.saveRecent(b2);
        return b2;
    }

    public static q f(IMMessageImpl iMMessageImpl) {
        String sessionId = iMMessageImpl.getSessionId();
        if (TextUtils.isEmpty(sessionId)) {
            com.netease.nimlib.log.b.f("MsgHelper", "updateLocalRecentContact uid is null");
            return null;
        }
        q queryRecentContact = MsgDBHelper.queryRecentContact(sessionId, iMMessageImpl.getSessionType());
        q g = g(iMMessageImpl);
        g.setMsgStatus(iMMessageImpl.getStatus());
        if (queryRecentContact != null) {
            if (iMMessageImpl.getTime() < queryRecentContact.getTime()) {
                com.netease.nimlib.log.b.f("MsgHelper", "updateLocalRecentContact , too old , msg time = " + iMMessageImpl.getTime() + " , old time = " + queryRecentContact.getTime());
                return null;
            }
            g.a(queryRecentContact.getUnreadCount());
            g.setTag(queryRecentContact.getTag());
            g.f(queryRecentContact.c());
        }
        MsgDBHelper.saveRecent(g);
        return g;
    }

    private static q a(IMMessageImpl iMMessageImpl, MsgStatusEnum msgStatusEnum, int i) {
        return a(iMMessageImpl, msgStatusEnum, i, false);
    }

    private static q a(IMMessageImpl iMMessageImpl, MsgStatusEnum msgStatusEnum, int i, boolean z) {
        if (msgStatusEnum == MsgStatusEnum.success && !z) {
            com.netease.nimlib.biz.l.o(iMMessageImpl.getTime());
        }
        String sessionId = iMMessageImpl.getSessionId();
        if (TextUtils.isEmpty(sessionId)) {
            com.netease.nimlib.log.b.f("MsgHelper", "updateRecentDatabase uid is null");
            return null;
        }
        SessionTypeEnum sessionType = iMMessageImpl.getSessionType();
        q queryRecentContact = MsgDBHelper.queryRecentContact(sessionId, sessionType);
        boolean z2 = queryRecentContact != null && iMMessageImpl.getTime() < queryRecentContact.getTime();
        q g = z2 ? queryRecentContact : g(iMMessageImpl);
        if (!z2) {
            g.setMsgStatus(msgStatusEnum);
        }
        if (queryRecentContact != null) {
            g.a(i + queryRecentContact.getUnreadCount());
            g.setTag(queryRecentContact.getTag());
            g.f(queryRecentContact.c());
        } else {
            g.a(v.b(sessionId, sessionType));
        }
        MsgDBHelper.saveRecent(g);
        return g;
    }

    public static void a(String str, SessionTypeEnum sessionTypeEnum) {
        q queryRecentContact = MsgDBHelper.queryRecentContact(str, sessionTypeEnum);
        if (queryRecentContact != null) {
            IMMessageImpl queryLatestMessage = MsgDBHelper.queryLatestMessage(str, sessionTypeEnum.getValue());
            if (queryLatestMessage == null) {
                com.netease.nimlib.i.b.a(a(str, sessionTypeEnum, queryRecentContact));
                return;
            }
            if (TextUtils.equals(queryRecentContact.getRecentMessageId(), queryLatestMessage.getUuid())) {
                return;
            }
            q g = g(queryLatestMessage);
            g.a(queryRecentContact.getUnreadCount());
            g.setTag(queryRecentContact.getTag());
            g.f(queryRecentContact.c());
            MsgDBHelper.saveRecent(g);
            com.netease.nimlib.i.b.a(g);
        }
    }

    public static q g(IMMessageImpl iMMessageImpl) {
        return b(iMMessageImpl, iMMessageImpl.getStatus(), iMMessageImpl.getTime());
    }

    public static q b(IMMessageImpl iMMessageImpl, MsgStatusEnum msgStatusEnum, long j) {
        q qVar = new q();
        qVar.a(iMMessageImpl.getSessionId());
        qVar.b(iMMessageImpl.getFromAccount());
        qVar.c(iMMessageImpl.getUuid());
        qVar.d(h(iMMessageImpl));
        qVar.a(iMMessageImpl.getSessionType());
        qVar.a(j);
        qVar.setMsgStatus(msgStatusEnum);
        qVar.b(iMMessageImpl.getMsgTypeInner());
        qVar.e(iMMessageImpl.getAttachStr(false));
        return qVar;
    }

    public static String h(IMMessageImpl iMMessageImpl) {
        if (com.netease.nimlib.c.i().recentContactContentSource == RecentContactContentSource.MessageTypeTipPreferred) {
            String sendMessageTip = iMMessageImpl.getMsgType().getSendMessageTip();
            if (!TextUtils.isEmpty(sendMessageTip)) {
                return "[" + sendMessageTip + "]";
            }
            return iMMessageImpl.getContent();
        }
        if (com.netease.nimlib.c.i().recentContactContentSource == RecentContactContentSource.MessageContentPreferred) {
            String content = iMMessageImpl.getContent();
            if (!TextUtils.isEmpty(content)) {
                return content;
            }
            String sendMessageTip2 = iMMessageImpl.getMsgType().getSendMessageTip();
            return !TextUtils.isEmpty(sendMessageTip2) ? sendMessageTip2 : "";
        }
        if (com.netease.nimlib.c.i().recentContactContentSource == RecentContactContentSource.MessageTypeTip) {
            return iMMessageImpl.getMsgType().getSendMessageTip();
        }
        return com.netease.nimlib.c.i().recentContactContentSource == RecentContactContentSource.MessageContent ? iMMessageImpl.getContent() : "";
    }

    public static String a(String str, int i) {
        return i + "_" + str;
    }

    public static MsgTypeEnum a(int i) {
        if (i != 100) {
            switch (i) {
                case 0:
                    return MsgTypeEnum.text;
                case 1:
                    return MsgTypeEnum.image;
                case 2:
                    return MsgTypeEnum.audio;
                case 3:
                    return MsgTypeEnum.video;
                case 4:
                    return MsgTypeEnum.location;
                case 5:
                    return MsgTypeEnum.notification;
                case 6:
                    return MsgTypeEnum.file;
                case 7:
                    return MsgTypeEnum.avchat;
                default:
                    switch (i) {
                        case 10:
                            return MsgTypeEnum.tip;
                        case 11:
                            return MsgTypeEnum.robot;
                        case 12:
                            return MsgTypeEnum.nrtc_netcall;
                        default:
                            return MsgTypeEnum.undef;
                    }
            }
        }
        return MsgTypeEnum.custom;
    }

    public static void a(ArrayList<SystemMessage> arrayList) {
        Iterator<SystemMessage> it = arrayList.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    public static void a(SystemMessage systemMessage) {
        if (systemMessage.getType() == SystemMessageType.AddFriend) {
            String attach = systemMessage.getAttach();
            if (TextUtils.isEmpty(attach)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(attach);
                int i = jSONObject.getInt("vt");
                JSONObject jSONObject2 = jSONObject.getJSONObject("serverex");
                String str = null;
                if (jSONObject2 != null && jSONObject2.getInt("0") == 1) {
                    str = jSONObject2.getString("1");
                }
                systemMessage.setAttachObject(new AddFriendNotify(systemMessage.getFromAccount(), AddFriendNotify.Event.eventOfValue((byte) i), systemMessage.getContent(), str));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void b(ArrayList<SystemMessage> arrayList) {
        Iterator<SystemMessage> it = arrayList.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    public static void b(SystemMessage systemMessage) {
        if (systemMessage.getType() == SystemMessageType.TeamInvite) {
            String attach = systemMessage.getAttach();
            if (TextUtils.isEmpty(attach)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(attach);
                systemMessage.setAttachObject(new TeamInviteNotify(com.netease.nimlib.team.c.b(attach), c(jSONObject.has("attach") ? jSONObject.getString("attach") : null)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(com.netease.nimlib.push.packet.b.c cVar) {
        if (com.netease.nimlib.c.x() && cVar.f(14)) {
            long e = cVar.e(14);
            String c = cVar.c(2);
            if (e > UserInfoDBHelper.getUpdateTimeTag(c)) {
                com.netease.nimlib.user.c.a(c);
            }
        }
    }

    public static void b(List<com.netease.nimlib.push.packet.b.c> list) {
        String c;
        if (com.netease.nimlib.c.x()) {
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList(list.size());
            for (com.netease.nimlib.push.packet.b.c cVar : list) {
                if (cVar.f(14) && (c = cVar.c(2)) != null) {
                    hashMap.put(c, Long.valueOf(cVar.e(14)));
                }
            }
            Map<String, Long> updateTimeTags = UserInfoDBHelper.getUpdateTimeTags(hashMap.keySet());
            for (Map.Entry entry : hashMap.entrySet()) {
                String str = (String) entry.getKey();
                Long l = updateTimeTags.get(str);
                if (l == null) {
                    l = 0L;
                }
                if (((Long) entry.getValue()).longValue() > l.longValue()) {
                    arrayList.add(str);
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            com.netease.nimlib.user.c.a(arrayList);
        }
    }

    public static void i(IMMessageImpl iMMessageImpl) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(iMMessageImpl);
        c((List<IMMessageImpl>) arrayList);
    }

    public static void c(List<IMMessageImpl> list) {
        JSONArray h;
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (IMMessageImpl iMMessageImpl : list) {
            if (iMMessageImpl.getSessionType() == SessionTypeEnum.Team || iMMessageImpl.getSessionType() == SessionTypeEnum.SUPER_TEAM) {
                if (iMMessageImpl.getMsgType() == MsgTypeEnum.notification) {
                    String attachStr = iMMessageImpl.getAttachStr(false);
                    if (!TextUtils.isEmpty(attachStr)) {
                        try {
                            JSONObject jSONObject = new JSONObject(attachStr).getJSONObject("data");
                            if (jSONObject.has("uinfos") && (h = com.netease.nimlib.o.k.h(jSONObject, "uinfos")) != null) {
                                for (int i = 0; i < h.length(); i++) {
                                    com.netease.nimlib.user.b a2 = com.netease.nimlib.user.b.a(new JSONObject(com.netease.nimlib.o.k.b(h, i)));
                                    if (a2 != null && !a2.getAccount().equals(com.netease.nimlib.c.n()) && !hashSet.contains(a2.getAccount()) && a2.b() > UserInfoDBHelper.getUpdateTimeTag(a2.getAccount())) {
                                        hashSet.add(a2.getAccount());
                                        arrayList.add(a2);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        com.netease.nimlib.user.c.b(arrayList);
    }

    public static void d(List<IMMessageImpl> list) {
        JSONArray h;
        JSONArray h2;
        com.netease.nimlib.log.b.L("updateTeamNotificationInfo messages size = " + list.size());
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (IMMessageImpl iMMessageImpl : list) {
            if (iMMessageImpl.getSessionType() == SessionTypeEnum.Team && iMMessageImpl.getMsgType() == MsgTypeEnum.notification) {
                String attachStr = iMMessageImpl.getAttachStr(false);
                if (!TextUtils.isEmpty(attachStr)) {
                    try {
                        JSONObject jSONObject = new JSONObject(attachStr).getJSONObject("data");
                        com.netease.nimlib.log.b.a("updateTeamNotificationInfo uuid = %s,data = %s", iMMessageImpl.getUuid(), jSONObject);
                        if (jSONObject.has("uinfos") && (h2 = com.netease.nimlib.o.k.h(jSONObject, "uinfos")) != null) {
                            for (int i = 0; i < h2.length(); i++) {
                                String b2 = com.netease.nimlib.o.k.b(h2, i);
                                if (!TextUtils.isEmpty(b2)) {
                                    com.netease.nimlib.user.b a2 = com.netease.nimlib.user.b.a(new JSONObject(b2));
                                    String account = a2 != null ? a2.getAccount() : null;
                                    if (account != null && !account.equals(com.netease.nimlib.c.n()) && !hashSet.contains(account)) {
                                        hashSet.add(account);
                                        hashMap.put(account, a2);
                                    }
                                }
                            }
                        }
                        if (jSONObject.has("tinfo")) {
                            com.netease.nimlib.team.d a3 = com.netease.nimlib.team.c.a(com.netease.nimlib.o.k.g(jSONObject, "tinfo"));
                            String id = a3 != null ? a3.getId() : null;
                            if (id != null && !hashSet2.contains(id)) {
                                hashSet2.add(id);
                                hashMap2.put(id, a3);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (iMMessageImpl.getSessionType() == SessionTypeEnum.SUPER_TEAM && iMMessageImpl.getMsgType() == MsgTypeEnum.notification) {
                String attachStr2 = iMMessageImpl.getAttachStr(false);
                if (!TextUtils.isEmpty(attachStr2)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(attachStr2).getJSONObject("data");
                        com.netease.nimlib.log.b.a("updateSuperTeamNotificationInfo uuid = %s,data = %s", iMMessageImpl.getUuid(), jSONObject2);
                        if (jSONObject2.has("uinfos") && (h = com.netease.nimlib.o.k.h(jSONObject2, "uinfos")) != null) {
                            for (int i2 = 0; i2 < h.length(); i2++) {
                                String b3 = com.netease.nimlib.o.k.b(h, i2);
                                if (!TextUtils.isEmpty(b3)) {
                                    com.netease.nimlib.user.b a4 = com.netease.nimlib.user.b.a(new JSONObject(b3));
                                    String account2 = a4 != null ? a4.getAccount() : null;
                                    if (account2 != null && !account2.equals(com.netease.nimlib.c.n()) && !hashSet.contains(account2)) {
                                        hashSet.add(account2);
                                        hashMap.put(account2, a4);
                                    }
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Map<String, Long> updateTimeTags = UserInfoDBHelper.getUpdateTimeTags(hashSet);
        for (Map.Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            com.netease.nimlib.user.b bVar = (com.netease.nimlib.user.b) entry.getValue();
            if (bVar != null) {
                Long l = updateTimeTags.get(str);
                if (l == null) {
                    l = 0L;
                }
                if (bVar.b() > l.longValue()) {
                    arrayList.add(bVar);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            com.netease.nimlib.log.b.L("updateTeamNotificationInfo saveUserInfo size = " + arrayList.size());
            com.netease.nimlib.user.c.b(arrayList);
        }
        Set<String> existTeamIdById = TeamDBHelper.getExistTeamIdById(hashSet2);
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (!existTeamIdById.contains((String) entry2.getKey())) {
                arrayList2.add(entry2.getValue());
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        com.netease.nimlib.log.b.L("updateTeamNotificationInfo saveTeams size = " + arrayList2.size());
        TeamDBHelper.saveTeams(arrayList2);
    }

    public static String a(NIMAntiSpamOption nIMAntiSpamOption) {
        if (nIMAntiSpamOption == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("k_ye", Boolean.valueOf(nIMAntiSpamOption.enable));
        hashMap.put("k_asc", nIMAntiSpamOption.content);
        hashMap.put("k_as_id", nIMAntiSpamOption.antiSpamConfigId);
        return a(hashMap);
    }

    public static NIMAntiSpamOption a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        NIMAntiSpamOption nIMAntiSpamOption = new NIMAntiSpamOption();
        Map<String, Object> c = c(str);
        if (c != null && !c.isEmpty()) {
            if (c.containsKey("k_ye")) {
                nIMAntiSpamOption.enable = ((Boolean) c.get("k_ye")).booleanValue();
            }
            if (c.containsKey("k_asc")) {
                nIMAntiSpamOption.content = (String) c.get("k_asc");
            }
            if (c.containsKey("k_as_id")) {
                nIMAntiSpamOption.antiSpamConfigId = (String) c.get("k_as_id");
            }
        }
        return nIMAntiSpamOption;
    }

    public static String e(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        return jSONArray.toString();
    }

    public static List<String> b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
        } catch (JSONException e) {
            com.netease.nimlib.log.b.f("MsgHelper", "getListFromJsonString exception =" + e.getMessage());
        }
        return arrayList;
    }

    public static String a(Map map) {
        if (map != null && !map.isEmpty()) {
            try {
                return b(map).toString();
            } catch (Exception e) {
                com.netease.nimlib.log.b.f("MsgHelper", "getJsonStringFromMap exception =" + e.getMessage());
            }
        }
        return null;
    }

    private static JSONObject b(Map map) throws JSONException {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : map.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            Object value = entry.getValue();
            if (value instanceof List) {
                jSONObject.put(valueOf, i((List) value));
            } else if (value instanceof Map) {
                jSONObject.put(valueOf, b((Map) value));
            } else if (value instanceof JSONObject) {
                jSONObject.put(valueOf, b(a((JSONObject) value)));
            } else if (value instanceof JSONArray) {
                jSONObject.put(valueOf, i(a((JSONArray) value)));
            } else {
                jSONObject.put(valueOf, value);
            }
        }
        return jSONObject;
    }

    private static JSONArray i(List list) throws JSONException {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object obj : list) {
            if (obj instanceof List) {
                jSONArray.put(i((List) obj));
            } else if (obj instanceof Map) {
                jSONArray.put(b((Map) obj));
            } else {
                jSONArray.put(obj);
            }
        }
        return jSONArray;
    }

    public static Map<String, Object> c(String str) {
        Map<String, Object> hashMap;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            try {
                hashMap = a(com.netease.nimlib.o.k.a(str));
            } catch (JSONException e) {
                com.netease.nimlib.log.b.f("MsgHelper", "getMapFromJsonString exception =" + e.getMessage());
                hashMap = new HashMap<>(1);
            }
            if (hashMap == null) {
                hashMap = new HashMap<>(1);
                hashMap.put(RecentSession.KEY_EXT, str);
            }
            return hashMap;
        } catch (Throwable th) {
            new HashMap(1).put(RecentSession.KEY_EXT, str);
            throw th;
        }
    }

    private static Map<String, Object> a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                hashMap.put(next, a((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                hashMap.put(next, a((JSONObject) obj));
            } else {
                hashMap.put(next, obj);
            }
        }
        return hashMap;
    }

    private static List a(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                arrayList.add(a((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                arrayList.add(a((JSONObject) obj));
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static void f(List<IMMessageImpl> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<IMMessageImpl> it = list.iterator();
        while (it.hasNext()) {
            com.netease.nimlib.biz.l.o(it.next().getTime());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003e, code lost:
    
        if (r1.equals("team") == false) goto L62;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.util.Pair<com.netease.nimlib.sdk.msg.constant.SessionTypeEnum, java.lang.String> d(java.lang.String r8) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            java.lang.String r1 = ""
            r2 = 0
            if (r0 == 0) goto Lf
            android.util.Pair r8 = new android.util.Pair
            r8.<init>(r2, r1)
            return r8
        Lf:
            java.lang.String r0 = "\\|"
            java.lang.String[] r8 = r8.split(r0)
            int r0 = r8.length
            r3 = 2
            if (r0 >= r3) goto L1f
            android.util.Pair r8 = new android.util.Pair
            r8.<init>(r2, r1)
            return r8
        L1f:
            r0 = 0
            r1 = r8[r0]
            r4 = -1
            int r5 = r1.hashCode()
            r6 = -1718157151(0xffffffff999700a1, float:-1.5613288E-23)
            r7 = 1
            if (r5 == r6) goto L4b
            r6 = 109294(0x1aaee, float:1.53154E-40)
            if (r5 == r6) goto L41
            r6 = 3555933(0x36425d, float:4.982923E-39)
            if (r5 == r6) goto L38
            goto L55
        L38:
            java.lang.String r5 = "team"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L55
            goto L56
        L41:
            java.lang.String r0 = "p2p"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L55
            r0 = 2
            goto L56
        L4b:
            java.lang.String r0 = "super_team"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L55
            r0 = 1
            goto L56
        L55:
            r0 = -1
        L56:
            if (r0 == 0) goto L63
            if (r0 == r7) goto L60
            if (r0 == r3) goto L5d
            goto L65
        L5d:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r2 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.P2P
            goto L65
        L60:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r2 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.SUPER_TEAM
            goto L65
        L63:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r2 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.Team
        L65:
            android.util.Pair r0 = new android.util.Pair
            r8 = r8[r7]
            r0.<init>(r2, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.session.j.d(java.lang.String):android.util.Pair");
    }

    public static String a(SessionTypeEnum sessionTypeEnum, String str) {
        String str2;
        if (sessionTypeEnum == null || TextUtils.isEmpty(str)) {
            return "";
        }
        int i = AnonymousClass1.a[sessionTypeEnum.ordinal()];
        if (i == 1) {
            str2 = "p2p";
        } else if (i == 2) {
            str2 = "team";
        } else {
            if (i != 3) {
                return "";
            }
            str2 = "super_team";
        }
        return str2 + "|" + str;
    }

    /* compiled from: MsgHelper.java */
    /* renamed from: com.netease.nimlib.session.j$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[SessionTypeEnum.values().length];
            a = iArr;
            try {
                iArr[SessionTypeEnum.P2P.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[SessionTypeEnum.Team.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[SessionTypeEnum.SUPER_TEAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* compiled from: MsgHelper.java */
    /* loaded from: classes.dex */
    public static class a {
        public List<IMMessageImpl> a;
        public List<IMMessageImpl> b;

        public a(List<IMMessageImpl> list) {
            this(list, null);
        }

        public a(List<IMMessageImpl> list, List<IMMessageImpl> list2) {
            this.b = list;
            this.a = list2;
        }

        public boolean a() {
            List<IMMessageImpl> list = this.b;
            return (list == null || list.isEmpty()) ? false : true;
        }

        public boolean b() {
            List<IMMessageImpl> list = this.a;
            return (list == null || list.isEmpty()) ? false : true;
        }
    }

    public static a a(List<IMMessageImpl> list, Set<String> set) {
        if (set == null || set.isEmpty()) {
            return new a(list);
        }
        a aVar = new a(new ArrayList(), new ArrayList());
        for (IMMessageImpl iMMessageImpl : list) {
            if (set.contains(iMMessageImpl.getUuid())) {
                aVar.a.add(iMMessageImpl);
            } else {
                aVar.b.add(iMMessageImpl);
            }
        }
        return aVar;
    }

    public static synchronized void a(IMMessageFilter iMMessageFilter) {
        synchronized (j.class) {
            a = iMMessageFilter;
            if (iMMessageFilter != null) {
                com.netease.nimlib.log.b.N("register IMMessageFilter");
            } else {
                com.netease.nimlib.log.b.N("unregister IMMessageFilter");
            }
        }
    }

    public static Set<String> g(List<IMMessageImpl> list) {
        if (a == null || list == null || list.isEmpty()) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (IMMessageImpl iMMessageImpl : list) {
            if (a.shouldIgnore(iMMessageImpl)) {
                hashSet.add(iMMessageImpl.getUuid());
                com.netease.nimlib.log.b.N("IMMessageFilter ignore received message, uuid=" + iMMessageImpl.getUuid());
            }
        }
        return hashSet;
    }

    public static boolean c(IMMessage iMMessage) {
        IMMessageFilter iMMessageFilter = a;
        if (iMMessageFilter == null || iMMessage == null) {
            return false;
        }
        return iMMessageFilter.shouldIgnore(iMMessage);
    }

    public static synchronized void a(ShowNotificationWhenRevokeFilter showNotificationWhenRevokeFilter) {
        synchronized (j.class) {
            b = showNotificationWhenRevokeFilter;
            if (showNotificationWhenRevokeFilter != null) {
                com.netease.nimlib.log.b.N("register ShowNotificationWhenRevokeFilter");
            } else {
                com.netease.nimlib.log.b.N("unregister ShowNotificationWhenRevokeFilter");
            }
        }
    }

    public static boolean a(RevokeMsgNotification revokeMsgNotification) {
        ShowNotificationWhenRevokeFilter showNotificationWhenRevokeFilter = b;
        if (showNotificationWhenRevokeFilter == null) {
            return true;
        }
        return showNotificationWhenRevokeFilter.showNotification(revokeMsgNotification);
    }

    public static boolean c(ArrayList<IMMessageImpl> arrayList) {
        Set<String> g = g(arrayList);
        return g == null || g.isEmpty();
    }

    public static boolean a(IMMessage iMMessage, boolean z) {
        SessionTypeEnum sessionType;
        if (iMMessage != null) {
            String sessionId = iMMessage.getSessionId();
            if (!TextUtils.isEmpty(sessionId) && (sessionType = iMMessage.getSessionType()) != null && iMMessage.getDirect() != MsgDirectionEnum.Out) {
                boolean z2 = iMMessage.getConfig() == null || iMMessage.getConfig().enableUnreadCount;
                if (MsgTypeEnum.notification == iMMessage.getMsgType()) {
                    z2 &= com.netease.nimlib.c.i().teamNotificationMessageMarkUnread;
                }
                if (z2 && z && com.netease.nimlib.c.i().sessionReadAck) {
                    return iMMessage.getTime() > MsgDBHelper.querySessionReadTimeTag(sessionId, sessionType);
                }
                return z2;
            }
        }
        return false;
    }

    public static boolean a(IMMessage iMMessage, boolean z, long j) {
        if (iMMessage == null || TextUtils.isEmpty(iMMessage.getSessionId()) || iMMessage.getSessionType() == null || iMMessage.getDirect() == MsgDirectionEnum.Out) {
            return false;
        }
        boolean z2 = iMMessage.getConfig() == null || iMMessage.getConfig().enableUnreadCount;
        if (MsgTypeEnum.notification == iMMessage.getMsgType()) {
            z2 &= com.netease.nimlib.c.i().teamNotificationMessageMarkUnread;
        }
        if (z2 && z && com.netease.nimlib.c.i().sessionReadAck) {
            return iMMessage.getTime() > j;
        }
        return z2;
    }

    public static List<IMMessageImpl> a(List<IMMessageImpl> list, boolean z) {
        LinkedList linkedList = new LinkedList();
        if (list != null && !list.isEmpty()) {
            HashMap hashMap = new HashMap();
            long E = com.netease.nimlib.biz.l.E();
            if (!z) {
                for (IMMessageImpl iMMessageImpl : list) {
                    long time = iMMessageImpl.getTime();
                    if (E < time) {
                        SessionTypeEnum sessionType = iMMessageImpl.getSessionType();
                        String sessionId = iMMessageImpl.getSessionId();
                        if (time > 0 && sessionType != null && !TextUtils.isEmpty(sessionId)) {
                            String str = sessionType.getValue() + "_" + sessionId;
                            Long l = (Long) hashMap.get(str);
                            if (l == null) {
                                l = Long.valueOf(MsgDBHelper.getClearSessionTime(sessionId, sessionType));
                                hashMap.remove(str);
                                hashMap.put(str, l);
                            }
                            if (l.longValue() >= time) {
                            }
                        }
                        linkedList.add(iMMessageImpl);
                    }
                }
            } else {
                linkedList.addAll(list);
            }
            Set<String> hasDeleteTag = MsgDBHelper.hasDeleteTag(linkedList);
            if (hasDeleteTag != null && !hasDeleteTag.isEmpty()) {
                int i = 0;
                int size = hasDeleteTag.size();
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    IMMessageImpl iMMessageImpl2 = (IMMessageImpl) it.next();
                    if (iMMessageImpl2 != null && hasDeleteTag.contains(iMMessageImpl2.getUuid())) {
                        it.remove();
                        i++;
                        if (i >= size) {
                            break;
                        }
                    }
                }
            }
        }
        return linkedList;
    }

    public static void b(List<IMMessage> list, boolean z) {
        HashMap a2 = com.netease.nimlib.o.f.a((Collection) list, true, (f.a) $$Lambda$j$t80WQgtEoxzhqeFcyHh1KmZ6gw.INSTANCE);
        ArrayList arrayList = (ArrayList) a2.get(false);
        if (com.netease.nimlib.o.f.d(arrayList)) {
            MsgDBHelper.deleteMessage(arrayList, !z);
        }
        List list2 = (List) a2.get(true);
        for (Map.Entry<String, Integer> entry : v.a((List<IMMessage>) list2).entrySet()) {
            Pair<String, SessionTypeEnum> a3 = v.a(entry.getKey());
            if (a3 != null) {
                String str = (String) a3.first;
                SessionTypeEnum sessionTypeEnum = (SessionTypeEnum) a3.second;
                if (!TextUtils.isEmpty(str) && sessionTypeEnum != null) {
                    q queryRecentContact = MsgDBHelper.queryRecentContact(str, sessionTypeEnum);
                    if (queryRecentContact == null) {
                        return;
                    }
                    Integer value = entry.getValue();
                    queryRecentContact.a(Math.max(0, queryRecentContact.getUnreadCount() - Integer.valueOf((value == null || value.intValue() < 0) ? 0 : value.intValue()).intValue()));
                    MsgDBHelper.saveRecent(queryRecentContact);
                    MsgDBHelper.deleteMessage((List<? extends IMMessage>) list2, !z);
                }
            }
        }
        for (Map.Entry<String, ArrayList<IMMessage>> entry2 : a((Collection<IMMessage>) list).entrySet()) {
            Pair<String, SessionTypeEnum> a4 = v.a(entry2.getKey());
            if (a4 != null && !com.netease.nimlib.o.w.a((CharSequence) a4.first) && a4.second != null) {
                a((List<IMMessage>) com.netease.nimlib.o.f.d(entry2.getValue(), new f.a() { // from class: com.netease.nimlib.session.-$$Lambda$j$A_NPEmBIpjNrGq70fog-dY9Hfl4
                    private final /* synthetic */ Pair f$0;

                    public /* synthetic */ $$Lambda$j$A_NPEmBIpjNrGq70fogdY9Hfl4(Pair a42) {
                        r1 = a42;
                    }

                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        Boolean a5;
                        a5 = j.a(r1, (IMMessage) obj);
                        return a5;
                    }
                }));
            }
        }
    }

    public static /* synthetic */ Boolean e(IMMessage iMMessage) {
        if (iMMessage == null) {
            return null;
        }
        return Boolean.valueOf(a(iMMessage, true));
    }

    public static /* synthetic */ Boolean a(Pair pair, IMMessage iMMessage) {
        return Boolean.valueOf(iMMessage != null && ((String) pair.first).equals(iMMessage.getSessionId()) && pair.second == iMMessage.getSessionType());
    }

    public static Map<String, ArrayList<IMMessage>> a(Collection<IMMessage> collection) {
        return com.netease.nimlib.o.f.a((Collection) collection, true, (f.a) $$Lambda$j$v6SpIZbY3uxwmOqJ3XoS23mA2Q.INSTANCE);
    }

    public static /* synthetic */ String d(IMMessage iMMessage) {
        if (iMMessage == null) {
            return null;
        }
        String sessionId = iMMessage.getSessionId();
        SessionTypeEnum sessionType = iMMessage.getSessionType();
        if (com.netease.nimlib.o.w.a((CharSequence) sessionId) || sessionType == null) {
            return null;
        }
        return v.e(sessionId, sessionType);
    }

    public static void b(IMMessageImpl iMMessageImpl, int i) {
        StringBuilder sb = new StringBuilder();
        if (i == 200) {
            sb.append("send message ack: ");
        } else {
            sb.append("send message failed: ");
            sb.append("[");
            sb.append("response code = ");
            sb.append(i);
            sb.append("] ");
        }
        sb.append("[");
        sb.append(iMMessageImpl.getSessionType());
        sb.append(" ");
        sb.append(iMMessageImpl.getSessionId());
        sb.append(" ");
        sb.append(iMMessageImpl.getUuid());
        sb.append(" ");
        sb.append(iMMessageImpl.getCallbackExtension());
        sb.append("]");
        com.netease.nimlib.log.b.N(sb.toString());
    }

    public static void h(List<IMMessageImpl> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("notify received messages: ");
        sb.append("[");
        sb.append(list.get(0).getSessionType());
        sb.append(" ");
        sb.append(list.get(0).getSessionId());
        sb.append("]");
        sb.append(" [");
        for (IMMessageImpl iMMessageImpl : list) {
            sb.append(iMMessageImpl.getUuid());
            sb.append(", ");
            sb.append(iMMessageImpl.getCallbackExtension());
            sb.append(" ");
        }
        sb.append("]");
        com.netease.nimlib.log.b.N(sb.toString());
    }

    public static String a(FileAttachment fileAttachment, String str) {
        if (str == null) {
            return null;
        }
        if (com.netease.nimlib.biz.b.e.d().a()) {
            return str;
        }
        if (!(fileAttachment instanceof ImageAttachment)) {
            return fileAttachment instanceof VideoAttachment ? com.netease.nimlib.net.a.c.d.b(str) : str;
        }
        ImageAttachment imageAttachment = (ImageAttachment) fileAttachment;
        return com.netease.nimlib.net.a.c.d.a(str, imageAttachment.getWidth(), imageAttachment.getHeight());
    }

    public static boolean e(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf >= 0 && lastIndexOf < str.length() - 1) {
            String substring = str.substring(lastIndexOf + 1);
            if (substring.contains("?")) {
                substring = substring.substring(0, substring.indexOf("?"));
            }
            try {
                return new String(Base64.decode(substring, 0)).contains(NimNosSceneKeyConstant.NIM_SECURITY_PREFIX);
            } catch (Throwable unused) {
            }
        }
        return false;
    }
}
