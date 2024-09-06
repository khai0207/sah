package com.netease.nimlib.abtest.db;

import com.netease.nimlib.database.a.d;

/* compiled from: ABTestDatabaseRevision.java */
/* loaded from: classes.dex */
public class b {
    public static d[] a() {
        return new d[]{b()};
    }

    private static d b() {
        return new d("ab_test_table").a(new d.a(1) { // from class: com.netease.nimlib.abtest.db.b.1
            @Override // com.netease.nimlib.database.a.d.a
            public String[] a() {
                return new String[]{"CREATE TABLE IF NOT EXISTS ab_test_table(ab_test_id Integer PRIMARY KEY AUTOINCREMENT, time Long NOT NULL, experiment_key text, scheme_key text, variates text)", "CREATE UNIQUE INDEX IF NOT EXISTS ab_test_table_key_index on ab_test_table(experiment_key, scheme_key)", "CREATE INDEX IF NOT EXISTS ab_test_table_time_index on ab_test_table(time)"};
            }

            @Override // com.netease.nimlib.database.a.d.a
            public String[] a(d.a aVar) {
                return null;
            }
        });
    }
}
