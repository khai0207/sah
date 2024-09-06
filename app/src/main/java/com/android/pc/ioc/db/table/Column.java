package com.android.pc.ioc.db.table;

import com.android.pc.ioc.app.Ioc;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class Column {
    protected Field columnField;
    protected String columnName;
    private Object defaultValue;
    protected Method getMethod;
    protected Method setMethod;

    protected Column(Class cls, Field field) {
        this.columnField = field;
        this.columnName = ColumnUtils.getColumnNameByField(field);
        this.defaultValue = ColumnUtils.getColumnDefaultValue(field);
        this.getMethod = ColumnUtils.getColumnGetMethod(cls, field);
        this.setMethod = ColumnUtils.getColumnSetMethod(cls, field);
    }

    public void setValue2Entity(Object obj, String str) {
        Object valueStr2SimpleTypeFieldValue = str != null ? ColumnUtils.valueStr2SimpleTypeFieldValue(this.columnField.getType(), str) : null;
        Method method = this.setMethod;
        if (method != null) {
            try {
                Object[] objArr = new Object[1];
                if (valueStr2SimpleTypeFieldValue == null) {
                    valueStr2SimpleTypeFieldValue = this.defaultValue;
                }
                objArr[0] = valueStr2SimpleTypeFieldValue;
                method.invoke(obj, objArr);
                return;
            } catch (Exception e) {
                Ioc.getIoc().getLogger().e(e);
                return;
            }
        }
        try {
            this.columnField.setAccessible(true);
            Field field = this.columnField;
            if (valueStr2SimpleTypeFieldValue == null) {
                valueStr2SimpleTypeFieldValue = this.defaultValue;
            }
            field.set(obj, valueStr2SimpleTypeFieldValue);
        } catch (Exception e2) {
            Ioc.getIoc().getLogger().e(e2);
        }
    }

    public Object getColumnValue(Object obj) {
        Object obj2;
        if (obj != null) {
            Method method = this.getMethod;
            if (method != null) {
                try {
                    obj2 = method.invoke(obj, new Object[0]);
                } catch (Exception e) {
                    Ioc.getIoc().getLogger().e(e);
                }
            } else {
                try {
                    this.columnField.setAccessible(true);
                    obj2 = this.columnField.get(obj);
                } catch (Exception e2) {
                    Ioc.getIoc().getLogger().e(e2);
                }
            }
            return ColumnUtils.convert2DbColumnValueIfNeeded(obj2);
        }
        obj2 = null;
        return ColumnUtils.convert2DbColumnValueIfNeeded(obj2);
    }

    public String getColumnName() {
        return this.columnName;
    }

    public Object getDefaultValue() {
        return this.defaultValue;
    }

    public Field getColumnField() {
        return this.columnField;
    }

    public String getColumnDbType() {
        return ColumnUtils.fieldType2DbType(this.columnField.getType());
    }
}
