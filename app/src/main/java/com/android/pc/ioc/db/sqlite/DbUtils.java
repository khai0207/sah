package com.android.pc.ioc.db.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.db.sqlite.CursorUtils;
import com.android.pc.ioc.db.table.DbModel;
import com.android.pc.ioc.db.table.KeyValue;
import com.android.pc.ioc.db.table.Table;
import com.android.pc.ioc.db.table.TableUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class DbUtils {
    private static HashMap<String, DbUtils> daoMap = new HashMap<>();
    private DaoConfig config;
    private SQLiteDatabase database;
    private boolean debug = false;
    private boolean allowTransaction = false;
    private final FindTempCache findTempCache = new FindTempCache();

    /* loaded from: classes.dex */
    public interface DbUpgradeListener {
        void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
    }

    private DbUtils(DaoConfig daoConfig) {
        if (daoConfig == null) {
            throw new IllegalArgumentException("daoConfig may not be null");
        }
        if (daoConfig.getContext() == null) {
            throw new IllegalArgumentException("context mey not be null");
        }
        if (TextUtils.isEmpty(daoConfig.getSdCardPath())) {
            this.database = new SQLiteDbHelper(daoConfig).getWritableDatabase();
        } else {
            this.database = createDbFileOnSDCard(daoConfig);
        }
        this.config = daoConfig;
    }

    private static synchronized DbUtils getInstance(DaoConfig daoConfig) {
        DbUtils dbUtils;
        synchronized (DbUtils.class) {
            dbUtils = daoMap.get(daoConfig.getDbName());
            if (dbUtils == null) {
                dbUtils = new DbUtils(daoConfig);
                daoMap.put(daoConfig.getDbName(), dbUtils);
            } else {
                dbUtils.config = daoConfig;
            }
        }
        return dbUtils;
    }

    public static DbUtils create(Context context) {
        return getInstance(new DaoConfig(context));
    }

    public static DbUtils create(Context context, String str) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbName(str);
        return getInstance(daoConfig);
    }

    public static DbUtils create(Context context, String str, String str2) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setSdCardPath(str);
        daoConfig.setDbName(str2);
        return getInstance(daoConfig);
    }

    public static DbUtils create(Context context, String str, int i, DbUpgradeListener dbUpgradeListener) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbName(str);
        daoConfig.setDbVersion(i);
        daoConfig.setDbUpgradeListener(dbUpgradeListener);
        return getInstance(daoConfig);
    }

    public static DbUtils create(Context context, String str, String str2, int i, DbUpgradeListener dbUpgradeListener) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setSdCardPath(str);
        daoConfig.setDbName(str2);
        daoConfig.setDbVersion(i);
        daoConfig.setDbUpgradeListener(dbUpgradeListener);
        return getInstance(daoConfig);
    }

    public static DbUtils create(DaoConfig daoConfig) {
        return getInstance(daoConfig);
    }

    public DbUtils configDebug(boolean z) {
        this.debug = z;
        return this;
    }

    public DbUtils configAllowTransaction(boolean z) {
        this.allowTransaction = z;
        return this;
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    public String getSdCardPath() {
        return this.config.getSdCardPath();
    }

    public void saveOrUpdate(Object obj) {
        try {
            beginTransaction();
            saveOrUpdateWithoutTransaction(obj);
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void saveOrUpdateAll(List<?> list) {
        try {
            beginTransaction();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                saveOrUpdateWithoutTransaction(it.next());
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void replace(Object obj) {
        try {
            beginTransaction();
            replaceWithoutTransaction(obj);
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void replaceAll(List<?> list) {
        try {
            beginTransaction();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                replaceWithoutTransaction(it.next());
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void save(Object obj) {
        try {
            beginTransaction();
            saveWithoutTransaction(obj);
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void saveAll(List<?> list) {
        try {
            beginTransaction();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                saveWithoutTransaction(it.next());
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public boolean saveBindingId(Object obj) {
        try {
            beginTransaction();
            boolean saveBindingIdWithoutTransaction = saveBindingIdWithoutTransaction(obj);
            setTransactionSuccessful();
            return saveBindingIdWithoutTransaction;
        } finally {
            endTransaction();
        }
    }

    public void saveBindingIdAll(List<?> list) {
        try {
            beginTransaction();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                if (!saveBindingIdWithoutTransaction(it.next())) {
                    Ioc.getIoc().getLogger().e("saveBindingId error, transaction will not commit!");
                }
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void delete(Object obj) {
        if (tableIsExist(obj.getClass())) {
            try {
                beginTransaction();
                deleteWithoutTransaction(obj);
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void deleteAll(List<?> list) {
        if (list == null || list.size() < 1 || !tableIsExist(list.get(0).getClass())) {
            return;
        }
        try {
            beginTransaction();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                deleteWithoutTransaction(it.next());
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void deleteById(Class<?> cls, Object obj) {
        if (tableIsExist(cls)) {
            try {
                try {
                    beginTransaction();
                    execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(cls, obj));
                    setTransactionSuccessful();
                } catch (Exception e) {
                    Ioc.getIoc().getLogger().e(e);
                }
            } finally {
                endTransaction();
            }
        }
    }

    public void delete(Class<?> cls, WhereBuilder whereBuilder) {
        if (tableIsExist(cls)) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(cls, whereBuilder));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void update(Object obj) {
        if (tableIsExist(obj.getClass())) {
            try {
                beginTransaction();
                updateWithoutTransaction(obj);
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void updateAll(List<?> list) {
        if (list == null || list.size() < 1 || !tableIsExist(list.get(0).getClass())) {
            return;
        }
        try {
            beginTransaction();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                updateWithoutTransaction(it.next());
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void update(Object obj, WhereBuilder whereBuilder) {
        if (tableIsExist(obj.getClass())) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, obj, whereBuilder));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public <T> T findById(Class<T> cls, Object obj) {
        if (!tableIsExist(cls)) {
            return null;
        }
        String selector = Selector.from(cls).where(Table.get(cls).getId().getColumnName(), "=", obj).limit(1).toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        this.findTempCache.setSeq(seq);
        T t = (T) this.findTempCache.get(selector);
        if (t != null) {
            return t;
        }
        Cursor execQuery = execQuery(selector);
        try {
            if (!execQuery.moveToNext()) {
                return null;
            }
            T t2 = (T) CursorUtils.getEntity(this, execQuery, cls, seq);
            this.findTempCache.put(selector, t2);
            return t2;
        } finally {
            IOUtils.closeQuietly(execQuery);
        }
    }

    public <T> T findFirst(Selector selector) {
        if (!tableIsExist(selector.getEntityType())) {
            return null;
        }
        String selector2 = selector.limit(1).toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        this.findTempCache.setSeq(seq);
        T t = (T) this.findTempCache.get(selector2);
        if (t != null) {
            return t;
        }
        Cursor execQuery = execQuery(selector2);
        try {
            if (!execQuery.moveToNext()) {
                return null;
            }
            T t2 = (T) CursorUtils.getEntity(this, execQuery, selector.getEntityType(), seq);
            this.findTempCache.put(selector2, t2);
            return t2;
        } finally {
            IOUtils.closeQuietly(execQuery);
        }
    }

    public <T> T findFirst(Object obj) {
        if (!tableIsExist(obj.getClass())) {
            return null;
        }
        Selector from = Selector.from(obj.getClass());
        List<KeyValue> entity2KeyValueList = SqlInfoBuilder.entity2KeyValueList(this, obj);
        if (entity2KeyValueList != null) {
            WhereBuilder b = WhereBuilder.b();
            for (KeyValue keyValue : entity2KeyValueList) {
                b.append(keyValue.getKey(), "=", keyValue.getValue());
            }
            from.where(b);
        }
        return (T) findFirst(from);
    }

    public <T> List<T> findAll(Selector selector) {
        if (!tableIsExist(selector.getEntityType())) {
            return null;
        }
        String selector2 = selector.toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        this.findTempCache.setSeq(seq);
        Object obj = this.findTempCache.get(selector2);
        if (obj != null) {
            return (List) obj;
        }
        Cursor execQuery = execQuery(selector2);
        ArrayList arrayList = new ArrayList();
        while (execQuery.moveToNext()) {
            try {
                arrayList.add(CursorUtils.getEntity(this, execQuery, selector.getEntityType(), seq));
            } finally {
                IOUtils.closeQuietly(execQuery);
            }
        }
        this.findTempCache.put(selector2, arrayList);
        return arrayList;
    }

    public <T> List<T> findAll(Object obj) {
        if (!tableIsExist(obj.getClass())) {
            return null;
        }
        Selector from = Selector.from(obj.getClass());
        List<KeyValue> entity2KeyValueList = SqlInfoBuilder.entity2KeyValueList(this, obj);
        if (entity2KeyValueList != null) {
            WhereBuilder b = WhereBuilder.b();
            for (KeyValue keyValue : entity2KeyValueList) {
                b.append(keyValue.getKey(), "=", keyValue.getValue());
            }
            from.where(b);
        }
        return findAll(from);
    }

    public DbModel findDbModelFirst(SqlInfo sqlInfo) {
        Cursor execQuery = execQuery(sqlInfo);
        try {
            if (execQuery.moveToNext()) {
                return CursorUtils.getDbModel(execQuery);
            }
            IOUtils.closeQuietly(execQuery);
            return null;
        } finally {
            IOUtils.closeQuietly(execQuery);
        }
    }

    public DbModel findDbModelFirst(DbModelSelector dbModelSelector) {
        if (!tableIsExist(dbModelSelector.getEntityType())) {
            return null;
        }
        Cursor execQuery = execQuery(dbModelSelector.limit(1).toString());
        try {
            if (execQuery.moveToNext()) {
                return CursorUtils.getDbModel(execQuery);
            }
            return null;
        } finally {
            IOUtils.closeQuietly(execQuery);
        }
    }

    public List<DbModel> findDbModelAll(SqlInfo sqlInfo) {
        Cursor execQuery = execQuery(sqlInfo);
        ArrayList arrayList = new ArrayList();
        while (execQuery.moveToNext()) {
            try {
                arrayList.add(CursorUtils.getDbModel(execQuery));
            } finally {
                IOUtils.closeQuietly(execQuery);
            }
        }
        return arrayList;
    }

    public List<DbModel> findDbModelAll(DbModelSelector dbModelSelector) {
        if (!tableIsExist(dbModelSelector.getEntityType())) {
            return null;
        }
        Cursor execQuery = execQuery(dbModelSelector.toString());
        ArrayList arrayList = new ArrayList();
        while (execQuery.moveToNext()) {
            try {
                arrayList.add(CursorUtils.getDbModel(execQuery));
            } finally {
                IOUtils.closeQuietly(execQuery);
            }
        }
        return arrayList;
    }

    /* loaded from: classes.dex */
    public static class DaoConfig {
        private Context context;
        private DbUpgradeListener dbUpgradeListener;
        private String sdCardPath;
        private String dbName = "xUtils.db";
        private int dbVersion = 1;

        public DaoConfig(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return this.context;
        }

        public String getDbName() {
            return this.dbName;
        }

        public void setDbName(String str) {
            this.dbName = str;
        }

        public int getDbVersion() {
            return this.dbVersion;
        }

        public void setDbVersion(int i) {
            this.dbVersion = i;
        }

        public DbUpgradeListener getDbUpgradeListener() {
            return this.dbUpgradeListener;
        }

        public void setDbUpgradeListener(DbUpgradeListener dbUpgradeListener) {
            this.dbUpgradeListener = dbUpgradeListener;
        }

        public String getSdCardPath() {
            return this.sdCardPath;
        }

        public void setSdCardPath(String str) {
            this.sdCardPath = str;
        }
    }

    /* loaded from: classes.dex */
    private class SQLiteDbHelper extends SQLiteOpenHelper {
        private DbUpgradeListener mDbUpgradeListener;

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        public SQLiteDbHelper(DaoConfig daoConfig) {
            super(daoConfig.getContext(), daoConfig.getDbName(), (SQLiteDatabase.CursorFactory) null, daoConfig.getDbVersion());
            this.mDbUpgradeListener = daoConfig.getDbUpgradeListener();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            DbUpgradeListener dbUpgradeListener = this.mDbUpgradeListener;
            if (dbUpgradeListener != null) {
                dbUpgradeListener.onUpgrade(sQLiteDatabase, i, i2);
                return;
            }
            try {
                DbUtils.this.dropDb();
            } catch (Exception e) {
                Ioc.getIoc().getLogger().e(e);
            }
        }
    }

    private SQLiteDatabase createDbFileOnSDCard(DaoConfig daoConfig) {
        int version;
        int dbVersion;
        File file = new File(daoConfig.getSdCardPath(), daoConfig.getDbName());
        boolean exists = file.exists();
        SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
        if (openOrCreateDatabase != null && (version = openOrCreateDatabase.getVersion()) != (dbVersion = daoConfig.getDbVersion())) {
            if (exists && daoConfig.getDbUpgradeListener() != null) {
                daoConfig.getDbUpgradeListener().onUpgrade(openOrCreateDatabase, version, dbVersion);
            }
            openOrCreateDatabase.setVersion(dbVersion);
        }
        return openOrCreateDatabase;
    }

    private void saveOrUpdateWithoutTransaction(Object obj) {
        if (TableUtils.getIdValue(obj) != null) {
            updateWithoutTransaction(obj);
        } else {
            saveBindingIdWithoutTransaction(obj);
        }
    }

    private void replaceWithoutTransaction(Object obj) {
        createTableIfNotExist(obj.getClass());
        execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, obj));
    }

    private void saveWithoutTransaction(Object obj) {
        createTableIfNotExist(obj.getClass());
        execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, obj));
    }

    private boolean saveBindingIdWithoutTransaction(Object obj) {
        createTableIfNotExist(obj.getClass());
        List<KeyValue> entity2KeyValueList = SqlInfoBuilder.entity2KeyValueList(this, obj);
        if (entity2KeyValueList == null || entity2KeyValueList.size() <= 0) {
            return false;
        }
        Table table = Table.get(obj.getClass());
        ContentValues contentValues = new ContentValues();
        fillContentValues(contentValues, entity2KeyValueList);
        Long valueOf = Long.valueOf(this.database.insert(table.getTableName(), null, contentValues));
        if (valueOf.longValue() == -1) {
            return false;
        }
        table.getId().setValue2Entity(obj, valueOf.toString());
        return true;
    }

    private void deleteWithoutTransaction(Object obj) {
        try {
            execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(obj));
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
        }
    }

    private void updateWithoutTransaction(Object obj) {
        try {
            execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, obj));
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
        }
    }

    private static void fillContentValues(ContentValues contentValues, List<KeyValue> list) {
        if (list != null && contentValues != null) {
            for (KeyValue keyValue : list) {
                contentValues.put(keyValue.getKey(), keyValue.getValue().toString());
            }
            return;
        }
        Ioc.getIoc().getLogger().w("List<KeyValue> is empty or ContentValues is empty!");
    }

    private void createTableIfNotExist(Class<?> cls) {
        if (tableIsExist(cls)) {
            return;
        }
        execNonQuery(SqlInfoBuilder.buildCreateTableSqlInfo(cls));
    }

    public boolean tableIsExist(Class<?> cls) {
        Table table = Table.get(cls);
        if (table.isCheckDatabase()) {
            return true;
        }
        Cursor cursor = null;
        try {
            cursor = execQuery("SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='" + table.getTableName() + "'");
            if (cursor == null || !cursor.moveToNext() || cursor.getInt(0) <= 0) {
                return false;
            }
            table.setCheckDatabase(true);
            return true;
        } finally {
            IOUtils.closeQuietly(cursor);
        }
    }

    public void dropDb() {
        Cursor cursor = null;
        try {
            cursor = execQuery("SELECT name FROM sqlite_master WHERE type ='table'");
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    try {
                        execNonQuery("DROP TABLE " + cursor.getString(0));
                    } catch (Exception e) {
                        Ioc.getIoc().getLogger().e(e);
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(cursor);
        }
    }

    public void dropTable(Class<?> cls) {
        if (tableIsExist(cls)) {
            execNonQuery("DROP TABLE " + Table.get(cls).getTableName());
        }
    }

    private void debugSql(String str) {
        if (this.config == null || !this.debug) {
            return;
        }
        Ioc.getIoc().getLogger().d(str);
    }

    private void beginTransaction() {
        if (this.allowTransaction) {
            this.database.beginTransaction();
        }
    }

    private void setTransactionSuccessful() {
        if (this.allowTransaction) {
            this.database.setTransactionSuccessful();
        }
    }

    private void endTransaction() {
        if (this.allowTransaction) {
            this.database.endTransaction();
        }
    }

    public void execNonQuery(SqlInfo sqlInfo) {
        debugSql(sqlInfo.getSql());
        try {
            if (sqlInfo.getBindArgs() != null) {
                this.database.execSQL(sqlInfo.getSql(), sqlInfo.getBindArgsAsArray());
            } else {
                this.database.execSQL(sqlInfo.getSql());
            }
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
        }
    }

    public void execNonQuery(String str) {
        debugSql(str);
        try {
            this.database.execSQL(str);
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
        }
    }

    public void deleteAll(Class cls) {
        try {
            this.database.delete(Table.get(cls).getTableName(), null, null);
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
        }
    }

    public Cursor execQuery(SqlInfo sqlInfo) {
        debugSql(sqlInfo.getSql());
        try {
            return this.database.rawQuery(sqlInfo.getSql(), sqlInfo.getBindArgsAsStrArray());
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
            return null;
        }
    }

    public Cursor execQuery(String str) {
        debugSql(str);
        try {
            return this.database.rawQuery(str, null);
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e);
            return null;
        }
    }

    /* loaded from: classes.dex */
    private class FindTempCache {
        private final ConcurrentHashMap<String, Object> cache;
        private long seq;

        /* synthetic */ FindTempCache(DbUtils dbUtils, AnonymousClass1 anonymousClass1) {
            this();
        }

        private FindTempCache() {
            this.cache = new ConcurrentHashMap<>();
            this.seq = 0L;
        }

        public void put(String str, Object obj) {
            this.cache.put(str, obj);
        }

        public Object get(String str) {
            return this.cache.get(str);
        }

        public void setSeq(long j) {
            if (this.seq != j) {
                this.cache.clear();
                this.seq = j;
            }
        }
    }
}
