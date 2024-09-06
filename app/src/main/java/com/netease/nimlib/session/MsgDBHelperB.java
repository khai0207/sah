package com.netease.nimlib.session;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.MsgService;
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
import com.netease.nimlib.sdk.msg.model.SearchOrderEnum;
import com.netease.nimlib.sdk.msg.model.StickTopSessionInfo;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class MsgDBHelperB {
    public static String repeatPlaceholders(int i) {
        return i <= 0 ? "" : TextUtils.join(",", Collections.nCopies(i, "?"));
    }

    public static ArrayList<IMMessage> queryMessageList(String str, int i, long j, int i2) {
        com.netease.nimlib.log.b.v(String.format("queryMessageList(%s, %s, %s, %s)", str, Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(i2)));
        return MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where id=? and sessiontype=? ORDER BY time desc limit ? offset ?", new String[]{str, String.valueOf(i), String.valueOf(i2), String.valueOf(j)});
    }

    public static ArrayList<IMMessage> queryMessageListEx(List<MsgTypeEnum> list, IMMessageImpl iMMessageImpl, long j, QueryDirectionEnum queryDirectionEnum, int i, boolean z) {
        int i2;
        com.netease.nimlib.log.b.v(String.format("queryMessageListEx(%s, %s, %s, %s, %s), types size is %s", list, IMMessageImpl.toStringSimple(iMMessageImpl), Long.valueOf(j), queryDirectionEnum, Integer.valueOf(i), Integer.valueOf(com.netease.nimlib.o.f.e(list))));
        String sessionId = iMMessageImpl.getSessionId();
        int value = iMMessageImpl.getSessionType().getValue();
        boolean z2 = iMMessageImpl.getMessageId() > 0;
        boolean z3 = com.netease.nimlib.o.f.c((Collection) list) || list.contains(iMMessageImpl.getMsgType());
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory WHERE id=? AND sessiontype=?");
        arrayList.add(sessionId);
        arrayList.add(String.valueOf(value));
        boolean z4 = queryDirectionEnum == QueryDirectionEnum.QUERY_NEW;
        if (z4) {
            sb.append(" AND time>=?");
            arrayList.add(String.valueOf(iMMessageImpl.getTime()));
        } else if (iMMessageImpl.getTime() > 0) {
            sb.append(" AND time<=?");
            arrayList.add(String.valueOf(iMMessageImpl.getTime()));
        }
        if (j > 0) {
            if (z4) {
                sb.append(" AND time<=?");
            } else {
                sb.append(" AND time>=?");
            }
            arrayList.add(String.valueOf(j));
        }
        if (list != null && !list.isEmpty()) {
            sb.append(" AND msgtype in(");
            sb.append(repeatPlaceholders(list.size()));
            sb.append(")");
            Iterator<MsgTypeEnum> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(String.valueOf(it.next().getValue()));
            }
        }
        int i3 = (!z ? z2 : z3 && z2) ? i : i + 1;
        sb.append(" ORDER BY time ");
        sb.append(z4 ? "ASC" : "DESC");
        sb.append(" LIMIT ?");
        arrayList.add(String.valueOf(i3));
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories(sb.toString(), (String[]) arrayList.toArray(new String[0]));
        if ((z && !z3) || !z2) {
            return queryMsgHistories;
        }
        int i4 = 0;
        while (true) {
            if (i4 >= queryMsgHistories.size()) {
                i2 = 0;
                break;
            }
            if (((IMMessageImpl) queryMsgHistories.get(i4)).getMessageId() == iMMessageImpl.getMessageId()) {
                i2 = i4 + 1;
                break;
            }
            i4++;
        }
        if (i2 == 0) {
            return queryMsgHistories;
        }
        for (int i5 = 0; i5 <= i2 - 1; i5++) {
            queryMsgHistories.remove(0);
        }
        if (i2 <= 1) {
            return queryMsgHistories;
        }
        sb.delete(sb.lastIndexOf(" ") + 1, sb.length());
        sb.append("?");
        arrayList.set(arrayList.size() - 1, String.valueOf(i));
        sb.append(" OFFSET ?");
        arrayList.add(String.valueOf(i2));
        return MsgDBHelperInternal.queryMsgHistories(sb.toString(), (String[]) arrayList.toArray(new String[0]));
    }

    public static List<IMMessage> queryMessageListByType(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i) {
        com.netease.nimlib.log.b.v(String.format("queryMessageListByType(%s, %s, %s)", msgTypeEnum, IMMessageImpl.toStringSimple(iMMessage), Integer.valueOf(i)));
        String sessionId = iMMessage.getSessionId();
        int value = iMMessage.getSessionType().getValue();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id=?");
        arrayList.add(sessionId);
        sb.append(" and sessiontype=?");
        arrayList.add(String.valueOf(value));
        if (iMMessage.getTime() > 0) {
            sb.append(" and time<?");
            arrayList.add(String.valueOf(iMMessage.getTime()));
        }
        sb.append(" and msgtype=?");
        arrayList.add(String.valueOf(msgTypeEnum.getValue()));
        sb.append(" ORDER BY time desc");
        sb.append(" limit ?");
        arrayList.add(String.valueOf(i));
        return MsgDBHelperInternal.queryMsgHistories(sb.toString(), (String[]) arrayList.toArray(new String[0]));
    }

    public static List<IMMessage> queryMessageListByType(MsgTypeEnum msgTypeEnum, Long l, int i) {
        String[] strArr;
        String str;
        com.netease.nimlib.log.b.v(String.format("queryMessageListByType(%s, %s, %s)", msgTypeEnum, l, Integer.valueOf(i)));
        if (l == null) {
            str = "SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where msgtype=? ORDER BY time desc";
            strArr = new String[]{String.valueOf(msgTypeEnum.getValue())};
        } else {
            String str2 = "SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where time<? and msgtype=? ORDER BY time desc limit ?";
            strArr = new String[]{String.valueOf(l), String.valueOf(msgTypeEnum.getValue()), String.valueOf(i)};
            str = str2;
        }
        return MsgDBHelperInternal.queryMsgHistories(str, strArr);
    }

    public static ArrayList<IMMessage> queryMessageListBySubtype(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i, int i2) {
        com.netease.nimlib.log.b.v(String.format("queryMessageListBySubtype(%s, %s, %s, %s)", msgTypeEnum, IMMessageImpl.toStringSimple(iMMessage), Integer.valueOf(i), Integer.valueOf(i2)));
        String sessionId = iMMessage.getSessionId();
        int value = iMMessage.getSessionType().getValue();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id=?");
        arrayList.add(sessionId);
        sb.append(" and sessiontype=?");
        arrayList.add(String.valueOf(value));
        sb.append(" and subtype=?");
        arrayList.add(String.valueOf(i2));
        if (iMMessage.getTime() > 0) {
            sb.append(" and time<?");
            arrayList.add(String.valueOf(iMMessage.getTime()));
        }
        sb.append(" and msgtype=?");
        arrayList.add(String.valueOf(msgTypeEnum.getValue()));
        sb.append(" ORDER BY time desc");
        sb.append(" limit ?");
        arrayList.add(String.valueOf(i));
        return MsgDBHelperInternal.queryMsgHistories(sb.toString(), (String[]) arrayList.toArray(new String[0]));
    }

    public static List<IMMessage> queryMsgListByUuid(List<String> list) {
        com.netease.nimlib.log.b.v(String.format("queryMsgListByUuid, uuid size is %s", Integer.valueOf(com.netease.nimlib.o.f.e(list))));
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return new ArrayList(0);
        }
        return MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where uuid in (" + repeatPlaceholders(list.size()) + ")", (String[]) list.toArray(new String[0]));
    }

    public static List<IMMessage> queryMsgListByServerId(List<String> list) {
        if (list == null) {
            return new ArrayList(0);
        }
        com.netease.nimlib.log.b.v(String.format("queryMsgListByServerId, serverId size is %s", Integer.valueOf(com.netease.nimlib.o.f.e(list))));
        return MsgDBHelperInternal.queryMsgHistories("SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory where serverid in (" + repeatPlaceholders(list.size()) + ")", (String[]) list.toArray(new String[0]));
    }

    public static ArrayList<IMMessage> queryMessageByPage(int i, int i2, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" from msghistory order by messageid ");
        sb.append(z ? "asc" : "desc");
        sb.append(" limit ? offset ?");
        return MsgDBHelperInternal.queryMsgHistories(sb.toString(), new String[]{String.valueOf(i), String.valueOf(i2)});
    }

    public static IMMessage queryLatestMessageFilterMsgType(String str, int i, List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id=? and sessiontype=?");
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(String.valueOf(i));
        if (list != null && !list.isEmpty()) {
            sb.append(" and msgtype not in (");
            sb.append(repeatPlaceholders(list.size()));
            sb.append(")");
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(String.valueOf(it.next()));
            }
        }
        sb.append(" ORDER BY time desc limit 1 offset 0");
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories(sb.toString(), (String[]) arrayList.toArray(new String[0]));
        if (queryMsgHistories.size() <= 0) {
            return null;
        }
        return queryMsgHistories.get(0);
    }

    public static List<IMMessage> searchMessageHistory(String str, List<String> list, IMMessage iMMessage, QueryDirectionEnum queryDirectionEnum, int i) {
        String sessionId = iMMessage.getSessionId();
        int value = iMMessage.getSessionType().getValue();
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id=? and sessiontype=?");
        arrayList.add(sessionId);
        arrayList.add(String.valueOf(value));
        boolean z = queryDirectionEnum == QueryDirectionEnum.QUERY_NEW;
        if (iMMessage.getTime() > 0) {
            if (z) {
                sb.append(" and time>?");
            } else {
                sb.append(" and time<?");
            }
            arrayList.add(String.valueOf(iMMessage.getTime()));
        }
        sb.append(" and (");
        if (list != null && !list.isEmpty()) {
            sb.append("fromid in (");
            sb.append(repeatPlaceholders(list.size()));
            sb.append(") or");
            arrayList.addAll(list);
        }
        sb.append(" content like ?)");
        sb.append(" ORDER BY time ");
        sb.append(z ? "ASC" : "DESC");
        sb.append(" limit ?");
        arrayList.add(com.netease.nimlib.database.a.c.c(str));
        arrayList.add(String.valueOf(i));
        return MsgDBHelperInternal.queryMsgHistories(sb.toString(), (String[]) arrayList.toArray(new String[0]));
    }

    public static List<IMMessage> searchAllMessageHistory(String str, List<String> list, long j, int i) {
        String str2 = "SELECT " + MsgDBHelperConstants.msgHistoryColumnStr() + " FROM msghistory WHERE 1=1";
        ArrayList arrayList = new ArrayList();
        if (j > 0) {
            str2 = str2 + " and time<?";
            arrayList.add(String.valueOf(j));
        }
        String str3 = str2 + " and (";
        if (list != null && list.size() > 0) {
            str3 = ((str3 + "fromid in (") + repeatPlaceholders(list.size())) + ") or";
            arrayList.addAll(list);
        }
        arrayList.add(com.netease.nimlib.database.a.c.c(str));
        String str4 = (str3 + " content like ?)") + " ORDER BY time desc limit ?";
        arrayList.add(String.valueOf(i));
        return MsgDBHelperInternal.queryMsgHistories(str4, (String[]) arrayList.toArray(new String[0]));
    }

    public static List<IMMessage> searchMessage(SessionTypeEnum sessionTypeEnum, String str, MsgSearchOption msgSearchOption) {
        com.netease.nimlib.log.b.v(String.format("searchMessage sessionType = %s, sessionId = %s, MsgSearchOption = %s", sessionTypeEnum, str, msgSearchOption));
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id=?");
        sb.append(" and sessiontype=?");
        arrayList.add(str);
        arrayList.add(String.valueOf(sessionTypeEnum.getValue()));
        long startTime = msgSearchOption.getStartTime();
        long endTime = msgSearchOption.getEndTime() == 0 ? Long.MAX_VALUE : msgSearchOption.getEndTime();
        sb.append(" and time>?");
        sb.append(" and time<?");
        arrayList.add(String.valueOf(startTime));
        arrayList.add(String.valueOf(endTime));
        boolean isAllMessageTypes = msgSearchOption.isAllMessageTypes();
        List<MsgTypeEnum> messageTypes = msgSearchOption.getMessageTypes();
        if (!isAllMessageTypes) {
            if (com.netease.nimlib.o.f.c((Collection) messageTypes)) {
                messageTypes = new ArrayList<>();
                messageTypes.add(MsgTypeEnum.text);
            }
            sb.append(" and msgtype in (");
            sb.append(repeatPlaceholders(messageTypes.size()));
            sb.append(")");
            Iterator<MsgTypeEnum> it = messageTypes.iterator();
            while (it.hasNext()) {
                arrayList.add(String.valueOf(it.next().getValue()));
            }
        }
        List<Integer> messageSubTypes = msgSearchOption.getMessageSubTypes();
        if (!com.netease.nimlib.o.f.c((Collection) messageSubTypes)) {
            sb.append(" and subtype in (");
            sb.append(repeatPlaceholders(messageSubTypes.size()));
            sb.append(")");
            Iterator<Integer> it2 = messageSubTypes.iterator();
            while (it2.hasNext()) {
                arrayList.add(String.valueOf(it2.next()));
            }
        }
        String searchContent = msgSearchOption.getSearchContent();
        List<String> fromIds = msgSearchOption.getFromIds();
        if (!TextUtils.isEmpty(searchContent) || !com.netease.nimlib.o.f.c((Collection) fromIds)) {
            sb.append(" and (");
            boolean z = !TextUtils.isEmpty(searchContent);
            if (z) {
                sb.append("content like ?");
                arrayList.add(com.netease.nimlib.database.a.c.c(searchContent));
            }
            if (fromIds != null && fromIds.size() > 0) {
                if (z) {
                    sb.append(" or ");
                }
                sb.append("fromid in (");
                sb.append(repeatPlaceholders(fromIds.size()));
                sb.append(")");
                arrayList.addAll(fromIds);
            }
            sb.append(")");
        }
        boolean z2 = msgSearchOption.getOrder() == SearchOrderEnum.ASC;
        sb.append(" ORDER BY time ");
        sb.append(z2 ? "ASC" : "DESC");
        int limit = msgSearchOption.getLimit();
        if (limit > 0) {
            sb.append(" limit ?");
            arrayList.add(String.valueOf(limit));
        }
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories(sb.toString(), (String[]) arrayList.toArray(new String[0]));
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
        ArrayList arrayList = new ArrayList();
        long startTime = msgSearchOption.getStartTime();
        long endTime = msgSearchOption.getEndTime() == 0 ? Long.MAX_VALUE : msgSearchOption.getEndTime();
        sb.append(" and time>? and time<?");
        arrayList.add(String.valueOf(startTime));
        arrayList.add(String.valueOf(endTime));
        boolean isAllMessageTypes = msgSearchOption.isAllMessageTypes();
        List<MsgTypeEnum> messageTypes = msgSearchOption.getMessageTypes();
        if (!isAllMessageTypes) {
            if (com.netease.nimlib.o.f.c((Collection) messageTypes)) {
                messageTypes = new ArrayList<>();
                messageTypes.add(MsgTypeEnum.text);
            }
            sb.append(" and msgtype in (");
            sb.append(repeatPlaceholders(messageTypes.size()));
            sb.append(")");
            Iterator<MsgTypeEnum> it = messageTypes.iterator();
            while (it.hasNext()) {
                arrayList.add(String.valueOf(it.next().getValue()));
            }
        }
        List<Integer> messageSubTypes = msgSearchOption.getMessageSubTypes();
        if (!com.netease.nimlib.o.f.c((Collection) messageSubTypes)) {
            sb.append(" and subtype in (");
            sb.append(repeatPlaceholders(messageSubTypes.size()));
            sb.append(")");
            Iterator<Integer> it2 = messageSubTypes.iterator();
            while (it2.hasNext()) {
                arrayList.add(String.valueOf(it2.next()));
            }
        }
        String searchContent = msgSearchOption.getSearchContent();
        List<String> fromIds = msgSearchOption.getFromIds();
        if (!TextUtils.isEmpty(searchContent) || !com.netease.nimlib.o.f.c((Collection) fromIds)) {
            sb.append(" and (");
            boolean z = !TextUtils.isEmpty(searchContent);
            if (z) {
                sb.append("content like ?");
                arrayList.add(com.netease.nimlib.database.a.c.c(searchContent));
            }
            if (fromIds != null && fromIds.size() > 0) {
                if (z) {
                    sb.append(" or ");
                }
                sb.append("fromid in (");
                sb.append(repeatPlaceholders(fromIds.size()));
                sb.append(")");
                arrayList.addAll(fromIds);
            }
            sb.append(")");
        }
        boolean z2 = msgSearchOption.getOrder() == SearchOrderEnum.ASC;
        sb.append(" ORDER BY time ");
        sb.append(z2 ? "ASC" : "DESC");
        int limit = msgSearchOption.getLimit();
        if (limit > 0) {
            sb.append(" limit ?");
            arrayList.add(String.valueOf(limit));
        }
        ArrayList<IMMessage> queryMsgHistories = MsgDBHelperInternal.queryMsgHistories(sb.toString(), (String[]) arrayList.toArray(new String[0]));
        if (!z2 && !queryMsgHistories.isEmpty()) {
            Collections.reverse(queryMsgHistories);
        }
        return queryMsgHistories;
    }

    public static int queryReplyCount(String str, String str2, SessionTypeEnum sessionTypeEnum) {
        int i = 0;
        Cursor rawQuery = MsgDBHelperUtils.rawQuery("SELECT COUNT(1) FROM msghistory WHERE threadmsgidclient=? AND id=? AND sessiontype=?", new String[]{str, str2, String.valueOf(sessionTypeEnum.getValue())});
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

    /* JADX WARN: Removed duplicated region for block: B:15:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void updateMessageStatus(com.netease.nimlib.session.IMMessageImpl r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "UPDATE msghistory SET"
            r0.append(r2)
            com.netease.nimlib.sdk.msg.constant.MsgStatusEnum r2 = r7.getStatus()
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L2d
            java.lang.String r2 = " status=?,"
            r0.append(r2)
            com.netease.nimlib.sdk.msg.constant.MsgStatusEnum r2 = r7.getStatus()
            int r2 = r2.getValue()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r1.add(r2)
            r2 = 1
            goto L2e
        L2d:
            r2 = 0
        L2e:
            com.netease.nimlib.sdk.msg.constant.AttachStatusEnum r5 = r7.getAttachStatus()
            if (r5 == 0) goto L49
            java.lang.String r2 = " status2=?,"
            r0.append(r2)
            com.netease.nimlib.sdk.msg.constant.AttachStatusEnum r2 = r7.getAttachStatus()
            int r2 = r2.getValue()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r1.add(r2)
            r2 = 1
        L49:
            com.netease.nimlib.sdk.msg.attachment.MsgAttachment r5 = r7.getAttachment()
            java.lang.String r6 = r7.getAttachStrOnly()
            if (r5 == 0) goto L69
            java.lang.String r2 = r5.toJson(r3)
            boolean r5 = defpackage.C$r8$backportedMethods$utility$Objects$2$equals.equals(r6, r2)
            if (r5 != 0) goto L60
            r7.setAttachStrOnly(r2)
        L60:
            java.lang.String r5 = " attach=?,"
            r0.append(r5)
            r1.add(r2)
            goto L74
        L69:
            boolean r5 = android.text.TextUtils.isEmpty(r6)
            if (r5 != 0) goto L75
            java.lang.String r2 = " attach=null,"
            r0.append(r2)
        L74:
            r2 = 1
        L75:
            if (r2 == 0) goto L9e
            int r2 = r0.length()
            int r2 = r2 - r4
            r0.deleteCharAt(r2)
            java.lang.String r2 = " WHERE uuid=?"
            r0.append(r2)
            java.lang.String r7 = r7.getUuid()
            r1.add(r7)
            java.lang.String[] r7 = new java.lang.String[r3]
            java.lang.Object[] r7 = r1.toArray(r7)
            java.lang.String[] r7 = (java.lang.String[]) r7
            com.netease.nimlib.database.d r1 = com.netease.nimlib.session.MsgDBHelperUtils.database()
            java.lang.String r0 = r0.toString()
            r1.a(r0, r7)
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.session.MsgDBHelperB.updateMessageStatus(com.netease.nimlib.session.IMMessageImpl):void");
    }

    public static void updateMessageLocalExt(IMMessageImpl iMMessageImpl) {
        MsgDBHelperUtils.database().a("UPDATE msghistory SET localext=? WHERE messageid=?", (Object[]) new String[]{iMMessageImpl.getLocalExtensionStr(), String.valueOf(iMMessageImpl.getMessageId())});
    }

    public static void updateMessageStatus(long j, int i) {
        MsgDBHelperUtils.database().a("UPDATE msghistory SET status=?WHERE messageid=?", (Object[]) new String[]{String.valueOf(i), String.valueOf(j)});
    }

    public static int deleteMessage(IMMessageImpl iMMessageImpl, boolean z) {
        if (iMMessageImpl == null) {
            return 0;
        }
        int a = MsgDBHelperUtils.database().a("msghistory", "uuid=?", new String[]{iMMessageImpl.getUuid()});
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
        ArrayList arrayList = new ArrayList();
        Iterator<? extends IMMessage> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getUuid());
        }
        int a = MsgDBHelperUtils.database().a("msghistory", String.format("uuid IN (%s)", repeatPlaceholders(arrayList.size())), (String[]) arrayList.toArray(new String[0]));
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
        MsgDBHelperUtils.database().a("DELETE FROM msghistory WHERE id=? AND sessiontype=?", (Object[]) new String[]{str, String.valueOf(sessionTypeEnum.getValue())});
        if (z) {
            MsgDBHelperInternal.recordClearContact(str, sessionTypeEnum, System.currentTimeMillis());
        }
        com.netease.nimlib.search.b.g().a(sessionTypeEnum, str);
    }

    public static ArrayList<IMMessage> queryUnreadMessages(String str, SessionTypeEnum sessionTypeEnum, long j) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        sb.append("SELECT ");
        sb.append(MsgDBHelperConstants.msgHistoryColumnStr());
        sb.append(" FROM msghistory where id=? and sessiontype=? and direct='1' and time > ?");
        arrayList.add(str);
        arrayList.add(String.valueOf(sessionTypeEnum.getValue()));
        arrayList.add(String.valueOf(j));
        return MsgDBHelperInternal.queryMsgHistories(sb.toString(), (String[]) arrayList.toArray(new String[0]));
    }

    public static void deleteRangeHistory(String str, SessionTypeEnum sessionTypeEnum, long j, long j2) {
        MsgDBHelperUtils.database().a("DELETE FROM msghistory WHERE id=? AND sessiontype=? AND time>? AND time<?", (Object[]) new String[]{str, String.valueOf(sessionTypeEnum.getValue()), String.valueOf(j), String.valueOf(j2)});
        com.netease.nimlib.search.b.g().a(sessionTypeEnum, str, j, j2);
    }

    public static void migrateMessages(Context context, String str, String str2, boolean z) {
        try {
            String str3 = com.netease.nimlib.c.g() + "/" + str2;
            String str4 = com.netease.nimlib.c.i().databaseEncryptKey;
            com.netease.nimlib.database.d dVar = new com.netease.nimlib.database.d(context, str3, str4, com.netease.nimlib.database.encrypt.d.a(com.netease.nimlib.database.d.a(str3, false), com.netease.nimlib.database.d.a(str3, true), str4));
            dVar.a("ATTACH DATABASE ? AS src", (Object[]) new String[]{MsgDBHelperUtils.dbPath(str)});
            dVar.a("INSERT INTO msghistory(uuid,serverid,time,content,msgtype,sessiontype,fromid,id,direct,status,status2,attach) SELECT uuid,serverid,time,content,msgtype,sessiontype,fromid,id,direct,status,status2,attach FROM src.msghistory");
            dVar.a("INSERT OR IGNORE INTO lstmsg(uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension) SELECT uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension FROM src.lstmsg");
            dVar.a("DETACH DATABASE src");
            if (z) {
                dVar.a("UPDATE msghistory SET fromid=? WHERE fromid=?", (Object[]) new String[]{str2, str});
                dVar.a("UPDATE lstmsg SET fromuid=? WHERE fromuid=?", (Object[]) new String[]{str2, str});
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

    public static List<IMMessageImpl> queryMessageListInSeqIdRange(long j, long j2, int[] iArr, int[] iArr2) {
        return MsgDBHelperA.queryMessageListInSeqIdRange(j, j2, iArr, iArr2);
    }

    public static ArrayList<IMMessage> queryMessageListEx(IMMessageImpl iMMessageImpl, long j, long j2, boolean z) {
        return MsgDBHelperA.queryMessageListEx(iMMessageImpl, j, j2, z);
    }

    public static long getMessageTimeByUuid(String str) {
        return MsgDBHelperA.getMessageTimeByUuid(str);
    }

    public static Map<String, IMMessage> queryMsgMapByProperty(List<com.netease.nimlib.push.packet.b.c> list) {
        return MsgDBHelperA.queryMsgMapByProperty(list);
    }

    public static IMMessage queryMessageByUuid(String str) {
        return MsgDBHelperA.queryMessageByUuid(str);
    }

    public static IMMessage queryMessageBySeqId(long j) {
        return MsgDBHelperA.queryMessageBySeqId(j);
    }

    public static long queryMessageIdByUuid(String str) {
        return MsgDBHelperA.queryMessageIdByUuid(str);
    }

    public static int queryStatus(String str, boolean z) {
        return MsgDBHelperA.queryStatus(str, z);
    }

    public static void updateSyncSelfMessageStatus(List<IMMessageImpl> list) {
        MsgDBHelperA.updateSyncSelfMessageStatus(list);
    }

    public static void updateMessageCallbackExt(long j, String str) {
        MsgDBHelperUtils.database().a("UPDATE msghistory set callbackext=? where messageid=?", new Object[]{str, Long.valueOf(j)});
    }

    public static void updateAttachStatus(String str, int i) {
        MsgDBHelperA.updateAttachStatus(str, i);
    }

    public static void setMessageBlacked(long j, boolean z) {
        MsgDBHelperA.setMessageBlacked(j, z);
    }

    public static long getSessionLastReceivedMsgTimeTag(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperA.getSessionLastReceivedMsgTimeTag(str, sessionTypeEnum);
    }

    public static void markHasSendTeamMsgAck(List<String> list) {
        MsgDBHelperA.markHasSendTeamMsgAck(list);
    }

    public static void updateTeamMsgAckCount(String str, int i, int i2) {
        MsgDBHelperA.updateTeamMsgAckCount(str, i, i2);
    }

    public static void saveRecent(q qVar) {
        MsgDBHelperUtils.database().a("INSERT OR REPLACE INTO lstmsg(uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", new Object[]{qVar.getContactId(), qVar.getFromAccount(), qVar.getRecentMessageId(), Integer.valueOf(qVar.getMsgStatus().getValue()), Integer.valueOf(qVar.getUnreadCount()), qVar.getContent(), Long.valueOf(qVar.getTime()), Integer.valueOf(qVar.getSessionType().getValue()), Long.valueOf(qVar.getTag()), Integer.valueOf(qVar.b()), qVar.a(), qVar.c()});
    }

    public static void importRecentContactByUnionKey(List<q> list) {
        MsgDBHelperA.importRecentContactByUnionKey(list);
    }

    public static void saveRoamMsgHasMore(List<RoamMsgHasMoreOption> list) {
        MsgDBHelperA.saveRoamMsgHasMore(list);
    }

    public static void saveQuickComment(String str, List<QuickCommentOption> list) {
        MsgDBHelperA.saveQuickComment(str, list);
    }

    public static void saveCollectInfo(List<a> list) {
        MsgDBHelperA.saveCollectInfo(list);
    }

    public static void saveMsgPin(List<o> list) {
        MsgDBHelperA.saveMsgPin(list);
    }

    public static void saveStickTopSession(List<StickTopSessionInfo> list) {
        MsgDBHelperA.saveStickTopSession(list);
    }

    public static List<RecentContact> queryRecentContacts(int i) {
        return MsgDBHelperA.queryRecentContacts(i);
    }

    public static q queryRecentContact(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperA.queryRecentContact(str, sessionTypeEnum);
    }

    public static long queryRoamMsgHasMoreTime(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperA.queryRoamMsgHasMoreTime(str, sessionTypeEnum);
    }

    public static long queryRoamMsgHasMoreServerId(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperA.queryRoamMsgHasMoreServerId(str, sessionTypeEnum);
    }

    public static ArrayList<QuickCommentOption> queryQuickCommentByUuid(String str) {
        return MsgDBHelperA.queryQuickCommentByUuid(str);
    }

    public static List<MsgPinDbOption> queryMsgPin(String str) {
        return MsgDBHelperA.queryMsgPin(str);
    }

    public static boolean isStickTopSession(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperA.isStickTopSession(str, sessionTypeEnum);
    }

    public static void setRecentStatus(String str, int i, long j) {
        MsgDBHelperA.setRecentStatus(str, i, j);
    }

    public static void setRecentRead(String str, int i) {
        MsgDBHelperA.setRecentRead(str, i);
    }

    public static void updateRecentUnreadNum(String str, SessionTypeEnum sessionTypeEnum, int i) {
        MsgDBHelperA.updateRecentUnreadNum(str, sessionTypeEnum, i);
    }

    public static void updateRecent(RecentContact recentContact) {
        MsgDBHelperA.updateRecent(recentContact);
    }

    public static void updateRoamMsgHasMoreTime(RoamMsgHasMoreOption roamMsgHasMoreOption) {
        MsgDBHelperA.updateRoamMsgHasMoreTime(roamMsgHasMoreOption);
    }

    public static void updateCollectInfo(a aVar) {
        MsgDBHelperA.updateCollectInfo(aVar);
    }

    public static void updateMsgPin(String str, String str2, String str3, long j) {
        MsgDBHelperA.updateMsgPin(str, str2, str3, j);
    }

    public static void updateStickTopSession(String str, SessionTypeEnum sessionTypeEnum, String str2, long j) {
        MsgDBHelperA.updateStickTopSession(str, sessionTypeEnum, str2, j);
    }

    public static void deleteRecentContact(String str, SessionTypeEnum sessionTypeEnum) {
        MsgDBHelperA.deleteRecentContact(str, sessionTypeEnum);
    }

    public static void deleteRoamMsgHasMoreTime(String str, SessionTypeEnum sessionTypeEnum) {
        MsgDBHelperA.deleteRoamMsgHasMoreTime(str, sessionTypeEnum);
    }

    public static void deleteQuickComment(String str, String str2, long j) {
        MsgDBHelperA.deleteQuickComment(str, str2, j);
    }

    public static void deleteQuickComment(String str) {
        MsgDBHelperA.deleteQuickComment(str);
    }

    public static void deleteCollectInfo(List<Long> list) {
        MsgDBHelperA.deleteCollectInfo(list);
    }

    public static void deleteMsgPin(String str, String str2) {
        MsgDBHelperA.deleteMsgPin(str, str2);
    }

    public static void deleteMsgPin(String str) {
        MsgDBHelperA.deleteMsgPin(str);
    }

    public static void deleteStickTopSession(String str) {
        MsgDBHelperA.deleteStickTopSession(str);
    }

    public static boolean hasDeleteTag(String str) {
        return MsgDBHelperA.hasDeleteTag(str);
    }

    public static Set<String> hasDeleteTag(Collection<IMMessageImpl> collection) {
        return MsgDBHelperA.hasDeleteTag(collection);
    }

    public static long getClearSessionTime(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperA.getClearSessionTime(str, sessionTypeEnum);
    }

    public static ArrayList<SystemMessage> querySystemMessages(int i, int i2) {
        return MsgDBHelperA.querySystemMessages(i, i2);
    }

    public static ArrayList<SystemMessage> querySystemMessage(List<SystemMessageType> list, int i, int i2) {
        return MsgDBHelperA.querySystemMessage(list, i, i2);
    }

    public static void setSystemMessageStatus(long j, SystemMessageStatus systemMessageStatus) {
        MsgDBHelperA.setSystemMessageStatus(j, systemMessageStatus);
    }

    public static void setAllSystemMessageRead(List<SystemMessageType> list) {
        MsgDBHelperA.setAllSystemMessageRead(list);
    }

    public static void setSystemMessageRead(long j) {
        MsgDBHelperA.setSystemMessageRead(j);
    }

    public static int querySystemMessageUnreadNum(List<SystemMessageType> list) {
        return MsgDBHelperA.querySystemMessageUnreadNum(list);
    }

    public static void deleteSystemMessage(long j) {
        MsgDBHelperA.deleteSystemMessage(j);
    }

    public static void clearSystemMessages(List<SystemMessageType> list) {
        MsgDBHelperA.clearSystemMessages(list);
    }

    public static void saveMessageReceipt(List<f> list) {
        MsgDBHelperA.saveMessageReceipt(list);
    }

    public static Map<String, f> queryMessageReceipt(List<String> list) {
        return MsgDBHelperA.queryMessageReceipt(list);
    }

    public static void saveSendReceiptRecord(MessageReceipt messageReceipt) {
        MsgDBHelperA.saveSendReceiptRecord(messageReceipt);
    }

    public static void saveSessionReadRecord(String str, SessionTypeEnum sessionTypeEnum, long j) {
        MsgDBHelperA.saveSessionReadRecord(str, sessionTypeEnum, j);
    }

    public static long querySessionReadTimeTag(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperA.querySessionReadTimeTag(str, sessionTypeEnum);
    }

    public static void saveSenderNick(String str, String str2) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("account", str);
        contentValues.put("nick", str2);
        MsgDBHelperUtils.database().c("sender_nick", null, contentValues);
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
                ContentValues contentValues = new ContentValues(2);
                contentValues.put("account", entry.getKey());
                contentValues.put("nick", entry.getValue());
                MsgDBHelperUtils.database().c("sender_nick", null, contentValues);
            }
            MsgDBHelperUtils.database().h();
            com.netease.nimlib.log.b.L("saveSenderNickMap success");
        } finally {
            MsgDBHelperUtils.database().g();
        }
    }

    public static Map<String, String> queryAllSenderNick() {
        return MsgDBHelperA.queryAllSenderNick();
    }

    public static void saveRevokeMessage(String str) {
        MsgDBHelperA.saveRevokeMessage(str);
    }

    public static String queryRevokeMessage(String str) {
        return MsgDBHelperA.queryRevokeMessage(str);
    }

    public static void saveTeamMsgAckDetail(TeamMsgAckInfo teamMsgAckInfo, String str) {
        MsgDBHelperA.saveTeamMsgAckDetail(teamMsgAckInfo, str);
    }

    public static void updateTeamMsgAckDetail(String str, String str2) {
        MsgDBHelperA.updateTeamMsgAckDetail(str, str2);
    }

    public static TeamMsgAckInfo queryTeamMsgAckDetail(String str) {
        return MsgDBHelperA.queryTeamMsgAckDetail(str);
    }

    public static int removeSessionReliableInfo(List<Long> list) {
        return MsgDBHelperA.removeSessionReliableInfo(list);
    }

    public static int removeSessionReliableInfo(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperA.removeSessionReliableInfo(str, sessionTypeEnum);
    }

    public static com.netease.nimlib.session.a.d queryLastSessionReliableInfo(String str, SessionTypeEnum sessionTypeEnum) {
        return MsgDBHelperA.queryLastSessionReliableInfo(str, sessionTypeEnum);
    }

    public static List<com.netease.nimlib.session.a.d> queryMayOverLappedInfos(com.netease.nimlib.session.a.d dVar) {
        return MsgDBHelperA.queryMayOverLappedInfos(dVar);
    }

    public static List<com.netease.nimlib.session.a.d> queryParentInfos(com.netease.nimlib.session.a.d dVar) {
        return MsgDBHelperA.queryParentInfos(dVar);
    }
}
