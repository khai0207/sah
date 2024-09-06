package com.android.pc.ioc.db.table;

import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.db.sqlite.DbUtils;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class Finder extends Column {
    public DbUtils db;
    private String targetColumnName;
    private String valueColumnName;

    @Override // com.android.pc.ioc.db.table.Column
    public String getColumnDbType() {
        return "";
    }

    @Override // com.android.pc.ioc.db.table.Column
    public Object getColumnValue(Object obj) {
        return null;
    }

    @Override // com.android.pc.ioc.db.table.Column
    public Object getDefaultValue() {
        return null;
    }

    protected Finder(Class cls, Field field) {
        super(cls, field);
        com.android.pc.ioc.db.annotation.Finder finder = (com.android.pc.ioc.db.annotation.Finder) field.getAnnotation(com.android.pc.ioc.db.annotation.Finder.class);
        this.valueColumnName = finder.valueColumn();
        this.targetColumnName = finder.targetColumn();
    }

    public Class<?> getTargetEntityType() {
        return ColumnUtils.getFinderTargetEntityType(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.android.pc.ioc.db.table.Column
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setValue2Entity(java.lang.Object r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.reflect.Field r5 = r3.columnField
            java.lang.Class r5 = r5.getType()
            java.lang.Class r0 = r4.getClass()
            java.lang.String r1 = r3.valueColumnName
            com.android.pc.ioc.db.table.Column r0 = com.android.pc.ioc.db.table.TableUtils.getColumnOrId(r0, r1)
            java.lang.Object r0 = r0.getColumnValue(r4)
            java.lang.Class<com.android.pc.ioc.db.sqlite.FinderLazyLoader> r1 = com.android.pc.ioc.db.sqlite.FinderLazyLoader.class
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L22
            com.android.pc.ioc.db.sqlite.FinderLazyLoader r5 = new com.android.pc.ioc.db.sqlite.FinderLazyLoader
            r5.<init>(r3, r0)
            goto L58
        L22:
            java.lang.Class<java.util.List> r1 = java.util.List.class
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L41
            com.android.pc.ioc.db.sqlite.FinderLazyLoader r5 = new com.android.pc.ioc.db.sqlite.FinderLazyLoader     // Catch: java.lang.Exception -> L34
            r5.<init>(r3, r0)     // Catch: java.lang.Exception -> L34
            java.util.List r5 = r5.getAllFromDb()     // Catch: java.lang.Exception -> L34
            goto L58
        L34:
            r5 = move-exception
            com.android.pc.ioc.app.Ioc r0 = com.android.pc.ioc.app.Ioc.getIoc()
            com.android.pc.util.Logger r0 = r0.getLogger()
            r0.e(r5)
            goto L57
        L41:
            com.android.pc.ioc.db.sqlite.FinderLazyLoader r5 = new com.android.pc.ioc.db.sqlite.FinderLazyLoader     // Catch: java.lang.Exception -> L4b
            r5.<init>(r3, r0)     // Catch: java.lang.Exception -> L4b
            java.lang.Object r5 = r5.getFirstFromDb()     // Catch: java.lang.Exception -> L4b
            goto L58
        L4b:
            r5 = move-exception
            com.android.pc.ioc.app.Ioc r0 = com.android.pc.ioc.app.Ioc.getIoc()
            com.android.pc.util.Logger r0 = r0.getLogger()
            r0.e(r5)
        L57:
            r5 = 0
        L58:
            java.lang.reflect.Method r0 = r3.setMethod
            r1 = 1
            if (r0 == 0) goto L75
            java.lang.reflect.Method r0 = r3.setMethod     // Catch: java.lang.Exception -> L68
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L68
            r2 = 0
            r1[r2] = r5     // Catch: java.lang.Exception -> L68
            r0.invoke(r4, r1)     // Catch: java.lang.Exception -> L68
            goto L8c
        L68:
            r4 = move-exception
            com.android.pc.ioc.app.Ioc r5 = com.android.pc.ioc.app.Ioc.getIoc()
            com.android.pc.util.Logger r5 = r5.getLogger()
            r5.e(r4)
            goto L8c
        L75:
            java.lang.reflect.Field r0 = r3.columnField     // Catch: java.lang.Exception -> L80
            r0.setAccessible(r1)     // Catch: java.lang.Exception -> L80
            java.lang.reflect.Field r0 = r3.columnField     // Catch: java.lang.Exception -> L80
            r0.set(r4, r5)     // Catch: java.lang.Exception -> L80
            goto L8c
        L80:
            r4 = move-exception
            com.android.pc.ioc.app.Ioc r5 = com.android.pc.ioc.app.Ioc.getIoc()
            com.android.pc.util.Logger r5 = r5.getLogger()
            r5.e(r4)
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.ioc.db.table.Finder.setValue2Entity(java.lang.Object, java.lang.String):void");
    }

    public String getTargetColumnName() {
        return this.targetColumnName;
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
}
