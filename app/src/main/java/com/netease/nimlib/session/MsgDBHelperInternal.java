package com.netease.nimlib.session;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechEvent;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MsgPinDbOption;
import com.netease.nimlib.sdk.msg.model.MsgThreadOption;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class MsgDBHelperInternal {
    static ContentValues toValues(IMMessageImpl iMMessageImpl) {
        ContentValues contentValues = new ContentValues(25);
        contentValues.put("uuid", iMMessageImpl.getUuid());
        contentValues.put("serverid", Long.valueOf(iMMessageImpl.getServerId()));
        contentValues.put("time", Long.valueOf(iMMessageImpl.getTime()));
        contentValues.put("content", iMMessageImpl.getContent());
        contentValues.put("msgtype", Integer.valueOf(iMMessageImpl.getMsgTypeInner()));
        contentValues.put("sessiontype", Integer.valueOf(iMMessageImpl.getSessionType().getValue()));
        contentValues.put("fromid", iMMessageImpl.getFromAccount());
        contentValues.put("id", iMMessageImpl.getSessionId());
        contentValues.put("direct", Integer.valueOf(iMMessageImpl.getDirect().getValue()));
        contentValues.put("status", Integer.valueOf(iMMessageImpl.getStatus().getValue()));
        contentValues.put("status2", Integer.valueOf(iMMessageImpl.getAttachStatus().getValue()));
        contentValues.put("attach", iMMessageImpl.getAttachStr(false));
        contentValues.put("remoteext", iMMessageImpl.getRemoteExtensionStr());
        contentValues.put("localext", iMMessageImpl.getLocalExtensionStr());
        contentValues.put("push", iMMessageImpl.getPushContent());
        contentValues.put("payload", iMMessageImpl.getPushPayloadStr());
        contentValues.put("config", iMMessageImpl.getConfigStr());
        contentValues.put("pushoption", iMMessageImpl.getMemberPushOptionStr());
        contentValues.put("fromclient", Integer.valueOf(iMMessageImpl.getFromClientType()));
        contentValues.put("antispamoption", iMMessageImpl.getNimAntiSpamOptionStr());
        contentValues.put("msgack", Integer.valueOf(iMMessageImpl.needMsgAck() ? 1 : 0));
        contentValues.put("acksend", Integer.valueOf(iMMessageImpl.hasSendAck() ? 1 : 0));
        contentValues.put("ackcount", Integer.valueOf(iMMessageImpl.getTeamMsgAckCount()));
        contentValues.put("unackcount", Integer.valueOf(iMMessageImpl.getTeamMsgUnAckCount()));
        contentValues.put("isblacked", Integer.valueOf(iMMessageImpl.isInBlackList() ? 1 : 0));
        MsgThreadOption msgThreadOption = iMMessageImpl.isThread() ? new MsgThreadOption() : iMMessageImpl.getThreadOption();
        contentValues.put("replymsgfromaccount", msgThreadOption.getReplyMsgFromAccount());
        contentValues.put("replymsgtoaccount", msgThreadOption.getReplyMsgToAccount());
        contentValues.put("replymsgtime", Long.valueOf(msgThreadOption.getReplyMsgTime()));
        contentValues.put("replymsgidserver", Long.valueOf(msgThreadOption.getReplyMsgIdServer()));
        contentValues.put("replymsgidclient", msgThreadOption.getReplyMsgIdClient());
        contentValues.put("threadmsgfromaccount", msgThreadOption.getThreadMsgFromAccount());
        contentValues.put("threadmsgtoaccount", msgThreadOption.getThreadMsgToAccount());
        contentValues.put("threadmsgtime", Long.valueOf(msgThreadOption.getThreadMsgTime()));
        contentValues.put("threadmsgidserver", Long.valueOf(msgThreadOption.getThreadMsgIdServer()));
        contentValues.put("threadmsgidclient", msgThreadOption.getThreadMsgIdClient());
        contentValues.put("quickcommentupdatetime", Long.valueOf(iMMessageImpl.getQuickCommentUpdateTime()));
        contentValues.put("isdelete", Integer.valueOf(iMMessageImpl.isDeleted() ? 1 : 0));
        contentValues.put("callbackext", iMMessageImpl.getCallbackExtension());
        contentValues.put("subtype", Integer.valueOf(iMMessageImpl.getSubtype()));
        contentValues.put("robotinfo", m.a(iMMessageImpl.getRobotInfo()));
        return contentValues;
    }

    static void updateIndex(IMMessageImpl iMMessageImpl) {
        if (com.netease.nimlib.search.b.g().a()) {
            new k().a(iMMessageImpl).a();
        }
    }

    static Map<String, IMMessage> queryMsgHistoriesMapFromProperty(List<com.netease.nimlib.push.packet.b.c> list, Map<String, IMMessage> map) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return map;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("in ('");
        boolean z = true;
        Iterator<com.netease.nimlib.push.packet.b.c> it = list.iterator();
        while (it.hasNext()) {
            String c = it.next().c(11);
            if (!TextUtils.isEmpty(c)) {
                if (z) {
                    sb.append(c);
                    sb.append("'");
                    z = false;
                } else {
                    sb.append(", '");
                    sb.append(c);
                    sb.append("'");
                }
            }
        }
        sb.append(")");
        return queryMsgHistoriesMap("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where uuid " + sb.toString(), map);
    }

    static Set<String> queryExistUuidsByUuids(List<com.netease.nimlib.push.packet.b.c> list, Set<String> set) {
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Iterator<com.netease.nimlib.push.packet.b.c> it = list.iterator();
            while (it.hasNext()) {
                String c = it.next().c(11);
                if (!TextUtils.isEmpty(c)) {
                    sb.append(", ");
                    sb.append("'");
                    sb.append(c);
                    sb.append("'");
                }
            }
            String str = "SELECT uuid FROM msghistory WHERE uuid IN (" + sb.substring(2) + ")";
            com.netease.nimlib.log.b.L("queryExistUuidsByUuids sql = " + str);
            Cursor rawQuery = MsgDBHelperUtils.rawQuery(str);
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    set.add(rawQuery.getString(0));
                }
                if (!rawQuery.isClosed()) {
                    rawQuery.close();
                }
            }
            com.netease.nimlib.log.b.L("queryExistUuidsByUuids msgUuidSet = " + set);
        }
        return set;
    }

    static ArrayList<IMMessage> queryMsgHistories(String str) {
        return queryMsgHistories(str, null);
    }

    static ArrayList<IMMessage> queryMsgHistories(String str, String[] strArr) {
        String format;
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(str, strArr);
        if (rawQuery == null) {
            return new ArrayList<>();
        }
        ArrayList<IMMessage> arrayList = new ArrayList<>();
        while (true) {
            boolean z = false;
            if (!rawQuery.moveToNext()) {
                break;
            }
            IMMessageImpl iMMessageImpl = new IMMessageImpl();
            iMMessageImpl.setMessageId(rawQuery.getLong(0));
            iMMessageImpl.setUuid(rawQuery.getString(1));
            iMMessageImpl.setServerId(rawQuery.getLong(2));
            iMMessageImpl.setTime(rawQuery.getLong(3));
            iMMessageImpl.setContent(rawQuery.getString(4));
            iMMessageImpl.setMsgType(rawQuery.getInt(5));
            iMMessageImpl.setSessionType(SessionTypeEnum.typeOfValue(rawQuery.getInt(6)));
            iMMessageImpl.setFromAccount(rawQuery.getString(7));
            iMMessageImpl.setSessionId(rawQuery.getString(8));
            iMMessageImpl.setDirect(MsgDirectionEnum.directionOfValue(rawQuery.getInt(9)));
            iMMessageImpl.setStatus(MsgStatusEnum.statusOfValue(rawQuery.getInt(10)));
            iMMessageImpl.setAttachStatus(AttachStatusEnum.statusOfValue(rawQuery.getInt(11)));
            iMMessageImpl.setAttachStr(rawQuery.getString(12));
            iMMessageImpl.setRemoteExtensionStr(rawQuery.getString(13));
            iMMessageImpl.setLocalExtensionStr(rawQuery.getString(14));
            iMMessageImpl.setPushContent(rawQuery.getString(15));
            iMMessageImpl.setPushPayloadStr(rawQuery.getString(16));
            iMMessageImpl.setConfigStr(rawQuery.getString(17));
            iMMessageImpl.setMemberPushOptionStr(rawQuery.getString(18));
            iMMessageImpl.setFromClientType(rawQuery.getInt(19));
            iMMessageImpl.setNimAntiSpamOptionStr(rawQuery.getString(20));
            if (rawQuery.getInt(21) == 1) {
                iMMessageImpl.setMsgAck();
            }
            if (rawQuery.getInt(22) == 1) {
                iMMessageImpl.setHasSendAck();
            }
            iMMessageImpl.setTeamMsgAckCount(rawQuery.getInt(23));
            iMMessageImpl.setTeamMsgUnAckCount(rawQuery.getInt(24));
            if (rawQuery.getInt(25) == 1) {
                iMMessageImpl.setInBlackList(true);
            }
            MsgThreadOption msgThreadOption = new MsgThreadOption();
            String string = rawQuery.getString(26);
            if (string == null) {
                string = "";
            }
            msgThreadOption.setReplyMsgFromAccount(string);
            String string2 = rawQuery.getString(27);
            if (string2 == null) {
                string2 = "";
            }
            msgThreadOption.setReplyMsgToAccount(string2);
            msgThreadOption.setReplyMsgTime(rawQuery.getLong(28));
            msgThreadOption.setReplyMsgIdServer(rawQuery.getLong(29));
            String string3 = rawQuery.getString(30);
            if (string3 == null) {
                string3 = "";
            }
            msgThreadOption.setReplyMsgIdClient(string3);
            String string4 = rawQuery.getString(31);
            if (string4 == null) {
                string4 = "";
            }
            msgThreadOption.setThreadMsgFromAccount(string4);
            String string5 = rawQuery.getString(32);
            if (string5 == null) {
                string5 = "";
            }
            msgThreadOption.setThreadMsgToAccount(string5);
            msgThreadOption.setThreadMsgTime(rawQuery.getLong(33));
            msgThreadOption.setThreadMsgIdServer(rawQuery.getLong(34));
            String string6 = rawQuery.getString(35);
            msgThreadOption.setThreadMsgIdClient(string6 != null ? string6 : "");
            iMMessageImpl.setThreadOption(msgThreadOption);
            iMMessageImpl.setQuickCommentUpdateTime(rawQuery.getLong(36));
            if (rawQuery.getInt(37) == 1) {
                z = true;
            }
            iMMessageImpl.setDeleted(z);
            iMMessageImpl.setCallbackExtension(rawQuery.getString(38));
            iMMessageImpl.setSubtype(rawQuery.getInt(39));
            iMMessageImpl.setRobotInfoStr(rawQuery.getString(40));
            arrayList.add(iMMessageImpl);
        }
        if (!rawQuery.isClosed()) {
            rawQuery.close();
        }
        int e = com.netease.nimlib.o.f.e(arrayList);
        StringBuilder sb = new StringBuilder();
        sb.append("queryMsgHistories: ");
        if (com.netease.nimlib.o.f.c((Collection) arrayList)) {
            format = "size=0";
        } else {
            int i = e - 1;
            format = String.format("size=%s, uuid%s=%s, uuid%s=%s", Integer.valueOf(e), 0, arrayList.get(0).getUuid(), Integer.valueOf(i), arrayList.get(i).getUuid());
        }
        sb.append(format);
        com.netease.nimlib.log.b.v(sb.toString());
        return arrayList;
    }

    static Map<String, IMMessage> queryMsgHistoriesMap(String str, Map<String, IMMessage> map) {
        com.netease.nimlib.log.b.L("queryMsgHistoriesMap sql = " + str);
        queryMsgHistoriesMap(MsgDBHelperUtils.rawQuery(str), map);
        return map;
    }

    static void queryMsgHistoriesMap(Cursor cursor, Map<String, IMMessage> map) {
        if (cursor == null) {
            return;
        }
        while (true) {
            boolean z = false;
            if (!cursor.moveToNext()) {
                break;
            }
            IMMessageImpl iMMessageImpl = new IMMessageImpl();
            iMMessageImpl.setMessageId(cursor.getLong(0));
            iMMessageImpl.setUuid(cursor.getString(1));
            iMMessageImpl.setServerId(cursor.getLong(2));
            iMMessageImpl.setTime(cursor.getLong(3));
            iMMessageImpl.setContent(cursor.getString(4));
            iMMessageImpl.setMsgType(cursor.getInt(5));
            iMMessageImpl.setSessionType(SessionTypeEnum.typeOfValue(cursor.getInt(6)));
            iMMessageImpl.setFromAccount(cursor.getString(7));
            iMMessageImpl.setSessionId(cursor.getString(8));
            iMMessageImpl.setDirect(MsgDirectionEnum.directionOfValue(cursor.getInt(9)));
            iMMessageImpl.setStatus(MsgStatusEnum.statusOfValue(cursor.getInt(10)));
            iMMessageImpl.setAttachStatus(AttachStatusEnum.statusOfValue(cursor.getInt(11)));
            iMMessageImpl.setAttachStr(cursor.getString(12));
            iMMessageImpl.setRemoteExtensionStr(cursor.getString(13));
            iMMessageImpl.setLocalExtensionStr(cursor.getString(14));
            iMMessageImpl.setPushContent(cursor.getString(15));
            iMMessageImpl.setPushPayloadStr(cursor.getString(16));
            iMMessageImpl.setConfigStr(cursor.getString(17));
            iMMessageImpl.setMemberPushOptionStr(cursor.getString(18));
            iMMessageImpl.setFromClientType(cursor.getInt(19));
            iMMessageImpl.setNimAntiSpamOptionStr(cursor.getString(20));
            if (cursor.getInt(21) == 1) {
                iMMessageImpl.setMsgAck();
            }
            if (cursor.getInt(22) == 1) {
                iMMessageImpl.setHasSendAck();
            }
            iMMessageImpl.setTeamMsgAckCount(cursor.getInt(23));
            iMMessageImpl.setTeamMsgUnAckCount(cursor.getInt(24));
            if (cursor.getInt(25) == 1) {
                iMMessageImpl.setInBlackList(true);
            }
            MsgThreadOption msgThreadOption = new MsgThreadOption();
            String string = cursor.getString(26);
            if (string == null) {
                string = "";
            }
            msgThreadOption.setReplyMsgFromAccount(string);
            String string2 = cursor.getString(27);
            if (string2 == null) {
                string2 = "";
            }
            msgThreadOption.setReplyMsgToAccount(string2);
            msgThreadOption.setReplyMsgTime(cursor.getLong(28));
            msgThreadOption.setReplyMsgIdServer(cursor.getLong(29));
            String string3 = cursor.getString(30);
            if (string3 == null) {
                string3 = "";
            }
            msgThreadOption.setReplyMsgIdClient(string3);
            String string4 = cursor.getString(31);
            if (string4 == null) {
                string4 = "";
            }
            msgThreadOption.setThreadMsgFromAccount(string4);
            String string5 = cursor.getString(32);
            if (string5 == null) {
                string5 = "";
            }
            msgThreadOption.setThreadMsgToAccount(string5);
            msgThreadOption.setThreadMsgTime(cursor.getLong(33));
            msgThreadOption.setThreadMsgIdServer(cursor.getLong(34));
            String string6 = cursor.getString(35);
            msgThreadOption.setThreadMsgIdClient(string6 != null ? string6 : "");
            iMMessageImpl.setThreadOption(msgThreadOption);
            iMMessageImpl.setQuickCommentUpdateTime(cursor.getLong(36));
            if (cursor.getInt(37) == 1) {
                z = true;
            }
            iMMessageImpl.setDeleted(z);
            iMMessageImpl.setCallbackExtension(cursor.getString(38));
            iMMessageImpl.setSubtype(cursor.getInt(39));
            iMMessageImpl.setRobotInfoStr(cursor.getString(40));
            map.put(iMMessageImpl.getUuid(), iMMessageImpl);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        int size = map.size();
        StringBuilder sb = new StringBuilder();
        sb.append("queryMsgHistoriesMap: ");
        sb.append(size == 0 ? "size=0" : String.format("size=%s, uuids=%s", Integer.valueOf(size), map.keySet()));
        com.netease.nimlib.log.b.L(sb.toString());
    }

    static q queryRecentContact(String str) {
        return queryRecentContact(str, null);
    }

    static q queryRecentContact(String str, String[] strArr) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(str, strArr);
        q recentFromCursor = (rawQuery == null || !rawQuery.moveToNext()) ? null : MsgDBHelperCursorTransfer.recentFromCursor(rawQuery);
        if (rawQuery != null && !rawQuery.isClosed()) {
            rawQuery.close();
        }
        return recentFromCursor;
    }

    static int querySameTimeRecentContractCount(long j) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT count(*) FROM lstmsg where time = " + j);
        if (rawQuery != null) {
            r3 = rawQuery.moveToNext() ? rawQuery.getInt(0) : 0;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r3;
    }

    static List<RecentContact> queryRecentContacts(long j, QueryDirectionEnum queryDirectionEnum, int i) {
        String str;
        long checkQueryTime = checkQueryTime(j, queryDirectionEnum);
        if (queryDirectionEnum == QueryDirectionEnum.QUERY_OLD) {
            str = "select uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension from lstmsg where time <= " + checkQueryTime + " order by time desc,rowid desc limit " + i;
        } else {
            str = "select uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension from lstmsg where time >= " + checkQueryTime + " order by time asc,rowid asc limit " + i;
        }
        return queryRecentContacts(str);
    }

    static long checkQueryTime(long j, QueryDirectionEnum queryDirectionEnum) {
        if (j < 0) {
            j = 0;
        }
        if (queryDirectionEnum == QueryDirectionEnum.QUERY_OLD && j == 0) {
            return Long.MAX_VALUE;
        }
        return j;
    }

    static List<RecentContact> queryRecentContacts(String str) {
        return queryRecentContacts(str, null);
    }

    static List<RecentContact> queryRecentContacts(String str, String[] strArr) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(str, strArr);
        ArrayList arrayList = new ArrayList();
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                q recentFromCursor = MsgDBHelperCursorTransfer.recentFromCursor(rawQuery);
                if (recentFromCursor != null) {
                    arrayList.add(recentFromCursor);
                }
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    static List<MsgPinDbOption> readMsgPinDbOptionList(String str, int i) {
        return readMsgPinDbOptionList(str, i, null);
    }

    static List<MsgPinDbOption> readMsgPinDbOptionList(String str, int i, String[] strArr) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(str, strArr);
        ArrayList arrayList = i < 0 ? new ArrayList() : new ArrayList(i);
        if (rawQuery == null) {
            return arrayList;
        }
        while (rawQuery.moveToNext()) {
            arrayList.add(new MsgPinDbOption(rawQuery.getString(0), rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3), rawQuery.getLong(4), rawQuery.getLong(5)));
        }
        if (!rawQuery.isClosed()) {
            rawQuery.close();
        }
        return arrayList;
    }

    static List<MsgPinDbOption> readMsgPinDbOptionList(String str, String[] strArr) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(str, strArr);
        ArrayList arrayList = new ArrayList(0);
        if (rawQuery == null) {
            return arrayList;
        }
        while (rawQuery.moveToNext()) {
            arrayList.add(new MsgPinDbOption(rawQuery.getString(0), rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3), rawQuery.getLong(4), rawQuery.getLong(5)));
        }
        if (!rawQuery.isClosed()) {
            rawQuery.close();
        }
        return arrayList;
    }

    static void updateMessageStatusAndTime(long j, int i, long j2, long j3) {
        MsgDBHelperUtils.database().a("UPDATE msghistory set status='" + i + "', time='" + j2 + "', serverid='" + j3 + "' where messageid='" + j + "'");
    }

    public static void updateMessageStatus(long j, int i) {
        MsgDBHelperUtils.database().a("UPDATE msghistory set status='" + i + "' where messageid='" + j + "'");
    }

    static void recordDelete(String str, String str2, SessionTypeEnum sessionTypeEnum) {
        MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO delete_message_record (uuid, session_id, session_type) values ('" + com.netease.nimlib.database.a.c.a(str) + "', '" + com.netease.nimlib.database.a.c.a(str2) + "', " + sessionTypeEnum.getValue() + ")");
    }

    static void recordClearContact(String str, SessionTypeEnum sessionTypeEnum, long j) {
        MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO clear_message_record (session_id, session_type, time) values ('" + com.netease.nimlib.database.a.c.a(str) + "', " + sessionTypeEnum.getValue() + ", " + j + ")");
        clearSingleDeleteRecord(str, sessionTypeEnum);
    }

    static void recordClearAllMsg() {
        com.netease.nimlib.biz.l.u(System.currentTimeMillis());
        clearRemovedInSessionRecord();
    }

    static void clearSingleDeleteRecord(String str, SessionTypeEnum sessionTypeEnum) {
        MsgDBHelperUtils.database().a("DELETE FROM delete_message_record WHERE session_id='" + str + "' AND session_type=" + sessionTypeEnum.getValue());
    }

    static void clearRemovedInSessionRecord() {
        MsgDBHelperUtils.database().a("DELETE FROM delete_message_record");
        MsgDBHelperUtils.database().a("DELETE FROM clear_message_record");
    }

    static ContentValues toValues(com.netease.nimlib.session.a.d dVar) {
        ContentValues contentValues = new ContentValues(8);
        contentValues.put(SpeechEvent.KEY_EVENT_SESSION_ID, dVar.e());
        contentValues.put("session_type", Integer.valueOf(dVar.f().getValue()));
        contentValues.put("start_time", Long.valueOf(dVar.i()));
        contentValues.put("start_server_id", Long.valueOf(dVar.g()));
        contentValues.put("start_client_id", dVar.h());
        contentValues.put("stop_time", Long.valueOf(dVar.l()));
        contentValues.put("stop_server_id", Long.valueOf(dVar.j()));
        contentValues.put("stop_client_id", dVar.k());
        return contentValues;
    }

    static List<com.netease.nimlib.session.a.d> querySessionReliableInfos(String str) {
        return querySessionReliableInfos(str, null);
    }

    static List<com.netease.nimlib.session.a.d> querySessionReliableInfos(String str, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (com.netease.nimlib.o.w.a((CharSequence) str)) {
            return arrayList;
        }
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(str, strArr);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                arrayList.add(MsgDBHelperCursorTransfer.sessionReliableInfoFromCursor(rawQuery));
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        com.netease.nimlib.log.b.v(String.format("query SessionReliableInfoInfos with %s. result is %s", str, com.netease.nimlib.o.f.f(arrayList)));
        return arrayList;
    }
}
