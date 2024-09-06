package com.android.pc.ioc.db.sqlite;

import com.android.pc.ioc.db.table.Finder;
import com.android.pc.ioc.db.table.TableUtils;
import java.util.List;

/* loaded from: classes.dex */
public class FinderLazyLoader<T> {
    private Finder finderColumn;
    private Object finderValue;

    public FinderLazyLoader(Class<?> cls, String str, Object obj) {
        this.finderColumn = (Finder) TableUtils.getColumnOrId(cls, str);
        this.finderValue = obj;
    }

    public FinderLazyLoader(Finder finder, Object obj) {
        this.finderColumn = finder;
        this.finderValue = obj;
    }

    public List<T> getAllFromDb() {
        Finder finder = this.finderColumn;
        if (finder == null || finder.db == null) {
            return null;
        }
        return this.finderColumn.db.findAll(Selector.from(this.finderColumn.getTargetEntityType()).where(this.finderColumn.getTargetColumnName(), "=", this.finderValue));
    }

    public T getFirstFromDb() {
        Finder finder = this.finderColumn;
        if (finder == null || finder.db == null) {
            return null;
        }
        return (T) this.finderColumn.db.findFirst(Selector.from(this.finderColumn.getTargetEntityType()).where(this.finderColumn.getTargetColumnName(), "=", this.finderValue));
    }
}
