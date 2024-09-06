package com.netease.nimlib.apm.event.db;

import com.netease.nimlib.database.a.d;

/* compiled from: EventDatabaseRevision.java */
/* loaded from: classes.dex */
public class b {
    public static d[] a() {
        return new d[]{b()};
    }

    private static d b() {
        return new d("event_history").a(new d.a(1) { // from class: com.netease.nimlib.apm.event.db.b.2
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS event_history(event_id Integer PRIMARY KEY AUTOINCREMENT, id Varchar(64) NOT NULL, time Long NOT NULL, content Varchar(2048))", "CREATE INDEX IF NOT EXISTS event_history_time_index on event_history(time)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        }).a(new d.a(2) { // from class: com.netease.nimlib.apm.event.db.b.1
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS event_history(event_id Integer PRIMARY KEY AUTOINCREMENT, id Varchar(64) NOT NULL, time Long NOT NULL, content Varchar(2048), priority Long DEFAULT 0)", "CREATE INDEX IF NOT EXISTS event_history_time_index on event_history(time)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return new String[]{"ALTER TABLE event_history ADD priority Long DEFAULT 0"};
            }
        });
    }
}
