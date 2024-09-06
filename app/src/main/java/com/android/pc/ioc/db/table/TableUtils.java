package com.android.pc.ioc.db.table;

import android.text.TextUtils;
import com.android.pc.ioc.app.Ioc;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class TableUtils {
    private static ConcurrentHashMap<String, HashMap<String, Column>> entityColumnsMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Id> entityIdMap = new ConcurrentHashMap<>();

    private TableUtils() {
    }

    public static String getTableName(Class<?> cls) {
        com.android.pc.ioc.db.annotation.Table table = (com.android.pc.ioc.db.annotation.Table) cls.getAnnotation(com.android.pc.ioc.db.annotation.Table.class);
        if (table == null || TextUtils.isEmpty(table.name())) {
            return cls.getName().replace('.', '_');
        }
        return table.name();
    }

    public static synchronized HashMap<String, Column> getColumnMap(Class<?> cls) {
        synchronized (TableUtils.class) {
            if (entityColumnsMap.containsKey(cls.getCanonicalName())) {
                return entityColumnsMap.get(cls.getCanonicalName());
            }
            HashMap<String, Column> hashMap = new HashMap<>();
            addColumns2Map(cls, getPrimaryKeyFieldName(cls), hashMap);
            entityColumnsMap.put(cls.getCanonicalName(), hashMap);
            return hashMap;
        }
    }

    private static void addColumns2Map(Class<?> cls, String str, HashMap<String, Column> hashMap) {
        if (Object.class.equals(cls)) {
            return;
        }
        try {
            for (Field field : cls.getDeclaredFields()) {
                if (!ColumnUtils.isTransient(field) && !Modifier.isStatic(field.getModifiers())) {
                    if (ColumnUtils.isSimpleColumnType(field)) {
                        if (!field.getName().equals(str)) {
                            Column column = new Column(cls, field);
                            if (!hashMap.containsKey(column.getColumnName())) {
                                hashMap.put(column.getColumnName(), column);
                            }
                        }
                    } else if (ColumnUtils.isForeign(field)) {
                        Foreign foreign = new Foreign(cls, field);
                        if (!hashMap.containsKey(foreign.getColumnName())) {
                            hashMap.put(foreign.getColumnName(), foreign);
                        }
                    } else if (ColumnUtils.isFinder(field)) {
                        Finder finder = new Finder(cls, field);
                        if (!hashMap.containsKey(finder.getColumnName())) {
                            hashMap.put(finder.getColumnName(), finder);
                        }
                    }
                }
            }
            if (Object.class.equals(cls.getSuperclass())) {
                return;
            }
            addColumns2Map(cls.getSuperclass(), str, hashMap);
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
        }
    }

    public static Column getColumnOrId(Class<?> cls, String str) {
        if (getPrimaryKeyColumnName(cls).equals(str)) {
            return Table.get(cls).getId();
        }
        return getColumnMap(cls).get(str);
    }

    public static Column getColumnOrId(Class<?> cls, Field field) {
        String columnNameByField = ColumnUtils.getColumnNameByField(field);
        if (getPrimaryKeyColumnName(cls).equals(columnNameByField)) {
            return Table.get(cls).getId();
        }
        return getColumnMap(cls).get(columnNameByField);
    }

    public static synchronized Id getId(Class<?> cls) {
        synchronized (TableUtils.class) {
            if (Object.class.equals(cls)) {
                throw new RuntimeException("field 'id' not found");
            }
            if (entityIdMap.containsKey(cls.getCanonicalName())) {
                return entityIdMap.get(cls.getCanonicalName());
            }
            Field field = null;
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null) {
                int length = declaredFields.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Field field2 = declaredFields[i];
                    if (field2.getAnnotation(com.android.pc.ioc.db.annotation.Id.class) != null) {
                        field = field2;
                        break;
                    }
                    i++;
                }
                if (field == null) {
                    for (Field field3 : declaredFields) {
                        if (!"id".equals(field3.getName()) && !TransferTable.COLUMN_ID.equals(field3.getName())) {
                        }
                        field = field3;
                        break;
                    }
                }
            }
            if (field == null) {
                return getId(cls.getSuperclass());
            }
            Id id = new Id(cls, field);
            entityIdMap.put(cls.getCanonicalName(), id);
            return id;
        }
    }

    private static String getPrimaryKeyFieldName(Class<?> cls) {
        Id id = getId(cls);
        if (id == null) {
            return null;
        }
        return id.getColumnField().getName();
    }

    private static String getPrimaryKeyColumnName(Class<?> cls) {
        Id id = getId(cls);
        if (id == null) {
            return null;
        }
        return id.getColumnName();
    }

    public static Object getIdValue(Object obj) {
        Object columnValue;
        if (obj == null) {
            return null;
        }
        try {
            Id id = getId(obj.getClass());
            if (id != null && (columnValue = id.getColumnValue(obj)) != null && !columnValue.equals(0)) {
                if (columnValue.toString().length() > 0) {
                    return columnValue;
                }
            }
            return null;
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
            return null;
        }
    }
}
