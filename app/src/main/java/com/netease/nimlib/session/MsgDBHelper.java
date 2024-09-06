package com.netease.nimlib.session;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SystemMessageStatus;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import com.netease.nimlib.sdk.msg.model.MsgPinDbOption;
import com.netease.nimlib.sdk.msg.model.MsgSearchOption;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.QuickCommentOption;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.msg.model.RoamMsgHasMoreOption;
import com.netease.nimlib.sdk.msg.model.StickTopSessionInfo;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class MsgDBHelper {
    public static boolean abTestSelected = com.netease.nimlib.biz.a.f();
    public static boolean abTestSelectedMsg = com.netease.nimlib.biz.a.g();

    public static void createTimeIndex(com.netease.nimlib.database.d dVar) {
        dVar.a("CREATE INDEX IF NOT EXISTS lstmsg_time_index on lstmsg(time)");
    }

    public static void dropTimeIndex(com.netease.nimlib.database.d dVar) {
        dVar.a("DROP INDEX IF EXISTS lstmsg_time_index");
    }

    public static void saveMessage(IMMessageImpl iMMessageImpl, MsgStatusEnum msgStatusEnum) {
        ContentValues values = MsgDBHelperInternal.toValues(iMMessageImpl);
        if (msgStatusEnum != null) {
            values.put("status", Integer.valueOf(msgStatusEnum.getValue()));
        }
        iMMessageImpl.setMessageId(MsgDBHelperUtils.database().a("msghistory", (String) null, values));
        MsgDBHelperInternal.updateIndex(iMMessageImpl);
    }

    public static void saveMessage(IMMessageImpl iMMessageImpl) {
        iMMessageImpl.setMessageId(MsgDBHelperUtils.database().a("msghistory", (String) null, MsgDBHelperInternal.toValues(iMMessageImpl)));
        MsgDBHelperInternal.updateIndex(iMMessageImpl);
    }

    public static boolean saveMessages(List<IMMessageImpl> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        MsgDBHelperUtils.database().f();
        try {
            for (IMMessageImpl iMMessageImpl : list) {
                iMMessageImpl.setMessageId(MsgDBHelperUtils.database().a("msghistory", (String) null, MsgDBHelperInternal.toValues(iMMessageImpl)));
            }
            MsgDBHelperUtils.database().h();
            MsgDBHelperUtils.database().g();
            if (com.netease.nimlib.search.b.g().a()) {
                k kVar = new k();
                Iterator<IMMessageImpl> it = list.iterator();
                while (it.hasNext()) {
                    kVar.a(it.next());
                }
                kVar.a();
            }
            return true;
        } catch (Throwable th) {
            MsgDBHelperUtils.database().g();
            throw th;
        }
    }

    public static void updateMessage(IMMessageImpl iMMessageImpl) {
        updateMessage(iMMessageImpl, null);
    }

    public static void updateMessage(IMMessageImpl iMMessageImpl, MsgStatusEnum msgStatusEnum) {
        ContentValues values = MsgDBHelperInternal.toValues(iMMessageImpl);
        values.put("messageid", Long.valueOf(iMMessageImpl.getMessageId()));
        if (msgStatusEnum != null) {
            values.put("status", Integer.valueOf(msgStatusEnum.getValue()));
        }
        MsgDBHelperUtils.database().b("msghistory", null, values);
    }

    public static void saveRecent(q qVar) {
        if (com.netease.nimlib.biz.a.h()) {
            MsgDBHelperB.saveRecent(qVar);
        } else {
            MsgDBHelperA.saveRecent(qVar);
        }
    }

    public static void importRecentContactByUnionKey(List<q> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.importRecentContactByUnionKey(list);
        } else {
            MsgDBHelperA.importRecentContactByUnionKey(list);
        }
    }

    public static void saveRoamMsgHasMore(List<RoamMsgHasMoreOption> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveRoamMsgHasMore(list);
        } else {
            MsgDBHelperA.saveRoamMsgHasMore(list);
        }
    }

    public static void saveQuickComment(String str, List<QuickCommentOption> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveQuickComment(str, list);
        } else {
            MsgDBHelperA.saveQuickComment(str, list);
        }
    }

    public static void saveCollectInfo(List<a> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveCollectInfo(list);
        } else {
            MsgDBHelperA.saveCollectInfo(list);
        }
    }

    public static void saveMsgPin(List<o> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveMsgPin(list);
        } else {
            MsgDBHelperA.saveMsgPin(list);
        }
    }

    public static void saveStickTopSession(StickTopSessionInfo stickTopSessionInfo) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(stickTopSessionInfo);
        saveStickTopSession(arrayList);
    }

    public static void saveStickTopSession(List<StickTopSessionInfo> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveStickTopSession(list);
        } else {
            MsgDBHelperA.saveStickTopSession(list);
        }
    }

    public static List<IMMessageImpl> queryMessageListInSeqIdRange(long j, long j2, int[] iArr, int[] iArr2) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageListInSeqIdRange(j, j2, iArr, iArr2);
        }
        return MsgDBHelperA.queryMessageListInSeqIdRange(j, j2, iArr, iArr2);
    }

    public static long getMessageTimeByUuid(String str) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.getMessageTimeByUuid(str);
        }
        return MsgDBHelperA.getMessageTimeByUuid(str);
    }

    public static ArrayList<IMMessage> queryMessageList(String str, int i, long j, int i2) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageList(str, i, j, i2);
        }
        return MsgDBHelperA.queryMessageList(str, i, j, i2);
    }

    public static ArrayList<IMMessage> queryMessageListEx(IMMessageImpl iMMessageImpl, QueryDirectionEnum queryDirectionEnum, int i, boolean z) {
        com.netease.nimlib.log.b.v(String.format("queryMessageListEx(%s, %s, %s, %s)", IMMessageImpl.toStringSimple(iMMessageImpl), queryDirectionEnum, Integer.valueOf(i), Boolean.valueOf(z)));
        ArrayList<IMMessage> queryMessageListEx = queryMessageListEx(null, iMMessageImpl, 0L, queryDirectionEnum, i, false);
        if ((queryDirectionEnum == QueryDirectionEnum.QUERY_NEW) != z) {
            Collections.reverse(queryMessageListEx);
        }
        return queryMessageListEx;
    }

    public static ArrayList<IMMessage> queryMessageListExWrapper(List<MsgTypeEnum> list, IMMessageImpl iMMessageImpl, long j, QueryDirectionEnum queryDirectionEnum, int i, boolean z, boolean z2) {
        boolean z3;
        boolean z4 = false;
        com.netease.nimlib.log.b.v(String.format("queryMessageListEx(%s, %s, %s, %s, %s, %s), type size is %s", list, IMMessageImpl.toStringSimple(iMMessageImpl), Long.valueOf(j), queryDirectionEnum, Integer.valueOf(i), Boolean.valueOf(z), Integer.valueOf(com.netease.nimlib.o.f.e(list))));
        ArrayList<IMMessage> queryMessageListEx = queryMessageListEx(list, iMMessageImpl, j, queryDirectionEnum, i, z2);
        if (queryDirectionEnum == QueryDirectionEnum.QUERY_NEW) {
            z3 = z;
            z4 = true;
        } else {
            z3 = z;
        }
        if (z4 != z3) {
            Collections.reverse(queryMessageListEx);
        }
        return queryMessageListEx;
    }

    public static ArrayList<IMMessage> queryMessageListEx(IMMessageImpl iMMessageImpl, long j, long j2, boolean z) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageListEx(iMMessageImpl, j, j2, z);
        }
        return MsgDBHelperA.queryMessageListEx(iMMessageImpl, j, j2, z);
    }

    public static ArrayList<IMMessage> queryMessageListEx(List<MsgTypeEnum> list, IMMessageImpl iMMessageImpl, long j, QueryDirectionEnum queryDirectionEnum, int i, boolean z) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageListEx(list, iMMessageImpl, j, queryDirectionEnum, i, z);
        }
        return MsgDBHelperA.queryMessageListEx(list, iMMessageImpl, j, queryDirectionEnum, i, z);
    }

    public static List<IMMessage> queryMessageListByType(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageListByType(msgTypeEnum, iMMessage, i);
        }
        return MsgDBHelperA.queryMessageListByType(msgTypeEnum, iMMessage, i);
    }

    public static List<IMMessage> queryMessageListByType(MsgTypeEnum msgTypeEnum, Long l, int i) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageListByType(msgTypeEnum, l, i);
        }
        return MsgDBHelperA.queryMessageListByType(msgTypeEnum, l, i);
    }

    public static ArrayList<IMMessage> queryMessageListBySubtype(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i, int i2) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageListBySubtype(msgTypeEnum, iMMessage, i, i2);
        }
        return MsgDBHelperA.queryMessageListBySubtype(msgTypeEnum, iMMessage, i, i2);
    }

    public static List<IMMessage> queryMsgListByUuid(List<String> list) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMsgListByUuid(list);
        }
        return MsgDBHelperA.queryMsgListByUuid(list);
    }

    public static Map<String, IMMessage> queryMsgMapByProperty(List<com.netease.nimlib.push.packet.b.c> list) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMsgMapByProperty(list);
        }
        return MsgDBHelperA.queryMsgMapByProperty(list);
    }

    public static List<IMMessage> queryMsgListByServerId(List<String> list) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMsgListByServerId(list);
        }
        return MsgDBHelperA.queryMsgListByServerId(list);
    }

    public static IMMessage queryMessageByUuid(String str) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageByUuid(str);
        }
        return MsgDBHelperA.queryMessageByUuid(str);
    }

    public static IMMessage queryMessageBySeqId(long j) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageBySeqId(j);
        }
        return MsgDBHelperA.queryMessageBySeqId(j);
    }

    public static ArrayList<IMMessage> queryMessageByPage(int i, int i2, boolean z) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageByPage(i, i2, z);
        }
        return MsgDBHelperA.queryMessageByPage(i, i2, z);
    }

    public static int countAllMessage() {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("select count(*) from msghistory");
        if (rawQuery == null) {
            return -1;
        }
        int i = rawQuery.moveToNext() ? rawQuery.getInt(0) : -1;
        if (!rawQuery.isClosed()) {
            rawQuery.close();
        }
        return i;
    }

    public static long queryMessageIdByUuid(String str) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageIdByUuid(str);
        }
        return MsgDBHelperA.queryMessageIdByUuid(str);
    }

    public static Set<String> queryExistUuidsByUuids(List<com.netease.nimlib.push.packet.b.c> list) {
        HashSet hashSet = new HashSet();
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            com.netease.nimlib.log.b.L("queryExistUuidsByUuids msgPropList is empty");
            return hashSet;
        }
        int size = list.size();
        if (size <= 200) {
            return MsgDBHelperInternal.queryExistUuidsByUuids(list, hashSet);
        }
        int i = size / 200;
        int i2 = size % 200;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i3 * 200;
            int i5 = i4 + 200;
            List<com.netease.nimlib.push.packet.b.c> subList = list.subList(i4, i5);
            com.netease.nimlib.log.b.a("queryExistUuidsByUuids for i = %d,fromIndex = %d,toIndex = %d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
            MsgDBHelperInternal.queryExistUuidsByUuids(subList, hashSet);
        }
        if (i2 > 0) {
            int i6 = i * 200;
            int i7 = i2 + i6;
            List<com.netease.nimlib.push.packet.b.c> subList2 = list.subList(i6, i7);
            com.netease.nimlib.log.b.a("queryExistUuidsByUuids lastFromIndex = %d,lastToIndex = %d", Integer.valueOf(i6), Integer.valueOf(i7));
            MsgDBHelperInternal.queryExistUuidsByUuids(subList2, hashSet);
        }
        return hashSet;
    }

    public static long queryLastMessageId() {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT messageid FROM msghistory order by messageid desc LIMIT 1 OFFSET 0");
        if (rawQuery != null) {
            r1 = rawQuery.moveToNext() ? rawQuery.getLong(0) : -1L;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r1;
    }

    public static int queryStatus(String str, boolean z) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryStatus(str, z);
        }
        return MsgDBHelperA.queryStatus(str, z);
    }

    public static IMMessageImpl queryLatestMessage(String str, int i) {
        ArrayList<IMMessage> queryMessageList = queryMessageList(str, i, 0L, 1);
        if (queryMessageList.size() != 1) {
            return null;
        }
        IMMessage iMMessage = queryMessageList.get(0);
        if (iMMessage instanceof IMMessageImpl) {
            return (IMMessageImpl) iMMessage;
        }
        return null;
    }

    public static IMMessage queryLatestMessageFilterMsgType(String str, int i, List<Integer> list) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryLatestMessageFilterMsgType(str, i, list);
        }
        return MsgDBHelperA.queryLatestMessageFilterMsgType(str, i, list);
    }

    public static List<IMMessage> searchMessageHistory(String str, List<String> list, IMMessage iMMessage, QueryDirectionEnum queryDirectionEnum, int i) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.searchMessageHistory(str, list, iMMessage, queryDirectionEnum, i);
        }
        return MsgDBHelperA.searchMessageHistory(str, list, iMMessage, queryDirectionEnum, i);
    }

    public static List<IMMessage> searchAllMessageHistory(String str, List<String> list, long j, int i) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.searchAllMessageHistory(str, list, j, i);
        }
        return MsgDBHelperA.searchAllMessageHistory(str, list, j, i);
    }

    public static List<IMMessage> searchMessage(SessionTypeEnum sessionTypeEnum, String str, MsgSearchOption msgSearchOption) {
        if (abTestSelectedMsg && msgSearchOption.isEnableContentTransfer()) {
            return MsgDBHelperB.searchMessage(sessionTypeEnum, str, msgSearchOption);
        }
        return MsgDBHelperA.searchMessage(sessionTypeEnum, str, msgSearchOption);
    }

    public static List<IMMessage> searchAllMessage(MsgSearchOption msgSearchOption) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.searchAllMessage(msgSearchOption);
        }
        return MsgDBHelperA.searchAllMessage(msgSearchOption);
    }

    public static q queryRecentContact(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryRecentContact(str, sessionTypeEnum);
        }
        return MsgDBHelperA.queryRecentContact(str, sessionTypeEnum);
    }

    public static List<RecentContact> queryRecentContacts() {
        return MsgDBHelperInternal.queryRecentContacts("select uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension from lstmsg order by time desc");
    }

    public static List<RecentContact> queryRecentContacts(int i) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryRecentContacts(i);
        }
        return MsgDBHelperA.queryRecentContacts(i);
    }

    public static List<RecentContact> queryRecentContacts(RecentContact recentContact, QueryDirectionEnum queryDirectionEnum, int i) {
        if (recentContact == null) {
            return MsgDBHelperInternal.queryRecentContacts(0L, queryDirectionEnum, i);
        }
        long checkQueryTime = MsgDBHelperInternal.checkQueryTime(recentContact.getTime(), queryDirectionEnum);
        List<RecentContact> queryRecentContacts = MsgDBHelperInternal.queryRecentContacts(checkQueryTime, queryDirectionEnum, i + 1);
        if (queryRecentContacts.isEmpty()) {
            return queryRecentContacts;
        }
        RecentContact recentContact2 = queryRecentContacts.get(0);
        if (recentContact2.getTime() != checkQueryTime) {
            if (queryRecentContacts.size() > i) {
                queryRecentContacts.remove(queryRecentContacts.size() - 1);
            }
            return queryRecentContacts;
        }
        if (MsgDBHelperUtils.isSameRecentContact(recentContact2, recentContact)) {
            if (queryRecentContacts.size() == 1) {
                return new ArrayList();
            }
            return queryRecentContacts.subList(1, queryRecentContacts.size());
        }
        List<RecentContact> queryRecentContacts2 = MsgDBHelperInternal.queryRecentContacts(checkQueryTime, queryDirectionEnum, MsgDBHelperInternal.querySameTimeRecentContractCount(checkQueryTime) + i);
        int i2 = -1;
        ArrayList arrayList = new ArrayList();
        for (RecentContact recentContact3 : queryRecentContacts2) {
            if (MsgDBHelperUtils.isSameRecentContact(recentContact3, recentContact)) {
                i2 = 0;
            } else if (i2 >= 0) {
                arrayList.add(recentContact3);
                i2++;
                if (i2 == i) {
                    break;
                }
            } else {
                continue;
            }
        }
        return arrayList;
    }

    public static List<RecentContact> queryAllUnreadRecentContact() {
        return MsgDBHelperInternal.queryRecentContacts("select uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension from lstmsg where unreadnum > 0 order by time desc");
    }

    public static List<RecentContact> queryUnreadRecentContactBySessionType(SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperInternal.queryRecentContacts("select uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension from lstmsg where unreadnum > 0 AND sessiontype=" + sessionTypeEnum.getValue() + " order by time desc");
    }

    public static int queryUnreadMsgCount() {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT sum(unreadnum) FROM lstmsg");
        if (rawQuery != null) {
            r1 = rawQuery.moveToNext() ? rawQuery.getInt(0) : 0;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r1;
    }

    public static long queryRoamMsgHasMoreTime(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryRoamMsgHasMoreTime(str, sessionTypeEnum);
        }
        return MsgDBHelperA.queryRoamMsgHasMoreTime(str, sessionTypeEnum);
    }

    public static long queryRoamMsgHasMoreServerId(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryRoamMsgHasMoreServerId(str, sessionTypeEnum);
        }
        return MsgDBHelperA.queryRoamMsgHasMoreServerId(str, sessionTypeEnum);
    }

    public static int queryReplyCount(String str, String str2, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryReplyCount(str, str2, sessionTypeEnum);
        }
        return MsgDBHelperA.queryReplyCount(str, str2, sessionTypeEnum);
    }

    public static ArrayList<QuickCommentOption> queryQuickCommentByUuid(String str) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryQuickCommentByUuid(str);
        }
        return MsgDBHelperA.queryQuickCommentByUuid(str);
    }

    public static List<MsgPinDbOption> queryMsgPin(String str) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMsgPin(str);
        }
        return MsgDBHelperA.queryMsgPin(str);
    }

    public static List<z> queryStickTopSession() {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT session_id, session_type, ext, create_time, update_time FROM session_stick_top");
        ArrayList arrayList = new ArrayList();
        if (rawQuery == null) {
            return arrayList;
        }
        while (rawQuery.moveToNext()) {
            arrayList.add(new z(rawQuery.getString(0), SessionTypeEnum.typeOfValue(rawQuery.getInt(1)), rawQuery.getString(2), rawQuery.getLong(3), rawQuery.getLong(4)));
        }
        if (!rawQuery.isClosed()) {
            rawQuery.close();
        }
        return arrayList;
    }

    public static boolean isStickTopSession(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.isStickTopSession(str, sessionTypeEnum);
        }
        return MsgDBHelperA.isStickTopSession(str, sessionTypeEnum);
    }

    public static void setMessageStatus(long j, int i, long j2, long j3) {
        if (j2 > 0) {
            MsgDBHelperInternal.updateMessageStatusAndTime(j, i, j2, j3);
        } else {
            MsgDBHelperInternal.updateMessageStatus(j, i);
        }
    }

    public static void updateSyncSelfMessageStatus(List<IMMessageImpl> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateSyncSelfMessageStatus(list);
        } else {
            MsgDBHelperA.updateSyncSelfMessageStatus(list);
        }
    }

    public static void updateMessageStatus(IMMessageImpl iMMessageImpl) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateMessageStatus(iMMessageImpl);
        } else {
            MsgDBHelperA.updateMessageStatus(iMMessageImpl);
        }
    }

    public static void updateMessageLocalExt(IMMessageImpl iMMessageImpl) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateMessageLocalExt(iMMessageImpl);
        } else {
            MsgDBHelperA.updateMessageLocalExt(iMMessageImpl);
        }
    }

    public static void updateMessageCallbackExt(long j, String str) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateMessageCallbackExt(j, str);
        } else {
            MsgDBHelperA.updateMessageCallbackExt(j, str);
        }
    }

    public static void updateAttachStatus(String str, int i) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateAttachStatus(str, i);
        } else {
            MsgDBHelperA.updateAttachStatus(str, i);
        }
    }

    public static void setRecentStatus(String str, int i, long j) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.setRecentStatus(str, i, j);
        } else {
            MsgDBHelperA.setRecentStatus(str, i, j);
        }
    }

    public static void setMessageBlacked(long j, boolean z) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.setMessageBlacked(j, z);
        } else {
            MsgDBHelperA.setMessageBlacked(j, z);
        }
    }

    public static void setRecentRead(String str, int i) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.setRecentRead(str, i);
        } else {
            MsgDBHelperA.setRecentRead(str, i);
        }
    }

    public static void updateRecentUnreadNum(String str, SessionTypeEnum sessionTypeEnum, int i) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateRecentUnreadNum(str, sessionTypeEnum, i);
        } else {
            MsgDBHelperA.updateRecentUnreadNum(str, sessionTypeEnum, i);
        }
    }

    public static void updateRecent(RecentContact recentContact) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateRecent(recentContact);
        } else {
            MsgDBHelperA.updateRecent(recentContact);
        }
    }

    public static void updateRoamMsgHasMoreTime(RoamMsgHasMoreOption roamMsgHasMoreOption) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateRoamMsgHasMoreTime(roamMsgHasMoreOption);
        } else {
            MsgDBHelperA.updateRoamMsgHasMoreTime(roamMsgHasMoreOption);
        }
    }

    public static void updateCollectInfo(a aVar) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateCollectInfo(aVar);
        } else {
            MsgDBHelperA.updateCollectInfo(aVar);
        }
    }

    public static void updateMsgPin(String str, String str2, String str3, long j) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateMsgPin(str, str2, str3, j);
        } else {
            MsgDBHelperA.updateMsgPin(str, str2, str3, j);
        }
    }

    public static void updateStickTopSession(String str, SessionTypeEnum sessionTypeEnum, String str2, long j) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateStickTopSession(str, sessionTypeEnum, str2, j);
        } else {
            MsgDBHelperA.updateStickTopSession(str, sessionTypeEnum, str2, j);
        }
    }

    public static int deleteMessage(IMMessageImpl iMMessageImpl) {
        return deleteMessage(iMMessageImpl, true);
    }

    public static int deleteMessage(IMMessageImpl iMMessageImpl, boolean z) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.deleteMessage(iMMessageImpl, z);
        }
        return MsgDBHelperA.deleteMessage(iMMessageImpl, z);
    }

    public static int deleteMessage(List<? extends IMMessage> list, boolean z) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.deleteMessage(list, z);
        }
        return MsgDBHelperA.deleteMessage(list, z);
    }

    public static void clearMessage(String str, SessionTypeEnum sessionTypeEnum, boolean z) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.clearMessage(str, sessionTypeEnum, z);
        } else {
            MsgDBHelperA.clearMessage(str, sessionTypeEnum, z);
        }
    }

    public static void clearAllMessages(boolean z) {
        MsgDBHelperUtils.database().a("DELETE FROM msghistory");
        MsgDBHelperInternal.recordClearAllMsg();
        com.netease.nimlib.search.b.g().d();
        if (z) {
            MsgDBHelperUtils.database().a("DELETE FROM lstmsg");
        }
    }

    public static void deleteRecentContact(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteRecentContact(str, sessionTypeEnum);
        } else {
            MsgDBHelperA.deleteRecentContact(str, sessionTypeEnum);
        }
    }

    public static void deleteRoamMsgHasMoreTime(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteRoamMsgHasMoreTime(str, sessionTypeEnum);
        } else {
            MsgDBHelperA.deleteRoamMsgHasMoreTime(str, sessionTypeEnum);
        }
    }

    public static void deleteQuickComment(String str, String str2, long j) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteQuickComment(str, str2, j);
        } else {
            MsgDBHelperA.deleteQuickComment(str, str2, j);
        }
    }

    public static void deleteQuickComment(String str) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteQuickComment(str);
        } else {
            MsgDBHelperA.deleteQuickComment(str);
        }
    }

    public static void deleteCollectInfo(List<Long> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteCollectInfo(list);
        } else {
            MsgDBHelperA.deleteCollectInfo(list);
        }
    }

    public static void deleteMsgPin(String str, String str2) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteMsgPin(str, str2);
        } else {
            MsgDBHelperA.deleteMsgPin(str, str2);
        }
    }

    public static void deleteMsgPin(String str) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteMsgPin(str);
        } else {
            MsgDBHelperA.deleteMsgPin(str);
        }
    }

    public static void clearStickTopSession() {
        MsgDBHelperUtils.database().a("DELETE FROM session_stick_top");
    }

    public static void deleteStickTopSession(String str) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteStickTopSession(str);
        } else {
            MsgDBHelperA.deleteStickTopSession(str);
        }
    }

    public static boolean hasDeleteTag(String str) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.hasDeleteTag(str);
        }
        return MsgDBHelperA.hasDeleteTag(str);
    }

    public static Set<String> hasDeleteTag(Collection<IMMessageImpl> collection) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.hasDeleteTag(collection);
        }
        return MsgDBHelperA.hasDeleteTag(collection);
    }

    public static long getClearSessionTime(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.getClearSessionTime(str, sessionTypeEnum);
        }
        return MsgDBHelperA.getClearSessionTime(str, sessionTypeEnum);
    }

    public static boolean isRemovedWhileClearingSession(String str, SessionTypeEnum sessionTypeEnum, long j) {
        long clearSessionTime = getClearSessionTime(str, sessionTypeEnum);
        return clearSessionTime > 0 && clearSessionTime >= j;
    }

    public static void saveSystemMessage(SystemMessage systemMessage, int i) {
        ContentValues contentValues = new ContentValues(8);
        contentValues.put("id", systemMessage.getTargetId());
        contentValues.put("fromid", systemMessage.getFromAccount());
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("time", Long.valueOf(systemMessage.getTime()));
        contentValues.put("status", Integer.valueOf(systemMessage.getStatus().getValue()));
        contentValues.put("content", systemMessage.getContent());
        contentValues.put("attach", systemMessage.getAttach());
        contentValues.put("unread", Boolean.valueOf(systemMessage.isUnread()));
        systemMessage.setMessageId(MsgDBHelperUtils.database().a("system_msg", (String) null, contentValues));
    }

    public static ArrayList<SystemMessage> querySystemMessages(int i, int i2) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.querySystemMessages(i, i2);
        }
        return MsgDBHelperA.querySystemMessages(i, i2);
    }

    public static ArrayList<SystemMessage> querySystemMessage(List<SystemMessageType> list, int i, int i2) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.querySystemMessage(list, i, i2);
        }
        return MsgDBHelperA.querySystemMessage(list, i, i2);
    }

    public static ArrayList<SystemMessage> querySystemMessageUnread() {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT messageid, id, fromid, type, time, status, content, attach, unread FROM system_msg where unread=='1'");
        ArrayList<SystemMessage> arrayList = new ArrayList<>();
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                arrayList.add(MsgDBHelperCursorTransfer.systemMsgFromCursor(rawQuery));
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    public static void setSystemMessageStatus(long j, SystemMessageStatus systemMessageStatus) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.setSystemMessageStatus(j, systemMessageStatus);
        } else {
            MsgDBHelperA.setSystemMessageStatus(j, systemMessageStatus);
        }
    }

    public static void setAllSystemMessageRead() {
        MsgDBHelperUtils.database().a("UPDATE system_msg SET unread='0'");
    }

    public static void setAllSystemMessageRead(List<SystemMessageType> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.setAllSystemMessageRead(list);
        } else {
            MsgDBHelperA.setAllSystemMessageRead(list);
        }
    }

    public static void setSystemMessageRead(long j) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.setSystemMessageRead(j);
        } else {
            MsgDBHelperA.setSystemMessageRead(j);
        }
    }

    public static int querySystemMessageUnreadNum() {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT count(*) FROM system_msg where unread=='1'");
        if (rawQuery != null) {
            r1 = rawQuery.moveToNext() ? rawQuery.getInt(0) : 0;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r1;
    }

    public static int querySystemMessageUnreadNum(List<SystemMessageType> list) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.querySystemMessageUnreadNum(list);
        }
        return MsgDBHelperA.querySystemMessageUnreadNum(list);
    }

    public static void deleteSystemMessage(long j) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteSystemMessage(j);
        } else {
            MsgDBHelperA.deleteSystemMessage(j);
        }
    }

    public static void clearSystemMessages() {
        MsgDBHelperUtils.database().a("DELETE FROM system_msg");
    }

    public static void clearSystemMessages(List<SystemMessageType> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.clearSystemMessages(list);
        } else {
            MsgDBHelperA.clearSystemMessages(list);
        }
    }

    public static void saveMessageReceipt(List<f> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveMessageReceipt(list);
        } else {
            MsgDBHelperA.saveMessageReceipt(list);
        }
    }

    public static List<f> queryAllMessageReceipt() {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT session_id,time,max_time FROM message_receipt");
        ArrayList arrayList = new ArrayList();
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                arrayList.add(MsgDBHelperCursorTransfer.readReceiptFromCursor(rawQuery));
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    public static Map<String, f> queryMessageReceipt(List<String> list) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMessageReceipt(list);
        }
        return MsgDBHelperA.queryMessageReceipt(list);
    }

    public static void saveSendReceiptRecord(MessageReceipt messageReceipt) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveSendReceiptRecord(messageReceipt);
        } else {
            MsgDBHelperA.saveSendReceiptRecord(messageReceipt);
        }
    }

    public static List<MessageReceipt> queryAllSendReceiptRecord() {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT session_id,time FROM send_receipt_record");
        ArrayList arrayList = new ArrayList();
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                arrayList.add(MsgDBHelperCursorTransfer.readSendReceiptRecordFromCursor(rawQuery));
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    public static long getSessionLastReceivedMsgTimeTag(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.getSessionLastReceivedMsgTimeTag(str, sessionTypeEnum);
        }
        return MsgDBHelperA.getSessionLastReceivedMsgTimeTag(str, sessionTypeEnum);
    }

    public static void saveSessionReadRecord(String str, SessionTypeEnum sessionTypeEnum, long j) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveSessionReadRecord(str, sessionTypeEnum, j);
        } else {
            MsgDBHelperA.saveSessionReadRecord(str, sessionTypeEnum, j);
        }
    }

    public static long querySessionReadTimeTag(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.querySessionReadTimeTag(str, sessionTypeEnum);
        }
        return MsgDBHelperA.querySessionReadTimeTag(str, sessionTypeEnum);
    }

    public static ArrayList<IMMessage> queryUnreadMessages(String str, SessionTypeEnum sessionTypeEnum, long j) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryUnreadMessages(str, sessionTypeEnum, j);
        }
        return MsgDBHelperA.queryUnreadMessages(str, sessionTypeEnum, j);
    }

    public static void saveSenderNick(String str, String str2) {
        if (com.netease.nimlib.biz.a.h()) {
            MsgDBHelperB.saveSenderNick(str, str2);
        } else {
            MsgDBHelperA.saveSenderNick(str, str2);
        }
    }

    public static void saveSenderNickMap(Map<String, String> map) {
        if (com.netease.nimlib.biz.a.h()) {
            MsgDBHelperB.saveSenderNickMap(map);
        } else {
            MsgDBHelperA.saveSenderNickMap(map);
        }
    }

    public static Map<String, String> queryAllSenderNick() {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryAllSenderNick();
        }
        return MsgDBHelperA.queryAllSenderNick();
    }

    public static void saveRevokeMessage(String str) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveRevokeMessage(str);
        } else {
            MsgDBHelperA.saveRevokeMessage(str);
        }
    }

    public static String queryRevokeMessage(String str) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryRevokeMessage(str);
        }
        return MsgDBHelperA.queryRevokeMessage(str);
    }

    public static void markHasSendTeamMsgAck(List<String> list) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.markHasSendTeamMsgAck(list);
        } else {
            MsgDBHelperA.markHasSendTeamMsgAck(list);
        }
    }

    public static void updateTeamMsgAckCount(String str, int i, int i2) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateTeamMsgAckCount(str, i, i2);
        } else {
            MsgDBHelperA.updateTeamMsgAckCount(str, i, i2);
        }
    }

    public static void saveTeamMsgAckDetail(TeamMsgAckInfo teamMsgAckInfo, String str) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.saveTeamMsgAckDetail(teamMsgAckInfo, str);
        } else {
            MsgDBHelperA.saveTeamMsgAckDetail(teamMsgAckInfo, str);
        }
    }

    public static void updateTeamMsgAckDetail(String str, String str2) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.updateTeamMsgAckDetail(str, str2);
        } else {
            MsgDBHelperA.updateTeamMsgAckDetail(str, str2);
        }
    }

    public static TeamMsgAckInfo queryTeamMsgAckDetail(String str) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryTeamMsgAckDetail(str);
        }
        return MsgDBHelperA.queryTeamMsgAckDetail(str);
    }

    public static void deleteRangeHistory(String str, SessionTypeEnum sessionTypeEnum, long j, long j2) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.deleteRangeHistory(str, sessionTypeEnum, j, j2);
        } else {
            MsgDBHelperA.deleteRangeHistory(str, sessionTypeEnum, j, j2);
        }
    }

    public static void migrateMessages(Context context, String str, String str2, boolean z) {
        if (abTestSelectedMsg) {
            MsgDBHelperB.migrateMessages(context, str, str2, z);
        } else {
            MsgDBHelperA.migrateMessages(context, str, str2, z);
        }
    }

    public static long saveSessionReliableInfo(com.netease.nimlib.session.a.d dVar) {
        com.netease.nimlib.log.b.v(String.format("to save session reliable info %s", dVar));
        return MsgDBHelperUtils.database().a("session_reliable_table", (String) null, MsgDBHelperInternal.toValues(dVar));
    }

    public static int removeSessionReliableInfo(List<Long> list) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.removeSessionReliableInfo(list);
        }
        return MsgDBHelperA.removeSessionReliableInfo(list);
    }

    public static int removeSessionReliableInfo(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.removeSessionReliableInfo(str, sessionTypeEnum);
        }
        return MsgDBHelperA.removeSessionReliableInfo(str, sessionTypeEnum);
    }

    public static int removeAllSessionReliableInfo() {
        com.netease.nimlib.log.b.v("to remove session reliable info with no whereClause");
        return MsgDBHelperUtils.database().a("session_reliable_table", (String) null);
    }

    public static com.netease.nimlib.session.a.d queryLastSessionReliableInfo(String str, SessionTypeEnum sessionTypeEnum) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryLastSessionReliableInfo(str, sessionTypeEnum);
        }
        return MsgDBHelperA.queryLastSessionReliableInfo(str, sessionTypeEnum);
    }

    public static List<com.netease.nimlib.session.a.d> queryMayOverLappedInfos(com.netease.nimlib.session.a.d dVar) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryMayOverLappedInfos(dVar);
        }
        return MsgDBHelperA.queryMayOverLappedInfos(dVar);
    }

    public static List<com.netease.nimlib.session.a.d> queryParentInfos(com.netease.nimlib.session.a.d dVar) {
        if (abTestSelectedMsg) {
            return MsgDBHelperB.queryParentInfos(dVar);
        }
        return MsgDBHelperA.queryParentInfos(dVar);
    }

    public static String getMessageJsonStringByUuids(List<String> list) {
        if (list == null || list.isEmpty()) {
            return new JSONArray().toString();
        }
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT * FROM msghistory WHERE uuid IN ('" + TextUtils.join("','", list) + "')", null);
        if (rawQuery == null) {
            return new JSONArray().toString();
        }
        JSONArray fromCursorToJsonArray = fromCursorToJsonArray(rawQuery);
        rawQuery.close();
        return fromCursorToJsonArray.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
    
        if (r3 == 1) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
    
        if (r3 == 3) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0024, code lost:
    
        r1.put(r6.getColumnName(r2), r6.getString(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0030, code lost:
    
        r1.put(r6.getColumnName(r2), r6.getLong(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        r1.put(r6.getColumnName(r2), org.json.JSONObject.NULL);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0046, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0047, code lost:
    
        r3.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004d, code lost:
    
        r0.put(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0054, code lost:
    
        if (r6.moveToNext() != false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0009, code lost:
    
        if (r6.moveToFirst() != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000b, code lost:
    
        r1 = new org.json.JSONObject();
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0015, code lost:
    
        if (r2 >= r6.getColumnCount()) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
    
        r3 = r6.getType(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
    
        if (r3 == 0) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONArray fromCursorToJsonArray(android.database.Cursor r6) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            boolean r1 = r6.moveToFirst()
            if (r1 == 0) goto L56
        Lb:
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            r2 = 0
        L11:
            int r3 = r6.getColumnCount()
            if (r2 >= r3) goto L4d
            int r3 = r6.getType(r2)     // Catch: org.json.JSONException -> L46
            if (r3 == 0) goto L3c
            r4 = 1
            if (r3 == r4) goto L30
            r4 = 3
            if (r3 == r4) goto L24
            goto L4a
        L24:
            java.lang.String r3 = r6.getColumnName(r2)     // Catch: org.json.JSONException -> L46
            java.lang.String r4 = r6.getString(r2)     // Catch: org.json.JSONException -> L46
            r1.put(r3, r4)     // Catch: org.json.JSONException -> L46
            goto L4a
        L30:
            java.lang.String r3 = r6.getColumnName(r2)     // Catch: org.json.JSONException -> L46
            long r4 = r6.getLong(r2)     // Catch: org.json.JSONException -> L46
            r1.put(r3, r4)     // Catch: org.json.JSONException -> L46
            goto L4a
        L3c:
            java.lang.String r3 = r6.getColumnName(r2)     // Catch: org.json.JSONException -> L46
            java.lang.Object r4 = org.json.JSONObject.NULL     // Catch: org.json.JSONException -> L46
            r1.put(r3, r4)     // Catch: org.json.JSONException -> L46
            goto L4a
        L46:
            r3 = move-exception
            r3.printStackTrace()
        L4a:
            int r2 = r2 + 1
            goto L11
        L4d:
            r0.put(r1)
            boolean r1 = r6.moveToNext()
            if (r1 != 0) goto Lb
        L56:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.session.MsgDBHelper.fromCursorToJsonArray(android.database.Cursor):org.json.JSONArray");
    }

    public static String getMessageJsonStringByTime(String str, int i, long j, long j2, int i2, boolean z) {
        Cursor rawQuery;
        if (j <= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM msghistory WHERE id = ? AND sessiontype = ? ORDER BY time ");
            sb.append(z ? "ASC" : "DESC");
            sb.append(", serverid ");
            sb.append(z ? "ASC" : "DESC");
            sb.append(" LIMIT ?");
            rawQuery = MsgDBHelperUtils.rawQuery(sb.toString(), new String[]{str, String.valueOf(i), String.valueOf(i2)});
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("SELECT * FROM msghistory WHERE id = ? AND sessiontype = ? AND (time ");
            sb2.append(z ? ">" : "<");
            sb2.append(" ? OR (time = ? AND serverid ");
            sb2.append(z ? ">" : "<");
            sb2.append(" ?)) ORDER BY time ");
            sb2.append(z ? "ASC" : "DESC");
            sb2.append(", serverid ");
            sb2.append(z ? "ASC" : "DESC");
            sb2.append(" LIMIT ?");
            rawQuery = MsgDBHelperUtils.rawQuery(sb2.toString(), new String[]{str, String.valueOf(i), String.valueOf(j), String.valueOf(j), String.valueOf(j2), String.valueOf(i2)});
        }
        if (rawQuery == null) {
            return new JSONArray().toString();
        }
        JSONArray fromCursorToJsonArray = fromCursorToJsonArray(rawQuery);
        rawQuery.close();
        return fromCursorToJsonArray.toString();
    }
}
