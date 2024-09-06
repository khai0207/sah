package com.android.pc.ioc.db.table;

import com.android.pc.ioc.db.annotation.NoAutoIncrement;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class Id extends Column {
    protected Id(Class cls, Field field) {
        super(cls, field);
    }

    public boolean isAutoIncrement() {
        if (getColumnField().getAnnotation(NoAutoIncrement.class) != null) {
            return false;
        }
        Class<?> type = getColumnField().getType();
        return type.equals(Integer.TYPE) || type.equals(Integer.class) || type.equals(Long.TYPE) || type.equals(Long.class);
    }
}
