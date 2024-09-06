package com.android.pc.ioc.db.table;

import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.db.sqlite.DbUtils;
import com.android.pc.ioc.db.sqlite.ForeignLazyLoader;
import java.lang.reflect.Field;
import java.util.List;

/* loaded from: classes.dex */
public class Foreign extends Column {
    public DbUtils db;
    private String foreignColumnName;

    @Override // com.android.pc.ioc.db.table.Column
    public Object getDefaultValue() {
        return null;
    }

    protected Foreign(Class cls, Field field) {
        super(cls, field);
        this.foreignColumnName = ColumnUtils.getForeignColumnNameByField(field);
    }

    public String getForeignColumnName() {
        return this.foreignColumnName;
    }

    public Class<?> getForeignEntityType() {
        return ColumnUtils.getForeignEntityType(this);
    }

    public Class<?> getForeignColumnType() {
        return TableUtils.getColumnOrId(getForeignEntityType(), this.foreignColumnName).columnField.getType();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.android.pc.ioc.db.table.Column
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setValue2Entity(java.lang.Object r4, java.lang.String r5) {
        /*
            r3 = this;
            if (r5 == 0) goto L53
            java.lang.reflect.Field r0 = r3.columnField
            java.lang.Class r0 = r0.getType()
            java.lang.Class r1 = r3.getForeignColumnType()
            java.lang.Object r5 = com.android.pc.ioc.db.table.ColumnUtils.valueStr2SimpleTypeFieldValue(r1, r5)
            java.lang.Class<com.android.pc.ioc.db.sqlite.ForeignLazyLoader> r1 = com.android.pc.ioc.db.sqlite.ForeignLazyLoader.class
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L1e
            com.android.pc.ioc.db.sqlite.ForeignLazyLoader r0 = new com.android.pc.ioc.db.sqlite.ForeignLazyLoader
            r0.<init>(r3, r5)
            goto L54
        L1e:
            java.lang.Class<java.util.List> r1 = java.util.List.class
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3d
            com.android.pc.ioc.db.sqlite.ForeignLazyLoader r0 = new com.android.pc.ioc.db.sqlite.ForeignLazyLoader     // Catch: java.lang.Exception -> L30
            r0.<init>(r3, r5)     // Catch: java.lang.Exception -> L30
            java.util.List r0 = r0.getAllFromDb()     // Catch: java.lang.Exception -> L30
            goto L54
        L30:
            r5 = move-exception
            com.android.pc.ioc.app.Ioc r0 = com.android.pc.ioc.app.Ioc.getIoc()
            com.android.pc.util.Logger r0 = r0.getLogger()
            r0.e(r5)
            goto L53
        L3d:
            com.android.pc.ioc.db.sqlite.ForeignLazyLoader r0 = new com.android.pc.ioc.db.sqlite.ForeignLazyLoader     // Catch: java.lang.Exception -> L47
            r0.<init>(r3, r5)     // Catch: java.lang.Exception -> L47
            java.lang.Object r0 = r0.getFirstFromDb()     // Catch: java.lang.Exception -> L47
            goto L54
        L47:
            r5 = move-exception
            com.android.pc.ioc.app.Ioc r0 = com.android.pc.ioc.app.Ioc.getIoc()
            com.android.pc.util.Logger r0 = r0.getLogger()
            r0.e(r5)
        L53:
            r0 = 0
        L54:
            java.lang.reflect.Method r5 = r3.setMethod
            r1 = 1
            if (r5 == 0) goto L71
            java.lang.reflect.Method r5 = r3.setMethod     // Catch: java.lang.Exception -> L64
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L64
            r2 = 0
            r1[r2] = r0     // Catch: java.lang.Exception -> L64
            r5.invoke(r4, r1)     // Catch: java.lang.Exception -> L64
            goto L88
        L64:
            r4 = move-exception
            com.android.pc.ioc.app.Ioc r5 = com.android.pc.ioc.app.Ioc.getIoc()
            com.android.pc.util.Logger r5 = r5.getLogger()
            r5.e(r4)
            goto L88
        L71:
            java.lang.reflect.Field r5 = r3.columnField     // Catch: java.lang.Exception -> L7c
            r5.setAccessible(r1)     // Catch: java.lang.Exception -> L7c
            java.lang.reflect.Field r5 = r3.columnField     // Catch: java.lang.Exception -> L7c
            r5.set(r4, r0)     // Catch: java.lang.Exception -> L7c
            goto L88
        L7c:
            r4 = move-exception
            com.android.pc.ioc.app.Ioc r5 = com.android.pc.ioc.app.Ioc.getIoc()
            com.android.pc.util.Logger r5 = r5.getLogger()
            r5.e(r4)
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.ioc.db.table.Foreign.setValue2Entity(java.lang.Object, java.lang.String):void");
    }

    @Override // com.android.pc.ioc.db.table.Column
    public Object getColumnValue(Object obj) {
        Object fieldValue = getFieldValue(obj);
        if (fieldValue != null) {
            Class<?> type = this.columnField.getType();
            if (type.equals(ForeignLazyLoader.class)) {
                fieldValue = ((ForeignLazyLoader) fieldValue).getColumnValue();
            } else if (type.equals(List.class)) {
                try {
                    List<?> list = (List) fieldValue;
                    if (list.size() > 0) {
                        if (this.db != null) {
                            this.db.saveOrUpdateAll(list);
                        }
                        fieldValue = TableUtils.getColumnOrId(ColumnUtils.getForeignEntityType(this), this.foreignColumnName).getColumnValue(list.get(0));
                    }
                } catch (Exception e) {
                    Ioc.getIoc().getLogger().e(e);
                    fieldValue = null;
                    return ColumnUtils.convert2DbColumnValueIfNeeded(fieldValue);
                }
            } else {
                try {
                    if (this.db != null) {
                        try {
                            this.db.saveOrUpdate(fieldValue);
                        } catch (Exception e2) {
                            Ioc.getIoc().getLogger().e(e2);
                        }
                    }
                    fieldValue = TableUtils.getColumnOrId(type, this.foreignColumnName).getColumnValue(fieldValue);
                } catch (Exception e3) {
                    Ioc.getIoc().getLogger().e(e3);
                    fieldValue = null;
                    return ColumnUtils.convert2DbColumnValueIfNeeded(fieldValue);
                }
            }
        }
        return ColumnUtils.convert2DbColumnValueIfNeeded(fieldValue);
    }

    public Object getFieldValue(Object obj) {
        if (obj != null) {
            if (this.getMethod != null) {
                try {
                    return this.getMethod.invoke(obj, new Object[0]);
                } catch (Exception e) {
                    Ioc.getIoc().getLogger().e(e);
                }
            } else {
                try {
                    this.columnField.setAccessible(true);
                    return this.columnField.get(obj);
                } catch (Exception e2) {
                    Ioc.getIoc().getLogger().e(e2);
                }
            }
        }
        return null;
    }

    @Override // com.android.pc.ioc.db.table.Column
    public String getColumnDbType() {
        return ColumnUtils.fieldType2DbType(getForeignColumnType());
    }
}
