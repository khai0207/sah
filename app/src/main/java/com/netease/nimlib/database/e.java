package com.netease.nimlib.database;

import com.netease.nimlib.database.a.d;

/* compiled from: MsgDatabaseRevision.java */
/* loaded from: classes.dex */
public class e {
    public static com.netease.nimlib.database.a.d[] a() {
        return new com.netease.nimlib.database.a.d[]{b(), c(), d(), e(), f(), g(), h(), i(), j(), k(), l(), m(), n(), o(), p(), r(), q(), s(), t()};
    }

    private static com.netease.nimlib.database.a.d b() {
        return new com.netease.nimlib.database.a.d("msghistory").a(new d.a(1) { // from class: com.netease.nimlib.database.e.4
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT)", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        }).a(new d.a(5) { // from class: com.netease.nimlib.database.e.3
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024))", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD remoteext Varchar(1024)", "ALTER TABLE msghistory ADD localext Varchar(1024)", "ALTER TABLE msghistory ADD push Varchar(200)", "ALTER TABLE msghistory ADD payload Varchar(2048)", "ALTER TABLE msghistory ADD config Varchar(1024)"};
            }
        }).a(new d.a(9) { // from class: com.netease.nimlib.database.e.2
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024))", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD pushoption Varchar(1024)"};
            }
        }).a(new d.a(10) { // from class: com.netease.nimlib.database.e.33
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024), fromclient Integer)", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD fromclient Integer DEFAULT 0"};
            }
        }).a(new d.a(11) { // from class: com.netease.nimlib.database.e.32
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024), fromclient Integer, antispamoption Varchar(64))", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD antispamoption Varchar(64)"};
            }
        }).a(new d.a(12) { // from class: com.netease.nimlib.database.e.31
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024), fromclient Integer, antispamoption Varchar(64))", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_direct_index on msghistory(id, sessiontype, direct)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_direct_index on msghistory(id, sessiontype, direct)"};
            }
        }).a(new d.a(14) { // from class: com.netease.nimlib.database.e.30
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024), fromclient Integer, antispamoption Varchar(64), msgack Integer DEFAULT 0, acksend Integer DEFAULT 0, ackcount Integer DEFAULT 0, unackcount Integer DEFAULT 0)", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_direct_index on msghistory(id, sessiontype, direct)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD msgack Integer DEFAULT 0", "ALTER TABLE msghistory ADD acksend Integer DEFAULT 0", "ALTER TABLE msghistory ADD ackcount Integer DEFAULT 0", "ALTER TABLE msghistory ADD unackcount Integer DEFAULT 0"};
            }
        }).a(new d.a(15) { // from class: com.netease.nimlib.database.e.29
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024), fromclient Integer, antispamoption Varchar(64), msgack Integer DEFAULT 0, acksend Integer DEFAULT 0, ackcount Integer DEFAULT 0, unackcount Integer DEFAULT 0,isblacked Integer DEFAULT 0)", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_direct_index on msghistory(id, sessiontype, direct)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD isblacked Integer DEFAULT 0"};
            }
        }).a(new d.a(18) { // from class: com.netease.nimlib.database.e.28
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024), fromclient Integer, antispamoption Varchar(64), msgack Integer DEFAULT 0, acksend Integer DEFAULT 0, ackcount Integer DEFAULT 0, unackcount Integer DEFAULT 0, isblacked Integer DEFAULT 0, replymsgfromaccount VARCHAR(32), replymsgtoaccount VARCHAR(32), replymsgtime LONG, replymsgidserver LONG, replymsgidclient VARCHAR(32), threadmsgfromaccount VARCHAR(32), threadmsgtoaccount VARCHAR(32), threadmsgtime LONG, threadmsgidserver LONG, threadmsgidclient VARCHAR(32), quickcommentupdatetime Long, isdelete Integer DEFAULT 0)", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_direct_index on msghistory(id, sessiontype, direct)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD replymsgfromaccount VARCHAR(32)", "ALTER TABLE msghistory ADD replymsgtoaccount VARCHAR(32)", "ALTER TABLE msghistory ADD replymsgtime LONG", "ALTER TABLE msghistory ADD replymsgidserver LONG", "ALTER TABLE msghistory ADD replymsgidclient VARCHAR(32)", "ALTER TABLE msghistory ADD threadmsgfromaccount VARCHAR(32)", "ALTER TABLE msghistory ADD threadmsgtoaccount VARCHAR(32)", "ALTER TABLE msghistory ADD threadmsgtime LONG", "ALTER TABLE msghistory ADD threadmsgidserver LONG", "ALTER TABLE msghistory ADD threadmsgidclient VARCHAR(32)", "ALTER TABLE msghistory ADD quickcommentupdatetime Long", "ALTER TABLE msghistory ADD isdelete Integer DEFAULT 0"};
            }
        }).a(new d.a(19) { // from class: com.netease.nimlib.database.e.23
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024), fromclient Integer, antispamoption Varchar(64), msgack Integer DEFAULT 0, acksend Integer DEFAULT 0, ackcount Integer DEFAULT 0, unackcount Integer DEFAULT 0, isblacked Integer DEFAULT 0, replymsgfromaccount VARCHAR(32), replymsgtoaccount VARCHAR(32), replymsgtime LONG, replymsgidserver LONG, replymsgidclient VARCHAR(32), threadmsgfromaccount VARCHAR(32), threadmsgtoaccount VARCHAR(32), threadmsgtime LONG, threadmsgidserver LONG, threadmsgidclient VARCHAR(32), quickcommentupdatetime Long, isdelete Integer DEFAULT 0, callbackext VARCHAR(1024))", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_direct_index on msghistory(id, sessiontype, direct)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD callbackext VARCHAR(1024)"};
            }
        }).a(new d.a(20) { // from class: com.netease.nimlib.database.e.12
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024), fromclient Integer, antispamoption Varchar(64), msgack Integer DEFAULT 0, acksend Integer DEFAULT 0, ackcount Integer DEFAULT 0, unackcount Integer DEFAULT 0, isblacked Integer DEFAULT 0, replymsgfromaccount VARCHAR(32), replymsgtoaccount VARCHAR(32), replymsgtime LONG, replymsgidserver LONG, replymsgidclient VARCHAR(32), threadmsgfromaccount VARCHAR(32), threadmsgtoaccount VARCHAR(32), threadmsgtime LONG, threadmsgidserver LONG, threadmsgidclient VARCHAR(32), quickcommentupdatetime Long, isdelete Integer DEFAULT 0, callbackext VARCHAR(1024), subtype Integer DEFAULT 0)", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_direct_index on msghistory(id, sessiontype, direct)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD subtype Integer DEFAULT 0"};
            }
        }).a(new d.a(24) { // from class: com.netease.nimlib.database.e.1
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msghistory(messageid Integer PRIMARY KEY AUTOINCREMENT, uuid Varchar(32) NOT NULL, serverid Long, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, sessiontype Integer, time Long, status Integer, direct Integer, msgtype Integer, content Varchar(512), status2 Integer, attach TEXT, remoteext Varchar(1024), localext Varchar(1024), push Varchar(200), payload Varchar(2048), config Varchar(1024), pushoption Varchar(1024), fromclient Integer, antispamoption Varchar(64), msgack Integer DEFAULT 0, acksend Integer DEFAULT 0, ackcount Integer DEFAULT 0, unackcount Integer DEFAULT 0, isblacked Integer DEFAULT 0, replymsgfromaccount VARCHAR(32), replymsgtoaccount VARCHAR(32), replymsgtime LONG, replymsgidserver LONG, replymsgidclient VARCHAR(32), threadmsgfromaccount VARCHAR(32), threadmsgtoaccount VARCHAR(32), threadmsgtime LONG, threadmsgidserver LONG, threadmsgidclient VARCHAR(32), quickcommentupdatetime Long, isdelete Integer DEFAULT 0, callbackext VARCHAR(1024), subtype Integer DEFAULT 0,robotinfo Varchar(2048))", "CREATE INDEX IF NOT EXISTS msghistory_uuid_index on msghistory(uuid)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_time on msghistory(id, sessiontype, time)", "CREATE INDEX IF NOT EXISTS msghistory_id_sessiontype_direct_index on msghistory(id, sessiontype, direct)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE msghistory ADD robotinfo Varchar(2048)"};
            }
        });
    }

    private static com.netease.nimlib.database.a.d c() {
        return new com.netease.nimlib.database.a.d("lastMsg").a(new d.a(1) { // from class: com.netease.nimlib.database.e.7
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS lstmsg(uid Varchar(16) NOT NULL, messageId long, msgstatus INTEGER, sessiontype INTEGER, unreadnum integer, content Varchar(512), time long, tag INTEGER default 0, tag_time long default 0, fromuid Varchar(16))", "CREATE UNIQUE INDEX IF NOT EXISTS lstmsg_uid_sessiontype on lstmsg(uid, sessiontype)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        }).a(new d.a(2) { // from class: com.netease.nimlib.database.e.6
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS lstmsg(uid Varchar(16) NOT NULL, messageId long, msgstatus INTEGER, sessiontype INTEGER, unreadnum INTEGER, content Varchar(512), time long, tag INTEGER default 0, tag_time long default 0, fromuid Varchar(16), msgtype INTEGER, attach TEXT)", "CREATE UNIQUE INDEX IF NOT EXISTS lstmsg_uid_sessiontype on lstmsg(uid, sessiontype)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE lstmsg ADD msgtype INTEGER", "ALTER TABLE lstmsg ADD attach TEXT"};
            }
        }).a(new d.a(6) { // from class: com.netease.nimlib.database.e.5
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS lstmsg(uid Varchar(16) NOT NULL, messageId long, msgstatus INTEGER, sessiontype INTEGER, unreadnum INTEGER, content Varchar(512), time long, tag INTEGER default 0, tag_time long default 0, fromuid Varchar(16), msgtype INTEGER, attach TEXT, extension Varchar(1024))", "CREATE UNIQUE INDEX IF NOT EXISTS lstmsg_uid_sessiontype on lstmsg(uid, sessiontype)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE lstmsg ADD extension Varchar(1024)"};
            }
        });
    }

    private static com.netease.nimlib.database.a.d d() {
        return new com.netease.nimlib.database.a.d("system_msg").a(new d.a(1) { // from class: com.netease.nimlib.database.e.9
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS system_msg(messageid Integer PRIMARY KEY AUTOINCREMENT, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, type Integer, time Long, status Integer, content TEXT, attach TEXT)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[0];
            }
        }).a(new d.a(4) { // from class: com.netease.nimlib.database.e.8
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS system_msg(messageid Integer PRIMARY KEY AUTOINCREMENT, id Varchar(32) NOT NULL, fromid Varchar(32) NOT NULL, type Integer, time Long, status Integer, content TEXT, attach TEXT, unread Integer default 1)", "CREATE INDEX IF NOT EXISTS unread_index on system_msg(unread)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE system_msg ADD COLUMN unread INTEGER default 0", "CREATE INDEX IF NOT EXISTS unread_index on system_msg(unread)"};
            }
        });
    }

    private static com.netease.nimlib.database.a.d e() {
        return new com.netease.nimlib.database.a.d("avchat").a(new d.a(3) { // from class: com.netease.nimlib.database.e.10
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS avchat(record_id Integer PRIMARY KEY AUTOINCREMENT, channel_id Integer, status Integer, direction Integer, record TEXT)", "CREATE INDEX IF NOT EXISTS avchat_channel_id ON avchat(channel_id)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d f() {
        return new com.netease.nimlib.database.a.d("message_receipt").a(new d.a(7) { // from class: com.netease.nimlib.database.e.11
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS message_receipt(session_id Varchar(32) NOT NULL, time Long, max_time Long, PRIMARY KEY (session_id))"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d g() {
        return new com.netease.nimlib.database.a.d("send_receipt_record").a(new d.a(7) { // from class: com.netease.nimlib.database.e.13
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS send_receipt_record(session_id Varchar(32) NOT NULL, time Long, PRIMARY KEY (session_id))"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d h() {
        return new com.netease.nimlib.database.a.d("session_read_record").a(new d.a(12) { // from class: com.netease.nimlib.database.e.14
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS session_read_record(session_id Varchar(32) NOT NULL, session_type Integer, time Long, PRIMARY KEY (session_id))"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d i() {
        return new com.netease.nimlib.database.a.d("sender_nick").a(new d.a(8) { // from class: com.netease.nimlib.database.e.15
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS sender_nick(account Varchar(32) NOT NULL, nick Varchar(32), PRIMARY KEY (account))"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d j() {
        return new com.netease.nimlib.database.a.d("revoke_message").a(new d.a(13) { // from class: com.netease.nimlib.database.e.16
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS revoke_message(uuid Long, PRIMARY KEY (uuid))", "CREATE INDEX IF NOT EXISTS revokemsg_uuid_index on revoke_message(uuid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d k() {
        return new com.netease.nimlib.database.a.d("team_msg_ack").a(new d.a(14) { // from class: com.netease.nimlib.database.e.17
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS team_msg_ack(msgid Varchar(32) NOT NULL, tid Varchar(32) NOT NULL, snapshot TEXT, bitmap Varchar(512), PRIMARY KEY (msgid))"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d l() {
        return new com.netease.nimlib.database.a.d("delete_message_record").a(new d.a(16) { // from class: com.netease.nimlib.database.e.18
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS delete_message_record(uuid VARCHAR(32) NOT NULL, session_id Varchar(32), session_type Integer, PRIMARY KEY (uuid))", "CREATE INDEX IF NOT EXISTS deletemsgrecord_uuid_index on delete_message_record(uuid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d m() {
        return new com.netease.nimlib.database.a.d("clear_message_record").a(new d.a(16) { // from class: com.netease.nimlib.database.e.19
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS clear_message_record(session_id Varchar(32) NOT NULL, session_type Integer, time Long, PRIMARY KEY (session_id))", "CREATE INDEX IF NOT EXISTS clearmsgrecord_session_id_index on clear_message_record(session_id)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d n() {
        return new com.netease.nimlib.database.a.d("roam_msg_has_more").a(new d.a(17) { // from class: com.netease.nimlib.database.e.20
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS roam_msg_has_more(session_id Varchar(32) NOT NULL, session_type Integer, time Long, serverid Long, PRIMARY KEY (session_id))", "CREATE INDEX IF NOT EXISTS roammsghasmore_serverid_index on roam_msg_has_more(serverid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d o() {
        return new com.netease.nimlib.database.a.d("quick_comment").a(new d.a(18) { // from class: com.netease.nimlib.database.e.21
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS quick_comment(uuid Varchar(32) NOT NULL, operator Varchar(32) NOT NULL, type Long, time Long, ext Varchar(16), PRIMARY KEY (uuid, operator, type))", "CREATE INDEX IF NOT EXISTS quickcomment_uuid_index on quick_comment(uuid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d p() {
        return new com.netease.nimlib.database.a.d("collect_info").a(new d.a(18) { // from class: com.netease.nimlib.database.e.22
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS collect_info(id Long NOT NULL, type Integer, data Varchar(20480), ext Varchar(1024), uniqueId Varchar(1024), createTime Long, updateTime Long, PRIMARY KEY (id))", "CREATE INDEX IF NOT EXISTS collectinfo_id_index on collect_info(id)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d q() {
        return new com.netease.nimlib.database.a.d("session_stick_top").a(new d.a(18) { // from class: com.netease.nimlib.database.e.24
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS session_stick_top(session_id Varchar(32) NOT NULL, session_type Integer, ext Varchar(32), create_time Long, update_time Long, PRIMARY KEY (session_id))", "CREATE INDEX IF NOT EXISTS sessionsticktop_sessionid_index on session_stick_top(session_id)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d r() {
        return new com.netease.nimlib.database.a.d("msg_pin").a(new d.a(18) { // from class: com.netease.nimlib.database.e.25
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS msg_pin(uuid Varchar(32) NOT NULL, session_id Varchar(32) NOT NULL, operator Varchar(32) NOT NULL, ext Varchar(512), create_time Long, update_time Long, PRIMARY KEY (uuid, session_id))", "CREATE INDEX IF NOT EXISTS msgpin_uuid_index on msg_pin(uuid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d s() {
        return new com.netease.nimlib.database.a.d("contact_pin_time").a(new d.a(18) { // from class: com.netease.nimlib.database.e.26
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS contact_pin_time(session_id Varchar(32) NOT NULL, session_type Integer, time Long, PRIMARY KEY (session_id))", "CREATE INDEX IF NOT EXISTS contactpintime_sessionid_index on contact_pin_time(session_id)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d t() {
        return new com.netease.nimlib.database.a.d("session_reliable_table").a(new d.a(23) { // from class: com.netease.nimlib.database.e.27
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS session_reliable_table(id INTEGER PRIMARY KEY AUTOINCREMENT, session_id TEXT NOT NULL, session_type INTEGER, start_time INTEGER, start_server_id INTEGER, start_client_id TEXT, stop_time INTEGER, stop_server_id INTEGER, stop_client_id TEXT)", "CREATE INDEX IF NOT EXISTS sessionreliabletable_sessionid_sessiontype_index on session_reliable_table(session_id, session_type)", "CREATE INDEX IF NOT EXISTS sessionreliabletable_stoptime_index on session_reliable_table(stop_time)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }
}
