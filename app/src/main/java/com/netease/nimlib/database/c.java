package com.netease.nimlib.database;

import com.netease.nimlib.database.a.d;

/* compiled from: MainDatabaseRevision.java */
/* loaded from: classes.dex */
public class c {
    public static String a() {
        return "super_team";
    }

    public static String b() {
        return "team";
    }

    public static String c() {
        return "sync_cross_process";
    }

    public static com.netease.nimlib.database.a.d[] d() {
        return new com.netease.nimlib.database.a.d[]{g(), h(), i(), j(), k(), l(), f(), e(), m()};
    }

    private static com.netease.nimlib.database.a.d e() {
        return new com.netease.nimlib.database.a.d("super_tuser").a(new d.a(13) { // from class: com.netease.nimlib.database.c.12
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS super_tuser(tid Varchar(16) NOT NULL, account Varchar(32) NOT NULL, type Integer, nick Varchar(32), bits Integer, join_time Integer, valid Integer, custom Varchar(32), mute Integer DEFAULT 0, invitor_accid Varchar(32) DEFAULT NULL)", "CREATE UNIQUE INDEX IF NOT EXISTS stuser_tid_account_index on super_tuser(tid, account)", "CREATE INDEX IF NOT EXISTS stuser_tid_index on super_tuser(tid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        }).a(new d.a(16) { // from class: com.netease.nimlib.database.c.1
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS super_tuser(tid Varchar(16) NOT NULL, account Varchar(32) NOT NULL, type Integer, nick Varchar(32), bits Integer, join_time Integer, valid Integer, custom Varchar(32), mute Integer DEFAULT 0, invitor_accid Varchar(32) DEFAULT NULL,follow_account_ids Varchar(1024) DEFAULT NULL)", "CREATE UNIQUE INDEX IF NOT EXISTS stuser_tid_account_index on super_tuser(tid, account)", "CREATE INDEX IF NOT EXISTS stuser_tid_index on super_tuser(tid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE super_tuser ADD COLUMN follow_account_ids Varchar(1024) DEFAULT NULL"};
            }
        });
    }

    private static com.netease.nimlib.database.a.d f() {
        return new com.netease.nimlib.database.a.d("super_team").a(new d.a(13) { // from class: com.netease.nimlib.database.c.13
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS super_team(id Varchar(16) NOT NULL PRIMARY KEY, name Varchar(32) NOT NULL, creator Varchar(32) NOT NULL, type Integer, level Integer, valid_flag, Integer, count Integer, member_tt Integer, introduce TEXT, announcement TEXT, dimen Varchar(32), config TEXT,timetag Integer, extension TEXT,create_time Integer, join_mode Integer, member_flag Integer, ext_server TEXT, bits Integer, icon Varchar(1024), be_invite_mode Integer DEFAULT 0, invite_mode Integer DEFAULT 0, update_tinfo_mode Integer DEFAULT 0, update_custom_mode Integer DEFAULT 0, all_mute Integer DEFAULT 0)", "CREATE INDEX IF NOT EXISTS team_id_index on super_team(id)", "CREATE INDEX IF NOT EXISTS team_valid_type_index on super_team(valid_flag,member_flag,type)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d g() {
        return new com.netease.nimlib.database.a.d("team").a(new d.a(1) { // from class: com.netease.nimlib.database.c.18
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS team(id Varchar(16) NOT NULL PRIMARY KEY, name Varchar(32) NOT NULL, creator Varchar(32) NOT NULL, type Integer, level Integer, valid_flag, Integer, count Integer, member_tt Integer, introduce TEXT, announcement TEXT, dimen Varchar(32), config TEXT, timetag Integer)", "CREATE INDEX IF NOT EXISTS team_id_index on team(id)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        }).a(new d.a(3) { // from class: com.netease.nimlib.database.c.17
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS team(id Varchar(16) NOT NULL PRIMARY KEY, name Varchar(32) NOT NULL, creator Varchar(32) NOT NULL, type Integer, level Integer, valid_flag, Integer, count Integer, member_tt Integer, introduce TEXT, announcement TEXT, dimen Varchar(32), config TEXT, timetag Integer, extension TEXT, create_time Integer, join_mode Integer, member_flag Integer)", "CREATE INDEX IF NOT EXISTS team_id_index on team(id)", "CREATE INDEX IF NOT EXISTS team_valid_type_index on team(valid_flag,member_flag,type)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE team ADD extension TEXT", "ALTER TABLE team ADD create_time Integer", "ALTER TABLE team ADD join_mode Integer", "ALTER TABLE team ADD member_flag Integer"};
            }
        }).a(new d.a(4) { // from class: com.netease.nimlib.database.c.16
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS team(id Varchar(16) NOT NULL PRIMARY KEY, name Varchar(32) NOT NULL, creator Varchar(32) NOT NULL, type Integer, level Integer, valid_flag, Integer, count Integer, member_tt Integer, introduce TEXT, announcement TEXT, dimen Varchar(32), config TEXT,timetag Integer, extension TEXT,create_time Integer, join_mode Integer, member_flag Integer,ext_server TEXT,bits Integer)", "CREATE INDEX IF NOT EXISTS team_id_index on team(id)", "CREATE INDEX IF NOT EXISTS team_valid_type_index on team(valid_flag,member_flag,type)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE team ADD ext_server TEXT", "ALTER TABLE team ADD bits Integer"};
            }
        }).a(new d.a(8) { // from class: com.netease.nimlib.database.c.15
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS team(id Varchar(16) NOT NULL PRIMARY KEY, name Varchar(32) NOT NULL, creator Varchar(32) NOT NULL, type Integer, level Integer, valid_flag, Integer, count Integer, member_tt Integer, introduce TEXT, announcement TEXT, dimen Varchar(32), config TEXT,timetag Integer, extension TEXT,create_time Integer, join_mode Integer, member_flag Integer, ext_server TEXT, bits Integer, icon Varchar(1024), be_invite_mode Integer DEFAULT 0, invite_mode Integer DEFAULT 0, update_tinfo_mode Integer DEFAULT 0, update_custom_mode Integer DEFAULT 0)", "CREATE INDEX IF NOT EXISTS team_id_index on team(id)", "CREATE INDEX IF NOT EXISTS team_valid_type_index on team(valid_flag,member_flag,type)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE team ADD icon Varchar(1024)", "ALTER TABLE team ADD be_invite_mode Integer DEFAULT 0", "ALTER TABLE team ADD invite_mode Integer DEFAULT 0", "ALTER TABLE team ADD update_tinfo_mode Integer DEFAULT 0", "ALTER TABLE team ADD update_custom_mode Integer DEFAULT 0"};
            }
        }).a(new d.a(9) { // from class: com.netease.nimlib.database.c.14
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS team(id Varchar(16) NOT NULL PRIMARY KEY, name Varchar(32) NOT NULL, creator Varchar(32) NOT NULL, type Integer, level Integer, valid_flag, Integer, count Integer, member_tt Integer, introduce TEXT, announcement TEXT, dimen Varchar(32), config TEXT,timetag Integer, extension TEXT,create_time Integer, join_mode Integer, member_flag Integer, ext_server TEXT, bits Integer, icon Varchar(1024), be_invite_mode Integer DEFAULT 0, invite_mode Integer DEFAULT 0, update_tinfo_mode Integer DEFAULT 0, update_custom_mode Integer DEFAULT 0, all_mute Integer DEFAULT 0)", "CREATE INDEX IF NOT EXISTS team_id_index on team(id)", "CREATE INDEX IF NOT EXISTS team_valid_type_index on team(valid_flag,member_flag,type)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE team ADD all_mute Integer DEFAULT 0"};
            }
        });
    }

