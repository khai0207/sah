package com.android.pc.ioc.db.sqlite;

import com.android.pc.ioc.db.table.Foreign;
import com.android.pc.ioc.db.table.TableUtils;
import java.util.List;

/* loaded from: classes.dex */
public class ForeignLazyLoader<T> {
    private Object columnValue;
    private Foreign foreignColumn;

    public ForeignLazyLoader(Class<?> cls, String str, Object obj) {
        this.foreignColumn = (Foreign) TableUtils.getColumnOrId(cls, str);
        this.columnValue = obj;
    }

    public ForeignLazyLoader(Foreign foreign, Object obj) {
        this.foreignColumn = foreign;
        this.columnValue = obj;
    }

    public List<T> getAllFromDb() {
        Foreign foreign = this.foreignColumn;
        if (foreign == null || foreign.db == null) {
            return null;
        }
        return this.foreignColumn.db.findAll(Selector.from(this.foreignColumn.getForeignEntityType()).where(this.foreignColumn.getForeignColumnName(), "=", this.columnValue));
    }

    public T getFirstFromDb() {
        Foreign foreign = this.foreignColumn;
        if (foreign == null || foreign.db == null) {
            return null;
        }
        return (T) this.foreignColumn.db.findFirst(Selector.from(this.foreignColumn.getForeignEntityType()).where(this.foreignColumn.getForeignColumnName(), "=", this.columnValue));
    }

    public Object getColumnValue() {
        return this.columnValue;
    }
}
