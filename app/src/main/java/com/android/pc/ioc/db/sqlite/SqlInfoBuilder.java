package com.android.pc.ioc.db.sqlite;

import com.android.pc.ioc.db.table.Column;
import com.android.pc.ioc.db.table.ColumnUtils;
import com.android.pc.ioc.db.table.Finder;
import com.android.pc.ioc.db.table.Foreign;
import com.android.pc.ioc.db.table.Id;
import com.android.pc.ioc.db.table.KeyValue;
import com.android.pc.ioc.db.table.Table;
import com.android.pc.ioc.db.table.TableUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SqlInfoBuilder {
    private SqlInfoBuilder() {
    }

    public static SqlInfo buildInsertSqlInfo(DbUtils dbUtils, Object obj) {
        List<KeyValue> entity2KeyValueList = entity2KeyValueList(dbUtils, obj);
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("INSERT INTO ");
        stringBuffer.append(Table.get(obj.getClass()).getTableName());
        stringBuffer.append(" (");
        for (KeyValue keyValue : entity2KeyValueList) {
            stringBuffer.append(keyValue.getKey());
            stringBuffer.append(",");
            sqlInfo.addBindArg(keyValue.getValue());
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(") VALUES (");
        int size = entity2KeyValueList.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(")");
        sqlInfo.setSql(stringBuffer.toString());
        return sqlInfo;
    }

    public static SqlInfo buildReplaceSqlInfo(DbUtils dbUtils, Object obj) {
        List<KeyValue> entity2KeyValueList = entity2KeyValueList(dbUtils, obj);
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("REPLACE INTO ");
        stringBuffer.append(Table.get(obj.getClass()).getTableName());
        stringBuffer.append(" (");
        for (KeyValue keyValue : entity2KeyValueList) {
            stringBuffer.append(keyValue.getKey());
            stringBuffer.append(",");
            sqlInfo.addBindArg(keyValue.getValue());
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(") VALUES (");
        int size = entity2KeyValueList.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(")");
        sqlInfo.setSql(stringBuffer.toString());
        return sqlInfo;
    }

    private static String buildDeleteSqlByTableName(String str) {
        return "DELETE FROM " + str;
    }

    public static SqlInfo buildDeleteSqlInfo(Object obj) throws Exception {
        SqlInfo sqlInfo = new SqlInfo();
        Table table = Table.get(obj.getClass());
        Id id = table.getId();
        Object columnValue = id.getColumnValue(obj);
        if (columnValue == null) {
            throw new Exception("this entity[" + obj.getClass() + "]'s id value is null");
        }
        sqlInfo.setSql(buildDeleteSqlByTableName(table.getTableName()) + " WHERE " + WhereBuilder.b(id.getColumnName(), "=", columnValue));
        return sqlInfo;
    }

    public static SqlInfo buildDeleteSqlInfo(Class<?> cls, Object obj) throws Exception {
        SqlInfo sqlInfo = new SqlInfo();
        Table table = Table.get(cls);
        Id id = table.getId();
        if (obj == null) {
            throw new Exception("this entity[" + cls + "]'s id value is null");
        }
        sqlInfo.setSql(buildDeleteSqlByTableName(table.getTableName()) + " WHERE " + WhereBuilder.b(id.getColumnName(), "=", obj));
        return sqlInfo;
    }

    public static SqlInfo buildDeleteSqlInfo(Class<?> cls, WhereBuilder whereBuilder) {
        StringBuilder sb = new StringBuilder(buildDeleteSqlByTableName(Table.get(cls).getTableName()));
        if (whereBuilder != null) {
            sb.append(" WHERE ");
            sb.append(whereBuilder.toString());
        }
        return new SqlInfo(sb.toString());
    }

    public static SqlInfo buildUpdateSqlInfo(DbUtils dbUtils, Object obj) throws Exception {
        List<KeyValue> entity2KeyValueList = entity2KeyValueList(dbUtils, obj);
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        Table table = Table.get(obj.getClass());
        Id id = table.getId();
        Object columnValue = id.getColumnValue(obj);
        if (columnValue == null) {
            throw new Exception("this entity[" + obj.getClass() + "]'s id value is null");
        }
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer("UPDATE ");
        stringBuffer.append(table.getTableName());
        stringBuffer.append(" SET ");
        for (KeyValue keyValue : entity2KeyValueList) {
            if (keyValue.getValue() != null) {
                stringBuffer.append(keyValue.getKey());
                stringBuffer.append("=?,");
                sqlInfo.addBindArg(keyValue.getValue());
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(" WHERE ");
        stringBuffer.append(WhereBuilder.b(id.getColumnName(), "=", columnValue));
        sqlInfo.setSql(stringBuffer.toString());
        return sqlInfo;
    }

    public static SqlInfo buildUpdateSqlInfo(DbUtils dbUtils, Object obj, WhereBuilder whereBuilder) {
        List<KeyValue> entity2KeyValueList = entity2KeyValueList(dbUtils, obj);
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        Table table = Table.get(obj.getClass());
        SqlInfo sqlInfo = new SqlInfo();
        StringBuffer stringBuffer = new StringBuffer("UPDATE ");
        stringBuffer.append(table.getTableName());
        stringBuffer.append(" SET ");
        for (KeyValue keyValue : entity2KeyValueList) {
            stringBuffer.append(keyValue.getKey());
            stringBuffer.append("=?,");
            sqlInfo.addBindArg(keyValue.getValue());
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        if (whereBuilder != null) {
            stringBuffer.append(" WHERE ");
            stringBuffer.append(whereBuilder.toString());
        }
        sqlInfo.setSql(stringBuffer.toString());
        return sqlInfo;
    }

    public static SqlInfo buildCreateTableSqlInfo(Class<?> cls) {
        Table table = Table.get(cls);
        Id id = table.getId();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE IF NOT EXISTS ");
        stringBuffer.append(table.getTableName());
        stringBuffer.append(" ( ");
        if (id.isAutoIncrement()) {
            stringBuffer.append("\"");
            stringBuffer.append(id.getColumnName());
            stringBuffer.append("\"  ");
            stringBuffer.append("INTEGER PRIMARY KEY AUTOINCREMENT,");
        } else {
            stringBuffer.append("\"");
            stringBuffer.append(id.getColumnName());
            stringBuffer.append("\"  ");
            stringBuffer.append(id.getColumnDbType());
            stringBuffer.append(" PRIMARY KEY,");
        }
        for (Column column : table.columnMap.values()) {
            if (!(column instanceof Finder)) {
                stringBuffer.append("\"");
                stringBuffer.append(column.getColumnName());
                stringBuffer.append("\"  ");
                stringBuffer.append(column.getColumnDbType());
                if (ColumnUtils.isUnique(column.getColumnField())) {
                    stringBuffer.append(" UNIQUE");
                }
                if (ColumnUtils.isNotNull(column.getColumnField())) {
                    stringBuffer.append(" NOT NULL");
                }
                String check = ColumnUtils.getCheck(column.getColumnField());
                if (check != null) {
                    stringBuffer.append(" CHECK(");
                    stringBuffer.append(check);
                    stringBuffer.append(")");
                }
                stringBuffer.append(",");
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(" )");
        return new SqlInfo(stringBuffer.toString());
    }

    private static KeyValue column2KeyValue(Object obj, Column column) {
        String columnName = column.getColumnName();
        Object columnValue = column.getColumnValue(obj);
        if (columnValue == null) {
            columnValue = column.getDefaultValue();
        }
        if (columnName == null || columnValue == null) {
            return null;
        }
        return new KeyValue(columnName, columnValue);
    }

    public static List<KeyValue> entity2KeyValueList(DbUtils dbUtils, Object obj) {
        ArrayList arrayList = new ArrayList();
        Table table = Table.get(obj.getClass());
        Id id = table.getId();
        Object idValue = TableUtils.getIdValue(obj);
        if (id != null && idValue != null) {
            arrayList.add(new KeyValue(id.getColumnName(), idValue));
        }
        for (Column column : table.columnMap.values()) {
            if (column instanceof Finder) {
                ((Finder) column).db = dbUtils;
            } else if (column instanceof Foreign) {
                ((Foreign) column).db = dbUtils;
            }
            KeyValue column2KeyValue = column2KeyValue(obj, column);
            if (column2KeyValue != null) {
                arrayList.add(column2KeyValue);
            }
        }
        return arrayList;
    }
}
