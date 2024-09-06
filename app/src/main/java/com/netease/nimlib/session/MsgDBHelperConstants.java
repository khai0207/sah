package com.netease.nimlib.session;

/* loaded from: classes.dex */
public class MsgDBHelperConstants {
    public static final int BATCH_SIZE = 200;
    static final String CLEAR_MESSAGE_RECORD_COLUMNS = "session_id, session_type, time";
    static final String DELETE_MESSAGE_RECORD_COLUMNS = "uuid, session_id, session_type";
    static final String LST_MSG_ORDER_SQL = " order by time desc";
    static final String MESSAGE_RECEIPT_COLUMNS = "session_id,time,max_time";
    static final String MSG_COLUMNS = "uuid,serverid,time,content,msgtype,sessiontype,fromid,id,direct,status,status2,attach";
    static final String RECENT_COLUMNS = "uid,fromuid,messageId,msgstatus,unreadnum,content,time,sessiontype,tag,msgtype,attach,extension";
    static final String REVOKE_MESSAGE_COLUMNS = "uuid";
    static final String SENDER_NICK_COLUMNS = "account,nick";
    static final String SEND_RECEIPT_RECORD_COLUMNS = "session_id,time";
    static final String SESSION_READ_RECORD_COLUMNS = "session_id,session_type,time";
    static final String SESSION_RELIABLE_COLUMNS = "session_id, session_type, start_time, start_server_id, start_client_id, stop_time, stop_server_id, stop_client_id";
    static final String SYSTEM_MSG_COLUMNS = "messageid, id, fromid, type, time, status, content, attach, unread";
    static final String TEAM_MSG_ACK_DETAIL_COLUMNS = "msgid,tid,snapshot,bitmap";

    static String msgHistoryColumnStr() {
        return "messageid,uuid,serverid,time,content,msgtype,sessiontype,fromid,id,direct,status,status2,attach,remoteext,localext,push,payload,config,pushoption,fromclient,antispamoption,msgack,acksend,ackcount,unackcount,isblacked,replymsgfromaccount,replymsgtoaccount,replymsgtime,replymsgidserver,replymsgidclient,threadmsgfromaccount,threadmsgtoaccount,threadmsgtime,threadmsgidserver,threadmsgidclient,quickcommentupdatetime,isdelete,callbackext,subtype,robotinfo";
    }
}
