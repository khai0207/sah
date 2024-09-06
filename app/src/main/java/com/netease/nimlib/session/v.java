package com.netease.nimlib.session;

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: SessionAckHelper.java */
/* loaded from: classes.dex */
public class v {
    public static boolean a(String str, SessionTypeEnum sessionTypeEnum, long j) {
        long querySessionReadTimeTag = MsgDBHelper.querySessionReadTimeTag(str, sessionTypeEnum);
        if (j > querySessionReadTimeTag) {
            MsgDBHelper.saveSessionReadRecord(str, sessionTypeEnum, j);
            return true;
        }
        com.netease.nimlib.log.b.y("local saved timetag=" + querySessionReadTimeTag + ", received new timetag=" + j + ", no need to update session read record, sessionId=" + str);
        return false;
    }

    public static long a(String str, SessionTypeEnum sessionTypeEnum) {
        long sessionLastReceivedMsgTimeTag = MsgDBHelper.getSessionLastReceivedMsgTimeTag(str, sessionTypeEnum);
        long querySessionReadTimeTag = MsgDBHelper.querySessionReadTimeTag(str, sessionTypeEnum);
        if (sessionLastReceivedMsgTimeTag > 0 && sessionLastReceivedMsgTimeTag > querySessionReadTimeTag) {
            com.netease.nimlib.log.b.d("SessionAckHelper", String.format("update session read record from %s to %s", Long.valueOf(querySessionReadTimeTag), Long.valueOf(sessionLastReceivedMsgTimeTag)));
            MsgDBHelper.saveSessionReadRecord(str, sessionTypeEnum, sessionLastReceivedMsgTimeTag);
        }
        return sessionLastReceivedMsgTimeTag;
    }

