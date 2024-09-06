package com.netease.nimlib.session;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SystemMessageStatus;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import com.netease.nimlib.sdk.msg.model.MsgPinDbOption;
import com.netease.nimlib.sdk.msg.model.MsgSearchOption;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.QuickCommentOption;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.msg.model.RoamMsgHasMoreOption;
import com.netease.nimlib.sdk.msg.model.SearchOrderEnum;
import com.netease.nimlib.sdk.msg.model.StickTopSessionInfo;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;
import com.netease.nimlib.session.MsgDBHelperUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class MsgDBHelperA {
    public static void saveRecent(q qVar) {
        MsgDBHelperUtils.database().a("insert or replace into lstmsg(uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension) values ('" + com.netease.nimlib.database.a.c.a(qVar.getContactId()) + "','" + com.netease.nimlib.database.a.c.a(qVar.getFromAccount()) + "','" + qVar.getRecentMessageId() + "','" + qVar.getMsgStatus().getValue() + "','" + qVar.getUnreadCount() + "','" + com.netease.nimlib.database.a.c.a(qVar.getContent()) + "','" + qVar.getTime() + "','" + qVar.getSessionType().getValue() + "','" + qVar.getTag() + "','" + qVar.b() + "','" + com.netease.nimlib.database.a.c.a(qVar.a()) + "','" + com.netease.nimlib.database.a.c.a(qVar.c()) + "')");
    }

    public static void importRecentContactByUnionKey(List<q> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        MsgDBHelperUtils.database().f();
        try {
            for (q qVar : list) {
                StringBuilder sb = new StringBuilder();
                sb.append("('");
                sb.append(com.netease.nimlib.database.a.c.a(qVar.getContactId()));
                sb.append("','");
                sb.append(qVar.getSessionType().getValue());
                sb.append("')");
                MsgDBHelperUtils.database().a("insert or ignore into lstmsg (uid,sessiontype) values" + ((Object) sb));
            }
            MsgDBHelperUtils.database().h();
        } finally {
            MsgDBHelperUtils.database().g();
        }
    }

    public static void saveRoamMsgHasMore(List<RoamMsgHasMoreOption> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        MsgDBHelperUtils.database().f();
        try {
            for (RoamMsgHasMoreOption roamMsgHasMoreOption : list) {
                com.netease.nimlib.database.d database = MsgDBHelperUtils.database();
                StringBuilder sb = new StringBuilder();
                sb.append("INSERT OR REPLACE INTO  roam_msg_has_more (serverid, session_id, session_type, time) VALUES ");
                sb.append("('" + roamMsgHasMoreOption.getServerId() + "','" + com.netease.nimlib.database.a.c.a(roamMsgHasMoreOption.getSessionId()) + "','" + roamMsgHasMoreOption.getSessionType().getValue() + "','" + roamMsgHasMoreOption.getTime() + "')");
                database.a(sb.toString());
            }
            MsgDBHelperUtils.database().h();
        } finally {
            MsgDBHelperUtils.database().g();
        }
    }

    public static void saveQuickComment(String str, List<QuickCommentOption> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        MsgDBHelperUtils.database().f();
        try {
            for (QuickCommentOption quickCommentOption : list) {
                com.netease.nimlib.database.d database = MsgDBHelperUtils.database();
                StringBuilder sb = new StringBuilder();
                sb.append("INSERT OR REPLACE INTO quick_comment (uuid, operator, type, time, ext) VALUES ");
                sb.append("('" + str + "','" + com.netease.nimlib.database.a.c.a(quickCommentOption.getFromAccount()) + "','" + quickCommentOption.getReplyType() + "','" + quickCommentOption.getTime() + "','" + com.netease.nimlib.database.a.c.a(quickCommentOption.getExt()) + "')");
                database.a(sb.toString());
            }
            MsgDBHelperUtils.database().h();
        } finally {
            MsgDBHelperUtils.database().g();
        }
    }

    public static void saveCollectInfo(List<a> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        MsgDBHelperUtils.database().f();
        try {
            for (a aVar : list) {
                com.netease.nimlib.database.d database = MsgDBHelperUtils.database();
                StringBuilder sb = new StringBuilder();
                sb.append("INSERT OR REPLACE INTO collect_info (id, type, data, ext, uniqueId, createTime, updateTime) VALUES ");
                sb.append("('" + aVar.getId() + "','" + aVar.getType() + "','" + com.netease.nimlib.database.a.c.a(aVar.getData()) + "','" + com.netease.nimlib.database.a.c.a(aVar.getExt()) + "','" + com.netease.nimlib.database.a.c.a(aVar.getUniqueId()) + "','" + aVar.getCreateTime() + "','" + aVar.getUpdateTime() + "')");
                database.a(sb.toString());
            }
            MsgDBHelperUtils.database().h();
        } finally {
            MsgDBHelperUtils.database().g();
        }
    }

    public static void saveMsgPin(List<o> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        MsgDBHelperUtils.database().f();
        try {
            for (o oVar : list) {
                if (oVar != null) {
                    MessageKey key = oVar.getKey();
                    n pinOption = oVar.getPinOption();
                    if (key != null && pinOption != null) {
                        com.netease.nimlib.database.d database = MsgDBHelperUtils.database();
                        StringBuilder sb = new StringBuilder();
                        sb.append("INSERT OR REPLACE INTO msg_pin (uuid, session_id, operator, ext, create_time, update_time) VALUES ");
                        sb.append("('" + key.getUuid() + "','" + g.a(key) + "','" + com.netease.nimlib.database.a.c.a(pinOption.getAccount()) + "','" + com.netease.nimlib.database.a.c.a(pinOption.getExt()) + "','" + pinOption.getCreateTime() + "','" + pinOption.getUpdateTime() + "')");
                        database.a(sb.toString());
                    }
                }
            }
            MsgDBHelperUtils.database().h();
        } finally {
            MsgDBHelperUtils.database().g();
        }
    }

    public static void saveStickTopSession(List<StickTopSessionInfo> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        MsgDBHelperUtils.database().f();
        try {
            for (StickTopSessionInfo stickTopSessionInfo : list) {
                if (stickTopSessionInfo != null) {
                    com.netease.nimlib.database.d database = MsgDBHelperUtils.database();
                    StringBuilder sb = new StringBuilder();
                    sb.append("INSERT OR REPLACE INTO session_stick_top (session_id, session_type, ext, create_time, update_time) VALUES ");
                    sb.append("('" + com.netease.nimlib.database.a.c.a(stickTopSessionInfo.getSessionId()) + "','" + stickTopSessionInfo.getSessionType().getValue() + "','" + com.netease.nimlib.database.a.c.a(stickTopSessionInfo.getExt()) + "','" + stickTopSessionInfo.getCreateTime() + "','" + stickTopSessionInfo.getUpdateTime() + "')");
                    database.a(sb.toString());
                }
            }
            MsgDBHelperUtils.database().h();
        } finally {
            MsgDBHelperUtils.database().g();
        }
    }

    public static List<IMMessageImpl> queryMessageListInSeqIdRange(long j, long j2, int[] iArr, int[] iArr2) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where");
        sb.append(" messageid > ");
        sb.append(j);
        sb.append(" and");
        sb.append(" messageid <= ");
        sb.append(j2);
        sb.append(" and");
        sb.append(" sessiontype in (");
        for (int i = 0; i < iArr.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(iArr[i]);
        }
        sb.append(")");
        sb.append(" and");
        sb.append(" msgtype in (");
        for (int i2 = 0; i2 < iArr2.length; i2++) {
            if (i2 > 0) {
                sb.append(",");
            }
            sb.append(iArr2[i2]);
        }
        sb.append(")");
        sb.append(" order by messageid asc");
        ArrayList arrayList = new ArrayList();
        Iterator<IMMessage> it = MsgDBHelperInternal.queryMsgHistories(sb.toString()).iterator();
        while (it.hasNext()) {
            arrayList.add((IMMessageImpl) it.next());
        }
        return arrayList;
    }

    public static long getMessageTimeByUuid(String str) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(("SELECT time FROM msghistory where uuid='" + com.netease.nimlib.database.a.c.a(str) + "'").toString());
        long j = (rawQuery == null || !rawQuery.moveToNext()) ? 0L : rawQuery.getLong(0);
        if (rawQuery != null && !rawQuery.isClosed()) {
            rawQuery.close();
        }
        return j;
    }

    public static ArrayList<IMMessage> queryMessageList(String str, int i, long j, int i2) {
        com.netease.nimlib.log.b.v(String.format("queryMessageList(%s, %s, %s, %s)", str, Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(i2)));
        return MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where id='" + com.netease.nimlib.database.a.c.a(str) + "' and sessiontype='" + i + "' ORDER BY time desc limit " + i2 + " offset " + j);
    }

    public static ArrayList<IMMessage> queryMessageListEx(IMMessageImpl iMMessageImpl, long j, long j2, boolean z) {
        String sessionId = iMMessageImpl.getSessionId();
        int value = iMMessageImpl.getSessionType().getValue();
        boolean z2 = iMMessageImpl.getMessageId() > 0;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id='");
        sb.append(com.netease.nimlib.database.a.c.a(sessionId));
        sb.append("' and sessiontype='");
        sb.append(value);
        sb.append("'");
        sb.append(" and time>=");
        sb.append(j);
        sb.append(" and time<=");
        sb.append(j2);
        sb.append(" ORDER BY time ");
        sb.append("ASC");
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories(sb.toString());
        if (!z2) {
            return queryMsgHistories;
        }
        Iterator<IMMessage> it = queryMsgHistories.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            if (((IMMessageImpl) it.next()).getMessageId() == iMMessageImpl.getMessageId()) {
                break;
            }
        }
        for (int i2 = 0; i2 <= i - 1; i2++) {
            queryMsgHistories.remove(i2);
        }
        if (i <= 1) {
            return queryMsgHistories;
        }
        sb.delete(sb.lastIndexOf(" "), sb.length());
        sb.append(" offset ");
        sb.append(i);
        return MsgDBHelperInternal.queryMsgHistories(sb.toString());
    }

    public static ArrayList<IMMessage> queryMessageListEx(List<MsgTypeEnum> list, IMMessageImpl iMMessageImpl, long j, QueryDirectionEnum queryDirectionEnum, int i, boolean z) {
        com.netease.nimlib.log.b.v(String.format("queryMessageListEx(%s, %s, %s, %s, %s), types size is %s", list, IMMessageImpl.toStringSimple(iMMessageImpl), Long.valueOf(j), queryDirectionEnum, Integer.valueOf(i), Integer.valueOf(com.netease.nimlib.o.f.e(list))));
        String sessionId = iMMessageImpl.getSessionId();
        int value = iMMessageImpl.getSessionType().getValue();
        boolean z2 = iMMessageImpl.getMessageId() > 0;
        boolean z3 = com.netease.nimlib.o.f.c((Collection) list) || list.contains(iMMessageImpl.getMsgType());
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id='");
        sb.append(com.netease.nimlib.database.a.c.a(sessionId));
        sb.append("' and sessiontype='");
        sb.append(value);
        sb.append("'");
        boolean z4 = queryDirectionEnum == QueryDirectionEnum.QUERY_NEW;
        if (z4) {
            sb.append(" and time>=");
            sb.append(iMMessageImpl.getTime());
        } else if (iMMessageImpl.getTime() > 0) {
            sb.append(" and time<=");
            sb.append(iMMessageImpl.getTime());
        }
        if (j > 0) {
            if (z4) {
                sb.append(" and time<=");
                sb.append(j);
            } else {
                sb.append(" and time>=");
                sb.append(j);
            }
        }
        if (list != null && !list.isEmpty()) {
            Iterator<MsgTypeEnum> it = list.iterator();
            String str = " and msgtype in(";
            while (it.hasNext()) {
                str = (str + it.next().getValue()) + ",";
            }
            sb.append(str.substring(0, str.length() - 1) + ")");
        }
        int i2 = (!z ? z2 : z3 && z2) ? i : i + 1;
        sb.append(" ORDER BY time ");
        sb.append(z4 ? "ASC" : "DESC");
        sb.append(" limit ");
        sb.append(i2);
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories(sb.toString());
        if ((z && !z3) || !z2) {
            return queryMsgHistories;
        }
        Iterator<IMMessage> it2 = queryMsgHistories.iterator();
        int i3 = 0;
        while (it2.hasNext()) {
            i3++;
            if (((IMMessageImpl) it2.next()).getMessageId() == iMMessageImpl.getMessageId()) {
                break;
            }
        }
        for (int i4 = 0; i4 <= i3 - 1; i4++) {
            queryMsgHistories.remove(0);
        }
        if (i3 <= 1) {
            return queryMsgHistories;
        }
        sb.delete(sb.lastIndexOf(" ") + 1, sb.length());
        sb.append(i);
        sb.append(" offset ");
        sb.append(i3);
        return MsgDBHelperInternal.queryMsgHistories(sb.toString());
    }

    public static List<IMMessage> queryMessageListByType(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i) {
        com.netease.nimlib.log.b.v(String.format("queryMessageListByType(%s, %s, %s)", msgTypeEnum, IMMessageImpl.toStringSimple(iMMessage), Integer.valueOf(i)));
        String sessionId = iMMessage.getSessionId();
        int value = iMMessage.getSessionType().getValue();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id='");
        sb.append(com.netease.nimlib.database.a.c.a(sessionId));
        sb.append("'");
        sb.append(" and sessiontype='");
        sb.append(value);
        sb.append("'");
        if (iMMessage.getTime() > 0) {
            sb.append(" and time<'");
            sb.append(iMMessage.getTime());
            sb.append("'");
        }
        sb.append(" and msgtype='");
        sb.append(msgTypeEnum.getValue());
        sb.append("'");
        sb.append(" ORDER BY time desc");
        sb.append(" limit ");
        sb.append(i);
        return MsgDBHelperInternal.queryMsgHistories(sb.toString());
    }

    public static List<IMMessage> queryMessageListByType(MsgTypeEnum msgTypeEnum, Long l, int i) {
        com.netease.nimlib.log.b.v(String.format("queryMessageListByType(%s, %s, %s)", msgTypeEnum, l, Integer.valueOf(i)));
        StringBuilder sb = new StringBuilder();
        if (l == null) {
            sb.append("SELECT ");
            sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
            sb.append(" FROM msghistory where msgtype='");
            sb.append(msgTypeEnum.getValue());
            sb.append("'");
            sb.append(" ORDER BY time desc");
        } else {
            sb.append("SELECT ");
            sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
            sb.append(" FROM msghistory where time<");
            sb.append(l);
            sb.append(" and msgtype='");
            sb.append(msgTypeEnum.getValue());
            sb.append("'");
            sb.append(" ORDER BY time desc");
            sb.append(" limit ");
            sb.append(i);
        }
        return MsgDBHelperInternal.queryMsgHistories(sb.toString());
    }

    public static ArrayList<IMMessage> queryMessageListBySubtype(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i, int i2) {
        com.netease.nimlib.log.b.v(String.format("queryMessageListBySubtype(%s, %s, %s, %s)", msgTypeEnum, IMMessageImpl.toStringSimple(iMMessage), Integer.valueOf(i), Integer.valueOf(i2)));
        String sessionId = iMMessage.getSessionId();
        int value = iMMessage.getSessionType().getValue();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id='");
        sb.append(com.netease.nimlib.database.a.c.a(sessionId));
        sb.append("'");
        sb.append(" and sessiontype='");
        sb.append(value);
        sb.append("'");
        sb.append(" and subtype=");
        sb.append(i2);
        if (iMMessage.getTime() > 0) {
            sb.append(" and time<'");
            sb.append(iMMessage.getTime());
            sb.append("'");
        }
        sb.append(" and msgtype='");
        sb.append(msgTypeEnum.getValue());
        sb.append("'");
        sb.append(" ORDER BY time desc");
        sb.append(" limit ");
        sb.append(i);
        return MsgDBHelperInternal.queryMsgHistories(sb.toString());
    }

    public static List<IMMessage> queryMsgListByUuid(List<String> list) {
        boolean z = true;
        com.netease.nimlib.log.b.v(String.format("queryMsgListByUuid, uuid size is %s", Integer.valueOf(com.netease.nimlib.o.f.e(list))));
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return new ArrayList(0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("in ('");
        for (String str : list) {
            if (z) {
                sb.append(str);
                sb.append("'");
                z = false;
            } else {
                sb.append(", '");
                sb.append(str);
                sb.append("'");
            }
        }
        sb.append(")");
        return MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where uuid " + sb.toString());
    }

    public static Map<String, IMMessage> queryMsgMapByProperty(List<com.netease.nimlib.push.packet.b.c> list) {
        com.netease.nimlib.log.b.v(String.format("queryMsgMapByProperty, msgProperty size is %s", Integer.valueOf(com.netease.nimlib.o.f.e(list))));
        HashMap hashMap = new HashMap();
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            com.netease.nimlib.log.b.L("queryMsgMapByProperty msgProperty list is empty ");
            return hashMap;
        }
        int size = list.size();
        com.netease.nimlib.log.b.L("queryMsgMapByProperty msgProperty size = " + size);
        if (size <= 200) {
            return MsgDBHelperInternal.queryMsgHistoriesMapFromProperty(list, hashMap);
        }
        int i = size / 200;
        int i2 = size % 200;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i3 * 200;
            int i5 = i4 + 200;
            List<com.netease.nimlib.push.packet.b.c> subList = list.subList(i4, i5);
            com.netease.nimlib.log.b.a("queryMsgMapByProperty for i = %d,fromIndex = %d,toIndex = %d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
            MsgDBHelperInternal.queryMsgHistoriesMapFromProperty(subList, hashMap);
        }
        if (i2 > 0) {
            int i6 = i * 200;
            int i7 = i2 + i6;
            List<com.netease.nimlib.push.packet.b.c> subList2 = list.subList(i6, i7);
            com.netease.nimlib.log.b.a("queryMsgMapByProperty lastFromIndex = %d,lastToIndex = %d", Integer.valueOf(i6), Integer.valueOf(i7));
            MsgDBHelperInternal.queryMsgHistoriesMapFromProperty(subList2, hashMap);
        }
        return hashMap;
    }

    public static List<IMMessage> queryMsgListByServerId(List<String> list) {
        boolean z = true;
        com.netease.nimlib.log.b.v(String.format("queryMsgListByUuid, serverId size is %s", Integer.valueOf(com.netease.nimlib.o.f.e(list))));
        StringBuilder sb = new StringBuilder();
        sb.append("in ('");
        for (String str : list) {
            if (z) {
                sb.append(str);
                sb.append("'");
                z = false;
            } else {
                sb.append(", '");
                sb.append(str);
                sb.append("'");
            }
        }
        sb.append(")");
        return MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where serverid " + sb.toString());
    }

    public static IMMessage queryMessageByUuid(String str) {
        if (com.netease.nimlib.o.w.a((CharSequence) str)) {
            return null;
        }
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where uuid='" + str + "'");
        if (queryMsgHistories == null || queryMsgHistories.size() != 1) {
            return null;
        }
        return queryMsgHistories.get(0);
    }

    public static IMMessage queryMessageBySeqId(long j) {
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where messageid='" + j + "'");
        if (queryMsgHistories == null || queryMsgHistories.size() != 1) {
            return null;
        }
        return queryMsgHistories.get(0);
    }

    public static ArrayList<IMMessage> queryMessageByPage(int i, int i2, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" from msghistory order by messageid  ");
        sb.append(z ? "asc" : "desc");
        sb.append(" limit ");
        sb.append(i);
        sb.append(" offset ");
        sb.append(i2);
        return MsgDBHelperInternal.queryMsgHistories(sb.toString());
    }

    public static long queryMessageIdByUuid(String str) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT messageid FROM msghistory where uuid='" + str + "'");
        if (rawQuery != null) {
            r0 = rawQuery.moveToNext() ? rawQuery.getLong(0) : 0L;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r0;
    }

    public static int queryStatus(String str, boolean z) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT " + (z ? "status2" : "status") + " FROM msghistory where uuid='" + str + "'");
        if (rawQuery != null) {
            r3 = rawQuery.moveToNext() ? rawQuery.getInt(0) : 0;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r3;
    }

    public static IMMessage queryLatestMessageFilterMsgType(String str, int i, List<Integer> list) {
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where id='" + com.netease.nimlib.database.a.c.a(str) + "' and sessiontype='" + i + "' and msgtype not in (" + com.netease.nimlib.o.f.f(list) + ") ORDER BY time desc limit 1 offset 0");
        if (queryMsgHistories.size() == 1) {
            return queryMsgHistories.get(0);
        }
        return null;
    }

    public static List<IMMessage> searchMessageHistory(String str, List<String> list, IMMessage iMMessage, QueryDirectionEnum queryDirectionEnum, int i) {
        String sessionId = iMMessage.getSessionId();
        int value = iMMessage.getSessionType().getValue();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id='");
        sb.append(com.netease.nimlib.database.a.c.a(sessionId));
        sb.append("'");
        sb.append(" and sessiontype='");
        sb.append(value);
        sb.append("'");
        boolean z = queryDirectionEnum == QueryDirectionEnum.QUERY_NEW;
        if (iMMessage.getTime() > 0) {
            if (z) {
                sb.append(" and time>'");
                sb.append(iMMessage.getTime());
                sb.append("'");
            } else {
                sb.append(" and time<'");
                sb.append(iMMessage.getTime());
                sb.append("'");
            }
        }
        sb.append(" and (");
        if (list != null && list.size() > 0) {
            sb.append("fromid in (");
            for (String str2 : list) {
                sb.append("'");
                sb.append(com.netease.nimlib.database.a.c.a(str2));
                sb.append("',");
            }
            sb.replace(sb.length() - 1, sb.length(), ") or");
        }
        sb.append(" content like ");
        sb.append(com.netease.nimlib.database.a.c.b(str));
        sb.append(")");
        sb.append(" ORDER BY time ");
        sb.append(z ? "ASC" : "DESC");
        sb.append(" limit ");
        sb.append(i);
        return MsgDBHelperInternal.queryMsgHistories(sb.toString());
    }

    public static List<IMMessage> searchAllMessageHistory(String str, List<String> list, long j, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where 1=1");
        if (j > 0) {
            sb.append(" and time<'");
            sb.append(j);
            sb.append("'");
        }
        sb.append(" and (");
        if (list != null && list.size() > 0) {
            sb.append("fromid in (");
            for (String str2 : list) {
                sb.append("'");
                sb.append(com.netease.nimlib.database.a.c.a(str2));
                sb.append("',");
            }
            sb.replace(sb.length() - 1, sb.length(), ") or");
        }
        sb.append(" content like ");
        sb.append(com.netease.nimlib.database.a.c.b(str));
        sb.append(")");
        sb.append(" ORDER BY time desc");
        sb.append(" limit ");
        sb.append(i);
        return MsgDBHelperInternal.queryMsgHistories(sb.toString());
    }

    public static List<IMMessage> searchMessage(SessionTypeEnum sessionTypeEnum, String str, MsgSearchOption msgSearchOption) {
        com.netease.nimlib.log.b.v(String.format("searchMessage sessionType = %s,sessionId = %s MsgSearchOption = %s", sessionTypeEnum, str, msgSearchOption));
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id='");
        sb.append(com.netease.nimlib.database.a.c.a(str));
        sb.append("'");
        sb.append(" and sessiontype='");
        sb.append(sessionTypeEnum.getValue());
        sb.append("'");
        long startTime = msgSearchOption.getStartTime();
        long endTime = msgSearchOption.getEndTime() == 0 ? Long.MAX_VALUE : msgSearchOption.getEndTime();
        sb.append(" and time>");
        sb.append(startTime);
        sb.append(" and time<");
        sb.append(endTime);
        boolean isAllMessageTypes = msgSearchOption.isAllMessageTypes();
        List<MsgTypeEnum> messageTypes = msgSearchOption.getMessageTypes();
        if (!isAllMessageTypes) {
            if (com.netease.nimlib.o.f.c((Collection) messageTypes)) {
                messageTypes = new ArrayList<>();
                messageTypes.add(MsgTypeEnum.text);
            }
            sb.append(" and msgtype in (");
            sb.append(messageTypes.get(0).getValue());
            if (messageTypes.size() > 1) {
                for (int i = 1; i < messageTypes.size(); i++) {
                    sb.append(",");
                    sb.append(messageTypes.get(i).getValue());
                }
            }
            sb.append(")");
        }
        List<Integer> messageSubTypes = msgSearchOption.getMessageSubTypes();
        if (!com.netease.nimlib.o.f.c((Collection) messageSubTypes)) {
            sb.append(" and subtype in (");
            sb.append(messageSubTypes.get(0));
            if (messageSubTypes.size() > 1) {
                for (int i2 = 1; i2 < messageSubTypes.size(); i2++) {
                    sb.append(",");
                    sb.append(messageSubTypes.get(i2));
                }
            }
            sb.append(")");
        }
        String d = msgSearchOption.isEnableContentTransfer() ? com.netease.nimlib.database.a.c.d(msgSearchOption.getSearchContent()) : msgSearchOption.getSearchContent();
        List<String> fromIds = msgSearchOption.getFromIds();
        if (!TextUtils.isEmpty(d) || !com.netease.nimlib.o.f.c((Collection) fromIds)) {
            sb.append(" and (");
            boolean z = !TextUtils.isEmpty(d);
            if (z) {
                sb.append("content like ");
                sb.append(com.netease.nimlib.database.a.c.b(d));
            }
            if (fromIds != null && fromIds.size() > 0) {
                if (z) {
                    sb.append(" or ");
                }
                sb.append("fromid in (");
                sb.append("'");
                sb.append(com.netease.nimlib.database.a.c.a(fromIds.get(0)));
                sb.append("'");
                if (fromIds.size() > 1) {
                    for (int i3 = 1; i3 < fromIds.size(); i3++) {
                        sb.append(",");
                        sb.append("'");
                        sb.append(com.netease.nimlib.database.a.c.a(fromIds.get(i3)));
                        sb.append("'");
                    }
                }
                sb.append(")");
            }
            sb.append(")");
        }
        boolean z2 = msgSearchOption.getOrder() == SearchOrderEnum.ASC;
        sb.append(" ORDER BY time ");
        sb.append(z2 ? "ASC" : "DESC");
        int limit = msgSearchOption.getLimit();
        if (limit > 0) {
            sb.append(" limit ");
            sb.append(limit);
        }
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories(sb.toString());
        if (!z2 && !queryMsgHistories.isEmpty()) {
            Collections.reverse(queryMsgHistories);
        }
        return queryMsgHistories;
    }

    public static List<IMMessage> searchAllMessage(MsgSearchOption msgSearchOption) {
        com.netease.nimlib.log.b.v(String.format("searchAllMessage MsgSearchOption = %s", msgSearchOption));
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where 1=1");
        long startTime = msgSearchOption.getStartTime();
        long endTime = msgSearchOption.getEndTime() == 0 ? Long.MAX_VALUE : msgSearchOption.getEndTime();
        sb.append(" and time>");
        sb.append(startTime);
        sb.append(" and time<");
        sb.append(endTime);
        boolean isAllMessageTypes = msgSearchOption.isAllMessageTypes();
        List<MsgTypeEnum> messageTypes = msgSearchOption.getMessageTypes();
        if (!isAllMessageTypes) {
            if (com.netease.nimlib.o.f.c((Collection) messageTypes)) {
                messageTypes = new ArrayList<>();
                messageTypes.add(MsgTypeEnum.text);
            }
            sb.append(" and msgtype in (");
            sb.append(messageTypes.get(0).getValue());
            if (messageTypes.size() > 1) {
                for (int i = 1; i < messageTypes.size(); i++) {
                    sb.append(",");
                    sb.append(messageTypes.get(i).getValue());
                }
            }
            sb.append(")");
        }
        List<Integer> messageSubTypes = msgSearchOption.getMessageSubTypes();
        if (!com.netease.nimlib.o.f.c((Collection) messageSubTypes)) {
            sb.append(" and subtype in (");
            sb.append(messageSubTypes.get(0));
            if (messageSubTypes.size() > 1) {
                for (int i2 = 1; i2 < messageSubTypes.size(); i2++) {
                    sb.append(",");
                    sb.append(messageSubTypes.get(i2));
                }
            }
            sb.append(")");
        }
        String d = msgSearchOption.isEnableContentTransfer() ? com.netease.nimlib.database.a.c.d(msgSearchOption.getSearchContent()) : msgSearchOption.getSearchContent();
        List<String> fromIds = msgSearchOption.getFromIds();
        if (!TextUtils.isEmpty(d) || !com.netease.nimlib.o.f.c((Collection) fromIds)) {
            sb.append(" and (");
            boolean z = !TextUtils.isEmpty(d);
            if (z) {
                sb.append("content like ");
                sb.append(com.netease.nimlib.database.a.c.b(d));
            }
            if (fromIds != null && fromIds.size() > 0) {
                if (z) {
                    sb.append(" or ");
                }
                sb.append("fromid in (");
                sb.append("'");
                sb.append(com.netease.nimlib.database.a.c.a(fromIds.get(0)));
                sb.append("'");
                if (fromIds.size() > 1) {
                    for (int i3 = 1; i3 < fromIds.size(); i3++) {
                        sb.append(",");
                        sb.append("'");
                        sb.append(com.netease.nimlib.database.a.c.a(fromIds.get(i3)));
                        sb.append("'");
                    }
                }
                sb.append(")");
            }
            sb.append(")");
        }
        boolean z2 = msgSearchOption.getOrder() == SearchOrderEnum.ASC;
        sb.append(" ORDER BY time ");
        sb.append(z2 ? "ASC" : "DESC");
        int limit = msgSearchOption.getLimit();
        if (limit > 0) {
            sb.append(" limit ");
            sb.append(limit);
        }
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories(sb.toString());
        if (!z2 && !queryMsgHistories.isEmpty()) {
            Collections.reverse(queryMsgHistories);
        }
        return queryMsgHistories;
    }

    public static List<RecentContact> queryRecentContacts(int i) {
        return MsgDBHelperInternal.queryRecentContacts("select uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension from lstmsg order by time desc limit " + i);
    }

    public static q queryRecentContact(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperInternal.queryRecentContact("select uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension from lstmsg where uid='" + com.netease.nimlib.database.a.c.a(str) + "' and sessiontype='" + sessionTypeEnum.getValue() + "'");
    }

    public static long queryRoamMsgHasMoreTime(String str, SessionTypeEnum sessionTypeEnum) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(String.format("SELECT time FROM roam_msg_has_more WHERE session_id='%s' AND session_type='%s'", com.netease.nimlib.database.a.c.a(str), Integer.valueOf(sessionTypeEnum.getValue())));
        if (rawQuery != null) {
            r5 = rawQuery.moveToNext() ? rawQuery.getLong(0) : 0L;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        com.netease.nimlib.log.b.v(String.format("queryRoamMsgHasMoreTime(%s, %s): %s", str, sessionTypeEnum, Long.valueOf(r5)));
        return r5;
    }

    public static long queryRoamMsgHasMoreServerId(String str, SessionTypeEnum sessionTypeEnum) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(String.format("SELECT serverid FROM roam_msg_has_more WHERE session_id='%s' AND session_type='%s'", com.netease.nimlib.database.a.c.a(str), Integer.valueOf(sessionTypeEnum.getValue())));
        if (rawQuery != null) {
            r5 = rawQuery.moveToNext() ? rawQuery.getLong(0) : 0L;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        com.netease.nimlib.log.b.v(String.format("queryRoamMsgHasMoreTime(%s, %s): %s", str, sessionTypeEnum, Long.valueOf(r5)));
        return r5;
    }

    public static int queryReplyCount(String str, String str2, SessionTypeEnum sessionTypeEnum) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT COUNT(1) FROM msghistory WHERE threadmsgidclient='" + com.netease.nimlib.database.a.c.a(str) + "' AND id='" + com.netease.nimlib.database.a.c.a(str2) + "' AND sessiontype=" + sessionTypeEnum.getValue());
        int i = 0;
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToNext()) {
                    i = rawQuery.getInt(0);
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th2;
                }
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return i;
    }

    public static ArrayList<QuickCommentOption> queryQuickCommentByUuid(String str) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(String.format("SELECT operator, type, time, ext FROM quick_comment WHERE uuid='%s'", com.netease.nimlib.database.a.c.a(str)));
        ArrayList<QuickCommentOption> arrayList = new ArrayList<>();
        if (rawQuery == null) {
            return arrayList;
        }
        while (rawQuery.moveToNext()) {
            arrayList.add(new QuickCommentOption(rawQuery.getString(0), rawQuery.getLong(1), rawQuery.getLong(2), rawQuery.getString(3)));
        }
        if (!rawQuery.isClosed()) {
            rawQuery.close();
        }
        return arrayList;
    }

    public static List<MsgPinDbOption> queryMsgPin(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList(0);
        }
        return MsgDBHelperInternal.readMsgPinDbOptionList(String.format("SELECT uuid, session_id, operator, ext, create_time, update_time FROM msg_pin WHERE session_id='%s'", com.netease.nimlib.database.a.c.a(str)), -1);
    }

    public static boolean isStickTopSession(String str, SessionTypeEnum sessionTypeEnum) {
        boolean z = false;
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(String.format("SELECT COUNT(1) FROM session_stick_top WHERE session_id='%s' AND session_type=%s", com.netease.nimlib.database.a.c.a(str), Integer.valueOf(sessionTypeEnum.getValue())));
        if (rawQuery == null) {
            return false;
        }
        if (rawQuery.moveToNext() && rawQuery.getInt(0) > 0) {
            z = true;
        }
        if (!rawQuery.isClosed()) {
            rawQuery.close();
        }
        return z;
    }

    public static void updateSyncSelfMessageStatus(List<IMMessageImpl> list) {
        if (list == null || list.isEmpty()) {
            com.netease.nimlib.log.b.L("updateSyncSelfMessageStatus msgList is empty");
            return;
        }
        MsgDBHelperUtils.database().f();
        try {
            for (IMMessageImpl iMMessageImpl : list) {
                StringBuilder sb = new StringBuilder();
                sb.append("UPDATE msghistory set");
                if (iMMessageImpl.getStatus() != null) {
                    sb.append(" status='");
                    sb.append(iMMessageImpl.getStatus().getValue());
                    sb.append("',");
                }
                if (iMMessageImpl.getTime() > 0) {
                    sb.append(" time='");
                    sb.append(iMMessageImpl.getTime());
                    sb.append("',");
                }
                if (iMMessageImpl.getServerId() > 0) {
                    sb.append(" serverid='");
                    sb.append(iMMessageImpl.getServerId());
                    sb.append("',");
                }
                sb.append(" isblacked='");
                sb.append(iMMessageImpl.isInBlackList() ? 1 : 0);
                sb.append("',");
                sb.deleteCharAt(sb.length() - 1);
                sb.append(" where uuid='");
                sb.append(iMMessageImpl.getUuid());
                sb.append("'");
                MsgDBHelperUtils.database().a(sb.toString());
                com.netease.nimlib.log.b.L("updateSyncSelfMessageStatus update uuid = " + iMMessageImpl.getUuid());
            }
            MsgDBHelperUtils.database().h();
            com.netease.nimlib.log.b.L("updateSyncSelfMessageStatus update success");
        } finally {
            MsgDBHelperUtils.database().g();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void updateMessageStatus(com.netease.nimlib.session.IMMessageImpl r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "UPDATE msghistory set"
            r0.append(r1)
            com.netease.nimlib.sdk.msg.constant.MsgStatusEnum r1 = r7.getStatus()
            r2 = 0
            java.lang.String r3 = "',"
            r4 = 1
            if (r1 == 0) goto L29
            java.lang.String r1 = " status='"
            r0.append(r1)
            com.netease.nimlib.sdk.msg.constant.MsgStatusEnum r1 = r7.getStatus()
            int r1 = r1.getValue()
            r0.append(r1)
            r0.append(r3)
            r1 = 1
            goto L2a
        L29:
            r1 = 0
        L2a:
            com.netease.nimlib.sdk.msg.constant.AttachStatusEnum r5 = r7.getAttachStatus()
            if (r5 == 0) goto L44
            java.lang.String r1 = " status2='"
            r0.append(r1)
            com.netease.nimlib.sdk.msg.constant.AttachStatusEnum r1 = r7.getAttachStatus()
            int r1 = r1.getValue()
            r0.append(r1)
            r0.append(r3)
            r1 = 1
        L44:
            com.netease.nimlib.sdk.msg.attachment.MsgAttachment r5 = r7.getAttachment()
            java.lang.String r6 = r7.getAttachStrOnly()
            if (r5 == 0) goto L6b
            java.lang.String r1 = r5.toJson(r2)
            boolean r2 = defpackage.C$r8$backportedMethods$utility$Objects$2$equals.equals(r6, r1)
            if (r2 != 0) goto L5b
            r7.setAttachStrOnly(r1)
        L5b:
            java.lang.String r2 = " attach='"
            r0.append(r2)
            java.lang.String r1 = com.netease.nimlib.database.a.c.a(r1)
            r0.append(r1)
            r0.append(r3)
            goto L76
        L6b:
            boolean r2 = android.text.TextUtils.isEmpty(r6)
            if (r2 != 0) goto L77
            java.lang.String r1 = " attach=null,"
            r0.append(r1)
        L76:
            r1 = 1
        L77:
            if (r1 == 0) goto L9d
            int r1 = r0.length()
            int r1 = r1 - r4
            r0.deleteCharAt(r1)
            java.lang.String r1 = " where uuid='"
            r0.append(r1)
            java.lang.String r7 = r7.getUuid()
            r0.append(r7)
            java.lang.String r7 = "'"
            r0.append(r7)
            com.netease.nimlib.database.d r7 = com.netease.nimlib.session.MsgDBHelperUtils.database()
            java.lang.String r0 = r0.toString()
            r7.a(r0)
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.session.MsgDBHelperA.updateMessageStatus(com.netease.nimlib.session.IMMessageImpl):void");
    }

    public static void updateMessageLocalExt(IMMessageImpl iMMessageImpl) {
        MsgDBHelperUtils.database().a("UPDATE msghistory set localext='" + iMMessageImpl.getLocalExtensionStr() + "' where messageid='" + iMMessageImpl.getMessageId() + "'");
    }

    public static void updateMessageCallbackExt(long j, String str) {
        MsgDBHelperUtils.database().a("UPDATE msghistory set callbackext='" + str + "' where messageid='" + j + "'");
    }

    public static void updateMessageStatus(long j, int i) {
        MsgDBHelperUtils.database().a("UPDATE msghistory set status='" + i + "' where messageid='" + j + "'");
    }

    public static void updateAttachStatus(String str, int i) {
        MsgDBHelperUtils.database().a("UPDATE msghistory set status2='" + i + "' where uuid='" + com.netease.nimlib.database.a.c.a(str) + "'");
    }

    public static void setRecentStatus(String str, int i, long j) {
        MsgDBHelperUtils.database().a(j <= 0 ? String.format("UPDATE lstmsg set msgstatus='%s' where messageId='%s'", Integer.valueOf(i), str) : String.format("UPDATE lstmsg set msgstatus='%s',time='%s' where messageId='%s'", Integer.valueOf(i), Long.valueOf(j), str));
    }

    public static void setMessageBlacked(long j, boolean z) {
        MsgDBHelperUtils.database().a(String.format("UPDATE msghistory set isblacked='%s' where messageid='%s'", Integer.valueOf(z ? 1 : 0), Long.valueOf(j)));
    }

    public static void setRecentRead(String str, int i) {
        MsgDBHelperUtils.database().a("update lstmsg set unreadnum = 0 where uid='" + com.netease.nimlib.database.a.c.a(str) + "' and sessiontype='" + i + "'");
    }

    public static void updateRecentUnreadNum(String str, SessionTypeEnum sessionTypeEnum, int i) {
        MsgDBHelperUtils.database().a("update lstmsg set unreadnum=" + i + " where uid='" + com.netease.nimlib.database.a.c.a(str) + "' and sessiontype='" + sessionTypeEnum.getValue() + "'");
    }

    public static void updateRecent(RecentContact recentContact) {
        MsgDBHelperUtils.database().a("UPDATE lstmsg set tag='" + recentContact.getTag() + "',extension='" + com.netease.nimlib.database.a.c.a(j.a(recentContact.getExtension())) + "' where uid='" + com.netease.nimlib.database.a.c.a(recentContact.getContactId()) + "' and sessiontype='" + recentContact.getSessionType().getValue() + "'");
    }

    public static void updateRoamMsgHasMoreTime(RoamMsgHasMoreOption roamMsgHasMoreOption) {
        MsgDBHelperUtils.database().a("UPDATE roam_msg_has_more SET time='" + roamMsgHasMoreOption.getTime() + "', serverid='" + roamMsgHasMoreOption.getServerId() + "' WHERE session_id='" + roamMsgHasMoreOption.getSessionId() + "' AND session_type='" + roamMsgHasMoreOption.getSessionType().getValue() + "'");
        com.netease.nimlib.log.b.v(String.format("updateRoamMsgHasMoreTime(%s)", roamMsgHasMoreOption));
    }

    public static void updateCollectInfo(a aVar) {
        if (aVar == null) {
            return;
        }
        MsgDBHelperUtils.database().a("UPDATE collect_info SET type='" + aVar.getType() + "', data='" + com.netease.nimlib.database.a.c.a(aVar.getData()) + "', ext='" + com.netease.nimlib.database.a.c.a(aVar.getExt()) + "', uniqueId='" + com.netease.nimlib.database.a.c.a(aVar.getUniqueId()) + "', createTime='" + aVar.getCreateTime() + "', updateTime='" + aVar.getUpdateTime() + "' WHERE id='" + aVar.getId() + "'");
    }

    public static void updateMsgPin(String str, String str2, String str3, long j) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        MsgDBHelperUtils.database().a("UPDATE msg_pin SET ext='" + com.netease.nimlib.database.a.c.a(str3) + "', update_time='" + j + "' WHERE uuid='" + com.netease.nimlib.database.a.c.a(str) + "' AND session_id='" + com.netease.nimlib.database.a.c.a(str2) + "'");
    }

    public static void updateStickTopSession(String str, SessionTypeEnum sessionTypeEnum, String str2, long j) {
        if (sessionTypeEnum == null || TextUtils.isEmpty(str)) {
            return;
        }
        MsgDBHelperUtils.database().a("UPDATE session_stick_top SET ext='" + com.netease.nimlib.database.a.c.a(str2) + "', update_time='" + j + "' WHERE session_id='" + com.netease.nimlib.database.a.c.a(str) + "' AND session_type='" + sessionTypeEnum.getValue() + "'");
    }

    public static int deleteMessage(IMMessageImpl iMMessageImpl, boolean z) {
        if (iMMessageImpl == null) {
            return 0;
        }
        int a = MsgDBHelperUtils.database().a("msghistory", String.format("uuid='%s'", iMMessageImpl.getUuid()));
        if (z) {
            MsgDBHelperInternal.recordDelete(iMMessageImpl.getUuid(), iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType());
        }
        com.netease.nimlib.search.b.g().a(iMMessageImpl.getMessageId());
        if (a != 0 && a != 1) {
            com.netease.nimlib.log.b.N("warn: delete one msg but result is " + a);
        }
        return a;
    }

    public static int deleteMessage(List<? extends IMMessage> list, boolean z) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return 0;
        }
        int a = MsgDBHelperUtils.database().a("msghistory", String.format("uuid IN (%s)", MsgDBHelperUtils.toString(list, new MsgDBHelperUtils.IGetString() { // from class: com.netease.nimlib.session.-$$Lambda$MsgDBHelperA$WpG970ZRfRfGyMqz2hl8ueYXoHw
            @Override // com.netease.nimlib.session.MsgDBHelperUtils.IGetString
            public final String getString(Object obj) {
                String format;
                format = String.format("'%s'", ((IMMessage) obj).getUuid());
                return format;
            }
        })));
        for (IMMessage iMMessage : list) {
            if (iMMessage instanceof IMMessageImpl) {
                if (z) {
                    MsgDBHelperInternal.recordDelete(iMMessage.getUuid(), iMMessage.getSessionId(), iMMessage.getSessionType());
                }
                com.netease.nimlib.search.b.g().a(((IMMessageImpl) iMMessage).getMessageId());
            }
        }
        return a;
    }

    public static void clearMessage(String str, SessionTypeEnum sessionTypeEnum, boolean z) {
        MsgDBHelperUtils.database().a("DELETE FROM msghistory where (id='" + com.netease.nimlib.database.a.c.a(str) + "' and sessiontype='" + sessionTypeEnum.getValue() + "')");
        if (z) {
            MsgDBHelperInternal.recordClearContact(str, sessionTypeEnum, System.currentTimeMillis());
        }
        com.netease.nimlib.search.b.g().a(sessionTypeEnum, str);
    }

    public static void deleteRecentContact(String str, SessionTypeEnum sessionTypeEnum) {
        MsgDBHelperUtils.database().a("DELETE FROM lstmsg where uid = '" + com.netease.nimlib.database.a.c.a(str) + "' and sessiontype='" + sessionTypeEnum.getValue() + "'");
    }

    public static void deleteRoamMsgHasMoreTime(String str, SessionTypeEnum sessionTypeEnum) {
        MsgDBHelperUtils.database().a("DELETE FROM roam_msg_has_more where session_id='" + com.netease.nimlib.database.a.c.a(str) + "' and session_type='" + sessionTypeEnum.getValue() + "'");
        com.netease.nimlib.log.b.v(String.format("deleteRoamMsgHasMoreTime(%s, %s)", str, sessionTypeEnum));
    }

    public static void deleteQuickComment(String str, String str2, long j) {
        MsgDBHelperUtils.database().a("DELETE FROM quick_comment where uuid='" + com.netease.nimlib.database.a.c.a(str) + "' and operator='" + com.netease.nimlib.database.a.c.a(str2) + "' and type=" + j);
    }

    public static void deleteQuickComment(String str) {
        MsgDBHelperUtils.database().a("DELETE FROM quick_comment where uuid='" + com.netease.nimlib.database.a.c.a(str) + "'");
    }

    public static void deleteCollectInfo(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Long l : list) {
            if (l != null) {
                sb.append(", ");
                sb.append("'");
                sb.append(l);
                sb.append("'");
            }
        }
        MsgDBHelperUtils.database().a(String.format("DELETE FROM collect_info where id IN (%s)", sb.substring(2)));
    }

    public static void deleteMsgPin(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        MsgDBHelperUtils.database().a(String.format("DELETE FROM msg_pin WHERE uuid='%s' AND session_id='%s'", str, str2));
    }

    public static void deleteMsgPin(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        MsgDBHelperUtils.database().a(String.format("DELETE FROM msg_pin WHERE session_id='%s'", str));
    }

    public static void deleteStickTopSession(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        MsgDBHelperUtils.database().a(String.format("DELETE FROM session_stick_top WHERE session_id='%s'", str));
    }

    public static boolean hasDeleteTag(String str) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT COUNT(1) FROM delete_message_record WHERE uuid='" + com.netease.nimlib.database.a.c.a(str) + "'");
        return rawQuery != null && rawQuery.moveToNext() && rawQuery.getLong(0) > 0;
    }

    public static Set<String> hasDeleteTag(Collection<IMMessageImpl> collection) {
        HashSet hashSet = new HashSet();
        if (collection != null && !collection.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (IMMessageImpl iMMessageImpl : collection) {
                if (iMMessageImpl != null) {
                    String uuid = iMMessageImpl.getUuid();
                    if (!TextUtils.isEmpty(uuid)) {
                        sb.append(", ");
                        sb.append("'");
                        sb.append(uuid);
                        sb.append("'");
                    }
                }
            }
            Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT uuid FROM delete_message_record WHERE uuid IN (" + sb.substring(2) + ")");
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    hashSet.add(rawQuery.getString(0));
                }
                if (!rawQuery.isClosed()) {
                    rawQuery.close();
                }
            }
        }
        return hashSet;
    }

    public static long getClearSessionTime(String str, SessionTypeEnum sessionTypeEnum) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT time FROM clear_message_record WHERE session_id='" + str + "' AND session_type=" + sessionTypeEnum.getValue() + "");
        if (rawQuery == null || !rawQuery.moveToNext()) {
            return 0L;
        }
        return rawQuery.getLong(0);
    }

    public static ArrayList<SystemMessage> querySystemMessages(int i, int i2) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT messageid, id, fromid, type, time, status, content, attach, unread FROM system_msg where type!=6 ORDER BY time desc LIMIT " + i2 + " OFFSET " + i);
        ArrayList<SystemMessage> arrayList = new ArrayList<>();
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                arrayList.add(MsgDBHelperUtils.systemMsgFromCursor(rawQuery));
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    public static ArrayList<SystemMessage> querySystemMessage(List<SystemMessageType> list, int i, int i2) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT messageid, id, fromid, type, time, status, content, attach, unread FROM system_msg where type in(" + MsgDBHelperUtils.buildTypeSql(list) + ") ORDER BY time desc LIMIT " + i2 + " OFFSET " + i);
        ArrayList<SystemMessage> arrayList = new ArrayList<>();
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                arrayList.add(MsgDBHelperUtils.systemMsgFromCursor(rawQuery));
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    public static void setSystemMessageStatus(long j, SystemMessageStatus systemMessageStatus) {
        MsgDBHelperUtils.database().a("UPDATE system_msg SET status='" + systemMessageStatus.getValue() + "' where messageid='" + j + "'");
    }

    public static void setAllSystemMessageRead(List<SystemMessageType> list) {
        MsgDBHelperUtils.database().a("UPDATE system_msg SET unread='0' where type in(" + MsgDBHelperUtils.buildTypeSql(list) + ")");
    }

    public static void setSystemMessageRead(long j) {
        MsgDBHelperUtils.database().a("UPDATE system_msg SET unread='0' where messageid='" + j + "'");
    }

    public static int querySystemMessageUnreadNum(List<SystemMessageType> list) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT count(*) FROM system_msg where unread=='1' and type in(" + MsgDBHelperUtils.buildTypeSql(list) + ")");
        if (rawQuery != null) {
            r0 = rawQuery.moveToNext() ? rawQuery.getInt(0) : 0;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r0;
    }

    public static void deleteSystemMessage(long j) {
        MsgDBHelperUtils.database().a("DELETE FROM system_msg where messageid='" + j + "'");
    }

    public static void clearSystemMessages(List<SystemMessageType> list) {
        MsgDBHelperUtils.database().a("DELETE FROM system_msg where type in(" + MsgDBHelperUtils.buildTypeSql(list) + ")");
    }

    public static void saveMessageReceipt(List<f> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            f fVar = list.get(i);
            if (sb.length() == 0) {
                sb.append(" select '");
            } else {
                sb.append(" union select '");
            }
            sb.append(com.netease.nimlib.database.a.c.a(fVar.a));
            sb.append("','");
            sb.append(fVar.b);
            sb.append("','");
            sb.append(fVar.c);
            sb.append("'");
            if (sb.length() > 10000) {
                MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO message_receipt (session_id,time,max_time)" + ((Object) sb));
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO message_receipt (session_id,time,max_time)" + ((Object) sb));
        }
    }

    public static Map<String, f> queryMessageReceipt(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT session_id,time,max_time FROM message_receipt where session_id in(" + MsgDBHelperUtils.buildSessionIdsSql(list) + ")");
        HashMap hashMap = new HashMap(list.size());
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                f readReceiptFromCursor = MsgDBHelperUtils.readReceiptFromCursor(rawQuery);
                hashMap.put(readReceiptFromCursor.a, readReceiptFromCursor);
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return hashMap;
    }

    public static void saveSendReceiptRecord(MessageReceipt messageReceipt) {
        MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO send_receipt_record (session_id,time) values ('" + com.netease.nimlib.database.a.c.a(messageReceipt.getSessionId()) + "','" + messageReceipt.getTime() + "')");
    }

    public static long getSessionLastReceivedMsgTimeTag(String str, SessionTypeEnum sessionTypeEnum) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT max(time) FROM msghistory where id='" + com.netease.nimlib.database.a.c.a(str) + "' and sessiontype='" + sessionTypeEnum.getValue() + "' and direct=1");
        if (rawQuery != null) {
            r0 = rawQuery.moveToNext() ? rawQuery.getLong(0) : 0L;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r0;
    }

    public static void saveSessionReadRecord(String str, SessionTypeEnum sessionTypeEnum, long j) {
        com.netease.nimlib.log.b.y("save session record: sessionId=" + str + ", timetag=" + j);
        MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO session_read_record (session_id,session_type,time) values ('" + com.netease.nimlib.database.a.c.a(str) + "','" + sessionTypeEnum.getValue() + "','" + j + "')");
    }

    public static long querySessionReadTimeTag(String str, SessionTypeEnum sessionTypeEnum) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery(String.format("SELECT time FROM session_read_record where session_id='%s' and session_type='%s'", com.netease.nimlib.database.a.c.a(str), Integer.valueOf(sessionTypeEnum.getValue())));
        if (rawQuery != null) {
            r2 = rawQuery.moveToNext() ? rawQuery.getLong(0) : 0L;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r2;
    }

    public static ArrayList<IMMessage> queryUnreadMessages(String str, SessionTypeEnum sessionTypeEnum, long j) {
        return MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where id='" + com.netease.nimlib.database.a.c.a(str) + "' and sessiontype='" + sessionTypeEnum.getValue() + "' and direct='1' and time > " + j);
    }

    public static void saveSenderNick(String str, String str2) {
        MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO sender_nick (account,nick) values ('" + com.netease.nimlib.database.a.c.a(str) + "','" + com.netease.nimlib.database.a.c.a(str2) + "')");
    }

    public static void saveSenderNickMap(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("saveSenderNickMap count = ");
        sb.append(map == null ? 0 : map.size());
        com.netease.nimlib.log.b.L(sb.toString());
        if (map == null || map.isEmpty()) {
            return;
        }
        MsgDBHelperUtils.database().f();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO sender_nick (account,nick) values ('" + com.netease.nimlib.database.a.c.a(entry.getKey()) + "','" + com.netease.nimlib.database.a.c.a(entry.getValue()) + "')");
            }
            MsgDBHelperUtils.database().h();
            com.netease.nimlib.log.b.L("saveSenderNickMap success");
        } finally {
            MsgDBHelperUtils.database().g();
        }
    }

    public static Map<String, String> queryAllSenderNick() {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT account,nick FROM sender_nick");
        HashMap hashMap = new HashMap();
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                hashMap.put(rawQuery.getString(0), rawQuery.getString(1));
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return hashMap;
    }

    public static void saveRevokeMessage(String str) {
        MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO revoke_message (uuid) values ('" + com.netease.nimlib.database.a.c.a(str) + "')");
    }

    public static String queryRevokeMessage(String str) {
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT uuid FROM revoke_message where uuid='" + str + "'");
        if (rawQuery != null) {
            r0 = rawQuery.moveToNext() ? rawQuery.getString(0) : null;
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        }
        return r0;
    }

    public static void markHasSendTeamMsgAck(List<String> list) {
        MsgDBHelperUtils.database().a("UPDATE msghistory set acksend='1' where uuid in(" + MsgDBHelperUtils.buildSessionIdsSql(list) + ")");
    }

    public static void updateTeamMsgAckCount(String str, int i, int i2) {
        MsgDBHelperUtils.database().a("UPDATE msghistory set ackcount='" + i + "', unackcount='" + i2 + "' where uuid='" + str + "' and (ackcount<'" + i + "' or ackcount='0')");
    }

    public static void saveTeamMsgAckDetail(TeamMsgAckInfo teamMsgAckInfo, String str) {
        ArrayList arrayList = new ArrayList();
        if (teamMsgAckInfo.getAckAccountList() != null) {
            Iterator<String> it = teamMsgAckInfo.getAckAccountList().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toLowerCase());
            }
        }
        if (teamMsgAckInfo.getUnAckAccountList() != null) {
            Iterator<String> it2 = teamMsgAckInfo.getUnAckAccountList().iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next().toLowerCase());
            }
        }
        Collections.sort(arrayList);
        JSONArray jSONArray = new JSONArray();
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            jSONArray.put((String) it3.next());
        }
        ContentValues contentValues = new ContentValues(4);
        contentValues.put("msgid", teamMsgAckInfo.getMsgId());
        contentValues.put("tid", teamMsgAckInfo.getTeamId());
        contentValues.put("snapshot", jSONArray.toString());
        contentValues.put("bitmap", str);
        MsgDBHelperUtils.database().b("team_msg_ack", null, contentValues);
    }

    public static void updateTeamMsgAckDetail(String str, String str2) {
        MsgDBHelperUtils.database().a("UPDATE team_msg_ack set bitmap='" + str2 + "' where msgid='" + str + "'");
    }

    public static TeamMsgAckInfo queryTeamMsgAckDetail(String str) {
        String str2;
        String str3;
        String str4;
        boolean z;
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT msgid,tid,snapshot,bitmap FROM team_msg_ack where msgid='" + str + "'");
        if (rawQuery != null) {
            z = true;
            if (rawQuery.moveToNext()) {
                str2 = rawQuery.getString(1);
                str3 = rawQuery.getString(2);
                str4 = rawQuery.getString(3);
            } else {
                str2 = null;
                str3 = null;
                str4 = null;
                z = false;
            }
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
        } else {
            str2 = null;
            str3 = null;
            str4 = null;
            z = false;
        }
        if (z && str3 != null && str4 != null) {
            try {
                JSONArray jSONArray = new JSONArray(str3);
                ArrayList arrayList = new ArrayList(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                Pair<List<String>, List<String>> parseBitmap = MsgDBHelperUtils.parseBitmap(com.netease.nimlib.o.j.a(str4), arrayList);
                return parseBitmap == null ? new TeamMsgAckInfo(str2, str, (List<String>) null, (List<String>) null) : new TeamMsgAckInfo(str2, str, (List<String>) parseBitmap.first, (List<String>) parseBitmap.second);
            } catch (JSONException e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.N("queryTeamMsgAckDetail parse error, e=" + e.getMessage());
            }
        }
        return null;
    }

    public static void deleteRangeHistory(String str, SessionTypeEnum sessionTypeEnum, long j, long j2) {
        MsgDBHelperUtils.database().a(String.format("DELETE FROM msghistory where(id='%s' and sessiontype='%s' and time> %s and time<%s)", com.netease.nimlib.database.a.c.a(str), Integer.valueOf(sessionTypeEnum.getValue()), Long.valueOf(j), Long.valueOf(j2)));
        com.netease.nimlib.search.b.g().a(sessionTypeEnum, str, j, j2);
    }

    public static void migrateMessages(Context context, String str, String str2, boolean z) {
        try {
            String str3 = com.netease.nimlib.c.g() + "/" + str2;
            String str4 = com.netease.nimlib.c.i().databaseEncryptKey;
            com.netease.nimlib.database.d dVar = new com.netease.nimlib.database.d(context, str3, str4, com.netease.nimlib.database.encrypt.d.a(com.netease.nimlib.database.d.a(str3, false), com.netease.nimlib.database.d.a(str3, true), str4));
            dVar.a("ATTACH DATABASE '" + MsgDBHelperUtils.dbPath(str) + "' AS src");
            dVar.a("INSERT INTO msghistory(uuid,serverid,time,content,msgtype,sessiontype,fromid,id,direct,status,status2,attach) SELECT uuid,serverid,time,content,msgtype,sessiontype,fromid,id,direct,status,status2,attach FROM src.msghistory");
            dVar.a("INSERT OR IGNORE INTO lstmsg(uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension) SELECT uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension FROM src.lstmsg");
            dVar.a("DETACH DATABASE src");
            if (z) {
                dVar.a("UPDATE msghistory SET fromid='" + com.netease.nimlib.database.a.c.a(str2) + "' WHERE fromid='" + com.netease.nimlib.database.a.c.a(str) + "'");
                dVar.a("UPDATE lstmsg SET fromuid='" + com.netease.nimlib.database.a.c.a(str2) + "' WHERE fromuid='" + com.netease.nimlib.database.a.c.a(str) + "'");
            }
            dVar.i();
            List<RecentContact> queryRecentContactsBlock = ((MsgService) NIMClient.getService(MsgService.class)).queryRecentContactsBlock();
            if (queryRecentContactsBlock == null) {
                return;
            }
            com.netease.nimlib.i.b.e(queryRecentContactsBlock);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("MsgDBHelper", "migrateMessages is error", th);
        }
    }

    public static int removeSessionReliableInfo(List<Long> list) {
        String format = String.format("id IN (%s)", com.netease.nimlib.o.f.a(list, ",", new f.a() { // from class: com.netease.nimlib.session.-$$Lambda$7ihq3SLa-_IujwU--I33p81mC2g
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return String.valueOf((Long) obj);
            }
        }));
        com.netease.nimlib.log.b.v(String.format("to remove session reliable info with whereClause %s", format));
        return MsgDBHelperUtils.database().a("session_reliable_table", format);
    }

    public static int removeSessionReliableInfo(String str, SessionTypeEnum sessionTypeEnum) {
        String format = String.format("session_id='%s' AND session_type=%s", com.netease.nimlib.database.a.c.a(str), Integer.valueOf(sessionTypeEnum.getValue()));
        com.netease.nimlib.log.b.v(String.format("to remove session reliable info with whereClause %s", format));
        return MsgDBHelperUtils.database().a("session_reliable_table", format);
    }

    public static com.netease.nimlib.session.a.d queryLastSessionReliableInfo(String str, SessionTypeEnum sessionTypeEnum) {
        return (com.netease.nimlib.session.a.d) com.netease.nimlib.o.f.a((Collection) MsgDBHelperInternal.querySessionReliableInfos(String.format("SELECT id,%s FROM session_reliable_table WHERE session_id='%s' AND session_type=%s ORDER BY stop_time DESC LIMIT 1", "session_id, session_type, start_time, start_server_id, start_client_id, stop_time, stop_server_id, stop_client_id", com.netease.nimlib.database.a.c.a(str), Integer.valueOf(sessionTypeEnum.getValue()))));
    }

    public static List<com.netease.nimlib.session.a.d> queryMayOverLappedInfos(com.netease.nimlib.session.a.d dVar) {
        ArrayList arrayList = new ArrayList();
        String e = dVar.e();
        SessionTypeEnum f = dVar.f();
        return (com.netease.nimlib.o.w.a((CharSequence) e) || f == null) ? arrayList : MsgDBHelperInternal.querySessionReliableInfos(String.format("SELECT id,%s FROM session_reliable_table WHERE session_id='%s' AND session_type=%s AND start_time<=%s AND stop_time>=%s", "session_id, session_type, start_time, start_server_id, start_client_id, stop_time, stop_server_id, stop_client_id", com.netease.nimlib.database.a.c.a(e), Integer.valueOf(f.getValue()), Long.valueOf(dVar.l()), Long.valueOf(dVar.i())));
    }

    public static List<com.netease.nimlib.session.a.d> queryParentInfos(com.netease.nimlib.session.a.d dVar) {
        ArrayList arrayList = new ArrayList();
        String e = dVar.e();
        SessionTypeEnum f = dVar.f();
        if (!com.netease.nimlib.o.w.a((CharSequence) e) && f != null) {
            Cursor rawQuery = MsgDBHelperUtils.rawQuery(String.format("SELECT id,%s FROM session_reliable_table WHERE session_id='%s' AND session_type=%s AND ((start_time<%s OR (start_time=%s AND start_client_id='%s')) AND (stop_time>%s OR (stop_time=%s AND stop_client_id='%s')))", "session_id, session_type, start_time, start_server_id, start_client_id, stop_time, stop_server_id, stop_client_id", com.netease.nimlib.database.a.c.a(e), Integer.valueOf(f.getValue()), Long.valueOf(dVar.i()), Long.valueOf(dVar.i()), dVar.h(), Long.valueOf(dVar.l()), Long.valueOf(dVar.l()), dVar.k()));
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    arrayList.add(MsgDBHelperUtils.sessionReliableInfoFromCursor(rawQuery));
                }
                if (!rawQuery.isClosed()) {
                    rawQuery.close();
                }
            }
            com.netease.nimlib.log.b.v(String.format("query parent infos with %s. result is %s", dVar, com.netease.nimlib.o.f.f(arrayList)));
        }
        return arrayList;
    }
}
