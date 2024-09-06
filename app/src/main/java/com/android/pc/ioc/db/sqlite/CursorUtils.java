package com.android.pc.ioc.db.sqlite;

import android.database.Cursor;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.db.table.Column;
import com.android.pc.ioc.db.table.DbModel;
import com.android.pc.ioc.db.table.Finder;
import com.android.pc.ioc.db.table.Foreign;
import com.android.pc.ioc.db.table.Table;

/* loaded from: classes.dex */
public class CursorUtils {
    public static <T> T getEntity(DbUtils dbUtils, Cursor cursor, Class<T> cls, long j) {
        if (dbUtils != null && cursor != null) {
            EntityTempCache.setSeq(j);
            try {
                Table table = Table.get(cls);
                String string = cursor.getString(cursor.getColumnIndex(table.getId().getColumnName()));
                T t = (T) EntityTempCache.get(cls, string);
                if (t != null) {
                    return t;
                }
                T newInstance = cls.newInstance();
                EntityTempCache.put(newInstance, string);
                int columnCount = cursor.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = cursor.getColumnName(i);
                    Column column = table.columnMap.get(columnName);
                    if (column != null) {
                        if (column instanceof Foreign) {
                            Foreign foreign = (Foreign) column;
                            if (foreign.getFieldValue(newInstance) == null) {
                                foreign.db = dbUtils;
                                foreign.setValue2Entity(newInstance, cursor.getString(i));
                            }
                        } else {
                            column.setValue2Entity(newInstance, cursor.getString(i));
                        }
                    } else if (columnName.equals(table.getId().getColumnName())) {
                        table.getId().setValue2Entity(newInstance, cursor.getString(i));
                    }
                }
                for (Column column2 : table.columnMap.values()) {
                    if (column2 instanceof Finder) {
                        Finder finder = (Finder) column2;
                        if (finder.getFieldValue(newInstance) == null) {
                            finder.db = dbUtils;
                            finder.setValue2Entity(newInstance, null);
                        }
                    }
                }
                return newInstance;
            } catch (Exception e) {
                Ioc.getIoc().getLogger().e(e);
            }
        }
        return null;
    }

    public static DbModel getDbModel(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        DbModel dbModel = new DbModel();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            dbModel.add(cursor.getColumnName(i), cursor.getString(i));
        }
        return dbModel;
    }

    /* loaded from: classes.dex */
    public static class FindCacheSequence {
        private static long seq;
        private static final String FOREIGN_LAZY_LOADER_CLASS_NAME = ForeignLazyLoader.class.getName();
        private static final String FINDER_LAZY_LOADER_CLASS_NAME = FinderLazyLoader.class.getName();

        public static long getSeq() {
            String className = Thread.currentThread().getStackTrace()[4].getClassName();
            if (!className.equals(FOREIGN_LAZY_LOADER_CLASS_NAME) && !className.equals(FINDER_LAZY_LOADER_CLASS_NAME)) {
                seq++;
            }
            return seq;
        }
    }

    /* loaded from: classes.dex */
    private static class EntityTempCache {
        private static final DoubleKeyValueMap<Class, String, Object> cache = new DoubleKeyValueMap<>();
        private static long seq = 0;

        private EntityTempCache() {
        }

        public static void put(Object obj, String str) {
            cache.put(obj.getClass(), str, obj);
        }

        public static <T> T get(Class<T> cls, String str) {
            return (T) cache.get(cls, str);
        }

        public static void setSeq(long j) {
            if (seq != j) {
                cache.clear();
                seq = j;
            }
        }
    }
}