    private static int a(String str, SessionTypeEnum sessionTypeEnum, List<IMMessage> list) {
        if (TextUtils.isEmpty(str) || sessionTypeEnum == null) {
            return -1;
        }
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return 0;
        }
        long querySessionReadTimeTag = MsgDBHelper.querySessionReadTimeTag(str, sessionTypeEnum);
        int i = 0;
        for (IMMessage iMMessage : list) {
            if (iMMessage != null && str.equals(iMMessage.getSessionId()) && sessionTypeEnum == iMMessage.getSessionType()) {
                i += (iMMessage.getTime() <= querySessionReadTimeTag || !j.a(iMMessage, false, querySessionReadTimeTag)) ? 0 : 1;
            }
        }
        return i;
    }

    public static Map<String, Integer> a(List<IMMessage> list) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return new HashMap(0);
        }
        HashMap a = com.netease.nimlib.o.f.a((Collection) list, true, (f.a) new f.a() { // from class: com.netease.nimlib.session.-$$Lambda$v$QypSXYeSqh0VKXu3W1eTWdSoAGo
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                String a2;
                a2 = v.a((IMMessage) obj);
                return a2;
            }
        });
        HashMap hashMap = new HashMap(((a.size() << 2) / 3) + 1);
        for (Map.Entry entry : a.entrySet()) {
            String str = (String) entry.getKey();
            ArrayList arrayList = (ArrayList) entry.getValue();
            Pair<String, SessionTypeEnum> a2 = a(str);
            if (!TextUtils.isEmpty(str) && a2 != null) {
                hashMap.put(str, Integer.valueOf(a((String) a2.first, (SessionTypeEnum) a2.second, arrayList)));
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String a(IMMessage iMMessage) {
        if (iMMessage == null) {
            return null;
        }
        return e(iMMessage.getSessionId(), iMMessage.getSessionType());
    }

    public static int b(String str, SessionTypeEnum sessionTypeEnum) {
        long querySessionReadTimeTag = MsgDBHelper.querySessionReadTimeTag(str, sessionTypeEnum);
        ArrayList<IMMessage> queryUnreadMessages = MsgDBHelper.queryUnreadMessages(str, sessionTypeEnum, querySessionReadTimeTag);
        if (queryUnreadMessages.isEmpty()) {
            return 0;
        }
        com.netease.nimlib.log.b.a("calculateUnreadCount sessionId = %s,sessionType = %s,lastReadTimeTag = %d", str, sessionTypeEnum, Long.valueOf(querySessionReadTimeTag));
        Iterator<IMMessage> it = queryUnreadMessages.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (j.a(it.next(), false, querySessionReadTimeTag)) {
                i++;
            }
        }
        com.netease.nimlib.log.b.a("calculateUnreadCount sessionId = %s,sessionType = %s,unread = %d", str, sessionTypeEnum, Integer.valueOf(i));
        return i;
    }

    public static void c(String str, SessionTypeEnum sessionTypeEnum) {
        boolean z;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int b = b(str, sessionTypeEnum);
        q queryRecentContact = MsgDBHelper.queryRecentContact(str, sessionTypeEnum);
        int unreadCount = (queryRecentContact == null || b <= queryRecentContact.getUnreadCount()) ? b : queryRecentContact.getUnreadCount();
        if (queryRecentContact == null || unreadCount == queryRecentContact.getUnreadCount()) {
            z = false;
        } else {
            MsgDBHelper.updateRecentUnreadNum(str, sessionTypeEnum, unreadCount);
            queryRecentContact.a(unreadCount);
            j.a(queryRecentContact);
            com.netease.nimlib.i.b.a(queryRecentContact);
            z = true;
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
        StringBuilder sb = new StringBuilder();
        sb.append("recalculate unread count, sessionId=");
        sb.append(str);
        sb.append(", type=");
        sb.append(sessionTypeEnum);
        sb.append(", recalculate unread=");
        sb.append(b);
        sb.append(", recent unread=");
        sb.append(queryRecentContact != null ? queryRecentContact.getUnreadCount() : 0);
        sb.append(", output unread=");
        sb.append(unreadCount);
        sb.append(", updateAndNotify=");
        sb.append(z);
        sb.append(", cost time=");
        sb.append(elapsedRealtime2);
        sb.append("ms");
        com.netease.nimlib.log.b.N(sb.toString());
    }

    public static void a(String str, SessionTypeEnum sessionTypeEnum, long j, com.netease.nimlib.i.k kVar) {
        b(str, sessionTypeEnum, j, kVar);
    }

    private static void b(String str, SessionTypeEnum sessionTypeEnum, long j, com.netease.nimlib.i.k kVar) {
        if (!com.netease.nimlib.c.i().sessionReadAck || j <= 0 || !c(str, sessionTypeEnum, j)) {
            if (kVar != null) {
                kVar.b((Object) null).b();
                return;
            }
            return;
        }
        com.netease.nimlib.biz.d.i.b bVar = new com.netease.nimlib.biz.d.i.b(sessionTypeEnum, str, j);
        if (kVar != null) {
            bVar.a(kVar);
        }
        com.netease.nimlib.biz.i.a().a(bVar, com.netease.nimlib.biz.g.a.b);
        com.netease.nimlib.log.b.y("send session ack to other clients, sessionId=" + str + ", timetag=" + j);
    }

    public static long b(String str, SessionTypeEnum sessionTypeEnum, long j) {
        long d = d(str, sessionTypeEnum);
        if (j <= d) {
            return d;
        }
        a(e(str, sessionTypeEnum), j);
        return j;
    }

    public static long d(String str, SessionTypeEnum sessionTypeEnum) {
        return b(e(str, sessionTypeEnum));
    }

    public static boolean c(String str, SessionTypeEnum sessionTypeEnum, long j) {
        return j > d(str, sessionTypeEnum);
    }

    public static String e(String str, SessionTypeEnum sessionTypeEnum) {
        return str + "_" + sessionTypeEnum.getValue();
    }

    public static Pair<String, SessionTypeEnum> a(String str) {
        int indexOf;
        if (!com.netease.nimlib.o.w.a((CharSequence) str) && (indexOf = str.indexOf(95)) > 0 && indexOf != str.length() - 1) {
            try {
                return new Pair<>(str.substring(0, indexOf), SessionTypeEnum.typeOfValue(Integer.parseInt(str.substring(indexOf + 1))));
            } catch (Throwable th) {
                com.netease.nimlib.log.b.e("SessionAckHelper", "readKey error, sessionKey=" + str, th);
            }
        }
        return null;
    }

    private static void a(String str, long j) {
        SharedPreferences.Editor edit = a().edit();
        edit.putLong(str, j);
        edit.commit();
    }

    private static long b(String str) {
        return a().getLong(str, 0L);
    }

    private static SharedPreferences a() {
        return com.netease.nimlib.c.e().getSharedPreferences("NIMSDK_SESSION_ACK_" + com.netease.nimlib.c.g() + "_" + com.netease.nimlib.c.n(), 0);
    }
}