    private static com.netease.nimlib.database.a.d h() {
        return new com.netease.nimlib.database.a.d("tuser").a(new d.a(2) { // from class: com.netease.nimlib.database.c.5
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS tuser(tid Varchar(16) NOT NULL, account Varchar(32) NOT NULL, type Integer, nick Varchar(32), bits Integer, join_time Integer )", "CREATE UNIQUE INDEX IF NOT EXISTS tuser_tid_account_index on tuser(tid, account)", "CREATE INDEX IF NOT EXISTS tuser_tid_index on tuser(tid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        }).a(new d.a(7) { // from class: com.netease.nimlib.database.c.4
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS tuser(tid Varchar(16) NOT NULL, account Varchar(32) NOT NULL, type Integer, nick Varchar(32), bits Integer, join_time Integer, valid Integer)", "CREATE UNIQUE INDEX IF NOT EXISTS tuser_tid_account_index on tuser(tid, account)", "CREATE INDEX IF NOT EXISTS tuser_tid_index on tuser(tid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE tuser ADD COLUMN valid INTEGER default 1"};
            }
        }).a(new d.a(8) { // from class: com.netease.nimlib.database.c.3
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS tuser(tid Varchar(16) NOT NULL, account Varchar(32) NOT NULL, type Integer, nick Varchar(32), bits Integer, join_time Integer, valid Integer, custom Varchar(1024), mute Integer DEFAULT 0)", "CREATE UNIQUE INDEX IF NOT EXISTS tuser_tid_account_index on tuser(tid, account)", "CREATE INDEX IF NOT EXISTS tuser_tid_index on tuser(tid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE tuser ADD COLUMN custom Varchar(1024)", "ALTER TABLE tuser ADD COLUMN mute Integer DEFAULT 0"};
            }
        }).a(new d.a(12) { // from class: com.netease.nimlib.database.c.2
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS tuser(tid Varchar(16) NOT NULL, account Varchar(32) NOT NULL, type Integer, nick Varchar(32), bits Integer, join_time Integer, valid Integer, custom Varchar(1024), mute Integer DEFAULT 0, invitor_accid Varchar(32) DEFAULT NULL)", "CREATE UNIQUE INDEX IF NOT EXISTS tuser_tid_account_index on tuser(tid, account)", "CREATE INDEX IF NOT EXISTS tuser_tid_index on tuser(tid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE tuser ADD COLUMN invitor_accid Varchar(32) DEFAULT NULL"};
            }
        }).a(new d.a(16) { // from class: com.netease.nimlib.database.c.19
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS tuser(tid Varchar(16) NOT NULL, account Varchar(32) NOT NULL, type Integer, nick Varchar(32), bits Integer, join_time Integer, valid Integer, custom Varchar(1024), mute Integer DEFAULT 0, invitor_accid Varchar(32) DEFAULT NULL, follow_account_ids Varchar(1024) DEFAULT NULL)", "CREATE UNIQUE INDEX IF NOT EXISTS tuser_tid_account_index on tuser(tid, account)", "CREATE INDEX IF NOT EXISTS tuser_tid_index on tuser(tid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE tuser ADD COLUMN follow_account_ids Varchar(1024) DEFAULT NULL"};
            }
        });
    }

    private static com.netease.nimlib.database.a.d i() {
        return new com.netease.nimlib.database.a.d("uinfo").a(new d.a(6) { // from class: com.netease.nimlib.database.c.6
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS uinfo(account Varchar(32) PRIMARY KEY NOT NULL, name Varchar(32) NOT NULL, icon Varchar(256) DEFAULT NULL, sign Varchar(256) DEFAULT NULL, gender Integer DEFAULT 0, email Varchar(64) DEFAULT NULL, birth Varchar(16) DEFAULT NULL, mobile Varchar(32) DEFAULT NULL, ex Varchar(1024) DEFAULT NULL, updatetime LONG DEFAULT 0)", "CREATE INDEX IF NOT EXISTS uinfo_account_updatetime_index on uinfo(account, updatetime)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d j() {
        return new com.netease.nimlib.database.a.d("friend").a(new d.a(5) { // from class: com.netease.nimlib.database.c.8
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS friend(account Varchar(32) PRIMARY KEY NOT NULL, flag Integer, beflag Integer, source Integer, alias Varchar(32), bits Long, ex Varchar(32), createtime Long, updatetime Long)", "CREATE INDEX IF NOT EXISTS friend_account_index on friend(account)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        }).a(new d.a(11) { // from class: com.netease.nimlib.database.c.7
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS friend(account Varchar(32) PRIMARY KEY NOT NULL, flag Integer, beflag Integer, source Integer, alias Varchar(32), bits Long, ex Varchar(32), createtime Long, updatetime Long ,serverex Varchar(256))", "CREATE INDEX IF NOT EXISTS friend_account_index on friend(account)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER  TABLE friend ADD COLUMN serverex Varchar(256) "};
            }
        });
    }

    private static com.netease.nimlib.database.a.d k() {
        return new com.netease.nimlib.database.a.d("user_tag").a(new d.a(5) { // from class: com.netease.nimlib.database.c.9
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS user_tag(account Varchar(32) PRIMARY KEY NOT NULL, mute Integer, black Integer, createtime Long, updatetime Long)", "CREATE INDEX IF NOT EXISTS user_tag_account_index on user_tag(account)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d l() {
        return new com.netease.nimlib.database.a.d("robot").a(new d.a(10) { // from class: com.netease.nimlib.database.c.10
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS robot(account Varchar(32) PRIMARY KEY NOT NULL, name Varchar(32) DEFAULT NULL, icon Varchar(256) DEFAULT NULL, intro Varchar(256) DEFAULT NULL, createtime LONG DEFAULT 0, updatetime LONG DEFAULT 0,botid Varchar(32) DEFAULT NULL)", "CREATE INDEX IF NOT EXISTS robot_account_botid_index on robot(account, botid)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }

    private static com.netease.nimlib.database.a.d m() {
        return new com.netease.nimlib.database.a.d("sync_cross_process").a(new d.a(14) { // from class: com.netease.nimlib.database.c.11
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS sync_cross_process(key Varchar(64) PRIMARY KEY NOT NULL, long_value LONG DEFAULT 0)", "CREATE INDEX IF NOT EXISTS sync_cross_process_key_index on sync_cross_process(key)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }
}
