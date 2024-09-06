package com.android.pc.ioc.db.table;

import java.util.HashMap;

/* loaded from: classes.dex */
public class Table {
    private static final HashMap<String, Table> tableMap = new HashMap<>();
    private boolean checkDatabase;
    public final HashMap<String, Column> columnMap;
    private Id id;
    private String tableName;

    private Table(Class cls) {
        this.tableName = TableUtils.getTableName(cls);
        this.id = TableUtils.getId(cls);
        this.columnMap = TableUtils.getColumnMap(cls);
    }

    public static synchronized Table get(Class cls) {
        Table table;
        synchronized (Table.class) {
            table = tableMap.get(cls.getCanonicalName());
            if (table == null) {
                table = new Table(cls);
                tableMap.put(cls.getCanonicalName(), table);
            }
        }
        return table;
    }

    public String getTableName() {
        return this.tableName;
    }

    public Id getId() {
        return this.id;
    }

    public boolean isCheckDatabase() {
        return this.checkDatabase;
    }

    public void setCheckDatabase(boolean z) {
        this.checkDatabase = z;
    }
}
